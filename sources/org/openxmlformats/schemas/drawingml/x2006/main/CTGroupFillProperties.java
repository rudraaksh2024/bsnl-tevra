package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTGroupFillProperties extends XmlObject {
    public static final DocumentFactory<CTGroupFillProperties> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTGroupFillProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupfillpropertiesec66type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
