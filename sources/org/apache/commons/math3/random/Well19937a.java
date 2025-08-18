package org.apache.commons.math3.random;

public class Well19937a extends AbstractWell {
    private static final int K = 19937;
    private static final int M1 = 70;
    private static final int M2 = 179;
    private static final int M3 = 449;
    private static final long serialVersionUID = -7462102162223815419L;

    public Well19937a() {
        super(K, 70, 179, M3);
    }

    public Well19937a(int i) {
        super((int) K, 70, 179, (int) M3, i);
    }

    public Well19937a(int[] iArr) {
        super((int) K, 70, 179, (int) M3, iArr);
    }

    public Well19937a(long j) {
        super((int) K, 70, 179, (int) M3, j);
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        int i2 = this.iRm1[this.index];
        int i3 = this.iRm2[this.index];
        int i4 = this.v[this.index];
        int i5 = this.v[this.i1[this.index]];
        int i6 = this.v[this.i2[this.index]];
        int i7 = this.v[this.i3[this.index]];
        int i8 = (i4 ^ (i4 << 25)) ^ (i5 ^ (i5 >>> 27));
        int i9 = (i6 >>> 9) ^ ((i7 >>> 1) ^ i7);
        int i10 = i8 ^ i9;
        int i11 = (((i8 ^ (i8 << 9)) ^ ((this.v[i2] & Integer.MIN_VALUE) ^ (this.v[i3] & Integer.MAX_VALUE))) ^ (i9 ^ (i9 << 21))) ^ ((i10 >>> 21) ^ i10);
        this.v[this.index] = i10;
        this.v[i2] = i11;
        int[] iArr = this.v;
        iArr[i3] = iArr[i3] & Integer.MIN_VALUE;
        this.index = i2;
        return i11 >>> (32 - i);
    }
}
