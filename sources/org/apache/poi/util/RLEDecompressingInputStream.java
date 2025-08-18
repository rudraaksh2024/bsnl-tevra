package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import kotlin.UByte;

public class RLEDecompressingInputStream extends InputStream {
    private static final int[] POWER2 = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
    private final byte[] buf = new byte[4096];
    private final InputStream in;
    private int len;
    private int pos = 0;

    public RLEDecompressingInputStream(InputStream inputStream) throws IOException {
        this.in = inputStream;
        int read = inputStream.read();
        if (read == 1) {
            this.len = readChunk();
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Header byte 0x01 expected, received 0x%02X", new Object[]{Integer.valueOf(read & 255)}));
    }

    public int read() throws IOException {
        int i = this.len;
        if (i == -1) {
            return -1;
        }
        if (this.pos >= i) {
            int readChunk = readChunk();
            this.len = readChunk;
            if (readChunk == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buf;
        int i2 = this.pos;
        this.pos = i2 + 1;
        return bArr[i2] & UByte.MAX_VALUE;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.len == -1) {
            return -1;
        }
        int i3 = i;
        int i4 = i2;
        while (i4 > 0) {
            if (this.pos >= this.len) {
                int readChunk = readChunk();
                this.len = readChunk;
                if (readChunk == -1) {
                    if (i3 > i) {
                        return i3 - i;
                    }
                    return -1;
                }
            }
            int min = Math.min(i4, this.len - this.pos);
            System.arraycopy(this.buf, this.pos, bArr, i3, min);
            this.pos += min;
            i4 -= min;
            i3 += min;
        }
        return i2;
    }

    public long skip(long j) throws IOException {
        long j2 = j;
        while (j2 > 0) {
            if (this.pos >= this.len) {
                int readChunk = readChunk();
                this.len = readChunk;
                if (readChunk == -1) {
                    return -1;
                }
            }
            int min = (int) Math.min(j, ((long) this.len) - ((long) this.pos));
            this.pos += min;
            j2 -= (long) min;
        }
        return j;
    }

    public int available() {
        int i = this.len;
        if (i > 0) {
            return i - this.pos;
        }
        return 0;
    }

    public void close() throws IOException {
        this.in.close();
    }

    private int readChunk() throws IOException {
        this.pos = 0;
        int readShort = readShort(this.in);
        if (readShort == -1 || readShort == 0) {
            return -1;
        }
        int i = (readShort & 4095) + 1;
        if ((readShort & 28672) == 12288) {
            if (!((readShort & 32768) == 0)) {
                int i2 = 0;
                int i3 = 0;
                while (i2 < i) {
                    int read = this.in.read();
                    i2++;
                    if (read == -1) {
                        break;
                    }
                    for (int i4 = 0; i4 < 8 && i2 < i; i4++) {
                        int[] iArr = POWER2;
                        if ((iArr[i4] & read) == 0) {
                            int read2 = this.in.read();
                            if (read2 == -1) {
                                return -1;
                            }
                            this.buf[i3] = (byte) read2;
                            i2++;
                            i3++;
                        } else {
                            int readShort2 = readShort(this.in);
                            if (readShort2 == -1) {
                                return -1;
                            }
                            i2 += 2;
                            int copyLenBits = getCopyLenBits(i3 - 1);
                            int i5 = i3 - ((readShort2 >> copyLenBits) + 1);
                            int i6 = ((iArr[copyLenBits] - 1) & readShort2) + 3 + i5;
                            while (i5 < i6) {
                                byte[] bArr = this.buf;
                                bArr[i3] = bArr[i5];
                                i5++;
                                i3++;
                            }
                        }
                    }
                }
                return i3;
            } else if (IOUtils.readFully(this.in, this.buf, 0, i) >= i) {
                return i;
            } else {
                throw new IllegalStateException(String.format(Locale.ROOT, "Not enough bytes read, expected %d", new Object[]{Integer.valueOf(i)}));
            }
        } else {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Chunksize header A should be 0x3000, received 0x%04X", new Object[]{Integer.valueOf(readShort & 57344)}));
        }
    }

    static int getCopyLenBits(int i) {
        for (int i2 = 11; i2 >= 4; i2--) {
            if ((POWER2[i2] & i) != 0) {
                return 15 - i2;
            }
        }
        return 12;
    }

    public int readShort() throws IOException {
        return readShort(this);
    }

    public int readInt() throws IOException {
        return readInt(this);
    }

    private int readShort(InputStream inputStream) throws IOException {
        int read;
        int read2 = inputStream.read();
        if (read2 == -1 || (read = inputStream.read()) == -1) {
            return -1;
        }
        return (read2 & 255) | ((read & 255) << 8);
    }

    private int readInt(InputStream inputStream) throws IOException {
        int read;
        int read2;
        int read3;
        int read4 = inputStream.read();
        if (read4 == -1 || (read = inputStream.read()) == -1 || (read2 = inputStream.read()) == -1 || (read3 = inputStream.read()) == -1) {
            return -1;
        }
        return (read4 & 255) | ((read & 255) << 8) | ((read2 & 255) << 16) | ((read3 & 255) << 24);
    }

    public static byte[] decompress(byte[] bArr) throws IOException {
        return decompress(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002b, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0033, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0037, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x003a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x003f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0040, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0043, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decompress(byte[] r2, int r3, int r4) throws java.io.IOException {
        /*
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            org.apache.commons.io.input.UnsynchronizedByteArrayInputStream r1 = new org.apache.commons.io.input.UnsynchronizedByteArrayInputStream     // Catch:{ all -> 0x0038 }
            r1.<init>(r2, r3, r4)     // Catch:{ all -> 0x0038 }
            org.apache.poi.util.RLEDecompressingInputStream r2 = new org.apache.poi.util.RLEDecompressingInputStream     // Catch:{ all -> 0x002c }
            r2.<init>(r1)     // Catch:{ all -> 0x002c }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r2, (java.io.OutputStream) r0)     // Catch:{ all -> 0x0020 }
            byte[] r3 = r0.toByteArray()     // Catch:{ all -> 0x0020 }
            r2.close()     // Catch:{ all -> 0x002c }
            r1.close()     // Catch:{ all -> 0x0038 }
            r0.close()
            return r3
        L_0x0020:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r4 = move-exception
            r2.close()     // Catch:{ all -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ all -> 0x002c }
        L_0x002b:
            throw r4     // Catch:{ all -> 0x002c }
        L_0x002c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002e }
        L_0x002e:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ all -> 0x0038 }
        L_0x0037:
            throw r3     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x003a }
        L_0x003a:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0043:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.RLEDecompressingInputStream.decompress(byte[], int, int):byte[]");
    }
}
