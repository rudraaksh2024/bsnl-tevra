package org.apache.poi.poifs.crypt.agile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.LittleEndian;

public class AgileDecryptor extends Decryptor {
    static final byte[] kCryptoKeyBlock = longToBytes(1472127217842311382L);
    static final byte[] kHashedVerifierBlock = longToBytes(-2906493647876705202L);
    static final byte[] kIntegrityKeyBlock = longToBytes(6895764199477731830L);
    static final byte[] kIntegrityValueBlock = longToBytes(-6888397455483960269L);
    static final byte[] kVerifierInputBlock = longToBytes(-96877461722390919L);
    private long _length = -1;

    protected AgileDecryptor() {
    }

    protected AgileDecryptor(AgileDecryptor agileDecryptor) {
        super(agileDecryptor);
        this._length = agileDecryptor._length;
    }

    private static byte[] longToBytes(long j) {
        return ByteBuffer.allocate(8).putLong(j).array();
    }

    public boolean verifyPassword(String str) throws GeneralSecurityException {
        AgileEncryptionVerifier agileEncryptionVerifier = (AgileEncryptionVerifier) getEncryptionInfo().getVerifier();
        AgileEncryptionHeader agileEncryptionHeader = (AgileEncryptionHeader) getEncryptionInfo().getHeader();
        int blockSize = agileEncryptionHeader.getBlockSize();
        byte[] hashPassword = CryptoFunctions.hashPassword(str, agileEncryptionVerifier.getHashAlgorithm(), agileEncryptionVerifier.getSalt(), agileEncryptionVerifier.getSpinCount());
        byte[] hashInput = hashInput(agileEncryptionVerifier, hashPassword, kVerifierInputBlock, agileEncryptionVerifier.getEncryptedVerifier(), 2);
        setVerifier(hashInput);
        byte[] digest = CryptoFunctions.getMessageDigest(agileEncryptionVerifier.getHashAlgorithm()).digest(hashInput);
        byte[] block0 = CryptoFunctions.getBlock0(hashInput(agileEncryptionVerifier, hashPassword, kHashedVerifierBlock, agileEncryptionVerifier.getEncryptedVerifierHash(), 2), agileEncryptionVerifier.getHashAlgorithm().hashSize);
        SecretKeySpec secretKeySpec = new SecretKeySpec(CryptoFunctions.getBlock0(hashInput(agileEncryptionVerifier, hashPassword, kCryptoKeyBlock, agileEncryptionVerifier.getEncryptedKey(), 2), agileEncryptionHeader.getKeySize() / 8), agileEncryptionHeader.getCipherAlgorithm().jceId);
        byte[] generateIv = CryptoFunctions.generateIv(agileEncryptionHeader.getHashAlgorithm(), agileEncryptionHeader.getKeySalt(), kIntegrityKeyBlock, blockSize);
        CipherAlgorithm cipherAlgorithm = agileEncryptionHeader.getCipherAlgorithm();
        byte[] block02 = CryptoFunctions.getBlock0(CryptoFunctions.getCipher(secretKeySpec, cipherAlgorithm, agileEncryptionHeader.getChainingMode(), generateIv, 2).doFinal(agileEncryptionHeader.getEncryptedHmacKey()), agileEncryptionHeader.getHashAlgorithm().hashSize);
        byte[] block03 = CryptoFunctions.getBlock0(CryptoFunctions.getCipher(secretKeySpec, cipherAlgorithm, agileEncryptionVerifier.getChainingMode(), CryptoFunctions.generateIv(agileEncryptionHeader.getHashAlgorithm(), agileEncryptionHeader.getKeySalt(), kIntegrityValueBlock, blockSize), 2).doFinal(agileEncryptionHeader.getEncryptedHmacValue()), agileEncryptionHeader.getHashAlgorithm().hashSize);
        if (!Arrays.equals(block0, digest)) {
            return false;
        }
        setSecretKey(secretKeySpec);
        setIntegrityHmacKey(block02);
        setIntegrityHmacValue(block03);
        return true;
    }

    protected static int getNextBlockSize(int i, int i2) {
        return ((int) Math.ceil(((double) i) / ((double) i2))) * i2;
    }

    static byte[] hashInput(AgileEncryptionVerifier agileEncryptionVerifier, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        CipherAlgorithm cipherAlgorithm = agileEncryptionVerifier.getCipherAlgorithm();
        ChainingMode chainingMode = agileEncryptionVerifier.getChainingMode();
        int blockSize = agileEncryptionVerifier.getBlockSize();
        HashAlgorithm hashAlgorithm = agileEncryptionVerifier.getHashAlgorithm();
        Cipher cipher = CryptoFunctions.getCipher(new SecretKeySpec(CryptoFunctions.generateKey(bArr, hashAlgorithm, bArr2, agileEncryptionVerifier.getKeySize() / 8), cipherAlgorithm.jceId), cipherAlgorithm, chainingMode, CryptoFunctions.generateIv(hashAlgorithm, agileEncryptionVerifier.getSalt(), (byte[]) null, blockSize), i);
        if (bArr3 != null) {
            try {
                return cipher.doFinal(CryptoFunctions.getBlock0(bArr3, getNextBlockSize(bArr3.length, blockSize)));
            } catch (GeneralSecurityException e) {
                throw new EncryptedDocumentException((Throwable) e);
            }
        } else {
            throw new EncryptedDocumentException("Cannot has input without inputKey");
        }
    }

    public InputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        DocumentInputStream createDocumentInputStream = directoryNode.createDocumentInputStream(Decryptor.DEFAULT_POIFS_ENTRY);
        this._length = createDocumentInputStream.readLong();
        return new AgileCipherInputStream(createDocumentInputStream, this._length);
    }

    public long getLength() {
        long j = this._length;
        if (j != -1) {
            return j;
        }
        throw new IllegalStateException("EcmaDecryptor.getDataStream() was not called");
    }

    protected static Cipher initCipherForBlock(Cipher cipher, int i, boolean z, EncryptionInfo encryptionInfo, SecretKey secretKey, int i2) throws GeneralSecurityException {
        AlgorithmParameterSpec algorithmParameterSpec;
        EncryptionHeader header = encryptionInfo.getHeader();
        String str = z ? "PKCS5Padding" : "NoPadding";
        if (cipher == null || !cipher.getAlgorithm().endsWith(str)) {
            cipher = CryptoFunctions.getCipher(secretKey, header.getCipherAlgorithm(), header.getChainingMode(), header.getKeySalt(), i2, str);
        }
        byte[] bArr = new byte[4];
        LittleEndian.putInt(bArr, 0, i);
        byte[] generateIv = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), bArr, header.getBlockSize());
        if (header.getCipherAlgorithm() == CipherAlgorithm.rc2) {
            algorithmParameterSpec = new RC2ParameterSpec(secretKey.getEncoded().length * 8, generateIv);
        } else {
            algorithmParameterSpec = new IvParameterSpec(generateIv);
        }
        cipher.init(i2, secretKey, algorithmParameterSpec);
        return cipher;
    }

    private class AgileCipherInputStream extends ChunkedCipherInputStream {
        public AgileCipherInputStream(DocumentInputStream documentInputStream, long j) throws GeneralSecurityException {
            super(documentInputStream, j, 4096);
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
            return AgileDecryptor.initCipherForBlock(cipher, i, false, AgileDecryptor.this.getEncryptionInfo(), AgileDecryptor.this.getSecretKey(), 2);
        }
    }

    public AgileDecryptor copy() {
        return new AgileDecryptor(this);
    }
}
