package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.RKUtil;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class RKRecord extends CellRecord {
    public static final short RK_IEEE_NUMBER = 0;
    public static final short RK_IEEE_NUMBER_TIMES_100 = 1;
    public static final short RK_INTEGER = 2;
    public static final short RK_INTEGER_TIMES_100 = 3;
    public static final short sid = 638;
    private final int field_4_rk_number;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "RK";
    }

    public short getSid() {
        return sid;
    }

    /* access modifiers changed from: protected */
    public int getValueDataSize() {
        return 4;
    }

    public RKRecord(RKRecord rKRecord) {
        super((CellRecord) rKRecord);
        this.field_4_rk_number = rKRecord.field_4_rk_number;
    }

    public RKRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
        this.field_4_rk_number = recordInputStream.readInt();
    }

    public double getRKNumber() {
        return RKUtil.decodeNumber(this.field_4_rk_number);
    }

    /* access modifiers changed from: protected */
    public void serializeValue(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.field_4_rk_number);
    }

    public RKRecord copy() {
        return new RKRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.RK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new RKRecord$$ExternalSyntheticLambda0(this), "rkNumber", new RKRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RKRecord  reason: not valid java name */
    public /* synthetic */ Object m2080lambda$getGenericProperties$0$orgapachepoihssfrecordRKRecord() {
        return super.getGenericProperties();
    }
}
