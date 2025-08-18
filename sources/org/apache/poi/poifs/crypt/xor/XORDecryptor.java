package org.apache.poi.poifs.crypt.xor;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndian;

public class XORDecryptor extends Decryptor {
    /* access modifiers changed from: private */
    public int chunkSize = 512;
    private long length = -1;

    protected static Cipher initCipherForBlock(Cipher cipher, int i, EncryptionInfo encryptionInfo, SecretKey secretKey, int i2) throws GeneralSecurityException {
        return null;
    }

    public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
        return null;
    }

    protected XORDecryptor() {
    }

    protected XORDecryptor(XORDecryptor xORDecryptor) {
        super(xORDecryptor);
        this.length = xORDecryptor.length;
        this.chunkSize = xORDecryptor.chunkSize;
    }

    public boolean verifyPassword(String str) {
        XOREncryptionVerifier xOREncryptionVerifier = (XOREncryptionVerifier) getEncryptionInfo().getVerifier();
        int uShort = LittleEndian.getUShort(xOREncryptionVerifier.getEncryptedKey());
        int uShort2 = LittleEndian.getUShort(xOREncryptionVerifier.getEncryptedVerifier());
        int createXorKey1 = CryptoFunctions.createXorKey1(str);
        int createXorVerifier1 = CryptoFunctions.createXorVerifier1(str);
        if (uShort != createXorKey1 || uShort2 != createXorVerifier1) {
            return false;
        }
        setSecretKey(new SecretKeySpec(CryptoFunctions.createXorArray1(str), "XOR"));
        return true;
    }

    public ChunkedCipherInputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        throw new EncryptedDocumentException("not supported");
    }

    public InputStream getDataStream(InputStream inputStream, int i, int i2) throws IOException, GeneralSecurityException {
        return new XORCipherInputStream(inputStream, i2);
    }

    public long getLength() {
        long j = this.length;
        if (j != -1) {
            return j;
        }
        throw new IllegalStateException("Decryptor.getDataStream() was not called");
    }

    public void setChunkSize(int i) {
        this.chunkSize = i;
    }

    public XORDecryptor copy() {
        return new XORDecryptor(this);
    }

    private class XORCipherInputStream extends ChunkedCipherInputStream {
        private final int initialOffset;
        private int recordEnd;
        private int recordStart;

        private byte rotateLeft(byte b, int i) {
            byte b2 = b & UByte.MAX_VALUE;
            return (byte) ((b2 >>> (8 - i)) | (b2 << i));
        }

        public XORCipherInputStream(InputStream inputStream, int i) throws GeneralSecurityException {
            super(inputStream, 2147483647L, XORDecryptor.this.chunkSize);
            this.initialOffset = i;
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
            return XORDecryptor.this.initCipherForBlock(cipher, i);
        }

        /* access modifiers changed from: protected */
        public int invokeCipher(int i, boolean z) {
            int pos = (int) getPos();
            byte[] encoded = XORDecryptor.this.getEncryptionInfo().getDecryptor().getSecretKey().getEncoded();
            byte[] chunk = getChunk();
            byte[] plain = getPlain();
            int chunkMask = getChunkMask() & pos;
            int i2 = this.initialOffset + this.recordEnd + (pos - this.recordStart);
            int i3 = 0;
            while (pos + i3 < this.recordEnd && i3 < i) {
                int i4 = chunkMask + i3;
                chunk[i4] = (byte) (rotateLeft(plain[i4], 3) ^ encoded[(i2 + i3) & 15]);
                i3++;
            }
            return i;
        }

        public void setNextRecordSize(int i) {
            int pos = (int) getPos();
            byte[] chunk = getChunk();
            int chunkMask = getChunkMask();
            this.recordStart = pos;
            this.recordEnd = pos + i;
            invokeCipher(Math.min(i, chunk.length - (pos & chunkMask)), true);
        }
    }
}
