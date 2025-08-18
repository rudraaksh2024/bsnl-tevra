package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PasswordRev4Record extends StandardRecord {
    public static final short sid = 444;
    private int field_1_password;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public PasswordRev4Record(int i) {
        this.field_1_password = i;
    }

    public PasswordRev4Record(PasswordRev4Record passwordRev4Record) {
        super(passwordRev4Record);
        this.field_1_password = passwordRev4Record.field_1_password;
    }

    public PasswordRev4Record(RecordInputStream recordInputStream) {
        this.field_1_password = recordInputStream.readShort();
    }

    public void setPassword(short s) {
        this.field_1_password = s;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_password);
    }

    public PasswordRev4Record copy() {
        return new PasswordRev4Record(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PASSWORD_REV_4;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("password", new PasswordRev4Record$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PasswordRev4Record  reason: not valid java name */
    public /* synthetic */ Object m2077lambda$getGenericProperties$0$orgapachepoihssfrecordPasswordRev4Record() {
        return Integer.valueOf(this.field_1_password);
    }
}
