package org.apache.commons.math3.ode;

import java.io.Serializable;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class FieldEquationsMapper<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20151114;
    private final int[] start;

    FieldEquationsMapper(FieldEquationsMapper<T> fieldEquationsMapper, int i) {
        int i2;
        if (fieldEquationsMapper == null) {
            i2 = 0;
        } else {
            i2 = fieldEquationsMapper.getNumberOfEquations();
        }
        int[] iArr = new int[(i2 + 2)];
        this.start = iArr;
        if (fieldEquationsMapper == null) {
            iArr[0] = 0;
        } else {
            System.arraycopy(fieldEquationsMapper.start, 0, iArr, 0, i2 + 1);
        }
        iArr[i2 + 1] = iArr[i2] + i;
    }

    public int getNumberOfEquations() {
        return this.start.length - 1;
    }

    public int getTotalDimension() {
        int[] iArr = this.start;
        return iArr[iArr.length - 1];
    }

    public T[] mapState(FieldODEState<T> fieldODEState) {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(fieldODEState.getTime().getField(), getTotalDimension());
        int i = 0;
        insertEquationData(0, fieldODEState.getState(), tArr);
        while (true) {
            i++;
            if (i >= getNumberOfEquations()) {
                return tArr;
            }
            insertEquationData(i, fieldODEState.getSecondaryState(i), tArr);
        }
    }

    public T[] mapDerivative(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(fieldODEStateAndDerivative.getTime().getField(), getTotalDimension());
        int i = 0;
        insertEquationData(0, fieldODEStateAndDerivative.getDerivative(), tArr);
        while (true) {
            i++;
            if (i >= getNumberOfEquations()) {
                return tArr;
            }
            insertEquationData(i, fieldODEStateAndDerivative.getSecondaryDerivative(i), tArr);
        }
    }

    public FieldODEStateAndDerivative<T> mapStateAndDerivative(T t, T[] tArr, T[] tArr2) throws DimensionMismatchException {
        if (tArr.length != getTotalDimension()) {
            throw new DimensionMismatchException(tArr.length, getTotalDimension());
        } else if (tArr2.length == getTotalDimension()) {
            int numberOfEquations = getNumberOfEquations();
            int i = 0;
            RealFieldElement[] extractEquationData = extractEquationData(0, tArr);
            RealFieldElement[] extractEquationData2 = extractEquationData(0, tArr2);
            if (numberOfEquations < 2) {
                return new FieldODEStateAndDerivative<>(t, extractEquationData, extractEquationData2);
            }
            int i2 = numberOfEquations - 1;
            RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(t.getField(), i2, -1);
            RealFieldElement[][] realFieldElementArr2 = (RealFieldElement[][]) MathArrays.buildArray(t.getField(), i2, -1);
            while (true) {
                i++;
                if (i >= getNumberOfEquations()) {
                    return new FieldODEStateAndDerivative(t, extractEquationData, extractEquationData2, realFieldElementArr, realFieldElementArr2);
                }
                int i3 = i - 1;
                realFieldElementArr[i3] = extractEquationData(i, tArr);
                realFieldElementArr2[i3] = extractEquationData(i, tArr2);
            }
        } else {
            throw new DimensionMismatchException(tArr2.length, getTotalDimension());
        }
    }

    public T[] extractEquationData(int i, T[] tArr) throws MathIllegalArgumentException, DimensionMismatchException {
        checkIndex(i);
        int[] iArr = this.start;
        int i2 = iArr[i];
        int i3 = iArr[i + 1];
        if (tArr.length >= i3) {
            int i4 = i3 - i2;
            T[] tArr2 = (RealFieldElement[]) MathArrays.buildArray(tArr[0].getField(), i4);
            System.arraycopy(tArr, i2, tArr2, 0, i4);
            return tArr2;
        }
        throw new DimensionMismatchException(tArr.length, i3);
    }

    public void insertEquationData(int i, T[] tArr, T[] tArr2) throws DimensionMismatchException {
        checkIndex(i);
        int[] iArr = this.start;
        int i2 = iArr[i];
        int i3 = iArr[i + 1];
        int i4 = i3 - i2;
        if (tArr2.length < i3) {
            throw new DimensionMismatchException(tArr2.length, i3);
        } else if (tArr.length == i4) {
            System.arraycopy(tArr, 0, tArr2, i2, i4);
        } else {
            throw new DimensionMismatchException(tArr.length, i4);
        }
    }

    private void checkIndex(int i) throws MathIllegalArgumentException {
        if (i < 0 || i > this.start.length - 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, Integer.valueOf(i), 0, Integer.valueOf(this.start.length - 2));
        }
    }
}
