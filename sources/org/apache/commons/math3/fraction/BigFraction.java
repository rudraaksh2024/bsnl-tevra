package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class BigFraction extends Number implements FieldElement<BigFraction>, Comparable<BigFraction>, Serializable {
    public static final BigFraction FOUR_FIFTHS = new BigFraction(4, 5);
    public static final BigFraction MINUS_ONE = new BigFraction(-1);
    public static final BigFraction ONE = new BigFraction(1);
    public static final BigFraction ONE_FIFTH = new BigFraction(1, 5);
    public static final BigFraction ONE_HALF = new BigFraction(1, 2);
    private static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100);
    public static final BigFraction ONE_QUARTER = new BigFraction(1, 4);
    public static final BigFraction ONE_THIRD = new BigFraction(1, 3);
    public static final BigFraction THREE_FIFTHS = new BigFraction(3, 5);
    public static final BigFraction THREE_QUARTERS = new BigFraction(3, 4);
    public static final BigFraction TWO = new BigFraction(2);
    public static final BigFraction TWO_FIFTHS = new BigFraction(2, 5);
    public static final BigFraction TWO_QUARTERS = new BigFraction(2, 4);
    public static final BigFraction TWO_THIRDS = new BigFraction(2, 3);
    public static final BigFraction ZERO = new BigFraction(0);
    private static final long serialVersionUID = -5630213147331578515L;
    private final BigInteger denominator;
    private final BigInteger numerator;

    public BigFraction(BigInteger bigInteger) {
        this(bigInteger, BigInteger.ONE);
    }

    public BigFraction(BigInteger bigInteger, BigInteger bigInteger2) {
        MathUtils.checkNotNull(bigInteger, LocalizedFormats.NUMERATOR, new Object[0]);
        MathUtils.checkNotNull(bigInteger2, LocalizedFormats.DENOMINATOR, new Object[0]);
        if (bigInteger2.signum() == 0) {
            throw new ZeroException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        } else if (bigInteger.signum() == 0) {
            this.numerator = BigInteger.ZERO;
            this.denominator = BigInteger.ONE;
        } else {
            BigInteger gcd = bigInteger.gcd(bigInteger2);
            if (BigInteger.ONE.compareTo(gcd) < 0) {
                bigInteger = bigInteger.divide(gcd);
                bigInteger2 = bigInteger2.divide(gcd);
            }
            if (bigInteger2.signum() == -1) {
                bigInteger = bigInteger.negate();
                bigInteger2 = bigInteger2.negate();
            }
            this.numerator = bigInteger;
            this.denominator = bigInteger2;
        }
    }

    public BigFraction(double d) throws MathIllegalArgumentException {
        if (Double.isNaN(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION, new Object[0]);
        } else if (!Double.isInfinite(d)) {
            long doubleToLongBits = Double.doubleToLongBits(d);
            long j = Long.MIN_VALUE & doubleToLongBits;
            long j2 = 9218868437227405312L & doubleToLongBits;
            long j3 = doubleToLongBits & IEEEDouble.FRAC_MASK;
            j3 = j2 != 0 ? j3 | IEEEDouble.FRAC_ASSUMED_HIGH_BIT : j3;
            j3 = j != 0 ? -j3 : j3;
            int i = ((int) (j2 >> 52)) - 1075;
            while ((9007199254740990L & j3) != 0 && (1 & j3) == 0) {
                j3 >>= 1;
                i++;
            }
            if (i < 0) {
                this.numerator = BigInteger.valueOf(j3);
                this.denominator = BigInteger.ZERO.flipBit(-i);
                return;
            }
            this.numerator = BigInteger.valueOf(j3).multiply(BigInteger.ZERO.flipBit(i));
            this.denominator = BigInteger.ONE;
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION, new Object[0]);
        }
    }

    public BigFraction(double d, double d2, int i) throws FractionConversionException {
        this(d, d2, Integer.MAX_VALUE, i);
    }

    private BigFraction(double d, double d2, int i, int i2) throws FractionConversionException {
        long j;
        long j2;
        long j3;
        double d3 = d;
        int i3 = i;
        int i4 = i2;
        long floor = (long) FastMath.floor(d);
        if (FastMath.abs(floor) > 2147483647L) {
            throw new FractionConversionException(d, floor, 1);
        } else if (FastMath.abs(((double) floor) - d3) < d2) {
            this.numerator = BigInteger.valueOf(floor);
            this.denominator = BigInteger.ONE;
        } else {
            double d4 = d3;
            long j4 = 1;
            long j5 = 0;
            long j6 = 1;
            int i5 = 0;
            boolean z = false;
            long j7 = floor;
            while (true) {
                i5++;
                double d5 = 1.0d / (d4 - ((double) floor));
                long floor2 = (long) FastMath.floor(d5);
                long j8 = floor;
                j = (floor2 * j7) + j6;
                j2 = j7;
                j3 = (floor2 * j4) + j5;
                if (j <= 2147483647L && j3 <= 2147483647L) {
                    long j9 = floor2;
                    boolean z2 = z;
                    long j10 = j6;
                    double d6 = ((double) j) / ((double) j3);
                    if (i5 >= i4 || FastMath.abs(d6 - d3) <= d2 || j3 >= ((long) i3)) {
                        z = true;
                        j9 = j8;
                        j6 = j10;
                    } else {
                        j5 = j4;
                        d4 = d5;
                        j6 = j2;
                        z = z2;
                        j2 = j;
                        j4 = j3;
                    }
                    if (z) {
                        break;
                    }
                    floor = j9;
                    j7 = j2;
                }
            }
            if (d2 != 0.0d || FastMath.abs(j4) >= ((long) i3)) {
                throw new FractionConversionException(d, j, j3);
            }
            if (i5 >= i4) {
                throw new FractionConversionException(d3, i4);
            } else if (j3 < ((long) i3)) {
                this.numerator = BigInteger.valueOf(j);
                this.denominator = BigInteger.valueOf(j3);
            } else {
                this.numerator = BigInteger.valueOf(j2);
                this.denominator = BigInteger.valueOf(j4);
            }
        }
    }

    public BigFraction(double d, int i) throws FractionConversionException {
        this(d, 0.0d, i, 100);
    }

    public BigFraction(int i) {
        this(BigInteger.valueOf((long) i), BigInteger.ONE);
    }

    public BigFraction(int i, int i2) {
        this(BigInteger.valueOf((long) i), BigInteger.valueOf((long) i2));
    }

    public BigFraction(long j) {
        this(BigInteger.valueOf(j), BigInteger.ONE);
    }

    public BigFraction(long j, long j2) {
        this(BigInteger.valueOf(j), BigInteger.valueOf(j2));
    }

    public static BigFraction getReducedFraction(int i, int i2) {
        if (i == 0) {
            return ZERO;
        }
        return new BigFraction(i, i2);
    }

    public BigFraction abs() {
        return this.numerator.signum() == 1 ? this : negate();
    }

    public BigFraction add(BigInteger bigInteger) throws NullArgumentException {
        MathUtils.checkNotNull(bigInteger);
        if (this.numerator.signum() == 0) {
            return new BigFraction(bigInteger);
        }
        if (bigInteger.signum() == 0) {
            return this;
        }
        return new BigFraction(this.numerator.add(this.denominator.multiply(bigInteger)), this.denominator);
    }

    public BigFraction add(int i) {
        return add(BigInteger.valueOf((long) i));
    }

    public BigFraction add(long j) {
        return add(BigInteger.valueOf(j));
    }

    public BigFraction add(BigFraction bigFraction) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (bigFraction.numerator.signum() == 0) {
            return this;
        } else {
            if (this.numerator.signum() == 0) {
                return bigFraction;
            }
            if (this.denominator.equals(bigFraction.denominator)) {
                bigInteger = this.numerator.add(bigFraction.numerator);
                bigInteger2 = this.denominator;
            } else {
                BigInteger add = this.numerator.multiply(bigFraction.denominator).add(bigFraction.numerator.multiply(this.denominator));
                bigInteger2 = this.denominator.multiply(bigFraction.denominator);
                bigInteger = add;
            }
            if (bigInteger.signum() == 0) {
                return ZERO;
            }
            return new BigFraction(bigInteger, bigInteger2);
        }
    }

    public BigDecimal bigDecimalValue() {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator));
    }

    public BigDecimal bigDecimalValue(int i) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), i);
    }

    public BigDecimal bigDecimalValue(int i, int i2) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), i, i2);
    }

    public int compareTo(BigFraction bigFraction) {
        int signum = this.numerator.signum();
        int signum2 = bigFraction.numerator.signum();
        if (signum != signum2) {
            return signum > signum2 ? 1 : -1;
        }
        if (signum == 0) {
            return 0;
        }
        return this.numerator.multiply(bigFraction.denominator).compareTo(this.denominator.multiply(bigFraction.numerator));
    }

    public BigFraction divide(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (bigInteger.signum() == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        } else if (this.numerator.signum() == 0) {
            return ZERO;
        } else {
            return new BigFraction(this.numerator, this.denominator.multiply(bigInteger));
        }
    }

    public BigFraction divide(int i) {
        return divide(BigInteger.valueOf((long) i));
    }

    public BigFraction divide(long j) {
        return divide(BigInteger.valueOf(j));
    }

    public BigFraction divide(BigFraction bigFraction) {
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (bigFraction.numerator.signum() == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        } else if (this.numerator.signum() == 0) {
            return ZERO;
        } else {
            return multiply(bigFraction.reciprocal());
        }
    }

    public double doubleValue() {
        double doubleValue = this.numerator.doubleValue() / this.denominator.doubleValue();
        if (!Double.isNaN(doubleValue)) {
            return doubleValue;
        }
        int max = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Double.MAX_VALUE);
        return this.numerator.shiftRight(max).doubleValue() / this.denominator.shiftRight(max).doubleValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BigFraction) {
            BigFraction reduce = ((BigFraction) obj).reduce();
            BigFraction reduce2 = reduce();
            if (reduce2.numerator.equals(reduce.numerator) && reduce2.denominator.equals(reduce.denominator)) {
                return true;
            }
        }
        return false;
    }

    public float floatValue() {
        float floatValue = this.numerator.floatValue() / this.denominator.floatValue();
        if (!Double.isNaN((double) floatValue)) {
            return floatValue;
        }
        int max = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Float.MAX_VALUE);
        return this.numerator.shiftRight(max).floatValue() / this.denominator.shiftRight(max).floatValue();
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }

    public int getDenominatorAsInt() {
        return this.denominator.intValue();
    }

    public long getDenominatorAsLong() {
        return this.denominator.longValue();
    }

    public BigInteger getNumerator() {
        return this.numerator;
    }

    public int getNumeratorAsInt() {
        return this.numerator.intValue();
    }

    public long getNumeratorAsLong() {
        return this.numerator.longValue();
    }

    public int hashCode() {
        return ((this.numerator.hashCode() + 629) * 37) + this.denominator.hashCode();
    }

    public int intValue() {
        return this.numerator.divide(this.denominator).intValue();
    }

    public long longValue() {
        return this.numerator.divide(this.denominator).longValue();
    }

    public BigFraction multiply(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullArgumentException();
        } else if (this.numerator.signum() == 0 || bigInteger.signum() == 0) {
            return ZERO;
        } else {
            return new BigFraction(bigInteger.multiply(this.numerator), this.denominator);
        }
    }

    public BigFraction multiply(int i) {
        if (i == 0 || this.numerator.signum() == 0) {
            return ZERO;
        }
        return multiply(BigInteger.valueOf((long) i));
    }

    public BigFraction multiply(long j) {
        if (j == 0 || this.numerator.signum() == 0) {
            return ZERO;
        }
        return multiply(BigInteger.valueOf(j));
    }

    public BigFraction multiply(BigFraction bigFraction) {
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (this.numerator.signum() == 0 || bigFraction.numerator.signum() == 0) {
            return ZERO;
        } else {
            return new BigFraction(this.numerator.multiply(bigFraction.numerator), this.denominator.multiply(bigFraction.denominator));
        }
    }

    public BigFraction negate() {
        return new BigFraction(this.numerator.negate(), this.denominator);
    }

    public double percentageValue() {
        return multiply(ONE_HUNDRED).doubleValue();
    }

    public BigFraction pow(int i) {
        if (i == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (i >= 0) {
            return new BigFraction(this.numerator.pow(i), this.denominator.pow(i));
        }
        int i2 = -i;
        return new BigFraction(this.denominator.pow(i2), this.numerator.pow(i2));
    }

    public BigFraction pow(long j) {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (i >= 0) {
            return new BigFraction(ArithmeticUtils.pow(this.numerator, j), ArithmeticUtils.pow(this.denominator, j));
        }
        long j2 = -j;
        return new BigFraction(ArithmeticUtils.pow(this.denominator, j2), ArithmeticUtils.pow(this.numerator, j2));
    }

    public BigFraction pow(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (bigInteger.signum() != -1) {
            return new BigFraction(ArithmeticUtils.pow(this.numerator, bigInteger), ArithmeticUtils.pow(this.denominator, bigInteger));
        }
        BigInteger negate = bigInteger.negate();
        return new BigFraction(ArithmeticUtils.pow(this.denominator, negate), ArithmeticUtils.pow(this.numerator, negate));
    }

    public double pow(double d) {
        return FastMath.pow(this.numerator.doubleValue(), d) / FastMath.pow(this.denominator.doubleValue(), d);
    }

    public BigFraction reciprocal() {
        return new BigFraction(this.denominator, this.numerator);
    }

    public BigFraction reduce() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        return BigInteger.ONE.compareTo(gcd) < 0 ? new BigFraction(this.numerator.divide(gcd), this.denominator.divide(gcd)) : this;
    }

    public BigFraction subtract(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullArgumentException();
        } else if (bigInteger.signum() == 0) {
            return this;
        } else {
            if (this.numerator.signum() == 0) {
                return new BigFraction(bigInteger.negate());
            }
            return new BigFraction(this.numerator.subtract(this.denominator.multiply(bigInteger)), this.denominator);
        }
    }

    public BigFraction subtract(int i) {
        return subtract(BigInteger.valueOf((long) i));
    }

    public BigFraction subtract(long j) {
        return subtract(BigInteger.valueOf(j));
    }

    public BigFraction subtract(BigFraction bigFraction) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (bigFraction.numerator.signum() == 0) {
            return this;
        } else {
            if (this.numerator.signum() == 0) {
                return bigFraction.negate();
            }
            if (this.denominator.equals(bigFraction.denominator)) {
                bigInteger = this.numerator.subtract(bigFraction.numerator);
                bigInteger2 = this.denominator;
            } else {
                BigInteger subtract = this.numerator.multiply(bigFraction.denominator).subtract(bigFraction.numerator.multiply(this.denominator));
                bigInteger2 = this.denominator.multiply(bigFraction.denominator);
                bigInteger = subtract;
            }
            return new BigFraction(bigInteger, bigInteger2);
        }
    }

    public String toString() {
        if (BigInteger.ONE.equals(this.denominator)) {
            return this.numerator.toString();
        }
        if (BigInteger.ZERO.equals(this.numerator)) {
            return "0";
        }
        return this.numerator + " / " + this.denominator;
    }

    public BigFractionField getField() {
        return BigFractionField.getInstance();
    }
}
