package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCnf extends XmlString {
    public static final SimpleTypeFactory<STCnf> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STCnf> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcnfcfa7type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
