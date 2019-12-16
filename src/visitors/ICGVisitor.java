/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import table.TAddressCode;
import table.*;

/**
 *
 * @author mijail
 */
public class ICGVisitor implements CGVisitor {

    private SymbolTable global;
    private SymbolTable temps;
    private Map<String, Integer> labels;
    private Map<String, String> texts;
    private List<TAddressCode> pseudocode;

    public ICGVisitor(SymbolTable global) {
        this.labels = new LinkedHashMap<>();
        this.texts = new LinkedHashMap<>();
        this.pseudocode = new ArrayList<>();
        this.global = global;
        this.temps = new SymbolTable(null);
    }

    private void gen(String i) {
        gen(i, null);
    }

    private void gen(String i, String p) {
        gen(i, p, null);
    }

    private void gen(String i, String p, String s1) {
        gen(i, p, s1, null);
    }

    private void gen(String i, String p, String s1, String s2) {
        this.pseudocode.add(new TAddressCode(i, p, s1, s2));
    }

    private String label(String s) {
        if (labels.containsKey(s)) {
            int count = labels.get(s);
            labels.put(s, count + 1);
            return s + (count + 1);
        }

        labels.put(s, 0);
        return s + 0;
    }

    private String labelId(String s) {
        if (labels.containsKey(s)) {
            int count = labels.get(s);
            labels.put(s, count + 1);
            return s + (count + 1);
        }

        labels.put(s, 0);
        return s;
    }
    
    private String getMsg(String msg) {
        for (Map.Entry<String, String> entry : texts.entrySet()) {
            if (entry.getValue().equals(msg)) {
                return entry.getKey();
            }
        }
        // new text
        String key = label("MSG");
        texts.put(key, msg);
        return key;
    }

    public String getPseudoCode() {
        StringBuilder strb = new StringBuilder();
        for (TAddressCode a : this.pseudocode) {
            strb.append(a.toString());
        }
        return strb.toString();
    }

    public List<TAddressCode> getPseudoCodeList() {
        return this.pseudocode;
    }
    
    public Map<String, String> getTexts() {
        return this.texts;
    }
    
    public SymbolTable getTemps() {
        return this.temps;
    }

