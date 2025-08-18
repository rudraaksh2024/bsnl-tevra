package org.openxmlformats.schemas.xpackage.x2006.digitalSignature;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFormat extends XmlString {
    public static final SimpleTypeFactory<STFormat> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFormat> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stformat98d1type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
