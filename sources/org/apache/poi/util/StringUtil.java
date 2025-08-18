package org.apache.poi.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Internal
public final class StringUtil {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 10000000;
    private static int MAX_RECORD_LENGTH = 10000000;
    public static final Charset UTF16LE = StandardCharsets.UTF_16LE;
    public static final Charset UTF8 = StandardCharsets.UTF_8;
    public static final Charset WIN_1252 = Charset.forName("cp1252");
    private static final int[] symbolMap_f020 = {32, 33, 8704, 35, 8707, 37, 38, 8717, 40, 41, 8727, 43, 44, 8722, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 8773, 913, 914, 935, 916, 917, 934, 915, 919, 921, 977, 922, 923, 924, 925, 927, 928, 920, 929, 931, CodePageUtil.CP_SJIS, 933, 962, 937, 926, CodePageUtil.CP_GBK, 918, 91, 8765, 93, 8869, 95, 32, 945, 946, 967, 948, CodePageUtil.CP_MS949, 966, 947, 951, 953, 981, 954, 955, 956, 957, 959, 960, 952, 961, 963, 964, 965, 982, 969, 958, 968, 950, 123, 124, 125, 8764, 32};
    private static final int[] symbolMap_f0a0 = {8364, 978, 8242, 8804, 8260, 8734, TypedValues.CycleType.TYPE_VISIBILITY, 9827, 9830, 9829, 9824, 8596, 8591, 8593, 8594, 8595, 176, 177, 8243, 8805, 215, 181, 8706, 8729, 247, 8800, 8801, 8776, 8230, 9168, 9135, 8629, 8501, 8475, 8476, 8472, 8855, 8853, 8709, 8745, 8746, 8835, 8839, 8836, 8834, 8838, 8712, 8713, 8736, 8711, 174, 169, 8482, 8719, 8730, 8901, 172, 8743, 8744, 8660, 8656, 8657, 8658, 8659, 9674, 9001, 174, 169, 8482, 8721, 9115, 9116, 9117, 9121, 9122, 9123, 9127, 9128, 9129, 9130, 32, 9002, 8747, 8992, 9134, 8993, 9118, 9119, 9120, 9124, 9125, 9126, 9131, 9132, 9133, 32};

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    private StringUtil() {
    }

    public static String getFromUnicodeLE(byte[] bArr, int i, int i2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (i2 == 0) {
            return "";
        }
        if (i < 0 || i >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset " + i + " (String data is of length " + bArr.length + ")");
        } else if (i2 >= 0 && (bArr.length - i) / 2 >= i2) {
            return new String(bArr, i, i2 * 2, UTF16LE);
        } else {
            throw new IllegalArgumentException("Illegal length " + i2);
        }
    }

    public static String getFromUnicodeLE(byte[] bArr) {
        if (bArr.length == 0) {
            return "";
        }
        return getFromUnicodeLE(bArr, 0, bArr.length / 2);
    }

    public static byte[] getToUnicodeLE(String str) {
        return str.getBytes(UTF16LE);
    }

    public static String getFromCompressedUnicode(byte[] bArr, int i, int i2) {
        return new String(bArr, i, Math.min(i2, bArr.length - i), StandardCharsets.ISO_8859_1);
    }

