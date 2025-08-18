package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.Table;

public final class Indirect implements FreeRefFunction {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) Indirect.class);
    public static final FreeRefFunction instance = new Indirect();

    private Indirect() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        boolean z = true;
        if (valueEvalArr.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            String coerceValueToString = OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEvalArr[0], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex()));
            int length = valueEvalArr.length;
            if (length != 1) {
                if (length != 2) {
                    return ErrorEval.VALUE_INVALID;
                }
                z = evaluateBooleanArg(valueEvalArr[1], operationEvaluationContext);
            }
            return evaluateIndirect(operationEvaluationContext, coerceValueToString, z);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static boolean evaluateBooleanArg(ValueEval valueEval, OperationEvaluationContext operationEvaluationContext) throws EvaluationException {
        ValueEval singleValue = OperandResolver.getSingleValue(valueEval, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
        if (singleValue == BlankEval.instance || singleValue == MissingArgEval.instance) {
            return false;
        }
        return OperandResolver.coerceValueToBoolean(singleValue, false).booleanValue();
    }

    private static ValueEval evaluateIndirect(OperationEvaluationContext operationEvaluationContext, String str, boolean z) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int lastIndexOf = str.lastIndexOf(33);
        if (lastIndexOf < 0) {
            str4 = str;
            str3 = null;
            str2 = null;
        } else {
            String[] parseWorkbookAndSheetName = parseWorkbookAndSheetName(str.subSequence(0, lastIndexOf));
            if (parseWorkbookAndSheetName == null) {
                return ErrorEval.REF_INVALID;
            }
            str3 = parseWorkbookAndSheetName[0];
            String str7 = parseWorkbookAndSheetName[1];
            str4 = str.substring(lastIndexOf + 1);
            str2 = str7;
        }
        if (!z || !Table.isStructuredReference.matcher(str4).matches()) {
            int indexOf = str4.indexOf(58);
            if (indexOf < 0) {
                str6 = str4.trim();
                str5 = null;
            } else {
                String trim = str4.substring(0, indexOf).trim();
                str5 = str4.substring(indexOf + 1).trim();
                str6 = trim;
            }
            try {
                return operationEvaluationContext.getDynamicReference(str3, str2, str6, str5, z);
            } catch (Exception e) {
                LOGGER.atWarn().log("Indirect function: failed to parse reference {}", str, e);
                return ErrorEval.REF_INVALID;
            }
        } else {
            try {
                return operationEvaluationContext.getArea3DEval(FormulaParser.parseStructuredReference(str4, (FormulaParsingWorkbook) operationEvaluationContext.getWorkbook(), operationEvaluationContext.getRowIndex()));
            } catch (FormulaParseException unused) {
                return ErrorEval.REF_INVALID;
            }
        }
    }

    private static String[] parseWorkbookAndSheetName(CharSequence charSequence) {
        String str;
        int i;
        int length = charSequence.length() - 1;
        if (length < 0 || canTrim(charSequence)) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        if (Character.isWhitespace(charAt)) {
            return null;
        }
        if (charAt == '\'') {
            if (charSequence.charAt(length) != '\'') {
                return null;
            }
            char charAt2 = charSequence.charAt(1);
            if (Character.isWhitespace(charAt2)) {
                return null;
            }
            if (charAt2 == '[') {
                int lastIndexOf = charSequence.toString().lastIndexOf(93);
                if (lastIndexOf < 0 || (str = unescapeString(charSequence.subSequence(2, lastIndexOf))) == null || canTrim(str)) {
                    return null;
                }
                i = lastIndexOf + 1;
            } else {
                i = 1;
                str = null;
            }
            String unescapeString = unescapeString(charSequence.subSequence(i, length));
            if (unescapeString == null) {
                return null;
            }
            return new String[]{str, unescapeString};
        } else if (charAt == '[') {
            int lastIndexOf2 = charSequence.toString().lastIndexOf(93);
            if (lastIndexOf2 < 0) {
                return null;
            }
            CharSequence subSequence = charSequence.subSequence(1, lastIndexOf2);
            if (canTrim(subSequence)) {
                return null;
            }
            CharSequence subSequence2 = charSequence.subSequence(lastIndexOf2 + 1, charSequence.length());
            if (canTrim(subSequence2)) {
                return null;
            }
            return new String[]{subSequence.toString(), subSequence2.toString()};
        } else {
            return new String[]{null, charSequence.toString()};
        }
    }

    private static String unescapeString(CharSequence charSequence) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (charAt2 == '\'') {
                i++;
                if (i >= length || (charAt = charSequence.charAt(i)) != '\'') {
                    return null;
                }
                charAt2 = charAt;
            }
            sb.append(charAt2);
            i++;
        }
        return sb.toString();
    }

    private static boolean canTrim(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        if (length < 0) {
            return false;
        }
        if (Character.isWhitespace(charSequence.charAt(0))) {
            return true;
        }
        return Character.isWhitespace(charSequence.charAt(length));
    }
}
