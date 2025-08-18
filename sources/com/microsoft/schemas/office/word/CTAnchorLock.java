package com.microsoft.schemas.office.word;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAnchorLock extends XmlObject {
    public static final DocumentFactory<CTAnchorLock> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTAnchorLock> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctanchorlocked31type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
