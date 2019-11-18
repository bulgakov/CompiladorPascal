/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author mijail
 */
public class Field extends ASTNode { 
    
    public Identifier id;
    public Type Type;
    
    public Field(Identifier i, Type t, int left, int right) {
        super(left, right);
        id=i;
        Type=t;
    }
}