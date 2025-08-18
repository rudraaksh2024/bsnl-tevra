package org.apache.poi.poifs.nio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.poi.util.IOUtils;

public class ByteArrayBackedDataSource extends DataSource {
    private static final int MAX_RECORD_LENGTH = Integer.MAX_VALUE;
    private byte[] buffer;
    private long size;

    public ByteArrayBackedDataSource(byte[] bArr, int i) {
        this.buffer = bArr;
        this.size = (long) i;
    }

    public ByteArrayBackedDataSource(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ByteBuffer read(int i, long j) {
        long j2 = this.size;
        if (j < j2) {
            return ByteBuffer.wrap(this.buffer, (int) j, (int) Math.min((long) i, j2 - j));
        }
        throw new IndexOutOfBoundsException("Unable to read " + i + " bytes from " + j + " in stream of length " + this.size);
    }

    public void write(ByteBuffer byteBuffer, long j) {
        long capacity = ((long) byteBuffer.capacity()) + j;
        if (capacity > ((long) this.buffer.length)) {
            extend(capacity);
        }
        byteBuffer.get(this.buffer, (int) j, byteBuffer.capacity());
        if (capacity > this.size) {
            this.size = capacity;
        }
    }

    private void extend(long j) {
        byte[] bArr = this.buffer;
        long length = j - ((long) bArr.length);
        if (((double) length) < ((double) bArr.length) * 0.25d) {
            length = (long) (((double) bArr.length) * 0.25d);
        }
        if (length < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            length = 4096;
        }
        byte[] safelyAllocate = IOUtils.safelyAllocate(length + ((long) bArr.length), Integer.MAX_VALUE);
        System.arraycopy(this.buffer, 0, safelyAllocate, 0, (int) this.size);
        this.buffer = safelyAllocate;
    }

    public void copyTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.buffer, 0, (int) this.size);
    }

    public long size() {
        return this.size;
    }

    public void close() {
        this.buffer = null;
        this.size = -1;
    }
}
