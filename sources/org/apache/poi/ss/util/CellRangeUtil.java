package org.apache.poi.ss.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CellRangeUtil {
    public static final int ENCLOSES = 4;
    public static final int INSIDE = 3;
    public static final int NO_INTERSECTION = 1;
    public static final int OVERLAP = 2;

    private static boolean lt(int i, int i2) {
        if (i == -1) {
            return false;
        }
        return i2 == -1 || i < i2;
    }

    private CellRangeUtil() {
    }

    public static int intersect(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        int firstRow = cellRangeAddress2.getFirstRow();
        int lastRow = cellRangeAddress2.getLastRow();
        int firstColumn = cellRangeAddress2.getFirstColumn();
        int lastColumn = cellRangeAddress2.getLastColumn();
        if (gt(cellRangeAddress.getFirstRow(), lastRow) || lt(cellRangeAddress.getLastRow(), firstRow) || gt(cellRangeAddress.getFirstColumn(), lastColumn) || lt(cellRangeAddress.getLastColumn(), firstColumn)) {
            return 1;
        }
        if (contains(cellRangeAddress, cellRangeAddress2)) {
            return 3;
        }
        return contains(cellRangeAddress2, cellRangeAddress) ? 4 : 2;
    }

    public static CellRangeAddress[] mergeCellRanges(CellRangeAddress[] cellRangeAddressArr) {
        if (cellRangeAddressArr.length < 1) {
            return new CellRangeAddress[0];
        }
        return toArray(mergeCellRanges(toList(cellRangeAddressArr)));
    }

    private static List<CellRangeAddress> mergeCellRanges(List<CellRangeAddress> list) {
        int i;
        while (list.size() > 1) {
            int i2 = 0;
            boolean z = false;
            while (i2 < list.size()) {
                CellRangeAddress cellRangeAddress = list.get(i2);
                int i3 = i2 + 1;
                int i4 = i3;
                while (i < list.size()) {
                    CellRangeAddress[] mergeRanges = mergeRanges(cellRangeAddress, list.get(i));
                    if (mergeRanges != null) {
                        CellRangeAddress cellRangeAddress2 = mergeRanges[0];
                        list.set(i2, cellRangeAddress2);
                        list.remove(i);
                        i--;
                        for (int i5 = 1; i5 < mergeRanges.length; i5++) {
                            i++;
                            list.add(i, mergeRanges[i5]);
                        }
                        cellRangeAddress = cellRangeAddress2;
                        z = true;
                    }
                    i4 = i + 1;
                }
                i2 = i3;
            }
            if (!z) {
                break;
            }
        }
        return list;
    }

    private static CellRangeAddress[] mergeRanges(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        int intersect = intersect(cellRangeAddress, cellRangeAddress2);
        if (intersect != 1) {
            if (intersect == 2) {
                return null;
            }
            if (intersect == 3) {
                return new CellRangeAddress[]{cellRangeAddress};
            } else if (intersect == 4) {
                return new CellRangeAddress[]{cellRangeAddress2};
            } else {
                throw new RuntimeException("unexpected intersection result (" + intersect + ")");
            }
        } else if (!hasExactSharedBorder(cellRangeAddress, cellRangeAddress2)) {
            return null;
        } else {
            return new CellRangeAddress[]{createEnclosingCellRange(cellRangeAddress, cellRangeAddress2)};
        }
    }

    private static CellRangeAddress[] toArray(List<CellRangeAddress> list) {
        CellRangeAddress[] cellRangeAddressArr = new CellRangeAddress[list.size()];
        list.toArray(cellRangeAddressArr);
        return cellRangeAddressArr;
    }

    private static List<CellRangeAddress> toList(CellRangeAddress[] cellRangeAddressArr) {
        ArrayList arrayList = new ArrayList(cellRangeAddressArr.length);
        Collections.addAll(arrayList, cellRangeAddressArr);
        return arrayList;
    }

    public static boolean contains(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        return le(cellRangeAddress.getFirstRow(), cellRangeAddress2.getFirstRow()) && ge(cellRangeAddress.getLastRow(), cellRangeAddress2.getLastRow()) && le(cellRangeAddress.getFirstColumn(), cellRangeAddress2.getFirstColumn()) && ge(cellRangeAddress.getLastColumn(), cellRangeAddress2.getLastColumn());
    }

    public static boolean hasExactSharedBorder(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        int firstRow = cellRangeAddress2.getFirstRow();
        int lastRow = cellRangeAddress2.getLastRow();
        int firstColumn = cellRangeAddress2.getFirstColumn();
        int lastColumn = cellRangeAddress2.getLastColumn();
        if ((cellRangeAddress.getFirstRow() <= 0 || cellRangeAddress.getFirstRow() - 1 != lastRow) && (firstRow <= 0 || firstRow - 1 != cellRangeAddress.getLastRow())) {
            if (((cellRangeAddress.getFirstColumn() > 0 && cellRangeAddress.getFirstColumn() - 1 == lastColumn) || (firstColumn > 0 && cellRangeAddress.getLastColumn() == firstColumn - 1)) && cellRangeAddress.getFirstRow() == firstRow && cellRangeAddress.getLastRow() == lastRow) {
                return true;
            }
            return false;
        } else if (cellRangeAddress.getFirstColumn() == firstColumn && cellRangeAddress.getLastColumn() == lastColumn) {
            return true;
        } else {
            return false;
        }
    }

    public static CellRangeAddress createEnclosingCellRange(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        if (cellRangeAddress2 == null) {
            return cellRangeAddress.copy();
        }
        return new CellRangeAddress(lt(cellRangeAddress2.getFirstRow(), cellRangeAddress.getFirstRow()) ? cellRangeAddress2.getFirstRow() : cellRangeAddress.getFirstRow(), gt(cellRangeAddress2.getLastRow(), cellRangeAddress.getLastRow()) ? cellRangeAddress2.getLastRow() : cellRangeAddress.getLastRow(), lt(cellRangeAddress2.getFirstColumn(), cellRangeAddress.getFirstColumn()) ? cellRangeAddress2.getFirstColumn() : cellRangeAddress.getFirstColumn(), gt(cellRangeAddress2.getLastColumn(), cellRangeAddress.getLastColumn()) ? cellRangeAddress2.getLastColumn() : cellRangeAddress.getLastColumn());
    }

    private static boolean le(int i, int i2) {
        return i == i2 || lt(i, i2);
    }

    private static boolean gt(int i, int i2) {
        return lt(i2, i);
    }

    private static boolean ge(int i, int i2) {
        return !lt(i, i2);
    }
}
