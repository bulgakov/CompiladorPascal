/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;
import type.BaseType;
import type.ProcType;
import visitors.CGVisitor;
import visitors.SymbolTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijai
 */
public class ProcedureDeclaration extends Declaration {

    public String procedure;
    public Identifier id;
    public ParameterDefinitions ParameterDefinitions;
    public Type ReturnType;
    public Block Block;

    public ProcedureDeclaration(String s, Identifier i, ParameterDefinitions p, Type r, Block b, int left, int right) {
        super(left, right);
        procedure = s;
        id = i;
        ParameterDefinitions = p;
        ReturnType = r;
        Block = b;
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
