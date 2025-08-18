package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class FormatRecord extends StandardRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) FormatRecord.class);
    public static final short sid = 1054;
    private final int field_1_index_code;
    private final boolean field_3_hasMultibyte;
    private final String field_4_formatstring;

    public short getSid() {
        return sid;
    }

    private FormatRecord(FormatRecord formatRecord) {
        super(formatRecord);
        this.field_1_index_code = formatRecord.field_1_index_code;
        this.field_3_hasMultibyte = formatRecord.field_3_hasMultibyte;
        this.field_4_formatstring = formatRecord.field_4_formatstring;
    }

    public FormatRecord(int i, String str) {
        this.field_1_index_code = i;
        this.field_4_formatstring = str;
        this.field_3_hasMultibyte = StringUtil.hasMultibyte(str);
    }

    public FormatRecord(RecordInputStream recordInputStream) {
        this.field_1_index_code = recordInputStream.readShort();
        int readUShort = recordInputStream.readUShort();
        boolean z = (recordInputStream.readByte() & 1) != 0;
        this.field_3_hasMultibyte = z;
        if (z) {
            this.field_4_formatstring = readStringCommon(recordInputStream, readUShort, false);
        } else {
            this.field_4_formatstring = readStringCommon(recordInputStream, readUShort, true);
        }
    }

    public int getIndexCode() {
        return this.field_1_index_code;
    }

    public String getFormatString() {
        return this.field_4_formatstring;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        String formatString = getFormatString();
        littleEndianOutput.writeShort(getIndexCode());
        littleEndianOutput.writeShort(formatString.length());
        littleEndianOutput.writeByte(this.field_3_hasMultibyte ? 1 : 0);
        if (this.field_3_hasMultibyte) {
            StringUtil.putUnicodeLE(formatString, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(formatString, littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (getFormatString().length() * (this.field_3_hasMultibyte ? 2 : 1)) + 5;
    }

    public FormatRecord copy() {
        return new FormatRecord(this);
    }

    private static String readStringCommon(RecordInputStream recordInputStream, int i, boolean z) {
        char[] cArr;
        int i2;
        if (i < 0 || i > 1048576) {
            throw new IllegalArgumentException("Bad requested string length (" + i + ")");
        }
        int remaining = recordInputStream.remaining();
        if (!z) {
            remaining /= 2;
        }
        if (i == remaining) {
            cArr = new char[i];
        } else {
            cArr = new char[remaining];
        }
        for (int i3 = 0; i3 < cArr.length; i3++) {
            if (z) {
                i2 = recordInputStream.readUByte();
            } else {
                i2 = recordInputStream.readShort();
            }
            cArr[i3] = (char) i2;
        }
        if (recordInputStream.available() == 1) {
            char[] copyOf = Arrays.copyOf(cArr, cArr.length + 1);
            copyOf[cArr.length] = (char) recordInputStream.readUByte();
            cArr = copyOf;
        }
        if (recordInputStream.available() > 0) {
            LOG.atInfo().log("FormatRecord has {} unexplained bytes. Silently skipping", (Object) Unbox.box(recordInputStream.available()));
            while (recordInputStream.available() > 0) {
                recordInputStream.readByte();
            }
        }
        return new String(cArr);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FORMAT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("indexCode", new FormatRecord$$ExternalSyntheticLambda0(this), "unicode", new FormatRecord$$ExternalSyntheticLambda1(this), "formatString", new FormatRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FormatRecord  reason: not valid java name */
    public /* synthetic */ Object m2024lambda$getGenericProperties$0$orgapachepoihssfrecordFormatRecord() {
        return Boolean.valueOf(this.field_3_hasMultibyte);
    }
}
