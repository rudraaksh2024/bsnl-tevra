package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class AxisRecord extends StandardRecord {
    public static final short AXIS_TYPE_CATEGORY_OR_X_AXIS = 0;
    public static final short AXIS_TYPE_SERIES_AXIS = 2;
    public static final short AXIS_TYPE_VALUE_AXIS = 1;
    public static final short sid = 4125;
    private short field_1_axisType;
    private int field_2_reserved1;
    private int field_3_reserved2;
    private int field_4_reserved3;
    private int field_5_reserved4;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 18;
    }

    public short getSid() {
        return sid;
    }

    public AxisRecord() {
    }

    public AxisRecord(AxisRecord axisRecord) {
        super(axisRecord);
        this.field_1_axisType = axisRecord.field_1_axisType;
        this.field_2_reserved1 = axisRecord.field_2_reserved1;
        this.field_3_reserved2 = axisRecord.field_3_reserved2;
        this.field_4_reserved3 = axisRecord.field_4_reserved3;
        this.field_5_reserved4 = axisRecord.field_5_reserved4;
    }

    public AxisRecord(RecordInputStream recordInputStream) {
        this.field_1_axisType = recordInputStream.readShort();
        this.field_2_reserved1 = recordInputStream.readInt();
        this.field_3_reserved2 = recordInputStream.readInt();
        this.field_4_reserved3 = recordInputStream.readInt();
        this.field_5_reserved4 = recordInputStream.readInt();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_axisType);
        littleEndianOutput.writeInt(this.field_2_reserved1);
        littleEndianOutput.writeInt(this.field_3_reserved2);
        littleEndianOutput.writeInt(this.field_4_reserved3);
        littleEndianOutput.writeInt(this.field_5_reserved4);
    }

    public short getAxisType() {
        return this.field_1_axisType;
    }

    public void setAxisType(short s) {
        this.field_1_axisType = s;
    }

    public int getReserved1() {
        return this.field_2_reserved1;
    }

    public void setReserved1(int i) {
        this.field_2_reserved1 = i;
    }

    public int getReserved2() {
        return this.field_3_reserved2;
    }

    public void setReserved2(int i) {
        this.field_3_reserved2 = i;
    }

    public int getReserved3() {
        return this.field_4_reserved3;
    }

    public void setReserved3(int i) {
        this.field_4_reserved3 = i;
    }

    public int getReserved4() {
        return this.field_5_reserved4;
    }

    public void setReserved4(int i) {
        this.field_5_reserved4 = i;
    }

    public AxisRecord copy() {
        return new AxisRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AXIS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("axisType", new AxisRecord$$ExternalSyntheticLambda0(this), "reserved1", new AxisRecord$$ExternalSyntheticLambda1(this), "reserved2", new AxisRecord$$ExternalSyntheticLambda2(this), "reserved3", new AxisRecord$$ExternalSyntheticLambda3(this), "reserved4", new AxisRecord$$ExternalSyntheticLambda4(this));
    }
}
