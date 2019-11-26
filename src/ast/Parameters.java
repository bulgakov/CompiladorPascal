/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.*;

/**
 *
 * @author mijail
 */

public class Parameters {

    public List<Expression> Parameters;
    
    public Parameters() { 
        Parameters = new ArrayList<>(); 
    }
    
    public void add(Expression e){
        Parameters.add(0, e);
    }
}