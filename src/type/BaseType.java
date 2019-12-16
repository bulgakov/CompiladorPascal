/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

/**
 *
 * @author mijail
 */
public class BaseType extends Type {

    public static final BaseType INTEGER = new BaseType("integer", 4);
    public static final BaseType BOOLEAN = new BaseType("boolean", 4);
    public static final BaseType FLOAT = new BaseType("float", 4);
    public static final BaseType CHAR = new BaseType("char", 4);
    public static final BaseType STRING = new BaseType("string", 4);
    public static final BaseType VOID = new BaseType("void");
    public static final BaseType UNDEFINED = new BaseType("undefined");

    private BaseType(String n) {
        name = n;
        size = 0;
    }

    private BaseType(String n, int s) {
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
