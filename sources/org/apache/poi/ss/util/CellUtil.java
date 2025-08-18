package org.apache.poi.ss.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public final class CellUtil {
    public static final String ALIGNMENT = "alignment";
    public static final String BORDER_BOTTOM = "borderBottom";
    public static final String BORDER_LEFT = "borderLeft";
    public static final String BORDER_RIGHT = "borderRight";
    public static final String BORDER_TOP = "borderTop";
    public static final String BOTTOM_BORDER_COLOR = "bottomBorderColor";
    public static final String DATA_FORMAT = "dataFormat";
    public static final String FILL_BACKGROUND_COLOR = "fillBackgroundColor";
    public static final String FILL_FOREGROUND_COLOR = "fillForegroundColor";
    public static final String FILL_PATTERN = "fillPattern";
    public static final String FONT = "font";
    public static final String HIDDEN = "hidden";
    public static final String INDENTION = "indention";
    public static final String LEFT_BORDER_COLOR = "leftBorderColor";
    public static final String LOCKED = "locked";
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) CellUtil.class);
    public static final String RIGHT_BORDER_COLOR = "rightBorderColor";
    public static final String ROTATION = "rotation";
    public static final String TOP_BORDER_COLOR = "topBorderColor";
    public static final String VERTICAL_ALIGNMENT = "verticalAlignment";
    public static final String WRAP_TEXT = "wrapText";
    private static final Set<String> booleanValues = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{LOCKED, HIDDEN, WRAP_TEXT})));
    private static final Set<String> borderTypeValues = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{BORDER_BOTTOM, BORDER_LEFT, BORDER_RIGHT, BORDER_TOP})));
    private static final Set<String> intValues = Collections.unmodifiableSet(new HashSet(Collections.singletonList(FONT)));
    private static final Set<String> shortValues = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{BOTTOM_BORDER_COLOR, LEFT_BORDER_COLOR, RIGHT_BORDER_COLOR, TOP_BORDER_COLOR, FILL_FOREGROUND_COLOR, FILL_BACKGROUND_COLOR, INDENTION, DATA_FORMAT, "rotation"})));
    private static final UnicodeMapping[] unicodeMappings = {um("alpha", "α"), um("beta", "β"), um("gamma", "γ"), um("delta", "δ"), um("epsilon", "ε"), um("zeta", "ζ"), um("eta", "η"), um("theta", "θ"), um("iota", "ι"), um("kappa", "κ"), um("lambda", "λ"), um("mu", "μ"), um("nu", "ν"), um("xi", "ξ"), um("omicron", "ο")};

    private static final class UnicodeMapping {
        public final String entityName;
        public final String resolvedValue;

        public UnicodeMapping(String str, String str2) {
            this.entityName = "&" + str + ";";
            this.resolvedValue = str2;
        }
    }

    private CellUtil() {
    }

    public static Row getRow(int i, Sheet sheet) {
        Row row = sheet.getRow(i);
        return row == null ? sheet.createRow(i) : row;
    }

    public static Cell getCell(Row row, int i) {
        Cell cell = row.getCell(i);
        return cell == null ? row.createCell(i) : cell;
    }

    public static Cell createCell(Row row, int i, String str, CellStyle cellStyle) {
        Cell cell = getCell(row, i);
        cell.setCellValue(cell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(str));
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        return cell;
    }

    public static Cell createCell(Row row, int i, String str) {
        return createCell(row, i, str, (CellStyle) null);
    }

    public static void copyCell(Cell cell, Cell cell2, CellCopyPolicy cellCopyPolicy, CellCopyContext cellCopyContext) {
        Hyperlink hyperlink;
        CellStyle cellStyle;
        if (cellCopyPolicy.isCopyCellValue()) {
            if (cell != null) {
                CellType cellType = cell.getCellType();
                if (cellType == CellType.FORMULA && !cellCopyPolicy.isCopyCellFormula()) {
                    cellType = cell.getCachedFormulaResultType();
                }
                switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
                    case 1:
                        if (!DateUtil.isCellDateFormatted(cell)) {
                            cell2.setCellValue(cell.getNumericCellValue());
                            break;
                        } else {
                            cell2.setCellValue(cell.getDateCellValue());
                            break;
                        }
                    case 2:
                        cell2.setCellValue(cell.getRichStringCellValue());
                        break;
                    case 3:
                        cell2.setCellFormula(cell.getCellFormula());
                        break;
                    case 4:
                        cell2.setBlank();
                        break;
                    case 5:
                        cell2.setCellValue(cell.getBooleanCellValue());
                        break;
                    case 6:
                        cell2.setCellErrorValue(cell.getErrorCellValue());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid cell type " + cell.getCellType());
                }
            } else {
                cell2.setBlank();
            }
        }
        if (cellCopyPolicy.isCopyCellStyle()) {
            if (cell.getSheet() == null || cell2.getSheet() == null || cell2.getSheet().getWorkbook() != cell.getSheet().getWorkbook()) {
                CellStyle cellStyle2 = cell.getCellStyle();
                if (cellCopyContext == null) {
                    cellStyle = null;
                } else {
                    cellStyle = cellCopyContext.getMappedStyle(cellStyle2);
                }
                if (cellStyle == null) {
                    cellStyle = cell2.getSheet().getWorkbook().createCellStyle();
                    cellStyle.cloneStyleFrom(cellStyle2);
                    if (cellCopyContext != null) {
                        cellCopyContext.putMappedStyle(cellStyle2, cellStyle);
                    }
                }
                cell2.setCellStyle(cellStyle);
            } else {
                cell2.setCellStyle(cell.getCellStyle());
            }
        }
        if (cell == null) {
            hyperlink = null;
        } else {
            hyperlink = cell.getHyperlink();
        }
        if (cellCopyPolicy.isMergeHyperlink()) {
            if (hyperlink == null) {
                return;
            }
            if (hyperlink instanceof Duplicatable) {
                cell2.setHyperlink((Hyperlink) ((Duplicatable) hyperlink).copy());
                return;
            }
            throw new IllegalStateException("srcCell hyperlink is not an instance of Duplicatable");
        } else if (!cellCopyPolicy.isCopyHyperlink()) {
        } else {
            if (hyperlink == null) {
                cell2.setHyperlink((Hyperlink) null);
            } else if (hyperlink instanceof Duplicatable) {
                cell2.setHyperlink((Hyperlink) ((Duplicatable) hyperlink).copy());
            } else {
                throw new IllegalStateException("srcCell hyperlink is not an instance of Duplicatable");
            }
        }
    }

    /* renamed from: org.apache.poi.ss.util.CellUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.CellUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static void setAlignment(Cell cell, HorizontalAlignment horizontalAlignment) {
        setCellStyleProperty(cell, ALIGNMENT, horizontalAlignment);
    }

    public static void setVerticalAlignment(Cell cell, VerticalAlignment verticalAlignment) {
        setCellStyleProperty(cell, VERTICAL_ALIGNMENT, verticalAlignment);
    }

    public static void setFont(Cell cell, Font font) {
        Workbook workbook = cell.getSheet().getWorkbook();
        int index = font.getIndex();
        if (workbook.getFontAt(index).equals(font)) {
            setCellStyleProperty(cell, FONT, Integer.valueOf(index));
            return;
        }
        throw new IllegalArgumentException("Font does not belong to this workbook");
    }

    public static void setCellStyleProperties(Cell cell, Map<String, Object> map) {
        CellStyle cellStyle;
        Workbook workbook = cell.getSheet().getWorkbook();
        Map<String, Object> formatProperties = getFormatProperties(cell.getCellStyle());
        putAll(map, formatProperties);
        int numCellStyles = workbook.getNumCellStyles();
        int i = 0;
        while (true) {
            if (i >= numCellStyles) {
                cellStyle = null;
                break;
            }
            cellStyle = workbook.getCellStyleAt(i);
            if (getFormatProperties(cellStyle).equals(formatProperties)) {
                break;
            }
            i++;
        }
        if (cellStyle == null) {
            cellStyle = workbook.createCellStyle();
            setFormatProperties(cellStyle, workbook, formatProperties);
        }
        cell.setCellStyle(cellStyle);
    }

    public static void setCellStyleProperty(Cell cell, String str, Object obj) {
        setCellStyleProperties(cell, Collections.singletonMap(str, obj));
    }

    private static Map<String, Object> getFormatProperties(CellStyle cellStyle) {
        HashMap hashMap = new HashMap();
        put(hashMap, ALIGNMENT, cellStyle.getAlignment());
        put(hashMap, VERTICAL_ALIGNMENT, cellStyle.getVerticalAlignment());
        put(hashMap, BORDER_BOTTOM, cellStyle.getBorderBottom());
        put(hashMap, BORDER_LEFT, cellStyle.getBorderLeft());
        put(hashMap, BORDER_RIGHT, cellStyle.getBorderRight());
        put(hashMap, BORDER_TOP, cellStyle.getBorderTop());
        put(hashMap, BOTTOM_BORDER_COLOR, Short.valueOf(cellStyle.getBottomBorderColor()));
        put(hashMap, DATA_FORMAT, Short.valueOf(cellStyle.getDataFormat()));
        put(hashMap, FILL_PATTERN, cellStyle.getFillPattern());
        put(hashMap, FILL_FOREGROUND_COLOR, Short.valueOf(cellStyle.getFillForegroundColor()));
        put(hashMap, FILL_BACKGROUND_COLOR, Short.valueOf(cellStyle.getFillBackgroundColor()));
        put(hashMap, FONT, Integer.valueOf(cellStyle.getFontIndex()));
        put(hashMap, HIDDEN, Boolean.valueOf(cellStyle.getHidden()));
        put(hashMap, INDENTION, Short.valueOf(cellStyle.getIndention()));
        put(hashMap, LEFT_BORDER_COLOR, Short.valueOf(cellStyle.getLeftBorderColor()));
        put(hashMap, LOCKED, Boolean.valueOf(cellStyle.getLocked()));
        put(hashMap, RIGHT_BORDER_COLOR, Short.valueOf(cellStyle.getRightBorderColor()));
        put(hashMap, "rotation", Short.valueOf(cellStyle.getRotation()));
        put(hashMap, TOP_BORDER_COLOR, Short.valueOf(cellStyle.getTopBorderColor()));
        put(hashMap, WRAP_TEXT, Boolean.valueOf(cellStyle.getWrapText()));
        return hashMap;
    }

    private static void putAll(Map<String, Object> map, Map<String, Object> map2) {
        for (String next : map.keySet()) {
            if (shortValues.contains(next)) {
                map2.put(next, Short.valueOf(getShort(map, next)));
            } else if (intValues.contains(next)) {
                map2.put(next, Integer.valueOf(getInt(map, next)));
            } else if (booleanValues.contains(next)) {
                map2.put(next, Boolean.valueOf(getBoolean(map, next)));
            } else if (borderTypeValues.contains(next)) {
                map2.put(next, getBorderStyle(map, next));
            } else if (ALIGNMENT.equals(next)) {
                map2.put(next, getHorizontalAlignment(map, next));
            } else if (VERTICAL_ALIGNMENT.equals(next)) {
                map2.put(next, getVerticalAlignment(map, next));
            } else if (FILL_PATTERN.equals(next)) {
                map2.put(next, getFillPattern(map, next));
            } else {
                LOGGER.atInfo().log("Ignoring unrecognized CellUtil format properties key: {}", (Object) next);
            }
        }
    }

    private static void setFormatProperties(CellStyle cellStyle, Workbook workbook, Map<String, Object> map) {
        cellStyle.setAlignment(getHorizontalAlignment(map, ALIGNMENT));
        cellStyle.setVerticalAlignment(getVerticalAlignment(map, VERTICAL_ALIGNMENT));
        cellStyle.setBorderBottom(getBorderStyle(map, BORDER_BOTTOM));
        cellStyle.setBorderLeft(getBorderStyle(map, BORDER_LEFT));
        cellStyle.setBorderRight(getBorderStyle(map, BORDER_RIGHT));
        cellStyle.setBorderTop(getBorderStyle(map, BORDER_TOP));
        cellStyle.setBottomBorderColor(getShort(map, BOTTOM_BORDER_COLOR));
        cellStyle.setDataFormat(getShort(map, DATA_FORMAT));
        cellStyle.setFillPattern(getFillPattern(map, FILL_PATTERN));
        cellStyle.setFillForegroundColor(getShort(map, FILL_FOREGROUND_COLOR));
        cellStyle.setFillBackgroundColor(getShort(map, FILL_BACKGROUND_COLOR));
        cellStyle.setFont(workbook.getFontAt(getInt(map, FONT)));
        cellStyle.setHidden(getBoolean(map, HIDDEN));
        cellStyle.setIndention(getShort(map, INDENTION));
        cellStyle.setLeftBorderColor(getShort(map, LEFT_BORDER_COLOR));
        cellStyle.setLocked(getBoolean(map, LOCKED));
        cellStyle.setRightBorderColor(getShort(map, RIGHT_BORDER_COLOR));
        cellStyle.setRotation(getShort(map, "rotation"));
        cellStyle.setTopBorderColor(getShort(map, TOP_BORDER_COLOR));
        cellStyle.setWrapText(getBoolean(map, WRAP_TEXT));
    }

    private static short getShort(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Number) {
            return ((Number) obj).shortValue();
        }
        return 0;
    }

    private static int getInt(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return 0;
    }

    private static BorderStyle getBorderStyle(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof BorderStyle) {
            return (BorderStyle) obj;
        }
        if (obj instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map uses Short values for {}. Should use BorderStyle enums instead.", (Object) str);
            return BorderStyle.valueOf(((Short) obj).shortValue());
        } else if (obj == null) {
            return BorderStyle.NONE;
        } else {
            throw new RuntimeException("Unexpected border style class. Must be BorderStyle or Short (deprecated).");
        }
    }

    private static FillPatternType getFillPattern(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof FillPatternType) {
            return (FillPatternType) obj;
        }
        if (obj instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map uses Short values for {}. Should use FillPatternType enums instead.", (Object) str);
            return FillPatternType.forInt(((Short) obj).shortValue());
        } else if (obj == null) {
            return FillPatternType.NO_FILL;
        } else {
            throw new RuntimeException("Unexpected fill pattern style class. Must be FillPatternType or Short (deprecated).");
        }
    }

    private static HorizontalAlignment getHorizontalAlignment(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof HorizontalAlignment) {
            return (HorizontalAlignment) obj;
        }
        if (obj instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map used a Short value for {}. Should use HorizontalAlignment enums instead.", (Object) str);
            return HorizontalAlignment.forInt(((Short) obj).shortValue());
        } else if (obj == null) {
            return HorizontalAlignment.GENERAL;
        } else {
            throw new RuntimeException("Unexpected horizontal alignment style class. Must be HorizontalAlignment or Short (deprecated).");
        }
    }

    private static VerticalAlignment getVerticalAlignment(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof VerticalAlignment) {
            return (VerticalAlignment) obj;
        }
        if (obj instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map used a Short value for {}. Should use VerticalAlignment enums instead.", (Object) str);
            return VerticalAlignment.forInt(((Short) obj).shortValue());
        } else if (obj == null) {
            return VerticalAlignment.BOTTOM;
        } else {
            throw new RuntimeException("Unexpected vertical alignment style class. Must be VerticalAlignment or Short (deprecated).");
        }
    }

    private static boolean getBoolean(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    private static void put(Map<String, Object> map, String str, Object obj) {
        map.put(str, obj);
    }

    public static Cell translateUnicodeValues(Cell cell) {
        String string = cell.getRichStringCellValue().getString();
        String lowerCase = string.toLowerCase(Locale.ROOT);
        boolean z = false;
        for (UnicodeMapping unicodeMapping : unicodeMappings) {
            String str = unicodeMapping.entityName;
            if (lowerCase.contains(str)) {
                string = string.replaceAll(str, unicodeMapping.resolvedValue);
                z = true;
            }
        }
        if (z) {
            cell.setCellValue(cell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(string));
        }
        return cell;
    }

    private static UnicodeMapping um(String str, String str2) {
        return new UnicodeMapping(str, str2);
    }
}
