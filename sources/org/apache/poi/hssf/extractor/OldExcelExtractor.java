package org.apache.poi.hssf.extractor;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.OldFormulaRecord;
import org.apache.poi.hssf.record.OldLabelRecord;
import org.apache.poi.hssf.record.OldSheetRecord;
import org.apache.poi.hssf.record.OldStringRecord;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.IOUtils;

public class OldExcelExtractor implements POITextExtractor {
    private static final int FILE_PASS_RECORD_SID = 47;
    private int biffVersion;
    private int fileType;
    /* access modifiers changed from: private */
    public RecordInputStream ris;
    /* access modifiers changed from: private */
    public Closeable toClose;

    public void setCloseFilesystem(boolean z) {
    }

    public OldExcelExtractor(InputStream inputStream) throws IOException {
        open(inputStream);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OldExcelExtractor(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            r2.<init>()
            r0 = 0
            org.apache.poi.poifs.filesystem.POIFSFileSystem r1 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ OldExcelFormatException | NotOLE2FileException -> 0x001d, all -> 0x0014 }
            r1.<init>((java.io.File) r3)     // Catch:{ OldExcelFormatException | NotOLE2FileException -> 0x001d, all -> 0x0014 }
            r2.open((org.apache.poi.poifs.filesystem.POIFSFileSystem) r1)     // Catch:{ OldExcelFormatException | NotOLE2FileException -> 0x0012, all -> 0x000f }
            r2.toClose = r1     // Catch:{ OldExcelFormatException | NotOLE2FileException -> 0x0012, all -> 0x000f }
            return
        L_0x000f:
            r3 = move-exception
            r0 = r1
            goto L_0x0015
        L_0x0012:
            r0 = r1
            goto L_0x001d
        L_0x0014:
            r3 = move-exception
        L_0x0015:
            java.io.Closeable r2 = r2.toClose
            if (r2 != 0) goto L_0x001c
            org.apache.poi.util.IOUtils.closeQuietly(r0)
        L_0x001c:
            throw r3
        L_0x001d:
            java.io.Closeable r1 = r2.toClose
            if (r1 != 0) goto L_0x0024
            org.apache.poi.util.IOUtils.closeQuietly(r0)
        L_0x0024:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            r2.open((java.io.InputStream) r0)     // Catch:{ IOException | RuntimeException -> 0x002d }
            return
        L_0x002d:
            r3 = move-exception
            r0.close()
            java.io.Closeable r2 = r2.toClose
            r2.close()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.extractor.OldExcelExtractor.<init>(java.io.File):void");
    }

    public OldExcelExtractor(POIFSFileSystem pOIFSFileSystem) throws IOException {
        this.toClose = pOIFSFileSystem;
        open(pOIFSFileSystem);
    }

    public OldExcelExtractor(DirectoryNode directoryNode) throws IOException {
        this.toClose = directoryNode.getFileSystem();
        open(directoryNode);
    }

