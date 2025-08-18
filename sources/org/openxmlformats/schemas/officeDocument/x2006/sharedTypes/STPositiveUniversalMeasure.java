package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositiveUniversalMeasure extends STUniversalMeasure {
    public static final SimpleTypeFactory<STPositiveUniversalMeasure> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositiveUniversalMeasure> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositiveuniversalmeasure3166type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
