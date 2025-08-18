package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.UByte;
import org.apache.poi.ss.formula.ptg.UnionPtg;

@Internal
public final class LittleEndian implements LittleEndianConsts {
    public static int ubyteToInt(byte b) {
        return b & UByte.MAX_VALUE;
    }

    public static final class BufferUnderrunException extends IOException {
        private static final long serialVersionUID = 8736973884877006145L;

        BufferUnderrunException() {
            super("buffer underrun");
        }
    }

    public static double getDouble(byte[] bArr) {
        return Double.longBitsToDouble(getLong(bArr, 0));
    }

    public static double getDouble(byte[] bArr, int i) {
        return Double.longBitsToDouble(getLong(bArr, i));
    }

    public static float getFloat(byte[] bArr) {
        return getFloat(bArr, 0);
    }

    public static float getFloat(byte[] bArr, int i) {
        return Float.intBitsToFloat(getInt(bArr, i));
    }

    public static int getInt(byte[] bArr) {
        return getInt(bArr, 0);
    }

    public static int getInt(byte[] bArr, int i) {
        int i2 = i + 1;
        byte b = bArr[i] & UByte.MAX_VALUE;
        int i3 = i2 + 1;
        byte b2 = bArr[i2] & UByte.MAX_VALUE;
        return ((bArr[i3 + 1] & UByte.MAX_VALUE) << 24) + ((bArr[i3] & UByte.MAX_VALUE) << UnionPtg.sid) + (b2 << 8) + b;
    }

    public static long getLong(byte[] bArr) {
        return getLong(bArr, 0);
    }

    public static long getLong(byte[] bArr, int i) {
        long j = (long) (bArr[i + 7] & UByte.MAX_VALUE);
        for (int i2 = (i + 8) - 1; i2 >= i; i2--) {
            j = (j << 8) | ((long) (bArr[i2] & UByte.MAX_VALUE));
        }
        return j;
    }

    public static short getShort(byte[] bArr) {
        return getShort(bArr, 0);
    }

    public static short getShort(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & UByte.MAX_VALUE) << 8) + (bArr[i] & UByte.MAX_VALUE));
    }

    public static short[] getShortArray(byte[] bArr, int i, int i2) {
        int i3 = i2 / 2;
        short[] sArr = new short[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            sArr[i4] = getShort(bArr, (i4 * 2) + i);
        }
        return sArr;
    }

    public static short getUByte(byte[] bArr) {
        return (short) (bArr[0] & UByte.MAX_VALUE);
    }

    public static short getUByte(byte[] bArr, int i) {
        return (short) (bArr[i] & UByte.MAX_VALUE);
    }

    public static long getUInt(byte[] bArr) {
        return getUInt(bArr, 0);
    }

    public static long getUInt(byte[] bArr, int i) {
        return ((long) getInt(bArr, i)) & 4294967295L;
    }

    public static int getUShort(byte[] bArr) {
        return getUShort(bArr, 0);
    }

    public static int getUShort(byte[] bArr, int i) {
        return ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + (bArr[i] & UByte.MAX_VALUE);
    }

    public static void putByte(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) i2;
    }

    public static void putDouble(byte[] bArr, int i, double d) {
        putLong(bArr, i, Double.doubleToLongBits(d));
    }

    public static void putDouble(double d, OutputStream outputStream) throws IOException {
        putLong(Double.doubleToLongBits(d), outputStream);
    }

    public static void putFloat(byte[] bArr, int i, float f) {
        putInt(bArr, i, Float.floatToIntBits(f));
    }

    public static void putFloat(float f, OutputStream outputStream) throws IOException {
        putInt(Float.floatToIntBits(f), outputStream);
    }

    public static void putInt(byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        bArr[i] = (byte) (i2 & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        bArr[i4 + 1] = (byte) ((i2 >>> 24) & 255);
    }

    public static void putInt(int i, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (i & 255));
        outputStream.write((byte) ((i >>> 8) & 255));
        outputStream.write((byte) ((i >>> 16) & 255));
        outputStream.write((byte) ((i >>> 24) & 255));
    }

    public static void putLong(byte[] bArr, int i, long j) {
        bArr[i] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >>> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >>> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >>> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >>> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >>> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >>> 56) & 255));
    }

    public static void putLong(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) ((int) (j & 255)));
        outputStream.write((byte) ((int) ((j >>> 8) & 255)));
        outputStream.write((byte) ((int) ((j >>> 16) & 255)));
        outputStream.write((byte) ((int) ((j >>> 24) & 255)));
        outputStream.write((byte) ((int) ((j >>> 32) & 255)));
        outputStream.write((byte) ((int) ((j >>> 40) & 255)));
        outputStream.write((byte) ((int) ((j >>> 48) & 255)));
        outputStream.write((byte) ((int) ((j >>> 56) & 255)));
    }

    public static void putShort(byte[] bArr, int i, short s) {
        bArr[i] = (byte) (s & 255);
        bArr[i + 1] = (byte) ((s >>> 8) & 255);
    }

    public static void putShort(OutputStream outputStream, short s) throws IOException {
        outputStream.write((byte) (s & 255));
        outputStream.write((byte) ((s >>> 8) & 255));
    }

    public static void putShortArray(byte[] bArr, int i, short[] sArr) {
        for (short putShort : sArr) {
            putShort(bArr, i, putShort);
            i += 2;
        }
    }

    public static void putUByte(byte[] bArr, int i, short s) {
        bArr[i] = (byte) (s & 255);
    }

    public static void putUInt(byte[] bArr, int i, long j) {
        int i2 = i + 1;
        bArr[i] = (byte) ((int) (j & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i3] = (byte) ((int) ((j >>> 16) & 255));
        bArr[i3 + 1] = (byte) ((int) ((j >>> 24) & 255));
    }

    public static void putUInt(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) ((int) (j & 255)));
        outputStream.write((byte) ((int) ((j >>> 8) & 255)));
        outputStream.write((byte) ((int) ((j >>> 16) & 255)));
        outputStream.write((byte) ((int) ((j >>> 24) & 255)));
    }

    public static void putUShort(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >>> 8) & 255);
    }

    public static void putUShort(int i, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (i & 255));
        outputStream.write((byte) ((i >>> 8) & 255));
    }

    public static int readInt(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if ((read | read2 | read3 | read4) >= 0) {
            return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
        }
        throw new BufferUnderrunException();
    }

    public static long readUInt(InputStream inputStream) throws IOException {
        return ((long) readInt(inputStream)) & 4294967295L;
    }

    public static long readLong(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        int read5 = inputStream.read();
        int read6 = inputStream.read();
        int read7 = inputStream.read();
        int read8 = inputStream.read();
        if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
            return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + ((long) (read3 << 16)) + ((long) (read2 << 8)) + ((long) read);
        }
        throw new BufferUnderrunException();
    }

    public static short readShort(InputStream inputStream) throws IOException {
        return (short) readUShort(inputStream);
    }

    public static int readUShort(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if ((read | read2) >= 0) {
            return (read2 << 8) + read;
        }
        throw new BufferUnderrunException();
    }

    private LittleEndian() {
    }
}
