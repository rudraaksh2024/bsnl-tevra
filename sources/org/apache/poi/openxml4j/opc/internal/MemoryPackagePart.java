package org.apache.poi.openxml4j.opc.internal;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;

public final class MemoryPackagePart extends PackagePart {
    protected byte[] data;

    public void close() {
    }

    public void flush() {
    }

    public MemoryPackagePart(OPCPackage oPCPackage, PackagePartName packagePartName, String str) throws InvalidFormatException {
        this(oPCPackage, packagePartName, str, true);
    }

    public MemoryPackagePart(OPCPackage oPCPackage, PackagePartName packagePartName, String str, boolean z) throws InvalidFormatException {
        super(oPCPackage, packagePartName, new ContentType(str), z);
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStreamImpl() {
        if (this.data == null) {
            this.data = new byte[0];
        }
        return new UnsynchronizedByteArrayInputStream(this.data);
    }

    /* access modifiers changed from: protected */
    public OutputStream getOutputStreamImpl() {
        return new MemoryPackagePartOutputStream(this);
    }

    public long getSize() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return 0;
        }
        return (long) bArr.length;
    }

    public void clear() {
        this.data = null;
    }

    public boolean save(OutputStream outputStream) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, outputStream);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001e, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean load(java.io.InputStream r2) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r1 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ IOException -> 0x001f }
            r0.<init>()     // Catch:{ IOException -> 0x001f }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r2, (java.io.OutputStream) r0)     // Catch:{ all -> 0x0013 }
            byte[] r2 = r0.toByteArray()     // Catch:{ all -> 0x0013 }
            r1.data = r2     // Catch:{ all -> 0x0013 }
            r0.close()     // Catch:{ IOException -> 0x001f }
            r1 = 1
            return r1
        L_0x0013:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException -> 0x001f }
        L_0x001e:
            throw r2     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            r1 = move-exception
            org.apache.poi.openxml4j.exceptions.InvalidFormatException r2 = new org.apache.poi.openxml4j.exceptions.InvalidFormatException
            java.lang.String r1 = r1.getMessage()
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.MemoryPackagePart.load(java.io.InputStream):boolean");
    }
}
