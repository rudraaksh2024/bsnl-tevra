package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STWrapDistance extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STWrapDistance> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STWrapDistance> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stwrapdistanceea50type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
