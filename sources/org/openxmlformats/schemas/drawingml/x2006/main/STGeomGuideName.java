package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STGeomGuideName extends XmlToken {
    public static final SimpleTypeFactory<STGeomGuideName> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STGeomGuideName> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stgeomguidename366ctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
