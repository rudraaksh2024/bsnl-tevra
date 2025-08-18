package org.apache.poi.ss.formula.functions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;

public class WeekNum extends Fixed2ArgFunction implements FreeRefFunction {
    private static final NumberEval DEFAULT_RETURN_TYPE = new NumberEval(1.0d);
    private static final HashSet<Integer> VALID_RETURN_TYPES = new HashSet<>(Arrays.asList(new Integer[]{1, 2, 11, 12, 13, 14, 15, 16, 17, 21}));
    public static final FreeRefFunction instance = new WeekNum();
    private WeekFields FRIDAY_START = WeekFields.of(DayOfWeek.FRIDAY, 1);
    private WeekFields MONDAY_START = WeekFields.of(DayOfWeek.MONDAY, 1);
    private WeekFields SATURDAY_START = WeekFields.of(DayOfWeek.SATURDAY, 1);
    private WeekFields SUNDAY_START = WeekFields.of(DayOfWeek.SUNDAY, 1);
    private WeekFields THURSDAY_START = WeekFields.of(DayOfWeek.THURSDAY, 1);
    private WeekFields TUESDAY_START = WeekFields.of(DayOfWeek.TUESDAY, 1);
    private WeekFields WEDNESDAY_START = WeekFields.of(DayOfWeek.WEDNESDAY, 1);

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        int i3;
        try {
            try {
                LocalDate localDate = DateUtil.getJavaDate(NumericFunction.singleOperandEvaluate(valueEval, i, i2), false).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                try {
                    ValueEval singleValue = OperandResolver.getSingleValue(valueEval2, i, i2);
                    if (singleValue instanceof MissingArgEval) {
                        i3 = (int) DEFAULT_RETURN_TYPE.getNumberValue();
                    } else {
                        i3 = OperandResolver.coerceValueToInt(singleValue);
                    }
                    if (!VALID_RETURN_TYPES.contains(Integer.valueOf(i3))) {
                        return ErrorEval.NUM_ERROR;
                    }
                    return new NumberEval((double) getWeekNo(localDate, i3));
                } catch (EvaluationException unused) {
                    return ErrorEval.NUM_ERROR;
                }
            } catch (Exception unused2) {
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException unused3) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    public int getWeekNo(LocalDate localDate, int i) {
        if (i == 1 || i == 17) {
            return localDate.get(this.SUNDAY_START.weekOfYear());
        }
        if (i == 2 || i == 11) {
            return localDate.get(this.MONDAY_START.weekOfYear());
        }
        if (i == 12) {
            return localDate.get(this.TUESDAY_START.weekOfYear());
        }
        if (i == 13) {
            return localDate.get(this.WEDNESDAY_START.weekOfYear());
        }
        if (i == 14) {
            return localDate.get(this.THURSDAY_START.weekOfYear());
        }
        if (i == 15) {
            return localDate.get(this.FRIDAY_START.weekOfYear());
        }
        if (i == 16) {
            return localDate.get(this.SATURDAY_START.weekOfYear());
        }
        return localDate.get(WeekFields.ISO.weekOfYear());
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length == 1) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], DEFAULT_RETURN_TYPE);
        }
        if (valueEvalArr.length == 2) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], valueEvalArr[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }
}
