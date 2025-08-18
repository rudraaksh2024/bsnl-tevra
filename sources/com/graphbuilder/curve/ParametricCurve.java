package com.graphbuilder.curve;

public abstract class ParametricCurve extends Curve {
    /* access modifiers changed from: protected */
    public abstract void eval(double[] dArr);

    public abstract int getSampleLimit();

    public ParametricCurve(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }
}
