package com.microsoft.schemas.office.x2006.digsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUniqueIdentifierWithBraces extends XmlString {
    public static final SimpleTypeFactory<STUniqueIdentifierWithBraces> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STUniqueIdentifierWithBraces> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stuniqueidentifierwithbraces0a78type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
