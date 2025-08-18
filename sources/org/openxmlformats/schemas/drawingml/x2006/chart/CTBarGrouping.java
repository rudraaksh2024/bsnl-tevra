package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;

public interface CTBarGrouping extends XmlObject {
    public static final DocumentFactory<CTBarGrouping> Factory;
    public static final SchemaType type;

    STBarGrouping.Enum getVal();

    boolean isSetVal();

    void setVal(STBarGrouping.Enum enumR);

    void unsetVal();

    STBarGrouping xgetVal();

    void xsetVal(STBarGrouping sTBarGrouping);

    static {
        DocumentFactory<CTBarGrouping> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbargrouping8bf0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
