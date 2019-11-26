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
public abstract class Statement extends ASTNode {

    public Statement(int left, int right) {
        super(left, right);
    }

    public abstract void accept(Visitor v);

    public abstract void accept(TypeVisitor v);

    public abstract void accept(CGVisitor v);
}
