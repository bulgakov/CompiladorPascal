/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author mijai
 */
public class Identifier extends ASTNode {

    public String id;

    public Identifier(String i, int left, int right) {
        super(left, right);
        id = i;
    }

    @Override
    public String toString() {
        return id;
    }
}
