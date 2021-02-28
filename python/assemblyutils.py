import math
import numpy as np
import re

##############################################################
### I/O
##############################################################

def usage():
  print("\nUsage: <infile> [[options] [parameters] ...]")
  print("If no options are given, the program will not generate any output.")
  print("\n\t-h\n\t   print this usage message")
  print("\n\t-o outfile\n\t   generate output binary with the given name")
  print("\n\t-p romname\n\t   generate program rom verilog hex with the given romname")
  print("\n\t-d romname\n\t   generate data rom verilog hex with the given romname")
  print("\n\t-P bits\n\t   specify the number of bits in the program rom address")
  print("\n\t-D bits\n\t   specify the number of bits in the data rom address")
  print("\n\t--debug-vars\n\t   print out the organized variables for debugging checks")
  print("\n\t--debug-lines\n\t   print out the organized program lines for debugging checks")
  print("\n\t--log logname\n\t   write the above debug messages to \"logname.txt\" instead of the terminal")
  print()
  exit(0)

def writeMem(code, outfile, memAddBits, memBits):
  memWidth = math.ceil(memBits/4)
  wordsPerLine = math.floor((8/memWidth)*8)
  if wordsPerLine < 1:
    wordsPerLine = 1
  memAddWidth = math.ceil(memAddBits/4)
  numLines = math.ceil((2**memAddBits)/wordsPerLine)
  with open(outfile, 'w') as file:
    for i in range(numLines):
      file.write('@{:0>{w}X} '.format(i*wordsPerLine, w=memAddWidth))
      for j in range(wordsPerLine):
        if j + i*wordsPerLine < len(code):
          file.write('{:0>{w}X} '.format(code[j + i*wordsPerLine], w=memWidth))
        else:
          if j + i*wordsPerLine >= 2**memAddBits:
              break
          file.write('{:0>{w}X} '.format(0, w=memWidth))
      file.write('\n')
  if len(code) > 2**memAddBits:
    print('Error: program size too big ({} words) for memory ({} words)'.format(len(code), 2**memAddBits))
    exit(1)

def writeBin(code, outfile, promAddBits, dromAddBits, promDataWidth, dromDataWidth):
  pass

def debug(options_args, options_noargs, instructions, variables, labels):
  variables = variables.getVariables()
  if options_args['--log'] != '':
    with open(options_args['--log'] + '.txt', 'w') as file:
      if options_noargs['--debug-lines']:
        file.write('\n  Program Instructions:\n')
        for line in instructions.getInstructions():
          for label in labels.getLabels():
            if label['address'] == line['address']:
              file.write(f"{label['name']}:\n")
          tempargs = ''
          for i in range(len(line['arguments'])):
            tempargs += str(line['arguments'][i])
            if i < len(line['arguments']) - 1:
              tempargs += ', '
          file.write(f"{line['address']} : {line['mnemonic']} {tempargs}\n")

      if options_noargs['--debug-vars']:
        file.write('\n  Program Variables:\n')
        for var in variables:
          if variables[var]['type'] == 'pre':
            file.write(f"{variables[var]['type']}: {var} = {variables[var]['value']}\n")
        file.write('\n')
        for var in variables:
          if variables[var]['type'] == 'rom':
            file.write(f"{variables[var]['type']}: {var} (address {variables[var]['address']}) = {variables[var]['value']}\n")
        file.write('\n')
        for var in variables:
          if variables[var]['type'] == 'ram':
            file.write(f"{variables[var]['type']}: {var} (address {variables[var]['address']})\n")
  else:
    if options_noargs['--debug-lines']:
      print('\n  Program Instructions:\n')
      for line in instructions.getInstructions():
        for label in labels.getLabels():
          if label['address'] == line['address']:
            print(f"{label['name']}:")
        tempargs = ''
        for i in range(len(line['arguments'])):
          tempargs += str(line['arguments'][i])
          if i < len(line['arguments']) - 1:
            tempargs += ', '
        print(f"{hex(line['address'])} : {line['mnemonic']} {tempargs} [{line['line']}]")

    if options_noargs['--debug-vars']:
      print('\n  Program Variables:\n')
      for var in variables:
        if variables[var]['type'] == 'pre':
          print(f"{variables[var]['type']}: {var} = {variables[var]['value']}")
      print()
      for var in variables:
        if variables[var]['type'] == 'rom':
          print(f"{variables[var]['type']}: {var} (address {variables[var]['address']}) = {variables[var]['value']}")
      print()
      for var in variables:
        if variables[var]['type'] == 'ram':
          print(f"{variables[var]['type']}: {var} (address {variables[var]['address']})")

