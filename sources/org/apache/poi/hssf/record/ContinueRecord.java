package org.apache.poi.hssf.record;

import com.google.firebase.messaging.Constants;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ContinueRecord extends StandardRecord {
    public static final short sid = 60;
    private byte[] _data;

    public short getSid() {
        return 60;
    }

    public ContinueRecord(byte[] bArr) {
        this._data = (byte[]) bArr.clone();
    }

    public ContinueRecord(ContinueRecord continueRecord) {
        super(continueRecord);
        byte[] bArr = continueRecord._data;
        this._data = bArr == null ? null : (byte[]) bArr.clone();
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this._data.length;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this._data);
    }

    public byte[] getData() {
        return this._data;
    }

    public ContinueRecord(RecordInputStream recordInputStream) {
        this._data = recordInputStream.readRemainder();
    }

    public ContinueRecord copy() {
        return new ContinueRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CONTINUE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, new ContinueRecord$$ExternalSyntheticLambda0(this));
    }
}
