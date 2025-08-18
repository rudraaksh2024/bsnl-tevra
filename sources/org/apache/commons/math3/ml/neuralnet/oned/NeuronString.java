package org.apache.commons.math3.ml.neuralnet.oned;

import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.ml.neuralnet.Network;

public class NeuronString implements Serializable {
    private static final long serialVersionUID = 1;
    private final long[] identifiers;
    private final Network network;
    private final int size;
    private final boolean wrap;

    NeuronString(boolean z, double[][] dArr) {
        int length = dArr.length;
        this.size = length;
        if (length >= 2) {
            this.wrap = z;
            this.network = new Network(0, dArr[0].length);
            this.identifiers = new long[length];
            for (int i = 0; i < this.size; i++) {
                this.identifiers[i] = this.network.createNeuron(dArr[i]);
            }
            createLinks();
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(length), 2, true);
    }

    public NeuronString(int i, boolean z, FeatureInitializer[] featureInitializerArr) {
        if (i >= 2) {
            this.size = i;
            this.wrap = z;
            this.identifiers = new long[i];
            int length = featureInitializerArr.length;
            this.network = new Network(0, length);
            for (int i2 = 0; i2 < i; i2++) {
                double[] dArr = new double[length];
                for (int i3 = 0; i3 < length; i3++) {
                    dArr[i3] = featureInitializerArr[i3].value();
                }
                this.identifiers[i2] = this.network.createNeuron(dArr);
            }
            createLinks();
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public Network getNetwork() {
        return this.network;
    }

    public int getSize() {
        return this.size;
    }

    public double[] getFeatures(int i) {
        if (i >= 0 && i < this.size) {
            return this.network.getNeuron(this.identifiers[i]).getFeatures();
        }
        throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.size - 1));
    }

    private void createLinks() {
        int i;
        int i2 = 0;
        while (true) {
            i = this.size;
            if (i2 >= i - 1) {
                break;
            }
            Network network2 = this.network;
            i2++;
            network2.addLink(network2.getNeuron((long) i2), this.network.getNeuron((long) i2));
        }
        for (int i3 = i - 1; i3 > 0; i3--) {
            Network network3 = this.network;
            network3.addLink(network3.getNeuron((long) i3), this.network.getNeuron((long) (i3 - 1)));
        }
        if (this.wrap) {
            Network network4 = this.network;
            network4.addLink(network4.getNeuron(0), this.network.getNeuron((long) (this.size - 1)));
            Network network5 = this.network;
            network5.addLink(network5.getNeuron((long) (this.size - 1)), this.network.getNeuron(0));
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        double[][] dArr = new double[this.size][];
        for (int i = 0; i < this.size; i++) {
            dArr[i] = getFeatures(i);
        }
        return new SerializationProxy(this.wrap, dArr);
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 20130226;
        private final double[][] featuresList;
        private final boolean wrap;

        SerializationProxy(boolean z, double[][] dArr) {
            this.wrap = z;
            this.featuresList = dArr;
        }

        private Object readResolve() {
            return new NeuronString(this.wrap, this.featuresList);
        }
    }
}
