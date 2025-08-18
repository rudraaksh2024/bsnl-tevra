package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STXstring extends XmlString {
    public static final SimpleTypeFactory<STXstring> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STXstring> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stxstringf179type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
