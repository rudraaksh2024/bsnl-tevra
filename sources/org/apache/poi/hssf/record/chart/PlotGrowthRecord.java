package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PlotGrowthRecord extends StandardRecord {
    public static final short sid = 4196;
    private int field_1_horizontalScale;
    private int field_2_verticalScale;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return sid;
    }

    public PlotGrowthRecord() {
    }

    public PlotGrowthRecord(PlotGrowthRecord plotGrowthRecord) {
        this.field_1_horizontalScale = plotGrowthRecord.field_1_horizontalScale;
        this.field_2_verticalScale = plotGrowthRecord.field_2_verticalScale;
    }

    public PlotGrowthRecord(RecordInputStream recordInputStream) {
        this.field_1_horizontalScale = recordInputStream.readInt();
        this.field_2_verticalScale = recordInputStream.readInt();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.field_1_horizontalScale);
        littleEndianOutput.writeInt(this.field_2_verticalScale);
    }

    public PlotGrowthRecord copy() {
        return new PlotGrowthRecord(this);
    }

    public int getHorizontalScale() {
        return this.field_1_horizontalScale;
    }

    public void setHorizontalScale(int i) {
        this.field_1_horizontalScale = i;
    }

    public int getVerticalScale() {
        return this.field_2_verticalScale;
    }

    public void setVerticalScale(int i) {
        this.field_2_verticalScale = i;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PLOT_GROWTH;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("horizontalScale", new PlotGrowthRecord$$ExternalSyntheticLambda0(this), "verticalScale", new PlotGrowthRecord$$ExternalSyntheticLambda1(this));
    }
}
