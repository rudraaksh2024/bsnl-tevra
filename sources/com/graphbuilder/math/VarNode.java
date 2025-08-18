package com.graphbuilder.math;

public class VarNode extends TermNode {
    public VarNode(String str, boolean z) {
        super(str, z);
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        double value = varMap.getValue(this.name);
        return this.negate ? -value : value;
    }
}
