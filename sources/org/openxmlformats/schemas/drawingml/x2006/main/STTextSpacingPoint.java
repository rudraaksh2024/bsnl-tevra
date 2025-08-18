package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextSpacingPoint extends XmlInt {
    public static final SimpleTypeFactory<STTextSpacingPoint> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextSpacingPoint> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextspacingpointdd05type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
