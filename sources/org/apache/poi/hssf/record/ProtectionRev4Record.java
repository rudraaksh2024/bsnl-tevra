package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ProtectionRev4Record extends StandardRecord {
    private static final BitField protectedFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 431;
    private int _options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    private ProtectionRev4Record(int i) {
        this._options = i;
    }

    private ProtectionRev4Record(ProtectionRev4Record protectionRev4Record) {
        super(protectionRev4Record);
        this._options = protectionRev4Record._options;
    }

    public ProtectionRev4Record(boolean z) {
        this(0);
        setProtect(z);
    }

    public ProtectionRev4Record(RecordInputStream recordInputStream) {
        this(recordInputStream.readUShort());
    }

    public void setProtect(boolean z) {
        this._options = protectedFlag.setBoolean(this._options, z);
    }

    public boolean getProtect() {
        return protectedFlag.isSet(this._options);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
    }

    public ProtectionRev4Record copy() {
        return new ProtectionRev4Record(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PROTECTION_REV_4;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new ProtectionRev4Record$$ExternalSyntheticLambda0(this), "protect", new ProtectionRev4Record$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ProtectionRev4Record  reason: not valid java name */
    public /* synthetic */ Object m2079lambda$getGenericProperties$0$orgapachepoihssfrecordProtectionRev4Record() {
        return Integer.valueOf(this._options);
    }
}
