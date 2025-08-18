package org.apache.xmlbeans.impl.common;

import java.io.File;
import java.io.IOException;

public class JarHelper {
    private static final int BUFFER_SIZE = 2156;
    private static final char SEP = '/';
    private final byte[] mBuffer = new byte[BUFFER_SIZE];
    private String mDestJarName = "";
    private boolean mVerbose = false;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002a, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0032, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0033, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0036, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void jarDir(java.io.File r3, java.io.File r4) throws java.io.IOException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0037
            if (r4 == 0) goto L_0x0037
            java.lang.String r0 = r4.getCanonicalPath()
            r2.mDestJarName = r0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r4)
            java.util.jar.JarOutputStream r4 = new java.util.jar.JarOutputStream     // Catch:{ all -> 0x002b }
            r4.<init>(r0)     // Catch:{ all -> 0x002b }
            r1 = 0
            r2.jarDir(r3, r4, r1)     // Catch:{ all -> 0x001f }
            r4.close()     // Catch:{ all -> 0x002b }
            r0.close()
            return
        L_0x001f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r3 = move-exception
            r4.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ all -> 0x002b }
        L_0x002a:
            throw r3     // Catch:{ all -> 0x002b }
        L_0x002b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002d }
        L_0x002d:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0036:
            throw r3
        L_0x0037:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.JarHelper.jarDir(java.io.File, java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unjarDir(java.io.File r2, java.io.File r3) throws java.io.IOException {
        /*
            r1 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            r1.unjar(r0, r3)     // Catch:{ all -> 0x000c }
            r0.close()
            return
        L_0x000c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000e }
        L_0x000e:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.JarHelper.unjarDir(java.io.File, java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0099, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009e, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
        r10.addSuppressed(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a2, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unjar(java.io.InputStream r11, java.io.File r12) throws java.io.IOException {
        /*
            r10 = this;
            java.util.jar.JarInputStream r0 = new java.util.jar.JarInputStream
            r0.<init>(r11)
        L_0x0005:
            java.util.jar.JarEntry r11 = r0.getNextJarEntry()     // Catch:{ all -> 0x0097 }
            if (r11 == 0) goto L_0x0093
            boolean r1 = r11.isDirectory()     // Catch:{ all -> 0x0097 }
            r2 = -1
            if (r1 == 0) goto L_0x002f
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = r11.getName()     // Catch:{ all -> 0x0097 }
            r1.<init>(r12, r4)     // Catch:{ all -> 0x0097 }
            r1.mkdir()     // Catch:{ all -> 0x0097 }
            long r4 = r11.getTime()     // Catch:{ all -> 0x0097 }
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0005
            long r2 = r11.getTime()     // Catch:{ all -> 0x0097 }
            r1.setLastModified(r2)     // Catch:{ all -> 0x0097 }
            goto L_0x0005
        L_0x002f:
            r1 = 2156(0x86c, float:3.021E-42)
            byte[] r4 = new byte[r1]     // Catch:{ all -> 0x0097 }
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x0097 }
            java.lang.String r6 = r11.getName()     // Catch:{ all -> 0x0097 }
            r5.<init>(r12, r6)     // Catch:{ all -> 0x0097 }
            boolean r6 = r10.mVerbose     // Catch:{ all -> 0x0097 }
            if (r6 == 0) goto L_0x0066
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r7.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r8 = "unjarring "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r7 = r7.append(r5)     // Catch:{ all -> 0x0097 }
            java.lang.String r8 = " from "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0097 }
            java.lang.String r8 = r11.getName()     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0097 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0097 }
            r6.println(r7)     // Catch:{ all -> 0x0097 }
        L_0x0066:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x0097 }
            r6.<init>(r5)     // Catch:{ all -> 0x0097 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0097 }
            r7.<init>(r6, r1)     // Catch:{ all -> 0x0097 }
        L_0x0070:
            r6 = 0
            int r8 = r0.read(r4, r6, r1)     // Catch:{ all -> 0x0097 }
            r9 = -1
            if (r8 == r9) goto L_0x007c
            r7.write(r4, r6, r8)     // Catch:{ all -> 0x0097 }
            goto L_0x0070
        L_0x007c:
            r7.flush()     // Catch:{ all -> 0x0097 }
            r7.close()     // Catch:{ all -> 0x0097 }
            long r6 = r11.getTime()     // Catch:{ all -> 0x0097 }
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x0005
            long r1 = r11.getTime()     // Catch:{ all -> 0x0097 }
            r5.setLastModified(r1)     // Catch:{ all -> 0x0097 }
            goto L_0x0005
        L_0x0093:
            r0.close()
            return
        L_0x0097:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r11 = move-exception
            r0.close()     // Catch:{ all -> 0x009e }
            goto L_0x00a2
        L_0x009e:
            r12 = move-exception
            r10.addSuppressed(r12)
        L_0x00a2:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.JarHelper.unjar(java.io.InputStream, java.io.File):void");
    }

    public void setVerbose(boolean z) {
        this.mVerbose = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0117, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011c, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x011d, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0120, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void jarDir(java.io.File r6, java.util.jar.JarOutputStream r7, java.lang.String r8) throws java.io.IOException {
        /*
            r5 = this;
            boolean r0 = r5.mVerbose
            if (r0 == 0) goto L_0x0018
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "checking "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
        L_0x0018:
            boolean r0 = r6.isDirectory()
            r1 = 0
            if (r0 == 0) goto L_0x006c
            java.lang.String[] r0 = r6.list()
            if (r8 != 0) goto L_0x0028
            java.lang.String r2 = ""
            goto L_0x0043
        L_0x0028:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.String r3 = r6.getName()
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = 47
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
        L_0x0043:
            if (r8 == 0) goto L_0x005a
            java.util.jar.JarEntry r8 = new java.util.jar.JarEntry
            r8.<init>(r2)
            long r3 = r6.lastModified()
            r8.setTime(r3)
            r7.putNextEntry(r8)
            r7.flush()
            r7.closeEntry()
        L_0x005a:
            if (r0 == 0) goto L_0x0114
            int r8 = r0.length
        L_0x005d:
            if (r1 >= r8) goto L_0x0114
            r3 = r0[r1]
            java.io.File r4 = new java.io.File
            r4.<init>(r6, r3)
            r5.jarDir(r4, r7, r2)
            int r1 = r1 + 1
            goto L_0x005d
        L_0x006c:
            java.lang.String r0 = r6.getCanonicalPath()
            java.lang.String r2 = r5.mDestJarName
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0095
            boolean r5 = r5.mVerbose
            if (r5 == 0) goto L_0x0094
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "skipping "
            r7.<init>(r8)
            java.lang.String r6 = r6.getPath()
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r6 = r6.toString()
            r5.println(r6)
        L_0x0094:
            return
        L_0x0095:
            boolean r0 = r5.mVerbose
            if (r0 == 0) goto L_0x00b1
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "adding "
            r2.<init>(r3)
            java.lang.String r3 = r6.getPath()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
        L_0x00b1:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r6)
            java.util.jar.JarEntry r2 = new java.util.jar.JarEntry     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0115 }
            r3.<init>()     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r8 = r3.append(r8)     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = r6.getName()     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r8 = r8.append(r3)     // Catch:{ all -> 0x0115 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0115 }
            r2.<init>(r8)     // Catch:{ all -> 0x0115 }
            long r3 = r6.lastModified()     // Catch:{ all -> 0x0115 }
            r2.setTime(r3)     // Catch:{ all -> 0x0115 }
            r7.putNextEntry(r2)     // Catch:{ all -> 0x0115 }
        L_0x00da:
            byte[] r6 = r5.mBuffer     // Catch:{ all -> 0x0115 }
            int r6 = r0.read(r6)     // Catch:{ all -> 0x0115 }
            r8 = -1
            if (r6 == r8) goto L_0x010b
            byte[] r8 = r5.mBuffer     // Catch:{ all -> 0x0115 }
            r7.write(r8, r1, r6)     // Catch:{ all -> 0x0115 }
            boolean r8 = r5.mVerbose     // Catch:{ all -> 0x0115 }
            if (r8 == 0) goto L_0x00da
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0115 }
            r2.<init>()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "wrote "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r6 = r2.append(r6)     // Catch:{ all -> 0x0115 }
            java.lang.String r2 = " bytes"
            java.lang.StringBuilder r6 = r6.append(r2)     // Catch:{ all -> 0x0115 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0115 }
            r8.println(r6)     // Catch:{ all -> 0x0115 }
            goto L_0x00da
        L_0x010b:
            r7.flush()     // Catch:{ all -> 0x0115 }
            r7.closeEntry()     // Catch:{ all -> 0x0115 }
            r0.close()
        L_0x0114:
            return
        L_0x0115:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0117 }
        L_0x0117:
            r6 = move-exception
            r0.close()     // Catch:{ all -> 0x011c }
            goto L_0x0120
        L_0x011c:
            r7 = move-exception
            r5.addSuppressed(r7)
        L_0x0120:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.JarHelper.jarDir(java.io.File, java.util.jar.JarOutputStream, java.lang.String):void");
    }

    public static void main(String[] strArr) throws IOException {
        if (strArr.length < 2) {
            System.err.println("Usage: JarHelper jarname.jar directory");
            return;
        }
        JarHelper jarHelper = new JarHelper();
        jarHelper.mVerbose = true;
        jarHelper.jarDir(new File(strArr[1]), new File(strArr[0]));
    }
}
