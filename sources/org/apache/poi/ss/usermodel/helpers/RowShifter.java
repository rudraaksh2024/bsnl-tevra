package org.apache.poi.ss.usermodel.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.LocaleUtil;

public abstract class RowShifter extends BaseRowColShifter {
    protected final Sheet sheet;

    public RowShifter(Sheet sheet2) {
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
                boolean z2 = mergedRegion.getFirstRow() >= i || mergedRegion.getLastRow() >= i;
                if (mergedRegion.getFirstRow() > i2 && mergedRegion.getLastRow() > i2) {
                    z = false;
                }
                if (z2 && z && !mergedRegion.containsRow(i - 1) && !mergedRegion.containsRow(i2 + 1)) {
                    mergedRegion.setFirstRow(mergedRegion.getFirstRow() + i3);
                    mergedRegion.setLastRow(mergedRegion.getLastRow() + i3);
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
            cellRangeAddress2 = new CellRangeAddress(Math.max(i5, i6 - i4), i6, 0, 0);
        } else {
            int i7 = i3 + i;
            cellRangeAddress2 = new CellRangeAddress(i7, Math.min(i - 1, i4 + i7), 0, 0);
        }
        return cellRangeAddress.intersects(cellRangeAddress2);
    }

    public static void validateShiftParameters(int i, int i2, int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Shifting step may not be negative, but had " + i3);
        } else if (i > i2) {
            throw new IllegalArgumentException(String.format(LocaleUtil.getUserLocale(), "Incorrect shifting range : %d-%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public static void validateShiftLeftParameters(int i, int i2, int i3) {
        validateShiftParameters(i, i2, i3);
        if (i - i3 < 0) {
            throw new IllegalStateException("Column index less than zero: " + (i + i3));
        }
    }
}
