package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextTypeface extends XmlString {
    public static final SimpleTypeFactory<STTextTypeface> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextTypeface> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttexttypefacea80ftype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
