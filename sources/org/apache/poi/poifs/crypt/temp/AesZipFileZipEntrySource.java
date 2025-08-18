package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.TempFile;

public final class AesZipFileZipEntrySource implements ZipEntrySource {
    private static final Logger LOG = LogManager.getLogger((Class<?>) AesZipFileZipEntrySource.class);
    private static final String PADDING = "PKCS5Padding";
    private final Cipher ci;
    private boolean closed = false;
    private final File tmpFile;
    private final ZipFile zipFile;

    private AesZipFileZipEntrySource(File file, Cipher cipher) throws IOException {
        this.tmpFile = file;
        this.zipFile = new ZipFile(file);
        this.ci = cipher;
    }

    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        return this.zipFile.getEntries();
    }

    public ZipArchiveEntry getEntry(String str) {
        return this.zipFile.getEntry(str);
    }

    public InputStream getInputStream(ZipArchiveEntry zipArchiveEntry) throws IOException {
        return new CipherInputStream(this.zipFile.getInputStream(zipArchiveEntry), this.ci);
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.zipFile.close();
            if (!this.tmpFile.delete()) {
                LOG.atWarn().log("{} can't be removed (or was already removed).", (Object) this.tmpFile.getAbsolutePath());
            }
        }
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public static AesZipFileZipEntrySource createZipEntrySource(InputStream inputStream) throws IOException {
        File createTempFile;
        try {
            byte[] bArr = new byte[16];
            byte[] bArr2 = new byte[16];
            RandomSingleton.getInstance().nextBytes(bArr);
            RandomSingleton.getInstance().nextBytes(bArr2);
            createTempFile = TempFile.createTempFile("protectedXlsx", ".zip");
            copyToFile(inputStream, createTempFile, bArr2, bArr);
            AesZipFileZipEntrySource fileToSource = fileToSource(createTempFile, bArr2, bArr);
            IOUtils.closeQuietly(inputStream);
            return fileToSource;
        } catch (IOException | RuntimeException e) {
            if (!createTempFile.delete()) {
                LOG.atInfo().log("Temp file was not deleted, may already have been deleted by another method.");
            }
            throw e;
        } catch (Throwable th) {
            IOUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r8.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0069, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r8.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0082, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0087, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r7.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x008b, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x008e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0093, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0094, code lost:
        r6.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0097, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void copyToFile(java.io.InputStream r6, java.io.File r7, byte[] r8, byte[] r9) throws java.io.IOException {
        /*
            javax.crypto.spec.SecretKeySpec r0 = new javax.crypto.spec.SecretKeySpec
            org.apache.poi.poifs.crypt.CipherAlgorithm r1 = org.apache.poi.poifs.crypt.CipherAlgorithm.aes128
            java.lang.String r1 = r1.jceId
            r0.<init>(r8, r1)
            org.apache.poi.poifs.crypt.CipherAlgorithm r1 = org.apache.poi.poifs.crypt.CipherAlgorithm.aes128
            org.apache.poi.poifs.crypt.ChainingMode r2 = org.apache.poi.poifs.crypt.ChainingMode.cbc
            r4 = 1
            java.lang.String r5 = "PKCS5Padding"
            r3 = r9
            javax.crypto.Cipher r8 = org.apache.poi.poifs.crypt.CryptoFunctions.getCipher(r0, r1, r2, r3, r4, r5)
            org.apache.commons.compress.archivers.zip.ZipArchiveInputStream r9 = new org.apache.commons.compress.archivers.zip.ZipArchiveInputStream
            r9.<init>(r6)
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x008c }
            r6.<init>(r7)     // Catch:{ all -> 0x008c }
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r7 = new org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream     // Catch:{ all -> 0x0080 }
            r7.<init>((java.io.OutputStream) r6)     // Catch:{ all -> 0x0080 }
        L_0x0024:
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r0 = r9.getNextZipEntry()     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x006a
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r1 = new org.apache.commons.compress.archivers.zip.ZipArchiveEntry     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r0.getName()     // Catch:{ all -> 0x0074 }
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r0.getComment()     // Catch:{ all -> 0x0074 }
            r1.setComment(r2)     // Catch:{ all -> 0x0074 }
            byte[] r2 = r0.getExtra()     // Catch:{ all -> 0x0074 }
            r1.setExtra(r2)     // Catch:{ all -> 0x0074 }
            long r2 = r0.getTime()     // Catch:{ all -> 0x0074 }
            r1.setTime(r2)     // Catch:{ all -> 0x0074 }
            r7.putArchiveEntry(r1)     // Catch:{ all -> 0x0074 }
            javax.crypto.CipherOutputStream r0 = new javax.crypto.CipherOutputStream     // Catch:{ all -> 0x0074 }
            org.apache.commons.io.output.CloseShieldOutputStream r1 = org.apache.commons.io.output.CloseShieldOutputStream.wrap(r7)     // Catch:{ all -> 0x0074 }
            r0.<init>(r1, r8)     // Catch:{ all -> 0x0074 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r9, (java.io.OutputStream) r0)     // Catch:{ all -> 0x005e }
            r0.close()     // Catch:{ all -> 0x0074 }
            r7.closeArchiveEntry()     // Catch:{ all -> 0x0074 }
            goto L_0x0024
        L_0x005e:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r0 = move-exception
            r8.addSuppressed(r0)     // Catch:{ all -> 0x0074 }
        L_0x0069:
            throw r1     // Catch:{ all -> 0x0074 }
        L_0x006a:
            r7.close()     // Catch:{ all -> 0x0080 }
            r6.close()     // Catch:{ all -> 0x008c }
            r9.close()
            return
        L_0x0074:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r0 = move-exception
            r7.close()     // Catch:{ all -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch:{ all -> 0x0080 }
        L_0x007f:
            throw r0     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r8 = move-exception
            r6.close()     // Catch:{ all -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch:{ all -> 0x008c }
        L_0x008b:
            throw r8     // Catch:{ all -> 0x008c }
        L_0x008c:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x008e }
        L_0x008e:
            r7 = move-exception
            r9.close()     // Catch:{ all -> 0x0093 }
            goto L_0x0097
        L_0x0093:
            r8 = move-exception
            r6.addSuppressed(r8)
        L_0x0097:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.temp.AesZipFileZipEntrySource.copyToFile(java.io.InputStream, java.io.File, byte[], byte[]):void");
    }

    private static AesZipFileZipEntrySource fileToSource(File file, byte[] bArr, byte[] bArr2) throws IOException {
        return new AesZipFileZipEntrySource(file, CryptoFunctions.getCipher(new SecretKeySpec(bArr, CipherAlgorithm.aes128.jceId), CipherAlgorithm.aes128, ChainingMode.cbc, bArr2, 2, PADDING));
    }
}
