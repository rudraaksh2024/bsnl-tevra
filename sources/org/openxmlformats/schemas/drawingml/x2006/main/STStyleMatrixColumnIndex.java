package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STStyleMatrixColumnIndex extends XmlUnsignedInt {
    public static final SimpleTypeFactory<STStyleMatrixColumnIndex> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STStyleMatrixColumnIndex> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "ststylematrixcolumnindex1755type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
