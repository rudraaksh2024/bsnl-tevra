package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHPercentUShort extends XmlUnsignedShort {
    public static final SimpleTypeFactory<STHPercentUShort> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STHPercentUShort> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sthpercentushort0848type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
