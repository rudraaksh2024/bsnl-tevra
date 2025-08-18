package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFontFamily extends XmlInteger {
    public static final SimpleTypeFactory<STFontFamily> Factory;
    public static final SchemaType type;

    int getIntValue();

    void setIntValue(int i);

    static {
        SimpleTypeFactory<STFontFamily> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfontfamily9c6ctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
