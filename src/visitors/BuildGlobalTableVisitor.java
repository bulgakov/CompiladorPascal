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
        errors = new ArrayList<ErrorMsg>();
    }
    
    @Override
    public void visit(Program n) {
        global = new SymbolTable(null);
        global.name = "global";
        n.SubProgramBody.accept(this);
    }

    @Override
    public void visit(SubProgramBody n) {
        
        String procName = n.SubProgramSpecification.Identifier.id;
        Type procType = n.SubProgramSpecification.Identifier.type;

        SymbolTable proctable = new SymbolTable(global);
        proctable.name = procName;
        ((ProcType) procType).thisType = proctable;

        try {
            global.put(procName, new SymbolInfo(procName, global.name, procType));
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.SubProgramSpecification.Identifier.getLine(), 
                    n.SubProgramSpecification.Identifier.getColumn(), 
                    ex.getMessage()));
        }
        try {
            global.put(procName, proctable);
        } catch (Exception ex) {
            errors.add(new ErrorMsg(
                    n.SubProgramSpecification.Identifier.getLine(), 
                    n.SubProgramSpecification.Identifier.getColumn(), 
                    ex.getMessage()));
        }

        //set scope to subprogram
        global = proctable;

        for(ParameterDefinition param : n.SubProgramSpecification.ParameterList.Parameters)
            param.accept(this);

        for(DeclarativeItem item : n.DeclarativePart.Items)
            item.accept(this);

        //restore scope to parent
        global = proctable.parent;
    }
    
    @Override
    public void visit(ParameterDefinition n) {
        for(Identifier i : n.ParameterNameList.Identifiers)
            try {
                global.put(i.id, new SymbolInfo(i.id, global.name, i.type));
            } catch (Exception ex) {
                errors.add(new ErrorMsg(
                    i.getLine(), 
                    i.getColumn(), 
                    ex.getMessage()));
            }
    }

    @Override
    public void visit(ObjectDeclarativeItem n) {
        for(Identifier i : n.IdentifierList.Identifiers)
            try {
                global.put(i.id, new SymbolInfo(i.id, global.name, i.type));
            } catch (Exception ex) {
                errors.add(new ErrorMsg(
                    i.getLine(), 
                    i.getColumn(), 
                    ex.getMessage()));
            }
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
}
