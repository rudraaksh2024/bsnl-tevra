package org.apache.commons.math3.ml.distance;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;

public interface DistanceMeasure extends Serializable {
    double compute(double[] dArr, double[] dArr2) throws DimensionMismatchException;
}
