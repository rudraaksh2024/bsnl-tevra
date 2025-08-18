package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;

public interface CTTickMark extends XmlObject {
    public static final DocumentFactory<CTTickMark> Factory;
    public static final SchemaType type;

    STTickMark.Enum getVal();

    boolean isSetVal();

    void setVal(STTickMark.Enum enumR);

    void unsetVal();

    STTickMark xgetVal();

    void xsetVal(STTickMark sTTickMark);

    static {
        DocumentFactory<CTTickMark> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttickmarke7f2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
