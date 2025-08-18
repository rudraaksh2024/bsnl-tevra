package org.w3.x2000.x09.xmldsig;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface X509IssuerSerialType extends XmlObject {
    public static final DocumentFactory<X509IssuerSerialType> Factory;
    public static final SchemaType type;

    String getX509IssuerName();

    BigInteger getX509SerialNumber();

    void setX509IssuerName(String str);

    void setX509SerialNumber(BigInteger bigInteger);

    XmlString xgetX509IssuerName();

    XmlInteger xgetX509SerialNumber();

    void xsetX509IssuerName(XmlString xmlString);

    void xsetX509SerialNumber(XmlInteger xmlInteger);

    static {
        DocumentFactory<X509IssuerSerialType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "x509issuerserialtype7eb2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
