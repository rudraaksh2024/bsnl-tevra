package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.poi.poifs.storage.BATBlock;

public abstract class BlockStore {
    /* access modifiers changed from: protected */
    public abstract ByteBuffer createBlockIfNeeded(int i) throws IOException;

    /* access modifiers changed from: protected */
    public abstract BATBlock.BATBlockAndIndex getBATBlockAndIndex(int i);

    /* access modifiers changed from: protected */
    public abstract ByteBuffer getBlockAt(int i) throws IOException;

    /* access modifiers changed from: protected */
    public abstract int getBlockStoreBlockSize();

    /* access modifiers changed from: protected */
    public abstract ChainLoopDetector getChainLoopDetector() throws IOException;

    /* access modifiers changed from: protected */
    public abstract int getFreeBlock() throws IOException;

    /* access modifiers changed from: protected */
    public abstract int getNextBlock(int i);

    /* access modifiers changed from: protected */
    public abstract void releaseBuffer(ByteBuffer byteBuffer);

    /* access modifiers changed from: protected */
    public abstract void setNextBlock(int i, int i2);

    protected class ChainLoopDetector {
        private final boolean[] used_blocks;

        protected ChainLoopDetector(long j) {
            if (j >= 0) {
                long blockStoreBlockSize = (long) BlockStore.this.getBlockStoreBlockSize();
                int i = (int) (j / blockStoreBlockSize);
                this.used_blocks = new boolean[(j % blockStoreBlockSize != 0 ? i + 1 : i)];
                return;
            }
            throw new IllegalArgumentException("Cannot create a ChainLoopDetector with negative size, but had: " + j);
        }

        /* access modifiers changed from: protected */
        public void claim(int i) {
            boolean[] zArr = this.used_blocks;
            if (i < zArr.length) {
                if (!zArr[i]) {
                    zArr[i] = true;
                    return;
                }
                throw new IllegalStateException("Potential loop detected - Block " + i + " was already claimed but was just requested again");
            }
        }
    }
}
