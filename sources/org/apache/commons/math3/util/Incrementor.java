package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.IntegerSequence;

@Deprecated
public class Incrementor {
    private int count;
    private final MaxCountExceededCallback maxCountCallback;
    private int maximalCount;

    public interface MaxCountExceededCallback {
        void trigger(int i) throws MaxCountExceededException;
    }

    public Incrementor() {
        this(0);
    }

    public Incrementor(int i) {
        this(i, new MaxCountExceededCallback() {
            public void trigger(int i) throws MaxCountExceededException {
                throw new MaxCountExceededException(Integer.valueOf(i));
            }
        });
    }

    public Incrementor(int i, MaxCountExceededCallback maxCountExceededCallback) throws NullArgumentException {
        this.count = 0;
        if (maxCountExceededCallback != null) {
            this.maximalCount = i;
            this.maxCountCallback = maxCountExceededCallback;
            return;
        }
        throw new NullArgumentException();
    }

    public void setMaximalCount(int i) {
        this.maximalCount = i;
    }

    public int getMaximalCount() {
        return this.maximalCount;
    }

    public int getCount() {
        return this.count;
    }

    public boolean canIncrement() {
        return this.count < this.maximalCount;
    }

    public void incrementCount(int i) throws MaxCountExceededException {
        for (int i2 = 0; i2 < i; i2++) {
            incrementCount();
        }
    }

    public void incrementCount() throws MaxCountExceededException {
        int i = this.count + 1;
        this.count = i;
        int i2 = this.maximalCount;
        if (i > i2) {
            this.maxCountCallback.trigger(i2);
        }
    }

    public void resetCount() {
        this.count = 0;
    }

    public static Incrementor wrap(IntegerSequence.Incrementor incrementor) {
        return new Incrementor(incrementor) {
            private IntegerSequence.Incrementor delegate;
            final /* synthetic */ IntegerSequence.Incrementor val$incrementor;

            {
                this.val$incrementor = r1;
                this.delegate = r1;
                Incrementor.super.setMaximalCount(r1.getMaximalCount());
                Incrementor.super.incrementCount(this.delegate.getCount());
            }

            public void setMaximalCount(int i) {
                Incrementor.super.setMaximalCount(i);
                this.delegate = this.delegate.withMaximalCount(i);
            }

            public void resetCount() {
                Incrementor.super.resetCount();
                this.delegate = this.delegate.withStart(0);
            }

            public void incrementCount() {
                Incrementor.super.incrementCount();
                this.delegate.increment();
            }
        };
    }
}
