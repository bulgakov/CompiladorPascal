/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import visitors.CGVisitor;
import visitors.GlobalTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */

public class ObjectDeclarativeItem extends DeclarativeItem {

    public IdentifierList IdentifierList;
    public Type Type;
    public Expression Expression;
    
    public ObjectDeclarativeItem(IdentifierList i, Type t, Expression e, int left, int right){
        super(0,0);
        this.IdentifierList=i; 
        this.Type=t; 
        this.Expression=e;
        this.type = Type.type;
        for (Identifier id : IdentifierList.Identifiers) 
            id.type = Type.type;
    }
    
    @Override
    public void accept(GlobalTableVisitor v) { 
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