    public static String readCompressedUnicode(LittleEndianInput littleEndianInput, int i) {
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, MAX_RECORD_LENGTH);
        littleEndianInput.readFully(safelyAllocate);
        return new String(safelyAllocate, StandardCharsets.ISO_8859_1);
    }

    public static String readUnicodeString(LittleEndianInput littleEndianInput) {
        int readUShort = littleEndianInput.readUShort();
        if ((littleEndianInput.readByte() & 1) == 0) {
            return readCompressedUnicode(littleEndianInput, readUShort);
        }
        return readUnicodeLE(littleEndianInput, readUShort);
    }

    public static String readUnicodeString(LittleEndianInput littleEndianInput, int i) {
        if ((littleEndianInput.readByte() & 1) == 0) {
            return readCompressedUnicode(littleEndianInput, i);
        }
        return readUnicodeLE(littleEndianInput, i);
    }

    public static void writeUnicodeString(LittleEndianOutput littleEndianOutput, String str) {
        littleEndianOutput.writeShort(str.length());
        boolean hasMultibyte = hasMultibyte(str);
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        if (hasMultibyte) {
            putUnicodeLE(str, littleEndianOutput);
        } else {
            putCompressedUnicode(str, littleEndianOutput);
        }
    }

    public static void writeUnicodeStringFlagAndData(LittleEndianOutput littleEndianOutput, String str) {
        boolean hasMultibyte = hasMultibyte(str);
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        if (hasMultibyte) {
            putUnicodeLE(str, littleEndianOutput);
        } else {
            putCompressedUnicode(str, littleEndianOutput);
        }
    }

    public static int getEncodedSize(String str) {
        return (str.length() * (hasMultibyte(str) ? 2 : 1)) + 3;
    }

    public static void putCompressedUnicode(String str, byte[] bArr, int i) {
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }

    public static void putCompressedUnicode(String str, LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(str.getBytes(StandardCharsets.ISO_8859_1));
    }

    public static void putUnicodeLE(String str, byte[] bArr, int i) {
        byte[] bytes = str.getBytes(UTF16LE);
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }

    public static void putUnicodeLE(String str, LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(str.getBytes(UTF16LE));
    }

    public static String readUnicodeLE(LittleEndianInput littleEndianInput, int i) {
        byte[] safelyAllocate = IOUtils.safelyAllocate(((long) i) * 2, MAX_RECORD_LENGTH);
        littleEndianInput.readFully(safelyAllocate);
        return new String(safelyAllocate, UTF16LE);
    }

    public static String getPreferredEncoding() {
        return StandardCharsets.ISO_8859_1.name();
    }

    public static boolean hasMultibyte(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (c > 255) {
                return true;
            }
        }
        return false;
    }

    public static boolean startsWithIgnoreCase(String str, String str2) {
        return str.regionMatches(true, 0, str2, 0, str2.length());
    }

    public static boolean endsWithIgnoreCase(String str, String str2) {
        int length = str2.length();
        return str.regionMatches(true, str.length() - length, str2, 0, length);
    }

    @Internal
    public static String toLowerCase(char c) {
        return Character.toString(c).toLowerCase(Locale.ROOT);
    }

    @Internal
    public static String toUpperCase(char c) {
        return Character.toString(c).toUpperCase(Locale.ROOT);
    }

    @Internal
    public static boolean isUpperCase(char c) {
        String ch = Character.toString(c);
        return ch.toUpperCase(Locale.ROOT).equals(ch);
    }

    public static String mapMsCodepointString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int[] array = str.codePoints().map(new StringUtil$$ExternalSyntheticLambda0()).toArray();
        return new String(array, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int mapMsCodepoint(int i) {
        if (61472 > i || i > 61567) {
            return (61600 > i || i > 61695) ? i : symbolMap_f0a0[i - 61600];
        }
        return symbolMap_f020[i - 61472];
    }

    @Internal
    public static String join(Object[] objArr, String str) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(objArr[0]);
        for (int i = 1; i < objArr.length; i++) {
            sb.append(str).append(objArr[i]);
        }
        return sb.toString();
    }

    @Internal
    public static String join(Object[] objArr) {
        if (objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object append : objArr) {
            sb.append(append);
        }
        return sb.toString();
    }

    @Internal
    public static String join(String str, Object... objArr) {
        return join(objArr, str);
    }

    public static int countMatches(CharSequence charSequence, char c) {
        if (charSequence == null) {
            return 0;
        }
        int length = charSequence.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (charSequence.charAt(i2) == c) {
                i++;
            }
        }
        return i;
    }

    public static String getFromUnicodeLE0Terminated(byte[] bArr, int i, int i2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String str;
        if (i < 0 || i >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset " + i + " (String data is of length " + bArr.length + ")");
        } else if (i2 < 0 || (bArr.length - i) / 2 < i2) {
            throw new IllegalArgumentException("Illegal length " + i2);
        } else {
            String str2 = "";
            int i3 = 0;
            if (i2 <= 0 || i >= bArr.length - 1 || bArr[i] != 0 || bArr[i + 1] != 0) {
                str = str2;
            } else {
                i += 2;
                i2 = Character.isJavaIdentifierPart(i2 > 1 ? LittleEndian.getShort(bArr, i) : 0) ? i2 - 1 : 0;
                str = "?";
            }
            while (i3 < i2) {
                int i4 = (i3 * 2) + i;
                if (bArr[i4] == 0 && bArr[i4 + 1] == 0) {
                    break;
                }
                i3++;
            }
            int min = Math.min(i3, i2);
            if (min != 0) {
                str2 = new String(bArr, i, min * 2, UTF16LE);
            }
            return str.concat(str2);
        }
    }
}
