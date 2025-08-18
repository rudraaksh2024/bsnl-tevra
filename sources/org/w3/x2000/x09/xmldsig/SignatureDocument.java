package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignatureDocument extends XmlObject {
    public static final DocumentFactory<SignatureDocument> Factory;
    public static final SchemaType type;

    SignatureType addNewSignature();

    SignatureType getSignature();

    void setSignature(SignatureType signatureType);

    static {
        DocumentFactory<SignatureDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signature5269doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
