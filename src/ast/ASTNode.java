/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import type.Type;

/**
 *
 * @author mijail
 */
public abstract class ASTNode {

    protected int line;
    protected int column;
    protected int depth;

    public Type type;

    public String Lugar;
    public String Verdadera;
    public String Falsa;
    public String Comienzo;
    public String Siguiente;

    private ASTNode() {
        this(0, 0);
    }

    public ASTNode(int line, int column) {
        this.line = line;
        this.column = column;
        depth = 0;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getDepth() {
        return depth;
    }
}
