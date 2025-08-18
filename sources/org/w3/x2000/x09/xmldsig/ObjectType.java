package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ObjectType extends XmlObject {
    public static final DocumentFactory<ObjectType> Factory;
    public static final SchemaType type;

    String getEncoding();

    String getId();

    String getMimeType();

    boolean isSetEncoding();

    boolean isSetId();

    boolean isSetMimeType();

    void setEncoding(String str);

    void setId(String str);

    void setMimeType(String str);

    void unsetEncoding();

    void unsetId();

    void unsetMimeType();

    XmlAnyURI xgetEncoding();

    XmlID xgetId();

    XmlString xgetMimeType();

    void xsetEncoding(XmlAnyURI xmlAnyURI);

    void xsetId(XmlID xmlID);

    void xsetMimeType(XmlString xmlString);

    static {
        DocumentFactory<ObjectType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "objecttypec966type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
