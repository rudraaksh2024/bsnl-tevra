package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTWrapNone extends XmlObject {
    public static final DocumentFactory<CTWrapNone> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTWrapNone> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctwrapnonec43dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
