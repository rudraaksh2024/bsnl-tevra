package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class HighamHall54FieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    HighamHall54FieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public HighamHall54FieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new HighamHall54FieldStepInterpolator(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        RealFieldElement[] realFieldElementArr;
        RealFieldElement[] realFieldElementArr2;
        T t5 = t2;
        T t6 = t3;
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-10.0d)).add(16.0d))).add(-7.5d))).add(1.0d);
        RealFieldElement realFieldElement2 = (RealFieldElement) t.getField().getZero();
        RealFieldElement realFieldElement3 = (RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(67.5d)).add(-91.125d))).add(28.6875d));
        RealFieldElement realFieldElement4 = (RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-120.0d)).add(152.0d))).add(-44.0d));
        RealFieldElement realFieldElement5 = (RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(62.5d)).add(-78.125d))).add(23.4375d));
        RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) t5.multiply(0.625d)).multiply(((RealFieldElement) t5.multiply(2)).subtract(1.0d));
        if (getGlobalPreviousState() == null || t2.getReal() > 0.5d) {
            RealFieldElement realFieldElement7 = (RealFieldElement) t5.multiply(t5);
            RealFieldElement realFieldElement8 = (RealFieldElement) t6.divide(t5);
            realFieldElementArr = currentStateLinearCombination((RealFieldElement) realFieldElement8.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-2.5d)).add(5.333333333333333d))).add(-3.75d))).add(1.0d))).add(-0.08333333333333333d)), (RealFieldElement) t.getField().getZero(), (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement7.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(16.875d)).add(-30.375d))).add(14.34375d))).add(-0.84375d)), (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement7.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-30.0d)).add(50.666666666666664d))).add(-22.0d))).add(1.3333333333333333d)), (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement7.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(15.625d)).add(-26.041666666666668d))).add(11.71875d))).add(-1.3020833333333333d)), (RealFieldElement) realFieldElement8.multiply(((RealFieldElement) realFieldElement7.multiply(((RealFieldElement) t5.multiply(0.4166666666666667d)).add(-0.3125d))).add(-0.10416666666666667d)));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement, realFieldElement2, realFieldElement3, realFieldElement4, realFieldElement5, realFieldElement6);
        } else {
            realFieldElementArr = previousStateLinearCombination((RealFieldElement) t6.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-2.5d)).add(5.333333333333333d))).add(-3.75d))).add(1.0d)), (RealFieldElement) t.getField().getZero(), (RealFieldElement) t6.multiply(t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(16.875d)).add(-30.375d))).add(14.34375d))), (RealFieldElement) t6.multiply(t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-30.0d)).add(50.666666666666664d))).add(-22.0d))), (RealFieldElement) t6.multiply(t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(15.625d)).add(-26.041666666666668d))).add(11.71875d))), (RealFieldElement) t6.multiply(t5.multiply(((RealFieldElement) t5.multiply(0.4166666666666667d)).add(-0.3125d))));
            realFieldElementArr2 = derivativeLinearCombination(realFieldElement, realFieldElement2, realFieldElement3, realFieldElement4, realFieldElement5, realFieldElement6);
        }
        return new FieldODEStateAndDerivative<>(t, realFieldElementArr, realFieldElementArr2);
    }
}
