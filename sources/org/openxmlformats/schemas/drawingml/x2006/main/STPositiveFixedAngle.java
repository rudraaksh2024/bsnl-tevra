package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositiveFixedAngle extends STAngle {
    public static final SimpleTypeFactory<STPositiveFixedAngle> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositiveFixedAngle> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivefixedangle2503type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
