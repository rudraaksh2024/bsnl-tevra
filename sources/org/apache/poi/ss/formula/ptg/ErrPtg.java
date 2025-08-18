package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ErrPtg extends ScalarConstantPtg {
    public static final ErrPtg DIV_ZERO = new ErrPtg(FormulaError.DIV0.getCode());
    public static final ErrPtg NAME_INVALID = new ErrPtg(FormulaError.NAME.getCode());
    public static final ErrPtg NULL_INTERSECTION = new ErrPtg(FormulaError.NULL.getCode());
    public static final ErrPtg NUM_ERROR = new ErrPtg(FormulaError.NUM.getCode());
    public static final ErrPtg N_A = new ErrPtg(FormulaError.NA.getCode());
    public static final ErrPtg REF_INVALID = new ErrPtg(FormulaError.REF.getCode());
    private static final int SIZE = 2;
    public static final ErrPtg VALUE_INVALID = new ErrPtg(FormulaError.VALUE.getCode());
    public static final short sid = 28;
    private final int field_1_error_code;

    public ErrPtg copy() {
        return this;
    }

    public byte getSid() {
        return 28;
    }

    public int getSize() {
        return 2;
    }

    private ErrPtg(int i) {
        if (FormulaError.isValidCode(i)) {
            this.field_1_error_code = i;
            return;
        }
        throw new IllegalArgumentException("Invalid error code (" + i + ")");
    }

    public static ErrPtg read(LittleEndianInput littleEndianInput) {
        return valueOf(littleEndianInput.readByte());
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 28);
        littleEndianOutput.writeByte(this.field_1_error_code);
    }

    public String toFormulaString() {
        return FormulaError.forInt(this.field_1_error_code).getString();
    }

    public int getErrorCode() {
        return this.field_1_error_code;
    }

    /* renamed from: org.apache.poi.ss.formula.ptg.ErrPtg$1  reason: invalid class name */
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
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.DIV0     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NAME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NULL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.NUM     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.REF     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$FormulaError     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.ss.usermodel.FormulaError r1 = org.apache.poi.ss.usermodel.FormulaError.VALUE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.ptg.ErrPtg.AnonymousClass1.<clinit>():void");
        }
    }

    public static ErrPtg valueOf(int i) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$FormulaError[FormulaError.forInt(i).ordinal()]) {
            case 1:
                return DIV_ZERO;
            case 2:
                return N_A;
            case 3:
                return NAME_INVALID;
            case 4:
                return NULL_INTERSECTION;
            case 5:
                return NUM_ERROR;
            case 6:
                return REF_INVALID;
            case 7:
                return VALUE_INVALID;
            default:
                throw new RuntimeException("Unexpected error code (" + i + ")");
        }
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("errorCode", new ErrPtg$$ExternalSyntheticLambda0(this));
    }
}
