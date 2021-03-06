import math
import sys
import re
import numpy as np
import copy

from . import _keyword_data


class Globals:
    RAM_ADDRESS_BEGIN = 65
    PGM_ADDRESS_BEGIN = 3
    PROGRAM_WORD_WIDTH = 32
    DATA_WORD_WIDTH = 16
    RAM_SIZE = 2048
    ROM_SIZE = 1024

    INIT_INSTRUCTIONS = [
        {
            "mnemonic": "jmp",
            "address": 0,
            "arguments": ["__pgm_start__"],
            "line": -1,
            "path": -1,
        },
        {
            "mnemonic": "jmp",
            "address": 1,
            "arguments": ["__pgm_start__"],
            "line": -1,
            "path": -1,
        },
        {
            "mnemonic": "jmp",
            "address": 2,
            "arguments": ["__pgm_start__"],
            "line": -1,
            "path": -1,
        },
    ]

    sysvarTable = _keyword_data.sysvars

    SYSVARS = {}

    for var in sysvarTable:
        if var[0] == "ram":
            SYSVARS[var[1]] = {
                "type": "ram",
                "address": int(var[2]),
                "value": None,
                "line": -1,
                "path": -1,
            }
        elif var[0] == "pre":
            SYSVARS[var[1]] = {
                "type": "pre",
                "name": var[1],
                "address": 0,
                "value": var[2],
                "line": -1,
                "path": -1,
            }


class Colors:
    head = "\033[95m"
    blue = "\033[94m"
    cyan = "\033[96m"
    green = "\033[92m"
    yellow = "\033[93m"
    red = "\033[91m"
    stop = "\033[0m"
    bold = "\033[1m"
    underline = "\033[4m"


##############################################################
# I/O
##############################################################


def usage():
    print("\nUsage: <infile> [[options] [parameters] ...]")
    print("If no options are given, the program will not generate any output.")
    print("\n\t-h\n\t   print this usage message")
    print("\n\t-o output\n\t   specify the output binary name (defaults to 'out')")
    # print("\n\t-o outfile\n\t   generate output binary with the given name")
    print(
        "\n\t-p romname\n\t   generate program rom verilog hex with the given romname"
    )
    print("\n\t-d romname\n\t   generate data rom verilog hex with the given romname")
    print("\n\t-P bits\n\t   specify the number of bits in the program rom address")
    print("\n\t-D bits\n\t   specify the number of bits in the data rom address")
    print("\n\t-s sector\n\t   indicate program sector (defaults to 0 (0x200000)")
    print(
        "\n\t--debug-vars\n\t   print out the organized variables for debugging checks"
    )
    print(
        "\n\t--debug-lines\n\t   print out the organized program lines for debugging checks"
    )
    print(
        '\n\t--log logname\n\t   write the above debug messages to "logname.txt" instead of the terminal'
    )
    print()
    sys.exit(0)


def parse_input(argv):
    infile = ""
    options_args = {"-p": "", "-d": "", "-o": "out", "-P": 10, "-D": 10, "-s": 0, "--pexp": None, "--dexp": None, "--log": ""}
    options_noargs = {"--debug-vars": False, "--debug-lines": False}

    if len(argv) < 2:
        usage()
    for arg in argv:
        if arg in ("-h", "--help"):
            usage()

    infile = argv[1]
    if ".cor" not in infile[-4:]:
        print("Error: first argument must be a file of type .cor", end="\n\n")
        sys.exit(1)

    length = len(argv)
    tempargs = argv
    for i in range(length - 1, -1, -1):
        if tempargs[i] in options_args:
            if (
                i == length - 1
                or tempargs[i + 1] in options_args
                or tempargs[i + 1] in options_noargs
            ):
                print(
                    f"Error: expected argument after option {tempargs[i]}", end="\n\n"
                )
                sys.exit(1)
            options_args[tempargs[i]] = tempargs[i + 1]
            tempargs.pop(i)
            tempargs.pop(i)
        elif tempargs[i] in options_noargs:
            options_noargs[tempargs[i]] = True
            tempargs.pop(i)

    if len(tempargs) > 2:
        print(f'Error: undefined option "{tempargs[2]}"\n')
        sys.exit(1)

    return infile, options_args, options_noargs


