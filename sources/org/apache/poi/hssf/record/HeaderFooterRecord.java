package org.apache.poi.hssf.record;

import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class HeaderFooterRecord extends StandardRecord {
    private static final byte[] BLANK_GUID = new byte[16];
    public static final short sid = 2204;
    private byte[] _rawData;

    public short getSid() {
        return sid;
    }

    public HeaderFooterRecord(byte[] bArr) {
        this._rawData = bArr;
    }

    public HeaderFooterRecord(HeaderFooterRecord headerFooterRecord) {
        super(headerFooterRecord);
        byte[] bArr = headerFooterRecord._rawData;
        this._rawData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public HeaderFooterRecord(RecordInputStream recordInputStream) {
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
        return Arrays.copyOfRange(this._rawData, 12, 28);
    }

    public boolean isCurrentSheet() {
        return Arrays.equals(getGuid(), BLANK_GUID);
    }

    public HeaderFooterRecord copy() {
        return new HeaderFooterRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HEADER_FOOTER;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(Constants.MessagePayloadKeys.RAW_DATA, new HeaderFooterRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-HeaderFooterRecord  reason: not valid java name */
    public /* synthetic */ Object m2030lambda$getGenericProperties$0$orgapachepoihssfrecordHeaderFooterRecord() {
        return this._rawData;
    }
}
