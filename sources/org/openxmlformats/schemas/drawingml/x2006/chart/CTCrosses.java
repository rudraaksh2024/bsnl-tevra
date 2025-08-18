package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;

public interface CTCrosses extends XmlObject {
    public static final DocumentFactory<CTCrosses> Factory;
    public static final SchemaType type;

    STCrosses.Enum getVal();

    void setVal(STCrosses.Enum enumR);

    STCrosses xgetVal();

    void xsetVal(STCrosses sTCrosses);

    static {
        DocumentFactory<CTCrosses> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcrossesbcb8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
