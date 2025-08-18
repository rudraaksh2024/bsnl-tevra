package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCoordinate32Unqualified extends XmlInt {
    public static final SimpleTypeFactory<STCoordinate32Unqualified> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STCoordinate32Unqualified> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcoordinate32unqualifieda9edtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
