package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class UncalcedRecord extends StandardRecord {
    public static final short sid = 94;
    private short _reserved;

    public static int getStaticRecordSize() {
        return 6;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 94;
    }

    public UncalcedRecord() {
        this._reserved = 0;
    }

    public UncalcedRecord(UncalcedRecord uncalcedRecord) {
        super(uncalcedRecord);
        this._reserved = uncalcedRecord._reserved;
    }

    public UncalcedRecord(RecordInputStream recordInputStream) {
        this._reserved = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._reserved);
    }

    public UncalcedRecord copy() {
        return new UncalcedRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.UNCALCED;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new UncalcedRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UncalcedRecord  reason: not valid java name */
    public /* synthetic */ Object m2109lambda$getGenericProperties$0$orgapachepoihssfrecordUncalcedRecord() {
        return Short.valueOf(this._reserved);
    }
}
