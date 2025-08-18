package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.UByte;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianInput;

public final class DocumentInputStream extends InputStream implements LittleEndianInput {
    private static final int EOF = -1;
    private ByteBuffer _buffer;
    private boolean _closed;
    private int _current_block_count;
    private int _current_offset;
    private Iterator<ByteBuffer> _data;
    private final POIFSDocument _document;
    private final int _document_size;
    private int _marked_offset;
    private int _marked_offset_count;

    public boolean markSupported() {
        return true;
    }

    public DocumentInputStream(DocumentEntry documentEntry) throws IOException {
        if (documentEntry instanceof DocumentNode) {
            this._current_offset = 0;
            this._current_block_count = 0;
            this._marked_offset = 0;
            this._marked_offset_count = 0;
            this._document_size = documentEntry.getSize();
            this._closed = false;
            DocumentNode documentNode = (DocumentNode) documentEntry;
            POIFSDocument pOIFSDocument = new POIFSDocument((DocumentProperty) documentNode.getProperty(), ((DirectoryNode) documentNode.getParent()).getFileSystem());
            this._document = pOIFSDocument;
            this._data = pOIFSDocument.getBlockIterator();
            return;
        }
        throw new IOException("Cannot open internal document storage, " + documentEntry + " not a Document Node");
    }

    public DocumentInputStream(POIFSDocument pOIFSDocument) {
        this._current_offset = 0;
        this._current_block_count = 0;
        this._marked_offset = 0;
        this._marked_offset_count = 0;
        this._document_size = pOIFSDocument.getSize();
        this._closed = false;
        this._document = pOIFSDocument;
        this._data = pOIFSDocument.getBlockIterator();
    }

    public int available() {
        return remainingBytes();
    }

    private int remainingBytes() {
        if (!this._closed) {
            return this._document_size - this._current_offset;
        }
        throw new IllegalStateException("cannot perform requested operation on a closed stream");
    }

    public void close() {
        this._closed = true;
    }

    public synchronized void mark(int i) {
        this._marked_offset = this._current_offset;
        this._marked_offset_count = Math.max(0, this._current_block_count - 1);
    }

    public int read() throws IOException {
        dieIfClosed();
        if (atEOD()) {
            return -1;
        }
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & UByte.MAX_VALUE;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        dieIfClosed();
        if (bArr == null) {
            throw new IllegalArgumentException("buffer must not be null");
        } else if (i < 0 || i2 < 0 || bArr.length < i + i2) {
            throw new IndexOutOfBoundsException("can't read past buffer boundaries");
        } else if (i2 == 0) {
            return 0;
        } else {
            if (atEOD()) {
                return -1;
            }
            int min = Math.min(remainingBytes(), i2);
            readFully(bArr, i, min);
            return min;
        }
    }

    public synchronized void reset() {
        int i;
        int i2;
        int i3 = this._marked_offset;
        if (i3 == 0 && (i2 = this._marked_offset_count) == 0) {
            this._current_block_count = i2;
            this._current_offset = i3;
            this._data = this._document.getBlockIterator();
            this._buffer = null;
            return;
        }
        this._data = this._document.getBlockIterator();
        int i4 = 0;
        this._current_offset = 0;
        while (true) {
            i = this._marked_offset_count;
            if (i4 >= i) {
                break;
            }
            ByteBuffer next = this._data.next();
            this._buffer = next;
            this._current_offset += next.remaining();
            i4++;
        }
        this._current_block_count = i;
        if (this._current_offset != this._marked_offset) {
            ByteBuffer next2 = this._data.next();
            this._buffer = next2;
            this._current_block_count++;
            next2.position(next2.position() + (this._marked_offset - this._current_offset));
        }
        this._current_offset = this._marked_offset;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r1 > ((long) r6)) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long skip(long r6) throws java.io.IOException {
        /*
            r5 = this;
            r5.dieIfClosed()
            r0 = 0
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x000a
            return r0
        L_0x000a:
            int r0 = r5._current_offset
            long r1 = (long) r0
            long r1 = r1 + r6
            long r6 = (long) r0
            int r6 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x0017
            int r6 = r5._document_size
        L_0x0015:
            long r1 = (long) r6
            goto L_0x001f
        L_0x0017:
            int r6 = r5._document_size
            long r3 = (long) r6
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x001f
            goto L_0x0015
        L_0x001f:
            long r6 = (long) r0
            long r1 = r1 - r6
            r6 = 2147483647(0x7fffffff, float:NaN)
            byte[] r6 = org.apache.poi.util.IOUtils.safelyAllocate(r1, r6)
            r5.readFully(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.DocumentInputStream.skip(long):long");
    }

    private void dieIfClosed() throws IOException {
        if (this._closed) {
            throw new IOException("cannot perform requested operation on a closed stream");
        }
    }

    private boolean atEOD() {
        return this._current_offset == this._document_size;
    }

    private void checkAvaliable(int i) {
        if (this._closed) {
            throw new IllegalStateException("cannot perform requested operation on a closed stream");
        } else if (i > this._document_size - this._current_offset) {
            throw new RuntimeException("Buffer underrun - requested " + i + " bytes but " + (this._document_size - this._current_offset) + " was available");
        }
    }

    public void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            checkAvaliable(i2);
            int i3 = 0;
            while (i3 < i2) {
                ByteBuffer byteBuffer = this._buffer;
                if (byteBuffer == null || byteBuffer.remaining() == 0) {
                    this._current_block_count++;
                    this._buffer = this._data.next();
                }
                int min = Math.min(i2 - i3, this._buffer.remaining());
                this._buffer.get(bArr, i + i3, min);
                this._current_offset += min;
                i3 += min;
            }
            return;
        }
        throw new RuntimeException("Can't read negative number of bytes");
    }

    public void readPlain(byte[] bArr, int i, int i2) {
        readFully(bArr, i, i2);
    }

    public byte readByte() {
        return (byte) readUByte();
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public long readLong() {
        checkAvaliable(8);
        byte[] bArr = new byte[8];
        readFully(bArr, 0, 8);
        return LittleEndian.getLong(bArr, 0);
    }

    public short readShort() {
        checkAvaliable(2);
        byte[] bArr = new byte[2];
        readFully(bArr, 0, 2);
        return LittleEndian.getShort(bArr);
    }

    public int readInt() {
        checkAvaliable(4);
        byte[] bArr = new byte[4];
        readFully(bArr, 0, 4);
        return LittleEndian.getInt(bArr);
    }

    public long readUInt() {
        return ((long) readInt()) & 4294967295L;
    }

    public int readUShort() {
        checkAvaliable(2);
        byte[] bArr = new byte[2];
        readFully(bArr, 0, 2);
        return LittleEndian.getUShort(bArr);
    }

    public int readUByte() {
        checkAvaliable(1);
        byte[] bArr = new byte[1];
        readFully(bArr, 0, 1);
        byte b = bArr[0];
        return b >= 0 ? b : b + 256;
    }
}
