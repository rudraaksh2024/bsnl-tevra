package org.apache.poi.poifs.nio;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.IdentityHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.IOUtils;

public class FileBackedDataSource extends DataSource implements Closeable {
    private static final Logger LOG = LogManager.getLogger((Class<?>) FileBackedDataSource.class);
    private final IdentityHashMap<ByteBuffer, ByteBuffer> buffersToClean;
    private final FileChannel channel;
    private Long channelSize;
    private final boolean closeChannelOnClose;
    private final RandomAccessFile srcFile;
    private final boolean writable;

    public FileBackedDataSource(File file) throws FileNotFoundException {
        this(newSrcFile(file, "r"), true);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileBackedDataSource(File file, boolean z) throws FileNotFoundException {
        this(newSrcFile(file, z ? "r" : "rw"), z);
    }

    public FileBackedDataSource(RandomAccessFile randomAccessFile, boolean z) {
        this(randomAccessFile, randomAccessFile.getChannel(), z, false);
    }

    public FileBackedDataSource(FileChannel fileChannel, boolean z) {
        this(fileChannel, z, true);
    }

    public FileBackedDataSource(FileChannel fileChannel, boolean z, boolean z2) {
        this((RandomAccessFile) null, fileChannel, z, z2);
    }

    private FileBackedDataSource(RandomAccessFile randomAccessFile, FileChannel fileChannel, boolean z, boolean z2) {
        this.buffersToClean = new IdentityHashMap<>();
        this.srcFile = randomAccessFile;
        this.channel = fileChannel;
        this.writable = !z;
        this.closeChannelOnClose = z2;
    }

    public boolean isWriteable() {
        return this.writable;
    }

    public FileChannel getChannel() {
        return this.channel;
    }

    public ByteBuffer read(int i, long j) throws IOException {
        ByteBuffer byteBuffer;
        if (j < size()) {
            if (this.writable) {
                byteBuffer = this.channel.map(FileChannel.MapMode.READ_WRITE, j, (long) i);
                this.buffersToClean.put(byteBuffer, byteBuffer);
            } else {
                this.channel.position(j);
                byteBuffer = ByteBuffer.allocate(i);
                if (IOUtils.readFully((ReadableByteChannel) this.channel, byteBuffer) == -1) {
                    throw new IndexOutOfBoundsException("Position " + j + " past the end of the file");
                }
            }
            byteBuffer.position(0);
            return byteBuffer;
        }
        throw new IndexOutOfBoundsException("Position " + j + " past the end of the file");
    }

    public void write(ByteBuffer byteBuffer, long j) throws IOException {
        this.channel.write(byteBuffer, j);
        Long l = this.channelSize;
        if (l != null && j >= l.longValue()) {
            this.channelSize = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r6.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        if (r7 != null) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyTo(java.io.OutputStream r7) throws java.io.IOException {
        /*
            r6 = this;
            java.nio.channels.WritableByteChannel r7 = java.nio.channels.Channels.newChannel(r7)
            java.nio.channels.FileChannel r0 = r6.channel     // Catch:{ all -> 0x0016 }
            r1 = 0
            long r3 = r0.size()     // Catch:{ all -> 0x0016 }
            r5 = r7
            r0.transferTo(r1, r3, r5)     // Catch:{ all -> 0x0016 }
            if (r7 == 0) goto L_0x0015
            r7.close()
        L_0x0015:
            return
        L_0x0016:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r0 = move-exception
            if (r7 == 0) goto L_0x0023
            r7.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r7 = move-exception
            r6.addSuppressed(r7)
        L_0x0023:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.nio.FileBackedDataSource.copyTo(java.io.OutputStream):void");
    }

    public long size() throws IOException {
        if (this.channelSize == null) {
            this.channelSize = Long.valueOf(this.channel.size());
        }
        return this.channelSize.longValue();
    }

    public void releaseBuffer(ByteBuffer byteBuffer) {
        ByteBuffer remove = this.buffersToClean.remove(byteBuffer);
        if (remove != null) {
            unmap(remove);
        }
    }

    public void close() throws IOException {
        this.buffersToClean.forEach(new FileBackedDataSource$$ExternalSyntheticLambda0());
        this.buffersToClean.clear();
        RandomAccessFile randomAccessFile = this.srcFile;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        } else if (this.closeChannelOnClose) {
            this.channel.close();
        }
    }

    private static RandomAccessFile newSrcFile(File file, String str) throws FileNotFoundException {
        if (file.exists()) {
            return new RandomAccessFile(file, str);
        }
        throw new FileNotFoundException(file.toString());
    }

    /* access modifiers changed from: private */
    public static void unmap(ByteBuffer byteBuffer) {
        if (!byteBuffer.getClass().getName().endsWith("HeapByteBuffer")) {
            if (CleanerUtil.UNMAP_SUPPORTED) {
                try {
                    CleanerUtil.getCleaner().freeBuffer(byteBuffer);
                } catch (IOException e) {
                    LOG.atWarn().withThrowable(e).log("Failed to unmap the buffer");
                }
            } else {
                LOG.atDebug().log(CleanerUtil.UNMAP_NOT_SUPPORTED_REASON);
            }
        }
    }
}
