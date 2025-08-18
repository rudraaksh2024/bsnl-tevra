package org.apache.poi.hssf.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherDggRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSplitMenuColorsRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BackupRecord;
import org.apache.poi.hssf.record.BookBoolRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.CountryRecord;
import org.apache.poi.hssf.record.DSFRecord;
import org.apache.poi.hssf.record.DateWindow1904Record;
import org.apache.poi.hssf.record.DrawingGroupRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ExtSSTRecord;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FileSharingRecord;
import org.apache.poi.hssf.record.FnGroupCountRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.HideObjRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.InterfaceEndRecord;
import org.apache.poi.hssf.record.InterfaceHdrRecord;
import org.apache.poi.hssf.record.MMSRecord;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.hssf.record.PasswordRecord;
import org.apache.poi.hssf.record.PasswordRev4Record;
import org.apache.poi.hssf.record.PrecisionRecord;
import org.apache.poi.hssf.record.ProtectRecord;
import org.apache.poi.hssf.record.ProtectionRev4Record;
import org.apache.poi.hssf.record.RecalcIdRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RefreshAllRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.record.TabIdRecord;
import org.apache.poi.hssf.record.UseSelFSRecord;
import org.apache.poi.hssf.record.WindowOneRecord;
import org.apache.poi.hssf.record.WindowProtectRecord;
import org.apache.poi.hssf.record.WriteAccessRecord;
import org.apache.poi.hssf.record.WriteProtectRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.RecordFormatException;

@Internal
public final class InternalWorkbook {
    private static final short CODEPAGE = 1200;
    private static final Logger LOG = LogManager.getLogger((Class<?>) InternalWorkbook.class);
    private static final int MAX_SENSITIVE_SHEET_NAME_LEN = 31;
    public static final String OLD_WORKBOOK_DIR_ENTRY_NAME = "Book";
    public static final List<String> WORKBOOK_DIR_ENTRY_NAMES = Collections.unmodifiableList(Arrays.asList(new String[]{"Workbook", "WORKBOOK", "BOOK", "WorkBook"}));
    private final List<BoundSheetRecord> boundsheets = new ArrayList();
    private final Map<String, NameCommentRecord> commentRecords = new LinkedHashMap();
    private DrawingManager2 drawingManager;
    private final List<EscherBSERecord> escherBSERecords = new ArrayList();
    private FileSharingRecord fileShare;
    private final List<FormatRecord> formats = new ArrayList();
    private final List<HyperlinkRecord> hyperlinks = new ArrayList();
    private LinkTable linkTable;
    private int maxformatid = -1;
    private int numfonts = 0;
    private int numxfs = 0;
    private final WorkbookRecordList records = new WorkbookRecordList();
    protected SSTRecord sst;
    private boolean uses1904datewindowing = false;
    private WindowOneRecord windowOne;
    private WriteAccessRecord writeAccess;
    private WriteProtectRecord writeProtect;

    private InternalWorkbook() {
    }

    public static InternalWorkbook createWorkbook(List<Record> list) {
        String str;
        LOG.atDebug().log("Workbook (readfile) created with reclen={}", (Object) Unbox.box(list.size()));
        InternalWorkbook internalWorkbook = new InternalWorkbook();
        ArrayList arrayList = new ArrayList(list.size() / 3);
        internalWorkbook.records.setRecords(arrayList);
        int i = 0;
        boolean z = false;
        while (i < list.size()) {
            Record record = list.get(i);
            switch (record.getSid()) {
                case 10:
                    str = "workbook eof";
                    break;
                case 18:
                    internalWorkbook.records.setProtpos(i);
                    str = "protect";
                    break;
                case 23:
                    throw new RecordFormatException("Extern sheet is part of LinkTable");
                case 24:
                case 430:
                    LOG.atDebug().log("found SupBook record at {}", (Object) Unbox.box(i));
                    LinkTable linkTable2 = new LinkTable(list, i, internalWorkbook.records, internalWorkbook.commentRecords);
                    internalWorkbook.linkTable = linkTable2;
                    i += linkTable2.getRecordCount() - 1;
                    continue;
                case 34:
                    internalWorkbook.uses1904datewindowing = ((DateWindow1904Record) record).getWindowing() == 1;
                    str = "datewindow1904";
                    break;
                case 49:
                    internalWorkbook.records.setFontpos(i);
                    internalWorkbook.numfonts++;
                    str = CellUtil.FONT;
                    break;
                case 61:
                    internalWorkbook.windowOne = (WindowOneRecord) record;
                    str = "WindowOneRecord";
                    break;
                case 64:
                    internalWorkbook.records.setBackuppos(i);
                    str = "backup";
                    break;
                case 91:
                    internalWorkbook.fileShare = (FileSharingRecord) record;
                    str = "FileSharing";
                    break;
                case 92:
                    internalWorkbook.writeAccess = (WriteAccessRecord) record;
                    str = "WriteAccess";
                    break;
                case 133:
                    internalWorkbook.boundsheets.add((BoundSheetRecord) record);
                    internalWorkbook.records.setBspos(i);
                    str = "boundsheet";
                    break;
                case 134:
                    internalWorkbook.writeProtect = (WriteProtectRecord) record;
                    str = "WriteProtect";
                    break;
                case 146:
                    internalWorkbook.records.setPalettepos(i);
                    str = "palette";
                    break;
                case 224:
                    internalWorkbook.records.setXfpos(i);
                    internalWorkbook.numxfs++;
                    str = "XF";
                    break;
                case 252:
                    internalWorkbook.sst = (SSTRecord) record;
                    str = "sst";
                    break;
                case TypedValues.AttributesType.TYPE_EASING:
                    internalWorkbook.records.setTabpos(i);
                    str = "tabid";
                    break;
                case 440:
                    internalWorkbook.hyperlinks.add((HyperlinkRecord) record);
                    str = "Hyperlink";
                    break;
                case 1054:
                    FormatRecord formatRecord = (FormatRecord) record;
                    internalWorkbook.formats.add(formatRecord);
                    internalWorkbook.maxformatid = Math.max(internalWorkbook.maxformatid, formatRecord.getIndexCode());
                    str = "format";
                    break;
                case 2196:
                    NameCommentRecord nameCommentRecord = (NameCommentRecord) record;
                    internalWorkbook.commentRecords.put(nameCommentRecord.getNameText(), nameCommentRecord);
                    str = "NameComment";
                    break;
                default:
                    str = "(sid=" + record.getSid() + ")";
                    break;
            }
            if (!z) {
                arrayList.add(record);
            }
            LOG.atTrace().log("found {} record at {}", str, Unbox.box(i));
            if (record.getSid() == 10) {
                z = true;
            }
            i++;
        }
        if (internalWorkbook.windowOne == null) {
            internalWorkbook.windowOne = createWindowOne();
        }
        LOG.atDebug().log("exit create workbook from existing file function");
        return internalWorkbook;
    }

