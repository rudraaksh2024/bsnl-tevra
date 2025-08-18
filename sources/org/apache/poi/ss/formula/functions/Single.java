package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Single implements FreeRefFunction {
    public static final FreeRefFunction instance = new Single();

    private Single() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        int columnIndex = operationEvaluationContext.getColumnIndex();
        int rowIndex = operationEvaluationContext.getRowIndex();
        ValueEval valueEval = null;
        for (AreaEvalBase areaEvalBase : valueEvalArr) {
            if (areaEvalBase instanceof AreaEvalBase) {
                AreaEvalBase areaEvalBase2 = areaEvalBase;
                if (areaEvalBase2.contains(rowIndex, columnIndex)) {
                    if (valueEval != null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    valueEval = areaEvalBase2.getAbsoluteValue(rowIndex, columnIndex);
                } else if (areaEvalBase2.containsRow(rowIndex)) {
                    if (valueEval != null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    valueEval = areaEvalBase2.getAbsoluteValue(rowIndex, areaEvalBase2.getFirstColumn());
                } else if (!areaEvalBase2.containsColumn(columnIndex)) {
                    continue;
                } else if (valueEval != null) {
                    return ErrorEval.VALUE_INVALID;
                } else {
                    valueEval = areaEvalBase2.getAbsoluteValue(areaEvalBase2.getFirstRow(), columnIndex);
                }
            }
        }
        return valueEval != null ? valueEval : ErrorEval.VALUE_INVALID;
    }
}
