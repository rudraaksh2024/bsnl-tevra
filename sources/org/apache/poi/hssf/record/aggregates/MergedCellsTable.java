package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

public final class MergedCellsTable extends RecordAggregate {
    private static final int MAX_MERGED_REGIONS = 1027;
    private final List<CellRangeAddress> _mergedRegions = new ArrayList();

    public void read(RecordStream recordStream) {
        while (recordStream.peekNextClass() == MergeCellsRecord.class) {
            MergeCellsRecord mergeCellsRecord = (MergeCellsRecord) recordStream.getNext();
            short numAreas = mergeCellsRecord.getNumAreas();
            for (int i = 0; i < numAreas; i++) {
                this._mergedRegions.add(mergeCellsRecord.getAreaAt(i));
            }
        }
    }

    public int getRecordSize() {
        int size = this._mergedRegions.size();
        if (size < 1) {
            return 0;
        }
        return ((size / MAX_MERGED_REGIONS) * (CellRangeAddressList.getEncodedSize(MAX_MERGED_REGIONS) + 4)) + 4 + CellRangeAddressList.getEncodedSize(size % MAX_MERGED_REGIONS);
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
        int size = this._mergedRegions.size();
        if (size >= 1) {
            int i = size / MAX_MERGED_REGIONS;
            int i2 = size % MAX_MERGED_REGIONS;
            CellRangeAddress[] cellRangeAddressArr = new CellRangeAddress[size];
            this._mergedRegions.toArray(cellRangeAddressArr);
            for (int i3 = 0; i3 < i; i3++) {
                recordVisitor.visitRecord(new MergeCellsRecord(cellRangeAddressArr, i3 * MAX_MERGED_REGIONS, MAX_MERGED_REGIONS));
            }
            if (i2 > 0) {
                recordVisitor.visitRecord(new MergeCellsRecord(cellRangeAddressArr, i * MAX_MERGED_REGIONS, i2));
            }
        }
    }

    public void addRecords(MergeCellsRecord[] mergeCellsRecordArr) {
        for (MergeCellsRecord addMergeCellsRecord : mergeCellsRecordArr) {
            addMergeCellsRecord(addMergeCellsRecord);
        }
    }

    private void addMergeCellsRecord(MergeCellsRecord mergeCellsRecord) {
        short numAreas = mergeCellsRecord.getNumAreas();
        for (int i = 0; i < numAreas; i++) {
            this._mergedRegions.add(mergeCellsRecord.getAreaAt(i));
        }
    }

    public CellRangeAddress get(int i) {
        checkIndex(i);
        return this._mergedRegions.get(i);
    }

    public void remove(int i) {
        checkIndex(i);
        this._mergedRegions.remove(i);
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= this._mergedRegions.size()) {
            throw new IllegalArgumentException("Specified CF index " + i + " is outside the allowable range (0.." + (this._mergedRegions.size() - 1) + ")");
        }
    }

    public void addArea(int i, int i2, int i3, int i4) {
        this._mergedRegions.add(new CellRangeAddress(i, i3, i2, i4));
    }

    public int getNumberOfMergedRegions() {
        return this._mergedRegions.size();
    }
}
