package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTwipsMeasure extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STTwipsMeasure> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STTwipsMeasure> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttwipsmeasure9c4ftype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
