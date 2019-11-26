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
public class Block extends ASTNode {

    public Declarations Declarations;
    public Statements Statements;
    
    public Block(Declarations dl, Statements sl, int left, int right) {
        super(left,right);
        Declarations=dl; 
        Statements=sl;
    }
}