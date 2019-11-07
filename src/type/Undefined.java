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
public class Undefined extends Type {

    public static final Undefined UNDEFINED = new Undefined("undefined");
    
    private String rep;
    
    private Undefined(String rep) { 
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
        return rep;
    }
    
}
