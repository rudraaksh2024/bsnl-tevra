package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ArrayRecord extends SharedValueRecordBase {
    private static final int OPT_ALWAYS_RECALCULATE = 1;
    private static final int OPT_CALCULATE_ON_OPEN = 2;
    public static final short sid = 545;
    private int _field3notUsed;
    private Formula _formula;
    private int _options;

    public short getSid() {
        return sid;
    }

    public ArrayRecord(ArrayRecord arrayRecord) {
        super((SharedValueRecordBase) arrayRecord);
        this._options = arrayRecord._options;
        this._field3notUsed = arrayRecord._field3notUsed;
        Formula formula = arrayRecord._formula;
        this._formula = formula == null ? null : formula.copy();
    }

    public ArrayRecord(RecordInputStream recordInputStream) {
        super((LittleEndianInput) recordInputStream);
        this._options = recordInputStream.readUShort();
        this._field3notUsed = recordInputStream.readInt();
        this._formula = Formula.read(recordInputStream.readUShort(), recordInputStream, recordInputStream.available());
    }

    public ArrayRecord(Formula formula, CellRangeAddress8Bit cellRangeAddress8Bit) {
        super(cellRangeAddress8Bit);
        this._options = 0;
        this._field3notUsed = 0;
        this._formula = formula;
    }

    public boolean isAlwaysRecalculate() {
        return (this._options & 1) != 0;
    }

    public boolean isCalculateOnOpen() {
        return (this._options & 2) != 0;
    }

    public Ptg[] getFormulaTokens() {
        return this._formula.getTokens();
    }

    /* access modifiers changed from: protected */
    public int getExtraDataSize() {
        return this._formula.getEncodedSize() + 6;
    }

    /* access modifiers changed from: protected */
    public void serializeExtraData(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
        littleEndianOutput.writeInt(this._field3notUsed);
        this._formula.serialize(littleEndianOutput);
    }

    public ArrayRecord copy() {
        return new ArrayRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ARRAY;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new ArrayRecord$$ExternalSyntheticLambda0(this), "options", new ArrayRecord$$ExternalSyntheticLambda1(this), "notUsed", new ArrayRecord$$ExternalSyntheticLambda2(this), "formula", new ArrayRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ArrayRecord  reason: not valid java name */
    public /* synthetic */ Object m1966lambda$getGenericProperties$0$orgapachepoihssfrecordArrayRecord() {
        return Integer.valueOf(this._options);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-ArrayRecord  reason: not valid java name */
    public /* synthetic */ Object m1967lambda$getGenericProperties$1$orgapachepoihssfrecordArrayRecord() {
        return Integer.valueOf(this._field3notUsed);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-ArrayRecord  reason: not valid java name */
    public /* synthetic */ Object m1968lambda$getGenericProperties$2$orgapachepoihssfrecordArrayRecord() {
        return this._formula;
    }
}
