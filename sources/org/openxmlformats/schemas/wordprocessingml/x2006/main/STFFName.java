package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFFName extends XmlString {
    public static final SimpleTypeFactory<STFFName> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFFName> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stffname852dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
