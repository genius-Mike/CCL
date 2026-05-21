package io.ccl;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static io.ccl.Main.selectedDirectory;

public class CCLCompiler {
    private String classname;
    private String[] attributes;
    private final compilerGUI source;

    public CCLCompiler(compilerGUI source){
        this.source = source;
    }

    public void compile(){
        classname = source.getClassField();
        attributes = source.getAttributeField().split("\\n");

        try {
            List<String> parsedAttributes = parseAttributes();
            try {
                FileWriter fw = new FileWriter(selectedDirectory.toString() + "/" + classname + ".java");
                fw.append("public class " + classname + " {\n");
                for(String s : parsedAttributes){
                    fw.append(s).append("\n");
                }
                for(String s : attributes){
                    List<String> methods = methodBuilder(s);
                    fw.append("\n");
                    fw.append(methods.get(0));
                    fw.append("\n");
                    fw.append(methods.get(1));
                }
                fw.append("}");
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (AttributeCompilationException e) {
            JFrame errormsg = new JFrame();
            errormsg.setSize(300, 100);
            errormsg.setLocationRelativeTo(null);
            errormsg.add(new JLabel(e.getMessage()));
            errormsg.setVisible(true);
            throw new RuntimeException(e);
        }
    }

    private List<String> parseAttributes() throws AttributeCompilationException {
        List<String> parsed = new ArrayList<>(List.of());

        for(String s : attributes){
            s = s.replace("\r", "");
            String parsedLine = "";

            String accessor = s.substring(0, 1);
            parsedLine = switch (accessor) {
                case "+" -> parsedLine.concat("    public ");
                case "-" -> parsedLine.concat("    private ");
                case "#" -> parsedLine.concat("    protected ");
                default -> throw new AttributeCompilationException("Invalid accessor signature");
            };

            String type = s.substring(s.indexOf(":")+1);
            parsedLine = switch (type) {
                case "y" -> parsedLine.concat("byte ");
                case "s" -> parsedLine.concat("short ");
                case "i" -> parsedLine.concat("int ");
                case "l" -> parsedLine.concat("long ");
                case "f" -> parsedLine.concat("float ");
                case "d" -> parsedLine.concat("double ");
                case "b" -> parsedLine.concat("boolean ");
                case "c" -> parsedLine.concat("char ");
                case "S" -> parsedLine.concat("String ");
                default -> throw new AttributeCompilationException("Invalid type signature");
            };

            parsedLine = parsedLine.concat(s.substring(1, s.indexOf(":")) + ";");
            parsed.add(parsedLine);
        }
        return parsed;
    }

    private List<String> methodBuilder(String attribute) {
        attribute = attribute.replace("\r", "");

        String name = attribute.substring(1, attribute.indexOf(":"));
        String parsedName = attribute.substring(1, 2).toUpperCase(Locale.ROOT) + attribute.substring(2, attribute.indexOf(":"));

        String type = switch (attribute.substring(attribute.indexOf(":") + 1)) {
            case "y" -> "byte";
            case "s" -> "short";
            case "i" -> "int";
            case "l" -> "long";
            case "f" -> "float";
            case "d" -> "double";
            case "b" -> "boolean";
            case "c" -> "char";
            case "S" -> "String";
            default -> "";
        };

        String setter = "    public void set" + parsedName + "(" + type + " " + name + "){\n"
                + "        this." + name + " = " + name + ";\n"
                + "}\n";
        String getter = "    public " + type + " get" + parsedName + "(){\n"
                + "        return " + name + ";\n"
                + "}\n";
        return List.of(getter, setter);
    }
}
