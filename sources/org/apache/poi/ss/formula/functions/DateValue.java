package org.apache.poi.ss.formula.functions;

import java.time.DateTimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateParser;

public class DateValue extends Fixed1ArgFunction {
    private static final Logger LOG = LogManager.getLogger((Class<?>) DateValue.class);

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        try {
            String coerceValueToString = OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2));
            if (coerceValueToString != null) {
                if (!coerceValueToString.isEmpty()) {
                    return new NumberEval(DateUtil.getExcelDate(DateParser.parseLocalDate(coerceValueToString)));
                }
            }
            return BlankEval.instance;
        } catch (DateTimeException e) {
            LOG.atInfo().log("Failed to parse date", (Object) e);
            return ErrorEval.VALUE_INVALID;
        } catch (EvaluationException e2) {
            return e2.getErrorEval();
        }
    }
}
