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

    infile, options_args, options_noargs = parseInput(argv)

    ##############################################################
    # PARSING
    ##############################################################

    try:
        input = FileStream(infile)
    except FileNotFoundError:
        print(f'Error: cannot find file \"{infile}\"\n')
        sys.exit(1)
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
        instructions.insert(listener.getInstructions().getInstructions(),
                            numInstructions)
        variables.insert(listener.getVariables().getVariables())
        listener.reset(file['name'],
                       file['path'],
                       stream,
                       startaddr=PGM_ADDRESS_BEGIN)
        # listener.setName(file['name'], file['path'], stream)
        walker = ParseTreeWalker()
        walker.walk(listener, tree)

    numInstructions = listener.getNumInstructions()
    labels.insert(listener.getLabels().getLabels(), numInstructions)
    # labels.labels.insert(0, {'name': '__pgm_start__', 'address': 3})
    instructions.insert(listener.getInstructions().getInstructions(),
                        numInstructions)

    listener.getLabels().setInit(pgmStartAddress=PGM_ADDRESS_BEGIN)
    labels.setInit(pgmStartAddress=PGM_ADDRESS_BEGIN)

    for i in range(len(INIT_INSTRUCTIONS) - 1, -1, -1):
        listener.getInstructions().getInstructions().insert(
            0, INIT_INSTRUCTIONS[i])
        instructions.getInstructions().insert(0, INIT_INSTRUCTIONS[i])
    setInterrupts(labels.getLabels(), instructions.getInstructions())

    ##############################################################
    # OUTPUT
    ##############################################################

    debug(options_args, options_noargs, instructions, listener.getVariables(),
          labels)

    prom = assembleInstructions(instructions.getInstructions(),
                                listener.getVariables().getVariables(),
                                labels.getLabels())
    drom = assembleVariables(listener.getVariables().getVariables(),
                             DATA_WORD_WIDTH)
    endExecution()

    if options_args['-p'] != '':
        writeMem(prom, options_args['-p'] + '.hex', int(options_args['-P']),
                 PROGRAM_WORD_WIDTH)
    if options_args['-d'] != '':
        writeMem(drom, options_args['-d'] + '.hex', int(options_args['-D']),
                 DATA_WORD_WIDTH)


if __name__ == '__main__':
    main(sys.argv)
