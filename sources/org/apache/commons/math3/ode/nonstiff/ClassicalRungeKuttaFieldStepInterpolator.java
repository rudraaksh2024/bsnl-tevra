package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class ClassicalRungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    ClassicalRungeKuttaFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public ClassicalRungeKuttaFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ClassicalRungeKuttaFieldStepInterpolator(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        RealFieldElement[] realFieldElementArr;
        RealFieldElement[] realFieldElementArr2;
        T t5 = t2;
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.subtract(t5);
        RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement.subtract(t5.multiply(2));
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement2.multiply(realFieldElement3);
        RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) t5.multiply(realFieldElement2)).multiply(2);
        RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) t5.multiply(realFieldElement3)).negate();
        if (getGlobalPreviousState() == null || t2.getReal() > 0.5d) {
            RealFieldElement realFieldElement7 = (RealFieldElement) t5.multiply(4);
            RealFieldElement realFieldElement8 = (RealFieldElement) t4.divide(6.0d);
            RealFieldElement realFieldElement9 = realFieldElement4;
            RealFieldElement realFieldElement10 = (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) t5.multiply(realFieldElement7.subtract(2.0d))).subtract(2.0d));
            realFieldElementArr = currentStateLinearCombination((RealFieldElement) realFieldElement8.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) realFieldElement7.negate()).add(5.0d))).subtract(1.0d)), realFieldElement10, realFieldElement10, (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) realFieldElement7.negate()).subtract(1.0d))).subtract(1.0d)));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement9, realFieldElement5, realFieldElement5, realFieldElement6);
        } else {
            RealFieldElement realFieldElement11 = (RealFieldElement) ((RealFieldElement) t5.multiply(t5)).multiply(4);
            RealFieldElement realFieldElement12 = (RealFieldElement) t3.divide(6.0d);
            RealFieldElement realFieldElement13 = (RealFieldElement) realFieldElement12.multiply(((RealFieldElement) t5.multiply(6)).subtract(realFieldElement11));
            realFieldElementArr = previousStateLinearCombination((RealFieldElement) realFieldElement12.multiply(((RealFieldElement) realFieldElement11.subtract(t5.multiply(9))).add(6.0d)), realFieldElement13, realFieldElement13, (RealFieldElement) realFieldElement12.multiply(realFieldElement11.subtract(t5.multiply(3))));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement4, realFieldElement5, realFieldElement5, realFieldElement6);
        }
        return new FieldODEStateAndDerivative<>(t, realFieldElementArr, realFieldElementArr2);
    }
}
