package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextBulletStartAtNum extends XmlInt {
    public static final SimpleTypeFactory<STTextBulletStartAtNum> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextBulletStartAtNum> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextbulletstartatnum562btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
