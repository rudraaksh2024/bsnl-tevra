package org.apache.poi.poifs.crypt.temp;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SheetDataWriter;

public class SXSSFWorkbookWithCustomZipEntrySource extends SXSSFWorkbook {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SXSSFWorkbookWithCustomZipEntrySource.class);

    public SXSSFWorkbookWithCustomZipEntrySource() {
        super(20);
        setCompressTempFiles(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        if (r2 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003d, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0040, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0041, code lost:
        if (r2 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004b, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.io.OutputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            r4.flushSheets()
            org.apache.poi.poifs.crypt.temp.EncryptedTempData r0 = new org.apache.poi.poifs.crypt.temp.EncryptedTempData
            r0.<init>()
            r1 = 0
            java.io.OutputStream r2 = r0.getOutputStream()     // Catch:{ all -> 0x004c }
            org.apache.poi.xssf.usermodel.XSSFWorkbook r3 = r4.getXSSFWorkbook()     // Catch:{ all -> 0x003e }
            r3.write(r2)     // Catch:{ all -> 0x003e }
            if (r2 == 0) goto L_0x0019
            r2.close()     // Catch:{ all -> 0x004c }
        L_0x0019:
            java.io.InputStream r2 = r0.getInputStream()     // Catch:{ all -> 0x004c }
            org.apache.poi.poifs.crypt.temp.AesZipFileZipEntrySource r1 = org.apache.poi.poifs.crypt.temp.AesZipFileZipEntrySource.createZipEntrySource(r2)     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0026
            r2.close()     // Catch:{ all -> 0x004c }
        L_0x0026:
            r4.injectData(r1, r5)     // Catch:{ all -> 0x004c }
            r0.dispose()
            org.apache.poi.util.IOUtils.closeQuietly(r1)
            return
        L_0x0030:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r5 = move-exception
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r2 = move-exception
            r4.addSuppressed(r2)     // Catch:{ all -> 0x004c }
        L_0x003d:
            throw r5     // Catch:{ all -> 0x004c }
        L_0x003e:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r5 = move-exception
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r2 = move-exception
            r4.addSuppressed(r2)     // Catch:{ all -> 0x004c }
        L_0x004b:
            throw r5     // Catch:{ all -> 0x004c }
        L_0x004c:
            r4 = move-exception
            r0.dispose()
            org.apache.poi.util.IOUtils.closeQuietly(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.temp.SXSSFWorkbookWithCustomZipEntrySource.write(java.io.OutputStream):void");
    }

    /* access modifiers changed from: protected */
    public SheetDataWriter createSheetDataWriter() throws IOException {
        Logger logger = LOG;
        logger.atInfo().log("isCompressTempFiles: {}", (Object) Unbox.box(isCompressTempFiles()));
        logger.atInfo().log("SharedStringSource: {}", (Object) getSharedStringSource());
        return new SheetDataWriterWithDecorator();
    }
}
