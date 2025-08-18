package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPositivePercentage extends STPercentage {
    public static final SimpleTypeFactory<STPositivePercentage> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPositivePercentage> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpositivepercentage942dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
