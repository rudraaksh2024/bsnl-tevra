package org.apache.poi.ss.util;

import java.util.ArrayList;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.ss.SpreadsheetVersion;

public class AreaReference {
    private static final char CELL_DELIMITER = ':';
    private static final SpreadsheetVersion DEFAULT_SPREADSHEET_VERSION = SpreadsheetVersion.EXCEL97;
    private static final char SHEET_NAME_DELIMITER = '!';
    private static final char SPECIAL_NAME_DELIMITER = '\'';
    private final CellReference _firstCell;
    private final boolean _isSingleCell;
    private final CellReference _lastCell;
    private final SpreadsheetVersion _version;

    public AreaReference(String str, SpreadsheetVersion spreadsheetVersion) {
        this._version = spreadsheetVersion == null ? DEFAULT_SPREADSHEET_VERSION : spreadsheetVersion;
        if (isContiguous(str)) {
            String[] separateAreaRefs = separateAreaRefs(str);
            String str2 = separateAreaRefs[0];
            if (separateAreaRefs.length == 1) {
                CellReference cellReference = new CellReference(str2);
                this._firstCell = cellReference;
                this._lastCell = cellReference;
                this._isSingleCell = true;
            } else if (separateAreaRefs.length == 2) {
                String str3 = separateAreaRefs[1];
                if (!isPlainColumn(str2)) {
                    this._firstCell = new CellReference(str2);
                    this._lastCell = new CellReference(str3);
                    this._isSingleCell = str2.equals(str3);
                } else if (isPlainColumn(str3)) {
                    boolean isPartAbsolute = CellReference.isPartAbsolute(str2);
                    boolean isPartAbsolute2 = CellReference.isPartAbsolute(str3);
                    int convertColStringToIndex = CellReference.convertColStringToIndex(str2);
                    int convertColStringToIndex2 = CellReference.convertColStringToIndex(str3);
                    this._firstCell = new CellReference(0, convertColStringToIndex, true, isPartAbsolute);
                    this._lastCell = new CellReference(65535, convertColStringToIndex2, true, isPartAbsolute2);
                    this._isSingleCell = false;
                } else {
                    throw new RuntimeException("Bad area ref '" + str + "'");
                }
            } else {
                throw new IllegalArgumentException("Bad area ref '" + str + "'");
            }
        } else {
            throw new IllegalArgumentException("References passed to the AreaReference must be contiguous, use generateContiguous(ref) if you have non-contiguous references");
        }
    }

    public AreaReference(CellReference cellReference, CellReference cellReference2, SpreadsheetVersion spreadsheetVersion) {
        SpreadsheetVersion spreadsheetVersion2;
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        String str;
        short s;
        String str2;
        short s2;
        boolean z4;
        if (spreadsheetVersion != null) {
            spreadsheetVersion2 = spreadsheetVersion;
        } else {
            spreadsheetVersion2 = DEFAULT_SPREADSHEET_VERSION;
        }
        this._version = spreadsheetVersion2;
        boolean z5 = true;
        boolean z6 = cellReference.getRow() > cellReference2.getRow();
        z5 = cellReference.getCol() <= cellReference2.getCol() ? false : z5;
        if (z6 || z5) {
            CellReference cellReference3 = cellReference;
            CellReference cellReference4 = cellReference2;
            if (z6) {
                i2 = cellReference2.getRow();
                z2 = cellReference2.isRowAbsolute();
                i = cellReference.getRow();
                z = cellReference.isRowAbsolute();
            } else {
                i2 = cellReference.getRow();
                z2 = cellReference.isRowAbsolute();
                i = cellReference2.getRow();
                z = cellReference2.isRowAbsolute();
            }
            int i3 = i2;
            boolean z7 = z2;
            boolean z8 = z;
            if (z5) {
                String sheetName = cellReference2.getSheetName();
                s2 = cellReference2.getCol();
                boolean isColAbsolute = cellReference2.isColAbsolute();
                str2 = cellReference.getSheetName();
                s = cellReference.getCol();
                z4 = cellReference.isColAbsolute();
                str = sheetName;
                z3 = isColAbsolute;
            } else {
                String sheetName2 = cellReference.getSheetName();
                s2 = cellReference.getCol();
                boolean isColAbsolute2 = cellReference.isColAbsolute();
                str2 = cellReference2.getSheetName();
                s = cellReference2.getCol();
                z3 = isColAbsolute2;
                str = sheetName2;
                z4 = cellReference2.isColAbsolute();
            }
            this._firstCell = new CellReference(str, i3, s2, z7, z3);
            this._lastCell = new CellReference(str2, i, s, z8, z4);
        } else {
            this._firstCell = cellReference;
            this._lastCell = cellReference2;
        }
        this._isSingleCell = false;
    }

