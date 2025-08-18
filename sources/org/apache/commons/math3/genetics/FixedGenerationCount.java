package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class FixedGenerationCount implements StoppingCondition {
    private final int maxGenerations;
    private int numGenerations = 0;

    public FixedGenerationCount(int i) throws NumberIsTooSmallException {
        if (i > 0) {
            this.maxGenerations = i;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 1, true);
    }

    public boolean isSatisfied(Population population) {
        int i = this.numGenerations;
        if (i >= this.maxGenerations) {
            return true;
        }
        this.numGenerations = i + 1;
        return false;
    }

    public int getNumGenerations() {
        return this.numGenerations;
    }
}
