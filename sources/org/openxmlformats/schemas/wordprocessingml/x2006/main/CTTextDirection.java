package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextDirection;

public interface CTTextDirection extends XmlObject {
    public static final DocumentFactory<CTTextDirection> Factory;
    public static final SchemaType type;

    STTextDirection.Enum getVal();

    void setVal(STTextDirection.Enum enumR);

    STTextDirection xgetVal();

    void xsetVal(STTextDirection sTTextDirection);

    static {
        DocumentFactory<CTTextDirection> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextdirection0940type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
