package io.ccl;

public class AttributeCompilationException extends Exception {

    public AttributeCompilationException(){
        super("Unspecified error in Attribute Compiler.");
    }

    public AttributeCompilationException(String message){
        super(message);
    }
}
