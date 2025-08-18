package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STShape;

public interface CTShape extends XmlObject {
    public static final DocumentFactory<CTShape> Factory;
    public static final SchemaType type;

    STShape.Enum getVal();

    boolean isSetVal();

    void setVal(STShape.Enum enumR);

    void unsetVal();

    STShape xgetVal();

    void xsetVal(STShape sTShape);

    static {
        DocumentFactory<CTShape> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshape89e5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
