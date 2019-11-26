/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorpascal;

import ast.Program;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import table.ErrorMsg;
import table.SymbolTable;
import visitors.ConstructTableSymbol;
import visitors.ICGVisitor;
import visitors.TypeCheckVisitor;

/**
 *
 * @author mijai
 */
public class PascalCompilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        try {
            if (args.length == 0){
                System.out.println("No source file!");
                return;
            }
                
            File codeFile = new File(args[0]);
            if (!codeFile.canRead()) {
                System.out.println("Unable to read source file!");
                return;
            }
            
            fileToTokens(args[0]);
            
            ComplexSymbolFactory csf = new ComplexSymbolFactory();
            PascalScanner scanner = new PascalScanner(new FileReader(args[0]), csf);
            PascalParser parser = new PascalParser(scanner,csf);
            Symbol root = parser.parse();
            
            if(root.value != null) {
                Program program = (Program)root.value;
                programToXML(program);
                
//                if (parser.error()) {
//                    for (ErrorMsg e : parser.getErrors())
//                        System.err.println(e.toString());
//                    System.exit(1);
//                }
                
                ConstructTableSymbol construct = new ConstructTableSymbol(program);
                SymbolTable global = construct.Construct();

                tableToXML(global);
                
                TypeCheckVisitor typeCheck = new TypeCheckVisitor(global);
                program.accept(typeCheck);

                if (parser.error() || construct.error() || typeCheck.error()) {
                    for (ErrorMsg e : parser.getErrors())
                        System.err.println(e.toString());
                    for (ErrorMsg e : construct.getErrors())
                        System.err.println(e.toString());
                    for (ErrorMsg e : typeCheck.getErrors())
                        System.err.println(e.toString());
                    System.exit(1);
                }
//                
//                ICGVisitor cgVisitor = new ICGVisitor(global);
//                cgVisitor.visit(program); // Unfinished
//                
//                cgVisitor.writePseudoCode("PseudoCodigo.txt");
            } else {
                for (ErrorMsg e : parser.getErrors())
                    System.err.println(e.toString());
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Unexpected compiler error: " + e.toString());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
    
    private static void fileToTokens(String path){
        try {
            File file = new File("Tokens.txt");
            try (FileWriter fileWriter = new FileWriter(file)) {
                PascalScanner scanner = new PascalScanner(new FileReader(path), new ComplexSymbolFactory());
                scanner.yybegin(0);
                java_cup.runtime.Symbol token;
                while((token=scanner.next_token()).sym != sym.EOF){
                    fileWriter.append(token.toString());
                    fileWriter.append(System.lineSeparator());
                }
                fileWriter.flush();
            }
        } catch (Exception e) {
            System.err.println("error: " + e.toString());
            e.printStackTrace(System.err);
        }
    }
    
    private static void programToXML(Program ast) {
        try {
            File file = new File("Program.xml");
            XStream xstream = new XStream(new DomDriver());
            xstream.useAttributeFor(ast.ASTNode.class, "line");
            xstream.useAttributeFor(ast.ASTNode.class, "column");
            xstream.useAttributeFor(ast.ASTNode.class, "depth");
            xstream.useAttributeFor(ast.ProcedureDeclaration.class, "procedure");
            xstream.useAttributeFor(type.Type.class, "size");
            xstream.useAttributeFor(type.Type.class, "name");
            xstream.omitField(ast.ASTNode.class, "type");
            xstream.omitField(type.ProcType.class, "Table");
            
            try (FileWriter fileWriter = new FileWriter(file)) {
                xstream.toXML(ast, fileWriter);
                fileWriter.flush();
            }
        } catch (Exception e) {
            System.err.println("error: " + e.toString());
            e.printStackTrace(System.err);
        }
    }
    
    private static void tableToXML(SymbolTable table) {
        try {
            File file = new File("Table.xml");
            XStream xstream = new XStream(new DomDriver());
            xstream.useAttributeFor(table.SymbolTable.class, "name");
            xstream.useAttributeFor(table.SymbolTable.class, "depth");
            xstream.useAttributeFor(table.SymbolTable.class, "updated");
            xstream.useAttributeFor(table.SymbolInfo.class, "id");
            xstream.useAttributeFor(table.SymbolInfo.class, "belonging");
            xstream.useAttributeFor(table.SymbolInfo.class, "type");
            xstream.useAttributeFor(table.SymbolInfo.class, "offset");
            xstream.useAttributeFor(table.SymbolType.class, "id");
            xstream.useAttributeFor(table.SymbolType.class, "belonging");
            xstream.useAttributeFor(table.SymbolType.class, "type");
            xstream.useAttributeFor(table.SymbolType.class, "offset");
            xstream.omitField(table.SymbolTable.class, "parent");
            
            try (FileWriter fileWriter = new FileWriter(file)) {
                xstream.toXML(table, fileWriter);
                fileWriter.flush();
            }
        } catch (Exception e) {
            System.err.println("error: " + e.toString());
            e.printStackTrace(System.err);
        }
    }
}
