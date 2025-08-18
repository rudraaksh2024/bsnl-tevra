package org.apache.poi.xssf.streaming;

import androidx.core.view.MotionEventCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.usermodel.OoxmlSheetExtensions;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFVMLDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class SXSSFSheet implements Sheet, OoxmlSheetExtensions {
    protected final AutoSizeColumnTracker _autoSizeColumnTracker;
    private int _randomAccessWindowSize = 100;
    private final TreeMap<Integer, SXSSFRow> _rows = new TreeMap<>();
    final XSSFSheet _sh;
    protected final SXSSFWorkbook _workbook;
    protected SheetDataWriter _writer;
    private boolean allFlushed;
    private int lastFlushedRowNumber = -1;
    private int outlineLevelRow;

    protected SXSSFSheet(SXSSFWorkbook sXSSFWorkbook, XSSFSheet xSSFSheet, int i) {
        this._workbook = sXSSFWorkbook;
        this._sh = xSSFSheet;
        setRandomAccessWindowSize(i);
        this._autoSizeColumnTracker = new AutoSizeColumnTracker(this);
    }

    public SXSSFSheet(SXSSFWorkbook sXSSFWorkbook, XSSFSheet xSSFSheet) throws IOException {
        this._workbook = sXSSFWorkbook;
        this._sh = xSSFSheet;
        this._writer = sXSSFWorkbook.createSheetDataWriter();
        setRandomAccessWindowSize(sXSSFWorkbook.getRandomAccessWindowSize());
        this._autoSizeColumnTracker = new AutoSizeColumnTracker(this);
    }

    /* access modifiers changed from: package-private */
    @Internal
    public SheetDataWriter getSheetDataWriter() {
        return this._writer;
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        flushRows(0);
        this._writer.close();
        return this._writer.getWorksheetXMLInputStream();
    }

    public SXSSFRow createRow(int i) {
        int i2;
        int lastRowIndex = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        if (i < 0 || i > lastRowIndex) {
            throw new IllegalArgumentException("Invalid row number (" + i + ") outside allowable range (0.." + lastRowIndex + ")");
        }
        SheetDataWriter sheetDataWriter = this._writer;
        if (sheetDataWriter != null && i <= sheetDataWriter.getLastFlushedRow()) {
            throw new IllegalArgumentException("Attempting to write a row[" + i + "] in the range [0," + this._writer.getLastFlushedRow() + "] that is already written to disk.");
        } else if (this._sh.getPhysicalNumberOfRows() <= 0 || i > this._sh.getLastRowNum()) {
            SXSSFRow sXSSFRow = new SXSSFRow(this);
            sXSSFRow.setRowNumWithoutUpdatingSheet(i);
            this._rows.put(Integer.valueOf(i), sXSSFRow);
            this.allFlushed = false;
            if (this._randomAccessWindowSize >= 0 && this._rows.size() > (i2 = this._randomAccessWindowSize)) {
                try {
                    flushRows(i2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return sXSSFRow;
        } else {
            throw new IllegalArgumentException("Attempting to write a row[" + i + "] in the range [0," + this._sh.getLastRowNum() + "] that is already written to disk.");
        }
    }

    public void removeRow(Row row) {
        if (row.getSheet() == this) {
            Iterator<Map.Entry<Integer, SXSSFRow>> it = this._rows.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getValue() == row) {
                    it.remove();
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException("Specified row does not belong to this sheet");
    }

    public SXSSFRow getRow(int i) {
        return this._rows.get(Integer.valueOf(i));
    }

    public int getPhysicalNumberOfRows() {
        return this._rows.size() + this._writer.getNumberOfFlushedRows();
    }

    public int getFirstRowNum() {
        if (this._writer.getNumberOfFlushedRows() > 0) {
            return this._writer.getLowestIndexOfFlushedRows();
        }
        if (this._rows.isEmpty()) {
            return -1;
        }
        return this._rows.firstKey().intValue();
    }

    public int getLastRowNum() {
        if (this._rows.isEmpty()) {
            return -1;
        }
        return this._rows.lastKey().intValue();
    }

    public void setColumnHidden(int i, boolean z) {
        this._sh.setColumnHidden(i, z);
    }

    public boolean isColumnHidden(int i) {
        return this._sh.isColumnHidden(i);
    }

    public void setColumnWidth(int i, int i2) {
        this._sh.setColumnWidth(i, i2);
    }

    public int getColumnWidth(int i) {
        return this._sh.getColumnWidth(i);
    }

    public float getColumnWidthInPixels(int i) {
        return this._sh.getColumnWidthInPixels(i);
    }

    public void setDefaultColumnWidth(int i) {
        this._sh.setDefaultColumnWidth(i);
    }

    public int getDefaultColumnWidth() {
        return this._sh.getDefaultColumnWidth();
    }

    public short getDefaultRowHeight() {
        return this._sh.getDefaultRowHeight();
    }

    public float getDefaultRowHeightInPoints() {
        return this._sh.getDefaultRowHeightInPoints();
    }

    public void setDefaultRowHeight(short s) {
        this._sh.setDefaultRowHeight(s);
    }

    public void setDefaultRowHeightInPoints(float f) {
        this._sh.setDefaultRowHeightInPoints(f);
    }

    public XSSFVMLDrawing getVMLDrawing(boolean z) {
        XSSFSheet xSSFSheet = getWorkbook().getXSSFSheet(this);
        if (xSSFSheet == null) {
            return null;
        }
        return xSSFSheet.getVMLDrawing(z);
    }

    public CellStyle getColumnStyle(int i) {
        return this._sh.getColumnStyle(i);
    }

    public int addMergedRegion(CellRangeAddress cellRangeAddress) {
        return this._sh.addMergedRegion(cellRangeAddress);
    }

    public int addMergedRegionUnsafe(CellRangeAddress cellRangeAddress) {
        return this._sh.addMergedRegionUnsafe(cellRangeAddress);
    }

    public void validateMergedRegions() {
        this._sh.validateMergedRegions();
    }

    public void setVerticallyCenter(boolean z) {
        this._sh.setVerticallyCenter(z);
    }

    public void setHorizontallyCenter(boolean z) {
        this._sh.setHorizontallyCenter(z);
    }

    public boolean getHorizontallyCenter() {
        return this._sh.getHorizontallyCenter();
    }

    public boolean getVerticallyCenter() {
        return this._sh.getVerticallyCenter();
    }

    public void removeMergedRegion(int i) {
        this._sh.removeMergedRegion(i);
    }

    public void removeMergedRegions(Collection<Integer> collection) {
        this._sh.removeMergedRegions(collection);
    }

    public int getNumMergedRegions() {
        return this._sh.getNumMergedRegions();
    }

    public CellRangeAddress getMergedRegion(int i) {
        return this._sh.getMergedRegion(i);
    }

    public List<CellRangeAddress> getMergedRegions() {
        return this._sh.getMergedRegions();
    }

    public Iterator<Row> rowIterator() {
        return this._rows.values().iterator();
    }

    public Spliterator<Row> spliterator() {
        return this._rows.values().spliterator();
    }

    public void setAutobreaks(boolean z) {
        this._sh.setAutobreaks(z);
    }

    public void setDisplayGuts(boolean z) {
        this._sh.setDisplayGuts(z);
    }

    public void setDisplayZeros(boolean z) {
        this._sh.setDisplayZeros(z);
    }

    public boolean isDisplayZeros() {
        return this._sh.isDisplayZeros();
    }

    public void setRightToLeft(boolean z) {
        this._sh.setRightToLeft(z);
    }

    public boolean isRightToLeft() {
        return this._sh.isRightToLeft();
    }

    public void setFitToPage(boolean z) {
        this._sh.setFitToPage(z);
    }

    public void setRowSumsBelow(boolean z) {
        this._sh.setRowSumsBelow(z);
    }

    public void setRowSumsRight(boolean z) {
        this._sh.setRowSumsRight(z);
    }

    public boolean getAutobreaks() {
        return this._sh.getAutobreaks();
    }

    public boolean getDisplayGuts() {
        return this._sh.getDisplayGuts();
    }

    public boolean getFitToPage() {
        return this._sh.getFitToPage();
    }

    public boolean getRowSumsBelow() {
        return this._sh.getRowSumsBelow();
    }

    public boolean getRowSumsRight() {
        return this._sh.getRowSumsRight();
    }

    public boolean isPrintGridlines() {
        return this._sh.isPrintGridlines();
    }

    public void setPrintGridlines(boolean z) {
        this._sh.setPrintGridlines(z);
    }

    public boolean isPrintRowAndColumnHeadings() {
        return this._sh.isPrintRowAndColumnHeadings();
    }

    public void setPrintRowAndColumnHeadings(boolean z) {
        this._sh.setPrintRowAndColumnHeadings(z);
    }

    public PrintSetup getPrintSetup() {
        return this._sh.getPrintSetup();
    }

    public Header getHeader() {
        return this._sh.getHeader();
    }

    public Footer getFooter() {
        return this._sh.getFooter();
    }

    public void setSelected(boolean z) {
        this._sh.setSelected(z);
    }

    public double getMargin(short s) {
        return this._sh.getMargin(s);
    }

    public void setMargin(short s, double d) {
        this._sh.setMargin(s, d);
    }

    public boolean getProtect() {
        return this._sh.getProtect();
    }

    public void protectSheet(String str) {
        this._sh.protectSheet(str);
    }

    public boolean getScenarioProtect() {
        return this._sh.getScenarioProtect();
    }

    public void setZoom(int i) {
        this._sh.setZoom(i);
    }

    public short getTopRow() {
        return this._sh.getTopRow();
    }

    public short getLeftCol() {
        return this._sh.getLeftCol();
    }

    public void showInPane(int i, int i2) {
        this._sh.showInPane(i, i2);
    }

    public void setForceFormulaRecalculation(boolean z) {
        this._sh.setForceFormulaRecalculation(z);
    }

    public boolean getForceFormulaRecalculation() {
        return this._sh.getForceFormulaRecalculation();
    }

    @NotImplemented
    public void shiftRows(int i, int i2, int i3) {
        throw new RuntimeException("Not Implemented");
    }

    @NotImplemented
    public void shiftRows(int i, int i2, int i3, boolean z, boolean z2) {
        throw new RuntimeException("Not Implemented");
    }

    public void createFreezePane(int i, int i2, int i3, int i4) {
        this._sh.createFreezePane(i, i2, i3, i4);
    }

    public void createFreezePane(int i, int i2) {
        this._sh.createFreezePane(i, i2);
    }

    public void createSplitPane(int i, int i2, int i3, int i4, int i5) {
        this._sh.createSplitPane(i, i2, i3, i4, i5);
    }

    public PaneInformation getPaneInformation() {
        return this._sh.getPaneInformation();
    }

    public void setDisplayGridlines(boolean z) {
        this._sh.setDisplayGridlines(z);
    }

    public boolean isDisplayGridlines() {
        return this._sh.isDisplayGridlines();
    }

    public void setDisplayFormulas(boolean z) {
        this._sh.setDisplayFormulas(z);
    }

    public boolean isDisplayFormulas() {
        return this._sh.isDisplayFormulas();
    }

    public void setDisplayRowColHeadings(boolean z) {
        this._sh.setDisplayRowColHeadings(z);
    }

    public boolean isDisplayRowColHeadings() {
        return this._sh.isDisplayRowColHeadings();
    }

    public void setRowBreak(int i) {
        this._sh.setRowBreak(i);
    }

    public boolean isRowBroken(int i) {
        return this._sh.isRowBroken(i);
    }

    public void removeRowBreak(int i) {
        this._sh.removeRowBreak(i);
    }

    public int[] getRowBreaks() {
        return this._sh.getRowBreaks();
    }

    public int[] getColumnBreaks() {
        return this._sh.getColumnBreaks();
    }

    public void setColumnBreak(int i) {
        this._sh.setColumnBreak(i);
    }

    public boolean isColumnBroken(int i) {
        return this._sh.isColumnBroken(i);
    }

    public void removeColumnBreak(int i) {
        this._sh.removeColumnBreak(i);
    }

    public void setColumnGroupCollapsed(int i, boolean z) {
        this._sh.setColumnGroupCollapsed(i, z);
    }

    public void groupColumn(int i, int i2) {
        this._sh.groupColumn(i, i2);
    }

    public void ungroupColumn(int i, int i2) {
        this._sh.ungroupColumn(i, i2);
    }

    public void groupRow(int i, int i2) {
        for (SXSSFRow next : this._rows.subMap(Integer.valueOf(i), Integer.valueOf(i2 + 1)).values()) {
            int outlineLevel = next.getOutlineLevel() + 1;
            next.setOutlineLevel(outlineLevel);
            if (outlineLevel > this.outlineLevelRow) {
                this.outlineLevelRow = outlineLevel;
            }
        }
        setWorksheetOutlineLevelRow();
    }

    public void setRowOutlineLevel(int i, int i2) {
        this._rows.get(Integer.valueOf(i)).setOutlineLevel(i2);
        if (i2 > 0 && i2 > this.outlineLevelRow) {
            this.outlineLevelRow = i2;
            setWorksheetOutlineLevelRow();
        }
    }

    private void setWorksheetOutlineLevelRow() {
        CTSheetFormatPr cTSheetFormatPr;
        CTWorksheet cTWorksheet = this._sh.getCTWorksheet();
        if (cTWorksheet.isSetSheetFormatPr()) {
            cTSheetFormatPr = cTWorksheet.getSheetFormatPr();
        } else {
            cTSheetFormatPr = cTWorksheet.addNewSheetFormatPr();
        }
        int i = this.outlineLevelRow;
        if (i > 0) {
            cTSheetFormatPr.setOutlineLevelRow((short) i);
        }
    }

    public void ungroupRow(int i, int i2) {
        this._sh.ungroupRow(i, i2);
    }

    public void setRowGroupCollapsed(int i, boolean z) {
        if (z) {
            collapseRow(i);
            return;
        }
        throw new RuntimeException("Unable to expand row: Not Implemented");
    }

    private void collapseRow(int i) {
        SXSSFRow row = getRow(i);
        if (row != null) {
            int writeHidden = writeHidden(row, findStartOfRowOutlineGroup(i));
            SXSSFRow row2 = getRow(writeHidden);
            if (row2 != null) {
                row2.setCollapsed(true);
            } else {
                createRow(writeHidden).setCollapsed(true);
            }
        } else {
            throw new IllegalArgumentException("Invalid row number(" + i + "). Row does not exist.");
        }
    }

    private int findStartOfRowOutlineGroup(int i) {
        int outlineLevel = getRow(i).getOutlineLevel();
        if (outlineLevel != 0) {
            while (getRow(i) != null) {
                if (getRow(i).getOutlineLevel() < outlineLevel) {
                    return i + 1;
                }
                i--;
            }
            return i + 1;
        }
        throw new IllegalArgumentException("Outline level is zero for the row (" + i + ").");
    }

    private int writeHidden(SXSSFRow sXSSFRow, int i) {
        int outlineLevel = sXSSFRow.getOutlineLevel();
        SXSSFRow row = getRow(i);
        while (row != null && row.getOutlineLevel() >= outlineLevel) {
            row.setHidden(true);
            i++;
            row = getRow(i);
        }
        return i;
    }

    public void setDefaultColumnStyle(int i, CellStyle cellStyle) {
        this._sh.setDefaultColumnStyle(i, cellStyle);
    }

    public void trackColumnForAutoSizing(int i) {
        this._autoSizeColumnTracker.trackColumn(i);
    }

    public void trackColumnsForAutoSizing(Collection<Integer> collection) {
        this._autoSizeColumnTracker.trackColumns(collection);
    }

    public void trackAllColumnsForAutoSizing() {
        this._autoSizeColumnTracker.trackAllColumns();
    }

    public boolean untrackColumnForAutoSizing(int i) {
        return this._autoSizeColumnTracker.untrackColumn(i);
    }

    public boolean untrackColumnsForAutoSizing(Collection<Integer> collection) {
        return this._autoSizeColumnTracker.untrackColumns(collection);
    }

    public void untrackAllColumnsForAutoSizing() {
        this._autoSizeColumnTracker.untrackAllColumns();
    }

    public boolean isColumnTrackedForAutoSizing(int i) {
        return this._autoSizeColumnTracker.isColumnTracked(i);
    }

    public Set<Integer> getTrackedColumnsForAutoSizing() {
        return this._autoSizeColumnTracker.getTrackedColumns();
    }

    public void autoSizeColumn(int i) {
        autoSizeColumn(i, false);
    }

    public void autoSizeColumn(int i, boolean z) {
        try {
            int max = Math.max(this._autoSizeColumnTracker.getBestFitColumnWidth(i, z), (int) (SheetUtil.getColumnWidth(this, i, z) * 256.0d));
            if (max > 0) {
                setColumnWidth(i, Math.min(max, MotionEventCompat.ACTION_POINTER_INDEX_MASK));
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Could not auto-size column. Make sure the column was tracked prior to auto-sizing the column.", e);
        }
    }

    public XSSFComment getCellComment(CellAddress cellAddress) {
        return this._sh.getCellComment(cellAddress);
    }

    public Map<CellAddress, XSSFComment> getCellComments() {
        return this._sh.getCellComments();
    }

    public XSSFHyperlink getHyperlink(int i, int i2) {
        return this._sh.getHyperlink(i, i2);
    }

    public XSSFHyperlink getHyperlink(CellAddress cellAddress) {
        return this._sh.getHyperlink(cellAddress);
    }

    public List<XSSFHyperlink> getHyperlinkList() {
        return this._sh.getHyperlinkList();
    }

    public XSSFDrawing getDrawingPatriarch() {
        return this._sh.getDrawingPatriarch();
    }

    public SXSSFDrawing createDrawingPatriarch() {
        return new SXSSFDrawing(getWorkbook(), this._sh.createDrawingPatriarch());
    }

    public SXSSFWorkbook getWorkbook() {
        return this._workbook;
    }

    public String getSheetName() {
        return this._sh.getSheetName();
    }

    public boolean isSelected() {
        return this._sh.isSelected();
    }

    public CellRange<? extends Cell> setArrayFormula(String str, CellRangeAddress cellRangeAddress) {
        throw new RuntimeException("Not Implemented");
    }

    public CellRange<? extends Cell> removeArrayFormula(Cell cell) {
        throw new RuntimeException("Not Implemented");
    }

    public DataValidationHelper getDataValidationHelper() {
        return this._sh.getDataValidationHelper();
    }

    public List<XSSFDataValidation> getDataValidations() {
        return this._sh.getDataValidations();
    }

    public void addValidationData(DataValidation dataValidation) {
        this._sh.addValidationData(dataValidation);
    }

    public AutoFilter setAutoFilter(CellRangeAddress cellRangeAddress) {
        return this._sh.setAutoFilter(cellRangeAddress);
    }

    public SheetConditionalFormatting getSheetConditionalFormatting() {
        return this._sh.getSheetConditionalFormatting();
    }

    public CellRangeAddress getRepeatingRows() {
        return this._sh.getRepeatingRows();
    }

    public CellRangeAddress getRepeatingColumns() {
        return this._sh.getRepeatingColumns();
    }

    public void setRepeatingRows(CellRangeAddress cellRangeAddress) {
        this._sh.setRepeatingRows(cellRangeAddress);
    }

    public void setRepeatingColumns(CellRangeAddress cellRangeAddress) {
        this._sh.setRepeatingColumns(cellRangeAddress);
    }

    public void setRandomAccessWindowSize(int i) {
        if (i == 0 || i < -1) {
            throw new IllegalArgumentException("RandomAccessWindowSize must be either -1 or a positive integer");
        }
        this._randomAccessWindowSize = i;
    }

    public boolean areAllRowsFlushed() {
        return this.allFlushed;
    }

    public int getLastFlushedRowNum() {
        return this.lastFlushedRowNumber;
    }

    public void flushRows(int i) throws IOException {
        while (this._rows.size() > i) {
            flushOneRow();
        }
        if (i == 0) {
            this.allFlushed = true;
        }
    }

    public void flushRows() throws IOException {
        flushRows(0);
    }

    public void flushBufferedData() throws IOException {
        this._writer.flush();
    }

    private void flushOneRow() throws IOException {
        Integer firstKey = this._rows.firstKey();
        if (firstKey != null) {
            int intValue = firstKey.intValue();
            SXSSFRow sXSSFRow = this._rows.get(firstKey);
            this._autoSizeColumnTracker.updateColumnWidths(sXSSFRow);
            SheetDataWriter sheetDataWriter = this._writer;
            if (sheetDataWriter != null) {
                sheetDataWriter.writeRow(intValue, sXSSFRow);
            }
            this._rows.remove(firstKey);
            this.lastFlushedRowNumber = intValue;
        }
    }

    public void changeRowNum(SXSSFRow sXSSFRow, int i) {
        removeRow(sXSSFRow);
        sXSSFRow.setRowNumWithoutUpdatingSheet(i);
        this._rows.put(Integer.valueOf(i), sXSSFRow);
    }

    public int getRowNum(SXSSFRow sXSSFRow) {
        return sXSSFRow.getRowNum();
    }

    /* access modifiers changed from: package-private */
    public boolean dispose() throws IOException {
        try {
            if (!this.allFlushed) {
                flushRows();
            }
        } finally {
            SheetDataWriter sheetDataWriter = this._writer;
            if (sheetDataWriter != null) {
                boolean dispose = sheetDataWriter.dispose();
            }
        }
    }

    public int getColumnOutlineLevel(int i) {
        return this._sh.getColumnOutlineLevel(i);
    }

    public CellAddress getActiveCell() {
        return this._sh.getActiveCell();
    }

    public void setActiveCell(CellAddress cellAddress) {
        this._sh.setActiveCell(cellAddress);
    }

    public XSSFColor getTabColor() {
        return this._sh.getTabColor();
    }

    public void setTabColor(XSSFColor xSSFColor) {
        this._sh.setTabColor(xSSFColor);
    }

    public void enableLocking() {
        safeGetProtectionField().setSheet(true);
    }

    public void disableLocking() {
        safeGetProtectionField().setSheet(false);
    }

    public void lockAutoFilter(boolean z) {
        safeGetProtectionField().setAutoFilter(z);
    }

    public void lockDeleteColumns(boolean z) {
        safeGetProtectionField().setDeleteColumns(z);
    }

    public void lockDeleteRows(boolean z) {
        safeGetProtectionField().setDeleteRows(z);
    }

    public void lockFormatCells(boolean z) {
        safeGetProtectionField().setFormatCells(z);
    }

    public void lockFormatColumns(boolean z) {
        safeGetProtectionField().setFormatColumns(z);
    }

    public void lockFormatRows(boolean z) {
        safeGetProtectionField().setFormatRows(z);
    }

    public void lockInsertColumns(boolean z) {
        safeGetProtectionField().setInsertColumns(z);
    }

    public void lockInsertHyperlinks(boolean z) {
        safeGetProtectionField().setInsertHyperlinks(z);
    }

    public void lockInsertRows(boolean z) {
        safeGetProtectionField().setInsertRows(z);
    }

    public void lockPivotTables(boolean z) {
        safeGetProtectionField().setPivotTables(z);
    }

    public void lockSort(boolean z) {
        safeGetProtectionField().setSort(z);
    }

    public void lockObjects(boolean z) {
        safeGetProtectionField().setObjects(z);
    }

    public void lockScenarios(boolean z) {
        safeGetProtectionField().setScenarios(z);
    }

    public void lockSelectLockedCells(boolean z) {
        safeGetProtectionField().setSelectLockedCells(z);
    }

    public void lockSelectUnlockedCells(boolean z) {
        safeGetProtectionField().setSelectUnlockedCells(z);
    }

    private CTSheetProtection safeGetProtectionField() {
        CTWorksheet cTWorksheet = this._sh.getCTWorksheet();
        if (!isSheetProtectionEnabled()) {
            return cTWorksheet.addNewSheetProtection();
        }
        return cTWorksheet.getSheetProtection();
    }

    /* access modifiers changed from: package-private */
    public boolean isSheetProtectionEnabled() {
        return this._sh.getCTWorksheet().isSetSheetProtection();
    }

    public void setTabColor(int i) {
        CTWorksheet cTWorksheet = this._sh.getCTWorksheet();
        CTSheetPr sheetPr = cTWorksheet.getSheetPr();
        if (sheetPr == null) {
            sheetPr = cTWorksheet.addNewSheetPr();
        }
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) i);
        sheetPr.setTabColor(newInstance);
    }

    @NotImplemented
    public void shiftColumns(int i, int i2, int i3) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
