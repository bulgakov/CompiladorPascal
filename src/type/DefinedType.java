/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

/**
 *
 * @author mijai
 */
public class DefinedType extends Type {

    public DefinedType(String n) {
        name = n;
        size = 0;
    }

    private DefinedType(String n, int s) {
        name = n;
        size = s;
    }

    @Override
    public boolean same(Type e) {
        return e.name.equalsIgnoreCase(this.name);
    }

    @Override
    public boolean assignable(Type e) {
        return e.name.equalsIgnoreCase(this.name);
    }
}
