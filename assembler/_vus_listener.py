import re
import math

import antlr4

from collections import namedtuple

from antlr4.error.ErrorListener import ErrorListener
from gen.CorListener import CorListener
from gen.CorLexer import CorLexer
from gen.CorParser import CorParser

import _assembly_utils

def extract_escapes(string, listener, linenum):
    escape_regex = re.compile(r'\\.')
    match = escape_regex.search(string)
    while match is not None:
        sequence = match.group(0)
        if sequence == '\\0':
            string = string[:match.start()] + '\x00' + string[match.end() + 1:]
        elif sequence not in (r'\\', r"\'", r'\"'):
            warnmess = f'invalid escape sequence \"{sequence}\"'
            _assembly_utils.warning(warnmess, linenum, listener.full_path, 999)
            string = string[:match.start()] + match.group(0)[-1] + string[match.end() + 1:]
        else:
            string = string[:match.start()] + match.group(0)[-1] + string[match.end() + 1:]
        match = escape_regex.search(string, pos=match.end())
    return string

class Variables:
    def __init__(self, sysvars=None, ram_start_addr=41):
        if sysvars:
            self.vars = sysvars
        else:
            self.vars = {}
            sysvars = {}
        self.size = {"ram": ram_start_addr, "rom": 0, "pre": 0}
        self.eval_dict = {}
        for var in sysvars:
            if sysvars[var]["type"] == "rom" or sysvars[var]["type"] == "pre":
                self.eval_dict[sysvars[var]["type"]] = sysvars[var]["value"]
        self.variable_regex = re.compile(
            "(?<!&)(\\b|^)[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b"
        )

        self.address_regex = re.compile(
            "(^|(?<=[+-/*=><\\^|()]))&[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b"
        )

        self.char_regex = re.compile(
            r"'((\\.)|[^\\])'"
        )

        self.attr_regex = re.compile(
            r"(?<!&)(\b|^)[A-Za-z_][A-Za-z_0-9.]*->[A-Za-z_][A-Za-z_0-9.]*\b"
        )

    # TODO -- all variables should be scoped when added

    # ['var_type', 'name', 'address', 'value']
    def add(self, var_type, name, value, listener, size=1, linenum=-1, arr=False):

        if name in self.vars:
            errmess = f'variable "{name}" already defined'
            _assembly_utils.error(errmess, linenum, listener.full_path, 4000)
            return

        address = self.size[var_type]
        self.size[var_type] += 1
        self.vars[name] = {"type": var_type, "address": address, "value": value, "size": size, "arr": arr}
        # TODO - arrays should not be added like other variables -- rather, they
        # should have one entry in the vars dictionary and contain a list of all
        # their elements. This would save a lot of memory and probably execution time
        # this would also make the variables debug output a lot more readable

        # for files that are imported, every variable and label is scoped with
        # a prefix, i.e. <imported.element>. These can still be evaluated by
        # the python eval method with a workaround -- if <imported> is added to
        # the variable dicionary as a class and <element> is added as an attribute
        # to that object, the evaluation succeeds.
        if var_type != "ram":
            if "." in name:
                key = name[: name.find(".")]
                attribute = name[name.find(".") + 1 :]
                try:
                    setattr(self.eval_dict[key], attribute, value)
                except KeyError:
                    self.eval_dict[key] = type(key, (object,), {})
                    setattr(self.eval_dict[key], attribute, value)
            else:
                self.eval_dict[name] = value

    def getVariables(self):
        return self.vars

    def getAddress(self, variable, linenum=-1, full_path="err"):
        variable = variable[1:]
        try:
            if self.vars[variable]["type"] == "pre":
                errmess = f"Pre-processor variable {variable} does not have an address"
                _assembly_utils.error(errmess, linenum, full_path, 470)
                return -1
            return self.vars[variable]["address"]
        except KeyError:
            errmess = f"no address for undefined variable {variable}"
            _assembly_utils.error(errmess, linenum, full_path, 474)
            return -1

    def decrementAddress(self, var_type):
        # to overload variable names
        self.size[var_type] -= 1

    def calc(self, math_string, listener, linenum=-1, full_path="err"):

        match = self.attr_regex.search(math_string)
        while match is not None:
            # print(math_string[match.end():])
            name = listener.scope(match.group(0)[:match.group(0).find('->')])
            attr_type = match.group(0)[match.group(0).find('->') + 2:]
            insertion = '0'
            try:
                if not self.vars[name]["arr"]:
                    errmess = f'variable "{name}" is not an array'
                    _assembly_utils.error(errmess, linenum, full_path, 466)
                if attr_type not in ('end', 'length'):
                    errmess = f'unknown attribute "{attr_type}"'
                    _assembly_utils.error(errmess, linenum, full_path, 467)
                if attr_type == 'end':
                    insertion = str(self.vars[name]["value"] + self.vars[name]["size"])
                elif attr_type == 'length':
                    insertion = str(self.vars[name]["size"])
            except KeyError:
                errmess = f'"{name}" is not defined'
                _assembly_utils.error(errmess, linenum, full_path, 468)

            math_string = (
                math_string[: match.start()]
                + insertion
                + math_string[match.end() :]
            )
            newpos = match.start() + len(insertion)
            match = self.attr_regex.search(math_string, pos=newpos)

        match = self.char_regex.search(math_string)
        while match is not None:
            # print(math_string[match.end():])
            insertion = str(ord(extract_escapes(match.group(0)[1:-1], listener, linenum)))
            math_string = (
                math_string[: match.start()]
                + insertion
                + math_string[match.end() :]
            )
            newpos = match.start() + len(insertion)
            match = self.char_regex.search(math_string, pos=newpos)

        # print(math_string)
        match = self.variable_regex.search(math_string)
        while match is not None:
            # print(math_string[match.end():], match.group(0), match.end())
            math_string = (
                math_string[: match.start()]
                + listener.scope(match.group(0))
                + math_string[match.end() :]
            )
            newPos = match.start() + len(listener.scope(match.group(0)))
            match = self.variable_regex.search(math_string, pos=newPos)

        # print(math_string)
        match = self.address_regex.search(math_string)
        # print(match.group(0) if match != None else '.', math_string)
        while match is not None:
            # print(match.group(0))
            insertion = str(
                self.getAddress(
                    listener.scope(match.group(0)),
                    linenum=linenum,
                    full_path=full_path,
                )
            )
            math_string = (
                math_string[: match.start()]
                + insertion
                + math_string[match.end() :]
            )
            newpos = match.start() + len(insertion)
            match = self.address_regex.search(math_string, pos=newpos)

        # print(math_string + '\n')
        solution = 0
        try:
            solution = round(eval(math_string, {}, self.eval_dict))
        except NameError as err:
            print(err)
            wrong_type = False
            wrong_type_name = ""
            for key in self.vars:
                tempregstr = "(\\b|^)({})\\b".format(key)
                if "." in tempregstr:
                    tempregstr.replace(".", "\\.")
                tempreg = re.compile(tempregstr)
                if tempreg.search(math_string) is not None:
                    wrong_type = True
                    wrong_type_name = key
                    break
            if not wrong_type:
                extracted = err.args[0][6:]
                extraced = extracted[: extracted.find("'")]
                errmess = f'"{extraced}" is not defined'
                _assembly_utils.error(errmess, linenum, full_path, 469)
            else:
                print(wrong_type_name, dir(self.eval_dict['menu']))
                if (
                    listener.current_name != listener.main_name
                    and "." in wrong_type_name
                ):
                    wrong_type_name = wrong_type_name[wrong_type_name.find(".") + 1 :]
                errmess = f'cannot evaluate "{wrong_type_name}" at compile time'
                _assembly_utils.error(errmess, linenum, full_path, 469)
        except SyntaxError:
            # print(e.args)
            errmess = "invalid syntax"
            _assembly_utils.error(errmess, linenum, full_path, 470)
        except AttributeError as err:
            # prints in the form:
            # type object 'importname' has no attribute 'errorcause'
            error_bound = err.args[0][:-1].rfind("'")
            errmess = f'"{err.args[0][error_bound + 1:-1]}" is not defined'
            _assembly_utils.error(errmess, linenum, full_path, 471)
        except TypeError:
            errmess = "invalid operation"
            _assembly_utils.error(errmess, linenum, full_path, 472)

        return solution

    # this may need to be updated later if users want to specify
    # addresses for data
    def insert(self, newvars):
        self.vars.update(newvars)


