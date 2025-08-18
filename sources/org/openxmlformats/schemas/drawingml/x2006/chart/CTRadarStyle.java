package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;

public interface CTRadarStyle extends XmlObject {
    public static final DocumentFactory<CTRadarStyle> Factory;
    public static final SchemaType type;

    STRadarStyle.Enum getVal();

    boolean isSetVal();

    void setVal(STRadarStyle.Enum enumR);

    void unsetVal();

    STRadarStyle xgetVal();

    void xsetVal(STRadarStyle sTRadarStyle);

    static {
        DocumentFactory<CTRadarStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctradarstyle77d1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
