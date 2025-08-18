package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public final class IntervalUtils {
    private static final BinomialConfidenceInterval AGRESTI_COULL = new AgrestiCoullInterval();
    private static final BinomialConfidenceInterval CLOPPER_PEARSON = new ClopperPearsonInterval();
    private static final BinomialConfidenceInterval NORMAL_APPROXIMATION = new NormalApproximationInterval();
    private static final BinomialConfidenceInterval WILSON_SCORE = new WilsonScoreInterval();

    private IntervalUtils() {
    }

    public static ConfidenceInterval getAgrestiCoullInterval(int i, int i2, double d) {
        return AGRESTI_COULL.createInterval(i, i2, d);
    }

    public static ConfidenceInterval getClopperPearsonInterval(int i, int i2, double d) {
        return CLOPPER_PEARSON.createInterval(i, i2, d);
    }

    public static ConfidenceInterval getNormalApproximationInterval(int i, int i2, double d) {
        return NORMAL_APPROXIMATION.createInterval(i, i2, d);
    }

    public static ConfidenceInterval getWilsonScoreInterval(int i, int i2, double d) {
        return WILSON_SCORE.createInterval(i, i2, d);
    }

    static void checkParameters(int i, int i2, double d) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_TRIALS, Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NEGATIVE_NUMBER_OF_SUCCESSES, Integer.valueOf(i2));
        } else if (i2 > i) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE, Integer.valueOf(i2), Integer.valueOf(i), true);
        } else if (d <= 0.0d || d >= 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_CONFIDENCE_LEVEL, Double.valueOf(d), 0, 1);
        }
    }
}
