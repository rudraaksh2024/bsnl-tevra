package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class ThreeEighthesFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    ThreeEighthesFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public ThreeEighthesFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ThreeEighthesFieldStepInterpolator(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        RealFieldElement[] realFieldElementArr;
        RealFieldElement[] realFieldElementArr2;
        T t5 = t2;
        RealFieldElement realFieldElement = (RealFieldElement) t5.multiply(0.75d);
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(((RealFieldElement) t5.multiply(4)).subtract(5.0d))).add(1.0d);
        RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement.multiply(((RealFieldElement) t5.multiply(-6)).add(5.0d));
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement.multiply(((RealFieldElement) t5.multiply(2)).subtract(1.0d));
        if (getGlobalPreviousState() == null || t2.getReal() > 0.5d) {
            RealFieldElement realFieldElement5 = (RealFieldElement) t4.divide(-8.0d);
            RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) t5.multiply(t5)).multiply(4);
            RealFieldElement realFieldElement7 = (RealFieldElement) t5.add(1.0d);
            RealFieldElement[] currentStateLinearCombination = currentStateLinearCombination((RealFieldElement) realFieldElement5.multiply(((RealFieldElement) ((RealFieldElement) realFieldElement6.multiply(2)).subtract(t5.multiply(7))).add(1.0d)), (RealFieldElement) ((RealFieldElement) realFieldElement5.multiply(realFieldElement7.subtract(realFieldElement6))).multiply(3), (RealFieldElement) ((RealFieldElement) realFieldElement5.multiply(realFieldElement7)).multiply(3), (RealFieldElement) realFieldElement5.multiply(realFieldElement7.add(realFieldElement6)));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement2, realFieldElement3, realFieldElement, realFieldElement4);
            realFieldElementArr = currentStateLinearCombination;
        } else {
            RealFieldElement realFieldElement8 = (RealFieldElement) t3.divide(8.0d);
            RealFieldElement realFieldElement9 = (RealFieldElement) ((RealFieldElement) t5.multiply(t5)).multiply(4);
            realFieldElementArr = previousStateLinearCombination((RealFieldElement) realFieldElement8.multiply(((RealFieldElement) ((RealFieldElement) realFieldElement9.multiply(2)).subtract(t5.multiply(15))).add(8.0d)), (RealFieldElement) ((RealFieldElement) realFieldElement8.multiply(((RealFieldElement) t5.multiply(5)).subtract(realFieldElement9))).multiply(3), (RealFieldElement) ((RealFieldElement) realFieldElement8.multiply(t5)).multiply(3), (RealFieldElement) realFieldElement8.multiply(realFieldElement9.subtract(t5.multiply(3))));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement2, realFieldElement3, realFieldElement, realFieldElement4);
        }
        return new FieldODEStateAndDerivative<>(t, realFieldElementArr, realFieldElementArr2);
    }
}
