package org.apache.commons.io.file;

import java.math.BigInteger;
import java.util.Objects;

public class Counters {

    public interface Counter {
        void add(long j);

        long get();

        BigInteger getBigInteger();

        Long getLong();

        void increment();

        void reset() {
        }
    }

    public interface PathCounters {
        Counter getByteCounter();

        Counter getDirectoryCounter();

        Counter getFileCounter();

        void reset() {
        }
    }

    private static class AbstractPathCounters implements PathCounters {
        private final Counter byteCounter;
        private final Counter directoryCounter;
        private final Counter fileCounter;

        protected AbstractPathCounters(Counter counter, Counter counter2, Counter counter3) {
            this.byteCounter = counter;
            this.directoryCounter = counter2;
            this.fileCounter = counter3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AbstractPathCounters)) {
                return false;
            }
            AbstractPathCounters abstractPathCounters = (AbstractPathCounters) obj;
            if (!Objects.equals(this.byteCounter, abstractPathCounters.byteCounter) || !Objects.equals(this.directoryCounter, abstractPathCounters.directoryCounter) || !Objects.equals(this.fileCounter, abstractPathCounters.fileCounter)) {
                return false;
            }
            return true;
        }

        public Counter getByteCounter() {
            return this.byteCounter;
        }

        public Counter getDirectoryCounter() {
            return this.directoryCounter;
        }

        public Counter getFileCounter() {
            return this.fileCounter;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.byteCounter, this.directoryCounter, this.fileCounter});
        }

        public void reset() {
            this.byteCounter.reset();
            this.directoryCounter.reset();
            this.fileCounter.reset();
        }

        public String toString() {
            return String.format("%,d files, %,d directories, %,d bytes", new Object[]{Long.valueOf(this.fileCounter.get()), Long.valueOf(this.directoryCounter.get()), Long.valueOf(this.byteCounter.get())});
        }
    }

    private static final class BigIntegerCounter implements Counter {
        private BigInteger value;

        private BigIntegerCounter() {
            this.value = BigInteger.ZERO;
        }

        public void add(long j) {
            this.value = this.value.add(BigInteger.valueOf(j));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Counter)) {
                return false;
            }
            return Objects.equals(this.value, ((Counter) obj).getBigInteger());
        }

        public long get() {
            return this.value.longValueExact();
        }

        public BigInteger getBigInteger() {
            return this.value;
        }

        public Long getLong() {
            return Long.valueOf(this.value.longValueExact());
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.value});
        }

        public void increment() {
            this.value = this.value.add(BigInteger.ONE);
        }

        public String toString() {
            return this.value.toString();
        }

        public void reset() {
            this.value = BigInteger.ZERO;
        }
    }

    private static final class BigIntegerPathCounters extends AbstractPathCounters {
        protected BigIntegerPathCounters() {
            super(Counters.bigIntegerCounter(), Counters.bigIntegerCounter(), Counters.bigIntegerCounter());
        }
    }

    private static final class LongCounter implements Counter {
        private long value;

        private LongCounter() {
        }

        public void add(long j) {
            this.value += j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Counter)) {
                return false;
            }
            if (this.value == ((Counter) obj).get()) {
                return true;
            }
            return false;
        }

        public long get() {
            return this.value;
        }

        public BigInteger getBigInteger() {
            return BigInteger.valueOf(this.value);
        }

        public Long getLong() {
            return Long.valueOf(this.value);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.value)});
        }

        public void increment() {
            this.value++;
        }

        public String toString() {
            return Long.toString(this.value);
        }

        public void reset() {
            this.value = 0;
        }
    }

    private static final class LongPathCounters extends AbstractPathCounters {
        protected LongPathCounters() {
            super(Counters.longCounter(), Counters.longCounter(), Counters.longCounter());
        }
    }

    private static final class NoopCounter implements Counter {
        static final NoopCounter INSTANCE = new NoopCounter();

        public void add(long j) {
        }

        public long get() {
            return 0;
        }

        public void increment() {
        }

        private NoopCounter() {
        }

        public BigInteger getBigInteger() {
            return BigInteger.ZERO;
        }

        public Long getLong() {
            return 0L;
        }
    }

    private static final class NoopPathCounters extends AbstractPathCounters {
        static final NoopPathCounters INSTANCE = new NoopPathCounters();

        private NoopPathCounters() {
            super(Counters.noopCounter(), Counters.noopCounter(), Counters.noopCounter());
        }
    }

    public static Counter bigIntegerCounter() {
        return new BigIntegerCounter();
    }

    public static PathCounters bigIntegerPathCounters() {
        return new BigIntegerPathCounters();
    }

    public static Counter longCounter() {
        return new LongCounter();
    }

    public static PathCounters longPathCounters() {
        return new LongPathCounters();
    }

    public static Counter noopCounter() {
        return NoopCounter.INSTANCE;
    }

    public static PathCounters noopPathCounters() {
        return NoopPathCounters.INSTANCE;
    }
}
