package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class RightMarginRecord extends StandardRecord implements Margin {
    public static final short sid = 39;
    private double field_1_margin;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return 39;
    }

    public RightMarginRecord() {
    }

    public RightMarginRecord(RightMarginRecord rightMarginRecord) {
        super(rightMarginRecord);
        this.field_1_margin = rightMarginRecord.field_1_margin;
    }

    public RightMarginRecord(RecordInputStream recordInputStream) {
        this.field_1_margin = recordInputStream.readDouble();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeDouble(this.field_1_margin);
    }

    public double getMargin() {
        return this.field_1_margin;
    }

    public void setMargin(double d) {
        this.field_1_margin = d;
    }

    public RightMarginRecord copy() {
        return new RightMarginRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.RIGHT_MARGIN;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("margin", new RightMarginRecord$$ExternalSyntheticLambda0(this));
    }
}
