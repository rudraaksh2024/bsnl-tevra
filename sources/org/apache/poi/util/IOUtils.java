package org.apache.poi.util;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.zip.CRC32;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;

@Internal
public final class IOUtils {
    private static int BYTE_ARRAY_MAX_OVERRIDE = -1;
    private static int DEFAULT_BUFFER_SIZE = 4096;
    private static final Logger LOG = LogManager.getLogger((Class<?>) IOUtils.class);
    private static int MAX_BYTE_ARRAY_INIT_SIZE = -1;
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static byte[] SKIP_BYTE_BUFFER;

    private IOUtils() {
    }

    public static void setMaxByteArrayInitSize(int i) {
        MAX_BYTE_ARRAY_INIT_SIZE = i;
    }

    public static int getMaxByteArrayInitSize() {
        return MAX_BYTE_ARRAY_INIT_SIZE;
    }

    public static void setByteArrayMaxOverride(int i) {
        BYTE_ARRAY_MAX_OVERRIDE = i;
    }

    public static byte[] peekFirst8Bytes(InputStream inputStream) throws IOException, EmptyFileException {
        return peekFirstNBytes(inputStream, 8);
    }

    private static void checkByteSizeLimit(int i) {
        int i2 = BYTE_ARRAY_MAX_OVERRIDE;
        if (i2 != -1 && i > i2) {
            throwRFE((long) i, i2);
        }
    }

