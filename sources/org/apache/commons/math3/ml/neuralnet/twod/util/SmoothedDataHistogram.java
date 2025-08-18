package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;

public class SmoothedDataHistogram implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final double membershipNormalization;
    private final int smoothingBins;

    public SmoothedDataHistogram(int i, DistanceMeasure distanceMeasure) {
        this.smoothingBins = i;
        this.distance = distanceMeasure;
        double d = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            d += (double) (i - i2);
        }
        this.membershipNormalization = 1.0d / d;
    }

    public double[][] computeImage(NeuronSquareMesh2D neuronSquareMesh2D, Iterable<double[]> iterable) {
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        int i = numberOfRows * numberOfColumns;
        if (i >= this.smoothingBins) {
            LocationFinder locationFinder = new LocationFinder(neuronSquareMesh2D);
            int[] iArr = new int[2];
            iArr[1] = numberOfColumns;
            iArr[0] = numberOfRows;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (double[] sort : iterable) {
                Neuron[] sort2 = MapUtils.sort(sort, neuronSquareMesh2D.getNetwork(), this.distance);
                for (int i2 = 0; i2 < this.smoothingBins; i2++) {
                    LocationFinder.Location location = locationFinder.getLocation(sort2[i2]);
                    int row = location.getRow();
                    int column = location.getColumn();
                    double[] dArr2 = dArr[row];
                    dArr2[column] = dArr2[column] + (((double) (this.smoothingBins - i2)) * this.membershipNormalization);
                }
            }
            return dArr;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(this.smoothingBins), true);
    }
}
