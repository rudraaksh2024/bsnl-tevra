package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCellStyleXfId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STCellStyleXfId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STCellStyleXfId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcellstylexfid70c7type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
