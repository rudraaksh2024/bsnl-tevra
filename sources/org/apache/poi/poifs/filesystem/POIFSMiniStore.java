package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.poi.poifs.filesystem.BlockStore;
import org.apache.poi.poifs.property.RootProperty;
import org.apache.poi.poifs.storage.BATBlock;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.RecordFormatException;

public class POIFSMiniStore extends BlockStore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final POIFSFileSystem _filesystem;
    private final HeaderBlock _header;
    private POIFSStream _mini_stream;
    private final RootProperty _root;
    private final List<BATBlock> _sbat_blocks;

    /* access modifiers changed from: protected */
    public int getBlockStoreBlockSize() {
        return 64;
    }

    POIFSMiniStore(POIFSFileSystem pOIFSFileSystem, RootProperty rootProperty, List<BATBlock> list, HeaderBlock headerBlock) {
        if (rootProperty != null) {
            this._filesystem = pOIFSFileSystem;
            this._sbat_blocks = list;
            this._header = headerBlock;
            this._root = rootProperty;
            this._mini_stream = new POIFSStream(pOIFSFileSystem, rootProperty.getStartBlock());
            return;
        }
        throw new RecordFormatException("Invalid argument to POIFSMiniStore: root is null");
    }

    /* access modifiers changed from: protected */
    public ByteBuffer getBlockAt(int i) throws IOException {
        int i2 = i * 64;
        int bigBlockSize = i2 / this._filesystem.getBigBlockSize();
        int bigBlockSize2 = i2 % this._filesystem.getBigBlockSize();
        Iterator<Integer> blockOffsetIterator = this._mini_stream.getBlockOffsetIterator();
        for (int i3 = 0; i3 < bigBlockSize; i3++) {
            blockOffsetIterator.next();
        }
        ByteBuffer blockAt = this._filesystem.getBlockAt(blockOffsetIterator.next().intValue());
        blockAt.position(blockAt.position() + bigBlockSize2);
        ByteBuffer slice = blockAt.slice();
        slice.limit(64);
        return slice;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer createBlockIfNeeded(int i) throws IOException {
        boolean z = this._mini_stream.getStartBlock() == -2;
        if (!z) {
            try {
                return getBlockAt(i);
            } catch (NoSuchElementException unused) {
            }
        }
        int freeBlock = this._filesystem.getFreeBlock();
        this._filesystem.createBlockIfNeeded(freeBlock);
        if (z) {
            this._filesystem._get_property_table().getRoot().setStartBlock(freeBlock);
            this._mini_stream = new POIFSStream(this._filesystem, freeBlock);
        } else {
            BlockStore.ChainLoopDetector chainLoopDetector = this._filesystem.getChainLoopDetector();
            int startBlock = this._mini_stream.getStartBlock();
            while (true) {
                chainLoopDetector.claim(startBlock);
                int nextBlock = this._filesystem.getNextBlock(startBlock);
                if (nextBlock == -2) {
                    break;
                }
                startBlock = nextBlock;
            }
            this._filesystem.setNextBlock(startBlock, freeBlock);
        }
        this._filesystem.setNextBlock(freeBlock, -2);
        return createBlockIfNeeded(i);
    }

    /* access modifiers changed from: protected */
    public BATBlock.BATBlockAndIndex getBATBlockAndIndex(int i) {
        return BATBlock.getSBATBlockAndIndex(i, this._header, this._sbat_blocks);
    }

    /* access modifiers changed from: protected */
    public int getNextBlock(int i) {
        BATBlock.BATBlockAndIndex bATBlockAndIndex = getBATBlockAndIndex(i);
        return bATBlockAndIndex.getBlock().getValueAt(bATBlockAndIndex.getIndex());
    }

    /* access modifiers changed from: protected */
    public void setNextBlock(int i, int i2) {
        BATBlock.BATBlockAndIndex bATBlockAndIndex = getBATBlockAndIndex(i);
        bATBlockAndIndex.getBlock().setValueAt(bATBlockAndIndex.getIndex(), i2);
    }

    /* access modifiers changed from: protected */
    public int getFreeBlock() throws IOException {
        int bATEntriesPerBlock = this._filesystem.getBigBlockSizeDetails().getBATEntriesPerBlock();
        int i = 0;
        for (BATBlock next : this._sbat_blocks) {
            if (next.hasFreeSectors()) {
                for (int i2 = 0; i2 < bATEntriesPerBlock; i2++) {
                    if (next.getValueAt(i2) == -1) {
                        return i + i2;
                    }
                }
                continue;
            }
            i += bATEntriesPerBlock;
        }
        BATBlock createEmptyBATBlock = BATBlock.createEmptyBATBlock(this._filesystem.getBigBlockSizeDetails(), false);
        int freeBlock = this._filesystem.getFreeBlock();
        createEmptyBATBlock.setOurBlockIndex(freeBlock);
        if (this._header.getSBATCount() == 0) {
            this._header.setSBATStart(freeBlock);
            this._header.setSBATBlockCount(1);
        } else {
            BlockStore.ChainLoopDetector chainLoopDetector = this._filesystem.getChainLoopDetector();
            int sBATStart = this._header.getSBATStart();
            while (true) {
                chainLoopDetector.claim(sBATStart);
                int nextBlock = this._filesystem.getNextBlock(sBATStart);
                if (nextBlock == -2) {
                    break;
                }
                sBATStart = nextBlock;
            }
            this._filesystem.setNextBlock(sBATStart, freeBlock);
            HeaderBlock headerBlock = this._header;
            headerBlock.setSBATBlockCount(headerBlock.getSBATCount() + 1);
        }
        this._filesystem.setNextBlock(freeBlock, -2);
        this._sbat_blocks.add(createEmptyBATBlock);
        return i;
    }

    /* access modifiers changed from: protected */
    public BlockStore.ChainLoopDetector getChainLoopDetector() {
        return new BlockStore.ChainLoopDetector((long) this._root.getSize());
    }

    /* access modifiers changed from: package-private */
    public void syncWithDataSource() throws IOException {
        int i;
        int i2 = 0;
        for (BATBlock next : this._sbat_blocks) {
            next.writeData(this._filesystem.getBlockAt(next.getOurBlockIndex()));
            if (!next.hasFreeSectors()) {
                i = this._filesystem.getBigBlockSizeDetails().getBATEntriesPerBlock();
            } else {
                i = next.getOccupiedSize();
            }
            i2 += i;
        }
        this._filesystem._get_property_table().getRoot().setSize(i2);
    }

    /* access modifiers changed from: protected */
    public void releaseBuffer(ByteBuffer byteBuffer) {
        this._filesystem.releaseBuffer(byteBuffer);
    }
}
