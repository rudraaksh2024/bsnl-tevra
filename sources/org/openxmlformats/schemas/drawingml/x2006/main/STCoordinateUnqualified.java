package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCoordinateUnqualified extends XmlLong {
    public static final SimpleTypeFactory<STCoordinateUnqualified> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STCoordinateUnqualified> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcoordinateunqualified6d2ctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
