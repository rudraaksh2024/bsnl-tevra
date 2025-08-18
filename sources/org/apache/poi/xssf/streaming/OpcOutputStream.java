package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.poi.xssf.streaming.Zip64Impl;

class OpcOutputStream extends DeflaterOutputStream {
    private final CRC32 crc = new CRC32();
    private Zip64Impl.Entry current;
    private final List<Zip64Impl.Entry> entries = new ArrayList();
    private boolean finished = false;
    private final Zip64Impl spec;
    private int written = 0;

    public OpcOutputStream(OutputStream outputStream) {
        super(outputStream, new Deflater(-1, true));
        this.spec = new Zip64Impl(outputStream);
    }

    public void setLevel(int i) {
        this.def.setLevel(i);
    }

    public void putNextEntry(String str) throws IOException {
        if (this.current != null) {
            closeEntry();
        }
        Zip64Impl.Entry entry = new Zip64Impl.Entry(str);
        this.current = entry;
        entry.offset = this.written;
        this.written += this.spec.writeLFH(this.current);
        this.entries.add(this.current);
    }

    public void closeEntry() throws IOException {
        if (this.current != null) {
            this.def.finish();
            while (!this.def.finished()) {
                deflate();
            }
            this.current.size = this.def.getBytesRead();
            this.current.compressedSize = Math.toIntExact(this.def.getBytesWritten());
            this.current.crc = this.crc.getValue();
            int i = this.written + this.current.compressedSize;
            this.written = i;
            this.written = i + this.spec.writeDAT(this.current);
            this.current = null;
            this.def.reset();
            this.crc.reset();
            return;
        }
        throw new IllegalStateException("not current zip current");
    }

    public void finish() throws IOException {
        if (!this.finished) {
            if (this.current != null) {
                closeEntry();
            }
            int i = this.written;
            for (Zip64Impl.Entry writeCEN : this.entries) {
                this.written += this.spec.writeCEN(writeCEN);
            }
            this.written += this.spec.writeEND(this.entries.size(), i, this.written - i);
            this.finished = true;
        }
    }

    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        if (i >= 0 && i2 >= 0) {
            if (i <= bArr.length - i2) {
                if (i2 != 0) {
                    super.write(bArr, i, i2);
                    this.crc.update(bArr, i, i2);
                    return;
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public void close() throws IOException {
        finish();
        this.out.close();
    }
}
