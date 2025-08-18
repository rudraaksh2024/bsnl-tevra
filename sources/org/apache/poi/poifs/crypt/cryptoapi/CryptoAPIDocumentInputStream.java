package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import kotlin.UByte;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.util.Internal;

@Internal
class CryptoAPIDocumentInputStream extends ByteArrayInputStream {
    private Cipher cipher;
    private final CryptoAPIDecryptor decryptor;
    private byte[] oneByte = {0};

    public void seek(int i) {
        if (i <= this.count) {
            this.pos = i;
            this.mark = i;
            return;
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public void setBlock(int i) throws GeneralSecurityException {
        this.cipher = this.decryptor.initCipherForBlock(this.cipher, i);
    }

    public synchronized int read() {
        int read = super.read();
        if (read == -1) {
            return -1;
        }
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) read;
        try {
            this.cipher.update(bArr, 0, 1, bArr);
            return this.oneByte[0] & UByte.MAX_VALUE;
        } catch (ShortBufferException e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        int read = super.read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        try {
            this.cipher.update(bArr, i, read, bArr, i);
            return read;
        } catch (ShortBufferException e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    public CryptoAPIDocumentInputStream(CryptoAPIDecryptor cryptoAPIDecryptor, byte[] bArr) throws GeneralSecurityException {
        super(bArr);
        this.decryptor = cryptoAPIDecryptor;
        this.cipher = cryptoAPIDecryptor.initCipherForBlock((Cipher) null, 0);
    }
}
