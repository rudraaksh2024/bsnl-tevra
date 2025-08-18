package org.apache.poi.xssf.streaming;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.CodepointsUtil;
import org.apache.poi.util.Removal;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

public class SheetDataWriter implements Closeable {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SheetDataWriter.class);
    private final File _fd;
    private int _lowestIndexOfFlushedRows;
    private int _numberLastFlushedRow;
    private int _numberOfCellsOfLastFlushedRow;
    private int _numberOfFlushedRows;
    protected final Writer _out;
    private int _rownum;
    private SharedStringsTable _sharedStringSource;

    static boolean replaceWithQuestionMark(char c) {
        return c < ' ' || (65534 <= c && c <= 65535);
    }

    /* access modifiers changed from: protected */
    public InputStream decorateInputStream(FileInputStream fileInputStream) throws IOException {
        return fileInputStream;
    }

    /* access modifiers changed from: protected */
    public OutputStream decorateOutputStream(FileOutputStream fileOutputStream) throws IOException {
        return fileOutputStream;
    }

    public SheetDataWriter() throws IOException {
        this._numberLastFlushedRow = -1;
        File createTempFile = createTempFile();
        this._fd = createTempFile;
        this._out = createWriter(createTempFile);
    }

    public SheetDataWriter(Writer writer) throws IOException {
        this._numberLastFlushedRow = -1;
        this._fd = null;
        this._out = writer;
    }

    public SheetDataWriter(SharedStringsTable sharedStringsTable) throws IOException {
        this();
        this._sharedStringSource = sharedStringsTable;
    }

    @Removal(version = "6.0.0")
    public File createTempFile() throws IOException {
        return TempFile.createTempFile("poi-sxssf-sheet", ".xml");
    }

    @Removal(version = "6.0.0")
    public Writer createWriter(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            return new BufferedWriter(new OutputStreamWriter(decorateOutputStream(fileOutputStream), StandardCharsets.UTF_8));
        } catch (IOException e) {
            fileOutputStream.close();
            throw e;
        }
    }

    public void close() throws IOException {
        this._out.close();
    }

    /* access modifiers changed from: protected */
    public File getTempFile() {
        return this._fd;
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        File tempFile = getTempFile();
        if (tempFile != null) {
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            try {
                return decorateInputStream(fileInputStream);
            } catch (IOException e) {
                fileInputStream.close();
                throw e;
            }
        } else {
            throw new IOException("getWorksheetXMLInputStream only works when a temp file is used");
        }
    }

    public int getNumberOfFlushedRows() {
        return this._numberOfFlushedRows;
    }

    public int getNumberOfCellsOfLastFlushedRow() {
        return this._numberOfCellsOfLastFlushedRow;
    }

    public int getLowestIndexOfFlushedRows() {
        return this._lowestIndexOfFlushedRows;
    }

    public int getLastFlushedRow() {
        return this._numberLastFlushedRow;
    }

    public void writeRow(int i, SXSSFRow sXSSFRow) throws IOException {
        if (this._numberOfFlushedRows == 0) {
            this._lowestIndexOfFlushedRows = i;
        }
        this._numberLastFlushedRow = Math.max(i, this._numberLastFlushedRow);
        this._numberOfCellsOfLastFlushedRow = sXSSFRow.getLastCellNum();
        this._numberOfFlushedRows++;
        beginRow(i, sXSSFRow);
        Iterator<Cell> allCellsIterator = sXSSFRow.allCellsIterator();
        int i2 = 0;
        while (allCellsIterator.hasNext()) {
            writeCell(i2, allCellsIterator.next());
            i2++;
        }
        endRow();
    }

    /* access modifiers changed from: package-private */
    public void beginRow(int i, SXSSFRow sXSSFRow) throws IOException {
        String str;
        this._out.write("<row");
        writeAttribute("r", Integer.toString(i + 1));
        if (sXSSFRow.hasCustomHeight()) {
            writeAttribute("customHeight", "true");
            writeAttribute("ht", Float.toString(sXSSFRow.getHeightInPoints()));
        }
        if (sXSSFRow.getZeroHeight()) {
            writeAttribute(CellUtil.HIDDEN, "true");
        }
        String str2 = "1";
        if (sXSSFRow.isFormatted()) {
            writeAttribute("s", Integer.toString(sXSSFRow.getRowStyleIndex()));
            writeAttribute("customFormat", str2);
        }
        if (sXSSFRow.getOutlineLevel() != 0) {
            writeAttribute("outlineLevel", Integer.toString(sXSSFRow.getOutlineLevel()));
        }
        if (sXSSFRow.getHidden() != null) {
            if (sXSSFRow.getHidden().booleanValue()) {
                str = str2;
            } else {
                str = "0";
            }
            writeAttribute(CellUtil.HIDDEN, str);
        }
        if (sXSSFRow.getCollapsed() != null) {
            if (!sXSSFRow.getCollapsed().booleanValue()) {
                str2 = "0";
            }
            writeAttribute("collapsed", str2);
        }
        this._out.write(">\n");
        this._rownum = i;
    }

    /* access modifiers changed from: package-private */
    public void endRow() throws IOException {
        this._out.write("</row>\n");
    }

    public void writeCell(int i, Cell cell) throws IOException {
        if (cell != null) {
            String formatAsString = new CellReference(this._rownum, i).formatAsString();
            this._out.write("<c");
            writeAttribute("r", formatAsString);
            CellStyle cellStyle = cell.getCellStyle();
            if (cellStyle.getIndex() != 0) {
                writeAttribute("s", Integer.toString(cellStyle.getIndex() & 65535));
            }
            CellType cellType = cell.getCellType();
            String str = "1";
            switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
                case 1:
                    writeAttribute("t", "n");
                    this._out.write("><v>");
                    this._out.write(Double.toString(cell.getNumericCellValue()));
                    this._out.write("</v>");
                    break;
                case 2:
                    if (this._sharedStringSource == null) {
                        writeAttribute("t", "inlineStr");
                        this._out.write("><is><t");
                        if (hasLeadingTrailingSpaces(cell.getStringCellValue())) {
                            writeAttribute("xml:space", "preserve");
                        }
                        this._out.write(">");
                        outputEscapedString(cell.getStringCellValue());
                        this._out.write("</t></is>");
                        break;
                    } else {
                        int addSharedStringItem = this._sharedStringSource.addSharedStringItem(cell.getRichStringCellValue());
                        writeAttribute("t", STCellType.S.toString());
                        this._out.write("><v>");
                        this._out.write(String.valueOf(addSharedStringItem));
                        this._out.write("</v>");
                        break;
                    }
                case 3:
                    writeAttribute("t", "b");
                    this._out.write("><v>");
                    Writer writer = this._out;
                    if (!cell.getBooleanCellValue()) {
                        str = "0";
                    }
                    writer.write(str);
                    this._out.write("</v>");
                    break;
                case 4:
                    FormulaError forInt = FormulaError.forInt(cell.getErrorCellValue());
                    writeAttribute("t", "e");
                    this._out.write("><v>");
                    outputEscapedString(forInt.getString());
                    this._out.write("</v>");
                    break;
                case 5:
                    this._out.write(62);
                    break;
                case 6:
                    int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cell.getCachedFormulaResultType().ordinal()];
                    if (i2 == 1) {
                        writeAttribute("t", "n");
                    } else if (i2 == 2) {
                        writeAttribute("t", STCellType.STR.toString());
                    } else if (i2 == 3) {
                        writeAttribute("t", "b");
                    } else if (i2 == 4) {
                        writeAttribute("t", "e");
                    }
                    this._out.write("><f>");
                    outputEscapedString(cell.getCellFormula());
                    this._out.write("</f>");
                    int i3 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cell.getCachedFormulaResultType().ordinal()];
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 != 3) {
                                if (i3 == 4) {
                                    FormulaError forInt2 = FormulaError.forInt(cell.getErrorCellValue());
                                    this._out.write("><v>");
                                    outputEscapedString(forInt2.getString());
                                    this._out.write("</v>");
                                    break;
                                }
                            } else {
                                this._out.write("><v>");
                                Writer writer2 = this._out;
                                if (!cell.getBooleanCellValue()) {
                                    str = "0";
                                }
                                writer2.write(str);
                                this._out.write("</v>");
                                break;
                            }
                        } else {
                            String stringCellValue = cell.getStringCellValue();
                            if (stringCellValue != null && !stringCellValue.isEmpty()) {
                                this._out.write("<v>");
                                outputEscapedString(stringCellValue);
                                this._out.write("</v>");
                                break;
                            }
                        }
                    } else {
                        double numericCellValue = cell.getNumericCellValue();
                        if (!Double.isNaN(numericCellValue)) {
                            this._out.write("<v>");
                            this._out.write(Double.toString(numericCellValue));
                            this._out.write("</v>");
                            break;
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Invalid cell type: " + cellType);
            }
            this._out.write("</c>");
        }
    }

    /* renamed from: org.apache.poi.xssf.streaming.SheetDataWriter$1  reason: invalid class name */
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SheetDataWriter.AnonymousClass1.<clinit>():void");
        }
    }

    private void writeAttribute(String str, String str2) throws IOException {
        this._out.write(32);
        this._out.write(str);
        this._out.write("=\"");
        this._out.write(str2);
        this._out.write(34);
    }

    /* access modifiers changed from: package-private */
    public boolean hasLeadingTrailingSpaces(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        char charAt = str.charAt(0);
        char charAt2 = str.charAt(str.length() - 1);
        if (Character.isWhitespace(charAt) || Character.isWhitespace(charAt2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void outputEscapedString(String str) throws IOException {
        if (str != null && str.length() != 0) {
            Iterator<String> iteratorFor = CodepointsUtil.iteratorFor(str);
            while (iteratorFor.hasNext()) {
                String next = iteratorFor.next();
                next.hashCode();
                char c = 65535;
                switch (next.hashCode()) {
                    case 9:
                        if (next.equals("\t")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 10:
                        if (next.equals("\n")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 13:
                        if (next.equals("\r")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 34:
                        if (next.equals("\"")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 38:
                        if (next.equals("&")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 60:
                        if (next.equals("<")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 62:
                        if (next.equals(">")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 160:
                        if (next.equals(" ")) {
                            c = 7;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this._out.write("&#x9;");
                        break;
                    case 1:
                        this._out.write("&#xa;");
                        break;
                    case 2:
                        this._out.write("&#xd;");
                        break;
                    case 3:
                        this._out.write("&quot;");
                        break;
                    case 4:
                        this._out.write("&amp;");
                        break;
                    case 5:
                        this._out.write("&lt;");
                        break;
                    case 6:
                        this._out.write("&gt;");
                        break;
                    case 7:
                        this._out.write("&#xa0;");
                        break;
                    default:
                        if (next.length() != 1) {
                            this._out.write(next);
                            break;
                        } else {
                            char charAt = next.charAt(0);
                            if (!replaceWithQuestionMark(charAt)) {
                                this._out.write(charAt);
                                break;
                            } else {
                                this._out.write(63);
                                break;
                            }
                        }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void flush() throws IOException {
        this._out.flush();
    }

    /* access modifiers changed from: package-private */
    public boolean dispose() throws IOException {
        File file;
        try {
            this._out.close();
            return file.delete();
        } finally {
            this._fd.delete();
        }
    }
}
