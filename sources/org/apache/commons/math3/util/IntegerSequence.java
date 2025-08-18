package org.apache.commons.math3.util;

import java.util.Iterator;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;

public class IntegerSequence {
    private IntegerSequence() {
    }

    public static Range range(int i, int i2) {
        return range(i, i2, 1);
    }

    public static Range range(int i, int i2, int i3) {
        return new Range(i, i2, i3);
    }

    public static class Range implements Iterable<Integer> {
        private final int max;
        private final int size;
        private final int start;
        private final int step;

        public Range(int i, int i2, int i3) {
            this.start = i;
            this.max = i2;
            this.step = i3;
            int i4 = ((i2 - i) / i3) + 1;
            this.size = i4 < 0 ? 0 : i4;
        }

        public int size() {
            return this.size;
        }

        public Iterator<Integer> iterator() {
            return Incrementor.create().withStart(this.start).withMaximalCount(this.max + (this.step > 0 ? 1 : -1)).withIncrement(this.step);
        }
    }

    public static class Incrementor implements Iterator<Integer> {
        private static final MaxCountExceededCallback CALLBACK = new MaxCountExceededCallback() {
            public void trigger(int i) throws MaxCountExceededException {
                throw new MaxCountExceededException(Integer.valueOf(i));
            }
        };
        private int count = 0;
        private final int increment;
        private final int init;
        private final MaxCountExceededCallback maxCountCallback;
        private final int maximalCount;

        public interface MaxCountExceededCallback {
            void trigger(int i) throws MaxCountExceededException;
        }

        private Incrementor(int i, int i2, int i3, MaxCountExceededCallback maxCountExceededCallback) throws NullArgumentException {
            if (maxCountExceededCallback != null) {
                this.init = i;
                this.maximalCount = i2;
                this.increment = i3;
                this.maxCountCallback = maxCountExceededCallback;
                this.count = i;
                return;
            }
            throw new NullArgumentException();
        }

        public static Incrementor create() {
            return new Incrementor(0, 0, 1, CALLBACK);
        }

        public Incrementor withStart(int i) {
            return new Incrementor(i, this.maximalCount, this.increment, this.maxCountCallback);
        }

        public Incrementor withMaximalCount(int i) {
            return new Incrementor(this.init, i, this.increment, this.maxCountCallback);
        }

        public Incrementor withIncrement(int i) {
            if (i != 0) {
                return new Incrementor(this.init, this.maximalCount, i, this.maxCountCallback);
            }
            throw new ZeroException();
        }

        public Incrementor withCallback(MaxCountExceededCallback maxCountExceededCallback) {
            return new Incrementor(this.init, this.maximalCount, this.increment, maxCountExceededCallback);
        }

        public int getMaximalCount() {
            return this.maximalCount;
        }

        public int getCount() {
            return this.count;
        }

        public boolean canIncrement() {
            return canIncrement(1);
        }

        public boolean canIncrement(int i) {
            int i2 = this.count;
            int i3 = this.increment;
            int i4 = i2 + (i * i3);
            int i5 = this.maximalCount;
            if (i3 < 0) {
                if (i4 > i5) {
                    return true;
                }
            } else if (i4 < i5) {
                return true;
            }
            return false;
        }

        public void increment(int i) throws MaxCountExceededException {
            if (i > 0) {
                if (!canIncrement(0)) {
                    this.maxCountCallback.trigger(this.maximalCount);
                }
                this.count += i * this.increment;
                return;
            }
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }

        public void increment() throws MaxCountExceededException {
            increment(1);
        }

        public boolean hasNext() {
            return canIncrement(0);
        }

        public Integer next() {
            int i = this.count;
            increment();
            return Integer.valueOf(i);
        }

        public void remove() {
            throw new MathUnsupportedOperationException();
        }
    }
}
