package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractWell extends BitsStreamGenerator implements Serializable {
    private static final long serialVersionUID = -817701723016583596L;
    protected final int[] i1;
    protected final int[] i2;
    protected final int[] i3;
    protected final int[] iRm1;
    protected final int[] iRm2;
    protected int index;
    protected final int[] v;

    /* access modifiers changed from: protected */
    public abstract int next(int i);

    protected AbstractWell(int i, int i4, int i5, int i6) {
        this(i, i4, i5, i6, (int[]) null);
    }

    protected AbstractWell(int i, int i4, int i5, int i6, int i7) {
        this(i, i4, i5, i6, new int[]{i7});
    }

    protected AbstractWell(int i, int i4, int i5, int i6, int[] iArr) {
        int i7 = ((i + 32) - 1) / 32;
        this.v = new int[i7];
        this.index = 0;
        this.iRm1 = new int[i7];
        this.iRm2 = new int[i7];
        this.i1 = new int[i7];
        this.i2 = new int[i7];
        this.i3 = new int[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i8 + i7;
            this.iRm1[i8] = (i9 - 1) % i7;
            this.iRm2[i8] = (i9 - 2) % i7;
            this.i1[i8] = (i8 + i4) % i7;
            this.i2[i8] = (i8 + i5) % i7;
            this.i3[i8] = (i8 + i6) % i7;
        }
        setSeed(iArr);
    }

    protected AbstractWell(int i, int i4, int i5, int i6, long j) {
        this(i, i4, i5, i6, new int[]{(int) (j >>> 32), (int) (j & 4294967295L)});
    }

    public void setSeed(int i) {
        setSeed(new int[]{i});
    }

    public void setSeed(int[] iArr) {
        if (iArr == null) {
            setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
            return;
        }
        int[] iArr2 = this.v;
        System.arraycopy(iArr, 0, iArr2, 0, FastMath.min(iArr.length, iArr2.length));
        if (iArr.length < this.v.length) {
            int length = iArr.length;
            while (true) {
                int[] iArr3 = this.v;
                if (length >= iArr3.length) {
                    break;
                }
                long j = (long) iArr3[length - iArr.length];
                iArr3[length] = (int) ((((j ^ (j >> 30)) * 1812433253) + ((long) length)) & 4294967295L);
                length++;
            }
        }
        this.index = 0;
        clear();
    }

    public void setSeed(long j) {
        setSeed(new int[]{(int) (j >>> 32), (int) (j & 4294967295L)});
    }
}
