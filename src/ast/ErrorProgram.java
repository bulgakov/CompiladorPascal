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
public class ErrorProgram extends Program {

    public ErrorProgram(int left, int right) {
        super(null, null, left, right);
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
