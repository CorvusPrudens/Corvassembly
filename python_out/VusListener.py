import sys
from antlr4 import *
from gen.CorParser import CorParser
from gen.CorListener import CorListener

from gen.CorLexer import CorLexer
from gen.CorParser import CorParser

import re
import math


class Variables:

  def __init__(self, sysvars=[], ramStartAddress=41):
    self.vars = sysvars
    self.size = {'ram': ramStartAddress, 'rom': 0, 'pre': 0}
    self.evalDict = {}
    for var in sysvars:
      if var['type'] == 'rom' or var['type'] == 'pre':
        self.evalDict[var['name']] = var['value']
    self.variableRegex = re.compile('\\b\\${0,1}[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b')
    self.addressRegex = re.compile('\\b\\$[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b')

  # ['varType', 'name', 'address', 'value']
  def add(self, varType, name, value):
    address = self.size[varType]
    self.size[varType] += 1
    self.vars.append({'type': varType, 'name': name, 'address': address, 'value': value})

    # for files that are imported, every variable and label is scoped with
    # a prefix, i.e. <imported.element>. These can still be evaluated by
    # the python eval method with a workaround -- if <imported> is added to
    # the variable dicionary as a class and <element> is added as an attribute
    # to that object, the evaluation succeeds.
    if varType != 'ram':
      if '.' in name:
        key = name[:name.find('.')]
        attribute = name[name.find('.') + 1:]
        try:
          setattr(self.evalDict[key], attribute, value)
        except KeyError:
          self.evalDict[key] = type(key, (object, ), {})
          setattr(self.evalDict[key], attribute, value)
      else:
        self.evalDict[name] = value

  def getVariables(self):
    return self.vars

  def getAddress(self, variable):
    variable = variable[1:]
    found = False
    for var in self.vars:
      if var['name'] == variable:
        return var['address']
    # TODO -- proper error throwing
    print(f'\nError: could not find address of undefined variable {variable}!\n')
    exit(1)

  def decrementAddress(self, varType):
    # to overload variable names
    self.size[varType] -= 1

  def calc(self, mathString, listener):

    match = self.variableRegex.search(mathString)
    while match != None:
      mathString = mathString[:match.start()] + listener.scope(match.group(0)) + mathString[match.end():]
      match = self.addressRegex.search(mathString, pos=match.end())

    match = self.addressRegex.search(mathString)
    while match != None:
      mathString = mathString[:match.start()] + str(self.getAddress(match.group(0))) + mathString[match.end():]
      match = self.addressRegex.search(mathString, pos=match.end())

    solution = 0
    # try:
    solution = round(eval(mathString, {}, self.evalDict))
    # except NameError:
    #   pass
    #   # errstr = "-> undefined assignment"
    #   # err(preserved, lines[i][0], errstr, 55)
    # except SyntaxError:
    #   pass
    #   # errstr = "-> invalid syntax"
    #   # err(preserved, lines[i][0], errstr, 56)
    # except AttributeError:
    #   pass
    #   # errstr = "-> scope does not contain element"
    #   # err(preserved, lines[i][0], errstr, 57)
    # except TypeError:
    #   pass
    #   # errstr = "-> undefined assignment"
    #   # err(preserved, lines[i][0], errstr, 58)

    return solution



class Instructions:

  def __init__(self):
    self.instructions = []
    self.size = 0

  # ['mnemonic', 'address', 'args']
  def add(self, mnemonic, args, line, path):
    address = self.size
    self.size += 1
    self.instructions.append({'mnemonic': mnemonic, 'address': address,
                              'arguments': args, 'line': line, 'path': path})

  def getAddress(self):
    return self.size

  def getInstructions(self):
    return self.instructions

  def insert(self, dictlist, num):
    for instruction in self.instructions:
      instruction['address'] += num
    self.instructions = dictlist + self.instructions


class Labels:

  def __init__(self):
    self.labels = []

  # ['label', 'address']
  def add(self, name, instr):
    address = instr.getAddress()
    self.labels.append({'name': name, 'address': address})

  def getLabels(self):
    return self.labels

  def insert(self, dictlist, num):
    for label in self.labels:
      label['address'] += num
    self.labels = dictlist + self.labels


