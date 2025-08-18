package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class FileSharingRecord extends StandardRecord {
    public static final short sid = 91;
    private short field_1_readonly;
    private short field_2_password;
    private byte field_3_username_unicode_options;
    private String field_3_username_value;

    public short getSid() {
        return 91;
    }

    public FileSharingRecord() {
    }

    public FileSharingRecord(FileSharingRecord fileSharingRecord) {
        super(fileSharingRecord);
        this.field_1_readonly = fileSharingRecord.field_1_readonly;
        this.field_2_password = fileSharingRecord.field_2_password;
        this.field_3_username_unicode_options = fileSharingRecord.field_3_username_unicode_options;
        this.field_3_username_value = fileSharingRecord.field_3_username_value;
    }

    public FileSharingRecord(RecordInputStream recordInputStream) {
        this.field_1_readonly = recordInputStream.readShort();
        this.field_2_password = recordInputStream.readShort();
        short readShort = recordInputStream.readShort();
        if (readShort > 0) {
            this.field_3_username_unicode_options = recordInputStream.readByte();
            this.field_3_username_value = recordInputStream.readCompressedUnicode(readShort);
            return;
        }
        this.field_3_username_value = "";
    }

    public void setReadOnly(short s) {
        this.field_1_readonly = s;
    }

    public short getReadOnly() {
        return this.field_1_readonly;
    }

    public void setPassword(short s) {
        this.field_2_password = s;
    }

    public short getPassword() {
        return this.field_2_password;
    }

    public String getUsername() {
        return this.field_3_username_value;
    }

    public void setUsername(String str) {
        this.field_3_username_value = str;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getReadOnly());
        littleEndianOutput.writeShort(getPassword());
        littleEndianOutput.writeShort(this.field_3_username_value.length());
        if (this.field_3_username_value.length() > 0) {
            littleEndianOutput.writeByte(this.field_3_username_unicode_options);
            StringUtil.putCompressedUnicode(getUsername(), littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int length = this.field_3_username_value.length();
        if (length < 1) {
            return 6;
        }
        return length + 7;
    }

    public FileSharingRecord copy() {
        return new FileSharingRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FILE_SHARING;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("readOnly", new FileSharingRecord$$ExternalSyntheticLambda0(this), "password", new FileSharingRecord$$ExternalSyntheticLambda1(this), "username", new FileSharingRecord$$ExternalSyntheticLambda2(this));
    }
}
