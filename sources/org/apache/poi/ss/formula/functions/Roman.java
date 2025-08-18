package org.apache.poi.ss.formula.functions;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Roman extends Fixed2ArgFunction {
    private static final String[][] REPLACEMENTS = {new String[]{"XLV", "VL", "XCV", "VC", "CDL", "LD", "CML", "LM", "CMVC", "LMVL"}, new String[]{"CDXC", "LDXL", "CDVC", "LDVL", "CMXC", "LMXL", "XCIX", "VCIV", "XLIX", "VLIV"}, new String[]{"XLIX", "IL", "XCIX", "IC", "CDXC", "XD", "CDVC", "XDV", "CDIC", "XDIX", "LMVL", "XMV", "CMIC", "XMIX", "CMXC", "XM"}, new String[]{"XDV", "VD", "XDIX", "VDIV", "XMV", "VM", "XMIX", "VMIV"}, new String[]{"VDIV", "ID", "VMIV", "IM"}};
    private static final String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "IV", "I"};
    private static final int[] VALUES = {1000, TypedValues.Custom.TYPE_INT, 500, FontHeader.REGULAR_WEIGHT, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            int coerceValueToInt = OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEval, i, i2));
            if (coerceValueToInt < 0) {
                return ErrorEval.VALUE_INVALID;
            }
            if (coerceValueToInt > 3999) {
                return ErrorEval.VALUE_INVALID;
            }
            if (coerceValueToInt == 0) {
                return new StringEval("");
            }
            try {
                int coerceValueToInt2 = OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEval2, i, i2));
                if (coerceValueToInt2 > 4 || coerceValueToInt2 < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                String integerToRoman = integerToRoman(coerceValueToInt);
                if (coerceValueToInt2 == 0) {
                    return new StringEval(integerToRoman);
                }
                return new StringEval(makeConcise(integerToRoman, coerceValueToInt2));
            } catch (EvaluationException unused) {
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException unused2) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    private String integerToRoman(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 13; i2++) {
            while (true) {
                int i3 = VALUES[i2];
                if (i < i3) {
                    break;
                }
                i -= i3;
                sb.append(ROMAN[i2]);
            }
        }
        return sb.toString();
    }

    public String makeConcise(String str, int i) {
        int i2 = 0;
        while (i2 <= i && i2 <= 4 && i > 0) {
            if (i2 != 1 || i <= 1) {
                String[] strArr = REPLACEMENTS[i2];
                for (int i3 = 0; i3 < strArr.length; i3 += 2) {
                    str = str.replace(strArr[i3], strArr[i3 + 1]);
                }
            }
            i2++;
        }
        return str;
    }
}
