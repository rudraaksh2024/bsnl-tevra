package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STDxfId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STDxfId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STDxfId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdxfid9fdctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
