package org.etsi.uri.x01903.v13;

import java.math.BigInteger;
import java.util.Calendar;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CRLIdentifierType extends XmlObject {
    public static final DocumentFactory<CRLIdentifierType> Factory;
    public static final SchemaType type;

    Calendar getIssueTime();

    String getIssuer();

    BigInteger getNumber();

    String getURI();

    boolean isSetNumber();

    boolean isSetURI();

    void setIssueTime(Calendar calendar);

    void setIssuer(String str);

    void setNumber(BigInteger bigInteger);

    void setURI(String str);

    void unsetNumber();

    void unsetURI();

    XmlDateTime xgetIssueTime();

    XmlString xgetIssuer();

    XmlInteger xgetNumber();

    XmlAnyURI xgetURI();

    void xsetIssueTime(XmlDateTime xmlDateTime);

    void xsetIssuer(XmlString xmlString);

    void xsetNumber(XmlInteger xmlInteger);

    void xsetURI(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<CRLIdentifierType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "crlidentifiertypeb702type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
