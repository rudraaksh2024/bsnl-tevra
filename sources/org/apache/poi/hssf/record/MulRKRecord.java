package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.util.RKUtil;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class MulRKRecord extends StandardRecord {
    public static final short sid = 189;
    private final int field_1_row;
    private final short field_2_first_col;
    private final RkRec[] field_3_rks;
    private final short field_4_last_col;

    public MulRKRecord copy() {
        return this;
    }

    public short getSid() {
        return 189;
    }

    public int getRow() {
        return this.field_1_row;
    }

    public short getFirstColumn() {
        return this.field_2_first_col;
    }

    public short getLastColumn() {
        return this.field_4_last_col;
    }

    public int getNumColumns() {
        return (this.field_4_last_col - this.field_2_first_col) + 1;
    }

    public short getXFAt(int i) {
        return this.field_3_rks[i].xf;
    }

    public double getRKNumberAt(int i) {
        return RKUtil.decodeNumber(this.field_3_rks[i].rk);
    }

    public MulRKRecord(RecordInputStream recordInputStream) {
        this.field_1_row = recordInputStream.readUShort();
        this.field_2_first_col = recordInputStream.readShort();
        this.field_3_rks = RkRec.parseRKs(recordInputStream);
        this.field_4_last_col = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        throw new RecordFormatException("Sorry, you can't serialize MulRK in this release");
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        throw new RecordFormatException("Sorry, you can't serialize MulRK in this release");
    }

    private static final class RkRec implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        public final int rk;
        public final short xf;

        private RkRec(RecordInputStream recordInputStream) {
            this.xf = recordInputStream.readShort();
            this.rk = recordInputStream.readInt();
        }

        public static RkRec[] parseRKs(RecordInputStream recordInputStream) {
            int remaining = (recordInputStream.remaining() - 2) / 6;
            RkRec[] rkRecArr = new RkRec[remaining];
            for (int i = 0; i < remaining; i++) {
                rkRecArr[i] = new RkRec(recordInputStream);
            }
            return rkRecArr;
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("xf", new MulRKRecord$RkRec$$ExternalSyntheticLambda0(this), "rk", new MulRKRecord$RkRec$$ExternalSyntheticLambda1(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-MulRKRecord$RkRec  reason: not valid java name */
        public /* synthetic */ Object m2053lambda$getGenericProperties$0$orgapachepoihssfrecordMulRKRecord$RkRec() {
            return Short.valueOf(this.xf);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-MulRKRecord$RkRec  reason: not valid java name */
        public /* synthetic */ Object m2054lambda$getGenericProperties$1$orgapachepoihssfrecordMulRKRecord$RkRec() {
            return Integer.valueOf(this.rk);
        }
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MUL_RK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new MulRKRecord$$ExternalSyntheticLambda0(this), "firstColumn", new MulRKRecord$$ExternalSyntheticLambda1(this), "lastColumn", new MulRKRecord$$ExternalSyntheticLambda2(this), "rk", new MulRKRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-MulRKRecord  reason: not valid java name */
    public /* synthetic */ Object m2052lambda$getGenericProperties$0$orgapachepoihssfrecordMulRKRecord() {
        return this.field_3_rks;
    }
}