def generate_output(options_args, options_noargs, listener, labels, instructions):
    debug(options_args, options_noargs, instructions, listener.getVariables(), labels)

    prom = assemble_instruction(
        instructions.getInstructions(),
        listener.getVariables().getVariables(),
        labels.getLabels(),
    )
    drom = assemble_variables(
        listener.getVariables().getVariables(), Globals.DATA_WORD_WIDTH
    )
    end_execution()

    prombits = options_args["-P"] if listener.directives['prom'] == -1 else listener.directives['prom']
    drombits = options_args["-D"] if listener.directives['drom'] == -1 else listener.directives['drom']

    if options_args["-p"] != "":
        write_memory(
            prom,
            options_args["-p"] + ".hex",
            int(prombits),
            Globals.PROGRAM_WORD_WIDTH,
            explicit=options_args['--pexp'],
            mem_name="Program ROM"
        )
    if options_args["-d"] != "":
        write_memory(
            drom,
            options_args["-d"] + ".hex",
            int(drombits),
            Globals.DATA_WORD_WIDTH,
            explicit=options_args['--dexp'],
            mem_name="Data ROM"
        )


def enbitten(data: 'list[int]', num_bytes: int, little_endian: bool = True) -> list:
    output = []
    for number in data:
        for i in range(num_bytes):
            if little_endian:
                output.append((number >> (i * 8)) & 255)
            else:
                output.append((number >> ((num_bytes - 1 - i) * 8)) & 255)
    return output


def generate_binary(options_args, options_noargs, listener, labels, instructions):
    debug(options_args, options_noargs, instructions, listener.getVariables(), labels)

    prom = assemble_instruction(
        instructions.getInstructions(),
        listener.getVariables().getVariables(),
        labels.getLabels(),
    )
    drom = assemble_variables(
        listener.getVariables().getVariables(), Globals.DATA_WORD_WIDTH
    )
    ram = assemble_ram(
        listener.getVariables().getVariables(), Globals.DATA_WORD_WIDTH
    )

    address = 0x2000 + int(options_args['-s']) * 0x1000

    progbin = enbitten(prom, 4)
    progbin += [0 for n in range(0x040000 - len(progbin))]

    rambin = enbitten(ram, 2)
    rambin += [0 for n in range(0x1000 - len(rambin))]

    rombin = enbitten(drom, 2)
    rombin += [0 for n in range(0x0800 - len(rombin))]
    if len(rombin) > Globals.ROM_SIZE * 2:
        errmess = f'requested ROM size ({len(rombin) // 2}) greater than available space ({Globals.ROM_SIZE})'
        error(errmess, -1, -1, 411, line_based=False)

    end_execution()

    # TODO -- then allow extra data in the second half of the sector
    # TODO -- add output binary name arg

    output = progbin + rambin + rombin
    output += [0 for n in range(0x100000 - len(output))]

    outname = options_args["-o"]
    outname = outname + '.bin' if '.bin' not in outname[-4:] else outname
    with open(outname, 'wb') as file:
        output = bytes(output)
        file.write(output)


def write_memory(code, outfile, memory_addr_bits, memory_bits, explicit=None, mem_name = 'Program ROM'):
    max_words = 2 ** memory_addr_bits if explicit is None else int(explicit)
    memory_width = math.ceil(memory_bits / 4)
    words_per_line = math.floor((8 / memory_width) * 8)
    if words_per_line < 1:
        words_per_line = 1
    memory_addr_width = math.ceil(memory_addr_bits / 4)
    num_lines = math.ceil(max_words / words_per_line)

    with open(outfile, "w") as file:
        for i in range(num_lines):
            file.write("@{:0>{w}X} ".format(i * words_per_line, w=memory_addr_width))
            for j in range(words_per_line):
                if j + i * words_per_line < len(code):
                    file.write(
                        "{:0>{w}X} ".format(
                            code[j + i * words_per_line], w=memory_width
                        )
                    )
                else:
                    if j + i * words_per_line >= max_words:
                        break
                    file.write("{:0>{w}X} ".format(0, w=memory_width))
            file.write("\n")
    if len(code) > max_words:
        print(
            "Error: {} size too big ({} words) for available memory ({} words)".format(
                mem_name, len(code), max_words
            )
        )
        sys.exit(1)


# def writeBin(code, outfile, promAddBits, dromAddBits, promDataWidth,
#              dromDataWidth):
#     pass


