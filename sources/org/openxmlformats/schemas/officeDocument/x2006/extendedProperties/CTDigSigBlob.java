package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDigSigBlob extends XmlObject {
    public static final DocumentFactory<CTDigSigBlob> Factory;
    public static final SchemaType type;

    byte[] getBlob();

    void setBlob(byte[] bArr);

    XmlBase64Binary xgetBlob();

    void xsetBlob(XmlBase64Binary xmlBase64Binary);

    static {
        DocumentFactory<CTDigSigBlob> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdigsigblob73c9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
