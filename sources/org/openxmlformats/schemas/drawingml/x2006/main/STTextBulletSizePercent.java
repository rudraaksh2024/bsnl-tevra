package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextBulletSizePercent extends XmlString {
    public static final SimpleTypeFactory<STTextBulletSizePercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextBulletSizePercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextbulletsizepercentb516type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
