package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

public class TricubicInterpolatingFunction implements TrivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, -9.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, -4.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{9.0d, -9.0d, 0.0d, 0.0d, -9.0d, 9.0d, 0.0d, 0.0d, 6.0d, 3.0d, 0.0d, 0.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, -3.0d, -3.0d, 0.0d, 0.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, -9.0d, 0.0d, 0.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, 0.0d, 0.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 3.0d, -3.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 2.0d, 1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 0.0d, 0.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, -2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, -1.0d, -1.0d, 0.0d, 0.0d}, new double[]{9.0d, 0.0d, -9.0d, 0.0d, -9.0d, 0.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 0.0d, 3.0d, 0.0d, -6.0d, 0.0d, -3.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, 0.0d, -9.0d, 0.0d, -9.0d, 0.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 0.0d, 3.0d, 0.0d, -6.0d, 0.0d, -3.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, 1.0d, 0.0d}, new double[]{-27.0d, 27.0d, 27.0d, -27.0d, 27.0d, -27.0d, -27.0d, 27.0d, -18.0d, -9.0d, 18.0d, 9.0d, 18.0d, 9.0d, -18.0d, -9.0d, -18.0d, 18.0d, -9.0d, 9.0d, 18.0d, -18.0d, 9.0d, -9.0d, -18.0d, 18.0d, 18.0d, -18.0d, -9.0d, 9.0d, 9.0d, -9.0d, -12.0d, -6.0d, -6.0d, -3.0d, 12.0d, 6.0d, 6.0d, 3.0d, -12.0d, -6.0d, 12.0d, 6.0d, -6.0d, -3.0d, 6.0d, 3.0d, -12.0d, 12.0d, -6.0d, 6.0d, -6.0d, 6.0d, -3.0d, 3.0d, -8.0d, -4.0d, -4.0d, -2.0d, -4.0d, -2.0d, -2.0d, -1.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 9.0d, 9.0d, -9.0d, -9.0d, -9.0d, -9.0d, 9.0d, 9.0d, 12.0d, -12.0d, 6.0d, -6.0d, -12.0d, 12.0d, -6.0d, 6.0d, 12.0d, -12.0d, -12.0d, 12.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 3.0d, 3.0d, -6.0d, -6.0d, -3.0d, -3.0d, 6.0d, 6.0d, -6.0d, -6.0d, 3.0d, 3.0d, -3.0d, -3.0d, 8.0d, -8.0d, 4.0d, -4.0d, 4.0d, -4.0d, 2.0d, -2.0d, 4.0d, 4.0d, 2.0d, 2.0d, 2.0d, 2.0d, 1.0d, 1.0d}, new double[]{-6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 3.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 3.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -1.0d, 0.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 12.0d, 6.0d, -12.0d, -6.0d, -12.0d, -6.0d, 12.0d, 6.0d, 9.0d, -9.0d, 9.0d, -9.0d, -9.0d, 9.0d, -9.0d, 9.0d, 12.0d, -12.0d, -12.0d, 12.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, 3.0d, 6.0d, 3.0d, -6.0d, -3.0d, -6.0d, -3.0d, 8.0d, 4.0d, -8.0d, -4.0d, 4.0d, 2.0d, -4.0d, -2.0d, 6.0d, -6.0d, 6.0d, -6.0d, 3.0d, -3.0d, 3.0d, -3.0d, 4.0d, 2.0d, 4.0d, 2.0d, 2.0d, 1.0d, 2.0d, 1.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 6.0d, -6.0d, -6.0d, -6.0d, 6.0d, -6.0d, 6.0d, 6.0d, -6.0d, 6.0d, -6.0d, -8.0d, 8.0d, 8.0d, -8.0d, -4.0d, 4.0d, 4.0d, -4.0d, -3.0d, -3.0d, -3.0d, -3.0d, 3.0d, 3.0d, 3.0d, 3.0d, -4.0d, -4.0d, 4.0d, 4.0d, -2.0d, -2.0d, 2.0d, 2.0d, -4.0d, 4.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, -2.0d, -2.0d, -2.0d, -1.0d, -1.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, -4.0d, -2.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{4.0d, -4.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, 2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, -4.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 0.0d, -2.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 0.0d, -2.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 12.0d, 6.0d, -12.0d, -6.0d, -12.0d, -6.0d, 12.0d, 6.0d, 12.0d, -12.0d, 6.0d, -6.0d, -12.0d, 12.0d, -6.0d, 6.0d, 9.0d, -9.0d, -9.0d, 9.0d, 9.0d, -9.0d, -9.0d, 9.0d, 8.0d, 4.0d, 4.0d, 2.0d, -8.0d, -4.0d, -4.0d, -2.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 6.0d, -6.0d, -6.0d, -8.0d, 8.0d, -4.0d, 4.0d, 8.0d, -8.0d, 4.0d, -4.0d, -6.0d, 6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -4.0d, -2.0d, -2.0d, 4.0d, 4.0d, 2.0d, 2.0d, -3.0d, -3.0d, 3.0d, 3.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{4.0d, 0.0d, -4.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, -4.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -8.0d, -4.0d, 8.0d, 4.0d, 8.0d, 4.0d, -8.0d, -4.0d, -6.0d, 6.0d, -6.0d, 6.0d, 6.0d, -6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, -4.0d, -2.0d, 4.0d, 2.0d, 4.0d, 2.0d, -4.0d, -2.0d, 4.0d, 2.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{8.0d, -8.0d, -8.0d, 8.0d, -8.0d, 8.0d, 8.0d, -8.0d, 4.0d, 4.0d, -4.0d, -4.0d, -4.0d, -4.0d, 4.0d, 4.0d, 4.0d, -4.0d, 4.0d, -4.0d, -4.0d, 4.0d, -4.0d, 4.0d, 4.0d, -4.0d, -4.0d, 4.0d, 4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, 2.0d, 2.0d, -2.0d, -2.0d, -2.0d, -2.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private final TricubicFunction[][][] splines;
    private final double[] xval;
    private final double[] yval;
    private final double[] zval;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v13, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v19, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v21, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v3, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v4, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v5, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v3, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v4, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v11, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v4, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v9, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v7, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v10, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v7, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v14, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v10, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v16, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v11, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v18, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v20, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v11, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v10, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v11, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v23, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v25, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v27, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v29, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v12, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v17, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v19, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v3, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v21, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v23, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v13, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v18, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v4, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v20, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v24, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v22, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v24, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v14, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v9, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v26, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v28, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v27, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v10, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v29, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v29, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v31, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v33, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v18, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v11, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v5, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v31, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v33, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v35, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v37, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v39, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v17, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v19, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v21, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v9, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v10, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v22, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v4, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v11, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v6, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v23, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v25, resolved type: double} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TricubicInterpolatingFunction(double[] r47, double[] r48, double[] r49, double[][][] r50, double[][][] r51, double[][][] r52, double[][][] r53, double[][][] r54, double[][][] r55, double[][][] r56, double[][][] r57) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NonMonotonicSequenceException {
        /*
            r46 = this;
            r0 = r46
            r1 = r49
            r2 = r50
            r3 = r51
            r4 = r52
            r5 = r53
            r6 = r54
            r7 = r55
            r8 = r56
            r9 = r57
            r46.<init>()
            r10 = r47
            int r11 = r10.length
            r12 = r48
            int r13 = r12.length
            int r14 = r1.length
            if (r11 == 0) goto L_0x048e
            if (r13 == 0) goto L_0x048e
            int r15 = r1.length
            if (r15 == 0) goto L_0x048e
            int r15 = r2.length
            if (r15 == 0) goto L_0x048e
            r15 = 0
            r1 = r2[r15]
            int r1 = r1.length
            if (r1 == 0) goto L_0x048e
            int r1 = r2.length
            if (r11 != r1) goto L_0x0487
            int r1 = r3.length
            if (r11 != r1) goto L_0x0480
            int r1 = r4.length
            if (r11 != r1) goto L_0x0479
            int r1 = r5.length
            if (r11 != r1) goto L_0x0472
            int r1 = r6.length
            if (r11 != r1) goto L_0x046b
            int r1 = r7.length
            if (r11 != r1) goto L_0x0464
            int r1 = r8.length
            if (r11 != r1) goto L_0x045d
            int r1 = r9.length
            if (r11 != r1) goto L_0x0456
            org.apache.commons.math3.util.MathArrays.checkOrder(r47)
            org.apache.commons.math3.util.MathArrays.checkOrder(r48)
            org.apache.commons.math3.util.MathArrays.checkOrder(r49)
            java.lang.Object r1 = r47.clone()
            double[] r1 = (double[]) r1
            r0.xval = r1
            java.lang.Object r1 = r48.clone()
            double[] r1 = (double[]) r1
            r0.yval = r1
            java.lang.Object r1 = r49.clone()
            double[] r1 = (double[]) r1
            r0.zval = r1
            r1 = 1
            int r11 = r11 - r1
            int r10 = r13 + -1
            int r12 = r14 + -1
            r15 = 3
            int[] r1 = new int[r15]
            r17 = 2
            r1[r17] = r12
            r18 = 1
            r1[r18] = r10
            r16 = 0
            r1[r16] = r11
            java.lang.Class<org.apache.commons.math3.analysis.interpolation.TricubicFunction> r15 = org.apache.commons.math3.analysis.interpolation.TricubicFunction.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r15, r1)
            org.apache.commons.math3.analysis.interpolation.TricubicFunction[][][] r1 = (org.apache.commons.math3.analysis.interpolation.TricubicFunction[][][]) r1
            r0.splines = r1
            r1 = 0
        L_0x0087:
            if (r1 >= r11) goto L_0x0455
            r15 = r2[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x044b
            r15 = r3[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0441
            r15 = r4[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0437
            r15 = r5[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x042d
            r15 = r6[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0423
            r15 = r7[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0419
            r15 = r8[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x040f
            r15 = r9[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0402
            int r15 = r1 + 1
            r49 = r11
            double[] r11 = r0.xval
            r18 = r11[r15]
            r20 = r11[r1]
            double r18 = r18 - r20
            r11 = 0
        L_0x00be:
            if (r11 >= r10) goto L_0x03f6
            r20 = r2[r1]
            r21 = r10
            r10 = r20[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03eb
            r10 = r3[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03e0
            r10 = r4[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03d5
            r10 = r5[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03ca
            r10 = r6[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03bf
            r10 = r7[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03b4
            r10 = r8[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03a9
            r10 = r9[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x039e
            int r10 = r11 + 1
            r20 = r13
            double[] r13 = r0.yval
            r22 = r13[r10]
            r24 = r13[r11]
            double r22 = r22 - r24
            double r24 = r18 * r22
            r13 = 0
        L_0x0109:
            if (r13 >= r12) goto L_0x038e
            int r26 = r13 + 1
            r27 = r12
            double[] r12 = r0.zval
            r28 = r12[r26]
            r30 = r12[r13]
            double r28 = r28 - r30
            double r30 = r18 * r28
            double r32 = r22 * r28
            double r34 = r18 * r32
            r12 = 64
            double[] r12 = new double[r12]
            r36 = r2[r1]
            r37 = r36[r11]
            r38 = r37[r13]
            r16 = 0
            r12[r16] = r38
            r38 = r2[r15]
            r39 = r38[r11]
            r40 = r39[r13]
            r42 = 1
            r12[r42] = r40
            r36 = r36[r10]
            r40 = r36[r13]
            r12[r17] = r40
            r38 = r38[r10]
            r40 = r38[r13]
            r43 = 3
            r12[r43] = r40
            r40 = 4
            r44 = r37[r26]
            r12[r40] = r44
            r37 = 5
            r39 = r39[r26]
            r12[r37] = r39
            r37 = 6
            r39 = r36[r26]
            r12[r37] = r39
            r36 = 7
            r37 = r38[r26]
            r12[r36] = r37
            r36 = r3[r1]
            r37 = r36[r11]
            r38 = r37[r13]
            double r38 = r38 * r18
            r40 = 8
            r12[r40] = r38
            r38 = r3[r15]
            r39 = r38[r11]
            r40 = r39[r13]
            double r40 = r40 * r18
            r44 = 9
            r12[r44] = r40
            r36 = r36[r10]
            r40 = r36[r13]
            double r40 = r40 * r18
            r44 = 10
            r12[r44] = r40
            r38 = r38[r10]
            r40 = r38[r13]
            double r40 = r40 * r18
            r44 = 11
            r12[r44] = r40
            r40 = r37[r26]
            double r40 = r40 * r18
            r37 = 12
            r12[r37] = r40
            r39 = r39[r26]
            double r39 = r39 * r18
            r37 = 13
            r12[r37] = r39
            r36 = r36[r26]
            double r36 = r36 * r18
            r39 = 14
            r12[r39] = r36
            r36 = r38[r26]
            double r36 = r36 * r18
            r38 = 15
            r12[r38] = r36
            r36 = r4[r1]
            r37 = r36[r11]
            r38 = r37[r13]
            double r38 = r38 * r22
            r40 = 16
            r12[r40] = r38
            r38 = r4[r15]
            r39 = r38[r11]
            r40 = r39[r13]
            double r40 = r40 * r22
            r44 = 17
            r12[r44] = r40
            r36 = r36[r10]
            r40 = r36[r13]
            double r40 = r40 * r22
            r44 = 18
            r12[r44] = r40
            r38 = r38[r10]
            r40 = r38[r13]
            double r40 = r40 * r22
            r44 = 19
            r12[r44] = r40
            r40 = r37[r26]
            double r40 = r40 * r22
            r37 = 20
            r12[r37] = r40
            r39 = r39[r26]
            double r39 = r39 * r22
            r37 = 21
            r12[r37] = r39
            r36 = r36[r26]
            double r36 = r36 * r22
            r39 = 22
            r12[r39] = r36
            r36 = r38[r26]
            double r36 = r36 * r22
            r38 = 23
            r12[r38] = r36
            r36 = r5[r1]
            r37 = r36[r11]
            r38 = r37[r13]
            double r38 = r38 * r28
            r40 = 24
            r12[r40] = r38
            r38 = r5[r15]
            r39 = r38[r11]
            r40 = r39[r13]
            double r40 = r40 * r28
            r44 = 25
            r12[r44] = r40
            r36 = r36[r10]
            r40 = r36[r13]
            double r40 = r40 * r28
            r44 = 26
            r12[r44] = r40
            r38 = r38[r10]
            r40 = r38[r13]
            double r40 = r40 * r28
            r44 = 27
            r12[r44] = r40
            r40 = r37[r26]
            double r40 = r40 * r28
            r37 = 28
            r12[r37] = r40
            r39 = r39[r26]
            double r39 = r39 * r28
            r37 = 29
            r12[r37] = r39
            r36 = r36[r26]
            double r36 = r36 * r28
            r39 = 30
            r12[r39] = r36
            r36 = r38[r26]
            double r36 = r36 * r28
            r28 = 31
            r12[r28] = r36
            r28 = r6[r1]
            r29 = r28[r11]
            r36 = r29[r13]
            double r36 = r36 * r24
            r38 = 32
            r12[r38] = r36
            r36 = r6[r15]
            r37 = r36[r11]
            r38 = r37[r13]
            double r38 = r38 * r24
            r40 = 33
            r12[r40] = r38
            r28 = r28[r10]
            r38 = r28[r13]
            double r38 = r38 * r24
            r40 = 34
            r12[r40] = r38
            r36 = r36[r10]
            r38 = r36[r13]
            double r38 = r38 * r24
            r40 = 35
            r12[r40] = r38
            r38 = r29[r26]
            double r38 = r38 * r24
            r29 = 36
            r12[r29] = r38
            r37 = r37[r26]
            double r37 = r37 * r24
            r29 = 37
            r12[r29] = r37
            r28 = r28[r26]
            double r28 = r28 * r24
            r37 = 38
            r12[r37] = r28
            r28 = r36[r26]
            double r28 = r28 * r24
            r36 = 39
            r12[r36] = r28
            r28 = r7[r1]
            r29 = r28[r11]
            r36 = r29[r13]
            double r36 = r36 * r30
            r38 = 40
            r12[r38] = r36
            r36 = r7[r15]
            r37 = r36[r11]
            r38 = r37[r13]
            double r38 = r38 * r30
            r40 = 41
            r12[r40] = r38
            r28 = r28[r10]
            r38 = r28[r13]
            double r38 = r38 * r30
            r40 = 42
            r12[r40] = r38
            r36 = r36[r10]
            r38 = r36[r13]
            double r38 = r38 * r30
            r40 = 43
            r12[r40] = r38
            r38 = r29[r26]
            double r38 = r38 * r30
            r29 = 44
            r12[r29] = r38
            r37 = r37[r26]
            double r37 = r37 * r30
            r29 = 45
            r12[r29] = r37
            r28 = r28[r26]
            double r28 = r28 * r30
            r37 = 46
            r12[r37] = r28
            r28 = r36[r26]
            double r28 = r28 * r30
            r30 = 47
            r12[r30] = r28
            r28 = r8[r1]
            r29 = r28[r11]
            r30 = r29[r13]
            double r30 = r30 * r32
            r36 = 48
            r12[r36] = r30
            r30 = r8[r15]
            r31 = r30[r11]
            r36 = r31[r13]
            double r36 = r36 * r32
            r38 = 49
            r12[r38] = r36
            r28 = r28[r10]
            r36 = r28[r13]
            double r36 = r36 * r32
            r38 = 50
            r12[r38] = r36
            r30 = r30[r10]
            r36 = r30[r13]
            double r36 = r36 * r32
            r38 = 51
            r12[r38] = r36
            r36 = r29[r26]
            double r36 = r36 * r32
            r29 = 52
            r12[r29] = r36
            r36 = r31[r26]
            double r36 = r36 * r32
            r29 = 53
            r12[r29] = r36
            r28 = r28[r26]
            double r28 = r28 * r32
            r31 = 54
            r12[r31] = r28
            r28 = r30[r26]
            double r28 = r28 * r32
            r30 = 55
            r12[r30] = r28
            r28 = r9[r1]
            r29 = r28[r11]
            r30 = r29[r13]
            double r30 = r30 * r34
            r32 = 56
            r12[r32] = r30
            r30 = r9[r15]
            r31 = r30[r11]
            r32 = r31[r13]
            double r32 = r32 * r34
            r36 = 57
            r12[r36] = r32
            r28 = r28[r10]
            r32 = r28[r13]
            double r32 = r32 * r34
            r36 = 58
            r12[r36] = r32
            r30 = r30[r10]
            r32 = r30[r13]
            double r32 = r32 * r34
            r36 = 59
            r12[r36] = r32
            r32 = r29[r26]
            double r32 = r32 * r34
            r29 = 60
            r12[r29] = r32
            r31 = r31[r26]
            double r31 = r31 * r34
            r29 = 61
            r12[r29] = r31
            r28 = r28[r26]
            double r28 = r28 * r34
            r31 = 62
            r12[r31] = r28
            r28 = r30[r26]
            double r28 = r28 * r34
            r30 = 63
            r12[r30] = r28
            r47 = r10
            org.apache.commons.math3.analysis.interpolation.TricubicFunction[][][] r10 = r0.splines
            r10 = r10[r1]
            r10 = r10[r11]
            r48 = r15
            org.apache.commons.math3.analysis.interpolation.TricubicFunction r15 = new org.apache.commons.math3.analysis.interpolation.TricubicFunction
            double[] r12 = r0.computeCoefficients(r12)
            r15.<init>(r12)
            r10[r13] = r15
            r10 = r47
            r15 = r48
            r13 = r26
            r12 = r27
            goto L_0x0109
        L_0x038e:
            r47 = r10
            r16 = 0
            r42 = 1
            r43 = 3
            r11 = r47
            r13 = r20
            r10 = r21
            goto L_0x00be
        L_0x039e:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r9[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03a9:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r8[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03b4:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r7[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03bf:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r6[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03ca:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r5[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03d5:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r4[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03e0:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r3[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03eb:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r2[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r0.<init>(r1, r14)
            throw r0
        L_0x03f6:
            r48 = r15
            r16 = 0
            r43 = 3
            r1 = r48
            r11 = r49
            goto L_0x0087
        L_0x0402:
            r20 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r9[r1]
            int r1 = r1.length
            r9 = r20
            r0.<init>(r1, r9)
            throw r0
        L_0x040f:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r8[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x0419:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r7[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x0423:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r6[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x042d:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r5[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x0437:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r4[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x0441:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r3[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x044b:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r2[r1]
            int r1 = r1.length
            r0.<init>(r1, r9)
            throw r0
        L_0x0455:
            return
        L_0x0456:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r9.length
            r0.<init>(r11, r1)
            throw r0
        L_0x045d:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r8.length
            r0.<init>(r11, r1)
            throw r0
        L_0x0464:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r7.length
            r0.<init>(r11, r1)
            throw r0
        L_0x046b:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r6.length
            r0.<init>(r11, r1)
            throw r0
        L_0x0472:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r5.length
            r0.<init>(r11, r1)
            throw r0
        L_0x0479:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r4.length
            r0.<init>(r11, r1)
            throw r0
        L_0x0480:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r3.length
            r0.<init>(r11, r1)
            throw r0
        L_0x0487:
            org.apache.commons.math3.exception.DimensionMismatchException r0 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r2.length
            r0.<init>(r11, r1)
            throw r0
        L_0x048e:
            org.apache.commons.math3.exception.NoDataException r0 = new org.apache.commons.math3.exception.NoDataException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.interpolation.TricubicInterpolatingFunction.<init>(double[], double[], double[], double[][][], double[][][], double[][][], double[][][], double[][][], double[][][], double[][][], double[][][]):void");
    }

    public double value(double d, double d2, double d3) throws OutOfRangeException {
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        int searchIndex = searchIndex(d4, this.xval);
        if (searchIndex != -1) {
            int searchIndex2 = searchIndex(d5, this.yval);
            if (searchIndex2 != -1) {
                int searchIndex3 = searchIndex(d6, this.zval);
                if (searchIndex3 != -1) {
                    double[] dArr = this.xval;
                    double d7 = dArr[searchIndex];
                    double d8 = (d4 - d7) / (dArr[searchIndex + 1] - d7);
                    double[] dArr2 = this.yval;
                    double d9 = dArr2[searchIndex2];
                    double d10 = (d5 - d9) / (dArr2[searchIndex2 + 1] - d9);
                    double[] dArr3 = this.zval;
                    double d11 = dArr3[searchIndex3];
                    return this.splines[searchIndex][searchIndex2][searchIndex3].value(d8, d10, (d6 - d11) / (dArr3[searchIndex3 + 1] - d11));
                }
                Double valueOf = Double.valueOf(d3);
                Double valueOf2 = Double.valueOf(this.zval[0]);
                double[] dArr4 = this.zval;
                throw new OutOfRangeException(valueOf, valueOf2, Double.valueOf(dArr4[dArr4.length - 1]));
            }
            Double valueOf3 = Double.valueOf(d2);
            Double valueOf4 = Double.valueOf(this.yval[0]);
            double[] dArr5 = this.yval;
            throw new OutOfRangeException(valueOf3, valueOf4, Double.valueOf(dArr5[dArr5.length - 1]));
        }
        Double valueOf5 = Double.valueOf(d);
        Double valueOf6 = Double.valueOf(this.xval[0]);
        double[] dArr6 = this.xval;
        throw new OutOfRangeException(valueOf5, valueOf6, Double.valueOf(dArr6[dArr6.length - 1]));
    }

    public boolean isValidPoint(double d, double d2, double d3) {
        double[] dArr = this.xval;
        if (d >= dArr[0] && d <= dArr[dArr.length - 1]) {
            double[] dArr2 = this.yval;
            if (d2 >= dArr2[0] && d2 <= dArr2[dArr2.length - 1]) {
                double[] dArr3 = this.zval;
                return d3 >= dArr3[0] && d3 <= dArr3[dArr3.length - 1];
            }
        }
    }

    private int searchIndex(double d, double[] dArr) {
        if (d < dArr[0]) {
            return -1;
        }
        int length = dArr.length;
        for (int i = 1; i < length; i++) {
            if (d <= dArr[i]) {
                return i - 1;
            }
        }
        return -1;
    }

    private double[] computeCoefficients(double[] dArr) {
        double[] dArr2 = new double[64];
        for (int i = 0; i < 64; i++) {
            double[] dArr3 = AINV[i];
            double d = 0.0d;
            for (int i2 = 0; i2 < 64; i2++) {
                d += dArr3[i2] * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }
}