class VusListener(CorListener) :

  def __init__(self, mainName, ramStartAddress_init, fullpath, sysvars_init):
    self.variables = Variables(ramStartAddress=ramStartAddress_init, sysvars=sysvars_init)
    self.instructions = Instructions()
    self.labels = Labels()
    self.mainName = mainName
    self.currentName = ''
    self.keywords = [
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
      'pre', 'ram', 'rom', 'gpu',
      'zero', 'carry', 'negative',
      'equal', 'greater', 'less',
      'UART', 'STACK', 'STATUS',
      'GPIO', 'GPIO_DIR', 'TX_EMPTY',
      'TX_FULL', 'RX_EMPTY', 'RX_FULL'
    ]
    self.variableRegex = re.compile('\\b\\${0,1}[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b')
    self.fullpath = fullpath
    self.stream = None

  def scope(self, input):
    if self.variableRegex.search(input) != None:
      if self.currentName != self.mainName and '.' not in input and input not in self.keywords:
        if '$' in input:
          input = '$' + self.currentName + '.' + input[1:]
        else:
          input = self.currentName + '.' + input
    return input

  def setName(self, name, fullpath, stream):
    self.currentName = name
    self.fullpath = fullpath
    self.stream = stream

  def getVariables(self):
    return self.variables

  def getInstructions(self):
    return self.instructions

  def getNumInstructions(self):
    return self.instructions.getAddress()

  def getLabels(self):
    return self.labels

  def reset(self):
    self.instructions = Instructions()
    self.labels = Labels()


  # Enter a parse tree produced by CorParser#parse.
  def enterParse(self, ctx:CorParser.ParseContext):
      pass

  # Exit a parse tree produced by CorParser#parse.
  def exitParse(self, ctx:CorParser.ParseContext):
      pass


  # Enter a parse tree produced by CorParser#block.
  def enterBlock(self, ctx:CorParser.BlockContext):
      templabel = self.scope(ctx.label().VARIABLE().getText())
      self.labels.add(templabel, self.instructions)
      pass

  def convertString(self, string):
    string = string.strip('\"')
    chars = []

    for c in string:
      # TODO -- doesn't sanitize escaped characters
      chars.append(ord(c))

    chars.append(0) #terminating character

    return chars

    # out = '{ '
    # for i in range(len(chars)):
    #   out += str(chars[i]) + ', '
    #
    # return out


  # Exit a parse tree produced by CorParser#assignment_arr.
  def exitAssignment_arr(self, ctx:CorParser.Assignment_arrContext):
      varType = ctx.CONST().getText()
      name = self.scope(ctx.array().VARIABLE().getText())
      numDimensions = ctx.array().getChildCount() - 3
      dimensions = []

      if numDimensions > 2:
        print('\n error constant array cannot be initialized with more than two dimension\n')
        exit(1)

      if numDimensions == 1 and ctx.array().arr_data() != None and (len(ctx.array().arr_data().string()) != 0 or len(ctx.array().arr_data().arr_data()) != 0):
        print(f'\n error one dimensional array \"{name}\" contains subarrays\n')
        exit(1)

      self.variables.add('pre', name, self.variables.size[varType])

      if numDimensions == 1:
        if ctx.array().string() != None:
          chars = self.convertString(ctx.array().string().getText())
          for i in range(len(chars)):
            self.variables.add(varType, name + f'[{i}]', chars[i])
        else:
          width = math.ceil((ctx.array().arr_data().getChildCount() - 2)/2.0)
          for i in range(width):
            tempval = self.variables.calc(ctx.array().arr_data().getChild(1 + i*2).getText(), self)
            self.variables.add(varType, name + f'[{i}]', tempval)

      pass


  # Exit a parse tree produced by CorParser#declaration.
  def exitDeclaration(self, ctx:CorParser.DeclarationContext):
      if ctx.getChildCount() == 2: # simple declaration
        self.variables.add(self.scope(ctx.RAM().getText()), ctx.VARIABLE().getText(), 0)
      else: # array
        # adding sneaky precompiler variable to mimic the behavior of C arrays
        self.variables.add('pre', self.scope(ctx.VARIABLE().getText()), self.variables.size['ram'])

        varType = ctx.RAM().getText()
        name = self.scope(ctx.VARIABLE().getText())
        dimensions = ctx.getChildCount() - 2

        if dimensions > 1:
          print('\n error ram array can only be initialized as one dimension\n')
          exit(1)

        size = self.variables.calc(ctx.getChild(2).expression().getText(), self)

        for i in range(size):
          self.variables.add(varType, name + f'[{i}]', 0)





  # Exit a parse tree produced by CorParser#assignment.
  def exitAssignment(self, ctx:CorParser.AssignmentContext):
      var = self.scope(ctx.VARIABLE().getText())

      self.variables.add(ctx.CONST().getText(),
                         var,
                         self.variables.calc(ctx.expression().getText(), self)
                         )

  # Exit a parse tree produced by CorParser#instruction.
  def exitInstruction(self, ctx:CorParser.InstructionContext):
      mnem = ctx.MNEMONIC().getText()
      tempargs = []
      for arg in ctx.argument():
        if arg.expression() != None and arg.expression().math() != None or '$' in arg.getText():
          tempargs.append(self.variables.calc(arg.getText(), self))
        else:
          tempargs.append(self.scope(arg.getText()))

      linenum = self.stream.get(ctx.getSourceInterval()[0]).line
      self.instructions.add(mnem, tempargs, linenum, self.fullpath)


