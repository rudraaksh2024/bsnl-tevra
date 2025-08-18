package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public final class Today {
    public static ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 0) {
            return ErrorEval.VALUE_INVALID;
        }
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
        localeCalendar.clear(10);
        localeCalendar.set(11, 0);
        localeCalendar.clear(12);
        localeCalendar.clear(13);
        localeCalendar.clear(14);
        return new NumberEval(DateUtil.getExcelDate(localeCalendar.getTime()));
    }
}
