package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repackage {
    private List<List<String>> _fromPackages;
    private List<String> _moveAlongFiles;
    private Map<String, String> _movedDirs;
    private Pattern _packagePattern;
    private final Repackager _repackager;
    private int _skippedFiles;
    private final File _sourceBase;
    private final File _targetBase;
    private List<List<String>> _toPackages;

    public static void main(String[] strArr) throws Exception {
        new Repackage(strArr).repackage();
    }

    private Repackage(String[] strArr) {
        int i;
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < strArr.length) {
            if (strArr[i2].equals("-repackage") && (i = i2 + 1) < strArr.length) {
                str = strArr[i];
            } else if (strArr[i2].equals("-f") && (i = i2 + 1) < strArr.length) {
                str2 = strArr[i];
            } else if (!strArr[i2].equals("-t") || (i = i2 + 1) >= strArr.length) {
                z2 = true;
                i2++;
            } else {
                str3 = strArr[i];
            }
            i2 = i;
            i2++;
        }
        if (!z2 && str != null) {
            if (!((str2 == null) ^ (str3 == null ? true : z))) {
                this._repackager = new Repackager(str);
                if (str2 == null || str3 == null) {
                    this._targetBase = null;
                    this._sourceBase = null;
                    return;
                }
                this._sourceBase = new File(str2);
                this._targetBase = new File(str3);
                return;
            }
        }
        throw new RuntimeException("Usage: repackage -repackage [spec] [ -f [sourcedir] -t [targetdir] ]");
    }

    public void repackage() throws Exception {
        if (this._sourceBase == null || this._targetBase == null) {
            System.out.println(this._repackager.repackage(readInputStream(System.in)).toString());
            return;
        }
        this._fromPackages = this._repackager.getFromPackages();
        this._toPackages = this._repackager.getToPackages();
        this._packagePattern = Pattern.compile("^\\s*package\\s+((?:\\w|\\.)*)\\s*;", 8);
        this._moveAlongFiles = new ArrayList();
        this._movedDirs = new HashMap();
        this._targetBase.mkdirs();
        ArrayList<File> arrayList = new ArrayList<>();
        fillFiles(arrayList, this._sourceBase);
        System.out.println("Repackaging " + arrayList.size() + " files ...");
        int length = this._sourceBase.getCanonicalPath().length();
        for (File canonicalPath : arrayList) {
            repackageFile(canonicalPath.getCanonicalPath().substring(length + 1));
        }
        finishMovingFiles();
        if (this._skippedFiles > 0) {
            System.out.println("Skipped " + this._skippedFiles + " unmodified files.");
        }
    }

    public void repackageFile(String str) throws IOException {
        if (str.endsWith(".java")) {
            repackageJavaFile(str);
        } else if (str.endsWith(".xsdconfig") || str.endsWith(".xml") || str.endsWith(".g")) {
            repackageNonJavaFile(str);
        } else if (str.startsWith("bin" + File.separatorChar)) {
            repackageNonJavaFile(str);
        } else {
            moveAlongWithJavaFiles(str);
        }
    }

    public void moveAlongWithJavaFiles(String str) {
        this._moveAlongFiles.add(str);
    }

    public void finishMovingFiles() throws IOException {
        String str;
        for (String next : this._moveAlongFiles) {
            String str2 = this._movedDirs.get(Repackager.dirForPath(next));
            if (str2 == null) {
                str = next;
            } else {
                str = new File(str2, new File(next).getName()).toString();
            }
            if (next.endsWith(".html")) {
                repackageNonJavaFile(next, str);
            } else {
                justMoveNonJavaFile(next, str);
            }
        }
    }

    public void repackageNonJavaFile(String str) throws IOException {
        File file = new File(this._sourceBase, str);
        File file2 = new File(this._targetBase, str);
        if (file.lastModified() < file2.lastModified()) {
            this._skippedFiles++;
        } else {
            writeFile(file2, this._repackager.repackage(readFile(file)));
        }
    }

    public void repackageNonJavaFile(String str, String str2) throws IOException {
        File file = new File(this._sourceBase, str);
        File file2 = new File(this._targetBase, str2);
        if (file.lastModified() < file2.lastModified()) {
            this._skippedFiles++;
        } else {
            writeFile(file2, this._repackager.repackage(readFile(file)));
        }
    }

    public void justMoveNonJavaFile(String str, String str2) throws IOException {
        File file = new File(this._sourceBase, str);
        File file2 = new File(this._targetBase, str2);
        if (file.lastModified() < file2.lastModified()) {
            this._skippedFiles++;
        } else {
            copyFile(file, file2);
        }
    }

    public void repackageJavaFile(String str) throws IOException {
        List list;
        List list2;
        String str2 = str;
        File file = new File(this._sourceBase, str2);
        StringBuffer readFile = readFile(file);
        Matcher matcher = this._packagePattern.matcher(readFile);
        int i = 1;
        if (matcher.find()) {
            String group = matcher.group(1);
            int start = matcher.start(1);
            int end = matcher.end(1);
            if (!matcher.find()) {
                List<String> splitPath = Repackager.splitPath(str2, File.separatorChar);
                String dirForPath = Repackager.dirForPath(str);
                while (true) {
                    boolean z = false;
                    for (int i2 = i; i2 < splitPath.size(); i2++) {
                        int i3 = i2 - 1;
                        String str3 = splitPath.get(i3);
                        String str4 = splitPath.get(i2);
                        if (str3.indexOf(58) < str4.indexOf(58)) {
                            splitPath.set(i3, str4);
                            splitPath.set(i2, str3);
                            z = true;
                        }
                    }
                    if (!z) {
                        break;
                    }
                    i = 1;
                }
                List<String> splitPath2 = Repackager.splitPath(group, '.');
                int size = splitPath.size() - 2;
                if (size < 0 || splitPath.size() - 1 < splitPath2.size()) {
                    throw new RuntimeException("Package spec differs from file path: " + str2);
                }
                int size2 = splitPath2.size() - 1;
                while (size2 >= 0) {
                    if (splitPath2.get(size2).equals(splitPath.get(size))) {
                        size--;
                        size2--;
                    } else {
                        throw new RuntimeException("Package spec differs from file path: " + str2);
                    }
                }
                int i4 = 0;
                loop3:
                while (true) {
                    if (i4 >= this._fromPackages.size()) {
                        list = null;
                        list2 = null;
                        break;
                    }
                    list = this._fromPackages.get(i4);
                    if (list.size() <= splitPath2.size()) {
                        int i5 = 0;
                        while (i5 < list.size()) {
                            if (((String) list.get(i5)).equals(splitPath2.get(i5))) {
                                i5++;
                            }
                        }
                        list2 = this._toPackages.get(i4);
                        break loop3;
                    }
                    i4++;
                }
                if (list2 != null) {
                    String str5 = "";
                    String str6 = str5;
                    for (int i6 = 0; i6 < list2.size(); i6++) {
                        if (i6 > 0) {
                            str5 = str5 + ".";
                            str6 = str6 + File.separatorChar;
                        }
                        str5 = str5 + ((String) list2.get(i6));
                        str6 = str6 + ((String) list2.get(i6));
                    }
                    for (int size3 = (splitPath.size() - splitPath2.size()) - 2; size3 >= 0; size3--) {
                        str6 = splitPath.get(size3) + File.separatorChar + str6;
                    }
                    for (int size4 = list.size(); size4 < splitPath2.size(); size4++) {
                        str6 = str6 + File.separatorChar + splitPath2.get(size4);
                        str5 = str5 + '.' + splitPath2.get(size4);
                    }
                    String str7 = str6 + File.separatorChar + splitPath.get(splitPath.size() - 1);
                    readFile.replace(start, end, str5);
                    String dirForPath2 = Repackager.dirForPath(str7);
                    if (!dirForPath.equals(dirForPath2)) {
                        this._movedDirs.put(dirForPath, dirForPath2);
                    }
                    str2 = str7;
                }
            } else {
                throw new RuntimeException("Two package specifications found: " + str2);
            }
        }
        if (file.lastModified() < new File(this._targetBase, str2).lastModified()) {
            this._skippedFiles++;
        } else {
            writeFile(new File(this._targetBase, str2), this._repackager.repackage(readFile));
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0038, code lost:
        if (r1 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003f, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeFile(java.io.File r2, java.lang.StringBuffer r3) throws java.io.IOException {
        /*
            r1 = this;
            java.io.File r1 = r2.getParentFile()
            r1.mkdirs()
            java.nio.file.Path r1 = r2.toPath()
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.ISO_8859_1
            r0 = 0
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.io.BufferedWriter r1 = java.nio.file.Files.newBufferedWriter(r1, r2, r0)
            java.io.StringReader r2 = new java.io.StringReader     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0035 }
            r2.<init>(r3)     // Catch:{ all -> 0x0035 }
            copy((java.io.Reader) r2, (java.io.Writer) r1)     // Catch:{ all -> 0x0029 }
            r2.close()     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x0028
            r1.close()
        L_0x0028:
            return
        L_0x0029:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002b }
        L_0x002b:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ all -> 0x0035 }
        L_0x0034:
            throw r0     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r3 = move-exception
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x0042:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.repackage.Repackage.writeFile(java.io.File, java.lang.StringBuffer):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002e, code lost:
        if (r2 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0034, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0035, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0038, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer readFile(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            java.nio.file.Path r2 = r3.toPath()
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.ISO_8859_1
            java.io.BufferedReader r2 = java.nio.file.Files.newBufferedReader(r2, r3)
            java.io.StringWriter r3 = new java.io.StringWriter     // Catch:{ all -> 0x002b }
            r3.<init>()     // Catch:{ all -> 0x002b }
            copy((java.io.Reader) r2, (java.io.Writer) r3)     // Catch:{ all -> 0x001f }
            java.lang.StringBuffer r0 = r3.getBuffer()     // Catch:{ all -> 0x001f }
            r3.close()     // Catch:{ all -> 0x002b }
            if (r2 == 0) goto L_0x001e
            r2.close()
        L_0x001e:
            return r0
        L_0x001f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch:{ all -> 0x002b }
        L_0x002a:
            throw r1     // Catch:{ all -> 0x002b }
        L_0x002b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002d }
        L_0x002d:
            r0 = move-exception
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0038:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.repackage.Repackage.readFile(java.io.File):java.lang.StringBuffer");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0025, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0028, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002e, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0031, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer readInputStream(java.io.InputStream r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.ISO_8859_1
            r2.<init>(r3, r0)
            java.io.StringWriter r3 = new java.io.StringWriter     // Catch:{ all -> 0x0026 }
            r3.<init>()     // Catch:{ all -> 0x0026 }
            copy((java.io.Reader) r2, (java.io.Writer) r3)     // Catch:{ all -> 0x001a }
            java.lang.StringBuffer r0 = r3.getBuffer()     // Catch:{ all -> 0x001a }
            r3.close()     // Catch:{ all -> 0x0026 }
            r2.close()
            return r0
        L_0x001a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001c }
        L_0x001c:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch:{ all -> 0x0026 }
        L_0x0025:
            throw r1     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0031:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.repackage.Repackage.readInputStream(java.io.InputStream):java.lang.StringBuffer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002f, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0032, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(java.io.File r2, java.io.File r3) throws java.io.IOException {
        /*
            java.io.File r0 = r3.getParentFile()
            r0.mkdirs()
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0027 }
            r2.<init>(r3)     // Catch:{ all -> 0x0027 }
            copy((java.io.InputStream) r0, (java.io.OutputStream) r2)     // Catch:{ all -> 0x001b }
            r2.close()     // Catch:{ all -> 0x0027 }
            r0.close()
            return
        L_0x001b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001d }
        L_0x001d:
            r1 = move-exception
            r2.close()     // Catch:{ all -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ all -> 0x0027 }
        L_0x0026:
            throw r1     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0032:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.repackage.Repackage.copyFile(java.io.File, java.io.File):void");
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[16384];
        while (true) {
            int read = inputStream.read(bArr, 0, 16384);
            if (read >= 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void copy(Reader reader, Writer writer) throws IOException {
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

    public void fillFiles(List<File> list, File file) {
        if (!file.isDirectory()) {
            list.add(file);
        } else if (!file.getName().equals("build") && !file.getName().equals("CVS")) {
            String[] list2 = file.list();
            if (list2 != null) {
                for (String file2 : list2) {
                    fillFiles(list, new File(file, file2));
                }
                return;
            }
            throw new RuntimeException("Directory can't be accessed: " + file.toString());
        }
    }

    public void recursiveDelete(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                String[] list = file.list();
                if (list != null) {
                    for (String file2 : list) {
                        recursiveDelete(new File(file, file2));
                    }
                } else {
                    throw new RuntimeException("Directory can't be accessed: " + file.toString());
                }
            }
            file.delete();
        }
    }
}
