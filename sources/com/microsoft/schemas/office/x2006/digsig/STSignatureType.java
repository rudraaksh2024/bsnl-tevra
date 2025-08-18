package com.microsoft.schemas.office.x2006.digsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSignatureType extends XmlInt {
    public static final SimpleTypeFactory<STSignatureType> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STSignatureType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsignaturetypeae80type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
