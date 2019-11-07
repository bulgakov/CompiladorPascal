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
public interface CGVisitor {
    public void visit(Program n);
    public void visit(SubProgramBody n);
    public void visit(ParameterDefinition n);
    public void visit(ObjectDeclarativeItem n);
    public void visit(NullStatement n);
    public void visit(AssignStatement n);
    public void visit(ReturnStatement n);
    public void visit(ExitStatement n);
    public void visit(CallStatement n);
    public void visit(IfStatement n);
    public void visit(LoopStatement n);
    public void visit(ReadStatement n);
    public void visit(WriteStatement n);
    public void visit(False n);
    public void visit(True n);
    public void visit(LogicalExpression n);
    public void visit(RelationExpression n);
    public void visit(AritmeticExpression n);
    public void visit(NotExpression n);
    public void visit(UnaryExpression n);
    public void visit(CallExpression n);
    public void visit(StringLiteral n);
    public void visit(FloatLiteral n);
    public void visit(IntegerLiteral n);
    public void visit(IdentifierExpression n);
    public void visit(UndefinedExpression n);
}
