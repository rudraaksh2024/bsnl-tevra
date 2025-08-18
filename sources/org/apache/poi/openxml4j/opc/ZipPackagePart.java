package org.apache.poi.openxml4j.opc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.util.NotImplemented;

public class ZipPackagePart extends PackagePart {
    private ZipArchiveEntry zipEntry;

    /* access modifiers changed from: protected */
    public OutputStream getOutputStreamImpl() {
        return null;
    }

    public ZipPackagePart(OPCPackage oPCPackage, ZipArchiveEntry zipArchiveEntry, PackagePartName packagePartName, String str) throws InvalidFormatException {
        this(oPCPackage, zipArchiveEntry, packagePartName, str, true);
    }

    ZipPackagePart(OPCPackage oPCPackage, ZipArchiveEntry zipArchiveEntry, PackagePartName packagePartName, String str, boolean z) throws InvalidFormatException {
        super(oPCPackage, packagePartName, new ContentType(str), z);
        this.zipEntry = zipArchiveEntry;
    }

    public ZipArchiveEntry getZipArchive() {
        return this.zipEntry;
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStreamImpl() throws IOException {
        return ((ZipPackage) this._container).getZipArchive().getInputStream(this.zipEntry);
    }

    public long getSize() {
        return this.zipEntry.getSize();
    }

    public boolean save(OutputStream outputStream) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, outputStream);
    }

    @NotImplemented
    public boolean load(InputStream inputStream) {
        throw new InvalidOperationException("Method not implemented !");
    }

    @NotImplemented
    public void close() {
        throw new InvalidOperationException("Method not implemented !");
    }

    @NotImplemented
    public void flush() {
        throw new InvalidOperationException("Method not implemented !");
    }
}
