package org.apache.poi.xssf.util;

public class NumericRanges {
    public static final int NO_OVERLAPS = -1;
    public static final int OVERLAPS_1_MINOR = 0;
    public static final int OVERLAPS_1_WRAPS = 2;
    public static final int OVERLAPS_2_MINOR = 1;
    public static final int OVERLAPS_2_WRAPS = 3;

    public static long[] getOverlappingRange(long[] jArr, long[] jArr2) {
        int overlappingType = getOverlappingType(jArr, jArr2);
        if (overlappingType == 0) {
            return new long[]{jArr2[0], jArr[1]};
        } else if (overlappingType == 1) {
            return new long[]{jArr[0], jArr2[1]};
        } else if (overlappingType == 2) {
            return jArr2;
        } else {
            if (overlappingType != 3) {
                return new long[]{-1, -1};
            }
            return jArr;
        }
    }

    public static int getOverlappingType(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr2[0];
        long j4 = jArr2[1];
        if (j >= j3) {
            if (j2 <= j4) {
                return 3;
            }
            if (j <= j4) {
                return 1;
            }
            return -1;
        } else if (j2 >= j4) {
            return 2;
        } else {
            if (j2 >= j3) {
                return 0;
            }
            return -1;
        }
    }
}
