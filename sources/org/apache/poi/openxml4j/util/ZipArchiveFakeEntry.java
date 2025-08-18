package org.apache.poi.openxml4j.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;

class ZipArchiveFakeEntry extends ZipArchiveEntry implements Closeable {
    private static final int DEFAULT_MAX_ENTRY_SIZE = 100000000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) ZipArchiveFakeEntry.class);
    private static int MAX_ENTRY_SIZE = DEFAULT_MAX_ENTRY_SIZE;
    private byte[] data;
    private EncryptedTempData encryptedTempData;
    private File tempFile;

    public static void setMaxEntrySize(int i) {
        MAX_ENTRY_SIZE = i;
    }

    public static int getMaxEntrySize() {
        return MAX_ENTRY_SIZE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r6 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        r7.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ZipArchiveFakeEntry(org.apache.commons.compress.archivers.zip.ZipArchiveEntry r7, java.io.InputStream r8) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = r7.getName()
            r6.<init>((java.lang.String) r0)
            long r0 = r7.getSize()
            int r2 = org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource.getThresholdBytesForTempFiles()
            if (r2 < 0) goto L_0x0077
            long r2 = (long) r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0077
            boolean r2 = org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource.shouldEncryptTempFiles()
            if (r2 == 0) goto L_0x003e
            org.apache.poi.poifs.crypt.temp.EncryptedTempData r7 = new org.apache.poi.poifs.crypt.temp.EncryptedTempData
            r7.<init>()
            r6.encryptedTempData = r7
            java.io.OutputStream r6 = r7.getOutputStream()
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r8, (java.io.OutputStream) r6)     // Catch:{ all -> 0x0030 }
            if (r6 == 0) goto L_0x009a
            r6.close()
            goto L_0x009a
        L_0x0030:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r8 = move-exception
            if (r6 == 0) goto L_0x003d
            r6.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r6 = move-exception
            r7.addSuppressed(r6)
        L_0x003d:
            throw r8
        L_0x003e:
            java.lang.String r2 = "poi-zip-entry"
            java.lang.String r3 = ".tmp"
            java.io.File r2 = org.apache.poi.util.TempFile.createTempFile(r2, r3)
            r6.tempFile = r2
            org.apache.logging.log4j.Logger r2 = LOG
            org.apache.logging.log4j.LogBuilder r2 = r2.atInfo()
            r3 = 3
            org.apache.logging.log4j.util.Supplier[] r3 = new org.apache.logging.log4j.util.Supplier[r3]
            org.apache.poi.openxml4j.util.ZipArchiveFakeEntry$$ExternalSyntheticLambda0 r4 = new org.apache.poi.openxml4j.util.ZipArchiveFakeEntry$$ExternalSyntheticLambda0
            r4.<init>(r6)
            r5 = 0
            r3[r5] = r4
            r7.getClass()
            org.apache.poi.openxml4j.util.ZipArchiveFakeEntry$$ExternalSyntheticLambda1 r4 = new org.apache.poi.openxml4j.util.ZipArchiveFakeEntry$$ExternalSyntheticLambda1
            r4.<init>(r7)
            r7 = 1
            r3[r7] = r4
            org.apache.poi.openxml4j.util.ZipArchiveFakeEntry$$ExternalSyntheticLambda2 r7 = new org.apache.poi.openxml4j.util.ZipArchiveFakeEntry$$ExternalSyntheticLambda2
            r7.<init>(r0)
            r0 = 2
            r3[r0] = r7
            java.lang.String r7 = "created for temp file {} for zip entry {} of size {} bytes"
            r2.log((java.lang.String) r7, (org.apache.logging.log4j.util.Supplier<?>[]) r3)
            java.io.File r6 = r6.tempFile
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r8, (java.io.File) r6)
            goto L_0x009a
        L_0x0077:
            r2 = -1
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 < 0) goto L_0x009b
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x009b
            if (r7 != 0) goto L_0x008f
            int r7 = getMaxEntrySize()
            byte[] r7 = org.apache.poi.util.IOUtils.toByteArrayWithMaxLength(r8, r7)
            goto L_0x0098
        L_0x008f:
            int r7 = (int) r0
            int r0 = getMaxEntrySize()
            byte[] r7 = org.apache.poi.util.IOUtils.toByteArray(r8, r7, r0)
        L_0x0098:
            r6.data = r7
        L_0x009a:
            return
        L_0x009b:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r7 = "ZIP entry size is too large or invalid"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.util.ZipArchiveFakeEntry.<init>(org.apache.commons.compress.archivers.zip.ZipArchiveEntry, java.io.InputStream):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$org-apache-poi-openxml4j-util-ZipArchiveFakeEntry  reason: not valid java name */
    public /* synthetic */ Object m2215lambda$new$0$orgapachepoiopenxml4jutilZipArchiveFakeEntry() {
        return this.tempFile.getAbsolutePath();
    }

    public InputStream getInputStream() throws IOException {
        EncryptedTempData encryptedTempData2 = this.encryptedTempData;
        if (encryptedTempData2 != null) {
            try {
                return encryptedTempData2.getInputStream();
            } catch (IOException e) {
                throw new IOException("failed to read from encrypted temp data", e);
            }
        } else if (this.tempFile != null) {
            try {
                return new FileInputStream(this.tempFile);
            } catch (FileNotFoundException unused) {
                throw new IOException("temp file " + this.tempFile.getAbsolutePath() + " is missing");
            }
        } else if (this.data != null) {
            return new UnsynchronizedByteArrayInputStream(this.data);
        } else {
            throw new IOException("Cannot retrieve data from Zip Entry, probably because the Zip Entry was closed before the data was requested.");
        }
    }

    public void close() throws IOException {
        this.data = null;
        EncryptedTempData encryptedTempData2 = this.encryptedTempData;
        if (encryptedTempData2 != null) {
            encryptedTempData2.dispose();
        }
        File file = this.tempFile;
        if (file != null && file.exists() && !this.tempFile.delete()) {
            LOG.atDebug().log("temp file was already deleted (probably due to previous call to close this resource)");
        }
    }
}
