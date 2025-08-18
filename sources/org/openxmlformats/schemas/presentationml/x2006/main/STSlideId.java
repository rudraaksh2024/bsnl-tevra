package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSlideId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STSlideId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STSlideId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stslideida0b3type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
