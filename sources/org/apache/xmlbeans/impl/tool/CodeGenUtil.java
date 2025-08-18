package org.apache.xmlbeans.impl.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.xmlbeans.SystemProperties;

public class CodeGenUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_COMPILER = "javac";
    public static final String DEFAULT_MEM_MAX = "256m";
    public static final String DEFAULT_MEM_START = "8m";

    public static URI resolve(URI uri, URI uri2) {
        URI resolve = uri.resolve(uri2);
        if (!"file".equals(resolve.getScheme()) || uri2.equals(resolve) || !uri.getPath().startsWith("//") || resolve.getPath().startsWith("//")) {
            return resolve;
        }
        try {
            return new URI("file", (String) null, "///".concat(resolve.getPath()), resolve.getQuery(), resolve.getFragment());
        } catch (URISyntaxException unused) {
            return resolve;
        }
    }

    static void addAllJavaFiles(List<File> list, List<String> list2) {
        for (File next : list) {
            if (next.isDirectory()) {
                File[] listFiles = next.listFiles(new CodeGenUtil$$ExternalSyntheticLambda1());
                if (listFiles != null) {
                    addAllJavaFiles(Arrays.asList(listFiles), list2);
                }
            } else {
                list2.add(quoteAndEscapeFilename(next.getAbsolutePath()));
            }
        }
    }

    static /* synthetic */ boolean lambda$addAllJavaFiles$0(File file) {
        return (file.isFile() && file.getName().endsWith(".java")) || file.isDirectory();
    }

    private static String quoteAndEscapeFilename(String str) {
        if (!str.contains(" ")) {
            return str;
        }
        return "\"" + str.replaceAll("\\\\", "\\\\\\\\") + "\"";
    }

    public static boolean externalCompile(List<File> list, File file, File[] fileArr, boolean z) {
        return externalCompile(list, file, fileArr, z, DEFAULT_COMPILER, (String) null, DEFAULT_MEM_START, DEFAULT_MEM_MAX, false, false);
    }

    public static boolean externalCompile(List<File> list, File file, File[] fileArr, boolean z, String str, String str2, String str3, boolean z2, boolean z3) {
        return externalCompile(list, file, fileArr, z, str, (String) null, str2, str3, z2, z3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012c, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x012d, code lost:
        if (r6 != null) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0137, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean externalCompile(java.util.List<java.io.File> r5, java.io.File r6, java.io.File[] r7, boolean r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, boolean r13, boolean r14) {
        /*
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.lang.String r0 = "javac"
            if (r9 != 0) goto L_0x000a
            r9 = r0
        L_0x000a:
            java.io.File r9 = findJavaTool(r9)
            java.lang.String r9 = r9.getAbsolutePath()
            r13.add(r9)
            if (r6 != 0) goto L_0x001f
            java.io.File r6 = new java.io.File
            java.lang.String r9 = "."
            r6.<init>(r9)
            goto L_0x002f
        L_0x001f:
            java.lang.String r9 = "-d"
            r13.add(r9)
            java.lang.String r9 = r6.getAbsolutePath()
            java.lang.String r9 = quoteAndEscapeFilename(r9)
            r13.add(r9)
        L_0x002f:
            if (r7 != 0) goto L_0x0035
            java.io.File[] r7 = systemClasspath()
        L_0x0035:
            int r9 = r7.length
            r1 = 0
            if (r9 <= 0) goto L_0x006a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r6 = r6.getAbsolutePath()
            r9.append(r6)
            int r6 = r7.length
            r2 = r1
        L_0x0047:
            if (r2 >= r6) goto L_0x005a
            r3 = r7[r2]
            java.lang.String r4 = java.io.File.pathSeparator
            r9.append(r4)
            java.lang.String r3 = r3.getAbsolutePath()
            r9.append(r3)
            int r2 = r2 + 1
            goto L_0x0047
        L_0x005a:
            java.lang.String r6 = "-classpath"
            r13.add(r6)
            java.lang.String r6 = r9.toString()
            java.lang.String r6 = quoteAndEscapeFilename(r6)
            r13.add(r6)
        L_0x006a:
            if (r10 != 0) goto L_0x006e
            java.lang.String r10 = "1.8"
        L_0x006e:
            java.lang.String r6 = "-source"
            r13.add(r6)
            r13.add(r10)
            java.lang.String r6 = "-target"
            r13.add(r6)
            r13.add(r10)
            if (r8 == 0) goto L_0x0083
            java.lang.String r6 = "-g"
            goto L_0x0085
        L_0x0083:
            java.lang.String r6 = "-g:none"
        L_0x0085:
            r13.add(r6)
            if (r14 == 0) goto L_0x008f
            java.lang.String r6 = "-verbose"
            r13.add(r6)
        L_0x008f:
            addAllJavaFiles(r5, r13)
            r5 = 0
            java.lang.String r6 = ""
            java.io.File r5 = java.io.File.createTempFile(r0, r6)     // Catch:{ Exception -> 0x0138 }
            java.nio.file.Path r6 = r5.toPath()     // Catch:{ Exception -> 0x0138 }
            java.nio.charset.Charset r7 = java.nio.charset.StandardCharsets.ISO_8859_1     // Catch:{ Exception -> 0x0138 }
            java.nio.file.OpenOption[] r8 = new java.nio.file.OpenOption[r1]     // Catch:{ Exception -> 0x0138 }
            java.io.BufferedWriter r6 = java.nio.file.Files.newBufferedWriter(r6, r7, r8)     // Catch:{ Exception -> 0x0138 }
            java.util.Iterator r7 = r13.iterator()     // Catch:{ all -> 0x012a }
            r7.next()     // Catch:{ all -> 0x012a }
        L_0x00ac:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x012a }
            if (r8 == 0) goto L_0x00c1
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x012a }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x012a }
            r6.write(r8)     // Catch:{ all -> 0x012a }
            r8 = 10
            r6.write(r8)     // Catch:{ all -> 0x012a }
            goto L_0x00ac
        L_0x00c1:
            if (r6 == 0) goto L_0x00c6
            r6.close()     // Catch:{ Exception -> 0x0138 }
        L_0x00c6:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0138 }
            r6.<init>()     // Catch:{ Exception -> 0x0138 }
            java.lang.Object r7 = r13.get(r1)     // Catch:{ Exception -> 0x0138 }
            r6.add(r7)     // Catch:{ Exception -> 0x0138 }
            if (r11 == 0) goto L_0x00f0
            int r7 = r11.length()     // Catch:{ Exception -> 0x0138 }
            if (r7 == 0) goto L_0x00f0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0138 }
            r7.<init>()     // Catch:{ Exception -> 0x0138 }
            java.lang.String r8 = "-J-Xms"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x0138 }
            java.lang.StringBuilder r7 = r7.append(r11)     // Catch:{ Exception -> 0x0138 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0138 }
            r6.add(r7)     // Catch:{ Exception -> 0x0138 }
        L_0x00f0:
            if (r12 == 0) goto L_0x010e
            int r7 = r12.length()     // Catch:{ Exception -> 0x0138 }
            if (r7 == 0) goto L_0x010e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0138 }
            r7.<init>()     // Catch:{ Exception -> 0x0138 }
            java.lang.String r8 = "-J-Xmx"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x0138 }
            java.lang.StringBuilder r7 = r7.append(r12)     // Catch:{ Exception -> 0x0138 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0138 }
            r6.add(r7)     // Catch:{ Exception -> 0x0138 }
        L_0x010e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0138 }
            r7.<init>()     // Catch:{ Exception -> 0x0138 }
            java.lang.String r8 = "@"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x0138 }
            java.lang.String r8 = r5.getAbsolutePath()     // Catch:{ Exception -> 0x0138 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x0138 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0138 }
            r6.add(r7)     // Catch:{ Exception -> 0x0138 }
            r13 = r6
            goto L_0x013f
        L_0x012a:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x012c }
        L_0x012c:
            r8 = move-exception
            if (r6 == 0) goto L_0x0137
            r6.close()     // Catch:{ all -> 0x0133 }
            goto L_0x0137
        L_0x0133:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch:{ Exception -> 0x0138 }
        L_0x0137:
            throw r8     // Catch:{ Exception -> 0x0138 }
        L_0x0138:
            java.io.PrintStream r6 = java.lang.System.err
            java.lang.String r7 = "Could not create command-line file for javac"
            r6.println(r7)
        L_0x013f:
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ all -> 0x01d7 }
            java.lang.Object[] r6 = r13.toArray(r6)     // Catch:{ all -> 0x01d7 }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ all -> 0x01d7 }
            if (r14 == 0) goto L_0x0176
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ all -> 0x01d7 }
            java.lang.String r8 = "compile command:"
            r7.print(r8)     // Catch:{ all -> 0x01d7 }
            int r7 = r6.length     // Catch:{ all -> 0x01d7 }
            r8 = r1
        L_0x0152:
            if (r8 >= r7) goto L_0x0171
            r9 = r6[r8]     // Catch:{ all -> 0x01d7 }
            java.io.PrintStream r10 = java.lang.System.out     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r11.<init>()     // Catch:{ all -> 0x01d7 }
            java.lang.String r12 = " "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r9 = r11.append(r9)     // Catch:{ all -> 0x01d7 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x01d7 }
            r10.print(r9)     // Catch:{ all -> 0x01d7 }
            int r8 = r8 + 1
            goto L_0x0152
        L_0x0171:
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ all -> 0x01d7 }
            r7.println()     // Catch:{ all -> 0x01d7 }
        L_0x0176:
            java.lang.Runtime r7 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x01d7 }
            java.lang.Process r6 = r7.exec(r6)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r7.<init>()     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r8.<init>()     // Catch:{ all -> 0x01d7 }
            java.io.InputStream r9 = r6.getInputStream()     // Catch:{ all -> 0x01d7 }
            copy(r9, r8)     // Catch:{ all -> 0x01d7 }
            java.io.InputStream r9 = r6.getErrorStream()     // Catch:{ all -> 0x01d7 }
            copy(r9, r7)     // Catch:{ all -> 0x01d7 }
            r6.waitFor()     // Catch:{ all -> 0x01d7 }
            if (r14 != 0) goto L_0x01a1
            int r9 = r6.exitValue()     // Catch:{ all -> 0x01d7 }
            if (r9 == 0) goto L_0x01d0
        L_0x01a1:
            int r9 = r8.length()     // Catch:{ all -> 0x01d7 }
            if (r9 <= 0) goto L_0x01b5
            java.io.PrintStream r9 = java.lang.System.out     // Catch:{ all -> 0x01d7 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01d7 }
            r9.println(r8)     // Catch:{ all -> 0x01d7 }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ all -> 0x01d7 }
            r8.flush()     // Catch:{ all -> 0x01d7 }
        L_0x01b5:
            int r8 = r7.length()     // Catch:{ all -> 0x01d7 }
            if (r8 <= 0) goto L_0x01c9
            java.io.PrintStream r8 = java.lang.System.err     // Catch:{ all -> 0x01d7 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01d7 }
            r8.println(r7)     // Catch:{ all -> 0x01d7 }
            java.io.PrintStream r7 = java.lang.System.err     // Catch:{ all -> 0x01d7 }
            r7.flush()     // Catch:{ all -> 0x01d7 }
        L_0x01c9:
            int r6 = r6.exitValue()     // Catch:{ all -> 0x01d7 }
            if (r6 == 0) goto L_0x01d0
            return r1
        L_0x01d0:
            if (r5 == 0) goto L_0x01d5
            r5.delete()
        L_0x01d5:
            r5 = 1
            return r5
        L_0x01d7:
            r5 = move-exception
            java.io.PrintStream r6 = java.lang.System.err
            java.lang.String r7 = r5.toString()
            r6.println(r7)
            java.io.PrintStream r6 = java.lang.System.err
            java.lang.Throwable r7 = r5.getCause()
            r6.println(r7)
            java.io.PrintStream r6 = java.lang.System.err
            r5.printStackTrace(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.CodeGenUtil.externalCompile(java.util.List, java.io.File, java.io.File[], boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean):boolean");
    }

    public static File[] systemClasspath() {
        ArrayList arrayList = new ArrayList();
        CodeSource codeSource = CodeGenUtil.class.getProtectionDomain().getCodeSource();
        if (codeSource != null) {
            arrayList.add(new File(codeSource.getLocation().getPath()));
        } else {
            System.err.println("Can't determine path of xmlbeans-*.jar - specify classpath explicitly!");
        }
        String property = SystemProperties.getProperty("java.class.path");
        if (property != null) {
            for (String file : property.split(File.pathSeparator)) {
                arrayList.add(new File(file));
            }
        }
        return (File[]) arrayList.toArray(new File[0]);
    }

    private static File findJavaTool(String str) {
        File file = new File(str);
        if (file.isFile()) {
            return file;
        }
        File file2 = new File(str + ".exe");
        if (file2.isFile()) {
            return file2;
        }
        String property = SystemProperties.getProperty("java.home");
        String str2 = File.separator;
        File file3 = new File(property + str2 + ".." + str2 + "bin", str);
        if (file3.isFile()) {
            return file3;
        }
        File file4 = new File(file3.getPath() + ".exe");
        if (file4.isFile()) {
            return file4;
        }
        File file5 = new File(property + str2 + "bin", str);
        if (file5.isFile()) {
            return file5;
        }
        File file6 = new File(file5.getPath() + ".exe");
        return file6.isFile() ? file6 : file;
    }

    private static Thread copy(InputStream inputStream, StringBuilder sb) {
        Thread thread = new Thread(new CodeGenUtil$$ExternalSyntheticLambda2(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1)), sb));
        thread.start();
        return thread;
    }
}
