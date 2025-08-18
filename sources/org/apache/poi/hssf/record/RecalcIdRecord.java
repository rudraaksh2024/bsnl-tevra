package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class RecalcIdRecord extends StandardRecord {
    public static final short sid = 449;
    private int _engineId;
    private final int _reserved0;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return sid;
    }

    public boolean isNeeded() {
        return true;
    }

    public RecalcIdRecord() {
        this._reserved0 = 0;
        this._engineId = 0;
    }

    public RecalcIdRecord(RecalcIdRecord recalcIdRecord) {
        this._reserved0 = recalcIdRecord._reserved0;
        this._engineId = recalcIdRecord._engineId;
    }

    public RecalcIdRecord(RecordInputStream recordInputStream) {
        recordInputStream.readUShort();
        this._reserved0 = recordInputStream.readUShort();
        this._engineId = recordInputStream.readInt();
    }

    public void setEngineId(int i) {
        this._engineId = i;
    }

    public int getEngineId() {
        return this._engineId;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(449);
        littleEndianOutput.writeShort(this._reserved0);
        littleEndianOutput.writeInt(this._engineId);
    }

    public RecalcIdRecord copy() {
        return new RecalcIdRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.RECALC_ID;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved0", new RecalcIdRecord$$ExternalSyntheticLambda0(this), "engineId", new RecalcIdRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RecalcIdRecord  reason: not valid java name */
    public /* synthetic */ Object m2081lambda$getGenericProperties$0$orgapachepoihssfrecordRecalcIdRecord() {
        return Integer.valueOf(this._reserved0);
    }
}
