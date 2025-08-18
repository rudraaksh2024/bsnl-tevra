package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHoleSize extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STHoleSize> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STHoleSize> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stholesize912btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
