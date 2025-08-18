package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class LegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {
    private static final double[] ABSCISSAS_2 = {-1.0d / FastMath.sqrt(3.0d), 1.0d / FastMath.sqrt(3.0d)};
    private static final double[] ABSCISSAS_3 = {-FastMath.sqrt(0.6d), 0.0d, FastMath.sqrt(0.6d)};
    private static final double[] ABSCISSAS_4 = {-FastMath.sqrt(((FastMath.sqrt(30.0d) * 2.0d) + 15.0d) / 35.0d), -FastMath.sqrt((15.0d - (FastMath.sqrt(30.0d) * 2.0d)) / 35.0d), FastMath.sqrt((15.0d - (FastMath.sqrt(30.0d) * 2.0d)) / 35.0d), FastMath.sqrt(((FastMath.sqrt(30.0d) * 2.0d) + 15.0d) / 35.0d)};
    private static final double[] ABSCISSAS_5 = {-FastMath.sqrt(((FastMath.sqrt(70.0d) * 2.0d) + 35.0d) / 63.0d), -FastMath.sqrt((35.0d - (FastMath.sqrt(70.0d) * 2.0d)) / 63.0d), 0.0d, FastMath.sqrt((35.0d - (FastMath.sqrt(70.0d) * 2.0d)) / 63.0d), FastMath.sqrt(((FastMath.sqrt(70.0d) * 2.0d) + 35.0d) / 63.0d)};
    private static final double[] WEIGHTS_2 = {1.0d, 1.0d};
    private static final double[] WEIGHTS_3 = {0.5555555555555556d, 0.8888888888888888d, 0.5555555555555556d};
    private static final double[] WEIGHTS_4 = {(90.0d - (FastMath.sqrt(30.0d) * 5.0d)) / 180.0d, ((FastMath.sqrt(30.0d) * 5.0d) + 90.0d) / 180.0d, ((FastMath.sqrt(30.0d) * 5.0d) + 90.0d) / 180.0d, (90.0d - (FastMath.sqrt(30.0d) * 5.0d)) / 180.0d};
    private static final double[] WEIGHTS_5 = {(322.0d - (FastMath.sqrt(70.0d) * 13.0d)) / 900.0d, ((FastMath.sqrt(70.0d) * 13.0d) + 322.0d) / 900.0d, 0.5688888888888889d, ((FastMath.sqrt(70.0d) * 13.0d) + 322.0d) / 900.0d, (322.0d - (FastMath.sqrt(70.0d) * 13.0d)) / 900.0d};
    private final double[] abscissas;
    private final double[] weights;

    public LegendreGaussIntegrator(int i, double d, double d2, int i2, int i3) throws MathIllegalArgumentException, NotStrictlyPositiveException, NumberIsTooSmallException {
        super(d, d2, i2, i3);
        if (i == 2) {
            this.abscissas = ABSCISSAS_2;
            this.weights = WEIGHTS_2;
        } else if (i == 3) {
            this.abscissas = ABSCISSAS_3;
            this.weights = WEIGHTS_3;
        } else if (i == 4) {
            this.abscissas = ABSCISSAS_4;
            this.weights = WEIGHTS_4;
        } else if (i == 5) {
            this.abscissas = ABSCISSAS_5;
            this.weights = WEIGHTS_5;
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.N_POINTS_GAUSS_LEGENDRE_INTEGRATOR_NOT_SUPPORTED, Integer.valueOf(i), 2, 5);
        }
    }

    public LegendreGaussIntegrator(int i, double d, double d2) throws MathIllegalArgumentException {
        this(i, d, d2, 3, Integer.MAX_VALUE);
    }

    public LegendreGaussIntegrator(int i, int i2, int i3) throws MathIllegalArgumentException {
        this(i, 1.0E-6d, 1.0E-15d, i2, i3);
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double stage = stage(1);
        int i = 2;
        while (true) {
            double stage2 = stage(i);
            double abs = FastMath.abs(stage2 - stage);
            double max = FastMath.max(getAbsoluteAccuracy(), getRelativeAccuracy() * (FastMath.abs(stage) + FastMath.abs(stage2)) * 0.5d);
            if (getIterations() + 1 >= getMinimalIterationCount() && abs <= max) {
                return stage2;
            }
            i = FastMath.max((int) (FastMath.min(4.0d, FastMath.pow(abs / max, 0.5d / ((double) this.abscissas.length))) * ((double) i)), i + 1);
            incrementCount();
            stage = stage2;
        }
    }

    private double stage(int i) throws TooManyEvaluationsException {
        int i2 = i;
        double max = (getMax() - getMin()) / ((double) i2);
        double d = max / 2.0d;
        double min = getMin() + d;
        double d2 = 0.0d;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = 0;
            while (true) {
                double[] dArr = this.abscissas;
                if (i4 >= dArr.length) {
                    break;
                }
                d2 += this.weights[i4] * computeObjectiveValue(min + (dArr[i4] * d));
                i4++;
                i3 = i3;
            }
            min += max;
            i3++;
        }
        return d * d2;
    }
}
