import numpy as np
import re

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
  memWidth = m.ceil(memBits/4)
  wordsPerLine = m.floor((8/memWidth)*8)
  if wordsPerLine < 1:
    wordsPerLine = 1
  memAddWidth = m.ceil(memAddBits/4)
  numLines = m.ceil((2**memAddBits)/wordsPerLine)
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
        for var in variables.getVariables():
          if var['type'] == 'pre':
            file.write(f"{var['type']}: {var['name']} = {var['value']}\n")
        file.write('\n')
        for var in variables.getVariables():
          if var['type'] == 'rom':
            file.write(f"{var['type']}: {var['name']} (address {var['address']}) = {var['value']}\n")
        file.write('\n')
        for var in variables.getVariables():
          if var['type'] == 'ram':
            file.write(f"{var['type']}: {var['name']} (address {var['address']})\n")
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
        print(f"{line['address']} : {line['mnemonic']} {tempargs} [{line['line']}]")

    if options_noargs['--debug-vars']:
      print('\n  Program Variables:\n')
      for var in variables.getVariables():
        if var['type'] == 'pre':
          print(f"{var['type']}: {var['name']} = {var['value']}")
      print()
      for var in variables.getVariables():
        if var['type'] == 'rom':
          print(f"{var['type']}: {var['name']} (address {var['address']}) = {var['value']}")
      print()
      for var in variables.getVariables():
        if var['type'] == 'ram':
          print(f"{var['type']}: {var['name']} (address {var['address']})")

errorList = []
warningList = []

def endExecution():
  if len(errorList) > 0:
    warningPrint(warningList)
    errorPrint(errorList)
    # TODO -- we need to exit after like 5 errors or something
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
  # oh shit, this won't work with multiple files... we'll have to sort and stuff
  with open(entries[0]['path'], 'r') as file:
    lines = file.readlines()
    for entry in entries:
      print(f'({entry["code"]}) Error in file \"{entry["path"]}\" at line {entry["line"]}:')
      original = lines[entry['line'] - 1].strip(' \n')
      print(f'  {original}')
      print(f'-> {entry["message"]}\n')

def warningPrint(entries):
  with open(entries[0]['path'], 'r') as file:
    lines = file.readlines()
    for entry in entries:
      print(f'({entry["code"]}) Warning for file \"{entry["path"]}\" at line {entry["line"]}:')
      original = lines[entry['line'] - 1].strip(' \n')
      print(f'  {original}')
      print(f'-> {entry["message"]}\n')


MNEM2OP        = {'nop': 0, 'ldr': 1, 'str': 2, 'lpt': 3, 'spt': 4, 'cmp': 5,
                  'add': 6, 'sub': 7, 'mul': 8, 'div': 9, 'mod': 10, 'and': 11,
                  'or': 12, 'xor': 13, 'not': 14, 'lsl': 15, 'lsr': 16, 'psh': 17,
                  'pop': 18, 'pek': 19, 'jmp': 20, 'jsr': 21, 'rts': 22, 'joc': 23,
                  'jsc': 24, 'rsc': 25}

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

MATH_DICT = {'ram': MATH_RAM, 'rom': MATH_IMM, 'pre': MATH_IMM, 'reg': MATH_REG}

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

def assembleInstructions(instructions, variables, labels):

  number = re.compile('([1-9][0-9_]*)|(0x[0-9A-Fa-f][0-9A-Fa-f_]*)|(0b[10][10_]*)|0')

  mathops = ['add', 'sub', 'mul', 'div', 'mod',
             'and', 'or', 'xor', 'not', 'lsr', 'lsl']

  machine = np.empty(len(instructions), dtype='u4')

  for i in range(len(instructions)):
    word = 0
    inst = instructions[i]['mnemonic']
    word = setOpcode(word, inst)

    if inst in mathops:
      args = instructions[i]['arguments']

      if len(args) < 2 or len(args) > 3:
        errmess = f'\"{inst}\" needs at least two arguments and takes no more than three'
        error(errmess, instructions[i]['line'], instructions[i]['path'], 4)

      try:
        word = setOperand1(word, args[0])
      except KeyError:
        errmess = f'\"{args[0]}\" is not a valid register'
        error(errmess, instructions[i]['line'], instructions[i]['path'], 3)

      # since the second argument can be many different things,
      # it's not immdiately an error if the argument is not a register
      try:
        word = setOperand2(word, args[1])
        word = setOpvar(word, MATH_DICT['reg'])
      except KeyError:
        found = False
        for var in variables:
          if args[1] == var['name']:
            found = True
            word = setWord2(word, int(var['value']))
            word = setOpvar(word, MATH_DICT[var['type']])
            if var['type'] == 'rom':
              warnmess = 'rom values cannot be directly loaded as operands:\n embedding value in instruction'
              warning(warnmess, instructions[i]['line'], instructions[i]['path'], 6)
            break
        if not found:
          # could still be a number XD
          numstring = str(args[1])
          if re.search(number, numstring) != None:
            word = setWord2(word, eval(numstring))
            word = setOpvar(word, MATH_IMM)
          else:
            errmess = f'\"{args[1]}\" is not defined'
            error(errmess, instructions[i]['line'], instructions[i]['path'], 1)

        if len(args) == 3:
          try:
            word = setResults(word, args[2])
          except KeyError:
            errmess = f'\"{args[2]}\" is not a valid register'
            error(errmess, instructions[i]['line'], instructions[i]['path'], 2)
        else:
          word = setResults(word, args[0])

    elif inst == 'nop':
      pass
    elif inst == 'ldr':
      pass

    machine[i] = word

  return machine
