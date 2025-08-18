package org.apache.poi.poifs.crypt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;

public abstract class EncryptionVerifier implements GenericRecord, Duplicatable {
    private ChainingMode chainingMode;
    private CipherAlgorithm cipherAlgorithm;
    private byte[] encryptedKey;
    private byte[] encryptedVerifier;
    private byte[] encryptedVerifierHash;
    private HashAlgorithm hashAlgorithm;
    private byte[] salt;
    private int spinCount;

    public abstract EncryptionVerifier copy();

    protected EncryptionVerifier() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected EncryptionVerifier(org.apache.poi.poifs.crypt.EncryptionVerifier r3) {
        /*
            r2 = this;
            r2.<init>()
            byte[] r0 = r3.salt
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r0 = r1
            goto L_0x0010
        L_0x000a:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0010:
            r2.salt = r0
            byte[] r0 = r3.encryptedVerifier
            if (r0 != 0) goto L_0x0018
            r0 = r1
            goto L_0x001e
        L_0x0018:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x001e:
            r2.encryptedVerifier = r0
            byte[] r0 = r3.encryptedVerifierHash
            if (r0 != 0) goto L_0x0026
            r0 = r1
            goto L_0x002c
        L_0x0026:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x002c:
            r2.encryptedVerifierHash = r0
            byte[] r0 = r3.encryptedKey
            if (r0 != 0) goto L_0x0033
            goto L_0x003a
        L_0x0033:
            java.lang.Object r0 = r0.clone()
            r1 = r0
            byte[] r1 = (byte[]) r1
        L_0x003a:
            r2.encryptedKey = r1
            int r0 = r3.spinCount
            r2.spinCount = r0
            org.apache.poi.poifs.crypt.CipherAlgorithm r0 = r3.cipherAlgorithm
            r2.cipherAlgorithm = r0
            org.apache.poi.poifs.crypt.ChainingMode r0 = r3.chainingMode
            r2.chainingMode = r0
            org.apache.poi.poifs.crypt.HashAlgorithm r3 = r3.hashAlgorithm
            r2.hashAlgorithm = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.EncryptionVerifier.<init>(org.apache.poi.poifs.crypt.EncryptionVerifier):void");
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public byte[] getEncryptedVerifier() {
        return this.encryptedVerifier;
    }

    public byte[] getEncryptedVerifierHash() {
        return this.encryptedVerifierHash;
    }

    public int getSpinCount() {
        return this.spinCount;
    }

    public byte[] getEncryptedKey() {
        return this.encryptedKey;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public ChainingMode getChainingMode() {
        return this.chainingMode;
    }

    public void setSalt(byte[] bArr) {
        this.salt = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void setEncryptedVerifier(byte[] bArr) {
        this.encryptedVerifier = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void setEncryptedVerifierHash(byte[] bArr) {
        this.encryptedVerifierHash = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void setEncryptedKey(byte[] bArr) {
        this.encryptedKey = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void setSpinCount(int i) {
        this.spinCount = i;
    }

    public void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm2) {
        this.cipherAlgorithm = cipherAlgorithm2;
    }

    public void setChainingMode(ChainingMode chainingMode2) {
        this.chainingMode = chainingMode2;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm2) {
        this.hashAlgorithm = hashAlgorithm2;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("salt", new EncryptionVerifier$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("encryptedVerifier", new EncryptionVerifier$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("encryptedVerifierHash", new EncryptionVerifier$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("encryptedKey", new EncryptionVerifier$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("spinCount", new EncryptionVerifier$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("cipherAlgorithm", new EncryptionVerifier$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("chainingMode", new EncryptionVerifier$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("hashAlgorithm", new EncryptionVerifier$$ExternalSyntheticLambda7(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
