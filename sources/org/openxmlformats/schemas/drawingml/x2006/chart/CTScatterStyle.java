package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STScatterStyle;

public interface CTScatterStyle extends XmlObject {
    public static final DocumentFactory<CTScatterStyle> Factory;
    public static final SchemaType type;

    STScatterStyle.Enum getVal();

    boolean isSetVal();

    void setVal(STScatterStyle.Enum enumR);

    void unsetVal();

    STScatterStyle xgetVal();

    void xsetVal(STScatterStyle sTScatterStyle);

    static {
        DocumentFactory<CTScatterStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctscatterstyle94c9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
