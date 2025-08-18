package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Supplier;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CalcCountRecord;
import org.apache.poi.hssf.record.CalcModeRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ColumnInfoRecord;
import org.apache.poi.hssf.record.DefaultColWidthRecord;
import org.apache.poi.hssf.record.DefaultRowHeightRecord;
import org.apache.poi.hssf.record.DeltaRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.GridsetRecord;
import org.apache.poi.hssf.record.GutsRecord;
import org.apache.poi.hssf.record.IterationRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.PaneRecord;
import org.apache.poi.hssf.record.PrintGridlinesRecord;
import org.apache.poi.hssf.record.PrintHeadersRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RefModeRecord;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SCLRecord;
import org.apache.poi.hssf.record.SaveRecalcRecord;
import org.apache.poi.hssf.record.SelectionRecord;
import org.apache.poi.hssf.record.UncalcedRecord;
import org.apache.poi.hssf.record.WSBoolRecord;
import org.apache.poi.hssf.record.WindowTwoRecord;
import org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate;
import org.apache.poi.hssf.record.aggregates.ConditionalFormattingTable;
import org.apache.poi.hssf.record.aggregates.DataValidityTable;
import org.apache.poi.hssf.record.aggregates.MergedCellsTable;
import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.aggregates.RowRecordsAggregate;
import org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.util.Internal;
import org.apache.poi.util.RecordFormatException;

