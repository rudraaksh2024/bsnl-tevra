package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STDecimalNumber extends XmlInteger {
    public static final SimpleTypeFactory<STDecimalNumber> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STDecimalNumber> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdecimalnumber8d28type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