errorList = []
warningList = []

def endExecution():
  if len(errorList) > 0:
    print()
    warningPrint(warningList)
    errorPrint(errorList)
    warnS = 's' if len(warningList) != 1 else ''
    errS = 's' if len(errorList) != 1 else ''
    print(f'{len(warningList)} warning{warnS}, {len(errorList)} error{errS}, exiting...')
    exit(1)
  if len(warningList) > 0:
    warningPrint(warningList)
    warnS = 's' if len(warningList) != 1 else ''
    errS = 's' if len(errorList) != 1 else ''
    print(f'{len(warningList)} warning{warnS}, {len(errorList)} error{errS}')

def error(message, lineNumber, filepath, errorcode):
  errorList.append({'message': message, 'line': lineNumber,
                      'path': filepath, 'code': errorcode})
  if len(errorList) == 5:
    endExecution()

def warning(message, lineNumber, filepath, errorcode):
  warningList.append({'message': message, 'line': lineNumber,
                      'path': filepath, 'code': errorcode})

def errorPrint(entries):
  # uh oh, this won't work with multiple files... we'll have to sort and stuff
  try:
    with open(entries[0]['path'], 'r') as file:
      lines = file.readlines()
      for entry in entries:
        print(f'({entry["code"]}) Error in file \"{entry["path"]}\" at line {entry["line"]}:')
        original = lines[entry['line'] - 1].strip(' \n')
        print(f'  {original}')
        print(f'-> {entry["message"]}\n')
  except FileNotFoundError:
    for entry in entries:
      # print(f'({entry["code"]}) Error in file \"{entry["path"]}\" at line {entry["line"]}:')
      # original = lines[entry['line'] - 1].strip(' \n')
      # print(f'  {original}')
      print(f'-> {entry["message"]}\n')

def warningPrint(entries):
  if len(entries) > 0:
    with open(entries[0]['path'], 'r') as file:
      lines = file.readlines()
      for entry in entries:
        print(f'({entry["code"]}) Warning for file \"{entry["path"]}\" at line {entry["line"]}:')
        original = lines[entry['line'] - 1].strip(' \n')
        print(f'  {original}')
        print(f'-> {entry["message"]}\n')

##############################################################
### Assembly Data
##############################################################

