package org.apache.poi.ss.formula.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.poi.ss.formula.LazyRefEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Subtotal implements Function {
    private static Function findFunction(int i) throws EvaluationException {
        switch (i) {
            case 1:
                return AggregateFunction.subtotalInstance(AggregateFunction.AVERAGE, true);
            case 2:
                return Count.subtotalInstance(true);
            case 3:
                return Counta.subtotalInstance(true);
            case 4:
                return AggregateFunction.subtotalInstance(AggregateFunction.MAX, true);
            case 5:
                return AggregateFunction.subtotalInstance(AggregateFunction.MIN, true);
            case 6:
                return AggregateFunction.subtotalInstance(AggregateFunction.PRODUCT, true);
            case 7:
                return AggregateFunction.subtotalInstance(AggregateFunction.STDEV, true);
            case 8:
                throw new NotImplementedFunctionException("STDEVP");
            case 9:
                return AggregateFunction.subtotalInstance(AggregateFunction.SUM, true);
            case 10:
                throw new NotImplementedFunctionException("VAR");
            case 11:
                throw new NotImplementedFunctionException("VARP");
            default:
                switch (i) {
                    case 101:
                        return AggregateFunction.subtotalInstance(AggregateFunction.AVERAGE, false);
                    case 102:
                        return Count.subtotalInstance(false);
                    case 103:
                        return Counta.subtotalInstance(false);
                    case 104:
                        return AggregateFunction.subtotalInstance(AggregateFunction.MAX, false);
                    case 105:
                        return AggregateFunction.subtotalInstance(AggregateFunction.MIN, false);
                    case 106:
                        return AggregateFunction.subtotalInstance(AggregateFunction.PRODUCT, false);
                    case 107:
                        return AggregateFunction.subtotalInstance(AggregateFunction.STDEV, false);
                    case 108:
                        throw new NotImplementedFunctionException("STDEVP SUBTOTAL with 'exclude hidden values' option");
                    case 109:
                        return AggregateFunction.subtotalInstance(AggregateFunction.SUM, false);
                    case 110:
                        throw new NotImplementedFunctionException("VAR SUBTOTAL with 'exclude hidden values' option");
                    case 111:
                        throw new NotImplementedFunctionException("VARP SUBTOTAL with 'exclude hidden values' option");
                    default:
                        throw EvaluationException.invalidValue();
                }
        }
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length - 1 < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            int coerceValueToInt = OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEvalArr[0], i, i2));
            Function findFunction = findFunction(coerceValueToInt);
            ArrayList arrayList = new ArrayList(Arrays.asList(valueEvalArr).subList(1, valueEvalArr.length));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ValueEval valueEval = (ValueEval) it.next();
                if (valueEval instanceof LazyRefEval) {
                    LazyRefEval lazyRefEval = (LazyRefEval) valueEval;
                    if (lazyRefEval.isSubTotal()) {
                        it.remove();
                    }
                    if (coerceValueToInt > 100 && lazyRefEval.isRowHidden()) {
                        it.remove();
                    }
                }
            }
            return findFunction.evaluate((ValueEval[]) arrayList.toArray(new ValueEval[0]), i, i2);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
