package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;

public class DormandPrince853FieldIntegrator<T extends RealFieldElement<T>> extends EmbeddedRungeKuttaFieldIntegrator<T> {
    private static final String METHOD_NAME = "Dormand-Prince 8 (5, 3)";
    private final T e1_01 = fraction(1.16092271E8d, 8.84846592E9d);
    private final T e1_06 = fraction(-1871647.0d, 1527680.0d);
    private final T e1_07 = fraction(-6.9799717E7d, 1.4079366E8d);
    private final T e1_08 = fraction(1.230164450203E12d, 7.39113984E11d);
    private final T e1_09 = fraction(-1.980813971228885E15d, 5.654156025964544E15d);
    private final T e1_10 = fraction(4.64500805E8d, 1.389975552E9d);
    private final T e1_11 = fraction(1.606764981773E12d, 1.9613062656E13d);
    private final T e1_12 = fraction(-137909.0d, 6168960.0d);
    private final T e2_01 = fraction(-364463.0d, 1920240.0d);
    private final T e2_06 = fraction(3399327.0d, 763840.0d);
    private final T e2_07 = fraction(6.6578432E7d, 3.5198415E7d);
    private final T e2_08 = fraction(-1.674902723E9d, 2.887164E8d);
    private final T e2_09 = fraction(-7.4684743568175E13d, 1.76692375811392E14d);
    private final T e2_10 = fraction(-734375.0d, 4826304.0d);
    private final T e2_11 = fraction(1.71414593E8d, 8.512614E8d);
    private final T e2_12 = fraction(69869.0d, 3084480.0d);

    public int getOrder() {
        return 8;
    }

    public DormandPrince853FieldIntegrator(Field<T> field, double d, double d2, double d3, double d4) {
        super(field, METHOD_NAME, 12, d, d2, d3, d4);
    }

