package org.apache.poi.poifs.crypt.binaryrc4;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

public class BinaryRC4EncryptionVerifier extends EncryptionVerifier implements EncryptionRecord {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    protected BinaryRC4EncryptionVerifier() {
        setSpinCount(-1);
        setCipherAlgorithm(CipherAlgorithm.rc4);
        setChainingMode((ChainingMode) null);
        setEncryptedKey((byte[]) null);
        setHashAlgorithm(HashAlgorithm.md5);
    }

    protected BinaryRC4EncryptionVerifier(LittleEndianInput littleEndianInput) {
        byte[] bArr = new byte[16];
        littleEndianInput.readFully(bArr);
        setSalt(bArr);
        byte[] bArr2 = new byte[16];
        littleEndianInput.readFully(bArr2);
        setEncryptedVerifier(bArr2);
        byte[] bArr3 = new byte[16];
        littleEndianInput.readFully(bArr3);
        setEncryptedVerifierHash(bArr3);
        setSpinCount(-1);
        setCipherAlgorithm(CipherAlgorithm.rc4);
        setChainingMode((ChainingMode) null);
        setEncryptedKey((byte[]) null);
        setHashAlgorithm(HashAlgorithm.md5);
    }

    protected BinaryRC4EncryptionVerifier(BinaryRC4EncryptionVerifier binaryRC4EncryptionVerifier) {
        super(binaryRC4EncryptionVerifier);
    }

    public void setSalt(byte[] bArr) {
        if (bArr == null || bArr.length != 16) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setSalt(bArr);
    }

    public void setEncryptedVerifier(byte[] bArr) {
        super.setEncryptedVerifier(bArr);
    }

    public void setEncryptedVerifierHash(byte[] bArr) {
        super.setEncryptedVerifierHash(bArr);
    }

    public void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
        littleEndianByteArrayOutputStream.write(getSalt());
        littleEndianByteArrayOutputStream.write(getEncryptedVerifier());
        littleEndianByteArrayOutputStream.write(getEncryptedVerifierHash());
    }

    public BinaryRC4EncryptionVerifier copy() {
        return new BinaryRC4EncryptionVerifier(this);
    }
}
