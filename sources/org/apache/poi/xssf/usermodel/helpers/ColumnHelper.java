package org.apache.poi.xssf.usermodel.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.util.CTColComparator;
import org.apache.poi.xssf.util.NumericRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class ColumnHelper {
    private CTWorksheet worksheet;

    public ColumnHelper(CTWorksheet cTWorksheet) {
        this.worksheet = cTWorksheet;
        cleanColumns();
    }

    public void cleanColumns() {
        TreeSet treeSet = new TreeSet(CTColComparator.BY_MIN_MAX);
        CTCols newInstance = CTCols.Factory.newInstance();
        CTCols[] colsArray = this.worksheet.getColsArray();
        int i = 0;
        while (i < colsArray.length) {
            for (CTCol addCleanColIntoCols : colsArray[i].getColList()) {
                addCleanColIntoCols(newInstance, addCleanColIntoCols, treeSet);
            }
            i++;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            this.worksheet.removeCols(i2);
        }
        newInstance.setColArray((CTCol[]) treeSet.toArray(new CTCol[0]));
        this.worksheet.addNewCols();
        this.worksheet.setColsArray(0, newInstance);
    }

    public CTCols addCleanColIntoCols(CTCols cTCols, CTCol cTCol) {
        TreeSet treeSet = new TreeSet(CTColComparator.BY_MIN_MAX);
        treeSet.addAll(cTCols.getColList());
        addCleanColIntoCols(cTCols, cTCol, treeSet);
        cTCols.setColArray((CTCol[]) treeSet.toArray(new CTCol[0]));
        return cTCols;
    }

    private void addCleanColIntoCols(CTCols cTCols, CTCol cTCol, TreeSet<CTCol> treeSet) {
        CTCols cTCols2 = cTCols;
        CTCol cTCol2 = cTCol;
        TreeSet<CTCol> treeSet2 = treeSet;
        List<CTCol> overlappingCols = getOverlappingCols(cTCol2, treeSet2);
        if (overlappingCols.isEmpty()) {
            treeSet2.add(cloneCol(cTCols, cTCol));
            return;
        }
        treeSet2.removeAll(overlappingCols);
        for (CTCol next : overlappingCols) {
            long[] overlap = getOverlap(cTCol2, next);
            CTCol cloneCol = cloneCol(cTCols2, next, overlap);
            setColumnAttributes(cTCol2, cloneCol);
            treeSet2.add(cloneCol);
            CTCol cTCol3 = next.getMin() < cTCol.getMin() ? next : cTCol2;
            long min = Math.min(next.getMin(), cTCol.getMin());
            long j = overlap[0] - 1;
            long[] jArr = {min, j};
            if (min <= j) {
                treeSet2.add(cloneCol(cTCols2, cTCol3, jArr));
            }
            CTCol cTCol4 = next.getMax() > cTCol.getMax() ? next : cTCol2;
            long max = Math.max(next.getMax(), cTCol.getMax());
            long[] jArr2 = {overlap[1] + 1, max};
            if (jArr2[0] <= max) {
                treeSet2.add(cloneCol(cTCols2, cTCol4, jArr2));
            }
        }
    }

    private CTCol cloneCol(CTCols cTCols, CTCol cTCol, long[] jArr) {
        CTCol cloneCol = cloneCol(cTCols, cTCol);
        cloneCol.setMin(jArr[0]);
        cloneCol.setMax(jArr[1]);
        return cloneCol;
    }

    private long[] getOverlap(CTCol cTCol, CTCol cTCol2) {
        return getOverlappingRange(cTCol, cTCol2);
    }

    private List<CTCol> getOverlappingCols(CTCol cTCol, TreeSet<CTCol> treeSet) {
        CTCol lower = treeSet.lower(cTCol);
        NavigableSet<CTCol> navigableSet = treeSet;
        if (lower != null) {
            navigableSet = treeSet.tailSet(lower, overlaps(lower, cTCol));
        }
        ArrayList arrayList = new ArrayList();
        for (CTCol next : navigableSet) {
            if (!overlaps(cTCol, next)) {
                break;
            }
            arrayList.add(next);
        }
        return arrayList;
    }

    private boolean overlaps(CTCol cTCol, CTCol cTCol2) {
        return NumericRanges.getOverlappingType(toRange(cTCol), toRange(cTCol2)) != -1;
    }

    private long[] getOverlappingRange(CTCol cTCol, CTCol cTCol2) {
        return NumericRanges.getOverlappingRange(toRange(cTCol), toRange(cTCol2));
    }

    private long[] toRange(CTCol cTCol) {
        return new long[]{cTCol.getMin(), cTCol.getMax()};
    }

    public static void sortColumns(CTCols cTCols) {
        CTCol[] colArray = cTCols.getColArray();
        Arrays.sort(colArray, CTColComparator.BY_MIN_MAX);
        cTCols.setColArray(colArray);
    }

    public CTCol cloneCol(CTCols cTCols, CTCol cTCol) {
        CTCol addNewCol = cTCols.addNewCol();
        addNewCol.setMin(cTCol.getMin());
        addNewCol.setMax(cTCol.getMax());
        setColumnAttributes(cTCol, addNewCol);
        return addNewCol;
    }

    public CTCol getColumn(long j, boolean z) {
        return getColumn1Based(j + 1, z);
    }

    public CTCol getColumn1Based(long j, boolean z) {
        int i;
        int i2;
        long j2 = j;
        char c = 0;
        CTCols colsArray = this.worksheet.getColsArray(0);
        CTCol[] colArray = colsArray.getColArray();
        int length = colArray.length;
        int i3 = 0;
        while (i3 < length) {
            CTCol cTCol = colArray[i3];
            long min = cTCol.getMin();
            long max = cTCol.getMax();
            int i4 = (min > j2 ? 1 : (min == j2 ? 0 : -1));
            if (i4 > 0 || max < j2) {
                i3++;
                c = c;
            } else {
                if (z) {
                    if (i4 < 0) {
                        CTCol[] cTColArr = new CTCol[1];
                        cTColArr[c] = cTCol;
                        i2 = 1;
                        insertCol(colsArray, min, j2 - 1, cTColArr);
                    } else {
                        i2 = 1;
                    }
                    if (i > 0) {
                        CTCol[] cTColArr2 = new CTCol[i2];
                        cTColArr2[0] = cTCol;
                        insertCol(colsArray, j2 + 1, max, cTColArr2);
                    }
                    cTCol.setMin(j2);
                    cTCol.setMax(j2);
                }
                return cTCol;
            }
        }
        return null;
    }

    private CTCol insertCol(CTCols cTCols, long j, long j2, CTCol[] cTColArr) {
        return insertCol(cTCols, j, j2, cTColArr, false, (CTCol) null);
    }

    private CTCol insertCol(CTCols cTCols, long j, long j2, CTCol[] cTColArr, boolean z, CTCol cTCol) {
        if (!z && columnExists(cTCols, j, j2)) {
            return null;
        }
        CTCol insertNewCol = cTCols.insertNewCol(0);
        insertNewCol.setMin(j);
        insertNewCol.setMax(j2);
        for (CTCol columnAttributes : cTColArr) {
            setColumnAttributes(columnAttributes, insertNewCol);
        }
        if (cTCol != null) {
            setColumnAttributes(cTCol, insertNewCol);
        }
        return insertNewCol;
    }

    public boolean columnExists(CTCols cTCols, long j) {
        return columnExists1Based(cTCols, j + 1);
    }

    private boolean columnExists1Based(CTCols cTCols, long j) {
        for (CTCol min : cTCols.getColArray()) {
            if (min.getMin() == j) {
                return true;
            }
        }
        return false;
    }

    public void setColumnAttributes(CTCol cTCol, CTCol cTCol2) {
        if (cTCol.isSetBestFit()) {
            cTCol2.setBestFit(cTCol.getBestFit());
        }
        if (cTCol.isSetCustomWidth()) {
            cTCol2.setCustomWidth(cTCol.getCustomWidth());
        }
        if (cTCol.isSetHidden()) {
            cTCol2.setHidden(cTCol.getHidden());
        }
        if (cTCol.isSetStyle()) {
            cTCol2.setStyle(cTCol.getStyle());
        }
        if (cTCol.isSetWidth()) {
            cTCol2.setWidth(cTCol.getWidth());
        }
        if (cTCol.isSetCollapsed()) {
            cTCol2.setCollapsed(cTCol.getCollapsed());
        }
        if (cTCol.isSetPhonetic()) {
            cTCol2.setPhonetic(cTCol.getPhonetic());
        }
        if (cTCol.isSetOutlineLevel()) {
            cTCol2.setOutlineLevel(cTCol.getOutlineLevel());
        }
    }

    public void setColBestFit(long j, boolean z) {
        getOrCreateColumn1Based(j + 1, false).setBestFit(z);
    }

    public void setCustomWidth(long j, boolean z) {
        getOrCreateColumn1Based(j + 1, true).setCustomWidth(z);
    }

    public void setColWidth(long j, double d) {
        getOrCreateColumn1Based(j + 1, true).setWidth(d);
    }

    public void setColHidden(long j, boolean z) {
        getOrCreateColumn1Based(j + 1, true).setHidden(z);
    }

    /* access modifiers changed from: protected */
    public CTCol getOrCreateColumn1Based(long j, boolean z) {
        CTCol column1Based = getColumn1Based(j, z);
        if (column1Based != null) {
            return column1Based;
        }
        CTCol addNewCol = this.worksheet.getColsArray(0).addNewCol();
        addNewCol.setMin(j);
        addNewCol.setMax(j);
        return addNewCol;
    }

    public void setColDefaultStyle(long j, CellStyle cellStyle) {
        setColDefaultStyle(j, (int) cellStyle.getIndex());
    }

    public void setColDefaultStyle(long j, int i) {
        getOrCreateColumn1Based(j + 1, true).setStyle((long) i);
    }

    public int getColDefaultStyle(long j) {
        if (getColumn(j, false) != null) {
            return (int) getColumn(j, false).getStyle();
        }
        return -1;
    }

    private boolean columnExists(CTCols cTCols, long j, long j2) {
        for (CTCol next : cTCols.getColList()) {
            if (next.getMin() == j && next.getMax() == j2) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOfColumn(CTCols cTCols, CTCol cTCol) {
        if (!(cTCols == null || cTCol == null)) {
            int i = 0;
            for (CTCol next : cTCols.getColList()) {
                if (next.getMin() == cTCol.getMin() && next.getMax() == cTCol.getMax()) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }
}
