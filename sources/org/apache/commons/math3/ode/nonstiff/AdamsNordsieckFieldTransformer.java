package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.util.MathArrays;

public class AdamsNordsieckFieldTransformer<T extends RealFieldElement<T>> {
    private static final Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> CACHE = new HashMap();
    private final T[] c1;
    private final Field<T> field;
    private final Array2DRowFieldMatrix<T> update;

    private AdamsNordsieckFieldTransformer(Field<T> field2, int i) {
        this.field = field2;
        int i2 = i - 1;
        FieldMatrix buildP = buildP(i2);
        FieldDecompositionSolver solver = new FieldLUDecomposition(buildP).getSolver();
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(field2, i2);
        Arrays.fill(realFieldElementArr, field2.getOne());
        this.c1 = (RealFieldElement[]) solver.solve(new ArrayFieldVector((T[]) realFieldElementArr, false)).toArray();
        RealFieldElement[][] realFieldElementArr2 = (RealFieldElement[][]) buildP.getData();
        for (int length = realFieldElementArr2.length - 1; length > 0; length--) {
            realFieldElementArr2[length] = realFieldElementArr2[length - 1];
        }
        RealFieldElement[] realFieldElementArr3 = (RealFieldElement[]) MathArrays.buildArray(field2, i2);
        realFieldElementArr2[0] = realFieldElementArr3;
        Arrays.fill(realFieldElementArr3, field2.getZero());
        this.update = new Array2DRowFieldMatrix<>((T[][]) solver.solve(new Array2DRowFieldMatrix((T[][]) realFieldElementArr2, false)).getData());
    }

    public static <T extends RealFieldElement<T>> AdamsNordsieckFieldTransformer<T> getInstance(Field<T> field2, int i) {
        AdamsNordsieckFieldTransformer<T> adamsNordsieckFieldTransformer;
        Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> map = CACHE;
        synchronized (map) {
            Map map2 = map.get(Integer.valueOf(i));
            if (map2 == null) {
                map2 = new HashMap();
                map.put(Integer.valueOf(i), map2);
            }
            adamsNordsieckFieldTransformer = (AdamsNordsieckFieldTransformer) map2.get(field2);
            if (adamsNordsieckFieldTransformer == null) {
                adamsNordsieckFieldTransformer = new AdamsNordsieckFieldTransformer<>(field2, i);
                map2.put(field2, adamsNordsieckFieldTransformer);
            }
        }
        return adamsNordsieckFieldTransformer;
    }

