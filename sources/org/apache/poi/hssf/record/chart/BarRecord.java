package org.apache.poi.hssf.record.chart;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianOutput;

public final class BarRecord extends StandardRecord {
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(4);
    private static final BitField horizontal = BitFieldFactory.getInstance(1);
    private static final BitField shadow = BitFieldFactory.getInstance(8);
    public static final short sid = 4119;
    private static final BitField stacked = BitFieldFactory.getInstance(2);
    private short field_1_barSpace;
    private short field_2_categorySpace;
    private short field_3_formatFlags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 6;
    }

    public short getSid() {
        return sid;
    }

    public BarRecord() {
    }

    public BarRecord(BarRecord barRecord) {
        super(barRecord);
        this.field_1_barSpace = barRecord.field_1_barSpace;
        this.field_2_categorySpace = barRecord.field_2_categorySpace;
        this.field_3_formatFlags = barRecord.field_3_formatFlags;
    }

    public BarRecord(RecordInputStream recordInputStream) {
        this.field_1_barSpace = recordInputStream.readShort();
        this.field_2_categorySpace = recordInputStream.readShort();
        this.field_3_formatFlags = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_barSpace);
        littleEndianOutput.writeShort(this.field_2_categorySpace);
        littleEndianOutput.writeShort(this.field_3_formatFlags);
    }

    public short getBarSpace() {
        return this.field_1_barSpace;
    }

    public void setBarSpace(short s) {
        this.field_1_barSpace = s;
    }

    public short getCategorySpace() {
        return this.field_2_categorySpace;
    }

    public void setCategorySpace(short s) {
        this.field_2_categorySpace = s;
    }

    public short getFormatFlags() {
        return this.field_3_formatFlags;
    }

    public void setFormatFlags(short s) {
        this.field_3_formatFlags = s;
    }

    public void setHorizontal(boolean z) {
        this.field_3_formatFlags = horizontal.setShortBoolean(this.field_3_formatFlags, z);
    }

    public boolean isHorizontal() {
        return horizontal.isSet(this.field_3_formatFlags);
    }

    public void setStacked(boolean z) {
        this.field_3_formatFlags = stacked.setShortBoolean(this.field_3_formatFlags, z);
    }

    public boolean isStacked() {
        return stacked.isSet(this.field_3_formatFlags);
    }

    public void setDisplayAsPercentage(boolean z) {
        this.field_3_formatFlags = displayAsPercentage.setShortBoolean(this.field_3_formatFlags, z);
    }

    public boolean isDisplayAsPercentage() {
        return displayAsPercentage.isSet(this.field_3_formatFlags);
    }

    public void setShadow(boolean z) {
        this.field_3_formatFlags = shadow.setShortBoolean(this.field_3_formatFlags, z);
    }

    public boolean isShadow() {
        return shadow.isSet(this.field_3_formatFlags);
    }

    public BarRecord copy() {
        return new BarRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BAR;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("barSpace", new BarRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("categorySpace", new BarRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("formatFlags", new BarRecord$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("horizontal", new BarRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("stacked", new BarRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("displayAsPercentage", new BarRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("shadow", new BarRecord$$ExternalSyntheticLambda6(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
