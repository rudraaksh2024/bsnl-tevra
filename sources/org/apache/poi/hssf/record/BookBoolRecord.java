package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class BookBoolRecord extends StandardRecord {
    public static final short sid = 218;
    private short field_1_save_link_values;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public BookBoolRecord() {
    }

    public BookBoolRecord(BookBoolRecord bookBoolRecord) {
        super(bookBoolRecord);
        this.field_1_save_link_values = bookBoolRecord.field_1_save_link_values;
    }

    public BookBoolRecord(RecordInputStream recordInputStream) {
        this.field_1_save_link_values = recordInputStream.readShort();
    }

    public void setSaveLinkValues(short s) {
        this.field_1_save_link_values = s;
    }

    public short getSaveLinkValues() {
        return this.field_1_save_link_values;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_save_link_values);
    }

    public BookBoolRecord copy() {
        return new BookBoolRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOOK_BOOL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("saveLinkValues", new BookBoolRecord$$ExternalSyntheticLambda0(this));
    }
}
