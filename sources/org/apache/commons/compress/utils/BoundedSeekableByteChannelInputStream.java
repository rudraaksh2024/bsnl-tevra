package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class BoundedSeekableByteChannelInputStream extends BoundedArchiveInputStream {
    private final SeekableByteChannel channel;

    public BoundedSeekableByteChannelInputStream(long j, long j2, SeekableByteChannel seekableByteChannel) {
        super(j, j2);
        this.channel = seekableByteChannel;
    }

    /* access modifiers changed from: protected */
    public int read(long j, ByteBuffer byteBuffer) throws IOException {
        int read;
        synchronized (this.channel) {
            this.channel.position(j);
            read = this.channel.read(byteBuffer);
        }
        byteBuffer.flip();
        return read;
    }
}
