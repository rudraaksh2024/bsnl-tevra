package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DateWindow1904Record extends StandardRecord {
    public static final short sid = 34;
    private short field_1_window;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 34;
    }

    public DateWindow1904Record() {
    }

    public DateWindow1904Record(DateWindow1904Record dateWindow1904Record) {
        super(dateWindow1904Record);
        this.field_1_window = dateWindow1904Record.field_1_window;
    }

    public DateWindow1904Record(RecordInputStream recordInputStream) {
        this.field_1_window = recordInputStream.readShort();
    }

    public void setWindowing(short s) {
        this.field_1_window = s;
    }

    public short getWindowing() {
        return this.field_1_window;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getWindowing());
    }

    public DateWindow1904Record copy() {
        return new DateWindow1904Record(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DATE_WINDOW_1904;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("is1904", new DateWindow1904Record$$ExternalSyntheticLambda0(this));
    }
}
