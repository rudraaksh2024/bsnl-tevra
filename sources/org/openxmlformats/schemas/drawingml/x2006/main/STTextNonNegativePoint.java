package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextNonNegativePoint extends XmlInt {
    public static final SimpleTypeFactory<STTextNonNegativePoint> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextNonNegativePoint> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextnonnegativepointbac6type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
