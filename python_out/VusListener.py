import sys
from antlr4 import *
from gen.CorParser import CorParser
from gen.CorListener import CorListener

from gen.CorLexer import CorLexer
from gen.CorParser import CorParser



class Variables:

  def __init__(self):
    self.vars = []
    self.size = {'ram': 0, 'rom': 0, 'pre': 0}
    self.evalDict = {}

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

  def calc(self, mathString):
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
  def add(self, mnemonic, args):
    address = self.size
    self.size += 1
    self.instructions.append({'mnemonic': mnemonic, 'address': address, 'args': args})

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

  def __init__(self, mainName):
    self.variables = Variables()
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

  def scope(self, input):
    if self.currentName != self.mainName and '.' not in input and input not in self.keywords:
      input = self.currentName + '.' + input
    return input

  def setName(self, name):
    self.currentName = name

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


  # Exit a parse tree produced by CorParser#assignment.
  def exitAssignment(self, ctx:CorParser.AssignmentContext):
      var = self.scope(ctx.VARIABLE().getText())
      self.variables.add(ctx.CONST().getText(),
                         var,
                         # TODO -- math boys aren't scoped properly yet, this
                         # will cause errors
                         self.variables.calc(ctx.expression().getChild(0).getText())
                         )

  # Exit a parse tree produced by CorParser#instruction.
  def exitInstruction(self, ctx:CorParser.InstructionContext):
      mnem = ctx.MNEMONIC().getText()
      tempargs = []
      for arg in ctx.argument():
        if arg.expression() != None and arg.expression().math() != None:
          scopedLine = ''
          for i in range(arg.expression().math().getChildCount()):
            if not arg.expression().math().getChild(i).getText()[0].isnumeric():
              scopedLine += self.scope(arg.expression().math().getChild(i).getText())
            else:
              scopedLine += arg.expression().math().getChild(i).getText()

          tempargs.append(self.variables.calc(scopedLine))
        elif arg.expression() != None and arg.expression().exp_var() != None:
          tempargs.append(self.scope(arg.getText()))
        else:
          tempargs.append(arg.getText())

      self.instructions.add(mnem, tempargs)
      # self.output.write(f'{mnem}, {tempargs}\n')
      pass

class ImportListener(CorListener):

  def __init__(self, infile):
    tempfile = infile[:infile.rfind('.')]
    nameIndex = tempfile.rfind('/')
    nameIndex = nameIndex if nameIndex >= 0 else 0
    # We're extracting the path to the file from where the assembler
    # was invoked so that any files imported are searched for correctly
    # from any arbitrary calling path
    self.workingDirectory = infile[:nameIndex] if nameIndex > 0 else './'
    self.currentPrefix = ''
    name = infile[nameIndex:].replace('.cor', '')

    self.imports = [{'name': name, 'path': self.workingDirectory + name + '.cor'}]

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