def debug(options_args, options_noargs, instructions, variables, labels):
    variables = variables.getVariables()
    if options_args["--log"] != "":
        with open(options_args["--log"] + ".txt", "w") as file:
            if options_noargs["--debug-lines"]:
                file.write("\n  Program Instructions:\n")
                for line in instructions.getInstructions():
                    for label in labels.getLabels():
                        if label["address"] == line["address"]:
                            file.write(f"{label['name']}:\n")
                    tempargs = ""
                    for i in range(len(line["arguments"])):
                        tempargs += str(line["arguments"][i])
                        if i < len(line["arguments"]) - 1:
                            tempargs += ", "
                    file.write(f"{line['address']} : {line['mnemonic']} {tempargs}\n")

            if options_noargs["--debug-vars"]:
                file.write("\n  Program Variables:\n")
                for var in variables:
                    if variables[var]["type"] == "pre":
                        file.write(
                            "{}: {} = {}\n".format(
                                variables[var]["type"], var, variables[var]["value"]
                            )
                        )
                file.write("\n")
                for var in variables:
                    if variables[var]["type"] == "rom":
                        file.write(
                            "{}: {} (address {}) = {}\n".format(
                                variables[var]["type"],
                                var,
                                variables[var]["address"],
                                variables[var]["value"],
                            )
                        )
                file.write("\n")
                for var in variables:
                    if variables[var]["type"] == "ram":
                        file.write(
                            "{}: {} (address {})\n".format(
                                variables[var]["type"], var, variables[var]["address"]
                            )
                        )
    else:
        if options_noargs["--debug-lines"]:
            print("\n  Program Instructions:\n")
            for line in instructions.getInstructions():
                for label in labels.getLabels():
                    if label["address"] == line["address"]:
                        print(f"{label['name']}:")
                tempargs = ""
                for i in range(len(line["arguments"])):
                    tempargs += str(line["arguments"][i])
                    if i < len(line["arguments"]) - 1:
                        tempargs += ", "
                print(
                    f"{hex(line['address'])} : {line['mnemonic']} {tempargs} [{line['line']}]"
                )

        if options_noargs["--debug-vars"]:
            print("\n  Program Variables:\n")
            for var in variables:
                if variables[var]["type"] == "pre":
                    print(
                        f"{variables[var]['type']}: {var} = {variables[var]['value']}"
                    )
            print()
            for var in variables:
                if variables[var]["type"] == "rom":
                    print(
                        f"{variables[var]['type']}: {var} (address {variables[var]['address']}) = {variables[var]['value']}"
                    )
            print()
            for var in variables:
                if variables[var]["type"] == "ram":
                    print(
                        f"{variables[var]['type']}: {var} (address {variables[var]['address']})"
                    )


def test_output(instructions, variables, labels):
    variables = variables.getVariables()
    lines = []

    for line in instructions.getInstructions():
        for label in labels.getLabels():
            if label["address"] == line["address"]:
                lines.append(f"{label['name']}:\n")
        tempargs = ""
        for i in range(len(line["arguments"])):
            tempargs += str(line["arguments"][i])
            if i < len(line["arguments"]) - 1:
                tempargs += ", "
        lines.append(f"{line['mnemonic']} {tempargs}\n")
    
    # grabbing final labels:
    last_line = instructions.getInstructions()[-1]
    for label in labels.getLabels():
        if label["address"] == last_line["address"] + 1:
            lines.append(f"{label['name']}:\n")

        # if options_noargs["--debug-vars"]:
        #     file.write("\n  Program Variables:\n")
        #     for var in variables:
        #         if variables[var]["type"] == "pre":
        #             file.write(
        #                 "{}: {} = {}\n".format(
        #                     variables[var]["type"], var, variables[var]["value"]
        #                 )
        #             )
        #     file.write("\n")
        #     for var in variables:
        #         if variables[var]["type"] == "rom":
        #             file.write(
        #                 "{}: {} (address {}) = {}\n".format(
        #                     variables[var]["type"],
        #                     var,
        #                     variables[var]["address"],
        #                     variables[var]["value"],
        #                 )
        #             )
        #     file.write("\n")
        #     for var in variables:
        #         if variables[var]["type"] == "ram":
        #             file.write(
        #                 "{}: {} (address {})\n".format(
        #                     variables[var]["type"], var, variables[var]["address"]
        #                 )
        #             )
    return ''.join(lines)


errorList = []
warningList = []


def end_execution():
    if len(errorList) > 0:
        print()
        warning_print(warningList)
        error_print(errorList)
        warn_s = "s" if len(warningList) != 1 else ""
        error_s = "s" if len(errorList) != 1 else ""
        print(
            f"{len(warningList)} warning{warn_s}, {len(errorList)} error{error_s}, exiting..."
        )
        sys.exit(1)
    if len(warningList) > 0:
        print()
        warning_print(warningList)
        warn_s = "s" if len(warningList) != 1 else ""
        error_s = "s" if len(errorList) != 1 else ""
        print(f"{len(warningList)} warning{warn_s}, {len(errorList)} error{error_s}")


