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
public class TAddressCode {

    String Instr;
    String Rdest;
    String Rscr;
    String Rscr2;

    public TAddressCode(String i, String d, String s1, String s2) {
        this.Instr = i;
        this.Rdest = d;
        this.Rscr = s1;
        this.Rscr2 = s2;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(this.Instr);
        if (this.Rdest != null) {
            strb.append(" ");
            strb.append(this.Rdest);
        }
        if (this.Rscr != null) {
            strb.append(" ");
            strb.append(this.Rscr);
        }
        if (this.Rscr2 != null) {
            strb.append(" ");
            strb.append(this.Rscr2);
        }
        return strb.toString();
    }
}
