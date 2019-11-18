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
public class TypeDefinitions {
    public List<TypeDefinition> Types;

    public TypeDefinitions() {
        Types = new ArrayList<>();
    }

    public void add(TypeDefinition t) {
        Types.add(t);
    }
}