    public static InternalWorkbook createWorkbook() {
        LOG.atDebug().log("creating new workbook from scratch");
        InternalWorkbook internalWorkbook = new InternalWorkbook();
        ArrayList arrayList = new ArrayList(30);
        internalWorkbook.records.setRecords(arrayList);
        List<FormatRecord> list = internalWorkbook.formats;
        arrayList.add(createBOF());
        arrayList.add(new InterfaceHdrRecord(1200));
        arrayList.add(createMMS());
        arrayList.add(InterfaceEndRecord.instance);
        internalWorkbook.getWriteAccess();
        arrayList.add(createCodepage());
        arrayList.add(createDSF());
        arrayList.add(createTabId());
        internalWorkbook.records.setTabpos(arrayList.size() - 1);
        arrayList.add(createFnGroupCount());
        arrayList.add(createWindowProtect());
        arrayList.add(createProtect());
        internalWorkbook.records.setProtpos(arrayList.size() - 1);
        arrayList.add(createPassword());
        arrayList.add(createProtectionRev4());
        arrayList.add(createPasswordRev4());
        WindowOneRecord createWindowOne = createWindowOne();
        internalWorkbook.windowOne = createWindowOne;
        arrayList.add(createWindowOne);
        arrayList.add(createBackup());
        internalWorkbook.records.setBackuppos(arrayList.size() - 1);
        arrayList.add(createHideObj());
        arrayList.add(createDateWindow1904());
        arrayList.add(createPrecision());
        arrayList.add(createRefreshAll());
        arrayList.add(createBookBool());
        arrayList.add(createFont());
        arrayList.add(createFont());
        arrayList.add(createFont());
        arrayList.add(createFont());
        internalWorkbook.records.setFontpos(arrayList.size() - 1);
        internalWorkbook.numfonts = 4;
        for (int i = 0; i <= 7; i++) {
            FormatRecord createFormat = createFormat(i);
            internalWorkbook.maxformatid = Math.max(internalWorkbook.maxformatid, createFormat.getIndexCode());
            list.add(createFormat);
            arrayList.add(createFormat);
        }
        for (int i2 = 0; i2 < 21; i2++) {
            arrayList.add(createExtendedFormat(i2));
            internalWorkbook.numxfs++;
        }
        internalWorkbook.records.setXfpos(arrayList.size() - 1);
        for (int i3 = 0; i3 < 6; i3++) {
            arrayList.add(createStyle(i3));
        }
        arrayList.add(createUseSelFS());
        BoundSheetRecord createBoundSheet = createBoundSheet(0);
        arrayList.add(createBoundSheet);
        internalWorkbook.boundsheets.add(createBoundSheet);
        internalWorkbook.records.setBspos(arrayList.size() - 1);
        arrayList.add(createCountry());
        internalWorkbook.getOrCreateLinkTable().checkExternSheet(0);
        SSTRecord sSTRecord = new SSTRecord();
        internalWorkbook.sst = sSTRecord;
        arrayList.add(sSTRecord);
        arrayList.add(createExtendedSST());
        arrayList.add(EOFRecord.instance);
        LOG.atDebug().log("exit create new workbook from scratch");
        return internalWorkbook;
    }

    public NameRecord getSpecificBuiltinRecord(byte b, int i) {
        return getOrCreateLinkTable().getSpecificBuiltinRecord(b, i);
    }

    public void removeBuiltinRecord(byte b, int i) {
        this.linkTable.removeBuiltinRecord(b, i);
    }

    public int getNumRecords() {
        return this.records.size();
    }

    public FontRecord getFontRecordAt(int i) {
        int i2 = i > 4 ? i - 1 : i;
        if (i2 <= this.numfonts - 1) {
            WorkbookRecordList workbookRecordList = this.records;
            return (FontRecord) workbookRecordList.get((workbookRecordList.getFontpos() - (this.numfonts - 1)) + i2);
        }
        throw new ArrayIndexOutOfBoundsException("There are only " + this.numfonts + " font records, but you asked for index " + i);
    }

    public int getFontIndex(FontRecord fontRecord) {
        int i = 0;
        while (i <= this.numfonts) {
            WorkbookRecordList workbookRecordList = this.records;
            if (((FontRecord) workbookRecordList.get((workbookRecordList.getFontpos() - (this.numfonts - 1)) + i)) == fontRecord) {
                return i > 3 ? i + 1 : i;
            }
            i++;
        }
        throw new IllegalArgumentException("Could not find that font!");
    }

    public FontRecord createNewFont() {
        FontRecord createFont = createFont();
        WorkbookRecordList workbookRecordList = this.records;
        workbookRecordList.add(workbookRecordList.getFontpos() + 1, createFont);
        WorkbookRecordList workbookRecordList2 = this.records;
        workbookRecordList2.setFontpos(workbookRecordList2.getFontpos() + 1);
        this.numfonts++;
        return createFont;
    }

    public void removeFontRecord(FontRecord fontRecord) {
        this.records.remove((Object) fontRecord);
        this.numfonts--;
    }

    public int getNumberOfFontRecords() {
        return this.numfonts;
    }

    public void setSheetBof(int i, int i2) {
        LOG.atDebug().log("setting bof for sheetnum ={} at pos={}", Unbox.box(i), Unbox.box(i2));
        checkSheets(i);
        getBoundSheetRec(i).setPositionOfBof(i2);
    }

    private BoundSheetRecord getBoundSheetRec(int i) {
        return this.boundsheets.get(i);
    }

    public BackupRecord getBackupRecord() {
        WorkbookRecordList workbookRecordList = this.records;
        return (BackupRecord) workbookRecordList.get(workbookRecordList.getBackuppos());
    }

    public void setSheetName(int i, String str) {
        checkSheets(i);
        if (str.length() > 31) {
            str = str.substring(0, 31);
        }
        this.boundsheets.get(i).setSheetname(str);
    }

    public boolean doesContainsSheetName(String str, int i) {
        if (str.length() > 31) {
            str = str.substring(0, 31);
        }
        int i2 = 0;
        for (BoundSheetRecord next : this.boundsheets) {
            int i3 = i2 + 1;
            if (i != i2) {
                String sheetname = next.getSheetname();
                if (sheetname.length() > 31) {
                    sheetname = sheetname.substring(0, 31);
                }
                if (str.equalsIgnoreCase(sheetname)) {
                    return true;
                }
            }
            i2 = i3;
        }
        return false;
    }

    public void setSheetOrder(String str, int i) {
        int sheetIndex = getSheetIndex(str);
        List<BoundSheetRecord> list = this.boundsheets;
        list.add(i, list.remove(sheetIndex));
        int bspos = this.records.getBspos();
        int size = bspos - (this.boundsheets.size() - 1);
        int i2 = sheetIndex + size;
        Record record = this.records.get(i2);
        this.records.remove(i2);
        this.records.add(size + i, record);
        this.records.setBspos(bspos);
    }

    public String getSheetName(int i) {
        return getBoundSheetRec(i).getSheetname();
    }

    public boolean isSheetHidden(int i) {
        return getBoundSheetRec(i).isHidden();
    }

    public boolean isSheetVeryHidden(int i) {
        return getBoundSheetRec(i).isVeryHidden();
    }

    public SheetVisibility getSheetVisibility(int i) {
        BoundSheetRecord boundSheetRec = getBoundSheetRec(i);
        if (boundSheetRec.isVeryHidden()) {
            return SheetVisibility.VERY_HIDDEN;
        }
        if (boundSheetRec.isHidden()) {
            return SheetVisibility.HIDDEN;
        }
        return SheetVisibility.VISIBLE;
    }

