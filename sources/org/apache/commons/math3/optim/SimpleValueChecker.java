package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

public class SimpleValueChecker extends AbstractConvergenceChecker<PointValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    public SimpleValueChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimpleValueChecker(double d, double d2, int i) {
        super(d, d2);
        if (i > 0) {
            this.maxIterationCount = i;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public boolean converged(int i, PointValuePair pointValuePair, PointValuePair pointValuePair2) {
        int i2 = this.maxIterationCount;
        if (i2 != -1 && i >= i2) {
            return true;
        }
        double doubleValue = ((Double) pointValuePair.getValue()).doubleValue();
        double doubleValue2 = ((Double) pointValuePair2.getValue()).doubleValue();
        double abs = FastMath.abs(doubleValue - doubleValue2);
        if (abs <= FastMath.max(FastMath.abs(doubleValue), FastMath.abs(doubleValue2)) * getRelativeThreshold() || abs <= getAbsoluteThreshold()) {
            return true;
        }
        return false;
    }
}
