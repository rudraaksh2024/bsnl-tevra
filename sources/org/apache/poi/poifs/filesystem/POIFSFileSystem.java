package org.apache.poi.poifs.filesystem;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.filesystem.BlockStore;
import org.apache.poi.poifs.nio.ByteArrayBackedDataSource;
import org.apache.poi.poifs.nio.DataSource;
import org.apache.poi.poifs.nio.FileBackedDataSource;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.PropertyTable;
import org.apache.poi.poifs.storage.BATBlock;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;

public class POIFSFileSystem extends BlockStore implements POIFSViewable, Closeable {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) POIFSFileSystem.class);
    private static final int MAX_ALLOCATION_SIZE = 250000000;
    private static final int MAX_BLOCK_COUNT = 65535;
    private static int MAX_RECORD_LENGTH = 100000;
    private final List<BATBlock> _bat_blocks;
    protected DataSource _data;
    private HeaderBlock _header;
    private POIFSMiniStore _mini_store;
    private PropertyTable _property_table;
    private DirectoryNode _root;
    private final List<BATBlock> _xbat_blocks;
    private POIFSBigBlockSize bigBlockSize;

    public String getShortDescription() {
        return "POIFS FileSystem";
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    private POIFSFileSystem(boolean z) {
        this.bigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
        this._header = new HeaderBlock(this.bigBlockSize);
        this._property_table = new PropertyTable(this._header);
        this._mini_store = new POIFSMiniStore(this, this._property_table.getRoot(), new ArrayList(), this._header);
        this._xbat_blocks = new ArrayList();
        this._bat_blocks = new ArrayList();
        this._root = null;
        if (z) {
            createNewDataSource();
        }
    }

    /* access modifiers changed from: protected */
    public void createNewDataSource() {
        this._data = new ByteArrayBackedDataSource(IOUtils.safelyAllocate(Math.multiplyExact((long) this.bigBlockSize.getBigBlockSize(), 3), MAX_RECORD_LENGTH));
    }

    public POIFSFileSystem() {
        this(true);
        this._header.setBATCount(1);
        this._header.setBATArray(new int[]{1});
        BATBlock createEmptyBATBlock = BATBlock.createEmptyBATBlock(this.bigBlockSize, false);
        createEmptyBATBlock.setOurBlockIndex(1);
        this._bat_blocks.add(createEmptyBATBlock);
        setNextBlock(0, -2);
        setNextBlock(1, -3);
        this._property_table.setStartBlock(0);
    }

    public POIFSFileSystem(File file) throws IOException {
        this(file, true);
    }

    public POIFSFileSystem(File file, boolean z) throws IOException {
        this((FileChannel) null, file, z, true, true);
    }

    public POIFSFileSystem(FileChannel fileChannel) throws IOException {
        this(fileChannel, true);
    }

    public POIFSFileSystem(FileChannel fileChannel, boolean z) throws IOException {
        this(fileChannel, (File) null, z, false, true);
    }

    public POIFSFileSystem(FileChannel fileChannel, boolean z, boolean z2) throws IOException {
        this(fileChannel, (File) null, z, z2, z2);
    }

    private POIFSFileSystem(FileChannel fileChannel, File file, boolean z, boolean z2, boolean z3) throws IOException {
        this(false);
        if (file != null) {
            try {
                if (file.length() != 0) {
                    FileBackedDataSource fileBackedDataSource = new FileBackedDataSource(file, z);
                    fileChannel = fileBackedDataSource.getChannel();
                    this._data = fileBackedDataSource;
                } else {
                    throw new EmptyFileException(file);
                }
            } catch (IOException | RuntimeException e) {
                if (z2 && fileChannel != null) {
                    fileChannel.close();
                }
                throw e;
            }
        } else {
            this._data = new FileBackedDataSource(fileChannel, z, z3);
        }
        ByteBuffer allocate = ByteBuffer.allocate(512);
        IOUtils.readFully((ReadableByteChannel) fileChannel, allocate);
        this._header = new HeaderBlock(allocate);
        readCoreContents();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        if (r1 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public POIFSFileSystem(java.io.InputStream r8) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 0
            r7.<init>((boolean) r0)
            java.nio.channels.ReadableByteChannel r1 = java.nio.channels.Channels.newChannel(r8)     // Catch:{ all -> 0x0079 }
            r2 = 512(0x200, float:7.175E-43)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ all -> 0x006b }
            org.apache.poi.util.IOUtils.readFully((java.nio.channels.ReadableByteChannel) r1, (java.nio.ByteBuffer) r2)     // Catch:{ all -> 0x006b }
            org.apache.poi.poifs.storage.HeaderBlock r3 = new org.apache.poi.poifs.storage.HeaderBlock     // Catch:{ all -> 0x006b }
            r3.<init>((java.nio.ByteBuffer) r2)     // Catch:{ all -> 0x006b }
            r7._header = r3     // Catch:{ all -> 0x006b }
            int r3 = r3.getBATCount()     // Catch:{ all -> 0x006b }
            sanityCheckBlockCount(r3)     // Catch:{ all -> 0x006b }
            org.apache.poi.poifs.storage.HeaderBlock r3 = r7._header     // Catch:{ all -> 0x006b }
            long r3 = org.apache.poi.poifs.storage.BATBlock.calculateMaximumSize(r3)     // Catch:{ all -> 0x006b }
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0063
            r5 = 250000000(0xee6b280, float:5.6871267E-30)
            org.apache.poi.util.IOUtils.safelyAllocateCheck(r3, r5)     // Catch:{ all -> 0x006b }
            int r3 = (int) r3     // Catch:{ all -> 0x006b }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ all -> 0x006b }
            r2.position(r0)     // Catch:{ all -> 0x006b }
            r3.put(r2)     // Catch:{ all -> 0x006b }
            int r2 = r2.capacity()     // Catch:{ all -> 0x006b }
            r3.position(r2)     // Catch:{ all -> 0x006b }
            org.apache.poi.util.IOUtils.readFully((java.nio.channels.ReadableByteChannel) r1, (java.nio.ByteBuffer) r3)     // Catch:{ all -> 0x006b }
            r0 = 1
            org.apache.poi.poifs.nio.ByteArrayBackedDataSource r2 = new org.apache.poi.poifs.nio.ByteArrayBackedDataSource     // Catch:{ all -> 0x006b }
            byte[] r4 = r3.array()     // Catch:{ all -> 0x006b }
            int r3 = r3.position()     // Catch:{ all -> 0x006b }
            r2.<init>(r4, r3)     // Catch:{ all -> 0x006b }
            r7._data = r2     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x005c
            r1.close()     // Catch:{ all -> 0x0079 }
        L_0x005c:
            r7.closeInputStream(r8, r0)
            r7.readCoreContents()
            return
        L_0x0063:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x006b }
            java.lang.String r3 = "Unable read a >2gb file via an InputStream"
            r2.<init>(r3)     // Catch:{ all -> 0x006b }
            throw r2     // Catch:{ all -> 0x006b }
        L_0x006b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x006d }
        L_0x006d:
            r3 = move-exception
            if (r1 == 0) goto L_0x0078
            r1.close()     // Catch:{ all -> 0x0074 }
            goto L_0x0078
        L_0x0074:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ all -> 0x0079 }
        L_0x0078:
            throw r3     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r1 = move-exception
            r7.closeInputStream(r8, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.POIFSFileSystem.<init>(java.io.InputStream):void");
    }

    private void closeInputStream(InputStream inputStream, boolean z) {
        try {
            inputStream.close();
        } catch (IOException e) {
            if (!z) {
                LOG.atError().withThrowable(e).log("can't close input stream");
                return;
            }
            throw new RuntimeException(e);
        }
    }

    private void readCoreContents() throws IOException {
        this.bigBlockSize = this._header.getBigBlockSize();
        BlockStore.ChainLoopDetector chainLoopDetector = getChainLoopDetector();
        for (int readBAT : this._header.getBATArray()) {
            readBAT(readBAT, chainLoopDetector);
        }
        int bATCount = this._header.getBATCount() - this._header.getBATArray().length;
        int xBATIndex = this._header.getXBATIndex();
        for (int i = 0; i < this._header.getXBATCount(); i++) {
            chainLoopDetector.claim(xBATIndex);
            BATBlock createBATBlock = BATBlock.createBATBlock(this.bigBlockSize, getBlockAt(xBATIndex));
            createBATBlock.setOurBlockIndex(xBATIndex);
            xBATIndex = createBATBlock.getValueAt(this.bigBlockSize.getXBATEntriesPerBlock());
            this._xbat_blocks.add(createBATBlock);
            int min = Math.min(bATCount, this.bigBlockSize.getXBATEntriesPerBlock());
            for (int i2 = 0; i2 < min; i2++) {
                int valueAt = createBATBlock.getValueAt(i2);
                if (valueAt == -1 || valueAt == -2) {
                    break;
                }
                readBAT(valueAt, chainLoopDetector);
            }
            bATCount -= min;
        }
        this._property_table = new PropertyTable(this._header, this);
        ArrayList arrayList = new ArrayList();
        this._mini_store = new POIFSMiniStore(this, this._property_table.getRoot(), arrayList, this._header);
        int sBATStart = this._header.getSBATStart();
        for (int i3 = 0; i3 < this._header.getSBATCount() && sBATStart != -2; i3++) {
            chainLoopDetector.claim(sBATStart);
            BATBlock createBATBlock2 = BATBlock.createBATBlock(this.bigBlockSize, getBlockAt(sBATStart));
            createBATBlock2.setOurBlockIndex(sBATStart);
            arrayList.add(createBATBlock2);
            sBATStart = getNextBlock(sBATStart);
        }
    }

    private void readBAT(int i, BlockStore.ChainLoopDetector chainLoopDetector) throws IOException {
        chainLoopDetector.claim(i);
        BATBlock createBATBlock = BATBlock.createBATBlock(this.bigBlockSize, getBlockAt(i));
        createBATBlock.setOurBlockIndex(i);
        this._bat_blocks.add(createBATBlock);
    }

    private BATBlock createBAT(int i, boolean z) throws IOException {
        BATBlock createEmptyBATBlock = BATBlock.createEmptyBATBlock(this.bigBlockSize, !z);
        createEmptyBATBlock.setOurBlockIndex(i);
        this._data.write(ByteBuffer.allocate(this.bigBlockSize.getBigBlockSize()), Math.multiplyExact(((long) i) + 1, (long) this.bigBlockSize.getBigBlockSize()));
        return createEmptyBATBlock;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer getBlockAt(int i) throws IOException {
        try {
            return this._data.read(this.bigBlockSize.getBigBlockSize(), (((long) i) + 1) * ((long) this.bigBlockSize.getBigBlockSize()));
        } catch (IndexOutOfBoundsException e) {
            IndexOutOfBoundsException indexOutOfBoundsException = new IndexOutOfBoundsException("Block " + i + " not found");
            indexOutOfBoundsException.initCause(e);
            throw indexOutOfBoundsException;
        }
    }

    /* access modifiers changed from: protected */
    public ByteBuffer createBlockIfNeeded(int i) throws IOException {
        try {
            return getBlockAt(i);
        } catch (IndexOutOfBoundsException unused) {
            long bigBlockSize2 = (((long) i) + 1) * ((long) this.bigBlockSize.getBigBlockSize());
            this._data.write(ByteBuffer.allocate(getBigBlockSize()), bigBlockSize2);
            return getBlockAt(i);
        }
    }

    /* access modifiers changed from: protected */
    public BATBlock.BATBlockAndIndex getBATBlockAndIndex(int i) {
        return BATBlock.getBATBlockAndIndex(i, this._header, this._bat_blocks);
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
        BATBlock bATBlock;
        int bATEntriesPerBlock = this.bigBlockSize.getBATEntriesPerBlock();
        int i = 0;
        int i2 = 0;
        for (BATBlock next : this._bat_blocks) {
            if (next.hasFreeSectors()) {
                for (int i3 = 0; i3 < bATEntriesPerBlock; i3++) {
                    if (next.getValueAt(i3) == -1) {
                        return i2 + i3;
                    }
                }
                continue;
            }
            i2 += bATEntriesPerBlock;
        }
        BATBlock createBAT = createBAT(i2, true);
        createBAT.setValueAt(0, -3);
        this._bat_blocks.add(createBAT);
        if (this._header.getBATCount() >= 109) {
            Iterator<BATBlock> it = this._xbat_blocks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    bATBlock = null;
                    break;
                }
                bATBlock = it.next();
                if (bATBlock.hasFreeSectors()) {
                    break;
                }
            }
            if (bATBlock == null) {
                int i4 = i2 + 1;
                BATBlock createBAT2 = createBAT(i4, false);
                createBAT2.setValueAt(0, i2);
                createBAT.setValueAt(1, -4);
                if (this._xbat_blocks.isEmpty()) {
                    this._header.setXBATStart(i4);
                } else {
                    List<BATBlock> list = this._xbat_blocks;
                    list.get(list.size() - 1).setValueAt(this.bigBlockSize.getXBATEntriesPerBlock(), i4);
                }
                this._xbat_blocks.add(createBAT2);
                this._header.setXBATCount(this._xbat_blocks.size());
                i2 = i4;
            } else {
                while (true) {
                    if (i >= this.bigBlockSize.getXBATEntriesPerBlock()) {
                        break;
                    } else if (bATBlock.getValueAt(i) == -1) {
                        bATBlock.setValueAt(i, i2);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        } else {
            int bATCount = this._header.getBATCount() + 1;
            int[] iArr = new int[bATCount];
            int i5 = bATCount - 1;
            System.arraycopy(this._header.getBATArray(), 0, iArr, 0, i5);
            iArr[i5] = i2;
            this._header.setBATArray(iArr);
        }
        this._header.setBATCount(this._bat_blocks.size());
        return i2 + 1;
    }

    /* access modifiers changed from: protected */
    public long size() throws IOException {
        return this._data.size();
    }

    /* access modifiers changed from: protected */
    public BlockStore.ChainLoopDetector getChainLoopDetector() throws IOException {
        return new BlockStore.ChainLoopDetector(this._data.size());
    }

    /* access modifiers changed from: package-private */
    public PropertyTable _get_property_table() {
        return this._property_table;
    }

    /* access modifiers changed from: package-private */
    public POIFSMiniStore getMiniStore() {
        return this._mini_store;
    }

    /* access modifiers changed from: package-private */
    public void addDocument(POIFSDocument pOIFSDocument) {
        this._property_table.addProperty(pOIFSDocument.getDocumentProperty());
    }

    /* access modifiers changed from: package-private */
    public void addDirectory(DirectoryProperty directoryProperty) {
        this._property_table.addProperty(directoryProperty);
    }

    public DocumentEntry createDocument(InputStream inputStream, String str) throws IOException {
        return getRoot().createDocument(str, inputStream);
    }

    public DocumentEntry createDocument(String str, int i, POIFSWriterListener pOIFSWriterListener) throws IOException {
        return getRoot().createDocument(str, i, pOIFSWriterListener);
    }

    public DirectoryEntry createDirectory(String str) throws IOException {
        return getRoot().createDirectory(str);
    }

    public DocumentEntry createOrUpdateDocument(InputStream inputStream, String str) throws IOException {
        return getRoot().createOrUpdateDocument(str, inputStream);
    }

    public boolean isInPlaceWriteable() {
        DataSource dataSource = this._data;
        return (dataSource instanceof FileBackedDataSource) && ((FileBackedDataSource) dataSource).isWriteable();
    }

    public void writeFilesystem() throws IOException {
        DataSource dataSource = this._data;
        if (!(dataSource instanceof FileBackedDataSource)) {
            throw new IllegalArgumentException("POIFS opened from an inputstream, so writeFilesystem() may not be called. Use writeFilesystem(OutputStream) instead");
        } else if (((FileBackedDataSource) dataSource).isWriteable()) {
            syncWithDataSource();
        } else {
            throw new IllegalArgumentException("POIFS opened in read only mode, so writeFilesystem() may not be called. Open the FileSystem in read-write mode first");
        }
    }

    public void writeFilesystem(OutputStream outputStream) throws IOException {
        syncWithDataSource();
        this._data.copyTo(outputStream);
    }

    private void syncWithDataSource() throws IOException {
        this._mini_store.syncWithDataSource();
        POIFSStream pOIFSStream = new POIFSStream(this, this._header.getPropertyStart());
        this._property_table.preWrite();
        this._property_table.write(pOIFSStream);
        UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream(this._header.getBigBlockSize().getBigBlockSize());
        this._header.writeData(unsynchronizedByteArrayOutputStream);
        getBlockAt(-1).put(unsynchronizedByteArrayOutputStream.toByteArray());
        for (BATBlock next : this._bat_blocks) {
            next.writeData(getBlockAt(next.getOurBlockIndex()));
        }
        for (BATBlock next2 : this._xbat_blocks) {
            next2.writeData(getBlockAt(next2.getOurBlockIndex()));
        }
    }

    public void close() throws IOException {
        this._data.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003b, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0043, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0047, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x004a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x004f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0050, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0053, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r4) throws java.io.IOException {
        /*
            int r0 = r4.length
            r1 = 2
            r2 = 1
            if (r0 == r1) goto L_0x000f
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "two arguments required: input filename and output filename"
            r0.println(r1)
            java.lang.System.exit(r2)
        L_0x000f:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r1 = 0
            r1 = r4[r1]
            r0.<init>(r1)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0048 }
            r4 = r4[r2]     // Catch:{ all -> 0x0048 }
            r1.<init>(r4)     // Catch:{ all -> 0x0048 }
            org.apache.poi.poifs.filesystem.POIFSFileSystem r4 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x003c }
            r4.<init>((java.io.InputStream) r0)     // Catch:{ all -> 0x003c }
            r4.writeFilesystem(r1)     // Catch:{ all -> 0x0030 }
            r4.close()     // Catch:{ all -> 0x003c }
            r1.close()     // Catch:{ all -> 0x0048 }
            r0.close()
            return
        L_0x0030:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r3 = move-exception
            r4.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ all -> 0x003c }
        L_0x003b:
            throw r3     // Catch:{ all -> 0x003c }
        L_0x003c:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003e }
        L_0x003e:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r1 = move-exception
            r4.addSuppressed(r1)     // Catch:{ all -> 0x0048 }
        L_0x0047:
            throw r2     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004a }
        L_0x004a:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x0053:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.POIFSFileSystem.main(java.lang.String[]):void");
    }

    public DirectoryNode getRoot() {
        if (this._root == null) {
            this._root = new DirectoryNode(this._property_table.getRoot(), this, (DirectoryNode) null);
        }
        return this._root;
    }

    public DocumentInputStream createDocumentInputStream(String str) throws IOException {
        return getRoot().createDocumentInputStream(str);
    }

    /* access modifiers changed from: package-private */
    public void remove(EntryNode entryNode) throws IOException {
        if (entryNode instanceof DocumentEntry) {
            new POIFSDocument((DocumentProperty) entryNode.getProperty(), this).free();
        }
        this._property_table.removeProperty(entryNode.getProperty());
    }

    public Object[] getViewableArray() {
        return preferArray() ? getRoot().getViewableArray() : new Object[0];
    }

    public Iterator<Object> getViewableIterator() {
        if (!preferArray()) {
            return getRoot().getViewableIterator();
        }
        return Collections.emptyIterator();
    }

    public boolean preferArray() {
        return getRoot().preferArray();
    }

    public int getBigBlockSize() {
        return this.bigBlockSize.getBigBlockSize();
    }

    public POIFSBigBlockSize getBigBlockSizeDetails() {
        return this.bigBlockSize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0025, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002e, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0031, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.poifs.filesystem.POIFSFileSystem create(java.io.File r3) throws java.io.IOException {
        /*
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r0.<init>()
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0026 }
            r1.<init>(r3)     // Catch:{ all -> 0x0026 }
            r0.writeFilesystem(r1)     // Catch:{ all -> 0x001a }
            r1.close()     // Catch:{ all -> 0x0026 }
            r0.close()
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r1 = 0
            r0.<init>((java.io.File) r3, (boolean) r1)
            return r0
        L_0x001a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001c }
        L_0x001c:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch:{ all -> 0x0026 }
        L_0x0025:
            throw r2     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x0031:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.POIFSFileSystem.create(java.io.File):org.apache.poi.poifs.filesystem.POIFSFileSystem");
    }

    /* access modifiers changed from: protected */
    public int getBlockStoreBlockSize() {
        return getBigBlockSize();
    }

    @Internal
    public PropertyTable getPropertyTable() {
        return this._property_table;
    }

    @Internal
    public HeaderBlock getHeaderBlock() {
        return this._header;
    }

    /* access modifiers changed from: protected */
    public void releaseBuffer(ByteBuffer byteBuffer) {
        DataSource dataSource = this._data;
        if (dataSource instanceof FileBackedDataSource) {
            ((FileBackedDataSource) dataSource).releaseBuffer(byteBuffer);
        }
    }

    private static void sanityCheckBlockCount(int i) throws IOException {
        if (i <= 0) {
            throw new IOException("Illegal block count; minimum count is 1, got " + i + " instead");
        } else if (i > 65535) {
            throw new IOException("Block count " + i + " is too high. POI maximum is 65535.");
        }
    }
}
