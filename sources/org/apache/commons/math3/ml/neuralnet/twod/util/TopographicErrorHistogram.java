package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;
import org.apache.commons.math3.util.Pair;

public class TopographicErrorHistogram implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean relativeCount;

    public TopographicErrorHistogram(boolean z, DistanceMeasure distanceMeasure) {
        this.relativeCount = z;
        this.distance = distanceMeasure;
    }

    public double[][] computeImage(NeuronSquareMesh2D neuronSquareMesh2D, Iterable<double[]> iterable) {
        NeuronSquareMesh2D neuronSquareMesh2D2 = neuronSquareMesh2D;
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        Network network = neuronSquareMesh2D.getNetwork();
        LocationFinder locationFinder = new LocationFinder(neuronSquareMesh2D2);
        int[] iArr = new int[2];
        iArr[1] = numberOfColumns;
        iArr[0] = numberOfRows;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int[] iArr3 = new int[2];
        iArr3[1] = numberOfColumns;
        iArr3[0] = numberOfRows;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        for (double[] findBestAndSecondBest : iterable) {
            Pair<Neuron, Neuron> findBestAndSecondBest2 = MapUtils.findBestAndSecondBest(findBestAndSecondBest, neuronSquareMesh2D2, this.distance);
            Neuron first = findBestAndSecondBest2.getFirst();
            LocationFinder.Location location = locationFinder.getLocation(first);
            int row = location.getRow();
            int column = location.getColumn();
            int[] iArr4 = iArr2[row];
            iArr4[column] = iArr4[column] + 1;
            if (!network.getNeighbours(first).contains(findBestAndSecondBest2.getSecond())) {
                double[] dArr2 = dArr[row];
                dArr2[column] = dArr2[column] + 1.0d;
            }
        }
        if (this.relativeCount) {
            for (int i = 0; i < numberOfRows; i++) {
                for (int i2 = 0; i2 < numberOfColumns; i2++) {
                    double[] dArr3 = dArr[i];
                    dArr3[i2] = dArr3[i2] / ((double) iArr2[i][i2]);
                }
            }
        }
        return dArr;
    }
}
