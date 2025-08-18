package org.apache.commons.io.output;

import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.io.input.QueueInputStream;

public class QueueOutputStream extends OutputStream {
    private final BlockingQueue<Integer> blockingQueue;

    public QueueOutputStream() {
        this(new LinkedBlockingQueue());
    }

    public QueueOutputStream(BlockingQueue<Integer> blockingQueue2) {
        this.blockingQueue = (BlockingQueue) Objects.requireNonNull(blockingQueue2, "blockingQueue");
    }

    public QueueInputStream newQueueInputStream() {
        return new QueueInputStream(this.blockingQueue);
    }

    public void write(int i) throws InterruptedIOException {
        try {
            this.blockingQueue.put(Integer.valueOf(i & 255));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            InterruptedIOException interruptedIOException = new InterruptedIOException();
            interruptedIOException.initCause(e);
            throw interruptedIOException;
        }
    }
}
