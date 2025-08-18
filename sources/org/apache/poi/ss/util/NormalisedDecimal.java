package org.apache.poi.ss.util;

import androidx.core.view.ViewCompat;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.logging.log4j.util.Chars;

final class NormalisedDecimal {
    private static final BigDecimal BD_2_POW_24 = new BigDecimal(BigInteger.ONE.shiftLeft(24));
    private static final int C_2_POW_19 = 524288;
    private static final int EXPONENT_OFFSET = 14;
    private static final int FRAC_HALF = 8388608;
    private static final int LOG_BASE_10_OF_2_TIMES_2_POW_20 = 315653;
    private static final long MAX_REP_WHOLE_PART = 1000000000000000L;
    private final int _fractionalPart;
    private final int _relativeDecimalExponent;
    private final long _wholePart;

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        if (r1.isBelowMaxRep() != false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
        if (r1.isAboveMinRep() != false) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.ss.util.NormalisedDecimal create(java.math.BigInteger r2, int r3) {
        /*
            r0 = 49
            if (r3 > r0) goto L_0x000b
            r0 = 46
            if (r3 >= r0) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            r0 = 0
            goto L_0x0018
        L_0x000b:
            r0 = 315653(0x4d105, float:4.42324E-40)
            int r0 = r0 * r3
            r1 = 15204352(0xe80000, float:2.1305835E-38)
            int r1 = r1 - r0
            r0 = 524288(0x80000, float:7.34684E-40)
            int r1 = r1 + r0
            int r0 = r1 >> 20
            int r0 = -r0
        L_0x0018:
            org.apache.poi.ss.util.MutableFPNumber r1 = new org.apache.poi.ss.util.MutableFPNumber
            r1.<init>(r2, r3)
            if (r0 == 0) goto L_0x0023
            int r2 = -r0
            r1.multiplyByPowerOfTen(r2)
        L_0x0023:
            int r2 = r1.get64BitNormalisedExponent()
            switch(r2) {
                case 44: goto L_0x005e;
                case 45: goto L_0x005e;
                case 46: goto L_0x0057;
                case 47: goto L_0x0064;
                case 48: goto L_0x0064;
                case 49: goto L_0x0049;
                case 50: goto L_0x0050;
                default: goto L_0x002a;
            }
        L_0x002a:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "Bad binary exp "
            r3.<init>(r0)
            int r0 = r1.get64BitNormalisedExponent()
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r0 = "."
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x0049:
            boolean r2 = r1.isBelowMaxRep()
            if (r2 == 0) goto L_0x0050
            goto L_0x0064
        L_0x0050:
            r2 = -1
            r1.multiplyByPowerOfTen(r2)
            int r0 = r0 + 1
            goto L_0x0064
        L_0x0057:
            boolean r2 = r1.isAboveMinRep()
            if (r2 == 0) goto L_0x005e
            goto L_0x0064
        L_0x005e:
            r2 = 1
            r1.multiplyByPowerOfTen(r2)
            int r0 = r0 + -1
        L_0x0064:
            r1.normalise64bit()
            org.apache.poi.ss.util.NormalisedDecimal r2 = r1.createNormalisedDecimal(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.NormalisedDecimal.create(java.math.BigInteger, int):org.apache.poi.ss.util.NormalisedDecimal");
    }

    public NormalisedDecimal roundUnits() {
        long j = this._wholePart;
        if (this._fractionalPart >= 8388608) {
            j++;
        }
        int i = this._relativeDecimalExponent;
        if (j < MAX_REP_WHOLE_PART) {
            return new NormalisedDecimal(j, 0, i);
        }
        return new NormalisedDecimal(j / 10, 0, i + 1);
    }

    NormalisedDecimal(long j, int i, int i2) {
        this._wholePart = j;
        this._fractionalPart = i;
        this._relativeDecimalExponent = i2;
    }

    public ExpandedDouble normaliseBaseTwo() {
        MutableFPNumber mutableFPNumber = new MutableFPNumber(composeFrac(), 39);
        mutableFPNumber.multiplyByPowerOfTen(this._relativeDecimalExponent);
        mutableFPNumber.normalise64bit();
        return mutableFPNumber.createExpandedDouble();
    }

    /* access modifiers changed from: package-private */
    public BigInteger composeFrac() {
        return BigInteger.valueOf(this._wholePart).shiftLeft(24).or(BigInteger.valueOf((long) (this._fractionalPart & ViewCompat.MEASURED_SIZE_MASK)));
    }

    public String getSignificantDecimalDigits() {
        return Long.toString(this._wholePart);
    }

    public String getSignificantDecimalDigitsLastDigitRounded() {
        StringBuilder sb = new StringBuilder(24);
        sb.append(this._wholePart + 5);
        sb.setCharAt(sb.length() - 1, '0');
        return sb.toString();
    }

    public int getDecimalExponent() {
        return this._relativeDecimalExponent + 14;
    }

    public int compareNormalised(NormalisedDecimal normalisedDecimal) {
        int i = this._relativeDecimalExponent - normalisedDecimal._relativeDecimalExponent;
        if (i != 0) {
            return i;
        }
        long j = this._wholePart;
        long j2 = normalisedDecimal._wholePart;
        if (j > j2) {
            return 1;
        }
        if (j < j2) {
            return -1;
        }
        return this._fractionalPart - normalisedDecimal._fractionalPart;
    }

    public BigDecimal getFractionalPart() {
        return BigDecimal.valueOf((long) this._fractionalPart).divide(BD_2_POW_24);
    }

    private String getFractionalDigits() {
        if (this._fractionalPart == 0) {
            return "0";
        }
        return getFractionalPart().toString().substring(2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" [");
        String valueOf = String.valueOf(this._wholePart);
        sb.append(valueOf.charAt(0));
        sb.append('.');
        sb.append(valueOf.substring(1));
        sb.append(Chars.SPACE);
        sb.append(getFractionalDigits());
        sb.append('E');
        sb.append(getDecimalExponent());
        sb.append(']');
        return sb.toString();
    }
}
