package org.apache.poi.poifs.crypt.xor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndian;

public class XOREncryptor extends Encryptor {
    /* access modifiers changed from: protected */
    public int getKeySizeInBytes() {
        return -1;
    }

    public void setChunkSize(int i) {
    }

    protected XOREncryptor() {
    }

    protected XOREncryptor(XOREncryptor xOREncryptor) {
        super(xOREncryptor);
    }

    public void confirmPassword(String str) {
        int createXorKey1 = CryptoFunctions.createXorKey1(str);
        int createXorVerifier1 = CryptoFunctions.createXorVerifier1(str);
        byte[] createXorArray1 = CryptoFunctions.createXorArray1(str);
        byte[] bArr = new byte[2];
        XOREncryptionVerifier xOREncryptionVerifier = (XOREncryptionVerifier) getEncryptionInfo().getVerifier();
        LittleEndian.putUShort(bArr, 0, createXorKey1);
        xOREncryptionVerifier.setEncryptedKey(bArr);
        LittleEndian.putUShort(bArr, 0, createXorVerifier1);
        xOREncryptionVerifier.setEncryptedVerifier(bArr);
        setSecretKey(new SecretKeySpec(createXorArray1, "XOR"));
    }

    public void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        confirmPassword(str);
    }

    public OutputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        return new XORCipherOutputStream(directoryNode);
    }

    public XORCipherOutputStream getDataStream(OutputStream outputStream, int i) throws IOException, GeneralSecurityException {
        return new XORCipherOutputStream(outputStream, i);
    }

    public XOREncryptor copy() {
        return new XOREncryptor(this);
    }

    private class XORCipherOutputStream extends ChunkedCipherOutputStream {
        private int recordEnd;
        private int recordStart;

        private byte rotateLeft(byte b, int i) {
            byte b2 = b & UByte.MAX_VALUE;
            return (byte) ((b2 >>> (8 - i)) | (b2 << i));
        }

        /* access modifiers changed from: protected */
        public void calculateChecksum(File file, int i) {
        }

        public XORCipherOutputStream(OutputStream outputStream, int i) throws IOException, GeneralSecurityException {
            super(outputStream, -1);
        }

        public XORCipherOutputStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
            super(directoryNode, -1);
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i, boolean z) throws GeneralSecurityException {
            return XORDecryptor.initCipherForBlock(cipher, i, XOREncryptor.this.getEncryptionInfo(), XOREncryptor.this.getSecretKey(), 1);
        }

        /* access modifiers changed from: protected */
        public void createEncryptionInfoEntry(DirectoryNode directoryNode, File file) {
            throw new EncryptedDocumentException("createEncryptionInfoEntry not supported");
        }

        public void setNextRecordSize(int i, boolean z) {
            if (this.recordEnd > 0 && !z) {
                invokeCipher((int) getPos(), true);
            }
            int totalPos = ((int) getTotalPos()) + 4;
            this.recordStart = totalPos;
            this.recordEnd = totalPos + i;
        }

        public void flush() throws IOException {
            setNextRecordSize(0, true);
            super.flush();
        }

        /* access modifiers changed from: protected */
        public int invokeCipher(int i, boolean z) {
            if (i == 0) {
                return 0;
            }
            int max = Math.max(i - (this.recordEnd - this.recordStart), 0);
            SparseBitSet plainByteFlags = getPlainByteFlags();
            byte[] encoded = XOREncryptor.this.getEncryptionInfo().getEncryptor().getSecretKey().getEncoded();
            byte[] chunk = getChunk();
            byte[] bArr = plainByteFlags.isEmpty() ? null : (byte[]) chunk.clone();
            int i2 = this.recordEnd + (max - this.recordStart);
            int i3 = max;
            while (i3 < i) {
                chunk[i3] = rotateLeft((byte) (encoded[i2 & 15] ^ chunk[i3]), 5);
                i3++;
                i2++;
            }
            if (bArr != null) {
                int nextSetBit = plainByteFlags.nextSetBit(max);
                while (nextSetBit >= 0 && nextSetBit < i) {
                    chunk[nextSetBit] = bArr[nextSetBit];
                    nextSetBit = plainByteFlags.nextSetBit(nextSetBit + 1);
                }
            }
            return i;
        }
    }
}
