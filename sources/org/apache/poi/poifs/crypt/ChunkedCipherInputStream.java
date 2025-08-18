package org.apache.poi.poifs.crypt;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import kotlin.UByte;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInputStream;

@Internal
public abstract class ChunkedCipherInputStream extends LittleEndianInputStream {
    private final byte[] chunk;
    private final int chunkBits;
    private boolean chunkIsValid;
    private final int chunkSize;
    private final Cipher cipher;
    private int lastIndex;
    private final byte[] plain;
    private long pos;
    private final long size;

    /* access modifiers changed from: protected */
    public abstract Cipher initCipherForBlock(Cipher cipher2, int i) throws GeneralSecurityException;

    public boolean markSupported() {
        return false;
    }

    public void setNextRecordSize(int i) {
    }

    public ChunkedCipherInputStream(InputStream inputStream, long j, int i) throws GeneralSecurityException {
        this(inputStream, j, i, 0);
    }

    public ChunkedCipherInputStream(InputStream inputStream, long j, int i, int i2) throws GeneralSecurityException {
        super(inputStream);
        this.size = j;
        this.pos = (long) i2;
        this.chunkSize = i;
        long j2 = (long) (i == -1 ? 4096 : i);
        byte[] safelyAllocate = IOUtils.safelyAllocate(j2, CryptoFunctions.MAX_RECORD_LENGTH);
        this.chunk = safelyAllocate;
        this.plain = IOUtils.safelyAllocate(j2, CryptoFunctions.MAX_RECORD_LENGTH);
        int bitCount = Integer.bitCount(safelyAllocate.length - 1);
        this.chunkBits = bitCount;
        int i3 = (int) (this.pos >> bitCount);
        this.lastIndex = i3;
        this.cipher = initCipherForBlock((Cipher) null, i3);
    }

    public final Cipher initCipherForBlock(int i) throws IOException, GeneralSecurityException {
        if (this.chunkSize == -1) {
            this.chunkIsValid = false;
            return initCipherForBlock(this.cipher, i);
        }
        throw new GeneralSecurityException("the cipher block can only be set for streaming encryption, e.g. CryptoAPI...");
    }

    public int read() throws IOException {
        byte[] bArr = {0};
        if (read(bArr) == 1) {
            return bArr[0] & UByte.MAX_VALUE;
        }
        return -1;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return read(bArr, i, i2, false);
    }

    private int read(byte[] bArr, int i, int i2, boolean z) throws IOException {
        if (remainingBytes() <= 0) {
            return -1;
        }
        int chunkMask = getChunkMask();
        int i3 = 0;
        while (i2 > 0) {
            if (!this.chunkIsValid) {
                try {
                    nextChunk();
                    this.chunkIsValid = true;
                } catch (GeneralSecurityException e) {
                    throw new EncryptedDocumentException(e.getMessage(), e);
                }
            }
            long j = (long) chunkMask;
            int length = (int) (((long) this.chunk.length) - (this.pos & j));
            int remainingBytes = remainingBytes();
            if (remainingBytes == 0) {
                return i3;
            }
            int min = Math.min(remainingBytes, Math.min(length, i2));
            System.arraycopy(z ? this.plain : this.chunk, (int) (this.pos & j), bArr, i, min);
            i += min;
            i2 -= min;
            long j2 = this.pos + ((long) min);
            this.pos = j2;
            if ((j2 & j) == 0) {
                this.chunkIsValid = false;
            }
            i3 += min;
        }
        return i3;
    }

    public long skip(long j) {
        long j2 = this.pos;
        long min = Math.min((long) remainingBytes(), j);
        if (((j2 ^ (this.pos + min)) & ((long) (~getChunkMask()))) != 0) {
            this.chunkIsValid = false;
        }
        this.pos += min;
        return min;
    }

    public int available() {
        return remainingBytes();
    }

    private int remainingBytes() {
        return (int) (this.size - this.pos);
    }

    public synchronized void mark(int i) {
        throw new UnsupportedOperationException();
    }

    public synchronized void reset() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public int getChunkMask() {
        return this.chunk.length - 1;
    }

    private void nextChunk() throws GeneralSecurityException, IOException {
        int read;
        boolean z = true;
        if (this.chunkSize != -1) {
            int i = (int) (this.pos >> this.chunkBits);
            initCipherForBlock(this.cipher, i);
            int i2 = this.lastIndex;
            if (i2 != i) {
                long j = (long) ((i - i2) << this.chunkBits);
                if (super.skip(j) < j) {
                    throw new EOFException("buffer underrun");
                }
            }
            this.lastIndex = i + 1;
        }
        int min = (int) Math.min(this.size, (long) this.chunk.length);
        int i3 = 0;
        do {
            read = super.read(this.plain, i3, min - i3);
            i3 += Math.max(0, read);
            if (read == -1) {
                break;
            }
        } while (i3 < min);
        if (read == -1) {
            long j2 = this.pos + ((long) i3);
            long j3 = this.size;
            if (j2 < j3 && j3 < 2147483647L) {
                throw new EOFException("buffer underrun");
            }
        }
        System.arraycopy(this.plain, 0, this.chunk, 0, i3);
        if (i3 != this.chunkSize) {
            z = false;
        }
        invokeCipher(i3, z);
    }

    /* access modifiers changed from: protected */
    public int invokeCipher(int i, boolean z) throws GeneralSecurityException {
        if (z) {
            Cipher cipher2 = this.cipher;
            byte[] bArr = this.chunk;
            return cipher2.doFinal(bArr, 0, i, bArr);
        }
        Cipher cipher3 = this.cipher;
        byte[] bArr2 = this.chunk;
        return cipher3.update(bArr2, 0, i, bArr2);
    }

    public void readPlain(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            int i3 = 0;
            do {
                try {
                    int read = read(bArr, i, i2, true);
                    i3 += Math.max(0, read);
                    if (read <= -1) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (i3 < i2);
            if (i3 < i2) {
                throw new EOFException("buffer underrun");
            }
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getChunk() {
        return this.chunk;
    }

    /* access modifiers changed from: protected */
    public byte[] getPlain() {
        return this.plain;
    }

    public long getPos() {
        return this.pos;
    }
}
