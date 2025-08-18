package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

public class ScatterZipOutputStream implements Closeable {
    /* access modifiers changed from: private */
    public final ScatterGatherBackingStore backingStore;
    private final AtomicBoolean isClosed = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final Queue<CompressedEntry> items = new ConcurrentLinkedQueue();
    private final StreamCompressor streamCompressor;
    private ZipEntryWriter zipEntryWriter;

    private static class CompressedEntry {
        final long compressedSize;
        final long crc;
        final long size;
        final ZipArchiveEntryRequest zipArchiveEntryRequest;

        public CompressedEntry(ZipArchiveEntryRequest zipArchiveEntryRequest2, long j, long j2, long j3) {
            this.zipArchiveEntryRequest = zipArchiveEntryRequest2;
            this.crc = j;
            this.compressedSize = j2;
            this.size = j3;
        }

        public ZipArchiveEntry transferToArchiveEntry() {
            ZipArchiveEntry zipArchiveEntry = this.zipArchiveEntryRequest.getZipArchiveEntry();
            zipArchiveEntry.setCompressedSize(this.compressedSize);
            zipArchiveEntry.setSize(this.size);
            zipArchiveEntry.setCrc(this.crc);
            zipArchiveEntry.setMethod(this.zipArchiveEntryRequest.getMethod());
            return zipArchiveEntry;
        }
    }

