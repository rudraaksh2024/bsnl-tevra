package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STDateTime extends XmlDateTime {
    public static final SimpleTypeFactory<STDateTime> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STDateTime> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdatetimee41dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
