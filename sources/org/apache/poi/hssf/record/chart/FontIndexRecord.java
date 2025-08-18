package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FontIndexRecord extends StandardRecord {
    public static final short sid = 4134;
    private short field_1_fontIndex;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public FontIndexRecord() {
    }

    public FontIndexRecord(FontIndexRecord fontIndexRecord) {
        super(fontIndexRecord);
        this.field_1_fontIndex = fontIndexRecord.field_1_fontIndex;
    }

    public FontIndexRecord(RecordInputStream recordInputStream) {
        this.field_1_fontIndex = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_fontIndex);
    }

    public FontIndexRecord copy() {
        return new FontIndexRecord(this);
    }

    public short getFontIndex() {
        return this.field_1_fontIndex;
    }

    public void setFontIndex(short s) {
        this.field_1_fontIndex = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FONT_INDEX;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("fontIdex", new FontIndexRecord$$ExternalSyntheticLambda0(this));
    }
}