    public ScatterZipOutputStream(ScatterGatherBackingStore scatterGatherBackingStore, StreamCompressor streamCompressor2) {
        this.backingStore = scatterGatherBackingStore;
        this.streamCompressor = streamCompressor2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0033, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        if (r0 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        r10.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addArchiveEntry(org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest r11) throws java.io.IOException {
        /*
            r10 = this;
            java.io.InputStream r0 = r11.getPayloadStream()
            org.apache.commons.compress.archivers.zip.StreamCompressor r1 = r10.streamCompressor     // Catch:{ all -> 0x0031 }
            int r2 = r11.getMethod()     // Catch:{ all -> 0x0031 }
            r1.deflate(r0, r2)     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0012
            r0.close()
        L_0x0012:
            java.util.Queue<org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry> r0 = r10.items
            org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry r9 = new org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry
            org.apache.commons.compress.archivers.zip.StreamCompressor r1 = r10.streamCompressor
            long r3 = r1.getCrc32()
            org.apache.commons.compress.archivers.zip.StreamCompressor r1 = r10.streamCompressor
            long r5 = r1.getBytesWrittenForLastEntry()
            org.apache.commons.compress.archivers.zip.StreamCompressor r10 = r10.streamCompressor
            long r7 = r10.getBytesRead()
            r1 = r9
            r2 = r11
            r1.<init>(r2, r3, r5, r7)
            r0.add(r9)
            return
        L_0x0031:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r11 = move-exception
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r10.addSuppressed(r0)
        L_0x003e:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.addArchiveEntry(org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0044, code lost:
        if (r0 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004b, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004e, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            org.apache.commons.compress.parallel.ScatterGatherBackingStore r0 = r5.backingStore
            r0.closeForWriting()
            org.apache.commons.compress.parallel.ScatterGatherBackingStore r0 = r5.backingStore
            java.io.InputStream r0 = r0.getInputStream()
            java.util.Queue<org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry> r5 = r5.items     // Catch:{ all -> 0x0041 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0041 }
        L_0x0011:
            boolean r1 = r5.hasNext()     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x003b
            java.lang.Object r1 = r5.next()     // Catch:{ all -> 0x0041 }
            org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry r1 = (org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.CompressedEntry) r1     // Catch:{ all -> 0x0041 }
            org.apache.commons.compress.utils.BoundedInputStream r2 = new org.apache.commons.compress.utils.BoundedInputStream     // Catch:{ all -> 0x0041 }
            long r3 = r1.compressedSize     // Catch:{ all -> 0x0041 }
            r2.<init>(r0, r3)     // Catch:{ all -> 0x0041 }
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r1 = r1.transferToArchiveEntry()     // Catch:{ all -> 0x002f }
            r6.addRawArchiveEntry(r1, r2)     // Catch:{ all -> 0x002f }
            r2.close()     // Catch:{ all -> 0x0041 }
            goto L_0x0011
        L_0x002f:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r6 = move-exception
            r2.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ all -> 0x0041 }
        L_0x003a:
            throw r6     // Catch:{ all -> 0x0041 }
        L_0x003b:
            if (r0 == 0) goto L_0x0040
            r0.close()
        L_0x0040:
            return
        L_0x0041:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r6 = move-exception
            if (r0 == 0) goto L_0x004e
            r0.close()     // Catch:{ all -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r0 = move-exception
            r5.addSuppressed(r0)
        L_0x004e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.writeTo(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream):void");
    }

    public static class ZipEntryWriter implements Closeable {
        private final Iterator<CompressedEntry> itemsIterator;
        private final InputStream itemsIteratorData;

        public ZipEntryWriter(ScatterZipOutputStream scatterZipOutputStream) throws IOException {
            scatterZipOutputStream.backingStore.closeForWriting();
            this.itemsIterator = scatterZipOutputStream.items.iterator();
            this.itemsIteratorData = scatterZipOutputStream.backingStore.getInputStream();
        }

        public void close() throws IOException {
            InputStream inputStream = this.itemsIteratorData;
            if (inputStream != null) {
                inputStream.close();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
            r4.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
            throw r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
            r5 = move-exception;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeNextZipEntry(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r5) throws java.io.IOException {
            /*
                r4 = this;
                java.util.Iterator<org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry> r0 = r4.itemsIterator
                java.lang.Object r0 = r0.next()
                org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry r0 = (org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.CompressedEntry) r0
                org.apache.commons.compress.utils.BoundedInputStream r1 = new org.apache.commons.compress.utils.BoundedInputStream
                java.io.InputStream r4 = r4.itemsIteratorData
                long r2 = r0.compressedSize
                r1.<init>(r4, r2)
                org.apache.commons.compress.archivers.zip.ZipArchiveEntry r4 = r0.transferToArchiveEntry()     // Catch:{ all -> 0x001c }
                r5.addRawArchiveEntry(r4, r1)     // Catch:{ all -> 0x001c }
                r1.close()
                return
            L_0x001c:
                r4 = move-exception
                throw r4     // Catch:{ all -> 0x001e }
            L_0x001e:
                r5 = move-exception
                r1.close()     // Catch:{ all -> 0x0023 }
                goto L_0x0027
            L_0x0023:
                r0 = move-exception
                r4.addSuppressed(r0)
            L_0x0027:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.ZipEntryWriter.writeNextZipEntry(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream):void");
        }
    }

    public ZipEntryWriter zipEntryWriter() throws IOException {
        if (this.zipEntryWriter == null) {
            this.zipEntryWriter = new ZipEntryWriter(this);
        }
        return this.zipEntryWriter;
    }

    public void close() throws IOException {
        if (this.isClosed.compareAndSet(false, true)) {
            try {
                ZipEntryWriter zipEntryWriter2 = this.zipEntryWriter;
                if (zipEntryWriter2 != null) {
                    zipEntryWriter2.close();
                }
                this.backingStore.close();
            } finally {
                this.streamCompressor.close();
            }
        }
    }

    public static ScatterZipOutputStream fileBased(File file) throws FileNotFoundException {
        return fileBased(file, -1);
    }

    public static ScatterZipOutputStream fileBased(File file, int i) throws FileNotFoundException {
        FileBasedScatterGatherBackingStore fileBasedScatterGatherBackingStore = new FileBasedScatterGatherBackingStore(file);
        return new ScatterZipOutputStream(fileBasedScatterGatherBackingStore, StreamCompressor.create(i, (ScatterGatherBackingStore) fileBasedScatterGatherBackingStore));
    }
}
