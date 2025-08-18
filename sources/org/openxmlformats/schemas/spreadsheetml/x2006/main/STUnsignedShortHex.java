package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlHexBinary;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUnsignedShortHex extends XmlHexBinary {
    public static final SimpleTypeFactory<STUnsignedShortHex> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STUnsignedShortHex> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stunsignedshorthex0bedtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
