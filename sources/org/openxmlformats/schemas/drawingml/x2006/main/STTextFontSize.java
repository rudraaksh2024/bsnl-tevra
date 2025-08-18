package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextFontSize extends XmlInt {
    public static final SimpleTypeFactory<STTextFontSize> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextFontSize> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextfontsizeb3a8type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
