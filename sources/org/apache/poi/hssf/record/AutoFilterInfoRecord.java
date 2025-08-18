package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class AutoFilterInfoRecord extends StandardRecord {
    public static final short sid = 157;
    private short _cEntries;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 157;
    }

    public AutoFilterInfoRecord() {
    }

    public AutoFilterInfoRecord(AutoFilterInfoRecord autoFilterInfoRecord) {
        super(autoFilterInfoRecord);
        this._cEntries = autoFilterInfoRecord._cEntries;
    }

    public AutoFilterInfoRecord(RecordInputStream recordInputStream) {
        this._cEntries = recordInputStream.readShort();
    }

    public void setNumEntries(short s) {
        this._cEntries = s;
    }

    public short getNumEntries() {
        return this._cEntries;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._cEntries);
    }

    public AutoFilterInfoRecord copy() {
        return new AutoFilterInfoRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AUTO_FILTER_INFO;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numEntries", new AutoFilterInfoRecord$$ExternalSyntheticLambda0(this));
    }
}
