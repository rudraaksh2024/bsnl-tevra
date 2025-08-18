package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

public interface CTMarkerStyle extends XmlObject {
    public static final DocumentFactory<CTMarkerStyle> Factory;
    public static final SchemaType type;

    STMarkerStyle.Enum getVal();

    void setVal(STMarkerStyle.Enum enumR);

    STMarkerStyle xgetVal();

    void xsetVal(STMarkerStyle sTMarkerStyle);

    static {
        DocumentFactory<CTMarkerStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkerstyle1f6ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
