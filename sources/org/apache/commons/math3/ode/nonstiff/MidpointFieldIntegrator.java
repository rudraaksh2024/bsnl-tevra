package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class MidpointFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {
    public MidpointFieldIntegrator(Field<T> field, T t) {
        super(field, "midpoint", t);
    }

    public T[] getC() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 1);
        tArr[0] = (RealFieldElement) ((RealFieldElement) getField().getOne()).multiply(0.5d);
        return tArr;
    }

    public T[][] getA() {
        T[][] tArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), 1, 1);
        tArr[0][0] = fraction(1, 2);
        return tArr;
    }

    public T[] getB() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(getField(), 2);
        tArr[0] = (RealFieldElement) getField().getZero();
        tArr[1] = (RealFieldElement) getField().getOne();
        return tArr;
    }

    /* access modifiers changed from: protected */
    public MidpointFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new MidpointFieldStepInterpolator(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }
}
