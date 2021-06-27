# TODO

## ~~Add ram initialization capabilities, e.g.:~~

~~~ 
pre INIT = 1

ram value = 0
ram state = 1
ram init = INIT

ram arr[] = {1, 2, 3}
ram arr[20] = {INIT}

~~~

Where each initialization is _global_, happening once before any other code is run. Equivalent initial assignment should be grouped so their assignments take up less code, e.g.:

~~~
// Prefer this:
ldr a, 1
str a, state
str a, init

// over this:
ldr a, 1
str a, state
ldr a, 1
str a, init
~~~

Arrays should be handled simply: if no size is given, the array size is determined by the initializer list and filled accordingly. If a size is given and the initializer list matches the given list, fill it normally. If it is smaller, then continue filling the array with the last value in the list. If the list is longer, then throw an error. 

In either case, simple patterns should be extracted (i.e. all one number). If the array is longer than, say, 5 instructions (or however many are involved in a loop), then simply create a loop to iterate over each index and store the value. Execution time really isn't a concern compared to code size for this.

As usual, ram variables can also be uninitialized.

## Add simple variable operations:

~~~ cor

// these are reserved for automatic operations, so
// be careful using them for anything else. If these two
// are not enough for the operations below, then throw an
// error. If these registers are used manually after declaring
// this, throw a warning.
#pragma working a b

ram input = 1
ram prevInput = 1
ram inc = 2
ram state = 0

main:
    input = 2

    if (input != prevInput) {
        prevInput = input
        state += inc
    }

// The above would assemble to something like:

__pgm_start__:
ram input
ram prevInput
ram inc
ram state

ldr a, 1
str a, input
str a, prevInput
ldr a, 0
str a, state
ldr a, 2
str a, inc

main:
    ldr a, 2
    str a, input

    // This should detect if a register has the value already in it, and make sure it's
    // up-to-date / the same as that stored in ram
    if (cmp a, prevInput is not equal) {

        str a, prevInput

        ldr a, state
        ldr b, inc
        add state, inc
        str a, state

    }

~~~

## ~~Add tests~~

Each aspect of assembly should be tested, for example:

test input
~~~
for (ldr a, 0; cmp a, 256; add a, 1) {
    str a, UART
}
~~~

expected output:
~~~
jmp __pgm_start__
jmp __pgm_start__
jmp __pgm_start__
__pgm_start__:
ldr a, 0
__loop0_begin:
cmp a, 256
joc equal, __loop0_end
str a, UART
__loop0_continue:
add a, 1
jmp __loop0_begin
__loop0_end:
~~~