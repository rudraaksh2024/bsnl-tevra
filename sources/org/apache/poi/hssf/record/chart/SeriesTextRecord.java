package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class SeriesTextRecord extends StandardRecord {
    private static final int MAX_LEN = 255;
    public static final short sid = 4109;
    private int field_1_id;
    private String field_4_text;
    private boolean is16bit;

    public short getSid() {
        return sid;
    }

    public SeriesTextRecord() {
        this.field_4_text = "";
        this.is16bit = false;
    }

    public SeriesTextRecord(SeriesTextRecord seriesTextRecord) {
        super(seriesTextRecord);
        this.field_1_id = seriesTextRecord.field_1_id;
        this.is16bit = seriesTextRecord.is16bit;
        this.field_4_text = seriesTextRecord.field_4_text;
    }

    public SeriesTextRecord(RecordInputStream recordInputStream) {
        this.field_1_id = recordInputStream.readUShort();
        int readUByte = recordInputStream.readUByte();
        boolean z = (recordInputStream.readUByte() & 1) == 0 ? false : true;
        this.is16bit = z;
        if (z) {
            this.field_4_text = recordInputStream.readUnicodeLEString(readUByte);
        } else {
            this.field_4_text = recordInputStream.readCompressedUnicode(readUByte);
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_id);
        littleEndianOutput.writeByte(this.field_4_text.length());
        if (this.is16bit) {
            littleEndianOutput.writeByte(1);
            StringUtil.putUnicodeLE(this.field_4_text, littleEndianOutput);
            return;
        }
        littleEndianOutput.writeByte(0);
        StringUtil.putCompressedUnicode(this.field_4_text, littleEndianOutput);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.field_4_text.length() * (this.is16bit ? 2 : 1)) + 4;
    }

    public SeriesTextRecord copy() {
        return new SeriesTextRecord(this);
    }

    public int getId() {
        return this.field_1_id;
    }

    public void setId(int i) {
        this.field_1_id = i;
    }

    public String getText() {
        return this.field_4_text;
    }

    public void setText(String str) {
        if (str.length() <= 255) {
            this.field_4_text = str;
            this.is16bit = StringUtil.hasMultibyte(str);
            return;
        }
        throw new IllegalArgumentException("Text is too long (" + str.length() + ">255)");
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SERIES_TEXT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", new SeriesTextRecord$$ExternalSyntheticLambda0(this), "bit16", new SeriesTextRecord$$ExternalSyntheticLambda1(this), "text", new SeriesTextRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-SeriesTextRecord  reason: not valid java name */
    public /* synthetic */ Object m2160lambda$getGenericProperties$0$orgapachepoihssfrecordchartSeriesTextRecord() {
        return Boolean.valueOf(this.is16bit);
    }
}
