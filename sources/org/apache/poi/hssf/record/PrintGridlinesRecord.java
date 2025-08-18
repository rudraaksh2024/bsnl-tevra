package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PrintGridlinesRecord extends StandardRecord {
    public static final short sid = 43;
    private short field_1_print_gridlines;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 43;
    }

    public PrintGridlinesRecord() {
    }

    public PrintGridlinesRecord(PrintGridlinesRecord printGridlinesRecord) {
        super(printGridlinesRecord);
        this.field_1_print_gridlines = printGridlinesRecord.field_1_print_gridlines;
    }

    public PrintGridlinesRecord(RecordInputStream recordInputStream) {
        this.field_1_print_gridlines = recordInputStream.readShort();
    }

    public void setPrintGridlines(boolean z) {
        this.field_1_print_gridlines = z ? (short) 1 : 0;
    }

    public boolean getPrintGridlines() {
        return this.field_1_print_gridlines == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_print_gridlines);
    }

    public PrintGridlinesRecord copy() {
        return new PrintGridlinesRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PRINT_GRIDLINES;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("printGridlines", new PrintGridlinesRecord$$ExternalSyntheticLambda0(this));
    }
}
