package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STOverlapByte extends XmlByte {
    public static final SimpleTypeFactory<STOverlapByte> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STOverlapByte> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stoverlapbyte2427type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
