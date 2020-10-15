grammar Cor;

// parsey stuff

parse           : initial block* EOF;

block           : label (statement | statement_loop)*;

file_import     : IMPORT (VARIABLE | STRING) (AS VARIABLE)?;

initial         : (statement | file_import)*;

statement       : declaration | assignment | assignment_arr | instruction;

statement_loop  : loop;

loop            : FOR instruction SEMI instruction SEMI instruction SEMI OBRACE (label | instruction | loop_keyword | loop)* CBRACE;

loop_keyword    : CONTINUE | BREAK | BREAKALL;

/* loop_init       : REGISTER '=' expression;

loop_cond       : REGISTER OPERATOR expression;

loop_incr       : REGISTER (OP_SHORT | OPERATOR) expression?;
*/

assignment      : CONST VARIABLE '=' expression;

assignment_arr  : CONST array;

declaration     : RAM VARIABLE array_init*;

expression      : exp_number | exp_var | math;

math            : (((NUMBER | VARIABLE) OPERATOR)+ (NUMBER | VARIABLE))
                | OPERATOR (NUMBER | VARIABLE)
                ;

exp_number      : NUMBER;

exp_var         : VARIABLE;

array           : VARIABLE (array_init)+ '=' (arr_data | string);

string          : STRING;

array_init      : OBRACKET expression? CBRACKET;

arr_data        : OBRACE ((expression | arr_data | string) ',')*
                  (expression | arr_data | string) ','? CBRACE;

instruction     : MNEMONIC (argument (',' argument)*)?;

argument        : register
                | expression
                | RAM
                | CONST
                | GPU
                ;

register        : REGISTER;

label           : VARIABLE ':';

// lexy stuff

fragment SINGLE_STRING : '\'' (~["\r\n] | '\'\'')* '\'';
fragment DOUBLE_STRING : '"' (~["\r\n] | '""')* '"';

STRING                 : SINGLE_STRING | DOUBLE_STRING;

COMMENT                : '//' ~[\n\r]* [\n\r] -> skip;

COMMENT_BLOCK                   : '/*' .*? '*/' -> skip;

RAM : 'ram';
fragment ROM : 'rom';
fragment PRE : 'pre';
CONST : ROM | PRE;

GPU                    : 'gpu';
IMPORT                 : 'import';
AS                     : 'as';
FOR                    : 'for';
CONTINUE               : 'continue';
BREAKALL               : 'breakall';
BREAK                  : 'break';

SEMI                   : ';';

OBRACKET               : '[';
CBRACKET               : ']';

OBRACE                 : '{';
CBRACE                 : '}';

REGISTER               : [a-h];

// TODO -- make these case case insensitive
MNEMONIC               :  'nop'| 'ldr'| 'str'| 'lpt'|
                          'spt'| 'cmp'| 'add'| 'sub'|
                          'mul'| 'div'| 'mod'| 'and'|
                          'or'|  'xor'| 'not'| 'lsl'|
                          'lsr'| 'psh'| 'pop'| 'pek'|
                          'jmp'| 'jsr'| 'rts'| 'joc'|
                          'jsc'| 'rsc'| 'tfm';

// we won't be able to detect array names as variable (i.e. arr[0])
// we'll have to come up with a workaround
VARIABLE               : '$'?[A-Za-z_][A-Za-z_0-9.]*;

WHITESPACE             : [ \t\n\r] -> skip;

/* OP_SHORT               : '++' | '--' | [+\-/*<>|&^~][+\-=]; */

OPERATOR               : [+\-=/*()<>|&^~];

fragment DEC           : [1-9][0-9_]* | '0';
fragment HEX           : '0x'[0-9A-Fa-f][0-9A-Fa-f_]*;
fragment BIN           : '0b'[0-1][0-1_]*;
fragment FLT           : ([1-9][0-9_]* | '0') '.' ([1-9][0-9_]* | '0');

NUMBER                 : DEC | BIN | HEX | FLT;
