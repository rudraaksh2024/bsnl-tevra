package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class AreaRecord extends StandardRecord {
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(2);
    private static final BitField shadow = BitFieldFactory.getInstance(4);
    public static final short sid = 4122;
    private static final BitField stacked = BitFieldFactory.getInstance(1);
    private short field_1_formatFlags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public AreaRecord() {
    }

    public AreaRecord(AreaRecord areaRecord) {
        super(areaRecord);
        this.field_1_formatFlags = areaRecord.field_1_formatFlags;
    }

    public AreaRecord(RecordInputStream recordInputStream) {
        this.field_1_formatFlags = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_formatFlags);
    }

    public short getFormatFlags() {
        return this.field_1_formatFlags;
    }

    public void setFormatFlags(short s) {
        this.field_1_formatFlags = s;
    }

    public void setStacked(boolean z) {
        this.field_1_formatFlags = stacked.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isStacked() {
        return stacked.isSet(this.field_1_formatFlags);
    }

    public void setDisplayAsPercentage(boolean z) {
        this.field_1_formatFlags = displayAsPercentage.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isDisplayAsPercentage() {
        return displayAsPercentage.isSet(this.field_1_formatFlags);
    }

    public void setShadow(boolean z) {
        this.field_1_formatFlags = shadow.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isShadow() {
        return shadow.isSet(this.field_1_formatFlags);
    }

    public AreaRecord copy() {
        return new AreaRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AREA;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formatFlags", new AreaRecord$$ExternalSyntheticLambda0(this), "stacked", new AreaRecord$$ExternalSyntheticLambda1(this), "displayAsPercentage", new AreaRecord$$ExternalSyntheticLambda2(this), "shadow", new AreaRecord$$ExternalSyntheticLambda3(this));
    }
}
