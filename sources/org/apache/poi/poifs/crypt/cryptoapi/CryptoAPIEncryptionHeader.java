package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CipherProvider;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionHeader;
import org.apache.poi.util.LittleEndianInput;

public class CryptoAPIEncryptionHeader extends StandardEncryptionHeader {
    public CryptoAPIEncryptionHeader(LittleEndianInput littleEndianInput) throws IOException {
        super(littleEndianInput);
    }

    protected CryptoAPIEncryptionHeader(CryptoAPIEncryptionHeader cryptoAPIEncryptionHeader) {
        super((StandardEncryptionHeader) cryptoAPIEncryptionHeader);
    }

    protected CryptoAPIEncryptionHeader(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        super(cipherAlgorithm, hashAlgorithm, i, i2, chainingMode);
    }

    public void setKeySize(int i) {
        int[] iArr = getCipherAlgorithm().allowedKeySize;
        int length = iArr.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (iArr[i2] == i) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            super.setKeySize(i);
            if (i > 40) {
                setCspName("Microsoft Enhanced Cryptographic Provider v1.0");
            } else {
                setCspName(CipherProvider.rc4.cipherProviderName);
            }
        } else {
            throw new EncryptedDocumentException("invalid keysize " + i + " for cipher algorithm " + getCipherAlgorithm());
        }
    }

    public CryptoAPIEncryptionHeader copy() {
        return new CryptoAPIEncryptionHeader(this);
    }
}
