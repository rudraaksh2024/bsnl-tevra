package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class TabIdRecord extends StandardRecord {
    private static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final short sid = 317;
    private short[] _tabids;

    public short getSid() {
        return sid;
    }

    public TabIdRecord() {
        this._tabids = EMPTY_SHORT_ARRAY;
    }

    public TabIdRecord(TabIdRecord tabIdRecord) {
        super(tabIdRecord);
        short[] sArr = tabIdRecord._tabids;
        this._tabids = sArr == null ? null : (short[]) sArr.clone();
    }

    public TabIdRecord(RecordInputStream recordInputStream) {
        this._tabids = new short[(recordInputStream.remaining() / 2)];
        int i = 0;
        while (true) {
            short[] sArr = this._tabids;
            if (i < sArr.length) {
                sArr[i] = recordInputStream.readShort();
                i++;
            } else {
                return;
            }
        }
    }

    public void setTabIdArray(short[] sArr) {
        this._tabids = (short[]) sArr.clone();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        for (short writeShort : this._tabids) {
            littleEndianOutput.writeShort(writeShort);
        }
    }

    public int getTabIdSize() {
        return this._tabids.length;
    }

    public short getTabIdAt(int i) {
        return this._tabids[i];
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this._tabids.length * 2;
    }

    public TabIdRecord copy() {
        return new TabIdRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TAB_ID;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("elements", new TabIdRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TabIdRecord  reason: not valid java name */
    public /* synthetic */ Object m2095lambda$getGenericProperties$0$orgapachepoihssfrecordTabIdRecord() {
        return this._tabids;
    }
}
