package org.apache.poi.poifs.macros;

import java.io.File;
import java.io.IOException;

public class VBAMacroExtractor {
    public static void main(String[] strArr) throws IOException {
        if (strArr.length == 0) {
            System.err.println("Use:");
            System.err.println("   VBAMacroExtractor <office.doc> [output]");
            System.err.println();
            System.err.println("If an output directory is given, macros are written there");
            System.err.println("Otherwise they are output to the screen");
            System.exit(1);
        }
        new VBAMacroExtractor().extract(new File(strArr[0]), strArr.length > 1 ? new File(strArr[1]) : null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00de, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e3, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e7, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ea, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ef, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f0, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f3, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fe, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0103, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0104, code lost:
        r5.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0107, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void extract(java.io.File r5, java.io.File r6, java.lang.String r7) throws java.io.IOException {
        /*
            r4 = this;
            boolean r4 = r5.exists()
            if (r4 == 0) goto L_0x0108
            java.io.PrintStream r4 = java.lang.System.err
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Extracting VBA Macros from "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r1 = " to "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.print(r0)
            if (r6 == 0) goto L_0x0050
            boolean r4 = r6.exists()
            if (r4 != 0) goto L_0x004a
            boolean r4 = r6.mkdirs()
            if (r4 == 0) goto L_0x002f
            goto L_0x004a
        L_0x002f:
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "Output directory "
            r5.<init>(r7)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = " could not be created"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x004a:
            java.io.PrintStream r4 = java.lang.System.err
            r4.println(r6)
            goto L_0x0057
        L_0x0050:
            java.io.PrintStream r4 = java.lang.System.err
            java.lang.String r0 = "STDOUT"
            r4.println(r0)
        L_0x0057:
            org.apache.poi.poifs.macros.VBAMacroReader r4 = new org.apache.poi.poifs.macros.VBAMacroReader
            r4.<init>((java.io.File) r5)
            java.util.Map r5 = r4.readMacros()     // Catch:{ all -> 0x00fc }
            r4.close()
            java.util.Set r4 = r5.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x006b:
            boolean r5 = r4.hasNext()
            java.lang.String r0 = "---------------------------------------"
            if (r5 == 0) goto L_0x00f4
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r1 = r5.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            if (r6 != 0) goto L_0x009c
            java.io.PrintStream r2 = java.lang.System.out
            r2.println(r0)
            java.io.PrintStream r0 = java.lang.System.out
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            r0.println()
            java.io.PrintStream r0 = java.lang.System.out
            r0.println(r5)
            goto L_0x006b
        L_0x009c:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.StringBuilder r1 = r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r6, r1)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r0)
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00e8 }
            java.nio.charset.Charset r3 = org.apache.poi.util.StringUtil.UTF8     // Catch:{ all -> 0x00e8 }
            r2.<init>(r1, r3)     // Catch:{ all -> 0x00e8 }
            r2.write(r5)     // Catch:{ all -> 0x00dc }
            r2.close()     // Catch:{ all -> 0x00e8 }
            r1.close()
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Extracted "
            r1.<init>(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            r5.println(r0)
            goto L_0x006b
        L_0x00dc:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x00de }
        L_0x00de:
            r5 = move-exception
            r2.close()     // Catch:{ all -> 0x00e3 }
            goto L_0x00e7
        L_0x00e3:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ all -> 0x00e8 }
        L_0x00e7:
            throw r5     // Catch:{ all -> 0x00e8 }
        L_0x00e8:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x00ea }
        L_0x00ea:
            r5 = move-exception
            r1.close()     // Catch:{ all -> 0x00ef }
            goto L_0x00f3
        L_0x00ef:
            r6 = move-exception
            r4.addSuppressed(r6)
        L_0x00f3:
            throw r5
        L_0x00f4:
            if (r6 != 0) goto L_0x00fb
            java.io.PrintStream r4 = java.lang.System.out
            r4.println(r0)
        L_0x00fb:
            return
        L_0x00fc:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00fe }
        L_0x00fe:
            r6 = move-exception
            r4.close()     // Catch:{ all -> 0x0103 }
            goto L_0x0107
        L_0x0103:
            r4 = move-exception
            r5.addSuppressed(r4)
        L_0x0107:
            throw r6
        L_0x0108:
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroExtractor.extract(java.io.File, java.io.File, java.lang.String):void");
    }

    public void extract(File file, File file2) throws IOException {
        extract(file, file2, ".vba");
    }
}
