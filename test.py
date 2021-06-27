
from assembler.assembler import main

args = [__file__, './programs/example.cor', '--debug-lines', '--debug-vars', '--log', 'test_log']

main(args)