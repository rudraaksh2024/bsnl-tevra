package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STUnsignedDecimalNumber;

public interface STEighthPointMeasure extends STUnsignedDecimalNumber {
    public static final SimpleTypeFactory<STEighthPointMeasure> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STEighthPointMeasure> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "steighthpointmeasure3371type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
