package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUniversalMeasure extends XmlString {
    public static final SimpleTypeFactory<STUniversalMeasure> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STUniversalMeasure> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stuniversalmeasure148dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
