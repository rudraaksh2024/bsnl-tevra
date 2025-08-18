package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlHexBinary;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STLongHexNumber extends XmlHexBinary {
    public static final SimpleTypeFactory<STLongHexNumber> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STLongHexNumber> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stlonghexnumberd6batype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
