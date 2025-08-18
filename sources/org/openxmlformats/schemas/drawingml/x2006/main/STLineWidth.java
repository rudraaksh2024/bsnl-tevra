package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STLineWidth extends STCoordinate32Unqualified {
    public static final SimpleTypeFactory<STLineWidth> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STLineWidth> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stlinewidth8313type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
