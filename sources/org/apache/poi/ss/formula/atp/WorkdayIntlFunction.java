package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.usermodel.DateUtil;

final class WorkdayIntlFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new WorkdayIntlFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private WorkdayIntlFunction(ArgumentsEvaluator argumentsEvaluator) {
        this.evaluator = argumentsEvaluator;
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length < 2 || valueEvalArr.length > 4) {
            return ErrorEval.VALUE_INVALID;
        }
        int rowIndex = operationEvaluationContext.getRowIndex();
        int columnIndex = operationEvaluationContext.getColumnIndex();
        try {
            double evaluateDateArg = this.evaluator.evaluateDateArg(valueEvalArr[0], rowIndex, columnIndex);
            int i = 1;
            int floor = (int) Math.floor(this.evaluator.evaluateNumberArg(valueEvalArr[1], rowIndex, columnIndex));
            if (valueEvalArr.length >= 3) {
                if (valueEvalArr[2] != BlankEval.instance) {
                    i = (int) this.evaluator.evaluateNumberArg(valueEvalArr[2], rowIndex, columnIndex);
                }
                if (!WorkdayCalculator.instance.getValidWeekendTypes().contains(Integer.valueOf(i))) {
                    return ErrorEval.NUM_ERROR;
                }
            }
            return new NumberEval(DateUtil.getExcelDate(WorkdayCalculator.instance.calculateWorkdays(evaluateDateArg, floor, i, this.evaluator.evaluateDatesArg(valueEvalArr.length >= 4 ? valueEvalArr[3] : null, rowIndex, columnIndex))));
        } catch (EvaluationException unused) {
            return ErrorEval.VALUE_INVALID;
        }
    }
}
