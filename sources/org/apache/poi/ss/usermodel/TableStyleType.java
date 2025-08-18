package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;

public enum TableStyleType {
    wholeTable {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), table.getStartColIndex(), table.getEndColIndex());
        }
    },
    pageFieldLabels,
    pageFieldValues,
    firstColumnStripe {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            int i;
            TableStyleInfo style = table.getStyle();
            if (!style.isShowColumnStripes()) {
                return null;
            }
            DifferentialStyleProvider style2 = style.getStyle().getStyle(firstColumnStripe);
            DifferentialStyleProvider style3 = style.getStyle().getStyle(secondColumnStripe);
            int i2 = 1;
            if (style2 == null) {
                i = 1;
            } else {
                i = Math.max(1, style2.getStripeSize());
            }
            if (style3 != null) {
                i2 = Math.max(1, style3.getStripeSize());
            }
            int startColIndex = table.getStartColIndex();
            int i3 = startColIndex + i;
            short col = cellReference.getCol();
            while (startColIndex <= col) {
                int i4 = i3 - 1;
                if (col <= i4) {
                    return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), startColIndex, i4);
                }
                startColIndex = i3 + i2;
                i3 = startColIndex + i;
            }
            return null;
        }
    },
    secondColumnStripe {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            int i;
            int i2;
            int i3;
            TableStyleInfo style = table.getStyle();
            if (!style.isShowColumnStripes()) {
                return null;
            }
            DifferentialStyleProvider style2 = style.getStyle().getStyle(firstColumnStripe);
            DifferentialStyleProvider style3 = style.getStyle().getStyle(secondColumnStripe);
            if (style2 == null) {
                i = 1;
            } else {
                i = Math.max(1, style2.getStripeSize());
            }
            if (style3 == null) {
                i2 = 1;
            } else {
                i2 = Math.max(1, style3.getStripeSize());
            }
            int startColIndex = table.getStartColIndex();
            int i4 = startColIndex + i;
            short col = cellReference.getCol();
            while (startColIndex <= col) {
                if (col >= i4 && col <= (i3 = (i4 + i2) - 1)) {
                    return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), i4, i3);
                }
                startColIndex = i4 + i2;
                i4 = startColIndex + i;
            }
            return null;
        }
    },
    firstRowStripe {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            int i;
            TableStyleInfo style = table.getStyle();
            if (!style.isShowRowStripes()) {
                return null;
            }
            DifferentialStyleProvider style2 = style.getStyle().getStyle(firstRowStripe);
            DifferentialStyleProvider style3 = style.getStyle().getStyle(secondRowStripe);
            int i2 = 1;
            if (style2 == null) {
                i = 1;
            } else {
                i = Math.max(1, style2.getStripeSize());
            }
            if (style3 != null) {
                i2 = Math.max(1, style3.getStripeSize());
            }
            int startRowIndex = table.getStartRowIndex() + table.getHeaderRowCount();
            int i3 = startRowIndex + i;
            int row = cellReference.getRow();
            while (startRowIndex <= row) {
                int i4 = i3 - 1;
                if (row <= i4) {
                    return new CellRangeAddress(startRowIndex, i4, table.getStartColIndex(), table.getEndColIndex());
                }
                startRowIndex = i3 + i2;
                i3 = startRowIndex + i;
            }
            return null;
        }
    },
    secondRowStripe {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            int i;
            int i2;
            int i3;
            TableStyleInfo style = table.getStyle();
            if (!style.isShowRowStripes()) {
                return null;
            }
            DifferentialStyleProvider style2 = style.getStyle().getStyle(firstRowStripe);
            DifferentialStyleProvider style3 = style.getStyle().getStyle(secondRowStripe);
            if (style2 == null) {
                i = 1;
            } else {
                i = Math.max(1, style2.getStripeSize());
            }
            if (style3 == null) {
                i2 = 1;
            } else {
                i2 = Math.max(1, style3.getStripeSize());
            }
            int startRowIndex = table.getStartRowIndex() + table.getHeaderRowCount();
            int i4 = startRowIndex + i;
            int row = cellReference.getRow();
            while (startRowIndex <= row) {
                if (row >= i4 && row <= (i3 = (i4 + i2) - 1)) {
                    return new CellRangeAddress(i4, i3, table.getStartColIndex(), table.getEndColIndex());
                }
                startRowIndex = i4 + i2;
                i4 = startRowIndex + i;
            }
            return null;
        }
    },
    lastColumn {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (!table.getStyle().isShowLastColumn()) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), table.getEndColIndex(), table.getEndColIndex());
        }
    },
    firstColumn {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (!table.getStyle().isShowFirstColumn()) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), table.getStartColIndex(), table.getStartColIndex());
        }
    },
    headerRow {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (table.getHeaderRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), (table.getStartRowIndex() + table.getHeaderRowCount()) - 1, table.getStartColIndex(), table.getEndColIndex());
        }
    },
    totalRow {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (table.getTotalsRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress((table.getEndRowIndex() - table.getTotalsRowCount()) + 1, table.getEndRowIndex(), table.getStartColIndex(), table.getEndColIndex());
        }
    },
    firstHeaderCell {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (table.getHeaderRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getStartRowIndex(), table.getStartColIndex(), table.getStartColIndex());
        }
    },
    lastHeaderCell {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (table.getHeaderRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getStartRowIndex(), table.getEndColIndex(), table.getEndColIndex());
        }
    },
    firstTotalCell {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (table.getTotalsRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress((table.getEndRowIndex() - table.getTotalsRowCount()) + 1, table.getEndRowIndex(), table.getStartColIndex(), table.getStartColIndex());
        }
    },
    lastTotalCell {
        public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
            if (table.getTotalsRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress((table.getEndRowIndex() - table.getTotalsRowCount()) + 1, table.getEndRowIndex(), table.getEndColIndex(), table.getEndColIndex());
        }
    },
    firstSubtotalColumn,
    secondSubtotalColumn,
    thirdSubtotalColumn,
    blankRow,
    firstSubtotalRow,
    secondSubtotalRow,
    thirdSubtotalRow,
    firstColumnSubheading,
    secondColumnSubheading,
    thirdColumnSubheading,
    firstRowSubheading,
    secondRowSubheading,
    thirdRowSubheading;

    public CellRangeAddressBase getRange(Table table, CellReference cellReference) {
        return null;
    }

    public CellRangeAddressBase appliesTo(Table table, Cell cell) {
        if (cell == null) {
            return null;
        }
        return appliesTo(table, new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), true, true));
    }

    public CellRangeAddressBase appliesTo(Table table, CellReference cellReference) {
        CellRangeAddressBase range;
        if (table == null || cellReference == null || !cellReference.getSheetName().equals(table.getSheetName()) || !table.contains(cellReference) || (range = getRange(table, cellReference)) == null || !range.isInRange(cellReference.getRow(), cellReference.getCol())) {
            return null;
        }
        return range;
    }

    public final CellRangeAddressBase getRange(Table table, Cell cell) {
        if (cell == null) {
            return null;
        }
        return getRange(table, new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), true, true));
    }
}
