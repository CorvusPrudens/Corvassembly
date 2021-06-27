#!/usr/bin/env python

import sys

import antlr4
from . import _assembly_utils
from . import _vus_listener

from .gen.CorLexer import CorLexer
from .gen.CorParser import CorParser


def main(argv) -> dict:

    """
    Run the assembler with the given arguments, and
    return any directives found in the program.
    
    """

    infile, options_args, options_noargs = _assembly_utils.parse_input(argv)

    ##############################################################
    # PARSING
    ##############################################################

    try:
        file_stream = antlr4.FileStream(infile)
    except FileNotFoundError:
        print(f'Error: cannot find file "{infile}"\n')
        sys.exit(1)
    lexer = CorLexer(file_stream)
    lexer.removeErrorListeners()

    error_listener = _vus_listener.ImportErrorListener(infile)
    lexer.addErrorListener(error_listener)
    stream = antlr4.CommonTokenStream(lexer)
    parser = CorParser(stream)

    import_listener = _vus_listener.ImportListener(infile, stream)

    parser.removeErrorListeners()
    parser.addErrorListener(error_listener)
    tree = parser.initial()
    walker = antlr4.ParseTreeWalker()

    # recursively seraches through files until all are added to imports list
    walker.walk(import_listener, tree)

    # proper parsing
    listener = _vus_listener.VusListener(
        import_listener.getImports()[-1]["name"],
        _assembly_utils.Globals.RAM_ADDRESS_BEGIN,
        _assembly_utils.Globals.PGM_ADDRESS_BEGIN,
        import_listener.imports[-1]["path"],
        _assembly_utils.Globals.SYSVARS,
    )

    # NOTE -- main file is the last addition to imports list
    labels = _vus_listener.Labels()
    instructions = _vus_listener.Instructions()

    import_shuffle = import_listener.getImports()
    if len(import_shuffle) > 2:
        import_shuffle = import_shuffle[len(import_shuffle)-2::-1] + import_shuffle[-1:]
    for file in import_shuffle:
        # print(file)
        error_listener = _vus_listener.VusErrorListener(filepath=file["path"])
        file_stream = antlr4.FileStream(file["path"])
        lexer = CorLexer(file_stream)
        lexer.removeErrorListeners()
        lexer.addErrorListener(error_listener)
        stream = antlr4.CommonTokenStream(lexer)

        parser = CorParser(stream)
        parser.removeErrorListeners()
        parser.addErrorListener(error_listener)
        tree = parser.parse()

        num_instructions = listener.getNumInstructions()
        labels.insert(listener.getLabels().getLabels(), num_instructions)
        instructions.insert(
            listener.getInstructions().getInstructions(), num_instructions
        )

        listener.reset(
            file["name"],
            file["path"],
            stream,
            start_addr=_assembly_utils.Globals.PGM_ADDRESS_BEGIN,
        )

        walker = antlr4.ParseTreeWalker()
        walker.walk(listener, tree)

    num_instructions = listener.getNumInstructions()
    labels.insert(listener.getLabels().getLabels(), num_instructions)
    instructions.insert(listener.getInstructions().getInstructions(), num_instructions)

    labels.setInit(program_start_addr=_assembly_utils.Globals.PGM_ADDRESS_BEGIN)

    _assembly_utils.init_ram(instructions, listener.getVariables(), labels)

    for i in range(len(_assembly_utils.Globals.INIT_INSTRUCTIONS) - 1, -1, -1):
        instructions.getInstructions().insert(
            0, _assembly_utils.Globals.INIT_INSTRUCTIONS[i]
        )
    _assembly_utils.set_interrupts(labels.getLabels(), instructions.getInstructions())

    # Final stage
    _assembly_utils.generate_output(
        options_args, options_noargs, listener, labels, instructions
    )

    # this is a bit of a hack, but I don't feel like fixing it right now
    testing = _assembly_utils.test_output(instructions, listener.getVariables(), labels)

    return listener.directives, testing


if __name__ == "__main__":
    main(sys.argv)
