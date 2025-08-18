package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STString extends XmlString {
    public static final SimpleTypeFactory<STString> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STString> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "ststring76cbtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
