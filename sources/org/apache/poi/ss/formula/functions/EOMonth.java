package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class EOMonth implements FreeRefFunction {
    public static final FreeRefFunction instance = new EOMonth();

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double singleOperandEvaluate = NumericFunction.singleOperandEvaluate(valueEvalArr[0], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            int singleOperandEvaluate2 = (int) NumericFunction.singleOperandEvaluate(valueEvalArr[1], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            if (singleOperandEvaluate >= 0.0d && singleOperandEvaluate < 1.0d) {
                singleOperandEvaluate = 1.0d;
            }
            Date javaDate = DateUtil.getJavaDate(singleOperandEvaluate, false);
            Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
            localeCalendar.setTime(javaDate);
            localeCalendar.clear(10);
            localeCalendar.set(11, 0);
            localeCalendar.clear(12);
            localeCalendar.clear(13);
            localeCalendar.clear(14);
            localeCalendar.add(2, singleOperandEvaluate2 + 1);
            localeCalendar.set(5, 1);
            localeCalendar.add(5, -1);
            return new NumberEval(DateUtil.getExcelDate(localeCalendar.getTime()));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
