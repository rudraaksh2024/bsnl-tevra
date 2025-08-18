package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

public interface CTLegendPos extends XmlObject {
    public static final DocumentFactory<CTLegendPos> Factory;
    public static final SchemaType type;

    STLegendPos.Enum getVal();

    boolean isSetVal();

    void setVal(STLegendPos.Enum enumR);

    void unsetVal();

    STLegendPos xgetVal();

    void xsetVal(STLegendPos sTLegendPos);

    static {
        DocumentFactory<CTLegendPos> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlegendpos053ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
