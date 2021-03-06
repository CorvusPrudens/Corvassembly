grammar Cor;

// parsey stuff

parse           : initial block* EOF;

block           : label (statement | statement_loop | statement_if | directive)*;

file_import     : IMPORT (VARIABLE | STRING) (AS VARIABLE)?;

initial         : (statement | statement_loop | statement_if | file_import | directive)*;

directive       : PRAGMA (VARIABLE (NUMBER | VARIABLE | string)?)+;

statement       : struct_def | data | instruction;

statement_loop  : loop;

statement_if    : if_chain;

cond_block      : OBRACE (label | instruction | loop | if_chain | loop_keyword)* CBRACE;

loop            : FOR OPAR instruction SEMI instruction SEMI instruction CPAR cond_block;

loop_keyword    : CONTINUE | BREAK | BREAKALL;

// comparison      : REGISTER COMPARATOR math;

conditional     : OPAR (instruction SEMI)* instruction (IS|ISNT) CONDITION CPAR;

if_stat         : IF conditional cond_block;

elif_stat       : ELIF conditional cond_block;

else_stat       : ELSE cond_block;

if_chain        : if_stat elif_stat* else_stat?;

/* loop_init       : REGISTER '=' expression;

loop_cond       : REGISTER OPERATOR expression;

loop_incr       : REGISTER (OP_SHORT | OPERATOR) expression?;
*/

hint            : OBRACKET STRING (',' STRING)* ','? CBRACKET;

assignment      : hint? CONST VARIABLE '=' expression;

assignment_arr  : hint? CONST array;

declaration     : RAM VARIABLE array_init*;

assignment_ram  : RAM VARIABLE '=' expression;

assignment_arr_ram : RAM array;

expression      : exp_number | exp_var | math | exp_char | exp_attr;

math_obj        : NUMBER | VARIABLE | CHAR | exp_attr;

// this is getting ugly
math            : OPERATOR* OPAR* ((OPAR* (math_obj) CPAR* OPERATOR)+ OPAR*(math_obj)CPAR*) *CPAR ((OPERATOR|COMPARATOR) (math_obj))*
                | OPAR* OPERATOR OPAR* (math_obj) *CPAR
                | (((math_obj) (OPERATOR|COMPARATOR))+ (math_obj))
                | OPERATOR (math_obj)
                ;

exp_number      : NUMBER;

data            : assignment | assignment_arr | assignment_arr_ram | assignment_ram | declaration;

struct_def      : STRUCT VARIABLE '=' OBRACE data+ CBRACE;

struct_dec      : (VARIABLE VARIABLE)
                | VARIABLE VARIABLE '=' OBRACE (expression)+ CBRACE;

array           : VARIABLE (array_init)+ '=' (arr_data | string);

exp_var         : VARIABLE array_init*;

exp_char        : CHAR;

exp_attr        : VARIABLE attribute;

attribute       : ATTR_OP VARIABLE;

array_init      : OBRACKET expression? CBRACKET;

string          : STRING;

arr_data        : OBRACE ((expression | arr_data | string) ',')*
                  (expression | arr_data | string) ','? CBRACE;

instruction     : MNEMONIC (argument (',' argument)*)?;

argument        : register
                | expression
                | RAM
                | CONST
                | GPU
                | CONDITION
                ;

register        : REGISTER;

label           : (VARIABLE COLON) | (VARIABLE OPAR VARIABLE CPAR COLON);

// lexy stuff

STRING                 : '"' (~["\r\n] | '""' | '\\"')* '"';

CHAR                   : '\'' (('\\'.)|~[\\]) '\'';

COMMENT                : '//' ~[\n\r]* [\n\r] -> skip;

COMMENT_BLOCK          : '/*' .*? '*/' -> skip;

TEST_BLOCK             : '$' .*? '$end' -> skip;

PRAGMA                 : '#pragma';

RAM                    : 'ram';
fragment ROM           : 'rom';
fragment PRE           : 'pre';
fragment FLASH         : 'flash';
CONST                  : ROM | PRE | FLASH;
STRUCT                 : 'struct';

GPU                    : 'gpu';
IMPORT                 : 'import';
AS                     : 'as';
FOR                    : 'for';
CONTINUE               : 'continue';
BREAKALL               : 'breakall';
BREAK                  : 'break';

IF                     : 'if';
ELIF                   : 'elif';
ELSE                   : 'else';
IS                     : 'is';
ISNT                   : 'isnt';

CONDITION              : 'zero'
                       | 'carry'
                       | 'negative'
                       | 'equal'
                       | 'greater'
                       | 'less'
                       ;

ATTR_OP                : '->';

SEMI                   : ';';
COLON                  : ':';

OPAR                   : '(';
CPAR                   : ')';

OBRACKET               : '[';
CBRACKET               : ']';

OBRACE                 : '{';
CBRACE                 : '}';

REGISTER               : [a-h];

// TODO -- make these case insensitive
MNEMONIC               :  'nop'| 'ldr'| 'str'| 'lpt'|
                          'spt'| 'cmp'| 'add'| 'sub'|
                          'mul'| 'div'| 'mod'| 'and'|
                          'or' | 'xor'| 'not'| 'lsl'|
                          'lsr'| 'psh'| 'pop'| 'pek'|
                          'jmp'| 'jsr'| 'rts'| 'joc'|
                          'jsc'| 'rsc'| 'rti'| 'ric';

// we won't be able to detect array names as variable (i.e. arr[0])
// we'll have to come up with a workaround
VARIABLE               : '&'?[A-Za-z_]([A-Za-z_0-9]*|([A-Za-z_0-9.]*[A-Za-z_0-9]));

WHITESPACE             : [ \t\n\r] -> skip;

/* OP_SHORT               : '++' | '--' | [+\-/*<>|&^~][+\-=]; */

COMPARATOR             : '==' | '>' | '<' | '>=' | '<=';

OPERATOR               : '<<' | '>>' | '**' | [+\-/*|&^~];

fragment DEC           : [1-9][0-9_]* | '0';
fragment HEX           : '0x'[0-9A-Fa-f][0-9A-Fa-f_]*;
fragment BIN           : '0b'[0-1][0-1_]*;
fragment FLT           : ([1-9][0-9_]* | '0') '.' ([1-9][0-9_]* | '0');

NUMBER                 : DEC | BIN | HEX | FLT;
