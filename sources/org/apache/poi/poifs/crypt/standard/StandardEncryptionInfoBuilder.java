package org.apache.poi.poifs.crypt.standard;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianInput;

public class StandardEncryptionInfoBuilder implements EncryptionInfoBuilder {
    public void initialize(EncryptionInfo encryptionInfo, LittleEndianInput littleEndianInput) throws IOException {
        littleEndianInput.readInt();
        StandardEncryptionHeader standardEncryptionHeader = new StandardEncryptionHeader(littleEndianInput);
        encryptionInfo.setHeader(standardEncryptionHeader);
        encryptionInfo.setVerifier(new StandardEncryptionVerifier(littleEndianInput, standardEncryptionHeader));
        if (encryptionInfo.getVersionMinor() != 2) {
            return;
        }
        if (encryptionInfo.getVersionMajor() == 3 || encryptionInfo.getVersionMajor() == 4) {
            StandardDecryptor standardDecryptor = new StandardDecryptor();
            standardDecryptor.setEncryptionInfo(encryptionInfo);
            encryptionInfo.setDecryptor(standardDecryptor);
        }
    }

    public void initialize(EncryptionInfo encryptionInfo, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        if (cipherAlgorithm == null) {
            cipherAlgorithm = CipherAlgorithm.aes128;
        }
        if (cipherAlgorithm == CipherAlgorithm.aes128 || cipherAlgorithm == CipherAlgorithm.aes192 || cipherAlgorithm == CipherAlgorithm.aes256) {
            if (hashAlgorithm == null) {
                hashAlgorithm = HashAlgorithm.sha1;
            }
            if (hashAlgorithm == HashAlgorithm.sha1) {
                if (chainingMode == null) {
                    chainingMode = ChainingMode.ecb;
                }
                if (chainingMode == ChainingMode.ecb) {
                    if (i == -1) {
                        i = cipherAlgorithm.defaultKeySize;
                    }
                    if (i2 == -1) {
                        i2 = cipherAlgorithm.blockSize;
                    }
                    int[] iArr = cipherAlgorithm.allowedKeySize;
                    int length = iArr.length;
                    boolean z = false;
                    for (int i3 = 0; i3 < length; i3++) {
                        z |= iArr[i3] == i;
                    }
                    if (z) {
                        CipherAlgorithm cipherAlgorithm2 = cipherAlgorithm;
                        HashAlgorithm hashAlgorithm2 = hashAlgorithm;
                        int i4 = i;
                        int i5 = i2;
                        ChainingMode chainingMode2 = chainingMode;
                        encryptionInfo.setHeader(new StandardEncryptionHeader(cipherAlgorithm2, hashAlgorithm2, i4, i5, chainingMode2));
                        encryptionInfo.setVerifier(new StandardEncryptionVerifier(cipherAlgorithm2, hashAlgorithm2, i4, i5, chainingMode2));
                        StandardDecryptor standardDecryptor = new StandardDecryptor();
                        standardDecryptor.setEncryptionInfo(encryptionInfo);
                        encryptionInfo.setDecryptor(standardDecryptor);
                        StandardEncryptor standardEncryptor = new StandardEncryptor();
                        standardEncryptor.setEncryptionInfo(encryptionInfo);
                        encryptionInfo.setEncryptor(standardEncryptor);
                        return;
                    }
                    throw new EncryptedDocumentException("KeySize " + i + " not allowed for Cipher " + cipherAlgorithm);
                }
                throw new EncryptedDocumentException("Standard encryption only supports ECB chaining.");
            }
            throw new EncryptedDocumentException("Standard encryption only supports SHA-1.");
        }
        throw new EncryptedDocumentException("Standard encryption only supports AES128/192/256.");
    }
}
