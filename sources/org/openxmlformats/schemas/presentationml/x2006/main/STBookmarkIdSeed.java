package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBookmarkIdSeed extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STBookmarkIdSeed> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STBookmarkIdSeed> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stbookmarkidseed9d13type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
