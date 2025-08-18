package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

abstract class RungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>> extends AbstractFieldStepInterpolator<T> {
    private final Field<T> field;
    private final T[][] yDotK;

    /* access modifiers changed from: protected */
    public abstract RungeKuttaFieldStepInterpolator<T> create(Field<T> field2, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper);

    protected RungeKuttaFieldStepInterpolator(Field<T> field2, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(z, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
        this.field = field2;
        this.yDotK = (RealFieldElement[][]) MathArrays.buildArray(field2, tArr.length, -1);
        for (int i = 0; i < tArr.length; i++) {
            this.yDotK[i] = (RealFieldElement[]) tArr[i].clone();
        }
    }

    /* access modifiers changed from: protected */
    public RungeKuttaFieldStepInterpolator<T> create(boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return create(this.field, z, this.yDotK, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public final T[] previousStateLinearCombination(T... tArr) {
        return combine(getPreviousState().getState(), tArr);
    }

    /* access modifiers changed from: protected */
    public T[] currentStateLinearCombination(T... tArr) {
        return combine(getCurrentState().getState(), tArr);
    }

    /* access modifiers changed from: protected */
    public T[] derivativeLinearCombination(T... tArr) {
        return combine((RealFieldElement[]) MathArrays.buildArray(this.field, this.yDotK[0].length), tArr);
    }

    private T[] combine(T[] tArr, T... tArr2) {
        for (int i = 0; i < tArr.length; i++) {
            for (int i2 = 0; i2 < tArr2.length; i2++) {
                tArr[i] = (RealFieldElement) tArr[i].add(tArr2[i2].multiply(this.yDotK[i2][i]));
            }
        }
        return tArr;
    }
}
