.. _assembler:

========================================
Using the Assembler
========================================

.. fun comment for fungis

.. meta::
   :description: Using the Corvassembly assembler.
   :keywords: Corvassembly, assembly, assembler

Installation
==============

The assembler is written in python, and therefore requires an up-to-date installation.
If you don't have it -- on Windows, head to the `python downloads page`_ and
download the latest installer. On Unix, install python with your favorite
package manager.

.. _python downloads page: https://www.python.org/downloads/

There are two dependencies for the code -- `numpy`_ and `antlr`_. They can
be installed with:

.. _numpy: https://numpy.org/
.. _antlr: https://www.antlr.org/

.. code-block:: bash

  $ pip install numpy
  ...
  $ pip install antlr4-python3-runtime

Because of the limitations associated with running python code, the
process is not particularly elegant (I plan to rewrite the assembler in C++).
The recommended method is to clone `the repository`_
and place the "assembler" directory into the same directory as your code. You
can also download the zipped assembler directory :download:`here. <assembler.zip>`
Then, you'd simply invoke:

.. _the repository: https://github.com/CorvusPrudens/Corvassembly

.. code-block:: bash

  $ python assembler/assembler.py

to run the assembler.

.. note:: Your installation of python may require you to use ``python3``
  and ``pip3`` instead of ``python`` and ``pip``, since the latter are
  often linked to python 2.7 installations.

Assembler Program
=================

Usage: <infile> [[options] [parameters] ...]

Options:

-h, --help  print the usage message
-p romname  generate a program rom hex file with the given romname for use
  with verilog $readmemh
-d romname  generate a data rom hex file with the given romname for use with
  verilog $readmemh
-P bits  specify the number of bits in the program rom address (default: 10)
-D bits  specify the number of bits in the data rom address (default: 10)
--debug-vars  print out the organized variables for debugging purposes
--debug-lines  print out the organized program lines for debugging purposes
--log logname  redirect the above output into the given logname file

Example invocation:

.. code-block:: bash

  $ python assembler/assembler.py program.cor -p promdata -d dromdata
