package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface STFormula extends STXstring {
    public static final SimpleTypeFactory<STFormula> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STFormula> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stformula7e35type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
