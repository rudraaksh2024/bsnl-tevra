package org.apache.poi.poifs.crypt.standard;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

public class StandardEncryptionVerifier extends EncryptionVerifier implements EncryptionRecord {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SPIN_COUNT = 50000;
    private final int verifierHashSize;

    protected StandardEncryptionVerifier(LittleEndianInput littleEndianInput, StandardEncryptionHeader standardEncryptionHeader) {
        if (littleEndianInput.readInt() == 16) {
            byte[] bArr = new byte[16];
            littleEndianInput.readFully(bArr);
            setSalt(bArr);
            byte[] bArr2 = new byte[16];
            littleEndianInput.readFully(bArr2);
            setEncryptedVerifier(bArr2);
            this.verifierHashSize = littleEndianInput.readInt();
            byte[] bArr3 = new byte[standardEncryptionHeader.getCipherAlgorithm().encryptedVerifierHashLength];
            littleEndianInput.readFully(bArr3);
            setEncryptedVerifierHash(bArr3);
            setSpinCount(SPIN_COUNT);
            setCipherAlgorithm(standardEncryptionHeader.getCipherAlgorithm());
            setChainingMode(standardEncryptionHeader.getChainingMode());
            setEncryptedKey((byte[]) null);
            setHashAlgorithm(standardEncryptionHeader.getHashAlgorithm());
            return;
        }
        throw new RuntimeException("Salt size != 16 !?");
    }

    protected StandardEncryptionVerifier(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        setCipherAlgorithm(cipherAlgorithm);
        setHashAlgorithm(hashAlgorithm);
        setChainingMode(chainingMode);
        setSpinCount(SPIN_COUNT);
        this.verifierHashSize = hashAlgorithm.hashSize;
    }

    protected StandardEncryptionVerifier(StandardEncryptionVerifier standardEncryptionVerifier) {
        super(standardEncryptionVerifier);
        this.verifierHashSize = standardEncryptionVerifier.verifierHashSize;
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
        byte[] salt = getSalt();
        littleEndianByteArrayOutputStream.writeInt(salt.length);
        littleEndianByteArrayOutputStream.write(salt);
        littleEndianByteArrayOutputStream.write(getEncryptedVerifier());
        littleEndianByteArrayOutputStream.writeInt(20);
        littleEndianByteArrayOutputStream.write(getEncryptedVerifierHash());
    }

    public int getVerifierHashSize() {
        return this.verifierHashSize;
    }

    public StandardEncryptionVerifier copy() {
        return new StandardEncryptionVerifier(this);
    }
}
