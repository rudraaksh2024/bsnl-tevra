package org.apache.poi.hssf.record;

import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class UserSViewBegin extends StandardRecord {
    public static final short sid = 426;
    private byte[] _rawData;

    public short getSid() {
        return sid;
    }

    public UserSViewBegin(UserSViewBegin userSViewBegin) {
        super(userSViewBegin);
        byte[] bArr = userSViewBegin._rawData;
        this._rawData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public UserSViewBegin(byte[] bArr) {
        this._rawData = bArr;
    }

    public UserSViewBegin(RecordInputStream recordInputStream) {
        this._rawData = recordInputStream.readRemainder();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this._rawData);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this._rawData.length;
    }

    public byte[] getGuid() {
        return Arrays.copyOf(this._rawData, 16);
    }

    public UserSViewBegin copy() {
        return new UserSViewBegin(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.USER_SVIEW_BEGIN;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("guid", new UserSViewBegin$$ExternalSyntheticLambda0(this), Constants.MessagePayloadKeys.RAW_DATA, new UserSViewBegin$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UserSViewBegin  reason: not valid java name */
    public /* synthetic */ Object m2113lambda$getGenericProperties$0$orgapachepoihssfrecordUserSViewBegin() {
        return this._rawData;
    }
}
