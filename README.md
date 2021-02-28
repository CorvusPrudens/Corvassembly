# Corvassembly

 Development tools for the _Corvus_ flavor of assembly.

 The documentation can be found [here](https://corvusprudens.github.io/Corvassembly/).

If you'd like to assemble your own code, you'll need the [antlr4](https://www.antlr.org/) runtime for python. Assuming you already have python and pip installed, simply run:

```shell
$ pip install antlr4-python3-runtime
```
In addition, you'll need [numpy](https://numpy.org/):

```shell
$ pip install numpy
```

If you want to modify the assembler itself, it would be helpful to install the latest antlr4 complete jar. The ANTLR site provides [an easy quick-start guide](https://www.antlr.org/) for this process. Note that in order for the configurations to persist beyond the current session, you should paste the export and alias commands into your shell's configuration file.

To test the lexer and parser and produce a graphic syntax tree, simply enter:
```shell
$ make test
```

To build the python output files and assemble a test file:
```shell
$ make
```
