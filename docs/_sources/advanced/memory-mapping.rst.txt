.. _memory-mapping:

========================================
Memory-mapped Modules
========================================

.. the funnest of them all

.. meta::
   :description: Corvassembly memory mapping.
   :keywords: Corvassembly, assembly, memory, map


Corvassembly allows the programmer to access peripherals
through memory-mapped interfaces.
Reading and writing to these modules is done in the same way as reading and
writing to ``ram``:

.. code-block:: corvassembly

  ldr a, UART
  ldr a, add 32
  str a, UART

Each module has built-in variables so the programmer doesn't need to memorize
addresses.

Modules
=========

.. note -- invisible characters used in UART name and var!
.. function:: UART‎

  A UART module with built-in 512 byte RX and TX FIFO buffering.

  :var read UART‎: read the current FIFO value and increment FIFO pointer
  :var write UART: write to FIFO; data is sent as soon as the UART is not busy
  :var read UART_STATUS: read the UART status

+------------------+---------------+----------------------------------------------------------+
| ``UART_STATUS``  | ``---- EFGH`` | ``(E) RX empty, (F) RX full, (G) TX empty, (H) TX full`` |
+------------------+---------------+----------------------------------------------------------+


.. function:: APU‎

  Emulation of the 2A03's audio processing unit. Variable names reflect the
  addresses for the original chip.

  :var R4000: Pulse 1 duty, control bits, and volume
  :var R4001: Pulse 1 sweep unit
  :var R4002: Pulse 1 timer LSB
  :var R4003: Pulse 1 length counter and timer MSbs

  :var R4004: Pulse 2 duty, control bits, and volume
  :var R4005: Pulse 2 sweep unit
  :var R4006: Pulse 2 timer LSB
  :var R4007: Pulse 2 length counter and timer MSbs

  :var R4008: Triangle control bits and linear counter
  :var R400A: Triangle timer LSB
  :var R400B: Triangle length counter and timer MSbs

  :var R400C: Noise control bits and volume
  :var R400E: Noise loop flag and period
  :var R400F: Noise length counter

  :var R4010: DMC control bits and frequency
  :var R4011: DMC load counter
  :var R4012: DMC sample address
  :var R4013: DMC sample length

  :var R4015: APU status register

  :var R9000: VRC6 Pulse 1 control bit, duty, and volume
  :var R9001: VRC6 Pulse 1 timer LSB
  :var R9002: VRC6 Pulse 1 enable and timer MSbs

  :var RA000: VRC6 Pulse 2 control bit, duty, and volume
  :var RA001: VRC6 Pulse 2 timer LSB
  :var RA002: VRC6 Pulse 2 enable and timer MSbs

  :var RB000: VRC6 Saw accumulator rate
  :var RB001: VRC6 Saw timer LSB
  :var RB002: VRC6 Saw enable and timer MSbs

Detailed information about `2A03`_ and `VRC6`_ register function can be found
at the `NesDev wiki`_.

.. _2A03: https://wiki.nesdev.com/w/index.php/APU
.. _VRC6: https://wiki.nesdev.com/w/index.php/VRC6_audio
.. _NesDev wiki: https://wiki.nesdev.com/w/index.php/Nesdev_Wiki

.. function:: Timer‎

  A timer module that can produce interrupts at a fixed frequency.

  :var TIMER_STATUS: one enables the module, zero disables
  :var TIMER_COMP: Timer compare register. When the timer accumulator
    reaches the value in ``TIMER_COMP``, the timer accumulator is reset to
    zero and the ``TIMER`` interrupt is set.
  :var TIMER_PRES: 8-bit timer prescalar register. Each clock, the prescalar
    register is decremented. When it reaches zero, the timer accumulator is
    incremented and the prescalar register is reset to the value of ``TIMER_PRES``.
    A zero in ``TIMER_PRES`` means the accumulator will increment every clock.

.. function:: Scope‎

  A module that writes analog values to a 128-byte buffer at a specified frequency.

  :var SCOPE_RATE: 16-bit register that determines sampling frequency. When the
    sample accumulator is decremented to zero, a new sample is loaded into the
    buffer and the accumulator is reset to the value of ``SCOPE_RATE``.

  :var read SCOPE_DATA: reads from the sample buffer. When read, the read address
    is incremented by one. When the address reaches the end of the buffer, it
    simply wraps.

.. note -- we're using some invisible character here too
.. function:: Flash‎

  A module that allows easy access and control of typical 8-pin flash memory
  chips used for FPGA configuring.

  :var read FLASH_DATA‎: Read from the FIFO buffer. This should be done after
    a read request is sent and the module is no longer busy.

  :var write FLASH_DATA: Write to the FIFO buffer. This should be done before
    sending a write request.

  :var FLASH_PAGE: Address of the page to be operated on (most significant 16 bits).

  :var write FLASH_STATUS‎: Status register for the module. Read, write, and erase
    requests are handles through this register.

  :var read FLASH_STATUS: Reading the status register returns only the busy and
    error bits

+-------------------+---------------+-----------------------------------------------------------------------+
|                   |               | | ``(S) Program security bit, (s) Configuration security bit,``       |
| ``FLASH_STATUS``  | ``SsPE RWeB`` | | ``(P) 256 or 512 (packed) transfer, (E) Erase request,``            |
|                   |               | | ``(R) Read request, (W) Write request, (e) error bit, (B) Busy bit``|
+-------------------+---------------+-----------------------------------------------------------------------+

Read requests can be safely made anywhere within the Flash memory address space.
Write and erase requests, however, are not necessarily safe in the areas where
FPGA configuration and program data are stored. For these addresses, (page
0x05FF and below) the appropriate security bit must be set before a write
or erase operation will occur. The configuration space ends at address 0x02FF,
corresponding to the configuration security bit. The program space begins at
0x0300 and ends at 0x05FF, coresponding to the program security bit.

If an illegal operation is requested, the module exits the process and sets the
error bit.

After any operation, security bits are reset. For every write and read to
relevant addresses, they must be set before every transfer.

A read operation will read the entire page at address ``FLASH_PAGE``. Therefore,
it is recommended to read and write in 256-byte chunks.

The erase operation erases 4096 byte sectors, meaning that an erase only need be
requested every 16 pages. Erase requests must occur on address multiples of 4096.
If erase requests are not made on multiples of 4096, the module exits the
operation and sets the error bit.

.. note:: The ``page`` register controls the most significant 16 bits of the
  24 bit flash address, meaning that 4096 byte intervals correspond to page
  intervals of 16.
