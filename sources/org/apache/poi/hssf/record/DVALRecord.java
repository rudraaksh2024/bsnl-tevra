package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DVALRecord extends StandardRecord {
    public static final short sid = 434;
    private short field_1_options;
    private int field_2_horiz_pos;
    private int field_3_vert_pos;
    private int field_5_dv_no;
    private int field_cbo_id;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 18;
    }

    public short getSid() {
        return sid;
    }

    public DVALRecord() {
        this.field_cbo_id = -1;
        this.field_5_dv_no = 0;
    }

    public DVALRecord(DVALRecord dVALRecord) {
        super(dVALRecord);
        this.field_1_options = dVALRecord.field_1_options;
        this.field_2_horiz_pos = dVALRecord.field_2_horiz_pos;
        this.field_3_vert_pos = dVALRecord.field_3_vert_pos;
        this.field_cbo_id = dVALRecord.field_cbo_id;
        this.field_5_dv_no = dVALRecord.field_5_dv_no;
    }

    public DVALRecord(RecordInputStream recordInputStream) {
        this.field_1_options = recordInputStream.readShort();
        this.field_2_horiz_pos = recordInputStream.readInt();
        this.field_3_vert_pos = recordInputStream.readInt();
        this.field_cbo_id = recordInputStream.readInt();
        this.field_5_dv_no = recordInputStream.readInt();
    }

    public void setOptions(short s) {
        this.field_1_options = s;
    }

    public void setHorizontalPos(int i) {
        this.field_2_horiz_pos = i;
    }

    public void setVerticalPos(int i) {
        this.field_3_vert_pos = i;
    }

    public void setObjectID(int i) {
        this.field_cbo_id = i;
    }

    public void setDVRecNo(int i) {
        this.field_5_dv_no = i;
    }

    public short getOptions() {
        return this.field_1_options;
    }

    public int getHorizontalPos() {
        return this.field_2_horiz_pos;
    }

    public int getVerticalPos() {
        return this.field_3_vert_pos;
    }

    public int getObjectID() {
        return this.field_cbo_id;
    }

    public int getDVRecNo() {
        return this.field_5_dv_no;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getOptions());
        littleEndianOutput.writeInt(getHorizontalPos());
        littleEndianOutput.writeInt(getVerticalPos());
        littleEndianOutput.writeInt(getObjectID());
        littleEndianOutput.writeInt(getDVRecNo());
    }

    public DVALRecord copy() {
        return new DVALRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DVAL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new DVALRecord$$ExternalSyntheticLambda0(this), "horizPos", new DVALRecord$$ExternalSyntheticLambda1(this), "vertPos", new DVALRecord$$ExternalSyntheticLambda2(this), "comboObjectID", new DVALRecord$$ExternalSyntheticLambda3(this), "dvRecordsNumber", new DVALRecord$$ExternalSyntheticLambda4(this));
    }
}
