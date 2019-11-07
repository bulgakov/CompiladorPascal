/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;
import type.ProcType;
import type.Void;
import type.Type;
import visitors.CGVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
public class SubProgramSpecification extends ASTNode {

    public String SubProgram;
    public Identifier Identifier;
    public ParameterList ParameterList;
    public ast.Type ReturnType;
        
    public SubProgramSpecification(String s, Identifier i, ParameterList p, ast.Type r, int left, int right){
        super(left, right);
        this.SubProgram=s;
        this.Identifier=i;
        this.ParameterList=p;
        this.ReturnType=r;
        
        List<Type> params = new ArrayList<Type>();
        for(ParameterDefinition pd : p.Parameters)
            for(Identifier pid : pd.ParameterNameList.Identifiers)
                params.add(pid.type);
        this.Identifier.type = new ProcType(null, this.Identifier.toString(), this.ReturnType == null ? Void.VOID : this.ReturnType.type, params);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}