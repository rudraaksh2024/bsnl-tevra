package org.apache.commons.math3.primes;

import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class Primes {
    private Primes() {
    }

    public static boolean isPrime(int i) {
        if (i < 2) {
            return false;
        }
        int[] iArr = SmallPrimes.PRIMES;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i % i3 != 0) {
                i2++;
            } else if (i == i3) {
                return true;
            } else {
                return false;
            }
        }
        return SmallPrimes.millerRabinPrimeTest(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        if (1 == r1) goto L_0x001b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int nextPrime(int r5) {
        /*
            r0 = 1
            r1 = 2
            if (r5 < 0) goto L_0x002d
            if (r5 != r1) goto L_0x0007
            return r1
        L_0x0007:
            r5 = r5 | r0
            if (r5 != r0) goto L_0x000b
            return r1
        L_0x000b:
            boolean r1 = isPrime(r5)
            if (r1 == 0) goto L_0x0012
            return r5
        L_0x0012:
            int r1 = r5 % 3
            if (r1 != 0) goto L_0x0019
            int r5 = r5 + 2
            goto L_0x001d
        L_0x0019:
            if (r0 != r1) goto L_0x001d
        L_0x001b:
            int r5 = r5 + 4
        L_0x001d:
            boolean r0 = isPrime(r5)
            if (r0 == 0) goto L_0x0024
            return r5
        L_0x0024:
            int r5 = r5 + 2
            boolean r0 = isPrime(r5)
            if (r0 == 0) goto L_0x001b
            return r5
        L_0x002d:
            org.apache.commons.math3.exception.MathIllegalArgumentException r2 = new org.apache.commons.math3.exception.MathIllegalArgumentException
            org.apache.commons.math3.exception.util.LocalizedFormats r3 = org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_TOO_SMALL
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4 = 0
            r1[r4] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            r1[r0] = r5
            r2.<init>(r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.primes.Primes.nextPrime(int):int");
    }

    public static List<Integer> primeFactors(int i) {
        if (i >= 2) {
            return SmallPrimes.trialDivision(i);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(i), 2);
    }
}
