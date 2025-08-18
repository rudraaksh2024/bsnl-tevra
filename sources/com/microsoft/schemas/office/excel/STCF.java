package com.microsoft.schemas.office.excel;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCF extends XmlString {
    public static final SimpleTypeFactory<STCF> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STCF> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcffa3dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
