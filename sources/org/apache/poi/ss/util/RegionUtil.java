package org.apache.poi.ss.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public final class RegionUtil {
    private RegionUtil() {
    }

    private static final class CellPropertySetter {
        private final String _propertyName;
        private final Object _propertyValue;

        public CellPropertySetter(String str, int i) {
            this._propertyName = str;
            this._propertyValue = Integer.valueOf(i);
        }

        public CellPropertySetter(String str, BorderStyle borderStyle) {
            this._propertyName = str;
            this._propertyValue = borderStyle;
        }

        public void setProperty(Row row, int i) {
            CellUtil.setCellStyleProperty(CellUtil.getCell(row, i), this._propertyName, this._propertyValue);
        }
    }

    public static void setBorderLeft(BorderStyle borderStyle, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastRow = cellRangeAddress.getLastRow();
        int firstColumn = cellRangeAddress.getFirstColumn();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.BORDER_LEFT, borderStyle);
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            cellPropertySetter.setProperty(CellUtil.getRow(firstRow, sheet), firstColumn);
        }
    }

    public static void setLeftBorderColor(int i, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastRow = cellRangeAddress.getLastRow();
        int firstColumn = cellRangeAddress.getFirstColumn();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.LEFT_BORDER_COLOR, i);
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            cellPropertySetter.setProperty(CellUtil.getRow(firstRow, sheet), firstColumn);
        }
    }

    public static void setBorderRight(BorderStyle borderStyle, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.BORDER_RIGHT, borderStyle);
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            cellPropertySetter.setProperty(CellUtil.getRow(firstRow, sheet), lastColumn);
        }
    }

    public static void setRightBorderColor(int i, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.RIGHT_BORDER_COLOR, i);
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            cellPropertySetter.setProperty(CellUtil.getRow(firstRow, sheet), lastColumn);
        }
    }

    public static void setBorderBottom(BorderStyle borderStyle, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastColumn = cellRangeAddress.getLastColumn();
        int lastRow = cellRangeAddress.getLastRow();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.BORDER_BOTTOM, borderStyle);
        Row row = CellUtil.getRow(lastRow, sheet);
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            cellPropertySetter.setProperty(row, firstColumn);
        }
    }

    public static void setBottomBorderColor(int i, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastColumn = cellRangeAddress.getLastColumn();
        int lastRow = cellRangeAddress.getLastRow();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.BOTTOM_BORDER_COLOR, i);
        Row row = CellUtil.getRow(lastRow, sheet);
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            cellPropertySetter.setProperty(row, firstColumn);
        }
    }

    public static void setBorderTop(BorderStyle borderStyle, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastColumn = cellRangeAddress.getLastColumn();
        int firstRow = cellRangeAddress.getFirstRow();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.BORDER_TOP, borderStyle);
        Row row = CellUtil.getRow(firstRow, sheet);
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            cellPropertySetter.setProperty(row, firstColumn);
        }
    }

    public static void setTopBorderColor(int i, CellRangeAddress cellRangeAddress, Sheet sheet) {
        int lastColumn = cellRangeAddress.getLastColumn();
        int firstRow = cellRangeAddress.getFirstRow();
        CellPropertySetter cellPropertySetter = new CellPropertySetter(CellUtil.TOP_BORDER_COLOR, i);
        Row row = CellUtil.getRow(firstRow, sheet);
        for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= lastColumn; firstColumn++) {
            cellPropertySetter.setProperty(row, firstColumn);
        }
    }
}
