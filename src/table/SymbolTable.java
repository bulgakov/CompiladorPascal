/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.*;
import type.Undefined;

/**
 *
 * @author mijail
 */
public class SymbolTable {
    public String name;
    
    public Map<String, SymbolInfo> table;
    public Map<String, SymbolTable> scopes;
    
    public SymbolTable parent;
    
    private int depth;
    
    public boolean updated;
    
    public SymbolTable(SymbolTable parent) { 
        table = new HashMap<String, SymbolInfo>();
        scopes = new HashMap<String, SymbolTable>();
        this.parent = parent;
        updated = false;
        this.depth = (parent == null) ? 0 : parent.depth + 1;
    }
    
    public void put(String s, SymbolInfo i) throws Exception { 
        if (table.get(s) != null)
            throw new Exception(s + "is already declared.");
        table.put(s, i);
    }
    
    public void put(String s, SymbolTable t) throws Exception { 
        if (scopes.get(s) != null)
            throw new Exception(s + "is already declared.");
        scopes.put(s, t);
    }
    
    public SymbolInfo get(String s) { 
        for (SymbolTable t = this; t != null; t = t.parent) {
            SymbolInfo i = t.table.get(s);
            if (i != null) 
                return i;
        }
        
        SymbolInfo undef = new SymbolInfo(s, null, Undefined.UNDEFINED);
        table.put(s, undef);
        return undef;
    }
    
    public int getDepth() { 
        return depth;
    }
    
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        if (depth == 0)
            strb.append("Global:").append(System.lineSeparator());
        for (String s : table.keySet()) {
            strb.append(indent(depth + 1))
                    .append(s)
                    .append("-> SymbolInfo=[")
                    .append(table.get(s))
                    .append("]").append(System.lineSeparator());
            SymbolTable child = scopes.get(s);
            if (child != null) {
                strb.append(child).append(System.lineSeparator());
            }
        }
        return strb.toString();
    }
    
    private String indent(int n) { 
        return n < 0 ? "" : String.join("", Collections.nCopies(n, "\t"));
    }
}
