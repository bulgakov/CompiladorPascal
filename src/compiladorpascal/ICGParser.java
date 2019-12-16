/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorpascal;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import table.*;
import type.*;

/**
 *
 * @author mijail
 */
public class ICGParser {

    private SymbolTable global;
    private SymbolTable top;
    private SymbolTable temps;
    private List<TAddressCode> pseudocode;
    private Map<String, String> texts;
    private List<String> mipscode;

    private int offset;

    private Map<String, String> ts;
    private Map<String, String> ss;
    private Map<String, String> as;

    public ICGParser(SymbolTable global, SymbolTable temps, List<TAddressCode> pscode, Map<String, String> txts) {
        this.global = global;
        this.top = global;
        this.temps = temps;
        this.pseudocode = pscode;
        this.texts = txts;
        this.mipscode = new ArrayList<>();

        this.offset = 0;

        this.ts = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            this.ts.put("$t" + i, null);
        }

        this.ss = new LinkedHashMap<>();
        for (int i = 0; i < 8; i++) {
            this.ss.put("$s" + i, null);
        }

        this.as = new LinkedHashMap<>();
        for (int i = 0; i < 4; i++) {
            this.as.put("$a" + i, null);
        }
    }

    private SymbolInfo getSymbol(String v) {
        if (v.matches("^\\d+$")) {
            return null;
        }

        if (v.startsWith("'")) {
            return null;
        }

        if (v.equals("RET")) {
            return null;
        }

        if (v.matches("^t\\d+$")) {
            return temps.get(v);
        } else {
            return top.get(v);
        }
    }

    private String getRegistry(String v) {
        if (v.equals("RET")) {
            return "$v0";
        }

        SymbolInfo info = getSymbol(v);
        if (info == null) {
            if (v.matches("^\\d+$")) {
                for (Map.Entry<String, String> t : ts.entrySet()) {
                    if (t.getValue() == null) {
                        t.setValue(v);
                        mipscode.add("    li " + t.getKey() + "," + v);
                        return t.getKey();
                    }
                }
            } else {
                // TODO
            }
        } else {
            if (info.isGlobal()) {
                for (Map.Entry<String, String> t : ts.entrySet()) {
                    if (t.getValue() == null) {
                        t.setValue(v);
                        mipscode.add("    move " + t.getKey() + ",_" + v);
                        return t.getKey();
                    }
                }
            } else {
                return info.getDescriptor();
            }
        }

        // No reg available
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cleanRegistry(String v) {
        if (ts.containsKey(v)) {
            ts.put(v, null);
        }
    }

    private String getAddress(String v) {
        SymbolInfo info = getSymbol(v);
        return info.getDescriptor();
    }

    private String getParamRegistry(String v) {
        for (Map.Entry<String, String> a : as.entrySet()) {
            if (a.getValue() == null) {
                a.setValue(v);
                return a.getKey();
            }
        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getSavedRegistry(String v) {
        for (Map.Entry<String, String> s : ss.entrySet()) {
            if (s.getValue() == null) {
                s.setValue(v);
                return s.getKey();
            }
        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cleanSavedRegistry(String v) {
        if (ts.containsKey(v)) {
            ts.put(v, null);
        }
    }

    public void parse() {

        // parse data
        mipscode.add("        .data");

        // parse all txts
        for (Map.Entry<String, String> txt : texts.entrySet()) {
            mipscode.add("    _" + txt.getKey() + " .asciiz \"" + txt.getValue() + "\"");
        }
        // parse words (global vars)
        for (SymbolInfo s : global.getTable()) {
            s.setDescriptor("_" + s.getId());
            if (s.getType().same(BaseType.INTEGER)) {
                mipscode.add("    _" + s.getId() + "  .word 0");
            } else if (s.getType().same(BaseType.CHAR)) {
                //TODO
            }
        }

        // parse texts (empty)
        mipscode.add("        .text");

        // parse globl
        mipscode.add("        .globl " + global.name);

        // parse code
        for (TAddressCode c : pseudocode) {
            SymbolInfo info;
            String regRdest;
            String regRscr;
            String regRscr2;

            switch (c.getInstr()) {
                // read and write calls
                case "READ":
                    info = getSymbol(c.getRdest());
                    regRdest = getRegistry(c.getRdest());

                    if (info.getType().same(BaseType.INTEGER)) {
                        mipscode.add("    li $v0,5");
                        mipscode.add("    syscall");
                        mipscode.add("    sw $v0," + regRdest);
                        cleanRegistry(regRdest);
                    } else if (info.getType().same(BaseType.CHAR)) {
                        //TODO
                    }
                    break;
                case "WRITE":
                    if (c.getRdest().matches("^MSG\\d+$")) {
                        mipscode.add("    li $v0,4");
                        mipscode.add("    la $a0,_" + c.getRdest());
                        mipscode.add("    syscall");
                    } else {
                        regRdest = getRegistry(c.getRdest());

                        info = getSymbol(c.getRdest());
                        if (info != null) {
                            if (info.getType().same(BaseType.INTEGER)) {
                                mipscode.add("    li $v0,1");
                                mipscode.add("    la $a0," + regRdest);
                                mipscode.add("    syscall");
                                cleanRegistry(regRdest);
                            } else if (info.getType().same(BaseType.CHAR)) {
                                //TODO
                            }
                        } else if (c.getRdest().matches("^\\d+$")) {
                            mipscode.add("    li $v0,1");
                            mipscode.add("    la $a0," + regRdest);
                            mipscode.add("    syscall");
                            cleanRegistry(regRdest);
                        } else {
                            // TODO
                        }
                    }
                    break;

                // Asignacion
                case "ASSIGN":
                    info = getSymbol(c.getRdest());
                    if (info != null && info.isGlobal()) {
                        regRscr = getRegistry(c.getRscr());
                        mipscode.add("    sw " + regRscr + ",_" + c.getRdest());
                        cleanRegistry(regRscr);
                    } else if (info != null && info.getType() instanceof ProcType) {
                        regRscr = getRegistry(c.getRscr());
                        mipscode.add("    move " + regRscr + ",$v0");
                    } else {
                        regRscr = getRegistry(c.getRscr());
                        regRdest = getAddress(c.getRdest());
                        mipscode.add("    sw " + regRscr + "," + regRdest);
                        cleanRegistry(regRscr);
                    }

                    break;

                // Operaciones
                case "PLUS":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    add " + regRdest + "," + regRscr + "," + regRscr2);
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "MINUS":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    sub " + regRdest + "," + regRscr + "," + regRscr2);
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "MULT":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    mul " + regRdest + "," + regRscr + "," + regRscr2);
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "SLASH":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    div " + regRdest + "," + regRscr + "," + regRscr2);
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "DIV":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    div " + regRdest + "," + regRscr + "," + regRscr2);
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "MOD":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    rem " + regRdest + "," + regRscr + "," + regRscr2);
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "UPLUS":
                    info = getSymbol(c.getRdest());
                    if (info != null && info.isGlobal()) {
                        regRscr = getRegistry(c.getRscr());
                        mipscode.add("    sw " + regRscr + ",_" + c.getRdest());
                        cleanRegistry(regRscr);
                    } else {
                        regRscr = getRegistry(c.getRscr());
                        regRdest = getAddress(c.getRdest());
                        mipscode.add("    sw " + regRscr + "," + regRdest);
                        cleanRegistry(regRscr);
                    }

                    break;
                case "UMINUS":
                    regRscr = getRegistry(c.getRscr());
                    regRdest = getRegistry(c.getRdest());
                    mipscode.add("    neg " + regRdest + "," + regRscr);
                    cleanRegistry(regRscr);

                    break;

                // saltos
                case "IFEQ":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    mipscode.add("    beq " + regRscr + "," + regRscr2 + "," + c.getRdest());
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "IFNEQ":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    mipscode.add("    bne " + regRscr + "," + regRscr2 + "," + c.getRdest());
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "IFLT":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    mipscode.add("    blt " + regRscr + "," + regRscr2 + "," + c.getRdest());
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "IFGT":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    mipscode.add("    bgt " + regRscr + "," + regRscr2 + "," + c.getRdest());
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "IFLE":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    mipscode.add("    ble " + regRscr + "," + regRscr2 + "," + c.getRdest());
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "IFGE":
                    regRscr = getRegistry(c.getRscr());
                    regRscr2 = getRegistry(c.getRscr2());
                    mipscode.add("    bge " + regRscr + "," + regRscr2 + "," + c.getRdest());
                    cleanRegistry(regRscr);
                    cleanRegistry(regRscr2);

                    break;
                case "GOTO":
                    if (c.getRdest().equals(global.name)) {
                        mipscode.add("    b " + c.getRdest());

                    } else {
                        mipscode.add("    b " + "_" + c.getRdest());
                    }

                    break;

                // etiquetas
                case "LABEL":
                    if (c.getRdest().equals(global.name)) {
                        mipscode.add(c.getRdest() + ":");
                        mipscode.add("    move $fp,$sp");

                    } else if (c.getRdest().matches("^ETIQ\\d+$")) {
                        mipscode.add(c.getRdest() + ":");
                    } else {
                        mipscode.add("_" + c.getRdest() + ":");
                        if (!c.getRdest().endsWith("_b")) {
                            // change scope
                            top = top.getScope(c.getRdest());

                            mipscode.add("    sw $fp,-4($sp)");
                            mipscode.add("    sw $ra,-8($sp)");
                            offset = 8;
                            for (SymbolInfo s : top.getTable()) {
                                if (!(s.getType() instanceof ProcType)) {
                                    offset += s.getType().getSize();
                                    if (s.isParam()) {
                                        String sr = getSavedRegistry(s.getId());
                                        s.setDescriptor(sr);
                                        mipscode.add("    sw " + s.getDescriptor() + ",-" + offset + "($sp)");
                                    } else {
                                        s.setDescriptor("-" + offset + "($sp)");
                                    }
                                }
                            }
                            mipscode.add("    move $fp,$sp");
                            mipscode.add("    sub $fp,$fp," + offset);

                            int paramIndex = 0;
                            String[] params = as.keySet().toArray(new String[as.size()]);
                            for (SymbolInfo s : top.getTable()) {
                                if (!(s.getType() instanceof ProcType)) {
                                    if (s.isParam()) {
                                        mipscode.add("    move " + s.getDescriptor() + "," + params[paramIndex]);
                                    }
                                    paramIndex++;
                                }
                            }
                        }
                    }

                    break;
                case "END":
                    if (c.getRdest().equals(global.name)) {
                        mipscode.add("    li $v0,10");
                        mipscode.add("    syscall");
                    } else {
                        // return scope
                        top = top.parent;

                        mipscode.add("    move $sp,$fp");
                        offset = 8;
                        for (SymbolInfo s : top.getTable()) {
                            if (!(s.getType() instanceof ProcType)) {
                                offset += s.getType().getSize();
                                if (s.isParam()) {
                                    mipscode.add("    lw " + s.getDescriptor() + ",-" + offset + "($sp)");
                                    cleanSavedRegistry(s.getDescriptor());
                                }
                            }
                        }
                        mipscode.add("    sw $ra,-8($sp)");
                        mipscode.add("    sw $fp,-4($sp)");
                        mipscode.add("    jr $ra");
                    }
                    break;

                // param
                // call
                case "PARAM":                   
                    regRscr = getAddress(c.getRdest());
                    regRdest = getParamRegistry(c.getRdest());
                    mipscode.add("    lw " + regRdest + "," + regRscr);

                    break;
                case "CALL":

                    if (c.getRdest().equals(global.name)) {
                        mipscode.add("    jal " + c.getRdest());
                    } else {
                        mipscode.add("    jal _" + c.getRdest());
                    }

                    break;

                // direccionamiento con indices 
                // TODO records
                case "[]=":
                    break;
                case "=[]":
                    break;
                default:
                    break;
            }
        }
    }

    public void writeCode(String file) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file + ".asm"), "utf-8"))) {
            for (String c : this.mipscode) {
                writer.write(c);
                writer.write(System.lineSeparator());
            }
        } catch (IOException ex) {
            Logger.getLogger(ICGParser.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.err);
        }
    }
}
