package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBorderId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STBorderId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STBorderId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stborderid1a80type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
