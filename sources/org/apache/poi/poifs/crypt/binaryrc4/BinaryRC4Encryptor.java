package org.apache.poi.poifs.crypt.binaryrc4;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.RandomSingleton;

public class BinaryRC4Encryptor extends Encryptor {
    /* access modifiers changed from: private */
    public int chunkSize = 512;

    protected BinaryRC4Encryptor() {
    }

    protected BinaryRC4Encryptor(BinaryRC4Encryptor binaryRC4Encryptor) {
        super(binaryRC4Encryptor);
        this.chunkSize = binaryRC4Encryptor.chunkSize;
    }

    public void confirmPassword(String str) {
        SecureRandom instance = RandomSingleton.getInstance();
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[16];
        instance.nextBytes(bArr);
        instance.nextBytes(bArr2);
        confirmPassword(str, (byte[]) null, (byte[]) null, bArr2, bArr, (byte[]) null);
    }

    public void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        BinaryRC4EncryptionVerifier binaryRC4EncryptionVerifier = (BinaryRC4EncryptionVerifier) getEncryptionInfo().getVerifier();
        binaryRC4EncryptionVerifier.setSalt(bArr4);
        SecretKey generateSecretKey = BinaryRC4Decryptor.generateSecretKey(str, binaryRC4EncryptionVerifier);
        setSecretKey(generateSecretKey);
        try {
            Cipher initCipherForBlock = BinaryRC4Decryptor.initCipherForBlock((Cipher) null, 0, getEncryptionInfo(), generateSecretKey, 1);
            byte[] bArr6 = new byte[16];
            initCipherForBlock.update(bArr3, 0, 16, bArr6);
            binaryRC4EncryptionVerifier.setEncryptedVerifier(bArr6);
            binaryRC4EncryptionVerifier.setEncryptedVerifierHash(initCipherForBlock.doFinal(CryptoFunctions.getMessageDigest(binaryRC4EncryptionVerifier.getHashAlgorithm()).digest(bArr3)));
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    public OutputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        return new BinaryRC4CipherOutputStream(directoryNode);
    }

    public BinaryRC4CipherOutputStream getDataStream(OutputStream outputStream, int i) throws IOException, GeneralSecurityException {
        return new BinaryRC4CipherOutputStream(outputStream);
    }

    /* access modifiers changed from: protected */
    public int getKeySizeInBytes() {
        return getEncryptionInfo().getHeader().getKeySize() / 8;
    }

    /* access modifiers changed from: protected */
    public void createEncryptionInfoEntry(DirectoryNode directoryNode) throws IOException {
        DataSpaceMapUtils.addDefaultDataSpace(directoryNode);
        final EncryptionInfo encryptionInfo = getEncryptionInfo();
        final BinaryRC4EncryptionHeader binaryRC4EncryptionHeader = (BinaryRC4EncryptionHeader) encryptionInfo.getHeader();
        final BinaryRC4EncryptionVerifier binaryRC4EncryptionVerifier = (BinaryRC4EncryptionVerifier) encryptionInfo.getVerifier();
        DataSpaceMapUtils.createEncryptionEntry(directoryNode, EncryptionInfo.ENCRYPTION_INFO_ENTRY, new EncryptionRecord() {
            public void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
                littleEndianByteArrayOutputStream.writeShort(encryptionInfo.getVersionMajor());
                littleEndianByteArrayOutputStream.writeShort(encryptionInfo.getVersionMinor());
                binaryRC4EncryptionHeader.write(littleEndianByteArrayOutputStream);
                binaryRC4EncryptionVerifier.write(littleEndianByteArrayOutputStream);
            }
        });
    }

    public void setChunkSize(int i) {
        this.chunkSize = i;
    }

    public BinaryRC4Encryptor copy() {
        return new BinaryRC4Encryptor(this);
    }

    protected class BinaryRC4CipherOutputStream extends ChunkedCipherOutputStream {
        /* access modifiers changed from: protected */
        public void calculateChecksum(File file, int i) {
        }

        public BinaryRC4CipherOutputStream(OutputStream outputStream) throws IOException, GeneralSecurityException {
            super(outputStream, BinaryRC4Encryptor.this.chunkSize);
        }

        public BinaryRC4CipherOutputStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
            super(directoryNode, BinaryRC4Encryptor.this.chunkSize);
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i, boolean z) throws GeneralSecurityException {
            return BinaryRC4Decryptor.initCipherForBlock(cipher, i, BinaryRC4Encryptor.this.getEncryptionInfo(), BinaryRC4Encryptor.this.getSecretKey(), 1);
        }

        /* access modifiers changed from: protected */
        public void createEncryptionInfoEntry(DirectoryNode directoryNode, File file) throws IOException, GeneralSecurityException {
            BinaryRC4Encryptor.this.createEncryptionInfoEntry(directoryNode);
        }

        public void flush() throws IOException {
            writeChunk(false);
            super.flush();
        }
    }
}
