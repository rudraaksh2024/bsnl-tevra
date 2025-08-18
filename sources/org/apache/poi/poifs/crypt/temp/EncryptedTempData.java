package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.TempFile;

public class EncryptedTempData {
    private static final Logger LOG = LogManager.getLogger((Class<?>) EncryptedTempData.class);
    private static final String PADDING = "PKCS5Padding";
    private static final CipherAlgorithm cipherAlgorithm = CipherAlgorithm.aes128;
    private final byte[] ivBytes;
    private CountingOutputStream outputStream;
    private final SecretKeySpec skeySpec;
    private final File tempFile = TempFile.createTempFile("poi-temp-data", ".tmp");

    public EncryptedTempData() throws IOException {
        byte[] bArr = new byte[16];
        this.ivBytes = bArr;
        byte[] bArr2 = new byte[16];
        RandomSingleton.getInstance().nextBytes(bArr);
        RandomSingleton.getInstance().nextBytes(bArr2);
        this.skeySpec = new SecretKeySpec(bArr2, cipherAlgorithm.jceId);
    }

    public OutputStream getOutputStream() throws IOException {
        CountingOutputStream countingOutputStream = new CountingOutputStream(new CipherOutputStream(new FileOutputStream(this.tempFile), CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 1, PADDING)));
        this.outputStream = countingOutputStream;
        return countingOutputStream;
    }

    public InputStream getInputStream() throws IOException {
        return new CipherInputStream(new FileInputStream(this.tempFile), CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 2, PADDING));
    }

    public long getByteCount() {
        CountingOutputStream countingOutputStream = this.outputStream;
        if (countingOutputStream == null) {
            return 0;
        }
        return countingOutputStream.getByteCount();
    }

    public void dispose() {
        if (!this.tempFile.delete()) {
            LogBuilder atWarn = LOG.atWarn();
            File file = this.tempFile;
            file.getClass();
            atWarn.log("{} can't be removed (or was already removed).", (Supplier<?>[]) new Supplier[]{new EncryptedTempData$$ExternalSyntheticLambda0(file)});
        }
    }
}
