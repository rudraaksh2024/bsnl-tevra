package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositiveCoordinate32 extends STCoordinate32Unqualified {
    public static final SimpleTypeFactory<STPositiveCoordinate32> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositiveCoordinate32> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivecoordinate321b9btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
