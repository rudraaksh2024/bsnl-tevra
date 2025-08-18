package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.univariate.BracketFinder;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;

public class LineSearch {
    private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;
    private static final double REL_TOL_UNUSED = 1.0E-15d;
    private final BracketFinder bracket = new BracketFinder();
    private final double initialBracketingRange;
    private final UnivariateOptimizer lineOptimizer;
    /* access modifiers changed from: private */
    public final MultivariateOptimizer mainOptimizer;

    public LineSearch(MultivariateOptimizer multivariateOptimizer, double d, double d2, double d3) {
        this.mainOptimizer = multivariateOptimizer;
        this.lineOptimizer = new BrentOptimizer(1.0E-15d, Double.MIN_VALUE, new SimpleUnivariateValueChecker(d, d2));
        this.initialBracketingRange = d3;
    }

    public UnivariatePointValuePair search(final double[] dArr, final double[] dArr2) {
        final int length = dArr.length;
        AnonymousClass1 r8 = new UnivariateFunction() {
            public double value(double d) {
                double[] dArr = new double[length];
                for (int i = 0; i < length; i++) {
                    dArr[i] = dArr[i] + (dArr2[i] * d);
                }
                return LineSearch.this.mainOptimizer.computeObjectiveValue(dArr);
            }
        };
        GoalType goalType = this.mainOptimizer.getGoalType();
        this.bracket.search(r8, goalType, 0.0d, this.initialBracketingRange);
        return this.lineOptimizer.optimize(new OptimizationData[]{new MaxEval(Integer.MAX_VALUE), new UnivariateObjectiveFunction(r8), goalType, new SearchInterval(this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid())});
    }
}
