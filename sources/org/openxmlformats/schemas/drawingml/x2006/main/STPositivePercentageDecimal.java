package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositivePercentageDecimal extends STPercentageDecimal {
    public static final SimpleTypeFactory<STPositivePercentageDecimal> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositivePercentageDecimal> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivepercentagedecimal3c3ftype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
