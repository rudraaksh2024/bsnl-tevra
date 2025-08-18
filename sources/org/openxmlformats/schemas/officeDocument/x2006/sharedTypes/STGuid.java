package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STGuid extends XmlToken {
    public static final SimpleTypeFactory<STGuid> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STGuid> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stguid3883type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
