package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;

final class RangeToken extends Token implements Serializable {
    private static final int MAPSIZE = 256;
    boolean compacted;
    RangeToken icaseCache = null;
    int[] map = null;
    int nonMapIndex;
    int[] ranges;
    boolean sorted;

    RangeToken(int i) {
        super(i);
        setSorted(false);
    }

    /* access modifiers changed from: protected */
    public void addRange(int i, int i2) {
        this.icaseCache = null;
        if (i > i2) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        int[] iArr = this.ranges;
        if (iArr == null) {
            int[] iArr2 = new int[2];
            this.ranges = iArr2;
            iArr2[0] = i;
            iArr2[1] = i2;
            setSorted(true);
            return;
        }
        int length = iArr.length;
        int i4 = length - 1;
        if (iArr[i4] + 1 == i) {
            iArr[i4] = i2;
            return;
        }
        int[] iArr3 = new int[(length + 2)];
        System.arraycopy(iArr, 0, iArr3, 0, length);
        this.ranges = iArr3;
        if (iArr3[i4] >= i) {
            setSorted(false);
        }
        int[] iArr4 = this.ranges;
        iArr4[length] = i;
        iArr4[length + 1] = i2;
        if (!this.sorted) {
            sortRanges();
        }
    }

    private boolean isSorted() {
        return this.sorted;
    }

    private void setSorted(boolean z) {
        this.sorted = z;
        if (!z) {
            this.compacted = false;
        }
    }

    private boolean isCompacted() {
        return this.compacted;
    }

    private void setCompacted() {
        this.compacted = true;
    }

    /* access modifiers changed from: protected */
    public void sortRanges() {
        int[] iArr;
        if (!isSorted() && (iArr = this.ranges) != null) {
            for (int length = iArr.length - 4; length >= 0; length -= 2) {
                int i = 0;
                while (i <= length) {
                    int[] iArr2 = this.ranges;
                    int i2 = iArr2[i];
                    int i3 = i + 2;
                    int i4 = iArr2[i3];
                    if (i2 > i4 || (i2 == i4 && iArr2[i + 1] > iArr2[i + 3])) {
                        iArr2[i3] = i2;
                        iArr2[i] = i4;
                        int i5 = i + 3;
                        int i6 = iArr2[i5];
                        int i7 = i + 1;
                        iArr2[i5] = iArr2[i7];
                        iArr2[i7] = i6;
                    }
                    i = i3;
                }
            }
            setSorted(true);
        }
    }

