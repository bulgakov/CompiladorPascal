/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.ArrayList;
import type.Type;

/**
 *
 * @author mijail
 */
public class SymbolInfo {

    private String id;
    private String owner;

    private Type type;
    private int offset;
    private boolean global;
    private boolean param;

    private String descriptor;

    public SymbolInfo(String id, String owner, Type type) {
        this(id, owner, type, false);
    }

    public SymbolInfo(String id, String owner, Type type, boolean param) {
        this.id = id;
        this.owner = owner;
        this.type = type;
        this.offset = 0;
        this.param = param;
        this.descriptor = "";
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
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

    public void setGlobal(boolean v) {
        global = v;
    }

    public boolean isGlobal() {
        return global;
    }

    public boolean isParam() {
        return param;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String d) {
        descriptor = d;
    }

    @Override
    public String toString() {
        return "id= " + id + "|type " + type
                + "|belonging= " + owner
                + "|offset= " + offset;
    }
}
