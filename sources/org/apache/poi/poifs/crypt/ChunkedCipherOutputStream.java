package org.apache.poi.poifs.crypt;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.TempFile;

@Internal
public abstract class ChunkedCipherOutputStream extends FilterOutputStream {
    private static final Logger LOG = LogManager.getLogger((Class<?>) ChunkedCipherOutputStream.class);
    private static final int STREAMING = -1;
    private final byte[] chunk;
    private final int chunkBits;
    private final int chunkSize;
    private Cipher cipher;
    private final DirectoryNode dir;
    private final File fileOut;
    private boolean isClosed;
    private final SparseBitSet plainByteFlags;
    private long pos;
    private long totalPos;
    private long written;

    /* access modifiers changed from: protected */
    public abstract void calculateChecksum(File file, int i) throws GeneralSecurityException, IOException;

    /* access modifiers changed from: protected */
    public abstract void createEncryptionInfoEntry(DirectoryNode directoryNode, File file) throws IOException, GeneralSecurityException;

    /* access modifiers changed from: protected */
    public abstract Cipher initCipherForBlock(Cipher cipher2, int i, boolean z) throws IOException, GeneralSecurityException;

    public void setNextRecordSize(int i, boolean z) {
    }

    public ChunkedCipherOutputStream(DirectoryNode directoryNode, int i) throws IOException, GeneralSecurityException {
        super((OutputStream) null);
        this.chunkSize = i;
        i = i == -1 ? 4096 : i;
        this.chunk = IOUtils.safelyAllocate((long) i, CryptoFunctions.MAX_RECORD_LENGTH);
        this.plainByteFlags = new SparseBitSet(i);
        this.chunkBits = Integer.bitCount(i - 1);
        File createTempFile = TempFile.createTempFile("encrypted_package", "crypt");
        this.fileOut = createTempFile;
        this.out = new FileOutputStream(createTempFile);
        this.dir = directoryNode;
        this.cipher = initCipherForBlock((Cipher) null, 0, false);
    }

    public ChunkedCipherOutputStream(OutputStream outputStream, int i) throws IOException, GeneralSecurityException {
        super(outputStream);
        this.chunkSize = i;
        i = i == -1 ? 4096 : i;
        this.chunk = IOUtils.safelyAllocate((long) i, CryptoFunctions.MAX_RECORD_LENGTH);
        this.plainByteFlags = new SparseBitSet(i);
        this.chunkBits = Integer.bitCount(i - 1);
        this.fileOut = null;
        this.dir = null;
        this.cipher = initCipherForBlock((Cipher) null, 0, false);
    }

    public final Cipher initCipherForBlock(int i, boolean z) throws IOException, GeneralSecurityException {
        return initCipherForBlock(this.cipher, i, z);
    }

