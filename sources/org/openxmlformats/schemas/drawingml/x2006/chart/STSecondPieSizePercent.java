package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSecondPieSizePercent extends XmlString {
    public static final SimpleTypeFactory<STSecondPieSizePercent> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STSecondPieSizePercent> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsecondpiesizepercentcbc0type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
