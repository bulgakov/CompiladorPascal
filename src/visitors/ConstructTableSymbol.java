/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.Program;
import java.util.List;
import table.ErrorMsg;
import table.SymbolTable;

/**
 *
 * @author mijail
 */
public class ConstructTableSymbol {

    private Program program;
    private boolean error;
    private List<ErrorMsg> errors;

    public ConstructTableSymbol(Program program) {
        this.program = program;
        this.error = false;
    }

    public SymbolTable Construct() {
        //P1 - construct global table
        BuildGlobalTableVisitor p1 = new BuildGlobalTableVisitor();
        program.accept(p1);

        error = p1.error(); //|| p2.error() || p3.error();
        errors = p1.getErrors();
        return p1.getGlobalTable();
    }

    public boolean error() {
        return error;
    }

    public List<ErrorMsg> getErrors() {
        return errors;
    }
}
