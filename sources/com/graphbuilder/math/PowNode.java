package com.graphbuilder.math;

public class PowNode extends OpNode {
    public String getSymbol() {
        return "^";
    }

    public PowNode(Expression expression, Expression expression2) {
        super(expression, expression2);
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        return Math.pow(this.leftChild.eval(varMap, funcMap), this.rightChild.eval(varMap, funcMap));
    }
}
