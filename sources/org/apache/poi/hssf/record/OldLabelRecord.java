package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

public final class OldLabelRecord extends OldCellRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) OldLabelRecord.class);
    public static final short biff2_sid = 4;
    public static final short biff345_sid = 516;
    private CodepageRecord codepage;
    private short field_4_string_len;
    private final byte[] field_5_bytes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OldLabelRecord(RecordInputStream recordInputStream) {
        super(recordInputStream, recordInputStream.getSid() == 4);
        if (isBiff2()) {
            this.field_4_string_len = (short) recordInputStream.readUByte();
        } else {
            this.field_4_string_len = recordInputStream.readShort();
        }
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) this.field_4_string_len, HSSFWorkbook.getMaxRecordLength());
        this.field_5_bytes = safelyAllocate;
        recordInputStream.read(safelyAllocate, 0, this.field_4_string_len);
        if (recordInputStream.remaining() > 0) {
            LOG.atInfo().log("LabelRecord data remains: {} : {}", Unbox.box(recordInputStream.remaining()), HexDump.toHex(recordInputStream.readRemainder()));
        }
    }

    public void setCodePage(CodepageRecord codepageRecord) {
        this.codepage = codepageRecord;
    }

    public short getStringLength() {
        return this.field_4_string_len;
    }

    public String getValue() {
        return OldStringRecord.getString(this.field_5_bytes, this.codepage);
    }

    public int serialize(int i, byte[] bArr) {
        throw new RecordFormatException("Old Label Records are supported READ ONLY");
    }

    public int getRecordSize() {
        throw new RecordFormatException("Old Label Records are supported READ ONLY");
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LABEL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new OldLabelRecord$$ExternalSyntheticLambda0(this), "stringLength", new OldLabelRecord$$ExternalSyntheticLambda1(this), "value", new OldLabelRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-OldLabelRecord  reason: not valid java name */
    public /* synthetic */ Object m2065lambda$getGenericProperties$0$orgapachepoihssfrecordOldLabelRecord() {
        return super.getGenericProperties();
    }
}
