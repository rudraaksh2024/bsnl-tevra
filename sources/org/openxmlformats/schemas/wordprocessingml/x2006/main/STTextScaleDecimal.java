package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextScaleDecimal extends XmlInteger {
    public static final SimpleTypeFactory<STTextScaleDecimal> Factory;
    public static final SchemaType type;

    int getIntValue();

    void setIntValue(int i);

    static {
        SimpleTypeFactory<STTextScaleDecimal> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextscaledecimaldee4type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
