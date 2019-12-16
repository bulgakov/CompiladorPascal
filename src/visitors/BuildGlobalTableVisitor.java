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
import table.SymbolType;
import type.BaseType;
import type.DefinedType;
import type.Type;
import type.ProcType;

/**
 *
 * @author mijail
 */
public class BuildGlobalTableVisitor implements SymbolTableVisitor {

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

    @Override
    public void visit(Program n) {
        if (n instanceof ErrorProgram) {
            return;
        }

        global = new SymbolTable(null);
        global.name = n.id;
        n.Block.accept(this);
    }

    @Override
    public void visit(IdentifierList n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Block n) {

        // Get type definitions
        for (Declaration d : n.Declarations.Declarations) {
            if (d instanceof TypeDeclaration) {
                d.accept(this);
            }
        }

        // Another round for variables and procedures
        for (Declaration d : n.Declarations.Declarations) {
            if (!(d instanceof TypeDeclaration)) {
                d.accept(this);
            }
        }
    }

    @Override
    public void visit(TypeDeclaration n) {
        for (TypeDefinition t : n.TypeDefinitions.Types) {
            t.accept(this);
        }
    }

    @Override
    public void visit(TypeDefinition n) {

        DefinedType df = new DefinedType(n.id.id);
        SymbolType type = new SymbolType(n.id.id, global.name, df);

        for (Field f : n.Fields.Fields) {

            f.Type.accept(this);
            n.type = f.Type.type;

            try {
                type.put(f.id.id, new SymbolInfo(f.id.id, type.getId(), f.type));
            } catch (Exception ex) {
                errors.add(new ErrorMsg(
                        f.getLine(),
                        f.getColumn(),
                        ex.getMessage()));
            }
        }
        
        try {
            global.put(n.id.id, type);
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.id.getLine(),
                    n.id.getColumn(),
                    ex.getMessage()));
        }
    }

    @Override
    public void visit(VariableDeclaration n) {
        for (VariableDefinition t : n.VariableDefinitions.Variables) {
            t.accept(this);
        }
    }

    @Override
    public void visit(VariableDefinition n) {

        n.Type.accept(this);
        n.type = n.Type.type;

        try {
            global.put(n.id.id, new SymbolInfo(n.id.id, global.name, n.type));
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.getLine(),
                    n.getColumn(),
                    ex.getMessage()));
        }
    }

    @Override
    public void visit(ProcedureDeclaration n) {
        String procName = n.id.id;

        List<type.Type> params = new ArrayList<>();
        for (ParameterDefinition param : n.ParameterDefinitions.Parameters) {
            param.Type.accept(this);
            param.type = param.Type.type;
            params.add(param.type);
        }

        if (n.ReturnType != null) {
            n.ReturnType.accept(this);
        }

        Type procType = new ProcType(n.id.id, n.ReturnType == null ? BaseType.VOID : n.ReturnType.type, params);

        SymbolTable proctable = new SymbolTable(global);
        proctable.name = procName;

        try {
            global.put(procName, new SymbolInfo(procName, global.name, procType));
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.id.getLine(),
                    n.id.getColumn(),
                    ex.getMessage()));
        }
        try {
            global.put(procName, proctable);
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.id.getLine(),
                    n.id.getColumn(),
                    ex.getMessage()));
        }

        //set scope to procedure
        global = proctable;

        //add each parameter to table
        for (ParameterDefinition param : n.ParameterDefinitions.Parameters) {
            param.accept(this);
        }

        //add block to table
        n.Block.accept(this);

        //restore scope to parent
        global = proctable.parent;
    }

    @Override
    public void visit(ParameterDefinition n) {

        // Already done on procedure declaration
        //n.Type.accept(this);
        //n.type = n.Type.type;
        try {
            global.put(n.id.id, new SymbolInfo(n.id.id, global.name, n.type, true));
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.getLine(),
                    n.getColumn(),
                    ex.getMessage()));
        }
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
    public void visit(CharLiteral n) {
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
        SymbolType stype = global.getType(n.id);
        n.type = stype.getType();
    }

    @Override
    public void visit(BooleanType n) {
        n.type = BaseType.BOOLEAN;
    }

    @Override
    public void visit(IntegerType n) {
        n.type = BaseType.INTEGER;
    }

    @Override
    public void visit(CharType n) {
        n.type = BaseType.CHAR;
    }

    @Override
    public void visit(ErrorProgram n) {
        // Nothing here its a syntax error
    }

    @Override
    public void visit(ErrorDeclaration n) {
        // Nothing here its a syntax error
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
