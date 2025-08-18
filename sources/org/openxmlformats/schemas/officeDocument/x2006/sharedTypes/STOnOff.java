package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STOnOff extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STOnOff> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STOnOff> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stonoff9300type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
