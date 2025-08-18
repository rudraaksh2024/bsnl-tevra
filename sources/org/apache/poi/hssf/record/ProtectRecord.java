package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ProtectRecord extends StandardRecord {
    private static final BitField protectFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 18;
    private int _options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 18;
    }

    private ProtectRecord(int i) {
        this._options = i;
    }

    private ProtectRecord(ProtectRecord protectRecord) {
        super(protectRecord);
        this._options = protectRecord._options;
    }

    public ProtectRecord(boolean z) {
        this(0);
        setProtect(z);
    }

    public ProtectRecord(RecordInputStream recordInputStream) {
        this((int) recordInputStream.readShort());
    }

    public void setProtect(boolean z) {
        this._options = protectFlag.setBoolean(this._options, z);
    }

    public boolean getProtect() {
        return protectFlag.isSet(this._options);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
    }

    public ProtectRecord copy() {
        return new ProtectRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PROTECT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new ProtectRecord$$ExternalSyntheticLambda0(this), "protect", new ProtectRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ProtectRecord  reason: not valid java name */
    public /* synthetic */ Object m2078lambda$getGenericProperties$0$orgapachepoihssfrecordProtectRecord() {
        return Integer.valueOf(this._options);
    }
}
