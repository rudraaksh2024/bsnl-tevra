package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;

public class ThresholdingOutputStream extends OutputStream {
    private static final IOFunction<ThresholdingOutputStream, OutputStream> NOOP_OS_GETTER = new ThresholdingOutputStream$$ExternalSyntheticLambda0();
    private final IOFunction<ThresholdingOutputStream, OutputStream> outputStreamGetter;
    private final int threshold;
    private final IOConsumer<ThresholdingOutputStream> thresholdConsumer;
    private boolean thresholdExceeded;
    private long written;

    public ThresholdingOutputStream(int i) {
        this(i, IOConsumer.noop(), NOOP_OS_GETTER);
    }

    public ThresholdingOutputStream(int i, IOConsumer<ThresholdingOutputStream> iOConsumer, IOFunction<ThresholdingOutputStream, OutputStream> iOFunction) {
        this.threshold = i;
        this.thresholdConsumer = iOConsumer == null ? IOConsumer.noop() : iOConsumer;
        this.outputStreamGetter = iOFunction == null ? NOOP_OS_GETTER : iOFunction;
    }

    /* access modifiers changed from: protected */
    public void checkThreshold(int i) throws IOException {
        if (!this.thresholdExceeded && this.written + ((long) i) > ((long) this.threshold)) {
            this.thresholdExceeded = true;
            thresholdReached();
        }
    }

    public void close() throws IOException {
        try {
            flush();
        } catch (IOException unused) {
        }
        getStream().close();
    }

    public void flush() throws IOException {
        getStream().flush();
    }

    public long getByteCount() {
        return this.written;
    }

    /* access modifiers changed from: protected */
    public OutputStream getStream() throws IOException {
        return this.outputStreamGetter.apply(this);
    }

    public int getThreshold() {
        return this.threshold;
    }

    public boolean isThresholdExceeded() {
        return this.written > ((long) this.threshold);
    }

    /* access modifiers changed from: protected */
    public void resetByteCount() {
        this.thresholdExceeded = false;
        this.written = 0;
    }

    /* access modifiers changed from: protected */
    public void setByteCount(long j) {
        this.written = j;
    }

    /* access modifiers changed from: protected */
    public void thresholdReached() throws IOException {
        this.thresholdConsumer.accept(this);
    }

    public void write(byte[] bArr) throws IOException {
        checkThreshold(bArr.length);
        getStream().write(bArr);
        this.written += (long) bArr.length;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkThreshold(i2);
        getStream().write(bArr, i, i2);
        this.written += (long) i2;
    }

    public void write(int i) throws IOException {
        checkThreshold(1);
        getStream().write(i);
        this.written++;
    }
}
