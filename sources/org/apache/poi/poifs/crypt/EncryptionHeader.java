package org.apache.poi.poifs.crypt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;

public abstract class EncryptionHeader implements GenericRecord, Duplicatable {
    private int blockSize;
    private ChainingMode chainingMode;
    private CipherAlgorithm cipherAlgorithm;
    private String cspName;
    private int flags;
    private HashAlgorithm hashAlgorithm;
    private int keyBits;
    private byte[] keySalt;
    private CipherProvider providerType;
    private int sizeExtra;

    public abstract EncryptionHeader copy();

    protected EncryptionHeader() {
    }

    protected EncryptionHeader(EncryptionHeader encryptionHeader) {
        this.flags = encryptionHeader.flags;
        this.sizeExtra = encryptionHeader.sizeExtra;
        this.cipherAlgorithm = encryptionHeader.cipherAlgorithm;
        this.hashAlgorithm = encryptionHeader.hashAlgorithm;
        this.keyBits = encryptionHeader.keyBits;
        this.blockSize = encryptionHeader.blockSize;
        this.providerType = encryptionHeader.providerType;
        this.chainingMode = encryptionHeader.chainingMode;
        byte[] bArr = encryptionHeader.keySalt;
        this.keySalt = bArr == null ? null : (byte[]) bArr.clone();
        this.cspName = encryptionHeader.cspName;
    }

    public ChainingMode getChainingMode() {
        return this.chainingMode;
    }

    /* access modifiers changed from: protected */
    public void setChainingMode(ChainingMode chainingMode2) {
        this.chainingMode = chainingMode2;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public int getSizeExtra() {
        return this.sizeExtra;
    }

    public void setSizeExtra(int i) {
        this.sizeExtra = i;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm2) {
        this.cipherAlgorithm = cipherAlgorithm2;
        if (cipherAlgorithm2.allowedKeySize.length == 1) {
            setKeySize(cipherAlgorithm2.defaultKeySize);
        }
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm2) {
        this.hashAlgorithm = hashAlgorithm2;
    }

    public int getKeySize() {
        return this.keyBits;
    }

    public void setKeySize(int i) {
        this.keyBits = i;
        int[] iArr = getCipherAlgorithm().allowedKeySize;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            if (iArr[i2] != i) {
                i2++;
            } else {
                return;
            }
        }
        throw new EncryptedDocumentException("KeySize " + i + " not allowed for cipher " + getCipherAlgorithm());
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public void setBlockSize(int i) {
        this.blockSize = i;
    }

    public byte[] getKeySalt() {
        return this.keySalt;
    }

    public void setKeySalt(byte[] bArr) {
        this.keySalt = bArr == null ? null : (byte[]) bArr.clone();
    }

    public CipherProvider getCipherProvider() {
        return this.providerType;
    }

    public void setCipherProvider(CipherProvider cipherProvider) {
        this.providerType = cipherProvider;
    }

    public String getCspName() {
        return this.cspName;
    }

    public void setCspName(String str) {
        this.cspName = str;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("flags", new EncryptionHeader$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("sizeExtra", new EncryptionHeader$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("cipherAlgorithm", new EncryptionHeader$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("hashAlgorithm", new EncryptionHeader$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("keyBits", new EncryptionHeader$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("blockSize", new EncryptionHeader$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("providerType", new EncryptionHeader$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("chainingMode", new EncryptionHeader$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("keySalt", new EncryptionHeader$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("cspName", new EncryptionHeader$$ExternalSyntheticLambda9(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
