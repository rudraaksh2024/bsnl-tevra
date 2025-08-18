package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.UpdateAction;

public class KohonenUpdateAction implements UpdateAction {
    private final DistanceMeasure distance;
    private final LearningFactorFunction learningFactor;
    private final NeighbourhoodSizeFunction neighbourhoodSize;
    private final AtomicLong numberOfCalls = new AtomicLong(0);

    public KohonenUpdateAction(DistanceMeasure distanceMeasure, LearningFactorFunction learningFactorFunction, NeighbourhoodSizeFunction neighbourhoodSizeFunction) {
        this.distance = distanceMeasure;
        this.learningFactor = learningFactorFunction;
        this.neighbourhoodSize = neighbourhoodSizeFunction;
    }

    public void update(Network network, double[] dArr) {
        long incrementAndGet = this.numberOfCalls.incrementAndGet() - 1;
        double value = this.learningFactor.value(incrementAndGet);
        Neuron findAndUpdateBestNeuron = findAndUpdateBestNeuron(network, dArr, value);
        int value2 = this.neighbourhoodSize.value(incrementAndGet);
        Gaussian gaussian = new Gaussian(value, 0.0d, (double) value2);
        if (value2 > 0) {
            Collection<Neuron> hashSet = new HashSet<>();
            hashSet.add(findAndUpdateBestNeuron);
            HashSet hashSet2 = new HashSet();
            hashSet2.add(findAndUpdateBestNeuron);
            int i = 1;
            do {
                hashSet = network.getNeighbours((Iterable<Neuron>) hashSet, (Iterable<Neuron>) hashSet2);
                for (Neuron updateNeighbouringNeuron : hashSet) {
                    updateNeighbouringNeuron(updateNeighbouringNeuron, dArr, gaussian.value((double) i));
                }
                hashSet2.addAll(hashSet);
                i++;
            } while (i <= value2);
        }
    }

    public long getNumberOfCalls() {
        return this.numberOfCalls.get();
    }

    private boolean attemptNeuronUpdate(Neuron neuron, double[] dArr, double d) {
        double[] features = neuron.getFeatures();
        return neuron.compareAndSetFeatures(features, computeFeatures(features, dArr, d));
    }

    private void updateNeighbouringNeuron(Neuron neuron, double[] dArr, double d) {
        do {
        } while (!attemptNeuronUpdate(neuron, dArr, d));
    }

    private Neuron findAndUpdateBestNeuron(Network network, double[] dArr, double d) {
        Neuron findBest;
        do {
            findBest = MapUtils.findBest(dArr, network, this.distance);
        } while (!attemptNeuronUpdate(findBest, dArr, d));
        return findBest;
    }

    private double[] computeFeatures(double[] dArr, double[] dArr2, double d) {
        ArrayRealVector arrayRealVector = new ArrayRealVector(dArr, false);
        return new ArrayRealVector(dArr2, false).subtract((RealVector) arrayRealVector).mapMultiplyToSelf(d).add(arrayRealVector).toArray();
    }
}
