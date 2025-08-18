package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSignedTwipsMeasure extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STSignedTwipsMeasure> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STSignedTwipsMeasure> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsignedtwipsmeasureb227type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
