package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Bin2Dec extends Fixed1ArgFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new Bin2Dec();

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        String str;
        String str2;
        boolean z;
        String str3;
        if (valueEval instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval;
            str = OperandResolver.coerceValueToString(refEval.getInnerValueEval(refEval.getFirstSheetIndex()));
        } else {
            str = OperandResolver.coerceValueToString(valueEval);
        }
        if (str.length() > 10) {
            return ErrorEval.NUM_ERROR;
        }
        if (str.length() < 10) {
            str2 = str;
            z = true;
        } else {
            str2 = str.substring(1);
            z = str.startsWith("0");
        }
        if (z) {
            try {
                str3 = String.valueOf(getDecimalValue(str2));
            } catch (NumberFormatException unused) {
                return ErrorEval.NUM_ERROR;
            }
        } else {
            str3 = ProcessIdUtil.DEFAULT_PROCESSID + (getDecimalValue(toggleBits(str2)) + 1);
        }
        return new NumberEval((double) Long.parseLong(str3));
    }

    private int getDecimalValue(String str) {
        int length = str.length();
        int i = length - 1;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            i3 += (int) (((double) Integer.parseInt(str.substring(i2, i4))) * Math.pow(2.0d, (double) i));
            i--;
            i2 = i4;
        }
        return i3;
    }

    private static String toggleBits(String str) {
        String binaryString = Long.toBinaryString(Long.parseLong(str, 2) ^ ((1 << str.length()) - 1));
        while (binaryString.length() < str.length()) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0]);
    }
}
