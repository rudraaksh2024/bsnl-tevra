package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STGeomGuideFormula extends XmlString {
    public static final SimpleTypeFactory<STGeomGuideFormula> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STGeomGuideFormula> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stgeomguideformula4b51type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
