
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
          for i in range(len(line['args'])):
            tempargs += str(line['args'][i])
            if i < len(line['args']) - 1:
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
        for i in range(len(line['args'])):
          tempargs += str(line['args'][i])
          if i < len(line['args']) - 1:
            tempargs += ', '
        print(f"{line['address']} : {line['mnemonic']} {tempargs}")

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
