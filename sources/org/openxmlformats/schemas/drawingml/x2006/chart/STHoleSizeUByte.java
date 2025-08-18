package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHoleSizeUByte extends XmlUnsignedByte {
    public static final SimpleTypeFactory<STHoleSizeUByte> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STHoleSizeUByte> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stholesizeubyte577atype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
