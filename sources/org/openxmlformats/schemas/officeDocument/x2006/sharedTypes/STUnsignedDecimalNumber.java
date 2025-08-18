package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUnsignedDecimalNumber extends XmlUnsignedLong {
    public static final SimpleTypeFactory<STUnsignedDecimalNumber> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STUnsignedDecimalNumber> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stunsigneddecimalnumber7db5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
