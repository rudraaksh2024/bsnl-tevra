package org.apache.poi.openxml4j.opc.internal;

import java.io.File;

public final class FileHelper {
    public static File getDirectory(File file) {
        if (file == null) {
            return null;
        }
        String path = file.getPath();
        int length = path.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
        } while (path.charAt(length) != File.separatorChar);
        return new File(path.substring(0, length));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0030, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0031, code lost:
        if (r7 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0037, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x003e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x003f, code lost:
        if (r9 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0045, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r1.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0049, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x004c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0051, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r9.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0055, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0058, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x005d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x005e, code lost:
        r8.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0061, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(java.io.File r8, java.io.File r9) throws java.io.IOException {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r8)
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x0056 }
            r8.<init>(r9)     // Catch:{ all -> 0x0056 }
            java.nio.channels.FileChannel r9 = r0.getChannel()     // Catch:{ all -> 0x004a }
            java.nio.channels.FileChannel r7 = r8.getChannel()     // Catch:{ all -> 0x003c }
            r2 = 0
            long r4 = r9.size()     // Catch:{ all -> 0x002e }
            r1 = r9
            r6 = r7
            r1.transferTo(r2, r4, r6)     // Catch:{ all -> 0x002e }
            if (r7 == 0) goto L_0x0022
            r7.close()     // Catch:{ all -> 0x003c }
        L_0x0022:
            if (r9 == 0) goto L_0x0027
            r9.close()     // Catch:{ all -> 0x004a }
        L_0x0027:
            r8.close()     // Catch:{ all -> 0x0056 }
            r0.close()
            return
        L_0x002e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r2 = move-exception
            if (r7 == 0) goto L_0x003b
            r7.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r3 = move-exception
            r1.addSuppressed(r3)     // Catch:{ all -> 0x003c }
        L_0x003b:
            throw r2     // Catch:{ all -> 0x003c }
        L_0x003c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x003e }
        L_0x003e:
            r2 = move-exception
            if (r9 == 0) goto L_0x0049
            r9.close()     // Catch:{ all -> 0x0045 }
            goto L_0x0049
        L_0x0045:
            r9 = move-exception
            r1.addSuppressed(r9)     // Catch:{ all -> 0x004a }
        L_0x0049:
            throw r2     // Catch:{ all -> 0x004a }
        L_0x004a:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x004c }
        L_0x004c:
            r1 = move-exception
            r8.close()     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r8 = move-exception
            r9.addSuppressed(r8)     // Catch:{ all -> 0x0056 }
        L_0x0055:
            throw r1     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r9 = move-exception
            r0.close()     // Catch:{ all -> 0x005d }
            goto L_0x0061
        L_0x005d:
            r0 = move-exception
            r8.addSuppressed(r0)
        L_0x0061:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.FileHelper.copyFile(java.io.File, java.io.File):void");
    }

    public static String getFilename(File file) {
        if (file == null) {
            return "";
        }
        String path = file.getPath();
        int length = path.length();
        int i = length;
        do {
            i--;
            if (i < 0) {
                return "";
            }
        } while (path.charAt(i) != File.separatorChar);
        return path.substring(i + 1, length);
    }
}
