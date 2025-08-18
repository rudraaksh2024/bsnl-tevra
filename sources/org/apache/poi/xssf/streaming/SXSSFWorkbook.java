package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SXSSFWorkbook implements Workbook {
    public static final int DEFAULT_WINDOW_SIZE = 100;
    private static final Logger LOG = LogManager.getLogger((Class<?>) SXSSFWorkbook.class);
    private boolean _compressTmpFiles;
    private int _randomAccessWindowSize;
    protected final SharedStringsTable _sharedStringSource;
    private final Map<SXSSFSheet, XSSFSheet> _sxFromXHash;
    protected final XSSFWorkbook _wb;
    private final Map<XSSFSheet, SXSSFSheet> _xFromSxHash;
    protected Zip64Mode zip64Mode;

    protected interface ISheetInjector {
        void writeSheetData(OutputStream outputStream) throws IOException;
    }

    public SXSSFWorkbook() {
        this((XSSFWorkbook) null);
    }

    public SXSSFWorkbook(XSSFWorkbook xSSFWorkbook) {
        this(xSSFWorkbook, 100);
    }

    public SXSSFWorkbook(XSSFWorkbook xSSFWorkbook, int i) {
        this(xSSFWorkbook, i, false);
    }

    public SXSSFWorkbook(XSSFWorkbook xSSFWorkbook, int i, boolean z) {
        this(xSSFWorkbook, i, z, false);
    }

    public SXSSFWorkbook(XSSFWorkbook xSSFWorkbook, int i, boolean z, boolean z2) {
        this._sxFromXHash = new HashMap();
        this._xFromSxHash = new HashMap();
        this._randomAccessWindowSize = 100;
        this.zip64Mode = Zip64Mode.Always;
        setRandomAccessWindowSize(i);
        setCompressTempFiles(z);
        SharedStringsTable sharedStringsTable = null;
        if (xSSFWorkbook == null) {
            XSSFWorkbook xSSFWorkbook2 = new XSSFWorkbook();
            this._wb = xSSFWorkbook2;
            this._sharedStringSource = z2 ? xSSFWorkbook2.getSharedStringSource() : sharedStringsTable;
            return;
        }
        this._wb = xSSFWorkbook;
        this._sharedStringSource = z2 ? xSSFWorkbook.getSharedStringSource() : sharedStringsTable;
        Iterator<Sheet> it = xSSFWorkbook.iterator();
        while (it.hasNext()) {
            createAndRegisterSXSSFSheet((XSSFSheet) it.next());
        }
    }

    public SXSSFWorkbook(int i) {
        this((XSSFWorkbook) null, i);
    }

    public int getRandomAccessWindowSize() {
        return this._randomAccessWindowSize;
    }

    /* access modifiers changed from: protected */
    public void setRandomAccessWindowSize(int i) {
        if (i == 0 || i < -1) {
            throw new IllegalArgumentException("rowAccessWindowSize must be greater than 0 or -1");
        }
        this._randomAccessWindowSize = i;
    }

    public void setZip64Mode(Zip64Mode zip64Mode2) {
        this.zip64Mode = zip64Mode2;
    }

    public boolean isCompressTempFiles() {
        return this._compressTmpFiles;
    }

    public void setCompressTempFiles(boolean z) {
        this._compressTmpFiles = z;
    }

    /* access modifiers changed from: protected */
    @Internal
    public SharedStringsTable getSharedStringSource() {
        return this._sharedStringSource;
    }

    /* access modifiers changed from: protected */
    public SheetDataWriter createSheetDataWriter() throws IOException {
        if (this._compressTmpFiles) {
            return new GZIPSheetDataWriter(this._sharedStringSource);
        }
        return new SheetDataWriter(this._sharedStringSource);
    }

    /* access modifiers changed from: package-private */
    public XSSFSheet getXSSFSheet(SXSSFSheet sXSSFSheet) {
        return this._sxFromXHash.get(sXSSFSheet);
    }

    /* access modifiers changed from: package-private */
    public SXSSFSheet getSXSSFSheet(XSSFSheet xSSFSheet) {
        return this._xFromSxHash.get(xSSFSheet);
    }

    /* access modifiers changed from: package-private */
    public void registerSheetMapping(SXSSFSheet sXSSFSheet, XSSFSheet xSSFSheet) {
        this._sxFromXHash.put(sXSSFSheet, xSSFSheet);
        this._xFromSxHash.put(xSSFSheet, sXSSFSheet);
    }

    /* access modifiers changed from: package-private */
    public void deregisterSheetMapping(XSSFSheet xSSFSheet) {
        SXSSFSheet sXSSFSheet = getSXSSFSheet(xSSFSheet);
        if (sXSSFSheet != null) {
            IOUtils.closeQuietly(sXSSFSheet.getSheetDataWriter());
            this._sxFromXHash.remove(sXSSFSheet);
            this._xFromSxHash.remove(xSSFSheet);
        }
    }

    /* access modifiers changed from: protected */
    public XSSFSheet getSheetFromZipEntryName(String str) {
        for (XSSFSheet next : this._sxFromXHash.values()) {
            if (str.equals(next.getPackagePart().getPartName().getName().substring(1))) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0077, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0078, code lost:
        if (r2 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0082, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void injectData(org.apache.poi.openxml4j.util.ZipEntrySource r8, java.io.OutputStream r9) throws java.io.IOException {
        /*
            r7 = this;
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r9 = r7.createArchiveOutputStream(r9)
            java.util.Enumeration r0 = r8.getEntries()     // Catch:{ all -> 0x008f }
        L_0x0008:
            boolean r1 = r0.hasMoreElements()     // Catch:{ all -> 0x008f }
            if (r1 == 0) goto L_0x0088
            java.lang.Object r1 = r0.nextElement()     // Catch:{ all -> 0x008f }
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r1 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r1     // Catch:{ all -> 0x008f }
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r2 = new org.apache.commons.compress.archivers.zip.ZipArchiveEntry     // Catch:{ all -> 0x008f }
            java.lang.String r3 = r1.getName()     // Catch:{ all -> 0x008f }
            r2.<init>((java.lang.String) r3)     // Catch:{ all -> 0x008f }
            long r3 = r1.getSize()     // Catch:{ all -> 0x008f }
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 < 0) goto L_0x002e
            long r3 = r1.getSize()     // Catch:{ all -> 0x008f }
            r2.setSize(r3)     // Catch:{ all -> 0x008f }
        L_0x002e:
            long r3 = r1.getTime()     // Catch:{ all -> 0x008f }
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 < 0) goto L_0x003d
            long r3 = r1.getTime()     // Catch:{ all -> 0x008f }
            r2.setTime(r3)     // Catch:{ all -> 0x008f }
        L_0x003d:
            r9.putArchiveEntry(r2)     // Catch:{ all -> 0x008f }
            java.io.InputStream r2 = r8.getInputStream(r1)     // Catch:{ all -> 0x0083 }
            boolean r3 = r2 instanceof org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream     // Catch:{ all -> 0x0075 }
            if (r3 == 0) goto L_0x004f
            r3 = r2
            org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream r3 = (org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream) r3     // Catch:{ all -> 0x0075 }
            r4 = 0
            r3.setGuardState(r4)     // Catch:{ all -> 0x0075 }
        L_0x004f:
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0075 }
            org.apache.poi.xssf.usermodel.XSSFSheet r1 = r7.getSheetFromZipEntryName(r1)     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x0069
            boolean r3 = r1 instanceof org.apache.poi.xssf.usermodel.XSSFChartSheet     // Catch:{ all -> 0x0075 }
            if (r3 != 0) goto L_0x0069
            org.apache.poi.xssf.streaming.SXSSFSheet r1 = r7.getSXSSFSheet(r1)     // Catch:{ all -> 0x0075 }
            org.apache.poi.xssf.streaming.SXSSFWorkbook$ISheetInjector r1 = r7.createSheetInjector(r1)     // Catch:{ all -> 0x0075 }
            copyStreamAndInjectWorksheet(r2, r9, r1)     // Catch:{ all -> 0x0075 }
            goto L_0x006c
        L_0x0069:
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r2, (java.io.OutputStream) r9)     // Catch:{ all -> 0x0075 }
        L_0x006c:
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ all -> 0x0083 }
        L_0x0071:
            r9.closeArchiveEntry()     // Catch:{ all -> 0x008f }
            goto L_0x0008
        L_0x0075:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r0 = move-exception
            if (r2 == 0) goto L_0x0082
            r2.close()     // Catch:{ all -> 0x007e }
            goto L_0x0082
        L_0x007e:
            r1 = move-exception
            r7.addSuppressed(r1)     // Catch:{ all -> 0x0083 }
        L_0x0082:
            throw r0     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r7 = move-exception
            r9.closeArchiveEntry()     // Catch:{ all -> 0x008f }
            throw r7     // Catch:{ all -> 0x008f }
        L_0x0088:
            r9.finish()
            r8.close()
            return
        L_0x008f:
            r7 = move-exception
            r9.finish()
            r8.close()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFWorkbook.injectData(org.apache.poi.openxml4j.util.ZipEntrySource, java.io.OutputStream):void");
    }

    /* access modifiers changed from: protected */
    public ZipArchiveOutputStream createArchiveOutputStream(OutputStream outputStream) {
        if (Zip64Mode.Always.equals(this.zip64Mode)) {
            return new OpcZipArchiveOutputStream(outputStream);
        }
        ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream);
        zipArchiveOutputStream.setUseZip64(this.zip64Mode);
        return zipArchiveOutputStream;
    }

    /* access modifiers changed from: protected */
    public ISheetInjector createSheetInjector(SXSSFSheet sXSSFSheet) throws IOException {
        return new SXSSFWorkbook$$ExternalSyntheticLambda0(sXSSFSheet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        if (r1 != null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$createSheetInjector$0(org.apache.poi.xssf.streaming.SXSSFSheet r1, java.io.OutputStream r2) throws java.io.IOException {
        /*
            java.io.InputStream r1 = r1.getWorksheetXMLInputStream()
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r1, (java.io.OutputStream) r2)     // Catch:{ all -> 0x000d }
            if (r1 == 0) goto L_0x000c
            r1.close()
        L_0x000c:
            return
        L_0x000d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x000f }
        L_0x000f:
            r0 = move-exception
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ all -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x001a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFWorkbook.lambda$createSheetInjector$0(org.apache.poi.xssf.streaming.SXSSFSheet, java.io.OutputStream):void");
    }

    private static void copyStreamAndInjectWorksheet(InputStream inputStream, OutputStream outputStream, ISheetInjector iSheetInjector) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        int i = 10;
        boolean z = true;
        int i2 = 0;
        String str = "<sheetData";
        while (true) {
            int read = inputStreamReader.read();
            if (read == -1) {
                break;
            }
            if (read == str.charAt(i2)) {
                i2++;
                if (i2 != i) {
                    continue;
                } else if (!"<sheetData".equals(str)) {
                    break;
                } else {
                    int read2 = inputStreamReader.read();
                    if (read2 == -1) {
                        outputStreamWriter.write(str);
                        break;
                    } else if (read2 == 62) {
                        outputStreamWriter.write(str);
                        outputStreamWriter.write(read2);
                        i = 12;
                        i2 = 0;
                        z = false;
                        str = "</sheetData>";
                    } else if (read2 == 47) {
                        int read3 = inputStreamReader.read();
                        if (read3 == -1) {
                            outputStreamWriter.write(str);
                            break;
                        } else if (read3 == 62) {
                            break;
                        } else {
                            outputStreamWriter.write(str);
                            outputStreamWriter.write(47);
                            outputStreamWriter.write(read3);
                        }
                    } else {
                        outputStreamWriter.write(str);
                        outputStreamWriter.write(47);
                        outputStreamWriter.write(read2);
                    }
                }
            } else {
                if (i2 > 0) {
                    outputStreamWriter.write(str, 0, i2);
                }
                if (read == str.charAt(0)) {
                    i2 = 1;
                } else {
                    outputStreamWriter.write(read);
                }
            }
            i2 = 0;
        }
        outputStreamWriter.flush();
        if (z) {
            outputStreamWriter.write("<sheetData>\n");
            outputStreamWriter.flush();
        }
        iSheetInjector.writeSheetData(outputStream);
        outputStreamWriter.write("</sheetData>");
        outputStreamWriter.flush();
        while (true) {
            int read4 = inputStreamReader.read();
            if (read4 != -1) {
                outputStreamWriter.write(read4);
            } else {
                outputStreamWriter.flush();
                return;
            }
        }
    }

    public XSSFWorkbook getXSSFWorkbook() {
        return this._wb;
    }

    public int getActiveSheetIndex() {
        return this._wb.getActiveSheetIndex();
    }

    public void setActiveSheet(int i) {
        this._wb.setActiveSheet(i);
    }

    public int getFirstVisibleTab() {
        return this._wb.getFirstVisibleTab();
    }

    public void setFirstVisibleTab(int i) {
        this._wb.setFirstVisibleTab(i);
    }

    public void setSheetOrder(String str, int i) {
        this._wb.setSheetOrder(str, i);
    }

    public void setSelectedTab(int i) {
        this._wb.setSelectedTab(i);
    }

    public void setSheetName(int i, String str) {
        this._wb.setSheetName(i, str);
    }

    public String getSheetName(int i) {
        return this._wb.getSheetName(i);
    }

    public int getSheetIndex(String str) {
        return this._wb.getSheetIndex(str);
    }

    public int getSheetIndex(Sheet sheet) {
        return this._wb.getSheetIndex((Sheet) getXSSFSheet((SXSSFSheet) sheet));
    }

    public SXSSFSheet createSheet() {
        return createAndRegisterSXSSFSheet(this._wb.createSheet());
    }

    /* access modifiers changed from: package-private */
    public SXSSFSheet createAndRegisterSXSSFSheet(XSSFSheet xSSFSheet) {
        try {
            SXSSFSheet sXSSFSheet = new SXSSFSheet(this, xSSFSheet);
            registerSheetMapping(sXSSFSheet, xSSFSheet);
            return sXSSFSheet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SXSSFSheet createSheet(String str) {
        return createAndRegisterSXSSFSheet(this._wb.createSheet(str));
    }

    @NotImplemented
    public Sheet cloneSheet(int i) {
        throw new RuntimeException("Not Implemented");
    }

    public int getNumberOfSheets() {
        return this._wb.getNumberOfSheets();
    }

    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator();
    }

    public Spliterator<Sheet> spliterator() {
        return this._wb.spliterator();
    }

    protected final class SheetIterator<T extends Sheet> implements Iterator<T> {
        private final Iterator<XSSFSheet> it;

        public SheetIterator() {
            this.it = SXSSFWorkbook.this._wb.iterator();
        }

        public boolean hasNext() {
            return this.it.hasNext();
        }

        public T next() throws NoSuchElementException {
            return SXSSFWorkbook.this.getSXSSFSheet(this.it.next());
        }

        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on XSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }

    public SXSSFSheet getSheetAt(int i) {
        return getSXSSFSheet(this._wb.getSheetAt(i));
    }

    public SXSSFSheet getSheet(String str) {
        return getSXSSFSheet(this._wb.getSheet(str));
    }

    public void removeSheetAt(int i) {
        XSSFSheet sheetAt = this._wb.getSheetAt(i);
        SXSSFSheet sXSSFSheet = getSXSSFSheet(sheetAt);
        this._wb.removeSheetAt(i);
        deregisterSheetMapping(sheetAt);
        try {
            sXSSFSheet.dispose();
        } catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("Failed to dispose old sheet");
        }
    }

    public Font createFont() {
        return this._wb.createFont();
    }

    public Font findFont(boolean z, short s, short s2, String str, boolean z2, boolean z3, short s3, byte b) {
        return this._wb.findFont(z, s, s2, str, z2, z3, s3, b);
    }

    public int getNumberOfFonts() {
        return this._wb.getNumberOfFonts();
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getNumberOfFontsAsInt() {
        return getNumberOfFonts();
    }

    public Font getFontAt(int i) {
        return this._wb.getFontAt(i);
    }

    public CellStyle createCellStyle() {
        return this._wb.createCellStyle();
    }

    public int getNumCellStyles() {
        return this._wb.getNumCellStyles();
    }

    public CellStyle getCellStyleAt(int i) {
        return this._wb.getCellStyleAt(i);
    }

    public void close() throws IOException {
        for (SXSSFSheet next : this._xFromSxHash.values()) {
            try {
                SheetDataWriter sheetDataWriter = next.getSheetDataWriter();
                if (sheetDataWriter != null) {
                    sheetDataWriter.close();
                }
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("An exception occurred while closing sheet data writer for sheet {}.", (Object) next.getSheetName());
            }
        }
        this._wb.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0055, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005e, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0061, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x006a, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.io.OutputStream r4) throws java.io.IOException {
        /*
            r3 = this;
            r3.flushSheets()
            java.lang.String r0 = "poi-sxssf-template"
            java.lang.String r1 = ".xlsx"
            java.io.File r0 = org.apache.poi.util.TempFile.createTempFile(r0, r1)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x006b }
            r1.<init>(r0)     // Catch:{ all -> 0x006b }
            org.apache.poi.xssf.usermodel.XSSFWorkbook r2 = r3._wb     // Catch:{ all -> 0x005f }
            r2.write(r1)     // Catch:{ all -> 0x005f }
            r1.close()     // Catch:{ all -> 0x006b }
            org.apache.poi.openxml4j.util.ZipSecureFile r1 = new org.apache.poi.openxml4j.util.ZipSecureFile     // Catch:{ all -> 0x006b }
            r1.<init>((java.io.File) r0)     // Catch:{ all -> 0x006b }
            org.apache.poi.openxml4j.util.ZipFileZipEntrySource r2 = new org.apache.poi.openxml4j.util.ZipFileZipEntrySource     // Catch:{ all -> 0x0053 }
            r2.<init>(r1)     // Catch:{ all -> 0x0053 }
            r3.injectData(r2, r4)     // Catch:{ all -> 0x0047 }
            r2.close()     // Catch:{ all -> 0x0053 }
            r1.close()     // Catch:{ all -> 0x006b }
            boolean r3 = r0.delete()
            if (r3 == 0) goto L_0x0032
            return
        L_0x0032:
            java.io.IOException r3 = new java.io.IOException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "Could not delete temporary file after processing: "
            r4.<init>(r1)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x0047:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r4 = move-exception
            r2.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ all -> 0x0053 }
        L_0x0052:
            throw r4     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r4 = move-exception
            r1.close()     // Catch:{ all -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch:{ all -> 0x006b }
        L_0x005e:
            throw r4     // Catch:{ all -> 0x006b }
        L_0x005f:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r4 = move-exception
            r1.close()     // Catch:{ all -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch:{ all -> 0x006b }
        L_0x006a:
            throw r4     // Catch:{ all -> 0x006b }
        L_0x006b:
            r3 = move-exception
            r0.delete()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFWorkbook.write(java.io.OutputStream):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r5.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003e, code lost:
        if (r1 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0044, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0048, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0050, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0051, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0054, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeAvoidingTempFiles(java.io.OutputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            r5.flushSheets()
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            org.apache.poi.xssf.usermodel.XSSFWorkbook r1 = r5._wb     // Catch:{ all -> 0x0049 }
            r1.write(r0)     // Catch:{ all -> 0x0049 }
            java.io.InputStream r1 = r0.toInputStream()     // Catch:{ all -> 0x0049 }
            org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource r2 = new org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource     // Catch:{ all -> 0x003b }
            org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream r3 = new org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream     // Catch:{ all -> 0x003b }
            org.apache.commons.compress.archivers.zip.ZipArchiveInputStream r4 = new org.apache.commons.compress.archivers.zip.ZipArchiveInputStream     // Catch:{ all -> 0x003b }
            r4.<init>(r1)     // Catch:{ all -> 0x003b }
            r3.<init>(r4)     // Catch:{ all -> 0x003b }
            r2.<init>(r3)     // Catch:{ all -> 0x003b }
            r5.injectData(r2, r6)     // Catch:{ all -> 0x002f }
            r2.close()     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ all -> 0x0049 }
        L_0x002b:
            r0.close()
            return
        L_0x002f:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r6 = move-exception
            r2.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r2 = move-exception
            r5.addSuppressed(r2)     // Catch:{ all -> 0x003b }
        L_0x003a:
            throw r6     // Catch:{ all -> 0x003b }
        L_0x003b:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003d }
        L_0x003d:
            r6 = move-exception
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ all -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ all -> 0x0049 }
        L_0x0048:
            throw r6     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004b }
        L_0x004b:
            r6 = move-exception
            r0.close()     // Catch:{ all -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r0 = move-exception
            r5.addSuppressed(r0)
        L_0x0054:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFWorkbook.writeAvoidingTempFiles(java.io.OutputStream):void");
    }

    /* access modifiers changed from: protected */
    public void flushSheets() throws IOException {
        for (SXSSFSheet flushRows : this._xFromSxHash.values()) {
            flushRows.flushRows();
        }
    }

    public boolean dispose() {
        boolean z = true;
        for (SXSSFSheet dispose : this._sxFromXHash.keySet()) {
            boolean z2 = false;
            try {
                if (dispose.dispose() && z) {
                    z2 = true;
                }
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("Failed to dispose sheet");
            }
            z = z2;
        }
        return z;
    }

    public int getNumberOfNames() {
        return this._wb.getNumberOfNames();
    }

    public Name getName(String str) {
        return this._wb.getName(str);
    }

    public List<? extends Name> getNames(String str) {
        return this._wb.getNames(str);
    }

    public List<? extends Name> getAllNames() {
        return this._wb.getAllNames();
    }

    public Name createName() {
        return this._wb.createName();
    }

    public void removeName(Name name) {
        this._wb.removeName(name);
    }

    public void setPrintArea(int i, String str) {
        this._wb.setPrintArea(i, str);
    }

    public void setPrintArea(int i, int i2, int i3, int i4, int i5) {
        this._wb.setPrintArea(i, i2, i3, i4, i5);
    }

    public String getPrintArea(int i) {
        return this._wb.getPrintArea(i);
    }

    public void removePrintArea(int i) {
        this._wb.removePrintArea(i);
    }

    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this._wb.getMissingCellPolicy();
    }

    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this._wb.setMissingCellPolicy(missingCellPolicy);
    }

    public DataFormat createDataFormat() {
        return this._wb.createDataFormat();
    }

    public int addPicture(byte[] bArr, int i) {
        return this._wb.addPicture(bArr, i);
    }

    public List<? extends PictureData> getAllPictures() {
        return this._wb.getAllPictures();
    }

    public CreationHelper getCreationHelper() {
        return new SXSSFCreationHelper(this);
    }

    /* access modifiers changed from: protected */
    public boolean isDate1904() {
        return this._wb.isDate1904();
    }

    @NotImplemented("XSSFWorkbook#isHidden is not implemented")
    public boolean isHidden() {
        return this._wb.isHidden();
    }

    @NotImplemented("XSSFWorkbook#setHidden is not implemented")
    public void setHidden(boolean z) {
        this._wb.setHidden(z);
    }

    public boolean isSheetHidden(int i) {
        return this._wb.isSheetHidden(i);
    }

    public boolean isSheetVeryHidden(int i) {
        return this._wb.isSheetVeryHidden(i);
    }

    public SheetVisibility getSheetVisibility(int i) {
        return this._wb.getSheetVisibility(i);
    }

    public void setSheetHidden(int i, boolean z) {
        this._wb.setSheetHidden(i, z);
    }

    public void setSheetVisibility(int i, SheetVisibility sheetVisibility) {
        this._wb.setSheetVisibility(i, sheetVisibility);
    }

    @NotImplemented
    public int linkExternalWorkbook(String str, Workbook workbook) {
        throw new RuntimeException("Not Implemented");
    }

    public void addToolPack(UDFFinder uDFFinder) {
        this._wb.addToolPack(uDFFinder);
    }

    public void setForceFormulaRecalculation(boolean z) {
        this._wb.setForceFormulaRecalculation(z);
    }

    public boolean getForceFormulaRecalculation() {
        return this._wb.getForceFormulaRecalculation();
    }

    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public int addOlePackage(byte[] bArr, String str, String str2, String str3) throws IOException {
        return this._wb.addOlePackage(bArr, str, str2, str3);
    }

    public EvaluationWorkbook createEvaluationWorkbook() {
        return SXSSFEvaluationWorkbook.create(this);
    }

    public CellReferenceType getCellReferenceType() {
        return getXSSFWorkbook().getCellReferenceType();
    }

    public void setCellReferenceType(CellReferenceType cellReferenceType) {
        getXSSFWorkbook().setCellReferenceType(cellReferenceType);
    }
}
