/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.*;

/**
 *
 * @author mijail
 */
public interface TypeVisitor {
    public void visit(Program n);
    public void visit(IdentifierList n);
    public void visit(Block n);
    public void visit(Declarations n);
    public void visit(TypeDeclaration n);
    public void visit(TypeDefinitions n);
    public void visit(TypeDefinition n);
    public void visit(FieldList n);
    public void visit(VariableDeclaration n);
    public void visit(VariableDefinitions n);
    public void visit(VariableDefinition n);
    public void visit(ProcedureDeclaration n);
    public void visit(ParameterDefinitions n);
    public void visit(ParameterDefinition n);
    public void visit(Statements n);
    public void visit(WriteStatement n);
    public void visit(ReadStatement n);
    public void visit(AssignStatement n);
    public void visit(ProcedureStatement n);
    public void visit(IfStatement n);
    public void visit(WhileStatement n);
    public void visit(RepeatStatement n);
    public void visit(ForStatement n);
    public void visit(Parameters n);
    public void visit(LogicalExpression n);
    public void visit(RelationExpression n);
    public void visit(AritmeticExpression n);
    public void visit(NotExpression n);
    public void visit(UnaryExpression n);
    public void visit(IdentifierExpression n);
    public void visit(CallExpression n);
    public void visit(StringLiteral n);
    public void visit(IntegerLiteral n);
    public void visit(True n);
    public void visit(False n);
    public void visit(IdentifierType n);
    public void visit(BooleanType n);
    public void visit(IntegerType n);
    public void visit(CharType n);
    public void visit(ErrorProgram n);
    public void visit(ErrorDeclaration n);
    public void visit(ErrorStatement n);
    public void visit(ErrorExpression n);
}
