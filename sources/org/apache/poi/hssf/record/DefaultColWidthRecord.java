package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DefaultColWidthRecord extends StandardRecord {
    public static final int DEFAULT_COLUMN_WIDTH = 8;
    public static final short sid = 85;
    private int field_1_col_width;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 85;
    }

    public DefaultColWidthRecord() {
        this.field_1_col_width = 8;
    }

    public DefaultColWidthRecord(DefaultColWidthRecord defaultColWidthRecord) {
        super(defaultColWidthRecord);
        this.field_1_col_width = defaultColWidthRecord.field_1_col_width;
    }

    public DefaultColWidthRecord(RecordInputStream recordInputStream) {
        this.field_1_col_width = recordInputStream.readUShort();
    }

    public void setColWidth(int i) {
        this.field_1_col_width = i;
    }

    public int getColWidth() {
        return this.field_1_col_width;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getColWidth());
    }

    public DefaultColWidthRecord copy() {
        return new DefaultColWidthRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DEFAULT_COL_WIDTH;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("colWidth", new DefaultColWidthRecord$$ExternalSyntheticLambda0(this));
    }
}
