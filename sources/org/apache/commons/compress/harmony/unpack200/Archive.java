package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;

public class Archive {
    private boolean deflateHint;
    private String inputFileName;
    private InputStream inputStream;
    private FileOutputStream logFile;
    private int logLevel = 1;
    private String outputFileName;
    private final JarOutputStream outputStream;
    private boolean overrideDeflateHint;
    private boolean removePackFile;

    public Archive(String str, String str2) throws FileNotFoundException, IOException {
        this.inputFileName = str;
        this.outputFileName = str2;
        this.inputStream = new FileInputStream(str);
        this.outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
    }

    public Archive(InputStream inputStream2, JarOutputStream jarOutputStream) throws IOException {
        this.inputStream = inputStream2;
        this.outputStream = jarOutputStream;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|(2:4|(1:6)(2:7|8))|9|(1:11)(1:12)|13|(1:15)|78|16|(3:18|(2:20|80)(1:81)|21)|79|22|(2:24|(2:25|(4:27|(2:30|28)|82|31)))(2:32|(10:35|(1:37)(1:38)|39|(1:41)|42|(1:44)|45|(2:47|85)(1:84)|83|33))|48|49|50|51|52|(2:55|56)|57|(3:60|(1:62)|(1:87)(2:64|65))(1:86)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:66|67|68|69|70|71|(0)|76) */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x0154 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0185 */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x015d A[SYNTHETIC, Splitter:B:55:0x015d] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x018e A[SYNTHETIC, Splitter:B:74:0x018e] */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unpack() throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            r10 = this;
            java.util.jar.JarOutputStream r0 = r10.outputStream
            java.lang.String r1 = "PACK200"
            r0.setComment(r1)
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            boolean r0 = r0.markSupported()     // Catch:{ all -> 0x017f }
            if (r0 != 0) goto L_0x0025
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x017f }
            java.io.InputStream r1 = r10.inputStream     // Catch:{ all -> 0x017f }
            r0.<init>(r1)     // Catch:{ all -> 0x017f }
            r10.inputStream = r0     // Catch:{ all -> 0x017f }
            boolean r0 = r0.markSupported()     // Catch:{ all -> 0x017f }
            if (r0 == 0) goto L_0x001f
            goto L_0x0025
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x017f }
            r0.<init>()     // Catch:{ all -> 0x017f }
            throw r0     // Catch:{ all -> 0x017f }
        L_0x0025:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            r1 = 2
            r0.mark(r1)     // Catch:{ all -> 0x017f }
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            int r0 = r0.read()     // Catch:{ all -> 0x017f }
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.io.InputStream r2 = r10.inputStream     // Catch:{ all -> 0x017f }
            int r2 = r2.read()     // Catch:{ all -> 0x017f }
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r0 = r0 | r2
            r2 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r2) goto L_0x0057
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            r0.reset()     // Catch:{ all -> 0x017f }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x017f }
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x017f }
            java.io.InputStream r3 = r10.inputStream     // Catch:{ all -> 0x017f }
            r2.<init>(r3)     // Catch:{ all -> 0x017f }
            r0.<init>(r2)     // Catch:{ all -> 0x017f }
            r10.inputStream = r0     // Catch:{ all -> 0x017f }
            goto L_0x005c
        L_0x0057:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            r0.reset()     // Catch:{ all -> 0x017f }
        L_0x005c:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            r2 = 4
            r0.mark(r2)     // Catch:{ all -> 0x017f }
            int[] r0 = new int[r2]     // Catch:{ all -> 0x017f }
            r3 = 202(0xca, float:2.83E-43)
            r4 = 0
            r0[r4] = r3     // Catch:{ all -> 0x017f }
            r3 = 254(0xfe, float:3.56E-43)
            r5 = 1
            r0[r5] = r3     // Catch:{ all -> 0x017f }
            r3 = 208(0xd0, float:2.91E-43)
            r0[r1] = r3     // Catch:{ all -> 0x017f }
            r3 = 3
            r6 = 13
            r0[r3] = r6     // Catch:{ all -> 0x017f }
            int[] r3 = new int[r2]     // Catch:{ all -> 0x017f }
            r6 = r4
        L_0x007a:
            if (r6 >= r2) goto L_0x0087
            java.io.InputStream r7 = r10.inputStream     // Catch:{ all -> 0x017f }
            int r7 = r7.read()     // Catch:{ all -> 0x017f }
            r3[r6] = r7     // Catch:{ all -> 0x017f }
            int r6 = r6 + 1
            goto L_0x007a
        L_0x0087:
            r6 = r4
            r7 = r6
        L_0x0089:
            if (r6 >= r2) goto L_0x0095
            r8 = r3[r6]     // Catch:{ all -> 0x017f }
            r9 = r0[r6]     // Catch:{ all -> 0x017f }
            if (r8 == r9) goto L_0x0092
            r7 = r5
        L_0x0092:
            int r6 = r6 + 1
            goto L_0x0089
        L_0x0095:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x017f }
            r0.reset()     // Catch:{ all -> 0x017f }
            if (r7 == 0) goto L_0x00c9
            java.util.jar.JarInputStream r0 = new java.util.jar.JarInputStream     // Catch:{ all -> 0x017f }
            java.io.InputStream r1 = r10.inputStream     // Catch:{ all -> 0x017f }
            r0.<init>(r1)     // Catch:{ all -> 0x017f }
        L_0x00a3:
            java.util.jar.JarEntry r1 = r0.getNextJarEntry()     // Catch:{ all -> 0x017f }
            if (r1 == 0) goto L_0x014f
            java.util.jar.JarOutputStream r2 = r10.outputStream     // Catch:{ all -> 0x017f }
            r2.putNextEntry(r1)     // Catch:{ all -> 0x017f }
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x017f }
            int r2 = r0.read(r1)     // Catch:{ all -> 0x017f }
        L_0x00b6:
            r3 = -1
            if (r2 == r3) goto L_0x00c3
            java.util.jar.JarOutputStream r3 = r10.outputStream     // Catch:{ all -> 0x017f }
            r3.write(r1, r4, r2)     // Catch:{ all -> 0x017f }
            int r2 = r0.read(r1)     // Catch:{ all -> 0x017f }
            goto L_0x00b6
        L_0x00c3:
            java.util.jar.JarOutputStream r1 = r10.outputStream     // Catch:{ all -> 0x017f }
            r1.closeEntry()     // Catch:{ all -> 0x017f }
            goto L_0x00a3
        L_0x00c9:
            r0 = r4
        L_0x00ca:
            java.io.InputStream r2 = r10.inputStream     // Catch:{ all -> 0x017f }
            boolean r2 = r10.available(r2)     // Catch:{ all -> 0x017f }
            if (r2 == 0) goto L_0x014f
            int r0 = r0 + r5
            org.apache.commons.compress.harmony.unpack200.Segment r2 = new org.apache.commons.compress.harmony.unpack200.Segment     // Catch:{ all -> 0x017f }
            r2.<init>()     // Catch:{ all -> 0x017f }
            int r3 = r10.logLevel     // Catch:{ all -> 0x017f }
            r2.setLogLevel(r3)     // Catch:{ all -> 0x017f }
            java.io.FileOutputStream r3 = r10.logFile     // Catch:{ all -> 0x017f }
            if (r3 == 0) goto L_0x00e2
            goto L_0x00e4
        L_0x00e2:
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ all -> 0x017f }
        L_0x00e4:
            r2.setLogStream(r3)     // Catch:{ all -> 0x017f }
            r2.setPreRead(r4)     // Catch:{ all -> 0x017f }
            if (r0 != r5) goto L_0x0110
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x017f }
            r3.<init>()     // Catch:{ all -> 0x017f }
            java.lang.String r6 = "Unpacking from "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x017f }
            java.lang.String r6 = r10.inputFileName     // Catch:{ all -> 0x017f }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x017f }
            java.lang.String r6 = " to "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x017f }
            java.lang.String r6 = r10.outputFileName     // Catch:{ all -> 0x017f }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x017f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x017f }
            r2.log(r1, r3)     // Catch:{ all -> 0x017f }
        L_0x0110:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x017f }
            r3.<init>()     // Catch:{ all -> 0x017f }
            java.lang.String r6 = "Reading segment "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x017f }
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ all -> 0x017f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x017f }
            r2.log(r1, r3)     // Catch:{ all -> 0x017f }
            boolean r3 = r10.overrideDeflateHint     // Catch:{ all -> 0x017f }
            if (r3 == 0) goto L_0x012f
            boolean r3 = r10.deflateHint     // Catch:{ all -> 0x017f }
            r2.overrideDeflateHint(r3)     // Catch:{ all -> 0x017f }
        L_0x012f:
            java.io.InputStream r3 = r10.inputStream     // Catch:{ all -> 0x017f }
            java.util.jar.JarOutputStream r6 = r10.outputStream     // Catch:{ all -> 0x017f }
            r2.unpack(r3, r6)     // Catch:{ all -> 0x017f }
            java.util.jar.JarOutputStream r2 = r10.outputStream     // Catch:{ all -> 0x017f }
            r2.flush()     // Catch:{ all -> 0x017f }
            java.io.InputStream r2 = r10.inputStream     // Catch:{ all -> 0x017f }
            boolean r3 = r2 instanceof java.io.FileInputStream     // Catch:{ all -> 0x017f }
            if (r3 == 0) goto L_0x00ca
            java.io.FileInputStream r2 = (java.io.FileInputStream) r2     // Catch:{ all -> 0x017f }
            java.io.FileDescriptor r2 = r2.getFD()     // Catch:{ all -> 0x017f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x017f }
            r10.inputFileName = r2     // Catch:{ all -> 0x017f }
            goto L_0x00ca
        L_0x014f:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ Exception -> 0x0154 }
            r0.close()     // Catch:{ Exception -> 0x0154 }
        L_0x0154:
            java.util.jar.JarOutputStream r0 = r10.outputStream     // Catch:{ Exception -> 0x0159 }
            r0.close()     // Catch:{ Exception -> 0x0159 }
        L_0x0159:
            java.io.FileOutputStream r0 = r10.logFile
            if (r0 == 0) goto L_0x0160
            r0.close()     // Catch:{ Exception -> 0x0160 }
        L_0x0160:
            boolean r0 = r10.removePackFile
            if (r0 == 0) goto L_0x017e
            java.lang.String r0 = r10.inputFileName
            if (r0 == 0) goto L_0x0173
            java.io.File r0 = new java.io.File
            java.lang.String r10 = r10.inputFileName
            r0.<init>(r10)
            boolean r4 = r0.delete()
        L_0x0173:
            if (r4 == 0) goto L_0x0176
            goto L_0x017e
        L_0x0176:
            org.apache.commons.compress.harmony.pack200.Pack200Exception r10 = new org.apache.commons.compress.harmony.pack200.Pack200Exception
            java.lang.String r0 = "Failed to delete the input file."
            r10.<init>(r0)
            throw r10
        L_0x017e:
            return
        L_0x017f:
            r0 = move-exception
            java.io.InputStream r1 = r10.inputStream     // Catch:{ Exception -> 0x0185 }
            r1.close()     // Catch:{ Exception -> 0x0185 }
        L_0x0185:
            java.util.jar.JarOutputStream r1 = r10.outputStream     // Catch:{ Exception -> 0x018a }
            r1.close()     // Catch:{ Exception -> 0x018a }
        L_0x018a:
            java.io.FileOutputStream r10 = r10.logFile
            if (r10 == 0) goto L_0x0191
            r10.close()     // Catch:{ Exception -> 0x0191 }
        L_0x0191:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.Archive.unpack():void");
    }

    private boolean available(InputStream inputStream2) throws IOException {
        inputStream2.mark(1);
        int read = inputStream2.read();
        inputStream2.reset();
        if (read != -1) {
            return true;
        }
        return false;
    }

    public void setRemovePackFile(boolean z) {
        this.removePackFile = z;
    }

    public void setVerbose(boolean z) {
        if (z) {
            this.logLevel = 2;
        } else if (this.logLevel == 2) {
            this.logLevel = 1;
        }
    }

    public void setQuiet(boolean z) {
        if (z) {
            this.logLevel = 0;
        } else if (this.logLevel == 0) {
            this.logLevel = 0;
        }
    }

    public void setLogFile(String str) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str);
    }

    public void setLogFile(String str, boolean z) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str, z);
    }

    public void setDeflateHint(boolean z) {
        this.overrideDeflateHint = true;
        this.deflateHint = z;
    }
}
