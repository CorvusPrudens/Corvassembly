#!/usr/bin/env python

import sys
from antlr4 import *
from gen.CorLexer import CorLexer
from gen.CorParser import CorParser
from VusListener import *
from assemblyutils import *

RAM_ADDRESS_BEGIN = 48
PGM_ADDRESS_BEGIN = 3
PROGRAM_WORD_WIDTH = 32
DATA_WORD_WIDTH = 16

def main(argv):

  ##############################################################
  ### INPUT
  ##############################################################

  # TODO -- detect erroneous arguments, like paths with spaces in them
  # that are not contained in quotes:
  # programs/program file.cor

  # print()

  infile = ''
  options_args = {'-p': '', '-d': '', '-P': 10, '-D': 10, '--log': ''}
  options_noargs = {'--debug-vars': False, '--debug-lines': False}

  if len(argv) < 2:
    usage()
  for arg in argv:
    if arg == '-h' or arg == '--help':
      usage()

  infile = argv[1]
  if '.cor' not in infile[-4:]:
    print("Error: first argument must be a file of type .cor", end='\n\n')
    exit(1)

  length = len(argv)
  tempargs = argv
  for i in range(length - 1, -1, -1):
    if tempargs[i] in options_args:
      if i == length - 1 or tempargs[i + 1] in options_args or tempargs[i + 1] in options_noargs:
        print(f"Error: expected argument after option {tempargs[i]}", end='\n\n')
        exit(1)
      options_args[tempargs[i]] = tempargs[i + 1]
      tempargs.pop(i)
      tempargs.pop(i)
    elif tempargs[i] in options_noargs:
      options_noargs[tempargs[i]] = True
      tempargs.pop(i)

  if len(tempargs) > 2:
    print(f'Error: undefined option \"{tempargs[2]}\"\n')
    exit(1)


  ##############################################################
  ### PARSING
  ##############################################################

  try:
    input = FileStream(infile)
  except FileNotFoundError:
    print(f'Error: cannot find file \"{infile}\"\n')
    exit(1)
  lexer = CorLexer(input)
  lexer.removeErrorListeners()
  # custom error listener
  importError = ImportErrorListener(infile)
  lexer.addErrorListener(importError)
  stream = CommonTokenStream(lexer)
  parser = CorParser(stream)

  # sorting out file imports
  importListener = ImportListener(infile, stream)

  parser.removeErrorListeners()
  parser.addErrorListener(importError)
  tree = parser.initial()
  walker = ParseTreeWalker()

  # recursively seraches through files until all are added to imports list
  walker.walk(importListener, tree)

  # proper parsing
  listener = VusListener(importListener.getImports()[-1]['name'],
                         RAM_ADDRESS_BEGIN, PGM_ADDRESS_BEGIN,
                         importListener.imports[-1]['path'], SYSVARS)
  # NOTE -- main file is the last addition to imports list
  labels = Labels()
  instructions = Instructions()
  variables = Variables()

  for file in importListener.getImports():
    # custom error listener
    vusError = VusErrorListener(filepath=file['path'])
    input = FileStream(file['path'])
    lexer = CorLexer(input)
    lexer.removeErrorListeners()
    lexer.addErrorListener(vusError)
    stream = CommonTokenStream(lexer)
    parser = CorParser(stream)
    parser.removeErrorListeners()
    parser.addErrorListener(vusError)
    tree = parser.parse()

    numInstructions = listener.getNumInstructions()
    labels.insert(listener.getLabels().getLabels(), numInstructions)
    instructions.insert(listener.getInstructions().getInstructions(), numInstructions)
    variables.insert(listener.getVariables().getVariables())
    listener.reset(file['name'], file['path'], stream, startaddr=PGM_ADDRESS_BEGIN)
    # listener.setName(file['name'], file['path'], stream)
    walker = ParseTreeWalker()
    walker.walk(listener, tree)

  numInstructions = listener.getNumInstructions()
  labels.insert(listener.getLabels().getLabels(), numInstructions)
  # labels.labels.insert(0, {'name': '__pgm_start__', 'address': 3})
  instructions.insert(listener.getInstructions().getInstructions(), numInstructions)

  listener.getLabels().setInit(pgmStartAddress=PGM_ADDRESS_BEGIN)
  labels.setInit(pgmStartAddress=PGM_ADDRESS_BEGIN)


  for i in range(len(INIT_INSTRUCTIONS) - 1, -1, -1):
    listener.getInstructions().getInstructions().insert(0, INIT_INSTRUCTIONS[i])
    instructions.getInstructions().insert(0, INIT_INSTRUCTIONS[i])
  setInterrupts(labels.getLabels(),
                instructions.getInstructions())


  ##############################################################
  ### OUTPUT
  ##############################################################

  debug(options_args, options_noargs, instructions, listener.getVariables(), labels)

  code = []

  # if options_args['-o'] != '':
  #   writeBin(code, options_args['-o'],
  #            int(options_args['-P']),
  #            int(options_args['-D']),
  #            PROGRAM_WORD_WIDTH,
  #            DATA_WORD_WIDTH)

  prom = []
  drom = []

  prom = assembleInstructions(instructions.getInstructions(),
                              listener.getVariables().getVariables(),
                              labels.getLabels())
  drom = assembleVariables(listener.getVariables().getVariables(), DATA_WORD_WIDTH)
  endExecution()

  if options_args['-p'] != '':
    writeMem(prom, options_args['-p'] + '.hex', int(options_args['-P']), PROGRAM_WORD_WIDTH)
  if options_args['-d'] != '':
    writeMem(drom, options_args['-d'] + '.hex', int(options_args['-D']), DATA_WORD_WIDTH)


if __name__ == '__main__':
  main(sys.argv)