    public void writePseudoCode(String file) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {
            for (TAddressCode a : this.pseudocode) {
                writer.write(a.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException ex) {
            Logger.getLogger(ICGVisitor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void visit(Program n) {
        if (n instanceof ErrorProgram) {
            return;
        }

        n.Comienzo = labelId(n.id);
        n.Block.Comienzo = n.Comienzo + "_b";
        gen("LABEL", n.Comienzo);
        gen("GOTO", n.Block.Comienzo);
        n.Block.accept(this);
        gen("END", n.Comienzo);
    }

    @Override
    public void visit(IdentifierList n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Block n) {
        // visit ProcedureDeclaration
        for (Declaration d : n.Declarations.Declarations) {
            if (d instanceof ProcedureDeclaration) {
                d.Siguiente = n.Siguiente;
                d.accept(this);
            }
        }

        gen("LABEL", n.Comienzo);
        for (Statement s : n.Statements.Statements) {
            if (s != null) {
                //s.Siguiente = n.Siguiente;
                if (s instanceof IfStatement || s instanceof WhileStatement || s instanceof RepeatStatement || s instanceof ForStatement) {
                    s.Siguiente = label("ETIQ");
                }

                s.accept(this);

                if (s instanceof IfStatement || s instanceof WhileStatement || s instanceof RepeatStatement || s instanceof ForStatement) {
                    gen("GOTO", s.Siguiente);
                    gen("LABEL", s.Siguiente);
                }
            }
        }
    }

    @Override
    public void visit(TypeDeclaration n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TypeDefinition n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(VariableDeclaration n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(VariableDefinition n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ProcedureDeclaration n) {
        global = global.getScope(n.id.id);
        
        n.Comienzo = labelId(n.id.toString());
        n.Block.Comienzo = n.Comienzo + "_b";
        gen("LABEL", n.Comienzo);
        gen("GOTO", n.Block.Comienzo);
        n.Block.accept(this);
        gen("END", n.Comienzo);
        
        // return scope
        global = global.parent;
    }

    @Override
    public void visit(ParameterDefinition n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Statements n) {
        for (Statement s : n.Statements) {
            s.Siguiente = n.Siguiente;
            s.accept(this);
        }
    }

    @Override
    public void visit(WriteStatement n) {

        n.Lugar = getMsg(n.param);
        //gen("TXT", n.Lugar, n.param);
        gen("WRITE", n.Lugar);

        if (n.paramOpt != null) {
            n.paramOpt.accept(this);
            gen("WRITE", n.paramOpt.Lugar);
        }
    }

    @Override
    public void visit(ReadStatement n) {
        n.Expression.accept(this);
        gen("READ", n.Expression.Lugar);
    }

    @Override
    public void visit(AssignStatement n) {
        n.Identifier.accept(this);
        n.Expression.accept(this);
        gen("ASSIGN", n.Identifier.Lugar, n.Expression.Lugar);
        // TODO Records
    }

    @Override
    public void visit(ProcedureStatement n) {
        for (Expression e : n.Parameters.Parameters) {
            e.accept(this);
            gen("PARAM", e.Lugar);
        }
        gen("CALL", n.id.id, String.valueOf(n.Parameters.Parameters.size()));
    }

    @Override
    public void visit(IfStatement n) {
        n.Expression.Verdadera = label("ETIQ");
        n.Expression.Falsa = n.ElseStatement != null ? label("ETIQ") : n.Siguiente;

        // Expr
        n.Expression.accept(this);
        gen("LABEL", n.Expression.Verdadera);

        n.Statement.Siguiente = n.Siguiente;

        // S1
        n.Statement.accept(this);

        if (n.ElseStatement != null) {
            gen("GOTO", n.Siguiente);
            gen("LABEL", n.Expression.Falsa);

            n.ElseStatement.Siguiente = n.Siguiente;

            // S2
            n.ElseStatement.accept(this);
        }

        gen("GOTO", n.Siguiente);
    }

    @Override
    public void visit(WhileStatement n) {
        n.Comienzo = label("ETIQ");
        gen("LABEL", n.Comienzo);
        n.Expression.Verdadera = label("ETIQ");
        n.Expression.Falsa = n.Siguiente;

        n.Expression.accept(this);

        gen("LABEL", n.Expression.Verdadera);
        n.Statement.Siguiente = n.Comienzo;

        n.Statement.accept(this);

        gen("GOTO", n.Comienzo);
    }

    @Override
    public void visit(RepeatStatement n) {
        n.Expression.Verdadera = n.Siguiente;
        n.Expression.Falsa = label("ETIQ");
        n.Statements.Siguiente = label("ETIQ");
        gen("LABEL", n.Expression.Falsa);

        n.Statements.accept(this);

        gen("LABEL", n.Statements.Siguiente);
    }

    @Override
    public void visit(ForStatement n) {

        n.Comienzo = label("ETIQ");
        n.Verdadera = label("ETIQ");
        n.Statement.Siguiente = label("ETIQ");

        n.Exp1.accept(this);

        gen("ASSIGN", n.id.id, n.Exp1.Lugar);
        gen("LABEL", n.Comienzo);

        n.Exp2.accept(this);
        gen("IFLT", n.Verdadera, n.id.id, n.Exp2.Lugar);
        gen("GOTO", n.Siguiente);

        gen("LABEL", n.Statement.Siguiente);
        gen("PLUS", n.id.id, n.id.id, "1");
        gen("GOTO", n.Comienzo);

        gen("LABEL", n.Verdadera);
        n.Statement.accept(this);

    }

    @Override
    public void visit(LogicalExpression n) {
        switch (n.Operator.toUpperCase()) {
            case "AND":
                n.Exp1.Verdadera = label("ETIQ");
                n.Exp1.Falsa = n.Falsa;
            case "OR":
                n.Exp1.Verdadera = n.Verdadera;
                n.Exp1.Falsa = label("ETIQ");
        }

        n.Exp1.accept(this);

        switch (n.Operator.toUpperCase()) {
            case "AND":
                gen("LABEL", n.Exp1.Verdadera);
            case "OR":
                gen("LABEL", n.Exp1.Falsa);
        }
        n.Exp2.Verdadera = n.Verdadera;
        n.Exp2.Falsa = n.Falsa;

        n.Exp2.accept(this);

    }

    @Override
    public void visit(RelationExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);

        gen("IF" + n.Operator.toUpperCase(), n.Verdadera, n.Exp1.Lugar, n.Exp2.Lugar);
        gen("GOTO", n.Falsa);
    }

    @Override
    public void visit(AritmeticExpression n) {
        n.Lugar = label("t");
        
        try {
            //add temp
            temps.put(n.Lugar, new SymbolInfo(n.Lugar, global.name, n.type));
        } catch (Exception ex) {
            // should never throw error
            Logger.getLogger(ICGVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }

        n.Exp1.accept(this);
        n.Exp2.accept(this);

        gen(n.Operator.toUpperCase(), n.Lugar, n.Exp1.Lugar, n.Exp2.Lugar);
    }

    @Override
    public void visit(NotExpression n) {
        n.Exp.Verdadera = n.Falsa;
        n.Exp.Falsa = n.Verdadera;

        n.Exp.accept(this);
    }

    @Override
    public void visit(UnaryExpression n) {
        n.Lugar = label("t");
        
        try {
            //add temp
            temps.put(n.Lugar, new SymbolInfo(n.Lugar, global.name, n.type));
        } catch (Exception ex) {
            // should never throw error
            Logger.getLogger(ICGVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        n.Exp1.accept(this);
        gen("U" + n.Operator.toUpperCase(), n.Lugar, n.Exp1.Lugar);
    }

    @Override
    public void visit(IdentifierExpression n) {
        n.Lugar = n.id.id;
        // TODO Records
        if (n.field != null) {
            n.Lugar = n.field.id;
        }
    }

    @Override
    public void visit(CallExpression n) {
        n.Lugar = "RET";
        for (Expression e : n.Parameters.Parameters) {
            e.accept(this);
            gen("PARAM", e.Lugar);
        }
        gen("CALL", n.id.id, String.valueOf(n.Parameters.Parameters.size()));
    }

    @Override
    public void visit(StringLiteral n) {
        n.Lugar = getMsg(n.s);
        //n.Lugar = String.valueOf(n.s);
    }

    @Override
    public void visit(CharLiteral n) {
        n.Lugar = getMsg(n.s);
        //n.Lugar = String.valueOf(n.s);
    }

    @Override
    public void visit(IntegerLiteral n) {
        n.Lugar = String.valueOf(n.i);
    }

    @Override
    public void visit(True n) {
        n.Lugar = "0";
        if (!n.Verdadera.isEmpty()) {
            gen("GOTO", n.Verdadera);
        }
    }

    @Override
    public void visit(False n) {
        n.Lugar = "1";
        if (!n.Falsa.isEmpty()) {
            gen("GOTO", n.Falsa);
        }
    }

    @Override
    public void visit(IdentifierType n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(BooleanType n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IntegerType n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CharType n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ErrorProgram n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ErrorDeclaration n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ErrorStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ErrorExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