    /* access modifiers changed from: protected */
    public void compactRanges() {
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = this.ranges;
        if (iArr != null && iArr.length > 2 && !isCompacted()) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                int[] iArr2 = this.ranges;
                if (i5 < iArr2.length) {
                    if (i6 != i5) {
                        int i7 = i5 + 1;
                        iArr2[i6] = iArr2[i5];
                        i = i7 + 1;
                        iArr2[i6 + 1] = iArr2[i7];
                    } else {
                        i = i5 + 2;
                    }
                    int i8 = i6 + 1;
                    int i9 = iArr2[i8];
                    while (true) {
                        int[] iArr3 = this.ranges;
                        if (i >= iArr3.length || (i2 = i9 + 1) < (i3 = iArr3[i])) {
                            i6 += 2;
                            i5 = i;
                        } else {
                            if (i2 == i3) {
                                i9 = iArr3[i + 1];
                                iArr3[i8] = i9;
                            } else {
                                int i10 = i + 1;
                                int i11 = iArr3[i10];
                                if (i9 < i11) {
                                    if (i9 < i11) {
                                        iArr3[i8] = i11;
                                        i4 = i + 2;
                                        i9 = i11;
                                    } else {
                                        throw new RuntimeException("Token#compactRanges(): Internel Error: [" + this.ranges[i6] + "," + this.ranges[i8] + "] [" + this.ranges[i] + "," + this.ranges[i10] + "]");
                                    }
                                }
                            }
                            i4 = i + 2;
                        }
                    }
                    i6 += 2;
                    i5 = i;
                } else {
                    if (i6 != iArr2.length) {
                        int[] iArr4 = new int[i6];
                        System.arraycopy(iArr2, 0, iArr4, 0, i6);
                        this.ranges = iArr4;
                    }
                    setCompacted();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void mergeRanges(Token token) {
        RangeToken rangeToken = (RangeToken) token;
        sortRanges();
        rangeToken.sortRanges();
        if (rangeToken.ranges != null) {
            this.icaseCache = null;
            setSorted(true);
            int[] iArr = this.ranges;
            int i = 0;
            if (iArr == null) {
                int[] iArr2 = new int[rangeToken.ranges.length];
                this.ranges = iArr2;
                int[] iArr3 = rangeToken.ranges;
                System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
                return;
            }
            int[] iArr4 = new int[(iArr.length + rangeToken.ranges.length)];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr5 = this.ranges;
                if (i >= iArr5.length && i2 >= rangeToken.ranges.length) {
                    this.ranges = iArr4;
                    return;
                } else if (i >= iArr5.length) {
                    int i4 = i3 + 1;
                    int[] iArr6 = rangeToken.ranges;
                    int i5 = i2 + 1;
                    iArr4[i3] = iArr6[i2];
                    i3 = i4 + 1;
                    i2 = i5 + 1;
                    iArr4[i4] = iArr6[i5];
                } else {
                    int[] iArr7 = rangeToken.ranges;
                    if (i2 >= iArr7.length) {
                        int i6 = i3 + 1;
                        int i7 = i + 1;
                        iArr4[i3] = iArr5[i];
                        i3 = i6 + 1;
                        i = i7 + 1;
                        iArr4[i6] = iArr5[i7];
                    } else {
                        int i8 = iArr7[i2];
                        int i9 = iArr5[i];
                        if (i8 < i9 || (i8 == i9 && iArr7[i2 + 1] < iArr5[i + 1])) {
                            int i10 = i3 + 1;
                            int i11 = i2 + 1;
                            iArr4[i3] = i8;
                            i3 = i10 + 1;
                            iArr4[i10] = iArr7[i11];
                            i2 = i11 + 1;
                        } else {
                            int i12 = i3 + 1;
                            int i13 = i + 1;
                            iArr4[i3] = i9;
                            i3 = i12 + 1;
                            iArr4[i12] = iArr5[i13];
                            i = i13 + 1;
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void subtractRanges(Token token) {
        if (token.type == 5) {
            intersectRanges(token);
            return;
        }
        RangeToken rangeToken = (RangeToken) token;
        if (rangeToken.ranges != null && this.ranges != null) {
            this.icaseCache = null;
            sortRanges();
            compactRanges();
            rangeToken.sortRanges();
            rangeToken.compactRanges();
            int[] iArr = new int[(this.ranges.length + rangeToken.ranges.length)];
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr2 = this.ranges;
                if (i >= iArr2.length) {
                    break;
                }
                int[] iArr3 = rangeToken.ranges;
                if (i2 >= iArr3.length) {
                    break;
                }
                int i4 = iArr2[i];
                int i5 = i + 1;
                int i6 = iArr2[i5];
                int i7 = iArr3[i2];
                int i8 = i2 + 1;
                int i9 = iArr3[i8];
                if (i6 < i7) {
                    int i10 = i3 + 1;
                    iArr[i3] = i4;
                    i3 = i10 + 1;
                    iArr[i10] = iArr2[i5];
                    i = i5 + 1;
                } else {
                    if (i6 >= i7 && i4 <= i9) {
                        if (i7 > i4 || i6 > i9) {
                            if (i7 <= i4) {
                                iArr2[i] = i9 + 1;
                            } else if (i6 <= i9) {
                                int i11 = i3 + 1;
                                iArr[i3] = i4;
                                i3 = i11 + 1;
                                iArr[i11] = i7 - 1;
                            } else {
                                int i12 = i3 + 1;
                                iArr[i3] = i4;
                                i3 = i12 + 1;
                                iArr[i12] = i7 - 1;
                                iArr2[i] = i9 + 1;
                            }
                        }
                        i += 2;
                    } else if (i9 >= i4) {
                        throw new RuntimeException("Token#subtractRanges(): Internal Error: [" + this.ranges[i] + "," + this.ranges[i5] + "] - [" + rangeToken.ranges[i2] + "," + rangeToken.ranges[i8] + "]");
                    }
                    i2 += 2;
                }
            }
            while (true) {
                int[] iArr4 = this.ranges;
                if (i < iArr4.length) {
                    int i13 = i3 + 1;
                    int i14 = i + 1;
                    iArr[i3] = iArr4[i];
                    i3 = i13 + 1;
                    i = i14 + 1;
                    iArr[i13] = iArr4[i14];
                } else {
                    int[] iArr5 = new int[i3];
                    this.ranges = iArr5;
                    System.arraycopy(iArr, 0, iArr5, 0, i3);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void intersectRanges(Token token) {
        RangeToken rangeToken = (RangeToken) token;
        if (rangeToken.ranges != null && this.ranges != null) {
            this.icaseCache = null;
            sortRanges();
            compactRanges();
            rangeToken.sortRanges();
            rangeToken.compactRanges();
            int[] iArr = new int[(this.ranges.length + rangeToken.ranges.length)];
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr2 = this.ranges;
                if (i >= iArr2.length) {
                    break;
                }
                int[] iArr3 = rangeToken.ranges;
                if (i2 >= iArr3.length) {
                    break;
                }
                int i4 = iArr2[i];
                int i5 = i + 1;
                int i6 = iArr2[i5];
                int i7 = iArr3[i2];
                int i8 = i2 + 1;
                int i9 = iArr3[i8];
                if (i6 >= i7) {
                    if (i6 < i7 || i4 > i9) {
                        if (i9 >= i4) {
                            throw new RuntimeException("Token#intersectRanges(): Internal Error: [" + this.ranges[i] + "," + this.ranges[i5] + "] & [" + rangeToken.ranges[i2] + "," + rangeToken.ranges[i8] + "]");
                        }
                    } else if (i7 <= i4 && i6 <= i9) {
                        int i10 = i3 + 1;
                        iArr[i3] = i4;
                        i3 = i10 + 1;
                        iArr[i10] = i6;
                    } else if (i7 <= i4) {
                        int i11 = i3 + 1;
                        iArr[i3] = i4;
                        i3 = i11 + 1;
                        iArr[i11] = i9;
                        iArr2[i] = i9 + 1;
                    } else if (i6 <= i9) {
                        int i12 = i3 + 1;
                        iArr[i3] = i7;
                        i3 = i12 + 1;
                        iArr[i12] = i6;
                    } else {
                        int i13 = i3 + 1;
                        iArr[i3] = i7;
                        i3 = i13 + 1;
                        iArr[i13] = i9;
                        iArr2[i] = i9 + 1;
                    }
                    i2 += 2;
                }
                i += 2;
            }
            while (true) {
                int[] iArr4 = this.ranges;
                if (i < iArr4.length) {
                    int i14 = i3 + 1;
                    int i15 = i + 1;
                    iArr[i3] = iArr4[i];
                    i3 = i14 + 1;
                    i = i15 + 1;
                    iArr[i14] = iArr4[i15];
                } else {
                    int[] iArr5 = new int[i3];
                    this.ranges = iArr5;
                    System.arraycopy(iArr, 0, iArr5, 0, i3);
                    return;
                }
            }
        }
    }

    static Token complementRanges(Token token) {
        if (token.type == 4 || token.type == 5) {
            RangeToken rangeToken = (RangeToken) token;
            rangeToken.sortRanges();
            rangeToken.compactRanges();
            int[] iArr = rangeToken.ranges;
            int length = iArr.length + 2;
            int i = 0;
            if (iArr[0] == 0) {
                length -= 2;
            }
            int i2 = iArr[iArr.length - 1];
            if (i2 == 1114111) {
                length -= 2;
            }
            RangeToken createRange = Token.createRange();
            int[] iArr2 = new int[length];
            createRange.ranges = iArr2;
            int[] iArr3 = rangeToken.ranges;
            if (iArr3[0] > 0) {
                iArr2[0] = 0;
                iArr2[1] = iArr3[0] - 1;
                i = 2;
            }
            int i3 = 1;
            while (true) {
                int[] iArr4 = rangeToken.ranges;
                if (i3 >= iArr4.length - 2) {
                    break;
                }
                int[] iArr5 = createRange.ranges;
                int i4 = i + 1;
                iArr5[i] = iArr4[i3] + 1;
                i = i4 + 1;
                iArr5[i4] = iArr4[i3 + 1] - 1;
                i3 += 2;
            }
            if (i2 != 1114111) {
                int[] iArr6 = createRange.ranges;
                iArr6[i] = i2 + 1;
                iArr6[i + 1] = 1114111;
            }
            createRange.setCompacted();
            return createRange;
        }
        throw new IllegalArgumentException("Token#complementRanges(): must be RANGE: " + token.type);
    }

    /* access modifiers changed from: package-private */
    public synchronized RangeToken getCaseInsensitiveToken() {
        RangeToken rangeToken = this.icaseCache;
        if (rangeToken != null) {
            return rangeToken;
        }
        RangeToken createRange = this.type == 4 ? Token.createRange() : Token.createNRange();
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.ranges;
            if (i2 >= iArr.length) {
                break;
            }
            for (int i3 = iArr[i2]; i3 <= this.ranges[i2 + 1]; i3++) {
                if (i3 > 65535) {
                    createRange.addRange(i3, i3);
                } else {
                    char upperCase = Character.toUpperCase((char) i3);
                    createRange.addRange(upperCase, upperCase);
                }
            }
            i2 += 2;
        }
        RangeToken createRange2 = this.type == 4 ? Token.createRange() : Token.createNRange();
        while (true) {
            int[] iArr2 = createRange.ranges;
            if (i < iArr2.length) {
                for (int i4 = iArr2[i]; i4 <= createRange.ranges[i + 1]; i4++) {
                    if (i4 > 65535) {
                        createRange2.addRange(i4, i4);
                    } else {
                        char upperCase2 = Character.toUpperCase((char) i4);
                        createRange2.addRange(upperCase2, upperCase2);
                    }
                }
                i += 2;
            } else {
                createRange2.mergeRanges(createRange);
                createRange2.mergeRanges(this);
                createRange2.compactRanges();
                this.icaseCache = createRange2;
                return createRange2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean match(int i) {
        if (this.map == null) {
            createMap();
        }
        if (this.type == 4) {
            if (i >= 256) {
                int i2 = this.nonMapIndex;
                while (true) {
                    int[] iArr = this.ranges;
                    if (i2 >= iArr.length) {
                        return false;
                    }
                    if (iArr[i2] <= i && i <= iArr[i2 + 1]) {
                        return true;
                    }
                    i2 += 2;
                }
            } else if ((this.map[i / 32] & (1 << (i & 31))) != 0) {
                return true;
            } else {
                return false;
            }
        } else if (i >= 256) {
            int i3 = this.nonMapIndex;
            while (true) {
                int[] iArr2 = this.ranges;
                if (i3 >= iArr2.length) {
                    return true;
                }
                if (iArr2[i3] <= i && i <= iArr2[i3 + 1]) {
                    return false;
                }
                i3 += 2;
            }
        } else if ((this.map[i / 32] & (1 << (i & 31))) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void createMap() {
        int[] iArr = new int[8];
        int length = this.ranges.length;
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = 0;
        }
        while (true) {
            int[] iArr2 = this.ranges;
            if (i >= iArr2.length) {
                break;
            }
            int i3 = iArr2[i];
            int i4 = iArr2[i + 1];
            if (i3 >= 256) {
                break;
            }
            while (i3 <= i4 && i3 < 256) {
                int i5 = i3 / 32;
                iArr[i5] = iArr[i5] | (1 << (i3 & 31));
                i3++;
            }
            if (i4 >= 256) {
                break;
            }
            i += 2;
        }
        length = i;
        this.nonMapIndex = length;
        this.map = iArr;
    }

    public String toString(int i) {
        int i2 = 0;
        if (this.type == 4) {
            if (this == Token.token_dot) {
                return ".";
            }
            if (this == Token.token_0to9) {
                return "\\d";
            }
            if (this == Token.token_wordchars) {
                return "\\w";
            }
            if (this == Token.token_spaces) {
                return "\\s";
            }
            StringBuilder sb = new StringBuilder("[");
            while (i2 < this.ranges.length) {
                if ((i & 1024) != 0 && i2 > 0) {
                    sb.append(",");
                }
                int[] iArr = this.ranges;
                int i3 = iArr[i2];
                int i4 = i2 + 1;
                if (i3 == iArr[i4]) {
                    sb.append(escapeCharInCharClass(i3));
                } else {
                    sb.append(escapeCharInCharClass(i3));
                    sb.append('-');
                    sb.append(escapeCharInCharClass(this.ranges[i4]));
                }
                i2 += 2;
            }
            sb.append("]");
            return sb.toString();
        } else if (this == Token.token_not_0to9) {
            return "\\D";
        } else {
            if (this == Token.token_not_wordchars) {
                return "\\W";
            }
            if (this == Token.token_not_spaces) {
                return "\\S";
            }
            StringBuilder sb2 = new StringBuilder("[^");
            while (i2 < this.ranges.length) {
                if ((i & 1024) != 0 && i2 > 0) {
                    sb2.append(",");
                }
                int[] iArr2 = this.ranges;
                int i5 = iArr2[i2];
                int i6 = i2 + 1;
                if (i5 == iArr2[i6]) {
                    sb2.append(escapeCharInCharClass(i5));
                } else {
                    sb2.append(escapeCharInCharClass(i5));
                    sb2.append('-');
                    sb2.append(escapeCharInCharClass(this.ranges[i6]));
                }
                i2 += 2;
            }
            sb2.append("]");
            return sb2.toString();
        }
    }

    private static String escapeCharInCharClass(int i) {
        if (i == 9) {
            return "\\t";
        }
        if (i == 10) {
            return "\\n";
        }
        if (i == 12) {
            return "\\f";
        }
        if (i == 13) {
            return "\\r";
        }
        if (i == 27) {
            return "\\e";
        }
        if (!(i == 44 || i == 45)) {
            switch (i) {
                case 91:
                case 92:
                case 93:
                case 94:
                    break;
                default:
                    if (i < 32) {
                        String str = "0" + Integer.toHexString(i);
                        return "\\x" + str.substring(str.length() - 2);
                    } else if (i < 65536) {
                        return "" + ((char) i);
                    } else {
                        String str2 = "0" + Integer.toHexString(i);
                        return "\\v" + str2.substring(str2.length() - 6);
                    }
            }
        }
        return "\\" + ((char) i);
    }
}
