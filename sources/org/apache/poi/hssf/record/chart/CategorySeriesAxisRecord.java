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

public final class CategorySeriesAxisRecord extends StandardRecord {
    private static final BitField crossesFarRight = BitFieldFactory.getInstance(2);
    private static final BitField reversed = BitFieldFactory.getInstance(4);
    public static final short sid = 4128;
    private static final BitField valueAxisCrossing = BitFieldFactory.getInstance(1);
    private short field_1_crossingPoint;
    private short field_2_labelFrequency;
    private short field_3_tickMarkFrequency;
    private short field_4_options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return sid;
    }

    public CategorySeriesAxisRecord() {
    }

    public CategorySeriesAxisRecord(CategorySeriesAxisRecord categorySeriesAxisRecord) {
        super(categorySeriesAxisRecord);
        this.field_1_crossingPoint = categorySeriesAxisRecord.field_1_crossingPoint;
        this.field_2_labelFrequency = categorySeriesAxisRecord.field_2_labelFrequency;
        this.field_3_tickMarkFrequency = categorySeriesAxisRecord.field_3_tickMarkFrequency;
        this.field_4_options = categorySeriesAxisRecord.field_4_options;
    }

    public CategorySeriesAxisRecord(RecordInputStream recordInputStream) {
        this.field_1_crossingPoint = recordInputStream.readShort();
        this.field_2_labelFrequency = recordInputStream.readShort();
        this.field_3_tickMarkFrequency = recordInputStream.readShort();
        this.field_4_options = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_crossingPoint);
        littleEndianOutput.writeShort(this.field_2_labelFrequency);
        littleEndianOutput.writeShort(this.field_3_tickMarkFrequency);
        littleEndianOutput.writeShort(this.field_4_options);
    }

    public short getCrossingPoint() {
        return this.field_1_crossingPoint;
    }

    public void setCrossingPoint(short s) {
        this.field_1_crossingPoint = s;
    }

    public short getLabelFrequency() {
        return this.field_2_labelFrequency;
    }

    public void setLabelFrequency(short s) {
        this.field_2_labelFrequency = s;
    }

    public short getTickMarkFrequency() {
        return this.field_3_tickMarkFrequency;
    }

    public void setTickMarkFrequency(short s) {
        this.field_3_tickMarkFrequency = s;
    }

    public short getOptions() {
        return this.field_4_options;
    }

    public void setOptions(short s) {
        this.field_4_options = s;
    }

    public void setValueAxisCrossing(boolean z) {
        this.field_4_options = valueAxisCrossing.setShortBoolean(this.field_4_options, z);
    }

    public boolean isValueAxisCrossing() {
        return valueAxisCrossing.isSet(this.field_4_options);
    }

    public void setCrossesFarRight(boolean z) {
        this.field_4_options = crossesFarRight.setShortBoolean(this.field_4_options, z);
    }

    public boolean isCrossesFarRight() {
        return crossesFarRight.isSet(this.field_4_options);
    }

    public void setReversed(boolean z) {
        this.field_4_options = reversed.setShortBoolean(this.field_4_options, z);
    }

    public boolean isReversed() {
        return reversed.isSet(this.field_4_options);
    }

    public CategorySeriesAxisRecord copy() {
        return new CategorySeriesAxisRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CATEGORY_SERIES_AXIS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("crossingPoint", new CategorySeriesAxisRecord$$ExternalSyntheticLambda0(this), "labelFrequency", new CategorySeriesAxisRecord$$ExternalSyntheticLambda1(this), "tickMarkFrequency", new CategorySeriesAxisRecord$$ExternalSyntheticLambda2(this), "options", new CategorySeriesAxisRecord$$ExternalSyntheticLambda3(this), "valueAxisCrossing", new CategorySeriesAxisRecord$$ExternalSyntheticLambda4(this), "crossesFarRight", new CategorySeriesAxisRecord$$ExternalSyntheticLambda5(this), "reversed", new CategorySeriesAxisRecord$$ExternalSyntheticLambda6(this));
    }
}
