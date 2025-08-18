package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class EditBuildScript {
    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 3) {
            strArr[0] = strArr[0].replace('/', File.separatorChar);
            File file = new File(strArr[0]);
            StringBuffer readFile = readFile(file);
            String str = "<property name=\"" + strArr[1] + "\" value=\"";
            int indexOf = readFile.indexOf(str);
            if (indexOf >= 0) {
                int length = str.length() + indexOf;
                while (readFile.charAt(length) != '\"') {
                    length++;
                }
                readFile.replace(indexOf + str.length(), length, strArr[2]);
                writeFile(file, readFile);
                return;
            }
            throw new IllegalArgumentException("Can't find token: " + str);
        }
        throw new IllegalArgumentException("Wrong number of arguments");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002e, code lost:
        if (r3 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0034, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0035, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0038, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.StringBuffer readFile(java.io.File r3) throws java.io.IOException {
        /*
            java.nio.file.Path r3 = r3.toPath()
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.ISO_8859_1
            java.io.BufferedReader r3 = java.nio.file.Files.newBufferedReader(r3, r0)
            java.io.StringWriter r0 = new java.io.StringWriter     // Catch:{ all -> 0x002b }
            r0.<init>()     // Catch:{ all -> 0x002b }
            copy(r3, r0)     // Catch:{ all -> 0x001f }
            java.lang.StringBuffer r1 = r0.getBuffer()     // Catch:{ all -> 0x001f }
            r0.close()     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x001e
            r3.close()
        L_0x001e:
            return r1
        L_0x001f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x002b }
        L_0x002a:
            throw r2     // Catch:{ all -> 0x002b }
        L_0x002b:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002d }
        L_0x002d:
            r1 = move-exception
            if (r3 == 0) goto L_0x0038
            r3.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r3 = move-exception
            r0.addSuppressed(r3)
        L_0x0038:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.repackage.EditBuildScript.readFile(java.io.File):java.lang.StringBuffer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0031, code lost:
        if (r2 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0037, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0038, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void writeFile(java.io.File r2, java.lang.StringBuffer r3) throws java.io.IOException {
        /*
            java.nio.file.Path r2 = r2.toPath()
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.ISO_8859_1
            r1 = 0
            java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r1]
            java.io.BufferedWriter r2 = java.nio.file.Files.newBufferedWriter(r2, r0, r1)
            java.io.StringReader r0 = new java.io.StringReader     // Catch:{ all -> 0x002e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x002e }
            r0.<init>(r3)     // Catch:{ all -> 0x002e }
            copy(r0, r2)     // Catch:{ all -> 0x0022 }
            r0.close()     // Catch:{ all -> 0x002e }
            if (r2 == 0) goto L_0x0021
            r2.close()
        L_0x0021:
            return
        L_0x0022:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ all -> 0x002e }
        L_0x002d:
            throw r1     // Catch:{ all -> 0x002e }
        L_0x002e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x003b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.repackage.EditBuildScript.writeFile(java.io.File, java.lang.StringBuffer):void");
    }

    static void copy(Reader reader, Writer writer) throws IOException {
        char[] cArr = new char[16384];
        while (true) {
            int read = reader.read(cArr, 0, 16384);
            if (read >= 0) {
                writer.write(cArr, 0, read);
            } else {
                return;
            }
        }
    }
}
