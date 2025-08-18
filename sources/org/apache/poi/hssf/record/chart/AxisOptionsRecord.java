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

public final class AxisOptionsRecord extends StandardRecord {
    private static final int[] FLAG_MASKS = {1, 2, 4, 8, 16, 32, 64, 128};
    private static final String[] FLAG_NAMES = {"DEFAULT_MINIMUM", "DEFAULT_MAXIMUM", "DEFAULT_MAJOR", "DEFAULT_MINOR_UNIT", "IS_DATE", "DEFAULT_BASE", "DEFAULT_CROSS", "DEFAULT_DATE_SETTINGS"};
    private static final BitField defaultBase = BitFieldFactory.getInstance(32);
    private static final BitField defaultCross = BitFieldFactory.getInstance(64);
    private static final BitField defaultDateSettings = BitFieldFactory.getInstance(128);
    private static final BitField defaultMajor = BitFieldFactory.getInstance(4);
    private static final BitField defaultMaximum = BitFieldFactory.getInstance(2);
    private static final BitField defaultMinimum = BitFieldFactory.getInstance(1);
    private static final BitField defaultMinorUnit = BitFieldFactory.getInstance(8);
    private static final BitField isDate = BitFieldFactory.getInstance(16);
    public static final short sid = 4194;
    private short field_1_minimumCategory;
    private short field_2_maximumCategory;
    private short field_3_majorUnitValue;
    private short field_4_majorUnit;
    private short field_5_minorUnitValue;
    private short field_6_minorUnit;
    private short field_7_baseUnit;
    private short field_8_crossingPoint;
    private short field_9_options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 18;
    }

    public short getSid() {
        return sid;
    }

    public AxisOptionsRecord() {
    }

    public AxisOptionsRecord(AxisOptionsRecord axisOptionsRecord) {
        super(axisOptionsRecord);
        this.field_1_minimumCategory = axisOptionsRecord.field_1_minimumCategory;
        this.field_2_maximumCategory = axisOptionsRecord.field_2_maximumCategory;
        this.field_3_majorUnitValue = axisOptionsRecord.field_3_majorUnitValue;
        this.field_4_majorUnit = axisOptionsRecord.field_4_majorUnit;
        this.field_5_minorUnitValue = axisOptionsRecord.field_5_minorUnitValue;
        this.field_6_minorUnit = axisOptionsRecord.field_6_minorUnit;
        this.field_7_baseUnit = axisOptionsRecord.field_7_baseUnit;
        this.field_8_crossingPoint = axisOptionsRecord.field_8_crossingPoint;
        this.field_9_options = axisOptionsRecord.field_9_options;
    }

    public AxisOptionsRecord(RecordInputStream recordInputStream) {
        this.field_1_minimumCategory = recordInputStream.readShort();
        this.field_2_maximumCategory = recordInputStream.readShort();
        this.field_3_majorUnitValue = recordInputStream.readShort();
        this.field_4_majorUnit = recordInputStream.readShort();
        this.field_5_minorUnitValue = recordInputStream.readShort();
        this.field_6_minorUnit = recordInputStream.readShort();
        this.field_7_baseUnit = recordInputStream.readShort();
        this.field_8_crossingPoint = recordInputStream.readShort();
        this.field_9_options = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_minimumCategory);
        littleEndianOutput.writeShort(this.field_2_maximumCategory);
        littleEndianOutput.writeShort(this.field_3_majorUnitValue);
        littleEndianOutput.writeShort(this.field_4_majorUnit);
        littleEndianOutput.writeShort(this.field_5_minorUnitValue);
        littleEndianOutput.writeShort(this.field_6_minorUnit);
        littleEndianOutput.writeShort(this.field_7_baseUnit);
        littleEndianOutput.writeShort(this.field_8_crossingPoint);
        littleEndianOutput.writeShort(this.field_9_options);
    }

    public short getMinimumCategory() {
        return this.field_1_minimumCategory;
    }

    public void setMinimumCategory(short s) {
        this.field_1_minimumCategory = s;
    }

    public short getMaximumCategory() {
        return this.field_2_maximumCategory;
    }

    public void setMaximumCategory(short s) {
        this.field_2_maximumCategory = s;
    }

    public short getMajorUnitValue() {
        return this.field_3_majorUnitValue;
    }

    public void setMajorUnitValue(short s) {
        this.field_3_majorUnitValue = s;
    }

    public short getMajorUnit() {
        return this.field_4_majorUnit;
    }

    public void setMajorUnit(short s) {
        this.field_4_majorUnit = s;
    }

    public short getMinorUnitValue() {
        return this.field_5_minorUnitValue;
    }

    public void setMinorUnitValue(short s) {
        this.field_5_minorUnitValue = s;
    }

    public short getMinorUnit() {
        return this.field_6_minorUnit;
    }

    public void setMinorUnit(short s) {
        this.field_6_minorUnit = s;
    }

    public short getBaseUnit() {
        return this.field_7_baseUnit;
    }

    public void setBaseUnit(short s) {
        this.field_7_baseUnit = s;
    }

    public short getCrossingPoint() {
        return this.field_8_crossingPoint;
    }

    public void setCrossingPoint(short s) {
        this.field_8_crossingPoint = s;
    }

    public short getOptions() {
        return this.field_9_options;
    }

    public void setOptions(short s) {
        this.field_9_options = s;
    }

    public void setDefaultMinimum(boolean z) {
        this.field_9_options = defaultMinimum.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultMinimum() {
        return defaultMinimum.isSet(this.field_9_options);
    }

    public void setDefaultMaximum(boolean z) {
        this.field_9_options = defaultMaximum.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultMaximum() {
        return defaultMaximum.isSet(this.field_9_options);
    }

    public void setDefaultMajor(boolean z) {
        this.field_9_options = defaultMajor.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultMajor() {
        return defaultMajor.isSet(this.field_9_options);
    }

    public void setDefaultMinorUnit(boolean z) {
        this.field_9_options = defaultMinorUnit.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultMinorUnit() {
        return defaultMinorUnit.isSet(this.field_9_options);
    }

    public void setIsDate(boolean z) {
        this.field_9_options = isDate.setShortBoolean(this.field_9_options, z);
    }

    public boolean isIsDate() {
        return isDate.isSet(this.field_9_options);
    }

    public void setDefaultBase(boolean z) {
        this.field_9_options = defaultBase.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultBase() {
        return defaultBase.isSet(this.field_9_options);
    }

    public void setDefaultCross(boolean z) {
        this.field_9_options = defaultCross.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultCross() {
        return defaultCross.isSet(this.field_9_options);
    }

    public void setDefaultDateSettings(boolean z) {
        this.field_9_options = defaultDateSettings.setShortBoolean(this.field_9_options, z);
    }

    public boolean isDefaultDateSettings() {
        return defaultDateSettings.isSet(this.field_9_options);
    }

    public AxisOptionsRecord copy() {
        return new AxisOptionsRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AXIS_OPTIONS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        AxisOptionsRecord$$ExternalSyntheticLambda0 axisOptionsRecord$$ExternalSyntheticLambda0 = r3;
        AxisOptionsRecord$$ExternalSyntheticLambda0 axisOptionsRecord$$ExternalSyntheticLambda02 = new AxisOptionsRecord$$ExternalSyntheticLambda0(this);
        AxisOptionsRecord$$ExternalSyntheticLambda1 axisOptionsRecord$$ExternalSyntheticLambda1 = r5;
        AxisOptionsRecord$$ExternalSyntheticLambda1 axisOptionsRecord$$ExternalSyntheticLambda12 = new AxisOptionsRecord$$ExternalSyntheticLambda1(this);
        AxisOptionsRecord$$ExternalSyntheticLambda2 axisOptionsRecord$$ExternalSyntheticLambda2 = r7;
        AxisOptionsRecord$$ExternalSyntheticLambda2 axisOptionsRecord$$ExternalSyntheticLambda22 = new AxisOptionsRecord$$ExternalSyntheticLambda2(this);
        AxisOptionsRecord$$ExternalSyntheticLambda3 axisOptionsRecord$$ExternalSyntheticLambda3 = r9;
        AxisOptionsRecord$$ExternalSyntheticLambda3 axisOptionsRecord$$ExternalSyntheticLambda32 = new AxisOptionsRecord$$ExternalSyntheticLambda3(this);
        AxisOptionsRecord$$ExternalSyntheticLambda4 axisOptionsRecord$$ExternalSyntheticLambda4 = r11;
        AxisOptionsRecord$$ExternalSyntheticLambda4 axisOptionsRecord$$ExternalSyntheticLambda42 = new AxisOptionsRecord$$ExternalSyntheticLambda4(this);
        AxisOptionsRecord$$ExternalSyntheticLambda5 axisOptionsRecord$$ExternalSyntheticLambda5 = r13;
        AxisOptionsRecord$$ExternalSyntheticLambda5 axisOptionsRecord$$ExternalSyntheticLambda52 = new AxisOptionsRecord$$ExternalSyntheticLambda5(this);
        AxisOptionsRecord$$ExternalSyntheticLambda6 axisOptionsRecord$$ExternalSyntheticLambda6 = r15;
        AxisOptionsRecord$$ExternalSyntheticLambda6 axisOptionsRecord$$ExternalSyntheticLambda62 = new AxisOptionsRecord$$ExternalSyntheticLambda6(this);
        AxisOptionsRecord$$ExternalSyntheticLambda7 axisOptionsRecord$$ExternalSyntheticLambda7 = r1;
        AxisOptionsRecord$$ExternalSyntheticLambda7 axisOptionsRecord$$ExternalSyntheticLambda72 = new AxisOptionsRecord$$ExternalSyntheticLambda7(this);
        return GenericRecordUtil.getGenericProperties("minimumCategory", axisOptionsRecord$$ExternalSyntheticLambda0, "maximumCategory", axisOptionsRecord$$ExternalSyntheticLambda1, "majorUnitValue", axisOptionsRecord$$ExternalSyntheticLambda2, "majorUnit", axisOptionsRecord$$ExternalSyntheticLambda3, "minorUnitValue", axisOptionsRecord$$ExternalSyntheticLambda4, "minorUnit", axisOptionsRecord$$ExternalSyntheticLambda5, "baseUnit", axisOptionsRecord$$ExternalSyntheticLambda6, "crossingPoint", axisOptionsRecord$$ExternalSyntheticLambda7, "options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new AxisOptionsRecord$$ExternalSyntheticLambda8(this), FLAG_MASKS, FLAG_NAMES));
    }
}
