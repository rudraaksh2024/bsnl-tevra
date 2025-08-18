package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextSpacingPercent extends STPercentageDecimal {
    public static final SimpleTypeFactory<STTextSpacingPercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextSpacingPercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextspacingpercentde3atype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
