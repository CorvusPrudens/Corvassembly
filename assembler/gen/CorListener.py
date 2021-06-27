# Generated from Cor.g4 by ANTLR 4.9.1
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .CorParser import CorParser
else:
    from CorParser import CorParser

# This class defines a complete listener for a parse tree produced by CorParser.
class CorListener(ParseTreeListener):

    # Enter a parse tree produced by CorParser#parse.
    def enterParse(self, ctx:CorParser.ParseContext):
        pass

    # Exit a parse tree produced by CorParser#parse.
    def exitParse(self, ctx:CorParser.ParseContext):
        pass


    # Enter a parse tree produced by CorParser#block.
    def enterBlock(self, ctx:CorParser.BlockContext):
        pass

    # Exit a parse tree produced by CorParser#block.
    def exitBlock(self, ctx:CorParser.BlockContext):
        pass


    # Enter a parse tree produced by CorParser#file_import.
    def enterFile_import(self, ctx:CorParser.File_importContext):
        pass

    # Exit a parse tree produced by CorParser#file_import.
    def exitFile_import(self, ctx:CorParser.File_importContext):
        pass


    # Enter a parse tree produced by CorParser#initial.
    def enterInitial(self, ctx:CorParser.InitialContext):
        pass

    # Exit a parse tree produced by CorParser#initial.
    def exitInitial(self, ctx:CorParser.InitialContext):
        pass


    # Enter a parse tree produced by CorParser#directive.
    def enterDirective(self, ctx:CorParser.DirectiveContext):
        pass

    # Exit a parse tree produced by CorParser#directive.
    def exitDirective(self, ctx:CorParser.DirectiveContext):
        pass


    # Enter a parse tree produced by CorParser#statement.
    def enterStatement(self, ctx:CorParser.StatementContext):
        pass

    # Exit a parse tree produced by CorParser#statement.
    def exitStatement(self, ctx:CorParser.StatementContext):
        pass


    # Enter a parse tree produced by CorParser#statement_loop.
    def enterStatement_loop(self, ctx:CorParser.Statement_loopContext):
        pass

    # Exit a parse tree produced by CorParser#statement_loop.
    def exitStatement_loop(self, ctx:CorParser.Statement_loopContext):
        pass


    # Enter a parse tree produced by CorParser#statement_if.
    def enterStatement_if(self, ctx:CorParser.Statement_ifContext):
        pass

    # Exit a parse tree produced by CorParser#statement_if.
    def exitStatement_if(self, ctx:CorParser.Statement_ifContext):
        pass


    # Enter a parse tree produced by CorParser#cond_block.
    def enterCond_block(self, ctx:CorParser.Cond_blockContext):
        pass

    # Exit a parse tree produced by CorParser#cond_block.
    def exitCond_block(self, ctx:CorParser.Cond_blockContext):
        pass


    # Enter a parse tree produced by CorParser#loop.
    def enterLoop(self, ctx:CorParser.LoopContext):
        pass

    # Exit a parse tree produced by CorParser#loop.
    def exitLoop(self, ctx:CorParser.LoopContext):
        pass


    # Enter a parse tree produced by CorParser#loop_keyword.
    def enterLoop_keyword(self, ctx:CorParser.Loop_keywordContext):
        pass

    # Exit a parse tree produced by CorParser#loop_keyword.
    def exitLoop_keyword(self, ctx:CorParser.Loop_keywordContext):
        pass


    # Enter a parse tree produced by CorParser#conditional.
    def enterConditional(self, ctx:CorParser.ConditionalContext):
        pass

    # Exit a parse tree produced by CorParser#conditional.
    def exitConditional(self, ctx:CorParser.ConditionalContext):
        pass


    # Enter a parse tree produced by CorParser#if_stat.
    def enterIf_stat(self, ctx:CorParser.If_statContext):
        pass

    # Exit a parse tree produced by CorParser#if_stat.
    def exitIf_stat(self, ctx:CorParser.If_statContext):
        pass


    # Enter a parse tree produced by CorParser#elif_stat.
    def enterElif_stat(self, ctx:CorParser.Elif_statContext):
        pass

    # Exit a parse tree produced by CorParser#elif_stat.
    def exitElif_stat(self, ctx:CorParser.Elif_statContext):
        pass


    # Enter a parse tree produced by CorParser#else_stat.
    def enterElse_stat(self, ctx:CorParser.Else_statContext):
        pass

    # Exit a parse tree produced by CorParser#else_stat.
    def exitElse_stat(self, ctx:CorParser.Else_statContext):
        pass


    # Enter a parse tree produced by CorParser#if_chain.
    def enterIf_chain(self, ctx:CorParser.If_chainContext):
        pass

    # Exit a parse tree produced by CorParser#if_chain.
    def exitIf_chain(self, ctx:CorParser.If_chainContext):
        pass


    # Enter a parse tree produced by CorParser#assignment.
    def enterAssignment(self, ctx:CorParser.AssignmentContext):
        pass

    # Exit a parse tree produced by CorParser#assignment.
    def exitAssignment(self, ctx:CorParser.AssignmentContext):
        pass


    # Enter a parse tree produced by CorParser#assignment_arr.
    def enterAssignment_arr(self, ctx:CorParser.Assignment_arrContext):
        pass

    # Exit a parse tree produced by CorParser#assignment_arr.
    def exitAssignment_arr(self, ctx:CorParser.Assignment_arrContext):
        pass


    # Enter a parse tree produced by CorParser#declaration.
    def enterDeclaration(self, ctx:CorParser.DeclarationContext):
        pass

    # Exit a parse tree produced by CorParser#declaration.
    def exitDeclaration(self, ctx:CorParser.DeclarationContext):
        pass


    # Enter a parse tree produced by CorParser#assignment_ram.
    def enterAssignment_ram(self, ctx:CorParser.Assignment_ramContext):
        pass

    # Exit a parse tree produced by CorParser#assignment_ram.
    def exitAssignment_ram(self, ctx:CorParser.Assignment_ramContext):
        pass


    # Enter a parse tree produced by CorParser#assignment_arr_ram.
    def enterAssignment_arr_ram(self, ctx:CorParser.Assignment_arr_ramContext):
        pass

    # Exit a parse tree produced by CorParser#assignment_arr_ram.
    def exitAssignment_arr_ram(self, ctx:CorParser.Assignment_arr_ramContext):
        pass


    # Enter a parse tree produced by CorParser#expression.
    def enterExpression(self, ctx:CorParser.ExpressionContext):
        pass

    # Exit a parse tree produced by CorParser#expression.
    def exitExpression(self, ctx:CorParser.ExpressionContext):
        pass


    # Enter a parse tree produced by CorParser#math_obj.
    def enterMath_obj(self, ctx:CorParser.Math_objContext):
        pass

    # Exit a parse tree produced by CorParser#math_obj.
    def exitMath_obj(self, ctx:CorParser.Math_objContext):
        pass


    # Enter a parse tree produced by CorParser#math.
    def enterMath(self, ctx:CorParser.MathContext):
        pass

    # Exit a parse tree produced by CorParser#math.
    def exitMath(self, ctx:CorParser.MathContext):
        pass


    # Enter a parse tree produced by CorParser#exp_number.
    def enterExp_number(self, ctx:CorParser.Exp_numberContext):
        pass

    # Exit a parse tree produced by CorParser#exp_number.
    def exitExp_number(self, ctx:CorParser.Exp_numberContext):
        pass


    # Enter a parse tree produced by CorParser#array.
    def enterArray(self, ctx:CorParser.ArrayContext):
        pass

    # Exit a parse tree produced by CorParser#array.
    def exitArray(self, ctx:CorParser.ArrayContext):
        pass


    # Enter a parse tree produced by CorParser#exp_var.
    def enterExp_var(self, ctx:CorParser.Exp_varContext):
        pass

    # Exit a parse tree produced by CorParser#exp_var.
    def exitExp_var(self, ctx:CorParser.Exp_varContext):
        pass


    # Enter a parse tree produced by CorParser#exp_char.
    def enterExp_char(self, ctx:CorParser.Exp_charContext):
        pass

    # Exit a parse tree produced by CorParser#exp_char.
    def exitExp_char(self, ctx:CorParser.Exp_charContext):
        pass


    # Enter a parse tree produced by CorParser#exp_attr.
    def enterExp_attr(self, ctx:CorParser.Exp_attrContext):
        pass

    # Exit a parse tree produced by CorParser#exp_attr.
    def exitExp_attr(self, ctx:CorParser.Exp_attrContext):
        pass


    # Enter a parse tree produced by CorParser#array_init.
    def enterArray_init(self, ctx:CorParser.Array_initContext):
        pass

    # Exit a parse tree produced by CorParser#array_init.
    def exitArray_init(self, ctx:CorParser.Array_initContext):
        pass


    # Enter a parse tree produced by CorParser#string.
    def enterString(self, ctx:CorParser.StringContext):
        pass

    # Exit a parse tree produced by CorParser#string.
    def exitString(self, ctx:CorParser.StringContext):
        pass


    # Enter a parse tree produced by CorParser#arr_data.
    def enterArr_data(self, ctx:CorParser.Arr_dataContext):
        pass

    # Exit a parse tree produced by CorParser#arr_data.
    def exitArr_data(self, ctx:CorParser.Arr_dataContext):
        pass


    # Enter a parse tree produced by CorParser#instruction.
    def enterInstruction(self, ctx:CorParser.InstructionContext):
        pass

    # Exit a parse tree produced by CorParser#instruction.
    def exitInstruction(self, ctx:CorParser.InstructionContext):
        pass


    # Enter a parse tree produced by CorParser#argument.
    def enterArgument(self, ctx:CorParser.ArgumentContext):
        pass

    # Exit a parse tree produced by CorParser#argument.
    def exitArgument(self, ctx:CorParser.ArgumentContext):
        pass


    # Enter a parse tree produced by CorParser#register.
    def enterRegister(self, ctx:CorParser.RegisterContext):
        pass

    # Exit a parse tree produced by CorParser#register.
    def exitRegister(self, ctx:CorParser.RegisterContext):
        pass


    # Enter a parse tree produced by CorParser#label.
    def enterLabel(self, ctx:CorParser.LabelContext):
        pass

    # Exit a parse tree produced by CorParser#label.
    def exitLabel(self, ctx:CorParser.LabelContext):
        pass



del CorParser