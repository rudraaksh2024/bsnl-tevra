package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

class AdamsFieldStepInterpolator<T extends RealFieldElement<T>> extends AbstractFieldStepInterpolator<T> {
    private final Array2DRowFieldMatrix<T> nordsieck;
    private final FieldODEStateAndDerivative<T> reference;
    private final T[] scaled;
    private T scalingH;

    AdamsFieldStepInterpolator(T t, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T[] tArr, Array2DRowFieldMatrix<T> array2DRowFieldMatrix, boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldEquationsMapper<T> fieldEquationsMapper) {
        this(t, fieldODEStateAndDerivative, tArr, array2DRowFieldMatrix, z, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldEquationsMapper);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AdamsFieldStepInterpolator(T t, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T[] tArr, Array2DRowFieldMatrix<T> array2DRowFieldMatrix, boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative5, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(z, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldODEStateAndDerivative5, fieldEquationsMapper);
        this.scalingH = t;
        this.reference = fieldODEStateAndDerivative;
        this.scaled = (RealFieldElement[]) tArr.clone();
        this.nordsieck = new Array2DRowFieldMatrix<>((T[][]) array2DRowFieldMatrix.getData(), false);
    }

    /* access modifiers changed from: protected */
    public AdamsFieldStepInterpolator<T> create(boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new AdamsFieldStepInterpolator(this.scalingH, this.reference, this.scaled, this.nordsieck, z, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        return taylor(this.reference, t, this.scalingH, this.scaled, this.nordsieck);
    }

    public static <S extends RealFieldElement<S>> FieldODEStateAndDerivative<S> taylor(FieldODEStateAndDerivative<S> fieldODEStateAndDerivative, S s, S s2, S[] sArr, Array2DRowFieldMatrix<S> array2DRowFieldMatrix) {
        int i;
        RealFieldElement realFieldElement = (RealFieldElement) s.subtract(fieldODEStateAndDerivative.getTime());
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.divide(s2);
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(s.getField(), sArr.length);
        Arrays.fill(realFieldElementArr, s.getField().getZero());
        RealFieldElement[] realFieldElementArr2 = (RealFieldElement[]) MathArrays.buildArray(s.getField(), sArr.length);
        Arrays.fill(realFieldElementArr2, s.getField().getZero());
        RealFieldElement[][] realFieldElementArr3 = (RealFieldElement[][]) array2DRowFieldMatrix.getDataRef();
        int length = realFieldElementArr3.length;
        while (true) {
            length--;
            i = 0;
            if (length < 0) {
                break;
            }
            int i2 = length + 2;
            RealFieldElement[] realFieldElementArr4 = realFieldElementArr3[length];
            RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement2.pow(i2);
            while (i < realFieldElementArr4.length) {
                RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElementArr4[i].multiply(realFieldElement3);
                realFieldElementArr[i] = (RealFieldElement) realFieldElementArr[i].add(realFieldElement4);
                realFieldElementArr2[i] = (RealFieldElement) realFieldElementArr2[i].add(realFieldElement4.multiply(i2));
                i++;
            }
        }
        RealFieldElement[] state = fieldODEStateAndDerivative.getState();
        while (i < realFieldElementArr.length) {
            RealFieldElement realFieldElement5 = (RealFieldElement) realFieldElementArr[i].add(sArr[i].multiply(realFieldElement2));
            realFieldElementArr[i] = realFieldElement5;
            state[i] = (RealFieldElement) state[i].add(realFieldElement5);
            realFieldElementArr2[i] = (RealFieldElement) ((RealFieldElement) realFieldElementArr2[i].add(sArr[i].multiply(realFieldElement2))).divide(realFieldElement);
            i++;
        }
        return new FieldODEStateAndDerivative<>(s, state, realFieldElementArr2);
    }
}
