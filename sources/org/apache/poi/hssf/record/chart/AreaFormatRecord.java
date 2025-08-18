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

public final class AreaFormatRecord extends StandardRecord {
    private static final BitField automatic = BitFieldFactory.getInstance(1);
    private static final BitField invert = BitFieldFactory.getInstance(2);
    public static final short sid = 4106;
    private int field_1_foregroundColor;
    private int field_2_backgroundColor;
    private short field_3_pattern;
    private short field_4_formatFlags;
    private short field_5_forecolorIndex;
    private short field_6_backcolorIndex;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 16;
    }

    public short getSid() {
        return sid;
    }

    public AreaFormatRecord() {
    }

    public AreaFormatRecord(RecordInputStream recordInputStream) {
        this.field_1_foregroundColor = recordInputStream.readInt();
        this.field_2_backgroundColor = recordInputStream.readInt();
        this.field_3_pattern = recordInputStream.readShort();
        this.field_4_formatFlags = recordInputStream.readShort();
        this.field_5_forecolorIndex = recordInputStream.readShort();
        this.field_6_backcolorIndex = recordInputStream.readShort();
    }

    public AreaFormatRecord(AreaFormatRecord areaFormatRecord) {
        super(areaFormatRecord);
        this.field_1_foregroundColor = areaFormatRecord.field_1_foregroundColor;
        this.field_2_backgroundColor = areaFormatRecord.field_2_backgroundColor;
        this.field_3_pattern = areaFormatRecord.field_3_pattern;
        this.field_4_formatFlags = areaFormatRecord.field_4_formatFlags;
        this.field_5_forecolorIndex = areaFormatRecord.field_5_forecolorIndex;
        this.field_6_backcolorIndex = areaFormatRecord.field_6_backcolorIndex;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.field_1_foregroundColor);
        littleEndianOutput.writeInt(this.field_2_backgroundColor);
        littleEndianOutput.writeShort(this.field_3_pattern);
        littleEndianOutput.writeShort(this.field_4_formatFlags);
        littleEndianOutput.writeShort(this.field_5_forecolorIndex);
        littleEndianOutput.writeShort(this.field_6_backcolorIndex);
    }

    public int getForegroundColor() {
        return this.field_1_foregroundColor;
    }

    public void setForegroundColor(int i) {
        this.field_1_foregroundColor = i;
    }

    public int getBackgroundColor() {
        return this.field_2_backgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.field_2_backgroundColor = i;
    }

    public short getPattern() {
        return this.field_3_pattern;
    }

    public void setPattern(short s) {
        this.field_3_pattern = s;
    }

    public short getFormatFlags() {
        return this.field_4_formatFlags;
    }

    public void setFormatFlags(short s) {
        this.field_4_formatFlags = s;
    }

    public short getForecolorIndex() {
        return this.field_5_forecolorIndex;
    }

    public void setForecolorIndex(short s) {
        this.field_5_forecolorIndex = s;
    }

    public short getBackcolorIndex() {
        return this.field_6_backcolorIndex;
    }

    public void setBackcolorIndex(short s) {
        this.field_6_backcolorIndex = s;
    }

    public void setAutomatic(boolean z) {
        this.field_4_formatFlags = automatic.setShortBoolean(this.field_4_formatFlags, z);
    }

    public boolean isAutomatic() {
        return automatic.isSet(this.field_4_formatFlags);
    }

    public void setInvert(boolean z) {
        this.field_4_formatFlags = invert.setShortBoolean(this.field_4_formatFlags, z);
    }

    public boolean isInvert() {
        return invert.isSet(this.field_4_formatFlags);
    }

    public AreaFormatRecord copy() {
        return new AreaFormatRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AREA_FORMAT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("foregroundColor", new AreaFormatRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("backgroundColor", new AreaFormatRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("pattern", new AreaFormatRecord$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("inverted", new AreaFormatRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("automatic", new AreaFormatRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("formatFlags", new AreaFormatRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("forecolorIndex", new AreaFormatRecord$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("backcolorIndex", new AreaFormatRecord$$ExternalSyntheticLambda7(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