    public void setSheetHidden(int i, boolean z) {
        setSheetHidden(i, z ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    public void setSheetHidden(int i, SheetVisibility sheetVisibility) {
        checkSheets(i);
        BoundSheetRecord boundSheetRec = getBoundSheetRec(i);
        boolean z = true;
        boundSheetRec.setHidden(sheetVisibility == SheetVisibility.HIDDEN);
        if (sheetVisibility != SheetVisibility.VERY_HIDDEN) {
            z = false;
        }
        boundSheetRec.setVeryHidden(z);
    }

    public int getSheetIndex(String str) {
        int size = this.boundsheets.size();
        for (int i = 0; i < size; i++) {
            if (getSheetName(i).equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    private void checkSheets(int i) {
        if (this.boundsheets.size() > i) {
            return;
        }
        if (this.boundsheets.size() + 1 > i) {
            BoundSheetRecord createBoundSheet = createBoundSheet(i);
            WorkbookRecordList workbookRecordList = this.records;
            workbookRecordList.add(workbookRecordList.getBspos() + 1, createBoundSheet);
            WorkbookRecordList workbookRecordList2 = this.records;
            workbookRecordList2.setBspos(workbookRecordList2.getBspos() + 1);
            this.boundsheets.add(createBoundSheet);
            getOrCreateLinkTable().checkExternSheet(i);
            fixTabIdRecord();
            return;
        }
        throw new RuntimeException("Sheet number out of bounds!");
    }

    public void removeSheet(int i) {
        if (this.boundsheets.size() > i) {
            WorkbookRecordList workbookRecordList = this.records;
            workbookRecordList.remove((workbookRecordList.getBspos() - (this.boundsheets.size() - 1)) + i);
            this.boundsheets.remove(i);
            fixTabIdRecord();
        }
        int i2 = i + 1;
        for (int i3 = 0; i3 < getNumNames(); i3++) {
            NameRecord nameRecord = getNameRecord(i3);
            if (nameRecord.getSheetNumber() == i2) {
                nameRecord.setSheetNumber(0);
            } else if (nameRecord.getSheetNumber() > i2) {
                nameRecord.setSheetNumber(nameRecord.getSheetNumber() - 1);
            }
        }
        LinkTable linkTable2 = this.linkTable;
        if (linkTable2 != null) {
            linkTable2.removeSheet(i);
        }
    }

    private void fixTabIdRecord() {
        if (this.records.getTabpos() > 0) {
            WorkbookRecordList workbookRecordList = this.records;
            TabIdRecord tabIdRecord = (TabIdRecord) workbookRecordList.get(workbookRecordList.getTabpos());
            int size = this.boundsheets.size();
            short[] sArr = new short[size];
            for (short s = 0; s < size; s = (short) (s + 1)) {
                sArr[s] = s;
            }
            tabIdRecord.setTabIdArray(sArr);
        }
    }

    public int getNumSheets() {
        LOG.atDebug().log("getNumSheets={}", (Object) Unbox.box(this.boundsheets.size()));
        return this.boundsheets.size();
    }

    public int getNumExFormats() {
        LOG.atDebug().log("getXF={}", (Object) Unbox.box(this.numxfs));
        return this.numxfs;
    }

    public ExtendedFormatRecord getExFormatAt(int i) {
        return (ExtendedFormatRecord) this.records.get((this.records.getXfpos() - (this.numxfs - 1)) + i);
    }

    public void removeExFormatRecord(ExtendedFormatRecord extendedFormatRecord) {
        this.records.remove((Object) extendedFormatRecord);
        this.numxfs--;
    }

    public void removeExFormatRecord(int i) {
        this.records.remove((this.records.getXfpos() - (this.numxfs - 1)) + i);
        this.numxfs--;
    }

    public ExtendedFormatRecord createCellXF() {
        ExtendedFormatRecord createExtendedFormat = createExtendedFormat();
        WorkbookRecordList workbookRecordList = this.records;
        workbookRecordList.add(workbookRecordList.getXfpos() + 1, createExtendedFormat);
        WorkbookRecordList workbookRecordList2 = this.records;
        workbookRecordList2.setXfpos(workbookRecordList2.getXfpos() + 1);
        this.numxfs++;
        return createExtendedFormat;
    }

    public StyleRecord getStyleRecord(int i) {
        for (int xfpos = this.records.getXfpos(); xfpos < this.records.size(); xfpos++) {
            Record record = this.records.get(xfpos);
            if (record instanceof StyleRecord) {
                StyleRecord styleRecord = (StyleRecord) record;
                if (styleRecord.getXFIndex() == i) {
                    return styleRecord;
                }
            }
        }
        return null;
    }

    public void updateStyleRecord(int i, int i2) {
        for (int xfpos = this.records.getXfpos(); xfpos < this.records.size(); xfpos++) {
            Record record = this.records.get(xfpos);
            if (record instanceof StyleRecord) {
                StyleRecord styleRecord = (StyleRecord) record;
                if (styleRecord.getXFIndex() == i) {
                    styleRecord.setXFIndex(i2);
                }
            }
        }
    }

    public StyleRecord createStyleRecord(int i) {
        StyleRecord styleRecord = new StyleRecord();
        styleRecord.setXFIndex(i);
        int i2 = -1;
        for (int xfpos = this.records.getXfpos(); xfpos < this.records.size() && i2 == -1; xfpos++) {
            Record record = this.records.get(xfpos);
            if (!(record instanceof ExtendedFormatRecord) && !(record instanceof StyleRecord)) {
                i2 = xfpos;
            }
        }
        if (i2 != -1) {
            this.records.add(i2, styleRecord);
            return styleRecord;
        }
        throw new IllegalStateException("No XF Records found!");
    }

    public int addSSTString(UnicodeString unicodeString) {
        LOG.atDebug().log("insert to sst string='{}'", (Object) unicodeString);
        if (this.sst == null) {
            insertSST();
        }
        return this.sst.addString(unicodeString);
    }

    public UnicodeString getSSTString(int i) {
        if (this.sst == null) {
            insertSST();
        }
        UnicodeString string = this.sst.getString(i);
        LOG.atTrace().log("Returning SST for index={} String= {}", Unbox.box(i), string);
        return string;
    }

    public void insertSST() {
        LOG.atDebug().log("creating new SST via insertSST!");
        this.sst = new SSTRecord();
        WorkbookRecordList workbookRecordList = this.records;
        workbookRecordList.add(workbookRecordList.size() - 1, createExtendedSST());
        WorkbookRecordList workbookRecordList2 = this.records;
        workbookRecordList2.add(workbookRecordList2.size() - 2, this.sst);
    }

    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:579)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:485)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:232)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        */
    public int serialize(int r10, byte[] r11) {
        /*
            r9 = this;
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atDebug()
            java.lang.String r1 = "Serializing Workbook with offsets"
            r0.log((java.lang.String) r1)
            org.apache.poi.hssf.model.WorkbookRecordList r0 = r9.records
            java.util.List r0 = r0.getRecords()
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = r3
            r5 = r4
        L_0x001a:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x006c
            java.lang.Object r6 = r0.next()
            org.apache.poi.hssf.record.Record r6 = (org.apache.poi.hssf.record.Record) r6
            boolean r7 = r6 instanceof org.apache.poi.hssf.record.SSTRecord
            if (r7 == 0) goto L_0x002e
            r2 = r6
            org.apache.poi.hssf.record.SSTRecord r2 = (org.apache.poi.hssf.record.SSTRecord) r2
            r4 = r3
        L_0x002e:
            short r7 = r6.getSid()
            r8 = 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L_0x003e
            if (r2 == 0) goto L_0x003e
            int r6 = r4 + r10
            org.apache.poi.hssf.record.ExtSSTRecord r6 = r2.createExtSSTRecord(r6)
        L_0x003e:
            boolean r7 = r6 instanceof org.apache.poi.hssf.record.BoundSheetRecord
            if (r7 == 0) goto L_0x0064
            if (r5 != 0) goto L_0x0062
            java.util.List<org.apache.poi.hssf.record.BoundSheetRecord> r5 = r9.boundsheets
            java.util.Iterator r5 = r5.iterator()
            r6 = r1
        L_0x004b:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x0060
            java.lang.Object r7 = r5.next()
            org.apache.poi.hssf.record.BoundSheetRecord r7 = (org.apache.poi.hssf.record.BoundSheetRecord) r7
            int r8 = r3 + r10
            int r8 = r8 + r6
            int r7 = r7.serialize(r8, r11)
            int r6 = r6 + r7
            goto L_0x004b
        L_0x0060:
            r5 = 1
            goto L_0x006a
        L_0x0062:
            r6 = r1
            goto L_0x006a
        L_0x0064:
            int r7 = r3 + r10
            int r6 = r6.serialize(r7, r11)
        L_0x006a:
            int r3 = r3 + r6
            goto L_0x001a
        L_0x006c:
            org.apache.logging.log4j.Logger r9 = LOG
            org.apache.logging.log4j.LogBuilder r9 = r9.atDebug()
            java.lang.String r10 = "Exiting serialize workbook"
            r9.log((java.lang.String) r10)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.model.InternalWorkbook.serialize(int, byte[]):int");
    }

    public void preSerialize() {
        if (this.records.getTabpos() > 0) {
            WorkbookRecordList workbookRecordList = this.records;
            if (((TabIdRecord) workbookRecordList.get(workbookRecordList.getTabpos())).getTabIdSize() < this.boundsheets.size()) {
                fixTabIdRecord();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: org.apache.poi.hssf.record.Record} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: org.apache.poi.hssf.record.SSTRecord} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getSize() {
        /*
            r5 = this;
            org.apache.poi.hssf.model.WorkbookRecordList r5 = r5.records
            java.util.List r5 = r5.getRecords()
            java.util.Iterator r5 = r5.iterator()
            r0 = 0
            r1 = 0
        L_0x000c:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x0034
            java.lang.Object r2 = r5.next()
            org.apache.poi.hssf.record.Record r2 = (org.apache.poi.hssf.record.Record) r2
            boolean r3 = r2 instanceof org.apache.poi.hssf.record.SSTRecord
            if (r3 == 0) goto L_0x001f
            r1 = r2
            org.apache.poi.hssf.record.SSTRecord r1 = (org.apache.poi.hssf.record.SSTRecord) r1
        L_0x001f:
            short r3 = r2.getSid()
            r4 = 255(0xff, float:3.57E-43)
            if (r3 != r4) goto L_0x002e
            if (r1 == 0) goto L_0x002e
            int r2 = r1.calcExtSSTRecordSize()
            goto L_0x0032
        L_0x002e:
            int r2 = r2.getRecordSize()
        L_0x0032:
            int r0 = r0 + r2
            goto L_0x000c
        L_0x0034:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.model.InternalWorkbook.getSize():int");
    }

    private static BOFRecord createBOF() {
        BOFRecord bOFRecord = new BOFRecord();
        bOFRecord.setVersion(BOFRecord.VERSION);
        bOFRecord.setType(5);
        bOFRecord.setBuild(BOFRecord.BUILD);
        bOFRecord.setBuildYear(BOFRecord.BUILD_YEAR);
        bOFRecord.setHistoryBitMask(65);
        bOFRecord.setRequiredVersion(6);
        return bOFRecord;
    }

    private static MMSRecord createMMS() {
        MMSRecord mMSRecord = new MMSRecord();
        mMSRecord.setAddMenuCount((byte) 0);
        mMSRecord.setDelMenuCount((byte) 0);
        return mMSRecord;
    }

    private static WriteAccessRecord createWriteAccess() {
        WriteAccessRecord writeAccessRecord = new WriteAccessRecord();
        try {
            String property = System.getProperty("user.name");
            if (property == null) {
                property = "POI";
            }
            writeAccessRecord.setUsername(property);
        } catch (AccessControlException e) {
            LOG.atWarn().withThrowable(e).log("can't determine user.name");
            writeAccessRecord.setUsername("POI");
        }
        return writeAccessRecord;
    }

    private static CodepageRecord createCodepage() {
        CodepageRecord codepageRecord = new CodepageRecord();
        codepageRecord.setCodepage(1200);
        return codepageRecord;
    }

    private static DSFRecord createDSF() {
        return new DSFRecord(false);
    }

    private static TabIdRecord createTabId() {
        return new TabIdRecord();
    }

    private static FnGroupCountRecord createFnGroupCount() {
        FnGroupCountRecord fnGroupCountRecord = new FnGroupCountRecord();
        fnGroupCountRecord.setCount(14);
        return fnGroupCountRecord;
    }

    private static WindowProtectRecord createWindowProtect() {
        return new WindowProtectRecord(false);
    }

    private static ProtectRecord createProtect() {
        return new ProtectRecord(false);
    }

    private static PasswordRecord createPassword() {
        return new PasswordRecord(0);
    }

    private static ProtectionRev4Record createProtectionRev4() {
        return new ProtectionRev4Record(false);
    }

    private static PasswordRev4Record createPasswordRev4() {
        return new PasswordRev4Record(0);
    }

    private static WindowOneRecord createWindowOne() {
        WindowOneRecord windowOneRecord = new WindowOneRecord();
        windowOneRecord.setHorizontalHold(360);
        windowOneRecord.setVerticalHold(270);
        windowOneRecord.setWidth(14940);
        windowOneRecord.setHeight(9150);
        windowOneRecord.setOptions(56);
        windowOneRecord.setActiveSheetIndex(0);
        windowOneRecord.setFirstVisibleTab(0);
        windowOneRecord.setNumSelectedTabs(1);
        windowOneRecord.setTabWidthRatio(600);
        return windowOneRecord;
    }

    private static BackupRecord createBackup() {
        BackupRecord backupRecord = new BackupRecord();
        backupRecord.setBackup(0);
        return backupRecord;
    }

    private static HideObjRecord createHideObj() {
        HideObjRecord hideObjRecord = new HideObjRecord();
        hideObjRecord.setHideObj(0);
        return hideObjRecord;
    }

    private static DateWindow1904Record createDateWindow1904() {
        DateWindow1904Record dateWindow1904Record = new DateWindow1904Record();
        dateWindow1904Record.setWindowing(0);
        return dateWindow1904Record;
    }

    private static PrecisionRecord createPrecision() {
        PrecisionRecord precisionRecord = new PrecisionRecord();
        precisionRecord.setFullPrecision(true);
        return precisionRecord;
    }

    private static RefreshAllRecord createRefreshAll() {
        return new RefreshAllRecord(false);
    }

    private static BookBoolRecord createBookBool() {
        BookBoolRecord bookBoolRecord = new BookBoolRecord();
        bookBoolRecord.setSaveLinkValues(0);
        return bookBoolRecord;
    }

    private static FontRecord createFont() {
        FontRecord fontRecord = new FontRecord();
        fontRecord.setFontHeight(EscherAggregate.ST_ACTIONBUTTONMOVIE);
        fontRecord.setAttributes(0);
        fontRecord.setColorPaletteIndex(Short.MAX_VALUE);
        fontRecord.setBoldWeight(400);
        fontRecord.setFontName(HSSFFont.FONT_ARIAL);
        return fontRecord;
    }

    private static FormatRecord createFormat(int i) {
        int[] iArr = {5, 6, 7, 8, 42, 41, 44, 43};
        if (i < 0 || i >= 8) {
            throw new IllegalArgumentException("Unexpected id " + i);
        }
        int i2 = iArr[i];
        return new FormatRecord(i2, BuiltinFormats.getBuiltinFormat(i2));
    }

    private static ExtendedFormatRecord createExtendedFormat(int i) {
        switch (i) {
            case 0:
                return createExtendedFormat(0, 0, -11, 0);
            case 1:
            case 2:
                return createExtendedFormat(1, 0, -11, -3072);
            case 3:
            case 4:
                return createExtendedFormat(2, 0, -11, -3072);
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return createExtendedFormat(0, 0, -11, -3072);
            case 15:
                return createExtendedFormat(0, 0, 1, 0);
            case 16:
                return createExtendedFormat(1, 43, -11, -2048);
            case 17:
                return createExtendedFormat(1, 41, -11, -2048);
            case 18:
                return createExtendedFormat(1, 44, -11, -2048);
            case 19:
                return createExtendedFormat(1, 42, -11, -2048);
            case 20:
                return createExtendedFormat(1, 9, -11, -2048);
            case 21:
                return createExtendedFormat(5, 0, 1, 2048);
            case 22:
                return createExtendedFormat(6, 0, 1, 23552);
            case 23:
                return createExtendedFormat(0, 49, 1, 23552);
            case 24:
                return createExtendedFormat(0, 8, 1, 23552);
            case 25:
                return createExtendedFormat(6, 8, 1, 23552);
            default:
                throw new IllegalStateException("Unrecognized format id: " + i);
        }
    }

    private static ExtendedFormatRecord createExtendedFormat(int i, int i2, int i3, int i4) {
        ExtendedFormatRecord extendedFormatRecord = new ExtendedFormatRecord();
        extendedFormatRecord.setFontIndex((short) i);
        extendedFormatRecord.setFormatIndex((short) i2);
        extendedFormatRecord.setCellOptions((short) i3);
        extendedFormatRecord.setAlignmentOptions(32);
        extendedFormatRecord.setIndentionOptions((short) i4);
        extendedFormatRecord.setBorderOptions(0);
        extendedFormatRecord.setPaletteOptions(0);
        extendedFormatRecord.setAdtlPaletteOptions(0);
        extendedFormatRecord.setFillPaletteOptions(8384);
        return extendedFormatRecord;
    }

    private static ExtendedFormatRecord createExtendedFormat() {
        ExtendedFormatRecord extendedFormatRecord = new ExtendedFormatRecord();
        extendedFormatRecord.setFontIndex(0);
        extendedFormatRecord.setFormatIndex(0);
        extendedFormatRecord.setCellOptions(1);
        extendedFormatRecord.setAlignmentOptions(32);
        extendedFormatRecord.setIndentionOptions(0);
        extendedFormatRecord.setBorderOptions(0);
        extendedFormatRecord.setPaletteOptions(0);
        extendedFormatRecord.setAdtlPaletteOptions(0);
        extendedFormatRecord.setFillPaletteOptions(8384);
        extendedFormatRecord.setTopBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        extendedFormatRecord.setBottomBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        extendedFormatRecord.setLeftBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        extendedFormatRecord.setRightBorderPaletteIdx(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        return extendedFormatRecord;
    }

    private static StyleRecord createStyle(int i) {
        int[][] iArr = {new int[]{16, 3}, new int[]{17, 6}, new int[]{18, 4}, new int[]{19, 7}, new int[]{0, 0}, new int[]{20, 5}};
        if (i < 0 || i >= 6) {
            throw new IllegalArgumentException("Unexpected style id " + i);
        }
        StyleRecord styleRecord = new StyleRecord();
        styleRecord.setOutlineStyleLevel(-1);
        styleRecord.setXFIndex(iArr[i][0]);
        styleRecord.setBuiltinStyle(iArr[i][1]);
        return styleRecord;
    }

    private static PaletteRecord createPalette() {
        return new PaletteRecord();
    }

    private static UseSelFSRecord createUseSelFS() {
        return new UseSelFSRecord(false);
    }

    private static BoundSheetRecord createBoundSheet(int i) {
        return new BoundSheetRecord("Sheet" + (i + 1));
    }

    private static CountryRecord createCountry() {
        CountryRecord countryRecord = new CountryRecord();
        countryRecord.setDefaultCountry(1);
        if ("ru_RU".equals(LocaleUtil.getUserLocale().toString())) {
            countryRecord.setCurrentCountry(7);
        } else {
            countryRecord.setCurrentCountry(1);
        }
        return countryRecord;
    }

    private static ExtSSTRecord createExtendedSST() {
        ExtSSTRecord extSSTRecord = new ExtSSTRecord();
        extSSTRecord.setNumStringsPerBucket(8);
        return extSSTRecord;
    }

    private LinkTable getOrCreateLinkTable() {
        if (this.linkTable == null) {
            this.linkTable = new LinkTable((short) getNumSheets(), this.records);
        }
        return this.linkTable;
    }

    public int linkExternalWorkbook(String str, Workbook workbook) {
        return getOrCreateLinkTable().linkExternalWorkbook(str, workbook);
    }

    public String findSheetFirstNameFromExternSheet(int i) {
        return findSheetNameFromIndex(this.linkTable.getFirstInternalSheetIndexForExtIndex(i));
    }

    public String findSheetLastNameFromExternSheet(int i) {
        return findSheetNameFromIndex(this.linkTable.getLastInternalSheetIndexForExtIndex(i));
    }

    private String findSheetNameFromIndex(int i) {
        if (i >= 0 && i < this.boundsheets.size()) {
            return getSheetName(i);
        }
        return "";
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(int i) {
        String[] externalBookAndSheetName = this.linkTable.getExternalBookAndSheetName(i);
        if (externalBookAndSheetName == null) {
            return null;
        }
        if (externalBookAndSheetName.length == 2) {
            return new EvaluationWorkbook.ExternalSheet(externalBookAndSheetName[0], externalBookAndSheetName[1]);
        }
        return new EvaluationWorkbook.ExternalSheetRange(externalBookAndSheetName[0], externalBookAndSheetName[1], externalBookAndSheetName[2]);
    }

    public EvaluationWorkbook.ExternalName getExternalName(int i, int i2) {
        String resolveNameXText = this.linkTable.resolveNameXText(i, i2, this);
        if (resolveNameXText == null) {
            return null;
        }
        return new EvaluationWorkbook.ExternalName(resolveNameXText, i2, this.linkTable.resolveNameXIx(i, i2));
    }

    public int getFirstSheetIndexFromExternSheetIndex(int i) {
        return this.linkTable.getFirstInternalSheetIndexForExtIndex(i);
    }

    public int getLastSheetIndexFromExternSheetIndex(int i) {
        return this.linkTable.getLastInternalSheetIndexForExtIndex(i);
    }

    public short checkExternSheet(int i) {
        return (short) getOrCreateLinkTable().checkExternSheet(i);
    }

    public short checkExternSheet(int i, int i2) {
        return (short) getOrCreateLinkTable().checkExternSheet(i, i2);
    }

    public int getExternalSheetIndex(String str, String str2) {
        return getOrCreateLinkTable().getExternalSheetIndex(str, str2, str2);
    }

    public int getExternalSheetIndex(String str, String str2, String str3) {
        return getOrCreateLinkTable().getExternalSheetIndex(str, str2, str3);
    }

    public int getNumNames() {
        LinkTable linkTable2 = this.linkTable;
        if (linkTable2 == null) {
            return 0;
        }
        return linkTable2.getNumNames();
    }

    public NameRecord getNameRecord(int i) {
        return this.linkTable.getNameRecord(i);
    }

    public NameCommentRecord getNameCommentRecord(NameRecord nameRecord) {
        return this.commentRecords.get(nameRecord.getNameText());
    }

    public NameRecord createName() {
        return addName(new NameRecord());
    }

    public NameRecord addName(NameRecord nameRecord) {
        getOrCreateLinkTable().addName(nameRecord);
        return nameRecord;
    }

    public NameRecord createBuiltInName(byte b, int i) {
        if (i < 0 || i + 1 > 32767) {
            throw new IllegalArgumentException("Sheet number [" + i + "]is not valid ");
        }
        NameRecord nameRecord = new NameRecord(b, i);
        if (!this.linkTable.nameAlreadyExists(nameRecord)) {
            addName(nameRecord);
            return nameRecord;
        }
        throw new RuntimeException("Builtin (" + b + ") already exists for sheet (" + i + ")");
    }

    public void removeName(int i) {
        if (this.linkTable.getNumNames() > i) {
            this.records.remove(findFirstRecordLocBySid(24) + i);
            this.linkTable.removeName(i);
        }
    }

    public void updateNameCommentRecordCache(NameCommentRecord nameCommentRecord) {
        if (this.commentRecords.containsValue(nameCommentRecord)) {
            Iterator<Map.Entry<String, NameCommentRecord>> it = this.commentRecords.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (((NameCommentRecord) next.getValue()).equals(nameCommentRecord)) {
                    this.commentRecords.remove(next.getKey());
                    break;
                }
            }
        }
        this.commentRecords.put(nameCommentRecord.getNameText(), nameCommentRecord);
    }

    public short getFormat(String str, boolean z) {
        int createFormat;
        Iterator<FormatRecord> it = this.formats.iterator();
        while (true) {
            if (it.hasNext()) {
                FormatRecord next = it.next();
                if (next.getFormatString().equals(str)) {
                    createFormat = next.getIndexCode();
                    break;
                }
            } else if (!z) {
                return -1;
            } else {
                createFormat = createFormat(str);
            }
        }
        return (short) createFormat;
    }

    public List<FormatRecord> getFormats() {
        return this.formats;
    }

    public int createFormat(String str) {
        int i = this.maxformatid;
        int i2 = 164;
        if (i >= 164) {
            i2 = i + 1;
        }
        this.maxformatid = i2;
        FormatRecord formatRecord = new FormatRecord(this.maxformatid, str);
        int i3 = 0;
        while (i3 < this.records.size() && this.records.get(i3).getSid() != 1054) {
            i3++;
        }
        int size = i3 + this.formats.size();
        this.formats.add(formatRecord);
        this.records.add(size, formatRecord);
        return this.maxformatid;
    }

    public Record findFirstRecordBySid(short s) {
        for (Record next : this.records.getRecords()) {
            if (next.getSid() == s) {
                return next;
            }
        }
        return null;
    }

    public int findFirstRecordLocBySid(short s) {
        int i = 0;
        for (Record sid : this.records.getRecords()) {
            if (sid.getSid() == s) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Record findNextRecordBySid(short s, int i) {
        int i2 = 0;
        for (Record next : this.records.getRecords()) {
            if (next.getSid() == s) {
                int i3 = i2 + 1;
                if (i2 == i) {
                    return next;
                }
                i2 = i3;
            }
        }
        return null;
    }

    public List<HyperlinkRecord> getHyperlinks() {
        return this.hyperlinks;
    }

    public List<Record> getRecords() {
        return this.records.getRecords();
    }

    public boolean isUsing1904DateWindowing() {
        return this.uses1904datewindowing;
    }

    public PaletteRecord getCustomPalette() {
        int palettepos = this.records.getPalettepos();
        if (palettepos != -1) {
            Record record = this.records.get(palettepos);
            if (record instanceof PaletteRecord) {
                return (PaletteRecord) record;
            }
            throw new RuntimeException("InternalError: Expected PaletteRecord but got a '" + record + "'");
        }
        PaletteRecord createPalette = createPalette();
        this.records.add(1, createPalette);
        this.records.setPalettepos(1);
        return createPalette;
    }

    public DrawingManager2 findDrawingGroup() {
        DrawingManager2 drawingManager2 = this.drawingManager;
        if (drawingManager2 != null) {
            return drawingManager2;
        }
        for (Record next : this.records.getRecords()) {
            if (next instanceof DrawingGroupRecord) {
                DrawingGroupRecord drawingGroupRecord = (DrawingGroupRecord) next;
                drawingGroupRecord.decode();
                DrawingManager2 findDrawingManager = findDrawingManager(drawingGroupRecord, this.escherBSERecords);
                this.drawingManager = findDrawingManager;
                if (findDrawingManager != null) {
                    return findDrawingManager;
                }
            }
        }
        DrawingManager2 findDrawingManager2 = findDrawingManager((DrawingGroupRecord) findFirstRecordBySid(DrawingGroupRecord.sid), this.escherBSERecords);
        this.drawingManager = findDrawingManager2;
        return findDrawingManager2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: org.apache.poi.ddf.EscherRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: org.apache.poi.ddf.EscherContainerRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: org.apache.poi.ddf.EscherDggRecord} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.poi.hssf.model.DrawingManager2 findDrawingManager(org.apache.poi.hssf.record.DrawingGroupRecord r6, java.util.List<org.apache.poi.ddf.EscherBSERecord> r7) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            org.apache.poi.ddf.EscherContainerRecord r6 = r6.getEscherContainer()
            if (r6 != 0) goto L_0x000b
            return r0
        L_0x000b:
            java.util.Iterator r6 = r6.iterator()
            r1 = r0
            r2 = r1
        L_0x0011:
            boolean r3 = r6.hasNext()
            if (r3 == 0) goto L_0x0031
            java.lang.Object r3 = r6.next()
            org.apache.poi.ddf.EscherRecord r3 = (org.apache.poi.ddf.EscherRecord) r3
            boolean r4 = r3 instanceof org.apache.poi.ddf.EscherDggRecord
            if (r4 == 0) goto L_0x0025
            r1 = r3
            org.apache.poi.ddf.EscherDggRecord r1 = (org.apache.poi.ddf.EscherDggRecord) r1
            goto L_0x0011
        L_0x0025:
            short r4 = r3.getRecordId()
            short r5 = org.apache.poi.ddf.EscherContainerRecord.BSTORE_CONTAINER
            if (r4 != r5) goto L_0x0011
            r2 = r3
            org.apache.poi.ddf.EscherContainerRecord r2 = (org.apache.poi.ddf.EscherContainerRecord) r2
            goto L_0x0011
        L_0x0031:
            if (r1 != 0) goto L_0x0034
            return r0
        L_0x0034:
            org.apache.poi.hssf.model.DrawingManager2 r6 = new org.apache.poi.hssf.model.DrawingManager2
            r6.<init>(r1)
            if (r2 == 0) goto L_0x0055
            java.util.Iterator r0 = r2.iterator()
        L_0x003f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0055
            java.lang.Object r1 = r0.next()
            org.apache.poi.ddf.EscherRecord r1 = (org.apache.poi.ddf.EscherRecord) r1
            boolean r2 = r1 instanceof org.apache.poi.ddf.EscherBSERecord
            if (r2 == 0) goto L_0x003f
            org.apache.poi.ddf.EscherBSERecord r1 = (org.apache.poi.ddf.EscherBSERecord) r1
            r7.add(r1)
            goto L_0x003f
        L_0x0055:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.model.InternalWorkbook.findDrawingManager(org.apache.poi.hssf.record.DrawingGroupRecord, java.util.List):org.apache.poi.hssf.model.DrawingManager2");
    }

    public void createDrawingGroup() {
        EscherContainerRecord escherContainerRecord;
        if (this.drawingManager == null) {
            EscherContainerRecord escherContainerRecord2 = new EscherContainerRecord();
            EscherDggRecord escherDggRecord = new EscherDggRecord();
            EscherOptRecord escherOptRecord = new EscherOptRecord();
            EscherSplitMenuColorsRecord escherSplitMenuColorsRecord = new EscherSplitMenuColorsRecord();
            escherContainerRecord2.setRecordId(-4096);
            escherContainerRecord2.setOptions(15);
            escherDggRecord.setRecordId(EscherDggRecord.RECORD_ID);
            escherDggRecord.setOptions(0);
            escherDggRecord.setShapeIdMax(1024);
            escherDggRecord.setNumShapesSaved(0);
            escherDggRecord.setDrawingsSaved(0);
            escherDggRecord.setFileIdClusters(new EscherDggRecord.FileIdCluster[0]);
            this.drawingManager = new DrawingManager2(escherDggRecord);
            if (!this.escherBSERecords.isEmpty()) {
                escherContainerRecord = new EscherContainerRecord();
                escherContainerRecord.setRecordId(EscherContainerRecord.BSTORE_CONTAINER);
                escherContainerRecord.setOptions((short) (15 | (this.escherBSERecords.size() << 4)));
                for (EscherBSERecord addChildRecord : this.escherBSERecords) {
                    escherContainerRecord.addChildRecord(addChildRecord);
                }
            } else {
                escherContainerRecord = null;
            }
            escherOptRecord.setRecordId(-4085);
            escherOptRecord.setOptions(51);
            escherOptRecord.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.TEXT__SIZE_TEXT_TO_FIT_SHAPE, 524296));
            escherOptRecord.addEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, 134217793));
            escherOptRecord.addEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, (int) HSSFShape.LINESTYLE__COLOR_DEFAULT));
            escherSplitMenuColorsRecord.setRecordId(-3810);
            escherSplitMenuColorsRecord.setOptions(64);
            escherSplitMenuColorsRecord.setColor1(134217741);
            escherSplitMenuColorsRecord.setColor2(134217740);
            escherSplitMenuColorsRecord.setColor3(134217751);
            escherSplitMenuColorsRecord.setColor4(268435703);
            escherContainerRecord2.addChildRecord(escherDggRecord);
            if (escherContainerRecord != null) {
                escherContainerRecord2.addChildRecord(escherContainerRecord);
            }
            escherContainerRecord2.addChildRecord(escherOptRecord);
            escherContainerRecord2.addChildRecord(escherSplitMenuColorsRecord);
            int findFirstRecordLocBySid = findFirstRecordLocBySid(DrawingGroupRecord.sid);
            if (findFirstRecordLocBySid == -1) {
                DrawingGroupRecord drawingGroupRecord = new DrawingGroupRecord();
                drawingGroupRecord.addEscherRecord(escherContainerRecord2);
                getRecords().add(findFirstRecordLocBySid(140) + 1, drawingGroupRecord);
                return;
            }
            DrawingGroupRecord drawingGroupRecord2 = new DrawingGroupRecord();
            drawingGroupRecord2.addEscherRecord(escherContainerRecord2);
            getRecords().set(findFirstRecordLocBySid, drawingGroupRecord2);
        }
    }

    public WindowOneRecord getWindowOne() {
        return this.windowOne;
    }

    public EscherBSERecord getBSERecord(int i) {
        return this.escherBSERecords.get(i - 1);
    }

    public int addBSERecord(EscherBSERecord escherBSERecord) {
        EscherContainerRecord escherContainerRecord;
        createDrawingGroup();
        this.escherBSERecords.add(escherBSERecord);
        EscherContainerRecord escherContainerRecord2 = (EscherContainerRecord) ((DrawingGroupRecord) getRecords().get(findFirstRecordLocBySid(DrawingGroupRecord.sid))).getEscherRecord(0);
        if (escherContainerRecord2.getChild(1).getRecordId() == EscherContainerRecord.BSTORE_CONTAINER) {
            escherContainerRecord = (EscherContainerRecord) escherContainerRecord2.getChild(1);
        } else {
            EscherContainerRecord escherContainerRecord3 = new EscherContainerRecord();
            escherContainerRecord3.setRecordId(EscherContainerRecord.BSTORE_CONTAINER);
            List<EscherRecord> childRecords = escherContainerRecord2.getChildRecords();
            childRecords.add(1, escherContainerRecord3);
            escherContainerRecord2.setChildRecords(childRecords);
            escherContainerRecord = escherContainerRecord3;
        }
        escherContainerRecord.setOptions((short) ((this.escherBSERecords.size() << 4) | 15));
        escherContainerRecord.addChildRecord(escherBSERecord);
        return this.escherBSERecords.size();
    }

    public DrawingManager2 getDrawingManager() {
        return this.drawingManager;
    }

    public WriteProtectRecord getWriteProtect() {
        if (this.writeProtect == null) {
            this.writeProtect = new WriteProtectRecord();
            this.records.add(findFirstRecordLocBySid(2057) + 1, this.writeProtect);
        }
        return this.writeProtect;
    }

    public WriteAccessRecord getWriteAccess() {
        if (this.writeAccess == null) {
            this.writeAccess = createWriteAccess();
            this.records.add(findFirstRecordLocBySid(InterfaceEndRecord.sid) + 1, this.writeAccess);
        }
        return this.writeAccess;
    }

    public FileSharingRecord getFileSharing() {
        if (this.fileShare == null) {
            this.fileShare = new FileSharingRecord();
            this.records.add(findFirstRecordLocBySid(92) + 1, this.fileShare);
        }
        return this.fileShare;
    }

    public boolean isWriteProtected() {
        if (this.fileShare != null && getFileSharing().getReadOnly() == 1) {
            return true;
        }
        return false;
    }

    public void writeProtectWorkbook(String str, String str2) {
        FileSharingRecord fileSharing = getFileSharing();
        WriteAccessRecord writeAccess2 = getWriteAccess();
        getWriteProtect();
        fileSharing.setReadOnly(1);
        fileSharing.setPassword((short) CryptoFunctions.createXorVerifier1(str));
        fileSharing.setUsername(str2);
        writeAccess2.setUsername(str2);
    }

    public void unwriteProtectWorkbook() {
        this.records.remove((Object) this.fileShare);
        this.records.remove((Object) this.writeProtect);
        this.fileShare = null;
        this.writeProtect = null;
    }

    public String resolveNameXText(int i, int i2) {
        return this.linkTable.resolveNameXText(i, i2, this);
    }

    public NameXPtg getNameXPtg(String str, int i, UDFFinder uDFFinder) {
        LinkTable orCreateLinkTable = getOrCreateLinkTable();
        NameXPtg nameXPtg = orCreateLinkTable.getNameXPtg(str, i);
        return (nameXPtg != null || uDFFinder.findFunction(str) == null) ? nameXPtg : orCreateLinkTable.addNameXPtg(str);
    }

    public NameXPtg getNameXPtg(String str, UDFFinder uDFFinder) {
        return getNameXPtg(str, -1, uDFFinder);
    }

    public void cloneDrawings(InternalSheet internalSheet) {
        EscherContainerRecord escherContainer;
        EscherSimpleProperty escherSimpleProperty;
        findDrawingGroup();
        DrawingManager2 drawingManager2 = this.drawingManager;
        if (drawingManager2 != null && internalSheet.aggregateDrawingRecords(drawingManager2, false) != -1 && (escherContainer = ((EscherAggregate) internalSheet.findFirstRecordBySid(EscherAggregate.sid)).getEscherContainer()) != null) {
            EscherDggRecord dgg = this.drawingManager.getDgg();
            short findNewDrawingGroupId = this.drawingManager.findNewDrawingGroupId();
            dgg.addCluster(findNewDrawingGroupId, 0);
            dgg.setDrawingsSaved(dgg.getDrawingsSaved() + 1);
            Iterator<EscherRecord> it = escherContainer.iterator();
            EscherDgRecord escherDgRecord = null;
            while (it.hasNext()) {
                EscherRecord next = it.next();
                if (next instanceof EscherDgRecord) {
                    EscherDgRecord escherDgRecord2 = (EscherDgRecord) next;
                    escherDgRecord2.setOptions((short) (findNewDrawingGroupId << 4));
                    escherDgRecord = escherDgRecord2;
                } else if (next instanceof EscherContainerRecord) {
                    Iterator<EscherRecord> it2 = ((EscherContainerRecord) next).iterator();
                    while (it2.hasNext()) {
                        Iterator<EscherRecord> it3 = ((EscherContainerRecord) it2.next()).iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                EscherRecord next2 = it3.next();
                                short recordId = next2.getRecordId();
                                if (recordId == EscherSpRecord.RECORD_ID) {
                                    if (escherDgRecord != null) {
                                        int allocateShapeId = this.drawingManager.allocateShapeId(escherDgRecord);
                                        escherDgRecord.setNumShapes(escherDgRecord.getNumShapes() - 1);
                                        ((EscherSpRecord) next2).setShapeId(allocateShapeId);
                                    } else {
                                        throw new RecordFormatException("EscherDgRecord wasn't set/processed before.");
                                    }
                                } else if (recordId == EscherOptRecord.RECORD_ID && (escherSimpleProperty = (EscherSimpleProperty) ((EscherOptRecord) next2).lookup(EscherPropertyTypes.BLIP__BLIPTODISPLAY)) != null) {
                                    EscherBSERecord bSERecord = getBSERecord(escherSimpleProperty.getPropertyValue());
                                    bSERecord.setRef(bSERecord.getRef() + 1);
                                }
                            }
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
    }

    public NameRecord cloneFilter(int i, int i2) {
        NameRecord nameRecord = getNameRecord(i);
        short checkExternSheet = checkExternSheet(i2);
        Ptg[] nameDefinition = nameRecord.getNameDefinition();
        for (int i3 = 0; i3 < nameDefinition.length; i3++) {
            Ptg ptg = nameDefinition[i3];
            if (ptg instanceof Area3DPtg) {
                Area3DPtg area3DPtg = (Area3DPtg) ((OperandPtg) ptg).copy();
                area3DPtg.setExternSheetIndex(checkExternSheet);
                nameDefinition[i3] = area3DPtg;
            } else if (ptg instanceof Ref3DPtg) {
                Ref3DPtg ref3DPtg = (Ref3DPtg) ((OperandPtg) ptg).copy();
                ref3DPtg.setExternSheetIndex(checkExternSheet);
                nameDefinition[i3] = ref3DPtg;
            }
        }
        NameRecord createBuiltInName = createBuiltInName((byte) 13, i2 + 1);
        createBuiltInName.setNameDefinition(nameDefinition);
        createBuiltInName.setHidden(true);
        return createBuiltInName;
    }

    public void updateNamesAfterCellShift(FormulaShifter formulaShifter) {
        for (int i = 0; i < getNumNames(); i++) {
            NameRecord nameRecord = getNameRecord(i);
            Ptg[] nameDefinition = nameRecord.getNameDefinition();
            if (formulaShifter.adjustFormula(nameDefinition, nameRecord.getSheetNumber())) {
                nameRecord.setNameDefinition(nameDefinition);
            }
        }
    }

    public RecalcIdRecord getRecalcId() {
        RecalcIdRecord recalcIdRecord = (RecalcIdRecord) findFirstRecordBySid(RecalcIdRecord.sid);
        if (recalcIdRecord != null) {
            return recalcIdRecord;
        }
        RecalcIdRecord recalcIdRecord2 = new RecalcIdRecord();
        this.records.add(findFirstRecordLocBySid(140) + 1, recalcIdRecord2);
        return recalcIdRecord2;
    }

    public boolean changeExternalReference(String str, String str2) {
        return this.linkTable.changeExternalReference(str, str2);
    }

    @Internal
    public WorkbookRecordList getWorkbookRecordList() {
        return this.records;
    }
}
