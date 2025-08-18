package com.graphbuilder.math;

import com.graphbuilder.struc.Bag;
import org.apache.logging.log4j.util.ProcessIdUtil;

public abstract class Expression {
    protected Expression parent = null;

    public abstract double eval(VarMap varMap, FuncMap funcMap);

    public boolean isDescendent(Expression expression) {
        while (this != null) {
            if (this == expression) {
                return true;
            }
            this = this.parent;
        }
        return false;
    }

    public Expression getParent() {
        return this.parent;
    }

    /* access modifiers changed from: protected */
    public void checkBeforeAccept(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression cannot be null");
        } else if (expression.parent != null) {
            throw new IllegalArgumentException("expression must be removed parent");
        } else if (isDescendent(expression)) {
            throw new IllegalArgumentException("cyclic reference");
        }
    }

    public String[] getVariableNames() {
        return getTermNames(true);
    }

    public String[] getFunctionNames() {
        return getTermNames(false);
    }

    private String[] getTermNames(boolean z) {
        Bag bag = new Bag();
        getTermNames(this, bag, z);
        int size = bag.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) bag.get(i);
        }
        return strArr;
    }

    private static void getTermNames(Expression expression, Bag bag, boolean z) {
        if (expression instanceof OpNode) {
            OpNode opNode = (OpNode) expression;
            getTermNames(opNode.leftChild, bag, z);
            getTermNames(opNode.rightChild, bag, z);
        } else if (expression instanceof VarNode) {
            if (z) {
                VarNode varNode = (VarNode) expression;
                if (!bag.contains(varNode.name)) {
                    bag.add(varNode.name);
                }
            }
        } else if (expression instanceof FuncNode) {
            FuncNode funcNode = (FuncNode) expression;
            if (!z && !bag.contains(funcNode.name)) {
                bag.add(funcNode.name);
            }
            for (int i = 0; i < funcNode.numChildren(); i++) {
                getTermNames(funcNode.child(i), bag, z);
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        toString(this, stringBuffer);
        return stringBuffer.toString();
    }

    private static void toString(Expression expression, StringBuffer stringBuffer) {
        if (expression instanceof OpNode) {
            OpNode opNode = (OpNode) expression;
            stringBuffer.append("(");
            toString(opNode.leftChild, stringBuffer);
            stringBuffer.append(opNode.getSymbol());
            toString(opNode.rightChild, stringBuffer);
            stringBuffer.append(")");
        } else if (expression instanceof TermNode) {
            TermNode termNode = (TermNode) expression;
            if (termNode.getNegate()) {
                stringBuffer.append("(");
                stringBuffer.append(ProcessIdUtil.DEFAULT_PROCESSID);
            }
            stringBuffer.append(termNode.getName());
            if (termNode instanceof FuncNode) {
                FuncNode funcNode = (FuncNode) termNode;
                stringBuffer.append("(");
                if (funcNode.numChildren() > 0) {
                    toString(funcNode.child(0), stringBuffer);
                }
                for (int i = 1; i < funcNode.numChildren(); i++) {
                    stringBuffer.append(", ");
                    toString(funcNode.child(i), stringBuffer);
                }
                stringBuffer.append(")");
            }
            if (termNode.getNegate()) {
                stringBuffer.append(")");
            }
        } else if (expression instanceof ValNode) {
            stringBuffer.append(((ValNode) expression).val);
        }
    }
}
