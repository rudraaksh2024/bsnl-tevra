package org.apache.poi.util;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LittleEndianInputStream extends FilterInputStream implements LittleEndianInput {
    private static final int BUFFERED_SIZE = 8096;
    private static final int EOF = -1;
    private int markIndex;
    private int readIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LittleEndianInputStream(InputStream inputStream) {
        super(!inputStream.markSupported() ? new BufferedInputStream(inputStream, BUFFERED_SIZE) : inputStream);
        this.readIndex = 0;
        this.markIndex = -1;
    }

    public int available() {
        try {
            return super.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte readByte() {
        return (byte) readUByte();
    }

    public int readUByte() {
        byte[] bArr = new byte[1];
        try {
            checkEOF(read(bArr), 1);
            return LittleEndian.getUByte(bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public int readInt() {
        byte[] bArr = new byte[4];
        try {
            checkEOF(read(bArr), 4);
            return LittleEndian.getInt(bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long readUInt() {
        return ((long) readInt()) & 4294967295L;
    }

    public long readLong() {
        byte[] bArr = new byte[8];
        try {
            checkEOF(read(bArr), 8);
            return LittleEndian.getLong(bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public short readShort() {
        return (short) readUShort();
    }

    public int readUShort() {
        byte[] bArr = new byte[2];
        try {
            checkEOF(read(bArr), 2);
            return LittleEndian.getUShort(bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkEOF(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        if (i == -1 || i != i2) {
            throw new RuntimeException("Unexpected end-of-file");
        }
    }

    public void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr, int i, int i2) {
        try {
            checkEOF(_read(bArr, i, i2), i2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        this.readIndex += Math.max(0, read);
        return read;
    }

    public synchronized void mark(int i) {
        super.mark(i);
        this.markIndex = this.readIndex;
    }

    public synchronized void reset() throws IOException {
        super.reset();
        int i = this.markIndex;
        if (i > -1) {
            this.readIndex = i;
            this.markIndex = -1;
        }
    }

    public int getReadIndex() {
        return this.readIndex;
    }

    private int _read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2;
        while (i3 > 0) {
            int read = read(bArr, (i2 - i3) + i, i3);
            if (-1 == read) {
                break;
            }
            i3 -= read;
        }
        return i2 - i3;
    }

    public void readPlain(byte[] bArr, int i, int i2) {
        readFully(bArr, i, i2);
    }

    public void skipFully(int i) throws IOException {
        if (i != 0) {
            long skipFully = IOUtils.skipFully(this, (long) i);
            if (skipFully <= 2147483647L) {
                checkEOF((int) skipFully, i);
                return;
            }
            throw new IOException("can't skip further than 2147483647");
        }
    }
}
