package org.apache.poi.hssf.record;

public final class VerticalPageBreakRecord extends PageBreakRecord {
    public static final short sid = 26;

    public short getSid() {
        return 26;
    }

    public VerticalPageBreakRecord() {
    }

    public VerticalPageBreakRecord(VerticalPageBreakRecord verticalPageBreakRecord) {
        super((PageBreakRecord) verticalPageBreakRecord);
    }

    public VerticalPageBreakRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
    }

    public VerticalPageBreakRecord copy() {
        return new VerticalPageBreakRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VERTICAL_PAGE_BREAK;
    }
}
