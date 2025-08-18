package org.apache.poi.ss.formula.eval;

import java.time.DateTimeException;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;

public final class OperandResolver {
    private static final String Digits = "(\\p{Digit}+)";
    private static final String Exp = "[eE][+-]?(\\p{Digit}+)";
    private static final Pattern fpPattern = Pattern.compile(fpRegex);
    private static final String fpRegex = "[\\x00-\\x20]*[+-]?(((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.(\\p{Digit}+)([eE][+-]?(\\p{Digit}+))?))[\\x00-\\x20]*";

    private OperandResolver() {
    }

    public static ValueEval getSingleValue(ValueEval valueEval, int i, int i2) throws EvaluationException {
        if (valueEval instanceof RefEval) {
            valueEval = chooseSingleElementFromRef((RefEval) valueEval);
        } else if (valueEval instanceof AreaEval) {
            valueEval = chooseSingleElementFromArea((AreaEval) valueEval, i, i2);
        }
        if (!(valueEval instanceof ErrorEval)) {
            return valueEval;
        }
        throw new EvaluationException((ErrorEval) valueEval);
    }

    public static ValueEval getElementFromArray(AreaEval areaEval, EvaluationCell evaluationCell) {
        CellRangeAddress arrayFormulaRange = evaluationCell.getArrayFormulaRange();
        int rowIndex = evaluationCell.getRowIndex() - arrayFormulaRange.getFirstRow();
        int columnIndex = evaluationCell.getColumnIndex() - arrayFormulaRange.getFirstColumn();
        if (areaEval.isColumn()) {
            if (areaEval.isRow()) {
                return areaEval.getRelativeValue(0, 0);
            }
            if (rowIndex < areaEval.getHeight()) {
                return areaEval.getRelativeValue(rowIndex, 0);
            }
        } else if (!areaEval.isRow() && rowIndex < areaEval.getHeight() && columnIndex < areaEval.getWidth()) {
            return areaEval.getRelativeValue(rowIndex, columnIndex);
        } else {
            if (areaEval.isRow() && columnIndex < areaEval.getWidth()) {
                return areaEval.getRelativeValue(0, columnIndex);
            }
        }
        return ErrorEval.NA;
    }

    public static ValueEval chooseSingleElementFromArea(AreaEval areaEval, int i, int i2) throws EvaluationException {
        ValueEval chooseSingleElementFromAreaInternal = chooseSingleElementFromAreaInternal(areaEval, i, i2);
        if (!(chooseSingleElementFromAreaInternal instanceof ErrorEval)) {
            return chooseSingleElementFromAreaInternal;
        }
        throw new EvaluationException((ErrorEval) chooseSingleElementFromAreaInternal);
    }

    private static ValueEval chooseSingleElementFromAreaInternal(AreaEval areaEval, int i, int i2) throws EvaluationException {
        if (areaEval.isColumn()) {
            if (areaEval.isRow()) {
                return areaEval.getRelativeValue(0, 0);
            }
            if (areaEval.containsRow(i)) {
                return areaEval.getAbsoluteValue(i, areaEval.getFirstColumn());
            }
            throw EvaluationException.invalidValue();
        } else if (!areaEval.isRow()) {
            if (areaEval.containsRow(i) && areaEval.containsColumn(i2)) {
                return areaEval.getAbsoluteValue(i, i2);
            }
            throw EvaluationException.invalidValue();
        } else if (areaEval.containsColumn(i2)) {
            return areaEval.getAbsoluteValue(areaEval.getFirstRow(), i2);
        } else {
            throw EvaluationException.invalidValue();
        }
    }

    private static ValueEval chooseSingleElementFromRef(RefEval refEval) {
        return refEval.getInnerValueEval(refEval.getFirstSheetIndex());
    }

    public static int coerceValueToInt(ValueEval valueEval) throws EvaluationException {
        if (valueEval == BlankEval.instance) {
            return 0;
        }
        return (int) Math.floor(coerceValueToDouble(valueEval));
    }

    public static double coerceValueToDouble(ValueEval valueEval) throws EvaluationException {
        if (valueEval == BlankEval.instance) {
            return 0.0d;
        }
        if (valueEval instanceof NumericValueEval) {
            return ((NumericValueEval) valueEval).getNumberValue();
        }
        if (valueEval instanceof StringEval) {
            String stringValue = ((StringEval) valueEval).getStringValue();
            Double parseDouble = parseDouble(stringValue);
            if (parseDouble == null) {
                parseDouble = parseDateTime(stringValue);
            }
            if (parseDouble != null) {
                return parseDouble.doubleValue();
            }
            throw EvaluationException.invalidValue();
        }
        throw new RuntimeException("Unexpected arg eval type (" + valueEval.getClass().getName() + ")");
    }

    public static Double parseDouble(String str) {
        if (fpPattern.matcher(str).matches()) {
            try {
                return Double.valueOf(Double.parseDouble(str));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static Double parseDateTime(String str) {
        try {
            return DateUtil.parseDateTime(str);
        } catch (DateTimeException unused) {
            return null;
        }
    }

    public static String coerceValueToString(ValueEval valueEval) {
        if (valueEval instanceof StringValueEval) {
            return ((StringValueEval) valueEval).getStringValue();
        }
        if (valueEval == BlankEval.instance) {
            return "";
        }
        throw new IllegalArgumentException("Unexpected eval class (" + valueEval.getClass().getName() + ")");
    }

    public static Boolean coerceValueToBoolean(ValueEval valueEval, boolean z) throws EvaluationException {
        if (valueEval == null || valueEval == BlankEval.instance) {
            return null;
        }
        if (valueEval instanceof BoolEval) {
            return Boolean.valueOf(((BoolEval) valueEval).getBooleanValue());
        }
        if (valueEval instanceof StringEval) {
            if (z) {
                return null;
            }
            String stringValue = ((StringEval) valueEval).getStringValue();
            if (stringValue.equalsIgnoreCase("true")) {
                return Boolean.TRUE;
            }
            if (stringValue.equalsIgnoreCase("false")) {
                return Boolean.FALSE;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        } else if (valueEval instanceof NumericValueEval) {
            double numberValue = ((NumericValueEval) valueEval).getNumberValue();
            if (!Double.isNaN(numberValue)) {
                return Boolean.valueOf(numberValue != 0.0d);
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        } else if (valueEval instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) valueEval);
        } else {
            throw new RuntimeException("Unexpected eval (" + valueEval.getClass().getName() + ")");
        }
    }
}
