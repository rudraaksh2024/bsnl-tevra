package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class Days360 extends Var2or3ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            return new NumberEval(evaluate(NumericFunction.singleOperandEvaluate(valueEval, i, i2), NumericFunction.singleOperandEvaluate(valueEval2, i, i2), false));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            double singleOperandEvaluate = NumericFunction.singleOperandEvaluate(valueEval, i, i2);
            double singleOperandEvaluate2 = NumericFunction.singleOperandEvaluate(valueEval2, i, i2);
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval3, i, i2);
            boolean z = false;
            Boolean coerceValueToBoolean = OperandResolver.coerceValueToBoolean(singleValue, false);
            if (coerceValueToBoolean != null && coerceValueToBoolean.booleanValue()) {
                z = true;
            }
            return new NumberEval(evaluate(singleOperandEvaluate, singleOperandEvaluate2, z));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static double evaluate(double d, double d2, boolean z) {
        Calendar date = getDate(d);
        Calendar date2 = getDate(d2);
        int[] startingDate = getStartingDate(date, z);
        int[] endingDate = getEndingDate(date2, startingDate, z);
        return (((((double) endingDate[0]) * 360.0d) + (((double) endingDate[1]) * 30.0d)) + ((double) endingDate[2])) - (((((double) startingDate[0]) * 360.0d) + (((double) startingDate[1]) * 30.0d)) + ((double) startingDate[2]));
    }

    private static Calendar getDate(double d) {
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
        localeCalendar.setTime(DateUtil.getJavaDate(d, false));
        return localeCalendar;
    }

    private static int[] getStartingDate(Calendar calendar, boolean z) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = 30;
        int min = Math.min(30, calendar.get(5));
        if (z || !isLastDayOfMonth(calendar)) {
            i3 = min;
        }
        return new int[]{i, i2, i3};
    }

    private static int[] getEndingDate(Calendar calendar, int[] iArr, boolean z) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = 30;
        int min = Math.min(30, calendar.get(5));
        if (z || calendar.get(5) != 31) {
            i3 = min;
        } else if (iArr[2] < 30) {
            calendar.set(5, 1);
            calendar.add(2, 1);
            i = calendar.get(1);
            i2 = calendar.get(2);
            i3 = 1;
        }
        return new int[]{i, i2, i3};
    }

    private static boolean isLastDayOfMonth(Calendar calendar) {
        return calendar.get(5) == calendar.getActualMaximum(5);
    }
}
