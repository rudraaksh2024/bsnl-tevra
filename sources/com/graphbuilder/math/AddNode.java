package com.graphbuilder.math;

public class AddNode extends OpNode {
    public String getSymbol() {
        return "+";
    }

    public AddNode(Expression expression, Expression expression2) {
        super(expression, expression2);
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        return this.leftChild.eval(varMap, funcMap) + this.rightChild.eval(varMap, funcMap);
    }
}
