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

public class ParameterDefinition extends ASTNode {

    public ParameterNameList ParameterNameList;
    public Type Type;
    public Expression Expression;
    
    public ParameterDefinition(ParameterNameList l, Type t, Expression e, int left, int right) { 
        super(left, right);
        this.ParameterNameList=l; 
        this.Type=t; 
        this.Expression=e;
        this.type = t.type;
        for(Identifier id : ParameterNameList.Identifiers)
            id.type = t.type;
    }
    
    public void accept(GlobalTableVisitor v) { 
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