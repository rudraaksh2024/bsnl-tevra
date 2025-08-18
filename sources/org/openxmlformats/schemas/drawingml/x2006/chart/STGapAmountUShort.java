package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STGapAmountUShort extends XmlUnsignedShort {
    public static final SimpleTypeFactory<STGapAmountUShort> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STGapAmountUShort> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stgapamountushort3f61type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
