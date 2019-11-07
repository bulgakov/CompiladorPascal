/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.*;

/**
 *
 * @author mijail
 */
public interface GlobalTableVisitor {
    public void visit(Program n);
    public void visit(SubProgramBody n);
    public void visit(ParameterDefinition n);
    public void visit(ObjectDeclarativeItem n);
}
