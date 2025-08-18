package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class BottomMarginRecord extends StandardRecord implements Margin {
    public static final short sid = 41;
    private double field_1_margin;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return 41;
    }

    public BottomMarginRecord() {
    }

    public BottomMarginRecord(BottomMarginRecord bottomMarginRecord) {
        super(bottomMarginRecord);
        this.field_1_margin = bottomMarginRecord.field_1_margin;
    }

    public BottomMarginRecord(RecordInputStream recordInputStream) {
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

    public BottomMarginRecord copy() {
        return new BottomMarginRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOTTOM_MARGIN;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("margin", new BottomMarginRecord$$ExternalSyntheticLambda0(this));
    }
}
