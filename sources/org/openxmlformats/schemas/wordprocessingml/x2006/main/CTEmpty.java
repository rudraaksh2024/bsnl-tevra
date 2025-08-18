package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTEmpty extends XmlObject {
    public static final DocumentFactory<CTEmpty> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTEmpty> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctempty3fa5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
