package com.graphbuilder.math;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class DivNode extends OpNode {
    public String getSymbol() {
        return PackagingURIHelper.FORWARD_SLASH_STRING;
    }

    public DivNode(Expression expression, Expression expression2) {
        super(expression, expression2);
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        return this.leftChild.eval(varMap, funcMap) / this.rightChild.eval(varMap, funcMap);
    }
}
