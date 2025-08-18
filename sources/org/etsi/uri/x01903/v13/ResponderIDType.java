package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ResponderIDType extends XmlObject {
    public static final DocumentFactory<ResponderIDType> Factory;
    public static final SchemaType type;

    byte[] getByKey();

    String getByName();

    boolean isSetByKey();

    boolean isSetByName();

    void setByKey(byte[] bArr);

    void setByName(String str);

    void unsetByKey();

    void unsetByName();

    XmlBase64Binary xgetByKey();

    XmlString xgetByName();

    void xsetByKey(XmlBase64Binary xmlBase64Binary);

    void xsetByName(XmlString xmlString);

    static {
        DocumentFactory<ResponderIDType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "responderidtype55b9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
