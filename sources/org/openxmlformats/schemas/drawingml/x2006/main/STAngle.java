package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STAngle extends XmlInt {
    public static final SimpleTypeFactory<STAngle> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STAngle> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stangle8074type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
