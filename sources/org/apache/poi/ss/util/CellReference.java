package org.apache.poi.ss.util;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.StringUtil;

public class CellReference implements GenericRecord {
    private static final char ABSOLUTE_REFERENCE_MARKER = '$';
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("(\\$?[A-Z]+)?(\\$?[0-9]+)?", 2);
    private static final Pattern COLUMN_REF_PATTERN = Pattern.compile("\\$?([A-Z]+)", 2);
    private static final Pattern NAMED_RANGE_NAME_PATTERN = Pattern.compile("[_A-Z][_.A-Z0-9]*", 2);
    private static final Pattern ROW_REF_PATTERN = Pattern.compile("\\$?([0-9]+)");
    private static final char SHEET_NAME_DELIMITER = '!';
    private static final char SPECIAL_NAME_DELIMITER = '\'';
    private static final Pattern STRICTLY_CELL_REF_PATTERN = Pattern.compile("\\$?([A-Z]+)\\$?([0-9]+)", 2);
    private final int _colIndex;
    private final boolean _isColAbs;
    private final boolean _isRowAbs;
    private final int _rowIndex;
    private final String _sheetName;

    public enum NameType {
        CELL,
        NAMED_RANGE,
        COLUMN,
        ROW,
        BAD_CELL_OR_NAMED_RANGE
    }

    public CellReference(String str) {
        if (!StringUtil.endsWithIgnoreCase(str, "#REF!")) {
            CellRefParts separateRefParts = separateRefParts(str);
            this._sheetName = separateRefParts.sheetName;
            String str2 = separateRefParts.colRef;
            boolean z = false;
            boolean z2 = str2.length() > 0 && str2.charAt(0) == '$';
            this._isColAbs = z2;
            str2 = z2 ? str2.substring(1) : str2;
            if (str2.length() == 0) {
                this._colIndex = -1;
            } else {
                this._colIndex = convertColStringToIndex(str2);
            }
            String str3 = separateRefParts.rowRef;
            if (str3.length() > 0 && str3.charAt(0) == '$') {
                z = true;
            }
            this._isRowAbs = z;
            str3 = z ? str3.substring(1) : str3;
            if (str3.length() == 0) {
                this._rowIndex = -1;
            } else {
                this._rowIndex = Integer.parseInt(str3) - 1;
            }
        } else {
            throw new IllegalArgumentException("Cell reference invalid: " + str);
        }
    }

    public CellReference(int i, int i2) {
        this(i, i2, false, false);
    }

    public CellReference(int i, short s) {
        this(i, s & 65535, false, false);
    }

    public CellReference(Cell cell) {
        this(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false);
    }

    public CellReference(int i, int i2, boolean z, boolean z2) {
        this((String) null, i, i2, z, z2);
    }

    public CellReference(String str, int i, int i2, boolean z, boolean z2) {
        if (i < -1) {
            throw new IllegalArgumentException("row index may not be negative, but had " + i);
        } else if (i2 >= -1) {
            this._sheetName = str;
            this._rowIndex = i;
            this._colIndex = i2;
            this._isRowAbs = z;
            this._isColAbs = z2;
        } else {
            throw new IllegalArgumentException("column index may not be negative, but had " + i2);
        }
    }

    public int getRow() {
        return this._rowIndex;
    }

    public short getCol() {
        return (short) this._colIndex;
    }

    public boolean isRowAbsolute() {
        return this._isRowAbs;
    }

    public boolean isColAbsolute() {
        return this._isColAbs;
    }

    public String getSheetName() {
        return this._sheetName;
    }

    public static boolean isPartAbsolute(String str) {
        return str.charAt(0) == '$';
    }

