package org.apache.poi.ss.formula.constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ss.usermodel.FormulaError;

public final class ErrorConstant {
    private static final ErrorConstant DIV_0 = new ErrorConstant(FormulaError.DIV0.getCode());
    private static final Logger LOG = LogManager.getLogger((Class<?>) ErrorConstant.class);
    private static final ErrorConstant NA = new ErrorConstant(FormulaError.NA.getCode());
    private static final ErrorConstant NAME = new ErrorConstant(FormulaError.NAME.getCode());
    private static final ErrorConstant NULL = new ErrorConstant(FormulaError.NULL.getCode());
    private static final ErrorConstant NUM = new ErrorConstant(FormulaError.NUM.getCode());
    private static final ErrorConstant REF = new ErrorConstant(FormulaError.REF.getCode());
    private static final ErrorConstant VALUE = new ErrorConstant(FormulaError.VALUE.getCode());
    private final int _errorCode;

    private ErrorConstant(int i) {
        this._errorCode = i;
    }

    public int getErrorCode() {
        return this._errorCode;
    }

    public String getText() {
        if (FormulaError.isValidCode(this._errorCode)) {
            return FormulaError.forInt(this._errorCode).getString();
        }
        return "unknown error code (" + this._errorCode + ")";
    }

    /* renamed from: org.apache.poi.ss.formula.constant.ErrorConstant$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.constant.ErrorConstant.AnonymousClass1.<clinit>():void");
        }
    }

    public static ErrorConstant valueOf(int i) {
        if (FormulaError.isValidCode(i)) {
            switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$FormulaError[FormulaError.forInt(i).ordinal()]) {
                case 1:
                    return NULL;
                case 2:
                    return DIV_0;
                case 3:
                    return VALUE;
                case 4:
                    return REF;
                case 5:
                    return NAME;
                case 6:
                    return NUM;
                case 7:
                    return NA;
            }
        }
        LOG.atWarn().log("Warning - unexpected error code ({})", (Object) Unbox.box(i));
        return new ErrorConstant(i);
    }

    public String toString() {
        return getClass().getName() + " [" + getText() + "]";
    }
}
