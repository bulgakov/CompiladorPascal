/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import visitors.CGVisitor;
import visitors.SymbolTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
public class ParameterDefinition extends ASTNode {

    public Identifier id;
    public Type Type;

    public ParameterDefinition(Identifier i, Type t, int left, int right) {
        super(left, right);
        id = i;
        Type = t;
        type = t.type;
    }

    public void accept(SymbolTableVisitor v) {
        v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void accept(TypeVisitor v) {
        v.visit(this);
    }

    public void accept(CGVisitor v) {
        v.visit(this);
    }
}
