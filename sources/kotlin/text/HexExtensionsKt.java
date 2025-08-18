package kotlin.text;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.HexFormat;
import org.apache.logging.log4j.util.Chars;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0004\u001a \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a@\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0000\u001a@\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0000\u001a \u0010\u0017\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a,\u0010\u0018\u001a\u00020\u000b*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002\u001a,\u0010\u001d\u001a\u00020\u001e*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\"H\u0002\u001a\u001c\u0010#\u001a\u00020\u000b*\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0002\u001a\u0014\u0010$\u001a\u00020\u000b*\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000bH\u0002\u001a*\u0010%\u001a\u00020&*\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0003\u001a\u0016\u0010%\u001a\u00020&*\u00020\u00052\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a*\u0010)\u001a\u00020**\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0003\u001a\u0016\u0010)\u001a\u00020**\u00020\u00052\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a*\u0010+\u001a\u00020\u000b*\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0003\u001a\u0016\u0010+\u001a\u00020\u000b*\u00020\u00052\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a*\u0010,\u001a\u00020\b*\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0003\u001a\u0016\u0010,\u001a\u00020\b*\u00020\u00052\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a0\u0010-\u001a\u00020\b*\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(2\u0006\u0010 \u001a\u00020\u000bH\u0003\u001a*\u0010.\u001a\u00020/*\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0003\u001a\u0016\u0010.\u001a\u00020/*\u00020\u00052\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a\u0016\u00100\u001a\u00020\u0005*\u00020&2\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a*\u00100\u001a\u00020\u0005*\u00020*2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a\u0016\u00100\u001a\u00020\u0005*\u00020*2\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a\u0016\u00100\u001a\u00020\u0005*\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a\u0016\u00100\u001a\u00020\u0005*\u00020\b2\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a\u0016\u00100\u001a\u00020\u0005*\u00020/2\b\b\u0002\u0010'\u001a\u00020(H\u0007\u001a\u001c\u00101\u001a\u00020\u0005*\u00020\b2\u0006\u0010'\u001a\u00020(2\u0006\u00102\u001a\u00020\u000bH\u0003\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"HEX_DIGITS_TO_DECIMAL", "", "getHEX_DIGITS_TO_DECIMAL$annotations", "()V", "LOWER_CASE_HEX_DIGITS", "", "UPPER_CASE_HEX_DIGITS", "charsPerSet", "", "charsPerElement", "elementsPerSet", "", "elementSeparatorLength", "formattedStringLength", "totalBytes", "bytesPerLine", "bytesPerGroup", "groupSeparatorLength", "byteSeparatorLength", "bytePrefixLength", "byteSuffixLength", "parsedByteArrayMaxSize", "stringLength", "wholeElementsPerSet", "checkContainsAt", "part", "index", "endIndex", "partName", "checkHexLength", "", "startIndex", "maxDigits", "requireMaxLength", "", "checkNewLineAt", "decimalFromHexDigitAt", "hexToByte", "", "format", "Lkotlin/text/HexFormat;", "hexToByteArray", "", "hexToInt", "hexToLong", "hexToLongImpl", "hexToShort", "", "toHexString", "toHexStringImpl", "bits", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: HexExtensions.kt */
public final class HexExtensionsKt {
    private static final int[] HEX_DIGITS_TO_DECIMAL;
    private static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";
    private static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";

    private static /* synthetic */ void getHEX_DIGITS_TO_DECIMAL$annotations() {
    }

