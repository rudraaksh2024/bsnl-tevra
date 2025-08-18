package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositivePercentage extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STPositivePercentage> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STPositivePercentage> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivepercentagedb9etype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
