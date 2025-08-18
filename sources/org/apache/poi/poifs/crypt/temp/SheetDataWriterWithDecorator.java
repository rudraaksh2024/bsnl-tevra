package org.apache.poi.poifs.crypt.temp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.xssf.streaming.SheetDataWriter;

public class SheetDataWriterWithDecorator extends SheetDataWriter {
    static final CipherAlgorithm cipherAlgorithm = CipherAlgorithm.aes128;
    byte[] ivBytes;
    SecretKeySpec skeySpec;

    /* access modifiers changed from: package-private */
    public void init() {
        if (this.skeySpec == null) {
            this.ivBytes = new byte[16];
            byte[] bArr = new byte[16];
            RandomSingleton.getInstance().nextBytes(this.ivBytes);
            RandomSingleton.getInstance().nextBytes(bArr);
            this.skeySpec = new SecretKeySpec(bArr, cipherAlgorithm.jceId);
        }
    }

    /* access modifiers changed from: protected */
    public OutputStream decorateOutputStream(FileOutputStream fileOutputStream) {
        init();
        return new CipherOutputStream(fileOutputStream, CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 1, "PKCS5Padding"));
    }

    /* access modifiers changed from: protected */
    public InputStream decorateInputStream(FileInputStream fileInputStream) {
        return new CipherInputStream(fileInputStream, CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 2, "PKCS5Padding"));
    }
}
