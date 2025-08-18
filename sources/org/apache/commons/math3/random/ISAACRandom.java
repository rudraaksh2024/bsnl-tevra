package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;

public class ISAACRandom extends BitsStreamGenerator implements Serializable {
    private static final int GLD_RATIO = -1640531527;
    private static final int H_SIZE = 128;
    private static final int MASK = 1020;
    private static final int SIZE = 256;
    private static final int SIZE_L = 8;
    private static final long serialVersionUID = 7288197941165002400L;
    private final int[] arr = new int[8];
    private int count;
    private int isaacA;
    private int isaacB;
    private int isaacC;
    private int isaacI;
    private int isaacJ;
    private int isaacX;
    private final int[] mem = new int[256];
    private final int[] rsl = new int[256];

    public ISAACRandom() {
        setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    public ISAACRandom(long j) {
        setSeed(j);
    }

    public ISAACRandom(int[] iArr) {
        setSeed(iArr);
    }

    public void setSeed(int i) {
        setSeed(new int[]{i});
    }

    public void setSeed(long j) {
        setSeed(new int[]{(int) (j >>> 32), (int) (j & 4294967295L)});
    }

    public void setSeed(int[] iArr) {
        if (iArr == null) {
            setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
            return;
        }
        int length = iArr.length;
        int[] iArr2 = this.rsl;
        int length2 = iArr2.length;
        System.arraycopy(iArr, 0, iArr2, 0, FastMath.min(length, length2));
        if (length < length2) {
            for (int i = length; i < length2; i++) {
                int[] iArr3 = this.rsl;
                long j = (long) iArr3[i - length];
                iArr3[i] = (int) ((((j ^ (j >> 30)) * 1812433253) + ((long) i)) & 4294967295L);
            }
        }
        initState();
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        if (this.count < 0) {
            isaac();
            this.count = 255;
        }
        int[] iArr = this.rsl;
        int i2 = this.count;
        this.count = i2 - 1;
        return iArr[i2] >>> (32 - i);
    }

    private void isaac() {
        this.isaacI = 0;
        this.isaacJ = 128;
        int i = this.isaacB;
        int i2 = this.isaacC + 1;
        this.isaacC = i2;
        this.isaacB = i + i2;
        while (this.isaacI < 128) {
            isaac2();
        }
        this.isaacJ = 0;
        while (this.isaacJ < 128) {
            isaac2();
        }
    }

    private void isaac2() {
        int[] iArr = this.mem;
        this.isaacX = iArr[this.isaacI];
        int i = this.isaacA;
        int i2 = this.isaacJ;
        this.isaacJ = i2 + 1;
        this.isaacA = (i ^ (i << 13)) + iArr[i2];
        isaac3();
        int[] iArr2 = this.mem;
        this.isaacX = iArr2[this.isaacI];
        int i3 = this.isaacA;
        int i4 = this.isaacJ;
        this.isaacJ = i4 + 1;
        this.isaacA = (i3 ^ (i3 >>> 6)) + iArr2[i4];
        isaac3();
        int[] iArr3 = this.mem;
        this.isaacX = iArr3[this.isaacI];
        int i5 = this.isaacA;
        int i6 = this.isaacJ;
        this.isaacJ = i6 + 1;
        this.isaacA = (i5 ^ (i5 << 2)) + iArr3[i6];
        isaac3();
        int[] iArr4 = this.mem;
        this.isaacX = iArr4[this.isaacI];
        int i7 = this.isaacA;
        int i8 = this.isaacJ;
        this.isaacJ = i8 + 1;
        this.isaacA = (i7 ^ (i7 >>> 16)) + iArr4[i8];
        isaac3();
    }

    private void isaac3() {
        int[] iArr = this.mem;
        int i = this.isaacI;
        int i2 = this.isaacX;
        int i3 = iArr[(i2 & 1020) >> 2] + this.isaacA + this.isaacB;
        iArr[i] = i3;
        int i4 = iArr[((i3 >> 8) & 1020) >> 2] + i2;
        this.isaacB = i4;
        int[] iArr2 = this.rsl;
        this.isaacI = i + 1;
        iArr2[i] = i4;
    }

    private void initState() {
        this.isaacA = 0;
        this.isaacB = 0;
        this.isaacC = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.arr;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = GLD_RATIO;
            i++;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            shuffle();
        }
        for (int i3 = 0; i3 < 256; i3 += 8) {
            int[] iArr2 = this.arr;
            int i4 = iArr2[0];
            int[] iArr3 = this.rsl;
            iArr2[0] = i4 + iArr3[i3];
            iArr2[1] = iArr2[1] + iArr3[i3 + 1];
            iArr2[2] = iArr2[2] + iArr3[i3 + 2];
            iArr2[3] = iArr2[3] + iArr3[i3 + 3];
            iArr2[4] = iArr2[4] + iArr3[i3 + 4];
            iArr2[5] = iArr2[5] + iArr3[i3 + 5];
            iArr2[6] = iArr2[6] + iArr3[i3 + 6];
            iArr2[7] = iArr2[7] + iArr3[i3 + 7];
            shuffle();
            setState(i3);
        }
        for (int i5 = 0; i5 < 256; i5 += 8) {
            int[] iArr4 = this.arr;
            int i6 = iArr4[0];
            int[] iArr5 = this.mem;
            iArr4[0] = i6 + iArr5[i5];
            iArr4[1] = iArr4[1] + iArr5[i5 + 1];
            iArr4[2] = iArr4[2] + iArr5[i5 + 2];
            iArr4[3] = iArr4[3] + iArr5[i5 + 3];
            iArr4[4] = iArr4[4] + iArr5[i5 + 4];
            iArr4[5] = iArr4[5] + iArr5[i5 + 5];
            iArr4[6] = iArr4[6] + iArr5[i5 + 6];
            iArr4[7] = iArr4[7] + iArr5[i5 + 7];
            shuffle();
            setState(i5);
        }
        isaac();
        this.count = 255;
        clear();
    }

