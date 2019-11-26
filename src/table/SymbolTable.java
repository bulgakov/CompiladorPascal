/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.*;
import type.BaseType;

/**
 *
 * @author mijail
 */
public class SymbolTable {

    public String name;

    private Map<String, SymbolInfo> table;
    private Map<String, SymbolType> types;
    private Map<String, SymbolTable> scopes;

    public SymbolTable parent;

    private int depth;

    public boolean updated;

    public SymbolTable(SymbolTable parent) {
        table = new HashMap<>();
        scopes = new HashMap<>();
        this.parent = parent;
        updated = false;
        this.depth = (parent == null) ? 0 : parent.depth + 1;
    }

    public void put(String s, SymbolInfo i) throws Exception {
        if (table.get(s) != null) {
            throw new Exception(s + "is already declared.");
        }
        table.put(s, i);
    }

    public void put(String s, SymbolType t) throws Exception {
        if (types.get(s) != null) {
            throw new Exception(s + "is already declared.");
        }
        types.put(s, t);
    }

    public void put(String s, SymbolTable t) throws Exception {
        if (scopes.get(s) != null) {
            throw new Exception(s + "is already declared.");
        }
        scopes.put(s, t);
    }

    public SymbolInfo get(String s) {
        for (SymbolTable t = this; t != null; t = t.parent) {
            SymbolInfo i = t.table.get(s);
            if (i != null) {
                return i;
            }
        }

        SymbolInfo undef = new SymbolInfo(s, null, BaseType.UNDEFINED);
        table.put(s, undef);
        return undef;
    }

    public SymbolType getType(String s) {
        for (SymbolTable t = this; t != null; t = t.parent) {
            SymbolType i = t.types.get(s);
            if (i != null) {
                return i;
            }
        }

        SymbolType undef = new SymbolType(s, null, BaseType.UNDEFINED);
        types.put(s, undef);
        return undef;
    }

    public SymbolTable getScope(String s) {
        return scopes.get(s);
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        if (depth == 0) {
            strb.append("Global:").append(System.lineSeparator());
        }
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
