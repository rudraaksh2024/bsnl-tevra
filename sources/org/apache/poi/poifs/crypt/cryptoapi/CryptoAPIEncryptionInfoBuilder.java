package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianInput;

public class CryptoAPIEncryptionInfoBuilder implements EncryptionInfoBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public void initialize(EncryptionInfo encryptionInfo, LittleEndianInput littleEndianInput) throws IOException {
        littleEndianInput.readInt();
        CryptoAPIEncryptionHeader cryptoAPIEncryptionHeader = new CryptoAPIEncryptionHeader(littleEndianInput);
        encryptionInfo.setHeader(cryptoAPIEncryptionHeader);
        encryptionInfo.setVerifier(new CryptoAPIEncryptionVerifier(littleEndianInput, cryptoAPIEncryptionHeader));
        CryptoAPIDecryptor cryptoAPIDecryptor = new CryptoAPIDecryptor();
        cryptoAPIDecryptor.setEncryptionInfo(encryptionInfo);
        encryptionInfo.setDecryptor(cryptoAPIDecryptor);
        CryptoAPIEncryptor cryptoAPIEncryptor = new CryptoAPIEncryptor();
        cryptoAPIEncryptor.setEncryptionInfo(encryptionInfo);
        encryptionInfo.setEncryptor(cryptoAPIEncryptor);
    }

    public void initialize(EncryptionInfo encryptionInfo, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        if (cipherAlgorithm == null) {
            cipherAlgorithm = CipherAlgorithm.rc4;
        }
        if (hashAlgorithm == null) {
            hashAlgorithm = HashAlgorithm.sha1;
        }
        if (i == -1) {
            i = 40;
        }
        CipherAlgorithm cipherAlgorithm2 = cipherAlgorithm;
        HashAlgorithm hashAlgorithm2 = hashAlgorithm;
        int i3 = i;
        int i4 = i2;
        ChainingMode chainingMode2 = chainingMode;
        encryptionInfo.setHeader(new CryptoAPIEncryptionHeader(cipherAlgorithm2, hashAlgorithm2, i3, i4, chainingMode2));
        encryptionInfo.setVerifier(new CryptoAPIEncryptionVerifier(cipherAlgorithm2, hashAlgorithm2, i3, i4, chainingMode2));
        CryptoAPIDecryptor cryptoAPIDecryptor = new CryptoAPIDecryptor();
        cryptoAPIDecryptor.setEncryptionInfo(encryptionInfo);
        encryptionInfo.setDecryptor(cryptoAPIDecryptor);
        CryptoAPIEncryptor cryptoAPIEncryptor = new CryptoAPIEncryptor();
        cryptoAPIEncryptor.setEncryptionInfo(encryptionInfo);
        encryptionInfo.setEncryptor(cryptoAPIEncryptor);
    }
}
