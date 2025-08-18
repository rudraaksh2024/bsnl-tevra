package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class SCLRecord extends StandardRecord {
    public static final short sid = 160;
    private short field_1_numerator;
    private short field_2_denominator;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 4;
    }

    public short getSid() {
        return 160;
    }

    public SCLRecord() {
    }

    public SCLRecord(SCLRecord sCLRecord) {
        super(sCLRecord);
        this.field_1_numerator = sCLRecord.field_1_numerator;
        this.field_2_denominator = sCLRecord.field_2_denominator;
    }

    public SCLRecord(RecordInputStream recordInputStream) {
        this.field_1_numerator = recordInputStream.readShort();
        this.field_2_denominator = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_numerator);
        littleEndianOutput.writeShort(this.field_2_denominator);
    }

    public SCLRecord copy() {
        return new SCLRecord(this);
    }

    public short getNumerator() {
        return this.field_1_numerator;
    }

    public void setNumerator(short s) {
        this.field_1_numerator = s;
    }

    public short getDenominator() {
        return this.field_2_denominator;
    }

    public void setDenominator(short s) {
        this.field_2_denominator = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SCL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numerator", new SCLRecord$$ExternalSyntheticLambda0(this), "denominator", new SCLRecord$$ExternalSyntheticLambda1(this));
    }
}
