package org.apache.commons.io.input;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.io.output.QueueOutputStream;

public class QueueInputStream extends InputStream {
    private final BlockingQueue<Integer> blockingQueue;

    public QueueInputStream() {
        this(new LinkedBlockingQueue());
    }

    public QueueInputStream(BlockingQueue<Integer> blockingQueue2) {
        this.blockingQueue = (BlockingQueue) Objects.requireNonNull(blockingQueue2, "blockingQueue");
    }

    public QueueOutputStream newQueueOutputStream() {
        return new QueueOutputStream(this.blockingQueue);
    }

    public int read() {
        Integer num = (Integer) this.blockingQueue.poll();
        if (num == null) {
            return -1;
        }
        return num.intValue() & 255;
    }
}
