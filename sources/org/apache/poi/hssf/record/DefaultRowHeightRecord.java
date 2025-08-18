package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DefaultRowHeightRecord extends StandardRecord {
    public static final short DEFAULT_ROW_HEIGHT = 255;
    public static final short sid = 549;
    private short field_1_option_flags;
    private short field_2_row_height;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 4;
    }

    public short getSid() {
        return sid;
    }

    public DefaultRowHeightRecord() {
        this.field_1_option_flags = 0;
        this.field_2_row_height = 255;
    }

    public DefaultRowHeightRecord(DefaultRowHeightRecord defaultRowHeightRecord) {
        super(defaultRowHeightRecord);
        this.field_1_option_flags = defaultRowHeightRecord.field_1_option_flags;
        this.field_2_row_height = defaultRowHeightRecord.field_2_row_height;
    }

    public DefaultRowHeightRecord(RecordInputStream recordInputStream) {
        this.field_1_option_flags = recordInputStream.readShort();
        this.field_2_row_height = recordInputStream.readShort();
    }

    public void setOptionFlags(short s) {
        this.field_1_option_flags = s;
    }

    public void setRowHeight(short s) {
        this.field_2_row_height = s;
    }

    public short getOptionFlags() {
        return this.field_1_option_flags;
    }

    public short getRowHeight() {
        return this.field_2_row_height;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getOptionFlags());
        littleEndianOutput.writeShort(getRowHeight());
    }

    public DefaultRowHeightRecord copy() {
        return new DefaultRowHeightRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DEFAULT_ROW_HEIGHT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("optionFlags", new DefaultRowHeightRecord$$ExternalSyntheticLambda0(this), "rowHeight", new DefaultRowHeightRecord$$ExternalSyntheticLambda1(this));
    }
}
