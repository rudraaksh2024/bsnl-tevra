package org.apache.poi.sl.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ObjectData {
    String getFileName();

    InputStream getInputStream() throws IOException;

    String getOLE2ClassName();

    OutputStream getOutputStream() throws IOException;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r2 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    byte[] getBytes() throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStream r2 = r2.getInputStream()
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArray(r2)     // Catch:{ all -> 0x000e }
            if (r2 == 0) goto L_0x000d
            r2.close()
        L_0x000d:
            return r0
        L_0x000e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r1 = move-exception
            if (r2 == 0) goto L_0x001b
            r2.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x001b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.usermodel.ObjectData.getBytes():byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001d, code lost:
        if (r3 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0027, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean hasDirectoryEntry() {
        /*
            r3 = this;
            r0 = 0
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ IOException -> 0x0028 }
            java.io.InputStream r3 = org.apache.poi.poifs.filesystem.FileMagic.prepareToCheckMagic(r3)     // Catch:{ IOException -> 0x0028 }
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.io.InputStream) r3)     // Catch:{ all -> 0x001a }
            org.apache.poi.poifs.filesystem.FileMagic r2 = org.apache.poi.poifs.filesystem.FileMagic.OLE2     // Catch:{ all -> 0x001a }
            if (r1 != r2) goto L_0x0013
            r1 = 1
            goto L_0x0014
        L_0x0013:
            r1 = r0
        L_0x0014:
            if (r3 == 0) goto L_0x0019
            r3.close()     // Catch:{ IOException -> 0x0028 }
        L_0x0019:
            return r1
        L_0x001a:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001c }
        L_0x001c:
            r2 = move-exception
            if (r3 == 0) goto L_0x0027
            r3.close()     // Catch:{ all -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r3 = move-exception
            r1.addSuppressed(r3)     // Catch:{ IOException -> 0x0028 }
        L_0x0027:
            throw r2     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            r3 = move-exception
            java.lang.Class<org.apache.poi.sl.usermodel.ObjectData> r1 = org.apache.poi.sl.usermodel.ObjectData.class
            org.apache.logging.log4j.Logger r1 = org.apache.logging.log4j.LogManager.getLogger((java.lang.Class<?>) r1)
            org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()
            org.apache.logging.log4j.LogBuilder r3 = r1.withThrowable(r3)
            java.lang.String r1 = "Can't determine filemagic of ole stream"
            r3.log((java.lang.String) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.usermodel.ObjectData.hasDirectoryEntry():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r2 != null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    org.apache.poi.poifs.filesystem.DirectoryEntry getDirectory() throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStream r2 = r2.getInputStream()
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0013 }
            r0.<init>((java.io.InputStream) r2)     // Catch:{ all -> 0x0013 }
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = r0.getRoot()     // Catch:{ all -> 0x0013 }
            if (r2 == 0) goto L_0x0012
            r2.close()
        L_0x0012:
            return r0
        L_0x0013:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r1 = move-exception
            if (r2 == 0) goto L_0x0020
            r2.close()     // Catch:{ all -> 0x001c }
            goto L_0x0020
        L_0x001c:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x0020:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.usermodel.ObjectData.getDirectory():org.apache.poi.poifs.filesystem.DirectoryEntry");
    }
}
