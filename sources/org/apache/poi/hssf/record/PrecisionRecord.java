package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PrecisionRecord extends StandardRecord {
    public static final short sid = 14;
    private short field_1_precision;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 14;
    }

    public PrecisionRecord() {
    }

    public PrecisionRecord(PrecisionRecord precisionRecord) {
        super(precisionRecord);
        this.field_1_precision = precisionRecord.field_1_precision;
    }

    public PrecisionRecord(RecordInputStream recordInputStream) {
        this.field_1_precision = recordInputStream.readShort();
    }

    public void setFullPrecision(boolean z) {
        this.field_1_precision = z ? (short) 1 : 0;
    }

    public boolean getFullPrecision() {
        return this.field_1_precision == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_precision);
    }

    public PrecisionRecord copy() {
        return new PrecisionRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PRECISION;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("precision", new PrecisionRecord$$ExternalSyntheticLambda0(this));
    }
}
