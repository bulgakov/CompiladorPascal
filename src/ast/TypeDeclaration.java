/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;
import visitors.CGVisitor;
import visitors.GlobalTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
public class TypeDeclaration extends Declaration {

    public TypeDefinitions TypeDefinitions;
    
    public TypeDeclaration(TypeDefinitions t, int left, int right) { 
        super(left, right);
        TypeDefinitions = t;
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
