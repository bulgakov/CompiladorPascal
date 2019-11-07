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
    
    public static final BaseType INTEGER = new BaseType("integer");
    public static final BaseType BOOLEAN = new BaseType("boolean");
    public static final BaseType FLOAT = new BaseType("float");
    public static final BaseType STRING = new BaseType("string");
    
    private String rep;
    
    private BaseType() {
        this(null);
    }
    
    public BaseType(String rep) {
        this.rep = rep;
    }
    
    @Override
    public boolean same(Type e) {
        return e == this;
    }

    @Override
    public boolean assignable(Type e) {
        return e == this;
    }

    @Override
    public String toString() {
        return this.rep;
    }
    
}
