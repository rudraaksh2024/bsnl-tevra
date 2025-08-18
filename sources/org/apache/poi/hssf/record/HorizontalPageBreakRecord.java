package org.apache.poi.hssf.record;

public final class HorizontalPageBreakRecord extends PageBreakRecord {
    public static final short sid = 27;

    public short getSid() {
        return 27;
    }

    public HorizontalPageBreakRecord() {
    }

    public HorizontalPageBreakRecord(HorizontalPageBreakRecord horizontalPageBreakRecord) {
        super((PageBreakRecord) horizontalPageBreakRecord);
    }

    public HorizontalPageBreakRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
    }

    public HorizontalPageBreakRecord copy() {
        return new HorizontalPageBreakRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HORIZONTAL_PAGE_BREAK;
    }
}
