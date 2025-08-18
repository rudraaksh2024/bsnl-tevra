package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STNumFmtId extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STNumFmtId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STNumFmtId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stnumfmtid76fbtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
