package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;

public final class OldStringRecord implements GenericRecord {
    public static final short biff2_sid = 7;
    public static final short biff345_sid = 519;
    private CodepageRecord codepage;
    private short field_1_string_len;
    private byte[] field_2_bytes;
    private short sid;

    public OldStringRecord(RecordInputStream recordInputStream) {
        this.sid = recordInputStream.getSid();
        if (recordInputStream.getSid() == 7) {
            this.field_1_string_len = (short) recordInputStream.readUByte();
        } else {
            this.field_1_string_len = recordInputStream.readShort();
        }
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) this.field_1_string_len, HSSFWorkbook.getMaxRecordLength());
        this.field_2_bytes = safelyAllocate;
        recordInputStream.read(safelyAllocate, 0, this.field_1_string_len);
    }

    public boolean isBiff2() {
        return this.sid == 7;
    }

    public short getSid() {
        return this.sid;
    }

    public void setCodePage(CodepageRecord codepageRecord) {
        this.codepage = codepageRecord;
    }

    public String getString() {
        return getString(this.field_2_bytes, this.codepage);
    }

    protected static String getString(byte[] bArr, CodepageRecord codepageRecord) {
        try {
            return CodePageUtil.getStringFromCodePage(bArr, codepageRecord != null ? codepageRecord.getCodepage() & 65535 : 1252);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported codepage requested", e);
        }
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STRING;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(TypedValues.Custom.S_STRING, new OldStringRecord$$ExternalSyntheticLambda0(this));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }
}
