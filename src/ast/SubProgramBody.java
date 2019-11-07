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
public class SubProgramBody extends DeclarativeItem {

    public SubProgramSpecification SubProgramSpecification;
    public DeclarativePart DeclarativePart;
    public Statements Statements;
    public Identifier OptIdentifier;
    
    public SubProgramBody(SubProgramSpecification s, DeclarativePart d, Statements st, Identifier oi, int left, int right) {
        super(left,right);
        SubProgramSpecification=s; 
        DeclarativePart=d; 
        Statements=st;
        OptIdentifier=oi;
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