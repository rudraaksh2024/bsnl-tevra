package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils;

public final class Lookup extends Var2or3ArgFunction {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        LookupUtils.ValueVector valueVector;
        LookupUtils.ValueVector valueVector2;
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            TwoDEval resolveTableArrayArg = LookupUtils.resolveTableArrayArg(valueEval2);
            if (resolveTableArrayArg.getWidth() > resolveTableArrayArg.getHeight()) {
                valueVector = createVector(resolveTableArrayArg.getRow(0));
                valueVector2 = createVector(resolveTableArrayArg.getRow(resolveTableArrayArg.getHeight() - 1));
            } else {
                valueVector = createVector(resolveTableArrayArg.getColumn(0));
                valueVector2 = createVector(resolveTableArrayArg.getColumn(resolveTableArrayArg.getWidth() - 1));
            }
            return valueVector2.getItem(LookupUtils.lookupFirstIndexOfValue(singleValue, valueVector, true));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            TwoDEval resolveTableArrayArg = LookupUtils.resolveTableArrayArg(valueEval2);
            TwoDEval resolveTableArrayArg2 = LookupUtils.resolveTableArrayArg(valueEval3);
            LookupUtils.ValueVector createVector = createVector(resolveTableArrayArg);
            LookupUtils.ValueVector createVector2 = createVector(resolveTableArrayArg2);
            if (createVector.getSize() <= createVector2.getSize()) {
                return createVector2.getItem(LookupUtils.lookupFirstIndexOfValue(singleValue, createVector, true));
            }
            throw new RuntimeException("Lookup vector and result vector of differing sizes not supported yet");
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static LookupUtils.ValueVector createVector(TwoDEval twoDEval) {
        LookupUtils.ValueVector createVector = LookupUtils.createVector(twoDEval);
        if (createVector != null) {
            return createVector;
        }
        throw new RuntimeException("non-vector lookup or result areas not supported yet");
    }
}
