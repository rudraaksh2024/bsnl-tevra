package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class DormandPrince54FieldIntegrator<T extends RealFieldElement<T>> extends EmbeddedRungeKuttaFieldIntegrator<T> {
    private static final String METHOD_NAME = "Dormand-Prince 5(4)";
    private final T e1 = fraction(71, 57600);
    private final T e3 = fraction(-71, 16695);
    private final T e4 = fraction(71, 1920);
    private final T e5 = fraction(-17253, 339200);
    private final T e6 = fraction(22, 525);
    private final T e7 = fraction(-1, 40);

    public int getOrder() {
        return 5;
    }

    public DormandPrince54FieldIntegrator(Field<T> field, double d, double d2, double d3, double d4) {
        super(field, METHOD_NAME, 6, d, d2, d3, d4);
    }

    public DormandPrince54FieldIntegrator(Field<T> field, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, METHOD_NAME, 6, d, d2, dArr, dArr2);
    }

    public T[] getC() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 6);
        tArr[0] = fraction(1, 5);
        tArr[1] = fraction(3, 10);
        tArr[2] = fraction(4, 5);
        tArr[3] = fraction(8, 9);
        tArr[4] = (RealFieldElement) getField().getOne();
        tArr[5] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    public T[][] getA() {
        T[][] tArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), 6, -1);
        int i = 0;
        while (i < tArr.length) {
            int i2 = i + 1;
            tArr[i] = (RealFieldElement[]) MathArrays.buildArray(getField(), i2);
            i = i2;
        }
        tArr[0][0] = fraction(1, 5);
        tArr[1][0] = fraction(3, 40);
        tArr[1][1] = fraction(9, 40);
        tArr[2][0] = fraction(44, 45);
        tArr[2][1] = fraction(-56, 15);
        tArr[2][2] = fraction(32, 9);
        tArr[3][0] = fraction(19372, 6561);
        tArr[3][1] = fraction(-25360, 2187);
        tArr[3][2] = fraction(64448, 6561);
        tArr[3][3] = fraction(-212, 729);
        tArr[4][0] = fraction(9017, 3168);
        tArr[4][1] = fraction(-355, 33);
        tArr[4][2] = fraction(46732, 5247);
        tArr[4][3] = fraction(49, 176);
        tArr[4][4] = fraction(-5103, 18656);
        tArr[5][0] = fraction(35, 384);
        tArr[5][1] = (RealFieldElement) getField().getZero();
        tArr[5][2] = fraction(500, 1113);
        tArr[5][3] = fraction(125, 192);
        tArr[5][4] = fraction(-2187, 6784);
        tArr[5][5] = fraction(11, 84);
        return tArr;
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 7);
        tArr[0] = fraction(35, 384);
        tArr[1] = (RealFieldElement) getField().getZero();
        tArr[2] = fraction(500, 1113);
        tArr[3] = fraction(125, 192);
        tArr[4] = fraction(-2187, 6784);
        tArr[5] = fraction(11, 84);
        tArr[6] = (RealFieldElement) getField().getZero();
        return tArr;
    }

    /* access modifiers changed from: protected */
    public DormandPrince54FieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new DormandPrince54FieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t) {
        double d;
        RealFieldElement realFieldElement;
        RealFieldElement realFieldElement2 = (RealFieldElement) getField().getZero();
        for (int i = 0; i < this.mainSetDimension; i++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][i].multiply(this.e1)).add(tArr[2][i].multiply(this.e3))).add(tArr[3][i].multiply(this.e4))).add(tArr[4][i].multiply(this.e5))).add(tArr[5][i].multiply(this.e6))).add(tArr[6][i].multiply(this.e7));
            RealFieldElement max = MathUtils.max((RealFieldElement) tArr2[i].abs(), (RealFieldElement) tArr3[i].abs());
            if (this.vecAbsoluteTolerance == null) {
                realFieldElement = (RealFieldElement) max.multiply(this.scalRelativeTolerance);
                d = this.scalAbsoluteTolerance;
            } else {
                realFieldElement = (RealFieldElement) max.multiply(this.vecRelativeTolerance[i]);
                d = this.vecAbsoluteTolerance[i];
            }
            RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) t.multiply(realFieldElement3)).divide((RealFieldElement) realFieldElement.add(d));
            realFieldElement2 = (RealFieldElement) realFieldElement2.add(realFieldElement4.multiply(realFieldElement4));
        }
        return (RealFieldElement) ((RealFieldElement) realFieldElement2.divide((double) this.mainSetDimension)).sqrt();
    }
}
