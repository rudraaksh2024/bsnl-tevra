package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class WindowProtectRecord extends StandardRecord {
    private static final BitField settingsProtectedFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 25;
    private int _options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 25;
    }

    public WindowProtectRecord(int i) {
        this._options = i;
    }

    public WindowProtectRecord(WindowProtectRecord windowProtectRecord) {
        super(windowProtectRecord);
        this._options = windowProtectRecord._options;
    }

    public WindowProtectRecord(RecordInputStream recordInputStream) {
        this(recordInputStream.readUShort());
    }

    public WindowProtectRecord(boolean z) {
        this(0);
        setProtect(z);
    }

    public void setProtect(boolean z) {
        this._options = settingsProtectedFlag.setBoolean(this._options, z);
    }

    public boolean getProtect() {
        return settingsProtectedFlag.isSet(this._options);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
    }

    public WindowProtectRecord copy() {
        return new WindowProtectRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WINDOW_PROTECT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new WindowProtectRecord$$ExternalSyntheticLambda0(this), "protect", new WindowProtectRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-WindowProtectRecord  reason: not valid java name */
    public /* synthetic */ Object m2115lambda$getGenericProperties$0$orgapachepoihssfrecordWindowProtectRecord() {
        return Integer.valueOf(this._options);
    }
}