def error(message, line_num, filepath, errorcode, syntax=False, col=0, abort=False, line_based=True):
    global errorList
    errorList.append(
        {
            "message": message,
            "line": line_num,
            "path": filepath,
            "code": errorcode,
            "syntax": syntax,
            "col": col,
            "line_based": line_based
        }
    )
    if len(errorList) == 10:
        end_execution()
    if abort:
        print(f"{Colors.red}Fatal error: assembly stopped.{Colors.stop}")
        end_execution()


def warning(message, line_num, filepath, errorcode):
    global warningList
    warningList.append(
        {"message": message, "line": line_num, "path": filepath, "code": errorcode}
    )


def error_print(entries):
    for entry in entries:
        try:
            if entry['line_based']:
                with open(entry["path"], "r") as file:
                    lines = file.readlines()
                    if entry["syntax"]:
                        print(
                            f'({entry["code"]}) {Colors.red}Error{Colors.stop} in file {Colors.green}"{entry["path"]}"{Colors.stop} at line {entry["line"]}:'
                        )
                        original = lines[entry["line"] - 1].rstrip(" \n")
                        print(f"  {original}")
                        print(f'  {" "*entry["col"]}{Colors.red}^{Colors.stop}')
                        print(f'{Colors.blue}->{Colors.stop} {entry["message"]}\n')
                    else:
                        print(
                            f'({entry["code"]}) {Colors.red}Error{Colors.stop} in file {Colors.green}"{entry["path"]}"{Colors.stop} at line {entry["line"]}:'
                        )
                        original = lines[entry["line"] - 1].strip(" \n")
                        print(f"  {Colors.cyan}{original}{Colors.stop}")
                        print(f'{Colors.blue}->{Colors.stop} {entry["message"]}\n')
            else:
                print(
                    f'({entry["code"]}) {Colors.red}Error{Colors.stop}:'
                )
                print(f'{Colors.blue}->{Colors.stop} {entry["message"]}\n')
        except FileNotFoundError:
            # print(f'({entry["code"]}) Error in file \"{entry["path"]}\" at line {entry["line"]}:')
            # original = lines[entry['line'] - 1].strip(' \n')
            # print(f'  {original}')
            print(f'-> {entry["message"]}\n')


def warning_print(entries):
    for entry in entries:
        with open(entry["path"], "r") as file:
            lines = file.readlines()
            print(
                f'({entry["code"]}) {Colors.yellow}Warning{Colors.stop} in file {Colors.green}"{entry["path"]}"{Colors.stop} at line {entry["line"]}:'
            )
            original = lines[entry["line"] - 1].rstrip(" \n")
            print(f"  {original}")
            print(f'{Colors.blue}->{Colors.stop} {entry["message"]}\n')


##############################################################
# Assembly Data
##############################################################

# fmt: off
MNEM2OP = {
    'nop': 0, 'ldr': 1, 'str': 2, 'lpt': 3,
    'spt': 4, 'cmp': 5, 'add': 6, 'sub': 7,
    'mul': 8, 'div': 9, 'mod': 10, 'and': 11,
    'or': 12, 'xor': 13, 'not': 14, 'lsl': 15,
    'lsr': 16, 'psh': 17, 'pop': 18, 'pek': 19,
    'jmp': 20, 'jsr': 21, 'rts': 22, 'joc': 23,
    'jsc': 24, 'rsc': 25, 'rti': 26, 'ric': 27
}
# fmt: on
INTERRUPT_VECTORS = {"FRAME": 1, "TIMER": 2}

OPVAR_SHIFT = 0
OPCODE_SHIFT = 2
OPERAND1_SHIFT = 7
OPERAND2_SHIFT = 10
RESULTS_SHIFT = 13
WORD2_SHIFT = 16

REGISTERS = {"a": 0, "b": 1, "c": 2, "d": 3, "e": 4, "f": 5, "g": 6, "h": 7}
OPVAR_MASK = 0b11 << OPVAR_SHIFT
OPCODE_MASK = 0b11111 << OPCODE_SHIFT
OPERAND1_MASK = 0b111 << OPERAND1_SHIFT
OPERAND2_MASK = 0b111 << OPERAND2_SHIFT
RESULTS_MASK = 0b111 << RESULTS_SHIFT
WORD2_MASK = 0xFF << WORD2_SHIFT

