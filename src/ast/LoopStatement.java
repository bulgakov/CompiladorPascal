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
public class LoopStatement extends Statement {

    public IteratorPart Iterator;
    public Statements Statements;
    
    public LoopStatement(IteratorPart i, Statements s, int left, int right) { 
        super(left, right);
        Iterator=i; 
        Statements=s; 
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