package org.apache.xmlbeans.impl.common;

import java.lang.reflect.Array;

public class Levenshtein {
    private static int minimum(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static int distance(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        int[] iArr = new int[2];
        iArr[1] = length2 + 1;
        int i = 0;
        iArr[0] = length + 1;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        for (int i2 = 0; i2 <= length; i2++) {
            iArr2[i2][0] = i2;
        }
        for (int i3 = 0; i3 <= length2; i3++) {
            iArr2[0][i3] = i3;
        }
        int i4 = 1;
        while (i4 <= length) {
            int i5 = i4 - 1;
            char charAt = str.charAt(i5);
            int i6 = 1;
            while (i6 <= length2) {
                int i7 = i6 - 1;
                int i8 = charAt == str2.charAt(i7) ? i : 1;
                int[] iArr3 = iArr2[i4];
                int[] iArr4 = iArr2[i5];
                iArr3[i6] = minimum(iArr4[i6] + 1, iArr3[i7] + 1, iArr4[i7] + i8);
                i6++;
                i = 0;
            }
            String str3 = str2;
            i4++;
            i = 0;
        }
        return iArr2[length][length2];
    }
}
