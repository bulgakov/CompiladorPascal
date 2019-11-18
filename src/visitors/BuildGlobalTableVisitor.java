/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import table.ErrorMsg;
//import ast.Type;
import table.SymbolInfo;
import table.SymbolTable;
import type.Type;
import type.ProcType;


/**
 *
 * @author mijail
 */
public class BuildGlobalTableVisitor implements GlobalTableVisitor {
    
    private SymbolTable global;
    private List<ErrorMsg> errors;
    
    
    public BuildGlobalTableVisitor() {
        errors = new ArrayList<>();
    }
    
    public SymbolTable getGlobalTable() { 
        return global;
    }
    
    public boolean error() {
        return errors.size() > 0;
    }
    
    public List<ErrorMsg> getErrors() {
        return errors;
    }
    
//    @Override
//    public void visit(Program n) {
//        global = new SymbolTable(null);
//        global.name = "global";
//        n.SubProgramBody.accept(this);
//    }
//
//    @Override
//    public void visit(SubProgramBody n) {
//        
//        String procName = n.SubProgramSpecification.Identifier.id;
//        Type procType = n.SubProgramSpecification.Identifier.type;
//
//        SymbolTable proctable = new SymbolTable(global);
//        proctable.name = procName;
//        ((ProcType) procType).thisType = proctable;
//
//        try {
//            global.put(procName, new SymbolInfo(procName, global.name, procType));
//        } catch (Exception ex) {
//            errors.add(new ErrorMsg(
//                    n.SubProgramSpecification.Identifier.getLine(), 
//                    n.SubProgramSpecification.Identifier.getColumn(), 
//                    ex.getMessage()));
//        }
//        try {
//            global.put(procName, proctable);
//        } catch (Exception ex) {
//            errors.add(new ErrorMsg(
//                    n.SubProgramSpecification.Identifier.getLine(), 
//                    n.SubProgramSpecification.Identifier.getColumn(), 
//                    ex.getMessage()));
//        }
//
//        //set scope to subprogram
//        global = proctable;
//
//        for(ParameterDefinition param : n.SubProgramSpecification.ParameterList.Parameters)
//            param.accept(this);
//
//        for(DeclarativeItem item : n.DeclarativePart.Items)
//            item.accept(this);
//
//        //restore scope to parent
//        global = proctable.parent;
//    }
//    
//    @Override
//    public void visit(ParameterDefinition n) {
//        for(Identifier i : n.ParameterNameList.Identifiers)
//            try {
//                global.put(i.id, new SymbolInfo(i.id, global.name, i.type));
//            } catch (Exception ex) {
//                errors.add(new ErrorMsg(
//                    i.getLine(), 
//                    i.getColumn(), 
//                    ex.getMessage()));
//            }
//    }
//
//    @Override
//    public void visit(ObjectDeclarativeItem n) {
//        for(Identifier i : n.IdentifierList.Identifiers)
//            try {
//                global.put(i.id, new SymbolInfo(i.id, global.name, i.type));
//            } catch (Exception ex) {
//                errors.add(new ErrorMsg(
//                    i.getLine(), 
//                    i.getColumn(), 
//                    ex.getMessage()));
//            }
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
    public void visit(Declarations n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TypeDeclaration n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TypeDefinitions n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TypeDefinition n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(FieldList n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(VariableDeclaration n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(VariableDefinitions n) {
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
    public void visit(ParameterDefinitions n) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(WhileStatement n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void visit(Parameters n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(LogicalExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(RelationExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AritmeticExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NotExpression n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void visit(IntegerLiteral n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(True n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(False n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
