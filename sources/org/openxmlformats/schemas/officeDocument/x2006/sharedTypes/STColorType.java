package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STColorType extends XmlString {
    public static final SimpleTypeFactory<STColorType> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STColorType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcolortypea86dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