class Instructions:
    def __init__(self, program_start_addr=0, num_loops=0, numifs=0):
        self.instructions = []
        self.size = {
            "instructions": program_start_addr,
            "loops": num_loops,
            "ifs": numifs,
        }
        self.top_begin = ""
        self.top_end = ""

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
            if (
                arg.expression() is not None
                and
                (
                    arg.expression().math() is not None
                    or "&" in arg.getText()
                    or "'" in arg.getText()
                    or arg.expression().exp_attr() is not None
                )
            ):
                tempargs.append(
                    variables.calc(arg.getText(), listener, linenum, listener.full_path)
                )
            else:
                tempargs.append(listener.scope(arg.getText()))

        address = self.size["instructions"]
        self.size["instructions"] += 1
        self.instructions.append(
            {
                "mnemonic": mnemonic,
                "address": address,
                "arguments": tempargs,
                "line": linenum,
                "path": listener.full_path,
            }
        )

    def addManual(self, mnemonic, arguments, linenum, listener):
        address = self.size["instructions"]
        self.size["instructions"] += 1
        self.instructions.append(
            {
                "mnemonic": mnemonic,
                "address": address,
                "arguments": arguments,
                "line": linenum,
                "path": listener.full_path,
            }
        )

    # recursively add loops
    def addLoop(self, ctx, listener, variables, labels, top=False):
        linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
        LoopLabels = namedtuple('LoopLabels', ['name', 'begin', 'end', 'cont'])

        loop_name = f'__loop{self.size["loops"]}'

        l_labels = LoopLabels(loop_name,
                              loop_name + "_begin",
                              loop_name + "_end",
                              loop_name + "_continue")

        self.size["loops"] += 1
        if top:
            self.top_begin = l_labels.begin
            self.top_end = l_labels.end

        self.add(ctx.getChild(2), listener, variables)
        labels.add(ctx, l_labels.begin, self, listener, linenum=linenum)
        self.add(ctx.getChild(4), listener, variables)
        self.addManual("joc", ["equal", l_labels.end], linenum, listener)

        children = ctx.getChild(8).getChildCount()
        for i in range(1, children - 1):
            ctx_name = type(ctx.getChild(8).getChild(i)).__name__
            if ctx_name == "InstructionContext":
                self.add(ctx.getChild(8).getChild(i), listener, variables)
            elif ctx_name == "Loop_keywordContext":
                keyword = ctx.getChild(8).getChild(i).getText()
                target = ""
                if keyword == "continue":
                    target = l_labels.cont
                elif keyword == "break":
                    target = l_labels.end
                elif keyword == "breakall":
                    target = self.top_end
                self.addManual("jmp", [target], linenum, listener)
            elif ctx_name == "LabelContext":
                labels.add(
                    ctx,
                    ctx.getChild(8).getChild(i).getText()[:-1],
                    self,
                    listener,
                    linenum=linenum,
                )
            elif ctx_name == "LoopContext":
                self.addLoop(ctx.getChild(8).getChild(i), listener, variables, labels)
            elif ctx_name == "If_chainContext":
                self.addIfChains(
                    ctx.getChild(8).getChild(i), listener, variables, labels, l_labels=l_labels
                )

        labels.add(ctx, l_labels.cont, self, listener, linenum=linenum)
        self.add(ctx.getChild(6), listener, variables)
        self.addManual("jmp", [l_labels.begin], linenum, listener)
        labels.add(ctx, l_labels.end, self, listener, linenum=linenum)

    # recursively add ifs
    def addIfChains(self, ctx, listener, variables, labels, l_labels=None):
        linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
        if_name = f'__if{self.size["ifs"]}'
        # ifbegin = if_name + '_begin'
        if_end = if_name + "_end"
        this_if_num = self.size["ifs"]
        self.size["ifs"] += 1

        numifs = ctx.getChildCount()

        if numifs == 1:
            ifstat = ctx.getChild(0)
            current_branch = f"__if{this_if_num}_branch0"
            # adding instructions in conditional and the jump logic
            num_instructions = math.ceil((ifstat.getChild(1).getChildCount() - 4) / 2)
            condition = (
                ifstat.getChild(1)
                .getChild(ifstat.getChild(1).getChildCount() - 2)
                .getText()
            )
            condition_condition = (
                ifstat.getChild(1)
                .getChild(ifstat.getChild(1).getChildCount() - 3)
                .getText()
            )
            for j in range(num_instructions):
                temp_instruction = ifstat.getChild(1).getChild(1 + j * 2)
                self.add(temp_instruction, listener, variables)

            if condition_condition == "is":
                self.addManual(
                    "joc", [condition, current_branch + "_t"], linenum, listener
                )
                self.addManual("jmp", [if_end], linenum, listener)
            else:
                self.addManual("joc", [condition, if_end], linenum, listener)
            labels.add(ctx, current_branch + "_t", self, listener, linenum=linenum)

            num_items = ifstat.getChild(2).getChildCount() - 2

            for j in range(num_items):
                ctx_name = type(ifstat.getChild(2).getChild(1 + j)).__name__
                linenum = listener.stream.get(
                    ifstat.getChild(2).getChild(1 + j).getSourceInterval()[0]
                ).line
                if ctx_name == "InstructionContext":
                    self.add(ifstat.getChild(2).getChild(1 + j), listener, variables)
                elif ctx_name == "LabelContext":
                    labels.add(
                        ctx,
                        listener.scope(
                            ifstat.getChild(2).getChild(1 + j).getText()[:-1]
                        ),
                        self,
                        listener,
                        linenum=linenum,
                    )
                elif ctx_name == "LoopContext":
                    self.addLoop(
                        ifstat.getChild(2).getChild(1 + j),
                        listener,
                        variables,
                        labels,
                        top=True if l_labels is None else False,
                    )
                elif ctx_name == "If_chainContext":
                    self.addIfChains(
                        ifstat.getChild(2).getChild(1 + j), listener, variables, labels, l_labels=l_labels
                    )
                elif ctx_name == "Loop_keywordContext":
                    keyword = ifstat.getChild(2).getChild(1 + j).getText()
                    if l_labels is not None:
                        target = ""
                        if keyword == "continue":
                            target = l_labels.cont
                        elif keyword == "break":
                            target = l_labels.end
                        elif keyword == "breakall":
                            target = self.top_end
                        self.addManual("jmp", [target], linenum, listener)
                    else:
                        errmess = f'"{keyword}" must occur inside a for loop'
                        _assembly_utils.error(errmess, linenum, listener.full_path, 4002)

        else:
            for i in range(numifs):
                ifstat = ctx.getChild(i)
                typeif = type(ifstat).__name__
                current_branch = f"__if{this_if_num}_branch{i}"
                next_branch = f"__if{this_if_num}_branch{i + 1}"
                if i != 0:
                    labels.add(ctx, current_branch, self, listener, linenum=linenum)
                if typeif in ["If_statContext", "Elif_statContext"]:
                    # adding instructions in conditional and the jump logic
                    num_instructions = math.ceil(
                        (ifstat.getChild(1).getChildCount() - 4) / 2
                    )
                    condition = (
                        ifstat.getChild(1)
                        .getChild(ifstat.getChild(1).getChildCount() - 2)
                        .getText()
                    )
                    condition_condition = (
                        ifstat.getChild(1)
                        .getChild(ifstat.getChild(1).getChildCount() - 3)
                        .getText()
                    )
                    for j in range(num_instructions):
                        temp_instruction = ifstat.getChild(1).getChild(1 + j * 2)
                        self.add(temp_instruction, listener, variables)

                    if condition_condition == "is":
                        self.addManual(
                            "joc", [condition, current_branch + "_t"], linenum, listener
                        )
                        self.addManual("jmp", [next_branch], linenum, listener)
                    else:
                        self.addManual(
                            "joc", [condition, next_branch], linenum, listener
                        )
                    labels.add(
                        ctx, current_branch + "_t", self, listener, linenum=linenum
                    )

                    num_items = ifstat.getChild(2).getChildCount() - 2

                    for j in range(num_items):
                        ctx_name = type(ifstat.getChild(2).getChild(1 + j)).__name__
                        linenum = listener.stream.get(
                            ifstat.getChild(2).getChild(1 + j).getSourceInterval()[0]
                        ).line
                        if ctx_name == "InstructionContext":
                            self.add(
                                ifstat.getChild(2).getChild(1 + j), listener, variables
                            )
                        elif ctx_name == "LabelContext":
                            labels.add(
                                ctx,
                                listener.scope(
                                    ifstat.getChild(2).getChild(1 + j).getText()[:-1]
                                ),
                                self,
                                listener,
                                linenum=linenum,
                            )
                        elif ctx_name == "LoopContext":
                            self.addLoop(
                                ifstat.getChild(2).getChild(1 + j),
                                listener,
                                variables,
                                labels,
                                top=True if l_labels is None else False,
                            )
                        elif ctx_name == "If_chainContext":
                            self.addIfChains(
                                ifstat.getChild(2).getChild(1 + j), listener, variables, labels, l_labels=l_labels
                            )
                        elif ctx_name == "Loop_keywordContext":
                            keyword = ifstat.getChild(2).getChild(1 + j).getText()
                            if l_labels is not None:
                                target = ""
                                if keyword == "continue":
                                    target = l_labels.cont
                                elif keyword == "break":
                                    target = l_labels.end
                                elif keyword == "breakall":
                                    target = self.top_end
                                self.addManual("jmp", [target], linenum, listener)
                            else:
                                errmess = f'"{keyword}" must occur inside a for loop'
                                _assembly_utils.error(errmess, linenum, listener.full_path, 4002)

                    self.addManual("jmp", [if_end], linenum, listener)

                else:
                    # labels.add(ctx, current_branch, self, listener)
                    num_items = ifstat.getChild(1).getChildCount() - 2
                    for j in range(num_items):
                        ctx_name = type(ifstat.getChild(1).getChild(1 + j)).__name__
                        linenum = listener.stream.get(
                            ifstat.getChild(1).getChild(1 + j).getSourceInterval()[0]
                        ).line
                        if ctx_name == "InstructionContext":
                            self.add(
                                ifstat.getChild(1).getChild(1 + j), listener, variables
                            )
                        elif ctx_name == "LabelContext":
                            labels.add(
                                ctx,
                                listener.scope(
                                    ifstat.getChild(1).getChild(1 + j).getText()[:-1]
                                ),
                                self,
                                listener,
                                linenum=linenum,
                            )
                        elif ctx_name == "LoopContext":
                            self.addLoop(
                                ifstat.getChild(1).getChild(1 + j),
                                listener,
                                variables,
                                labels,
                                top=True if l_labels is None else False,
                            )
                        elif ctx_name == "If_chainContext":
                            self.addIfChains(
                                ifstat.getChild(1).getChild(1 + j), listener, variables, labels, l_labels=l_labels
                            )
                        elif ctx_name == "Loop_keywordContext":
                            keyword = ifstat.getChild(2).getChild(1 + j).getText()
                            if l_labels is not None:
                                target = ""
                                if keyword == "continue":
                                    target = l_labels.cont
                                elif keyword == "break":
                                    target = l_labels.end
                                elif keyword == "breakall":
                                    target = self.top_end
                                self.addManual("jmp", [target], linenum, listener)
                            else:
                                errmess = f'"{keyword}" must occur inside a for loop'
                                _assembly_utils.error(errmess, linenum, listener.full_path, 4002)

            labels.add(ctx, next_branch, self, listener, linenum=linenum)

        labels.add(ctx, if_end, self, listener, linenum=linenum)

    def getAddress(self):
        return self.size["instructions"]

    def getInstructions(self):
        return self.instructions

    def insert(self, dictlist, num):
        for instruction in self.instructions:
            instruction["address"] += num
        self.instructions = dictlist + self.instructions


