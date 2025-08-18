package org.apache.poi.hssf.record;

public final class HeaderRecord extends HeaderFooterBase {
    public static final short sid = 20;

    public short getSid() {
        return 20;
    }

    public HeaderRecord(String str) {
        super(str);
    }

    public HeaderRecord(HeaderRecord headerRecord) {
        super((HeaderFooterBase) headerRecord);
    }

    public HeaderRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
    }

    public HeaderRecord copy() {
        return new HeaderRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HEADER;
    }
}
