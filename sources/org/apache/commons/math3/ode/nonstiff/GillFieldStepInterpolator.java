package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class GillFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    private final T one_minus_inv_sqrt_2;
    private final T one_plus_inv_sqrt_2;

    GillFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) field.getZero()).add(0.5d)).sqrt();
        this.one_minus_inv_sqrt_2 = (RealFieldElement) ((RealFieldElement) field.getOne()).subtract(realFieldElement);
        this.one_plus_inv_sqrt_2 = (RealFieldElement) ((RealFieldElement) field.getOne()).add(realFieldElement);
    }

    /* access modifiers changed from: protected */
    public GillFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new GillFieldStepInterpolator(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        RealFieldElement[] realFieldElementArr;
        RealFieldElement[] realFieldElementArr2;
        T t5 = t2;
        RealFieldElement realFieldElement = (RealFieldElement) t5.multiply(2);
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.multiply(realFieldElement);
        RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) t5.multiply(realFieldElement.subtract(3.0d))).add(1.0d);
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement.multiply(((RealFieldElement) t.getField().getOne()).subtract(t5));
        RealFieldElement realFieldElement5 = (RealFieldElement) realFieldElement4.multiply(this.one_minus_inv_sqrt_2);
        RealFieldElement realFieldElement6 = (RealFieldElement) realFieldElement4.multiply(this.one_plus_inv_sqrt_2);
        RealFieldElement realFieldElement7 = (RealFieldElement) t5.multiply(realFieldElement.subtract(1.0d));
        if (getGlobalPreviousState() == null || t2.getReal() > 0.5d) {
            RealFieldElement realFieldElement8 = (RealFieldElement) t4.divide(-6.0d);
            RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement.add(2.0d)).subtract(realFieldElement2));
            realFieldElementArr = currentStateLinearCombination((RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement2.subtract(t5.multiply(5))).add(1.0d)), (RealFieldElement) realFieldElement9.multiply(this.one_minus_inv_sqrt_2), (RealFieldElement) realFieldElement9.multiply(this.one_plus_inv_sqrt_2), (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement2.add(t5)).add(1.0d)));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement3, realFieldElement5, realFieldElement6, realFieldElement7);
        } else {
            RealFieldElement realFieldElement10 = (RealFieldElement) t3.divide(6.0d);
            RealFieldElement realFieldElement11 = (RealFieldElement) realFieldElement10.multiply(((RealFieldElement) t5.multiply(6)).subtract(realFieldElement2));
            realFieldElementArr = previousStateLinearCombination((RealFieldElement) realFieldElement10.multiply(((RealFieldElement) realFieldElement2.subtract(t5.multiply(9))).add(6.0d)), (RealFieldElement) realFieldElement11.multiply(this.one_minus_inv_sqrt_2), (RealFieldElement) realFieldElement11.multiply(this.one_plus_inv_sqrt_2), (RealFieldElement) realFieldElement10.multiply(realFieldElement2.subtract(t5.multiply(3))));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement3, realFieldElement5, realFieldElement6, realFieldElement7);
        }
        return new FieldODEStateAndDerivative<>(t, realFieldElementArr, realFieldElementArr2);
    }
}