    private FieldMatrix<T> buildP(int i) {
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(this.field, i, i);
        for (int i2 = 1; i2 <= realFieldElementArr.length; i2++) {
            RealFieldElement[] realFieldElementArr2 = realFieldElementArr[i2 - 1];
            int i3 = -i2;
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) this.field.getZero()).add((double) i3);
            int i4 = 1;
            while (i4 <= realFieldElementArr2.length) {
                int i5 = i4 - 1;
                i4++;
                realFieldElementArr2[i5] = (RealFieldElement) realFieldElement.multiply(i4);
                realFieldElement = (RealFieldElement) realFieldElement.multiply(i3);
            }
        }
        return new Array2DRowFieldMatrix((T[][]) realFieldElementArr, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T r18, T[] r19, T[][] r20, T[][] r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r20
            org.apache.commons.math3.Field<T> r2 = r0.field
            T[] r3 = r0.c1
            int r4 = r3.length
            r5 = 1
            int r4 = r4 + r5
            int r3 = r3.length
            int r3 = r3 + r5
            java.lang.Object[][] r2 = org.apache.commons.math3.util.MathArrays.buildArray(r2, r4, r3)
            org.apache.commons.math3.RealFieldElement[][] r2 = (org.apache.commons.math3.RealFieldElement[][]) r2
            org.apache.commons.math3.Field<T> r3 = r0.field
            T[] r4 = r0.c1
            int r4 = r4.length
            int r4 = r4 + r5
            r6 = 0
            r7 = r1[r6]
            int r7 = r7.length
            java.lang.Object[][] r3 = org.apache.commons.math3.util.MathArrays.buildArray(r3, r4, r7)
            org.apache.commons.math3.RealFieldElement[][] r3 = (org.apache.commons.math3.RealFieldElement[][]) r3
            r4 = r1[r6]
            r7 = r21[r6]
            r8 = r5
        L_0x0028:
            int r9 = r1.length
            if (r8 >= r9) goto L_0x00b3
            r9 = r19[r8]
            r10 = r19[r6]
            java.lang.Object r9 = r9.subtract(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = r18
            java.lang.Object r11 = r9.divide(r10)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            java.lang.Object r12 = r18.reciprocal()
            org.apache.commons.math3.RealFieldElement r12 = (org.apache.commons.math3.RealFieldElement) r12
            int r13 = r8 * 2
            int r14 = r13 + -2
            r15 = r2[r14]
            int r13 = r13 - r5
            int r5 = r2.length
            r16 = 0
            if (r13 >= r5) goto L_0x0052
            r5 = r2[r13]
            goto L_0x0054
        L_0x0052:
            r5 = r16
        L_0x0054:
            int r10 = r15.length
            if (r6 >= r10) goto L_0x0075
            java.lang.Object r10 = r12.multiply(r11)
            r12 = r10
            org.apache.commons.math3.RealFieldElement r12 = (org.apache.commons.math3.RealFieldElement) r12
            java.lang.Object r10 = r9.multiply(r12)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r15[r6] = r10
            if (r5 == 0) goto L_0x0072
            int r10 = r6 + 2
            java.lang.Object r10 = r12.multiply((int) r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r5[r6] = r10
        L_0x0072:
            int r6 = r6 + 1
            goto L_0x0054
        L_0x0075:
            r5 = r1[r8]
            r6 = r21[r8]
            r10 = r3[r14]
            int r11 = r3.length
            if (r13 >= r11) goto L_0x0080
            r16 = r3[r13]
        L_0x0080:
            r11 = 0
        L_0x0081:
            int r12 = r5.length
            if (r11 >= r12) goto L_0x00ad
            r12 = r5[r11]
            r13 = r4[r11]
            java.lang.Object r12 = r12.subtract(r13)
            org.apache.commons.math3.RealFieldElement r12 = (org.apache.commons.math3.RealFieldElement) r12
            r13 = r7[r11]
            java.lang.Object r13 = r9.multiply(r13)
            java.lang.Object r12 = r12.subtract(r13)
            org.apache.commons.math3.RealFieldElement r12 = (org.apache.commons.math3.RealFieldElement) r12
            r10[r11] = r12
            if (r16 == 0) goto L_0x00aa
            r12 = r6[r11]
            r13 = r7[r11]
            java.lang.Object r12 = r12.subtract(r13)
            org.apache.commons.math3.RealFieldElement r12 = (org.apache.commons.math3.RealFieldElement) r12
            r16[r11] = r12
        L_0x00aa:
            int r11 = r11 + 1
            goto L_0x0081
        L_0x00ad:
            int r8 = r8 + 1
            r5 = 1
            r6 = 0
            goto L_0x0028
        L_0x00b3:
            org.apache.commons.math3.linear.FieldLUDecomposition r1 = new org.apache.commons.math3.linear.FieldLUDecomposition
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r4 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            r5 = 0
            r4.<init>((T[][]) r2, (boolean) r5)
            r1.<init>(r4)
            org.apache.commons.math3.linear.FieldDecompositionSolver r1 = r1.getSolver()
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r2 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            r2.<init>((T[][]) r3, (boolean) r5)
            org.apache.commons.math3.linear.FieldMatrix r1 = r1.solve(r2)
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r2 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            org.apache.commons.math3.Field<T> r0 = r0.field
            int r3 = r1.getRowDimension()
            r4 = 1
            int r3 = r3 - r4
            int r4 = r1.getColumnDimension()
            r2.<init>(r0, (int) r3, (int) r4)
            r0 = r5
        L_0x00dd:
            int r3 = r2.getRowDimension()
            if (r0 >= r3) goto L_0x00f7
            r3 = r5
        L_0x00e4:
            int r4 = r2.getColumnDimension()
            if (r3 >= r4) goto L_0x00f4
            org.apache.commons.math3.FieldElement r4 = r1.getEntry(r0, r3)
            r2.setEntry(r0, r3, r4)
            int r3 = r3 + 1
            goto L_0x00e4
        L_0x00f4:
            int r0 = r0 + 1
            goto L_0x00dd
        L_0x00f7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives(org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.RealFieldElement[], org.apache.commons.math3.RealFieldElement[][], org.apache.commons.math3.RealFieldElement[][]):org.apache.commons.math3.linear.Array2DRowFieldMatrix");
    }

    public Array2DRowFieldMatrix<T> updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) {
        return this.update.multiply(array2DRowFieldMatrix);
    }

    public void updateHighOrderDerivativesPhase2(T[] tArr, T[] tArr2, Array2DRowFieldMatrix<T> array2DRowFieldMatrix) {
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) array2DRowFieldMatrix.getDataRef();
        for (int i = 0; i < realFieldElementArr.length; i++) {
            RealFieldElement[] realFieldElementArr2 = realFieldElementArr[i];
            T t = this.c1[i];
            for (int i2 = 0; i2 < realFieldElementArr2.length; i2++) {
                realFieldElementArr2[i2] = (RealFieldElement) realFieldElementArr2[i2].add(t.multiply(tArr[i2].subtract(tArr2[i2])));
            }
        }
    }
}
