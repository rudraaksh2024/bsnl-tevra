package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFixedPercentage extends STPercentage {
    public static final SimpleTypeFactory<STFixedPercentage> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFixedPercentage> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfixedpercentage67detype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
