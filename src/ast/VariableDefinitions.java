/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mijai
 */
public class VariableDefinitions {
    public List<VariableDefinition> Variables;

    public VariableDefinitions() {
        Variables = new ArrayList<>();
    }

    public void add(VariableDefinition t) {
        Variables.add(0, t);
    }
    
    public void add(VariableDefinitions vl) {
        int index = 0;
        for (VariableDefinition t : vl.Variables) {
            Variables.add(index, t);
            index++;
        }
    }
    
    public void add(IdentifierList il, Type t) {
        int index = 0;
        for (Identifier id : il.Identifiers) {
            Variables.add(index, new VariableDefinition(id, t, id.getLine(), id.getColumn()));
            index++;
        }
    }
}