class Labels:
    def __init__(self):
        self.labels = []

    def setInit(self, program_start_addr=0):
        # begins with a default label to skip reserved
        # interrupt addresses
        self.labels.insert(0, {"name": "__pgm_start__", "address": program_start_addr})

    # ['label', 'address']
    def add(self, ctx, name, instr, listener, interrupt=-1, linenum=-1):
        for label in self.labels:
            if label["name"] == name:
                errmess = f'label "{name}" already used at line {label["line"]}'
                _assembly_utils.error(errmess, linenum, listener.full_path, 4001)
                return

        address = instr.getAddress()
        linenum = listener.stream.get(ctx.getSourceInterval()[0]).line
        if interrupt == -1:
            self.labels.append(
                {
                    "name": name,
                    "address": address,
                    "line": linenum,
                    "path": listener.full_path,
                }
            )
        else:
            self.labels.append(
                {
                    "name": name,
                    "address": address,
                    "interrupt": interrupt,
                    "line": linenum,
                    "path": listener.full_path,
                }
            )

    def getLabels(self):
        return self.labels

    def insert(self, dictlist, num):
        for label in self.labels:
            label["address"] += num
        self.labels = dictlist + self.labels


class VusListener(CorListener):
    def __init__(
        self,
        main_name,
        ram_start_addr_init,
        program_start_addr_init,
        full_path,
        sysvars_init,
    ):
        self.variables = Variables(
            ram_start_addr=ram_start_addr_init, sysvars=sysvars_init
        )
        self.instructions = Instructions(program_start_addr=program_start_addr_init)
        self.labels = Labels()
        self.main_name = main_name
        self.current_name = ""
        # fmt: off
        self.keywords = [
            'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h',
            'pre', 'ram', 'rom', 'gpu',
            'zero', 'carry', 'negative', 'as',
            'for', 'equal', 'greater', 'less',
            'SCOPE_RATE', 'UART', 'UART_STATUS', 'STACK', 'STATUS',
            'SCOPE_ADDR', 'GPIO', 'GPIO_DIR', 'TX_EMPTY',
            'SCOPE_DATA', 'TX_FULL', 'RX_EMPTY', 'RX_FULL',
            'SCOPE_TRIGGER', 'continue', 'break', 'breakall',
            'FLASH_READ', 'FLASH_WRITE', 'FLASH_STATUS', 'FLASH_PAGE',
            'FLASH_WRITE_WORD', 'FLASH_READ_WORD', 'FLASH_ERASE_WORD', 'TIMER',
            'TIMER_COMP', 'TIMER_PRES', 'TIMER_STATUS',
            'FRAME', 'R4000', 'R4001', 'R4002',
            'R4003', 'R4004', 'R4005', 'R4006',
            'R4007', 'R4008', 'R4009', 'R400A',
            'R400B', 'R400C', 'R400D', 'R400E',
            'R400F', 'R4010', 'R4011', 'R4012',
            'R4013', 'R4014', 'R4015', 'R4016',
            'R4017', 'R9000', 'R9001', 'R9002',
            'R9003', 'RA000', 'RA001', 'RA002',
            'RB000', 'RB001', 'RB002',
        ]
        # fmt: on
        self.variable_regex = re.compile("\\b\\&{0,1}[A-Za-z_][A-Za-z_0-9.\\[\\]]*\\b")
        self.full_path = full_path
        self.stream = None

    def scope(self, name):
        if self.variable_regex.search(name) is not None:
            if (
                self.current_name != self.main_name
                and "." not in name
                and name not in self.keywords
            ):
                if "&" in name:
                    if name[1:] in self.keywords:
                        return name
                    name = "&" + self.current_name + "." + name[1:]
                else:
                    name = self.current_name + "." + name
        return name

    # def setName(self, name, full_path, stream):
    #   self.current_name = name
    #   self.full_path = full_path
    #   self.stream = stream

    def getVariables(self):
        return self.variables

    def getInstructions(self):
        return self.instructions

    def getNumInstructions(self):
        return self.instructions.getAddress()

    def getLabels(self):
        return self.labels

    def reset(self, name, full_path, stream, start_addr=3):
        self.current_name = name
        self.full_path = full_path
        self.stream = stream

        prevloops = self.instructions.size["loops"]
        previfs = self.instructions.size["ifs"]
        if self.current_name == self.main_name:
            self.instructions = Instructions(
                program_start_addr=start_addr, num_loops=prevloops, numifs=previfs
            )
        else:
            self.instructions = Instructions(num_loops=prevloops, numifs=previfs)
        self.labels = Labels()

    def convertString(self, string, linenum):
        string = string.strip('"').replace('\\"', '"')
        string = extract_escapes(string, self, linenum)
        chars = []
        for char in string:
            chars.append(ord(char))
        chars.append(0)  # terminating character

        return chars

    # Enter a parse tree produced by CorParser#parse.
    def enterParse(self, ctx: CorParser.ParseContext):
        pass

    # Exit a parse tree produced by CorParser#parse.
    def exitParse(self, ctx: CorParser.ParseContext):
        pass

    # Enter a parse tree produced by CorParser#block.
    def enterBlock(self, ctx: CorParser.BlockContext):
        templabel = self.scope(ctx.label().getChild(0).getText())
        linenum = self.stream.get(ctx.getSourceInterval()[0]).line
        if ctx.label().getChildCount() == 2:
            self.labels.add(ctx, templabel, self.instructions, self, linenum=linenum)
        else:
            tempint = self.scope(ctx.label().getChild(2).getText())
            self.labels.add(
                ctx,
                templabel,
                self.instructions,
                self,
                interrupt=tempint,
                linenum=linenum,
            )

    # Exit a parse tree produced by CorParser#assignment_arr.
    def assignment_arr(self, ctx):
        linenum = self.stream.get(ctx.getSourceInterval()[0]).line
        var_type = ctx.CONST().getText()
        name = self.scope(ctx.array().VARIABLE().getText())
        num_dimensions = ctx.array().getChildCount() - 3

        if num_dimensions > 2:
            errmess = (
                f'array "{name}" cannot be initialized with more than two dimension'
            )
            _assembly_utils.error(errmess, linenum, self.full_path, 4002)

        if (
            num_dimensions == 1
            and ctx.array().arr_data() is not None
            and (
                len(ctx.array().arr_data().string()) != 0
                or len(ctx.array().arr_data().arr_data()) != 0
            )
        ):
            errmess = f'array "{name}" cannot contain subarrays'
            _assembly_utils.error(errmess, linenum, self.full_path, 4002)



        if num_dimensions == 1:
            if ctx.array().string() is not None:
                linenum = self.stream.get(
                    ctx.array().string().getSourceInterval()[0]
                ).line
                chars = self.convertString(ctx.array().string().getText(), linenum)

                self.variables.add(
                    "pre", name, self.variables.size[var_type], self, size=len(chars) - 1, linenum=linenum, arr=True
                )

                for i in range(len(chars)):
                    self.variables.add(
                        var_type, name + f"[{i}]", chars[i], self, linenum=linenum
                    )
            else:
                width = math.ceil((ctx.array().arr_data().getChildCount() - 2) / 2.0)
                self.variables.add(
                    "pre", name, self.variables.size[var_type], self, size=width, linenum=linenum, arr=True
                )
                for i in range(width):

                    linenum = self.stream.get(
                        ctx.array()
                        .arr_data()
                        .getChild(1 + i * 2)
                        .getSourceInterval()[0]
                    ).line
                    tempval = self.variables.calc(
                        ctx.array().arr_data().getChild(1 + i * 2).getText(),
                        self,
                        full_path=self.full_path,
                        linenum=linenum,
                    )
                    # print(name, ctx.array().arr_data().getChild(1 + i*2).getText(), tempval)
                    self.variables.add(
                        var_type, name + f"[{i}]", tempval, self, linenum=linenum
                    )

    # Exit a parse tree produced by CorParser#declaration.
    def declaration(self, ctx):
        linenum = self.stream.get(ctx.getSourceInterval()[0]).line
        if ctx.getChildCount() == 2:  # simple declaration
            self.variables.add(
                ctx.RAM().getText(),
                self.scope(ctx.VARIABLE().getText()),
                0,
                self,
                linenum=linenum,
            )
        else:  # array
            # adding sneaky precompiler variable to mimic the behavior of C arrays

            var_type = ctx.RAM().getText()
            name = self.scope(ctx.VARIABLE().getText())
            dimensions = ctx.getChildCount() - 2

            if dimensions > 1:
                errmess = f'array "{name}" can only be initialized with one dimension'
                _assembly_utils.error(errmess, linenum, self.full_path, 4001)

            size = self.variables.calc(
                ctx.getChild(2).expression().getText(),
                self,
                full_path=self.full_path,
                linenum=linenum,
            )

            self.variables.add(
                "pre",
                self.scope(ctx.VARIABLE().getText()),
                self.variables.size["ram"],
                self,
                size=size,
                linenum=linenum,
                arr=True
            )

            for i in range(size):
                self.variables.add(var_type, name + f"[{i}]", 0, self, linenum=linenum)

    # Exit a parse tree produced by CorParser#assignment.
    def assignment(self, ctx):
        linenum = self.stream.get(ctx.getSourceInterval()[0]).line
        var = self.scope(ctx.VARIABLE().getText())

        self.variables.add(
            ctx.CONST().getText(),
            var,
            self.variables.calc(
                ctx.expression().getText(),
                self,
                linenum=linenum,
                full_path=self.full_path,
            ),
            self,
            linenum=linenum,
        )

    # Exit a parse tree produced by CorParser#instruction.
    def instruction(self, ctx):
        self.instructions.add(ctx, self, self.variables)

    def exitStatement(self, ctx: CorParser.StatementContext):
        ctx = ctx.getChild(0)
        ctx_name = type(ctx).__name__

        if ctx_name == "InstructionContext":
            self.instruction(ctx)
        elif ctx_name == "AssignmentContext":
            self.assignment(ctx)
        elif ctx_name == "Assignment_arrContext":
            self.assignment_arr(ctx)
        elif ctx_name == "DeclarationContext":
            self.declaration(ctx)

    # Exit a parse tree produced by CorParser#statement_loop.
    def exitStatement_loop(self, ctx: CorParser.Statement_loopContext):
        self.instructions.addLoop(
            ctx.loop(), self, self.variables, self.labels, top=True
        )

    def exitStatement_if(self, ctx: CorParser.Statement_ifContext):
        self.instructions.addIfChains(ctx.if_chain(), self, self.variables, self.labels)

    def exitExp_attr(self, ctx:CorParser.Exp_attrContext):
        linenum = self.stream.get(ctx.getSourceInterval()[0]).line
        name = ctx.VARIABLE().getText()
        if name[0] == '&':
            errmess = f'cannot extract attribute from address of \"{name[1:]}\"'
            _assembly_utils.error(errmess, linenum, self.full_path, 998)


