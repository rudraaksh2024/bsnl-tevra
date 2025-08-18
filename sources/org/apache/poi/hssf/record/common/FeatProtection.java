package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class FeatProtection implements SharedFeature {
    public static final long HAS_SELF_RELATIVE_SECURITY_FEATURE = 1;
    public static final long NO_SELF_RELATIVE_SECURITY_FEATURE = 0;
    private int fSD;
    private int passwordVerifier;
    private byte[] securityDescriptor;
    private String title;

    public FeatProtection() {
        this.securityDescriptor = new byte[0];
    }

    public FeatProtection(FeatProtection featProtection) {
        this.fSD = featProtection.fSD;
        this.passwordVerifier = featProtection.passwordVerifier;
        this.title = featProtection.title;
        byte[] bArr = featProtection.securityDescriptor;
        this.securityDescriptor = bArr == null ? null : (byte[]) bArr.clone();
    }

    public FeatProtection(RecordInputStream recordInputStream) {
        this.fSD = recordInputStream.readInt();
        this.passwordVerifier = recordInputStream.readInt();
        this.title = StringUtil.readUnicodeString(recordInputStream);
        this.securityDescriptor = recordInputStream.readRemainder();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.fSD);
        littleEndianOutput.writeInt(this.passwordVerifier);
        StringUtil.writeUnicodeString(littleEndianOutput, this.title);
        littleEndianOutput.write(this.securityDescriptor);
    }

    public int getDataSize() {
        return StringUtil.getEncodedSize(this.title) + 8 + this.securityDescriptor.length;
    }

    public int getPasswordVerifier() {
        return this.passwordVerifier;
    }

    public void setPasswordVerifier(int i) {
        this.passwordVerifier = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getFSD() {
        return this.fSD;
    }

    public FeatProtection copy() {
        return new FeatProtection(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("FSD", new FeatProtection$$ExternalSyntheticLambda0(this), "passwordVerifier", new FeatProtection$$ExternalSyntheticLambda1(this), "title", new FeatProtection$$ExternalSyntheticLambda2(this), "securityDescriptor", new FeatProtection$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-common-FeatProtection  reason: not valid java name */
    public /* synthetic */ Object m2163lambda$getGenericProperties$0$orgapachepoihssfrecordcommonFeatProtection() {
        return this.securityDescriptor;
    }
}
