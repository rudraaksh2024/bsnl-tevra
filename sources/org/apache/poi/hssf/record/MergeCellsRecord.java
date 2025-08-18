package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class MergeCellsRecord extends StandardRecord {
    public static final short sid = 229;
    private final int _numberOfRegions;
    private final CellRangeAddress[] _regions;
    private final int _startIndex;

    public short getSid() {
        return sid;
    }

    public MergeCellsRecord(MergeCellsRecord mergeCellsRecord) {
        super(mergeCellsRecord);
        CellRangeAddress[] cellRangeAddressArr;
        CellRangeAddress[] cellRangeAddressArr2 = mergeCellsRecord._regions;
        if (cellRangeAddressArr2 == null) {
            cellRangeAddressArr = null;
        } else {
            cellRangeAddressArr = (CellRangeAddress[]) Stream.of(cellRangeAddressArr2).map(new FeatRecord$$ExternalSyntheticLambda0()).toArray(new MergeCellsRecord$$ExternalSyntheticLambda2());
        }
        this._regions = cellRangeAddressArr;
        this._startIndex = mergeCellsRecord._startIndex;
        this._numberOfRegions = mergeCellsRecord._numberOfRegions;
    }

    static /* synthetic */ CellRangeAddress[] lambda$new$0(int i) {
        return new CellRangeAddress[i];
    }

    public MergeCellsRecord(CellRangeAddress[] cellRangeAddressArr, int i, int i2) {
        this._regions = cellRangeAddressArr;
        this._startIndex = i;
        this._numberOfRegions = i2;
    }

    public MergeCellsRecord(RecordInputStream recordInputStream) {
        int readUShort = recordInputStream.readUShort();
        CellRangeAddress[] cellRangeAddressArr = new CellRangeAddress[readUShort];
        for (int i = 0; i < readUShort; i++) {
            cellRangeAddressArr[i] = new CellRangeAddress(recordInputStream);
        }
        this._numberOfRegions = readUShort;
        this._startIndex = 0;
        this._regions = cellRangeAddressArr;
    }

    public short getNumAreas() {
        return (short) this._numberOfRegions;
    }

    public CellRangeAddress getAreaAt(int i) {
        return this._regions[this._startIndex + i];
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return CellRangeAddressList.getEncodedSize(this._numberOfRegions);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._numberOfRegions);
        for (int i = 0; i < this._numberOfRegions; i++) {
            this._regions[this._startIndex + i].serialize(littleEndianOutput);
        }
    }

    public MergeCellsRecord copy() {
        return new MergeCellsRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MERGE_CELLS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numRegions", new MergeCellsRecord$$ExternalSyntheticLambda0(this), "regions", new MergeCellsRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-MergeCellsRecord  reason: not valid java name */
    public /* synthetic */ Object m2050lambda$getGenericProperties$1$orgapachepoihssfrecordMergeCellsRecord() {
        CellRangeAddress[] cellRangeAddressArr = this._regions;
        int i = this._startIndex;
        return (CellRangeAddress[]) Arrays.copyOfRange(cellRangeAddressArr, i, this._numberOfRegions + i);
    }
}
