package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class DormandPrince54FieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    private final T a70;
    private final T a72;
    private final T a73;
    private final T a74;
    private final T a75;
    private final T d0;
    private final T d2;
    private final T d3;
    private final T d4;
    private final T d5;
    private final T d6;

    DormandPrince54FieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
        RealFieldElement realFieldElement = (RealFieldElement) field.getOne();
        this.a70 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(35.0d)).divide(384.0d);
        this.a72 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(500.0d)).divide(1113.0d);
        this.a73 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(125.0d)).divide(192.0d);
        this.a74 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-2187.0d)).divide(6784.0d);
        this.a75 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(11.0d)).divide(84.0d);
        this.d0 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-1.2715105075E10d)).divide(1.1282082432E10d);
        this.d2 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(8.74874797E10d)).divide(3.2700410799E10d);
        this.d3 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-1.0690763975E10d)).divide(1.880347072E9d);
        this.d4 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(7.01980252875E11d)).divide(1.99316789632E11d);
        this.d5 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(-1.453857185E9d)).divide(8.22651844E8d);
        this.d6 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(6.9997945E7d)).divide(2.9380423E7d);
    }

    /* access modifiers changed from: protected */
    public DormandPrince54FieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new DormandPrince54FieldStepInterpolator(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        RealFieldElement[] realFieldElementArr;
        RealFieldElement[] realFieldElementArr2;
        T t5 = t2;
        T t6 = t3;
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.subtract(t5);
        RealFieldElement realFieldElement3 = (RealFieldElement) t5.multiply(2);
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement.subtract(realFieldElement3);
        RealFieldElement realFieldElement5 = (RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-3)).add(2.0d));
        RealFieldElement realFieldElement6 = (RealFieldElement) realFieldElement3.multiply(((RealFieldElement) t5.multiply(realFieldElement3.subtract(3.0d))).add(1.0d));
        if (getGlobalPreviousState() == null || t2.getReal() > 0.5d) {
            RealFieldElement realFieldElement7 = (RealFieldElement) t4.negate();
            RealFieldElement realFieldElement8 = (RealFieldElement) t4.multiply(t5);
            RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElement8.multiply(t5);
            RealFieldElement realFieldElement10 = (RealFieldElement) realFieldElement9.multiply(realFieldElement2);
            T t7 = this.a70;
            T t8 = this.a72;
            T t9 = this.a73;
            RealFieldElement realFieldElement11 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t8.subtract(realFieldElement4.multiply(t8))).add(realFieldElement5.multiply(this.a72.multiply(2)))).add(realFieldElement6.multiply(this.d2));
            T t10 = this.a74;
            RealFieldElement realFieldElement12 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t9.subtract(realFieldElement4.multiply(t9))).add(realFieldElement5.multiply(this.a73.multiply(2)))).add(realFieldElement6.multiply(this.d3));
            T t11 = this.a75;
            realFieldElementArr = currentStateLinearCombination((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(this.a70)).subtract(realFieldElement8.multiply(this.a70.subtract(1.0d)))).add(realFieldElement9.multiply(((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add(realFieldElement10.multiply(this.d0)), (RealFieldElement) t.getField().getZero(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(this.a72)).subtract(realFieldElement8.multiply(this.a72))).add(realFieldElement9.multiply(this.a72.multiply(2)))).add(realFieldElement10.multiply(this.d2)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(this.a73)).subtract(realFieldElement8.multiply(this.a73))).add(realFieldElement9.multiply(this.a73.multiply(2)))).add(realFieldElement10.multiply(this.d3)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(this.a74)).subtract(realFieldElement8.multiply(this.a74))).add(realFieldElement9.multiply(this.a74.multiply(2)))).add(realFieldElement10.multiply(this.d4)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(this.a75)).subtract(realFieldElement8.multiply(this.a75))).add(realFieldElement9.multiply(this.a75.multiply(2)))).add(realFieldElement10.multiply(this.d5)), (RealFieldElement) ((RealFieldElement) realFieldElement10.multiply(this.d6)).subtract(realFieldElement9));
            realFieldElementArr2 = derivativeLinearCombination((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t7.subtract(realFieldElement4.multiply(t7.subtract(1.0d)))).add(realFieldElement5.multiply(((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add(realFieldElement6.multiply(this.d0)), (RealFieldElement) t.getField().getZero(), realFieldElement11, realFieldElement12, (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t10.subtract(realFieldElement4.multiply(t10))).add(realFieldElement5.multiply(this.a74.multiply(2)))).add(realFieldElement6.multiply(this.d4)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t11.subtract(realFieldElement4.multiply(t11))).add(realFieldElement5.multiply(this.a75.multiply(2)))).add(realFieldElement6.multiply(this.d5)), (RealFieldElement) ((RealFieldElement) realFieldElement6.multiply(this.d6)).subtract(realFieldElement5));
        } else {
            RealFieldElement realFieldElement13 = (RealFieldElement) t6.multiply(realFieldElement2);
            RealFieldElement realFieldElement14 = (RealFieldElement) realFieldElement13.multiply(t5);
            RealFieldElement realFieldElement15 = (RealFieldElement) realFieldElement14.multiply(realFieldElement2);
            T t12 = this.a70;
            RealFieldElement realFieldElement16 = realFieldElement6;
            T t13 = this.a72;
            T t14 = this.a73;
            RealFieldElement realFieldElement17 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t13.subtract(realFieldElement4.multiply(t13))).add(realFieldElement5.multiply(this.a72.multiply(2)))).add(realFieldElement16.multiply(this.d2));
            T t15 = this.a74;
            RealFieldElement realFieldElement18 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t14.subtract(realFieldElement4.multiply(t14))).add(realFieldElement5.multiply(this.a73.multiply(2)))).add(realFieldElement16.multiply(this.d3));
            T t16 = this.a75;
            realFieldElementArr = previousStateLinearCombination((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(this.a70)).subtract(realFieldElement13.multiply(this.a70.subtract(1.0d)))).add(realFieldElement14.multiply(((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add(realFieldElement15.multiply(this.d0)), (RealFieldElement) t.getField().getZero(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(this.a72)).subtract(realFieldElement13.multiply(this.a72))).add(realFieldElement14.multiply(this.a72.multiply(2)))).add(realFieldElement15.multiply(this.d2)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(this.a73)).subtract(realFieldElement13.multiply(this.a73))).add(realFieldElement14.multiply(this.a73.multiply(2)))).add(realFieldElement15.multiply(this.d3)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(this.a74)).subtract(realFieldElement13.multiply(this.a74))).add(realFieldElement14.multiply(this.a74.multiply(2)))).add(realFieldElement15.multiply(this.d4)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(this.a75)).subtract(realFieldElement13.multiply(this.a75))).add(realFieldElement14.multiply(this.a75.multiply(2)))).add(realFieldElement15.multiply(this.d5)), (RealFieldElement) ((RealFieldElement) realFieldElement15.multiply(this.d6)).subtract(realFieldElement14));
            realFieldElementArr2 = derivativeLinearCombination((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t12.subtract(realFieldElement4.multiply(t12.subtract(1.0d)))).add(realFieldElement5.multiply(((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add(realFieldElement16.multiply(this.d0)), (RealFieldElement) t.getField().getZero(), realFieldElement17, realFieldElement18, (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t15.subtract(realFieldElement4.multiply(t15))).add(realFieldElement5.multiply(this.a74.multiply(2)))).add(realFieldElement16.multiply(this.d4)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t16.subtract(realFieldElement4.multiply(t16))).add(realFieldElement5.multiply(this.a75.multiply(2)))).add(realFieldElement16.multiply(this.d5)), (RealFieldElement) ((RealFieldElement) realFieldElement16.multiply(this.d6)).subtract(realFieldElement5));
        }
        return new FieldODEStateAndDerivative<>(t, realFieldElementArr, realFieldElementArr2);
    }
}
