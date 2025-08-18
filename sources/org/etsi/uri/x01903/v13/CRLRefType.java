package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CRLRefType extends XmlObject {
    public static final DocumentFactory<CRLRefType> Factory;
    public static final SchemaType type;

    CRLIdentifierType addNewCRLIdentifier();

    DigestAlgAndValueType addNewDigestAlgAndValue();

    CRLIdentifierType getCRLIdentifier();

    DigestAlgAndValueType getDigestAlgAndValue();

    boolean isSetCRLIdentifier();

    void setCRLIdentifier(CRLIdentifierType cRLIdentifierType);

    void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType);

    void unsetCRLIdentifier();

    static {
        DocumentFactory<CRLRefType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "crlreftype4444type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
