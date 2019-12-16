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
        strb.append(this.getInstr());
        if (this.getRdest() != null) {
            strb.append(" ");
            strb.append(this.getRdest());
        }
        if (this.getRscr() != null) {
            strb.append(" ");
            strb.append(this.getRscr());
        }
        if (this.getRscr2() != null) {
            strb.append(" ");
            strb.append(this.getRscr2());
        }
        return strb.toString();
    }

    public String getInstr() {
        return Instr;
    }

    public String getRdest() {
        return Rdest;
    }

    public String getRscr() {
        return Rscr;
    }

    public String getRscr2() {
        return Rscr2;
    }
}
