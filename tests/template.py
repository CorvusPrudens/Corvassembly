
pgm_start = """
jmp __pgm_start__
jmp __pgm_start__
jmp __pgm_start__
__pgm_start__:
""".strip('\n')

def gen_args(file: str, limit_mem: bool = False) -> 'list[str]':
    """
    Generate argument structure that the assembler's `main` function
    expects.
    """
    args = ['assembler.py', file]
    if limit_mem:
        args += ['-d', '10', '-p', '10']
    return args