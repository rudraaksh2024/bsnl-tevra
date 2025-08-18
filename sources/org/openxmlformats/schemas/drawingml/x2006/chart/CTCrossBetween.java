package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;

public interface CTCrossBetween extends XmlObject {
    public static final DocumentFactory<CTCrossBetween> Factory;
    public static final SchemaType type;

    STCrossBetween.Enum getVal();

    void setVal(STCrossBetween.Enum enumR);

    STCrossBetween xgetVal();

    void xsetVal(STCrossBetween sTCrossBetween);

    static {
        DocumentFactory<CTCrossBetween> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcrossbetweeneb14type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
