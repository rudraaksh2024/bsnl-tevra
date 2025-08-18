package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTEmptyElement extends XmlObject {
    public static final DocumentFactory<CTEmptyElement> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTEmptyElement> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctemptyelement05catype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