class ImportListener(CorListener):
    def __init__(self, infile, stream):
        tempfile = infile[: infile.rfind(".")]
        name_index = tempfile.rfind("/")
        name_index = name_index + 1 if name_index >= 0 else 0
        # We're extracting the path to the file from where the assembler
        # was invoked so that any files imported are searched for correctly
        # from any arbitrary calling path
        self.infile = infile
        self.working_dir = infile[:name_index] if name_index > 0 else "./"
        self.current_prefix = ""
        # print(infile)
        name = infile[name_index:].replace(".cor", "")

        self.imports = [{"name": name, "path": infile}]
        self.stream = stream

    def getImports(self):
        return self.imports

    # Enter a parse tree produced by CorParser#initial.
    def enterInitial(self, ctx: CorParser.InitialContext):
        if ctx.file_import() is not None:
            for file in ctx.file_import():
                # it's a bit messy to use positional searches, but idk how
                # to do it better
                # oldPrefix = self.current_prefix
                token = file.getChild(1).getText().strip("'\"")
                token = token[2:] if token[:2] == "./" else token

                name = ""
                new_prefix = ""
                path = ""
                # of the form: import "libs/lib.cor"
                if file.STRING() is not None:
                    new_prefix_index = token.rfind("/")
                    new_prefix = (
                        token[: new_prefix_index + 1] if new_prefix_index >= 0 else ""
                    )
                    name = token[new_prefix_index + 1 :].replace(".cor", "")
                    path = self.current_prefix + token
                # of the form: import libs.lib
                else:
                    token = token.replace(".", "/")
                    new_prefix_index = token.rfind("/")
                    new_prefix = (
                        token[: new_prefix_index + 1] if new_prefix_index >= 0 else ""
                    )
                    name = token[new_prefix_index + 1 :]
                    path = self.current_prefix + token + ".cor"

                if file.AS() is not None:
                    name = file.getChild(3).getText()

                self.current_prefix += new_prefix

                tempdict = {"name": name, "path": self.working_dir + path}

                new = True
                for link in self.imports:
                    if tempdict["path"] == link["path"]:
                        new = False
                        if tempdict["name"] != link["name"]:
                            linenum = self.stream.get(
                                file.getChild(3).getSourceInterval()[0]
                            ).line
                            errmess = f'file "{tempdict["path"]}" imported more than once with conflicting names!\n'
                            _assembly_utils.error(
                                errmess, linenum, self.infile, 4756, abort=True
                            )
                        break

                if new:
                    # TODO -- this does not quite properly place files. If one import
                    # depends on another, like:
                    # import one
                    # import depends_on_one
                    # the variables in depends_on_one will appear before one, potentially
                    # causing errors
                    self.imports.insert(0, tempdict)
                    try:
                        file_stream = antlr4.FileStream(tempdict["path"])
                    except FileNotFoundError:
                        linenum = self.stream.get(
                            file.getChild(3).getSourceInterval()[0]
                        ).line
                        errmess = f'cannot find file "{tempdict["path"]}"'
                        _assembly_utils.error(
                            errmess, linenum, self.infile, 4755, abort=True
                        )
                    lexer = CorLexer(file_stream)
                    # custom error listener
                    import_error = ImportErrorListener(filepath=tempdict["path"])
                    lexer.removeErrorListeners()
                    lexer.addErrorListener(import_error)
                    stream = antlr4.CommonTokenStream(lexer)
                    parser = CorParser(stream)
                    parser.removeErrorListeners()
                    parser.addErrorListener(import_error)
                    tree = parser.initial()
                    walker = antlr4.ParseTreeWalker()
                    prevstream = self.stream
                    self.stream = stream
                    walker.walk(self, tree)
                    self.stream = prevstream

                if len(new_prefix) > 0:
                    self.current_prefix = self.current_prefix[: -len(new_prefix)]

    # Exit a parse tree produced by CorParser#initial.
    def exitInitial(self, ctx: CorParser.InitialContext):
        pass


class ImportErrorListener(ErrorListener):
    def __init__(self, filepath="err"):

        super().__init__()
        self.filepath = filepath

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        errmess = "syntax error"
        _assembly_utils.error(
            errmess, line, self.filepath, 4000, syntax=True, col=column, abort=True
        )


class VusErrorListener(ErrorListener):
    def __init__(self, filepath="err"):

        super().__init__()
        self.filepath = filepath

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        errmess = "syntax error"
        _assembly_utils.error(
            errmess, line, self.filepath, 4000, syntax=True, col=column, abort=True
        )
