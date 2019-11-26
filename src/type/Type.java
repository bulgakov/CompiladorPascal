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
public abstract class Type {
    protected int size;
    protected String name;
    
    public int getSize(){
        return size;
    }
    
    public String getName(){
        return name;
    }
    
    public abstract boolean same(Type e);
    
    public abstract boolean assignable(Type e);
    
    @Override
    public String toString() {
        return name;
    }
}
