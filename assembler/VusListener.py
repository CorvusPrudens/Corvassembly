import sys
from antlr4 import *
from assemblyutils import *
from gen.CorParser import CorParser
from gen.CorListener import CorListener
from antlr4.error.ErrorListener import ErrorListener

from gen.CorLexer import CorLexer
from gen.CorParser import CorParser

import re
import math


class Variables:

  def __init__(self, sysvars={}, ramStartAddress=41):
    self.vars = sysvars
    self.size = {'ram': ramStartAddress, 'rom': 0, 'pre': 0}
    self.evalDict = {}
    for var in sysvars:
      if sysvars[var]['type'] == 'rom' or sysvars[var]['type'] == 'pre':
        self.evalDict[sysvars[var]['type']] = sysvars[var]['value']
    self.variableRegex = re.compile('(?<!&)(\\b|^)[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b')
    # TODO -- This address regex will likely need to be expanded upon, for example if
    # we want to do math with pointers e.g.
    # 270 + &var
    # or even, dare I say,
    # 270 & (&var)
    self.addressRegex = re.compile('(^|(?<=[+-/*=><\\^|()]))&[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b')

  # TODO -- all variables should be scoped when added

  # ['varType', 'name', 'address', 'value']
  def add(self, varType, name, value, listener, linenum=-1):

    # TODO - errors need to be unified
    if name in self.vars:
      errmess = f'variable \"{name}\" already defined'
      error(errmess, linenum, listener.fullpath, 4000)
      return

    address = self.size[varType]
    self.size[varType] += 1
    self.vars[name] = {'type': varType, 'address': address, 'value': value}
    # TODO - arrays should not be added like other variables -- rather, they
    # should have one entry in the vars dictionary and contain a list of all
    # their elements. This would save a lot of memory and probably execution time
    # this would also make the variables debug output a lot more readable

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

  def getAddress(self, variable, linenum=-1, fullpath='err'):
    variable = variable[1:]
    found = False
    try:
      if self.vars[variable]['type'] == 'pre':
        errmess = f'Pre-processor variable {variable} does not have an address'
        error(errmess, linenum, fullpath, 470)
        return -1
      else:
        return self.vars[variable]['address']
    except KeyError:
      errmess = f'no address for undefined variable {variable}'
      error(errmess, linenum, fullpath, 474)

  def decrementAddress(self, varType):
    # to overload variable names
    self.size[varType] -= 1

  def calc(self, mathString, listener, linenum=-1, fullpath='err'):

    # print(mathString)
    match = self.variableRegex.search(mathString)
    while match != None:
      mathString = mathString[:match.start()] + listener.scope(match.group(0)) + mathString[match.end():]
      match = self.addressRegex.search(mathString, pos=match.end())

    match = self.addressRegex.search(mathString)
    # print(match.group(0) if match != None else '.', mathString)
    while match != None:
      # print(match.group(0))
      mathString = mathString[:match.start()] + str(self.getAddress(match.group(0), linenum=linenum, fullpath=fullpath)) + mathString[match.end():]
      match = self.addressRegex.search(mathString, pos=match.end())

    # print(mathString)
    solution = 0
    try:
      solution = round(eval(mathString, {}, self.evalDict))
    except NameError as err:
      wrongtype = False
      wrongtypename = ''
      for key in self.vars:
        tempregstr = '(\\b|^)({})\\b'.format(key)
        if '.' in tempregstr:
          tempregstr.replace('.', '\\.')
        tempreg = re.compile(tempregstr)
        if tempreg.search(mathString) != None:
          wrongtype = True
          wrongtypename = key
          break
      if not wrongtype:
        extracted = err.args[0][6:]
        extraced = extracted[:extracted.find('\'')]
        errmess = f'\"{extraced}\" is not defined'
        error(errmess, linenum, fullpath, 469)
      else:
        if (listener.currentName != listener.mainName and '.' in wrongtypename):
          wrongtypename = wrongtypename[wrongtypename.find('.') + 1:]
        errmess = f'cannot evaluate \"{wrongtypename}\" at compile time'
        error(errmess, linenum, fullpath, 469)
    except SyntaxError as e:
      # print(e.args)
      errmess = 'invalid syntax'
      error(errmess, linenum, fullpath, 470)
    except AttributeError as e:
      # prints in the form:
      # type object 'importname' has no attribute 'errorcause'
      errorBound = e.args[0][:-1].rfind("'")
      errmess = f'\"{e.args[0][errorBound + 1:-1]}\" is not defined'
      error(errmess, linenum, fullpath, 471)
    except TypeError:
      errmess = 'invalid operation'
      error(errmess, linenum, fullpath, 472)

    return solution

  # this may need to be updated later if users want to specify
  # addresses for data
  def insert(self, newvars):
    self.vars.update(newvars)



