package com.graphbuilder.math;

public class ValNode extends Expression {
    protected double val;

    public ValNode(double d) {
        this.val = d;
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        return this.val;
    }

    public double getValue() {
        return this.val;
    }

    public void setValue(double d) {
        this.val = d;
    }
}
