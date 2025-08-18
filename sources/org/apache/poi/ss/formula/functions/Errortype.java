package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.FormulaError;

public final class Errortype extends Fixed1ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        try {
            OperandResolver.getSingleValue(valueEval, i, i2);
            return ErrorEval.NA;
        } catch (EvaluationException e) {
            return new NumberEval((double) this.translateErrorCodeToErrorTypeValue(e.getErrorEval().getErrorCode()));
        }
    }

    /* renamed from: org.apache.poi.ss.formula.functions.Errortype$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$FormulaError;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.FormulaError[] r0 = org.apache.poi.ss.usermodel.FormulaError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$FormulaError = r0
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NULL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.DIV0     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.VALUE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.REF     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NAME     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NUM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NA     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.Errortype.AnonymousClass1.<clinit>():void");
        }
    }

    private int translateErrorCodeToErrorTypeValue(int i) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$FormulaError[FormulaError.forInt(i).ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            default:
                throw new IllegalArgumentException("Invalid error code (" + i + ")");
        }
    }
}