@Internal
public final class InternalSheet {
    public static final short BottomMargin = 3;
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) InternalSheet.class);
    public static final short LeftMargin = 0;
    public static final byte PANE_LOWER_LEFT = 2;
    public static final byte PANE_LOWER_RIGHT = 0;
    public static final byte PANE_UPPER_LEFT = 3;
    public static final byte PANE_UPPER_RIGHT = 1;
    public static final short RightMargin = 1;
    public static final short TopMargin = 2;
    ColumnInfoRecordsAggregate _columnInfos;
    private DataValidityTable _dataValidityTable;
    private DimensionsRecord _dimensions;
    private GutsRecord _gutsRecord;
    protected boolean _isUncalced;
    private final MergedCellsTable _mergedCellsTable;
    private final WorksheetProtectionBlock _protectionBlock;
    private PageSettingsBlock _psBlock;
    private List<RecordBase> _records;
    protected final RowRecordsAggregate _rowsAggregate;
    protected SelectionRecord _selection;
    private ConditionalFormattingTable condFormatting;
    protected DefaultColWidthRecord defaultcolwidth;
    protected DefaultRowHeightRecord defaultrowheight;
    protected GridsetRecord gridset;
    protected PrintGridlinesRecord printGridlines;
    protected PrintHeadersRecord printHeaders;
    private Iterator<RowRecord> rowRecIterator;
    protected WindowTwoRecord windowTwo;

    public static InternalSheet createSheet(RecordStream recordStream) {
        return new InternalSheet(recordStream);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0050 A[LOOP:0: B:8:0x0050->B:11:0x005c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private InternalSheet(org.apache.poi.hssf.model.RecordStream r11) {
        /*
            r10 = this;
            r10.<init>()
            org.apache.poi.hssf.record.DefaultColWidthRecord r0 = new org.apache.poi.hssf.record.DefaultColWidthRecord
            r0.<init>()
            r10.defaultcolwidth = r0
            org.apache.poi.hssf.record.DefaultRowHeightRecord r0 = new org.apache.poi.hssf.record.DefaultRowHeightRecord
            r0.<init>()
            r10.defaultrowheight = r0
            org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock r0 = new org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock
            r0.<init>()
            r10._protectionBlock = r0
            org.apache.poi.hssf.record.aggregates.MergedCellsTable r0 = new org.apache.poi.hssf.record.aggregates.MergedCellsTable
            r0.<init>()
            r10._mergedCellsTable = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 128(0x80, float:1.794E-43)
            r0.<init>(r1)
            r10._records = r0
            int r2 = r11.peekNextSid()
            r3 = 2057(0x809, float:2.882E-42)
            if (r2 != r3) goto L_0x021a
            org.apache.poi.hssf.record.Record r2 = r11.getNext()
            org.apache.poi.hssf.record.BOFRecord r2 = (org.apache.poi.hssf.record.BOFRecord) r2
            int r4 = r2.getType()
            r5 = 16
            if (r4 != r5) goto L_0x003f
            goto L_0x0069
        L_0x003f:
            int r4 = r2.getType()
            r5 = 32
            if (r4 == r5) goto L_0x0069
            int r4 = r2.getType()
            r5 = 64
            if (r4 != r5) goto L_0x0050
            goto L_0x0069
        L_0x0050:
            boolean r10 = r11.hasNext()
            if (r10 == 0) goto L_0x005f
            org.apache.poi.hssf.record.Record r10 = r11.getNext()
            boolean r10 = r10 instanceof org.apache.poi.hssf.record.EOFRecord
            if (r10 != 0) goto L_0x005f
            goto L_0x0050
        L_0x005f:
            org.apache.poi.hssf.model.InternalSheet$UnsupportedBOFType r10 = new org.apache.poi.hssf.model.InternalSheet$UnsupportedBOFType
            int r11 = r2.getType()
            r10.<init>(r11)
            throw r10
        L_0x0069:
            r0.add(r2)
            r2 = 0
            r4 = -1
        L_0x006e:
            boolean r5 = r11.hasNext()
            r6 = 574(0x23e, float:8.04E-43)
            r7 = 1
            if (r5 == 0) goto L_0x01c7
            int r5 = r11.peekNextSid()
            r8 = 432(0x1b0, float:6.05E-43)
            if (r5 == r8) goto L_0x01bb
            r8 = 2169(0x879, float:3.04E-42)
            if (r5 != r8) goto L_0x0085
            goto L_0x01bb
        L_0x0085:
            r8 = 125(0x7d, float:1.75E-43)
            if (r5 != r8) goto L_0x0094
            org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate r5 = new org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate
            r5.<init>((org.apache.poi.hssf.model.RecordStream) r11)
            r10._columnInfos = r5
            r0.add(r5)
            goto L_0x006e
        L_0x0094:
            r8 = 434(0x1b2, float:6.08E-43)
            if (r5 != r8) goto L_0x00a3
            org.apache.poi.hssf.record.aggregates.DataValidityTable r5 = new org.apache.poi.hssf.record.aggregates.DataValidityTable
            r5.<init>(r11)
            r10._dataValidityTable = r5
            r0.add(r5)
            goto L_0x006e
        L_0x00a3:
            boolean r8 = org.apache.poi.hssf.model.RecordOrderer.isRowBlockRecord(r5)
            if (r8 == 0) goto L_0x00d3
            if (r2 != 0) goto L_0x00cb
            org.apache.poi.hssf.model.RowBlocksReader r2 = new org.apache.poi.hssf.model.RowBlocksReader
            r2.<init>(r11)
            org.apache.poi.hssf.record.aggregates.MergedCellsTable r5 = r10._mergedCellsTable
            org.apache.poi.hssf.record.MergeCellsRecord[] r6 = r2.getLooseMergedCells()
            r5.addRecords(r6)
            org.apache.poi.hssf.record.aggregates.RowRecordsAggregate r5 = new org.apache.poi.hssf.record.aggregates.RowRecordsAggregate
            org.apache.poi.hssf.model.RecordStream r6 = r2.getPlainRecordStream()
            org.apache.poi.hssf.record.aggregates.SharedValueManager r2 = r2.getSharedFormulaManager()
            r5.<init>(r6, r2)
            r0.add(r5)
            r2 = r5
            goto L_0x006e
        L_0x00cb:
            org.apache.poi.util.RecordFormatException r10 = new org.apache.poi.util.RecordFormatException
            java.lang.String r11 = "row/cell records found in the wrong place"
            r10.<init>((java.lang.String) r11)
            throw r10
        L_0x00d3:
            boolean r8 = org.apache.poi.hssf.record.aggregates.CustomViewSettingsRecordAggregate.isBeginRecord(r5)
            if (r8 == 0) goto L_0x00e2
            org.apache.poi.hssf.record.aggregates.CustomViewSettingsRecordAggregate r5 = new org.apache.poi.hssf.record.aggregates.CustomViewSettingsRecordAggregate
            r5.<init>(r11)
            r0.add(r5)
            goto L_0x006e
        L_0x00e2:
            boolean r8 = org.apache.poi.hssf.record.aggregates.PageSettingsBlock.isComponentRecord(r5)
            if (r8 == 0) goto L_0x0101
            org.apache.poi.hssf.record.aggregates.PageSettingsBlock r5 = r10._psBlock
            if (r5 != 0) goto L_0x00f7
            org.apache.poi.hssf.record.aggregates.PageSettingsBlock r5 = new org.apache.poi.hssf.record.aggregates.PageSettingsBlock
            r5.<init>(r11)
            r10._psBlock = r5
            r0.add(r5)
            goto L_0x00fa
        L_0x00f7:
            r5.addLateRecords(r11)
        L_0x00fa:
            org.apache.poi.hssf.record.aggregates.PageSettingsBlock r5 = r10._psBlock
            r5.positionRecords(r0)
            goto L_0x006e
        L_0x0101:
            boolean r8 = org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock.isComponentRecord(r5)
            if (r8 == 0) goto L_0x010e
            org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock r5 = r10._protectionBlock
            r5.addRecords(r11)
            goto L_0x006e
        L_0x010e:
            r8 = 229(0xe5, float:3.21E-43)
            if (r5 != r8) goto L_0x0119
            org.apache.poi.hssf.record.aggregates.MergedCellsTable r5 = r10._mergedCellsTable
            r5.read(r11)
            goto L_0x006e
        L_0x0119:
            if (r5 != r3) goto L_0x0125
            org.apache.poi.hssf.record.aggregates.ChartSubstreamRecordAggregate r5 = new org.apache.poi.hssf.record.aggregates.ChartSubstreamRecordAggregate
            r5.<init>(r11)
            spillAggregate(r5, r0)
            goto L_0x006e
        L_0x0125:
            org.apache.poi.hssf.record.Record r8 = r11.getNext()
            r9 = 523(0x20b, float:7.33E-43)
            if (r5 != r9) goto L_0x012f
            goto L_0x006e
        L_0x012f:
            r9 = 94
            if (r5 != r9) goto L_0x0137
            r10._isUncalced = r7
            goto L_0x006e
        L_0x0137:
            r9 = 2152(0x868, float:3.016E-42)
            if (r5 == r9) goto L_0x01b6
            r9 = 2151(0x867, float:3.014E-42)
            if (r5 != r9) goto L_0x0141
            goto L_0x01b6
        L_0x0141:
            r9 = 10
            if (r5 != r9) goto L_0x014a
            r0.add(r8)
            goto L_0x01c7
        L_0x014a:
            r7 = 512(0x200, float:7.175E-43)
            if (r5 != r7) goto L_0x0166
            org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate r4 = r10._columnInfos
            if (r4 != 0) goto L_0x015c
            org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate r4 = new org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate
            r4.<init>()
            r10._columnInfos = r4
            r0.add(r4)
        L_0x015c:
            r4 = r8
            org.apache.poi.hssf.record.DimensionsRecord r4 = (org.apache.poi.hssf.record.DimensionsRecord) r4
            r10._dimensions = r4
            int r4 = r0.size()
            goto L_0x01b1
        L_0x0166:
            r7 = 85
            if (r5 != r7) goto L_0x0170
            r5 = r8
            org.apache.poi.hssf.record.DefaultColWidthRecord r5 = (org.apache.poi.hssf.record.DefaultColWidthRecord) r5
            r10.defaultcolwidth = r5
            goto L_0x01b1
        L_0x0170:
            r7 = 549(0x225, float:7.7E-43)
            if (r5 != r7) goto L_0x017a
            r5 = r8
            org.apache.poi.hssf.record.DefaultRowHeightRecord r5 = (org.apache.poi.hssf.record.DefaultRowHeightRecord) r5
            r10.defaultrowheight = r5
            goto L_0x01b1
        L_0x017a:
            r7 = 43
            if (r5 != r7) goto L_0x0184
            r5 = r8
            org.apache.poi.hssf.record.PrintGridlinesRecord r5 = (org.apache.poi.hssf.record.PrintGridlinesRecord) r5
            r10.printGridlines = r5
            goto L_0x01b1
        L_0x0184:
            r7 = 42
            if (r5 != r7) goto L_0x018e
            r5 = r8
            org.apache.poi.hssf.record.PrintHeadersRecord r5 = (org.apache.poi.hssf.record.PrintHeadersRecord) r5
            r10.printHeaders = r5
            goto L_0x01b1
        L_0x018e:
            r7 = 130(0x82, float:1.82E-43)
            if (r5 != r7) goto L_0x0198
            r5 = r8
            org.apache.poi.hssf.record.GridsetRecord r5 = (org.apache.poi.hssf.record.GridsetRecord) r5
            r10.gridset = r5
            goto L_0x01b1
        L_0x0198:
            r7 = 29
            if (r5 != r7) goto L_0x01a2
            r5 = r8
            org.apache.poi.hssf.record.SelectionRecord r5 = (org.apache.poi.hssf.record.SelectionRecord) r5
            r10._selection = r5
            goto L_0x01b1
        L_0x01a2:
            if (r5 != r6) goto L_0x01aa
            r5 = r8
            org.apache.poi.hssf.record.WindowTwoRecord r5 = (org.apache.poi.hssf.record.WindowTwoRecord) r5
            r10.windowTwo = r5
            goto L_0x01b1
        L_0x01aa:
            if (r5 != r1) goto L_0x01b1
            r5 = r8
            org.apache.poi.hssf.record.GutsRecord r5 = (org.apache.poi.hssf.record.GutsRecord) r5
            r10._gutsRecord = r5
        L_0x01b1:
            r0.add(r8)
            goto L_0x006e
        L_0x01b6:
            r0.add(r8)
            goto L_0x006e
        L_0x01bb:
            org.apache.poi.hssf.record.aggregates.ConditionalFormattingTable r5 = new org.apache.poi.hssf.record.aggregates.ConditionalFormattingTable
            r5.<init>(r11)
            r10.condFormatting = r5
            r0.add(r5)
            goto L_0x006e
        L_0x01c7:
            org.apache.poi.hssf.record.WindowTwoRecord r11 = r10.windowTwo
            if (r11 == 0) goto L_0x0212
            org.apache.poi.hssf.record.DimensionsRecord r11 = r10._dimensions
            if (r11 != 0) goto L_0x01ef
            if (r2 != 0) goto L_0x01d7
            org.apache.poi.hssf.record.aggregates.RowRecordsAggregate r2 = new org.apache.poi.hssf.record.aggregates.RowRecordsAggregate
            r2.<init>()
            goto L_0x01e2
        L_0x01d7:
            org.apache.logging.log4j.Logger r11 = LOGGER
            org.apache.logging.log4j.LogBuilder r11 = r11.atWarn()
            java.lang.String r1 = "DIMENSION record not found even though row/cells present"
            r11.log((java.lang.String) r1)
        L_0x01e2:
            int r4 = r10.findFirstRecordLocBySid(r6)
            org.apache.poi.hssf.record.DimensionsRecord r11 = r2.createDimensions()
            r10._dimensions = r11
            r0.add(r4, r11)
        L_0x01ef:
            if (r2 != 0) goto L_0x01fa
            org.apache.poi.hssf.record.aggregates.RowRecordsAggregate r2 = new org.apache.poi.hssf.record.aggregates.RowRecordsAggregate
            r2.<init>()
            int r4 = r4 + r7
            r0.add(r4, r2)
        L_0x01fa:
            r10._rowsAggregate = r2
            org.apache.poi.hssf.record.aggregates.MergedCellsTable r11 = r10._mergedCellsTable
            org.apache.poi.hssf.model.RecordOrderer.addNewSheetRecord(r0, r11)
            org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock r10 = r10._protectionBlock
            org.apache.poi.hssf.model.RecordOrderer.addNewSheetRecord(r0, r10)
            org.apache.logging.log4j.Logger r10 = LOGGER
            org.apache.logging.log4j.LogBuilder r10 = r10.atDebug()
            java.lang.String r11 = "sheet createSheet (existing file) exited"
            r10.log((java.lang.String) r11)
            return
        L_0x0212:
            org.apache.poi.util.RecordFormatException r10 = new org.apache.poi.util.RecordFormatException
            java.lang.String r11 = "WINDOW2 was not found"
            r10.<init>((java.lang.String) r11)
            throw r10
        L_0x021a:
            org.apache.poi.util.RecordFormatException r10 = new org.apache.poi.util.RecordFormatException
            java.lang.String r11 = "BOF record expected"
            r10.<init>((java.lang.String) r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.model.InternalSheet.<init>(org.apache.poi.hssf.model.RecordStream):void");
    }

    private static void spillAggregate(RecordAggregate recordAggregate, List<RecordBase> list) {
        recordAggregate.visitContainedRecords(new InternalSheet$$ExternalSyntheticLambda0(list));
    }

    public static class UnsupportedBOFType extends RecordFormatException {
        private final int type;

        protected UnsupportedBOFType(int i) {
            super("BOF not of a supported type, found " + i);
            this.type = i;
        }

        public int getType() {
            return this.type;
        }
    }

    private static final class RecordCloner implements RecordAggregate.RecordVisitor {
        private final List<Record> _destList;

        public RecordCloner(List<Record> list) {
            this._destList = list;
        }

        public void visitRecord(Record record) {
            this._destList.add(record.copy());
        }
    }

    public InternalSheet cloneSheet() {
        ArrayList arrayList = new ArrayList(this._records.size());
        for (int i = 0; i < this._records.size(); i++) {
            Object obj = this._records.get(i);
            if (obj instanceof RecordAggregate) {
                ((RecordAggregate) obj).visitContainedRecords(new RecordCloner(arrayList));
            } else {
                if (obj instanceof EscherAggregate) {
                    obj = new DrawingRecord();
                }
                arrayList.add(((Record) obj).copy());
            }
        }
        return createSheet(new RecordStream(arrayList, 0));
    }

    public static InternalSheet createSheet() {
        return new InternalSheet();
    }

    private InternalSheet() {
        this.defaultcolwidth = new DefaultColWidthRecord();
        this.defaultrowheight = new DefaultRowHeightRecord();
        WorksheetProtectionBlock worksheetProtectionBlock = new WorksheetProtectionBlock();
        this._protectionBlock = worksheetProtectionBlock;
        MergedCellsTable mergedCellsTable = new MergedCellsTable();
        this._mergedCellsTable = mergedCellsTable;
        ArrayList arrayList = new ArrayList(32);
        Logger logger = LOGGER;
        logger.atDebug().log("Sheet createsheet from scratch called");
        arrayList.add(createBOF());
        arrayList.add(createCalcMode());
        arrayList.add(createCalcCount());
        arrayList.add(createRefMode());
        arrayList.add(createIteration());
        arrayList.add(createDelta());
        arrayList.add(createSaveRecalc());
        PrintHeadersRecord createPrintHeaders = createPrintHeaders();
        this.printHeaders = createPrintHeaders;
        arrayList.add(createPrintHeaders);
        PrintGridlinesRecord createPrintGridlines = createPrintGridlines();
        this.printGridlines = createPrintGridlines;
        arrayList.add(createPrintGridlines);
        GridsetRecord createGridset = createGridset();
        this.gridset = createGridset;
        arrayList.add(createGridset);
        GutsRecord createGuts = createGuts();
        this._gutsRecord = createGuts;
        arrayList.add(createGuts);
        DefaultRowHeightRecord createDefaultRowHeight = createDefaultRowHeight();
        this.defaultrowheight = createDefaultRowHeight;
        arrayList.add(createDefaultRowHeight);
        arrayList.add(createWSBool());
        PageSettingsBlock pageSettingsBlock = new PageSettingsBlock();
        this._psBlock = pageSettingsBlock;
        arrayList.add(pageSettingsBlock);
        arrayList.add(worksheetProtectionBlock);
        DefaultColWidthRecord createDefaultColWidth = createDefaultColWidth();
        this.defaultcolwidth = createDefaultColWidth;
        arrayList.add(createDefaultColWidth);
        ColumnInfoRecordsAggregate columnInfoRecordsAggregate = new ColumnInfoRecordsAggregate();
        arrayList.add(columnInfoRecordsAggregate);
        this._columnInfos = columnInfoRecordsAggregate;
        DimensionsRecord createDimensions = createDimensions();
        this._dimensions = createDimensions;
        arrayList.add(createDimensions);
        RowRecordsAggregate rowRecordsAggregate = new RowRecordsAggregate();
        this._rowsAggregate = rowRecordsAggregate;
        arrayList.add(rowRecordsAggregate);
        WindowTwoRecord createWindowTwo = createWindowTwo();
        this.windowTwo = createWindowTwo;
        arrayList.add(createWindowTwo);
        SelectionRecord createSelection = createSelection();
        this._selection = createSelection;
        arrayList.add(createSelection);
        arrayList.add(mergedCellsTable);
        arrayList.add(EOFRecord.instance);
        this._records = arrayList;
        logger.atDebug().log("Sheet createsheet from scratch exit");
    }

    public RowRecordsAggregate getRowsAggregate() {
        return this._rowsAggregate;
    }

    private MergedCellsTable getMergedRecords() {
        return this._mergedCellsTable;
    }

    public void updateFormulasAfterCellShift(FormulaShifter formulaShifter, int i) {
        getRowsAggregate().updateFormulasAfterRowShift(formulaShifter, i);
        if (this.condFormatting != null) {
            getConditionalFormattingTable().updateFormulasAfterCellShift(formulaShifter, i);
        }
    }

    public int addMergedRegion(int i, int i2, int i3, int i4) {
        if (i3 < i) {
            throw new IllegalArgumentException("The 'to' row (" + i3 + ") must not be less than the 'from' row (" + i + ")");
        } else if (i4 >= i2) {
            MergedCellsTable mergedRecords = getMergedRecords();
            mergedRecords.addArea(i, i2, i3, i4);
            return mergedRecords.getNumberOfMergedRegions() - 1;
        } else {
            throw new IllegalArgumentException("The 'to' col (" + i4 + ") must not be less than the 'from' col (" + i2 + ")");
        }
    }

    public void removeMergedRegion(int i) {
        MergedCellsTable mergedRecords = getMergedRecords();
        if (i < mergedRecords.getNumberOfMergedRegions()) {
            mergedRecords.remove(i);
        }
    }

    public CellRangeAddress getMergedRegionAt(int i) {
        MergedCellsTable mergedRecords = getMergedRecords();
        if (i >= mergedRecords.getNumberOfMergedRegions()) {
            return null;
        }
        return mergedRecords.get(i);
    }

    public int getNumMergedRegions() {
        return getMergedRecords().getNumberOfMergedRegions();
    }

    public ConditionalFormattingTable getConditionalFormattingTable() {
        if (this.condFormatting == null) {
            ConditionalFormattingTable conditionalFormattingTable = new ConditionalFormattingTable();
            this.condFormatting = conditionalFormattingTable;
            RecordOrderer.addNewSheetRecord(this._records, conditionalFormattingTable);
        }
        return this.condFormatting;
    }

    public void setDimensions(int i, short s, int i2, short s2) {
        Logger logger = LOGGER;
        logger.atDebug().log("Sheet.setDimensions");
        logger.atDebug().log((Supplier<Message>) new InternalSheet$$ExternalSyntheticLambda1(i, s, i2, s2));
        this._dimensions.setFirstCol(s);
        this._dimensions.setFirstRow(i);
        this._dimensions.setLastCol(s2);
        this._dimensions.setLastRow(i2);
        logger.atDebug().log("Sheet.setDimensions exiting");
    }

    static /* synthetic */ Message lambda$setDimensions$1(int i, short s, int i2, short s2) {
        return new SimpleMessage("firstrow" + i + "firstcol" + s + "lastrow" + i2 + "lastcol" + s2);
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor, int i) {
        RecordAggregate.PositionTrackingVisitor positionTrackingVisitor = new RecordAggregate.PositionTrackingVisitor(recordVisitor, i);
        boolean z = false;
        for (int i2 = 0; i2 < this._records.size(); i2++) {
            RecordBase recordBase = this._records.get(i2);
            if (recordBase instanceof RecordAggregate) {
                ((RecordAggregate) recordBase).visitContainedRecords(positionTrackingVisitor);
            } else if (recordBase instanceof Record) {
                positionTrackingVisitor.visitRecord((Record) recordBase);
            }
            if ((recordBase instanceof BOFRecord) && !z) {
                if (this._isUncalced) {
                    positionTrackingVisitor.visitRecord(new UncalcedRecord());
                }
                if (this._rowsAggregate != null) {
                    positionTrackingVisitor.visitRecord(this._rowsAggregate.createIndexRecord(positionTrackingVisitor.getPosition(), getSizeOfInitialSheetRecords(i2)));
                }
                z = true;
            }
        }
    }

    private int getSizeOfInitialSheetRecords(int i) {
        int i2 = 0;
        for (int i3 = i + 1; i3 < this._records.size(); i3++) {
            RecordBase recordBase = this._records.get(i3);
            if (recordBase instanceof RowRecordsAggregate) {
                break;
            }
            i2 += recordBase.getRecordSize();
        }
        return this._isUncalced ? i2 + UncalcedRecord.getStaticRecordSize() : i2;
    }

    public void addValueRecord(int i, CellValueRecordInterface cellValueRecordInterface) {
        LOGGER.atDebug().log("add value record row{}", (Object) Unbox.box(i));
        DimensionsRecord dimensionsRecord = this._dimensions;
        if (cellValueRecordInterface.getColumn() >= dimensionsRecord.getLastCol()) {
            dimensionsRecord.setLastCol((short) (cellValueRecordInterface.getColumn() + 1));
        }
        if (cellValueRecordInterface.getColumn() < dimensionsRecord.getFirstCol()) {
            dimensionsRecord.setFirstCol(cellValueRecordInterface.getColumn());
        }
        this._rowsAggregate.insertCell(cellValueRecordInterface);
    }

    public void removeValueRecord(int i, CellValueRecordInterface cellValueRecordInterface) {
        LOGGER.atDebug().log("remove value record row {}", (Object) Unbox.box(i));
        this._rowsAggregate.removeCell(cellValueRecordInterface);
    }

    public void replaceValueRecord(CellValueRecordInterface cellValueRecordInterface) {
        LOGGER.atDebug().log("replaceValueRecord ");
        this._rowsAggregate.removeCell(cellValueRecordInterface);
        this._rowsAggregate.insertCell(cellValueRecordInterface);
    }

    public void addRow(RowRecord rowRecord) {
        Logger logger = LOGGER;
        logger.atDebug().log("addRow ");
        DimensionsRecord dimensionsRecord = this._dimensions;
        if (rowRecord.getRowNumber() >= dimensionsRecord.getLastRow()) {
            dimensionsRecord.setLastRow(rowRecord.getRowNumber() + 1);
        }
        if (rowRecord.getRowNumber() < dimensionsRecord.getFirstRow()) {
            dimensionsRecord.setFirstRow(rowRecord.getRowNumber());
        }
        RowRecord row = this._rowsAggregate.getRow(rowRecord.getRowNumber());
        if (row != null) {
            this._rowsAggregate.removeRow(row);
        }
        this._rowsAggregate.insertRow(rowRecord);
        logger.atDebug().log("exit addRow");
    }

    public void removeRow(RowRecord rowRecord) {
        this._rowsAggregate.removeRow(rowRecord);
    }

    public Iterator<CellValueRecordInterface> getCellValueIterator() {
        return this._rowsAggregate.getCellValueIterator();
    }

    public RowRecord getNextRow() {
        if (this.rowRecIterator == null) {
            this.rowRecIterator = this._rowsAggregate.getIterator();
        }
        if (!this.rowRecIterator.hasNext()) {
            return null;
        }
        return this.rowRecIterator.next();
    }

    public RowRecord getRow(int i) {
        return this._rowsAggregate.getRow(i);
    }

    static BOFRecord createBOF() {
        BOFRecord bOFRecord = new BOFRecord();
        bOFRecord.setVersion(BOFRecord.VERSION);
        bOFRecord.setType(16);
        bOFRecord.setBuild(3515);
        bOFRecord.setBuildYear(BOFRecord.BUILD_YEAR);
        bOFRecord.setHistoryBitMask(193);
        bOFRecord.setRequiredVersion(6);
        return bOFRecord;
    }

    private static CalcModeRecord createCalcMode() {
        CalcModeRecord calcModeRecord = new CalcModeRecord();
        calcModeRecord.setCalcMode(1);
        return calcModeRecord;
    }

    private static CalcCountRecord createCalcCount() {
        CalcCountRecord calcCountRecord = new CalcCountRecord();
        calcCountRecord.setIterations(100);
        return calcCountRecord;
    }

    private static RefModeRecord createRefMode() {
        RefModeRecord refModeRecord = new RefModeRecord();
        refModeRecord.setMode(1);
        return refModeRecord;
    }

    private static IterationRecord createIteration() {
        return new IterationRecord(false);
    }

    private static DeltaRecord createDelta() {
        return new DeltaRecord(0.001d);
    }

    private static SaveRecalcRecord createSaveRecalc() {
        SaveRecalcRecord saveRecalcRecord = new SaveRecalcRecord();
        saveRecalcRecord.setRecalc(true);
        return saveRecalcRecord;
    }

    private static PrintHeadersRecord createPrintHeaders() {
        PrintHeadersRecord printHeadersRecord = new PrintHeadersRecord();
        printHeadersRecord.setPrintHeaders(false);
        return printHeadersRecord;
    }

    private static PrintGridlinesRecord createPrintGridlines() {
        PrintGridlinesRecord printGridlinesRecord = new PrintGridlinesRecord();
        printGridlinesRecord.setPrintGridlines(false);
        return printGridlinesRecord;
    }

    private static GridsetRecord createGridset() {
        GridsetRecord gridsetRecord = new GridsetRecord();
        gridsetRecord.setGridset(true);
        return gridsetRecord;
    }

    private static GutsRecord createGuts() {
        GutsRecord gutsRecord = new GutsRecord();
        gutsRecord.setLeftRowGutter(0);
        gutsRecord.setTopColGutter(0);
        gutsRecord.setRowLevelMax(0);
        gutsRecord.setColLevelMax(0);
        return gutsRecord;
    }

    private GutsRecord getGutsRecord() {
        if (this._gutsRecord == null) {
            GutsRecord createGuts = createGuts();
            RecordOrderer.addNewSheetRecord(this._records, createGuts);
            this._gutsRecord = createGuts;
        }
        return this._gutsRecord;
    }

    private static DefaultRowHeightRecord createDefaultRowHeight() {
        DefaultRowHeightRecord defaultRowHeightRecord = new DefaultRowHeightRecord();
        defaultRowHeightRecord.setOptionFlags(0);
        defaultRowHeightRecord.setRowHeight(255);
        return defaultRowHeightRecord;
    }

    private static WSBoolRecord createWSBool() {
        WSBoolRecord wSBoolRecord = new WSBoolRecord();
        wSBoolRecord.setWSBool1((byte) 4);
        wSBoolRecord.setWSBool2((byte) -63);
        return wSBoolRecord;
    }

    private static DefaultColWidthRecord createDefaultColWidth() {
        DefaultColWidthRecord defaultColWidthRecord = new DefaultColWidthRecord();
        defaultColWidthRecord.setColWidth(8);
        return defaultColWidthRecord;
    }

    public int getDefaultColumnWidth() {
        return this.defaultcolwidth.getColWidth();
    }

    public boolean isGridsPrinted() {
        if (this.gridset == null) {
            this.gridset = createGridset();
            this._records.add(findFirstRecordLocBySid(10), this.gridset);
        }
        return !this.gridset.getGridset();
    }

    public void setGridsPrinted(boolean z) {
        this.gridset.setGridset(!z);
    }

    public void setDefaultColumnWidth(int i) {
        this.defaultcolwidth.setColWidth(i);
    }

    public void setDefaultRowHeight(short s) {
        this.defaultrowheight.setRowHeight(s);
        this.defaultrowheight.setOptionFlags(1);
    }

    public short getDefaultRowHeight() {
        return this.defaultrowheight.getRowHeight();
    }

    public int getColumnWidth(int i) {
        ColumnInfoRecord findColumnInfo = this._columnInfos.findColumnInfo(i);
        if (findColumnInfo != null) {
            return findColumnInfo.getColumnWidth();
        }
        return this.defaultcolwidth.getColWidth() * 256;
    }

    public short getXFIndexForColAt(short s) {
        ColumnInfoRecord findColumnInfo = this._columnInfos.findColumnInfo(s);
        if (findColumnInfo != null) {
            return (short) findColumnInfo.getXFIndex();
        }
        return 15;
    }

    public void setColumnWidth(int i, int i2) {
        if (i2 <= 65280) {
            setColumn(i, (Short) null, Integer.valueOf(i2), (Integer) null, (Boolean) null, (Boolean) null);
            return;
        }
        throw new IllegalArgumentException("The maximum column width for an individual cell is 255 characters.");
    }

    public boolean isColumnHidden(int i) {
        ColumnInfoRecord findColumnInfo = this._columnInfos.findColumnInfo(i);
        if (findColumnInfo == null) {
            return false;
        }
        return findColumnInfo.getHidden();
    }

    public void setColumnHidden(int i, boolean z) {
        setColumn(i, (Short) null, (Integer) null, (Integer) null, Boolean.valueOf(z), (Boolean) null);
    }

    public void setDefaultColumnStyle(int i, int i2) {
        setColumn(i, Short.valueOf((short) i2), (Integer) null, (Integer) null, (Boolean) null, (Boolean) null);
    }

    private void setColumn(int i, Short sh, Integer num, Integer num2, Boolean bool, Boolean bool2) {
        this._columnInfos.setColumn(i, sh, num, num2, bool, bool2);
    }

    public void groupColumnRange(int i, int i2, boolean z) {
        this._columnInfos.groupColumnRange(i, i2, z);
        int maxOutlineLevel = this._columnInfos.getMaxOutlineLevel();
        GutsRecord gutsRecord = getGutsRecord();
        gutsRecord.setColLevelMax((short) (maxOutlineLevel + 1));
        if (maxOutlineLevel == 0) {
            gutsRecord.setTopColGutter(0);
        } else {
            gutsRecord.setTopColGutter((short) (((maxOutlineLevel - 1) * 12) + 29));
        }
    }

    private static DimensionsRecord createDimensions() {
        DimensionsRecord dimensionsRecord = new DimensionsRecord();
        dimensionsRecord.setFirstCol(0);
        dimensionsRecord.setLastRow(1);
        dimensionsRecord.setFirstRow(0);
        dimensionsRecord.setLastCol(1);
        return dimensionsRecord;
    }

    private static WindowTwoRecord createWindowTwo() {
        WindowTwoRecord windowTwoRecord = new WindowTwoRecord();
        windowTwoRecord.setOptions(1718);
        windowTwoRecord.setTopRow(0);
        windowTwoRecord.setLeftCol(0);
        windowTwoRecord.setHeaderColor(64);
        windowTwoRecord.setPageBreakZoom(0);
        windowTwoRecord.setNormalZoom(0);
        return windowTwoRecord;
    }

    private static SelectionRecord createSelection() {
        return new SelectionRecord(0, 0);
    }

    public short getTopRow() {
        WindowTwoRecord windowTwoRecord = this.windowTwo;
        if (windowTwoRecord == null) {
            return 0;
        }
        return windowTwoRecord.getTopRow();
    }

    public void setTopRow(short s) {
        WindowTwoRecord windowTwoRecord = this.windowTwo;
        if (windowTwoRecord != null) {
            windowTwoRecord.setTopRow(s);
        }
    }

    public void setLeftCol(short s) {
        WindowTwoRecord windowTwoRecord = this.windowTwo;
        if (windowTwoRecord != null) {
            windowTwoRecord.setLeftCol(s);
        }
    }

    public short getLeftCol() {
        WindowTwoRecord windowTwoRecord = this.windowTwo;
        if (windowTwoRecord == null) {
            return 0;
        }
        return windowTwoRecord.getLeftCol();
    }

    public int getActiveCellRow() {
        SelectionRecord selectionRecord = this._selection;
        if (selectionRecord == null) {
            return 0;
        }
        return selectionRecord.getActiveCellRow();
    }

    public void setActiveCellRow(int i) {
        SelectionRecord selectionRecord = this._selection;
        if (selectionRecord != null) {
            selectionRecord.setActiveCellRow(i);
        }
    }

    public short getActiveCellCol() {
        SelectionRecord selectionRecord = this._selection;
        if (selectionRecord == null) {
            return 0;
        }
        return (short) selectionRecord.getActiveCellCol();
    }

    public void setActiveCellCol(short s) {
        SelectionRecord selectionRecord = this._selection;
        if (selectionRecord != null) {
            selectionRecord.setActiveCellCol(s);
        }
    }

    public List<RecordBase> getRecords() {
        return this._records;
    }

    public GridsetRecord getGridsetRecord() {
        return this.gridset;
    }

    public Record findFirstRecordBySid(short s) {
        int findFirstRecordLocBySid = findFirstRecordLocBySid(s);
        if (findFirstRecordLocBySid < 0) {
            return null;
        }
        return (Record) this._records.get(findFirstRecordLocBySid);
    }

    public void setSCLRecord(SCLRecord sCLRecord) {
        int findFirstRecordLocBySid = findFirstRecordLocBySid(160);
        if (findFirstRecordLocBySid == -1) {
            this._records.add(findFirstRecordLocBySid(WindowTwoRecord.sid) + 1, sCLRecord);
            return;
        }
        this._records.set(findFirstRecordLocBySid, sCLRecord);
    }

    public int findFirstRecordLocBySid(short s) {
        int size = this._records.size();
        for (int i = 0; i < size; i++) {
            RecordBase recordBase = this._records.get(i);
            if ((recordBase instanceof Record) && ((Record) recordBase).getSid() == s) {
                return i;
            }
        }
        return -1;
    }

    public WindowTwoRecord getWindowTwo() {
        return this.windowTwo;
    }

    public PrintGridlinesRecord getPrintGridlines() {
        return this.printGridlines;
    }

    public void setPrintGridlines(PrintGridlinesRecord printGridlinesRecord) {
        this.printGridlines = printGridlinesRecord;
    }

    public PrintHeadersRecord getPrintHeaders() {
        return this.printHeaders;
    }

    public void setPrintHeaders(PrintHeadersRecord printHeadersRecord) {
        this.printHeaders = printHeadersRecord;
    }

    public void setSelected(boolean z) {
        this.windowTwo.setSelected(z);
    }

    public void createFreezePane(int i, int i2, int i3, int i4) {
        int findFirstRecordLocBySid = findFirstRecordLocBySid(65);
        if (findFirstRecordLocBySid != -1) {
            this._records.remove(findFirstRecordLocBySid);
        }
        if (i == 0 && i2 == 0) {
            this.windowTwo.setFreezePanes(false);
            this.windowTwo.setFreezePanesNoSplit(false);
            SelectionRecord selectionRecord = (SelectionRecord) findFirstRecordBySid(29);
            if (selectionRecord != null) {
                selectionRecord.setPane((byte) 3);
                return;
            }
            return;
        }
        int findFirstRecordLocBySid2 = findFirstRecordLocBySid(WindowTwoRecord.sid);
        PaneRecord paneRecord = new PaneRecord();
        paneRecord.setX((short) i);
        paneRecord.setY((short) i2);
        paneRecord.setTopRow((short) i3);
        paneRecord.setLeftColumn((short) i4);
        if (i2 == 0) {
            paneRecord.setTopRow(0);
            paneRecord.setActivePane(1);
        } else if (i == 0) {
            paneRecord.setLeftColumn(0);
            paneRecord.setActivePane(2);
        } else {
            paneRecord.setActivePane(0);
        }
        this._records.add(findFirstRecordLocBySid2 + 1, paneRecord);
        this.windowTwo.setFreezePanes(true);
        this.windowTwo.setFreezePanesNoSplit(true);
        SelectionRecord selectionRecord2 = (SelectionRecord) findFirstRecordBySid(29);
        if (selectionRecord2 != null) {
            selectionRecord2.setPane((byte) paneRecord.getActivePane());
        }
    }

    public void createSplitPane(int i, int i2, int i3, int i4, int i5) {
        int findFirstRecordLocBySid = findFirstRecordLocBySid(65);
        if (findFirstRecordLocBySid != -1) {
            this._records.remove(findFirstRecordLocBySid);
        }
        int findFirstRecordLocBySid2 = findFirstRecordLocBySid(WindowTwoRecord.sid);
        PaneRecord paneRecord = new PaneRecord();
        paneRecord.setX((short) i);
        paneRecord.setY((short) i2);
        paneRecord.setTopRow((short) i3);
        paneRecord.setLeftColumn((short) i4);
        paneRecord.setActivePane((short) i5);
        this._records.add(findFirstRecordLocBySid2 + 1, paneRecord);
        this.windowTwo.setFreezePanes(false);
        this.windowTwo.setFreezePanesNoSplit(false);
        SelectionRecord selectionRecord = (SelectionRecord) findFirstRecordBySid(29);
        if (selectionRecord != null) {
            selectionRecord.setPane((byte) 0);
        }
    }

    public PaneInformation getPaneInformation() {
        PaneRecord paneRecord = (PaneRecord) findFirstRecordBySid(65);
        if (paneRecord == null) {
            return null;
        }
        return new PaneInformation(paneRecord.getX(), paneRecord.getY(), paneRecord.getTopRow(), paneRecord.getLeftColumn(), (byte) paneRecord.getActivePane(), this.windowTwo.getFreezePanes());
    }

    public SelectionRecord getSelection() {
        return this._selection;
    }

    public void setSelection(SelectionRecord selectionRecord) {
        this._selection = selectionRecord;
    }

    public WorksheetProtectionBlock getProtectionBlock() {
        return this._protectionBlock;
    }

    public void setDisplayGridlines(boolean z) {
        this.windowTwo.setDisplayGridlines(z);
    }

    public boolean isDisplayGridlines() {
        return this.windowTwo.getDisplayGridlines();
    }

    public void setDisplayFormulas(boolean z) {
        this.windowTwo.setDisplayFormulas(z);
    }

    public boolean isDisplayFormulas() {
        return this.windowTwo.getDisplayFormulas();
    }

    public void setDisplayRowColHeadings(boolean z) {
        this.windowTwo.setDisplayRowColHeadings(z);
    }

    public boolean isDisplayRowColHeadings() {
        return this.windowTwo.getDisplayRowColHeadings();
    }

    public void setPrintRowColHeadings(boolean z) {
        this.windowTwo.setDisplayRowColHeadings(z);
    }

    public boolean isPrintRowColHeadings() {
        return this.windowTwo.getDisplayRowColHeadings();
    }

    public boolean getUncalced() {
        return this._isUncalced;
    }

    public void setUncalced(boolean z) {
        this._isUncalced = z;
    }

    public int aggregateDrawingRecords(DrawingManager2 drawingManager2, boolean z) {
        int findFirstRecordLocBySid = findFirstRecordLocBySid(236);
        if (!(findFirstRecordLocBySid == -1)) {
            EscherAggregate.createAggregate(getRecords(), findFirstRecordLocBySid);
            return findFirstRecordLocBySid;
        } else if (!z) {
            return -1;
        } else {
            EscherAggregate escherAggregate = new EscherAggregate(true);
            int findFirstRecordLocBySid2 = findFirstRecordLocBySid(EscherAggregate.sid);
            if (findFirstRecordLocBySid2 == -1) {
                findFirstRecordLocBySid2 = findFirstRecordLocBySid(WindowTwoRecord.sid);
            } else {
                getRecords().remove(findFirstRecordLocBySid2);
            }
            getRecords().add(findFirstRecordLocBySid2, escherAggregate);
            return findFirstRecordLocBySid2;
        }
    }

    public void preSerialize() {
        for (RecordBase next : getRecords()) {
            if (next instanceof EscherAggregate) {
                next.getRecordSize();
            }
        }
    }

    public PageSettingsBlock getPageSettings() {
        if (this._psBlock == null) {
            PageSettingsBlock pageSettingsBlock = new PageSettingsBlock();
            this._psBlock = pageSettingsBlock;
            RecordOrderer.addNewSheetRecord(this._records, pageSettingsBlock);
        }
        return this._psBlock;
    }

    public void setColumnGroupCollapsed(int i, boolean z) {
        if (z) {
            this._columnInfos.collapseColumn(i);
        } else {
            this._columnInfos.expandColumn(i);
        }
    }

    public void groupRowRange(int i, int i2, boolean z) {
        while (i <= i2) {
            RowRecord row = getRow(i);
            if (row == null) {
                row = RowRecordsAggregate.createRow(i);
                addRow(row);
            }
            short outlineLevel = row.getOutlineLevel();
            row.setOutlineLevel((short) Math.min(7, Math.max(0, z ? outlineLevel + 1 : outlineLevel - 1)));
            i++;
        }
        recalcRowGutter();
    }

    private void recalcRowGutter() {
        Iterator<RowRecord> iterator = this._rowsAggregate.getIterator();
        int i = 0;
        while (iterator.hasNext()) {
            i = Math.max(iterator.next().getOutlineLevel(), i);
        }
        GutsRecord gutsRecord = getGutsRecord();
        gutsRecord.setRowLevelMax((short) (i + 1));
        gutsRecord.setLeftRowGutter((short) ((i * 12) + 29));
    }

    public DataValidityTable getOrCreateDataValidityTable() {
        if (this._dataValidityTable == null) {
            DataValidityTable dataValidityTable = new DataValidityTable();
            RecordOrderer.addNewSheetRecord(this._records, dataValidityTable);
            this._dataValidityTable = dataValidityTable;
        }
        return this._dataValidityTable;
    }

    public NoteRecord[] getNoteRecords() {
        ArrayList arrayList = new ArrayList();
        for (int size = this._records.size() - 1; size >= 0; size--) {
            RecordBase recordBase = this._records.get(size);
            if (recordBase instanceof NoteRecord) {
                arrayList.add((NoteRecord) recordBase);
            }
        }
        if (arrayList.size() < 1) {
            return NoteRecord.EMPTY_ARRAY;
        }
        NoteRecord[] noteRecordArr = new NoteRecord[arrayList.size()];
        arrayList.toArray(noteRecordArr);
        return noteRecordArr;
    }

    public int getColumnOutlineLevel(int i) {
        return this._columnInfos.getOutlineLevel(i);
    }

    public int getMinColumnIndex() {
        return this._columnInfos.getMinColumnIndex();
    }

    public int getMaxColumnIndex() {
        return this._columnInfos.getMaxColumnIndex();
    }
}
