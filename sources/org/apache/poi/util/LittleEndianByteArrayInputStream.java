package org.apache.poi.util;

import java.io.ByteArrayInputStream;
import kotlin.UByte;

public class LittleEndianByteArrayInputStream extends ByteArrayInputStream implements LittleEndianInput {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public LittleEndianByteArrayInputStream(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
    }

    public LittleEndianByteArrayInputStream(byte[] bArr, int i) {
        this(bArr, i, bArr.length - i);
    }

    public LittleEndianByteArrayInputStream(byte[] bArr) {
        super(bArr);
    }

    /* access modifiers changed from: protected */
    public void checkPosition(int i) {
        if (i > this.count - this.pos) {
            throw new RuntimeException("Buffer overrun, having " + this.count + " bytes in the stream and position is at " + this.pos + ", but trying to increment position by " + i);
        }
    }

    public int getReadIndex() {
        return this.pos;
    }

    public void setReadIndex(int i) {
        if (i < 0 || i >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        this.pos = i;
    }

    public byte readByte() {
        checkPosition(1);
        return (byte) read();
    }

    public int readInt() {
        checkPosition(4);
        int i = LittleEndian.getInt(this.buf, this.pos);
        super.skip(4);
        return i;
    }

    public long readLong() {
        checkPosition(8);
        long j = LittleEndian.getLong(this.buf, this.pos);
        super.skip(8);
        return j;
    }

    public short readShort() {
        checkPosition(2);
        short s = LittleEndian.getShort(this.buf, this.pos);
        super.skip(2);
        return s;
    }

    public int readUByte() {
        return readByte() & UByte.MAX_VALUE;
    }

    public int readUShort() {
        return readShort() & 65535;
    }

    public long readUInt() {
        return ((long) readInt()) & 4294967295L;
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public void readFully(byte[] bArr, int i, int i2) {
        checkPosition(i2);
        read(bArr, i, i2);
    }

    public void readFully(byte[] bArr) {
        checkPosition(bArr.length);
        read(bArr, 0, bArr.length);
    }

    public void readPlain(byte[] bArr, int i, int i2) {
        readFully(bArr, i, i2);
    }

    public void limit(int i) {
        this.count = Math.min(i, this.buf.length);
    }
}
