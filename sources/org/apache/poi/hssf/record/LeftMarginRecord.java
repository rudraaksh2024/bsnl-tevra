package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class LeftMarginRecord extends StandardRecord implements Margin {
    public static final short sid = 38;
    private double field_1_margin;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return 38;
    }

    public LeftMarginRecord() {
    }

    public LeftMarginRecord(LeftMarginRecord leftMarginRecord) {
        super(leftMarginRecord);
        this.field_1_margin = leftMarginRecord.field_1_margin;
    }

    public LeftMarginRecord(RecordInputStream recordInputStream) {
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

    public LeftMarginRecord copy() {
        return new LeftMarginRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LEFT_MARGIN;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("margin", new LeftMarginRecord$$ExternalSyntheticLambda0(this));
    }
}
