package org.apache.poi.poifs.crypt.standard;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.LittleEndian;

public class StandardDecryptor extends Decryptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long _length = -1;

    protected StandardDecryptor() {
    }

    protected StandardDecryptor(StandardDecryptor standardDecryptor) {
        super(standardDecryptor);
        this._length = standardDecryptor._length;
    }

    public boolean verifyPassword(String str) {
        EncryptionVerifier verifier = getEncryptionInfo().getVerifier();
        SecretKey generateSecretKey = generateSecretKey(str, verifier, getKeySizeInBytes());
        Cipher cipher = getCipher(generateSecretKey);
        try {
            byte[] doFinal = cipher.doFinal(verifier.getEncryptedVerifier());
            setVerifier(doFinal);
            byte[] digest = CryptoFunctions.getMessageDigest(verifier.getHashAlgorithm()).digest(doFinal);
            if (!Arrays.equals(digest, Arrays.copyOf(cipher.doFinal(verifier.getEncryptedVerifierHash()), digest.length))) {
                return false;
            }
            setSecretKey(generateSecretKey);
            return true;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    protected static SecretKey generateSecretKey(String str, EncryptionVerifier encryptionVerifier, int i) {
        HashAlgorithm hashAlgorithm = encryptionVerifier.getHashAlgorithm();
        byte[] hashPassword = CryptoFunctions.hashPassword(str, hashAlgorithm, encryptionVerifier.getSalt(), encryptionVerifier.getSpinCount());
        byte[] bArr = new byte[4];
        LittleEndian.putInt(bArr, 0, 0);
        byte[] generateKey = CryptoFunctions.generateKey(hashPassword, hashAlgorithm, bArr, hashAlgorithm.hashSize);
        byte[] fillAndXor = fillAndXor(generateKey, TarConstants.LF_FIFO);
        byte[] fillAndXor2 = fillAndXor(generateKey, (byte) 92);
        byte[] bArr2 = new byte[(fillAndXor.length + fillAndXor2.length)];
        System.arraycopy(fillAndXor, 0, bArr2, 0, fillAndXor.length);
        System.arraycopy(fillAndXor2, 0, bArr2, fillAndXor.length, fillAndXor2.length);
        return new SecretKeySpec(Arrays.copyOf(bArr2, i), encryptionVerifier.getCipherAlgorithm().jceId);
    }

    protected static byte[] fillAndXor(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[64];
        Arrays.fill(bArr2, b);
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bArr2[i] ^ bArr[i]);
        }
        return CryptoFunctions.getMessageDigest(HashAlgorithm.sha1).digest(bArr2);
    }

    private Cipher getCipher(SecretKey secretKey) {
        EncryptionHeader header = getEncryptionInfo().getHeader();
        return CryptoFunctions.getCipher(secretKey, header.getCipherAlgorithm(), header.getChainingMode(), (byte[]) null, 2);
    }

    public InputStream getDataStream(DirectoryNode directoryNode) throws IOException {
        DocumentInputStream createDocumentInputStream = directoryNode.createDocumentInputStream(Decryptor.DEFAULT_POIFS_ENTRY);
        this._length = createDocumentInputStream.readLong();
        if (getSecretKey() == null) {
            verifyPassword((String) null);
        }
        long j = (long) getEncryptionInfo().getHeader().getCipherAlgorithm().blockSize;
        return new BoundedInputStream(new CipherInputStream(new BoundedInputStream(createDocumentInputStream, ((this._length / j) + 1) * j), getCipher(getSecretKey())), this._length);
    }

    public long getLength() {
        long j = this._length;
        if (j != -1) {
            return j;
        }
        throw new IllegalStateException("Decryptor.getDataStream() was not called");
    }

    public StandardDecryptor copy() {
        return new StandardDecryptor(this);
    }
}
