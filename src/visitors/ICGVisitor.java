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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import table.TAddressCode;
import table.SymbolTable;

/**
 *
 * @author mijail
 */
public class ICGVisitor implements CGVisitor {

    private SymbolTable global;
    private SymbolTable top;
    private Map<String, Integer> labels;
    private List<TAddressCode> pseudocode;

    public ICGVisitor(SymbolTable global) {
        this.labels = new HashMap<>();
        this.pseudocode = new ArrayList<>();
        this.global = global;
        this.top = global;
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

    public void writePseudoCode(String file) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {
            for (TAddressCode a : this.pseudocode) {
                writer.write(a.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(ICGVisitor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//    @Override
//    public void visit(Program n) {
//        n.SubProgramBody.accept(this);
//    }
//
//    @Override
//    public void visit(SubProgramBody n) {
//        global = global.scopes.get(n.SubProgramSpecification.Identifier.id);
//        
//        for (ParameterDefinition p : n.SubProgramSpecification.ParameterList.Parameters) {
//            p.accept(this);
//        }
//        
//        for (DeclarativeItem i : n.DeclarativePart.Items) {
//            if (i != null ) i.accept(this);
//        }
//        
//        for (Statement s : n.Statements.Statements) {
//            if (s != null) s.accept(this);
//        }
//        
//        global = global.parent;
//    }
//
//    @Override
//    public void visit(ParameterDefinition n) {
//        for (Identifier i : n.ParameterNameList.Identifiers) {
//            //TODO
//        }
//    }
//
//    @Override
//    public void visit(ObjectDeclarativeItem n) {
//        if (n.Expression == null) // optional expression
//            return;
//        
//        n.Expression.accept(this);
//        
//        for (Identifier i : n.IdentifierList.Identifiers) {
//            //TODO
//        }
//    }
//
//    @Override
//    public void visit(NullStatement n) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        // TODO
//    }
//
//    @Override
//    public void visit(AssignStatement n) {
//        n.Expression.accept(this);
//    }
//
//    @Override
//    public void visit(ReturnStatement n) {
//        if (n.Expression == null) {
//            
//            return;
//        }
//        
//        n.Expression.accept(this);
//    }
//
//    @Override
//    public void visit(ExitStatement n) {
//        n.Expression.accept(this);
//    }
//
//    @Override
//    public void visit(CallStatement n) {
//        n.Expression.accept(this);
//    }
//
//    @Override
//    public void visit(IfStatement n) {
//        n.Expression.Verdadera = label("ETIQ");
//        n.Expression.Falsa = label("ETIQ");
//        n.Expression.accept(this);
//        gen("LABEL", n.Expression.Verdadera);
//        for (Statement s : n.Statements.Statements) {
//            if (s != null) {
//                s.Siguiente = n.Siguiente;
//                s.accept(this); 
//            }
//        }
//        gen("GOTO", n.Siguiente);
//        gen("LABEL", n.Expression.Falsa);
//        
//        for (Statement s : n.ElseStatements.Statements) {
//            if (s != null) s.accept(this);
//        }
//    }
//    
//    @Override
//    public void visit(LoopStatement n) {
//        if (n.Iterator.Iterator.equals("WHILE")) {
//            
//        }
//        
//        if (n.Iterator.Iterator.equals("FOR")) {
//            
//        }
//        
//        if (n.Iterator.Iterator.equals("LOOP")) {
//            
//        }
//        
//        for (Statement s : n.Statements.Statements) {
//            if ( s != null) s.accept(this);
//        }
//    }
//
//    @Override
//    public void visit(ReadStatement n) {
//        //TODO
//        //n.Identifier
//        gen("READ",n.Identifier.Lugar);
//    }
//
//    @Override
//    public void visit(WriteStatement n) {
//        n.param.accept(this);
//        gen("WRITE",n.param.Lugar);
//    }
//
//    @Override
//    public void visit(False n) {
//        //TODO
//        n.Lugar = label("t");
//        gen("ASSING",n.Lugar,"0");
//    }
//
//    @Override
//    public void visit(True n) {
//        //TODO
//        n.Lugar = label("t");
//        gen("ASSING",n.Lugar,"1");
//    }
//
//    @Override
//    public void visit(LogicalExpression n) {
//        n.Exp1.accept(this);
//        n.Exp2.accept(this);
//        n.Lugar = label("t");
//        gen(n.Operator,n.Lugar,n.Exp1.Lugar,n.Exp2.Lugar);
//    }
//
//    @Override
//    public void visit(RelationExpression n) {
//        n.Exp1.accept(this);
//        n.Exp2.accept(this);
//        n.Lugar = label("t");
//        gen(n.Operator,n.Lugar,n.Exp1.Lugar,n.Exp2.Lugar);
//    }
//
//    @Override
//    public void visit(AritmeticExpression n) {
//        n.Exp1.accept(this);
//        n.Exp2.accept(this);
//        n.Lugar = label("t");
//        gen(n.Operator,n.Lugar,n.Exp1.Lugar,n.Exp2.Lugar);
//    }
//
//    @Override
//    public void visit(NotExpression n) {
//        n.Exp.accept(this);
//        n.Lugar = label("t");
//        gen("not",n.Lugar,n.Exp.Lugar);
//    }
//
//    @Override
//    public void visit(UnaryExpression n) {
//        n.Lugar = label("t");
//        n.Exp1.accept(this);
//        gen(n.Operator,n.Lugar,n.Exp1.Lugar);
//    }
//
//    @Override
//    public void visit(CallExpression n) {
//        for (Expression e : n.Parameters.Parameters) {
//            e.accept(this);
//        }
//        
//        for (Expression e : n.Parameters.Parameters) {
//            
//        }
//    }
//
//    @Override
//    public void visit(StringLiteral n) {
//        //TODO
//        n.Lugar = String.valueOf(n.s);
//    }
//
//    @Override
//    public void visit(FloatLiteral n) {
//        //TODO
//        n.Lugar = String.valueOf(n.f);
//    }
//
//    @Override
//    public void visit(IntegerLiteral n) {
//        //TODO
//        n.Lugar = String.valueOf(n.i);
//    }
//
//    @Override
//    public void visit(IdentifierExpression n) {
//        //TODO
//        n.Lugar = n.Identifier.id;
//    }
//
//    @Override
//    public void visit(UndefinedExpression n) {
//        //Should never get in here
//        //Nothing to do
//    }
    @Override
    public void visit(Program n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IdentifierList n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Block n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ParameterDefinition n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Statements n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(WriteStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ReadStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AssignStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ProcedureStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }

    @Override
    public void visit(WhileStatement n) {

    }

    @Override
    public void visit(RepeatStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ForStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(LogicalExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(RelationExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);
        
        gen("IF" + n.Operator, n.Verdadera, n.Exp1.Lugar, n.Exp2.Lugar);
        gen("GOTO", n.Falsa);
    }

    @Override
    public void visit(AritmeticExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NotExpression n) {
        n.Exp.Verdadera = n.Falsa;
        n.Exp.Falsa = n.Verdadera;
        
        n.Exp.accept(this);
    }

    @Override
    public void visit(UnaryExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IdentifierExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CallExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(StringLiteral n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CharLiteral n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IntegerLiteral n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(True n) {
        gen("GOTO", n.Verdadera);
    }

    @Override
    public void visit(False n) {
        gen("GOTO", n.Falsa);
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
