package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STLang extends XmlString {
    public static final SimpleTypeFactory<STLang> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STLang> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stlang46a8type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
