package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import java.util.Collection;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;

public class UnifiedDistanceMatrix implements MapVisualization {
    private final DistanceMeasure distance;
    private final boolean individualDistances;

    public UnifiedDistanceMatrix(boolean z, DistanceMeasure distanceMeasure) {
        this.individualDistances = z;
        this.distance = distanceMeasure;
    }

    public double[][] computeImage(NeuronSquareMesh2D neuronSquareMesh2D) {
        if (this.individualDistances) {
            return individualDistances(neuronSquareMesh2D);
        }
        return averageDistances(neuronSquareMesh2D);
    }

    private double[][] individualDistances(NeuronSquareMesh2D neuronSquareMesh2D) {
        double d;
        NeuronSquareMesh2D neuronSquareMesh2D2 = neuronSquareMesh2D;
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        int[] iArr = new int[2];
        iArr[1] = (numberOfColumns * 2) + 1;
        iArr[0] = (numberOfRows * 2) + 1;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < numberOfRows; i++) {
            int i2 = (i * 2) + 1;
            for (int i3 = 0; i3 < numberOfColumns; i3++) {
                int i4 = (i3 * 2) + 1;
                double[] features = neuronSquareMesh2D2.getNeuron(i, i3).getFeatures();
                Neuron neuron = neuronSquareMesh2D2.getNeuron(i, i3, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                if (neuron != null) {
                    dArr[i2][i4 + 1] = this.distance.compute(features, neuron.getFeatures());
                }
                Neuron neuron2 = neuronSquareMesh2D2.getNeuron(i, i3, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                if (neuron2 != null) {
                    dArr[i2 + 1][i4] = this.distance.compute(features, neuron2.getFeatures());
                }
            }
        }
        for (int i5 = 0; i5 < numberOfRows; i5++) {
            int i6 = (i5 * 2) + 1;
            for (int i7 = 0; i7 < numberOfColumns; i7++) {
                int i8 = (i7 * 2) + 1;
                Neuron neuron3 = neuronSquareMesh2D2.getNeuron(i5, i7);
                Neuron neuron4 = neuronSquareMesh2D2.getNeuron(i5, i7, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                Neuron neuron5 = neuronSquareMesh2D2.getNeuron(i5, i7, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                Neuron neuron6 = neuronSquareMesh2D2.getNeuron(i5, i7, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.DOWN);
                if (neuron6 == null) {
                    d = 0.0d;
                } else {
                    d = this.distance.compute(neuron3.getFeatures(), neuron6.getFeatures());
                }
                dArr[i6 + 1][i8 + 1] = (d + ((neuron4 == null || neuron5 == null) ? 0.0d : this.distance.compute(neuron4.getFeatures(), neuron5.getFeatures()))) * 0.5d;
            }
        }
        int length = dArr.length - 1;
        double[] dArr2 = dArr[length];
        dArr[0] = dArr2;
        int length2 = dArr2.length - 1;
        for (int i9 = 0; i9 < length; i9++) {
            double[] dArr3 = dArr[i9];
            dArr3[0] = dArr3[length2];
        }
        return dArr;
    }

    private double[][] averageDistances(NeuronSquareMesh2D neuronSquareMesh2D) {
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        int[] iArr = new int[2];
        iArr[1] = numberOfColumns;
        iArr[0] = numberOfRows;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        Network network = neuronSquareMesh2D.getNetwork();
        for (int i = 0; i < numberOfRows; i++) {
            for (int i2 = 0; i2 < numberOfColumns; i2++) {
                Neuron neuron = neuronSquareMesh2D.getNeuron(i, i2);
                Collection<Neuron> neighbours = network.getNeighbours(neuron);
                double[] features = neuron.getFeatures();
                double d = 0.0d;
                int i3 = 0;
                for (Neuron features2 : neighbours) {
                    i3++;
                    d += this.distance.compute(features, features2.getFeatures());
                }
                dArr[i][i2] = d / ((double) i3);
            }
        }
        return dArr;
    }
}
