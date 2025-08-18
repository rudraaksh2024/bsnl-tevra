package org.apache.poi.hssf.record;

public final class FooterRecord extends HeaderFooterBase {
    public static final short sid = 21;

    public short getSid() {
        return 21;
    }

    public FooterRecord(String str) {
        super(str);
    }

    public FooterRecord(FooterRecord footerRecord) {
        super((HeaderFooterBase) footerRecord);
    }

    public FooterRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
    }

    public FooterRecord copy() {
        return new FooterRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FOOTER;
    }
}
