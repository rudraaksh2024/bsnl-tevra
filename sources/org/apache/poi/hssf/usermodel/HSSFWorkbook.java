package org.apache.poi.hssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIDocument;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalSheet;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.model.WorkbookRecordList;
import org.apache.poi.hssf.record.AbstractEscherHolderRecord;
import org.apache.poi.hssf.record.DrawingGroupRecord;
import org.apache.poi.hssf.record.FilePassRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.RecalcIdRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RefModeRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Configurator;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.Removal;

public final class HSSFWorkbook extends POIDocument implements Workbook {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final int DEFAULT_MAX_IMAGE_LENGTH = 50000000;
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFWorkbook.SheetInitialCapacity", 3);
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) HSSFWorkbook.class);
    private static int MAX_IMAGE_LENGTH = 50000000;
    private static int MAX_RECORD_LENGTH = 100000;
    private static final int MAX_STYLES = 4030;
    protected List<HSSFSheet> _sheets;
    private final UDFFinder _udfFinder;
    private Map<Integer, HSSFFont> fonts;
    private HSSFDataFormat formatter;
    private Row.MissingCellPolicy missingCellPolicy;
    private final ArrayList<HSSFName> names;
    private boolean preserveNodes;
    private InternalWorkbook workbook;

    public static HSSFWorkbook create(InternalWorkbook internalWorkbook) {
        return new HSSFWorkbook(internalWorkbook);
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public static void setMaxImageLength(int i) {
        MAX_IMAGE_LENGTH = i;
    }

    public static int getMaxImageLength() {
        return MAX_IMAGE_LENGTH;
    }

    public HSSFWorkbook() {
        this(InternalWorkbook.createWorkbook());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private HSSFWorkbook(InternalWorkbook internalWorkbook) {
        super((DirectoryNode) null);
        DirectoryNode directoryNode = null;
        this.missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
        this._udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
        this.workbook = internalWorkbook;
        int i = INITIAL_CAPACITY;
        this._sheets = new ArrayList(i);
        this.names = new ArrayList<>(i);
    }

    public HSSFWorkbook(POIFSFileSystem pOIFSFileSystem) throws IOException {
        this(pOIFSFileSystem, true);
    }

    public HSSFWorkbook(POIFSFileSystem pOIFSFileSystem, boolean z) throws IOException {
        this(pOIFSFileSystem.getRoot(), pOIFSFileSystem, z);
    }

    public static String getWorkbookDirEntryName(DirectoryNode directoryNode) {
        for (String next : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
            if (directoryNode.hasEntry(next)) {
                return next;
            }
        }
        if (directoryNode.hasEntry(Decryptor.DEFAULT_POIFS_ENTRY)) {
            throw new EncryptedDocumentException("The supplied spreadsheet seems to be an Encrypted .xlsx file. It must be decrypted before use by XSSF, it cannot be used by HSSF");
        } else if (directoryNode.hasEntry(InternalWorkbook.OLD_WORKBOOK_DIR_ENTRY_NAME)) {
            throw new OldExcelFormatException("The supplied spreadsheet seems to be Excel 5.0/7.0 (BIFF5) format. POI only supports BIFF8 format (from Excel versions 97/2000/XP/2003)");
        } else if (directoryNode.hasEntry("WordDocument")) {
            throw new IllegalArgumentException("The document is really a DOC file");
        } else {
            throw new IllegalArgumentException("The supplied POIFSFileSystem does not contain a BIFF8 'Workbook' entry. Is it really an excel file? Had: " + directoryNode.getEntryNames());
        }
    }

    public HSSFWorkbook(DirectoryNode directoryNode, POIFSFileSystem pOIFSFileSystem, boolean z) throws IOException {
        this(directoryNode, z);
    }

    public HSSFWorkbook(DirectoryNode directoryNode, boolean z) throws IOException {
        super(directoryNode);
        this.missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
        this._udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
        String workbookDirEntryName = getWorkbookDirEntryName(directoryNode);
        this.preserveNodes = z;
        if (!z) {
            clearDirectory();
        }
        int i = INITIAL_CAPACITY;
        this._sheets = new ArrayList(i);
        this.names = new ArrayList<>(i);
        List<Record> createRecords = RecordFactory.createRecords(directoryNode.createDocumentInputStream(workbookDirEntryName));
        InternalWorkbook createWorkbook = InternalWorkbook.createWorkbook(createRecords);
        this.workbook = createWorkbook;
        setPropertiesFromWorkbook(createWorkbook);
        int numRecords = this.workbook.getNumRecords();
        convertLabelRecords(createRecords, numRecords);
        RecordStream recordStream = new RecordStream(createRecords, numRecords);
        while (recordStream.hasNext()) {
            try {
                this._sheets.add(new HSSFSheet(this, InternalSheet.createSheet(recordStream)));
            } catch (InternalSheet.UnsupportedBOFType e) {
                LOGGER.atWarn().log("Unsupported BOF found of type {}", (Object) Unbox.box(e.getType()));
            }
        }
        for (int i2 = 0; i2 < this.workbook.getNumNames(); i2++) {
            NameRecord nameRecord = this.workbook.getNameRecord(i2);
            this.names.add(new HSSFName(this, nameRecord, this.workbook.getNameCommentRecord(nameRecord)));
        }
    }

    public HSSFWorkbook(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    public HSSFWorkbook(InputStream inputStream, boolean z) throws IOException {
        this(new POIFSFileSystem(inputStream).getRoot(), z);
    }

    private void setPropertiesFromWorkbook(InternalWorkbook internalWorkbook) {
        this.workbook = internalWorkbook;
    }

    private void convertLabelRecords(List<Record> list, int i) {
        LOGGER.atDebug().log("convertLabelRecords called");
        while (i < list.size()) {
            Record record = list.get(i);
            if (record.getSid() == 516) {
                LabelRecord labelRecord = (LabelRecord) record;
                list.remove(i);
                LabelSSTRecord labelSSTRecord = new LabelSSTRecord();
                int addSSTString = this.workbook.addSSTString(new UnicodeString(labelRecord.getValue()));
                labelSSTRecord.setRow(labelRecord.getRow());
                labelSSTRecord.setColumn(labelRecord.getColumn());
                labelSSTRecord.setXFIndex(labelRecord.getXFIndex());
                labelSSTRecord.setSSTIndex(addSSTString);
                list.add(i, labelSSTRecord);
            }
            i++;
        }
        LOGGER.atDebug().log("convertLabelRecords exit");
    }

    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this.missingCellPolicy;
    }

    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy2) {
        this.missingCellPolicy = missingCellPolicy2;
    }

    public void setSheetOrder(String str, int i) {
        int sheetIndex = getSheetIndex(str);
        List<HSSFSheet> list = this._sheets;
        list.add(i, list.remove(sheetIndex));
        this.workbook.setSheetOrder(str, i);
        FormulaShifter createForSheetShift = FormulaShifter.createForSheetShift(sheetIndex, i);
        for (HSSFSheet sheet : this._sheets) {
            sheet.getSheet().updateFormulasAfterCellShift(createForSheetShift, -1);
        }
        this.workbook.updateNamesAfterCellShift(createForSheetShift);
        updateNamedRangesAfterSheetReorder(sheetIndex, i);
        updateActiveSheetAfterSheetReorder(sheetIndex, i);
    }

    private void updateNamedRangesAfterSheetReorder(int i, int i2) {
        Iterator<HSSFName> it = this.names.iterator();
        while (it.hasNext()) {
            HSSFName next = it.next();
            int sheetIndex = next.getSheetIndex();
            if (sheetIndex != -1) {
                if (sheetIndex == i) {
                    next.setSheetIndex(i2);
                } else if (i2 <= sheetIndex && sheetIndex < i) {
                    next.setSheetIndex(sheetIndex + 1);
                } else if (i < sheetIndex && sheetIndex <= i2) {
                    next.setSheetIndex(sheetIndex - 1);
                }
            }
        }
    }

    private void updateActiveSheetAfterSheetReorder(int i, int i2) {
        int activeSheetIndex = getActiveSheetIndex();
        if (activeSheetIndex == i) {
            setActiveSheet(i2);
        } else if (activeSheetIndex < i && activeSheetIndex < i2) {
        } else {
            if (activeSheetIndex > i && activeSheetIndex > i2) {
                return;
            }
            if (i2 > i) {
                setActiveSheet(activeSheetIndex - 1);
            } else {
                setActiveSheet(activeSheetIndex + 1);
            }
        }
    }

    private void validateSheetIndex(int i) {
        int size = this._sheets.size() - 1;
        if (i < 0 || i > size) {
            String str = "(0.." + size + ")";
            if (size == -1) {
                str = "(no sheets)";
            }
            throw new IllegalArgumentException("Sheet index (" + i + ") is out of range " + str);
        }
    }

    public void setSelectedTab(int i) {
        validateSheetIndex(i);
        int size = this._sheets.size();
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 < size) {
                HSSFSheet sheetAt = getSheetAt(i2);
                if (i2 != i) {
                    z = false;
                }
                sheetAt.setSelected(z);
                i2++;
            } else {
                this.workbook.getWindowOne().setNumSelectedTabs(1);
                return;
            }
        }
    }

    public void setSelectedTabs(int[] iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        setSelectedTabs((Collection<Integer>) arrayList);
    }

    public void setSelectedTabs(Collection<Integer> collection) {
        for (Integer intValue : collection) {
            validateSheetIndex(intValue.intValue());
        }
        HashSet hashSet = new HashSet(collection);
        int size = this._sheets.size();
        for (int i = 0; i < size; i++) {
            getSheetAt(i).setSelected(hashSet.contains(Integer.valueOf(i)));
        }
        this.workbook.getWindowOne().setNumSelectedTabs((short) hashSet.size());
    }

    public Collection<Integer> getSelectedTabs() {
        ArrayList arrayList = new ArrayList();
        int size = this._sheets.size();
        for (int i = 0; i < size; i++) {
            if (getSheetAt(i).isSelected()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public void setActiveSheet(int i) {
        validateSheetIndex(i);
        int size = this._sheets.size();
        int i2 = 0;
        while (i2 < size) {
            getSheetAt(i2).setActive(i2 == i);
            i2++;
        }
        this.workbook.getWindowOne().setActiveSheetIndex(i);
    }

    public int getActiveSheetIndex() {
        return this.workbook.getWindowOne().getActiveSheetIndex();
    }

    public void setFirstVisibleTab(int i) {
        this.workbook.getWindowOne().setFirstVisibleTab(i);
    }

    public int getFirstVisibleTab() {
        return this.workbook.getWindowOne().getFirstVisibleTab();
    }

    public void setSheetName(int i, String str) {
        if (str == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        } else if (!this.workbook.doesContainsSheetName(str, i)) {
            validateSheetIndex(i);
            this.workbook.setSheetName(i, str);
        } else {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + str + "'");
        }
    }

    public String getSheetName(int i) {
        validateSheetIndex(i);
        return this.workbook.getSheetName(i);
    }

    public boolean isHidden() {
        return this.workbook.getWindowOne().getHidden();
    }

    public void setHidden(boolean z) {
        this.workbook.getWindowOne().setHidden(z);
    }

    public boolean isSheetHidden(int i) {
        validateSheetIndex(i);
        return this.workbook.isSheetHidden(i);
    }

    public boolean isSheetVeryHidden(int i) {
        validateSheetIndex(i);
        return this.workbook.isSheetVeryHidden(i);
    }

    public SheetVisibility getSheetVisibility(int i) {
        return this.workbook.getSheetVisibility(i);
    }

    public void setSheetHidden(int i, boolean z) {
        setSheetVisibility(i, z ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    public void setSheetVisibility(int i, SheetVisibility sheetVisibility) {
        validateSheetIndex(i);
        this.workbook.setSheetHidden(i, sheetVisibility);
    }

    public int getSheetIndex(String str) {
        return this.workbook.getSheetIndex(str);
    }

    public int getSheetIndex(Sheet sheet) {
        return this._sheets.indexOf(sheet);
    }

    public HSSFSheet createSheet() {
        HSSFSheet hSSFSheet = new HSSFSheet(this);
        this._sheets.add(hSSFSheet);
        boolean z = true;
        this.workbook.setSheetName(this._sheets.size() - 1, "Sheet" + (this._sheets.size() - 1));
        if (this._sheets.size() != 1) {
            z = false;
        }
        hSSFSheet.setSelected(z);
        hSSFSheet.setActive(z);
        return hSSFSheet;
    }

    public HSSFSheet cloneSheet(int i) {
        validateSheetIndex(i);
        String sheetName = this.workbook.getSheetName(i);
        HSSFSheet cloneSheet = this._sheets.get(i).cloneSheet(this);
        cloneSheet.setSelected(false);
        cloneSheet.setActive(false);
        String uniqueSheetName = getUniqueSheetName(sheetName);
        int size = this._sheets.size();
        this._sheets.add(cloneSheet);
        this.workbook.setSheetName(size, uniqueSheetName);
        int findExistingBuiltinNameRecordIdx = findExistingBuiltinNameRecordIdx(i, (byte) 13);
        if (findExistingBuiltinNameRecordIdx != -1) {
            this.names.add(new HSSFName(this, this.workbook.cloneFilter(findExistingBuiltinNameRecordIdx, size)));
        }
        return cloneSheet;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0091 A[LOOP:0: B:12:0x0032->B:20:0x0091, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0090 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getUniqueSheetName(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 40
            int r0 = r9.lastIndexOf(r0)
            r1 = 0
            java.lang.String r2 = ")"
            r3 = 2
            if (r0 <= 0) goto L_0x0031
            boolean r4 = r9.endsWith(r2)
            if (r4 == 0) goto L_0x0031
            int r4 = r0 + 1
            int r5 = r9.length()
            int r5 = r5 + -1
            java.lang.String r4 = r9.substring(r4, r5)
            java.lang.String r4 = r4.trim()     // Catch:{ NumberFormatException -> 0x0031 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x0031 }
            int r4 = r4 + 1
            java.lang.String r0 = r9.substring(r1, r0)     // Catch:{ NumberFormatException -> 0x0032 }
            java.lang.String r9 = r0.trim()     // Catch:{ NumberFormatException -> 0x0032 }
            goto L_0x0032
        L_0x0031:
            r4 = r3
        L_0x0032:
            int r0 = r4 + 1
            java.lang.String r4 = java.lang.Integer.toString(r4)
            int r5 = r9.length()
            int r6 = r4.length()
            int r5 = r5 + r6
            int r5 = r5 + r3
            r6 = 31
            if (r5 >= r6) goto L_0x0062
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r6 = " ("
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            goto L_0x0087
        L_0x0062:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int r7 = r4.length()
            int r6 = r6 - r7
            int r6 = r6 - r3
            java.lang.String r6 = r9.substring(r1, r6)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "("
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
        L_0x0087:
            org.apache.poi.hssf.model.InternalWorkbook r5 = r8.workbook
            int r5 = r5.getSheetIndex(r4)
            r6 = -1
            if (r5 != r6) goto L_0x0091
            return r4
        L_0x0091:
            r4 = r0
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFWorkbook.getUniqueSheetName(java.lang.String):java.lang.String");
    }

    public HSSFSheet createSheet(String str) {
        if (str == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        } else if (!this.workbook.doesContainsSheetName(str, this._sheets.size())) {
            boolean z = false;
            if (str.length() > 31) {
                String substring = str.substring(0, 31);
                LOGGER.atWarn().log("Sheet '{}' will be added with a trimmed name '{}' for MS Excel compliance.", str, substring);
                str = substring;
            }
            HSSFSheet hSSFSheet = new HSSFSheet(this);
            this.workbook.setSheetName(this._sheets.size(), str);
            this._sheets.add(hSSFSheet);
            if (this._sheets.size() == 1) {
                z = true;
            }
            hSSFSheet.setSelected(z);
            hSSFSheet.setActive(z);
            return hSSFSheet;
        } else {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + str + "'");
        }
    }

    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator();
    }

    public Spliterator<Sheet> spliterator() {
        return this._sheets.spliterator();
    }

    private final class SheetIterator<T extends Sheet> implements Iterator<T> {
        private final Iterator<T> it;

        public SheetIterator() {
            this.it = HSSFWorkbook.this._sheets.iterator();
        }

        public boolean hasNext() {
            return this.it.hasNext();
        }

        public T next() throws NoSuchElementException {
            return (Sheet) this.it.next();
        }

        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on HSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }

    public int getNumberOfSheets() {
        return this._sheets.size();
    }

    private HSSFSheet[] getSheets() {
        HSSFSheet[] hSSFSheetArr = new HSSFSheet[this._sheets.size()];
        this._sheets.toArray(hSSFSheetArr);
        return hSSFSheetArr;
    }

    public HSSFSheet getSheetAt(int i) {
        validateSheetIndex(i);
        return this._sheets.get(i);
    }

    public HSSFSheet getSheet(String str) {
        HSSFSheet hSSFSheet = null;
        for (int i = 0; i < this._sheets.size(); i++) {
            if (this.workbook.getSheetName(i).equalsIgnoreCase(str)) {
                hSSFSheet = this._sheets.get(i);
            }
        }
        return hSSFSheet;
    }

    public void removeSheetAt(int i) {
        validateSheetIndex(i);
        boolean isSelected = getSheetAt(i).isSelected();
        this._sheets.remove(i);
        this.workbook.removeSheet(i);
        int size = this._sheets.size();
        if (size >= 1) {
            int i2 = i >= size ? size - 1 : i;
            if (isSelected) {
                boolean z = false;
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        break;
                    } else if (getSheetAt(i3).isSelected()) {
                        z = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (!z) {
                    setSelectedTab(i2);
                }
            }
            int activeSheetIndex = getActiveSheetIndex();
            if (activeSheetIndex == i) {
                setActiveSheet(i2);
            } else if (activeSheetIndex > i) {
                setActiveSheet(activeSheetIndex - 1);
            }
        }
    }

    public void setBackupFlag(boolean z) {
        this.workbook.getBackupRecord().setBackup(z ? (short) 1 : 0);
    }

    public boolean getBackupFlag() {
        return this.workbook.getBackupRecord().getBackup() != 0;
    }

    /* access modifiers changed from: package-private */
    public int findExistingBuiltinNameRecordIdx(int i, byte b) {
        int i2 = 0;
        while (i2 < this.names.size()) {
            NameRecord nameRecord = this.workbook.getNameRecord(i2);
            if (nameRecord == null) {
                throw new RuntimeException("Unable to find all defined names to iterate over");
            } else if (nameRecord.isBuiltInName() && nameRecord.getBuiltInName() == b && nameRecord.getSheetNumber() - 1 == i) {
                return i2;
            } else {
                i2++;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public HSSFName createBuiltInName(byte b, int i) {
        HSSFName hSSFName = new HSSFName(this, this.workbook.createBuiltInName(b, i + 1), (NameCommentRecord) null);
        this.names.add(hSSFName);
        return hSSFName;
    }

    /* access modifiers changed from: package-private */
    public HSSFName getBuiltInName(byte b, int i) {
        int findExistingBuiltinNameRecordIdx = findExistingBuiltinNameRecordIdx(i, b);
        if (findExistingBuiltinNameRecordIdx < 0) {
            return null;
        }
        return this.names.get(findExistingBuiltinNameRecordIdx);
    }

    public HSSFFont createFont() {
        this.workbook.createNewFont();
        int numberOfFonts = getNumberOfFonts() - 1;
        if (numberOfFonts > 3) {
            numberOfFonts++;
        }
        if (numberOfFonts < 32767) {
            return getFontAt(numberOfFonts);
        }
        throw new IllegalArgumentException("Maximum number of fonts was exceeded");
    }

    public HSSFFont findFont(boolean z, short s, short s2, String str, boolean z2, boolean z3, short s3, byte b) {
        int numberOfFonts = getNumberOfFonts();
        for (int i = 0; i <= numberOfFonts; i++) {
            if (i != 4) {
                HSSFFont fontAt = getFontAt(i);
                if (fontAt.getBold() == z && fontAt.getColor() == s && fontAt.getFontHeight() == s2 && fontAt.getFontName().equals(str) && fontAt.getItalic() == z2 && fontAt.getStrikeout() == z3 && fontAt.getTypeOffset() == s3 && fontAt.getUnderline() == b) {
                    return fontAt;
                }
            }
        }
        return null;
    }

    public int getNumberOfFonts() {
        return this.workbook.getNumberOfFontRecords();
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getNumberOfFontsAsInt() {
        return getNumberOfFonts();
    }

    public HSSFFont getFontAt(int i) {
        if (this.fonts == null) {
            this.fonts = new HashMap();
        }
        Integer valueOf = Integer.valueOf(i);
        if (this.fonts.containsKey(valueOf)) {
            return this.fonts.get(valueOf);
        }
        HSSFFont hSSFFont = new HSSFFont(i, this.workbook.getFontRecordAt(i));
        this.fonts.put(valueOf, hSSFFont);
        return hSSFFont;
    }

    /* access modifiers changed from: package-private */
    public void resetFontCache() {
        this.fonts = new HashMap();
    }

    public HSSFCellStyle createCellStyle() {
        if (this.workbook.getNumExFormats() != MAX_STYLES) {
            return new HSSFCellStyle((short) (getNumCellStyles() - 1), this.workbook.createCellXF(), this);
        }
        throw new IllegalStateException("The maximum number of cell styles was exceeded. You can define up to 4000 styles in a .xls workbook");
    }

    public int getNumCellStyles() {
        return this.workbook.getNumExFormats();
    }

    public HSSFCellStyle getCellStyleAt(int i) {
        return new HSSFCellStyle((short) i, this.workbook.getExFormatAt(i), this);
    }

    public void close() throws IOException {
        super.close();
    }

    public void write() throws IOException {
        validateInPlaceWritePossible();
        DirectoryNode directory = getDirectory();
        new POIFSDocument((DocumentNode) directory.getEntry(getWorkbookDirEntryName(directory))).replaceContents(new UnsynchronizedByteArrayInputStream(getBytes()));
        writeProperties();
        directory.getFileSystem().writeFilesystem();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.io.File r2) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = org.apache.poi.poifs.filesystem.POIFSFileSystem.create(r2)
            r1.write((org.apache.poi.poifs.filesystem.POIFSFileSystem) r2)     // Catch:{ all -> 0x0010 }
            r2.writeFilesystem()     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x000f
            r2.close()
        L_0x000f:
            return
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFWorkbook.write(java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.io.OutputStream r2) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r0.<init>()
            r1.write((org.apache.poi.poifs.filesystem.POIFSFileSystem) r0)     // Catch:{ all -> 0x000f }
            r0.writeFilesystem(r2)     // Catch:{ all -> 0x000f }
            r0.close()
            return
        L_0x000f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFWorkbook.write(java.io.OutputStream):void");
    }

    private void write(POIFSFileSystem pOIFSFileSystem) throws IOException {
        ArrayList arrayList = new ArrayList(1);
        pOIFSFileSystem.createDocument(new UnsynchronizedByteArrayInputStream(getBytes()), "Workbook");
        writeProperties(pOIFSFileSystem, arrayList);
        if (this.preserveNodes) {
            arrayList.addAll(InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES);
            arrayList.addAll(Arrays.asList(new String[]{DocumentSummaryInformation.DEFAULT_STREAM_NAME, SummaryInformation.DEFAULT_STREAM_NAME, getEncryptedPropertyStreamName()}));
            EntryUtils.copyNodes((DirectoryEntry) new FilteringDirectoryNode(getDirectory(), arrayList), (DirectoryEntry) new FilteringDirectoryNode(pOIFSFileSystem.getRoot(), arrayList));
            pOIFSFileSystem.getRoot().setStorageClsid(getDirectory().getStorageClsid());
        }
    }

    private static final class SheetRecordCollector implements RecordAggregate.RecordVisitor {
        private final List<Record> _list = new ArrayList(128);
        private int _totalSize = 0;

        public int getTotalSize() {
            return this._totalSize;
        }

        public void visitRecord(Record record) {
            this._list.add(record);
            this._totalSize += record.getRecordSize();
        }

        public int serialize(int i, byte[] bArr) {
            int i2 = 0;
            for (Record serialize : this._list) {
                i2 += serialize.serialize(i + i2, bArr);
            }
            return i2;
        }
    }

    public byte[] getBytes() {
        LOGGER.atDebug().log("HSSFWorkbook.getBytes()");
        HSSFSheet[] sheets = getSheets();
        int length = sheets.length;
        updateEncryptionInfo();
        this.workbook.preSerialize();
        int i = 0;
        for (HSSFSheet hSSFSheet : sheets) {
            hSSFSheet.getSheet().preSerialize();
            hSSFSheet.preSerialize();
        }
        int size = this.workbook.getSize();
        SheetRecordCollector[] sheetRecordCollectorArr = new SheetRecordCollector[length];
        for (int i2 = 0; i2 < length; i2++) {
            this.workbook.setSheetBof(i2, size);
            SheetRecordCollector sheetRecordCollector = new SheetRecordCollector();
            sheets[i2].getSheet().visitContainedRecords(sheetRecordCollector, size);
            size += sheetRecordCollector.getTotalSize();
            sheetRecordCollectorArr[i2] = sheetRecordCollector;
        }
        byte[] bArr = new byte[size];
        int serialize = this.workbook.serialize(0, bArr);
        while (i < length) {
            SheetRecordCollector sheetRecordCollector2 = sheetRecordCollectorArr[i];
            int serialize2 = sheetRecordCollector2.serialize(serialize, bArr);
            if (serialize2 == sheetRecordCollector2.getTotalSize()) {
                serialize += serialize2;
                i++;
            } else {
                throw new IllegalStateException("Actual serialized sheet size (" + serialize2 + ") differs from pre-calculated size (" + sheetRecordCollector2.getTotalSize() + ") for sheet (" + i + ")");
            }
        }
        encryptBytes(bArr);
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public void encryptBytes(byte[] bArr) {
        EncryptionInfo encryptionInfo = getEncryptionInfo();
        if (encryptionInfo != null) {
            Encryptor encryptor = encryptionInfo.getEncryptor();
            LittleEndianByteArrayInputStream littleEndianByteArrayInputStream = new LittleEndianByteArrayInputStream(bArr, 0);
            LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream = new LittleEndianByteArrayOutputStream(bArr, 0);
            encryptor.setChunkSize(1024);
            byte[] bArr2 = new byte[1024];
            try {
                ChunkedCipherOutputStream dataStream = encryptor.getDataStream(littleEndianByteArrayOutputStream, 0);
                int i = 0;
                while (i < bArr.length) {
                    IOUtils.readFully(littleEndianByteArrayInputStream, bArr2, 0, 4);
                    int uShort = LittleEndian.getUShort(bArr2, 0);
                    int uShort2 = LittleEndian.getUShort(bArr2, 2);
                    boolean isNeverEncryptedRecord = Biff8DecryptingStream.isNeverEncryptedRecord(uShort);
                    dataStream.setNextRecordSize(uShort2, isNeverEncryptedRecord);
                    dataStream.writePlain(bArr2, 0, 4);
                    if (uShort == 133) {
                        byte[] safelyAllocate = IOUtils.safelyAllocate((long) uShort2, MAX_RECORD_LENGTH);
                        littleEndianByteArrayInputStream.readFully(safelyAllocate);
                        dataStream.writePlain(safelyAllocate, 0, 4);
                        dataStream.write(safelyAllocate, 4, uShort2 - 4);
                    } else {
                        int i2 = uShort2;
                        while (i2 > 0) {
                            int min = Math.min(i2, 1024);
                            littleEndianByteArrayInputStream.readFully(bArr2, 0, min);
                            if (isNeverEncryptedRecord) {
                                dataStream.writePlain(bArr2, 0, min);
                            } else {
                                dataStream.write(bArr2, 0, min);
                            }
                            i2 -= min;
                        }
                    }
                    i += uShort2 + 4;
                }
                dataStream.close();
            } catch (Exception e) {
                throw new EncryptedDocumentException((Throwable) e);
            }
        }
    }

    @Internal
    public InternalWorkbook getWorkbook() {
        return this.workbook;
    }

    public int getNumberOfNames() {
        return this.names.size();
    }

    public HSSFName getName(String str) {
        int nameIndex = getNameIndex(str);
        if (nameIndex < 0) {
            return null;
        }
        return this.names.get(nameIndex);
    }

    public List<HSSFName> getNames(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<HSSFName> it = this.names.iterator();
        while (it.hasNext()) {
            HSSFName next = it.next();
            if (next.getNameName().equals(str)) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: package-private */
    public HSSFName getNameAt(int i) {
        int size = this.names.size();
        if (size < 1) {
            throw new IllegalStateException("There are no defined names in this workbook");
        } else if (i >= 0 && i <= size) {
            return this.names.get(i);
        } else {
            throw new IllegalArgumentException("Specified name index " + i + " is outside the allowable range (0.." + (size - 1) + ").");
        }
    }

    public List<HSSFName> getAllNames() {
        return Collections.unmodifiableList(this.names);
    }

    public NameRecord getNameRecord(int i) {
        return getWorkbook().getNameRecord(i);
    }

    public String getNameName(int i) {
        return getNameAt(i).getNameName();
    }

    public void setPrintArea(int i, String str) {
        int i2 = i + 1;
        NameRecord specificBuiltinRecord = this.workbook.getSpecificBuiltinRecord((byte) 6, i2);
        if (specificBuiltinRecord == null) {
            specificBuiltinRecord = this.workbook.createBuiltInName((byte) 6, i2);
        }
        String[] split = COMMA_PATTERN.split(str);
        StringBuilder sb = new StringBuilder(32);
        for (int i3 = 0; i3 < split.length; i3++) {
            if (i3 > 0) {
                sb.append(',');
            }
            SheetNameFormatter.appendFormat(sb, getSheetName(i));
            sb.append('!');
            sb.append(split[i3]);
        }
        specificBuiltinRecord.setNameDefinition(HSSFFormulaParser.parse(sb.toString(), this, FormulaType.NAMEDRANGE, i));
    }

    public void setPrintArea(int i, int i2, int i3, int i4, int i5) {
        setPrintArea(i, new CellReference(i4, i2, true, true).formatAsString() + ParameterizedMessage.ERROR_MSG_SEPARATOR + new CellReference(i5, i3, true, true).formatAsString());
    }

    public String getPrintArea(int i) {
        NameRecord specificBuiltinRecord = this.workbook.getSpecificBuiltinRecord((byte) 6, i + 1);
        if (specificBuiltinRecord == null) {
            return null;
        }
        return HSSFFormulaParser.toFormulaString(this, specificBuiltinRecord.getNameDefinition());
    }

    public void removePrintArea(int i) {
        getWorkbook().removeBuiltinRecord((byte) 6, i + 1);
    }

    public HSSFName createName() {
        HSSFName hSSFName = new HSSFName(this, this.workbook.createName());
        this.names.add(hSSFName);
        return hSSFName;
    }

    public CellReferenceType getCellReferenceType() {
        RefModeRecord refModeRecord;
        for (HSSFSheet sheet : this._sheets) {
            Iterator<RecordBase> it = sheet.getSheet().getRecords().iterator();
            while (true) {
                if (!it.hasNext()) {
                    refModeRecord = null;
                    break;
                }
                RecordBase next = it.next();
                if (next instanceof RefModeRecord) {
                    refModeRecord = (RefModeRecord) next;
                    break;
                }
            }
            if (refModeRecord != null) {
                if (refModeRecord.getMode() == 0) {
                    return CellReferenceType.R1C1;
                }
                if (refModeRecord.getMode() == 1) {
                    return CellReferenceType.A1;
                }
            }
        }
        return CellReferenceType.UNKNOWN;
    }

    public void setCellReferenceType(CellReferenceType cellReferenceType) {
        RefModeRecord refModeRecord;
        for (HSSFSheet sheet : this._sheets) {
            List<RecordBase> records = sheet.getSheet().getRecords();
            Iterator<RecordBase> it = records.iterator();
            while (true) {
                if (!it.hasNext()) {
                    refModeRecord = null;
                    break;
                }
                RecordBase next = it.next();
                if (next instanceof RefModeRecord) {
                    refModeRecord = (RefModeRecord) next;
                    break;
                }
            }
            if (cellReferenceType == CellReferenceType.R1C1) {
                if (refModeRecord == null) {
                    refModeRecord = new RefModeRecord();
                    records.add(records.size() - 1, refModeRecord);
                }
                refModeRecord.setMode(0);
            } else if (cellReferenceType == CellReferenceType.A1) {
                if (refModeRecord == null) {
                    refModeRecord = new RefModeRecord();
                    records.add(records.size() - 1, refModeRecord);
                }
                refModeRecord.setMode(1);
            } else if (refModeRecord != null) {
                records.remove(refModeRecord);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getNameIndex(String str) {
        for (int i = 0; i < this.names.size(); i++) {
            if (getNameName(i).equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getNameIndex(HSSFName hSSFName) {
        for (int i = 0; i < this.names.size(); i++) {
            if (hSSFName == this.names.get(i)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void removeName(int i) {
        this.names.remove(i);
        this.workbook.removeName(i);
    }

    public HSSFDataFormat createDataFormat() {
        if (this.formatter == null) {
            this.formatter = new HSSFDataFormat(this.workbook);
        }
        return this.formatter;
    }

    public void removeName(Name name) {
        removeName(getNameIndex((HSSFName) name));
    }

    public HSSFPalette getCustomPalette() {
        return new HSSFPalette(this.workbook.getCustomPalette());
    }

    public void insertChartRecord() {
        this.workbook.getRecords().add(this.workbook.findFirstRecordLocBySid(SSTRecord.sid), new UnknownRecord(235, new byte[]{IntersectionPtg.sid, 0, 0, -16, 82, 0, 0, 0, 0, 0, 6, -16, 24, 0, 0, 0, 1, 8, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, TarConstants.LF_CHR, 0, 11, -16, UnaryPlusPtg.sid, 0, 0, 0, -65, 0, 8, 0, 8, 0, -127, 1, 9, 0, 0, 8, -64, 1, Ptg.CLASS_ARRAY, 0, 0, 8, Ptg.CLASS_ARRAY, 0, IntPtg.sid, -15, UnionPtg.sid, 0, 0, 0, 13, 0, 0, 8, 12, 0, 0, 8, StringPtg.sid, 0, 0, 8, -9, 0, 0, UnionPtg.sid}));
    }

    public void dumpDrawingGroupRecords(boolean z) {
        DrawingGroupRecord drawingGroupRecord = (DrawingGroupRecord) this.workbook.findFirstRecordBySid(DrawingGroupRecord.sid);
        if (drawingGroupRecord != null) {
            drawingGroupRecord.decode();
            List<EscherRecord> escherRecords = drawingGroupRecord.getEscherRecords();
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out, Charset.defaultCharset()));
            for (EscherRecord next : escherRecords) {
                if (z) {
                    System.out.println(next);
                } else {
                    next.display(printWriter, 0);
                }
            }
            printWriter.flush();
        }
    }

    /* access modifiers changed from: package-private */
    public void initDrawings() {
        if (this.workbook.findDrawingGroup() != null) {
            for (HSSFSheet drawingPatriarch : this._sheets) {
                drawingPatriarch.getDrawingPatriarch();
            }
            return;
        }
        this.workbook.createDrawingGroup();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.apache.poi.ddf.EscherMetafileBlip} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: org.apache.poi.ddf.EscherBitmapBlip} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: org.apache.poi.ddf.EscherMetafileBlip} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: org.apache.poi.ddf.EscherMetafileBlip} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addPicture(byte[] r8, int r9) {
        /*
            r7 = this;
            r7.initDrawings()
            byte[] r0 = org.apache.commons.codec.digest.DigestUtils.md5((byte[]) r8)
            r1 = 2
            r2 = 0
            if (r9 == r1) goto L_0x0035
            r3 = 3
            if (r9 == r3) goto L_0x0023
            org.apache.poi.ddf.EscherBitmapBlip r3 = new org.apache.poi.ddf.EscherBitmapBlip
            r3.<init>()
            r3.setUID(r0)
            r4 = -1
            r3.setMarker(r4)
            r3.setPictureData(r8)
            int r8 = r8.length
            int r8 = r8 + 25
            r4 = 255(0xff, float:3.57E-43)
            goto L_0x004b
        L_0x0023:
            org.apache.poi.poifs.filesystem.FileMagic r3 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((byte[]) r8)
            org.apache.poi.poifs.filesystem.FileMagic r4 = org.apache.poi.poifs.filesystem.FileMagic.WMF
            if (r3 != r4) goto L_0x0035
            int r3 = r8.length
            r4 = 22
            int r3 = r3 - r4
            int r5 = MAX_IMAGE_LENGTH
            byte[] r8 = org.apache.poi.util.IOUtils.safelyClone(r8, r4, r3, r5)
        L_0x0035:
            org.apache.poi.ddf.EscherMetafileBlip r3 = new org.apache.poi.ddf.EscherMetafileBlip
            r3.<init>()
            r3.setUID(r0)
            r3.setPictureData(r8)
            r8 = -2
            r3.setFilter(r8)
            int r8 = r3.getCompressedSize()
            int r8 = r8 + 58
            r4 = r2
        L_0x004b:
            short r5 = org.apache.poi.ddf.EscherBlipRecord.RECORD_ID_START
            int r5 = r5 + r9
            short r5 = (short) r5
            r3.setRecordId(r5)
            switch(r9) {
                case 2: goto L_0x0088;
                case 3: goto L_0x0082;
                case 4: goto L_0x007c;
                case 5: goto L_0x0076;
                case 6: goto L_0x0070;
                case 7: goto L_0x006a;
                default: goto L_0x0055;
            }
        L_0x0055:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Unexpected picture format: "
            r8.<init>(r0)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x006a:
            r5 = 31360(0x7a80, float:4.3945E-41)
            r3.setOptions(r5)
            goto L_0x008d
        L_0x0070:
            r5 = 28160(0x6e00, float:3.946E-41)
            r3.setOptions(r5)
            goto L_0x008d
        L_0x0076:
            r5 = 18080(0x46a0, float:2.5335E-41)
            r3.setOptions(r5)
            goto L_0x008d
        L_0x007c:
            r5 = 21536(0x5420, float:3.0178E-41)
            r3.setOptions(r5)
            goto L_0x008d
        L_0x0082:
            r5 = 8544(0x2160, float:1.1973E-41)
            r3.setOptions(r5)
            goto L_0x008d
        L_0x0088:
            r5 = 15680(0x3d40, float:2.1972E-41)
            r3.setOptions(r5)
        L_0x008d:
            org.apache.poi.ddf.EscherBSERecord r5 = new org.apache.poi.ddf.EscherBSERecord
            r5.<init>()
            short r6 = org.apache.poi.ddf.EscherBSERecord.RECORD_ID
            r5.setRecordId(r6)
            int r6 = r9 << 4
            r1 = r1 | r6
            short r1 = (short) r1
            r5.setOptions(r1)
            byte r9 = (byte) r9
            r5.setBlipTypeMacOS(r9)
            r5.setBlipTypeWin32(r9)
            r5.setUid(r0)
            r5.setTag(r4)
            r5.setSize(r8)
            r5.setRef(r2)
            r5.setOffset(r2)
            r5.setBlipRecord(r3)
            org.apache.poi.hssf.model.InternalWorkbook r7 = r7.workbook
            int r7 = r7.addBSERecord(r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFWorkbook.addPicture(byte[], int):int");
    }

    public List<HSSFPictureData> getAllPictures() {
        ArrayList arrayList = new ArrayList();
        for (Record next : this.workbook.getRecords()) {
            if (next instanceof AbstractEscherHolderRecord) {
                AbstractEscherHolderRecord abstractEscherHolderRecord = (AbstractEscherHolderRecord) next;
                abstractEscherHolderRecord.decode();
                searchForPictures(abstractEscherHolderRecord.getEscherRecords(), arrayList);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private void searchForPictures(List<EscherRecord> list, List<HSSFPictureData> list2) {
        EscherBlipRecord blipRecord;
        for (EscherRecord next : list) {
            if ((next instanceof EscherBSERecord) && (blipRecord = ((EscherBSERecord) next).getBlipRecord()) != null) {
                list2.add(new HSSFPictureData(blipRecord));
            }
            searchForPictures(next.getChildRecords(), list2);
        }
    }

    static Map<String, ClassID> getOleMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("PowerPoint Document", ClassIDPredefined.POWERPOINT_V8.getClassID());
        for (String put : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
            hashMap.put(put, ClassIDPredefined.EXCEL_V7_WORKBOOK.getClassID());
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addOlePackage(org.apache.poi.poifs.filesystem.POIFSFileSystem r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.io.IOException {
        /*
            r4 = this;
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = r5.getRoot()
            java.util.Map r1 = getOleMap()
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0010:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0031
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r0.hasEntry(r3)
            if (r3 == 0) goto L_0x0010
            java.lang.Object r1 = r2.getValue()
            org.apache.poi.hpsf.ClassID r1 = (org.apache.poi.hpsf.ClassID) r1
            r0.setStorageClsid(r1)
        L_0x0031:
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            r5.writeFilesystem(r0)     // Catch:{ all -> 0x0045 }
            byte[] r5 = r0.toByteArray()     // Catch:{ all -> 0x0045 }
            int r4 = r4.addOlePackage((byte[]) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)     // Catch:{ all -> 0x0045 }
            r0.close()
            return r4
        L_0x0045:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r5 = move-exception
            r0.close()     // Catch:{ all -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r6 = move-exception
            r4.addSuppressed(r6)
        L_0x0050:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFWorkbook.addOlePackage(org.apache.poi.poifs.filesystem.POIFSFileSystem, java.lang.String, java.lang.String, java.lang.String):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        r5.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addOlePackage(byte[] r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) throws java.io.IOException {
        /*
            r5 = this;
            boolean r0 = r5.initDirectory()
            r1 = 1
            if (r0 == 0) goto L_0x0009
            r5.preserveNodes = r1
        L_0x0009:
            r0 = 0
            r2 = 0
        L_0x000b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "MBD"
            r3.<init>(r4)
            int r0 = r0 + r1
            java.lang.String r4 = org.apache.poi.util.HexDump.toHex((int) r0)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            org.apache.poi.poifs.filesystem.DirectoryNode r4 = r5.getDirectory()
            boolean r4 = r4.hasEntry(r3)
            if (r4 != 0) goto L_0x003a
            org.apache.poi.poifs.filesystem.DirectoryNode r2 = r5.getDirectory()
            org.apache.poi.poifs.filesystem.DirectoryEntry r2 = r2.createDirectory(r3)
            org.apache.poi.hpsf.ClassIDPredefined r3 = org.apache.poi.hpsf.ClassIDPredefined.OLE_V1_PACKAGE
            org.apache.poi.hpsf.ClassID r3 = r3.getClassID()
            r2.setStorageClsid(r3)
        L_0x003a:
            if (r2 == 0) goto L_0x000b
            org.apache.poi.poifs.filesystem.Ole10Native.createOleMarkerEntry((org.apache.poi.poifs.filesystem.DirectoryEntry) r2)
            org.apache.poi.poifs.filesystem.Ole10Native r5 = new org.apache.poi.poifs.filesystem.Ole10Native
            r5.<init>(r7, r8, r9, r6)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r6 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r6.<init>()
            r5.writeOut(r6)     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = "\u0001Ole10Native"
            java.io.InputStream r7 = r6.toInputStream()     // Catch:{ all -> 0x0059 }
            r2.createDocument(r5, r7)     // Catch:{ all -> 0x0059 }
            r6.close()
            return r0
        L_0x0059:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x005b }
        L_0x005b:
            r7 = move-exception
            r6.close()     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r6 = move-exception
            r5.addSuppressed(r6)
        L_0x0064:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFWorkbook.addOlePackage(byte[], java.lang.String, java.lang.String, java.lang.String):int");
    }

    public int linkExternalWorkbook(String str, Workbook workbook2) {
        return this.workbook.linkExternalWorkbook(str, workbook2);
    }

    public boolean isWriteProtected() {
        return this.workbook.isWriteProtected();
    }

    public void writeProtectWorkbook(String str, String str2) {
        this.workbook.writeProtectWorkbook(str, str2);
    }

    public void unwriteProtectWorkbook() {
        this.workbook.unwriteProtectWorkbook();
    }

    public List<HSSFObjectData> getAllEmbeddedObjects() {
        ArrayList arrayList = new ArrayList();
        for (HSSFSheet allEmbeddedObjects : this._sheets) {
            getAllEmbeddedObjects(allEmbeddedObjects, (List<HSSFObjectData>) arrayList);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private void getAllEmbeddedObjects(HSSFSheet hSSFSheet, List<HSSFObjectData> list) {
        HSSFPatriarch drawingPatriarch = hSSFSheet.getDrawingPatriarch();
        if (drawingPatriarch != null) {
            getAllEmbeddedObjects((HSSFShapeContainer) drawingPatriarch, list);
        }
    }

    private void getAllEmbeddedObjects(HSSFShapeContainer hSSFShapeContainer, List<HSSFObjectData> list) {
        for (HSSFShape next : hSSFShapeContainer.getChildren()) {
            if (next instanceof HSSFObjectData) {
                list.add((HSSFObjectData) next);
            } else if (next instanceof HSSFShapeContainer) {
                getAllEmbeddedObjects((HSSFShapeContainer) next, list);
            }
        }
    }

    public HSSFCreationHelper getCreationHelper() {
        return new HSSFCreationHelper(this);
    }

    /* access modifiers changed from: package-private */
    public UDFFinder getUDFFinder() {
        return this._udfFinder;
    }

    public void addToolPack(UDFFinder uDFFinder) {
        ((AggregatingUDFFinder) this._udfFinder).add(uDFFinder);
    }

    public void setForceFormulaRecalculation(boolean z) {
        getWorkbook().getRecalcId().setEngineId(0);
    }

    public boolean getForceFormulaRecalculation() {
        RecalcIdRecord recalcIdRecord = (RecalcIdRecord) getWorkbook().findFirstRecordBySid(RecalcIdRecord.sid);
        return (recalcIdRecord == null || recalcIdRecord.getEngineId() == 0) ? false : true;
    }

    public boolean changeExternalReference(String str, String str2) {
        return this.workbook.changeExternalReference(str, str2);
    }

    @Internal
    public InternalWorkbook getInternalWorkbook() {
        return this.workbook;
    }

    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    public EncryptionInfo getEncryptionInfo() {
        FilePassRecord filePassRecord = (FilePassRecord) this.workbook.findFirstRecordBySid(47);
        if (filePassRecord != null) {
            return filePassRecord.getEncryptionInfo();
        }
        return null;
    }

    private void updateEncryptionInfo() {
        readProperties();
        FilePassRecord filePassRecord = (FilePassRecord) this.workbook.findFirstRecordBySid(47);
        String currentUserPassword = Biff8EncryptionKey.getCurrentUserPassword();
        WorkbookRecordList workbookRecordList = this.workbook.getWorkbookRecordList();
        if (currentUserPassword != null) {
            if (filePassRecord == null) {
                filePassRecord = new FilePassRecord(EncryptionMode.cryptoAPI);
                workbookRecordList.add(1, filePassRecord);
            }
            EncryptionInfo encryptionInfo = filePassRecord.getEncryptionInfo();
            EncryptionVerifier verifier = encryptionInfo.getVerifier();
            byte[] encryptedVerifier = verifier.getEncryptedVerifier();
            Decryptor decryptor = encryptionInfo.getDecryptor();
            Encryptor encryptor = encryptionInfo.getEncryptor();
            if (encryptedVerifier != null) {
                try {
                    if (decryptor.verifyPassword(currentUserPassword)) {
                        encryptor.confirmPassword(currentUserPassword, (byte[]) null, (byte[]) null, decryptor.getVerifier(), verifier.getSalt(), (byte[]) null);
                        return;
                    }
                } catch (GeneralSecurityException e) {
                    throw new EncryptedDocumentException("can't validate/update encryption setting", e);
                }
            }
            encryptor.confirmPassword(currentUserPassword);
        } else if (filePassRecord != null) {
            workbookRecordList.remove((Object) filePassRecord);
        }
    }

    public HSSFEvaluationWorkbook createEvaluationWorkbook() {
        return HSSFEvaluationWorkbook.create(this);
    }

    public void setEncryptionMode(EncryptionMode encryptionMode) {
        EncryptionMode encryptionMode2 = null;
        if (encryptionMode == null) {
            Biff8EncryptionKey.setCurrentUserPassword((String) null);
        } else if (encryptionMode == EncryptionMode.xor || encryptionMode == EncryptionMode.binaryRC4 || encryptionMode == EncryptionMode.cryptoAPI) {
            FilePassRecord filePassRecord = (FilePassRecord) getInternalWorkbook().findFirstRecordBySid(47);
            if (filePassRecord != null) {
                encryptionMode2 = filePassRecord.getEncryptionInfo().getEncryptionMode();
            }
            if (encryptionMode != encryptionMode2) {
                readProperties();
                WorkbookRecordList workbookRecordList = getInternalWorkbook().getWorkbookRecordList();
                if (filePassRecord != null) {
                    workbookRecordList.remove((Object) filePassRecord);
                }
                workbookRecordList.add(1, new FilePassRecord(encryptionMode));
            }
        } else {
            throw new IllegalArgumentException("Only xor, binaryRC4 and cryptoAPI are supported.");
        }
    }

    public EncryptionMode getEncryptionMode() {
        FilePassRecord filePassRecord = (FilePassRecord) getInternalWorkbook().findFirstRecordBySid(47);
        if (filePassRecord == null) {
            return null;
        }
        return filePassRecord.getEncryptionInfo().getEncryptionMode();
    }
}
