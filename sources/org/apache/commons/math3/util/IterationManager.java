package org.apache.commons.math3.util;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.IntegerSequence;

public class IterationManager {
    private IntegerSequence.Incrementor iterations;
    private final Collection<IterationListener> listeners;

    public IterationManager(int i) {
        this.iterations = IntegerSequence.Incrementor.create().withMaximalCount(i);
        this.listeners = new CopyOnWriteArrayList();
    }

    @Deprecated
    public IterationManager(int i, final Incrementor.MaxCountExceededCallback maxCountExceededCallback) {
        this(i, (IntegerSequence.Incrementor.MaxCountExceededCallback) new IntegerSequence.Incrementor.MaxCountExceededCallback() {
            public void trigger(int i) throws MaxCountExceededException {
                Incrementor.MaxCountExceededCallback.this.trigger(i);
            }
        });
    }

    public IterationManager(int i, IntegerSequence.Incrementor.MaxCountExceededCallback maxCountExceededCallback) {
        this.iterations = IntegerSequence.Incrementor.create().withMaximalCount(i).withCallback(maxCountExceededCallback);
        this.listeners = new CopyOnWriteArrayList();
    }

    public void addIterationListener(IterationListener iterationListener) {
        this.listeners.add(iterationListener);
    }

    public void fireInitializationEvent(IterationEvent iterationEvent) {
        for (IterationListener initializationPerformed : this.listeners) {
            initializationPerformed.initializationPerformed(iterationEvent);
        }
    }

    public void fireIterationPerformedEvent(IterationEvent iterationEvent) {
        for (IterationListener iterationPerformed : this.listeners) {
            iterationPerformed.iterationPerformed(iterationEvent);
        }
    }

    public void fireIterationStartedEvent(IterationEvent iterationEvent) {
        for (IterationListener iterationStarted : this.listeners) {
            iterationStarted.iterationStarted(iterationEvent);
        }
    }

    public void fireTerminationEvent(IterationEvent iterationEvent) {
        for (IterationListener terminationPerformed : this.listeners) {
            terminationPerformed.terminationPerformed(iterationEvent);
        }
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    public int getMaxIterations() {
        return this.iterations.getMaximalCount();
    }

    public void incrementIterationCount() throws MaxCountExceededException {
        this.iterations.increment();
    }

    public void removeIterationListener(IterationListener iterationListener) {
        this.listeners.remove(iterationListener);
    }

    public void resetIterationCount() {
        this.iterations = this.iterations.withStart(0);
    }
}
