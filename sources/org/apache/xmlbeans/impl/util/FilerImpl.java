package org.apache.xmlbeans.impl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.impl.repackage.Repackager;

public class FilerImpl implements Filer {
    private static final Charset CHARSET;
    private final File classdir;
    private final boolean incrSrcGen;
    private final Repackager repackager;
    private Set<String> seenTypes;
    private final List<File> sourceFiles = new ArrayList();
    private final File srcdir;
    private final boolean verbose;

    static {
        Charset charset;
        try {
            charset = Charset.forName(System.getProperty("file.encoding"));
        } catch (Exception unused) {
            charset = null;
        }
        CHARSET = charset;
    }

    public FilerImpl(File file, File file2, Repackager repackager2, boolean z, boolean z2) {
        this.classdir = file;
        this.srcdir = file2;
        this.repackager = repackager2;
        this.verbose = z;
        this.incrSrcGen = z2;
        if (z2) {
            this.seenTypes = new HashSet();
        }
    }

    public OutputStream createBinaryFile(String str) throws IOException {
        if (this.verbose) {
            System.err.println("created binary: " + str);
        }
        File file = new File(this.classdir, str);
        file.getParentFile().mkdirs();
        return new FileOutputStream(file);
    }

    public Writer createSourceFile(String str) throws IOException {
        if (this.incrSrcGen) {
            this.seenTypes.add(str);
        }
        if (str.indexOf(36) > 0) {
            str = str.substring(0, str.lastIndexOf(46)) + "." + str.substring(str.indexOf(36) + 1);
        }
        File file = new File(this.srcdir, str.replace('.', File.separatorChar) + ".java");
        file.getParentFile().mkdirs();
        if (this.verbose) {
            System.err.println("created source: " + file.getAbsolutePath());
        }
        this.sourceFiles.add(file);
        if (!this.incrSrcGen || !file.exists()) {
            return this.repackager == null ? writerForFile(file) : new RepackagingWriter(file, this.repackager);
        }
        return new IncrFileWriter(file, this.repackager);
    }

    public List<File> getSourceFiles() {
        return new ArrayList(this.sourceFiles);
    }

    public Repackager getRepackager() {
        return this.repackager;
    }

