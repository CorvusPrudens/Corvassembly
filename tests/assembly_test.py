import sys, os, re

sys.path.append(os.path.realpath(os.path.dirname(__file__)+"/.."))

from .template import pgm_start, gen_args
from assembler.assembler import main
from assembler import _assembly_utils

expected_block = re.compile(r'\$expected[\n \t]+([\S\s]+?)\$end')

class ExpectedError(Exception):
    pass

def expect(source: str):

    with open(source, 'r') as file:
        string = file.read()
    
    match = expected_block.search(string)
    if match is None:
        raise ExpectedError(f'Unable to find expected output in file "{source}".')
    
    expected_output = match.group(1).strip('\n')

    expect = pgm_start + '\n' + expected_output
    args = gen_args(source)
    directives, output = main(args)
    output = output.strip('\n')

    if not expect == output:
        print('~~~ expected:')
        print(expect)
        print('~~~ generated:')
        print(output)
        print('~~~')

    assert expect == output


class TestHighLevel:

    def test_expectations(self):
        src_dir = './source/expectations'
        files = os.listdir(src_dir)

        for file in files:
            expect(os.path.join(src_dir, file))


class TestFunctions:

    def test_assemble_register(self):
        operands = ['operand1', 'operand2', 'results']
        op_shifts = [7, 10, 13]
        registers = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']

        for operand, shift in zip(operands, op_shifts):
            for idx, register in enumerate(registers):
                word = _assembly_utils.assemble_register(1, register, operand, 'n/a')
                assert word == 1 | (idx << shift)
    
    # # this test is only rudimentary
    # def test_assemble_argument2(self):
    #     variables = {'CONSTANT': {
    #         'type': 'pre',
    #         'address': -1,
    #         'value': 128,
    #     }}
    #     word = _assembly_utils.assemble_argument2(0, 'CONSTANT', {}, {}, variables, 0)

    #     assert word == 0b00000000_10000000_00000000_00000000

