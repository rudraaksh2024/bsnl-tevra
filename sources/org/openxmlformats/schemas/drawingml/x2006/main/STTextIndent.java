package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextIndent extends STCoordinate32Unqualified {
    public static final SimpleTypeFactory<STTextIndent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextIndent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextindent16e4type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
