package org.apache.poi.hssf.record;

import com.google.firebase.messaging.Constants;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class UserSViewEnd extends StandardRecord {
    public static final short sid = 427;
    private byte[] _rawData;

    public short getSid() {
        return sid;
    }

    public UserSViewEnd(UserSViewEnd userSViewEnd) {
        super(userSViewEnd);
        byte[] bArr = userSViewEnd._rawData;
        this._rawData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public UserSViewEnd(byte[] bArr) {
        this._rawData = bArr;
    }

    public UserSViewEnd(RecordInputStream recordInputStream) {
        this._rawData = recordInputStream.readRemainder();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this._rawData);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this._rawData.length;
    }

    public UserSViewEnd copy() {
        return new UserSViewEnd(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.USER_SVIEW_END;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(Constants.MessagePayloadKeys.RAW_DATA, new UserSViewEnd$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UserSViewEnd  reason: not valid java name */
    public /* synthetic */ Object m2114lambda$getGenericProperties$0$orgapachepoihssfrecordUserSViewEnd() {
        return this._rawData;
    }
}
