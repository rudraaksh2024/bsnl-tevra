package com.graphbuilder.math;

import org.apache.logging.log4j.util.ProcessIdUtil;

public class SubNode extends OpNode {
    public String getSymbol() {
        return ProcessIdUtil.DEFAULT_PROCESSID;
    }

    public SubNode(Expression expression, Expression expression2) {
        super(expression, expression2);
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        return this.leftChild.eval(varMap, funcMap) - this.rightChild.eval(varMap, funcMap);
    }
}
