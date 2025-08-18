package org.apache.commons.compress.compressors.brotli;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.brotli.dec.BrotliInputStream;

public class BrotliCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private final CountingInputStream countingStream;
    private final BrotliInputStream decIS;

    public BrotliCompressorInputStream(InputStream inputStream) throws IOException {
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        this.decIS = new BrotliInputStream(countingInputStream);
    }

    public int available() throws IOException {
        return this.decIS.available();
    }

    public void close() throws IOException {
        this.decIS.close();
    }

    public int read(byte[] bArr) throws IOException {
        return this.decIS.read(bArr);
    }

    public long skip(long j) throws IOException {
        return IOUtils.skip(this.decIS, j);
    }

    public synchronized void mark(int i) {
        this.decIS.mark(i);
    }

    public boolean markSupported() {
        return this.decIS.markSupported();
    }

    public int read() throws IOException {
        int read = this.decIS.read();
        count(read == -1 ? 0 : 1);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.decIS.read(bArr, i, i2);
        count(read);
        return read;
    }

    public String toString() {
        return this.decIS.toString();
    }

    public synchronized void reset() throws IOException {
        this.decIS.reset();
    }

    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }
}
