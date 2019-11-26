/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import visitors.CGVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
public class ForStatement extends Statement {

    public Identifier id;
    public Expression Exp1;
    public Expression Exp2;
    public Statement Statement;

    public ForStatement(Identifier i, Expression e1, Expression e2, Statement s, int left, int right) {
        super(left, right);
        id = i;
        Exp1 = e1;
        Exp2 = e2;
        Statement = s;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void accept(TypeVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(CGVisitor v) {
        v.visit(this);
    }
}
