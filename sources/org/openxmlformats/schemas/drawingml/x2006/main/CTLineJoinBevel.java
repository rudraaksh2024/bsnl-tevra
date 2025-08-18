package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTLineJoinBevel extends XmlObject {
    public static final DocumentFactory<CTLineJoinBevel> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTLineJoinBevel> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlinejoinbevel91cdtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
