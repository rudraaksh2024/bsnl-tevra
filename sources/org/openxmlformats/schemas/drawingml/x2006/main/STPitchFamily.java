package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPitchFamily extends XmlByte {
    public static final SimpleTypeFactory<STPitchFamily> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPitchFamily> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpitchfamily7765type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