MATH_RAM = 0b00
MATH_REG = 0b01
MATH_GPU = 0b10
MATH_IMM = 0b11

LOAD_ROM = 0b01

STORE_RAM = 0b00
STORE_ROM = 0b01
STORE_GPU = 0b10

LOAD_PTR_RAM = 0b00
LOAD_PTR_ROM = 0b01
LOAD_PTR_GPU = 0b10

STORE_PTR_RAM = 0b00
STORE_PTR_ROM = 0b01
STORE_PTR_GPU = 0b10

LOAD_CONST = ["rom", "pre"]
LOAD_MEM = ["ram", "gpu"]

MATH_DICT = {
    "ram": MATH_RAM,
    "rom": MATH_IMM,
    "pre": MATH_IMM,
    "reg": MATH_REG,
    "number": MATH_IMM,
}

LOAD_DICT = {
    "ram": MATH_RAM,
    "rom": LOAD_ROM,
    "gpu": MATH_GPU,
    "pre": MATH_IMM,
    "number": MATH_IMM,
}

# The extra keys are so that an incorrect instruction doesn't throw a KeyError
STORE_DICT = {"ram": STORE_RAM, "rom": STORE_ROM, "gpu": STORE_GPU, "pre": 0}

LOAD_PTR_DICT = {"ram": LOAD_PTR_RAM, "rom": LOAD_PTR_ROM, "gpu": LOAD_PTR_GPU}
STORE_PTR_DICT = {"ram": STORE_PTR_RAM, "rom": STORE_PTR_ROM, "gpu": STORE_PTR_GPU}

CONDITIONS = {
    "zero": 0b000001,
    "carry": 0b000010,
    "negative": 0b000100,
    "equal": 0b001000,
    "greater": 0b010000,
    "less": 0b100000,
}


def set_interrupts(labels, instructions):
    # preparing interrupt instructions
    for label in labels:
        if "interrupt" in label:
            try:
                # by the gods what have I done
                tempargs = instructions[INTERRUPT_VECTORS[label["interrupt"]]][
                    "arguments"
                ]
                if tempargs[0] != "__pgm_start__":
                    # this means the user attempted to link an interrupt to two subroutines
                    errmess = f'"{label["interrupt"]}" cannot  be attached to more than one subroutine'
                    error(errmess, label["line"], label["path"], 6)
                instructions[INTERRUPT_VECTORS[label["interrupt"]]]["arguments"] = [
                    label["name"]
                ]
            except KeyError:
                errmess = f'"{label["interrupt"]}" is not a valid interrupt vector'
                error(errmess, label["line"], label["path"], 6)


def set_operand1(word, register):
    word |= REGISTERS[register] << OPERAND1_SHIFT
    return word


def set_operand2(word, register):
    word |= REGISTERS[register] << OPERAND2_SHIFT
    return word


def set_results(word, register):
    word |= REGISTERS[register] << RESULTS_SHIFT
    return word


def set_opcode(word, mnemonic):
    word |= MNEM2OP[mnemonic] << OPCODE_SHIFT
    return word


def set_opvar(word, opvar):
    word |= opvar << OPVAR_SHIFT
    return word


def set_word2(word, word2):
    word |= word2 << WORD2_SHIFT
    return word


def assemble_register(word, reg, operand, instruction):
    opdict = {
        "operand1": OPERAND1_SHIFT,
        "operand2": OPERAND2_SHIFT,
        "results": RESULTS_SHIFT,
    }
    try:
        word |= REGISTERS[reg] << opdict[operand]
    except KeyError:
        errmess = f'"{reg}" is not a valid register'
        error(errmess, instruction["line"], instruction["path"], 3)
    return word


def assemble_argument2(word, argument, dict, instruction, variables, number):
    try:
        if isinstance(argument, str) and argument[0] == "&":
            try:
                if variables[argument[1:]]["type"] == "pre":
                    errmess = f"Pre-processor variable {argument[1:]} does not have an address"
                    error(errmess, instruction["line"], instruction["path"], 1)
                else:
                    var = variables[argument[1:]]
                    word = set_word2(word, var["address"])
            except KeyError:
                errmess = f'"{argument[1:]}" is not defined'
                error(errmess, instruction["line"], instruction["path"], 1)
        else:
            var = variables[argument]
            if var["type"] in LOAD_CONST:
                word = set_word2(word, int(var["value"]))
            else:
                word = set_word2(word, int(var["address"]))
            word = set_opvar(word, dict[var["type"]])
            if var["type"] == "rom":
                warnmess = "rom values cannot be directly loaded as operands:\n embedding value in instruction"
                warning(warnmess, instruction["line"], instruction["path"], 6)
    except KeyError:
        numstring = str(argument)
        if re.search(number, numstring) is not None:
            word = set_word2(word, eval(numstring))
            word = set_opvar(word, dict["number"])
        else:
            errmess = f'"{argument}" is not defined'
            error(errmess, instruction["line"], instruction["path"], 1)
    return word


