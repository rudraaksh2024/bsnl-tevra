package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;

public final class POIFSDocument implements POIFSViewable, Iterable<ByteBuffer> {
    private int _block_size;
    private POIFSFileSystem _filesystem;
    private DocumentProperty _property;
    private POIFSStream _stream;

    public boolean preferArray() {
        return true;
    }

    public POIFSDocument(DocumentNode documentNode) {
        this((DocumentProperty) documentNode.getProperty(), ((DirectoryNode) documentNode.getParent()).getFileSystem());
    }

    public POIFSDocument(DocumentProperty documentProperty, POIFSFileSystem pOIFSFileSystem) {
        this._property = documentProperty;
        this._filesystem = pOIFSFileSystem;
        if (documentProperty.getSize() < 4096) {
            this._stream = new POIFSStream(this._filesystem.getMiniStore(), documentProperty.getStartBlock());
            this._block_size = this._filesystem.getMiniStore().getBlockStoreBlockSize();
            return;
        }
        this._stream = new POIFSStream(this._filesystem, documentProperty.getStartBlock());
        this._block_size = this._filesystem.getBlockStoreBlockSize();
    }

    public POIFSDocument(String str, POIFSFileSystem pOIFSFileSystem, InputStream inputStream) throws IOException {
        this._filesystem = pOIFSFileSystem;
        DocumentProperty documentProperty = new DocumentProperty(str, store(inputStream));
        this._property = documentProperty;
        documentProperty.setStartBlock(this._stream.getStartBlock());
        this._property.setDocument(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0073, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0074, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0077, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public POIFSDocument(java.lang.String r3, int r4, org.apache.poi.poifs.filesystem.POIFSFileSystem r5, org.apache.poi.poifs.filesystem.POIFSWriterListener r6) throws java.io.IOException {
        /*
            r2 = this;
            r2.<init>()
            r2._filesystem = r5
            r0 = 4096(0x1000, float:5.74E-42)
            if (r4 >= r0) goto L_0x0021
            org.apache.poi.poifs.filesystem.POIFSStream r0 = new org.apache.poi.poifs.filesystem.POIFSStream
            org.apache.poi.poifs.filesystem.POIFSMiniStore r5 = r5.getMiniStore()
            r0.<init>(r5)
            r2._stream = r0
            org.apache.poi.poifs.filesystem.POIFSFileSystem r5 = r2._filesystem
            org.apache.poi.poifs.filesystem.POIFSMiniStore r5 = r5.getMiniStore()
            int r5 = r5.getBlockStoreBlockSize()
            r2._block_size = r5
            goto L_0x0030
        L_0x0021:
            org.apache.poi.poifs.filesystem.POIFSStream r0 = new org.apache.poi.poifs.filesystem.POIFSStream
            r0.<init>(r5)
            r2._stream = r0
            org.apache.poi.poifs.filesystem.POIFSFileSystem r5 = r2._filesystem
            int r5 = r5.getBlockStoreBlockSize()
            r2._block_size = r5
        L_0x0030:
            org.apache.poi.poifs.property.DocumentProperty r5 = new org.apache.poi.poifs.property.DocumentProperty
            r5.<init>(r3, r4)
            r2._property = r5
            org.apache.poi.poifs.filesystem.POIFSStream r0 = r2._stream
            int r0 = r0.getStartBlock()
            r5.setStartBlock(r0)
            org.apache.poi.poifs.property.DocumentProperty r5 = r2._property
            r5.setDocument(r2)
            org.apache.poi.poifs.filesystem.DocumentOutputStream r5 = new org.apache.poi.poifs.filesystem.DocumentOutputStream
            long r0 = (long) r4
            r5.<init>((org.apache.poi.poifs.filesystem.POIFSDocument) r2, (long) r0)
            org.apache.poi.poifs.filesystem.POIFSDocumentPath r2 = new org.apache.poi.poifs.filesystem.POIFSDocumentPath     // Catch:{ all -> 0x006c }
            java.lang.String r0 = "\\\\"
            java.lang.String[] r3 = r3.split(r0)     // Catch:{ all -> 0x006c }
            r2.<init>(r3)     // Catch:{ all -> 0x006c }
            int r3 = r2.length()     // Catch:{ all -> 0x006c }
            int r3 = r3 + -1
            java.lang.String r3 = r2.getComponent(r3)     // Catch:{ all -> 0x006c }
            org.apache.poi.poifs.filesystem.POIFSWriterEvent r0 = new org.apache.poi.poifs.filesystem.POIFSWriterEvent     // Catch:{ all -> 0x006c }
            r0.<init>(r5, r2, r3, r4)     // Catch:{ all -> 0x006c }
            r6.processPOIFSWriterEvent(r0)     // Catch:{ all -> 0x006c }
            r5.close()
            return
        L_0x006c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x006e }
        L_0x006e:
            r3 = move-exception
            r5.close()     // Catch:{ all -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0077:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.POIFSDocument.<init>(java.lang.String, int, org.apache.poi.poifs.filesystem.POIFSFileSystem, org.apache.poi.poifs.filesystem.POIFSWriterListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0076, code lost:
        if (r6 != null) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007d, code lost:
        r5.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0080, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int store(java.io.InputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            r1 = 4097(0x1001, float:5.741E-42)
            r0.<init>(r6, r1)
            r6 = 4096(0x1000, float:5.74E-42)
            r0.mark(r6)
            r1 = 4096(0x1000, double:2.0237E-320)
            long r3 = org.apache.poi.util.IOUtils.skipFully(r0, r1)
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x0030
            org.apache.poi.poifs.filesystem.POIFSStream r6 = new org.apache.poi.poifs.filesystem.POIFSStream
            org.apache.poi.poifs.filesystem.POIFSFileSystem r1 = r5._filesystem
            org.apache.poi.poifs.filesystem.POIFSMiniStore r1 = r1.getMiniStore()
            r6.<init>(r1)
            r5._stream = r6
            org.apache.poi.poifs.filesystem.POIFSFileSystem r6 = r5._filesystem
            org.apache.poi.poifs.filesystem.POIFSMiniStore r6 = r6.getMiniStore()
            int r6 = r6.getBlockStoreBlockSize()
            r5._block_size = r6
            goto L_0x0041
        L_0x0030:
            org.apache.poi.poifs.filesystem.POIFSStream r6 = new org.apache.poi.poifs.filesystem.POIFSStream
            org.apache.poi.poifs.filesystem.POIFSFileSystem r1 = r5._filesystem
            r6.<init>(r1)
            r5._stream = r6
            org.apache.poi.poifs.filesystem.POIFSFileSystem r6 = r5._filesystem
            int r6 = r6.getBlockStoreBlockSize()
            r5._block_size = r6
        L_0x0041:
            r0.reset()
            org.apache.poi.poifs.filesystem.POIFSStream r6 = r5._stream
            java.io.OutputStream r6 = r6.getOutputStream()
            long r0 = org.apache.poi.util.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r6)     // Catch:{ all -> 0x0073 }
            int r5 = r5._block_size     // Catch:{ all -> 0x0073 }
            long r2 = (long) r5     // Catch:{ all -> 0x0073 }
            long r2 = r0 % r2
            int r2 = (int) r2     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0069
            if (r2 == r5) goto L_0x0069
            int r5 = r5 - r2
            long r2 = (long) r5     // Catch:{ all -> 0x0073 }
            int r5 = org.apache.poi.poifs.filesystem.POIFSFileSystem.getMaxRecordLength()     // Catch:{ all -> 0x0073 }
            byte[] r5 = org.apache.poi.util.IOUtils.safelyAllocate(r2, r5)     // Catch:{ all -> 0x0073 }
            r2 = -1
            java.util.Arrays.fill(r5, r2)     // Catch:{ all -> 0x0073 }
            r6.write(r5)     // Catch:{ all -> 0x0073 }
        L_0x0069:
            if (r6 == 0) goto L_0x006e
            r6.close()
        L_0x006e:
            int r5 = java.lang.Math.toIntExact(r0)
            return r5
        L_0x0073:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r0 = move-exception
            if (r6 == 0) goto L_0x0080
            r6.close()     // Catch:{ all -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r6 = move-exception
            r5.addSuppressed(r6)
        L_0x0080:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.POIFSDocument.store(java.io.InputStream):int");
    }

    /* access modifiers changed from: package-private */
    public void free() throws IOException {
        this._stream.free();
        this._property.setStartBlock(-2);
    }

    /* access modifiers changed from: package-private */
    public POIFSFileSystem getFileSystem() {
        return this._filesystem;
    }

    /* access modifiers changed from: package-private */
    public int getDocumentBlockSize() {
        return this._block_size;
    }

    public Iterator<ByteBuffer> iterator() {
        return getBlockIterator();
    }

    /* access modifiers changed from: package-private */
    public Iterator<ByteBuffer> getBlockIterator() {
        return (getSize() > 0 ? this._stream : Collections.emptyList()).iterator();
    }

    public int getSize() {
        return this._property.getSize();
    }

    public void replaceContents(InputStream inputStream) throws IOException {
        free();
        int store = store(inputStream);
        this._property.setStartBlock(this._stream.getStartBlock());
        this._property.updateSize(store);
    }

    /* access modifiers changed from: package-private */
    public DocumentProperty getDocumentProperty() {
        return this._property;
    }

    public Object[] getViewableArray() {
        String str;
        if (getSize() > 0) {
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) getSize(), POIFSFileSystem.getMaxRecordLength());
            Iterator<ByteBuffer> it = this._stream.iterator();
            int i = 0;
            while (it.hasNext()) {
                int min = Math.min(this._block_size, safelyAllocate.length - i);
                it.next().get(safelyAllocate, i, min);
                i += min;
            }
            str = HexDump.dump(safelyAllocate, 0, 0);
        } else {
            str = "<NO DATA>";
        }
        return new String[]{str};
    }

    public Iterator<Object> getViewableIterator() {
        return Collections.emptyIterator();
    }

    public String getShortDescription() {
        return "Document: \"" + this._property.getName() + "\" size = " + getSize();
    }
}
