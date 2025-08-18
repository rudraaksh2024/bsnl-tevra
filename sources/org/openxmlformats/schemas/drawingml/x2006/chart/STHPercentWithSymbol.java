package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHPercentWithSymbol extends XmlString {
    public static final SimpleTypeFactory<STHPercentWithSymbol> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STHPercentWithSymbol> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sthpercentwithsymbole671type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
