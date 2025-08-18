package org.apache.poi.hssf.record.chart;

import androidx.core.os.EnvironmentCompat;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartFormatRecord extends StandardRecord {
    public static final short sid = 4116;
    private static final BitField varyDisplayPattern = BitFieldFactory.getInstance(1);
    private int field1_x_position;
    private int field2_y_position;
    private int field3_width;
    private int field4_height;
    private int field5_grbit;
    private int field6_unknown;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 20;
    }

    public short getSid() {
        return sid;
    }

    public ChartFormatRecord() {
    }

    public ChartFormatRecord(ChartFormatRecord chartFormatRecord) {
        super(chartFormatRecord);
        this.field1_x_position = chartFormatRecord.field1_x_position;
        this.field2_y_position = chartFormatRecord.field2_y_position;
        this.field3_width = chartFormatRecord.field3_width;
        this.field4_height = chartFormatRecord.field4_height;
        this.field5_grbit = chartFormatRecord.field5_grbit;
        this.field6_unknown = chartFormatRecord.field6_unknown;
    }

    public ChartFormatRecord(RecordInputStream recordInputStream) {
        this.field1_x_position = recordInputStream.readInt();
        this.field2_y_position = recordInputStream.readInt();
        this.field3_width = recordInputStream.readInt();
        this.field4_height = recordInputStream.readInt();
        this.field5_grbit = recordInputStream.readUShort();
        this.field6_unknown = recordInputStream.readUShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(getXPosition());
        littleEndianOutput.writeInt(getYPosition());
        littleEndianOutput.writeInt(getWidth());
        littleEndianOutput.writeInt(getHeight());
        littleEndianOutput.writeShort(this.field5_grbit);
        littleEndianOutput.writeShort(this.field6_unknown);
    }

    public int getXPosition() {
        return this.field1_x_position;
    }

    public void setXPosition(int i) {
        this.field1_x_position = i;
    }

    public int getYPosition() {
        return this.field2_y_position;
    }

    public void setYPosition(int i) {
        this.field2_y_position = i;
    }

    public int getWidth() {
        return this.field3_width;
    }

    public void setWidth(int i) {
        this.field3_width = i;
    }

    public int getHeight() {
        return this.field4_height;
    }

    public void setHeight(int i) {
        this.field4_height = i;
    }

    public boolean getVaryDisplayPattern() {
        return varyDisplayPattern.isSet(this.field5_grbit);
    }

    public void setVaryDisplayPattern(boolean z) {
        this.field5_grbit = varyDisplayPattern.setBoolean(this.field5_grbit, z);
    }

    public ChartFormatRecord copy() {
        return new ChartFormatRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_FORMAT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("x", new ChartFormatRecord$$ExternalSyntheticLambda0(this), "y", new ChartFormatRecord$$ExternalSyntheticLambda1(this), "width", new ChartFormatRecord$$ExternalSyntheticLambda2(this), "height", new ChartFormatRecord$$ExternalSyntheticLambda3(this), "grbit", new ChartFormatRecord$$ExternalSyntheticLambda4(this), "varyDisplayPattern", new ChartFormatRecord$$ExternalSyntheticLambda5(this), EnvironmentCompat.MEDIA_UNKNOWN, new ChartFormatRecord$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartFormatRecord  reason: not valid java name */
    public /* synthetic */ Object m2141lambda$getGenericProperties$0$orgapachepoihssfrecordchartChartFormatRecord() {
        return Integer.valueOf(this.field5_grbit);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartFormatRecord  reason: not valid java name */
    public /* synthetic */ Object m2142lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartFormatRecord() {
        return Integer.valueOf(this.field6_unknown);
    }
}
