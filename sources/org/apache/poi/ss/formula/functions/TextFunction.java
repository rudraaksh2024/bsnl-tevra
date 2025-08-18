package org.apache.poi.ss.formula.functions;

import java.util.Locale;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DataFormatter;

public abstract class TextFunction implements Function {
    public static final Function CHAR = new Fixed1ArgFunction() {
        public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
            try {
                int evaluateIntArg = TextFunction.evaluateIntArg(valueEval, i, i2);
                if (evaluateIntArg >= 0 && evaluateIntArg < 256) {
                    return new StringEval(String.valueOf((char) evaluateIntArg));
                }
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function CLEAN = new SingleArgTextFunc() {
        private boolean isPrintable(char c) {
            return c >= ' ';
        }

        /* access modifiers changed from: protected */
        public ValueEval evaluate(String str) {
            StringBuilder sb = new StringBuilder();
            for (char c : str.toCharArray()) {
                if (isPrintable(c)) {
                    sb.append(c);
                }
            }
            return new StringEval(sb.toString());
        }
    };
    public static final FreeRefFunction CONCAT = new TextFunction$$ExternalSyntheticLambda0();
    public static final Function CONCATENATE = new TextFunction$$ExternalSyntheticLambda1();
    public static final Function EXACT = new Fixed2ArgFunction() {
        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
            try {
                return BoolEval.valueOf(TextFunction.evaluateStringArg(valueEval, i, i2).equals(TextFunction.evaluateStringArg(valueEval2, i, i2)));
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function FIND = new SearchFind(true);
    public static final Function LEFT = new LeftRight(true);
    public static final Function LEN = new SingleArgTextFunc() {
        /* access modifiers changed from: protected */
        public ValueEval evaluate(String str) {
            return new NumberEval((double) str.length());
        }
    };
    public static final Function LOWER = new SingleArgTextFunc() {
        /* access modifiers changed from: protected */
        public ValueEval evaluate(String str) {
            return new StringEval(str.toLowerCase(Locale.ROOT));
        }
    };
    public static final Function MID = new Fixed3ArgFunction() {
        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
            try {
                String evaluateStringArg = TextFunction.evaluateStringArg(valueEval, i, i2);
                int evaluateIntArg = TextFunction.evaluateIntArg(valueEval2, i, i2);
                int evaluateIntArg2 = TextFunction.evaluateIntArg(valueEval3, i, i2);
                int i3 = evaluateIntArg - 1;
                if (i3 < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                if (evaluateIntArg2 < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                int length = evaluateStringArg.length();
                if (i3 > length) {
                    return new StringEval("");
                }
                return new StringEval(evaluateStringArg.substring(i3, Math.min(evaluateIntArg2 + i3, length)));
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function PROPER = new SingleArgTextFunc() {
        /* access modifiers changed from: protected */
        public ValueEval evaluate(String str) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (char c : str.toCharArray()) {
                if (z) {
                    sb.append(String.valueOf(c).toUpperCase(Locale.ROOT));
                } else {
                    sb.append(String.valueOf(c).toLowerCase(Locale.ROOT));
                }
                z = !Character.isLetter(c);
            }
            return new StringEval(sb.toString());
        }
    };
    public static final Function RIGHT = new LeftRight(false);
    public static final Function SEARCH = new SearchFind(false);
    public static final Function TEXT = new Fixed2ArgFunction() {
        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
            try {
                try {
                    return new StringEval(TextFunction.formatter.formatRawCellContents(TextFunction.evaluateDoubleArg(valueEval, i, i2), -1, TextFunction.evaluateStringArg(valueEval2, i, i2)));
                } catch (Exception unused) {
                    return ErrorEval.VALUE_INVALID;
                }
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function TRIM = new SingleArgTextFunc() {
        /* access modifiers changed from: protected */
        public ValueEval evaluate(String str) {
            return new StringEval(str.trim().replaceAll(" +", " "));
        }
    };
    public static final Function UPPER = new SingleArgTextFunc() {
        /* access modifiers changed from: protected */
        public ValueEval evaluate(String str) {
            return new StringEval(str.toUpperCase(Locale.ROOT));
        }
    };
    protected static final DataFormatter formatter = new DataFormatter();

    /* access modifiers changed from: protected */
    public abstract ValueEval evaluateFunc(ValueEval[] valueEvalArr, int i, int i2) throws EvaluationException;

    protected static String evaluateStringArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2));
    }

    protected static int evaluateIntArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEval, i, i2));
    }

    protected static double evaluateDoubleArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
    }

    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        try {
            return evaluateFunc(valueEvalArr, i, i2);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static abstract class SingleArgTextFunc extends Fixed1ArgFunction {
        /* access modifiers changed from: protected */
        public abstract ValueEval evaluate(String str);

        protected SingleArgTextFunc() {
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
            try {
                return evaluate(TextFunction.evaluateStringArg(valueEval, i, i2));
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    }

    private static final class LeftRight extends Var1or2ArgFunction {
        private static final ValueEval DEFAULT_ARG1 = new NumberEval(1.0d);
        private final boolean _isLeft;

        protected LeftRight(boolean z) {
            this._isLeft = z;
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
            return evaluate(i, i2, valueEval, DEFAULT_ARG1);
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
            String str;
            try {
                String evaluateStringArg = TextFunction.evaluateStringArg(valueEval, i, i2);
                int evaluateIntArg = TextFunction.evaluateIntArg(valueEval2, i, i2);
                if (evaluateIntArg < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                if (this._isLeft) {
                    str = evaluateStringArg.substring(0, Math.min(evaluateStringArg.length(), evaluateIntArg));
                } else {
                    str = evaluateStringArg.substring(Math.max(0, evaluateStringArg.length() - evaluateIntArg));
                }
                return new StringEval(str);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    }

    static /* synthetic */ ValueEval lambda$static$0(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        StringBuilder sb = new StringBuilder();
        int length = valueEvalArr.length;
        int i = 0;
        while (i < length) {
            AreaEval areaEval = valueEvalArr[i];
            try {
                if (areaEval instanceof AreaEval) {
                    AreaEval areaEval2 = areaEval;
                    for (int i2 = 0; i2 < areaEval2.getHeight(); i2++) {
                        for (int i3 = 0; i3 < areaEval2.getWidth(); i3++) {
                            sb.append(evaluateStringArg(areaEval2.getRelativeValue(i2, i3), operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex()));
                        }
                    }
                } else {
                    sb.append(evaluateStringArg(areaEval, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex()));
                }
                i++;
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        return new StringEval(sb.toString());
    }

    static /* synthetic */ ValueEval lambda$static$1(ValueEval[] valueEvalArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        int length = valueEvalArr.length;
        int i3 = 0;
        while (i3 < length) {
            try {
                sb.append(evaluateStringArg(valueEvalArr[i3], i, i2));
                i3++;
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        return new StringEval(sb.toString());
    }

    private static final class SearchFind extends Var2or3ArgFunction {
        private final boolean _isCaseSensitive;

        public SearchFind(boolean z) {
            this._isCaseSensitive = z;
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
            try {
                return eval(TextFunction.evaluateStringArg(valueEval2, i, i2), TextFunction.evaluateStringArg(valueEval, i, i2), 0);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
            try {
                String evaluateStringArg = TextFunction.evaluateStringArg(valueEval, i, i2);
                String evaluateStringArg2 = TextFunction.evaluateStringArg(valueEval2, i, i2);
                int evaluateIntArg = TextFunction.evaluateIntArg(valueEval3, i, i2) - 1;
                if (evaluateIntArg < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                return eval(evaluateStringArg2, evaluateStringArg, evaluateIntArg);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }

        private ValueEval eval(String str, String str2, int i) {
            int i2;
            if (this._isCaseSensitive) {
                i2 = str.indexOf(str2, i);
            } else {
                i2 = str.toUpperCase(Locale.ROOT).indexOf(str2.toUpperCase(Locale.ROOT), i);
            }
            if (i2 == -1) {
                return ErrorEval.VALUE_INVALID;
            }
            return new NumberEval(((double) i2) + 1.0d);
        }
    }
}
