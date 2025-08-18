package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFillId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STFillId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFillId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfillida097type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
