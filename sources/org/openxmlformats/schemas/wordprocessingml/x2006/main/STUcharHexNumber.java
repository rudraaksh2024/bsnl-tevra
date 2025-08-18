package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlHexBinary;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUcharHexNumber extends XmlHexBinary {
    public static final SimpleTypeFactory<STUcharHexNumber> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STUcharHexNumber> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stucharhexnumber4efftype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
