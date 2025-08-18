package org.apache.poi.ss.util;

import java.math.BigInteger;

final class ExpandedDouble {
    private static final BigInteger BI_FRAC_MASK = BigInteger.valueOf(IEEEDouble.FRAC_MASK);
    private static final BigInteger BI_IMPLIED_FRAC_MSB = BigInteger.valueOf(IEEEDouble.FRAC_ASSUMED_HIGH_BIT);
    private final int _binaryExponent;
    private final BigInteger _significand;

    private static BigInteger getFrac(long j) {
        return BigInteger.valueOf(j).and(BI_FRAC_MASK).or(BI_IMPLIED_FRAC_MSB).shiftLeft(11);
    }

    public static ExpandedDouble fromRawBitsAndExponent(long j, int i) {
        return new ExpandedDouble(getFrac(j), i);
    }

    public ExpandedDouble(long j) {
        int intExact = Math.toIntExact(j >> 52);
        if (intExact == 0) {
            BigInteger and = BigInteger.valueOf(j).and(BI_FRAC_MASK);
            int bitLength = 64 - and.bitLength();
            this._significand = and.shiftLeft(bitLength);
            this._binaryExponent = -1023 - bitLength;
            return;
        }
        this._significand = getFrac(j);
        this._binaryExponent = (intExact & IEEEDouble.BIASED_EXPONENT_SPECIAL_VALUE) - 1023;
    }

    ExpandedDouble(BigInteger bigInteger, int i) {
        if (bigInteger.bitLength() == 64) {
            this._significand = bigInteger;
            this._binaryExponent = i;
            return;
        }
        throw new IllegalArgumentException("bad bit length");
    }

    public NormalisedDecimal normaliseBaseTen() {
        return NormalisedDecimal.create(this._significand, this._binaryExponent);
    }

    public int getBinaryExponent() {
        return this._binaryExponent;
    }

    public BigInteger getSignificand() {
        return this._significand;
    }
}