sysvarTable = [
  ['ram', 'UART', '0'],
  ['ram', 'UART_STATUS', '1'],
  ['ram', 'DISPLAY', '36'],

  ['ram', 'R4000', '2'],
  ['ram', 'R4001', '3'],
  ['ram', 'R4002', '4'],
  ['ram', 'R4003', '5'],
  ['ram', 'R4004', '6'],
  ['ram', 'R4005', '7'],
  ['ram', 'R4006', '8'],
  ['ram', 'R4007', '9'],
  ['ram', 'R4008', '10'],
  ['ram', 'R4009', '11'],
  ['ram', 'R400A', '12'],
  ['ram', 'R400B', '13'],
  ['ram', 'R400C', '14'],
  ['ram', 'R400D', '15'],
  ['ram', 'R400E', '16'],
  ['ram', 'R400F', '17'],
  ['ram', 'R4010', '18'],
  ['ram', 'R4011', '19'],
  ['ram', 'R4012', '20'],
  ['ram', 'R4013', '21'],
  ['ram', 'R4014', '22'],
  ['ram', 'R4015', '23'],
  ['ram', 'R4016', '24'],
  ['ram', 'R4017', '25'],

  ['ram', 'R9000', '26'],
  ['ram', 'R9001', '27'],
  ['ram', 'R9002', '28'],
  ['ram', 'R9003', '29'],
  ['ram', 'RA000', '30'],
  ['ram', 'RA001', '31'],
  ['ram', 'RA002', '32'],
  ['ram', 'RB000', '33'],
  ['ram', 'RB001', '34'],
  ['ram', 'RB002', '35'],

  ['ram', 'TIMERS', '37'],
  ['ram', 'TIMER0_COMP', '38'],
  ['ram', 'TIMER0_PRES', '39'],
  ['ram', 'TIMER1_COMP', '40'],
  ['ram', 'TIMER1_PRES', '41'],
  ['ram', 'SCOPE_RATE', '42'],
  ['ram', 'SCOPE_ADDR', '43'],
  ['ram', 'SCOPE_DATA', '43'],
  ['ram', 'SCOPE_HOLD', '44'],

  ['ram', 'FLASH_WRITE', '45'],
  ['ram', 'FLASH_READ', '45'],
  ['ram', 'FLASH_PAGE', '46'],
  ['ram', 'FLASH_STATUS', '47'],

  ['pre', 'FLASH_WRITE_WORD', '8'],
  ['pre', 'FLASH_READ_WORD', '4'],
  ['pre', 'FLASH_ERASE_WORD', '16'],

  ['pre', 'TIMER0_EN', '1'],
  ['pre', 'TIMER1_EN', '2'],

  ['pre', 'TX_EMPTY', '2'],
  ['pre', 'TX_FULL', '1'],
  ['pre', 'RX_EMPTY', '8'],
  ['pre', 'RX_FULL', '4'],
]

SYSVARS = {}

for var in sysvarTable:
  if var[0] == 'ram':
    SYSVARS[var[1]] = {'type': 'ram', 'address': int(var[2]), 'value': 0, 'line': -1, 'path': -1}
  elif var[0] == 'pre':
    SYSVARS[var[1]] = {'type': 'pre', 'name': var[1], 'address': 0, 'value': var[2], 'line': -1, 'path': -1}


MNEM2OP = {'nop': 0,  'ldr': 1,  'str': 2,  'lpt': 3,  'spt': 4,  'cmp': 5,
           'add': 6,  'sub': 7,  'mul': 8,  'div': 9,  'mod': 10, 'and': 11,
           'or' : 12, 'xor': 13, 'not': 14, 'lsl': 15, 'lsr': 16, 'psh': 17,
           'pop': 18, 'pek': 19, 'jmp': 20, 'jsr': 21, 'rts': 22, 'joc': 23,
           'jsc': 24, 'rsc': 25, 'rti': 26, 'ric': 27}

INTERRUPT_VECTORS = {'FRAME': 1, 'TIMER': 2}

INIT_INSTRUCTIONS = [
  {'mnemonic': 'jmp', 'address': 0, 'arguments': ['__pgm_start__'], 'line': -1, 'path': -1},
  {'mnemonic': 'jmp', 'address': 1, 'arguments': ['__pgm_start__'], 'line': -1, 'path': -1},
  {'mnemonic': 'jmp', 'address': 2, 'arguments': ['__pgm_start__'], 'line': -1, 'path': -1},
]

OPVAR_SHIFT    = 0
OPCODE_SHIFT   = 2
OPERAND1_SHIFT = 7
OPERAND2_SHIFT = 10
RESULTS_SHIFT  = 13
WORD2_SHIFT    = 16

REGISTERS     = {'a': 0, 'b': 1, 'c': 2, 'd': 3,
                 'e': 4, 'f': 5, 'g': 6, 'h': 7}
OPVAR_MASK    = 0b11    << OPVAR_SHIFT
OPCODE_MASK   = 0b11111 << OPCODE_SHIFT
OPERAND1_MASK = 0b111   << OPERAND1_SHIFT
OPERAND2_MASK = 0b111   << OPERAND2_SHIFT
RESULTS_MASK  = 0b111   << RESULTS_SHIFT
WORD2_MASK    = 0xFF    << WORD2_SHIFT

MATH_RAM = 0b00
MATH_REG = 0b01
MATH_GPU = 0b10
MATH_IMM = 0b11

LOAD_ROM = 0b01