class Instructions:

  def __init__(self, pgmStartAddress=0, numloops=0, numifs=0):
    self.instructions = []
    self.size = {'instructions': pgmStartAddress, 'loops': numloops, 'ifs': numifs}
    self.top_begin = ''
    self.top_end = ''

  # # ['mnemonic', 'address', 'args']
  # def add(self, mnemonic, args, line, path):
  #   address = self.size['instructions']
  #   self.size['instructions'] += 1
  #   self.instructions.append({'mnemonic': mnemonic, 'address': address,
  #                             'arguments': args, 'line': line, 'path': path})

  def add(self, ctx, listener, variables):
    # print(type(ctx).__name__)
    mnemonic = ctx.MNEMONIC().getText()
    tempargs = []
    linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
    for arg in ctx.argument():
      if arg.expression() != None and arg.expression().math() != None or '$' in arg.getText():
        tempargs.append(variables.calc(arg.getText(), listener, linenum, listener.fullpath))
      else:
        tempargs.append(listener.scope(arg.getText()))


    address = self.size['instructions']
    self.size['instructions'] += 1
    self.instructions.append({'mnemonic': mnemonic, 'address': address,
                              'arguments': tempargs, 'line': linenum,
                              'path': listener.fullpath})

  def addManual(self, mnemonic, arguments, linenum, listener):
    address = self.size['instructions']
    self.size['instructions'] += 1
    self.instructions.append({'mnemonic': mnemonic, 'address': address,
                              'arguments': arguments, 'line': linenum,
                              'path': listener.fullpath})

  # recursively add loops
  def addLoop(self, ctx, listener, variables, labels, top=False):
    linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
    loopname = f'__loop{self.size["loops"]}'
    loopbegin = loopname + '_begin'
    loopend = loopname + '_end'
    loopcont = loopname + '_continue'
    self.size['loops'] += 1
    if top:
      self.top_begin = loopbegin
      self.top_end = loopend

    self.add(ctx.getChild(2), listener, variables)
    labels.add(ctx, loopbegin, self, listener, linenum=linenum)
    self.add(ctx.getChild(4), listener, variables)
    self.addManual('joc', ['equal', loopend], linenum, listener)

    children = ctx.getChild(8).getChildCount()
    for i in range(1, children - 1):
      ctxname = type(ctx.getChild(8).getChild(i)).__name__
      if ctxname == 'InstructionContext':
        self.add(ctx.getChild(8).getChild(i), listener, variables)
      elif ctxname == 'Loop_keywordContext':
        keyword = ctx.getChild(8).getChild(i).getText()
        target = ''
        if keyword == 'continue':
          target = loopcont
        elif keyword == 'break':
          target = loopend
        elif keyword == 'breakall':
          target = self.top_end
        self.addManual('jmp', [target], linenum, listener)
      elif ctxname == 'LabelContext':
        labels.add(ctx, ctx.getChild(8).getChild(i).getText()[:-1], self, listener, linenum=linenum)
      elif ctxname == 'LoopContext':
        self.addLoop(ctx.getChild(8).getChild(i), listener, variables, labels)
      elif ctxname == 'If_chainContext':
        self.addIfchain(ctx.getChild(8).getChild(i), listener, variables, labels)

    labels.add(ctx, loopcont, self, listener, linenum=linenum)
    self.add(ctx.getChild(6), listener, variables)
    self.addManual('jmp', [loopbegin], linenum, listener)
    labels.add(ctx, loopend, self, listener, linenum=linenum)

  # recursively add ifs
  def addIfchain(self, ctx, listener, variables, labels):
    linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
    ifname = f'__if{self.size["ifs"]}'
    # ifbegin = ifname + '_begin'
    ifend = ifname + '_end'
    thisIfNum = self.size['ifs']
    self.size['ifs'] += 1

    numifs = ctx.getChildCount()

    if numifs == 1:
      ifstat = ctx.getChild(0)
      currentBranch = f'__if{thisIfNum}_branch0'
      # adding instructions in conditional and the jump logic
      numInstructions = math.ceil((ifstat.getChild(1).getChildCount() - 4)/2)
      condition = ifstat.getChild(1).getChild(ifstat.getChild(1).getChildCount() - 2).getText()
      conditionCondition = ifstat.getChild(1).getChild(ifstat.getChild(1).getChildCount() - 3).getText()
      for j in range(numInstructions):
        tempInstr = ifstat.getChild(1).getChild(1 + j*2)
        self.add(tempInstr, listener, variables)

      if conditionCondition == 'is':
        self.addManual('joc', [condition, currentBranch + '_t'], linenum, listener)
        self.addManual('jmp', [ifend], linenum, listener)
      else:
        self.addManual('joc', [condition, ifend], linenum, listener)
      labels.add(ctx, currentBranch + '_t', self, listener, linenum=linenum)

      numItems = ifstat.getChild(2).getChildCount() - 2

      for j in range(numItems):
        ctxname = type(ifstat.getChild(2).getChild(1 + j)).__name__
        linenum = listener.stream.get(ifstat.getChild(2).getChild(1 + j).getSourceInterval()[0]).line
        if ctxname == 'InstructionContext':
          self.add(ifstat.getChild(2).getChild(1 + j), listener, variables)
        elif ctxname == 'LabelContext':
          labels.add(ctx, listeners.scope(ifstat.getChild(2).getChild(1 + j).getText()[:-1]), self, listener, linenum=linenum)
        elif ctxname == 'LoopContext':
          self.addLoop(ifstat.getChild(2).getChild(1 + j), listener, variables, labels, top=True)
        elif ctxname == 'If_chainContext':
          self.addIfchain(ifstat.getChild(2).getChild(1 + j), listener, variables, labels)

    else:
      for i in range(numifs):
        ifstat = ctx.getChild(i)
        typeif = type(ifstat).__name__
        currentBranch = f'__if{thisIfNum}_branch{i}'
        nextBranch = f'__if{thisIfNum}_branch{i + 1}'
        if i != 0:
          labels.add(ctx, currentBranch, self, listener, linenum=linenum)
        if typeif in ['If_statContext', 'Elif_statContext']:
          # adding instructions in conditional and the jump logic
          numInstructions = math.ceil((ifstat.getChild(1).getChildCount() - 4)/2)
          condition = ifstat.getChild(1).getChild(ifstat.getChild(1).getChildCount() - 2).getText()
          conditionCondition = ifstat.getChild(1).getChild(ifstat.getChild(1).getChildCount() - 3).getText()
          for j in range(numInstructions):
            tempInstr = ifstat.getChild(1).getChild(1 + j*2)
            self.add(tempInstr, listener, variables)

          if conditionCondition == 'is':
            self.addManual('joc', [condition, currentBranch + '_t'], linenum, listener)
            self.addManual('jmp', [nextBranch], linenum, listener)
          else:
            self.addManual('joc', [condition, nextBranch], linenum, listener)
          labels.add(ctx, currentBranch + '_t', self, listener, linenum=linenum)

          numItems = ifstat.getChild(2).getChildCount() - 2

          for j in range(numItems):
            ctxname = type(ifstat.getChild(2).getChild(1 + j)).__name__
            linenum = listener.stream.get(ifstat.getChild(2).getChild(1 + j).getSourceInterval()[0]).line
            if ctxname == 'InstructionContext':
              self.add(ifstat.getChild(2).getChild(1 + j), listener, variables)
            elif ctxname == 'LabelContext':
              labels.add(ctx, listener.scope(ifstat.getChild(2).getChild(1 + j).getText()[:-1]), self, listener, linenum=linenum)
            elif ctxname == 'LoopContext':
              self.addLoop(ifstat.getChild(2).getChild(1 + j), listener, variables, labels, top=True)
            elif ctxname == 'If_chainContext':
              self.addIfchain(ifstat.getChild(2).getChild(1 + j), listener, variables, labels)

          self.addManual('jmp', [ifend], linenum, listener)

        else:
          # labels.add(ctx, currentBranch, self, listener)
          numItems = ifstat.getChild(1).getChildCount() - 2
          for j in range(numItems):
            ctxname = type(ifstat.getChild(1).getChild(1 + j)).__name__
            linenum = listener.stream.get(ifstat.getChild(1).getChild(1 + j).getSourceInterval()[0]).line
            if ctxname == 'InstructionContext':
              self.add(ifstat.getChild(1).getChild(1 + j), listener, variables)
            elif ctxname == 'LabelContext':
              labels.add(ctx, listener.scope(ifstat.getChild(1).getChild(1 + j).getText()[:-1]), self, listener,linenum=linenum)
            elif ctxname == 'LoopContext':
              self.addLoop(ifstat.getChild(1).getChild(1 + j), listener, variables, labels, top=True)
            elif ctxname == 'If_chainContext':
              self.addIf(ifstat.getChild(1).getChild(1 + j), listener, variables, labels)

      labels.add(ctx, nextBranch, self, listener, linenum=linenum)

    labels.add(ctx, ifend, self, listener, linenum=linenum)

  def getAddress(self):
    return self.size['instructions']

  def getInstructions(self):
    return self.instructions

  def insert(self, dictlist, num):
    for instruction in self.instructions:
      instruction['address'] += num
    self.instructions = dictlist + self.instructions


