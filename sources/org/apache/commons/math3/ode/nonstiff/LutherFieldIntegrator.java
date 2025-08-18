package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class LutherFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {
    public LutherFieldIntegrator(Field<T> field, T t) {
        super(field, "Luther", t);
    }

    public T[] getC() {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getZero()).add(21.0d)).sqrt();
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 6);
        tArr[0] = (RealFieldElement) getField().getOne();
        tArr[1] = fraction(1, 2);
        tArr[2] = fraction(2, 3);
        tArr[3] = (RealFieldElement) ((RealFieldElement) realFieldElement.subtract(7.0d)).divide(-14.0d);
        tArr[4] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(7.0d)).divide(14.0d);
        tArr[5] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    public T[][] getA() {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getZero()).add(21.0d)).sqrt();
        T[][] tArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), 6, -1);
        int i = 0;
        while (i < tArr.length) {
            int i2 = i + 1;
            tArr[i] = (RealFieldElement[]) MathArrays.buildArray(getField(), i2);
            i = i2;
        }
        tArr[0][0] = (RealFieldElement) getField().getOne();
        tArr[1][0] = fraction(3, 8);
        tArr[1][1] = fraction(1, 8);
        tArr[2][0] = fraction(8, 27);
        tArr[2][1] = fraction(2, 27);
        T[] tArr2 = tArr[2];
        tArr2[2] = tArr2[0];
        tArr[3][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(9)).add(-21.0d)).divide(392.0d);
        tArr[3][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(8)).add(-56.0d)).divide(392.0d);
        tArr[3][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-48)).add(336.0d)).divide(392.0d);
        tArr[3][3] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(3)).add(-63.0d)).divide(392.0d);
        tArr[4][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-255)).add(-1155.0d)).divide(1960.0d);
        tArr[4][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-40)).add(-280.0d)).divide(1960.0d);
        tArr[4][2] = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-320)).divide(1960.0d);
        tArr[4][3] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(363)).add(63.0d)).divide(1960.0d);
        tArr[4][4] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(392)).add(2352.0d)).divide(1960.0d);
        tArr[5][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(105)).add(330.0d)).divide(180.0d);
        tArr[5][1] = fraction(2, 3);
        tArr[5][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(280)).add(-200.0d)).divide(180.0d);
        tArr[5][3] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-189)).add(126.0d)).divide(180.0d);
        tArr[5][4] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-126)).add(-686.0d)).divide(180.0d);
        tArr[5][5] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-70)).add(490.0d)).divide(180.0d);
        return tArr;
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 7);
        tArr[0] = fraction(1, 20);
        tArr[1] = (RealFieldElement) getField().getZero();
        tArr[2] = fraction(16, 45);
        tArr[3] = (RealFieldElement) getField().getZero();
        T fraction = fraction(49, 180);
        tArr[4] = fraction;
        tArr[5] = fraction;
        tArr[6] = tArr[0];
        return tArr;
    }

    /* access modifiers changed from: protected */
    public LutherFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new LutherFieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }
}
