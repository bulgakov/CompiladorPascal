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
 * @author mijail
 */
public class FieldList {

    public List<Field> Fields;

    public FieldList() {
        Fields = new ArrayList<>();
    }

    public void add(Field f) {
        Fields.add(0, f);
    }

    public void add(IdentifierList il, Type t) {
        int index = 0;
        for (Identifier id : il.Identifiers) {
            Fields.add(index, new Field(id, t, id.getLine(), id.getColumn()));
            index++;
        }
    }
}
