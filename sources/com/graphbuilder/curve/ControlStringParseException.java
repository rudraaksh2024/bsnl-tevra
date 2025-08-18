package com.graphbuilder.curve;

import com.graphbuilder.math.ExpressionParseException;

public class ControlStringParseException extends RuntimeException {
    private String descrip;
    private ExpressionParseException epe;
    private int fromIndex;
    private int toIndex;

    public ControlStringParseException(String str) {
        this.fromIndex = -1;
        this.toIndex = -1;
        this.epe = null;
        this.descrip = str;
    }

    public ControlStringParseException(String str, int i) {
        this.epe = null;
        this.descrip = str;
        this.fromIndex = i;
        this.toIndex = i;
    }

    public ControlStringParseException(String str, int i, int i2) {
        this.epe = null;
        this.descrip = str;
        this.fromIndex = i;
        this.toIndex = i2;
    }

    public ControlStringParseException(String str, int i, int i2, ExpressionParseException expressionParseException) {
        this.descrip = str;
        this.fromIndex = i;
        this.toIndex = i2;
        this.epe = expressionParseException;
    }

    public int getFromIndex() {
        return this.fromIndex;
    }

    public int getToIndex() {
        return this.toIndex;
    }

    public String getDescription() {
        return this.descrip;
    }

    public ExpressionParseException getExpressionParseException() {
        return this.epe;
    }

    public String toString() {
        String str = this.epe != null ? "\n" + this.epe.toString() : "";
        int i = this.fromIndex;
        if (i == -1 && this.toIndex == -1) {
            return this.descrip + str;
        }
        if (i == this.toIndex) {
            return this.descrip + " : [" + this.toIndex + "]" + str;
        }
        return this.descrip + " : [" + this.fromIndex + ", " + this.toIndex + "]" + str;
    }
}
