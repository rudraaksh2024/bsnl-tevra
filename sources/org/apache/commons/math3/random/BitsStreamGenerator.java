package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

public abstract class BitsStreamGenerator implements RandomGenerator, Serializable {
    private static final long serialVersionUID = 20130104;
    private double nextGaussian = Double.NaN;

    /* access modifiers changed from: protected */
    public abstract int next(int i);

    public abstract void setSeed(int i);

    public abstract void setSeed(long j);

    public abstract void setSeed(int[] iArr);

    public boolean nextBoolean() {
        return next(1) != 0;
    }

    public double nextDouble() {
        return ((double) ((((long) next(26)) << 26) | ((long) next(26)))) * 2.220446049250313E-16d;
    }

    public float nextFloat() {
        return ((float) next(23)) * 1.1920929E-7f;
    }

    public double nextGaussian() {
        if (Double.isNaN(this.nextGaussian)) {
            double nextDouble = nextDouble() * 6.283185307179586d;
            double sqrt = FastMath.sqrt(FastMath.log(nextDouble()) * -2.0d);
            double cos = FastMath.cos(nextDouble) * sqrt;
            this.nextGaussian = sqrt * FastMath.sin(nextDouble);
            return cos;
        }
        double d = this.nextGaussian;
        this.nextGaussian = Double.NaN;
        return d;
    }

    public int nextInt() {
        return next(32);
    }

    public int nextInt(int i) throws IllegalArgumentException {
        int next;
        int i2;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) next(31))) >> 31);
        } else {
            do {
                next = next(31);
                i2 = next % i;
            } while ((next - i2) + (i - 1) < 0);
            return i2;
        }
    }

    public long nextLong() {
        return (((long) next(32)) << 32) | (((long) next(32)) & 4294967295L);
    }

    public long nextLong(long j) throws IllegalArgumentException {
        long next;
        long j2;
        if (j > 0) {
            do {
                next = (((long) next(31)) << 32) | (((long) next(32)) & 4294967295L);
                j2 = next % j;
            } while ((next - j2) + (j - 1) < 0);
            return j2;
        }
        throw new NotStrictlyPositiveException(Long.valueOf(j));
    }

    public void clear() {
        this.nextGaussian = Double.NaN;
    }

    public void nextBytes(byte[] bArr) {
        nextBytesFill(bArr, 0, bArr.length);
    }

    public void nextBytes(byte[] bArr, int i, int i2) {
        if (i < 0 || i >= bArr.length) {
            throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(bArr.length));
        } else if (i2 < 0 || i2 > bArr.length - i) {
            throw new OutOfRangeException(Integer.valueOf(i2), 0, Integer.valueOf(bArr.length - i));
        } else {
            nextBytesFill(bArr, i, i2);
        }
    }

    private void nextBytesFill(byte[] bArr, int i, int i2) {
        int i3 = (2147483644 & i2) + i;
        int i4 = i;
        while (i4 < i3) {
            int next = next(32);
            int i5 = i4 + 1;
            bArr[i4] = (byte) next;
            int i6 = i5 + 1;
            bArr[i5] = (byte) (next >>> 8);
            int i7 = i6 + 1;
            bArr[i6] = (byte) (next >>> 16);
            i4 = i7 + 1;
            bArr[i7] = (byte) (next >>> 24);
        }
        int i8 = i + i2;
        if (i4 < i8) {
            int next2 = next(32);
            while (true) {
                int i9 = i4 + 1;
                bArr[i4] = (byte) next2;
                if (i9 < i8) {
                    next2 >>>= 8;
                    i4 = i9;
                } else {
                    return;
                }
            }
        }
    }
}
