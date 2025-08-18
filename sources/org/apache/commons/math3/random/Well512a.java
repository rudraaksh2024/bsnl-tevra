package org.apache.commons.math3.random;

public class Well512a extends AbstractWell {
    private static final int K = 512;
    private static final int M1 = 13;
    private static final int M2 = 9;
    private static final int M3 = 5;
    private static final long serialVersionUID = -6104179812103820574L;

    public Well512a() {
        super(512, 13, 9, 5);
    }

    public Well512a(int i) {
        super(512, 13, 9, 5, i);
    }

    public Well512a(int[] iArr) {
        super(512, 13, 9, 5, iArr);
    }

    public Well512a(long j) {
        super(512, 13, 9, 5, j);
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        int i2 = this.iRm1[this.index];
        int i3 = this.v[this.index];
        int i4 = this.v[this.i1[this.index]];
        int i5 = this.v[this.i2[this.index]];
        int i6 = this.v[i2];
        int i7 = (i3 ^ (i3 << 16)) ^ (i4 ^ (i4 << 15));
        int i8 = (i5 >>> 11) ^ i5;
        int i9 = i7 ^ i8;
        int i10 = (((i7 ^ (i7 << 18)) ^ (i6 ^ (i6 << 2))) ^ (i8 << 28)) ^ (((i9 << 5) & -633066204) ^ i9);
        this.v[this.index] = i9;
        this.v[i2] = i10;
        this.index = i2;
        return i10 >>> (32 - i);
    }
}
