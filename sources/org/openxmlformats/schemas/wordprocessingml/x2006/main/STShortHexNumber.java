package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlHexBinary;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STShortHexNumber extends XmlHexBinary {
    public static final SimpleTypeFactory<STShortHexNumber> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STShortHexNumber> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stshorthexnumber9170type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
