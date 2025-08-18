package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class HighamHall54FieldIntegrator<T extends RealFieldElement<T>> extends EmbeddedRungeKuttaFieldIntegrator<T> {
    private static final String METHOD_NAME = "Higham-Hall 5(4)";
    private final T[] e;

    public int getOrder() {
        return 5;
    }

    public HighamHall54FieldIntegrator(Field<T> field, double d, double d2, double d3, double d4) {
        super(field, METHOD_NAME, -1, d, d2, d3, d4);
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(field, 7);
        this.e = tArr;
        tArr[0] = fraction(-1, 20);
        tArr[1] = (RealFieldElement) field.getZero();
        tArr[2] = fraction(81, 160);
        tArr[3] = fraction(-6, 5);
        tArr[4] = fraction(25, 32);
        tArr[5] = fraction(1, 16);
        tArr[6] = fraction(-1, 10);
    }

    public HighamHall54FieldIntegrator(Field<T> field, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, METHOD_NAME, -1, d, d2, dArr, dArr2);
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(field, 7);
        this.e = tArr;
        tArr[0] = fraction(-1, 20);
        tArr[1] = (RealFieldElement) field.getZero();
        tArr[2] = fraction(81, 160);
        tArr[3] = fraction(-6, 5);
        tArr[4] = fraction(25, 32);
        tArr[5] = fraction(1, 16);
        tArr[6] = fraction(-1, 10);
    }

    public T[] getC() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 6);
        tArr[0] = fraction(2, 9);
        tArr[1] = fraction(1, 3);
        tArr[2] = fraction(1, 2);
        tArr[3] = fraction(3, 5);
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
        tArr[0][0] = fraction(2, 9);
        tArr[1][0] = fraction(1, 12);
        tArr[1][1] = fraction(1, 4);
        tArr[2][0] = fraction(1, 8);
        tArr[2][1] = (RealFieldElement) getField().getZero();
        tArr[2][2] = fraction(3, 8);
        tArr[3][0] = fraction(91, 500);
        tArr[3][1] = fraction(-27, 100);
        tArr[3][2] = fraction(78, 125);
        tArr[3][3] = fraction(8, 125);
        tArr[4][0] = fraction(-11, 20);
        tArr[4][1] = fraction(27, 20);
        tArr[4][2] = fraction(12, 5);
        tArr[4][3] = fraction(-36, 5);
        tArr[4][4] = fraction(5, 1);
        tArr[5][0] = fraction(1, 12);
        tArr[5][1] = (RealFieldElement) getField().getZero();
        tArr[5][2] = fraction(27, 32);
        tArr[5][3] = fraction(-4, 3);
        tArr[5][4] = fraction(125, 96);
        tArr[5][5] = fraction(5, 48);
        return tArr;
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 7);
        tArr[0] = fraction(1, 12);
        tArr[1] = (RealFieldElement) getField().getZero();
        tArr[2] = fraction(27, 32);
        tArr[3] = fraction(-4, 3);
        tArr[4] = fraction(125, 96);
        tArr[5] = fraction(5, 48);
        tArr[6] = (RealFieldElement) getField().getZero();
        return tArr;
    }

    /* access modifiers changed from: protected */
    public HighamHall54FieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new HighamHall54FieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t) {
        double d;
        RealFieldElement realFieldElement;
        RealFieldElement realFieldElement2 = (RealFieldElement) getField().getZero();
        for (int i = 0; i < this.mainSetDimension; i++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) tArr[0][i].multiply(this.e[0]);
            int i2 = 1;
            while (true) {
                T[] tArr4 = this.e;
                if (i2 >= tArr4.length) {
                    break;
                }
                realFieldElement3 = (RealFieldElement) realFieldElement3.add(tArr[i2][i].multiply(tArr4[i2]));
                i2++;
            }
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
