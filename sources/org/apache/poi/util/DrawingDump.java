package org.apache.poi.util;

public final class DrawingDump {
    private DrawingDump() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r3.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0086, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r8.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008f, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0092, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0097, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r8.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x009e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a4, code lost:
        r8.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00a7, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r8) throws java.io.IOException {
        /*
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter
            java.io.PrintStream r1 = java.lang.System.out
            java.nio.charset.Charset r2 = java.nio.charset.Charset.defaultCharset()
            r0.<init>(r1, r2)
            java.io.PrintWriter r1 = new java.io.PrintWriter     // Catch:{ all -> 0x009c }
            r1.<init>(r0)     // Catch:{ all -> 0x009c }
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0090 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0090 }
            r4 = 0
            r8 = r8[r4]     // Catch:{ all -> 0x0090 }
            r3.<init>(r8)     // Catch:{ all -> 0x0090 }
            r2.<init>((java.io.File) r3)     // Catch:{ all -> 0x0090 }
            org.apache.poi.hssf.usermodel.HSSFWorkbook r8 = new org.apache.poi.hssf.usermodel.HSSFWorkbook     // Catch:{ all -> 0x0084 }
            r8.<init>((org.apache.poi.poifs.filesystem.POIFSFileSystem) r2)     // Catch:{ all -> 0x0084 }
            java.lang.String r3 = "Drawing group:"
            r1.println(r3)     // Catch:{ all -> 0x0078 }
            r3 = 1
            r8.dumpDrawingGroupRecords(r3)     // Catch:{ all -> 0x0078 }
            java.util.Iterator r4 = r8.iterator()     // Catch:{ all -> 0x0078 }
        L_0x002f:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0078 }
            if (r5 == 0) goto L_0x006b
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0078 }
            org.apache.poi.ss.usermodel.Sheet r5 = (org.apache.poi.ss.usermodel.Sheet) r5     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r6.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r7 = "Sheet "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ all -> 0x0078 }
            java.lang.String r7 = "("
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0078 }
            java.lang.String r7 = r5.getSheetName()     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0078 }
            java.lang.String r7 = "):"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0078 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0078 }
            r1.println(r6)     // Catch:{ all -> 0x0078 }
            org.apache.poi.hssf.usermodel.HSSFSheet r5 = (org.apache.poi.hssf.usermodel.HSSFSheet) r5     // Catch:{ all -> 0x0078 }
            r5.dumpDrawingRecords(r3, r1)     // Catch:{ all -> 0x0078 }
            goto L_0x002f
        L_0x006b:
            r8.close()     // Catch:{ all -> 0x0084 }
            r2.close()     // Catch:{ all -> 0x0090 }
            r1.close()     // Catch:{ all -> 0x009c }
            r0.close()
            return
        L_0x0078:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x007a }
        L_0x007a:
            r4 = move-exception
            r8.close()     // Catch:{ all -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r8 = move-exception
            r3.addSuppressed(r8)     // Catch:{ all -> 0x0084 }
        L_0x0083:
            throw r4     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0086 }
        L_0x0086:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x008b }
            goto L_0x008f
        L_0x008b:
            r2 = move-exception
            r8.addSuppressed(r2)     // Catch:{ all -> 0x0090 }
        L_0x008f:
            throw r3     // Catch:{ all -> 0x0090 }
        L_0x0090:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0092 }
        L_0x0092:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0097 }
            goto L_0x009b
        L_0x0097:
            r1 = move-exception
            r8.addSuppressed(r1)     // Catch:{ all -> 0x009c }
        L_0x009b:
            throw r2     // Catch:{ all -> 0x009c }
        L_0x009c:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x009e }
        L_0x009e:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x00a3 }
            goto L_0x00a7
        L_0x00a3:
            r0 = move-exception
            r8.addSuppressed(r0)
        L_0x00a7:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.DrawingDump.main(java.lang.String[]):void");
    }
}
