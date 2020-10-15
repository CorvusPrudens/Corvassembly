// Generated from Cor.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CorParser}.
 */
public interface CorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CorParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(CorParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(CorParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CorParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CorParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#file_import}.
	 * @param ctx the parse tree
	 */
	void enterFile_import(CorParser.File_importContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#file_import}.
	 * @param ctx the parse tree
	 */
	void exitFile_import(CorParser.File_importContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#initial}.
	 * @param ctx the parse tree
	 */
	void enterInitial(CorParser.InitialContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#initial}.
	 * @param ctx the parse tree
	 */
	void exitInitial(CorParser.InitialContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CorParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CorParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#statement_loop}.
	 * @param ctx the parse tree
	 */
	void enterStatement_loop(CorParser.Statement_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#statement_loop}.
	 * @param ctx the parse tree
	 */
	void exitStatement_loop(CorParser.Statement_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(CorParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(CorParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#loop_keyword}.
	 * @param ctx the parse tree
	 */
	void enterLoop_keyword(CorParser.Loop_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#loop_keyword}.
	 * @param ctx the parse tree
	 */
	void exitLoop_keyword(CorParser.Loop_keywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CorParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CorParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#assignment_arr}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_arr(CorParser.Assignment_arrContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#assignment_arr}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_arr(CorParser.Assignment_arrContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(CorParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(CorParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#math}.
	 * @param ctx the parse tree
	 */
	void enterMath(CorParser.MathContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#math}.
	 * @param ctx the parse tree
	 */
	void exitMath(CorParser.MathContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#exp_number}.
	 * @param ctx the parse tree
	 */
	void enterExp_number(CorParser.Exp_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#exp_number}.
	 * @param ctx the parse tree
	 */
	void exitExp_number(CorParser.Exp_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#exp_var}.
	 * @param ctx the parse tree
	 */
	void enterExp_var(CorParser.Exp_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#exp_var}.
	 * @param ctx the parse tree
	 */
	void exitExp_var(CorParser.Exp_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(CorParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(CorParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(CorParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(CorParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#array_init}.
	 * @param ctx the parse tree
	 */
	void enterArray_init(CorParser.Array_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#array_init}.
	 * @param ctx the parse tree
	 */
	void exitArray_init(CorParser.Array_initContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#arr_data}.
	 * @param ctx the parse tree
	 */
	void enterArr_data(CorParser.Arr_dataContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#arr_data}.
	 * @param ctx the parse tree
	 */
	void exitArr_data(CorParser.Arr_dataContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(CorParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(CorParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(CorParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(CorParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#register}.
	 * @param ctx the parse tree
	 */
	void enterRegister(CorParser.RegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#register}.
	 * @param ctx the parse tree
	 */
	void exitRegister(CorParser.RegisterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CorParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(CorParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CorParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(CorParser.LabelContext ctx);
}