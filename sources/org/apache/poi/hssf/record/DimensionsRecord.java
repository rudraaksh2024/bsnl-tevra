package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DimensionsRecord extends StandardRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) DimensionsRecord.class);
    public static final short sid = 512;
    private int field_1_first_row;
    private int field_2_last_row;
    private short field_3_first_col;
    private short field_4_last_col;
    private short field_5_zero;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 14;
    }

    public short getSid() {
        return sid;
    }

    public DimensionsRecord() {
    }

    public DimensionsRecord(DimensionsRecord dimensionsRecord) {
        super(dimensionsRecord);
        this.field_1_first_row = dimensionsRecord.field_1_first_row;
        this.field_2_last_row = dimensionsRecord.field_2_last_row;
        this.field_3_first_col = dimensionsRecord.field_3_first_col;
        this.field_4_last_col = dimensionsRecord.field_4_last_col;
        this.field_5_zero = dimensionsRecord.field_5_zero;
    }

    public DimensionsRecord(RecordInputStream recordInputStream) {
        this.field_1_first_row = recordInputStream.readInt();
        this.field_2_last_row = recordInputStream.readInt();
        this.field_3_first_col = recordInputStream.readShort();
        this.field_4_last_col = recordInputStream.readShort();
        this.field_5_zero = recordInputStream.readShort();
        if (recordInputStream.available() == 2) {
            LOG.atInfo().log("DimensionsRecord has extra 2 bytes.");
            recordInputStream.readShort();
        }
    }

    public void setFirstRow(int i) {
        this.field_1_first_row = i;
    }

    public void setLastRow(int i) {
        this.field_2_last_row = i;
    }

    public void setFirstCol(short s) {
        this.field_3_first_col = s;
    }

    public void setLastCol(short s) {
        this.field_4_last_col = s;
    }

    public int getFirstRow() {
        return this.field_1_first_row;
    }

    public int getLastRow() {
        return this.field_2_last_row;
    }

    public short getFirstCol() {
        return this.field_3_first_col;
    }

    public short getLastCol() {
        return this.field_4_last_col;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(getFirstRow());
        littleEndianOutput.writeInt(getLastRow());
        littleEndianOutput.writeShort(getFirstCol());
        littleEndianOutput.writeShort(getLastCol());
        littleEndianOutput.writeShort(0);
    }

    public DimensionsRecord copy() {
        return new DimensionsRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DIMENSIONS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstRow", new DimensionsRecord$$ExternalSyntheticLambda0(this), "lastRow", new DimensionsRecord$$ExternalSyntheticLambda1(this), "firstColumn", new DimensionsRecord$$ExternalSyntheticLambda2(this), "lastColumn", new DimensionsRecord$$ExternalSyntheticLambda3(this), "zero", new DimensionsRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DimensionsRecord  reason: not valid java name */
    public /* synthetic */ Object m1994lambda$getGenericProperties$0$orgapachepoihssfrecordDimensionsRecord() {
        return Short.valueOf(this.field_5_zero);
    }
}