    public static byte[] peekFirstNBytes(InputStream inputStream, int i) throws IOException, EmptyFileException {
        checkByteSizeLimit(i);
        inputStream.mark(i);
        UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream(i);
        copy((InputStream) new BoundedInputStream(inputStream, (long) i), (OutputStream) unsynchronizedByteArrayOutputStream);
        int size = unsynchronizedByteArrayOutputStream.size();
        if (size != 0) {
            if (size < i) {
                unsynchronizedByteArrayOutputStream.write(new byte[(i - size)]);
            }
            byte[] byteArray = unsynchronizedByteArrayOutputStream.toByteArray();
            if (inputStream instanceof PushbackInputStream) {
                ((PushbackInputStream) inputStream).unread(byteArray, 0, size);
            } else {
                inputStream.reset();
            }
            return byteArray;
        }
        throw new EmptyFileException();
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        return toByteArray(inputStream, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream inputStream, int i) throws IOException {
        return toByteArray(inputStream, i, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream inputStream, int i, int i2) throws IOException {
        return toByteArray(inputStream, i, i2, true, i != Integer.MAX_VALUE);
    }

    public static byte[] toByteArrayWithMaxLength(InputStream inputStream, int i) throws IOException {
        return toByteArray(inputStream, i, i, false, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008d, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0092, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0093, code lost:
        r8.addSuppressed(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0096, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] toByteArray(java.io.InputStream r8, int r9, int r10, boolean r11, boolean r12) throws java.io.IOException {
        /*
            if (r9 < 0) goto L_0x0097
            if (r10 < 0) goto L_0x0097
            int r0 = BYTE_ARRAY_MAX_OVERRIDE
            int r10 = java.lang.Math.max(r10, r0)
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r9 != r0) goto L_0x0011
            if (r10 == r0) goto L_0x0015
        L_0x0011:
            long r1 = (long) r9
            checkLength(r1, r10)
        L_0x0015:
            if (r12 == 0) goto L_0x001c
            int r1 = java.lang.Math.min(r9, r10)
            goto L_0x001d
        L_0x001c:
            r1 = r10
        L_0x001d:
            int r9 = calculateByteArrayInitLength(r12, r9, r10)
            int r2 = DEFAULT_BUFFER_SIZE
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r3 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r3.<init>(r9)
            byte[] r9 = new byte[r2]     // Catch:{ all -> 0x008b }
            r4 = 0
            r5 = r4
        L_0x002c:
            int r6 = r1 - r5
            int r6 = java.lang.Math.min(r2, r6)     // Catch:{ all -> 0x008b }
            int r6 = r8.read(r9, r4, r6)     // Catch:{ all -> 0x008b }
            int r7 = java.lang.Math.max(r6, r4)     // Catch:{ all -> 0x008b }
            int r5 = r5 + r7
            if (r6 <= 0) goto L_0x0040
            r3.write(r9, r4, r6)     // Catch:{ all -> 0x008b }
        L_0x0040:
            checkByteSizeLimit(r5)     // Catch:{ all -> 0x008b }
            r7 = -1
            if (r5 >= r1) goto L_0x0048
            if (r6 > r7) goto L_0x002c
        L_0x0048:
            int r9 = BYTE_ARRAY_MAX_OVERRIDE     // Catch:{ all -> 0x008b }
            if (r9 >= 0) goto L_0x0059
            if (r6 <= r7) goto L_0x0059
            if (r12 != 0) goto L_0x0059
            int r8 = r8.read()     // Catch:{ all -> 0x008b }
            if (r8 < 0) goto L_0x0059
            throwRecordTruncationException(r10)     // Catch:{ all -> 0x008b }
        L_0x0059:
            if (r11 == 0) goto L_0x0083
            if (r1 == r0) goto L_0x0083
            if (r5 < r1) goto L_0x0060
            goto L_0x0083
        L_0x0060:
            java.io.EOFException r8 = new java.io.EOFException     // Catch:{ all -> 0x008b }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r9.<init>()     // Catch:{ all -> 0x008b }
            java.lang.String r10 = "unexpected EOF - expected len: "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x008b }
            java.lang.StringBuilder r9 = r9.append(r1)     // Catch:{ all -> 0x008b }
            java.lang.String r10 = " - actual len: "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x008b }
            java.lang.StringBuilder r9 = r9.append(r5)     // Catch:{ all -> 0x008b }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x008b }
            r8.<init>(r9)     // Catch:{ all -> 0x008b }
            throw r8     // Catch:{ all -> 0x008b }
        L_0x0083:
            byte[] r8 = r3.toByteArray()     // Catch:{ all -> 0x008b }
            r3.close()
            return r8
        L_0x008b:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x008d }
        L_0x008d:
            r9 = move-exception
            r3.close()     // Catch:{ all -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r10 = move-exception
            r8.addSuppressed(r10)
        L_0x0096:
            throw r9
        L_0x0097:
            org.apache.poi.util.RecordFormatException r8 = new org.apache.poi.util.RecordFormatException
            java.lang.String r9 = "Can't allocate an array of length < 0"
            r8.<init>((java.lang.String) r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.IOUtils.toByteArray(java.io.InputStream, int, int, boolean, boolean):byte[]");
    }

    static int calculateByteArrayInitLength(boolean z, int i, int i2) {
        int min = Math.min(i, i2);
        if (!z) {
            min = Math.min(DEFAULT_BUFFER_SIZE, min);
        }
        int i3 = MAX_BYTE_ARRAY_INIT_SIZE;
        return (i3 <= 0 || min <= i3) ? min : Math.min(min, i3);
    }

    private static void checkLength(long j, int i) {
        int i2 = BYTE_ARRAY_MAX_OVERRIDE;
        if (i2 > 0) {
            if (j > ((long) i2)) {
                throwRFE(j, i2);
            }
        } else if (j > ((long) i)) {
            throwRFE(j, i);
        }
    }

    public static byte[] toByteArray(ByteBuffer byteBuffer, int i) {
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return byteBuffer.array();
        }
        checkByteSizeLimit(i);
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    public static int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        return readFully(inputStream, bArr, 0, bArr.length);
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
            } else if (i3 == 0) {
                return -1;
            } else {
                return i3;
            }
        } while (i3 != i2);
        return i3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0007 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x000b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int readFully(java.nio.channels.ReadableByteChannel r3, java.nio.ByteBuffer r4) throws java.io.IOException {
        /*
            r0 = 0
        L_0x0001:
            int r1 = r3.read(r4)
            if (r1 >= 0) goto L_0x000b
            if (r0 != 0) goto L_0x000a
            r0 = -1
        L_0x000a:
            return r0
        L_0x000b:
            int r0 = r0 + r1
            int r1 = r4.capacity()
            if (r0 == r1) goto L_0x001c
            int r1 = r4.position()
            int r2 = r4.capacity()
            if (r1 != r2) goto L_0x0001
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.IOUtils.readFully(java.nio.channels.ReadableByteChannel, java.nio.ByteBuffer):int");
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, -1);
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[DEFAULT_BUFFER_SIZE];
        int i = -1;
        long j2 = 0;
        while (true) {
            int min = (int) (j < 0 ? (long) DEFAULT_BUFFER_SIZE : Math.min(j - j2, (long) DEFAULT_BUFFER_SIZE));
            if (min > 0) {
                int read = inputStream.read(bArr, 0, min);
                if (read > 0) {
                    outputStream.write(bArr, 0, read);
                    j2 += (long) read;
                }
                i = read;
            }
            if (i < 0 || (j != -1 && j2 >= j)) {
                return j2;
            }
        }
        return j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long copy(java.io.InputStream r2, java.io.File r3) throws java.io.IOException {
        /*
            java.io.File r0 = r3.getParentFile()
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0026
            boolean r1 = r0.mkdirs()
            if (r1 == 0) goto L_0x0011
            goto L_0x0026
        L_0x0011:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r1 = "Can't create destination directory: "
            r3.<init>(r1)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x0026:
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r3)
            long r2 = copy((java.io.InputStream) r2, (java.io.OutputStream) r0)     // Catch:{ all -> 0x0033 }
            r0.close()
            return r2
        L_0x0033:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x003e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.IOUtils.copy(java.io.InputStream, java.io.File):long");
    }

    public static long calculateChecksum(byte[] bArr) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, 0, bArr.length);
        return crc32.getValue();
    }

    public static long calculateChecksum(InputStream inputStream) throws IOException {
        CRC32 crc32 = new CRC32();
        byte[] bArr = new byte[DEFAULT_BUFFER_SIZE];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return crc32.getValue();
            }
            if (read > 0) {
                crc32.update(bArr, 0, read);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LOG.atError().withThrowable(e).log("Unable to close resource");
            }
        }
    }

    public static long skipFully(InputStream inputStream, long j) throws IOException {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + j);
        } else if (i == 0) {
            return 0;
        } else {
            if (SKIP_BYTE_BUFFER == null) {
                SKIP_BYTE_BUFFER = new byte[2048];
            }
            long j2 = j;
            while (j2 > 0) {
                long read = (long) inputStream.read(SKIP_BYTE_BUFFER, 0, (int) Math.min(j2, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH));
                if (read < 0) {
                    break;
                }
                j2 -= read;
            }
            if (j == j2) {
                return -1;
            }
            return j - j2;
        }
    }

    public static byte[] safelyAllocate(long j, int i) {
        safelyAllocateCheck(j, i);
        int i2 = (int) j;
        checkByteSizeLimit(i2);
        return new byte[i2];
    }

    public static void safelyAllocateCheck(long j, int i) {
        if (j < 0) {
            throw new RecordFormatException("Can't allocate an array of length < 0, but had " + j + " and " + i);
        } else if (j <= 2147483647L) {
            checkLength(j, i);
        } else {
            throw new RecordFormatException("Can't allocate an array > 2147483647");
        }
    }

    public static byte[] safelyClone(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            return null;
        }
        if (i < 0 || i2 < 0 || i3 < 0) {
            throw new RecordFormatException("Invalid offset/length specified: offset: " + i + ", lenght: " + i2 + ", maxLength: " + i3);
        }
        int min = Math.min(bArr.length - i, i2);
        safelyAllocateCheck((long) min, i3);
        return Arrays.copyOfRange(bArr, i, min + i);
    }

    public static int readByte(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private static void throwRFE(long j, int i) {
        throw new RecordFormatException(String.format(Locale.ROOT, "Tried to allocate an array of length %,d, but the maximum length for this record type is %,d.\nIf the file is not corrupt or large, please open an issue on bugzilla to request \nincreasing the maximum allowable size for this record type.\nAs a temporary workaround, consider setting a higher override value with IOUtils.setByteArrayMaxOverride()", new Object[]{Long.valueOf(j), Integer.valueOf(i)}));
    }

    private static void throwRecordTruncationException(int i) {
        throw new RecordFormatException(String.format(Locale.ROOT, "Tried to read data but the maximum length for this record type is %,d.\nIf the file is not corrupt or large, please open an issue on bugzilla to request \nincreasing the maximum allowable size for this record type.\nAs a temporary workaround, consider setting a higher override value with IOUtils.setByteArrayMaxOverride()", new Object[]{Integer.valueOf(i)}));
    }
}
