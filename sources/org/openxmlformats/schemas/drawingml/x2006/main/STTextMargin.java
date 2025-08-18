package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextMargin extends STCoordinate32Unqualified {
    public static final SimpleTypeFactory<STTextMargin> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextMargin> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextmargin9666type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
