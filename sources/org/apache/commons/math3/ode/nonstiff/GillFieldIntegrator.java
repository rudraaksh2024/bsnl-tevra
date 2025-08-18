package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class GillFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {
    public GillFieldIntegrator(Field<T> field, T t) {
        super(field, "Gill", t);
    }

    public T[] getC() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 3);
        T fraction = fraction(1, 2);
        tArr[0] = fraction;
        tArr[1] = fraction;
        tArr[2] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    public T[][] getA() {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getZero()).add(2.0d)).sqrt();
        T[][] tArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), 3, -1);
        int i = 0;
        while (i < tArr.length) {
            int i2 = i + 1;
            tArr[i] = (RealFieldElement[]) MathArrays.buildArray(getField(), i2);
            i = i2;
        }
        tArr[0][0] = fraction(1, 2);
        tArr[1][0] = (RealFieldElement) ((RealFieldElement) realFieldElement.subtract(1.0d)).multiply(0.5d);
        tArr[1][1] = (RealFieldElement) ((RealFieldElement) realFieldElement.subtract(2.0d)).multiply(-0.5d);
        tArr[2][0] = (RealFieldElement) getField().getZero();
        tArr[2][1] = (RealFieldElement) realFieldElement.multiply(-0.5d);
        tArr[2][2] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(2.0d)).multiply(0.5d);
        return tArr;
    }

    public T[] getB() {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getZero()).add(2.0d)).sqrt();
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 4);
        tArr[0] = fraction(1, 6);
        tArr[1] = (RealFieldElement) ((RealFieldElement) realFieldElement.subtract(2.0d)).divide(-6.0d);
        tArr[2] = (RealFieldElement) ((RealFieldElement) realFieldElement.add(2.0d)).divide(6.0d);
        tArr[3] = tArr[0];
        return tArr;
    }

    /* access modifiers changed from: protected */
    public GillFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new GillFieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }
}
