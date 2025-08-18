package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

class OpcZipArchiveOutputStream extends ZipArchiveOutputStream {
    private final OpcOutputStream out;

    OpcZipArchiveOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.out = new OpcOutputStream(outputStream);
    }

    public void setLevel(int i) {
        this.out.setLevel(i);
    }

    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        this.out.putNextEntry(archiveEntry.getName());
    }

    public void closeArchiveEntry() throws IOException {
        this.out.closeEntry();
    }

    public void finish() throws IOException {
        this.out.finish();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void close() throws IOException {
        this.out.close();
    }

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
    }
}