    private void shuffle() {
        int[] iArr = this.arr;
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = i ^ (i2 << 11);
        iArr[0] = i3;
        int i4 = iArr[3] + i3;
        iArr[3] = i4;
        int i5 = iArr[2];
        int i6 = i2 + i5;
        iArr[1] = i6;
        int i7 = i6 ^ (i5 >>> 2);
        iArr[1] = i7;
        int i8 = iArr[4] + i7;
        iArr[4] = i8;
        int i9 = i5 + i4;
        iArr[2] = i9;
        int i10 = i9 ^ (i4 << 8);
        iArr[2] = i10;
        int i11 = iArr[5] + i10;
        iArr[5] = i11;
        int i12 = i4 + i8;
        iArr[3] = i12;
        int i13 = i12 ^ (i8 >>> 16);
        iArr[3] = i13;
        int i14 = iArr[6] + i13;
        iArr[6] = i14;
        int i15 = i8 + i11;
        iArr[4] = i15;
        int i16 = (i11 << 10) ^ i15;
        iArr[4] = i16;
        int i17 = iArr[7] + i16;
        iArr[7] = i17;
        int i18 = i11 + i14;
        iArr[5] = i18;
        int i19 = (i14 >>> 4) ^ i18;
        iArr[5] = i19;
        int i20 = i3 + i19;
        iArr[0] = i20;
        int i21 = i14 + i17;
        iArr[6] = i21;
        int i22 = (i17 << 8) ^ i21;
        iArr[6] = i22;
        int i23 = i7 + i22;
        iArr[1] = i23;
        int i24 = i17 + i20;
        iArr[7] = i24;
        int i25 = (i20 >>> 9) ^ i24;
        iArr[7] = i25;
        iArr[2] = i10 + i25;
        iArr[0] = i20 + i23;
    }

    private void setState(int i) {
        int[] iArr = this.mem;
        int[] iArr2 = this.arr;
        iArr[i] = iArr2[0];
        iArr[i + 1] = iArr2[1];
        iArr[i + 2] = iArr2[2];
        iArr[i + 3] = iArr2[3];
        iArr[i + 4] = iArr2[4];
        iArr[i + 5] = iArr2[5];
        iArr[i + 6] = iArr2[6];
        iArr[i + 7] = iArr2[7];
    }
}
