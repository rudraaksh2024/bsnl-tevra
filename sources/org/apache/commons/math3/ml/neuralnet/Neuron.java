package org.apache.commons.math3.ml.neuralnet;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Precision;

public class Neuron implements Serializable {
    private static final long serialVersionUID = 20130207;
    private final AtomicReference<double[]> features;
    private final long identifier;
    private final AtomicLong numberOfAttemptedUpdates = new AtomicLong(0);
    private final AtomicLong numberOfSuccessfulUpdates = new AtomicLong(0);
    private final int size;

    Neuron(long j, double[] dArr) {
        this.identifier = j;
        this.size = dArr.length;
        this.features = new AtomicReference<>(dArr.clone());
    }

    public synchronized Neuron copy() {
        Neuron neuron;
        neuron = new Neuron(getIdentifier(), getFeatures());
        neuron.numberOfAttemptedUpdates.set(this.numberOfAttemptedUpdates.get());
        neuron.numberOfSuccessfulUpdates.set(this.numberOfSuccessfulUpdates.get());
        return neuron;
    }

    public long getIdentifier() {
        return this.identifier;
    }

    public int getSize() {
        return this.size;
    }

    public double[] getFeatures() {
        return (double[]) this.features.get().clone();
    }

    public boolean compareAndSetFeatures(double[] dArr, double[] dArr2) {
        if (dArr2.length == this.size) {
            double[] dArr3 = this.features.get();
            if (!containSameValues(dArr3, dArr)) {
                return false;
            }
            this.numberOfAttemptedUpdates.incrementAndGet();
            if (!PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.features, dArr3, dArr2.clone())) {
                return false;
            }
            this.numberOfSuccessfulUpdates.incrementAndGet();
            return true;
        }
        throw new DimensionMismatchException(dArr2.length, this.size);
    }

    public long getNumberOfAttemptedUpdates() {
        return this.numberOfAttemptedUpdates.get();
    }

    public long getNumberOfSuccessfulUpdates() {
        return this.numberOfSuccessfulUpdates.get();
    }

    private boolean containSameValues(double[] dArr, double[] dArr2) {
        if (dArr2.length == this.size) {
            for (int i = 0; i < this.size; i++) {
                if (!Precision.equals(dArr[i], dArr2[i])) {
                    return false;
                }
            }
            return true;
        }
        throw new DimensionMismatchException(dArr2.length, this.size);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        return new SerializationProxy(this.identifier, this.features.get());
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 20130207;
        private final double[] features;
        private final long identifier;

        SerializationProxy(long j, double[] dArr) {
            this.identifier = j;
            this.features = dArr;
        }

        private Object readResolve() {
            return new Neuron(this.identifier, this.features);
        }
    }
}
