package com.graphbuilder.math;

import com.graphbuilder.struc.Bag;

public class FuncNode extends TermNode {
    private Bag bag = new Bag(1);
    private double[] of = new double[1];

    public FuncNode(String str, boolean z) {
        super(str, z);
    }

    public void add(Expression expression) {
        insert(expression, this.bag.size());
    }

    public void insert(Expression expression, int i) {
        checkBeforeAccept(expression);
        int capacity = this.bag.getCapacity();
        this.bag.insert(expression, i);
        int capacity2 = this.bag.getCapacity();
        if (capacity != capacity2) {
            this.of = new double[capacity2];
        }
        expression.parent = this;
    }

    public void remove(Expression expression) {
        int size = this.bag.size();
        this.bag.remove((Object) expression);
        if (size != this.bag.size()) {
            expression.parent = null;
        }
    }

    public int numChildren() {
        return this.bag.size();
    }

    public Expression child(int i) {
        return (Expression) this.bag.get(i);
    }

    public double eval(VarMap varMap, FuncMap funcMap) {
        int size = this.bag.size();
        for (int i = 0; i < size; i++) {
            this.of[i] = child(i).eval(varMap, funcMap);
        }
        double of2 = funcMap.getFunction(this.name, size).of(this.of, size);
        return this.negate ? -of2 : of2;
    }
}