    private static boolean isPlainColumn(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if ((charAt != '$' || length != 0) && (charAt < 'A' || charAt > 'Z')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isContiguous(String str) {
        return splitAreaReferences(str).length == 1;
    }

    public static AreaReference getWholeRow(SpreadsheetVersion spreadsheetVersion, String str, String str2) {
        if (spreadsheetVersion == null) {
            spreadsheetVersion = DEFAULT_SPREADSHEET_VERSION;
        }
        return new AreaReference("$A" + str + ":$" + spreadsheetVersion.getLastColumnName() + str2, spreadsheetVersion);
    }

    public static AreaReference getWholeColumn(SpreadsheetVersion spreadsheetVersion, String str, String str2) {
        if (spreadsheetVersion == null) {
            spreadsheetVersion = DEFAULT_SPREADSHEET_VERSION;
        }
        return new AreaReference(str + "$1:" + str2 + "$" + spreadsheetVersion.getMaxRows(), spreadsheetVersion);
    }

    public static boolean isWholeColumnReference(SpreadsheetVersion spreadsheetVersion, CellReference cellReference, CellReference cellReference2) {
        if (spreadsheetVersion == null) {
            spreadsheetVersion = DEFAULT_SPREADSHEET_VERSION;
        }
        return cellReference.getRow() == 0 && cellReference.isRowAbsolute() && cellReference2.getRow() == spreadsheetVersion.getLastRowIndex() && cellReference2.isRowAbsolute();
    }

    public static AreaReference[] generateContiguous(SpreadsheetVersion spreadsheetVersion, String str) {
        if (spreadsheetVersion == null) {
            spreadsheetVersion = DEFAULT_SPREADSHEET_VERSION;
        }
        ArrayList arrayList = new ArrayList();
        for (String areaReference : splitAreaReferences(str)) {
            arrayList.add(new AreaReference(areaReference, spreadsheetVersion));
        }
        return (AreaReference[]) arrayList.toArray(new AreaReference[0]);
    }

    private static String[] separateAreaRefs(String str) {
        int length = str.length();
        int i = -1;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt != '\'') {
                if (charAt == ':' && !z) {
                    if (i < 0) {
                        i = i2;
                    } else {
                        throw new IllegalArgumentException("More than one cell delimiter ':' appears in area reference '" + str + "'");
                    }
                }
            } else if (!z) {
                z = true;
            } else if (i2 < length - 1) {
                int i3 = i2 + 1;
                if (str.charAt(i3) == '\'') {
                    i2 = i3;
                } else {
                    z = false;
                }
            } else {
                throw new IllegalArgumentException("Area reference '" + str + "' ends with special name delimiter '''");
            }
            i2++;
        }
        if (i < 0) {
            return new String[]{str};
        }
        String substring = str.substring(0, i);
        String substring2 = str.substring(i + 1);
        if (substring2.indexOf(33) < 0) {
            int lastIndexOf = substring.lastIndexOf(33);
            if (lastIndexOf < 0) {
                return new String[]{substring, substring2};
            }
            return new String[]{substring, substring.substring(0, lastIndexOf + 1) + substring2};
        }
        throw new RuntimeException("Unexpected ! in second cell reference of '" + str + "'");
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] splitAreaReferences(java.lang.String r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.StringTokenizer r1 = new java.util.StringTokenizer
            java.lang.String r2 = ","
            r1.<init>(r6, r2)
            java.lang.String r6 = ""
        L_0x000e:
            r3 = r6
        L_0x000f:
            boolean r4 = r1.hasMoreTokens()
            if (r4 == 0) goto L_0x0050
            int r4 = r3.length()
            if (r4 <= 0) goto L_0x002c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r3 = r3.append(r2)
            java.lang.String r3 = r3.toString()
        L_0x002c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = r1.nextToken()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 39
            int r4 = org.apache.poi.util.StringUtil.countMatches(r3, r4)
            if (r4 == 0) goto L_0x004c
            r5 = 2
            if (r4 != r5) goto L_0x000f
        L_0x004c:
            r0.add(r3)
            goto L_0x000e
        L_0x0050:
            int r6 = r3.length()
            if (r6 <= 0) goto L_0x0059
            r0.add(r3)
        L_0x0059:
            r6 = 0
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.Object[] r6 = r0.toArray(r6)
            java.lang.String[] r6 = (java.lang.String[]) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.AreaReference.splitAreaReferences(java.lang.String):java.lang.String[]");
    }

    public boolean isWholeColumnReference() {
        return isWholeColumnReference(this._version, this._firstCell, this._lastCell);
    }

    public boolean isSingleCell() {
        return this._isSingleCell;
    }

    public CellReference getFirstCell() {
        return this._firstCell;
    }

    public CellReference getLastCell() {
        return this._lastCell;
    }

    public CellReference[] getAllReferencedCells() {
        if (this._isSingleCell) {
            return new CellReference[]{this._firstCell};
        }
        int max = Math.max(this._firstCell.getRow(), this._lastCell.getRow());
        int min = Math.min(this._firstCell.getCol(), this._lastCell.getCol());
        int max2 = Math.max(this._firstCell.getCol(), this._lastCell.getCol());
        String sheetName = this._firstCell.getSheetName();
        ArrayList arrayList = new ArrayList();
        for (int min2 = Math.min(this._firstCell.getRow(), this._lastCell.getRow()); min2 <= max; min2++) {
            for (int i = min; i <= max2; i++) {
                arrayList.add(new CellReference(sheetName, min2, i, this._firstCell.isRowAbsolute(), this._firstCell.isColAbsolute()));
            }
        }
        return (CellReference[]) arrayList.toArray(new CellReference[0]);
    }

    public String formatAsString() {
        if (isWholeColumnReference()) {
            return CellReference.convertNumToColString(this._firstCell.getCol()) + ParameterizedMessage.ERROR_MSG_SEPARATOR + CellReference.convertNumToColString(this._lastCell.getCol());
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(this._firstCell.formatAsString());
        if (!this._isSingleCell) {
            sb.append(':');
            if (this._lastCell.getSheetName() == null) {
                sb.append(this._lastCell.formatAsString());
            } else {
                this._lastCell.appendCellReference(sb);
            }
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName()).append(" [");
        try {
            sb.append(formatAsString());
        } catch (Exception e) {
            sb.append(e);
        }
        sb.append(']');
        return sb.toString();
    }
}
