package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STUnsignedDecimalNumber;

public interface STPointMeasure extends STUnsignedDecimalNumber {
    public static final SimpleTypeFactory<STPointMeasure> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPointMeasure> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpointmeasurea96atype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
