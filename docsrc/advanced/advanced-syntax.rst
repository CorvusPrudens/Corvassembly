.. _advanced-syntax:

========================================
Advanced Syntax
========================================

.. fun comment for fungis

.. meta::
   :description: Advanced Corvassembly syntax.
   :keywords: Corvassembly, assembly, syntax

File imports
=============

Files can be imported using a Python-like syntax:

.. code-block:: corvassembly

  import libs.graphics as graphics
  import "libs/menu.cor"

  main:
    jsr graphics.begin
    jsr menu.main
    jsr graphics.end
    jmp main

As in python, imported file labels and variables are not implicit. Unlike Python,
however, Corvassembly does not support importing everything (i.e. ``from graphics import *``).
``ram`` and ``rom`` addresses are statically allocated in the order of importing.
Import paths are relative to the file doing the importing, not where the
assembler is executed (as it should be).

.. note:: Standard modules are not currently implemented, but may be added soon.

Assemble-time Math
========================================

Arbitrary math can be executed with static elements of a program at assemble
time in any place where static elements are valid. The math is executed
by Python, so any valid Python statement can be used:

.. code-block:: corvassembly

  pre MAX = 2**16 - 1
  pre SIZE = 128*64
  rom sizes[] = {SIZE/8, SIZE/16}

  cmp a, SIZE - 1

Arrays
========================================

Corvassembly permits the declaration of arrays in ``ram`` and ``rom``. ``ram``
arrays cannot be initialized, while ``rom`` arrays *require* initialization.
``rom`` arrays should not generally be given a size, as it is automatically
determined by the contents of the initializer.
Strings are interpreted as arrays, and are initialized with the same left-side
syntax. String arrays are converted to their ascii representation and null-
terminated:

.. code-block:: corvassembly

ram arr[20]
rom arrInit[] = {1, 1, 2, 3, 5, 8, 13}
rom string[] = "Hello, world!"

Character Constants
========================================

Character constants, much like in other languages, are surrounded by single ticks
and are converted to their `ASCII`_ representation. You can operate on them
like any other number, and even manually declare your own strings (if you like pain):

.. _ASCII: https://en.wikipedia.org/wiki/ASCII

.. code-block:: corvassembly

  rom char = 'a'
  rom escaped = '\''
  rom string[] = {'H', 'e', 'l', 'l', 'o', '\0'}

  cmp a, 'A' + 24

Variable Addressing
=====================

Variable addresses can be accessed with C-like syntax, using ``&``. In addition,
array names are automatically understood as static addresses rather than
variables, and can also be treated in a C-like manner:

.. code-block:: corvassembly

  ram variable
  ldr f, &variable

  rom array[] = {1, 2, 3, 'a', 'b', 'c'}
  ldr g, array

Pointer Registers
========================================

In Corvassembly, there are three registers that double as pointers to
specific types of memory.

f
  This register can be used to access ``ram``.
g
  This register can be used to access ``rom``.
h
  This register can be used to access the ``gpu``, should it be implemented.

The address space of any memory does not exceed 16 bits, so
each pointer can be fully loaded in a single instruction. These pointers
are invoked with a ``lpt`` or ``spt`` instruction and the corresponding
memory type, e.g.:

.. code-block:: corvassembly

  ram array[20]
  ldr f, array
  lpt a, ram

If statements
========================================

``if``, ``elif``, and ``else`` statements are fully supported by Corvassembly.
They are structured as you would expect, where an ``if`` can stand alone,
it can be followed by an ``elif`` or ``else``, or any number of ``elif``
statements and a closing ``else``. Conditions are indicated with an ``is`` or
``isnt`` and the desired condition. By the nature of Corvassembly, ``isnt``
statements require one less instruction, but may be more difficult to read:

.. code-block:: corvassembly

  wait:
    if (cmp a, 1 is equal) {
      jmp wait
    }
    rts

  waitShort:
    if (cmp a, 0 isnt equal) {
      jmp waitShort
    }
    rts

  switch:
    if (cmp a, 1 is equal) {
      jsr condition1
    } elif (cmp a, 2 is equal) {
      jsr condition2
    } elif (cmp a, 3 is equal) {
      jsr condition3
    } else {
      rts
    }
    rts

The first two examples assemble to:

.. code-block:: corvassembly

  wait:
  cmp a, 1
  joc equal, __if0_branch0_t
  jmp __if0_end
  __if0_branch0_t:
  jmp wait
  __if0_end:
  rts

  waitShort:
  cmp a, 0
  joc equal, __if1_end
  __if1_branch0_t:
  jmp waitShort
  __if1_end:
  rts

``if`` statements can, of course, be nested:

.. code-block:: corvassembly

  waitNested:
  if (cmp a, 1 is equal) {
    if (cmp b, 1 is equal) {
      jsr somewhereElse
    }
    rts
  }
  jmp waitNested

``if`` statements also permit an arbitrary number of instructions to precede
the actual evaluation, separated by semi-colons. This facilitates more
complicated behavior within ``if elif else`` blocks:

.. code-block:: corvassembly

  inbetween:

    ram state1
    ram state2

    ldr a, state1
    if (cmp a, 1 is equal) {
      jsr state1True
    } elif (ldr a, state2; cmp a, 1 is equal) {
      jsr state2True
    }

    rts

For loops
========================================

Corvassembly features a somewhat limited form of the for loop, with syntax
similar to C's:

.. code-block:: corvassembly

  for (ldr a, 0; cmp a, 256; add a, 1) {
    str a, UART
  }

This assembles to:

.. code-block:: corvassembly

  ldr a, 0
  __loop0_begin:
  cmp a, 256
  joc equal, __loop0_end
  str a, UART
  __loop0_continue:
  add a, 1
  jmp __loop0_begin
  __loop0_end:

The feature is limited because it can only accept register incrementors, meaning
the chosen register cannot be altered during the loop. It's often useful to
jump to subroutines during a loop, so this limitation is somewhat crippling.

There are a number of keywords available for use inside a loop, including
``continue``, ``break``, and ``breakall``. ``continue`` simply jumps to the
end of the loop, skipping to the next iteration. ``break`` will break out of
the loop, jumping to the end. ``breakall`` will break out of nested loops (I'm
not sure why more languages don't have this feature):

.. code-block:: corvassembly

  ram interruptBreak
  ram interruptCont

  for (ldr a, 0; cmp a, 256; add a, 1){
    for (ldr b, 0; cmp b, 256; add b, 1){
      if (cmp a, interruptStop is less) {
        breakall
      } else if (cmp b, interruptCont is less) {
        continue
      }
      str a, UART
    }
  }
