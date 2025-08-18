package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFixedPercentageDecimal extends STPercentageDecimal {
    public static final SimpleTypeFactory<STFixedPercentageDecimal> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFixedPercentageDecimal> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfixedpercentagedecimal1deetype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
