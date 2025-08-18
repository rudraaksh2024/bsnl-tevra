package org.apache.poi.ss.util;

import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedString;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;

public class SheetUtil {
    private static final char defaultChar = '0';
    private static final FormulaEvaluator dummyEvaluator = new FormulaEvaluator() {
        public void clearAllCachedResultValues() {
        }

        public CellValue evaluate(Cell cell) {
            return null;
        }

        public void evaluateAll() {
        }

        public Cell evaluateInCell(Cell cell) {
            return null;
        }

        public void notifyDeleteCell(Cell cell) {
        }

        public void notifySetFormula(Cell cell) {
        }

        public void notifyUpdateCell(Cell cell) {
        }

        public void setDebugEvaluationOutputForNextEval(boolean z) {
        }

        public void setIgnoreMissingWorkbooks(boolean z) {
        }

        public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> map) {
        }

        public CellType evaluateFormulaCell(Cell cell) {
            return cell.getCachedFormulaResultType();
        }
    };
    private static final double fontHeightMultiple = 2.0d;
    private static final FontRenderContext fontRenderContext = new FontRenderContext((AffineTransform) null, true, true);

    public static double getCellWidth(Cell cell, int i, DataFormatter dataFormatter, boolean z) {
        return getCellWidth(cell, i, dataFormatter, z, cell.getSheet().getMergedRegions());
    }

    public static double getCellWidth(Cell cell, int i, DataFormatter dataFormatter, boolean z, List<CellRangeAddress> list) {
        String str;
        Workbook workbook = cell.getSheet().getWorkbook();
        Row row = cell.getRow();
        int columnIndex = cell.getColumnIndex();
        int i2 = 1;
        for (CellRangeAddress next : list) {
            if (next.isInRange(row.getRowNum(), columnIndex)) {
                if (!z) {
                    return -1.0d;
                }
                cell = row.getCell(next.getFirstColumn());
                i2 = (next.getLastColumn() + 1) - next.getFirstColumn();
            }
        }
        CellStyle cellStyle = cell.getCellStyle();
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = cell.getCachedFormulaResultType();
        }
        Font fontAt = workbook.getFontAt(cellStyle.getFontIndex());
        double d = -1.0d;
        if (cellType == CellType.STRING) {
            RichTextString richStringCellValue = cell.getRichStringCellValue();
            if (richStringCellValue == null || richStringCellValue.getString() == null) {
                return -1.0d;
            }
            String[] split = richStringCellValue.getString().split("\\n");
            int length = split.length;
            for (int i3 = 0; i3 < length; i3++) {
                String str2 = split[i3] + defaultChar;
                AttributedString attributedString = new AttributedString(str2);
                copyAttributes(fontAt, attributedString, 0, str2.length());
                d = getCellWidth(i, i2, cellStyle, d, attributedString);
            }
            return d;
        }
        if (cellType == CellType.NUMERIC) {
            try {
                str = dataFormatter.formatCellValue(cell, dummyEvaluator);
            } catch (Exception unused) {
                str = String.valueOf(cell.getNumericCellValue());
            }
        } else {
            str = cellType == CellType.BOOLEAN ? String.valueOf(cell.getBooleanCellValue()).toUpperCase(Locale.ROOT) : null;
        }
        if (str == null) {
            return -1.0d;
        }
        String str3 = str + defaultChar;
        AttributedString attributedString2 = new AttributedString(str3);
        copyAttributes(fontAt, attributedString2, 0, str3.length());
        return getCellWidth(i, i2, cellStyle, -1.0d, attributedString2);
    }

    private static double getCellWidth(int i, int i2, CellStyle cellStyle, double d, AttributedString attributedString) {
        Rectangle rectangle;
        TextLayout textLayout = new TextLayout(attributedString.getIterator(), fontRenderContext);
        if (cellStyle.getRotation() != 0) {
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.concatenate(AffineTransform.getRotateInstance(((((double) cellStyle.getRotation()) * fontHeightMultiple) * 3.141592653589793d) / 360.0d));
            affineTransform.concatenate(AffineTransform.getScaleInstance(1.0d, fontHeightMultiple));
            rectangle = textLayout.getOutline(affineTransform).getBounds();
        } else {
            rectangle = textLayout.getBounds();
        }
        return Math.max(d, (((rectangle.getX() + rectangle.getWidth()) / ((double) i2)) / ((double) i)) + ((double) cellStyle.getIndention()));
    }

    public static double getColumnWidth(Sheet sheet, int i, boolean z) {
        return getColumnWidth(sheet, i, z, sheet.getFirstRowNum(), sheet.getLastRowNum());
    }

    public static double getColumnWidth(Sheet sheet, int i, boolean z, int i2, int i3) {
        DataFormatter dataFormatter = new DataFormatter();
        int defaultCharWidth = getDefaultCharWidth(sheet.getWorkbook());
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        double d = -1.0d;
        while (i2 <= i3) {
            Row row = sheet.getRow(i2);
            if (row != null) {
                d = Math.max(d, getColumnWidthForRow(row, i, defaultCharWidth, dataFormatter, z, mergedRegions));
            }
            i2++;
        }
        return d;
    }

    @Internal
    public static int getDefaultCharWidth(Workbook workbook) {
        Font fontAt = workbook.getFontAt(0);
        AttributedString attributedString = new AttributedString(String.valueOf(defaultChar));
        copyAttributes(fontAt, attributedString, 0, 1);
        return (int) new TextLayout(attributedString.getIterator(), fontRenderContext).getAdvance();
    }

    private static double getColumnWidthForRow(Row row, int i, int i2, DataFormatter dataFormatter, boolean z, List<CellRangeAddress> list) {
        Cell cell;
        if (row == null || (cell = row.getCell(i)) == null) {
            return -1.0d;
        }
        return getCellWidth(cell, i2, dataFormatter, z, list);
    }

    public static boolean canComputeColumnWidth(Font font) {
        AttributedString attributedString = new AttributedString("1w");
        copyAttributes(font, attributedString, 0, 2);
        if (new TextLayout(attributedString.getIterator(), fontRenderContext).getBounds().getWidth() > 0.0d) {
            return true;
        }
        return false;
    }

    private static void copyAttributes(Font font, AttributedString attributedString, int i, int i2) {
        attributedString.addAttribute(TextAttribute.FAMILY, font.getFontName(), i, i2);
        attributedString.addAttribute(TextAttribute.SIZE, Float.valueOf((float) font.getFontHeightInPoints()));
        if (font.getBold()) {
            attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, i, i2);
        }
        if (font.getItalic()) {
            attributedString.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, i, i2);
        }
        if (font.getUnderline() == 1) {
            attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, i, i2);
        }
    }

    public static Cell getCell(Sheet sheet, int i, int i2) {
        Row row = sheet.getRow(i);
        if (row != null) {
            return row.getCell(i2);
        }
        return null;
    }

    public static Cell getCellWithMerges(Sheet sheet, int i, int i2) {
        Row row;
        Cell cell = getCell(sheet, i, i2);
        if (cell != null) {
            return cell;
        }
        for (CellRangeAddress next : sheet.getMergedRegions()) {
            if (next.isInRange(i, i2) && (row = sheet.getRow(next.getFirstRow())) != null) {
                return row.getCell(next.getFirstColumn());
            }
        }
        return null;
    }
}