class ImportListener(CorListener):

  def __init__(self, infile):
    tempfile = infile[:infile.rfind('.')]
    nameIndex = tempfile.rfind('/')
    nameIndex = nameIndex + 1 if nameIndex >= 0 else 0
    # We're extracting the path to the file from where the assembler
    # was invoked so that any files imported are searched for correctly
    # from any arbitrary calling path
    self.workingDirectory = infile[:nameIndex] if nameIndex > 0 else './'
    self.currentPrefix = ''
    # print(infile)
    name = infile[nameIndex:].replace('.cor', '')

    self.imports = [{'name': name, 'path': infile}]

  def getImports(self):
    return self.imports

  # Enter a parse tree produced by CorParser#initial.
  def enterInitial(self, ctx:CorParser.InitialContext):
      if ctx.file_import() != None:
        for file in ctx.file_import():
          # it's a bit messy to use positional searches, but idk how
          # to do it better
          # oldPrefix = self.currentPrefix
          token = file.getChild(1).getText().strip("\'\"")
          token = token[2:] if token[:2] == './' else token

          name = ''
          newPrefix = ''
          path = ''
          # of the form: import "libs/lib.cor"
          if file.STRING() != None:
            newPrefixIndex = token.rfind('/')
            newPrefix = token[:newPrefixIndex + 1] if newPrefixIndex >= 0 else ''
            name = token[newPrefixIndex + 1:].replace('.cor', '')
            path = self.currentPrefix + token
          # of the form: import libs.lib
          else:
            token = token.replace('.', '/')
            newPrefixIndex = token.rfind('/')
            newPrefix = token[:newPrefixIndex + 1] if newPrefixIndex >= 0 else ''
            name = token[newPrefixIndex + 1:]
            path = self.currentPrefix + token + '.cor'

          if file.AS() != None:
            name = file.getChild(3).getText()

          self.currentPrefix += newPrefix

          tempdict = {
            'name': name,
            'path': self.workingDirectory + path
            }

          new = True
          for link in self.imports:
            if tempdict['path'] == link['path']:
              new = False
              if tempdict['name'] != link['name']:
                # TODO -- throw proper error
                print(f'\nError: file \"{link["path"]}\" imported more than once with conflicting names!\n')
                exit(1)
              break

          if new:
            # TODO -- this does not quite properly place files. If one import
            # depends on another, like:
            # import one
            # import depends_on_one
            # the variables in depends_on_one will appear before one, potentially
            # causing errors
            self.imports.insert(0, tempdict)

            input = FileStream(tempdict['path'])
            lexer = CorLexer(input)
            stream = CommonTokenStream(lexer)
            parser = CorParser(stream)
            tree = parser.initial()
            walker = ParseTreeWalker()
            walker.walk(self, tree)

          if len(newPrefix) > 0:
            self.currentPrefix = self.currentPrefix[:-len(newPrefix)]
      pass

  # Exit a parse tree produced by CorParser#initial.
  def exitInitial(self, ctx:CorParser.InitialContext):
      pass
