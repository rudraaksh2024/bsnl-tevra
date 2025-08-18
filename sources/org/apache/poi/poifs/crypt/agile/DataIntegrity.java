package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Element;

public class DataIntegrity {
    private byte[] encryptedHmacKey;
    private byte[] encryptedHmacValue;

    public DataIntegrity() {
    }

    public DataIntegrity(Element element) {
        Element tag = EncryptionDocument.getTag(element, "http://schemas.microsoft.com/office/2006/encryption", "dataIntegrity");
        if (tag != null) {
            this.encryptedHmacKey = EncryptionDocument.getBinAttr(tag, "encryptedHmacKey");
            this.encryptedHmacValue = EncryptionDocument.getBinAttr(tag, "encryptedHmacValue");
            return;
        }
        throw new EncryptedDocumentException("Unable to parse encryption descriptor");
    }

    /* access modifiers changed from: package-private */
    public void write(Element element) {
        Element element2 = (Element) element.appendChild(element.getOwnerDocument().createElementNS("http://schemas.microsoft.com/office/2006/encryption", "dataIntegrity"));
        EncryptionDocument.setBinAttr(element2, "encryptedHmacKey", this.encryptedHmacKey);
        EncryptionDocument.setBinAttr(element2, "encryptedHmacValue", this.encryptedHmacValue);
    }

    public byte[] getEncryptedHmacKey() {
        return this.encryptedHmacKey;
    }

    public void setEncryptedHmacKey(byte[] bArr) {
        this.encryptedHmacKey = bArr;
    }

    public byte[] getEncryptedHmacValue() {
        return this.encryptedHmacValue;
    }

    public void setEncryptedHmacValue(byte[] bArr) {
        this.encryptedHmacValue = bArr;
    }
}
