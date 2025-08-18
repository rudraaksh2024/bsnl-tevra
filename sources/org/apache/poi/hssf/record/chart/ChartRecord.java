package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartRecord extends StandardRecord {
    public static final short sid = 4098;
    private int field_1_x;
    private int field_2_y;
    private int field_3_width;
    private int field_4_height;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 16;
    }

    public short getSid() {
        return sid;
    }

    public ChartRecord() {
    }

    public ChartRecord(ChartRecord chartRecord) {
        super(chartRecord);
        this.field_1_x = chartRecord.field_1_x;
        this.field_2_y = chartRecord.field_2_y;
        this.field_3_width = chartRecord.field_3_width;
        this.field_4_height = chartRecord.field_4_height;
    }

    public ChartRecord(RecordInputStream recordInputStream) {
        this.field_1_x = recordInputStream.readInt();
        this.field_2_y = recordInputStream.readInt();
        this.field_3_width = recordInputStream.readInt();
        this.field_4_height = recordInputStream.readInt();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.field_1_x);
        littleEndianOutput.writeInt(this.field_2_y);
        littleEndianOutput.writeInt(this.field_3_width);
        littleEndianOutput.writeInt(this.field_4_height);
    }

    public ChartRecord copy() {
        return new ChartRecord(this);
    }

    public int getX() {
        return this.field_1_x;
    }

    public void setX(int i) {
        this.field_1_x = i;
    }

    public int getY() {
        return this.field_2_y;
    }

    public void setY(int i) {
        this.field_2_y = i;
    }

    public int getWidth() {
        return this.field_3_width;
    }

    public void setWidth(int i) {
        this.field_3_width = i;
    }

    public int getHeight() {
        return this.field_4_height;
    }

    public void setHeight(int i) {
        this.field_4_height = i;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("x", new ChartRecord$$ExternalSyntheticLambda0(this), "y", new ChartRecord$$ExternalSyntheticLambda1(this), "width", new ChartRecord$$ExternalSyntheticLambda2(this), "height", new ChartRecord$$ExternalSyntheticLambda3(this));
    }
}
