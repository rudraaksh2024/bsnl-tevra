package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTOfficeArtExtension extends XmlObject {
    public static final DocumentFactory<CTOfficeArtExtension> Factory;
    public static final SchemaType type;

    String getUri();

    void setUri(String str);

    XmlToken xgetUri();

    void xsetUri(XmlToken xmlToken);

    static {
        DocumentFactory<CTOfficeArtExtension> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctofficeartextension8e53type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
