========================================
Instructions
========================================

.. meta::
   :description: Corvassembly instructions reference.
   :keywords: Corvassembly, assembly, reference, instructions

Corvassembly currently has 25 instructions, with a maximum of 32 possible
given the structure of the 32-bit instruction word.

.. note -- invisible character used after nop
.. function:: nop‎

  No operation. Simply increments the program counter.

Memory Instructions
====================

.. function:: ldr <operand1>, <operand2>

  Load register. Loads ``operand1`` with ``operand2``.

  :param operand1: register

  :param operand2: register / immediate / variable

.. function:: str <operand1>, <operand2>

  Store register. Stores ``operand1`` in ``operand2``.

  :param operand1: register

  :param operand2: variable

.. function:: lpt <operand1>, <source>

  Load from pointer. Loads ``operand1`` from ``source``.

  :param operand1: register

  :param operand2: ram, rom, gpu

.. function:: spt <operand1>, <source>

  Store to pointer. Stores ``operand1`` in ``source``.

  :param operand1: register

  :param operand2: ram, gpu

Arithmetic Instructions
=======================

.. function:: cmp <operand1>, <operand2>

  Compare. Compare ``operand1`` to ``operand2``. This
  instruction updates the equal, greater, and less status flags.

  :param operand1: register

  :param operand2: register / immediate / variable

.. function:: add <operand1>, <operand2>[, <result>]

  Add. Adds ``operand2`` to ``operand1``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative status flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: sub <operand1>, <operand2>[, <result>]

  Subtract. Subtracts ``operand2`` from ``operand1``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative status flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: mul <operand1>, <operand2>[, <result>]

  Multiply. Multiplies ``operand1`` by ``operand2``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative status flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. warning:: div and mod not yet implemented in hardware!
.. function:: div <operand1>, <operand2>[, <result>]

  Divide. Divides ``operand1`` by ``operand2``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative status flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: mod <operand1>, <operand2>[, <result>]

  Modulus. Takes the remainder of ``operand1`` divided by ``operand2``,
  and stores in ``result``. If only two arguments are given,
  the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative status flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

Logic Instructions
=======================

.. function:: and <operand1>, <operand2>[, <result>]

  Logical and. Performs a bitwise and operation between ``operand2`` and
  ``operand1``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero and negative status flags, and sets carry to zero.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: or <operand1>, <operand2>[, <result>]

  Logical or. Performs a bitwise or operation between ``operand2`` and
  ``operand1``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero and negative status flags, and sets carry to zero.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: xor <operand1>, <operand2>[, <result>]

  Logical exclusive or. Performs a bitwise xor operation between ``operand2`` and
  ``operand1``, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero and negative status flags, and sets carry to zero.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: not <operand1>[, <result>]

  Logical not. Performs a bitwise not operation to ``operand1`` and stores
  in ``result``. If only one argument is given -- assuming that argument is a
  register -- the result is stored back in ``operand1``. If ``operand1`` is not
  a register and no valid result argument is given, the assembler reports an
  error. This
  instruction updates the zero and negative status flags, and sets carry to zero.

  :param operand1: register / variable

  :param result: register

.. function:: lsl <operand1>, <operand2>[, <result>]

  Logical shift left. Performs a bitshift left operation on ``operand1`` by
  ``operand2`` number of bits, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

.. function:: lsr <operand1>, <operand2>[, <result>]

  Logical shift right. Performs a bitshift right operation on ``operand1`` by
  ``operand2`` number of bits, and stores in ``result``. If only
  two arguments are given, the result is stored back in ``operand1``. This
  instruction updates the zero, carry, and negative flags.

  :param operand1: register

  :param operand2: register / immediate / variable

  :param result: register

Jump Instructions
=================

.. function:: jmp <operand1>

  Jump. Jumps to the address of label ``operand1``.

  :param operand1: label

.. function:: jsr <operand1>

  Jump to subroutine. Pushes the current address onto the address stack and
  Jumps to the address of label ``operand1``.

  :param operand1: label

.. note -- invisible character after rts
.. function:: rts‎

  Return from subroutine. Pops the last address off the address stack and
  Jumps to that address.

.. function:: joc <condition>, <operand1>

  Jump on condition. Jumps to the address of label ``operand1`` if ``condition``
  evaluates to true.

  :param condition: zero, carry, negative, equal, greater, less
  :param operand1: label

.. function:: jsc <condition>, <operand1>

  Jump to subroutine on condition. Pushes the current address onto the address
  stack and jumps to the address of label ``operand1`` if ``condition``
  evaluates to true.

  :param condition: zero, carry, negative, equal, greater, less
  :param operand1: label

.. function:: rsc <condition>

  Return from subroutine on condition. Pops the last address off the
  address stack and jumps to that address if ``condition``
  evaluates to true.

  :param condition: zero, carry, negative, equal, greater, less

.. function:: rti <interrupt>

  Return from interrupt. This tells the processor to restore the register states
  saved when ``interrupt`` was set, pops the last address off the address stack
  and jumps to that address.

  :param interrupt: interrupt vector

.. function:: ric <condition>, <interrupt>

  Return from interrupt on condition. This tells the processor to restore
  the register states saved when ``interrupt`` was set, pops the last address
  off the address stack and jumps to that address if ``condition`` evaluates
  to true.

  :param condition: zero, carry, negative, equal, greater, less
  :param interrupt: interrupt vector
