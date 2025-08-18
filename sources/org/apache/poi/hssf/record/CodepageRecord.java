package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CodepageRecord extends StandardRecord {
    public static final short CODEPAGE = 1200;
    public static final short sid = 66;
    private short field_1_codepage;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 66;
    }

    public CodepageRecord() {
    }

    public CodepageRecord(CodepageRecord codepageRecord) {
        super(codepageRecord);
        this.field_1_codepage = codepageRecord.field_1_codepage;
    }

    public CodepageRecord(RecordInputStream recordInputStream) {
        this.field_1_codepage = recordInputStream.readShort();
    }

    public void setCodepage(short s) {
        this.field_1_codepage = s;
    }

    public short getCodepage() {
        return this.field_1_codepage;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getCodepage());
    }

    public CodepageRecord copy() {
        return new CodepageRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CODEPAGE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("codepage", new CodepageRecord$$ExternalSyntheticLambda0(this));
    }
}
