package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class HideObjRecord extends StandardRecord {
    public static final short HIDE_ALL = 2;
    public static final short SHOW_ALL = 0;
    public static final short SHOW_PLACEHOLDERS = 1;
    public static final short sid = 141;
    private short field_1_hide_obj;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 141;
    }

    public HideObjRecord() {
    }

    public HideObjRecord(HideObjRecord hideObjRecord) {
        super(hideObjRecord);
        this.field_1_hide_obj = hideObjRecord.field_1_hide_obj;
    }

    public HideObjRecord(RecordInputStream recordInputStream) {
        this.field_1_hide_obj = recordInputStream.readShort();
    }

    public void setHideObj(short s) {
        this.field_1_hide_obj = s;
    }

    public short getHideObj() {
        return this.field_1_hide_obj;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getHideObj());
    }

    public HideObjRecord copy() {
        return new HideObjRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HIDE_OBJ;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("hideObj", new HideObjRecord$$ExternalSyntheticLambda0(this));
    }
}
