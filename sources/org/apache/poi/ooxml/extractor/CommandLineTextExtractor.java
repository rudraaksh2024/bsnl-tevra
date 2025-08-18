package org.apache.poi.ooxml.extractor;

public final class CommandLineTextExtractor {
    public static final String DIVIDER = "=======================";

    private CommandLineTextExtractor() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0091, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0092, code lost:
        if (r3 != null) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0098, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0099, code lost:
        r9.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r9) throws java.lang.Exception {
        /*
            java.lang.String r0 = "   ======================="
            int r1 = r9.length
            r2 = 1
            if (r1 >= r2) goto L_0x0017
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r3 = "Use:"
            r1.println(r3)
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r3 = "   CommandLineTextExtractor <filename> [filename] [filename]"
            r1.println(r3)
            java.lang.System.exit(r2)
        L_0x0017:
            int r1 = r9.length
            r2 = 0
        L_0x0019:
            if (r2 >= r1) goto L_0x009d
            r3 = r9[r2]
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r5 = "======================="
            r4.println(r5)
            java.io.File r4 = new java.io.File
            r4.<init>(r3)
            java.io.PrintStream r3 = java.lang.System.out
            r3.println(r4)
            org.apache.poi.extractor.POITextExtractor r3 = org.apache.poi.extractor.ExtractorFactory.createExtractor((java.io.File) r4)
            org.apache.poi.extractor.POITextExtractor r4 = r3.getMetadataTextExtractor()     // Catch:{ all -> 0x008f }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ all -> 0x008f }
            r6.println(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r4 = r4.getText()     // Catch:{ all -> 0x008f }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ all -> 0x008f }
            r6.println(r4)     // Catch:{ all -> 0x008f }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ all -> 0x008f }
            r6.println(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = r3.getText()     // Catch:{ all -> 0x008f }
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ all -> 0x008f }
            r7.println(r6)     // Catch:{ all -> 0x008f }
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ all -> 0x008f }
            r7.println(r5)     // Catch:{ all -> 0x008f }
            java.io.PrintStream r5 = java.lang.System.out     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r7.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r8 = "Had "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x008f }
            int r4 = r4.length()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r4 = r7.append(r4)     // Catch:{ all -> 0x008f }
            java.lang.String r7 = " characters of metadata and "
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ all -> 0x008f }
            int r6 = r6.length()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = " characters of text"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x008f }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x008f }
            r5.println(r4)     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x008c
            r3.close()
        L_0x008c:
            int r2 = r2 + 1
            goto L_0x0019
        L_0x008f:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r0 = move-exception
            if (r3 == 0) goto L_0x009c
            r3.close()     // Catch:{ all -> 0x0098 }
            goto L_0x009c
        L_0x0098:
            r1 = move-exception
            r9.addSuppressed(r1)
        L_0x009c:
            throw r0
        L_0x009d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.extractor.CommandLineTextExtractor.main(java.lang.String[]):void");
    }
}
