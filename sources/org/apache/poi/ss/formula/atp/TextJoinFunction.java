package org.apache.poi.ss.formula.atp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

final class TextJoinFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new TextJoinFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private TextJoinFunction(ArgumentsEvaluator argumentsEvaluator) {
        this.evaluator = argumentsEvaluator;
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length < 3 || valueEvalArr.length > 254) {
            return ErrorEval.VALUE_INVALID;
        }
        int rowIndex = operationEvaluationContext.getRowIndex();
        int columnIndex = operationEvaluationContext.getColumnIndex();
        try {
            List<ValueEval> values = getValues(valueEvalArr[0], rowIndex, columnIndex, true);
            boolean booleanValue = OperandResolver.coerceValueToBoolean(OperandResolver.getSingleValue(valueEvalArr[1], rowIndex, columnIndex), false).booleanValue();
            ArrayList arrayList = new ArrayList();
            for (int i = 2; i < valueEvalArr.length; i++) {
                for (ValueEval coerceValueToString : getValues(valueEvalArr[i], rowIndex, columnIndex, false)) {
                    String coerceValueToString2 = OperandResolver.coerceValueToString(coerceValueToString);
                    if (!booleanValue || (coerceValueToString2 != null && coerceValueToString2.length() > 0)) {
                        arrayList.add(coerceValueToString2);
                    }
                }
            }
            if (values.isEmpty()) {
                return new StringEval(String.join("", arrayList));
            }
            if (values.size() == 1) {
                return new StringEval(String.join(laxValueToString(values.get(0)), arrayList));
            }
            ArrayList arrayList2 = new ArrayList();
            for (ValueEval laxValueToString : values) {
                arrayList2.add(laxValueToString(laxValueToString));
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i2 > 0) {
                    sb.append((String) arrayList2.get((i2 - 1) % arrayList2.size()));
                }
                sb.append((String) arrayList.get(i2));
            }
            return new StringEval(sb.toString());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private String laxValueToString(ValueEval valueEval) {
        return valueEval instanceof MissingArgEval ? "" : OperandResolver.coerceValueToString(valueEval);
    }

    private List<ValueEval> getValues(ValueEval valueEval, int i, int i2, boolean z) throws EvaluationException {
        if (!(valueEval instanceof AreaEval)) {
            return Collections.singletonList(OperandResolver.getSingleValue(valueEval, i, i2));
        }
        AreaEval areaEval = (AreaEval) valueEval;
        ArrayList arrayList = new ArrayList();
        for (int lastRow = z ? areaEval.getLastRow() : areaEval.getFirstRow(); lastRow <= areaEval.getLastRow(); lastRow++) {
            for (int firstColumn = areaEval.getFirstColumn(); firstColumn <= areaEval.getLastColumn(); firstColumn++) {
                arrayList.add(OperandResolver.getSingleValue(areaEval.getAbsoluteValue(lastRow, firstColumn), lastRow, firstColumn));
            }
        }
        return arrayList;
    }
}
