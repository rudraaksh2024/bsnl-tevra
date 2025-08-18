package org.apache.commons.math3.random;

public class Well1024a extends AbstractWell {
    private static final int K = 1024;
    private static final int M1 = 3;
    private static final int M2 = 24;
    private static final int M3 = 10;
    private static final long serialVersionUID = 5680173464174485492L;

    public Well1024a() {
        super(1024, 3, 24, 10);
    }

    public Well1024a(int i) {
        super(1024, 3, 24, 10, i);
    }

    public Well1024a(int[] iArr) {
        super(1024, 3, 24, 10, iArr);
    }

    public Well1024a(long j) {
        super(1024, 3, 24, 10, j);
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        int i2 = this.iRm1[this.index];
        int i3 = this.v[this.index];
        int i4 = this.v[this.i1[this.index]];
        int i5 = this.v[this.i2[this.index]];
        int i6 = this.v[this.i3[this.index]];
        int i7 = this.v[i2];
        int i8 = i3 ^ (i4 ^ (i4 >>> 8));
        int i9 = ((i5 << 19) ^ i5) ^ ((i6 << 14) ^ i6);
        int i10 = i8 ^ i9;
        int i11 = ((i8 ^ (i8 << 7)) ^ ((i7 << 11) ^ i7)) ^ (i9 ^ (i9 << 13));
        this.v[this.index] = i10;
        this.v[i2] = i11;
        this.index = i2;
        return i11 >>> (32 - i);
    }
}