def assemble_argument2_store(word, argument, dict, instruction, variables, number):
    valid_table = ["ram", "rom", "gpu"]

    try:
        if isinstance(argument, str) and argument[0] == "&":
            errmess = f'cannot store register in literal value "{argument}"'
            error(errmess, instruction["line"], instruction["path"], 1)
        else:
            var = variables[argument]
            if var["type"] not in valid_table:
                errmess = f'"{argument}" must be a writable variable'
                error(errmess, instruction["line"], instruction["path"], 1)
            word = set_word2(word, int(var["address"]))
            word = set_opvar(word, dict[var["type"]])
    except KeyError:
        numstring = str(argument)
        if re.search(number, numstring) is not None:
            errmess = f'"{argument}" must be a writable variable'
            error(errmess, instruction["line"], instruction["path"], 1)
        else:
            errmess = f'"{argument}" is not defined'
            error(errmess, instruction["line"], instruction["path"], 1)
    return word


def assemble_label(word, arg, labels, instruction):
    found = False
    for label in labels:
        if label["name"] == arg:
            found = True
            word = set_word2(word, label["address"])
            break
    if not found:
        errmess = f'"{arg}" is not defined'
        # print(errmess)
        error(errmess, instruction["line"], instruction["path"], 444)
    return word


def check_num_args(args, argtarget, name, instruction):
    num_to_word = ["zero", "one", "two", "three"]
    if len(args) != argtarget:
        errmess = f'"{name}" takes exactly {num_to_word[argtarget]} argument{"s" if argtarget != 1 else ""}'
        error(errmess, instruction["line"], instruction["path"], 69)


def assemble_cond_jump(word, arg, instruction):
    try:
        word |= CONDITIONS[arg] << OPERAND1_SHIFT
    except KeyError:
        errmess = f'"{arg}" is not a valid condition'
        error(errmess, instruction["line"], instruction["path"], 420)
    return word


def assemble_interrupt_vector(word, arg, instruction):
    try:
        word |= (~INTERRUPT_VECTORS[arg] & 0b11) << 16
    except KeyError:
        errmess = f'"{arg}" is not a valid interrupt vector'
        error(errmess, instruction["line"], instruction["path"], 58008)
    return word


##############################################################
# Assembly Main Loop
##############################################################


