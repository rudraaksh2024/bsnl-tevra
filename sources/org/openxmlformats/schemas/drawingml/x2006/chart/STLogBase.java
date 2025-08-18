package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STLogBase extends XmlDouble {
    public static final SimpleTypeFactory<STLogBase> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STLogBase> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stlogbase11a1type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
