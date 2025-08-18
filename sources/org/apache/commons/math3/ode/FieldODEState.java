package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.util.MathArrays;

public class FieldODEState<T extends RealFieldElement<T>> {
    private final T[][] secondaryState;
    private final T[] state;
    private final T time;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FieldODEState(T t, T[] tArr) {
        this(t, tArr, (T[][]) null);
        RealFieldElement[][] realFieldElementArr = null;
    }

    public FieldODEState(T t, T[] tArr, T[][] tArr2) {
        this.time = t;
        this.state = (RealFieldElement[]) tArr.clone();
        this.secondaryState = copy(t.getField(), tArr2);
    }

    /* access modifiers changed from: protected */
    public T[][] copy(Field<T> field, T[][] tArr) {
        if (tArr == null) {
            RealFieldElement[][] realFieldElementArr = null;
            return null;
        }
        T[][] tArr2 = (RealFieldElement[][]) MathArrays.buildArray(field, tArr.length, -1);
        for (int i = 0; i < tArr.length; i++) {
            tArr2[i] = (RealFieldElement[]) tArr[i].clone();
        }
        return tArr2;
    }

    public T getTime() {
        return this.time;
    }

    public int getStateDimension() {
        return this.state.length;
    }

    public T[] getState() {
        return (RealFieldElement[]) this.state.clone();
    }

    public int getNumberOfSecondaryStates() {
        T[][] tArr = this.secondaryState;
        if (tArr == null) {
            return 0;
        }
        return tArr.length;
    }

    public int getSecondaryStateDimension(int i) {
        return i == 0 ? this.state.length : this.secondaryState[i - 1].length;
    }

    public T[] getSecondaryState(int i) {
        return (RealFieldElement[]) (i == 0 ? this.state.clone() : this.secondaryState[i - 1].clone());
    }
}
