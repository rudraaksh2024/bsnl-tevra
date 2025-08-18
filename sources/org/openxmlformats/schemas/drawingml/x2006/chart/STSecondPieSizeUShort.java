package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSecondPieSizeUShort extends XmlUnsignedShort {
    public static final SimpleTypeFactory<STSecondPieSizeUShort> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STSecondPieSizeUShort> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsecondpiesizeushort6af6type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
