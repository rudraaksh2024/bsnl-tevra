package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;

public interface CertIDType extends XmlObject {
    public static final DocumentFactory<CertIDType> Factory;
    public static final SchemaType type;

    DigestAlgAndValueType addNewCertDigest();

    X509IssuerSerialType addNewIssuerSerial();

    DigestAlgAndValueType getCertDigest();

    X509IssuerSerialType getIssuerSerial();

    String getURI();

    boolean isSetURI();

    void setCertDigest(DigestAlgAndValueType digestAlgAndValueType);

    void setIssuerSerial(X509IssuerSerialType x509IssuerSerialType);

    void setURI(String str);

    void unsetURI();

    XmlAnyURI xgetURI();

    void xsetURI(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<CertIDType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "certidtypee64dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
