package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class ThreeEighthesFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {
    public ThreeEighthesFieldIntegrator(Field<T> field, T t) {
        super(field, "3/8", t);
    }

    public T[] getC() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 3);
        T fraction = fraction(1, 3);
        tArr[0] = fraction;
        tArr[1] = (RealFieldElement) fraction.add(fraction);
        tArr[2] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    public T[][] getA() {
        T[][] tArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), 3, -1);
        int i = 0;
        while (i < tArr.length) {
            int i2 = i + 1;
            tArr[i] = (RealFieldElement[]) MathArrays.buildArray(getField(), i2);
            i = i2;
        }
        tArr[0][0] = fraction(1, 3);
        tArr[1][0] = (RealFieldElement) tArr[0][0].negate();
        tArr[1][1] = (RealFieldElement) getField().getOne();
        tArr[2][0] = (RealFieldElement) getField().getOne();
        tArr[2][1] = (RealFieldElement) ((RealFieldElement) getField().getOne()).negate();
        tArr[2][2] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 4);
        tArr[0] = fraction(1, 8);
        T fraction = fraction(3, 8);
        tArr[1] = fraction;
        tArr[2] = fraction;
        tArr[3] = tArr[0];
        return tArr;
    }

    /* access modifiers changed from: protected */
    public ThreeEighthesFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ThreeEighthesFieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }
}
