package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class SeriesListRecord extends StandardRecord {
    public static final short sid = 4118;
    private short[] field_1_seriesNumbers;

    public short getSid() {
        return sid;
    }

    public SeriesListRecord(SeriesListRecord seriesListRecord) {
        super(seriesListRecord);
        short[] sArr = seriesListRecord.field_1_seriesNumbers;
        this.field_1_seriesNumbers = sArr == null ? null : (short[]) sArr.clone();
    }

    public SeriesListRecord(short[] sArr) {
        short[] sArr2;
        if (sArr == null) {
            sArr2 = null;
        } else {
            sArr2 = (short[]) sArr.clone();
        }
        this.field_1_seriesNumbers = sArr2;
    }

    public SeriesListRecord(RecordInputStream recordInputStream) {
        int readUShort = recordInputStream.readUShort();
        short[] sArr = new short[readUShort];
        for (int i = 0; i < readUShort; i++) {
            sArr[i] = recordInputStream.readShort();
        }
        this.field_1_seriesNumbers = sArr;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_seriesNumbers.length);
        for (short writeShort : this.field_1_seriesNumbers) {
            littleEndianOutput.writeShort(writeShort);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.field_1_seriesNumbers.length * 2) + 2;
    }

    public SeriesListRecord copy() {
        return new SeriesListRecord(this);
    }

    public short[] getSeriesNumbers() {
        return this.field_1_seriesNumbers;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SERIES_LIST;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("seriesNumbers", new SeriesListRecord$$ExternalSyntheticLambda0(this));
    }
}
