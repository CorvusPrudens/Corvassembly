.. _basic-syntax:

========================================
Basic Syntax
========================================

.. even funner comment

.. meta::
   :description: Overview of basic Corvassembly syntax.
   :keywords: Corvassembly, assembly, syntax

Registers
========================================

Most instructions will require you to interact with the processor's *registers*.
The CorvusPrudensUnit features eight 16-bit registers -- from a to h.

Instructions
========================================

Corvassembly borrows many syntactical elements from typical assembly
implementations. Code consists of instructions followed by comma-separated
arguments:

.. code-block:: corvassembly

  add a, 42

For most arithmetic and memory operations, the first argument indicates
which CPU register the instruction should operate on. The second argument
can either be an immediate value, another register, or an address in RAM
represented by a variable. An optional third argument indicates which
register to store the result in. By default, the result is stored in the
register of argument 1.

.. _Labels:

Labels
========================================

Labels are written as names followd by a colon:

.. code-block:: corvassembly

  main:
    // main code loop
    jmp main

and can be used as arguments for jump-type instructions. If you'd like to
attach an interrupt to a label, append the name of the desired interrupt to
the label in parentheses:

.. code-block:: corvassembly

  frameInterrupt(FRAME):
    // interrupt routine
    rti FRAME

If you wish to preserve the state of the registers (which you almost
always will), you must end the interrupt subroutine with an ``rti``
instruction followed by the name of the interrupt.

Conditional Instructions
========================================

Instructions with conditional execution require conditions as arguments. For
example, a conditional jump is formatted as:

.. code-block:: corvassembly

  joc equal, label

The six processor conditions are ``zero``, ``carry``, ``negative``, ``equal``,
``greater``, and ``less``. The first three are updated by most arithmetic and
logic instructions, and the last three are updated by the ``cmp`` instruction.

Variables
========================================

Variables are handled by the assembler with little user intervention.
Align directives or other similar manual adjustment are not currently supported.
The two main types of variables are ``ram`` variables and ``rom`` variables. ``ram``
variables cannot be given initial assignments, while rom variables *must* be
given assignments:

.. code-block:: corvassembly

  ram ramVar
  rom romVar = 42

If you want to parameterize your code, you can also use ``pre`` variables. These
pre-processor values are not stored in memory once the code is assembled:

.. code-block:: corvassembly

  pre INIT = 42
  rom romVar = INIT

Comments
========================================

Single line comments are indicated by two forward-slashes. Block comments
can be made with /* and \*/:

.. code-block:: corvassembly

  // this is an in-line comment!

  /*
    This is a multi-
    line comment!
  */

Putting it all together
========================================

Using just these basic features, you can of course construct any program
imaginable (though it may not be very manageable). Here's an example of
computing the Fibonacci sequence and sending it to a memory-mapped UART:

.. code-block:: corvassembly

  // Fibonacci sequence!

  pre LIMIT = 255

  reset:
  ldr a, 1
  ldr b, 0
  ldr c, 0

  fibLoop:
    str a, UART // writing to a memory-mapped UART module

    add a, 0, c // like a mov a, c instruction
    add a, b
    add c, 0, b

    cmp a, LIMIT
    joc greater, reset
    jmp fibLoop
