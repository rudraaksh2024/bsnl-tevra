package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STOverlapPercent extends XmlString {
    public static final SimpleTypeFactory<STOverlapPercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STOverlapPercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stoverlappercent872etype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
