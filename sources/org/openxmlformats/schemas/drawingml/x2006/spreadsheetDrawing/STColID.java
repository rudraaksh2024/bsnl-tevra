package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STColID extends XmlInt {
    public static final SimpleTypeFactory<STColID> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STColID> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcolidb7f5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
