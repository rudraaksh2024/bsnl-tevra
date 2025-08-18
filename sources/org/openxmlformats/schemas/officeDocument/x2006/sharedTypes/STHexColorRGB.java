package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlHexBinary;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHexColorRGB extends XmlHexBinary {
    public static final SimpleTypeFactory<STHexColorRGB> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STHexColorRGB> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sthexcolorrgb8115type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
