package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextSpacingPercent extends XmlObject {
    public static final DocumentFactory<CTTextSpacingPercent> Factory;
    public static final SchemaType type;

    Object getVal();

    void setVal(Object obj);

    STTextSpacingPercentOrPercentString xgetVal();

    void xsetVal(STTextSpacingPercentOrPercentString sTTextSpacingPercentOrPercentString);

    static {
        DocumentFactory<CTTextSpacingPercent> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextspacingpercent322atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
