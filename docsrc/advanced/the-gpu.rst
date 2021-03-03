.. _The GPU:

===========
The GPU
===========

The CorvusPrudensUnit features a graphics
processing unit for offloading drawing
from the processor. It' a bit of a stretch to
call it a GPU, but it does allow for much
faster and more sophisticated monochrome
drawing.

The GPU is accessed primarily from the ``lpt``
and ``spt`` instructions, as ``gpu`` is one of
the three memories that can be accessed by
indirect addressing. GPU memory cannot be read --
only written.

Address space:
--------------

0x0000-0x01FF
	This range provides direct access to the display
	buffer. The buffer is organized into
	4 rows of 128, 16-bit words. Each word 
	corresponds to 16 vertical pixels, with
	bit position 0 being the top and bit 
	position 15 the bottom. Since GPU memory
	cannot be read, it is recommended to use
	a frame buffer in ram if you want to
	draw directly to the GPU buffer.

0x0200-0x03FF
	This range provides access to the GPU request
	buffer. Each request is two words long, with
	the first word specifying the parameters and
	sprite index, and the second word specifying
	the position. The screen position starts at
	16, 16 -- this facilitates easier partial
	drawing.

.. table of structure here with more in-depth
.. explanation
.. NOTE -- double check address ranges

0x0400-0x07FF
	This range provides access to the sprite
	buffer. The graphics acceleration 
	process reads the requests and copies the
	specified sprites to the screen buffer.
	Sprites can have an arbitrary width up to
	the buffer size of 256 words, but can only
	be a maximum height of 16 pixels.

0x0800-0x0FFF
	This range provides access to the character
	"ROM". The default font is loaded in during
	configuration, but can be rewritten if desired.
	The buffer consists of 1024 8-bit words, 
	corresponding to a maximum text size of 4x8.

0x1000-0x1001
	Address 0x1000 determines whether the display
	buffer is cleared before every draw, and
	address 0x1001 is the word written for
	clearing. Naturally, this would typically be zero
	but you may occasionally want different
	patterns.
