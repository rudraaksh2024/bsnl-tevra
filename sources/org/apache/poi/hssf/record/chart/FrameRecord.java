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

public final class FrameRecord extends StandardRecord {
    public static final short BORDER_TYPE_REGULAR = 0;
    public static final short BORDER_TYPE_SHADOW = 1;
    private static final BitField autoPosition = BitFieldFactory.getInstance(2);
    private static final BitField autoSize = BitFieldFactory.getInstance(1);
    public static final short sid = 4146;
    private short field_1_borderType;
    private short field_2_options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 4;
    }

    public short getSid() {
        return sid;
    }

    public FrameRecord() {
    }

    public FrameRecord(FrameRecord frameRecord) {
        super(frameRecord);
        this.field_1_borderType = frameRecord.field_1_borderType;
        this.field_2_options = frameRecord.field_2_options;
    }

    public FrameRecord(RecordInputStream recordInputStream) {
        this.field_1_borderType = recordInputStream.readShort();
        this.field_2_options = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_borderType);
        littleEndianOutput.writeShort(this.field_2_options);
    }

    public FrameRecord copy() {
        return new FrameRecord(this);
    }

    public short getBorderType() {
        return this.field_1_borderType;
    }

    public void setBorderType(short s) {
        this.field_1_borderType = s;
    }

    public short getOptions() {
        return this.field_2_options;
    }

    public void setOptions(short s) {
        this.field_2_options = s;
    }

    public void setAutoSize(boolean z) {
        this.field_2_options = autoSize.setShortBoolean(this.field_2_options, z);
    }

    public boolean isAutoSize() {
        return autoSize.isSet(this.field_2_options);
    }

    public void setAutoPosition(boolean z) {
        this.field_2_options = autoPosition.setShortBoolean(this.field_2_options, z);
    }

    public boolean isAutoPosition() {
        return autoPosition.isSet(this.field_2_options);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FRAME;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("borderType", new FrameRecord$$ExternalSyntheticLambda0(this), "options", new FrameRecord$$ExternalSyntheticLambda1(this), "autoSize", new FrameRecord$$ExternalSyntheticLambda2(this), "autoPosition", new FrameRecord$$ExternalSyntheticLambda3(this));
    }
}
