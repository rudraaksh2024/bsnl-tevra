package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public final class WriteAccessRecord extends StandardRecord {
    private static final int DATA_SIZE = 112;
    private static final byte[] PADDING;
    private static final byte PAD_CHAR = 32;
    public static final short sid = 92;
    private String field_1_username;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 112;
    }

    public short getSid() {
        return 92;
    }

    static {
        byte[] bArr = new byte[112];
        PADDING = bArr;
        Arrays.fill(bArr, (byte) 32);
    }

    public WriteAccessRecord() {
        setUsername("");
    }

    public WriteAccessRecord(WriteAccessRecord writeAccessRecord) {
        super(writeAccessRecord);
        this.field_1_username = writeAccessRecord.field_1_username;
    }

    public WriteAccessRecord(RecordInputStream recordInputStream) {
        String str;
        if (recordInputStream.remaining() <= 112) {
            int readUShort = recordInputStream.readUShort();
            int readUByte = recordInputStream.readUByte();
            if (readUShort > 112 || (readUByte & 254) != 0) {
                int remaining = recordInputStream.remaining() + 3;
                byte[] bArr = new byte[remaining];
                LittleEndian.putUShort(bArr, 0, readUShort);
                LittleEndian.putByte(bArr, 2, readUByte);
                recordInputStream.readFully(bArr, 3, remaining - 3);
                setUsername(new String(bArr, StringUtil.UTF8).trim());
                return;
            }
            if ((readUByte & 1) == 0) {
                str = StringUtil.readCompressedUnicode(recordInputStream, readUShort);
            } else {
                str = StringUtil.readUnicodeLE(recordInputStream, readUShort);
            }
            this.field_1_username = str.trim();
            for (int remaining2 = recordInputStream.remaining(); remaining2 > 0; remaining2--) {
                recordInputStream.readUByte();
            }
            return;
        }
        throw new RecordFormatException("Expected data size (112) but got (" + recordInputStream.remaining() + ")");
    }

    public void setUsername(String str) {
        if (112 - ((str.length() * (StringUtil.hasMultibyte(str) ? 2 : 1)) + 3) >= 0) {
            this.field_1_username = str;
            return;
        }
        throw new IllegalArgumentException("Name is too long: " + str);
    }

    public String getUsername() {
        return this.field_1_username;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        String username = getUsername();
        boolean hasMultibyte = StringUtil.hasMultibyte(username);
        littleEndianOutput.writeShort(username.length());
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        if (hasMultibyte) {
            StringUtil.putUnicodeLE(username, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(username, littleEndianOutput);
        }
        littleEndianOutput.write(PADDING, 0, 112 - ((username.length() * (hasMultibyte ? 2 : 1)) + 3));
    }

    public WriteAccessRecord copy() {
        return new WriteAccessRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WRITE_ACCESS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("username", new WriteAccessRecord$$ExternalSyntheticLambda0(this));
    }
}
