package org.apache.poi.hssf.record;

import org.apache.poi.ss.util.CellRangeAddress;

public final class CFHeaderRecord extends CFHeaderBase {
    public static final short sid = 432;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "CFHEADER";
    }

    public short getSid() {
        return sid;
    }

    public CFHeaderRecord() {
        createEmpty();
    }

    public CFHeaderRecord(CFHeaderRecord cFHeaderRecord) {
        super(cFHeaderRecord);
    }

    public CFHeaderRecord(CellRangeAddress[] cellRangeAddressArr, int i) {
        super(cellRangeAddressArr, i);
    }

    public CFHeaderRecord(RecordInputStream recordInputStream) {
        read(recordInputStream);
    }

    public CFHeaderRecord copy() {
        return new CFHeaderRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_HEADER;
    }
}