    public static int convertColStringToIndex(String str) {
        char[] charArray = str.toUpperCase(Locale.ROOT).toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            char c = charArray[i2];
            if (c != '$') {
                i = (i * 26) + (c - 'A') + 1;
            } else if (i2 != 0) {
                throw new IllegalArgumentException("Bad col ref format '" + str + "'");
            }
        }
        return i - 1;
    }

    public static NameType classifyCellReference(String str, SpreadsheetVersion spreadsheetVersion) {
        int length = str.length();
        if (length >= 1) {
            char charAt = str.charAt(0);
            if (charAt != '$' && charAt != '.' && charAt != '_' && !Character.isLetter(charAt) && !Character.isDigit(charAt)) {
                throw new IllegalArgumentException("Invalid first char (" + charAt + ") of cell reference or named range.  Letter expected");
            } else if (!Character.isDigit(str.charAt(length - 1))) {
                return validateNamedRangeName(str, spreadsheetVersion);
            } else {
                Matcher matcher = STRICTLY_CELL_REF_PATTERN.matcher(str);
                if (!matcher.matches()) {
                    return validateNamedRangeName(str, spreadsheetVersion);
                }
                if (cellReferenceIsWithinRange(matcher.group(1), matcher.group(2), spreadsheetVersion)) {
                    return NameType.CELL;
                }
                if (str.indexOf(36) >= 0) {
                    return NameType.BAD_CELL_OR_NAMED_RANGE;
                }
                return NameType.NAMED_RANGE;
            }
        } else {
            throw new IllegalArgumentException("Empty string not allowed");
        }
    }

    private static NameType validateNamedRangeName(String str, SpreadsheetVersion spreadsheetVersion) {
        Matcher matcher = COLUMN_REF_PATTERN.matcher(str);
        if (matcher.matches() && isColumnWithinRange(matcher.group(1), spreadsheetVersion)) {
            return NameType.COLUMN;
        }
        Matcher matcher2 = ROW_REF_PATTERN.matcher(str);
        if (matcher2.matches() && isRowWithinRange(matcher2.group(1), spreadsheetVersion)) {
            return NameType.ROW;
        }
        if (!NAMED_RANGE_NAME_PATTERN.matcher(str).matches()) {
            return NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return NameType.NAMED_RANGE;
    }

    public static boolean cellReferenceIsWithinRange(String str, String str2, SpreadsheetVersion spreadsheetVersion) {
        if (!isColumnWithinRange(str, spreadsheetVersion)) {
            return false;
        }
        return isRowWithinRange(str2, spreadsheetVersion);
    }

    public static boolean isColumnWithinRange(String str, SpreadsheetVersion spreadsheetVersion) {
        String lastColumnName = spreadsheetVersion.getLastColumnName();
        int length = lastColumnName.length();
        int length2 = str.length();
        if (length2 > length) {
            return false;
        }
        if (length2 != length || str.toUpperCase(Locale.ROOT).compareTo(lastColumnName) <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isRowWithinRange(String str, SpreadsheetVersion spreadsheetVersion) {
        long parseLong = Long.parseLong(str) - 1;
        if (parseLong > 2147483647L) {
            return false;
        }
        return isRowWithinRange(Math.toIntExact(parseLong), spreadsheetVersion);
    }

    public static boolean isRowWithinRange(int i, SpreadsheetVersion spreadsheetVersion) {
        return i >= 0 && i <= spreadsheetVersion.getLastRowIndex();
    }

    private static final class CellRefParts {
        final String colRef;
        final String rowRef;
        final String sheetName;

        private CellRefParts(String str, String str2, String str3) {
            this.sheetName = str;
            this.rowRef = str2 == null ? "" : str2;
            this.colRef = str3 == null ? "" : str3;
        }
    }

    private static CellRefParts separateRefParts(String str) {
        int lastIndexOf = str.lastIndexOf(33);
        String parseSheetName = parseSheetName(str, lastIndexOf);
        Matcher matcher = CELL_REF_PATTERN.matcher(str.substring(lastIndexOf + 1).toUpperCase(Locale.ROOT));
        if (matcher.matches()) {
            return new CellRefParts(parseSheetName, matcher.group(2), matcher.group(1));
        }
        throw new IllegalArgumentException("Invalid CellReference: " + str);
    }

    private static String parseSheetName(String str, int i) {
        if (i < 0) {
            return null;
        }
        if (str.charAt(0) == '\'') {
            int i2 = i - 1;
            if (str.charAt(i2) == '\'') {
                StringBuilder sb = new StringBuilder(i);
                int i3 = 1;
                while (i3 < i2) {
                    char charAt = str.charAt(i3);
                    if (charAt != '\'') {
                        sb.append(charAt);
                    } else {
                        i3++;
                        if (i3 >= i2 || str.charAt(i3) != '\'') {
                            throw new IllegalArgumentException("Bad sheet name quote escaping: (" + str + ")");
                        }
                        sb.append(charAt);
                    }
                    i3++;
                }
                return sb.toString();
            }
            throw new IllegalArgumentException("Mismatched quotes: (" + str + ")");
        } else if (!str.contains(" ")) {
            return str.substring(0, i);
        } else {
            throw new IllegalArgumentException("Sheet names containing spaces must be quoted: (" + str + ")");
        }
    }

    public static String convertNumToColString(int i) {
        int i2 = i + 1;
        StringBuilder sb = new StringBuilder(2);
        while (i2 > 0) {
            int i3 = i2 % 26;
            if (i3 == 0) {
                i3 = 26;
            }
            i2 = (i2 - i3) / 26;
            sb.insert(0, (char) (i3 + 64));
        }
        return sb.toString();
    }

    public String formatAsString() {
        return formatAsString(true);
    }

    public String formatAsR1C1String() {
        return formatAsR1C1String(true);
    }

    public String formatAsString(boolean z) {
        String str;
        StringBuilder sb = new StringBuilder(32);
        if (z && (str = this._sheetName) != null) {
            SheetNameFormatter.appendFormat(sb, str);
            sb.append(SHEET_NAME_DELIMITER);
        }
        appendCellReference(sb);
        return sb.toString();
    }

    public String formatAsR1C1String(boolean z) {
        String str;
        StringBuilder sb = new StringBuilder(32);
        if (z && (str = this._sheetName) != null) {
            SheetNameFormatter.appendFormat(sb, str);
            sb.append(SHEET_NAME_DELIMITER);
        }
        appendR1C1CellReference(sb);
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " [" + formatAsString() + "]";
    }

    public String[] getCellRefParts() {
        return new String[]{this._sheetName, Integer.toString(this._rowIndex + 1), convertNumToColString(this._colIndex)};
    }

    /* access modifiers changed from: package-private */
    public void appendCellReference(StringBuilder sb) {
        if (this._colIndex != -1) {
            if (this._isColAbs) {
                sb.append('$');
            }
            sb.append(convertNumToColString(this._colIndex));
        }
        if (this._rowIndex != -1) {
            if (this._isRowAbs) {
                sb.append('$');
            }
            sb.append(this._rowIndex + 1);
        }
    }

    /* access modifiers changed from: package-private */
    public void appendR1C1CellReference(StringBuilder sb) {
        if (this._rowIndex != -1) {
            sb.append('R').append(this._rowIndex + 1);
        }
        if (this._colIndex != -1) {
            sb.append('C').append(this._colIndex + 1);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CellReference)) {
            return false;
        }
        CellReference cellReference = (CellReference) obj;
        if (this._rowIndex == cellReference._rowIndex && this._colIndex == cellReference._colIndex && this._isRowAbs == cellReference._isRowAbs && this._isColAbs == cellReference._isColAbs && Objects.equals(this._sheetName, cellReference._sheetName)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this._rowIndex), Integer.valueOf(this._colIndex), Boolean.valueOf(this._isRowAbs), Boolean.valueOf(this._isColAbs), this._sheetName});
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("sheetName", new CellReference$$ExternalSyntheticLambda0(this), "rowIndex", new CellReference$$ExternalSyntheticLambda1(this), "colIndex", new CellReference$$ExternalSyntheticLambda2(this), "rowAbs", new CellReference$$ExternalSyntheticLambda3(this), "colAbs", new CellReference$$ExternalSyntheticLambda4(this), "formatAsString", new CellReference$$ExternalSyntheticLambda5(this));
    }
}
