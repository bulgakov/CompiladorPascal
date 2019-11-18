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
public class IdentifierExpression extends Expression {

    public Identifier id;
    public Identifier field;

    public IdentifierExpression(Identifier i, int left, int right) {
        super(left, right);
        id=i;
        field=null;
    }
    
    public IdentifierExpression(Identifier i, Identifier f, int left, int right) {
        super(left, right);
        id=i;
        field=f;
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