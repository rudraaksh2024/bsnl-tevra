package org.apache.xmlbeans.impl.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class IOUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|13|14|15|18) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyCompletely(java.io.InputStream r8, java.io.OutputStream r9) throws java.io.IOException {
        /*
            boolean r0 = r9 instanceof java.io.FileOutputStream
            if (r0 == 0) goto L_0x0027
            boolean r0 = r8 instanceof java.io.FileInputStream
            if (r0 == 0) goto L_0x0027
            r0 = r9
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ Exception -> 0x0027 }
            java.nio.channels.FileChannel r0 = r0.getChannel()     // Catch:{ Exception -> 0x0027 }
            r1 = r8
            java.io.FileInputStream r1 = (java.io.FileInputStream) r1     // Catch:{ Exception -> 0x0027 }
            java.nio.channels.FileChannel r7 = r1.getChannel()     // Catch:{ Exception -> 0x0027 }
            r2 = 0
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r1 = r7
            r6 = r0
            r1.transferTo(r2, r4, r6)     // Catch:{ Exception -> 0x0027 }
            r7.close()     // Catch:{ Exception -> 0x0027 }
            r0.close()     // Catch:{ Exception -> 0x0027 }
            return
        L_0x0027:
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]
        L_0x002b:
            int r1 = r8.read(r0)
            if (r1 >= 0) goto L_0x0038
            r8.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            r9.close()     // Catch:{ IOException -> 0x0037 }
        L_0x0037:
            return
        L_0x0038:
            r2 = 0
            r9.write(r0, r2, r1)
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.IOUtil.copyCompletely(java.io.InputStream, java.io.OutputStream):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|5|6|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyCompletely(java.io.Reader r3, java.io.Writer r4) throws java.io.IOException {
        /*
            r0 = 8192(0x2000, float:1.14794E-41)
            char[] r0 = new char[r0]
        L_0x0004:
            int r1 = r3.read(r0)
            if (r1 >= 0) goto L_0x0011
            r3.close()     // Catch:{ IOException -> 0x000d }
        L_0x000d:
            r4.close()     // Catch:{ IOException -> 0x0010 }
        L_0x0010:
            return
        L_0x0011:
            r2 = 0
            r4.write(r0, r2, r1)
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.IOUtil.copyCompletely(java.io.Reader, java.io.Writer):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0030, code lost:
        if (r3 != null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003a, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyCompletely(java.net.URI r3, java.net.URI r4) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            java.io.File r1 = r0.getParentFile()
            r1.mkdirs()
            java.io.InputStream r3 = urlToStream(r3)     // Catch:{ IllegalArgumentException -> 0x003b }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x002d }
            r1.<init>(r0)     // Catch:{ all -> 0x002d }
            copyCompletely((java.io.InputStream) r3, (java.io.OutputStream) r1)     // Catch:{ all -> 0x0021 }
            r1.close()     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x0020
            r3.close()     // Catch:{ IllegalArgumentException -> 0x003b }
        L_0x0020:
            return
        L_0x0021:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch:{ all -> 0x002d }
        L_0x002c:
            throw r2     // Catch:{ all -> 0x002d }
        L_0x002d:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002f }
        L_0x002f:
            r1 = move-exception
            if (r3 == 0) goto L_0x003a
            r3.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch:{ IllegalArgumentException -> 0x003b }
        L_0x003a:
            throw r1     // Catch:{ IllegalArgumentException -> 0x003b }
        L_0x003b:
            java.io.IOException r3 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot copy to "
            r0.<init>(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.IOUtil.copyCompletely(java.net.URI, java.net.URI):void");
    }

    private static InputStream urlToStream(URI uri) throws IOException {
        try {
            File file = new File(uri);
            if (file.exists()) {
                return new FileInputStream(file);
            }
        } catch (Exception unused) {
        }
        return uri.toURL().openStream();
    }

    public static File createDir(File file, String str) {
        if (str != null) {
            file = new File(file, str);
        }
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return file;
    }
}
