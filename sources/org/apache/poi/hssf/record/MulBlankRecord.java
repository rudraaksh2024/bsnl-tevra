package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class MulBlankRecord extends StandardRecord {
    public static final short sid = 190;
    private final int _firstCol;
    private final int _lastCol;
    private final int _row;
    private final short[] _xfs;

    public MulBlankRecord copy() {
        return this;
    }

    public short getSid() {
        return 190;
    }

    public MulBlankRecord(int i, int i2, short[] sArr) {
        this._row = i;
        this._firstCol = i2;
        this._xfs = sArr;
        this._lastCol = (i2 + sArr.length) - 1;
    }

    public int getRow() {
        return this._row;
    }

    public int getFirstColumn() {
        return this._firstCol;
    }

    public int getLastColumn() {
        return this._lastCol;
    }

    public int getNumColumns() {
        return (this._lastCol - this._firstCol) + 1;
    }

    public short getXFAt(int i) {
        return this._xfs[i];
    }

    public MulBlankRecord(RecordInputStream recordInputStream) {
        this._row = recordInputStream.readUShort();
        this._firstCol = recordInputStream.readShort();
        this._xfs = parseXFs(recordInputStream);
        this._lastCol = recordInputStream.readShort();
    }

    private static short[] parseXFs(RecordInputStream recordInputStream) {
        int remaining = (recordInputStream.remaining() - 2) / 2;
        short[] sArr = new short[remaining];
        for (int i = 0; i < remaining; i++) {
            sArr[i] = recordInputStream.readShort();
        }
        return sArr;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._row);
        littleEndianOutput.writeShort(this._firstCol);
        for (short writeShort : this._xfs) {
            littleEndianOutput.writeShort(writeShort);
        }
        littleEndianOutput.writeShort(this._lastCol);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this._xfs.length * 2) + 6;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MUL_BLANK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new MulBlankRecord$$ExternalSyntheticLambda0(this), "firstColumn", new MulBlankRecord$$ExternalSyntheticLambda1(this), "lastColumn", new MulBlankRecord$$ExternalSyntheticLambda2(this), "xf", new MulBlankRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-MulBlankRecord  reason: not valid java name */
    public /* synthetic */ Object m2051lambda$getGenericProperties$0$orgapachepoihssfrecordMulBlankRecord() {
        return this._xfs;
    }
}
