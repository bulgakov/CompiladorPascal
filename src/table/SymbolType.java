/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.Map;
import type.BaseType;
import type.Type;

/**
 *
 * @author mijai
 */
public class SymbolType {

    private String id;
    private String owner;

    private Type type;
    private int offset;

    private Map<String, SymbolInfo> fields;

    public SymbolType(String id, String owner, Type type) {
        this.id = id;
        this.owner = owner;
        this.type = type;
        this.offset = 0;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int v) {
        offset = v;
    }

    public void put(String s, SymbolInfo i) throws Exception {
        if (fields.get(s) != null) {
            throw new Exception(s + "is already declared.");
        }
        fields.put(s, i);
    }

    public SymbolInfo get(String s) {
        SymbolInfo i = fields.get(s);
        if (i != null) {
            return i;
        }

        SymbolInfo undef = new SymbolInfo(s, null, BaseType.UNDEFINED);
        fields.put(s, undef);
        return undef;
    }

    @Override
    public String toString() {
        return "id= " + id + "|type " + type
                + "|belonging= " + owner
                + "|offset= " + offset;
    }
}
