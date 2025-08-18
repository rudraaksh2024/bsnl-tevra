package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PrintHeadersRecord extends StandardRecord {
    public static final short sid = 42;
    private short field_1_print_headers;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 42;
    }

    public PrintHeadersRecord() {
    }

    public PrintHeadersRecord(PrintHeadersRecord printHeadersRecord) {
        super(printHeadersRecord);
        this.field_1_print_headers = printHeadersRecord.field_1_print_headers;
    }

    public PrintHeadersRecord(RecordInputStream recordInputStream) {
        this.field_1_print_headers = recordInputStream.readShort();
    }

    public void setPrintHeaders(boolean z) {
        if (z) {
            this.field_1_print_headers = 1;
        } else {
            this.field_1_print_headers = 0;
        }
    }

    public boolean getPrintHeaders() {
        return this.field_1_print_headers == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_print_headers);
    }

    public PrintHeadersRecord copy() {
        return new PrintHeadersRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PRINT_HEADERS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("printHeaders", new PrintHeadersRecord$$ExternalSyntheticLambda0(this));
    }
}
