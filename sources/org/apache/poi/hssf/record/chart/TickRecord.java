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
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class TickRecord extends StandardRecord {
    private static final BitField autoTextBackground = BitFieldFactory.getInstance(2);
    private static final BitField autoTextColor = BitFieldFactory.getInstance(1);
    private static final BitField autorotate = BitFieldFactory.getInstance(32);
    private static final BitField rotation = BitFieldFactory.getInstance(28);
    public static final short sid = 4126;
    private short field_10_options;
    private short field_11_tickColor;
    private short field_12_zero5;
    private byte field_1_majorTickType;
    private byte field_2_minorTickType;
    private byte field_3_labelPosition;
    private byte field_4_background;
    private int field_5_labelColorRgb;
    private int field_6_zero1;
    private int field_7_zero2;
    private int field_8_zero3;
    private int field_9_zero4;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 30;
    }

    public short getSid() {
        return sid;
    }

    public TickRecord() {
    }

    public TickRecord(TickRecord tickRecord) {
        super(tickRecord);
        this.field_1_majorTickType = tickRecord.field_1_majorTickType;
        this.field_2_minorTickType = tickRecord.field_2_minorTickType;
        this.field_3_labelPosition = tickRecord.field_3_labelPosition;
        this.field_4_background = tickRecord.field_4_background;
        this.field_5_labelColorRgb = tickRecord.field_5_labelColorRgb;
        this.field_6_zero1 = tickRecord.field_6_zero1;
        this.field_7_zero2 = tickRecord.field_7_zero2;
        this.field_8_zero3 = tickRecord.field_8_zero3;
        this.field_9_zero4 = tickRecord.field_9_zero4;
        this.field_10_options = tickRecord.field_10_options;
        this.field_11_tickColor = tickRecord.field_11_tickColor;
        this.field_12_zero5 = tickRecord.field_12_zero5;
    }

    public TickRecord(RecordInputStream recordInputStream) {
        this.field_1_majorTickType = recordInputStream.readByte();
        this.field_2_minorTickType = recordInputStream.readByte();
        this.field_3_labelPosition = recordInputStream.readByte();
        this.field_4_background = recordInputStream.readByte();
        this.field_5_labelColorRgb = recordInputStream.readInt();
        this.field_6_zero1 = recordInputStream.readInt();
        this.field_7_zero2 = recordInputStream.readInt();
        this.field_8_zero3 = recordInputStream.readInt();
        this.field_9_zero4 = recordInputStream.readInt();
        this.field_10_options = recordInputStream.readShort();
        this.field_11_tickColor = recordInputStream.readShort();
        this.field_12_zero5 = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this.field_1_majorTickType);
        littleEndianOutput.writeByte(this.field_2_minorTickType);
        littleEndianOutput.writeByte(this.field_3_labelPosition);
        littleEndianOutput.writeByte(this.field_4_background);
        littleEndianOutput.writeInt(this.field_5_labelColorRgb);
        littleEndianOutput.writeInt(this.field_6_zero1);
        littleEndianOutput.writeInt(this.field_7_zero2);
        littleEndianOutput.writeInt(this.field_8_zero3);
        littleEndianOutput.writeInt(this.field_9_zero4);
        littleEndianOutput.writeShort(this.field_10_options);
        littleEndianOutput.writeShort(this.field_11_tickColor);
        littleEndianOutput.writeShort(this.field_12_zero5);
    }

    public TickRecord copy() {
        return new TickRecord(this);
    }

    public byte getMajorTickType() {
        return this.field_1_majorTickType;
    }

    public void setMajorTickType(byte b) {
        this.field_1_majorTickType = b;
    }

    public byte getMinorTickType() {
        return this.field_2_minorTickType;
    }

    public void setMinorTickType(byte b) {
        this.field_2_minorTickType = b;
    }

    public byte getLabelPosition() {
        return this.field_3_labelPosition;
    }

    public void setLabelPosition(byte b) {
        this.field_3_labelPosition = b;
    }

    public byte getBackground() {
        return this.field_4_background;
    }

    public void setBackground(byte b) {
        this.field_4_background = b;
    }

    public int getLabelColorRgb() {
        return this.field_5_labelColorRgb;
    }

    public void setLabelColorRgb(int i) {
        this.field_5_labelColorRgb = i;
    }

    public int getZero1() {
        return this.field_6_zero1;
    }

    public void setZero1(int i) {
        this.field_6_zero1 = i;
    }

    public int getZero2() {
        return this.field_7_zero2;
    }

    public void setZero2(int i) {
        this.field_7_zero2 = i;
    }

    public short getOptions() {
        return this.field_10_options;
    }

    public void setOptions(short s) {
        this.field_10_options = s;
    }

    public short getTickColor() {
        return this.field_11_tickColor;
    }

    public void setTickColor(short s) {
        this.field_11_tickColor = s;
    }

    public short getZero3() {
        return this.field_12_zero5;
    }

    public void setZero3(short s) {
        this.field_12_zero5 = s;
    }

    public void setAutoTextColor(boolean z) {
        this.field_10_options = autoTextColor.setShortBoolean(this.field_10_options, z);
    }

    public boolean isAutoTextColor() {
        return autoTextColor.isSet(this.field_10_options);
    }

    public void setAutoTextBackground(boolean z) {
        this.field_10_options = autoTextBackground.setShortBoolean(this.field_10_options, z);
    }

    public boolean isAutoTextBackground() {
        return autoTextBackground.isSet(this.field_10_options);
    }

    public void setRotation(short s) {
        this.field_10_options = rotation.setShortValue(this.field_10_options, s);
    }

    public short getRotation() {
        return rotation.getShortValue(this.field_10_options);
    }

    public void setAutorotate(boolean z) {
        this.field_10_options = autorotate.setShortBoolean(this.field_10_options, z);
    }

    public boolean isAutorotate() {
        return autorotate.isSet(this.field_10_options);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TICK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("majorTickType", new TickRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("minorTickType", new TickRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("labelPosition", new TickRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("background", new TickRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("labelColorRgb", new TickRecord$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("zero1", new TickRecord$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("zero2", new TickRecord$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new TickRecord$$ExternalSyntheticLambda9(this), new BitField[]{autoTextColor, autoTextBackground, autorotate}, new String[]{"AUTO_TEXT_COLOR", "AUTO_TEXT_BACKGROUND", "AUTO_ROTATE"}));
        linkedHashMap.put("rotation", new TickRecord$$ExternalSyntheticLambda10(this));
        linkedHashMap.put("tickColor", new TickRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("zero3", new TickRecord$$ExternalSyntheticLambda2(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