class Labels:

  def __init__(self):
    self.labels = []

  def setInit(self, pgmStartAddress=0):
    # begins with a default label to skip reserved
    # interrupt addresses
    self.labels.insert(0, {'name': '__pgm_start__', 'address': pgmStartAddress})

  # ['label', 'address']
  def add(self, ctx, name, instr, listener, interrupt=-1, linenum=-1):
    # TODO - errors need to be unified
    for label in self.labels:
      if label['name'] == name:
        errmess = f'label \"{name}\" already used at line {label["line"]}'
        error(errmess, linenum, listener.fullpath, 4001)
        return

    address = instr.getAddress()
    linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
    if (interrupt == -1):
      self.labels.append({'name': name, 'address': address,
                          'line': linenum, 'path': listener.fullpath})
    else:
      self.labels.append({'name': name, 'address': address,
                          'interrupt': interrupt, 'line': linenum,
                          'path': listener.fullpath})

  def getLabels(self):
    return self.labels

  def insert(self, dictlist, num):
    for label in self.labels:
      label['address'] += num
    self.labels = dictlist + self.labels


class VusListener(CorListener) :

  def __init__(self, mainName, ramStartAddress_init, pgmStartAddress_init, fullpath, sysvars_init):
    self.variables = Variables(ramStartAddress=ramStartAddress_init, sysvars=sysvars_init)
    self.instructions = Instructions(pgmStartAddress=pgmStartAddress_init)
    self.labels = Labels()
    self.mainName = mainName
    self.currentName = ''
    self.keywords = [
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
      'pre', 'ram', 'rom', 'gpu',
      'zero', 'carry', 'negative', 'as', 'for',
      'equal', 'greater', 'less', 'SCOPE_RATE',
      'UART', 'STACK', 'STATUS', 'SCOPE_ADDR',
      'GPIO', 'GPIO_DIR', 'TX_EMPTY', 'SCOPE_DATA',
      'TX_FULL', 'RX_EMPTY', 'RX_FULL', 'SCOPE_TRIGGER',
      'continue', 'break', 'breakall', 'FLASH_READ', 'FLASH_WRITE',
      'FLASH_STATUS', 'FLASH_PAGE', 'FLASH_WRITE_WORD',
      'FLASH_READ_WORD', 'FLASH_ERASE_WORD', 'TIMER', 'FRAME',
    ]
    self.variableRegex = re.compile('\\b\\&{0,1}[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b')
    self.fullpath = fullpath
    self.stream = None

  def scope(self, input):
    if self.variableRegex.search(input) != None:
      if self.currentName != self.mainName and '.' not in input and input not in self.keywords:
        if '&' in input:
          input = '&' + self.currentName + '.' + input[1:]
        else:
          input = self.currentName + '.' + input
    return input

  # def setName(self, name, fullpath, stream):
  #   self.currentName = name
  #   self.fullpath = fullpath
  #   self.stream = stream

  def getVariables(self):
    return self.variables

  def getInstructions(self):
    return self.instructions

  def getNumInstructions(self):
    return self.instructions.getAddress()

  def getLabels(self):
    return self.labels

  def reset(self, name, fullpath, stream, startaddr=3):
    self.currentName = name
    self.fullpath = fullpath
    self.stream = stream

    prevloops = self.instructions.size['loops']
    previfs   = self.instructions.size['ifs']
    if self.currentName == self.mainName:
      self.instructions = Instructions(pgmStartAddress=startaddr, numloops=prevloops, numifs=previfs)
    else:
      self.instructions = Instructions(numloops=prevloops, numifs=previfs)
    self.labels = Labels()


  # Enter a parse tree produced by CorParser#parse.
  def enterParse(self, ctx:CorParser.ParseContext):
      pass

  # Exit a parse tree produced by CorParser#parse.
  def exitParse(self, ctx:CorParser.ParseContext):
      pass


  # Enter a parse tree produced by CorParser#block.
  def enterBlock(self, ctx:CorParser.BlockContext):
      templabel = self.scope(ctx.label().getChild(0).getText())
      linenum = self.stream.get(ctx.getSourceInterval()[0]).line
      if ctx.label().getChildCount() == 2:
        self.labels.add(ctx, templabel, self.instructions, self, linenum=linenum)
      else:
        tempint = self.scope(ctx.label().getChild(2).getText())
        self.labels.add(ctx, templabel, self.instructions, self, interrupt=tempint, linenum=linenum)
      pass

  def convertString(self, string):
    string = string.strip('\"')
    chars = []

    for c in string:
      # TODO -- doesn't sanitize escaped characters
      chars.append(ord(c))
    chars.append(0) #terminating character

    return chars


  # Exit a parse tree produced by CorParser#assignment_arr.
  def assignment_arr(self, ctx):
      linenum = self.stream.get(ctx.getSourceInterval()[0]).line
      varType = ctx.CONST().getText()
      name = self.scope(ctx.array().VARIABLE().getText())
      numDimensions = ctx.array().getChildCount() - 3
      dimensions = []

      if numDimensions > 2:
        errmess = f'array \"{name}\" cannot be initialized with more than two dimension'
        error(errmess, linenum, self.fullpath, 4002)

      if numDimensions == 1 and ctx.array().arr_data() != None and (len(ctx.array().arr_data().string()) != 0 or len(ctx.array().arr_data().arr_data()) != 0):
        errmess = f'array \"{name}\" cannot contain subarrays'
        error(errmess, linenum, self.fullpath, 4002)

      self.variables.add('pre', name, self.variables.size[varType], self, linenum=linenum)

      if numDimensions == 1:
        if ctx.array().string() != None:
          linenum = self.stream.get(ctx.array().string().getSourceInterval()[0]).line
          chars = self.convertString(ctx.array().string().getText())
          for i in range(len(chars)):
            self.variables.add(varType, name + f'[{i}]', chars[i], self, linenum=linenum)
        else:
          width = math.ceil((ctx.array().arr_data().getChildCount() - 2)/2.0)
          for i in range(width):
            linenum = self.stream.get(ctx.array().arr_data().getChild(1 + i*2).getSourceInterval()[0]).line
            tempval = self.variables.calc(ctx.array().arr_data().getChild(1 + i*2).getText(), self, fullpath=self.fullpath, linenum=linenum)
            self.variables.add(varType, name + f'[{i}]', tempval, self, linenum=linenum)


  # Exit a parse tree produced by CorParser#declaration.
  def declaration(self, ctx):
      linenum = self.stream.get(ctx.getSourceInterval()[0]).line
      if ctx.getChildCount() == 2: # simple declaration
        self.variables.add(ctx.RAM().getText(), self.scope(ctx.VARIABLE().getText()), 0, self, linenum=linenum)
      else: # array
        # adding sneaky precompiler variable to mimic the behavior of C arrays
        self.variables.add('pre', self.scope(ctx.VARIABLE().getText()), self.variables.size['ram'], self, linenum=linenum)

        varType = ctx.RAM().getText()
        name = self.scope(ctx.VARIABLE().getText())
        dimensions = ctx.getChildCount() - 2

        if dimensions > 1:
          print('\n error ram array can only be initialized as one dimension\n')
          exit(1)

        size = self.variables.calc(ctx.getChild(2).expression().getText(), self,fullpath=self.fullpath, linenum=linenum)

        for i in range(size):
          self.variables.add(varType, name + f'[{i}]', 0, self, linenum=linenum)





  # Exit a parse tree produced by CorParser#assignment.
  def assignment(self, ctx):
      linenum = self.stream.get(ctx.getSourceInterval()[0]).line
      var = self.scope(ctx.VARIABLE().getText())

      self.variables.add(ctx.CONST().getText(),
                         var,
                         self.variables.calc(ctx.expression().getText(), self, linenum=linenum, fullpath=self.fullpath),
                         self,
                         linenum=linenum
                         )

  # Exit a parse tree produced by CorParser#instruction.
  def instruction(self, ctx):
      self.instructions.add(ctx, self, self.variables)


  def exitStatement(self, ctx:CorParser.StatementContext):
    ctx = ctx.getChild(0)
    ctxname = type(ctx).__name__

    if ctxname == 'InstructionContext':
      self.instruction(ctx)
    elif ctxname == 'AssignmentContext':
      self.assignment(ctx)
    elif ctxname == 'Assignment_arrContext':
      self.assignment_arr(ctx)
    elif ctxname == 'DeclarationContext':
      self.declaration(ctx)

  # Exit a parse tree produced by CorParser#statement_loop.
  def exitStatement_loop(self, ctx:CorParser.Statement_loopContext):
    self.instructions.addLoop(ctx.loop(), self, self.variables, self.labels, top=True)

  def exitStatement_if(self, ctx:CorParser.Statement_ifContext):
    self.instructions.addIfchain(ctx.if_chain(), self, self.variables, self.labels)


