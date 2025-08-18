package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.formula.functions.Countif;

abstract class Baseifs implements FreeRefFunction {

    protected interface Aggregator {
        void addValue(ValueEval valueEval);

        ValueEval getResult();
    }

    /* access modifiers changed from: protected */
    public abstract Aggregator createAggregator();

    /* access modifiers changed from: protected */
    public abstract boolean hasInitialRange();

    Baseifs() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        AreaEval areaEval;
        int hasInitialRange = hasInitialRange();
        if (valueEvalArr.length < hasInitialRange + 2 || valueEvalArr.length % 2 != hasInitialRange) {
            return ErrorEval.VALUE_INVALID;
        }
        int i = 0;
        if (hasInitialRange != 0) {
            try {
                areaEval = convertRangeArg(valueEvalArr[0]);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        } else {
            areaEval = null;
        }
        int length = (valueEvalArr.length - hasInitialRange) / 2;
        AreaEval[] areaEvalArr = new AreaEval[length];
        CountUtils.I_MatchPredicate[] i_MatchPredicateArr = new CountUtils.I_MatchPredicate[length];
        while (hasInitialRange < valueEvalArr.length) {
            areaEvalArr[i] = convertRangeArg(valueEvalArr[hasInitialRange]);
            i_MatchPredicateArr[i] = Countif.createCriteriaPredicate(valueEvalArr[hasInitialRange + 1], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            hasInitialRange += 2;
            i++;
        }
        validateCriteriaRanges(areaEval, areaEvalArr);
        validateCriteria(i_MatchPredicateArr);
        return aggregateMatchingCells(createAggregator(), areaEval, areaEvalArr, i_MatchPredicateArr);
    }

    private static void validateCriteriaRanges(AreaEval areaEval, AreaEval[] areaEvalArr) throws EvaluationException {
        int i = 0;
        int height = areaEvalArr[0].getHeight();
        int width = areaEvalArr[0].getWidth();
        if (areaEval == null || (areaEval.getHeight() == height && areaEval.getWidth() == width)) {
            int length = areaEvalArr.length;
            while (i < length) {
                AreaEval areaEval2 = areaEvalArr[i];
                if (areaEval2.getHeight() == height && areaEval2.getWidth() == width) {
                    i++;
                } else {
                    throw EvaluationException.invalidValue();
                }
            }
            return;
        }
        throw EvaluationException.invalidValue();
    }

    private static void validateCriteria(CountUtils.I_MatchPredicate[] i_MatchPredicateArr) throws EvaluationException {
        int length = i_MatchPredicateArr.length;
        int i = 0;
        while (i < length) {
            Countif.ErrorMatcher errorMatcher = i_MatchPredicateArr[i];
            if (!(errorMatcher instanceof Countif.ErrorMatcher)) {
                i++;
            } else {
                throw new EvaluationException(ErrorEval.valueOf(errorMatcher.getValue()));
            }
        }
    }

    private static ValueEval aggregateMatchingCells(Aggregator aggregator, AreaEval areaEval, AreaEval[] areaEvalArr, CountUtils.I_MatchPredicate[] i_MatchPredicateArr) throws EvaluationException {
        boolean z;
        int height = areaEvalArr[0].getHeight();
        int width = areaEvalArr[0].getWidth();
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int i3 = 0;
                while (true) {
                    if (i3 >= areaEvalArr.length) {
                        z = true;
                        break;
                    }
                    AreaEval areaEval2 = areaEvalArr[i3];
                    CountUtils.I_MatchPredicate i_MatchPredicate = i_MatchPredicateArr[i3];
                    if (i_MatchPredicate == null || !i_MatchPredicate.matches(areaEval2.getRelativeValue(i, i2))) {
                        z = false;
                    } else {
                        i3++;
                    }
                }
                z = false;
                if (z) {
                    if (areaEval != null) {
                        ValueEval relativeValue = areaEval.getRelativeValue(i, i2);
                        if (!(relativeValue instanceof ErrorEval)) {
                            aggregator.addValue(relativeValue);
                        } else {
                            throw new EvaluationException((ErrorEval) relativeValue);
                        }
                    } else {
                        aggregator.addValue((ValueEval) null);
                    }
                }
            }
        }
        return aggregator.getResult();
    }

    protected static AreaEval convertRangeArg(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof AreaEval) {
            return (AreaEval) valueEval;
        }
        if (valueEval instanceof RefEval) {
            return ((RefEval) valueEval).offset(0, 0, 0, 0);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }
}
