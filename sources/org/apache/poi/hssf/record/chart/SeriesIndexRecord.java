package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class SeriesIndexRecord extends StandardRecord {
    public static final short sid = 4197;
    private short field_1_index;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public SeriesIndexRecord() {
    }

    public SeriesIndexRecord(SeriesIndexRecord seriesIndexRecord) {
        super(seriesIndexRecord);
        this.field_1_index = seriesIndexRecord.field_1_index;
    }

    public SeriesIndexRecord(RecordInputStream recordInputStream) {
        this.field_1_index = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_index);
    }

    public SeriesIndexRecord copy() {
        return new SeriesIndexRecord(this);
    }

    public short getIndex() {
        return this.field_1_index;
    }

    public void setIndex(short s) {
        this.field_1_index = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SERIES_INDEX;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("index", new SeriesIndexRecord$$ExternalSyntheticLambda0(this));
    }
}
