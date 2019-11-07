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
public class IteratorPart extends ASTNode {

    public String Iterator;
    public Identifier Identifier;
    public Expression Exp1;
    public Expression Exp2;
    
    public IteratorPart(String i, Identifier id, Expression e1, Expression e2, int left, int right) {
        super(left, right);
        Iterator=i; 
        Identifier=id; 
        Exp1=e1; 
        Exp2=e2;
    }
}