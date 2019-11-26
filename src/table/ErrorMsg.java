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

    private int line;
    private int column;
    private String message;

    public ErrorMsg(int l, int c, String message) {
        this.line = l;
        this.column = c;
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("Error at ");
        strb.append(line);
        strb.append(" , ");
        strb.append(column);
        strb.append(" : ");
        strb.append(message);
        return strb.toString();
    }
}
