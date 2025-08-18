package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPercentageDecimal extends XmlInt {
    public static final SimpleTypeFactory<STPercentageDecimal> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPercentageDecimal> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpercentagedecimalcb78type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
