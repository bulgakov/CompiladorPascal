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
public class DeclarativePart {

    public List<DeclarativeItem> Items;
    
    public DeclarativePart(){ 
        Items = new ArrayList<>(); 
    }
}
