package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface EncapsulatedPKIDataType extends XmlBase64Binary {
    public static final DocumentFactory<EncapsulatedPKIDataType> Factory;
    public static final SchemaType type;

    String getEncoding();

    String getId();

    boolean isSetEncoding();

    boolean isSetId();

    void setEncoding(String str);

    void setId(String str);

    void unsetEncoding();

    void unsetId();

    XmlAnyURI xgetEncoding();

    XmlID xgetId();

    void xsetEncoding(XmlAnyURI xmlAnyURI);

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<EncapsulatedPKIDataType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "encapsulatedpkidatatype4081type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
