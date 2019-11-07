/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

/**
 *
 * @author mijail
 */
public class ErrorMsg {
    private int left;
    private int right;
    private String message;
    
    public ErrorMsg(int left, int right, String message) {
        this.left = left;
        this.right = right;
        this.message = message;
    }
    
    @Override
    public String toString() { 
        StringBuilder strb = new StringBuilder();
        strb.append("Error at ");
        strb.append(left);
        strb.append(" , ");
        strb.append(right);
        strb.append(" : ");
        strb.append(message);
        return strb.toString();
    }
}
