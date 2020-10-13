import sys
from antlr4 import *
from gen.CorLexer import CorLexer
from gen.CorParser import CorParser
from VusListener import *
from assemblyutils import *

RAM_ADDRESS_BEGIN = 41
PROGRAM_WORD_WIDTH = 32
DATA_WORD_WIDTH = 16

def main(argv):

  ##############################################################
  ### INPUT
  ##############################################################

  # TODO -- detect erroneous arguments, like paths with spaces in them
  # that are not contained in quotes:
  # programs/program file.cor

  print()

  infile = ''
  options_args = {'-o': '', '-p': '', '-d': '', '-P': 10, '-D': 10, '--log': ''}
  options_noargs = {'--debug-vars': False, '--debug-lines': False}

  if len(argv) < 2:
    usage()
  for arg in argv:
    if arg == '-h':
      usage()

  infile = argv[1]
  if '.cor' not in infile[-4:]:
    print("Error: first argument must be a file of type .cor", end='\n\n')
    exit(1)

  length = len(argv)
  for i in range(length):
    if argv[i] in options_args:
      if i == length - 1 or argv[i + 1] in options_args or argv[i + 1] in options_noargs:
        print(f"Error: expected argument after option {argv[i]}", end='\n\n')
        exit(1)
      options_args[argv[i]] = argv[i + 1]
    elif argv[i] in options_noargs:
      options_noargs[argv[i]] = True


  ##############################################################
  ### PARSING
  ##############################################################

  # sorting out file imports
  importListener = ImportListener(infile)

  input = FileStream(infile)
  lexer = CorLexer(input)
  stream = CommonTokenStream(lexer)
  parser = CorParser(stream)
  tree = parser.initial()
  walker = ParseTreeWalker()

  # recursively seraches through files until all are added to imports list
  walker.walk(importListener, tree)

  # print(importListener.imports)

  # proper parsing
  listener = VusListener(importListener.getImports()[-1]['name'], RAM_ADDRESS_BEGIN, importListener.imports[-1]['path'], SYSVARS)
  labels = Labels()
  instructions = Instructions()

  for file in importListener.getImports():
    input = FileStream(file['path'])
    lexer = CorLexer(input)
    stream = CommonTokenStream(lexer)
    parser = CorParser(stream)
    tree = parser.parse()

    numInstructions = listener.getNumInstructions()
    labels.insert(listener.getLabels().getLabels(), numInstructions)
    instructions.insert(listener.getInstructions().getInstructions(), numInstructions)
    listener.reset()
    listener.setName(file['name'], file['path'], stream)
    walker = ParseTreeWalker()
    walker.walk(listener, tree)

  numInstructions = listener.getNumInstructions()
  labels.insert(listener.getLabels().getLabels(), numInstructions)
  instructions.insert(listener.getInstructions().getInstructions(), numInstructions)

  debug(options_args, options_noargs, instructions, listener.getVariables(), labels)

  code = []

  if options_args['-o'] != '':
    writeBin(code, options_args['-o'],
             int(options_args['-P']),
             int(options_args['-D']),
             PROGRAM_WORD_WIDTH,
             DATA_WORD_WIDTH)

  prom = []
  drom = []

  prom = assembleInstructions(listener.getInstructions().getInstructions(),
                              listener.getVariables().getVariables(),
                              listener.getLabels().getLabels())
  drom = assembleVariables(listener.getVariables().getVariables(), DATA_WORD_WIDTH)

  endExecution()

  if options_args['-p'] != '':
    writeMem(prom, options_args['-p'] + '.hex', int(options_args['-P']), PROGRAM_WORD_WIDTH)
  if options_args['-d'] != '':
    writeMem(drom, options_args['-d'] + '.hex', int(options_args['-D']), DATA_WORD_WIDTH)



if __name__ == '__main__':
  main(sys.argv)
