package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STGapAmountPercent extends XmlString {
    public static final SimpleTypeFactory<STGapAmountPercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STGapAmountPercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stgapamountpercent84b5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
