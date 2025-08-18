package org.apache.poi.hssf.extractor;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Row;

public class ExcelExtractor implements POIOLE2TextExtractor, org.apache.poi.ss.extractor.ExcelExtractor {
    private final HSSFDataFormatter _formatter;
    private boolean _includeBlankCells;
    private boolean _includeCellComments;
    private boolean _includeHeadersFooters;
    private boolean _includeSheetNames;
    private boolean _shouldEvaluateFormulas;
    private final HSSFWorkbook _wb;
    private boolean doCloseFilesystem;

    public ExcelExtractor(HSSFWorkbook hSSFWorkbook) {
        this.doCloseFilesystem = true;
        this._includeSheetNames = true;
        this._shouldEvaluateFormulas = true;
        this._includeHeadersFooters = true;
        this._wb = hSSFWorkbook;
        this._formatter = new HSSFDataFormatter();
    }

    public ExcelExtractor(POIFSFileSystem pOIFSFileSystem) throws IOException {
        this(pOIFSFileSystem.getRoot());
    }

    public ExcelExtractor(DirectoryNode directoryNode) throws IOException {
        this(new HSSFWorkbook(directoryNode, true));
    }

    private static final class CommandParseException extends Exception {
        public CommandParseException(String str) {
            super(str);
        }
    }

    private static final class CommandArgs {
        private final boolean _evaluateFormulas;
        private final boolean _headersFooters;
        private final File _inputFile;
        private final boolean _requestHelp;
        private final boolean _showBlankCells;
        private final boolean _showCellComments;
        private final boolean _showSheetNames;

        public CommandArgs(String[] strArr) throws CommandParseException {
            int i;
            int length = strArr.length;
            File file = null;
            boolean z = false;
            int i2 = 0;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = true;
            boolean z5 = true;
            boolean z6 = true;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str = strArr[i2];
                if ("-help".equalsIgnoreCase(str)) {
                    z = true;
                    break;
                }
                if ("-i".equals(str)) {
                    i = i2 + 1;
                    if (i < length) {
                        String str2 = strArr[i];
                        if (file == null) {
                            file = new File(str2);
                            if (!file.exists()) {
                                throw new CommandParseException("Specified input file '" + str2 + "' does not exist");
                            } else if (file.isDirectory()) {
                                throw new CommandParseException("Specified input file '" + str2 + "' is a directory");
                            }
                        } else {
                            throw new CommandParseException("Only one input file can be supplied");
                        }
                    } else {
                        throw new CommandParseException("Expected filename after '-i'");
                    }
                } else if ("--show-sheet-names".equals(str)) {
                    i = i2 + 1;
                    z4 = parseBoolArg(strArr, i);
                } else if ("--evaluate-formulas".equals(str)) {
                    i = i2 + 1;
                    z5 = parseBoolArg(strArr, i);
                } else if ("--show-comments".equals(str)) {
                    i = i2 + 1;
                    z2 = parseBoolArg(strArr, i);
                } else if ("--show-blanks".equals(str)) {
                    i = i2 + 1;
                    z3 = parseBoolArg(strArr, i);
                } else if ("--headers-footers".equals(str)) {
                    i = i2 + 1;
                    z6 = parseBoolArg(strArr, i);
                } else {
                    throw new CommandParseException("Invalid argument '" + str + "'");
                }
                i2 = i + 1;
            }
            this._requestHelp = z;
            this._inputFile = file;
            this._showSheetNames = z4;
            this._evaluateFormulas = z5;
            this._showCellComments = z2;
            this._showBlankCells = z3;
            this._headersFooters = z6;
        }

        private static boolean parseBoolArg(String[] strArr, int i) throws CommandParseException {
            if (i < strArr.length) {
                String upperCase = strArr[i].toUpperCase(Locale.ROOT);
                if ("Y".equals(upperCase) || "YES".equals(upperCase) || "ON".equals(upperCase) || "TRUE".equals(upperCase)) {
                    return true;
                }
                if ("N".equals(upperCase) || "NO".equals(upperCase) || "OFF".equals(upperCase) || "FALSE".equals(upperCase)) {
                    return false;
                }
                throw new CommandParseException("Invalid value '" + strArr[i] + "' for '" + strArr[i - 1] + "'. Expected 'Y' or 'N'");
            }
            throw new CommandParseException("Expected value after '" + strArr[i - 1] + "'");
        }

        public boolean isRequestHelp() {
            return this._requestHelp;
        }

        public File getInputFile() {
            return this._inputFile;
        }

        public boolean shouldShowSheetNames() {
            return this._showSheetNames;
        }

        public boolean shouldEvaluateFormulas() {
            return this._evaluateFormulas;
        }

        public boolean shouldShowCellComments() {
            return this._showCellComments;
        }

        public boolean shouldShowBlankCells() {
            return this._showBlankCells;
        }

