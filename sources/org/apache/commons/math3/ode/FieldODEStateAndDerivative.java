package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;

public class FieldODEStateAndDerivative<T extends RealFieldElement<T>> extends FieldODEState<T> {
    private final T[] derivative;
    private final T[][] secondaryDerivative;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FieldODEStateAndDerivative(T t, T[] tArr, T[] tArr2) {
        this(t, tArr, tArr2, (T[][]) null, (T[][]) null);
        RealFieldElement[][] realFieldElementArr = null;
    }

    public FieldODEStateAndDerivative(T t, T[] tArr, T[] tArr2, T[][] tArr3, T[][] tArr4) {
        super(t, tArr, tArr3);
        this.derivative = (RealFieldElement[]) tArr2.clone();
        this.secondaryDerivative = copy(t.getField(), tArr4);
    }

    public T[] getDerivative() {
        return (RealFieldElement[]) this.derivative.clone();
    }

    public T[] getSecondaryDerivative(int i) {
        return (RealFieldElement[]) (i == 0 ? this.derivative.clone() : this.secondaryDerivative[i - 1].clone());
    }
}
