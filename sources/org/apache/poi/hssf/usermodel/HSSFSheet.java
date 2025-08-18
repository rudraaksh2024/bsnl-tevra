package org.apache.poi.hssf.usermodel;

import androidx.core.view.MotionEventCompat;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Supplier;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalSheet;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.AutoFilterInfoRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SCLRecord;
import org.apache.poi.hssf.record.WSBoolRecord;
import org.apache.poi.hssf.record.WindowTwoRecord;
import org.apache.poi.hssf.record.aggregates.DataValidityTable;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock;
import org.apache.poi.hssf.usermodel.helpers.HSSFColumnShifter;
import org.apache.poi.hssf.usermodel.helpers.HSSFRowShifter;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SSCellRange;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Configurator;
import org.apache.poi.util.Internal;

public final class HSSFSheet implements Sheet {
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFSheet.RowInitialCapacity", 20);
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) HSSFSheet.class);
    private static final float PX_DEFAULT = 32.0f;
    private static final float PX_MODIFIED = 36.56f;
    protected final InternalWorkbook _book;
    private int _firstrow;
    private int _lastrow;
    private HSSFPatriarch _patriarch;
    private final TreeMap<Integer, HSSFRow> _rows;
    private final InternalSheet _sheet;
    protected final HSSFWorkbook _workbook;

    protected HSSFSheet(HSSFWorkbook hSSFWorkbook) {
        this._firstrow = -1;
        this._lastrow = -1;
        this._sheet = InternalSheet.createSheet();
        this._rows = new TreeMap<>();
        this._workbook = hSSFWorkbook;
        this._book = hSSFWorkbook.getWorkbook();
    }

    protected HSSFSheet(HSSFWorkbook hSSFWorkbook, InternalSheet internalSheet) {
        this._firstrow = -1;
        this._lastrow = -1;
        this._sheet = internalSheet;
        this._rows = new TreeMap<>();
        this._workbook = hSSFWorkbook;
        this._book = hSSFWorkbook.getWorkbook();
        setPropertiesFromSheet(internalSheet);
    }

    /* access modifiers changed from: package-private */
    public HSSFSheet cloneSheet(HSSFWorkbook hSSFWorkbook) {
        getDrawingPatriarch();
        HSSFSheet hSSFSheet = new HSSFSheet(hSSFWorkbook, this._sheet.cloneSheet());
        int findFirstRecordLocBySid = hSSFSheet._sheet.findFirstRecordLocBySid(236);
        DrawingRecord drawingRecord = (DrawingRecord) hSSFSheet._sheet.findFirstRecordBySid(236);
        if (drawingRecord != null) {
            hSSFSheet._sheet.getRecords().remove(drawingRecord);
        }
        if (getDrawingPatriarch() != null) {
            HSSFPatriarch createPatriarch = HSSFPatriarch.createPatriarch(getDrawingPatriarch(), hSSFSheet);
            hSSFSheet._sheet.getRecords().add(findFirstRecordLocBySid, createPatriarch.getBoundAggregate());
            hSSFSheet._patriarch = createPatriarch;
        }
        return hSSFSheet;
    }

    /* access modifiers changed from: protected */
    public void preSerialize() {
        HSSFPatriarch hSSFPatriarch = this._patriarch;
        if (hSSFPatriarch != null) {
            hSSFPatriarch.preSerialize();
        }
    }

    public HSSFWorkbook getWorkbook() {
        return this._workbook;
    }

    private void setPropertiesFromSheet(InternalSheet internalSheet) {
        HSSFRow hSSFRow;
        RowRecord nextRow = internalSheet.getNextRow();
        while (nextRow != null) {
            createRowFromRecord(nextRow);
            nextRow = internalSheet.getNextRow();
        }
        Iterator<CellValueRecordInterface> cellValueIterator = internalSheet.getCellValueIterator();
        long currentTimeMillis = System.currentTimeMillis();
        LOGGER.atDebug().log("Time at start of cell creating in HSSF sheet = {}", (Object) Unbox.box(currentTimeMillis));
        HSSFRow hSSFRow2 = null;
        while (cellValueIterator.hasNext()) {
            CellValueRecordInterface next = cellValueIterator.next();
            long currentTimeMillis2 = System.currentTimeMillis();
            if ((hSSFRow2 == null || hSSFRow2.getRowNum() != next.getRow()) && (hSSFRow2 = getRow(next.getRow())) == null) {
                RowRecord rowRecord = new RowRecord(next.getRow());
                internalSheet.addRow(rowRecord);
                HSSFRow createRowFromRecord = createRowFromRecord(rowRecord);
                hSSFRow = hSSFRow2;
                hSSFRow2 = createRowFromRecord;
            } else {
                hSSFRow = hSSFRow2;
            }
            Logger logger = LOGGER;
            logger.atTrace().log((Supplier<Message>) new HSSFSheet$$ExternalSyntheticLambda0(next));
            hSSFRow2.createCellFromRecord(next);
            logger.atTrace().log("record took {}ms", (Object) Unbox.box(System.currentTimeMillis() - currentTimeMillis2));
            hSSFRow2 = hSSFRow;
        }
        LOGGER.atDebug().log("total sheet cell creation took {}ms", (Object) Unbox.box(System.currentTimeMillis() - currentTimeMillis));
    }

    static /* synthetic */ Message lambda$setPropertiesFromSheet$0(CellValueRecordInterface cellValueRecordInterface) {
        if (cellValueRecordInterface instanceof Record) {
            return new SimpleMessage("record id = " + Integer.toHexString(((Record) cellValueRecordInterface).getSid()));
        }
        return new SimpleMessage("record = " + cellValueRecordInterface);
    }

    public HSSFRow createRow(int i) {
        HSSFRow hSSFRow = new HSSFRow(this._workbook, this, i);
        hSSFRow.setHeight(getDefaultRowHeight());
        hSSFRow.getRowRecord().setBadFontHeight(false);
        addRow(hSSFRow, true);
        return hSSFRow;
    }

    private HSSFRow createRowFromRecord(RowRecord rowRecord) {
        HSSFRow hSSFRow = new HSSFRow(this._workbook, this, rowRecord);
        addRow(hSSFRow, false);
        return hSSFRow;
    }

    public void removeRow(Row row) {
        HSSFRow hSSFRow = (HSSFRow) row;
        if (row.getSheet() == this) {
            Iterator<Cell> it = row.iterator();
            while (it.hasNext()) {
                HSSFCell hSSFCell = (HSSFCell) it.next();
                if (hSSFCell.isPartOfArrayFormulaGroup()) {
                    hSSFCell.tryToDeleteArrayFormula("Row[rownum=" + row.getRowNum() + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.");
                }
            }
            if (!this._rows.isEmpty()) {
                if (this._rows.remove(Integer.valueOf(row.getRowNum())) == row) {
                    if (hSSFRow.getRowNum() == getLastRowNum()) {
                        this._lastrow = findLastRow(this._lastrow);
                    }
                    if (hSSFRow.getRowNum() == getFirstRowNum()) {
                        this._firstrow = findFirstRow(this._firstrow);
                    }
                    this._sheet.removeRow(hSSFRow.getRowRecord());
                    if (this._rows.isEmpty()) {
                        this._firstrow = -1;
                        this._lastrow = -1;
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("Specified row does not belong to this sheet");
            }
            return;
        }
        throw new IllegalArgumentException("Specified row does not belong to this sheet");
    }

    private int findLastRow(int i) {
        if (i < 1) {
            return 0;
        }
        int i2 = i - 1;
        HSSFRow row = getRow(i2);
        while (row == null && i2 > 0) {
            i2--;
            row = getRow(i2);
        }
        if (row == null) {
            return 0;
        }
        return i2;
    }

    private int findFirstRow(int i) {
        int i2 = i + 1;
        HSSFRow row = getRow(i2);
        while (row == null && i2 <= getLastRowNum()) {
            i2++;
            row = getRow(i2);
        }
        if (i2 > getLastRowNum()) {
            return 0;
        }
        return i2;
    }

    private void addRow(HSSFRow hSSFRow, boolean z) {
        this._rows.put(Integer.valueOf(hSSFRow.getRowNum()), hSSFRow);
        if (z) {
            this._sheet.addRow(hSSFRow.getRowRecord());
        }
        boolean z2 = true;
        if (this._rows.size() != 1) {
            z2 = false;
        }
        if (hSSFRow.getRowNum() > getLastRowNum() || z2) {
            this._lastrow = hSSFRow.getRowNum();
        }
        if (hSSFRow.getRowNum() < getFirstRowNum() || z2) {
            this._firstrow = hSSFRow.getRowNum();
        }
    }

    public HSSFRow getRow(int i) {
        return this._rows.get(Integer.valueOf(i));
    }

    public int getPhysicalNumberOfRows() {
        return this._rows.size();
    }

    public int getFirstRowNum() {
        return this._firstrow;
    }

    public int getLastRowNum() {
        return this._lastrow;
    }

    public List<HSSFDataValidation> getDataValidations() {
        DataValidityTable orCreateDataValidityTable = this._sheet.getOrCreateDataValidityTable();
        final ArrayList arrayList = new ArrayList();
        orCreateDataValidityTable.visitContainedRecords(new RecordAggregate.RecordVisitor() {
            private HSSFEvaluationWorkbook book;

            {
                this.book = HSSFEvaluationWorkbook.create(HSSFSheet.this.getWorkbook());
            }

            public void visitRecord(Record record) {
                if (record instanceof DVRecord) {
                    DVRecord dVRecord = (DVRecord) record;
                    HSSFDataValidation hSSFDataValidation = new HSSFDataValidation(dVRecord.getCellRangeAddress().copy(), DVConstraint.createDVConstraint(dVRecord, this.book));
                    hSSFDataValidation.setErrorStyle(dVRecord.getErrorStyle());
                    hSSFDataValidation.setEmptyCellAllowed(dVRecord.getEmptyCellAllowed());
                    hSSFDataValidation.setSuppressDropDownArrow(dVRecord.getSuppressDropdownArrow());
                    hSSFDataValidation.createPromptBox(dVRecord.getPromptTitle(), dVRecord.getPromptText());
                    hSSFDataValidation.setShowPromptBox(dVRecord.getShowPromptOnCellSelected());
                    hSSFDataValidation.createErrorBox(dVRecord.getErrorTitle(), dVRecord.getErrorText());
                    hSSFDataValidation.setShowErrorBox(dVRecord.getShowErrorOnInvalidValue());
                    arrayList.add(hSSFDataValidation);
                }
            }
        });
        return arrayList;
    }

    public void addValidationData(DataValidation dataValidation) {
        if (dataValidation != null) {
            this._sheet.getOrCreateDataValidityTable().addDataValidation(((HSSFDataValidation) dataValidation).createDVRecord(this));
            return;
        }
        throw new IllegalArgumentException("objValidation must not be null");
    }

    public void setColumnHidden(int i, boolean z) {
        this._sheet.setColumnHidden(i, z);
    }

    public boolean isColumnHidden(int i) {
        return this._sheet.isColumnHidden(i);
    }

    public void setColumnWidth(int i, int i2) {
        this._sheet.setColumnWidth(i, i2);
    }

    public int getColumnWidth(int i) {
        return this._sheet.getColumnWidth(i);
    }

    public float getColumnWidthInPixels(int i) {
        int columnWidth = getColumnWidth(i);
        return ((float) columnWidth) / (columnWidth == getDefaultColumnWidth() * 256 ? PX_DEFAULT : PX_MODIFIED);
    }

    public int getDefaultColumnWidth() {
        return this._sheet.getDefaultColumnWidth();
    }

    public void setDefaultColumnWidth(int i) {
        this._sheet.setDefaultColumnWidth(i);
    }

    public short getDefaultRowHeight() {
        return this._sheet.getDefaultRowHeight();
    }

    public float getDefaultRowHeightInPoints() {
        return ((float) this._sheet.getDefaultRowHeight()) / 20.0f;
    }

    public void setDefaultRowHeight(short s) {
        this._sheet.setDefaultRowHeight(s);
    }

    public void setDefaultRowHeightInPoints(float f) {
        this._sheet.setDefaultRowHeight((short) ((int) (f * 20.0f)));
    }

    public HSSFCellStyle getColumnStyle(int i) {
        short xFIndexForColAt = this._sheet.getXFIndexForColAt((short) i);
        if (xFIndexForColAt == 15) {
            return null;
        }
        return new HSSFCellStyle(xFIndexForColAt, this._book.getExFormatAt(xFIndexForColAt), this._book);
    }

    public boolean isGridsPrinted() {
        return this._sheet.isGridsPrinted();
    }

    public void setGridsPrinted(boolean z) {
        this._sheet.setGridsPrinted(z);
    }

    public int addMergedRegion(CellRangeAddress cellRangeAddress) {
        return addMergedRegion(cellRangeAddress, true);
    }

    public int addMergedRegionUnsafe(CellRangeAddress cellRangeAddress) {
        return addMergedRegion(cellRangeAddress, false);
    }

    public void validateMergedRegions() {
        checkForMergedRegionsIntersectingArrayFormulas();
        checkForIntersectingMergedRegions();
    }

    private int addMergedRegion(CellRangeAddress cellRangeAddress, boolean z) {
        if (cellRangeAddress.getNumberOfCells() >= 2) {
            cellRangeAddress.validate(SpreadsheetVersion.EXCEL97);
            if (z) {
                validateArrayFormulas(cellRangeAddress);
                validateMergedRegions(cellRangeAddress);
            }
            return this._sheet.addMergedRegion(cellRangeAddress.getFirstRow(), cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastRow(), cellRangeAddress.getLastColumn());
        }
        throw new IllegalArgumentException("Merged region " + cellRangeAddress.formatAsString() + " must contain 2 or more cells");
    }

    private void validateArrayFormulas(CellRangeAddress cellRangeAddress) {
        int firstColumn = cellRangeAddress.getFirstColumn();
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= lastRow; firstRow++) {
            HSSFRow row = getRow(firstRow);
            if (row != null) {
                for (int i = firstColumn; i <= lastColumn; i++) {
                    HSSFCell cell = row.getCell(i);
                    if (cell != null && cell.isPartOfArrayFormulaGroup()) {
                        CellRangeAddress arrayFormulaRange = cell.getArrayFormulaRange();
                        if (arrayFormulaRange.getNumberOfCells() > 1 && cellRangeAddress.intersects(arrayFormulaRange)) {
                            throw new IllegalStateException("The range " + cellRangeAddress.formatAsString() + " intersects with a multi-cell array formula. You cannot merge cells of an array.");
                        }
                    }
                }
                continue;
            }
        }
    }

    private void checkForMergedRegionsIntersectingArrayFormulas() {
        for (CellRangeAddress validateArrayFormulas : getMergedRegions()) {
            validateArrayFormulas(validateArrayFormulas);
        }
    }

    private void validateMergedRegions(CellRangeAddress cellRangeAddress) {
        for (CellRangeAddress next : getMergedRegions()) {
            if (next.intersects(cellRangeAddress)) {
                throw new IllegalStateException("Cannot add merged region " + cellRangeAddress.formatAsString() + " to sheet because it overlaps with an existing merged region (" + next.formatAsString() + ").");
            }
        }
    }

    private void checkForIntersectingMergedRegions() {
        List<CellRangeAddress> mergedRegions = getMergedRegions();
        int size = mergedRegions.size();
        int i = 0;
        while (i < size) {
            CellRangeAddress cellRangeAddress = mergedRegions.get(i);
            i++;
            Iterator<CellRangeAddress> it = mergedRegions.subList(i, mergedRegions.size()).iterator();
            while (true) {
                if (it.hasNext()) {
                    CellRangeAddress next = it.next();
                    if (cellRangeAddress.intersects(next)) {
                        throw new IllegalStateException("The range " + cellRangeAddress.formatAsString() + " intersects with another merged region " + next.formatAsString() + " in this sheet");
                    }
                }
            }
        }
    }

    public void setForceFormulaRecalculation(boolean z) {
        this._sheet.setUncalced(z);
    }

    public boolean getForceFormulaRecalculation() {
        return this._sheet.getUncalced();
    }

    public void setVerticallyCenter(boolean z) {
        this._sheet.getPageSettings().getVCenter().setVCenter(z);
    }

    public boolean getVerticallyCenter() {
        return this._sheet.getPageSettings().getVCenter().getVCenter();
    }

    public void setHorizontallyCenter(boolean z) {
        this._sheet.getPageSettings().getHCenter().setHCenter(z);
    }

    public boolean getHorizontallyCenter() {
        return this._sheet.getPageSettings().getHCenter().getHCenter();
    }

    public void setRightToLeft(boolean z) {
        this._sheet.getWindowTwo().setArabic(z);
    }

    public boolean isRightToLeft() {
        return this._sheet.getWindowTwo().getArabic();
    }

    public void removeMergedRegion(int i) {
        this._sheet.removeMergedRegion(i);
    }

    public void removeMergedRegions(Collection<Integer> collection) {
        for (Integer intValue : new TreeSet(collection).descendingSet()) {
            this._sheet.removeMergedRegion(intValue.intValue());
        }
    }

    public int getNumMergedRegions() {
        return this._sheet.getNumMergedRegions();
    }

    public CellRangeAddress getMergedRegion(int i) {
        return this._sheet.getMergedRegionAt(i);
    }

    public List<CellRangeAddress> getMergedRegions() {
        ArrayList arrayList = new ArrayList();
        int numMergedRegions = this._sheet.getNumMergedRegions();
        for (int i = 0; i < numMergedRegions; i++) {
            arrayList.add(this._sheet.getMergedRegionAt(i));
        }
        return arrayList;
    }

    public Iterator<Row> rowIterator() {
        return this._rows.values().iterator();
    }

    public Spliterator<Row> spliterator() {
        return this._rows.values().spliterator();
    }

    @Internal
    public InternalSheet getSheet() {
        return this._sheet;
    }

    public void setAlternativeExpression(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setAlternateExpression(z);
    }

    public void setAlternativeFormula(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setAlternateFormula(z);
    }

    public void setAutobreaks(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setAutobreaks(z);
    }

    public void setDialog(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setDialog(z);
    }

    public void setDisplayGuts(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setDisplayGuts(z);
    }

    public void setFitToPage(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setFitToPage(z);
    }

    public void setRowSumsBelow(boolean z) {
        WSBoolRecord wSBoolRecord = (WSBoolRecord) this._sheet.findFirstRecordBySid(129);
        wSBoolRecord.setRowSumsBelow(z);
        wSBoolRecord.setAlternateExpression(z);
    }

    public void setRowSumsRight(boolean z) {
        ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).setRowSumsRight(z);
    }

    public boolean getAlternateExpression() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getAlternateExpression();
    }

    public boolean getAlternateFormula() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getAlternateFormula();
    }

    public boolean getAutobreaks() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getAutobreaks();
    }

    public boolean getDialog() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getDialog();
    }

    public boolean getDisplayGuts() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getDisplayGuts();
    }

    public boolean isDisplayZeros() {
        return this._sheet.getWindowTwo().getDisplayZeros();
    }

    public void setDisplayZeros(boolean z) {
        this._sheet.getWindowTwo().setDisplayZeros(z);
    }

    public boolean getFitToPage() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getFitToPage();
    }

    public boolean getRowSumsBelow() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getRowSumsBelow();
    }

    public boolean getRowSumsRight() {
        return ((WSBoolRecord) this._sheet.findFirstRecordBySid(129)).getRowSumsRight();
    }

    public boolean isPrintGridlines() {
        return getSheet().getPrintGridlines().getPrintGridlines();
    }

    public void setPrintGridlines(boolean z) {
        getSheet().getPrintGridlines().setPrintGridlines(z);
    }

    public boolean isPrintRowAndColumnHeadings() {
        return getSheet().getPrintHeaders().getPrintHeaders();
    }

    public void setPrintRowAndColumnHeadings(boolean z) {
        getSheet().getPrintHeaders().setPrintHeaders(z);
    }

    public HSSFPrintSetup getPrintSetup() {
        return new HSSFPrintSetup(this._sheet.getPageSettings().getPrintSetup());
    }

    public HSSFHeader getHeader() {
        return new HSSFHeader(this._sheet.getPageSettings());
    }

    public HSSFFooter getFooter() {
        return new HSSFFooter(this._sheet.getPageSettings());
    }

    public boolean isSelected() {
        return getSheet().getWindowTwo().getSelected();
    }

    public void setSelected(boolean z) {
        getSheet().getWindowTwo().setSelected(z);
    }

    public boolean isActive() {
        return getSheet().getWindowTwo().isActive();
    }

    public void setActive(boolean z) {
        getSheet().getWindowTwo().setActive(z);
    }

    public double getMargin(short s) {
        if (s == 4) {
            return this._sheet.getPageSettings().getPrintSetup().getHeaderMargin();
        }
        if (s != 5) {
            return this._sheet.getPageSettings().getMargin(s);
        }
        return this._sheet.getPageSettings().getPrintSetup().getFooterMargin();
    }

    public void setMargin(short s, double d) {
        if (s == 4) {
            this._sheet.getPageSettings().getPrintSetup().setHeaderMargin(d);
        } else if (s != 5) {
            this._sheet.getPageSettings().setMargin(s, d);
        } else {
            this._sheet.getPageSettings().getPrintSetup().setFooterMargin(d);
        }
    }

    private WorksheetProtectionBlock getProtectionBlock() {
        return this._sheet.getProtectionBlock();
    }

    public boolean getProtect() {
        return getProtectionBlock().isSheetProtected();
    }

    public short getPassword() {
        return (short) getProtectionBlock().getPasswordHash();
    }

    public boolean getObjectProtect() {
        return getProtectionBlock().isObjectProtected();
    }

    public boolean getScenarioProtect() {
        return getProtectionBlock().isScenarioProtected();
    }

    public void protectSheet(String str) {
        getProtectionBlock().protectSheet(str, true, true);
    }

    public void setZoom(int i, int i2) {
        if (i < 1 || i > 65535) {
            throw new IllegalArgumentException("Numerator must be greater than 0 and less than 65536");
        } else if (i2 < 1 || i2 > 65535) {
            throw new IllegalArgumentException("Denominator must be greater than 0 and less than 65536");
        } else {
            SCLRecord sCLRecord = new SCLRecord();
            sCLRecord.setNumerator((short) i);
            sCLRecord.setDenominator((short) i2);
            getSheet().setSCLRecord(sCLRecord);
        }
    }

    public void setZoom(int i) {
        setZoom(i, 100);
    }

    public short getTopRow() {
        return this._sheet.getTopRow();
    }

    public short getLeftCol() {
        return this._sheet.getLeftCol();
    }

    public void showInPane(int i, int i2) {
        int lastRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (i <= lastRowIndex) {
            showInPane((short) i, (short) i2);
            return;
        }
        throw new IllegalArgumentException("Maximum row number is " + lastRowIndex);
    }

    private void showInPane(short s, short s2) {
        this._sheet.setTopRow(s);
        this._sheet.setLeftCol(s2);
    }

    /* access modifiers changed from: protected */
    public void shiftMerged(int i, int i2, int i3, boolean z) {
        new HSSFRowShifter(this).shiftMergedRegions(i, i2, i3);
    }

    public void shiftRows(int i, int i2, int i3) {
        shiftRows(i, i2, i3, false, false);
    }

    public void shiftRows(int i, int i2, int i3, boolean z, boolean z2) {
        shiftRows(i, i2, i3, z, z2, true);
    }

    private static int clip(int i) {
        return Math.min(Math.max(0, i), SpreadsheetVersion.EXCEL97.getLastRowIndex());
    }

    public void shiftRows(int i, int i2, int i3, boolean z, boolean z2, boolean z3) {
        int i4;
        int i5;
        if (i2 >= i) {
            if (i3 < 0) {
                i5 = 1;
                i4 = i;
            } else if (i3 > 0) {
                i5 = -1;
                i4 = i2;
            } else {
                return;
            }
            HSSFRowShifter hSSFRowShifter = new HSSFRowShifter(this);
            if (z3) {
                moveCommentsForRowShift(i, i2, i3);
            }
            hSSFRowShifter.shiftMergedRegions(i, i2, i3);
            this._sheet.getPageSettings().shiftRowBreaks(i, i2, i3);
            deleteOverwrittenHyperlinksForRowShift(i, i2, i3);
            while (i4 >= i && i4 <= i2 && i4 >= 0 && i4 < 65536) {
                HSSFRow row = getRow(i4);
                if (row != null) {
                    notifyRowShifting(row);
                }
                int i6 = i4 + i3;
                HSSFRow row2 = getRow(i6);
                if (row2 == null) {
                    row2 = createRow(i6);
                }
                row2.removeAllCells();
                if (row != null) {
                    if (z) {
                        row2.setHeight(row.getHeight());
                    }
                    if (z2) {
                        row.setHeight(255);
                    }
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        HSSFCell hSSFCell = (HSSFCell) cellIterator.next();
                        HSSFHyperlink hyperlink = hSSFCell.getHyperlink();
                        row.removeCell(hSSFCell);
                        CellValueRecordInterface cellValueRecord = hSSFCell.getCellValueRecord();
                        cellValueRecord.setRow(i6);
                        row2.createCellFromRecord(cellValueRecord);
                        this._sheet.addValueRecord(i6, cellValueRecord);
                        if (hyperlink != null) {
                            hyperlink.setFirstRow(hyperlink.getFirstRow() + i3);
                            hyperlink.setLastRow(hyperlink.getLastRow() + i3);
                        }
                    }
                    row.removeAllCells();
                }
                i4 += i5;
            }
            recomputeFirstAndLastRowsForRowShift(i, i2, i3);
            int sheetIndex = this._workbook.getSheetIndex((Sheet) this);
            updateFormulasForShift(FormulaShifter.createForRowShift(this._book.checkExternSheet(sheetIndex), this._workbook.getSheetName(sheetIndex), i, i2, i3, SpreadsheetVersion.EXCEL97));
            return;
        }
        throw new IllegalArgumentException("startRow must be less than or equal to endRow. To shift rows up, use n<0.");
    }

    private void updateFormulasForShift(FormulaShifter formulaShifter) {
        this._sheet.updateFormulasAfterCellShift(formulaShifter, this._book.checkExternSheet(this._workbook.getSheetIndex((Sheet) this)));
        int numberOfSheets = this._workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            InternalSheet sheet = this._workbook.getSheetAt(i).getSheet();
            if (sheet != this._sheet) {
                sheet.updateFormulasAfterCellShift(formulaShifter, this._book.checkExternSheet(i));
            }
        }
        this._workbook.getWorkbook().updateNamesAfterCellShift(formulaShifter);
    }

    private void recomputeFirstAndLastRowsForRowShift(int i, int i2, int i3) {
        if (i3 > 0) {
            if (i == this._firstrow) {
                int i4 = i + i3;
                this._firstrow = Math.max(i4, 0);
                while (true) {
                    i++;
                    if (i < i4) {
                        if (getRow(i) != null) {
                            this._firstrow = i;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            int i5 = i2 + i3;
            if (i5 > this._lastrow) {
                this._lastrow = Math.min(i5, SpreadsheetVersion.EXCEL97.getLastRowIndex());
                return;
            }
            return;
        }
        int i6 = i + i3;
        if (i6 < this._firstrow) {
            this._firstrow = Math.max(i6, 0);
        }
        if (i2 == this._lastrow) {
            int i7 = i3 + i2;
            this._lastrow = Math.min(i7, SpreadsheetVersion.EXCEL97.getLastRowIndex());
            for (int i8 = i2 - 1; i8 > i7; i8--) {
                if (getRow(i8) != null) {
                    this._lastrow = i8;
                    return;
                }
            }
        }
    }

    private void deleteOverwrittenHyperlinksForRowShift(int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = i2 + i3;
        for (HSSFHyperlink next : getHyperlinkList()) {
            int firstRow = next.getFirstRow();
            int lastRow = next.getLastRow();
            if (i4 <= firstRow && firstRow <= i5 && i5 <= lastRow && lastRow <= i5) {
                removeHyperlink(next);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001d, code lost:
        r0 = (org.apache.poi.hssf.usermodel.HSSFComment) r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void moveCommentsForRowShift(int r3, int r4, int r5) {
        /*
            r2 = this;
            org.apache.poi.hssf.usermodel.HSSFPatriarch r2 = r2.createDrawingPatriarch()
            java.util.List r2 = r2.getChildren()
            java.util.Iterator r2 = r2.iterator()
        L_0x000c:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0030
            java.lang.Object r0 = r2.next()
            org.apache.poi.hssf.usermodel.HSSFShape r0 = (org.apache.poi.hssf.usermodel.HSSFShape) r0
            boolean r1 = r0 instanceof org.apache.poi.hssf.usermodel.HSSFComment
            if (r1 != 0) goto L_0x001d
            goto L_0x000c
        L_0x001d:
            org.apache.poi.hssf.usermodel.HSSFComment r0 = (org.apache.poi.hssf.usermodel.HSSFComment) r0
            int r1 = r0.getRow()
            if (r3 > r1) goto L_0x000c
            if (r1 > r4) goto L_0x000c
            int r1 = r1 + r5
            int r1 = clip(r1)
            r0.setRow(r1)
            goto L_0x000c
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFSheet.moveCommentsForRowShift(int, int, int):void");
    }

    public void shiftColumns(int i, int i2, int i3) {
        new HSSFColumnShifter(this).shiftColumns(i, i2, i3);
        int sheetIndex = this._workbook.getSheetIndex((Sheet) this);
        updateFormulasForShift(FormulaShifter.createForColumnShift(this._book.checkExternSheet(sheetIndex), this._workbook.getSheetName(sheetIndex), i, i2, i3, SpreadsheetVersion.EXCEL97));
    }

    /* access modifiers changed from: protected */
    public void insertChartRecords(List<Record> list) {
        this._sheet.getRecords().addAll(this._sheet.findFirstRecordLocBySid(WindowTwoRecord.sid), list);
    }

    private void notifyRowShifting(HSSFRow hSSFRow) {
        String str = "Row[rownum=" + hSSFRow.getRowNum() + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.";
        Iterator<Cell> it = hSSFRow.iterator();
        while (it.hasNext()) {
            HSSFCell hSSFCell = (HSSFCell) it.next();
            if (hSSFCell.isPartOfArrayFormulaGroup()) {
                hSSFCell.tryToDeleteArrayFormula(str);
            }
        }
    }

    public void createFreezePane(int i, int i2, int i3, int i4) {
        validateColumn(i);
        validateRow(i2);
        if (i3 < i) {
            throw new IllegalArgumentException("leftmostColumn parameter must not be less than colSplit parameter");
        } else if (i4 >= i2) {
            getSheet().createFreezePane(i, i2, i4, i3);
        } else {
            throw new IllegalArgumentException("topRow parameter must not be less than leftmostColumn parameter");
        }
    }

    public void createFreezePane(int i, int i2) {
        createFreezePane(i, i2, i, i2);
    }

    public void createSplitPane(int i, int i2, int i3, int i4, int i5) {
        getSheet().createSplitPane(i, i2, i4, i3, i5);
    }

    public PaneInformation getPaneInformation() {
        return getSheet().getPaneInformation();
    }

    public void setDisplayGridlines(boolean z) {
        this._sheet.setDisplayGridlines(z);
    }

    public boolean isDisplayGridlines() {
        return this._sheet.isDisplayGridlines();
    }

    public void setDisplayFormulas(boolean z) {
        this._sheet.setDisplayFormulas(z);
    }

    public boolean isDisplayFormulas() {
        return this._sheet.isDisplayFormulas();
    }

    public void setDisplayRowColHeadings(boolean z) {
        this._sheet.setDisplayRowColHeadings(z);
    }

    public boolean isDisplayRowColHeadings() {
        return this._sheet.isDisplayRowColHeadings();
    }

    public void setRowBreak(int i) {
        validateRow(i);
        this._sheet.getPageSettings().setRowBreak(i, 0, 255);
    }

    public boolean isRowBroken(int i) {
        return this._sheet.getPageSettings().isRowBroken(i);
    }

    public void removeRowBreak(int i) {
        this._sheet.getPageSettings().removeRowBreak(i);
    }

    public int[] getRowBreaks() {
        return this._sheet.getPageSettings().getRowBreaks();
    }

    public int[] getColumnBreaks() {
        return this._sheet.getPageSettings().getColumnBreaks();
    }

    public void setColumnBreak(int i) {
        short s = (short) i;
        validateColumn(s);
        this._sheet.getPageSettings().setColumnBreak(s, 0, (short) SpreadsheetVersion.EXCEL97.getLastRowIndex());
    }

    public boolean isColumnBroken(int i) {
        return this._sheet.getPageSettings().isColumnBroken(i);
    }

    public void removeColumnBreak(int i) {
        this._sheet.getPageSettings().removeColumnBreak(i);
    }

    /* access modifiers changed from: protected */
    public void validateRow(int i) {
        int lastRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (i > lastRowIndex) {
            throw new IllegalArgumentException("Maximum row number is " + lastRowIndex);
        } else if (i < 0) {
            throw new IllegalArgumentException("Minumum row number is 0");
        }
    }

    /* access modifiers changed from: protected */
    public void validateColumn(int i) {
        int lastColumnIndex = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
        if (i > lastColumnIndex) {
            throw new IllegalArgumentException("Maximum column number is " + lastColumnIndex);
        } else if (i < 0) {
            throw new IllegalArgumentException("Minimum column number is 0");
        }
    }

    public void dumpDrawingRecords(boolean z, PrintWriter printWriter) {
        this._sheet.aggregateDrawingRecords(this._book.getDrawingManager(), false);
        for (EscherRecord next : ((EscherAggregate) getSheet().findFirstRecordBySid(EscherAggregate.sid)).getEscherRecords()) {
            if (z) {
                printWriter.println(next);
            } else {
                next.display(printWriter, 0);
            }
        }
        printWriter.flush();
    }

    public EscherAggregate getDrawingEscherAggregate() {
        this._book.findDrawingGroup();
        if (this._book.getDrawingManager() == null || this._sheet.aggregateDrawingRecords(this._book.getDrawingManager(), false) == -1) {
            return null;
        }
        return (EscherAggregate) this._sheet.findFirstRecordBySid(EscherAggregate.sid);
    }

    public HSSFPatriarch getDrawingPatriarch() {
        HSSFPatriarch patriarch = getPatriarch(false);
        this._patriarch = patriarch;
        return patriarch;
    }

    public HSSFPatriarch createDrawingPatriarch() {
        HSSFPatriarch patriarch = getPatriarch(true);
        this._patriarch = patriarch;
        return patriarch;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: org.apache.poi.hssf.record.EscherAggregate} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.hssf.usermodel.HSSFPatriarch getPatriarch(boolean r5) {
        /*
            r4 = this;
            org.apache.poi.hssf.usermodel.HSSFPatriarch r0 = r4._patriarch
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            org.apache.poi.hssf.model.InternalWorkbook r0 = r4._book
            org.apache.poi.hssf.model.DrawingManager2 r0 = r0.findDrawingGroup()
            r1 = 0
            if (r0 != 0) goto L_0x001c
            if (r5 != 0) goto L_0x0011
            return r1
        L_0x0011:
            org.apache.poi.hssf.model.InternalWorkbook r0 = r4._book
            r0.createDrawingGroup()
            org.apache.poi.hssf.model.InternalWorkbook r0 = r4._book
            org.apache.poi.hssf.model.DrawingManager2 r0 = r0.getDrawingManager()
        L_0x001c:
            org.apache.poi.hssf.model.InternalSheet r2 = r4._sheet
            r3 = 9876(0x2694, float:1.3839E-41)
            org.apache.poi.hssf.record.Record r2 = r2.findFirstRecordBySid(r3)
            org.apache.poi.hssf.record.EscherAggregate r2 = (org.apache.poi.hssf.record.EscherAggregate) r2
            if (r2 != 0) goto L_0x005e
            org.apache.poi.hssf.model.InternalSheet r2 = r4._sheet
            r3 = 0
            int r2 = r2.aggregateDrawingRecords(r0, r3)
            r3 = -1
            if (r3 != r2) goto L_0x0051
            if (r5 == 0) goto L_0x0050
            org.apache.poi.hssf.model.InternalSheet r5 = r4._sheet
            r1 = 1
            int r5 = r5.aggregateDrawingRecords(r0, r1)
            org.apache.poi.hssf.model.InternalSheet r0 = r4._sheet
            java.util.List r0 = r0.getRecords()
            java.lang.Object r5 = r0.get(r5)
            org.apache.poi.hssf.record.EscherAggregate r5 = (org.apache.poi.hssf.record.EscherAggregate) r5
            org.apache.poi.hssf.usermodel.HSSFPatriarch r0 = new org.apache.poi.hssf.usermodel.HSSFPatriarch
            r0.<init>(r4, r5)
            r0.afterCreate()
            return r0
        L_0x0050:
            return r1
        L_0x0051:
            org.apache.poi.hssf.model.InternalSheet r5 = r4._sheet
            java.util.List r5 = r5.getRecords()
            java.lang.Object r5 = r5.get(r2)
            r2 = r5
            org.apache.poi.hssf.record.EscherAggregate r2 = (org.apache.poi.hssf.record.EscherAggregate) r2
        L_0x005e:
            org.apache.poi.hssf.usermodel.HSSFPatriarch r5 = new org.apache.poi.hssf.usermodel.HSSFPatriarch
            r5.<init>(r4, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFSheet.getPatriarch(boolean):org.apache.poi.hssf.usermodel.HSSFPatriarch");
    }

    public void setColumnGroupCollapsed(int i, boolean z) {
        this._sheet.setColumnGroupCollapsed(i, z);
    }

    public void groupColumn(int i, int i2) {
        this._sheet.groupColumnRange(i, i2, true);
    }

    public void ungroupColumn(int i, int i2) {
        this._sheet.groupColumnRange(i, i2, false);
    }

    public void groupRow(int i, int i2) {
        this._sheet.groupRowRange(i, i2, true);
    }

    public void ungroupRow(int i, int i2) {
        this._sheet.groupRowRange(i, i2, false);
    }

    public void setRowGroupCollapsed(int i, boolean z) {
        if (z) {
            this._sheet.getRowsAggregate().collapseRow(i);
        } else {
            this._sheet.getRowsAggregate().expandRow(i);
        }
    }

    public void setDefaultColumnStyle(int i, CellStyle cellStyle) {
        this._sheet.setDefaultColumnStyle(i, cellStyle.getIndex());
    }

    public void autoSizeColumn(int i) {
        autoSizeColumn(i, false);
    }

    public void autoSizeColumn(int i, boolean z) {
        double columnWidth = SheetUtil.getColumnWidth(this, i, z);
        if (columnWidth != -1.0d) {
            double d = columnWidth * 256.0d;
            double d2 = (double) MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            if (d > d2) {
                d = d2;
            }
            setColumnWidth(i, (int) d);
        }
    }

    public HSSFComment getCellComment(CellAddress cellAddress) {
        return findCellComment(cellAddress.getRow(), cellAddress.getColumn());
    }

    public HSSFHyperlink getHyperlink(int i, int i2) {
        for (RecordBase next : this._sheet.getRecords()) {
            if (next instanceof HyperlinkRecord) {
                HyperlinkRecord hyperlinkRecord = (HyperlinkRecord) next;
                if (hyperlinkRecord.getFirstColumn() == i2 && hyperlinkRecord.getFirstRow() == i) {
                    return new HSSFHyperlink(hyperlinkRecord);
                }
            }
        }
        return null;
    }

    public HSSFHyperlink getHyperlink(CellAddress cellAddress) {
        return getHyperlink(cellAddress.getRow(), cellAddress.getColumn());
    }

    public List<HSSFHyperlink> getHyperlinkList() {
        ArrayList arrayList = new ArrayList();
        for (RecordBase next : this._sheet.getRecords()) {
            if (next instanceof HyperlinkRecord) {
                arrayList.add(new HSSFHyperlink((HyperlinkRecord) next));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void removeHyperlink(HSSFHyperlink hSSFHyperlink) {
        removeHyperlink(hSSFHyperlink.record);
    }

    /* access modifiers changed from: protected */
    public void removeHyperlink(HyperlinkRecord hyperlinkRecord) {
        Iterator<RecordBase> it = this._sheet.getRecords().iterator();
        while (it.hasNext()) {
            RecordBase next = it.next();
            if ((next instanceof HyperlinkRecord) && hyperlinkRecord == ((HyperlinkRecord) next)) {
                it.remove();
                return;
            }
        }
    }

    public HSSFSheetConditionalFormatting getSheetConditionalFormatting() {
        return new HSSFSheetConditionalFormatting(this);
    }

    public String getSheetName() {
        HSSFWorkbook workbook = getWorkbook();
        return workbook.getSheetName(workbook.getSheetIndex((Sheet) this));
    }

    private CellRange<HSSFCell> getCellRange(CellRangeAddress cellRangeAddress) {
        int firstRow = cellRangeAddress.getFirstRow();
        int firstColumn = cellRangeAddress.getFirstColumn();
        int lastRow = cellRangeAddress.getLastRow();
        int lastColumn = cellRangeAddress.getLastColumn();
        int i = (lastRow - firstRow) + 1;
        int i2 = (lastColumn - firstColumn) + 1;
        ArrayList arrayList = new ArrayList(i * i2);
        for (int i3 = firstRow; i3 <= lastRow; i3++) {
            for (int i4 = firstColumn; i4 <= lastColumn; i4++) {
                HSSFRow row = getRow(i3);
                if (row == null) {
                    row = createRow(i3);
                }
                HSSFCell cell = row.getCell(i4);
                if (cell == null) {
                    cell = row.createCell(i4);
                }
                arrayList.add(cell);
            }
        }
        return SSCellRange.create(firstRow, firstColumn, i, i2, arrayList, HSSFCell.class);
    }

    public CellRange<HSSFCell> setArrayFormula(String str, CellRangeAddress cellRangeAddress) {
        Ptg[] parse = HSSFFormulaParser.parse(str, this._workbook, FormulaType.ARRAY, this._workbook.getSheetIndex((Sheet) this));
        CellRange<HSSFCell> cellRange = getCellRange(cellRangeAddress);
        for (HSSFCell cellArrayFormula : cellRange) {
            cellArrayFormula.setCellArrayFormula(cellRangeAddress);
        }
        ((FormulaRecordAggregate) cellRange.getTopLeftCell().getCellValueRecord()).setArrayFormula(cellRangeAddress, parse);
        return cellRange;
    }

    public CellRange<HSSFCell> removeArrayFormula(Cell cell) {
        if (cell.getSheet() == this) {
            CellValueRecordInterface cellValueRecord = ((HSSFCell) cell).getCellValueRecord();
            if (cellValueRecord instanceof FormulaRecordAggregate) {
                CellRange<HSSFCell> cellRange = getCellRange(((FormulaRecordAggregate) cellValueRecord).removeArrayFormula(cell.getRowIndex(), cell.getColumnIndex()));
                for (HSSFCell blank : cellRange) {
                    blank.setBlank();
                }
                return cellRange;
            }
            throw new IllegalArgumentException("Cell " + new CellReference(cell).formatAsString() + " is not part of an array formula.");
        }
        throw new IllegalArgumentException("Specified cell does not belong to this sheet.");
    }

    public DataValidationHelper getDataValidationHelper() {
        return new HSSFDataValidationHelper(this);
    }

    public HSSFAutoFilter setAutoFilter(CellRangeAddress cellRangeAddress) {
        InternalWorkbook workbook = this._workbook.getWorkbook();
        int sheetIndex = this._workbook.getSheetIndex((Sheet) this);
        int i = sheetIndex + 1;
        NameRecord specificBuiltinRecord = workbook.getSpecificBuiltinRecord((byte) 13, i);
        if (specificBuiltinRecord == null) {
            specificBuiltinRecord = workbook.createBuiltInName((byte) 13, i);
        }
        NameRecord nameRecord = specificBuiltinRecord;
        int firstRow = cellRangeAddress.getFirstRow();
        if (firstRow == -1) {
            firstRow = 0;
        }
        nameRecord.setNameDefinition(new Ptg[]{new Area3DPtg(firstRow, cellRangeAddress.getLastRow(), cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn(), false, false, false, false, sheetIndex)});
        AutoFilterInfoRecord autoFilterInfoRecord = new AutoFilterInfoRecord();
        autoFilterInfoRecord.setNumEntries((short) ((cellRangeAddress.getLastColumn() + 1) - cellRangeAddress.getFirstColumn()));
        this._sheet.getRecords().add(this._sheet.findFirstRecordLocBySid(DimensionsRecord.sid), autoFilterInfoRecord);
        HSSFPatriarch createDrawingPatriarch = createDrawingPatriarch();
        int firstColumn = cellRangeAddress.getFirstColumn();
        int lastColumn = cellRangeAddress.getLastColumn();
        while (firstColumn <= lastColumn) {
            short s = (short) firstColumn;
            firstColumn++;
            createDrawingPatriarch.createComboBox(new HSSFClientAnchor(0, 0, 0, 0, s, firstRow, (short) firstColumn, firstRow + 1));
        }
        return new HSSFAutoFilter(this);
    }

    /* access modifiers changed from: protected */
    public HSSFComment findCellComment(int i, int i2) {
        HSSFPatriarch drawingPatriarch = getDrawingPatriarch();
        if (drawingPatriarch == null) {
            drawingPatriarch = createDrawingPatriarch();
        }
        return lookForComment(drawingPatriarch, i, i2);
    }

    private HSSFComment lookForComment(HSSFShapeContainer hSSFShapeContainer, int i, int i2) {
        for (HSSFShape next : hSSFShapeContainer.getChildren()) {
            if (next instanceof HSSFShapeGroup) {
                HSSFComment lookForComment = lookForComment((HSSFShapeContainer) next, i, i2);
                if (lookForComment != null) {
                    return lookForComment;
                }
            } else if (next instanceof HSSFComment) {
                HSSFComment hSSFComment = (HSSFComment) next;
                if (hSSFComment.hasPosition() && hSSFComment.getColumn() == i2 && hSSFComment.getRow() == i) {
                    return hSSFComment;
                }
            } else {
                continue;
            }
        }
        return null;
    }

    public Map<CellAddress, HSSFComment> getCellComments() {
        HSSFPatriarch drawingPatriarch = getDrawingPatriarch();
        if (drawingPatriarch == null) {
            drawingPatriarch = createDrawingPatriarch();
        }
        TreeMap treeMap = new TreeMap();
        findCellCommentLocations(drawingPatriarch, treeMap);
        return treeMap;
    }

    private void findCellCommentLocations(HSSFShapeContainer hSSFShapeContainer, Map<CellAddress, HSSFComment> map) {
        for (HSSFShape next : hSSFShapeContainer.getChildren()) {
            if (next instanceof HSSFShapeGroup) {
                findCellCommentLocations((HSSFShapeGroup) next, map);
            } else if (next instanceof HSSFComment) {
                HSSFComment hSSFComment = (HSSFComment) next;
                if (hSSFComment.hasPosition()) {
                    map.put(new CellAddress(hSSFComment.getRow(), hSSFComment.getColumn()), hSSFComment);
                }
            }
        }
    }

    public CellRangeAddress getRepeatingRows() {
        return getRepeatingRowsOrColums(true);
    }

    public CellRangeAddress getRepeatingColumns() {
        return getRepeatingRowsOrColums(false);
    }

    public void setRepeatingRows(CellRangeAddress cellRangeAddress) {
        setRepeatingRowsAndColumns(cellRangeAddress, getRepeatingColumns());
    }

    public void setRepeatingColumns(CellRangeAddress cellRangeAddress) {
        setRepeatingRowsAndColumns(getRepeatingRows(), cellRangeAddress);
    }

    private void setRepeatingRowsAndColumns(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        int i;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        int sheetIndex = this._workbook.getSheetIndex((Sheet) this);
        int lastRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        int lastColumnIndex = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
        if (cellRangeAddress != null) {
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            if ((firstRow != -1 || lastRow == -1) && firstRow <= lastRow && firstRow >= 0 && firstRow <= lastRowIndex && lastRow >= 0 && lastRow <= lastRowIndex) {
                i2 = firstRow;
                i = lastRow;
            } else {
                throw new IllegalArgumentException("Invalid row range specification");
            }
        } else {
            i2 = -1;
            i = -1;
        }
        if (cellRangeAddress2 != null) {
            int firstColumn = cellRangeAddress2.getFirstColumn();
            int lastColumn = cellRangeAddress2.getLastColumn();
            if ((firstColumn != -1 || lastColumn == -1) && firstColumn <= lastColumn && firstColumn >= 0 && firstColumn <= lastColumnIndex && lastColumn >= 0 && lastColumn <= lastColumnIndex) {
                i3 = lastColumn;
                i4 = firstColumn;
            } else {
                throw new IllegalArgumentException("Invalid column range specification");
            }
        } else {
            i4 = -1;
            i3 = -1;
        }
        short checkExternSheet = this._workbook.getWorkbook().checkExternSheet(sheetIndex);
        boolean z = (cellRangeAddress == null || cellRangeAddress2 == null) ? false : true;
        boolean z2 = cellRangeAddress == null && cellRangeAddress2 == null;
        HSSFName builtInName = this._workbook.getBuiltInName((byte) 7, sheetIndex);
        if (!z2) {
            if (builtInName == null) {
                builtInName = this._workbook.createBuiltInName((byte) 7, sheetIndex);
            }
            HSSFName hSSFName = builtInName;
            ArrayList arrayList2 = new ArrayList();
            if (z) {
                arrayList2.add(new MemFuncPtg(23));
            }
            if (cellRangeAddress2 != null) {
                Area3DPtg area3DPtg = r3;
                Area3DPtg area3DPtg2 = new Area3DPtg(0, lastRowIndex, i4, i3, false, false, false, false, checkExternSheet);
                arrayList = arrayList2;
                arrayList.add(area3DPtg);
            } else {
                arrayList = arrayList2;
            }
            if (cellRangeAddress != null) {
                arrayList.add(new Area3DPtg(i2, i, 0, lastColumnIndex, false, false, false, false, checkExternSheet));
            }
            if (z) {
                arrayList.add(UnionPtg.instance);
            }
            Ptg[] ptgArr = new Ptg[arrayList.size()];
            arrayList.toArray(ptgArr);
            hSSFName.setNameDefinition(ptgArr);
            getPrintSetup().setValidSettings(false);
            setActive(true);
        } else if (builtInName != null) {
            this._workbook.removeName((Name) builtInName);
        }
    }

    private CellRangeAddress getRepeatingRowsOrColums(boolean z) {
        Ptg[] nameDefinition;
        NameRecord builtinNameRecord = getBuiltinNameRecord((byte) 7);
        if (builtinNameRecord == null || (nameDefinition = builtinNameRecord.getNameDefinition()) == null) {
            return null;
        }
        int lastRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        int lastColumnIndex = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
        for (Ptg ptg : nameDefinition) {
            if (ptg instanceof Area3DPtg) {
                Area3DPtg area3DPtg = (Area3DPtg) ptg;
                if (area3DPtg.getFirstColumn() == 0 && area3DPtg.getLastColumn() == lastColumnIndex) {
                    if (z) {
                        return new CellRangeAddress(area3DPtg.getFirstRow(), area3DPtg.getLastRow(), -1, -1);
                    }
                } else if (area3DPtg.getFirstRow() == 0 && area3DPtg.getLastRow() == lastRowIndex && !z) {
                    return new CellRangeAddress(-1, -1, area3DPtg.getFirstColumn(), area3DPtg.getLastColumn());
                }
            }
        }
        return null;
    }

    private NameRecord getBuiltinNameRecord(byte b) {
        int findExistingBuiltinNameRecordIdx = this._workbook.findExistingBuiltinNameRecordIdx(this._workbook.getSheetIndex((Sheet) this), b);
        if (findExistingBuiltinNameRecordIdx == -1) {
            return null;
        }
        return this._workbook.getNameRecord(findExistingBuiltinNameRecordIdx);
    }

    public int getColumnOutlineLevel(int i) {
        return this._sheet.getColumnOutlineLevel(i);
    }

    public CellAddress getActiveCell() {
        return new CellAddress(this._sheet.getActiveCellRow(), this._sheet.getActiveCellCol());
    }

    public void setActiveCell(CellAddress cellAddress) {
        this._sheet.setActiveCellRow(cellAddress.getRow());
        this._sheet.setActiveCellCol((short) cellAddress.getColumn());
    }
}
