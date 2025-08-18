package org.apache.poi.ss.formula.functions;

public class BaseNumberUtils {
    public static double convertToDecimal(String str, int i, int i2) throws IllegalArgumentException {
        long j;
        int i3;
        int i4 = i;
        int i5 = i2;
        if (str == null || str.length() == 0) {
            return 0.0d;
        }
        int i6 = (((long) str.length()) > ((long) i5) ? 1 : (((long) str.length()) == ((long) i5) ? 0 : -1));
        if (i6 <= 0) {
            char[] charArray = str.toCharArray();
            int length = charArray.length;
            long j2 = 0;
            double d = 0.0d;
            int i7 = 0;
            boolean z = true;
            while (i7 < length) {
                char c = charArray[i7];
                if ('0' > c || c > '9') {
                    if ('A' <= c && c <= 'Z') {
                        i3 = c - 'A';
                    } else if ('a' > c || c > 'z') {
                        j = (long) i4;
                    } else {
                        i3 = c - 'a';
                    }
                    j = ((long) i3) + 10;
                } else {
                    j = ((long) c) - 48;
                }
                if (j < ((long) i4)) {
                    if (z) {
                        j2 = j;
                        z = false;
                    }
                    d = (d * ((double) i4)) + ((double) j);
                    i7++;
                } else {
                    throw new IllegalArgumentException("character not allowed");
                }
            }
            return !z && i6 == 0 && (j2 > ((long) (i4 / 2)) ? 1 : (j2 == ((long) (i4 / 2)) ? 0 : -1)) >= 0 ? getTwoComplement((double) i4, (double) i5, d) * -1.0d : d;
        }
        throw new IllegalArgumentException();
    }

    private static double getTwoComplement(double d, double d2, double d3) {
        return Math.pow(d, d2) - d3;
    }
}
