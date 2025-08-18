package org.apache.poi.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import org.apache.logging.log4j.util.Chars;

@Internal
public final class HexDump {
    public static final String EOL = System.getProperty("line.separator");
    public static final Charset UTF8 = StandardCharsets.UTF_8;

    private HexDump() {
    }

    public static void dump(byte[] bArr, long j, OutputStream outputStream, int i, int i2) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (outputStream != null) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF8);
            outputStreamWriter.write(dump(bArr, j, i, i2));
            outputStreamWriter.flush();
            return;
        }
        throw new IllegalArgumentException("cannot write to nullstream");
    }

    public static synchronized void dump(byte[] bArr, long j, OutputStream outputStream, int i) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        synchronized (HexDump.class) {
            dump(bArr, j, outputStream, i, Integer.MAX_VALUE);
        }
    }

    public static String dump(byte[] bArr, long j, int i) {
        return dump(bArr, j, i, Integer.MAX_VALUE);
    }

    public static String dump(byte[] bArr, long j, int i, int i2) {
        int i3;
        int i4;
        if (bArr == null || bArr.length == 0) {
            return "No Data" + EOL;
        }
        if (i2 == Integer.MAX_VALUE || i2 < 0 || (i4 = i2 + i) < 0) {
            i3 = bArr.length;
        } else {
            i3 = Math.min(bArr.length, i4);
        }
        if (i < 0 || i >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException("illegal index: " + i + " into array of length " + bArr.length);
        }
        long j2 = j + ((long) i);
        StringBuilder sb = new StringBuilder(74);
        while (i < i3) {
            int i5 = i3 - i;
            if (i5 > 16) {
                i5 = 16;
            }
            writeHex(sb, j2, 8, "");
            for (int i6 = 0; i6 < 16; i6++) {
                if (i6 < i5) {
                    writeHex(sb, (long) bArr[i6 + i], 2, " ");
                } else {
                    sb.append("   ");
                }
            }
            sb.append(Chars.SPACE);
            for (int i7 = 0; i7 < i5; i7++) {
                sb.append(toAscii(bArr[i7 + i]));
            }
            sb.append(EOL);
            j2 += (long) i5;
            i += 16;
        }
        return sb.toString();
    }

    public static char toAscii(int i) {
        char c = (char) (i & 255);
        if (Character.isISOControl(c)) {
            return '.';
        }
        if (c == 221 || c == 255) {
            return '.';
        }
        return c;
    }

    public static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("[");
        if (bArr != null && bArr.length > 0) {
            for (int i = 0; i < bArr.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(toHex(bArr[i]));
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static String toHex(short s) {
        StringBuilder sb = new StringBuilder(4);
        writeHex(sb, (long) (s & 65535), 4, "");
        return sb.toString();
    }

    public static String toHex(byte b) {
        StringBuilder sb = new StringBuilder(2);
        writeHex(sb, (long) (b & UByte.MAX_VALUE), 2, "");
        return sb.toString();
    }

    public static String toHex(int i) {
        StringBuilder sb = new StringBuilder(8);
        writeHex(sb, ((long) i) & 4294967295L, 8, "");
        return sb.toString();
    }

    public static String toHex(long j) {
        StringBuilder sb = new StringBuilder(16);
        writeHex(sb, j, 16, "");
        return sb.toString();
    }

    public static String longToHex(long j) {
        StringBuilder sb = new StringBuilder(18);
        writeHex(sb, j, 16, "0x");
        return sb.toString();
    }

    public static String intToHex(int i) {
        StringBuilder sb = new StringBuilder(10);
        writeHex(sb, ((long) i) & 4294967295L, 8, "0x");
        return sb.toString();
    }

    public static String shortToHex(int i) {
        StringBuilder sb = new StringBuilder(6);
        writeHex(sb, ((long) i) & 65535, 4, "0x");
        return sb.toString();
    }

    public static String byteToHex(int i) {
        StringBuilder sb = new StringBuilder(4);
        writeHex(sb, ((long) i) & 255, 2, "0x");
        return sb.toString();
    }

    private static void writeHex(StringBuilder sb, long j, int i, String str) {
        sb.append(str);
        char[] cArr = new char[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            int intExact = Math.toIntExact(15 & j);
            cArr[i2] = (char) (intExact < 10 ? intExact + 48 : (intExact + 65) - 10);
            j >>>= 4;
        }
        sb.append(cArr);
    }
}
