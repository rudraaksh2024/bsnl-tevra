package com.graphbuilder.math;

public class ExpressionParseException extends RuntimeException {
    private String descrip;
    private int index;

    public ExpressionParseException(String str, int i) {
        this.descrip = str;
        this.index = i;
    }

    public String getDescription() {
        return this.descrip;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return "(" + this.index + ") " + this.descrip;
    }
}
