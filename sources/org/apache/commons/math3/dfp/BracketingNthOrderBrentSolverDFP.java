package org.apache.commons.math3.dfp;

import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
public class BracketingNthOrderBrentSolverDFP extends FieldBracketingNthOrderBrentSolver<Dfp> {
    public BracketingNthOrderBrentSolverDFP(Dfp dfp, Dfp dfp2, Dfp dfp3, int i) throws NumberIsTooSmallException {
        super(dfp, dfp2, dfp3, i);
    }

    public Dfp getAbsoluteAccuracy() {
        return (Dfp) super.getAbsoluteAccuracy();
    }

    public Dfp getRelativeAccuracy() {
        return (Dfp) super.getRelativeAccuracy();
    }

    public Dfp getFunctionValueAccuracy() {
        return (Dfp) super.getFunctionValueAccuracy();
    }

    public Dfp solve(int i, UnivariateDfpFunction univariateDfpFunction, Dfp dfp, Dfp dfp2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return solve(i, univariateDfpFunction, dfp, dfp2, dfp.add(dfp2).divide(2), allowedSolution);
    }

    public Dfp solve(int i, final UnivariateDfpFunction univariateDfpFunction, Dfp dfp, Dfp dfp2, Dfp dfp3, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        MathUtils.checkNotNull(univariateDfpFunction);
        return (Dfp) solve(i, new RealFieldUnivariateFunction<Dfp>() {
            public Dfp value(Dfp dfp) {
                return univariateDfpFunction.value(dfp);
            }
        }, dfp, dfp2, dfp3, allowedSolution);
    }
}
