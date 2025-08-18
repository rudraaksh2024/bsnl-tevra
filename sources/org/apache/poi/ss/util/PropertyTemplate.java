package org.apache.poi.ss.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public final class PropertyTemplate {
    private final Map<CellAddress, Map<String, Object>> _propertyTemplate;

    public PropertyTemplate() {
        this._propertyTemplate = new HashMap();
    }

    public PropertyTemplate(PropertyTemplate propertyTemplate) {
        this();
        for (Map.Entry next : propertyTemplate.getTemplate().entrySet()) {
            this._propertyTemplate.put(new CellAddress((CellAddress) next.getKey()), cloneCellProperties((Map) next.getValue()));
        }
    }

    private Map<CellAddress, Map<String, Object>> getTemplate() {
        return this._propertyTemplate;
    }

    private static Map<String, Object> cloneCellProperties(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(next.getKey(), next.getValue());
        }
        return hashMap;
    }

    /* renamed from: org.apache.poi.ss.util.PropertyTemplate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.BorderExtent[] r0 = org.apache.poi.ss.usermodel.BorderExtent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent = r0
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.ALL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.INSIDE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.OUTSIDE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.TOP     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.LEFT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.RIGHT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.INSIDE_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.OUTSIDE_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.INSIDE_VERTICAL     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$BorderExtent     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.apache.poi.ss.usermodel.BorderExtent r1 = org.apache.poi.ss.usermodel.BorderExtent.OUTSIDE_VERTICAL     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.PropertyTemplate.AnonymousClass1.<clinit>():void");
        }
    }

    public void drawBorders(CellRangeAddress cellRangeAddress, BorderStyle borderStyle, BorderExtent borderExtent) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()]) {
            case 1:
                removeBorders(cellRangeAddress);
                return;
            case 2:
                drawHorizontalBorders(cellRangeAddress, borderStyle, BorderExtent.ALL);
                drawVerticalBorders(cellRangeAddress, borderStyle, BorderExtent.ALL);
                return;
            case 3:
                drawHorizontalBorders(cellRangeAddress, borderStyle, BorderExtent.INSIDE);
                drawVerticalBorders(cellRangeAddress, borderStyle, BorderExtent.INSIDE);
                return;
            case 4:
                drawOutsideBorders(cellRangeAddress, borderStyle, BorderExtent.ALL);
                return;
            case 5:
                drawTopBorder(cellRangeAddress, borderStyle);
                return;
            case 6:
                drawBottomBorder(cellRangeAddress, borderStyle);
                return;
            case 7:
                drawLeftBorder(cellRangeAddress, borderStyle);
                return;
            case 8:
                drawRightBorder(cellRangeAddress, borderStyle);
                return;
            case 9:
                drawHorizontalBorders(cellRangeAddress, borderStyle, BorderExtent.ALL);
                return;
            case 10:
                drawHorizontalBorders(cellRangeAddress, borderStyle, BorderExtent.INSIDE);
                return;
            case 11:
                drawOutsideBorders(cellRangeAddress, borderStyle, BorderExtent.HORIZONTAL);
                return;
            case 12:
                drawVerticalBorders(cellRangeAddress, borderStyle, BorderExtent.ALL);
                return;
            case 13:
                drawVerticalBorders(cellRangeAddress, borderStyle, BorderExtent.INSIDE);
                return;
            case 14:
                drawOutsideBorders(cellRangeAddress, borderStyle, BorderExtent.VERTICAL);
                return;
            default:
                return;
        }
    }

    public void drawBorders(CellRangeAddress cellRangeAddress, BorderStyle borderStyle, short s, BorderExtent borderExtent) {
        drawBorders(cellRangeAddress, borderStyle, borderExtent);
        if (borderStyle != BorderStyle.NONE) {
            drawBorderColors(cellRangeAddress, s, borderExtent);
        }
    }

    private void drawTopBorder(CellRangeAddress cellRangeAddress, BorderStyle borderStyle) {
        int firstRow = cellRangeAddress.getFirstRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            addProperty(firstRow, firstColumn, CellUtil.BORDER_TOP, (Object) borderStyle);
            if (borderStyle == BorderStyle.NONE && firstRow > 0) {
                addProperty(firstRow - 1, firstColumn, CellUtil.BORDER_BOTTOM, (Object) borderStyle);
            }
        }
    }

    private void drawBottomBorder(CellRangeAddress cellRangeAddress, BorderStyle borderStyle) {
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            addProperty(lastRow, firstColumn, CellUtil.BORDER_BOTTOM, (Object) borderStyle);
            if (borderStyle == BorderStyle.NONE && lastRow < SpreadsheetVersion.EXCEL2007.getMaxRows() - 1) {
                addProperty(lastRow + 1, firstColumn, CellUtil.BORDER_TOP, (Object) borderStyle);
            }
        }
    }

    private void drawLeftBorder(CellRangeAddress cellRangeAddress, BorderStyle borderStyle) {
        int lastRow = cellRangeAddress.getLastRow();
        int firstColumn = cellRangeAddress.getFirstColumn();
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            addProperty(firstRow, firstColumn, CellUtil.BORDER_LEFT, (Object) borderStyle);
            if (borderStyle == BorderStyle.NONE && firstColumn > 0) {
                addProperty(firstRow, firstColumn - 1, CellUtil.BORDER_RIGHT, (Object) borderStyle);
            }
        }
    }

    private void drawRightBorder(CellRangeAddress cellRangeAddress, BorderStyle borderStyle) {
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            addProperty(firstRow, lastColumn, CellUtil.BORDER_RIGHT, (Object) borderStyle);
            if (borderStyle == BorderStyle.NONE && lastColumn < SpreadsheetVersion.EXCEL2007.getMaxColumns() - 1) {
                addProperty(firstRow, lastColumn + 1, CellUtil.BORDER_LEFT, (Object) borderStyle);
            }
        }
    }

    private void drawOutsideBorders(CellRangeAddress cellRangeAddress, BorderStyle borderStyle, BorderExtent borderExtent) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()];
        if (i == 2 || i == 9 || i == 12) {
            if (borderExtent == BorderExtent.ALL || borderExtent == BorderExtent.HORIZONTAL) {
                drawTopBorder(cellRangeAddress, borderStyle);
                drawBottomBorder(cellRangeAddress, borderStyle);
            }
            if (borderExtent == BorderExtent.ALL || borderExtent == BorderExtent.VERTICAL) {
                drawLeftBorder(cellRangeAddress, borderStyle);
                drawRightBorder(cellRangeAddress, borderStyle);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL, HORIZONTAL, and VERTICAL");
    }

    private void drawHorizontalBorders(CellRangeAddress cellRangeAddress, BorderStyle borderStyle, BorderExtent borderExtent) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()];
        if (i == 2 || i == 3) {
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastColumn = cellRangeAddress.getLastColumn();
            for (int i2 = firstRow; i2 <= lastRow; i2++) {
                CellRangeAddress cellRangeAddress2 = new CellRangeAddress(i2, i2, firstColumn, lastColumn);
                if (borderExtent == BorderExtent.ALL || i2 > firstRow) {
                    drawTopBorder(cellRangeAddress2, borderStyle);
                }
                if (borderExtent == BorderExtent.ALL || i2 < lastRow) {
                    drawBottomBorder(cellRangeAddress2, borderStyle);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
    }

    private void drawVerticalBorders(CellRangeAddress cellRangeAddress, BorderStyle borderStyle, BorderExtent borderExtent) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()];
        if (i == 2 || i == 3) {
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastColumn = cellRangeAddress.getLastColumn();
            for (int i2 = firstColumn; i2 <= lastColumn; i2++) {
                CellRangeAddress cellRangeAddress2 = new CellRangeAddress(firstRow, lastRow, i2, i2);
                if (borderExtent == BorderExtent.ALL || i2 > firstColumn) {
                    drawLeftBorder(cellRangeAddress2, borderStyle);
                }
                if (borderExtent == BorderExtent.ALL || i2 < lastColumn) {
                    drawRightBorder(cellRangeAddress2, borderStyle);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
    }

    private void removeBorders(CellRangeAddress cellRangeAddress) {
        HashSet hashSet = new HashSet();
        hashSet.add(CellUtil.BORDER_TOP);
        hashSet.add(CellUtil.BORDER_BOTTOM);
        hashSet.add(CellUtil.BORDER_LEFT);
        hashSet.add(CellUtil.BORDER_RIGHT);
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= cellRangeAddress.getLastRow(); firstRow++) {
            for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= cellRangeAddress.getLastColumn(); firstColumn++) {
                removeProperties(firstRow, firstColumn, hashSet);
            }
        }
        removeBorderColors(cellRangeAddress);
    }

    public void applyBorders(Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();
        for (Map.Entry next : this._propertyTemplate.entrySet()) {
            CellAddress cellAddress = (CellAddress) next.getKey();
            if (cellAddress.getRow() < workbook.getSpreadsheetVersion().getMaxRows() && cellAddress.getColumn() < workbook.getSpreadsheetVersion().getMaxColumns()) {
                CellUtil.setCellStyleProperties(CellUtil.getCell(CellUtil.getRow(cellAddress.getRow(), sheet), cellAddress.getColumn()), (Map) next.getValue());
            }
        }
    }

    public void drawBorderColors(CellRangeAddress cellRangeAddress, short s, BorderExtent borderExtent) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()]) {
            case 1:
                removeBorderColors(cellRangeAddress);
                return;
            case 2:
                drawHorizontalBorderColors(cellRangeAddress, s, BorderExtent.ALL);
                drawVerticalBorderColors(cellRangeAddress, s, BorderExtent.ALL);
                return;
            case 3:
                drawHorizontalBorderColors(cellRangeAddress, s, BorderExtent.INSIDE);
                drawVerticalBorderColors(cellRangeAddress, s, BorderExtent.INSIDE);
                return;
            case 4:
                drawOutsideBorderColors(cellRangeAddress, s, BorderExtent.ALL);
                return;
            case 5:
                drawTopBorderColor(cellRangeAddress, s);
                return;
            case 6:
                drawBottomBorderColor(cellRangeAddress, s);
                return;
            case 7:
                drawLeftBorderColor(cellRangeAddress, s);
                return;
            case 8:
                drawRightBorderColor(cellRangeAddress, s);
                return;
            case 9:
                drawHorizontalBorderColors(cellRangeAddress, s, BorderExtent.ALL);
                return;
            case 10:
                drawHorizontalBorderColors(cellRangeAddress, s, BorderExtent.INSIDE);
                return;
            case 11:
                drawOutsideBorderColors(cellRangeAddress, s, BorderExtent.HORIZONTAL);
                return;
            case 12:
                drawVerticalBorderColors(cellRangeAddress, s, BorderExtent.ALL);
                return;
            case 13:
                drawVerticalBorderColors(cellRangeAddress, s, BorderExtent.INSIDE);
                return;
            case 14:
                drawOutsideBorderColors(cellRangeAddress, s, BorderExtent.VERTICAL);
                return;
            default:
                return;
        }
    }

    private void drawTopBorderColor(CellRangeAddress cellRangeAddress, short s) {
        int firstRow = cellRangeAddress.getFirstRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            if (getBorderStyle(firstRow, firstColumn, CellUtil.BORDER_TOP) == BorderStyle.NONE) {
                drawTopBorder(new CellRangeAddress(firstRow, firstRow, firstColumn, firstColumn), BorderStyle.THIN);
            }
            addProperty(firstRow, firstColumn, CellUtil.TOP_BORDER_COLOR, s);
        }
    }

    private void drawBottomBorderColor(CellRangeAddress cellRangeAddress, short s) {
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            if (getBorderStyle(lastRow, firstColumn, CellUtil.BORDER_BOTTOM) == BorderStyle.NONE) {
                drawBottomBorder(new CellRangeAddress(lastRow, lastRow, firstColumn, firstColumn), BorderStyle.THIN);
            }
            addProperty(lastRow, firstColumn, CellUtil.BOTTOM_BORDER_COLOR, s);
        }
    }

    private void drawLeftBorderColor(CellRangeAddress cellRangeAddress, short s) {
        int lastRow = cellRangeAddress.getLastRow();
        int firstColumn = cellRangeAddress.getFirstColumn();
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            if (getBorderStyle(firstRow, firstColumn, CellUtil.BORDER_LEFT) == BorderStyle.NONE) {
                drawLeftBorder(new CellRangeAddress(firstRow, firstRow, firstColumn, firstColumn), BorderStyle.THIN);
            }
            addProperty(firstRow, firstColumn, CellUtil.LEFT_BORDER_COLOR, s);
        }
    }

    private void drawRightBorderColor(CellRangeAddress cellRangeAddress, short s) {
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            if (getBorderStyle(firstRow, lastColumn, CellUtil.BORDER_RIGHT) == BorderStyle.NONE) {
                drawRightBorder(new CellRangeAddress(firstRow, firstRow, lastColumn, lastColumn), BorderStyle.THIN);
            }
            addProperty(firstRow, lastColumn, CellUtil.RIGHT_BORDER_COLOR, s);
        }
    }

    private void drawOutsideBorderColors(CellRangeAddress cellRangeAddress, short s, BorderExtent borderExtent) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()];
        if (i == 2 || i == 9 || i == 12) {
            if (borderExtent == BorderExtent.ALL || borderExtent == BorderExtent.HORIZONTAL) {
                drawTopBorderColor(cellRangeAddress, s);
                drawBottomBorderColor(cellRangeAddress, s);
            }
            if (borderExtent == BorderExtent.ALL || borderExtent == BorderExtent.VERTICAL) {
                drawLeftBorderColor(cellRangeAddress, s);
                drawRightBorderColor(cellRangeAddress, s);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL, HORIZONTAL, and VERTICAL");
    }

    private void drawHorizontalBorderColors(CellRangeAddress cellRangeAddress, short s, BorderExtent borderExtent) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()];
        if (i == 2 || i == 3) {
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastColumn = cellRangeAddress.getLastColumn();
            for (int i2 = firstRow; i2 <= lastRow; i2++) {
                CellRangeAddress cellRangeAddress2 = new CellRangeAddress(i2, i2, firstColumn, lastColumn);
                if (borderExtent == BorderExtent.ALL || i2 > firstRow) {
                    drawTopBorderColor(cellRangeAddress2, s);
                }
                if (borderExtent == BorderExtent.ALL || i2 < lastRow) {
                    drawBottomBorderColor(cellRangeAddress2, s);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
    }

    private void drawVerticalBorderColors(CellRangeAddress cellRangeAddress, short s, BorderExtent borderExtent) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$BorderExtent[borderExtent.ordinal()];
        if (i == 2 || i == 3) {
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastColumn = cellRangeAddress.getLastColumn();
            for (int i2 = firstColumn; i2 <= lastColumn; i2++) {
                CellRangeAddress cellRangeAddress2 = new CellRangeAddress(firstRow, lastRow, i2, i2);
                if (borderExtent == BorderExtent.ALL || i2 > firstColumn) {
                    drawLeftBorderColor(cellRangeAddress2, s);
                }
                if (borderExtent == BorderExtent.ALL || i2 < lastColumn) {
                    drawRightBorderColor(cellRangeAddress2, s);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
    }

    private void removeBorderColors(CellRangeAddress cellRangeAddress) {
        HashSet hashSet = new HashSet();
        hashSet.add(CellUtil.TOP_BORDER_COLOR);
        hashSet.add(CellUtil.BOTTOM_BORDER_COLOR);
        hashSet.add(CellUtil.LEFT_BORDER_COLOR);
        hashSet.add(CellUtil.RIGHT_BORDER_COLOR);
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= cellRangeAddress.getLastRow(); firstRow++) {
            for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= cellRangeAddress.getLastColumn(); firstColumn++) {
                removeProperties(firstRow, firstColumn, hashSet);
            }
        }
    }

    private void addProperty(int i, int i2, String str, short s) {
        addProperty(i, i2, str, (Object) Short.valueOf(s));
    }

    private void addProperty(int i, int i2, String str, Object obj) {
        CellAddress cellAddress = new CellAddress(i, i2);
        Map map = this._propertyTemplate.get(cellAddress);
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, obj);
        this._propertyTemplate.put(cellAddress, map);
    }

    private void removeProperties(int i, int i2, Set<String> set) {
        CellAddress cellAddress = new CellAddress(i, i2);
        Map map = this._propertyTemplate.get(cellAddress);
        if (map != null) {
            map.keySet().removeAll(set);
            if (map.isEmpty()) {
                this._propertyTemplate.remove(cellAddress);
            } else {
                this._propertyTemplate.put(cellAddress, map);
            }
        }
    }

    public int getNumBorders(CellAddress cellAddress) {
        Map map = this._propertyTemplate.get(cellAddress);
        int i = 0;
        if (map == null) {
            return 0;
        }
        for (String str : map.keySet()) {
            if (str.equals(CellUtil.BORDER_TOP)) {
                i++;
            }
            if (str.equals(CellUtil.BORDER_BOTTOM)) {
                i++;
            }
            if (str.equals(CellUtil.BORDER_LEFT)) {
                i++;
            }
            if (str.equals(CellUtil.BORDER_RIGHT)) {
                i++;
            }
        }
        return i;
    }

    public int getNumBorders(int i, int i2) {
        return getNumBorders(new CellAddress(i, i2));
    }

    public int getNumBorderColors(CellAddress cellAddress) {
        Map map = this._propertyTemplate.get(cellAddress);
        int i = 0;
        if (map == null) {
            return 0;
        }
        for (String str : map.keySet()) {
            if (str.equals(CellUtil.TOP_BORDER_COLOR)) {
                i++;
            }
            if (str.equals(CellUtil.BOTTOM_BORDER_COLOR)) {
                i++;
            }
            if (str.equals(CellUtil.LEFT_BORDER_COLOR)) {
                i++;
            }
            if (str.equals(CellUtil.RIGHT_BORDER_COLOR)) {
                i++;
            }
        }
        return i;
    }

    public int getNumBorderColors(int i, int i2) {
        return getNumBorderColors(new CellAddress(i, i2));
    }

    public BorderStyle getBorderStyle(CellAddress cellAddress, String str) {
        BorderStyle borderStyle = BorderStyle.NONE;
        Map map = this._propertyTemplate.get(cellAddress);
        if (map == null) {
            return borderStyle;
        }
        Object obj = map.get(str);
        return obj instanceof BorderStyle ? (BorderStyle) obj : borderStyle;
    }

    public BorderStyle getBorderStyle(int i, int i2, String str) {
        return getBorderStyle(new CellAddress(i, i2), str);
    }

    public short getTemplateProperty(CellAddress cellAddress, String str) {
        Object obj;
        Map map = this._propertyTemplate.get(cellAddress);
        if (map == null || (obj = map.get(str)) == null) {
            return 0;
        }
        return getShort(obj);
    }

    public short getTemplateProperty(int i, int i2, String str) {
        return getTemplateProperty(new CellAddress(i, i2), str);
    }

    private static short getShort(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).shortValue();
        }
        return 0;
    }
}
