package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;

public class QuantizationError implements MapDataVisualization {
    private final DistanceMeasure distance;

    public QuantizationError(DistanceMeasure distanceMeasure) {
        this.distance = distanceMeasure;
    }

    public double[][] computeImage(NeuronSquareMesh2D neuronSquareMesh2D, Iterable<double[]> iterable) {
        NeuronSquareMesh2D neuronSquareMesh2D2 = neuronSquareMesh2D;
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        LocationFinder locationFinder = new LocationFinder(neuronSquareMesh2D2);
        int[] iArr = new int[2];
        int i = 1;
        iArr[1] = numberOfColumns;
        iArr[0] = numberOfRows;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int[] iArr3 = new int[2];
        iArr3[1] = numberOfColumns;
        iArr3[0] = numberOfRows;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        for (double[] next : iterable) {
            Neuron findBest = MapUtils.findBest(next, neuronSquareMesh2D2, this.distance);
            LocationFinder.Location location = locationFinder.getLocation(findBest);
            int row = location.getRow();
            int column = location.getColumn();
            int[] iArr4 = iArr2[row];
            iArr4[column] = iArr4[column] + i;
            double[] dArr2 = dArr[row];
            dArr2[column] = dArr2[column] + this.distance.compute(next, findBest.getFeatures());
            i = 1;
        }
        for (int i2 = 0; i2 < numberOfRows; i2++) {
            for (int i3 = 0; i3 < numberOfColumns; i3++) {
                int i4 = iArr2[i2][i3];
                if (i4 != 0) {
                    double[] dArr3 = dArr[i2];
                    dArr3[i3] = dArr3[i3] / ((double) i4);
                }
            }
        }
        return dArr;
    }
}
