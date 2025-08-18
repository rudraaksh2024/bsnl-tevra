package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class BoolErrRecord extends CellRecord {
    public static final short sid = 517;
    private boolean _isError;
    private int _value;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "BOOLERR";
    }

    public short getSid() {
        return sid;
    }

    /* access modifiers changed from: protected */
    public int getValueDataSize() {
        return 2;
    }

    public BoolErrRecord() {
    }

    public BoolErrRecord(BoolErrRecord boolErrRecord) {
        super((CellRecord) boolErrRecord);
        this._value = boolErrRecord._value;
        this._isError = boolErrRecord._isError;
    }

    public BoolErrRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
        int remaining = recordInputStream.remaining();
        if (remaining == 2) {
            this._value = recordInputStream.readByte();
        } else if (remaining == 3) {
            this._value = recordInputStream.readUShort();
        } else {
            throw new RecordFormatException("Unexpected size (" + recordInputStream.remaining() + ") for BOOLERR record.");
        }
        int readUByte = recordInputStream.readUByte();
        if (readUByte == 0) {
            this._isError = false;
        } else if (readUByte == 1) {
            this._isError = true;
        } else {
            throw new RecordFormatException("Unexpected isError flag (" + readUByte + ") for BOOLERR record.");
        }
    }

    public void setValue(boolean z) {
        this._value = z ? 1 : 0;
        this._isError = false;
    }

    public void setValue(byte b) {
        setValue(FormulaError.forInt(b));
    }

    /* renamed from: org.apache.poi.hssf.record.BoolErrRecord$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.BoolErrRecord.AnonymousClass1.<clinit>():void");
        }
    }

    public void setValue(FormulaError formulaError) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$FormulaError[formulaError.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                this._value = formulaError.getCode();
                this._isError = true;
                return;
            default:
                throw new IllegalArgumentException("Error Value can only be 0,7,15,23,29,36 or 42. It cannot be " + formulaError.getCode() + " (" + formulaError + ")");
        }
    }

    public boolean getBooleanValue() {
        return this._value != 0;
    }

    public byte getErrorValue() {
        return (byte) this._value;
    }

    public boolean isBoolean() {
        return !this._isError;
    }

    public boolean isError() {
        return this._isError;
    }

    /* access modifiers changed from: protected */
    public void serializeValue(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this._value);
        littleEndianOutput.writeByte(this._isError ? 1 : 0);
    }

    public BoolErrRecord copy() {
        return new BoolErrRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOOL_ERR;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new BoolErrRecord$$ExternalSyntheticLambda0(this), "isBoolean", new BoolErrRecord$$ExternalSyntheticLambda1(this), "booleanVal", new BoolErrRecord$$ExternalSyntheticLambda2(this), "isError", new BoolErrRecord$$ExternalSyntheticLambda3(this), "errorVal", new BoolErrRecord$$ExternalSyntheticLambda4(this), "errorTxt", new BoolErrRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-BoolErrRecord  reason: not valid java name */
    public /* synthetic */ Object m1969lambda$getGenericProperties$0$orgapachepoihssfrecordBoolErrRecord() {
        return super.getGenericProperties();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-BoolErrRecord  reason: not valid java name */
    public /* synthetic */ Object m1970lambda$getGenericProperties$1$orgapachepoihssfrecordBoolErrRecord() {
        if (isError()) {
            return FormulaError.forInt(getErrorValue()).getString();
        }
        return null;
    }
}
