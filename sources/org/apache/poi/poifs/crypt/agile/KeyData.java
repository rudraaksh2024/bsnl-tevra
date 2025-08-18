package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.w3c.dom.Element;

public class KeyData {
    private Integer blockSize;
    private CipherAlgorithm cipherAlgorithm;
    private ChainingMode cipherChaining;
    private HashAlgorithm hashAlgorithm;
    private Integer hashSize;
    private Integer keyBits;
    private Integer saltSize;
    private byte[] saltValue;

    public KeyData() {
    }

    public KeyData(Element element) {
        Element tag = EncryptionDocument.getTag(element, "http://schemas.microsoft.com/office/2006/encryption", "keyData");
        if (tag != null) {
            this.saltSize = EncryptionDocument.getIntAttr(tag, "saltSize");
            this.blockSize = EncryptionDocument.getIntAttr(tag, "blockSize");
            this.keyBits = EncryptionDocument.getIntAttr(tag, "keyBits");
            this.hashSize = EncryptionDocument.getIntAttr(tag, "hashSize");
            this.cipherAlgorithm = CipherAlgorithm.fromXmlId(tag.getAttribute("cipherAlgorithm"), this.keyBits.intValue());
            this.cipherChaining = ChainingMode.fromXmlId(tag.getAttribute("cipherChaining"));
            HashAlgorithm fromEcmaId = HashAlgorithm.fromEcmaId(tag.getAttribute("hashAlgorithm"));
            this.hashAlgorithm = fromEcmaId;
            if (this.cipherAlgorithm == null || this.cipherChaining == null || fromEcmaId == null) {
                throw new EncryptedDocumentException("Cipher algorithm, chaining mode or hash algorithm was null");
            }
            this.saltValue = EncryptionDocument.getBinAttr(tag, "saltValue");
            return;
        }
        throw new EncryptedDocumentException("Unable to parse encryption descriptor");
    }

    /* access modifiers changed from: package-private */
    public void write(Element element) {
        Element element2 = (Element) element.appendChild(element.getOwnerDocument().createElementNS("http://schemas.microsoft.com/office/2006/encryption", "keyData"));
        EncryptionDocument.setIntAttr(element2, "saltSize", this.saltSize);
        EncryptionDocument.setIntAttr(element2, "blockSize", this.blockSize);
        EncryptionDocument.setIntAttr(element2, "keyBits", this.keyBits);
        EncryptionDocument.setIntAttr(element2, "hashSize", this.hashSize);
        CipherAlgorithm cipherAlgorithm2 = this.cipherAlgorithm;
        String str = null;
        EncryptionDocument.setAttr(element2, "cipherAlgorithm", cipherAlgorithm2 == null ? null : cipherAlgorithm2.xmlId);
        ChainingMode chainingMode = this.cipherChaining;
        EncryptionDocument.setAttr(element2, "cipherChaining", chainingMode == null ? null : chainingMode.xmlId);
        HashAlgorithm hashAlgorithm2 = this.hashAlgorithm;
        if (hashAlgorithm2 != null) {
            str = hashAlgorithm2.ecmaString;
        }
        EncryptionDocument.setAttr(element2, "hashAlgorithm", str);
        EncryptionDocument.setBinAttr(element2, "saltValue", this.saltValue);
    }

    public Integer getSaltSize() {
        return this.saltSize;
    }

    public void setSaltSize(Integer num) {
        this.saltSize = num;
    }

    public Integer getBlockSize() {
        return this.blockSize;
    }

    public void setBlockSize(Integer num) {
        this.blockSize = num;
    }

    public Integer getKeyBits() {
        return this.keyBits;
    }

    public void setKeyBits(Integer num) {
        this.keyBits = num;
    }

    public Integer getHashSize() {
        return this.hashSize;
    }

    public void setHashSize(Integer num) {
        this.hashSize = num;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm2) {
        this.cipherAlgorithm = cipherAlgorithm2;
    }

    public ChainingMode getCipherChaining() {
        return this.cipherChaining;
    }

    public void setCipherChaining(ChainingMode chainingMode) {
        this.cipherChaining = chainingMode;
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm2) {
        this.hashAlgorithm = hashAlgorithm2;
    }

    public byte[] getSaltValue() {
        return this.saltValue;
    }

    public void setSaltValue(byte[] bArr) {
        this.saltValue = bArr == null ? null : (byte[]) bArr.clone();
    }
}