STORE_RAM = 0b00
STORE_GPU = 0b10

LOAD_PTR_RAM = 0b00
LOAD_PTR_ROM = 0b01
LOAD_PTR_GPU = 0b10

STORE_PTR_RAM = 0b00
STORE_PTR_GPU = 0b01

LOAD_CONST = ['rom', 'pre']
LOAD_MEM = ['ram', 'gpu']

MATH_DICT = {'ram': MATH_RAM, 'rom': MATH_IMM,
             'pre': MATH_IMM, 'reg': MATH_REG, 'number': MATH_IMM}

LOAD_DICT = {'ram': MATH_RAM, 'rom': LOAD_ROM, 'gpu': MATH_GPU,
             'pre': MATH_IMM, 'number': MATH_IMM}

# The extra keys are so that an incorrect instruction doesn't throw a KeyError
STORE_DICT = {'ram': STORE_RAM, 'gpu': STORE_GPU, 'rom': 0, 'pre': 0}

LOAD_PTR_DICT = {'ram': LOAD_PTR_RAM, 'rom': LOAD_PTR_ROM, 'gpu': LOAD_PTR_GPU}
STORE_PTR_DICT = {'ram': STORE_PTR_RAM, 'gpu': STORE_PTR_GPU}

CONDITIONS = {
  'zero': 0b000000,
  'carry': 0b000010,
  'negative': 0b000100,
  'equal': 0b001000,
  'greater': 0b010000,
  'less': 0b100000,
}


def setInterrupts(labels, instructions):
  # preparing interrupt instructions
  for label in labels:
    if 'interrupt' in label:
      try:
        # by the gods what have I done
        tempargs = instructions[INTERRUPT_VECTORS[label['interrupt']]]['arguments']
        if tempargs[0] != '__pgm_start__':
          # this means the user attempted to link an interrupt to two subroutines
          errmess = f'\"{label["interrupt"]}\" cannot  be attached to more than one subroutine'
          error(errmess, label['line'], label['path'], 6)
        instructions[INTERRUPT_VECTORS[label['interrupt']]]['arguments'] = [label['name']]
      except KeyError:
        errmess = f'\"{label["interrupt"]}\" is not a valid interrupt vector'
        error(errmess, label['line'], label['path'], 6)

def setOperand1(word, register):
  word |= REGISTERS[register] << OPERAND1_SHIFT
  return word

def setOperand2(word, register):
  word |= REGISTERS[register] << OPERAND2_SHIFT
  return word

def setResults(word, register):
  word |= REGISTERS[register] << RESULTS_SHIFT
  return word

def setOpcode(word, mnemonic):
  word |= MNEM2OP[mnemonic] << OPCODE_SHIFT
  return word

def setOpvar(word, opvar):
  word |= opvar << OPVAR_SHIFT
  return word

def setWord2(word, word2):
  word |= word2 << WORD2_SHIFT
  return word

def assembleRegister(word, reg, operand, instruction):
  opdict = {'operand1': OPERAND1_SHIFT,
            'operand2': OPERAND2_SHIFT,
            'results': RESULTS_SHIFT}
  try:
    word |= REGISTERS[reg] << opdict[operand]
  except KeyError:
    errmess = f'\"{reg}\" is not a valid register'
    error(errmess, instruction['line'], instruction['path'], 3)
  return word

def assembleArgument2(word, argument, dict, instruction, variables, number):
  try:
    if argument[0] == '&':
      var = variables[argument[1:]]
      word = setWord2(word, var['address'])
    else:
      var = variables[argument]
      if var['type'] in LOAD_CONST:
        word = setWord2(word, int(var['value']))
      else:
        word = setWord2(word, int(var['address']))
      word = setOpvar(word, dict[var['type']])
      if var['type'] == 'rom':
        warnmess = 'rom values cannot be directly loaded as operands:\n embedding value in instruction'
        warning(warnmess, instruction['line'], instruction['path'], 6)
  except KeyError:
    numstring = str(argument)
    if re.search(number, numstring) != None:
      word = setWord2(word, eval(numstring))
      word = setOpvar(word, dict['number'])
    else:
      errmess = f'\"{argument}\" is not defined'
      error(errmess, instruction['line'], instruction['path'], 1)
  return word

