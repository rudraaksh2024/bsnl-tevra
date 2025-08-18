package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

public interface CTTickLblPos extends XmlObject {
    public static final DocumentFactory<CTTickLblPos> Factory;
    public static final SchemaType type;

    STTickLblPos.Enum getVal();

    boolean isSetVal();

    void setVal(STTickLblPos.Enum enumR);

    void unsetVal();

    STTickLblPos xgetVal();

    void xsetVal(STTickLblPos sTTickLblPos);

    static {
        DocumentFactory<CTTickLblPos> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctticklblposff61type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
