package org.apache.poi.xssf.binary;

import java.io.InputStream;
import java.util.Queue;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFComment;

@Internal
public class XSSFBSheetHandler extends XSSFBParser {
    private static final int CHECK_ALL_ROWS = -1;
    private final XSSFBCellHeader cellBuffer = new XSSFBCellHeader();
    private final XSSFBCommentsTable comments;
    private int currentRow;
    private final DataFormatter dataFormatter;
    private final boolean formulasNotResults;
    private final XSSFSheetXMLHandler.SheetContentsHandler handler;
    private XSSFBCellRange hyperlinkCellRange;
    private int lastEndedRow = -1;
    private int lastStartedRow = -1;
    private byte[] rkBuffer = new byte[8];
    private final SharedStrings stringsTable;
    private final XSSFBStylesTable styles;
    private StringBuilder xlWideStringBuffer = new StringBuilder();

    public interface SheetContentsHandler extends XSSFSheetXMLHandler.SheetContentsHandler {
        void hyperlinkCell(String str, String str2, String str3, String str4, XSSFComment xSSFComment);
    }

    public XSSFBSheetHandler(InputStream inputStream, XSSFBStylesTable xSSFBStylesTable, XSSFBCommentsTable xSSFBCommentsTable, SharedStrings sharedStrings, XSSFSheetXMLHandler.SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter2, boolean z) {
        super(inputStream);
        this.styles = xSSFBStylesTable;
        this.comments = xSSFBCommentsTable;
        this.stringsTable = sharedStrings;
        this.handler = sheetContentsHandler;
        this.dataFormatter = dataFormatter2;
        this.formulasNotResults = z;
    }

