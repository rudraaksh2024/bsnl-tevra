package com.microsoft.schemas.office.x2006.digsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignatureInfoV1Document extends XmlObject {
    public static final DocumentFactory<SignatureInfoV1Document> Factory;
    public static final SchemaType type;

    CTSignatureInfoV1 addNewSignatureInfoV1();

    CTSignatureInfoV1 getSignatureInfoV1();

    void setSignatureInfoV1(CTSignatureInfoV1 cTSignatureInfoV1);

    static {
        DocumentFactory<SignatureInfoV1Document> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signatureinfov14a6bdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
