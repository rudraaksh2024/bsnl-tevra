package org.openxmlformats.schemas.xpackage.x2006.digitalSignature;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STValue extends XmlString {
    public static final SimpleTypeFactory<STValue> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STValue> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stvalueb6e1type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
