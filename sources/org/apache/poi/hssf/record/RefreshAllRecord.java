package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class RefreshAllRecord extends StandardRecord {
    private static final BitField refreshFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 439;
    private int _options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    private RefreshAllRecord(int i) {
        this._options = i;
    }

    private RefreshAllRecord(RefreshAllRecord refreshAllRecord) {
        super(refreshAllRecord);
        this._options = refreshAllRecord._options;
    }

    public RefreshAllRecord(RecordInputStream recordInputStream) {
        this(recordInputStream.readUShort());
    }

    public RefreshAllRecord(boolean z) {
        this(0);
        setRefreshAll(z);
    }

    public void setRefreshAll(boolean z) {
        this._options = refreshFlag.setBoolean(this._options, z);
    }

    public boolean getRefreshAll() {
        return refreshFlag.isSet(this._options);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
    }

    public RefreshAllRecord copy() {
        return new RefreshAllRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.REFRESH_ALL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new RefreshAllRecord$$ExternalSyntheticLambda0(this), "refreshAll", new RefreshAllRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RefreshAllRecord  reason: not valid java name */
    public /* synthetic */ Object m2082lambda$getGenericProperties$0$orgapachepoihssfrecordRefreshAllRecord() {
        return Integer.valueOf(this._options);
    }
}
