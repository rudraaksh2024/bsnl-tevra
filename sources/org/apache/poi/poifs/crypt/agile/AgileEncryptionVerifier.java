package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;

public class AgileEncryptionVerifier extends EncryptionVerifier {
    private int blockSize;
    private int keyBits;

    public AgileEncryptionVerifier(String str) {
        this(AgileEncryptionInfoBuilder.parseDescriptor(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0011 A[LOOP:0: B:1:0x0011->B:4:0x0021, LOOP_START, PHI: r0 
      PHI: (r0v2 org.apache.poi.poifs.crypt.agile.PasswordKeyEncryptor) = (r0v1 org.apache.poi.poifs.crypt.agile.PasswordKeyEncryptor), (r0v11 org.apache.poi.poifs.crypt.agile.PasswordKeyEncryptor) binds: [B:0:0x0000, B:4:0x0021] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AgileEncryptionVerifier(org.apache.poi.poifs.crypt.agile.EncryptionDocument r4) {
        /*
            r3 = this;
            r3.<init>()
            r0 = -1
            r3.keyBits = r0
            r3.blockSize = r0
            java.util.List r4 = r4.getKeyEncryptors()
            java.util.Iterator r4 = r4.iterator()
            r0 = 0
        L_0x0011:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0023
            java.lang.Object r0 = r4.next()
            org.apache.poi.poifs.crypt.agile.KeyEncryptor r0 = (org.apache.poi.poifs.crypt.agile.KeyEncryptor) r0
            org.apache.poi.poifs.crypt.agile.PasswordKeyEncryptor r0 = r0.getPasswordKeyEncryptor()
            if (r0 == 0) goto L_0x0011
        L_0x0023:
            if (r0 == 0) goto L_0x00f6
            org.apache.poi.poifs.crypt.CipherAlgorithm r4 = r0.getCipherAlgorithm()
            r3.setCipherAlgorithm(r4)
            java.lang.Integer r4 = r0.getKeyBits()
            int r4 = r4.intValue()
            r3.setKeySize(r4)
            java.lang.Integer r4 = r0.getBlockSize()
            int r4 = r4.intValue()
            r3.setBlockSize(r4)
            java.lang.Integer r4 = r0.getHashSize()
            int r4 = r4.intValue()
            org.apache.poi.poifs.crypt.HashAlgorithm r1 = r0.getHashAlgorithm()
            r3.setHashAlgorithm(r1)
            org.apache.poi.poifs.crypt.HashAlgorithm r1 = r3.getHashAlgorithm()
            int r1 = r1.hashSize
            if (r1 != r4) goto L_0x00cd
            java.lang.Integer r4 = r0.getSpinCount()
            if (r4 == 0) goto L_0x0066
            int r4 = r4.intValue()
            r3.setSpinCount(r4)
        L_0x0066:
            byte[] r4 = r0.getEncryptedVerifierHashInput()
            r3.setEncryptedVerifier(r4)
            byte[] r4 = r0.getSaltValue()
            r3.setSalt(r4)
            byte[] r4 = r0.getEncryptedKeyValue()
            r3.setEncryptedKey(r4)
            byte[] r4 = r0.getEncryptedVerifierHashValue()
            r3.setEncryptedVerifierHash(r4)
            java.lang.Integer r4 = r0.getSaltSize()
            if (r4 == 0) goto L_0x00c5
            int r4 = r4.intValue()
            byte[] r1 = r3.getSalt()
            int r1 = r1.length
            if (r4 != r1) goto L_0x00c5
            org.apache.poi.poifs.crypt.ChainingMode r4 = r0.getCipherChaining()
            r3.setChainingMode(r4)
            org.apache.poi.poifs.crypt.ChainingMode r3 = r0.getCipherChaining()
            org.apache.poi.poifs.crypt.ChainingMode r4 = org.apache.poi.poifs.crypt.ChainingMode.cbc
            if (r3 == r4) goto L_0x00c4
            org.apache.poi.poifs.crypt.ChainingMode r3 = r0.getCipherChaining()
            org.apache.poi.poifs.crypt.ChainingMode r4 = org.apache.poi.poifs.crypt.ChainingMode.cfb
            if (r3 != r4) goto L_0x00ab
            goto L_0x00c4
        L_0x00ab:
            org.apache.poi.EncryptedDocumentException r3 = new org.apache.poi.EncryptedDocumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported chaining mode - "
            r4.<init>(r1)
            org.apache.poi.poifs.crypt.ChainingMode r0 = r0.getCipherChaining()
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            r3.<init>((java.lang.String) r4)
            throw r3
        L_0x00c4:
            return
        L_0x00c5:
            org.apache.poi.EncryptedDocumentException r3 = new org.apache.poi.EncryptedDocumentException
            java.lang.String r4 = "Invalid salt size"
            r3.<init>((java.lang.String) r4)
            throw r3
        L_0x00cd:
            org.apache.poi.EncryptedDocumentException r3 = new org.apache.poi.EncryptedDocumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unsupported hash algorithm: "
            r1.<init>(r2)
            org.apache.poi.poifs.crypt.HashAlgorithm r0 = r0.getHashAlgorithm()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = " @ "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r0 = " bytes"
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            r3.<init>((java.lang.String) r4)
            throw r3
        L_0x00f6:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "encryptedKey not set"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.agile.AgileEncryptionVerifier.<init>(org.apache.poi.poifs.crypt.agile.EncryptionDocument):void");
    }

    public AgileEncryptionVerifier(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        this.keyBits = -1;
        this.blockSize = -1;
        setCipherAlgorithm(cipherAlgorithm);
        setHashAlgorithm(hashAlgorithm);
        setChainingMode(chainingMode);
        setKeySize(i);
        setBlockSize(i2);
        setSpinCount(BZip2Constants.BASEBLOCKSIZE);
    }

    public AgileEncryptionVerifier(AgileEncryptionVerifier agileEncryptionVerifier) {
        super(agileEncryptionVerifier);
        this.keyBits = -1;
        this.blockSize = -1;
        this.keyBits = agileEncryptionVerifier.keyBits;
        this.blockSize = agileEncryptionVerifier.blockSize;
    }

    public void setSalt(byte[] bArr) {
        if (bArr == null || bArr.length != getCipherAlgorithm().blockSize) {
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

    public void setEncryptedKey(byte[] bArr) {
        super.setEncryptedKey(bArr);
    }

    public AgileEncryptionVerifier copy() {
        return new AgileEncryptionVerifier(this);
    }

    public int getKeySize() {
        return this.keyBits;
    }

    public int getBlockSize() {
        return this.blockSize;
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

    public void setBlockSize(int i) {
        this.blockSize = i;
    }

    public final void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        super.setCipherAlgorithm(cipherAlgorithm);
        if (cipherAlgorithm.allowedKeySize.length == 1) {
            setKeySize(cipherAlgorithm.defaultKeySize);
        }
    }
}
