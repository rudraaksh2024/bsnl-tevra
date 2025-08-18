package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

public final class MemoryPackagePartOutputStream extends OutputStream {
    private final UnsynchronizedByteArrayOutputStream _buff = new UnsynchronizedByteArrayOutputStream();
    private final MemoryPackagePart _part;

    public MemoryPackagePartOutputStream(MemoryPackagePart memoryPackagePart) {
        this._part = memoryPackagePart;
    }

    public void write(int i) {
        this._buff.write(i);
    }

    public void close() throws IOException {
        flush();
    }

    public void flush() throws IOException {
        this._buff.flush();
        if (this._part.data != null) {
            byte[] bArr = new byte[(this._part.data.length + this._buff.size())];
            System.arraycopy(this._part.data, 0, bArr, 0, this._part.data.length);
            byte[] byteArray = this._buff.toByteArray();
            System.arraycopy(byteArray, 0, bArr, this._part.data.length, byteArray.length);
            this._part.data = bArr;
        } else {
            this._part.data = this._buff.toByteArray();
        }
        this._buff.reset();
    }

    public void write(byte[] bArr, int i, int i2) {
        this._buff.write(bArr, i, i2);
    }

    public void write(byte[] bArr) throws IOException {
        this._buff.write(bArr);
    }
}
