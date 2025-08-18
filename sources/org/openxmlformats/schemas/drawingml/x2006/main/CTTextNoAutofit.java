package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextNoAutofit extends XmlObject {
    public static final DocumentFactory<CTTextNoAutofit> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextNoAutofit> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextnoautofit1045type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
