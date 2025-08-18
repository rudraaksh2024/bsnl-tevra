package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextShapeAutofit extends XmlObject {
    public static final DocumentFactory<CTTextShapeAutofit> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextShapeAutofit> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextshapeautofita009type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
