package org.apache.poi.openxml4j.opc.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.util.TempFile;

public final class TempFilePackagePart extends PackagePart {
    private static final Logger LOG = LogManager.getLogger((Class<?>) TempFilePackagePart.class);
    private File tempFile;

    public void flush() {
    }

    public TempFilePackagePart(OPCPackage oPCPackage, PackagePartName packagePartName, String str) throws InvalidFormatException, IOException {
        this(oPCPackage, packagePartName, str, true);
    }

    public TempFilePackagePart(OPCPackage oPCPackage, PackagePartName packagePartName, String str, boolean z) throws InvalidFormatException, IOException {
        super(oPCPackage, packagePartName, new ContentType(str), z);
        this.tempFile = TempFile.createTempFile("poi-package-part", ".tmp");
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStreamImpl() throws IOException {
        return new FileInputStream(this.tempFile);
    }

    /* access modifiers changed from: protected */
    public OutputStream getOutputStreamImpl() throws IOException {
        return new FileOutputStream(this.tempFile);
    }

    public long getSize() {
        return this.tempFile.length();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r2 = this;
            java.io.OutputStream r2 = r2.getOutputStreamImpl()     // Catch:{ IOException -> 0x001e }
            r0 = 0
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0010 }
            r2.write(r0)     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x002a
            r2.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x002a
        L_0x0010:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r1 = move-exception
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException -> 0x001e }
        L_0x001d:
            throw r1     // Catch:{ IOException -> 0x001e }
        L_0x001e:
            r2 = move-exception
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            java.lang.String r1 = "Failed to clear data in temp file"
            r0.log((java.lang.String) r1, (java.lang.Object) r2)
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.TempFilePackagePart.clear():void");
    }

    public boolean save(OutputStream outputStream) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, outputStream);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        if (r1 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean load(java.io.InputStream r2) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r1 = this;
            java.io.OutputStream r1 = r1.getOutputStreamImpl()     // Catch:{ IOException -> 0x001c }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r2, (java.io.OutputStream) r1)     // Catch:{ all -> 0x000e }
            if (r1 == 0) goto L_0x000c
            r1.close()     // Catch:{ IOException -> 0x001c }
        L_0x000c:
            r1 = 1
            return r1
        L_0x000e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r0 = move-exception
            if (r1 == 0) goto L_0x001b
            r1.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ IOException -> 0x001c }
        L_0x001b:
            throw r0     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            r1 = move-exception
            org.apache.poi.openxml4j.exceptions.InvalidFormatException r2 = new org.apache.poi.openxml4j.exceptions.InvalidFormatException
            java.lang.String r0 = r1.getMessage()
            r2.<init>(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.TempFilePackagePart.load(java.io.InputStream):boolean");
    }

    public void close() {
        if (!this.tempFile.delete()) {
            LOG.atInfo().log("Failed to delete temp file; may already have been closed and deleted");
        }
    }
}
