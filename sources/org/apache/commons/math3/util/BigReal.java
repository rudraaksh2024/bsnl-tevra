package org.apache.commons.math3.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class BigReal implements FieldElement<BigReal>, Comparable<BigReal>, Serializable {
    public static final BigReal ONE = new BigReal(BigDecimal.ONE);
    public static final BigReal ZERO = new BigReal(BigDecimal.ZERO);
    private static final long serialVersionUID = 4984534880991310382L;
    private final BigDecimal d;
    private RoundingMode roundingMode = RoundingMode.HALF_UP;
    private int scale = 64;

    public BigReal(BigDecimal bigDecimal) {
        this.d = bigDecimal;
    }

    public BigReal(BigInteger bigInteger) {
        this.d = new BigDecimal(bigInteger);
    }

    public BigReal(BigInteger bigInteger, int i) {
        this.d = new BigDecimal(bigInteger, i);
    }

    public BigReal(BigInteger bigInteger, int i, MathContext mathContext) {
        this.d = new BigDecimal(bigInteger, i, mathContext);
    }

    public BigReal(BigInteger bigInteger, MathContext mathContext) {
        this.d = new BigDecimal(bigInteger, mathContext);
    }

    public BigReal(char[] cArr) {
        this.d = new BigDecimal(cArr);
    }

    public BigReal(char[] cArr, int i, int i2) {
        this.d = new BigDecimal(cArr, i, i2);
    }

    public BigReal(char[] cArr, int i, int i2, MathContext mathContext) {
        this.d = new BigDecimal(cArr, i, i2, mathContext);
    }

    public BigReal(char[] cArr, MathContext mathContext) {
        this.d = new BigDecimal(cArr, mathContext);
    }

    public BigReal(double d2) {
        this.d = new BigDecimal(d2);
    }

    public BigReal(double d2, MathContext mathContext) {
        this.d = new BigDecimal(d2, mathContext);
    }

    public BigReal(int i) {
        this.d = new BigDecimal(i);
    }

    public BigReal(int i, MathContext mathContext) {
        this.d = new BigDecimal(i, mathContext);
    }

    public BigReal(long j) {
        this.d = new BigDecimal(j);
    }

    public BigReal(long j, MathContext mathContext) {
        this.d = new BigDecimal(j, mathContext);
    }

    public BigReal(String str) {
        this.d = new BigDecimal(str);
    }

    public BigReal(String str, MathContext mathContext) {
        this.d = new BigDecimal(str, mathContext);
    }

    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    public void setRoundingMode(RoundingMode roundingMode2) {
        this.roundingMode = roundingMode2;
    }

    public int getScale() {
        return this.scale;
    }

    public void setScale(int i) {
        this.scale = i;
    }

    public BigReal add(BigReal bigReal) {
        return new BigReal(this.d.add(bigReal.d));
    }

    public BigReal subtract(BigReal bigReal) {
        return new BigReal(this.d.subtract(bigReal.d));
    }

    public BigReal negate() {
        return new BigReal(this.d.negate());
    }

    public BigReal divide(BigReal bigReal) throws MathArithmeticException {
        try {
            return new BigReal(this.d.divide(bigReal.d, this.scale, this.roundingMode));
        } catch (ArithmeticException unused) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
        }
    }

    public BigReal reciprocal() throws MathArithmeticException {
        try {
            return new BigReal(BigDecimal.ONE.divide(this.d, this.scale, this.roundingMode));
        } catch (ArithmeticException unused) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
        }
    }

    public BigReal multiply(BigReal bigReal) {
        return new BigReal(this.d.multiply(bigReal.d));
    }

    public BigReal multiply(int i) {
        return new BigReal(this.d.multiply(new BigDecimal(i)));
    }

    public int compareTo(BigReal bigReal) {
        return this.d.compareTo(bigReal.d);
    }

    public double doubleValue() {
        return this.d.doubleValue();
    }

    public BigDecimal bigDecimalValue() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BigReal) {
            return this.d.equals(((BigReal) obj).d);
        }
        return false;
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    public Field<BigReal> getField() {
        return BigRealField.getInstance();
    }
}
