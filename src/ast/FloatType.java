/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlRootElement;
import type.BaseType;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
public class FloatType extends Type { 
    
    private FloatType() {
        this(0, 0);
    }
    
    public FloatType(int left, int right) { 
        super(left, right);
        type = BaseType.FLOAT;
        super.type = type;
    } 
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}