    private void open(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, 8);
        if (FileMagic.valueOf((InputStream) bufferedInputStream) == FileMagic.OLE2) {
            POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem((InputStream) bufferedInputStream);
            try {
                open(pOIFSFileSystem);
                this.toClose = pOIFSFileSystem;
            } catch (Throwable th) {
                if (this.toClose == null) {
                    pOIFSFileSystem.close();
                }
                throw th;
            }
        } else {
            this.ris = new RecordInputStream(bufferedInputStream);
            this.toClose = bufferedInputStream;
            prepare();
        }
    }

    private void open(POIFSFileSystem pOIFSFileSystem) throws IOException {
        open(pOIFSFileSystem.getRoot());
    }

    private void open(DirectoryNode directoryNode) throws IOException {
        DocumentNode documentNode;
        try {
            documentNode = (DocumentNode) directoryNode.getEntry(InternalWorkbook.OLD_WORKBOOK_DIR_ENTRY_NAME);
        } catch (FileNotFoundException | IllegalArgumentException unused) {
            documentNode = (DocumentNode) directoryNode.getEntry(InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES.get(0));
        }
        if (documentNode != null) {
            this.ris = new RecordInputStream(directoryNode.createDocumentInputStream((Entry) documentNode));
            prepare();
            return;
        }
        throw new IOException("No Excel 5/95 Book stream found");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r3) throws java.io.IOException {
        /*
            int r0 = r3.length
            r1 = 1
            if (r0 >= r1) goto L_0x0015
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r2 = "Use:"
            r0.println(r2)
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r2 = "   OldExcelExtractor <filename>"
            r0.println(r2)
            java.lang.System.exit(r1)
        L_0x0015:
            org.apache.poi.hssf.extractor.OldExcelExtractor r0 = new org.apache.poi.hssf.extractor.OldExcelExtractor
            java.io.File r1 = new java.io.File
            r2 = 0
            r3 = r3[r2]
            r1.<init>(r3)
            r0.<init>((java.io.File) r1)
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ all -> 0x002f }
            java.lang.String r1 = r0.getText()     // Catch:{ all -> 0x002f }
            r3.println(r1)     // Catch:{ all -> 0x002f }
            r0.close()
            return
        L_0x002f:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x003a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.extractor.OldExcelExtractor.main(java.lang.String[]):void");
    }

    private void prepare() {
        if (this.ris.hasNextRecord()) {
            this.ris.nextRecord();
            short sid = this.ris.getSid();
            if (sid == 9) {
                this.biffVersion = 2;
            } else if (sid == 521) {
                this.biffVersion = 3;
            } else if (sid == 1033) {
                this.biffVersion = 4;
            } else if (sid == 2057) {
                this.biffVersion = 5;
            } else {
                throw new IllegalArgumentException("File does not begin with a BOF, found sid of " + sid);
            }
            this.fileType = new BOFRecord(this.ris).getType();
            return;
        }
        throw new IllegalArgumentException("File contains no records!");
    }

    public int getBiffVersion() {
        return this.biffVersion;
    }

    public int getFileType() {
        return this.fileType;
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        CodepageRecord codepageRecord = null;
        while (this.ris.hasNextRecord()) {
            int nextSid = this.ris.getNextSid();
            this.ris.nextRecord();
            if (nextSid != 4) {
                if (nextSid == 47) {
                    throw new EncryptedDocumentException("Encryption not supported for Old Excel files");
                } else if (nextSid == 66) {
                    codepageRecord = new CodepageRecord(this.ris);
                } else if (nextSid == 133) {
                    OldSheetRecord oldSheetRecord = new OldSheetRecord(this.ris);
                    oldSheetRecord.setCodePage(codepageRecord);
                    sb.append("Sheet: ");
                    sb.append(oldSheetRecord.getSheetname());
                    sb.append(10);
                } else if (nextSid != 638) {
                    if (!(nextSid == 1030 || nextSid == 6)) {
                        if (nextSid != 7) {
                            if (nextSid == 515) {
                                handleNumericCell(sb, new NumberRecord(this.ris).getValue());
                            } else if (nextSid != 516) {
                                if (nextSid != 518) {
                                    if (nextSid != 519) {
                                        RecordInputStream recordInputStream = this.ris;
                                        recordInputStream.readFully(IOUtils.safelyAllocate((long) recordInputStream.remaining(), HSSFWorkbook.getMaxRecordLength()));
                                    }
                                }
                            }
                        }
                        OldStringRecord oldStringRecord = new OldStringRecord(this.ris);
                        oldStringRecord.setCodePage(codepageRecord);
                        sb.append(oldStringRecord.getString());
                        sb.append(10);
                    }
                    if (this.biffVersion == 5) {
                        FormulaRecord formulaRecord = new FormulaRecord(this.ris);
                        if (formulaRecord.getCachedResultTypeEnum() == CellType.NUMERIC) {
                            handleNumericCell(sb, formulaRecord.getValue());
                        }
                    } else {
                        OldFormulaRecord oldFormulaRecord = new OldFormulaRecord(this.ris);
                        if (oldFormulaRecord.getCachedResultTypeEnum() == CellType.NUMERIC) {
                            handleNumericCell(sb, oldFormulaRecord.getValue());
                        }
                    }
                } else {
                    handleNumericCell(sb, new RKRecord(this.ris).getRKNumber());
                }
            }
            OldLabelRecord oldLabelRecord = new OldLabelRecord(this.ris);
            oldLabelRecord.setCodePage(codepageRecord);
            sb.append(oldLabelRecord.getValue());
            sb.append(10);
        }
        this.ris = null;
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void handleNumericCell(StringBuilder sb, double d) {
        sb.append(d);
        sb.append(10);
    }

    public POITextExtractor getMetadataTextExtractor() {
        return new POITextExtractor() {
            public String getText() {
                return "";
            }

            public void setCloseFilesystem(boolean z) {
            }

            public POITextExtractor getMetadataTextExtractor() {
                throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
            }

            public boolean isCloseFilesystem() {
                return OldExcelExtractor.this.toClose != null;
            }

            public Closeable getFilesystem() {
                return OldExcelExtractor.this.toClose;
            }

            public Object getDocument() {
                return OldExcelExtractor.this.ris;
            }
        };
    }

    public boolean isCloseFilesystem() {
        return this.toClose != null;
    }

    public Closeable getFilesystem() {
        return this.toClose;
    }

    public Object getDocument() {
        return this.ris;
    }
}
