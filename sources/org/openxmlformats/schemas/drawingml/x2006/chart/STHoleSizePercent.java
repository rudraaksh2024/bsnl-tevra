package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHoleSizePercent extends XmlString {
    public static final SimpleTypeFactory<STHoleSizePercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STHoleSizePercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stholesizepercenta3d2type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
