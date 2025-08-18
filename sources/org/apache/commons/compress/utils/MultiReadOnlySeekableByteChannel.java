package org.apache.commons.compress.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MultiReadOnlySeekableByteChannel implements SeekableByteChannel {
    private final List<SeekableByteChannel> channels;
    private int currentChannelIdx;
    private long globalPosition;

    public MultiReadOnlySeekableByteChannel(List<SeekableByteChannel> list) {
        this.channels = Collections.unmodifiableList(new ArrayList((Collection) Objects.requireNonNull(list, "channels must not be null")));
    }

    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (isOpen()) {
            int i = 0;
            if (!byteBuffer.hasRemaining()) {
                return 0;
            }
            while (byteBuffer.hasRemaining() && this.currentChannelIdx < this.channels.size()) {
                SeekableByteChannel seekableByteChannel = this.channels.get(this.currentChannelIdx);
                int read = seekableByteChannel.read(byteBuffer);
                if (read == -1) {
                    this.currentChannelIdx++;
                } else {
                    if (seekableByteChannel.position() >= seekableByteChannel.size()) {
                        this.currentChannelIdx++;
                    }
                    i += read;
                }
            }
            if (i <= 0) {
                return -1;
            }
            this.globalPosition += (long) i;
            return i;
        }
        throw new ClosedChannelException();
    }

    public void close() throws IOException {
        IOException iOException = null;
        for (SeekableByteChannel close : this.channels) {
            try {
                close.close();
            } catch (IOException e) {
                if (iOException == null) {
                    iOException = e;
                }
            }
        }
        if (iOException != null) {
            throw new IOException("failed to close wrapped channel", iOException);
        }
    }

    public boolean isOpen() {
        for (SeekableByteChannel isOpen : this.channels) {
            if (!isOpen.isOpen()) {
                return false;
            }
        }
        return true;
    }

    public long position() {
        return this.globalPosition;
    }

    public synchronized SeekableByteChannel position(long j, long j2) throws IOException {
        if (isOpen()) {
            for (int i = 0; ((long) i) < j; i++) {
                j2 += this.channels.get(i).size();
            }
        } else {
            throw new ClosedChannelException();
        }
        return position(j2);
    }

    public long size() throws IOException {
        if (isOpen()) {
            long j = 0;
            for (SeekableByteChannel size : this.channels) {
                j += size.size();
            }
            return j;
        }
        throw new ClosedChannelException();
    }

    public SeekableByteChannel truncate(long j) {
        throw new NonWritableChannelException();
    }

    public int write(ByteBuffer byteBuffer) {
        throw new NonWritableChannelException();
    }

    public synchronized SeekableByteChannel position(long j) throws IOException {
        if (j >= 0) {
            try {
                if (isOpen()) {
                    this.globalPosition = j;
                    int i = 0;
                    while (i < this.channels.size()) {
                        SeekableByteChannel seekableByteChannel = this.channels.get(i);
                        long size = seekableByteChannel.size();
                        long j2 = -1;
                        if (j == -1) {
                            j2 = j;
                            j = 0;
                        } else if (j <= size) {
                            this.currentChannelIdx = i;
                        } else {
                            j2 = j - size;
                            j = size;
                        }
                        seekableByteChannel.position(j);
                        i++;
                        j = j2;
                    }
                } else {
                    throw new ClosedChannelException();
                }
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IOException("Negative position: " + j);
        }
        return this;
    }

    public static SeekableByteChannel forSeekableByteChannels(SeekableByteChannel... seekableByteChannelArr) {
        if (((SeekableByteChannel[]) Objects.requireNonNull(seekableByteChannelArr, "channels must not be null")).length == 1) {
            return seekableByteChannelArr[0];
        }
        return new MultiReadOnlySeekableByteChannel(Arrays.asList(seekableByteChannelArr));
    }

    public static SeekableByteChannel forFiles(File... fileArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (File path : (File[]) Objects.requireNonNull(fileArr, "files must not be null")) {
            arrayList.add(Files.newByteChannel(path.toPath(), new OpenOption[]{StandardOpenOption.READ}));
        }
        if (arrayList.size() == 1) {
            return (SeekableByteChannel) arrayList.get(0);
        }
        return new MultiReadOnlySeekableByteChannel(arrayList);
    }
}
