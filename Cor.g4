grammar Cor;

// parsey stuff

parse           : initial block* EOF;

block           : label statement*;

file_import     : IMPORT (VARIABLE | STRING) (AS VARIABLE)?;

initial         : (statement | file_import)*;

statement       : declaration | assignment | assignment_arr | instruction;

assignment      : CONST VARIABLE '=' expression;

assignment_arr  : CONST array;

declaration     : RAM VARIABLE array_init*;

expression      : exp_number | exp_var | math;

math            : ((NUMBER | VARIABLE) OPERATOR)+ (NUMBER | VARIABLE);

exp_number      : NUMBER;

exp_var         : VARIABLE;

array           : VARIABLE (array_init)+ '=' (arr_data | string);

string          : STRING;

array_init      : OBRACKET expression? CBRACKET;

arr_data        : '{' ((expression | arr_data | string) ',')*
                  (expression | arr_data | string) ','? '}';

instruction     : MNEMONIC (argument (',' argument)*)?;

argument        : register | expression;

register        : REGISTER;

label           : VARIABLE ':';

// lexy stuff
RAM : 'ram';
fragment ROM : 'rom';
fragment PRE : 'pre';
CONST : ROM | PRE;

IMPORT                 : 'import';
AS                     : 'as';

fragment SINGLE_STRING : '\'' (~["\r\n] | '\'\'')* '\'';
fragment DOUBLE_STRING : '"' (~["\r\n] | '""')* '"';

STRING                 : SINGLE_STRING | DOUBLE_STRING;

COMMENT                : '//' ~[\n\r]* [\n\r] -> skip;

COMMENT_BLOCK                   : '/*' .*? '*/' -> skip;



OBRACKET               : '[';
CBRACKET               : ']';

REGISTER               : [a-h];

// TODO -- make these case case insensitive
MNEMONIC               :  'nop'| 'ldr'| 'str'| 'lpt'|
                          'spt'| 'cmp'| 'add'| 'sub'|
                          'mul'| 'div'| 'mod'| 'and'|
                          'or'|  'xor'| 'not'| 'lsl'|
                          'lsr'| 'psh'| 'pop'| 'pek'|
                          'jmp'| 'jsr'| 'rts'| 'joc'|
                          'jsc'| 'rsc'| 'tfm';

VARIABLE               : [A-Za-z_][A-Za-z_0-9.]*;

WHITESPACE             : [ \t\n\r] -> skip;

OPERATOR               : [+\-=/*()<>|&^~];

fragment DEC           : [1-9][0-9_]* | '0';
fragment HEX           : '0x'[0-9A-Fa-f][0-9A-Fa-f_]*;
fragment BIN           : '0b'[0-1][0-1_]*;
fragment FLT           : ([1-9][0-9_]* | '0') '.' ([1-9][0-9_]* | '0');

NUMBER                 : DEC | BIN | HEX | FLT;
