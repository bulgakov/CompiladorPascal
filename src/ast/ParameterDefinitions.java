/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mijai
 */
public class ParameterDefinitions {

    public List<ParameterDefinition> Parameters;

    public ParameterDefinitions() {
        Parameters = new ArrayList<>();
    }

    public void add(ParameterDefinition t) {
        Parameters.add(0, t);
    }

    public void add(ParameterDefinitions pl) {
        int index = 0;
        for (ParameterDefinition t : pl.Parameters) {
            Parameters.add(index, t);
            index++;
        }
    }

    public void add(IdentifierList il, Type t) {
        for (Identifier id : il.Identifiers) {
            Parameters.add(new ParameterDefinition(id, t, id.getLine(), id.getColumn()));
        }
    }
}
