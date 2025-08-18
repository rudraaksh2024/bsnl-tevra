package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.MathArrays;

public class FieldExpandableODE<T extends RealFieldElement<T>> {
    private List<FieldSecondaryEquations<T>> components = new ArrayList();
    private FieldEquationsMapper<T> mapper;
    private final FirstOrderFieldDifferentialEquations<T> primary;

    public FieldExpandableODE(FirstOrderFieldDifferentialEquations<T> firstOrderFieldDifferentialEquations) {
        this.primary = firstOrderFieldDifferentialEquations;
        this.mapper = new FieldEquationsMapper<>((FieldEquationsMapper) null, firstOrderFieldDifferentialEquations.getDimension());
    }

    public FieldEquationsMapper<T> getMapper() {
        return this.mapper;
    }

    public int addSecondaryEquations(FieldSecondaryEquations<T> fieldSecondaryEquations) {
        this.components.add(fieldSecondaryEquations);
        this.mapper = new FieldEquationsMapper<>(this.mapper, fieldSecondaryEquations.getDimension());
        return this.components.size();
    }

    public void init(T t, T[] tArr, T t2) {
        int i = 0;
        RealFieldElement[] extractEquationData = this.mapper.extractEquationData(0, tArr);
        this.primary.init(t, extractEquationData, t2);
        while (true) {
            i++;
            if (i < this.mapper.getNumberOfEquations()) {
                this.components.get(i - 1).init(t, extractEquationData, this.mapper.extractEquationData(i, tArr), t2);
            } else {
                return;
            }
        }
    }

    public T[] computeDerivatives(T t, T[] tArr) throws MaxCountExceededException, DimensionMismatchException {
        T[] tArr2 = (RealFieldElement[]) MathArrays.buildArray(t.getField(), this.mapper.getTotalDimension());
        int i = 0;
        RealFieldElement[] extractEquationData = this.mapper.extractEquationData(0, tArr);
        RealFieldElement[] computeDerivatives = this.primary.computeDerivatives(t, extractEquationData);
        this.mapper.insertEquationData(0, computeDerivatives, tArr2);
        while (true) {
            i++;
            if (i >= this.mapper.getNumberOfEquations()) {
                return tArr2;
            }
            this.mapper.insertEquationData(i, this.components.get(i - 1).computeDerivatives(t, extractEquationData, computeDerivatives, this.mapper.extractEquationData(i, tArr)), tArr2);
        }
    }
}
