/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.*;
import java.util.ArrayList;
import java.util.List;
import table.ErrorMsg;
import table.SymbolInfo;
import table.SymbolTable;
import type.BaseType;
import type.ProcType;

/**
 *
 * @author mijail
 */
public class TypeCheckVisitor implements TypeVisitor {

    private SymbolTable global;
    private SymbolTable top;
    private List<ErrorMsg> errors;
    
    public TypeCheckVisitor(SymbolTable global) {
        this.global = global;
        this.top = global;
        this.errors = new ArrayList<>();
    }
    
    public boolean error() { 
        return errors.size() > 0;
    }
    
    public List<ErrorMsg> getErrors() {
        return errors;
    }
    
//    @Override
//    public void visit(Program n) {
//        n.SubProgramBody.accept(this);
//    }
//
//    @Override
//    public void visit(SubProgramBody n) {
//        if (global.get(n.SubProgramSpecification.Identifier.id).type.same(Undefined.UNDEFINED)) 
//            return;
//        
//        global = global.scopes.get(n.SubProgramSpecification.Identifier.id);
//        
//        for (ParameterDefinition p : n.SubProgramSpecification.ParameterList.Parameters) {
//            p.accept(this);
//        }
//        
//        for(DeclarativeItem i : n.DeclarativePart.Items)
//            if (i != null) i.accept(this);
//        
//        for(Statement s : n.Statements.Statements )
//            if (s != null) s.accept(this);
//        
//        // BIG TODO
//        // check every return statements :(
//        // if FUNCTION check return type vs return statement type
//        // if PROCEDURE check return statement type must be VOID
//        // check assig statements
//        // if assing id in parameters check mode Out or InOut
//        
//        global = global.parent;
//    }
//
//    @Override
//    public void visit(ParameterDefinition n) {
//        if (n.Expression == null) // optional expression
//            return;
//        
//        n.Expression.accept(this);
//        if (n.Expression.type == null) { 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'ParameterDefinition'"));
//            return;
//        }
//        
//        for (Identifier i : n.ParameterNameList.Identifiers) 
//            if (i.type.assignable(n.Expression.type)) 
//                errors.add(new ErrorMsg(
//                        n.Expression.getLine(), 
//                        n.Expression.getColumn(), 
//                        "Error unable to assing " + n.Expression.type.toString() + " to " + i.type.toString()));
//    }
//
//    @Override
//    public void visit(ObjectDeclarativeItem n) {
//        if (n.Expression == null) // optional expression
//            return;
//        
//        n.Expression.accept(this);
//        if (n.Expression.type == null) {
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'ObjectDeclarativeItem'"));
//            return;
//        }
//        
//        for (Identifier i : n.IdentifierList.Identifiers) 
//            if (i.type.assignable(n.Expression.type)) 
//                errors.add(new ErrorMsg(
//                        n.Expression.getLine(), 
//                        n.Expression.getColumn(), 
//                        "unable to assing " + n.Expression.type.toString() + " to " + i.type.toString()));
//    }
//
//    @Override
//    public void visit(NullStatement n) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        //nothing here
//    }
//
//    @Override
//    public void visit(AssignStatement n) {
//        n.Expression.accept(this);
//        n.Identifier.type = n.Expression.type;
//        
//        if (n.Expression.type == null) { 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'AssignStatement'"));
//            return;
//        }
//        
//        if (global.get(n.Identifier.id).type == null) {
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'AssignStatement'"));
//            return;
//        }
//        
//        if (!global.get(n.Identifier.id).type.assignable(n.Expression.type)) {
//            errors.add(new ErrorMsg(
//                        n.Expression.getLine(), 
//                        n.Expression.getColumn(), 
//                        "Error unable to assing " + n.Expression.type.toString() + " to " + global.get(n.Identifier.id).type.toString()));
//        }
//    }
//
//    @Override
//    public void visit(ReturnStatement n) {
//        ProcType parentType = (ProcType)global.parent.get(global.name).type;
//        
//        if (n.Expression == null) {  // optional expression
//            n.type = VoidType.VOID;
//            
//            if (!parentType.returnType.assignable(n.type))
//                errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "Expected return type [" + parentType.returnType.toString() + "]"));
//            
//            return;
//        }
//        
//        n.Expression.accept(this);
//        n.type = n.Expression.type;
//        
//        if (n.Expression.type == null) {
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'ReturnStatement'"));
//            return;
//        }
//        
//        if (!parentType.returnType.assignable(n.type))
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "Expected return type [" + parentType.returnType.toString() + "]"));
//    }
//
//    @Override
//    public void visit(ExitStatement n) {
//        n.Expression.accept(this);
//        n.type = n.Expression.type;
//        
//        if (n.Expression.type == null) { 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'AssignStatement'"));
//            return;
//        }
//        
//        if (!n.Expression.type.same(BaseType.BOOLEAN)) 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "the expression must be of type boolean."));
//    }
//
//    @Override
//    public void visit(CallStatement n) {
//        n.Expression.accept(this);
//        
//        if (n.Expression.type == null) { 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'CallStatement'"));
//            return;
//        }
//        
//        if (!(n.Expression.type instanceof ProcType)) { 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "Unable to invoke " + n.Expression.type.toString()));
//            return;
//        }
//            
//        ProcType type = (ProcType)n.Expression.type;
//        if (!type.returnType.same(VoidType.VOID)) 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "Unable to assing " + n.Expression.type.toString()));
//    }
//
//    @Override
//    public void visit(IfStatement n) {
//        n.Expression.accept(this);
//        
//        if (n.Expression.type == null) { 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'IfStatement'"));
//            return;
//        }
//        
//        if (!n.Expression.type.same(BaseType.BOOLEAN)) 
//            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "The expression must be of type boolean."));
//        
//        for (Statement s : n.Statements.Statements) 
//            if (s != null) s.accept(this);
//        
//        for (Statement s : n.ElseStatements.Statements) {
//            if (s != null) s.accept(this);
//        }        
//    }
//
//    @Override
//    public void visit(LoopStatement n) {
//        if (n.Iterator.Iterator.equals("WHILE")) {
//            n.Iterator.Exp1.accept(this);
//        
//            if (n.Iterator.Exp1.type == null) { 
//                errors.add(new ErrorMsg(n.Iterator.Exp1.getLine(), n.Iterator.Exp1.getColumn(), "null in 'LoopStatement'"));
//                return;
//            }
//
//            if (!n.Iterator.Exp1.type.same(BaseType.BOOLEAN)) 
//                errors.add(new ErrorMsg(n.Iterator.Exp1.getLine(), n.Iterator.Exp1.getColumn(), "The expression must be of type boolean."));
//        }
//        
//        if (n.Iterator.Iterator.equals("FOR")) {
//            n.Iterator.Exp1.accept(this);
//            n.Iterator.Exp2.accept(this);
//            n.Iterator.Identifier.type = n.Iterator.Exp1.type;
//        
//            if (n.Iterator.Exp1.type == null || n.Iterator.Exp2.type == null) { 
//                errors.add(new ErrorMsg(n.Iterator.Exp1.getLine(), n.Iterator.Exp1.getColumn(), "null in 'LoopStatement'"));
//                return;
//            }
//            
//            if (global.get(n.Iterator.Identifier.id).type == null) {
//                errors.add(new ErrorMsg(n.Iterator.Exp1.getLine(), n.Iterator.Exp1.getColumn(), "null in 'LoopStatement'"));
//                return;
//            }
//            
//            if (!n.Iterator.Identifier.type.same(BaseType.INTEGER)) 
//                errors.add(new ErrorMsg(n.Iterator.Identifier.getLine(), n.Iterator.Identifier.getColumn(), "The expression must be of type integer."));
//            
//            if (!global.get(n.Iterator.Identifier.id).type.assignable(n.Iterator.Exp1.type))
//                errors.add(new ErrorMsg(
//                        n.Iterator.Exp1.getLine(), 
//                        n.Iterator.Exp1.getColumn(), 
//                        "Error unable to assing " + n.Iterator.Exp1.type.toString() + " to " + global.get(n.Iterator.Identifier.id).type.toString()));
//            
//            if (!global.get(n.Iterator.Identifier.id).type.assignable(n.Iterator.Exp1.type))
//                errors.add(new ErrorMsg(
//                        n.Iterator.Exp2.getLine(), 
//                        n.Iterator.Exp2.getColumn(), 
//                        "Error unable to assing " + n.Iterator.Exp2.type.toString() + " to " + global.get(n.Iterator.Identifier.id).type.toString()));
//        }
//        
//        if (n.Iterator.Iterator.equals("LOOP")) {
//            // Nothing to do here
//            // On loop keeps going until a exit statement is executed
//        }
//        
//        for (Statement s : n.Statements.Statements) 
//            if ( s != null) s.accept(this);
//    }
//
//    @Override
//    public void visit(ReadStatement n) {
//        //n.Identifier.id
//        if (global == null) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "null in 'GetStatement'"));
//            return;
//        }
//        
//        SymbolInfo info = global.get(n.Identifier.id);
//        if (info.getType().same(Undefined.UNDEFINED)) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.Identifier.id + " is not declared."));
//        }
//    }
//    
//    @Override
//    public void visit(WriteStatement n) {
//        n.param.accept(this);
//        
//        if (n.param.type == null) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "null in 'PutStatement'"));
//            return;
//        }
//        
//        if (BaseType.STRING.assignable(n.param.type)) {
//            n.type = BaseType.STRING;
//        } else if (BaseType.INTEGER.assignable(n.param.type)) {
//            n.type = BaseType.INTEGER;
//        } else if (BaseType.FLOAT.assignable(n.param.type)) {
//            n.type = BaseType.FLOAT;
//        } else if (BaseType.BOOLEAN.assignable(n.param.type)) {
//            n.type = BaseType.BOOLEAN;
//        } else {
//            errors.add(new ErrorMsg(
//                    n.param.getLine(), 
//                    n.param.getColumn(), 
//                    "Error unable to [" + n.param.type.toString() + "] to [" + BaseType.BOOLEAN.toString() + "]"));
//            n.type = Undefined.UNDEFINED;
//        }
//    }
//
//    @Override
//    public void visit(False n) {
//        n.type = BaseType.BOOLEAN;
//    }
//
//    @Override
//    public void visit(True n) {
//        n.type = BaseType.BOOLEAN;
//    }
//
//    @Override
//    public void visit(LogicalExpression n) {
//        n.Exp1.accept(this);
//        n.Exp2.accept(this);
//        
//        if (n.Exp1.type == null) { 
//            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'LogicalExpression'"));
//            return;
//        }
//        
//        if (n.Exp2.type == null) { 
//            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'LogicalExpression'"));
//            return;
//        }
//
//        if (!(BaseType.BOOLEAN.assignable(n.Exp1.type) && BaseType.BOOLEAN.assignable(n.Exp2.type))) {
//            errors.add(new ErrorMsg(
//                    n.Exp1.getLine(), 
//                    n.Exp1.getColumn(), 
//                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
//            n.type = Undefined.UNDEFINED;
//            return;
//        }
//        
//        n.type = n.Exp1.type;
//    }
//
//    @Override
//    public void visit(RelationExpression n) {
//        n.Exp1.accept(this);
//        n.Exp2.accept(this);
//        
//        if (n.Exp1.type == null) { 
//            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'RelationExpression'"));
//            return;
//        }
//        
//        if (n.Exp2.type == null) { 
//            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'RelationExpression'"));
//            return;
//        }
//
//        if (BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type)) {
//            n.type = BaseType.BOOLEAN;
//        } else if (BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type)) {
//            n.type = BaseType.BOOLEAN;
//        } else {
//            errors.add(new ErrorMsg(
//                    n.Exp1.getLine(), 
//                    n.Exp1.getColumn(), 
//                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
//            n.type = Undefined.UNDEFINED;
//        }
//    }
//
//    @Override
//    public void visit(AritmeticExpression n) {
//        n.Exp1.accept(this);
//        n.Exp2.accept(this);
//        
//        if (n.Exp1.type == null) { 
//            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'AritmeticExpression'"));
//            return;
//        }
//        
//        if (n.Exp2.type == null) { 
//            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'AritmeticExpression'"));
//            return;
//        }
//
//        if (BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type)) {
//            n.type = n.Exp1.type;
//        } else if (BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type)) {
//            n.type = n.Exp1.type;
//        } else {
//            errors.add(new ErrorMsg(
//                    n.Exp1.getLine(), 
//                    n.Exp1.getColumn(), 
//                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
//            n.type = Undefined.UNDEFINED;
//        }
//    }
//
//    @Override
//    public void visit(NotExpression n) {
//        n.Exp.accept(this);
//        
//        if (n.Exp.type == null) { 
//            errors.add(new ErrorMsg(n.Exp.getLine(), n.Exp.getColumn(), "null in 'NotExpression'"));
//            return;
//        }
//
//        n.type = BaseType.BOOLEAN;
//        
//        if (!n.Exp.type.same(BaseType.BOOLEAN)) { 
//            errors.add(new ErrorMsg(n.Exp.getLine(), n.Exp.getColumn(), "The expression must be of type boolean."));
//            n.type = Undefined.UNDEFINED;
//        }
//    }
//
//    @Override
//    public void visit(UnaryExpression n) {
//        n.Exp1.accept(this);
//        
//        if (n.Exp1.type == null) { 
//            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'UnaryExpression'"));
//            return;
//        }
//
//        if (BaseType.INTEGER.assignable(n.Exp1.type)) {
//            n.type = BaseType.INTEGER;
//        } else if (BaseType.FLOAT.assignable(n.Exp1.type)) {
//            n.type = BaseType.FLOAT;
//        } else {
//            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "The expression must be of type integer or float."));
//            n.type = Undefined.UNDEFINED;
//        }
//    }
//
//    @Override
//    public void visit(CallExpression n) {
//        SymbolInfo info = global.get(n.Identifier.id);
//        if (info.getType().same(Undefined.UNDEFINED)) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.Identifier.id + " is not declared."));
//            n.type = Undefined.UNDEFINED;
//            return;
//        }
//        
//        if (info.getType() instanceof ProcType) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.Identifier.id + " is not a procedure."));
//            n.type = Undefined.UNDEFINED;
//            return;
//        }
//        
//        ProcType type = (ProcType)info.getType();
//        n.type = type.returnType;
//        
//        if (type.paramsCount() != n.Parameters.Parameters.size()) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.Identifier.id + " incorrect number of arguments."));
//            n.type = Undefined.UNDEFINED; // TODO
//        } else {
//            for (int i = 0; i < n.Parameters.Parameters.size(); i++) {
//                n.Parameters.Parameters.get(i).accept(this);
//                type.Type et = n.Parameters.Parameters.get(i).type;
//                if (!type.params.get(i).assignable(et)) {
//                    errors.add(new ErrorMsg(n.Parameters.Parameters.get(i).getLine(), n.Parameters.Parameters.get(i).getColumn(), "" + n.Parameters.Parameters.get(i).type.toString() + " parameter do not match type with argument " + type.params.get(i).toString()));
//                    n.type = Undefined.UNDEFINED; // TODO
//                }
//            }
//        }
//    }
//
//    @Override
//    public void visit(StringLiteral n) {
//        n.type = BaseType.STRING;
//    }
//
//    @Override
//    public void visit(FloatLiteral n) {
//        n.type = BaseType.FLOAT;
//    }
//
//    @Override
//    public void visit(IntegerLiteral n) {
//        n.type = BaseType.INTEGER;
//    }
//
//    @Override
//    public void visit(IdentifierExpression n) {
//        if (global == null) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "null in 'IdentifierExpression'"));
//            return;
//        }
//        
//        SymbolInfo info = global.get(n.Identifier.id);
//        if (info.getType().same(Undefined.UNDEFINED)) {
//            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.Identifier.id + " is not declared."));
//            n.type = Undefined.UNDEFINED;
//            return;
//        }
//        
//        n.type = info.getType();
//    }
//    
//    @Override
//    public void visit(UndefinedExpression n) {
//        n.type = Undefined.UNDEFINED;
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
