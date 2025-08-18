package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSlideMasterId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STSlideMasterId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STSlideMasterId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stslidemasteridfe71type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
