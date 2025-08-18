package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSecondPieSize extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STSecondPieSize> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STSecondPieSize> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsecondpiesize58fdtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
