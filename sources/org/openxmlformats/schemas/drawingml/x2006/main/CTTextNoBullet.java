package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextNoBullet extends XmlObject {
    public static final DocumentFactory<CTTextNoBullet> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextNoBullet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextnobulleta08btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