    /* access modifiers changed from: protected */
    @Internal
    public Cipher initCipherForBlockNoFlush(Cipher cipher2, int i, boolean z) throws IOException, GeneralSecurityException {
        return initCipherForBlock(this.cipher, i, z);
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2, false);
    }

    public void writePlain(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2, true);
    }

    /* access modifiers changed from: protected */
    public void write(byte[] bArr, int i, int i2, boolean z) throws IOException {
        if (i2 != 0) {
            if (i2 < 0 || bArr.length < i + i2) {
                throw new IOException("not enough bytes in your input buffer");
            }
            int chunkMask = getChunkMask();
            while (i2 > 0) {
                long j = (long) chunkMask;
                int i3 = (int) (this.pos & j);
                int min = Math.min(this.chunk.length - i3, i2);
                System.arraycopy(bArr, i, this.chunk, i3, min);
                if (z) {
                    this.plainByteFlags.set(i3, i3 + min);
                }
                long j2 = (long) min;
                long j3 = this.pos + j2;
                this.pos = j3;
                this.totalPos += j2;
                i += min;
                i2 -= min;
                if ((j3 & j) == 0) {
                    writeChunk(i2 > 0);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getChunkMask() {
        return this.chunk.length - 1;
    }

    /* access modifiers changed from: protected */
    public void writeChunk(boolean z) throws IOException {
        boolean z2;
        long j = this.pos;
        if (j != 0 && this.totalPos != this.written) {
            int chunkMask = (int) (j & ((long) getChunkMask()));
            long j2 = this.pos;
            int i = (int) (j2 >> this.chunkBits);
            boolean z3 = true;
            if (chunkMask == 0) {
                i--;
                chunkMask = this.chunk.length;
                z2 = false;
            } else {
                z2 = true;
            }
            try {
                this.pos = 0;
                if (this.chunkSize != -1) {
                    this.cipher = initCipherForBlock(this.cipher, i, z2);
                    this.pos = j2;
                } else if (z) {
                    z3 = false;
                }
                int invokeCipher = invokeCipher(chunkMask, z3);
                this.out.write(this.chunk, 0, invokeCipher);
                this.plainByteFlags.clear();
                this.written += (long) invokeCipher;
            } catch (GeneralSecurityException e) {
                throw new IOException("can't re-/initialize cipher", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int invokeCipher(int i, boolean z) throws GeneralSecurityException, IOException {
        int i2;
        boolean z2;
        byte[] bArr = this.plainByteFlags.isEmpty() ? null : (byte[]) this.chunk.clone();
        if (z) {
            Cipher cipher2 = this.cipher;
            byte[] bArr2 = this.chunk;
            i2 = cipher2.doFinal(bArr2, 0, i, bArr2);
        } else {
            Cipher cipher3 = this.cipher;
            byte[] bArr3 = this.chunk;
            i2 = cipher3.update(bArr3, 0, i, bArr3);
        }
        if (z && "IBMJCE".equals(this.cipher.getProvider().getName()) && "RC4".equals(this.cipher.getAlgorithm())) {
            int i3 = (int) (this.pos >> this.chunkBits);
            if (i == 0) {
                i3--;
                i = this.chunk.length;
                z2 = false;
            } else {
                z2 = true;
            }
            this.cipher = initCipherForBlockNoFlush(this.cipher, i3, z2);
        }
        if (bArr != null) {
            int nextSetBit = this.plainByteFlags.nextSetBit(0);
            while (nextSetBit >= 0 && nextSetBit < i) {
                this.chunk[nextSetBit] = bArr[nextSetBit];
                nextSetBit = this.plainByteFlags.nextSetBit(nextSetBit + 1);
            }
        }
        return i2;
    }

    public void close() throws IOException {
        if (this.isClosed) {
            LOG.atDebug().log("ChunkedCipherOutputStream was already closed - ignoring");
            return;
        }
        this.isClosed = true;
        try {
            writeChunk(false);
            super.close();
            File file = this.fileOut;
            if (file != null) {
                calculateChecksum(this.fileOut, (int) this.pos);
                this.dir.createDocument(Decryptor.DEFAULT_POIFS_ENTRY, (int) (file.length() + 8), new ChunkedCipherOutputStream$$ExternalSyntheticLambda0(this));
                createEncryptionInfoEntry(this.dir, this.fileOut);
            }
            File file2 = this.fileOut;
            if (file2 != null) {
                file2.delete();
            }
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        } catch (Throwable th) {
            File file3 = this.fileOut;
            if (file3 != null) {
                file3.delete();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getChunk() {
        return this.chunk;
    }

    /* access modifiers changed from: protected */
    public SparseBitSet getPlainByteFlags() {
        return this.plainByteFlags;
    }

    /* access modifiers changed from: protected */
    public long getPos() {
        return this.pos;
    }

    /* access modifiers changed from: protected */
    public long getTotalPos() {
        return this.totalPos;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0047, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0048, code lost:
        if (r6 != null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processPOIFSWriterEvent(org.apache.poi.poifs.filesystem.POIFSWriterEvent r6) {
        /*
            r5 = this;
            org.apache.poi.poifs.filesystem.DocumentOutputStream r6 = r6.getStream()     // Catch:{ IOException -> 0x0053 }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ all -> 0x0045 }
            java.io.File r1 = r5.fileOut     // Catch:{ all -> 0x0045 }
            r0.<init>(r1)     // Catch:{ all -> 0x0045 }
            r1 = 8
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0039 }
            long r2 = r5.pos     // Catch:{ all -> 0x0039 }
            r4 = 0
            org.apache.poi.util.LittleEndian.putLong(r1, r4, r2)     // Catch:{ all -> 0x0039 }
            r6.write(r1)     // Catch:{ all -> 0x0039 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r6)     // Catch:{ all -> 0x0039 }
            r0.close()     // Catch:{ all -> 0x0045 }
            if (r6 == 0) goto L_0x0023
            r6.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0023:
            java.io.File r6 = r5.fileOut     // Catch:{ IOException -> 0x0053 }
            boolean r6 = r6.delete()     // Catch:{ IOException -> 0x0053 }
            if (r6 != 0) goto L_0x0038
            org.apache.logging.log4j.Logger r6 = LOG     // Catch:{ IOException -> 0x0053 }
            org.apache.logging.log4j.LogBuilder r6 = r6.atError()     // Catch:{ IOException -> 0x0053 }
            java.lang.String r0 = "Can't delete temporary encryption file: {}"
            java.io.File r5 = r5.fileOut     // Catch:{ IOException -> 0x0053 }
            r6.log((java.lang.String) r0, (java.lang.Object) r5)     // Catch:{ IOException -> 0x0053 }
        L_0x0038:
            return
        L_0x0039:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003b }
        L_0x003b:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ all -> 0x0045 }
        L_0x0044:
            throw r1     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r0 = move-exception
            if (r6 == 0) goto L_0x0052
            r6.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch:{ IOException -> 0x0053 }
        L_0x0052:
            throw r0     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            r5 = move-exception
            org.apache.poi.EncryptedDocumentException r6 = new org.apache.poi.EncryptedDocumentException
            r6.<init>((java.lang.Throwable) r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.ChunkedCipherOutputStream.processPOIFSWriterEvent(org.apache.poi.poifs.filesystem.POIFSWriterEvent):void");
    }
}