        public boolean shouldIncludeHeadersFooters() {
            return this._headersFooters;
        }
    }

    private static void printUsageMessage(PrintStream printStream) {
        printStream.println("Use:");
        printStream.println("    " + ExcelExtractor.class.getName() + " [<flag> <value> [<flag> <value> [...]]] [-i <filename.xls>]");
        printStream.println("       -i <filename.xls> specifies input file (default is to use stdin)");
        printStream.println("       Flags can be set on or off by using the values 'Y' or 'N'.");
        printStream.println("       Following are available flags and their default values:");
        printStream.println("       --show-sheet-names  Y");
        printStream.println("       --evaluate-formulas Y");
        printStream.println("       --show-comments     N");
        printStream.println("       --show-blanks       Y");
        printStream.println("       --headers-footers   Y");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0071, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0075, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0078, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0081, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0084, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0085, code lost:
        if (r5 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x008b, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x008c, code lost:
        r0.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x008f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r5) throws java.io.IOException {
        /*
            r0 = 1
            org.apache.poi.hssf.extractor.ExcelExtractor$CommandArgs r1 = new org.apache.poi.hssf.extractor.ExcelExtractor$CommandArgs     // Catch:{ CommandParseException -> 0x0090 }
            r1.<init>(r5)     // Catch:{ CommandParseException -> 0x0090 }
            boolean r5 = r1.isRequestHelp()
            if (r5 == 0) goto L_0x0012
            java.io.PrintStream r5 = java.lang.System.out
            printUsageMessage(r5)
            return
        L_0x0012:
            java.io.File r5 = r1.getInputFile()
            if (r5 != 0) goto L_0x001b
            java.io.InputStream r5 = java.lang.System.in
            goto L_0x0024
        L_0x001b:
            java.io.FileInputStream r5 = new java.io.FileInputStream
            java.io.File r2 = r1.getInputFile()
            r5.<init>(r2)
        L_0x0024:
            org.apache.poi.hssf.usermodel.HSSFWorkbook r2 = new org.apache.poi.hssf.usermodel.HSSFWorkbook     // Catch:{ all -> 0x0082 }
            r2.<init>((java.io.InputStream) r5)     // Catch:{ all -> 0x0082 }
            org.apache.poi.hssf.extractor.ExcelExtractor r3 = new org.apache.poi.hssf.extractor.ExcelExtractor     // Catch:{ all -> 0x0076 }
            r3.<init>((org.apache.poi.hssf.usermodel.HSSFWorkbook) r2)     // Catch:{ all -> 0x0076 }
            boolean r4 = r1.shouldShowSheetNames()     // Catch:{ all -> 0x006a }
            r3.setIncludeSheetNames(r4)     // Catch:{ all -> 0x006a }
            boolean r4 = r1.shouldEvaluateFormulas()     // Catch:{ all -> 0x006a }
            if (r4 != 0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r0 = 0
        L_0x003d:
            r3.setFormulasNotResults(r0)     // Catch:{ all -> 0x006a }
            boolean r0 = r1.shouldShowCellComments()     // Catch:{ all -> 0x006a }
            r3.setIncludeCellComments(r0)     // Catch:{ all -> 0x006a }
            boolean r0 = r1.shouldShowBlankCells()     // Catch:{ all -> 0x006a }
            r3.setIncludeBlankCells(r0)     // Catch:{ all -> 0x006a }
            boolean r0 = r1.shouldIncludeHeadersFooters()     // Catch:{ all -> 0x006a }
            r3.setIncludeHeadersFooters(r0)     // Catch:{ all -> 0x006a }
            java.io.PrintStream r0 = java.lang.System.out     // Catch:{ all -> 0x006a }
            java.lang.String r1 = r3.getText()     // Catch:{ all -> 0x006a }
            r0.println(r1)     // Catch:{ all -> 0x006a }
            r3.close()     // Catch:{ all -> 0x0076 }
            r2.close()     // Catch:{ all -> 0x0082 }
            if (r5 == 0) goto L_0x0069
            r5.close()
        L_0x0069:
            return
        L_0x006a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x006c }
        L_0x006c:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch:{ all -> 0x0076 }
        L_0x0075:
            throw r1     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r1 = move-exception
            r2.close()     // Catch:{ all -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ all -> 0x0082 }
        L_0x0081:
            throw r1     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r1 = move-exception
            if (r5 == 0) goto L_0x008f
            r5.close()     // Catch:{ all -> 0x008b }
            goto L_0x008f
        L_0x008b:
            r5 = move-exception
            r0.addSuppressed(r5)
        L_0x008f:
            throw r1
        L_0x0090:
            r5 = move-exception
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r5 = r5.getMessage()
            r1.println(r5)
            java.io.PrintStream r5 = java.lang.System.err
            printUsageMessage(r5)
            java.lang.System.exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.extractor.ExcelExtractor.main(java.lang.String[]):void");
    }

    public void setIncludeSheetNames(boolean z) {
        this._includeSheetNames = z;
    }

    public void setFormulasNotResults(boolean z) {
        this._shouldEvaluateFormulas = !z;
    }

    public void setIncludeCellComments(boolean z) {
        this._includeCellComments = z;
    }

    public void setIncludeBlankCells(boolean z) {
        this._includeBlankCells = z;
    }

    public void setIncludeHeadersFooters(boolean z) {
        this._includeHeadersFooters = z;
    }

    public String getText() {
        boolean z;
        String sheetName;
        StringBuilder sb = new StringBuilder();
        this._wb.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        for (int i = 0; i < this._wb.getNumberOfSheets(); i++) {
            HSSFSheet sheetAt = this._wb.getSheetAt(i);
            if (sheetAt != null) {
                if (this._includeSheetNames && (sheetName = this._wb.getSheetName(i)) != null) {
                    sb.append(sheetName);
                    sb.append("\n");
                }
                if (this._includeHeadersFooters) {
                    sb.append(_extractHeaderFooter(sheetAt.getHeader()));
                }
                int lastRowNum = sheetAt.getLastRowNum();
                for (int firstRowNum = sheetAt.getFirstRowNum(); firstRowNum <= lastRowNum; firstRowNum++) {
                    HSSFRow row = sheetAt.getRow(firstRowNum);
                    if (row != null) {
                        int firstCellNum = row.getFirstCellNum();
                        short lastCellNum = row.getLastCellNum();
                        if (this._includeBlankCells) {
                            firstCellNum = 0;
                        }
                        while (firstCellNum < lastCellNum) {
                            HSSFCell cell = row.getCell(firstCellNum);
                            if (cell == null) {
                                z = this._includeBlankCells;
                            } else {
                                int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cell.getCellType().ordinal()];
                                if (i2 == 1) {
                                    sb.append(cell.getRichStringCellValue().getString());
                                } else if (i2 == 2) {
                                    sb.append(this._formatter.formatCellValue(cell));
                                } else if (i2 == 3) {
                                    sb.append(cell.getBooleanCellValue());
                                } else if (i2 == 4) {
                                    sb.append(ErrorEval.getText(cell.getErrorCellValue()));
                                } else if (i2 != 5) {
                                    throw new RuntimeException("Unexpected cell type (" + cell.getCellType() + ")");
                                } else if (!this._shouldEvaluateFormulas) {
                                    sb.append(cell.getCellFormula());
                                } else {
                                    int i3 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cell.getCachedFormulaResultType().ordinal()];
                                    if (i3 == 1) {
                                        HSSFRichTextString richStringCellValue = cell.getRichStringCellValue();
                                        if (richStringCellValue != null && richStringCellValue.length() > 0) {
                                            sb.append(richStringCellValue);
                                        }
                                    } else if (i3 == 2) {
                                        HSSFCellStyle cellStyle = cell.getCellStyle();
                                        sb.append(this._formatter.formatRawCellContents(cell.getNumericCellValue(), cellStyle.getDataFormat(), cellStyle.getDataFormatString()));
                                    } else if (i3 == 3) {
                                        sb.append(cell.getBooleanCellValue());
                                    } else if (i3 == 4) {
                                        sb.append(ErrorEval.getText(cell.getErrorCellValue()));
                                    } else {
                                        throw new IllegalStateException("Unexpected cell cached formula result type: " + cell.getCachedFormulaResultType());
                                    }
                                }
                                HSSFComment cellComment = cell.getCellComment();
                                if (this._includeCellComments && cellComment != null) {
                                    sb.append(" Comment by ").append(cellComment.getAuthor()).append(": ").append(cellComment.getString().getString().replace(10, Chars.SPACE));
                                }
                                z = true;
                            }
                            if (z && firstCellNum < lastCellNum - 1) {
                                sb.append("\t");
                            }
                            firstCellNum++;
                        }
                        sb.append("\n");
                    }
                }
                if (this._includeHeadersFooters) {
                    sb.append(_extractHeaderFooter(sheetAt.getFooter()));
                }
            }
        }
        return sb.toString();
    }

    /* renamed from: org.apache.poi.hssf.extractor.ExcelExtractor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.extractor.ExcelExtractor.AnonymousClass1.<clinit>():void");
        }
    }

    public static String _extractHeaderFooter(HeaderFooter headerFooter) {
        StringBuilder sb = new StringBuilder();
        if (headerFooter.getLeft() != null) {
            sb.append(headerFooter.getLeft());
        }
        if (headerFooter.getCenter() != null) {
            if (sb.length() > 0) {
                sb.append("\t");
            }
            sb.append(headerFooter.getCenter());
        }
        if (headerFooter.getRight() != null) {
            if (sb.length() > 0) {
                sb.append("\t");
            }
            sb.append(headerFooter.getRight());
        }
        if (sb.length() > 0) {
            sb.append("\n");
        }
        return sb.toString();
    }

    public HSSFWorkbook getDocument() {
        return this._wb;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public HSSFWorkbook getFilesystem() {
        return this._wb;
    }
}