class ImportListener(CorListener):

  def __init__(self, infile, stream):
    tempfile = infile[:infile.rfind('.')]
    nameIndex = tempfile.rfind('/')
    nameIndex = nameIndex + 1 if nameIndex >= 0 else 0
    # We're extracting the path to the file from where the assembler
    # was invoked so that any files imported are searched for correctly
    # from any arbitrary calling path
    self.infile = infile
    self.workingDirectory = infile[:nameIndex] if nameIndex > 0 else './'
    self.currentPrefix = ''
    # print(infile)
    name = infile[nameIndex:].replace('.cor', '')

    self.imports = [{'name': name, 'path': infile}]
    self.stream=stream

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
            # TODO -- this needs to be expanded so that it is clear where
            # the file is imported!
            try:
              input = FileStream(tempdict['path'])
            except FileNotFoundError:
              linenum = self.stream.get(file.getChild(3).getSourceInterval()[0]).line
              errmess = f'cannot find file \"{tempdict["path"]}\"'
              error(errmess, linenum, self.infile, 4755, abort=True)
            lexer = CorLexer(input)
            # custom error listener
            importError = ImportErrorListener(filepath=tempdict['path'])
            lexer.removeErrorListeners()
            lexer.addErrorListener(importError)
            stream = CommonTokenStream(lexer)
            parser = CorParser(stream)
            parser.removeErrorListeners()
            parser.addErrorListener(importError)
            tree = parser.initial()
            walker = ParseTreeWalker()
            prevstream = self.stream
            self.stream = stream
            walker.walk(self, tree)
            self.stream = prevstream

          if len(newPrefix) > 0:
            self.currentPrefix = self.currentPrefix[:-len(newPrefix)]
      pass

  # Exit a parse tree produced by CorParser#initial.
  def exitInitial(self, ctx:CorParser.InitialContext):
      pass