def assembleArgument2Store(word, argument, dict, instruction, variables, number):
  validTable = ['ram', 'gpu']

  try:
    if argument[0] == '&':
      errmess = f'cannot store register in literal value \"{argument}\"' 
      error(errmess, instruction['line'], instruction['path'], 1)
    else:
      var = variables[argument]
      if var['type'] not in validTable:
        errmess = f'\"{argument}\" must be a writable variable'
        error(errmess, instruction['line'], instruction['path'], 1)
      word = setWord2(word, int(var['address']))
      word = setOpvar(word, dict[var['type']])
  except KeyError:
    numstring = str(argument)
    if re.search(number, numstring) != None:
      errmess = f'\"{argument}\" must be a writable variable'
      error(errmess, instruction['line'], instruction['path'], 1)
    else:
      errmess = f'\"{argument}\" is not defined'
      error(errmess, instruction['line'], instruction['path'], 1)
  return word

def assembleLabel(word, arg, labels, instruction):
  found = False
  for label in labels:
    if label['name'] == arg:
      found = True
      word = setWord2(word, label['address'])
      break
  if not found:
    errmess = f'\"{arg}\" is not defined'
    # print(errmess)
    error(errmess, instruction['line'], instruction['path'], 444)
  return word

def checkNumArgs(args, argtarget, name, instruction):
  numToWord = ['zero', 'one', 'two', 'three']
  if len(args) != argtarget:
    errmess = f'\"{name}\" takes exactly {numToWord[argtarget]} argument{"s" if argtarget != 1 else ""}'
    error(errmess, instruction['line'], instruction['path'], 69)

def assembleCondJump(word, arg, instruction):
  try:
    word |= CONDITIONS[arg] << OPERAND1_SHIFT
  except KeyError:
    errmess = f'\"{arg}\" is not a valid condition'
    error(errmess, instruction['line'], instruction['path'], 420)
  return word

def assembleInterruptVector(word, arg, instruction):
  try:
    word |= INTERRUPT_VECTORS[arg] << 16
  except KeyError:
    errmess = f'\"{arg}\" is not a valid interrupt vector'
    error(errmess, instruction['line'], instruction['path'], 58008)
  return word

##############################################################
### Assembly Main Loop
##############################################################

