package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPath2DClose extends XmlObject {
    public static final DocumentFactory<CTPath2DClose> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTPath2DClose> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2dclose09f2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
