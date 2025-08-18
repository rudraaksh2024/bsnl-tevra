package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.poi.poifs.filesystem.BlockStore;

public class POIFSStream implements Iterable<ByteBuffer> {
    /* access modifiers changed from: private */
    public final BlockStore blockStore;
    private OutputStream outStream;
    /* access modifiers changed from: private */
    public int startBlock;

    public POIFSStream(BlockStore blockStore2, int i) {
        this.blockStore = blockStore2;
        this.startBlock = i;
    }

    public POIFSStream(BlockStore blockStore2) {
        this.blockStore = blockStore2;
        this.startBlock = -2;
    }

    public int getStartBlock() {
        return this.startBlock;
    }

    public Iterator<ByteBuffer> iterator() {
        return getBlockIterator();
    }

    /* access modifiers changed from: package-private */
    public Iterator<ByteBuffer> getBlockIterator() {
        if (this.startBlock != -2) {
            return new StreamBlockByteBufferIterator(this.startBlock);
        }
        throw new IllegalStateException("Can't read from a new stream before it has been written to");
    }

    /* access modifiers changed from: package-private */
    public Iterator<Integer> getBlockOffsetIterator() {
        if (this.startBlock != -2) {
            return new StreamBlockOffsetIterator(this.startBlock);
        }
        throw new IllegalStateException("Can't read from a new stream before it has been written to");
    }

    /* access modifiers changed from: package-private */
    public void updateContents(byte[] bArr) throws IOException {
        OutputStream outputStream = getOutputStream();
        outputStream.write(bArr);
        outputStream.close();
    }

    public OutputStream getOutputStream() throws IOException {
        if (this.outStream == null) {
            this.outStream = new StreamBlockByteBuffer();
        }
        return this.outStream;
    }

    public void free() throws IOException {
        free(this.blockStore.getChainLoopDetector());
    }

    /* access modifiers changed from: private */
    public void free(BlockStore.ChainLoopDetector chainLoopDetector) {
        int i = this.startBlock;
        while (i != -2) {
            chainLoopDetector.claim(i);
            int nextBlock = this.blockStore.getNextBlock(i);
            this.blockStore.setNextBlock(i, -1);
            i = nextBlock;
        }
        this.startBlock = -2;
    }

    private class StreamBlockOffsetIterator implements Iterator<Integer> {
        private final BlockStore.ChainLoopDetector loopDetector;
        private int nextBlock;

        StreamBlockOffsetIterator(int i) {
            this.nextBlock = i;
            try {
                this.loopDetector = POIFSStream.this.blockStore.getChainLoopDetector();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean hasNext() {
            return this.nextBlock != -2;
        }

        public Integer next() {
            if (hasNext()) {
                this.loopDetector.claim(this.nextBlock);
                int i = this.nextBlock;
                this.nextBlock = POIFSStream.this.blockStore.getNextBlock(this.nextBlock);
                return Integer.valueOf(i);
            }
            throw new NoSuchElementException("Can't read past the end of the stream");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class StreamBlockByteBufferIterator implements Iterator<ByteBuffer> {
        private final BlockStore.ChainLoopDetector loopDetector;
        private int nextBlock;

        StreamBlockByteBufferIterator(int i) {
            this.nextBlock = i;
            try {
                this.loopDetector = POIFSStream.this.blockStore.getChainLoopDetector();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean hasNext() {
            return this.nextBlock != -2;
        }

        public ByteBuffer next() {
            if (hasNext()) {
                try {
                    this.loopDetector.claim(this.nextBlock);
                    ByteBuffer blockAt = POIFSStream.this.blockStore.getBlockAt(this.nextBlock);
                    this.nextBlock = POIFSStream.this.blockStore.getNextBlock(this.nextBlock);
                    return blockAt;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new NoSuchElementException("Can't read past the end of the stream");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected class StreamBlockByteBuffer extends OutputStream {
        ByteBuffer buffer;
        BlockStore.ChainLoopDetector loopDetector;
        int nextBlock;
        byte[] oneByte = new byte[1];
        int prevBlock;

        StreamBlockByteBuffer() throws IOException {
            this.loopDetector = POIFSStream.this.blockStore.getChainLoopDetector();
            this.prevBlock = -2;
            this.nextBlock = POIFSStream.this.startBlock;
        }

        /* access modifiers changed from: package-private */
        public void createBlockIfNeeded() throws IOException {
            ByteBuffer byteBuffer = this.buffer;
            if (byteBuffer == null || !byteBuffer.hasRemaining()) {
                int i = this.nextBlock;
                if (i == -2) {
                    i = POIFSStream.this.blockStore.getFreeBlock();
                    this.loopDetector.claim(i);
                    this.nextBlock = -2;
                    if (this.prevBlock != -2) {
                        POIFSStream.this.blockStore.setNextBlock(this.prevBlock, i);
                    }
                    POIFSStream.this.blockStore.setNextBlock(i, -2);
                    if (POIFSStream.this.startBlock == -2) {
                        int unused = POIFSStream.this.startBlock = i;
                    }
                } else {
                    this.loopDetector.claim(i);
                    this.nextBlock = POIFSStream.this.blockStore.getNextBlock(i);
                }
                if (this.buffer != null) {
                    POIFSStream.this.blockStore.releaseBuffer(this.buffer);
                }
                this.buffer = POIFSStream.this.blockStore.createBlockIfNeeded(i);
                this.prevBlock = i;
            }
        }

        public void write(int i) throws IOException {
            byte[] bArr = this.oneByte;
            bArr[0] = (byte) (i & 255);
            write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                do {
                    createBlockIfNeeded();
                    int min = Math.min(this.buffer.remaining(), i2);
                    this.buffer.put(bArr, i, min);
                    i += min;
                    i2 -= min;
                } while (i2 > 0);
            }
        }

        public void close() throws IOException {
            new POIFSStream(POIFSStream.this.blockStore, this.nextBlock).free(this.loopDetector);
            if (this.prevBlock != -2) {
                POIFSStream.this.blockStore.setNextBlock(this.prevBlock, -2);
            }
        }
    }
}
