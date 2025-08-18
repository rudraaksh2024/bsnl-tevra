package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextFontScalePercent extends STPercentageDecimal {
    public static final SimpleTypeFactory<STTextFontScalePercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STTextFontScalePercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextfontscalepercente6c2type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
