package com.zaxxer.sparsebits;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.logging.log4j.util.Chars;

public class SparseBitSet implements Cloneable, Serializable {
    protected static final int INDEX_SIZE = 31;
    protected static final int LENGTH2 = 32;
    protected static final int LENGTH3 = 32;
    protected static final int LENGTH4 = 64;
    protected static final int LEVEL1 = 15;
    protected static final int LEVEL2 = 5;
    protected static final int LEVEL3 = 5;
    protected static final int LEVEL4 = 6;
    protected static final int MASK2 = 31;
    protected static final int MASK3 = 31;
    protected static final int MAX_LENGTH1 = 32768;
    protected static final int SHIFT1 = 10;
    protected static final int SHIFT2 = 5;
    protected static final int SHIFT3 = 6;
    protected static final int UNIT = 65536;
    static final long[] ZERO_BLOCK = new long[32];
    protected static final transient AndNotStrategy andNotStrategy = new AndNotStrategy();
    protected static final transient AndStrategy andStrategy = new AndStrategy();
    protected static final transient ClearStrategy clearStrategy = new ClearStrategy();
    static int compactionCountDefault = 2;
    protected static final transient CopyStrategy copyStrategy = new CopyStrategy();
    protected static final transient FlipStrategy flipStrategy = new FlipStrategy();
    protected static transient IntersectsStrategy intersectsStrategy = new IntersectsStrategy();
    protected static final transient OrStrategy orStrategy = new OrStrategy();
    private static final long serialVersionUID = -6663013367427929992L;
    protected static final transient SetStrategy setStrategy = new SetStrategy();
    protected static final transient XorStrategy xorStrategy = new XorStrategy();
    protected transient long[][][] bits;
    protected transient int bitsLength;
    protected transient Cache cache;
    protected transient int compactionCount;
    protected transient EqualsStrategy equalsStrategy;
    protected transient long[] spare;
    protected transient UpdateStrategy updateStrategy;

    public enum Statistics {
        Size,
        Length,
        Cardinality,
        Total_words,
        Set_array_length,
        Set_array_max_length,
        Level2_areas,
        Level2_area_length,
        Level3_blocks,
        Level3_block_length,
        Compaction_count_value
    }

    protected SparseBitSet(int i, int i2) throws NegativeArraySizeException {
        if (i >= 0) {
            resize(i - 1);
            this.compactionCount = i2;
            constructorHelper();
            statisticsUpdate();
            return;
        }
        throw new NegativeArraySizeException("(requested capacity=" + i + ") < 0");
    }

    public SparseBitSet() {
        this(1, compactionCountDefault);
    }

    public SparseBitSet(int i) throws NegativeArraySizeException {
        this(i, compactionCountDefault);
    }

