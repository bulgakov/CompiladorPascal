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

public class ParameterList {

    public List<ParameterDefinition> Parameters;
    
    public ParameterList() { 
        Parameters = new ArrayList<>(); 
    }
}