package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CertificateKeyEncryptor {
    private byte[] certVerifier;
    private byte[] encryptedKeyValue;
    private byte[] x509Certificate;

    public CertificateKeyEncryptor(Element element) {
        if (element != null) {
            this.encryptedKeyValue = EncryptionDocument.getBinAttr(element, "encryptedKeyValue");
            this.x509Certificate = EncryptionDocument.getBinAttr(element, "X509Certificate");
            this.certVerifier = EncryptionDocument.getBinAttr(element, "certVerifier");
            return;
        }
        throw new EncryptedDocumentException("Unable to parse encryption descriptor");
    }

    /* access modifiers changed from: package-private */
    public void write(Element element) {
        Document ownerDocument = element.getOwnerDocument();
        Element element2 = (Element) element.appendChild(ownerDocument.createElementNS("http://schemas.microsoft.com/office/2006/encryption", "keyEncryptor"));
        element2.setAttribute("uri", "http://schemas.microsoft.com/office/2006/keyEncryptor/certificate");
        Element element3 = (Element) element2.appendChild(ownerDocument.createElementNS("http://schemas.microsoft.com/office/2006/keyEncryptor/certificate", "c:encryptedKey"));
        EncryptionDocument.setBinAttr(element3, "encryptedKeyValue", this.encryptedKeyValue);
        EncryptionDocument.setBinAttr(element3, "x509Certificate", this.x509Certificate);
        EncryptionDocument.setBinAttr(element3, "certVerifier", this.certVerifier);
    }

    public byte[] getEncryptedKeyValue() {
        return this.encryptedKeyValue;
    }

    public void setEncryptedKeyValue(byte[] bArr) {
        this.encryptedKeyValue = bArr;
    }

    public byte[] getX509Certificate() {
        return this.x509Certificate;
    }

    public void setX509Certificate(byte[] bArr) {
        this.x509Certificate = bArr;
    }

    public byte[] getCertVerifier() {
        return this.certVerifier;
    }

    public void setCertVerifier(byte[] bArr) {
        this.certVerifier = bArr;
    }
}