    public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType[XSSFBRecordType.lookup(i).ordinal()]) {
            case 1:
                int castToInt = XSSFBUtils.castToInt(LittleEndian.getUInt(bArr, 0));
                if (castToInt <= 1048576) {
                    this.currentRow = castToInt;
                    checkMissedComments(castToInt);
                    startRow(this.currentRow);
                    return;
                }
                throw new XSSFBParseException("Row number beyond allowable range: " + castToInt);
            case 2:
                handleBrtCellIsst(bArr);
                return;
            case 3:
                handleCellSt(bArr);
                return;
            case 4:
                handleCellRk(bArr);
                return;
            case 5:
                handleCellReal(bArr);
                return;
            case 6:
                handleBoolean(bArr);
                return;
            case 7:
                handleCellError(bArr);
                return;
            case 8:
                beforeCellValue(bArr);
                return;
            case 9:
                handleFmlaString(bArr);
                return;
            case 10:
                handleFmlaNum(bArr);
                return;
            case 11:
                handleFmlaError(bArr);
                return;
            case 12:
                checkMissedComments(-1);
                endRow(this.lastStartedRow);
                return;
            case 13:
                handleHeaderFooter(bArr);
                return;
            default:
                return;
        }
    }

    /* renamed from: org.apache.poi.xssf.binary.XSSFBSheetHandler$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xssf.binary.XSSFBRecordType[] r0 = org.apache.poi.xssf.binary.XSSFBRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType = r0
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtRowHdr     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellIsst     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellSt     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellRk     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellReal     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellBool     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellError     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCellBlank     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtFmlaString     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtFmlaNum     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtFmlaError     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtEndSheetData     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtBeginHeaderFooter     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBSheetHandler.AnonymousClass1.<clinit>():void");
        }
    }

    private void beforeCellValue(byte[] bArr) {
        XSSFBCellHeader.parse(bArr, 0, this.currentRow, this.cellBuffer);
        checkMissedComments(this.currentRow, this.cellBuffer.getColNum());
    }

    private void handleCellValue(String str) {
        CellAddress cellAddress = new CellAddress(this.currentRow, this.cellBuffer.getColNum());
        XSSFBCommentsTable xSSFBCommentsTable = this.comments;
        this.handler.cell(cellAddress.formatAsString(), str, xSSFBCommentsTable != null ? xSSFBCommentsTable.get(cellAddress) : null);
    }

    private void handleFmlaNum(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue(formatVal(LittleEndian.getDouble(bArr, 8), this.cellBuffer.getStyleIdx()));
    }

    private void handleCellSt(byte[] bArr) {
        beforeCellValue(bArr);
        this.xlWideStringBuffer.setLength(0);
        XSSFBUtils.readXLWideString(bArr, 8, this.xlWideStringBuffer);
        handleCellValue(this.xlWideStringBuffer.toString());
    }

    private void handleFmlaString(byte[] bArr) {
        beforeCellValue(bArr);
        this.xlWideStringBuffer.setLength(0);
        XSSFBUtils.readXLWideString(bArr, 8, this.xlWideStringBuffer);
        handleCellValue(this.xlWideStringBuffer.toString());
    }

    private void handleCellError(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue("ERROR");
    }

    private void handleFmlaError(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue("ERROR");
    }

    private void handleBoolean(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue(bArr[8] == 1 ? "TRUE" : "FALSE");
    }

    private void handleCellReal(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue(formatVal(LittleEndian.getDouble(bArr, 8), this.cellBuffer.getStyleIdx()));
    }

    private void handleCellRk(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue(formatVal(rkNumber(bArr, 8), this.cellBuffer.getStyleIdx()));
    }

    private String formatVal(double d, int i) {
        String numberFormatString = this.styles.getNumberFormatString(i);
        short numberFormatIndex = this.styles.getNumberFormatIndex(i);
        if (numberFormatString == null) {
            numberFormatIndex = 0;
            numberFormatString = BuiltinFormats.getBuiltinFormat(0);
        }
        return this.dataFormatter.formatRawCellContents(d, numberFormatIndex, numberFormatString);
    }

    private void handleBrtCellIsst(byte[] bArr) {
        beforeCellValue(bArr);
        handleCellValue(this.stringsTable.getItemAt(XSSFBUtils.castToInt(LittleEndian.getUInt(bArr, 8))).getString());
    }

    private void handleHeaderFooter(byte[] bArr) {
        XSSFBHeaderFooters parse = XSSFBHeaderFooters.parse(bArr);
        outputHeaderFooter(parse.getHeader());
        outputHeaderFooter(parse.getFooter());
        outputHeaderFooter(parse.getHeaderEven());
        outputHeaderFooter(parse.getFooterEven());
        outputHeaderFooter(parse.getHeaderFirst());
        outputHeaderFooter(parse.getFooterFirst());
    }

    private void outputHeaderFooter(XSSFBHeaderFooter xSSFBHeaderFooter) {
        String string = xSSFBHeaderFooter.getString();
        if (string != null && !string.trim().isEmpty()) {
            this.handler.headerFooter(string, xSSFBHeaderFooter.isHeader(), xSSFBHeaderFooter.getHeaderFooterTypeLabel());
        }
    }

    private void checkMissedComments(int i, int i2) {
        XSSFBCommentsTable xSSFBCommentsTable = this.comments;
        if (xSSFBCommentsTable != null) {
            Queue<CellAddress> addresses = xSSFBCommentsTable.getAddresses();
            while (!addresses.isEmpty()) {
                CellAddress peek = addresses.peek();
                if (peek.getRow() == i && peek.getColumn() < i2) {
                    CellAddress remove = addresses.remove();
                    dumpEmptyCellComment(remove, this.comments.get(remove));
                } else if (peek.getRow() == i && peek.getColumn() == i2) {
                    addresses.remove();
                    return;
                } else if ((peek.getRow() == i && peek.getColumn() > i2) || peek.getRow() > i) {
                    return;
                }
            }
        }
    }

    private void checkMissedComments(int i) {
        XSSFBCommentsTable xSSFBCommentsTable = this.comments;
        if (xSSFBCommentsTable != null) {
            Queue<CellAddress> addresses = xSSFBCommentsTable.getAddresses();
            int i2 = -1;
            while (!addresses.isEmpty()) {
                CellAddress peek = addresses.peek();
                if (i == -1 || peek.getRow() < i) {
                    CellAddress remove = addresses.remove();
                    if (remove.getRow() != i2) {
                        startRow(remove.getRow());
                    }
                    dumpEmptyCellComment(remove, this.comments.get(remove));
                    i2 = remove.getRow();
                } else {
                    return;
                }
            }
        }
    }

    private void startRow(int i) {
        int i2 = this.lastStartedRow;
        if (i != i2) {
            if (i2 != this.lastEndedRow) {
                endRow(i2);
            }
            this.handler.startRow(i);
            this.lastStartedRow = i;
        }
    }

    private void endRow(int i) {
        if (this.lastEndedRow != i) {
            this.handler.endRow(i);
            this.lastEndedRow = i;
        }
    }

    private void dumpEmptyCellComment(CellAddress cellAddress, XSSFBComment xSSFBComment) {
        this.handler.cell(cellAddress.formatAsString(), (String) null, xSSFBComment);
    }

    private double rkNumber(byte[] bArr, int i) {
        double d;
        byte b = bArr[i];
        boolean z = false;
        boolean z2 = (b & 1) == 1;
        if (((b >> 1) & 1) == 0) {
            z = true;
        }
        byte[] bArr2 = this.rkBuffer;
        bArr2[4] = (byte) (((byte) (b & -2)) & -3);
        System.arraycopy(bArr, i + 1, bArr2, 5, 3);
        if (z) {
            d = LittleEndian.getDouble(this.rkBuffer);
        } else {
            d = (double) (LittleEndian.getInt(this.rkBuffer, 4) >> 2);
        }
        return z2 ? d / 100.0d : d;
    }
}