def assembleInstructions(instructions, variables, labels):

  number = re.compile('\\b(([1-9][0-9_]*)|(0x[0-9A-Fa-f][0-9A-Fa-f_]*)|(0b[10][10_]*)|0)\\b')

  mathops = ['cmp', 'add', 'sub', 'mul', 'div', 'mod',
             'and', 'or',  'xor', 'lsr', 'lsl']

  machine = np.empty(len(instructions), dtype='u4')

  for i in range(len(instructions)):
    word = 0
    inst = instructions[i]['mnemonic']
    word = setOpcode(word, inst)
    args = instructions[i]['arguments']

    if inst in mathops:
      if len(args) < 2 or len(args) > 3:
        errmess = f'\"{inst}\" needs at least two arguments and takes no more than three'
        error(errmess, instructions[i]['line'], instructions[i]['path'], 76)

      try:
        word = assembleRegister(word, args[0], 'operand1', instructions[i])
        # since the second argument can be many different things,
        # it's not immdiately an error if the argument is not a register
        try:
          word = setOperand2(word, args[1])
          word = setOpvar(word, MATH_DICT['reg'])
        except KeyError:
          word = assembleArgument2(word, args[1], MATH_DICT, instructions[i], variables, number)
        if len(args) == 3:
          if 'inst' == 'cmp':
            errmess = f'\"{inst}\" takes exactly two arguments, ignoring \"{args[2]}\"'
            warning(errmess, instructions[i]['line'], instructions[i]['path'], 119)
          word = assembleRegister(word, args[2], 'results', instructions[i])
        else:
          word = assembleRegister(word, args[0], 'results', instructions[i])
      except IndexError:
        pass

    elif inst == 'not': # TODO -- extend to allow const / ram arguments for arg 1!!
      if len(args) < 1 or len(args) > 2:
        errmess = f'\"{inst}\" needs at least one argument and takes no more than two'
        error(errmess, instructions[i]['line'], instructions[i]['path'], 169)
      try:
        word = assembleRegister(word, args[0], 'operand2', instructions[i])
        word = setOpvar(word, MATH_DICT['reg'])
        if len(args) == 2:
          word = assembleRegister(word, args[1], 'results', instructions[i])
        else:
          word = assembleRegister(word, args[0], 'results', instructions[i])
      except IndexError:
        pass

    elif inst == 'nop':
      checkNumArgs(args, 0, inst, instructions[i])

    elif inst == 'ldr':
      checkNumArgs(args, 2, inst, instructions[i])
      try:
        word = assembleRegister(word, args[0], 'results', instructions[i])
        # TODO -- this doesn't quite work, since ldr CAN load rom values directly,
        # but this method throws a warning if you try to load a rom value
        word = assembleArgument2(word, args[1], LOAD_DICT, instructions[i], variables, number)
      except IndexError:
        pass

    elif inst == 'str':
      checkNumArgs(args, 2, inst, instructions[i])
      try:
        word = assembleRegister(word, args[0], 'operand1', instructions[i])
        word = assembleArgument2Store(word, args[1], STORE_DICT, instructions[i], variables, number)
      except IndexError:
        pass

    elif inst == 'lpt':
      checkNumArgs(args, 2, inst, instructions[i])
      try:
        word = assembleRegister(word, args[0], 'results', instructions[i])
        try:
          word = setOpvar(word, LOAD_PTR_DICT[args[1]])
        except KeyError:
          errmess = f'\"{inst}\" must be \"ram,\" \"rom,\" or \"gpu\"'
          error(errmess, instructions[i]['line'], instructions[i]['path'], -2)
      except IndexError:
        pass

    elif inst == 'spt':
      checkNumArgs(args, 2, inst, instructions[i])
      try:
        word = assembleRegister(word, args[0], 'operand1', instructions[i])
        try:
          word = setOpvar(word, STORE_PTR_DICT[args[1]])
        except KeyError:
          errmess = f'\"{inst}\" must be \"ram\" or \"gpu\"'
          error(errmess, instructions[i]['line'], instructions[i]['path'], 1000)
      except IndexError:
        pass

    elif inst == 'jmp' or inst == 'jsr':
      checkNumArgs(args, 1, inst, instructions[i])
      try:
        word = assembleLabel(word, args[0], labels, instructions[i])
      except IndexError:
        pass

    elif inst == 'joc' or inst == 'jsc':
      checkNumArgs(args, 2, inst, instructions[i])
      try:
        word = assembleLabel(word, args[1], labels, instructions[i])
        word = assembleCondJump(word, args[0], instructions[i])
      except IndexError:
        pass

    elif inst == 'rts':
      checkNumArgs(args, 0, inst, instructions[i])

    elif inst == 'rsc':
      checkNumArgs(args, 1, inst, instructions[i])
      try:
        word = assembleCondJump(word, args[0], instructions[i])
      except IndexError:
        pass

    elif inst == 'rti':
      checkNumArgs(args, 1, inst, instructions[i])
      try:
        word = assembleInterruptVector(word, args[0], instructions[i])
      except IndexError:
        pass

    elif inst == 'ric':
      checkNumArgs(args, 2, inst, instructions[i])
      try:
        word = assembleInterruptVector(word, args[1], instructions[i])
        word = assembleCondJump(word, args[0], instructions[i])
      except IndexError:
        pass

    machine[i] = word

  return machine

def assembleVariables(variables, ramBitWidth):
  biggestAddress = -1
  for var in variables:
    if variables[var]['type'] == 'rom' and int(variables[var]['address']) > biggestAddress:
      biggestAddress = variables[var]['address']
  machine = np.empty(biggestAddress + 1, dtype=f'u{math.ceil(ramBitWidth/8)}')

  for var in variables:
    if variables[var]['type'] == 'rom':
      machine[int(variables[var]['address'])] = variables[var]['value']

  return machine
