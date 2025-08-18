package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DSFRecord extends StandardRecord {
    private static final BitField biff5BookStreamFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 353;
    private int _options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    private DSFRecord(DSFRecord dSFRecord) {
        super(dSFRecord);
        this._options = dSFRecord._options;
    }

    private DSFRecord(int i) {
        this._options = i;
    }

    public DSFRecord(boolean z) {
        this(0);
        this._options = biff5BookStreamFlag.setBoolean(0, z);
    }

    public DSFRecord(RecordInputStream recordInputStream) {
        this((int) recordInputStream.readShort());
    }

    public boolean isBiff5BookStreamPresent() {
        return biff5BookStreamFlag.isSet(this._options);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
    }

    public DSFRecord copy() {
        return new DSFRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DSF;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new DSFRecord$$ExternalSyntheticLambda0(this), "biff5BookStreamPresent", new DSFRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DSFRecord  reason: not valid java name */
    public /* synthetic */ Object m1991lambda$getGenericProperties$0$orgapachepoihssfrecordDSFRecord() {
        return Integer.valueOf(this._options);
    }
}
