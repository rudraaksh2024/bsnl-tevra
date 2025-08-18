package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextSpacingPoint extends XmlObject {
    public static final DocumentFactory<CTTextSpacingPoint> Factory;
    public static final SchemaType type;

    int getVal();

    void setVal(int i);

    STTextSpacingPoint xgetVal();

    void xsetVal(STTextSpacingPoint sTTextSpacingPoint);

    static {
        DocumentFactory<CTTextSpacingPoint> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextspacingpoint6cf5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
