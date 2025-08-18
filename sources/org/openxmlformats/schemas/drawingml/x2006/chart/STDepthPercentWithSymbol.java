package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STDepthPercentWithSymbol extends XmlString {
    public static final SimpleTypeFactory<STDepthPercentWithSymbol> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STDepthPercentWithSymbol> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdepthpercentwithsymbola04ctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
