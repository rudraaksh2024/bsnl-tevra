package org.apache.poi.ss.usermodel.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public abstract class ColumnShifter extends BaseRowColShifter {
    protected final Sheet sheet;

    public ColumnShifter(Sheet sheet2) {
        this.sheet = sheet2;
    }

    public List<CellRangeAddress> shiftMergedRegions(int i, int i2, int i3) {
        ArrayList<CellRangeAddress> arrayList = new ArrayList<>();
        HashSet hashSet = new HashSet();
        int numMergedRegions = this.sheet.getNumMergedRegions();
        for (int i4 = 0; i4 < numMergedRegions; i4++) {
            CellRangeAddress mergedRegion = this.sheet.getMergedRegion(i4);
            if (removalNeeded(mergedRegion, i, i2, i3)) {
                hashSet.add(Integer.valueOf(i4));
            } else {
                boolean z = true;
                boolean z2 = mergedRegion.getFirstColumn() >= i || mergedRegion.getLastColumn() >= i;
                if (mergedRegion.getFirstColumn() > i2 && mergedRegion.getLastColumn() > i2) {
                    z = false;
                }
                if (z2 && z && !mergedRegion.containsColumn(i - 1) && !mergedRegion.containsColumn(i2 + 1)) {
                    mergedRegion.setFirstColumn(mergedRegion.getFirstColumn() + i3);
                    mergedRegion.setLastColumn(mergedRegion.getLastColumn() + i3);
                    arrayList.add(mergedRegion);
                    hashSet.add(Integer.valueOf(i4));
                }
            }
        }
        if (!hashSet.isEmpty()) {
            this.sheet.removeMergedRegions(hashSet);
        }
        for (CellRangeAddress addMergedRegion : arrayList) {
            this.sheet.addMergedRegion(addMergedRegion);
        }
        return arrayList;
    }

    private boolean removalNeeded(CellRangeAddress cellRangeAddress, int i, int i2, int i3) {
        CellRangeAddress cellRangeAddress2;
        int i4 = (i2 - i) + 1;
        if (i3 > 0) {
            int i5 = i2 + 1;
            int i6 = i2 + i3;
            cellRangeAddress2 = new CellRangeAddress(0, 0, Math.max(i5, i6 - i4), i6);
        } else {
            int i7 = i3 + i;
            cellRangeAddress2 = new CellRangeAddress(0, 0, i7, Math.min(i - 1, i4 + i7));
        }
        return cellRangeAddress.intersects(cellRangeAddress2);
    }

    public void shiftColumns(int i, int i2, int i3) {
        if (i3 > 0) {
            for (Row next : this.sheet) {
                if (next != null) {
                    next.shiftCellsRight(i, i2, i3);
                }
            }
        } else if (i3 < 0) {
            for (Row next2 : this.sheet) {
                if (next2 != null) {
                    next2.shiftCellsLeft(i, i2, -i3);
                }
            }
        }
    }
}
