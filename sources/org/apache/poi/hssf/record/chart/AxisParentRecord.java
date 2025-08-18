package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class AxisParentRecord extends StandardRecord {
    public static final short AXIS_TYPE_MAIN = 0;
    public static final short AXIS_TYPE_SECONDARY = 1;
    public static final short sid = 4161;
    private short field_1_axisType;
    private int field_2_x;
    private int field_3_y;
    private int field_4_width;
    private int field_5_height;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 18;
    }

    public short getSid() {
        return sid;
    }

    public AxisParentRecord() {
    }

    public AxisParentRecord(AxisParentRecord axisParentRecord) {
        super(axisParentRecord);
        this.field_1_axisType = axisParentRecord.field_1_axisType;
        this.field_2_x = axisParentRecord.field_2_x;
        this.field_3_y = axisParentRecord.field_3_y;
        this.field_4_width = axisParentRecord.field_4_width;
        this.field_5_height = axisParentRecord.field_5_height;
    }

    public AxisParentRecord(RecordInputStream recordInputStream) {
        this.field_1_axisType = recordInputStream.readShort();
        this.field_2_x = recordInputStream.readInt();
        this.field_3_y = recordInputStream.readInt();
        this.field_4_width = recordInputStream.readInt();
        this.field_5_height = recordInputStream.readInt();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_axisType);
        littleEndianOutput.writeInt(this.field_2_x);
        littleEndianOutput.writeInt(this.field_3_y);
        littleEndianOutput.writeInt(this.field_4_width);
        littleEndianOutput.writeInt(this.field_5_height);
    }

    public short getAxisType() {
        return this.field_1_axisType;
    }

    public void setAxisType(short s) {
        this.field_1_axisType = s;
    }

    public int getX() {
        return this.field_2_x;
    }

    public void setX(int i) {
        this.field_2_x = i;
    }

    public int getY() {
        return this.field_3_y;
    }

    public void setY(int i) {
        this.field_3_y = i;
    }

    public int getWidth() {
        return this.field_4_width;
    }

    public void setWidth(int i) {
        this.field_4_width = i;
    }

    public int getHeight() {
        return this.field_5_height;
    }

    public void setHeight(int i) {
        this.field_5_height = i;
    }

    public AxisParentRecord copy() {
        return new AxisParentRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AXIS_PARENT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("axisType", new AxisParentRecord$$ExternalSyntheticLambda0(this), "x", new AxisParentRecord$$ExternalSyntheticLambda1(this), "y", new AxisParentRecord$$ExternalSyntheticLambda2(this), "width", new AxisParentRecord$$ExternalSyntheticLambda3(this), "height", new AxisParentRecord$$ExternalSyntheticLambda4(this));
    }
}