    static {
        int[] iArr = new int[128];
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            iArr[i2] = -1;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < r0.length()) {
            iArr[r0.charAt(i3)] = i4;
            i3++;
            i4++;
        }
        int i5 = 0;
        while (i < r0.length()) {
            iArr[r0.charAt(i)] = i5;
            i++;
            i5++;
        }
        HEX_DIGITS_TO_DECIMAL = iArr;
    }

    public static final String toHexString(byte[] bArr, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return toHexString(bArr, 0, bArr.length, hexFormat);
    }

    public static /* synthetic */ String toHexString$default(byte[] bArr, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(bArr, hexFormat);
    }

    public static /* synthetic */ String toHexString$default(byte[] bArr, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(bArr, i, i2, hexFormat);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0083 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String toHexString(byte[] r17, int r18, int r19, kotlin.text.HexFormat r20) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            java.lang.String r3 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "format"
            r4 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
            kotlin.collections.AbstractList$Companion r3 = kotlin.collections.AbstractList.Companion
            int r5 = r0.length
            r3.checkBoundsIndexes$kotlin_stdlib(r1, r2, r5)
            if (r1 != r2) goto L_0x001d
            java.lang.String r0 = ""
            return r0
        L_0x001d:
            boolean r3 = r20.getUpperCase()
            if (r3 == 0) goto L_0x0026
            java.lang.String r3 = "0123456789ABCDEF"
            goto L_0x0028
        L_0x0026:
            java.lang.String r3 = "0123456789abcdef"
        L_0x0028:
            kotlin.text.HexFormat$BytesHexFormat r4 = r20.getBytes()
            int r12 = r4.getBytesPerLine()
            int r13 = r4.getBytesPerGroup()
            java.lang.String r14 = r4.getBytePrefix()
            java.lang.String r15 = r4.getByteSuffix()
            java.lang.String r11 = r4.getByteSeparator()
            java.lang.String r4 = r4.getGroupSeparator()
            int r5 = r2 - r1
            int r8 = r4.length()
            int r9 = r11.length()
            int r10 = r14.length()
            int r16 = r15.length()
            r6 = r12
            r7 = r13
            r1 = r11
            r11 = r16
            int r5 = formattedStringLength(r5, r6, r7, r8, r9, r10, r11)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            r8 = r18
            r9 = 0
            r10 = 0
        L_0x0068:
            if (r8 >= r2) goto L_0x00a2
            byte r7 = r0[r8]
            r7 = r7 & 255(0xff, float:3.57E-43)
            if (r9 != r12) goto L_0x0078
            r9 = 10
            r6.append(r9)
            r9 = 0
        L_0x0076:
            r10 = 0
            goto L_0x007e
        L_0x0078:
            if (r10 != r13) goto L_0x007e
            r6.append(r4)
            goto L_0x0076
        L_0x007e:
            if (r10 == 0) goto L_0x0083
            r6.append(r1)
        L_0x0083:
            r6.append(r14)
            int r11 = r7 >> 4
            char r11 = r3.charAt(r11)
            r6.append(r11)
            r7 = r7 & 15
            char r7 = r3.charAt(r7)
            r6.append(r7)
            r6.append(r15)
            int r10 = r10 + 1
            r7 = 1
            int r9 = r9 + r7
            int r8 = r8 + 1
            goto L_0x0068
        L_0x00a2:
            r7 = 1
            int r0 = r6.length()
            if (r5 != r0) goto L_0x00aa
            goto L_0x00ab
        L_0x00aa:
            r7 = 0
        L_0x00ab:
            if (r7 == 0) goto L_0x00b7
            java.lang.String r0 = r6.toString()
            java.lang.String r1 = "StringBuilder(capacity).…builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x00b7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Check failed."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.HexExtensionsKt.toHexString(byte[], int, int, kotlin.text.HexFormat):java.lang.String");
    }

    public static final int formattedStringLength(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i > 0) {
            int i8 = i - 1;
            int i9 = i8 / i2;
            int i10 = (i2 - 1) / i3;
            int i11 = i % i2;
            if (i11 != 0) {
                i2 = i11;
            }
            int i12 = (i10 * i9) + ((i2 - 1) / i3);
            long j = ((long) i9) + (((long) i12) * ((long) i4)) + (((long) ((i8 - i9) - i12)) * ((long) i5)) + (((long) i) * (((long) i6) + 2 + ((long) i7)));
            if (RangesKt.intRangeContains((ClosedRange<Integer>) new IntRange(0, Integer.MAX_VALUE), j)) {
                return (int) j;
            }
            throw new IllegalArgumentException("The resulting string length is too big: " + ULong.m548toStringimpl(ULong.m502constructorimpl(j)));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static final byte[] hexToByteArray(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToByteArray(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ byte[] hexToByteArray$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByteArray(str, hexFormat);
    }

    static /* synthetic */ byte[] hexToByteArray$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByteArray(str, i, i2, hexFormat);
    }

    private static final byte[] hexToByteArray(String str, int i, int i2, HexFormat hexFormat) {
        int i3;
        String str2 = str;
        int i4 = i;
        int i5 = i2;
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i4, i5, str.length());
        if (i4 == i5) {
            return new byte[0];
        }
        HexFormat.BytesHexFormat bytes = hexFormat.getBytes();
        int bytesPerLine = bytes.getBytesPerLine();
        int bytesPerGroup = bytes.getBytesPerGroup();
        String bytePrefix = bytes.getBytePrefix();
        String byteSuffix = bytes.getByteSuffix();
        String byteSeparator = bytes.getByteSeparator();
        String groupSeparator = bytes.getGroupSeparator();
        String str3 = byteSeparator;
        int parsedByteArrayMaxSize = parsedByteArrayMaxSize(i5 - i4, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
        byte[] bArr = new byte[parsedByteArrayMaxSize];
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i3 < i5) {
            if (i7 == bytesPerLine) {
                i3 = checkNewLineAt(str2, i3, i5);
                i7 = 0;
            } else if (i8 == bytesPerGroup) {
                i3 = checkContainsAt(str2, groupSeparator, i3, i5, "group separator");
            } else {
                if (i8 != 0) {
                    i3 = checkContainsAt(str2, str3, i3, i5, "byte separator");
                }
                i7++;
                i8++;
                int checkContainsAt = checkContainsAt(str2, bytePrefix, i3, i5, "byte prefix");
                checkHexLength(str2, checkContainsAt, RangesKt.coerceAtMost(checkContainsAt + 2, i5), 2, true);
                int i9 = checkContainsAt + 1;
                bArr[i6] = (byte) ((decimalFromHexDigitAt(str2, checkContainsAt) << 4) | decimalFromHexDigitAt(str2, i9));
                i4 = checkContainsAt(str2, byteSuffix, i9 + 1, i5, "byte suffix");
                i6++;
                str3 = str3;
            }
            i8 = 0;
            i7++;
            i8++;
            int checkContainsAt2 = checkContainsAt(str2, bytePrefix, i3, i5, "byte prefix");
            checkHexLength(str2, checkContainsAt2, RangesKt.coerceAtMost(checkContainsAt2 + 2, i5), 2, true);
            int i92 = checkContainsAt2 + 1;
            bArr[i6] = (byte) ((decimalFromHexDigitAt(str2, checkContainsAt2) << 4) | decimalFromHexDigitAt(str2, i92));
            i4 = checkContainsAt(str2, byteSuffix, i92 + 1, i5, "byte suffix");
            i6++;
            str3 = str3;
        }
        if (i6 == parsedByteArrayMaxSize) {
            return bArr;
        }
        byte[] copyOf = Arrays.copyOf(bArr, i6);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        return copyOf;
    }

    public static final int parsedByteArrayMaxSize(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        long j;
        int i8 = i;
        int i9 = i2;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        if (i8 > 0) {
            long j2 = ((long) i6) + 2 + ((long) i7);
            long charsPerSet = charsPerSet(j2, i10, i12);
            if (i9 <= i10) {
                j = charsPerSet(j2, i9, i12);
            } else {
                j = charsPerSet(charsPerSet, i9 / i10, i11);
                int i13 = i9 % i10;
                if (i13 != 0) {
                    j = j + ((long) i11) + charsPerSet(j2, i13, i12);
                }
            }
            long j3 = (long) i8;
            long wholeElementsPerSet = wholeElementsPerSet(j3, j, 1);
            long j4 = j3 - ((j + 1) * wholeElementsPerSet);
            long wholeElementsPerSet2 = wholeElementsPerSet(j4, charsPerSet, i11);
            long j5 = j4 - ((charsPerSet + ((long) i11)) * wholeElementsPerSet2);
            long wholeElementsPerSet3 = wholeElementsPerSet(j5, j2, i12);
            return (int) (((long) (j5 - ((j2 + ((long) i12)) * wholeElementsPerSet3) > 0 ? 1 : 0)) + (wholeElementsPerSet * ((long) i9)) + (wholeElementsPerSet2 * ((long) i10)) + wholeElementsPerSet3);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final long charsPerSet(long j, int i, int i2) {
        if (i > 0) {
            long j2 = (long) i;
            return (j * j2) + (((long) i2) * (j2 - 1));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final long wholeElementsPerSet(long j, long j2, int i) {
        if (j <= 0 || j2 <= 0) {
            return 0;
        }
        long j3 = (long) i;
        return (j + j3) / (j2 + j3);
    }

    private static final int checkNewLineAt(String str, int i, int i2) {
        if (str.charAt(i) == 13) {
            int i3 = i + 1;
            if (i3 >= i2 || str.charAt(i3) != 10) {
                return i3;
            }
            return i + 2;
        } else if (str.charAt(i) == 10) {
            return i + 1;
        } else {
            throw new NumberFormatException("Expected a new line at index " + i + ", but was " + str.charAt(i));
        }
    }

    public static final String toHexString(byte b, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return toHexStringImpl((long) b, hexFormat, 8);
    }

    public static /* synthetic */ String toHexString$default(byte b, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(b, hexFormat);
    }

    public static final byte hexToByte(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToByte(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ byte hexToByte$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByte(str, hexFormat);
    }

    static /* synthetic */ byte hexToByte$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByte(str, i, i2, hexFormat);
    }

    private static final byte hexToByte(String str, int i, int i2, HexFormat hexFormat) {
        return (byte) ((int) hexToLongImpl(str, i, i2, hexFormat, 2));
    }

    public static final String toHexString(short s, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return toHexStringImpl((long) s, hexFormat, 16);
    }

    public static /* synthetic */ String toHexString$default(short s, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(s, hexFormat);
    }

    public static final short hexToShort(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToShort(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ short hexToShort$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToShort(str, hexFormat);
    }

    static /* synthetic */ short hexToShort$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToShort(str, i, i2, hexFormat);
    }

    private static final short hexToShort(String str, int i, int i2, HexFormat hexFormat) {
        return (short) ((int) hexToLongImpl(str, i, i2, hexFormat, 4));
    }

    public static final String toHexString(int i, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return toHexStringImpl((long) i, hexFormat, 32);
    }

    public static /* synthetic */ String toHexString$default(int i, HexFormat hexFormat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(i, hexFormat);
    }

    public static final int hexToInt(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToInt(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ int hexToInt$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToInt(str, hexFormat);
    }

    static /* synthetic */ int hexToInt$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToInt(str, i, i2, hexFormat);
    }

    private static final int hexToInt(String str, int i, int i2, HexFormat hexFormat) {
        return (int) hexToLongImpl(str, i, i2, hexFormat, 8);
    }

    public static final String toHexString(long j, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return toHexStringImpl(j, hexFormat, 64);
    }

    public static /* synthetic */ String toHexString$default(long j, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(j, hexFormat);
    }

    public static final long hexToLong(String str, HexFormat hexFormat) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat, "format");
        return hexToLong(str, 0, str.length(), hexFormat);
    }

    public static /* synthetic */ long hexToLong$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToLong(str, hexFormat);
    }

    static /* synthetic */ long hexToLong$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToLong(str, i, i2, hexFormat);
    }

    private static final long hexToLong(String str, int i, int i2, HexFormat hexFormat) {
        return hexToLongImpl(str, i, i2, hexFormat, 16);
    }

    private static final String toHexStringImpl(long j, HexFormat hexFormat, int i) {
        if ((i & 3) == 0) {
            String str = hexFormat.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
            String prefix = hexFormat.getNumber().getPrefix();
            String suffix = hexFormat.getNumber().getSuffix();
            boolean removeLeadingZeros = hexFormat.getNumber().getRemoveLeadingZeros();
            StringBuilder sb = new StringBuilder(prefix.length() + (i >> 2) + suffix.length());
            sb.append(prefix);
            while (i > 0) {
                i -= 4;
                int i2 = (int) ((j >> i) & 15);
                removeLeadingZeros = removeLeadingZeros && i2 == 0 && i > 0;
                if (!removeLeadingZeros) {
                    sb.append(str.charAt(i2));
                }
            }
            sb.append(suffix);
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
            return sb2;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    static /* synthetic */ long hexToLongImpl$default(String str, int i, int i2, HexFormat hexFormat, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = str.length();
        }
        return hexToLongImpl(str, i, i2, hexFormat, i3);
    }

    private static final long hexToLongImpl(String str, int i, int i2, HexFormat hexFormat, int i3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        String prefix = hexFormat.getNumber().getPrefix();
        String suffix = hexFormat.getNumber().getSuffix();
        if (prefix.length() + suffix.length() < i2 - i) {
            int checkContainsAt = checkContainsAt(str, prefix, i, i2, "prefix");
            int length = i2 - suffix.length();
            checkContainsAt(str, suffix, length, i2, "suffix");
            checkHexLength(str, checkContainsAt, length, i3, false);
            long j = 0;
            while (checkContainsAt < length) {
                j = (j << 4) | ((long) decimalFromHexDigitAt(str, checkContainsAt));
                checkContainsAt++;
            }
            return j;
        }
        StringBuilder append = new StringBuilder("Expected a hexadecimal number with prefix \"").append(prefix).append("\" and suffix \"").append(suffix).append("\", but was ");
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        throw new NumberFormatException(append.append(substring).toString());
    }

    private static final int checkContainsAt(String str, String str2, int i, int i2, String str3) {
        int length = str2.length() + i;
        if (length <= i2) {
            if (StringsKt.regionMatches(str, i, str2, 0, str2.length(), true)) {
                return length;
            }
        }
        StringBuilder append = new StringBuilder("Expected ").append(str3).append(" \"").append(str2).append("\" at index ").append(i).append(", but was ");
        int coerceAtMost = RangesKt.coerceAtMost(length, i2);
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, coerceAtMost);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        throw new NumberFormatException(append.append(substring).toString());
    }

    private static final void checkHexLength(String str, int i, int i2, int i3, boolean z) {
        int i4 = i2 - i;
        boolean z2 = true;
        if (!z ? i4 > i3 : i4 != i3) {
            z2 = false;
        }
        if (!z2) {
            String str2 = z ? "exactly" : "at most";
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
            String substring = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            throw new NumberFormatException("Expected " + str2 + Chars.SPACE + i3 + " hexadecimal digits at index " + i + ", but was " + substring + " of length " + i4);
        }
    }

    private static final int decimalFromHexDigitAt(String str, int i) {
        int i2;
        char charAt = str.charAt(i);
        if (charAt <= 127 && (i2 = HEX_DIGITS_TO_DECIMAL[charAt]) >= 0) {
            return i2;
        }
        throw new NumberFormatException("Expected a hexadecimal digit at index " + i + ", but was " + str.charAt(i));
    }
}
