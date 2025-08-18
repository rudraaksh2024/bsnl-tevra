package org.apache.poi.poifs.crypt.cryptoapi;

import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier;
import org.apache.poi.util.LittleEndianInput;

public class CryptoAPIEncryptionVerifier extends StandardEncryptionVerifier {
    protected CryptoAPIEncryptionVerifier(LittleEndianInput littleEndianInput, CryptoAPIEncryptionHeader cryptoAPIEncryptionHeader) {
        super(littleEndianInput, cryptoAPIEncryptionHeader);
    }

    protected CryptoAPIEncryptionVerifier(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        super(cipherAlgorithm, hashAlgorithm, i, i2, chainingMode);
    }

    protected CryptoAPIEncryptionVerifier(CryptoAPIEncryptionVerifier cryptoAPIEncryptionVerifier) {
        super(cryptoAPIEncryptionVerifier);
    }

    public void setSalt(byte[] bArr) {
        super.setSalt(bArr);
    }

    public void setEncryptedVerifier(byte[] bArr) {
        super.setEncryptedVerifier(bArr);
    }

    public void setEncryptedVerifierHash(byte[] bArr) {
        super.setEncryptedVerifierHash(bArr);
    }

    public CryptoAPIEncryptionVerifier copy() {
        return new CryptoAPIEncryptionVerifier(this);
    }
}