    /* access modifiers changed from: private */
    public static Writer writerForFile(File file) throws IOException {
        Charset charset = CHARSET;
        if (charset == null) {
            return Files.newBufferedWriter(file.toPath(), StandardCharsets.ISO_8859_1, new OpenOption[0]);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        CharsetEncoder newEncoder = charset.newEncoder();
        newEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return new OutputStreamWriter(fileOutputStream, newEncoder);
    }

    static class IncrFileWriter extends StringWriter {
        private final File _file;
        private final Repackager _repackager;

        public IncrFileWriter(File file, Repackager repackager) {
            this._file = file;
            this._repackager = repackager;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
            if (r6 != null) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            r6.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
            r0.addSuppressed(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0063, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
            if (r3 != null) goto L_0x006a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x006e, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            r6.addSuppressed(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0072, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0075, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
            r2.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x007a, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x007b, code lost:
            r6.addSuppressed(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x007e, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r6 = this;
                super.close()
                org.apache.xmlbeans.impl.repackage.Repackager r0 = r6._repackager
                if (r0 == 0) goto L_0x0010
                java.lang.StringBuffer r1 = r6.getBuffer()
                java.lang.StringBuffer r0 = r0.repackage(r1)
                goto L_0x0014
            L_0x0010:
                java.lang.StringBuffer r0 = r6.getBuffer()
            L_0x0014:
                java.lang.String r0 = r0.toString()
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.io.StringReader r2 = new java.io.StringReader
                r2.<init>(r0)
                java.io.File r3 = r6._file     // Catch:{ all -> 0x0073 }
                java.nio.file.Path r3 = r3.toPath()     // Catch:{ all -> 0x0073 }
                java.nio.charset.Charset r4 = java.nio.charset.StandardCharsets.ISO_8859_1     // Catch:{ all -> 0x0073 }
                java.io.BufferedReader r3 = java.nio.file.Files.newBufferedReader(r3, r4)     // Catch:{ all -> 0x0073 }
                java.lang.String r4 = "<generated>"
                java.io.File r5 = r6._file     // Catch:{ all -> 0x0065 }
                java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0065 }
                org.apache.xmlbeans.impl.util.Diff.readersAsText(r2, r4, r3, r5, r1)     // Catch:{ all -> 0x0065 }
                if (r3 == 0) goto L_0x003e
                r3.close()     // Catch:{ all -> 0x0073 }
            L_0x003e:
                r2.close()
                int r1 = r1.size()
                if (r1 <= 0) goto L_0x0064
                java.io.File r6 = r6._file
                java.io.Writer r6 = org.apache.xmlbeans.impl.util.FilerImpl.writerForFile(r6)
                r6.write(r0)     // Catch:{ all -> 0x0056 }
                if (r6 == 0) goto L_0x0064
                r6.close()
                goto L_0x0064
            L_0x0056:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x0058 }
            L_0x0058:
                r1 = move-exception
                if (r6 == 0) goto L_0x0063
                r6.close()     // Catch:{ all -> 0x005f }
                goto L_0x0063
            L_0x005f:
                r6 = move-exception
                r0.addSuppressed(r6)
            L_0x0063:
                throw r1
            L_0x0064:
                return
            L_0x0065:
                r6 = move-exception
                throw r6     // Catch:{ all -> 0x0067 }
            L_0x0067:
                r0 = move-exception
                if (r3 == 0) goto L_0x0072
                r3.close()     // Catch:{ all -> 0x006e }
                goto L_0x0072
            L_0x006e:
                r1 = move-exception
                r6.addSuppressed(r1)     // Catch:{ all -> 0x0073 }
            L_0x0072:
                throw r0     // Catch:{ all -> 0x0073 }
            L_0x0073:
                r6 = move-exception
                throw r6     // Catch:{ all -> 0x0075 }
            L_0x0075:
                r0 = move-exception
                r2.close()     // Catch:{ all -> 0x007a }
                goto L_0x007e
            L_0x007a:
                r1 = move-exception
                r6.addSuppressed(r1)
            L_0x007e:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.util.FilerImpl.IncrFileWriter.close():void");
        }
    }

    static class RepackagingWriter extends StringWriter {
        private final File _file;
        private final Repackager _repackager;

        public RepackagingWriter(File file, Repackager repackager) {
            this._file = file;
            this._repackager = repackager;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
            r2.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
            if (r0 != null) goto L_0x0025;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r2 = this;
                super.close()
                java.io.File r0 = r2._file
                java.io.Writer r0 = org.apache.xmlbeans.impl.util.FilerImpl.writerForFile(r0)
                org.apache.xmlbeans.impl.repackage.Repackager r1 = r2._repackager     // Catch:{ all -> 0x0020 }
                java.lang.StringBuffer r2 = r2.getBuffer()     // Catch:{ all -> 0x0020 }
                java.lang.StringBuffer r2 = r1.repackage(r2)     // Catch:{ all -> 0x0020 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
                r0.write(r2)     // Catch:{ all -> 0x0020 }
                if (r0 == 0) goto L_0x001f
                r0.close()
            L_0x001f:
                return
            L_0x0020:
                r2 = move-exception
                throw r2     // Catch:{ all -> 0x0022 }
            L_0x0022:
                r1 = move-exception
                if (r0 == 0) goto L_0x002d
                r0.close()     // Catch:{ all -> 0x0029 }
                goto L_0x002d
            L_0x0029:
                r0 = move-exception
                r2.addSuppressed(r0)
            L_0x002d:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.util.FilerImpl.RepackagingWriter.close():void");
        }
    }
}