    public void and(int i, boolean z) throws IndexOutOfBoundsException {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        } else if (!z) {
            clear(i);
        }
    }

    public void and(int i, int i2, SparseBitSet sparseBitSet) throws IndexOutOfBoundsException {
        setScanner(i, i2, sparseBitSet, andStrategy);
    }

    public void and(SparseBitSet sparseBitSet) {
        nullify(Math.min(this.bits.length, sparseBitSet.bits.length));
        setScanner(0, Math.min(this.bitsLength, sparseBitSet.bitsLength), sparseBitSet, andStrategy);
    }

    public static SparseBitSet and(SparseBitSet sparseBitSet, SparseBitSet sparseBitSet2) {
        SparseBitSet clone = sparseBitSet.clone();
        clone.and(sparseBitSet2);
        return clone;
    }

    public void andNot(int i, boolean z) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        } else if (z) {
            clear(i);
        }
    }

    public void andNot(int i, int i2, SparseBitSet sparseBitSet) throws IndexOutOfBoundsException {
        setScanner(i, i2, sparseBitSet, andNotStrategy);
    }

    public void andNot(SparseBitSet sparseBitSet) {
        setScanner(0, Math.min(this.bitsLength, sparseBitSet.bitsLength), sparseBitSet, andNotStrategy);
    }

    public static SparseBitSet andNot(SparseBitSet sparseBitSet, SparseBitSet sparseBitSet2) {
        SparseBitSet clone = sparseBitSet.clone();
        clone.andNot(sparseBitSet2);
        return clone;
    }

    public int cardinality() {
        statisticsUpdate();
        return this.cache.cardinality;
    }

    public void clear(int i) {
        long[] jArr;
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        } else if (i < this.bitsLength) {
            int i2 = i >> 6;
            long[][] jArr2 = this.bits[i2 >> 10];
            if (jArr2 != null && (jArr = jArr2[(i2 >> 5) & 31]) != null) {
                int i3 = i2 & 31;
                jArr[i3] = jArr[i3] & (~(1 << i));
                this.cache.hash = 0;
            }
        }
    }

    public void clear(int i, int i2) throws IndexOutOfBoundsException {
        setScanner(i, i2, (SparseBitSet) null, clearStrategy);
    }

    public void clear() {
        nullify(0);
    }

    public SparseBitSet clone() {
        try {
            SparseBitSet sparseBitSet = (SparseBitSet) super.clone();
            long[][][] jArr = null;
            sparseBitSet.bits = null;
            sparseBitSet.resize(1);
            sparseBitSet.constructorHelper();
            sparseBitSet.equalsStrategy = null;
            sparseBitSet.setScanner(0, this.bitsLength, this, copyStrategy);
            return sparseBitSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SparseBitSet)) {
            return false;
        }
        SparseBitSet sparseBitSet = (SparseBitSet) obj;
        if (this == sparseBitSet) {
            return true;
        }
        if (this.equalsStrategy == null) {
            this.equalsStrategy = new EqualsStrategy();
        }
        setScanner(0, Math.max(this.bitsLength, sparseBitSet.bitsLength), sparseBitSet, this.equalsStrategy);
        return this.equalsStrategy.result;
    }

    public void flip(int i) {
        if (i + 1 >= 1) {
            int i2 = i >> 6;
            int i3 = i2 >> 10;
            int i4 = (i2 >> 5) & 31;
            if (i >= this.bitsLength) {
                resize(i);
            }
            long[][][] jArr = this.bits;
            long[][] jArr2 = jArr[i3];
            if (jArr2 == null) {
                jArr2 = new long[32][];
                jArr[i3] = jArr2;
            }
            long[] jArr3 = jArr2[i4];
            if (jArr3 == null) {
                jArr3 = new long[32];
                jArr2[i4] = jArr3;
            }
            int i5 = i2 & 31;
            jArr3[i5] = jArr3[i5] ^ (1 << i);
            this.cache.hash = 0;
            return;
        }
        throw new IndexOutOfBoundsException("i=" + i);
    }

    public void flip(int i, int i2) throws IndexOutOfBoundsException {
        setScanner(i, i2, (SparseBitSet) null, flipStrategy);
    }

    public boolean get(int i) {
        long[][] jArr;
        long[] jArr2;
        if (i + 1 >= 1) {
            int i2 = i >> 6;
            if (!(i >= this.bitsLength || (jArr = this.bits[i2 >> 10]) == null || (jArr2 = jArr[(i2 >> 5) & 31]) == null)) {
                if (((1 << i) & jArr2[i2 & 31]) != 0) {
                    return true;
                }
            }
            return false;
        }
        throw new IndexOutOfBoundsException("i=" + i);
    }

    public SparseBitSet get(int i, int i2) throws IndexOutOfBoundsException {
        SparseBitSet sparseBitSet = new SparseBitSet(i2, this.compactionCount);
        sparseBitSet.setScanner(i, i2, this, copyStrategy);
        return sparseBitSet;
    }

    public int hashCode() {
        statisticsUpdate();
        return this.cache.hash;
    }

    public boolean intersects(int i, int i2, SparseBitSet sparseBitSet) throws IndexOutOfBoundsException {
        setScanner(i, i2, sparseBitSet, intersectsStrategy);
        return intersectsStrategy.result;
    }

    public boolean intersects(SparseBitSet sparseBitSet) {
        setScanner(0, Math.max(this.bitsLength, sparseBitSet.bitsLength), sparseBitSet, intersectsStrategy);
        return intersectsStrategy.result;
    }

    public boolean isEmpty() {
        statisticsUpdate();
        return this.cache.cardinality == 0;
    }

    public int length() {
        statisticsUpdate();
        return this.cache.length;
    }

    public int nextClearBit(int i) {
        long[][] jArr;
        long[] jArr2;
        if (i >= 0) {
            int i2 = i >> 6;
            int i3 = i2 & 31;
            int i4 = (i2 >> 5) & 31;
            int i5 = i2 >> 10;
            long j = -1;
            long j2 = -1 << i;
            long[][][] jArr3 = this.bits;
            int length = jArr3.length;
            if (!(i5 >= length || (jArr = jArr3[i5]) == null || (jArr2 = jArr[i4]) == null)) {
                j2 &= ~jArr2[i3];
                if (j2 == 0) {
                    int i6 = i2 + 1;
                    i5 = i6 >> 10;
                    i4 = (i6 >> 5) & 31;
                    i3 = i6 & 31;
                    loop0:
                    while (i5 != length) {
                        long[][] jArr4 = this.bits[i5];
                        if (jArr4 == null) {
                            break;
                        }
                        while (i4 != 32) {
                            long[] jArr5 = jArr4[i4];
                            if (jArr5 == null) {
                                break loop0;
                            }
                            while (i3 != 32) {
                                j = ~jArr5[i3];
                                if (j != 0) {
                                    break loop0;
                                }
                                i3++;
                            }
                            i4++;
                            i3 = 0;
                        }
                        i5++;
                        i3 = 0;
                        i4 = 0;
                    }
                    j2 = j;
                }
            }
            int numberOfTrailingZeros = ((((i5 << 10) + (i4 << 5)) + i3) << 6) + Long.numberOfTrailingZeros(j2);
            if (numberOfTrailingZeros == Integer.MAX_VALUE) {
                return -1;
            }
            return numberOfTrailingZeros;
        }
        throw new IndexOutOfBoundsException("i=" + i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        if (r8 == 0) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextSetBit(int r13) {
        /*
            r12 = this;
            if (r13 < 0) goto L_0x006d
            int r0 = r13 >> 6
            r1 = r0 & 31
            int r2 = r0 >> 5
            r2 = r2 & 31
            int r3 = r0 >> 10
            long[][][] r4 = r12.bits
            int r5 = r4.length
            r6 = 0
            if (r3 >= r5) goto L_0x005b
            r4 = r4[r3]
            if (r4 == 0) goto L_0x0026
            r4 = r4[r2]
            if (r4 == 0) goto L_0x0026
            r8 = r4[r1]
            r10 = -1
            long r10 = r10 << r13
            long r8 = r8 & r10
            int r13 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r13 != 0) goto L_0x005a
            goto L_0x0027
        L_0x0026:
            r8 = r6
        L_0x0027:
            int r0 = r0 + 1
            r13 = r0 & 31
            int r1 = r0 >> 5
            r1 = r1 & 31
            int r0 = r0 >> 10
            r3 = r0
            r2 = r1
            r1 = r13
        L_0x0034:
            if (r3 == r5) goto L_0x005a
            long[][][] r13 = r12.bits
            r13 = r13[r3]
            r0 = 0
            if (r13 == 0) goto L_0x0055
        L_0x003d:
            r4 = 32
            if (r2 == r4) goto L_0x0055
            r10 = r13[r2]
            if (r10 == 0) goto L_0x0051
        L_0x0045:
            if (r1 == r4) goto L_0x0051
            r8 = r10[r1]
            int r11 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r11 == 0) goto L_0x004e
            goto L_0x005a
        L_0x004e:
            int r1 = r1 + 1
            goto L_0x0045
        L_0x0051:
            int r2 = r2 + 1
            r1 = r0
            goto L_0x003d
        L_0x0055:
            int r3 = r3 + 1
            r1 = r0
            r2 = r1
            goto L_0x0034
        L_0x005a:
            r6 = r8
        L_0x005b:
            if (r3 < r5) goto L_0x005f
            r12 = -1
            goto L_0x006c
        L_0x005f:
            int r12 = r3 << 10
            int r13 = r2 << 5
            int r12 = r12 + r13
            int r12 = r12 + r1
            int r12 = r12 << 6
            int r13 = java.lang.Long.numberOfTrailingZeros(r6)
            int r12 = r12 + r13
        L_0x006c:
            return r12
        L_0x006d:
            java.lang.IndexOutOfBoundsException r12 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "i="
            r0.<init>(r1)
            java.lang.StringBuilder r13 = r0.append(r13)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zaxxer.sparsebits.SparseBitSet.nextSetBit(int):int");
    }

    public int previousClearBit(int i) {
        int i2 = i;
        if (i2 >= 0) {
            long[][][] jArr = this.bits;
            int i3 = i2 >> 6;
            int i4 = i3 & 31;
            int i5 = (i3 >> 5) & 31;
            int i6 = i3 >> 10;
            int length = jArr.length - 1;
            if (i6 > length) {
                return i2;
            }
            int min = Math.min(i6, length);
            int i7 = i2 % 64;
            int i8 = min;
            int i9 = i4;
            int i10 = i5;
            while (i8 >= 0) {
                long[][] jArr2 = jArr[i8];
                if (jArr2 == null) {
                    int i11 = (((i8 << 10) + (i10 << 5)) + i9) << 6;
                    if (min != i8) {
                        i7 = 63;
                    }
                    return i11 + i7;
                }
                while (i10 >= 0) {
                    long[] jArr3 = jArr2[i10];
                    if (jArr3 == null) {
                        int i12 = (((i8 << 10) + (i10 << 5)) + i9) << 6;
                        if (i5 != i10) {
                            i7 = 63;
                        }
                        return i12 + i7;
                    }
                    while (i9 >= 0) {
                        long j = jArr3[i9];
                        if (j == 0) {
                            int i13 = (((i8 << 10) + (i10 << 5)) + i9) << 6;
                            if (i4 != i9) {
                                i7 = 63;
                            }
                            return i13 + i7;
                        }
                        for (int i14 = i7; i14 >= 0; i14--) {
                            if ((j & (1 << i14)) == 0) {
                                return ((((i8 << 10) + (i10 << 5)) + i9) << 6) + i14;
                            }
                        }
                        i9--;
                    }
                    i10--;
                    i9 = 31;
                }
                i8--;
                i10 = 31;
                i9 = 31;
            }
            return -1;
        } else if (i2 == -1) {
            return -1;
        } else {
            throw new IndexOutOfBoundsException("i=" + i2);
        }
    }

    public int previousSetBit(int i) {
        int i2;
        int i3;
        int i4;
        int i5 = i;
        if (i5 >= 0) {
            long[][][] jArr = this.bits;
            int i6 = i5 >> 6;
            int i7 = i6 >> 10;
            boolean z = true;
            int length = jArr.length - 1;
            if (i7 > length) {
                i7 = length;
                i4 = 63;
                i3 = 31;
                i2 = 31;
            } else {
                i3 = (i6 >> 5) & 31;
                i2 = i6 & 31;
                i4 = i5 % 64;
            }
            while (i7 >= 0) {
                long[][] jArr2 = jArr[i7];
                if (jArr2 != null) {
                    while (i3 >= 0) {
                        long[] jArr3 = jArr2[i3];
                        if (jArr3 != null) {
                            while (i2 >= 0) {
                                long j = jArr3[i2];
                                if (j != 0) {
                                    for (int i8 = z ? i4 : 63; i8 >= 0; i8--) {
                                        if ((j & (1 << i8)) != 0) {
                                            return ((((i7 << 10) + (i3 << 5)) + i2) << 6) + i8;
                                        }
                                    }
                                    continue;
                                }
                                i2--;
                                z = false;
                            }
                            continue;
                        }
                        i3--;
                        i2 = 31;
                        z = false;
                    }
                    continue;
                }
                i7--;
                i3 = 31;
                i2 = 31;
                z = false;
            }
            return -1;
        } else if (i5 == -1) {
            return -1;
        } else {
            throw new IndexOutOfBoundsException("i=" + i5);
        }
    }

    public void or(int i, boolean z) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        } else if (z) {
            set(i);
        }
    }

    public void or(int i, int i2, SparseBitSet sparseBitSet) throws IndexOutOfBoundsException {
        setScanner(i, i2, sparseBitSet, orStrategy);
    }

    public void or(SparseBitSet sparseBitSet) {
        setScanner(0, sparseBitSet.bitsLength, sparseBitSet, orStrategy);
    }

    public static SparseBitSet or(SparseBitSet sparseBitSet, SparseBitSet sparseBitSet2) {
        SparseBitSet clone = sparseBitSet.clone();
        clone.or(sparseBitSet2);
        return clone;
    }

    public void set(int i) {
        if (i + 1 >= 1) {
            int i2 = i >> 6;
            int i3 = i2 >> 10;
            int i4 = (i2 >> 5) & 31;
            if (i >= this.bitsLength) {
                resize(i);
            }
            long[][][] jArr = this.bits;
            long[][] jArr2 = jArr[i3];
            if (jArr2 == null) {
                jArr2 = new long[32][];
                jArr[i3] = jArr2;
            }
            long[] jArr3 = jArr2[i4];
            if (jArr3 == null) {
                jArr3 = new long[32];
                jArr2[i4] = jArr3;
            }
            int i5 = i2 & 31;
            jArr3[i5] = jArr3[i5] | (1 << i);
            this.cache.hash = 0;
            return;
        }
        throw new IndexOutOfBoundsException("i=" + i);
    }

    public void set(int i, boolean z) {
        if (z) {
            set(i);
        } else {
            clear(i);
        }
    }

    public void set(int i, int i2) throws IndexOutOfBoundsException {
        setScanner(i, i2, (SparseBitSet) null, setStrategy);
    }

    public void set(int i, int i2, boolean z) {
        if (z) {
            set(i, i2);
        } else {
            clear(i, i2);
        }
    }

    public int size() {
        statisticsUpdate();
        return this.cache.size;
    }

    public String statistics() {
        return statistics((String[]) null);
    }

    public String statistics(String[] strArr) {
        statisticsUpdate();
        int length = Statistics.values().length;
        String[] strArr2 = new String[length];
        strArr2[Statistics.Size.ordinal()] = Integer.toString(size());
        strArr2[Statistics.Length.ordinal()] = Integer.toString(length());
        strArr2[Statistics.Cardinality.ordinal()] = Integer.toString(cardinality());
        strArr2[Statistics.Total_words.ordinal()] = Integer.toString(this.cache.count);
        strArr2[Statistics.Set_array_length.ordinal()] = Integer.toString(this.bits.length);
        strArr2[Statistics.Set_array_max_length.ordinal()] = Integer.toString(32768);
        strArr2[Statistics.Level2_areas.ordinal()] = Integer.toString(this.cache.a2Count);
        strArr2[Statistics.Level2_area_length.ordinal()] = Integer.toString(32);
        strArr2[Statistics.Level3_blocks.ordinal()] = Integer.toString(this.cache.a3Count);
        strArr2[Statistics.Level3_block_length.ordinal()] = Integer.toString(32);
        strArr2[Statistics.Compaction_count_value.ordinal()] = Integer.toString(this.compactionCount);
        int i = 0;
        for (Statistics name : Statistics.values()) {
            i = Math.max(i, name.name().length());
        }
        StringBuilder sb = new StringBuilder();
        for (Statistics statistics : Statistics.values()) {
            sb.append(statistics.name());
            for (int i2 = 0; i2 != i - statistics.name().length(); i2++) {
                sb.append(Chars.SPACE);
            }
            sb.append(" = ");
            sb.append(strArr2[statistics.ordinal()]);
            sb.append(10);
        }
        for (int i3 = 0; i3 != sb.length(); i3++) {
            if (sb.charAt(i3) == '_') {
                sb.setCharAt(i3, Chars.SPACE);
            }
        }
        if (strArr != null) {
            System.arraycopy(strArr2, 0, strArr, 0, Math.min(strArr.length, length));
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0011 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 200(0xc8, float:2.8E-43)
            r0.<init>(r1)
            r1 = 123(0x7b, float:1.72E-43)
            r0.append(r1)
            r1 = 0
            int r1 = r5.nextSetBit(r1)
        L_0x0011:
            if (r1 < 0) goto L_0x004a
            r0.append(r1)
            int r2 = r1 + 1
            int r2 = r5.nextSetBit(r2)
            int r3 = r5.compactionCount
            if (r3 <= 0) goto L_0x0041
            if (r2 >= 0) goto L_0x0023
            goto L_0x004a
        L_0x0023:
            int r3 = r5.nextClearBit(r1)
            if (r3 >= 0) goto L_0x002c
            r3 = 2147483647(0x7fffffff, float:NaN)
        L_0x002c:
            int r4 = r5.compactionCount
            int r1 = r1 + r4
            if (r1 >= r3) goto L_0x0041
            java.lang.String r1 = ".."
            java.lang.StringBuilder r1 = r0.append(r1)
            int r2 = r3 + -1
            r1.append(r2)
            int r1 = r5.nextSetBit(r3)
            goto L_0x0042
        L_0x0041:
            r1 = r2
        L_0x0042:
            if (r1 < 0) goto L_0x0011
            java.lang.String r2 = ", "
            r0.append(r2)
            goto L_0x0011
        L_0x004a:
            r5 = 125(0x7d, float:1.75E-43)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zaxxer.sparsebits.SparseBitSet.toString():java.lang.String");
    }

    public void toStringCompaction(int i) {
        this.compactionCount = i;
    }

    public void toStringCompaction(boolean z) {
        if (z) {
            compactionCountDefault = this.compactionCount;
        }
    }

    public void xor(int i, boolean z) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        } else if (z) {
            flip(i);
        }
    }

    public void xor(int i, int i2, SparseBitSet sparseBitSet) throws IndexOutOfBoundsException {
        setScanner(i, i2, sparseBitSet, xorStrategy);
    }

    public void xor(SparseBitSet sparseBitSet) {
        setScanner(0, sparseBitSet.bitsLength, sparseBitSet, xorStrategy);
    }

    public static SparseBitSet xor(SparseBitSet sparseBitSet, SparseBitSet sparseBitSet2) {
        SparseBitSet clone = sparseBitSet.clone();
        clone.xor(sparseBitSet2);
        return clone;
    }

    protected static void throwIndexOutOfBoundsException(int i, int i2) throws IndexOutOfBoundsException {
        String str;
        String str2 = "";
        String str3 = i < 0 ? "(i=" + i + ") < 0" : str2;
        if (i == Integer.MAX_VALUE) {
            str3 = str3 + "(i=" + i + ")";
        }
        if (i2 < 0) {
            StringBuilder append = new StringBuilder().append(str3);
            if (str3.isEmpty()) {
                str = str2;
            } else {
                str = ", ";
            }
            str3 = append.append(str).append("(j=").append(i2).append(") < 0").toString();
        }
        if (i > i2) {
            StringBuilder append2 = new StringBuilder().append(str3);
            if (!str3.isEmpty()) {
                str2 = ", ";
            }
            str3 = append2.append(str2).append("(i=").append(i).append(") > (j=").append(i2).append(")").toString();
        }
        throw new IndexOutOfBoundsException(str3);
    }

    /* access modifiers changed from: protected */
    public final void constructorHelper() {
        this.spare = new long[32];
        this.cache = new Cache();
        this.updateStrategy = new UpdateStrategy();
    }

    /* access modifiers changed from: protected */
    public final void nullify(int i) {
        int length = this.bits.length;
        if (i < length) {
            while (i != length) {
                long[][] jArr = null;
                this.bits[i] = null;
                i++;
            }
            this.cache.hash = 0;
        }
    }

    /* access modifiers changed from: protected */
    public final void resize(int i) {
        int i2 = (i >> 6) >> 10;
        int highestOneBit = Integer.highestOneBit(i2);
        if (highestOneBit == 0) {
            highestOneBit = 1;
        }
        if (i2 >= highestOneBit) {
            highestOneBit <<= 1;
        }
        if (highestOneBit > 32768) {
            highestOneBit = 32768;
        }
        long[][][] jArr = this.bits;
        int length = jArr != null ? jArr.length : 0;
        if (highestOneBit != length || jArr == null) {
            long[][][] jArr2 = new long[highestOneBit][][];
            if (length != 0) {
                System.arraycopy(jArr, 0, jArr2, 0, Math.min(length, highestOneBit));
                nullify(0);
            }
            this.bits = jArr2;
            this.bitsLength = highestOneBit == 32768 ? Integer.MAX_VALUE : 65536 * highestOneBit;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0170 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0265  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02cd A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x02ed  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x02f0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0131 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setScanner(int r49, int r50, com.zaxxer.sparsebits.SparseBitSet r51, com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy r52) throws java.lang.IndexOutOfBoundsException {
        /*
            r48 = this;
            r0 = r48
            r1 = r49
            r2 = r50
            r3 = r51
            r10 = r52
            boolean r4 = r10.start(r3)
            r11 = 0
            if (r4 == 0) goto L_0x0015
            com.zaxxer.sparsebits.SparseBitSet$Cache r4 = r0.cache
            r4.hash = r11
        L_0x0015:
            r12 = 1
            if (r2 < r1) goto L_0x001c
            int r4 = r1 + 1
            if (r4 >= r12) goto L_0x001f
        L_0x001c:
            throwIndexOutOfBoundsException(r49, r50)
        L_0x001f:
            if (r1 != r2) goto L_0x0022
            return
        L_0x0022:
            int r4 = r52.properties()
            r5 = r4 & 1
            if (r5 == 0) goto L_0x002c
            r13 = r12
            goto L_0x002d
        L_0x002c:
            r13 = r11
        L_0x002d:
            r5 = r4 & 2
            if (r5 == 0) goto L_0x0033
            r14 = r12
            goto L_0x0034
        L_0x0033:
            r14 = r11
        L_0x0034:
            r5 = r4 & 4
            if (r5 == 0) goto L_0x003a
            r15 = r12
            goto L_0x003b
        L_0x003a:
            r15 = r11
        L_0x003b:
            r4 = r4 & 8
            if (r4 == 0) goto L_0x0042
            r16 = r12
            goto L_0x0044
        L_0x0042:
            r16 = r11
        L_0x0044:
            int r4 = r1 >> 6
            r5 = -1
            long r17 = r5 << r1
            int r7 = r2 + -1
            int r9 = r7 >> 6
            int r7 = -r2
            long r19 = r5 >>> r7
            long[][][] r7 = r0.bits
            int r8 = r7.length
            r21 = 0
            if (r3 == 0) goto L_0x005b
            long[][][] r11 = r3.bits
            goto L_0x0061
        L_0x005b:
            r11 = r21
            long[][][] r11 = (long[][][]) r11
            r11 = r21
        L_0x0061:
            if (r11 == 0) goto L_0x0067
            long[][][] r3 = r3.bits
            int r3 = r3.length
            goto L_0x0068
        L_0x0067:
            r3 = 0
        L_0x0068:
            int r22 = r4 >> 10
            int r23 = r4 >> 5
            r23 = r23 & 31
            r24 = r4 & 31
            int r12 = r9 >> 10
            int r26 = r9 >> 5
            r26 = r26 & 31
            r27 = r9 & 31
            int r28 = r12 << 5
            int r1 = r28 + r26
            if (r4 != 0) goto L_0x0084
            int r5 = (r17 > r5 ? 1 : (r17 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x0084
            r5 = 1
            goto L_0x0085
        L_0x0084:
            r5 = 0
        L_0x0085:
            if (r23 != 0) goto L_0x0089
            r6 = 1
            goto L_0x008a
        L_0x0089:
            r6 = 0
        L_0x008a:
            r51 = r4
            r4 = r7
            r7 = 0
            r29 = 0
            r46 = r8
            r8 = r49
            r49 = r6
            r6 = r46
            r47 = r22
            r22 = r9
            r9 = r47
        L_0x009e:
            if (r8 >= r2) goto L_0x0307
            r28 = r21
            long[][] r28 = (long[][]) r28
            if (r9 >= r6) goto L_0x00ad
            r28 = r4[r9]
            if (r28 == 0) goto L_0x00af
            r30 = 1
            goto L_0x00b1
        L_0x00ad:
            r28 = r21
        L_0x00af:
            r30 = 0
        L_0x00b1:
            if (r9 >= r3) goto L_0x00bc
            if (r11 == 0) goto L_0x00bc
            r31 = r11[r9]
            if (r31 == 0) goto L_0x00be
            r32 = 1
            goto L_0x00c0
        L_0x00bc:
            r31 = r21
        L_0x00be:
            r32 = 0
        L_0x00c0:
            if (r30 != 0) goto L_0x00c6
            if (r32 != 0) goto L_0x00c6
            if (r13 != 0) goto L_0x00ce
        L_0x00c6:
            if (r30 != 0) goto L_0x00ca
            if (r14 != 0) goto L_0x00ce
        L_0x00ca:
            if (r32 != 0) goto L_0x00e7
            if (r15 == 0) goto L_0x00e7
        L_0x00ce:
            if (r5 == 0) goto L_0x00e7
            if (r9 == r12) goto L_0x00e7
            if (r9 >= r6) goto L_0x00d6
            r4[r9] = r21
        L_0x00d6:
            r30 = r49
            r51 = r1
            r39 = r3
            r38 = r11
            r44 = r22
            r25 = 1
            r22 = r12
            r12 = r9
            goto L_0x02e5
        L_0x00e7:
            r33 = r6
            r25 = 1
            if (r9 != r12) goto L_0x00f2
            int r34 = r26 + 1
            r6 = r34
            goto L_0x00f4
        L_0x00f2:
            r6 = 32
        L_0x00f4:
            r35 = r28
            r36 = r30
            r30 = r49
            r28 = r24
            r24 = r5
            r5 = r23
            r23 = r4
            r46 = r33
            r33 = r7
            r7 = r46
        L_0x0108:
            if (r5 == r6) goto L_0x02bb
            if (r36 == 0) goto L_0x0113
            r4 = r35[r5]
            if (r4 == 0) goto L_0x0115
            r37 = r25
            goto L_0x0117
        L_0x0113:
            r4 = r21
        L_0x0115:
            r37 = 0
        L_0x0117:
            if (r32 == 0) goto L_0x0120
            r38 = r31[r5]
            if (r38 == 0) goto L_0x0122
            r39 = r25
            goto L_0x0124
        L_0x0120:
            r38 = r21
        L_0x0122:
            r39 = 0
        L_0x0124:
            int r40 = r9 << 5
            int r2 = r40 + r5
            if (r1 == r2) goto L_0x012d
            r40 = r25
            goto L_0x012f
        L_0x012d:
            r40 = 0
        L_0x012f:
            if (r37 != 0) goto L_0x0135
            if (r39 != 0) goto L_0x0135
            if (r13 != 0) goto L_0x013d
        L_0x0135:
            if (r37 != 0) goto L_0x0139
            if (r14 != 0) goto L_0x013d
        L_0x0139:
            if (r39 != 0) goto L_0x015a
            if (r15 == 0) goto L_0x015a
        L_0x013d:
            if (r24 == 0) goto L_0x015a
            if (r40 == 0) goto L_0x015a
            if (r36 == 0) goto L_0x0145
            r35[r5] = r21
        L_0x0145:
            r45 = r51
            r51 = r1
            r39 = r3
            r34 = r6
            r1 = r8
            r38 = r11
            r44 = r22
            r2 = 32
            r11 = r5
            r22 = r12
            r12 = r9
            goto L_0x02a3
        L_0x015a:
            int r2 = r2 << 5
            if (r40 == 0) goto L_0x0161
            r49 = 32
            goto L_0x0163
        L_0x0161:
            r49 = r27
        L_0x0163:
            if (r37 != 0) goto L_0x0167
            long[] r4 = r0.spare
        L_0x0167:
            if (r39 != 0) goto L_0x016c
            long[] r37 = ZERO_BLOCK
            goto L_0x016e
        L_0x016c:
            r37 = r38
        L_0x016e:
            if (r24 == 0) goto L_0x01bd
            if (r40 == 0) goto L_0x01bd
            if (r16 == 0) goto L_0x018c
            if (r39 != 0) goto L_0x018c
            boolean r2 = r10.isZeroBlock(r4)
            r45 = r51
            r51 = r1
            r39 = r3
            r34 = r6
            r41 = r7
            r1 = r8
            r38 = r11
            r44 = r22
            r3 = r2
            r11 = r5
            goto L_0x01b6
        L_0x018c:
            r28 = 0
            r38 = 32
            r39 = r3
            r3 = r52
            r40 = r4
            r4 = r2
            r2 = r5
            r5 = r28
            r34 = r6
            r6 = r38
            r42 = r51
            r41 = r7
            r7 = r40
            r51 = r1
            r1 = r8
            r8 = r37
            boolean r3 = r3.block(r4, r5, r6, r7, r8)
            r38 = r11
            r44 = r22
            r4 = r40
            r45 = r42
            r11 = r2
        L_0x01b6:
            r22 = r12
            r2 = 32
            r12 = r9
            goto L_0x0263
        L_0x01bd:
            r42 = r51
            r51 = r1
            r39 = r3
            r40 = r4
            r34 = r6
            r41 = r7
            r1 = r8
            r8 = r5
            if (r24 == 0) goto L_0x01f9
            r5 = 0
            r3 = r52
            r4 = r2
            r6 = r49
            r7 = r40
            r38 = r11
            r11 = r8
            r8 = r37
            boolean r28 = r3.block(r4, r5, r6, r7, r8)
            r5 = r49
            r6 = r40
            r7 = r37
            r2 = r22
            r22 = r12
            r12 = r9
            r8 = r19
            boolean r3 = r3.word(r4, r5, r6, r7, r8)
            r3 = r28 & r3
            r44 = r2
            r45 = r42
            r2 = 32
            goto L_0x025b
        L_0x01f9:
            r38 = r11
            r11 = r8
            r8 = r22
            r22 = r12
            r12 = r9
            r9 = r42
            if (r9 != r8) goto L_0x021e
            long r42 = r17 & r19
            r3 = r52
            r4 = r2
            r5 = r28
            r6 = r40
            r7 = r37
            r44 = r8
            r45 = r9
            r8 = r42
            boolean r2 = r3.word(r4, r5, r6, r7, r8)
            r3 = r2
            r2 = 32
            goto L_0x0259
        L_0x021e:
            r44 = r8
            r45 = r9
            r3 = r52
            r4 = r2
            r5 = r28
            r6 = r40
            r7 = r37
            r8 = r17
            boolean r9 = r3.word(r4, r5, r6, r7, r8)
            int r5 = r28 + 1
            r6 = r49
            r7 = r40
            r8 = r37
            boolean r3 = r3.block(r4, r5, r6, r7, r8)
            r24 = r9 & r3
            r5 = r49
            r8 = 32
            if (r5 == r8) goto L_0x0256
            r3 = r52
            r4 = r2
            r6 = r40
            r7 = r37
            r2 = r8
            r8 = r19
            boolean r3 = r3.word(r4, r5, r6, r7, r8)
            r3 = r24 & r3
            goto L_0x0259
        L_0x0256:
            r2 = r8
            r3 = r24
        L_0x0259:
            r24 = r25
        L_0x025b:
            r4 = r40
            if (r3 == 0) goto L_0x0263
            boolean r3 = r10.isZeroBlock(r4)
        L_0x0263:
            if (r3 == 0) goto L_0x026c
            if (r36 == 0) goto L_0x0269
            r35[r11] = r21
        L_0x0269:
            r7 = r41
            goto L_0x0294
        L_0x026c:
            long[] r3 = r0.spare
            if (r4 != r3) goto L_0x0290
            int r3 = r0.bitsLength
            if (r1 < r3) goto L_0x027d
            r0.resize(r1)
            long[][][] r3 = r0.bits
            int r7 = r3.length
            r23 = r3
            goto L_0x027f
        L_0x027d:
            r7 = r41
        L_0x027f:
            if (r35 != 0) goto L_0x0289
            long[][] r3 = new long[r2][]
            r23[r12] = r3
            r35 = r3
            r36 = r25
        L_0x0289:
            r35[r11] = r4
            long[] r3 = new long[r2]
            r0.spare = r3
            goto L_0x0292
        L_0x0290:
            r7 = r41
        L_0x0292:
            int r33 = r33 + 1
        L_0x0294:
            if (r36 == 0) goto L_0x029d
            r3 = r35[r11]
            if (r3 != 0) goto L_0x029b
            goto L_0x029d
        L_0x029b:
            r3 = 0
            goto L_0x029f
        L_0x029d:
            r3 = r25
        L_0x029f:
            r3 = r30 & r3
            r30 = r3
        L_0x02a3:
            int r5 = r11 + 1
            r2 = r50
            r8 = r1
            r9 = r12
            r12 = r22
            r6 = r34
            r11 = r38
            r3 = r39
            r22 = r44
            r28 = 0
            r1 = r51
            r51 = r45
            goto L_0x0108
        L_0x02bb:
            r51 = r1
            r39 = r3
            r41 = r7
            r38 = r11
            r44 = r22
            r2 = 32
            r11 = r5
            r22 = r12
            r12 = r9
            if (r11 != r2) goto L_0x02d6
            if (r30 == 0) goto L_0x02d6
            r7 = r41
            if (r12 >= r7) goto L_0x02d8
            r23[r12] = r21
            goto L_0x02dc
        L_0x02d6:
            r7 = r41
        L_0x02d8:
            r1 = r29
            int r29 = r1 + 1
        L_0x02dc:
            r6 = r7
            r4 = r23
            r5 = r24
            r24 = r28
            r7 = r33
        L_0x02e5:
            int r9 = r12 + 1
            int r1 = r9 << 10
            int r2 = r1 << 6
            if (r2 >= 0) goto L_0x02f0
            r2 = 2147483647(0x7fffffff, float:NaN)
        L_0x02f0:
            r8 = r2
            r2 = r50
            r12 = r22
            r49 = r30
            r11 = r38
            r3 = r39
            r22 = r44
            r23 = 0
            r46 = r1
            r1 = r51
            r51 = r46
            goto L_0x009e
        L_0x0307:
            r1 = r29
            r10.finish(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zaxxer.sparsebits.SparseBitSet.setScanner(int, int, com.zaxxer.sparsebits.SparseBitSet, com.zaxxer.sparsebits.SparseBitSet$AbstractStrategy):void");
    }

    /* access modifiers changed from: protected */
    public final void statisticsUpdate() {
        if (this.cache.hash == 0) {
            setScanner(0, this.bitsLength, (SparseBitSet) null, this.updateStrategy);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException, InternalError {
        ObjectOutputStream objectOutputStream2 = objectOutputStream;
        statisticsUpdate();
        objectOutputStream.defaultWriteObject();
        objectOutputStream2.writeInt(this.compactionCount);
        objectOutputStream2.writeInt(this.cache.length);
        int i = this.cache.count;
        objectOutputStream2.writeInt(i);
        long[][][] jArr = this.bits;
        int length = jArr.length;
        for (int i2 = 0; i2 != length; i2++) {
            long[][] jArr2 = jArr[i2];
            if (jArr2 != null) {
                for (int i3 = 0; i3 != 32; i3++) {
                    long[] jArr3 = jArr2[i3];
                    if (jArr3 != null) {
                        int i4 = (i2 << 10) + (i3 << 5);
                        for (int i5 = 0; i5 != 32; i5++) {
                            long j = jArr3[i5];
                            if (j != 0) {
                                objectOutputStream2.writeInt(i4 + i5);
                                objectOutputStream2.writeLong(j);
                                i--;
                            }
                        }
                    }
                }
            }
        }
        if (i == 0) {
            objectOutputStream2.writeInt(this.cache.hash);
            return;
        }
        throw new InternalError("count of entries not consistent");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.compactionCount = objectInputStream.readInt();
        resize(objectInputStream.readInt());
        int readInt = objectInputStream.readInt();
        for (int i = 0; i != readInt; i++) {
            int readInt2 = objectInputStream.readInt();
            int i2 = readInt2 & 31;
            int i3 = (readInt2 >> 5) & 31;
            int i4 = readInt2 >> 10;
            long readLong = objectInputStream.readLong();
            long[][][] jArr = this.bits;
            long[][] jArr2 = jArr[i4];
            if (jArr2 == null) {
                jArr2 = new long[32][];
                jArr[i4] = jArr2;
            }
            long[] jArr3 = jArr2[i3];
            if (jArr3 == null) {
                jArr3 = new long[32];
                jArr2[i3] = jArr3;
            }
            jArr3[i2] = readLong;
        }
        constructorHelper();
        statisticsUpdate();
        if (readInt != this.cache.count) {
            throw new InternalError("count of entries not consistent");
        } else if (objectInputStream.readInt() != this.cache.hash) {
            throw new IOException("deserialized hashCode mis-match");
        }
    }

    protected class Cache {
        protected transient int a2Count;
        protected transient int a3Count;
        protected transient int cardinality;
        protected transient int count;
        protected transient int hash;
        protected transient int length;
        protected transient int size;

        protected Cache() {
        }
    }

    protected static abstract class AbstractStrategy {
        static final int F_OP_F_EQ_F = 1;
        static final int F_OP_X_EQ_F = 2;
        static final int X_OP_F_EQ_F = 4;
        static final int X_OP_F_EQ_X = 8;

        /* access modifiers changed from: protected */
        public abstract boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2);

        /* access modifiers changed from: protected */
        public void finish(int i, int i2) {
        }

        /* access modifiers changed from: protected */
        public abstract int properties();

        /* access modifiers changed from: protected */
        public abstract boolean start(SparseBitSet sparseBitSet);

        /* access modifiers changed from: protected */
        public abstract boolean word(int i, int i2, long[] jArr, long[] jArr2, long j);

        protected AbstractStrategy() {
        }

        /* access modifiers changed from: protected */
        public final boolean isZeroBlock(long[] jArr) {
            for (long j : jArr) {
                if (j != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    protected static class AndStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 7;
        }

        protected AndStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            sparseBitSet.getClass();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2] & ((~j) | jArr2[i2]);
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr[i2] & jArr2[i2];
                jArr[i2] = j;
                z &= j == 0;
                i2++;
            }
            return z;
        }
    }

    protected static class AndNotStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 11;
        }

        protected AndNotStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            sparseBitSet.getClass();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2] & (~(jArr2[i2] & j));
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr[i2] & (~jArr2[i2]);
                jArr[i2] = j;
                z &= j == 0;
                i2++;
            }
            return z;
        }
    }

    protected static class ClearStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 3;
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            return true;
        }

        protected ClearStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2] & (~j);
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            if (i2 == 0 && i3 == 32) {
                return true;
            }
            while (i2 != i3) {
                jArr[i2] = 0;
                i2++;
            }
            return true;
        }
    }

    protected static class CopyStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 5;
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            return true;
        }

        protected CopyStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr2[i2] & j;
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr2[i2];
                jArr[i2] = j;
                z &= j == 0;
                i2++;
            }
            return z;
        }
    }

    protected static class EqualsStrategy extends AbstractStrategy {
        boolean result;

        /* access modifiers changed from: protected */
        public int properties() {
            return 1;
        }

        protected EqualsStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            sparseBitSet.getClass();
            this.result = true;
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2];
            this.result &= (j2 & j) == (jArr2[i2] & j);
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr[i2];
                boolean z2 = false;
                this.result &= j == jArr2[i2];
                if (j == 0) {
                    z2 = true;
                }
                z &= z2;
                i2++;
            }
            return z;
        }
    }

    protected static class FlipStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            return true;
        }

        protected FlipStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2] ^ j;
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = ~jArr[i2];
                jArr[i2] = j;
                z &= j == 0;
                i2++;
            }
            return z;
        }
    }

    protected static class IntersectsStrategy extends AbstractStrategy {
        protected boolean result;

        /* access modifiers changed from: protected */
        public int properties() {
            return 3;
        }

        protected IntersectsStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            sparseBitSet.getClass();
            this.result = false;
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2];
            this.result |= ((jArr2[i2] & j2) & j) != 0;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr[i2];
                boolean z2 = false;
                this.result |= (jArr2[i2] & j) != 0;
                if (j == 0) {
                    z2 = true;
                }
                z &= z2;
                i2++;
            }
            return z;
        }
    }

    protected static class OrStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 9;
        }

        protected OrStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            sparseBitSet.getClass();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2] | (jArr2[i2] & j);
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr[i2] | jArr2[i2];
                jArr[i2] = j;
                z &= j == 0;
                i2++;
            }
            return z;
        }
    }

    protected static class SetStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            return true;
        }

        protected SetStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            jArr[i2] = jArr[i2] | j;
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            while (i2 != i3) {
                jArr[i2] = -1;
                i2++;
            }
            return false;
        }
    }

    protected class UpdateStrategy extends AbstractStrategy {
        protected transient int cardinality;
        protected transient int count;
        protected transient long hash;
        protected transient int wMax;
        protected transient int wMin;
        protected transient long wordMax;
        protected transient long wordMin;

        /* access modifiers changed from: protected */
        public int properties() {
            return 3;
        }

        protected UpdateStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            this.hash = 1234;
            this.wMin = -1;
            this.wordMin = 0;
            this.wMax = 0;
            this.wordMax = 0;
            this.count = 0;
            this.cardinality = 0;
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2];
            long j3 = j & j2;
            if (j3 != 0) {
                compute(i + i2, j3);
            }
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            for (int i4 = 0; i4 != i3; i4++) {
                long j = jArr[i4];
                if (j != 0) {
                    compute(i + i4, j);
                    z = false;
                }
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public void finish(int i, int i2) {
            SparseBitSet.this.cache.a2Count = i;
            SparseBitSet.this.cache.a3Count = i2;
            SparseBitSet.this.cache.count = this.count;
            SparseBitSet.this.cache.cardinality = this.cardinality;
            SparseBitSet.this.cache.length = ((this.wMax + 1) * 64) - Long.numberOfLeadingZeros(this.wordMax);
            SparseBitSet.this.cache.size = (SparseBitSet.this.cache.length - (this.wMin * 64)) - Long.numberOfTrailingZeros(this.wordMin);
            Cache cache = SparseBitSet.this.cache;
            long j = this.hash;
            cache.hash = (int) (j ^ (j >> 32));
        }

        private void compute(int i, long j) {
            this.count++;
            this.hash ^= ((long) (i + 1)) * j;
            if (this.wMin < 0) {
                this.wMin = i;
                this.wordMin = j;
            }
            this.wMax = i;
            this.wordMax = j;
            this.cardinality += Long.bitCount(j);
        }
    }

    protected static class XorStrategy extends AbstractStrategy {
        /* access modifiers changed from: protected */
        public int properties() {
            return 9;
        }

        protected XorStrategy() {
        }

        /* access modifiers changed from: protected */
        public boolean start(SparseBitSet sparseBitSet) {
            sparseBitSet.getClass();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean word(int i, int i2, long[] jArr, long[] jArr2, long j) {
            long j2 = jArr[i2] ^ (jArr2[i2] & j);
            jArr[i2] = j2;
            return j2 == 0;
        }

        /* access modifiers changed from: protected */
        public boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2) {
            boolean z = true;
            while (i2 != i3) {
                long j = jArr[i2] ^ jArr2[i2];
                jArr[i2] = j;
                z &= j == 0;
                i2++;
            }
            return z;
        }
    }
}