def assemble_instruction(instructions, variables, labels):

    number = re.compile(
        "\\b(([1-9][0-9_]*)|(0x[0-9A-Fa-f][0-9A-Fa-f_]*)|(0b[10][10_]*)|0)\\b"
    )

    mathops = [
        "cmp",
        "add",
        "sub",
        "mul",
        "div",
        "mod",
        "and",
        "or",
        "xor",
        "lsr",
        "lsl",
    ]

    machine = np.empty(len(instructions), dtype="u4")

    for i in range(len(instructions)):
        word = 0
        inst = instructions[i]["mnemonic"]
        word = set_opcode(word, inst)
        args = instructions[i]["arguments"]

        if inst in mathops:
            if len(args) < 2 or len(args) > 3:
                errmess = f'"{inst}" needs at least two arguments and takes no more than three'
                error(errmess, instructions[i]["line"], instructions[i]["path"], 76)

            try:
                word = assemble_register(word, args[0], "operand1", instructions[i])
                # since the second argument can be many different things,
                # it's not immdiately an error if the argument is not a register
                try:
                    word = set_operand2(word, args[1])
                    word = set_opvar(word, MATH_DICT["reg"])
                except KeyError:
                    word = assemble_argument2(
                        word, args[1], MATH_DICT, instructions[i], variables, number
                    )
                if len(args) == 3:
                    if "inst" == "cmp":
                        errmess = f'"{inst}" takes exactly two arguments, ignoring "{args[2]}"'
                        warning(
                            errmess,
                            instructions[i]["line"],
                            instructions[i]["path"],
                            119,
                        )
                    word = assemble_register(word, args[2], "results", instructions[i])
                else:
                    word = assemble_register(word, args[0], "results", instructions[i])
            except IndexError:
                pass

        elif inst == "not":  # TODO -- extend to allow const / ram arguments for arg 1!!
            if len(args) < 1 or len(args) > 2:
                errmess = (
                    f'"{inst}" needs at least one argument and takes no more than two'
                )
                error(errmess, instructions[i]["line"], instructions[i]["path"], 169)
            try:
                word = assemble_register(word, args[0], "operand2", instructions[i])
                word = set_opvar(word, MATH_DICT["reg"])
                if len(args) == 2:
                    word = assemble_register(word, args[1], "results", instructions[i])
                else:
                    word = assemble_register(word, args[0], "results", instructions[i])
            except IndexError:
                pass

        elif inst == "nop":
            check_num_args(args, 0, inst, instructions[i])

        elif inst == "ldr":
            check_num_args(args, 2, inst, instructions[i])
            try:
                word = assemble_register(word, args[0], "results", instructions[i])
                if args[1] in variables and variables[args[1]]["type"] == "rom":
                    word = assemble_argument2(
                        word,
                        variables[args[1]]["value"],
                        LOAD_DICT,
                        instructions[i],
                        variables,
                        number,
                    )
                else:
                    word = assemble_argument2(
                        word, args[1], LOAD_DICT, instructions[i], variables, number
                    )
            except IndexError:
                pass

        elif inst == "str":
            check_num_args(args, 2, inst, instructions[i])
            try:
                word = assemble_register(word, args[0], "operand1", instructions[i])
                word = assemble_argument2_store(
                    word, args[1], STORE_DICT, instructions[i], variables, number
                )
            except IndexError:
                pass

        elif inst == "lpt":
            check_num_args(args, 2, inst, instructions[i])
            try:
                word = assemble_register(word, args[0], "results", instructions[i])
                try:
                    word = set_opvar(word, LOAD_PTR_DICT[args[1]])
                except KeyError:
                    errmess = f'"{inst}" must be "ram," "rom," or "gpu"'
                    error(errmess, instructions[i]["line"], instructions[i]["path"], -2)
            except IndexError:
                pass

        elif inst == "spt":
            check_num_args(args, 2, inst, instructions[i])
            try:
                word = assemble_register(word, args[0], "operand1", instructions[i])
                try:
                    word = set_opvar(word, STORE_PTR_DICT[args[1]])
                except KeyError:
                    errmess = f'"{args[1]}" must be "ram" or "gpu"'
                    error(
                        errmess, instructions[i]["line"], instructions[i]["path"], 1000
                    )
            except IndexError:
                pass

        elif inst in ("jmp", "jsr"):
            check_num_args(args, 1, inst, instructions[i])
            try:
                word = assemble_label(word, args[0], labels, instructions[i])
            except IndexError:
                pass

        elif inst in ("joc", "jsc"):
            check_num_args(args, 2, inst, instructions[i])
            try:
                word = assemble_label(word, args[1], labels, instructions[i])
                word = assemble_cond_jump(word, args[0], instructions[i])
            except IndexError:
                pass

        elif inst == "rts":
            check_num_args(args, 0, inst, instructions[i])

        elif inst == "rsc":
            check_num_args(args, 1, inst, instructions[i])
            try:
                word = assemble_cond_jump(word, args[0], instructions[i])
            except IndexError:
                pass

        elif inst == "rti":
            check_num_args(args, 0, inst, instructions[i])
            # try:
            #     word = assemble_interrupt_vector(word, args[0], instructions[i])
            # except IndexError:
            #     pass

        elif inst == "ric":
            check_num_args(args, 1, inst, instructions[i])
            try:
                # word = assemble_interrupt_vector(word, args[1], instructions[i])
                word = assemble_cond_jump(word, args[0], instructions[i])
            except IndexError:
                pass

        machine[i] = word

    return machine


def assemble_variables(variables, ram_bit_width):
    biggest_addr = -1
    for var in variables:
        if (
            variables[var]["type"] == "rom"
            and int(variables[var]["address"]) > biggest_addr
        ):
            biggest_addr = variables[var]["address"]
    machine = np.empty(biggest_addr + 1, dtype=f"u{math.ceil(ram_bit_width/8)}")

    for var in variables:
        if variables[var]["type"] == "rom":
            machine[int(variables[var]["address"])] = variables[var]["value"]

    return machine

