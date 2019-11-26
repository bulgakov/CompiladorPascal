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
public class TypeDefinition extends ASTNode {

    public Identifier id;
    public FieldList Fields;
    
    public TypeDefinition(Identifier i, FieldList fl, int left, int right) {
        super(left, right);
        id=i;
        Fields=fl;
    }
}
