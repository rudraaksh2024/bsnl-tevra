package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTNoFillProperties extends XmlObject {
    public static final DocumentFactory<CTNoFillProperties> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTNoFillProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnofillpropertiesbf92type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