    public DormandPrince853FieldIntegrator(Field<T> field, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, METHOD_NAME, 12, d, d2, dArr, dArr2);
    }

    public T[] getC() {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getOne()).multiply(6)).sqrt();
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 15);
        tArr[0] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(-6.0d)).divide(-67.5d);
        tArr[1] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(-6.0d)).divide(-45.0d);
        tArr[2] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(-6.0d)).divide(-30.0d);
        tArr[3] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(6.0d)).divide(30.0d);
        tArr[4] = fraction(1, 3);
        tArr[5] = fraction(1, 4);
        tArr[6] = fraction(4, 13);
        tArr[7] = fraction(127, (int) HSSFShapeTypes.ActionButtonEnd);
        tArr[8] = fraction(3, 5);
        tArr[9] = fraction(6, 7);
        tArr[10] = (RealFieldElement) getField().getOne();
        tArr[11] = (RealFieldElement) getField().getOne();
        tArr[12] = fraction(1.0d, 10.0d);
        tArr[13] = fraction(1.0d, 5.0d);
        tArr[14] = fraction(7.0d, 9.0d);
        return tArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: T[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v36, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v37, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v38, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v39, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v40, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v34, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v35, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v36, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v37, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v38, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v34, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v35, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v36, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v37, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v38, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v39, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v40, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v68, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v69, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v70, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v71, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v72, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v73, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v74, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v75, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v76, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v62, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v22, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v23, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v24, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v80, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v44, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v45, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v46, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v47, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v66, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v67, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v68, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v69, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v70, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v71, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v72, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v73, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v74, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v75, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v76, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v77, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v87, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v88, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v89, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v90, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v91, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v92, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v93, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v94, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v95, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v96, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v97, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v98, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v99, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v133, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v134, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v135, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v136, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v137, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v138, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v139, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v140, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v141, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v142, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v143, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v144, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v145, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v146, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v138, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v29, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v30, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v32, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v33, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T[][] getA() {
        /*
            r16 = this;
            r0 = r16
            org.apache.commons.math3.Field r1 = r16.getField()
            java.lang.Object r1 = r1.getOne()
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r2 = 6
            java.lang.Object r1 = r1.multiply((int) r2)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            java.lang.Object r1 = r1.sqrt()
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            org.apache.commons.math3.Field r3 = r16.getField()
            r4 = 15
            r5 = -1
            java.lang.Object[][] r3 = org.apache.commons.math3.util.MathArrays.buildArray(r3, r4, r5)
            org.apache.commons.math3.RealFieldElement[][] r3 = (org.apache.commons.math3.RealFieldElement[][]) r3
            r4 = 0
            r5 = r4
        L_0x0028:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x003b
            org.apache.commons.math3.Field r6 = r16.getField()
            int r7 = r5 + 1
            java.lang.Object[] r6 = org.apache.commons.math3.util.MathArrays.buildArray(r6, r7)
            org.apache.commons.math3.RealFieldElement[] r6 = (org.apache.commons.math3.RealFieldElement[]) r6
            r3[r5] = r6
            r5 = r7
            goto L_0x0028
        L_0x003b:
            r5 = r3[r4]
            r6 = -4604930618986332160(0xc018000000000000, double:-6.0)
            java.lang.Object r8 = r1.add(r6)
            org.apache.commons.math3.RealFieldElement r8 = (org.apache.commons.math3.RealFieldElement) r8
            r9 = -4588921729685913600(0xc050e00000000000, double:-67.5)
            java.lang.Object r8 = r8.divide(r9)
            org.apache.commons.math3.RealFieldElement r8 = (org.apache.commons.math3.RealFieldElement) r8
            r5[r4] = r8
            r5 = 1
            r8 = r3[r5]
            java.lang.Object r9 = r1.add(r6)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = -4582834833314545664(0xc066800000000000, double:-180.0)
            java.lang.Object r9 = r9.divide(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r8[r4] = r9
            r8 = r3[r5]
            java.lang.Object r9 = r1.add(r6)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = -4589730970243956736(0xc04e000000000000, double:-60.0)
            java.lang.Object r9 = r9.divide(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r8[r5] = r9
            r8 = 2
            r9 = r3[r8]
            java.lang.Object r10 = r1.add(r6)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = -4585227370616586240(0xc05e000000000000, double:-120.0)
            java.lang.Object r10 = r10.divide(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r4] = r10
            r9 = r3[r8]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r5] = r10
            r9 = r3[r8]
            java.lang.Object r6 = r1.add(r6)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r10 = -4592545720011063296(0xc044000000000000, double:-40.0)
            java.lang.Object r6 = r6.divide(r10)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r9[r8] = r6
            r6 = 3
            r7 = r3[r6]
            r9 = 107(0x6b, float:1.5E-43)
            java.lang.Object r9 = r1.multiply((int) r9)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = 4646835206144131072(0x407ce00000000000, double:462.0)
            java.lang.Object r9 = r9.add(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = 4658815484840378368(0x40a7700000000000, double:3000.0)
            java.lang.Object r9 = r9.divide(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r7[r4] = r9
            r7 = r3[r6]
            org.apache.commons.math3.Field r9 = r16.getField()
            java.lang.Object r9 = r9.getZero()
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r7[r5] = r9
            r7 = r3[r6]
            r9 = 197(0xc5, float:2.76E-43)
            java.lang.Object r9 = r1.multiply((int) r9)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = 4645779674981466112(0x4079200000000000, double:402.0)
            java.lang.Object r9 = r9.add(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = -4571364728013586432(0xc08f400000000000, double:-1000.0)
            java.lang.Object r9 = r9.divide(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r7[r8] = r9
            r7 = r3[r6]
            r9 = 73
            java.lang.Object r9 = r1.multiply((int) r9)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = 4640114991075164160(0x4065000000000000, double:168.0)
            java.lang.Object r9 = r9.add(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = 4645304685958266880(0x4077700000000000, double:375.0)
            java.lang.Object r9 = r9.divide(r10)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r7[r6] = r9
            r7 = 4
            r9 = r3[r7]
            r10 = 27
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((int) r5, (int) r10)
            r9[r4] = r10
            r9 = r3[r7]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r5] = r10
            r9 = r3[r7]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r8] = r10
            r9 = r3[r7]
            r10 = 4625196817309499392(0x4030000000000000, double:16.0)
            java.lang.Object r10 = r1.add(r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 4637300241308057600(0x405b000000000000, double:108.0)
            java.lang.Object r10 = r10.divide(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r6] = r10
            r9 = r3[r7]
            r10 = -4598175219545276416(0xc030000000000000, double:-16.0)
            java.lang.Object r10 = r1.add(r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = -4586071795546718208(0xc05b000000000000, double:-108.0)
            java.lang.Object r10 = r10.divide(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r7] = r10
            r9 = 5
            r10 = r3[r9]
            r11 = 19
            r12 = 512(0x200, float:7.175E-43)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((int) r11, (int) r12)
            r10[r4] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r5] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r8] = r11
            r10 = r3[r9]
            r11 = 23
            java.lang.Object r11 = r1.multiply((int) r11)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r13 = 4638003928749834240(0x405d800000000000, double:118.0)
            java.lang.Object r11 = r11.add(r13)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r4 = 4652218415073722368(0x4090000000000000, double:1024.0)
            java.lang.Object r11 = r11.divide(r4)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r6] = r11
            r10 = r3[r9]
            r11 = -23
            java.lang.Object r11 = r1.multiply((int) r11)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            java.lang.Object r11 = r11.add(r13)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            java.lang.Object r4 = r11.divide(r4)
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            r10[r7] = r4
            r4 = r3[r9]
            r5 = -9
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((int) r5, (int) r12)
            r4[r9] = r5
            r4 = r3[r2]
            r5 = 13772(0x35cc, float:1.9299E-41)
            r10 = 371293(0x5aa5d, float:5.20292E-40)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((int) r5, (int) r10)
            r11 = 0
            r4[r11] = r5
            r4 = r3[r2]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 1
            r4[r11] = r5
            r4 = r3[r2]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r8] = r5
            r4 = r3[r2]
            r5 = 4784(0x12b0, float:6.704E-42)
            java.lang.Object r5 = r1.multiply((int) r5)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 4677316967000965120(0x40e92b0000000000, double:51544.0)
            java.lang.Object r5 = r5.add(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r13 = 4690122377634250752(0x4116a97400000000, double:371293.0)
            java.lang.Object r5 = r5.divide(r13)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r6] = r5
            r4 = r3[r2]
            r5 = -4784(0xffffffffffffed50, float:NaN)
            java.lang.Object r5 = r1.multiply((int) r5)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            java.lang.Object r5 = r5.add(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            java.lang.Object r5 = r5.divide(r13)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r7] = r5
            r4 = r3[r2]
            r5 = -5688(0xffffffffffffe9c8, float:NaN)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((int) r5, (int) r10)
            r4[r9] = r5
            r4 = r3[r2]
            r5 = 3072(0xc00, float:4.305E-42)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((int) r5, (int) r10)
            r4[r2] = r5
            r4 = 7
            r5 = r3[r4]
            r10 = 4767992986025197568(0x422b505bdf960000, double:5.8656157643E10)
            r12 = 4770967711076384768(0x4235e1db05910000, double:9.3983540625E10)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r11 = 0
            r5[r11] = r10
            r5 = r3[r4]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 1
            r5[r11] = r10
            r5 = r3[r4]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r5[r8] = r10
            r5 = r3[r4]
            r10 = -4444333188970135552(0xc2528e82fff4c000, double:-3.18801444819E11)
            java.lang.Object r10 = r1.multiply(r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = -4435122484277379072(0xc27347994d4c8000, double:-1.324889724104E12)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 4783451959072096256(0x42623c3684a38000, double:6.265569375E11)
            java.lang.Object r10 = r10.divide(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r5[r6] = r10
            r5 = r3[r4]
            r10 = 4779038847884640256(0x42528e82fff4c000, double:3.18801444819E11)
            java.lang.Object r10 = r1.multiply(r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = -4435122484277379072(0xc27347994d4c8000, double:-1.324889724104E12)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 4783451959072096256(0x42623c3684a38000, double:6.265569375E11)
            java.lang.Object r10 = r10.divide(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r5[r7] = r10
            r5 = r3[r4]
            r10 = 4771102782292230144(0x42365cb3bd680000, double:9.6044563816E10)
            r12 = 4749590325035532288(0x41e9ef3c78600000, double:3.480871875E9)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r5[r9] = r10
            r5 = r3[r4]
            r10 = 4797648834246475776(0x4294ac321a540000, double:5.682451879168E12)
            r12 = 4778435084001525760(0x42506964442cc000, double:2.81950621875E11)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r5[r2] = r10
            r5 = r3[r4]
            r10 = -4493555330214526976(0xc1a3af3c2c000000, double:-1.65125654E8)
            r12 = 4705408138323427328(0x414cf7c580000000, double:3796875.0)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r5[r4] = r10
            r5 = 8
            r10 = r3[r5]
            r11 = 4711045076204126208(0x4160fe8960000000, double:8909899.0)
            r13 = 4715772370344738816(0x4171c9fc50000000, double:1.8653125E7)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r12 = 0
            r10[r12] = r11
            r10 = r3[r5]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 1
            r10[r12] = r11
            r10 = r3[r5]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r8] = r11
            r10 = r3[r5]
            r11 = -4525733711265660928(0xc1315d2b00000000, double:-1137963.0)
            java.lang.Object r11 = r1.multiply(r11)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = -4516759201007009792(0xc1513f7000000000, double:-4521408.0)
            java.lang.Object r11 = r11.add(r12)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 4703562644563427328(0x4146694e00000000, double:2937500.0)
            java.lang.Object r11 = r11.divide(r12)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r6] = r11
            r10 = r3[r5]
            r11 = 4697638325589114880(0x41315d2b00000000, double:1137963.0)
            java.lang.Object r11 = r1.multiply(r11)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = -4516759201007009792(0xc1513f7000000000, double:-4521408.0)
            java.lang.Object r11 = r11.add(r12)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 4703562644563427328(0x4146694e00000000, double:2937500.0)
            java.lang.Object r11 = r11.divide(r12)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r7] = r11
            r10 = r3[r5]
            r11 = 4726259358839603200(0x41970bd898000000, double:9.6663078E7)
            r13 = 4706646891717197824(0x41515e6940000000, double:4553125.0)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r10[r9] = r11
            r10 = r3[r5]
            r11 = 4746625234361122816(0x41df668080000000, double:2.107245056E9)
            r13 = 4728903689572450304(0x41a070d9d2000000, double:1.37915625E8)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r10[r2] = r11
            r10 = r3[r5]
            r11 = -4471425692029943808(0xc1f24e0613000000, double:-4.913652016E9)
            r13 = 4729228957847650304(0x41a198ae3e000000, double:1.47609375E8)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r10[r4] = r11
            r10 = r3[r5]
            r11 = -4498305122534686720(0xc192cf52f8000000, double:-7.889427E7)
            r13 = 4750428307116261376(0x41ece96060a00000, double:3.880452869E9)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r10[r5] = r11
            r10 = 9
            r11 = r3[r10]
            r12 = -4462222760928083968(0xc213000a72380000, double:-2.0401265806E10)
            r4 = 4761507990500802560(0x4214464a30fc0000, double:2.1769653311E10)
            org.apache.commons.math3.RealFieldElement r4 = r0.fraction((double) r12, (double) r4)
            r5 = 0
            r11[r5] = r4
            r4 = r3[r10]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 1
            r4[r11] = r5
            r4 = r3[r10]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r8] = r5
            r4 = r3[r10]
            r11 = 4681218446573174784(0x40f7076000000000, double:94326.0)
            java.lang.Object r5 = r1.multiply(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 4689828997008195584(0x41159ea000000000, double:354216.0)
            java.lang.Object r5 = r5.add(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 4682491200001802240(0x40fb8cf000000000, double:112847.0)
            java.lang.Object r5 = r5.divide(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r6] = r5
            r4 = r3[r10]
            r11 = -4542153590281601024(0xc0f7076000000000, double:-94326.0)
            java.lang.Object r5 = r1.multiply(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 4689828997008195584(0x41159ea000000000, double:354216.0)
            java.lang.Object r5 = r5.add(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 4682491200001802240(0x40fb8cf000000000, double:112847.0)
            java.lang.Object r5 = r5.divide(r11)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r7] = r5
            r4 = r3[r10]
            r11 = -4457390926405304320(0xc2242a9196900000, double:-4.3306765128E10)
            r14 = 4752365985324859392(0x41f3cbaf3df00000, double:5.313852383E9)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r4[r9] = r11
            r4 = r3[r10]
            r11 = -4417192956738142208(0xc2b2fa68bd440000, double:-2.0866708358144E13)
            r14 = 4787437800726122496(0x42706550988ed000, double:1.126708119789E12)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r4[r2] = r11
            r4 = r3[r10]
            r11 = 4803955236909844480(0x42ab13d5e4e38800, double:1.488600343802E13)
            r14 = 4783681952692920320(0x42630d6414b56000, double:6.54632330667E11)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r5 = 7
            r4[r5] = r11
            r4 = r3[r10]
            r11 = 4854695870606231040(0x435f582c4c0a4600, double:3.5290686222309376E16)
            r13 = 4848445836116850406(0x434923ccbe7776e6, double:1.4152473387134412E16)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r12 = 8
            r4[r12] = r11
            r4 = r3[r10]
            r11 = -4479386532515414016(0xc1d605ae75c00000, double:-1.477884375E9)
            r13 = 4736917679670034432(0x41bce9884b000000, double:4.85066827E8)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r4[r10] = r11
            r4 = 10
            r11 = r3[r4]
            r12 = 4720612790836920320(0x4182fc5288000000, double:3.9815761E7)
            r9 = 4715466707722829824(0x4170b3fcb0000000, double:1.7514443E7)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r12, (double) r9)
            r10 = 0
            r11[r10] = r9
            r9 = r3[r4]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 1
            r9[r11] = r10
            r9 = r3[r4]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r8] = r10
            r9 = r3[r4]
            r10 = -4526870713662963712(0xc12d531200000000, double:-960905.0)
            java.lang.Object r10 = r1.multiply(r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = -4518692743744061440(0xc14a60e400000000, double:-3457480.0)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 4692985729251278848(0x4120d5a800000000, double:551636.0)
            java.lang.Object r10 = r10.divide(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r6] = r10
            r9 = r3[r4]
            r10 = 4696501323191812096(0x412d531200000000, double:960905.0)
            java.lang.Object r1 = r1.multiply(r10)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r10 = -4518692743744061440(0xc14a60e400000000, double:-3457480.0)
            java.lang.Object r1 = r1.add(r10)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r10 = 4692985729251278848(0x4120d5a800000000, double:551636.0)
            java.lang.Object r1 = r1.divide(r10)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r9[r7] = r1
            r1 = r3[r4]
            r9 = -4483004194940256256(0xc1c92b6fca000000, double:-8.44554132E8)
            r11 = 4721580662790815744(0x41866c98c8000000, double:4.7026969E7)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r10 = 5
            r1[r10] = r9
            r1 = r3[r4]
            r9 = 4755649227743297536(0x41ff75c6b0000000, double:8.444996352E9)
            r11 = 4733848989156245504(0x41b202931b000000, double:3.02158619E8)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r1[r2] = r9
            r1 = r3[r4]
            r9 = -4475818611662913536(0xc1e2b2afc4c00000, double:-2.509602342E9)
            r11 = 4740646651167768576(0x41ca290320800000, double:8.77790785E8)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r5 = 7
            r1[r5] = r9
            r1 = r3[r4]
            r9 = -4370401638979623050(0xc35936dd5ed5a776, double:-2.8388795297996248E16)
            r11 = 4838761420351255774(0x4326bbdffce6b4de, double:3.199510091356783E15)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((double) r9, (double) r11)
            r9 = 8
            r1[r9] = r5
            r1 = r3[r4]
            r9 = 4731883344105570304(0x41ab06d4b4000000, double:2.2671625E8)
            r11 = 4715688825714638848(0x41717e0090000000, double:1.8341897E7)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((double) r9, (double) r11)
            r9 = 9
            r1[r9] = r5
            r1 = r3[r4]
            r9 = 4743538527298387968(0x41d46f2882000000, double:1.371316744E9)
            r11 = 4746726478731804672(0x41dfc2954ac00000, double:2.131383595E9)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((double) r9, (double) r11)
            r1[r4] = r5
            r1 = 11
            r5 = r3[r1]
            r9 = 4681900899696640000(0x40f9741000000000, double:104257.0)
            r11 = 4700998179720527872(0x413d4cf000000000, double:1920240.0)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r10 = 0
            r5[r10] = r9
            r5 = r3[r1]
            org.apache.commons.math3.Field r9 = r16.getField()
            java.lang.Object r9 = r9.getZero()
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r10 = 1
            r5[r10] = r9
            r5 = r3[r1]
            org.apache.commons.math3.Field r9 = r16.getField()
            java.lang.Object r9 = r9.getZero()
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r5[r8] = r9
            r5 = r3[r1]
            org.apache.commons.math3.Field r9 = r16.getField()
            java.lang.Object r9 = r9.getZero()
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r5[r6] = r9
            r5 = r3[r1]
            org.apache.commons.math3.Field r9 = r16.getField()
            java.lang.Object r9 = r9.getZero()
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r5[r7] = r9
            r5 = r3[r1]
            r9 = 4704554410494132224(0x4149ef4f80000000, double:3399327.0)
            r11 = 4694808547731439616(0x41274f8000000000, double:763840.0)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r10 = 5
            r5[r10] = r9
            r5 = r3[r1]
            r9 = 4724204815733751808(0x418fbf4000000000, double:6.6578432E7)
            r11 = 4719993061147410432(0x4180c8ae78000000, double:3.5198415E7)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r5[r2] = r9
            r5 = r3[r1]
            r9 = -4478560177670324224(0xc1d8f53f30c00000, double:-1.674902723E9)
            r11 = 4733623466144563200(0x41b1357670000000, double:2.887164E8)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r10 = 7
            r5[r10] = r9
            r5 = r3[r1]
            r9 = 4812378289926319232(0x42c9008ebfdc2c80, double:5.4980371265625E13)
            r11 = 4820002157685024768(0x42e4166cc96ea800, double:1.76692375811392E14)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r10 = 8
            r5[r10] = r9
            r5 = r3[r1]
            r9 = -4528816591546089472(0xc126694e00000000, double:-734375.0)
            r11 = 4706940215434936320(0x4152693000000000, double:4826304.0)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r10 = 9
            r5[r10] = r9
            r5 = r3[r1]
            r9 = 4730027728416276480(0x41a46f2882000000, double:1.71414593E8)
            r11 = 4740424106556522496(0x41c95e9bec000000, double:8.512614E8)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r5[r4] = r9
            r5 = r3[r1]
            r9 = 4683978529996537856(0x4100d5a800000000, double:137909.0)
            r11 = 4703878281710010368(0x4147886000000000, double:3084480.0)
            org.apache.commons.math3.RealFieldElement r9 = r0.fraction((double) r9, (double) r11)
            r5[r1] = r9
            r5 = 12
            r9 = r3[r5]
            r10 = 4758365997699170304(0x42091ca9fc280000, double:1.3481885573E10)
            r12 = 4777177308425355264(0x424bf17391c00000, double:2.4003E11)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r11 = 0
            r9[r11] = r10
            r9 = r3[r5]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 1
            r9[r11] = r10
            r9 = r3[r5]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r8] = r10
            r9 = r3[r5]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r6] = r10
            r9 = r3[r5]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r9[r7] = r10
            r9 = r3[r5]
            org.apache.commons.math3.Field r10 = r16.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 5
            r9[r11] = r10
            r9 = r3[r5]
            r10 = 4773880481853472768(0x42403b01510c0000, double:1.39418837528E11)
            r12 = 4782824601760096256(0x426001a282e8e000, double:5.49975234375E11)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r9[r2] = r10
            r9 = r3[r5]
            r10 = -4421350973830154752(0xc2a434b6f186b600, double:-1.1108320068443E13)
            r12 = 4811115130404319232(0x42c483b88141b000, double:4.51119375E13)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r11 = 7
            r9[r11] = r10
            r9 = r3[r5]
            r10 = -4388434630625900260(0xc31925f45185491c, double:-1.769651421925959E15)
            r12 = 4848494291996323200(0x43494fdec1f32d80, double:1.424938514608E16)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r11 = 8
            r9[r11] = r10
            r9 = r3[r5]
            r10 = 4723026519239163904(0x418b8f9878000000, double:5.7799439E7)
            r12 = 4735105541917900800(0x41b6796718000000, double:3.77055E8)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r11 = 9
            r9[r11] = r10
            r9 = r3[r5]
            r10 = 4784818103731789824(0x426716b6dbcaa000, double:7.93322643029E11)
            r12 = 4816035394031689728(0x42d5feaca6f7a000, double:9.673425E13)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r9[r4] = r10
            r9 = r3[r5]
            r10 = 4743906042981646336(0x41d5bd696bc00000, double:1.458939311E9)
            r12 = 4775629020425355264(0x4246714aad800000, double:1.9278E11)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r9[r1] = r10
            r9 = r3[r5]
            r10 = -4562088148410040320(0xc0b0350000000000, double:-4149.0)
            r12 = 4692333547057315840(0x411e848000000000, double:500000.0)
            org.apache.commons.math3.RealFieldElement r10 = r0.fraction((double) r10, (double) r12)
            r9[r5] = r10
            r9 = 13
            r10 = r3[r9]
            r11 = 4789358223240572928(0x427737edce59b000, double:1.595561272731E12)
            r14 = 4811756197412319232(0x42c6cac48c8bb000, double:5.01202735E13)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r12 = 0
            r10[r12] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 1
            r10[r12] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r8] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r6] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r10[r7] = r11
            r10 = r3[r9]
            r11 = 4786307911283990528(0x426c61afb5b96000, double:9.75183916491E11)
            r13 = 4809658370912948736(0x42bf56ce41401200, double:3.445768803125E13)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r12 = 5
            r10[r12] = r11
            r10 = r3[r9]
            r11 = 4810267780187701248(0x42c1810f68984000, double:3.8492013932672E13)
            r13 = 4829106502297926216(0x43046ec733ff3a48, double:7.18912673015625E14)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r10[r2] = r11
            r10 = r3[r9]
            r11 = -4391097785648834136(0xc30fafd4386599a8, double:-1.114881286517557E15)
            r13 = 4850947876742528696(0x43520764792c7eb8, double:2.02987107675E16)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r13)
            r12 = 7
            r10[r12] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 8
            r10[r12] = r11
            r10 = r3[r9]
            org.apache.commons.math3.Field r11 = r16.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 9
            r10[r12] = r11
            r10 = r3[r9]
            r11 = -4430846352940763136(0xc28278b7b6a17800, double:-2.538710946863E12)
            r14 = 4851731006015966196(0x4354cfa4f7548bf4, double:2.343122786125E16)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r10[r4] = r11
            r10 = r3[r9]
            r11 = 4755924269694189568(0x42006fece1c80000, double:8.824659001E9)
            r14 = 4806742282272948736(0x42b4faa375bac200, double:2.306671678125E13)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r10[r1] = r11
            r10 = r3[r9]
            r11 = -4466035505387536384(0xc205745eb3180000, double:-1.1518334563E10)
            r14 = 4809497986037748736(0x42bec4efbdbc9400, double:3.38311846125E13)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r10[r5] = r11
            r10 = r3[r9]
            r11 = 4745807604674985984(0x41dc7edf41000000, double:1.912306948E9)
            r14 = 4758392520523120640(0x420934c94fa80000, double:1.3532473845E10)
            org.apache.commons.math3.RealFieldElement r11 = r0.fraction((double) r11, (double) r14)
            r10[r9] = r11
            r10 = 14
            r11 = r3[r10]
            r14 = -4464936779979948032(0xc2095ba7a4b80000, double:-1.3613986967E10)
            r4 = 4764122157246578688(0x421d8fdc51400000, double:3.1741908048E10)
            org.apache.commons.math3.RealFieldElement r4 = r0.fraction((double) r14, (double) r4)
            r5 = 0
            r11[r5] = r4
            r4 = r3[r10]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r11 = 1
            r4[r11] = r5
            r4 = r3[r10]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r8] = r5
            r4 = r3[r10]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r6] = r5
            r4 = r3[r10]
            org.apache.commons.math3.Field r5 = r16.getField()
            java.lang.Object r5 = r5.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r7] = r5
            r4 = r3[r10]
            r5 = -4471591408336109568(0xc1f1b74e3d700000, double:-4.755612631E9)
            r7 = 4741775372087984128(0x41ce2b93f2000000, double:1.012344804E9)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((double) r5, (double) r7)
            r6 = 5
            r4[r6] = r5
            r4 = r3[r10]
            r5 = 4810837027421224960(0x42c386c99bbd0000, double:4.2939257944576E13)
            r7 = 4797552688640365568(0x429454c075a11400, double:5.588559685701E12)
            org.apache.commons.math3.RealFieldElement r5 = r0.fraction((double) r5, (double) r7)
            r4[r2] = r5
            r2 = r3[r10]
            r4 = 4814828848297307456(0x42d1b553d5106d40, double:7.7881972900277E13)
            r6 = 4805737137638502400(0x42b16876fcd87000, double:1.9140370552944E13)
            org.apache.commons.math3.RealFieldElement r4 = r0.fraction((double) r4, (double) r6)
            r5 = 7
            r2[r5] = r4
            r2 = r3[r10]
            r4 = 4806653479060948736(0x42b4a9df59d2c700, double:2.2719829234375E13)
            r6 = 4813493077432037888(0x42ccf67371237200, double:6.3689648654052E13)
            org.apache.commons.math3.RealFieldElement r4 = r0.fraction((double) r4, (double) r6)
            r5 = 8
            r2[r5] = r4
            r2 = r3[r10]
            org.apache.commons.math3.Field r4 = r16.getField()
            java.lang.Object r4 = r4.getZero()
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            r5 = 9
            r2[r5] = r4
            r2 = r3[r10]
            org.apache.commons.math3.Field r4 = r16.getField()
            java.lang.Object r4 = r4.getZero()
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            r5 = 10
            r2[r5] = r4
            r2 = r3[r10]
            org.apache.commons.math3.Field r4 = r16.getField()
            java.lang.Object r4 = r4.getZero()
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            r2[r1] = r4
            r1 = r3[r10]
            r4 = -4480556225636859904(0xc1d1ddda0ec00000, double:-1.199007803E9)
            r6 = 4785340006829785088(0x4268f161e48e0000, double:8.57031517296E11)
            org.apache.commons.math3.RealFieldElement r2 = r0.fraction((double) r4, (double) r6)
            r4 = 12
            r1[r4] = r2
            r1 = r3[r10]
            r4 = 4774485484956811264(0x42426140941c0000, double:1.57882067E11)
            r6 = 4767325608320303104(0x4228f161e48e0000, double:5.3564469831E10)
            org.apache.commons.math3.RealFieldElement r2 = r0.fraction((double) r4, (double) r6)
            r1[r9] = r2
            r1 = r3[r10]
            r4 = -4444797389673218048(0xc250e852d9f1c000, double:-2.90468882375E11)
            r6 = 4764122157246578688(0x421d8fdc51400000, double:3.1741908048E10)
            org.apache.commons.math3.RealFieldElement r0 = r0.fraction((double) r4, (double) r6)
            r1[r10] = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldIntegrator.getA():org.apache.commons.math3.RealFieldElement[][]");
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 16);
        tArr[0] = fraction(104257, 1920240);
        tArr[1] = (RealFieldElement) getField().getZero();
        tArr[2] = (RealFieldElement) getField().getZero();
        tArr[3] = (RealFieldElement) getField().getZero();
        tArr[4] = (RealFieldElement) getField().getZero();
        tArr[5] = fraction(3399327.0d, 763840.0d);
        tArr[6] = fraction(6.6578432E7d, 3.5198415E7d);
        tArr[7] = fraction(-1.674902723E9d, 2.887164E8d);
        tArr[8] = fraction(5.4980371265625E13d, 1.76692375811392E14d);
        tArr[9] = fraction(-734375.0d, 4826304.0d);
        tArr[10] = fraction(1.71414593E8d, 8.512614E8d);
        tArr[11] = fraction(137909.0d, 3084480.0d);
        tArr[12] = (RealFieldElement) getField().getZero();
        tArr[13] = (RealFieldElement) getField().getZero();
        tArr[14] = (RealFieldElement) getField().getZero();
        tArr[15] = (RealFieldElement) getField().getZero();
        return tArr;
    }

    /* access modifiers changed from: protected */
    public DormandPrince853FieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new DormandPrince853FieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t) {
        double d;
        RealFieldElement realFieldElement;
        RealFieldElement realFieldElement2 = (RealFieldElement) t.getField().getZero();
        RealFieldElement realFieldElement3 = (RealFieldElement) t.getField().getZero();
        for (int i = 0; i < this.mainSetDimension; i++) {
            RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][i].multiply(this.e1_01)).add(tArr[5][i].multiply(this.e1_06))).add(tArr[6][i].multiply(this.e1_07))).add(tArr[7][i].multiply(this.e1_08))).add(tArr[8][i].multiply(this.e1_09))).add(tArr[9][i].multiply(this.e1_10))).add(tArr[10][i].multiply(this.e1_11))).add(tArr[11][i].multiply(this.e1_12));
            RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][i].multiply(this.e2_01)).add(tArr[5][i].multiply(this.e2_06))).add(tArr[6][i].multiply(this.e2_07))).add(tArr[7][i].multiply(this.e2_08))).add(tArr[8][i].multiply(this.e2_09))).add(tArr[9][i].multiply(this.e2_10))).add(tArr[10][i].multiply(this.e2_11))).add(tArr[11][i].multiply(this.e2_12));
            RealFieldElement max = MathUtils.max((RealFieldElement) tArr2[i].abs(), (RealFieldElement) tArr3[i].abs());
            if (this.vecAbsoluteTolerance == null) {
                realFieldElement = (RealFieldElement) max.multiply(this.scalRelativeTolerance);
                d = this.scalAbsoluteTolerance;
            } else {
                realFieldElement = (RealFieldElement) max.multiply(this.vecRelativeTolerance[i]);
                d = this.vecAbsoluteTolerance[i];
            }
            RealFieldElement realFieldElement6 = (RealFieldElement) realFieldElement.add(d);
            RealFieldElement realFieldElement7 = (RealFieldElement) realFieldElement4.divide(realFieldElement6);
            realFieldElement2 = (RealFieldElement) realFieldElement2.add(realFieldElement7.multiply(realFieldElement7));
            RealFieldElement realFieldElement8 = (RealFieldElement) realFieldElement5.divide(realFieldElement6);
            realFieldElement3 = (RealFieldElement) realFieldElement3.add(realFieldElement8.multiply(realFieldElement8));
        }
        RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElement2.add(realFieldElement3.multiply(0.01d));
        if (realFieldElement9.getReal() <= 0.0d) {
            realFieldElement9 = (RealFieldElement) t.getField().getOne();
        }
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.abs()).multiply(realFieldElement2)).divide(((RealFieldElement) realFieldElement9.multiply(this.mainSetDimension)).sqrt());
    }
}
