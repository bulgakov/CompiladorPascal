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

    @Override
    public void visit(Program n) {
        if (n instanceof ErrorProgram) {
            return;
        }

        n.Block.accept(this);
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
                d.accept(this);
            }
        }

        for (Statement s : n.Statements.Statements) {
            if (s != null) {
                s.accept(this);
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
        if (global.get(n.id.id).type.same(BaseType.UNDEFINED)) {
            return;
        }

        global = global.getScope(n.id.id);

        // visit Block
        n.Block.accept(this);

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
            if (s != null) {
                s.accept(this);
            }
        }
    }

    @Override
    public void visit(WriteStatement n) {
        n.paramOpt.accept(this);

        if (n.paramOpt.type == null) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "null in 'WriteStatement'"));
            return;
        }

        if (BaseType.STRING.assignable(n.paramOpt.type)) {
            n.type = BaseType.STRING;
        } else if (BaseType.CHAR.assignable(n.paramOpt.type)) {
            n.type = BaseType.CHAR;
        } else if (BaseType.INTEGER.assignable(n.paramOpt.type)) {
            n.type = BaseType.INTEGER;
        } else if (BaseType.FLOAT.assignable(n.paramOpt.type)) {
            n.type = BaseType.FLOAT;
        } else if (BaseType.BOOLEAN.assignable(n.paramOpt.type)) {
            n.type = BaseType.BOOLEAN;
        } else {
            errors.add(new ErrorMsg(
                    n.paramOpt.getLine(),
                    n.paramOpt.getColumn(),
                    "Error in use [" + n.paramOpt.type.toString() + "] on write statement"));
            n.type = BaseType.UNDEFINED;
        }
    }

    @Override
    public void visit(ReadStatement n) {
        n.Expression.accept(this);

        if (global == null) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "null in 'ReadStatement'"));
            return;
        }

        if (BaseType.STRING.assignable(n.Expression.type)) {
            n.type = BaseType.STRING;
        } else if (BaseType.CHAR.assignable(n.Expression.type)) {
            n.type = BaseType.CHAR;
        } else if (BaseType.INTEGER.assignable(n.Expression.type)) {
            n.type = BaseType.INTEGER;
        } else if (BaseType.FLOAT.assignable(n.Expression.type)) {
            n.type = BaseType.FLOAT;
        } else if (BaseType.BOOLEAN.assignable(n.Expression.type)) {
            n.type = BaseType.BOOLEAN;
        } else {
            errors.add(new ErrorMsg(
                    n.Expression.getLine(),
                    n.Expression.getColumn(),
                    "Error in use [" + n.Expression.type.toString() + "] on read statement"));
            n.type = BaseType.UNDEFINED;
        }
    }

    @Override
    public void visit(AssignStatement n) {
        n.Expression.accept(this);
        n.Identifier.accept(this);

        if (n.Expression.type == null) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'AssignStatement'"));
            return;
        }

        if (n.Identifier.type == null) {
            errors.add(new ErrorMsg(n.Identifier.getLine(), n.Identifier.getColumn(), "null in 'AssignStatement'"));
            return;
        }

        if (n.Identifier.type.same(BaseType.UNDEFINED)) {
            return;
        }

        if (n.Expression.type.same(BaseType.UNDEFINED)) {
            errors.add(new ErrorMsg(
                    n.Expression.getLine(),
                    n.Expression.getColumn(),
                    "Error unable to assing " + n.Expression.type.toString() + " to " + n.Identifier.type.toString()));
        }

        if (!n.Identifier.type.assignable(n.Expression.type)) {
            errors.add(new ErrorMsg(
                    n.Expression.getLine(),
                    n.Expression.getColumn(),
                    "Error unable to assing " + n.Expression.type.toString() + " to " + n.Identifier.type.toString()));
        }
    }

    @Override
    public void visit(ProcedureStatement n) {
        SymbolInfo info = global.get(n.id.id);

        if (info.getType().same(BaseType.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " is not declared."));
            n.type = BaseType.UNDEFINED;
            return;
        }

        if (!(info.getType() instanceof ProcType)) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " is not a procedure."));
            n.type = BaseType.UNDEFINED;
            return;
        }

        ProcType type = (ProcType) info.getType();
        n.type = type.returnType;

        if (type.paramsCount() != n.Parameters.Parameters.size()) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " incorrect number of arguments."));
            n.type = BaseType.UNDEFINED; // TODO
        } else {
            for (int i = 0; i < n.Parameters.Parameters.size(); i++) {
                n.Parameters.Parameters.get(i).accept(this);
                type.Type et = n.Parameters.Parameters.get(i).type;
                if (!type.params.get(i).assignable(et)) {
                    errors.add(new ErrorMsg(n.Parameters.Parameters.get(i).getLine(), n.Parameters.Parameters.get(i).getColumn(), "" + n.Parameters.Parameters.get(i).type.toString() + " parameter do not match type with argument " + type.params.get(i).toString()));
                    n.type = BaseType.UNDEFINED; // TODO
                }
            }
        }
    }

    @Override
    public void visit(IfStatement n) {
        n.Expression.accept(this);

        if (n.Expression.type == null) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'IfStatement'"));
            return;
        }

        if (!n.Expression.type.same(BaseType.BOOLEAN)) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "The expression must be of type boolean."));
        }

        n.Statement.accept(this);

        if (n.ElseStatement != null) {
            n.ElseStatement.accept(this);
        }
    }

    @Override
    public void visit(WhileStatement n) {
        n.Expression.accept(this);

        if (n.Expression.type == null) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'WhileStatement'"));
            return;
        }

        if (!n.Expression.type.same(BaseType.BOOLEAN)) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "The expression must be of type boolean."));
        }

        n.Statement.accept(this);
    }

    @Override
    public void visit(RepeatStatement n) {
        n.Expression.accept(this);

        if (n.Expression.type == null) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "null in 'RepeatStatement'"));
            return;
        }

        if (!n.Expression.type.same(BaseType.BOOLEAN)) {
            errors.add(new ErrorMsg(n.Expression.getLine(), n.Expression.getColumn(), "The expression must be of type boolean."));
        }

        n.Statements.accept(this);
    }

    @Override
    public void visit(ForStatement n) {
        SymbolInfo info = global.get(n.id.id);
        if (info.getType().same(BaseType.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " is not declared."));
            n.type = BaseType.UNDEFINED;
            return;
        }

        n.Exp1.accept(this);
        n.Exp2.accept(this);

        if (n.Exp1.type == null) {
            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'ForStatement'"));
            return;
        }

        if (n.Exp2.type == null) {
            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'ForStatement'"));
            return;
        }

        if (!info.getType().assignable(n.Exp1.type)) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLine(),
                    n.Exp1.getColumn(),
                    "Error to convert " + n.Exp1.type.toString() + " to " + info.getType().toString()));
        }

        if (!info.getType().assignable(n.Exp2.type)) {
            errors.add(new ErrorMsg(
                    n.Exp2.getLine(),
                    n.Exp2.getColumn(),
                    "Error to convert " + n.Exp2.type.toString() + " to " + info.getType().toString()));
        }

        if (!info.getType().same(BaseType.INTEGER)) {
            errors.add(new ErrorMsg(n.id.getLine(), n.id.getColumn(), "[" + n.id.toString() + "] must be of type integer."));
        }

        n.Statement.accept(this);
    }

    @Override
    public void visit(LogicalExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);

        if (n.Exp1.type == null) {
            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'LogicalExpression'"));
            return;
        }

        if (n.Exp2.type == null) {
            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'LogicalExpression'"));
            return;
        }

        if (!(BaseType.BOOLEAN.assignable(n.Exp1.type) && BaseType.BOOLEAN.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLine(),
                    n.Exp1.getColumn(),
                    "Error unable to " + n.Operator + " [" + n.Exp1.type.toString() + "] and [" + n.Exp2.type.toString() + "]"));
            n.type = BaseType.UNDEFINED;
            return;
        }

        n.type = BaseType.BOOLEAN;
    }

    @Override
    public void visit(RelationExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);

        if (n.Exp1.type == null) {
            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'RelationExpression'"));
            return;
        }

        if (n.Exp2.type == null) {
            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'RelationExpression'"));
            return;
        }

        if (BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type)) {
            n.type = BaseType.BOOLEAN;
        } else if (BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type)) {
            n.type = BaseType.BOOLEAN;
        } else {
            errors.add(new ErrorMsg(
                    n.Exp1.getLine(),
                    n.Exp1.getColumn(),
                    "Error unable to " + n.Operator + " [" + n.Exp1.type.toString() + "] and [" + n.Exp2.type.toString() + "]"));
            n.type = BaseType.UNDEFINED;
        }
    }

    @Override
    public void visit(AritmeticExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);

        if (n.Exp1.type == null) {
            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'AritmeticExpression'"));
            return;
        }

        if (n.Exp2.type == null) {
            errors.add(new ErrorMsg(n.Exp2.getLine(), n.Exp2.getColumn(), "null in 'AritmeticExpression'"));
            return;
        }

        if (BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type)) {
            n.type = n.Exp1.type;
        } else if (BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type)) {
            n.type = n.Exp1.type;
        } else {
            errors.add(new ErrorMsg(
                    n.Exp1.getLine(),
                    n.Exp1.getColumn(),
                    "Error unable to " + n.Operator + " [" + n.Exp1.type.toString() + "] and [" + n.Exp2.type.toString() + "]"));
            n.type = BaseType.UNDEFINED;
        }
    }

    @Override
    public void visit(NotExpression n) {
        n.Exp.accept(this);

        if (n.Exp.type == null) {
            errors.add(new ErrorMsg(n.Exp.getLine(), n.Exp.getColumn(), "null in 'NotExpression'"));
            return;
        }

        n.type = BaseType.BOOLEAN;

        if (!n.Exp.type.same(BaseType.BOOLEAN)) {
            errors.add(new ErrorMsg(n.Exp.getLine(), n.Exp.getColumn(), "The expression must be of type boolean."));
            n.type = BaseType.UNDEFINED;
        }
    }

    @Override
    public void visit(UnaryExpression n) {
        n.Exp1.accept(this);

        if (n.Exp1.type == null) {
            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "null in 'UnaryExpression'"));
            return;
        }

        if (BaseType.INTEGER.assignable(n.Exp1.type)) {
            n.type = BaseType.INTEGER;
        } else if (BaseType.FLOAT.assignable(n.Exp1.type)) {
            n.type = BaseType.FLOAT;
        } else {
            errors.add(new ErrorMsg(n.Exp1.getLine(), n.Exp1.getColumn(), "The expression must be of type integer or float."));
            n.type = BaseType.UNDEFINED;
        }
    }

    @Override
    public void visit(IdentifierExpression n) {
        if (global == null) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "null in 'IdentifierExpression'"));
            return;
        }

        SymbolInfo info = global.get(n.id.id);
        if (info.getType().same(BaseType.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " is not declared."));
            n.type = BaseType.UNDEFINED;
            return;
        }

        n.type = info.getType();
    }

    @Override
    public void visit(CallExpression n) {
        SymbolInfo info = global.get(n.id.id);
        if (info.getType().same(BaseType.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " is not declared."));
            n.type = BaseType.UNDEFINED;
            return;
        }

        if (!(info.getType() instanceof ProcType)) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " is not a procedure."));
            n.type = BaseType.UNDEFINED;
            return;
        }

        ProcType type = (ProcType) info.getType();
        n.type = type.returnType;

        if (type.paramsCount() != n.Parameters.Parameters.size()) {
            errors.add(new ErrorMsg(n.getLine(), n.getColumn(), "" + n.id.id + " incorrect number of arguments."));
            n.type = BaseType.UNDEFINED; // TODO
        } else {
            for (int i = 0; i < n.Parameters.Parameters.size(); i++) {
                n.Parameters.Parameters.get(i).accept(this);
                type.Type et = n.Parameters.Parameters.get(i).type;
                if (!type.params.get(i).assignable(et)) {
                    errors.add(new ErrorMsg(n.Parameters.Parameters.get(i).getLine(), n.Parameters.Parameters.get(i).getColumn(), "" + n.Parameters.Parameters.get(i).type.toString() + " parameter do not match type with argument " + type.params.get(i).toString()));
                    n.type = BaseType.UNDEFINED; // TODO
                }
            }
        }
    }

    @Override
    public void visit(StringLiteral n) {
        n.type = BaseType.STRING;
    }

    @Override
    public void visit(CharLiteral n) {
        n.type = BaseType.CHAR;
    }

    @Override
    public void visit(IntegerLiteral n) {
        n.type = BaseType.INTEGER;
    }

    @Override
    public void visit(True n) {
        n.type = BaseType.BOOLEAN;
    }

    @Override
    public void visit(False n) {
        n.type = BaseType.BOOLEAN;
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
        // Nothing here its a syntax error
    }

    @Override
    public void visit(ErrorDeclaration n) {
        // Nothing here its a syntax error
    }

    @Override
    public void visit(ErrorStatement n) {
        // Nothing here its a syntax error
    }

    @Override
    public void visit(ErrorExpression n) {
        // Undefined because its a syntax error
        n.type = BaseType.UNDEFINED;
    }
}
