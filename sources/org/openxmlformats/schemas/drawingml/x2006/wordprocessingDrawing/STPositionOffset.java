package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositionOffset extends XmlInt {
    public static final SimpleTypeFactory<STPositionOffset> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositionOffset> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositionoffseta433type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
