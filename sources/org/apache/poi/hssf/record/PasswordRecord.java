package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PasswordRecord extends StandardRecord {
    public static final short sid = 19;
    private int field_1_password;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 19;
    }

    public PasswordRecord(int i) {
        this.field_1_password = i;
    }

    public PasswordRecord(PasswordRecord passwordRecord) {
        super(passwordRecord);
        this.field_1_password = passwordRecord.field_1_password;
    }

    public PasswordRecord(RecordInputStream recordInputStream) {
        this.field_1_password = recordInputStream.readShort();
    }

    public void setPassword(int i) {
        this.field_1_password = i;
    }

    public int getPassword() {
        return this.field_1_password;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_password);
    }

    public PasswordRecord copy() {
        return new PasswordRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PASSWORD;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("password", new PasswordRecord$$ExternalSyntheticLambda0(this));
    }
}
