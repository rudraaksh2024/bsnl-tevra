package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class KeyEncryptor {
    static final String CERT_NS = "http://schemas.microsoft.com/office/2006/keyEncryptor/certificate";
    static final String PASS_NS = "http://schemas.microsoft.com/office/2006/keyEncryptor/password";
    private CertificateKeyEncryptor certificateKeyEncryptor;
    private PasswordKeyEncryptor passwordKeyEncryptor;

    public KeyEncryptor() {
    }

    public KeyEncryptor(Element element) {
        if (element != null) {
            NodeList elementsByTagNameNS = element.getElementsByTagNameNS("*", "encryptedKey");
            for (int i = 0; i < elementsByTagNameNS.getLength(); i++) {
                Element element2 = (Element) elementsByTagNameNS.item(i);
                String namespaceURI = element2.getNamespaceURI();
                if (PASS_NS.equals(namespaceURI)) {
                    this.passwordKeyEncryptor = new PasswordKeyEncryptor(element2);
                } else if (CERT_NS.equals(namespaceURI)) {
                    this.certificateKeyEncryptor = new CertificateKeyEncryptor(element2);
                }
            }
            return;
        }
        throw new EncryptedDocumentException("Unable to parse encryption descriptor");
    }

    /* access modifiers changed from: package-private */
    public void write(Element element) {
        PasswordKeyEncryptor passwordKeyEncryptor2 = this.passwordKeyEncryptor;
        if (passwordKeyEncryptor2 != null) {
            passwordKeyEncryptor2.write(element);
            return;
        }
        CertificateKeyEncryptor certificateKeyEncryptor2 = this.certificateKeyEncryptor;
        if (certificateKeyEncryptor2 != null) {
            certificateKeyEncryptor2.write(element);
        }
    }

    public PasswordKeyEncryptor getPasswordKeyEncryptor() {
        return this.passwordKeyEncryptor;
    }

    public void setPasswordKeyEncryptor(PasswordKeyEncryptor passwordKeyEncryptor2) {
        this.passwordKeyEncryptor = passwordKeyEncryptor2;
    }

    public CertificateKeyEncryptor getCertificateKeyEncryptor() {
        return this.certificateKeyEncryptor;
    }

    public void setCertificateKeyEncryptor(CertificateKeyEncryptor certificateKeyEncryptor2) {
        this.certificateKeyEncryptor = certificateKeyEncryptor2;
    }
}
