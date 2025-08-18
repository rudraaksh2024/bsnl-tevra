package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CanonicalizationMethodType extends XmlObject {
    public static final DocumentFactory<CanonicalizationMethodType> Factory;
    public static final SchemaType type;

    String getAlgorithm();

    void setAlgorithm(String str);

    XmlAnyURI xgetAlgorithm();

    void xsetAlgorithm(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<CanonicalizationMethodType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "canonicalizationmethodtypeec74type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
