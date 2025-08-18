package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFontId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STFontId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFontId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfontid9d63type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
