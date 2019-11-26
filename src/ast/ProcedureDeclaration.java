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
import visitors.GlobalTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijai
 */
public class ProcedureDeclaration extends Declaration {
    
    public String SubProgram;
    public Identifier id;
    public ParameterDefinitions ParameterDefinitions;
    public Type ReturnType;
    public Block Block;
    
    
    public ProcedureDeclaration(String s, Identifier i, ParameterDefinitions p, Type r, Block b,int left, int right){
        super(left, right);
        SubProgram=s;
        id=i;
        ParameterDefinitions=p;
        ReturnType=r;
        Block=b;
        
        List<type.Type> params = new ArrayList<>();
        for(ParameterDefinition pd : p.Parameters)
            params.add(pd.Type.type);
        type = new ProcType(id.id, ReturnType == null ? BaseType.VOID : this.ReturnType.type, params, null);
        id.type = type;
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