class CorSyntaxError(Exception):
  def __init__(self, message='Catch me daddy'):
    super(CorSyntaxError, self).__init__(message)

class ImportErrorListener(ErrorListener):
  def __init__(self, filepath='err'):

    super(ErrorListener, self).__init__()
    self.filepath = filepath

  def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
    errmess = f'syntax error'
    error(errmess, line, self.filepath, 4000, syntax=True, col=column, abort=True)
    # raise CorSyntaxError


class VusErrorListener(ErrorListener):
  def __init__(self, filepath='err'):

    super(ErrorListener, self).__init__()
    self.filepath = filepath

  def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
    errmess = f'syntax error'
    error(errmess, line, self.filepath, 4000, syntax=True, col=column, abort=True)
    # raise CorSyntaxError

  # def reportAmbiguity(self, recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs):
  #   errmess = f'syntax error'
  #   error(errmess, line, self.filepath, 4000, syntax=True, col=column, abort=True)
  #
  # def reportAttemptingFullContext(self, recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs):
  #   errmess = f'syntax error'
  #   error(errmess, line, self.filepath, 4000, syntax=True, col=column, abort=True)
  #
  # def reportContextSensitivity(self, recognizer, dfa, startIndex, stopIndex, prediction, configs):
  #   errmess = f'syntax error'
  #   error(errmess, line, self.filepath, 4000, syntax=True, col=startIndex, abort=True)