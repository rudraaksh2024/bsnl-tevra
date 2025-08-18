package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

public class SimplePointChecker<PAIR extends Pair<double[], ? extends Object>> extends AbstractConvergenceChecker<PAIR> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    public SimplePointChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimplePointChecker(double d, double d2, int i) {
        super(d, d2);
        if (i > 0) {
            this.maxIterationCount = i;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public boolean converged(int i, PAIR pair, PAIR pair2) {
        int i2 = this.maxIterationCount;
        if (i2 != -1 && i >= i2) {
            return true;
        }
        double[] dArr = (double[]) pair.getKey();
        double[] dArr2 = (double[]) pair2.getKey();
        for (int i3 = 0; i3 < dArr.length; i3++) {
            double d = dArr[i3];
            double d2 = dArr2[i3];
            double abs = FastMath.abs(d - d2);
            if (abs > FastMath.max(FastMath.abs(d), FastMath.abs(d2)) * getRelativeThreshold() && abs > getAbsoluteThreshold()) {
                return false;
            }
        }
        return true;
    }
}
