package org.apache.poi.poifs.crypt.agile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EncryptionDocument {
    static final String ENC_NS = "http://schemas.microsoft.com/office/2006/encryption";
    private DataIntegrity dataIntegrity;
    private KeyData keyData;
    private final List<KeyEncryptor> keyEncryptors = new ArrayList();

    public void parse(Document document) {
        Element documentElement = document.getDocumentElement();
        if (!ENC_NS.equals(documentElement.getNamespaceURI()) || !"encryption".equals(documentElement.getLocalName())) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor");
        }
        this.keyData = new KeyData(documentElement);
        this.dataIntegrity = new DataIntegrity(documentElement);
        Element tag = getTag(documentElement, ENC_NS, "keyEncryptors");
        if (tag != null) {
            NodeList elementsByTagNameNS = tag.getElementsByTagNameNS(ENC_NS, "keyEncryptor");
            for (int i = 0; i < elementsByTagNameNS.getLength(); i++) {
                this.keyEncryptors.add(new KeyEncryptor((Element) elementsByTagNameNS.item(i)));
            }
            return;
        }
        throw new EncryptedDocumentException("Unable to parse encryption descriptor");
    }

    public void write(Document document) {
        document.setXmlStandalone(true);
        Element element = (Element) document.appendChild(document.createElementNS(ENC_NS, "encryption"));
        KeyData keyData2 = this.keyData;
        if (keyData2 != null) {
            keyData2.write(element);
        }
        DataIntegrity dataIntegrity2 = this.dataIntegrity;
        if (dataIntegrity2 != null) {
            dataIntegrity2.write(element);
        }
        Element element2 = (Element) element.appendChild(document.createElementNS(ENC_NS, "keyEncryptors"));
        boolean z = false;
        boolean z2 = false;
        for (KeyEncryptor next : this.keyEncryptors) {
            next.write(element2);
            z |= next.getPasswordKeyEncryptor() != null;
            z2 |= next.getCertificateKeyEncryptor() != null;
        }
        if (z) {
            element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:p", "http://schemas.microsoft.com/office/2006/keyEncryptor/password");
        }
        if (z2) {
            element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:c", "http://schemas.microsoft.com/office/2006/keyEncryptor/certificate");
        }
    }

    public KeyData getKeyData() {
        return this.keyData;
    }

    public void setKeyData(KeyData keyData2) {
        this.keyData = keyData2;
    }

    public DataIntegrity getDataIntegrity() {
        return this.dataIntegrity;
    }

    public void setDataIntegrity(DataIntegrity dataIntegrity2) {
        this.dataIntegrity = dataIntegrity2;
    }

    public List<KeyEncryptor> getKeyEncryptors() {
        return this.keyEncryptors;
    }

    static Element getTag(Element element, String str, String str2) {
        if (element == null) {
            return null;
        }
        NodeList elementsByTagNameNS = element.getElementsByTagNameNS(str, str2);
        if (elementsByTagNameNS.getLength() > 0) {
            return (Element) elementsByTagNameNS.item(0);
        }
        return null;
    }

    static Integer getIntAttr(Element element, String str) {
        String attribute = element.getAttribute(str);
        if (attribute.isEmpty()) {
            return null;
        }
        return Integer.valueOf(attribute);
    }

    static byte[] getBinAttr(Element element, String str) {
        String attribute = element.getAttribute(str);
        if (attribute.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(attribute);
    }

    static void setIntAttr(Element element, String str, Integer num) {
        setAttr(element, str, num == null ? null : num.toString());
    }

    static void setAttr(Element element, String str, String str2) {
        if (str2 != null) {
            element.setAttribute(str, str2);
        }
    }

    static void setBinAttr(Element element, String str, byte[] bArr) {
        if (bArr != null) {
            setAttr(element, str, Base64.getEncoder().encodeToString(bArr));
        }
    }
}
