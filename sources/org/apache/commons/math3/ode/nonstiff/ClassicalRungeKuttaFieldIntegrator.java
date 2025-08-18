package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class ClassicalRungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {
    public ClassicalRungeKuttaFieldIntegrator(Field<T> field, T t) {
        super(field, "classical Runge-Kutta", t);
    }

    public T[] getC() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 3);
        T t = (RealFieldElement) ((RealFieldElement) getField().getOne()).multiply(0.5d);
        tArr[0] = t;
        tArr[1] = t;
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
        tArr[0][0] = fraction(1, 2);
        tArr[1][0] = (RealFieldElement) getField().getZero();
        tArr[1][1] = tArr[0][0];
        tArr[2][0] = (RealFieldElement) getField().getZero();
        tArr[2][1] = (RealFieldElement) getField().getZero();
        tArr[2][2] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 4);
        tArr[0] = fraction(1, 6);
        T fraction = fraction(1, 3);
        tArr[1] = fraction;
        tArr[2] = fraction;
        tArr[3] = tArr[0];
        return tArr;
    }

    /* access modifiers changed from: protected */
    public ClassicalRungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ClassicalRungeKuttaFieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }
}
