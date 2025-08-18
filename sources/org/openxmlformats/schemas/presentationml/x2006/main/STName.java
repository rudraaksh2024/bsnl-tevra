package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STName extends XmlString {
    public static final SimpleTypeFactory<STName> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STName> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stnameadaatype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
