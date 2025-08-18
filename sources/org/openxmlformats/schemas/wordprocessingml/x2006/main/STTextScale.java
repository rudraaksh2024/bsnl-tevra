package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextScale extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STTextScale> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STTextScale> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextscalea465type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
