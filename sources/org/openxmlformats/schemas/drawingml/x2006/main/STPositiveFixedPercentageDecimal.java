package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositiveFixedPercentageDecimal extends STPercentageDecimal {
    public static final SimpleTypeFactory<STPositiveFixedPercentageDecimal> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositiveFixedPercentageDecimal> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivefixedpercentagedecimal1187type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