def assemble_ram(variables, ram_bit_width):

    machine = np.zeros(Globals.RAM_SIZE, dtype=f"u{math.ceil(ram_bit_width/8)}")

    highest = max([i['address'] for k, i in variables.items()])
    if (highest >= Globals.RAM_SIZE):
        errmess = f'requested RAM size ({highest + 1}) greater than available space ({Globals.RAM_SIZE})'
        error(errmess, -1, -1, 410, line_based=False)
        return machine

    for var in variables:
        if variables[var]["type"] == "ram" and variables[var]["address"] >= Globals.RAM_ADDRESS_BEGIN:
            machine[int(variables[var]["address"])] = variables[var]["value"] if variables[var]["value"] is not None else 0

    return machine

# this is so awful and hacky
def init_ram(instructions, variables, labels):
    raminst = {}
    for key, item in variables.getVariables().items():
        if item['type'] == 'ram' and item['value'] is not None:
            raminst[key] = item
    
    byval = {}
    for key, item in raminst.items():
        if item['value'] not in byval:
            byval[item['value']] = []
        item['name'] = key
        byval[item['value']].append(item)

    # print(byval)

    # detect consecutive array elements
    runs = []

    for key, item in byval.items():
        # 6 is our magic number -- roughly the number
        # of instructions required for a loop
        if len(item) > 6:
            item.sort(key=lambda x: x['address'])
            runstart = item[0]['address']
            prevaddr = runstart
            idx = 1
            runvars = [item[0]]
            while idx < len(item):
                if item[idx]['address'] == prevaddr + 1:
                    runvars.append(item[idx])
                    idx += 1
                    prevaddr += 1
                    if idx == len(item):
                        runend = item[idx - 1]['address']
                        runlen = runend - runstart 
                        if runlen > 6:
                            copyvars = copy.deepcopy(runvars)
                            runs.append({'value': key, 'vars': copyvars})
                else:
                    runend = item[idx - 1]['address']
                    runlen = runend - runstart 
                    if runlen > 6:
                        copyvars = copy.deepcopy(runvars)
                        runs.append({'value': key, 'vars': copyvars})
                        runstart = item[idx]['address']
                        prevaddr = runstart
                        runvars = [item[idx]]
                    else:
                        runstart = item[idx]['address']
                        prevaddr = runstart
                        runvars = [item[idx]]
                    idx += 1

    # print(runs)
    #removing runs from normal pool
    for run in runs:
        key = run['value']
        for var in run['vars']:
            byval[key].remove(var)

    newinstrs = []
    newlabels = []
    addr = Globals.PGM_ADDRESS_BEGIN
    initloops = 0
    
    for key, item in byval.items():
        newinstrs.append({
            "mnemonic": 'ldr',
            "address": addr,
            "arguments": ['a', key],
            # these won't cause errors, so it doesn't matter
            "line": -1,
            "path": '',
        })
        addr += 1
        for var in item:
            newinstrs.append({
                "mnemonic": 'str',
                "address": addr,
                "arguments": ['a', var['name']],
                # these won't cause errors, so it doesn't matter
                "line": -1,
                "path": '',
            })
            addr += 1
        for run in runs:
            if run['value'] == key:
                runstart = run['vars'][0]['address']
                runend = run['vars'][len(run['vars']) - 1]['address']
                newinstrs.append({"mnemonic": 'ldr', "address": addr, "arguments": ['f', runstart], "line": -1,"path": ''})
                addr += 1
                newlabels.append({"name": f'__init_loop{initloops}',"address": addr, "line": -1,"path": ''})
                newinstrs.append({"mnemonic": 'spt', "address": addr, "arguments": ['a', 'ram'], "line": -1,"path": ''})
                addr += 1
                newinstrs.append({"mnemonic": 'add', "address": addr, "arguments": ['f', 1], "line": -1,"path": ''})
                addr += 1
                newinstrs.append({"mnemonic": 'cmp', "address": addr, "arguments": ['f', runend + 1], "line": -1,"path": ''})
                addr += 1
                newinstrs.append({"mnemonic": 'joc', "address": addr, "arguments": ['equal', f'__init_loop{initloops}_end'], "line": -1,"path": ''})
                addr += 1
                newinstrs.append({"mnemonic": 'jmp', "address": addr, "arguments": [f'__init_loop{initloops}'], "line": -1,"path": ''})
                addr += 1
                newlabels.append({"name": f'__init_loop{initloops}_end',"address": addr, "line": -1,"path": ''})
                initloops += 1

    instructions.insert(newinstrs, len(newinstrs))

    labels.insert(newlabels, len(newinstrs))