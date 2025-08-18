package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTGraphicalObjectData extends XmlObject {
    public static final DocumentFactory<CTGraphicalObjectData> Factory;
    public static final SchemaType type;

    String getUri();

    void setUri(String str);

    XmlToken xgetUri();

    void xsetUri(XmlToken xmlToken);

    static {
        DocumentFactory<CTGraphicalObjectData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgraphicalobjectdata66adtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
