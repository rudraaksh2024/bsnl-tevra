package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextPointUnqualified extends XmlInt {
    public static final SimpleTypeFactory<STTextPointUnqualified> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextPointUnqualified> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextpointunqualifiedc6ebtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
