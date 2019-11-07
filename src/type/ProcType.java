/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

import table.SymbolTable;
import java.util.List;

/**
 *
 * @author mijail
 */
public class ProcType extends Type {

    public String name;
    public Type returnType;
    public List<Type> params;
    public SymbolTable thisType;
    
    public ProcType(SymbolTable table, String name, Type returnType, List<Type> params) { 
        this.thisType = table;
        this.name = name;
        this.returnType = returnType;
        this.params = params;
    }
    
    public void addParameter(Type t) { 
        params.add(t);
    }
    
    @Override
    public boolean same(Type e) {
        if (!(e instanceof ProcType))
            return false;
        
        ProcType p = (ProcType) e;
        if (params.size() != params.size()) 
            return false;
        
        for (int i = 0; i < params.size(); i++) 
            if (!params.get(i).same(p.params.get(i)))
                return false;
        
        return returnType.same(p.returnType);
    }

    @Override
    public boolean assignable(Type e) {
        if (!(e instanceof ProcType))
            return false;
        
        ProcType p = (ProcType) e;
        if (params.size() != params.size()) 
            return false;
        
        for (int i = 0; i < params.size(); i++) 
            if (!params.get(i).same(p.params.get(i)))
                return false;
        
        return returnType.assignable(p.returnType);
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("(");
        strb.append(returnType);
        strb.append(" -> ");
        strb.append("(");
        for(Type t : params)
            strb.append(t);
        strb.append(")");        
        strb.append(")");
        return strb.toString();
    }
    
    public int paramsCount() {
        return params.size();
    }
}
