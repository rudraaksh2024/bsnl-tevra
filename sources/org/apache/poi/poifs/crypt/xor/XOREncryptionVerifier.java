package org.apache.poi.poifs.crypt.xor;

import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

public class XOREncryptionVerifier extends EncryptionVerifier implements EncryptionRecord {
    protected XOREncryptionVerifier() {
        setEncryptedKey(new byte[2]);
        setEncryptedVerifier(new byte[2]);
    }

    protected XOREncryptionVerifier(LittleEndianInput littleEndianInput) {
        byte[] bArr = new byte[2];
        littleEndianInput.readFully(bArr);
        setEncryptedKey(bArr);
        byte[] bArr2 = new byte[2];
        littleEndianInput.readFully(bArr2);
        setEncryptedVerifier(bArr2);
    }

    protected XOREncryptionVerifier(XOREncryptionVerifier xOREncryptionVerifier) {
        super(xOREncryptionVerifier);
    }

    public void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
        littleEndianByteArrayOutputStream.write(getEncryptedKey());
        littleEndianByteArrayOutputStream.write(getEncryptedVerifier());
    }

    public XOREncryptionVerifier copy() {
        return new XOREncryptionVerifier(this);
    }

    public final void setEncryptedVerifier(byte[] bArr) {
        super.setEncryptedVerifier(bArr);
    }

    public final void setEncryptedKey(byte[] bArr) {
        super.setEncryptedKey(bArr);
    }
}
