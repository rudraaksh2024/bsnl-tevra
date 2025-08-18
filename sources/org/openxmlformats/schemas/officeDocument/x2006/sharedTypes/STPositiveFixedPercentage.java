package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositiveFixedPercentage extends STPercentage {
    public static final SimpleTypeFactory<STPositiveFixedPercentage> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositiveFixedPercentage> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivefixedpercentagecbe5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
