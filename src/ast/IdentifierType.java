/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import type.DefinedType;
import visitors.CGVisitor;
import visitors.SymbolTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
public class IdentifierType extends Type {

    public String id;

    public IdentifierType(String i, int left, int right) {
        super(left, right);
        id = i;
        type = new DefinedType(i);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public void accept(SymbolTableVisitor v) {
        v.visit(this);
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
