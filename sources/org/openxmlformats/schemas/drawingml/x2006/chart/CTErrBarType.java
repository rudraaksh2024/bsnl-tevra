package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType;

public interface CTErrBarType extends XmlObject {
    public static final DocumentFactory<CTErrBarType> Factory;
    public static final SchemaType type;

    STErrBarType.Enum getVal();

    boolean isSetVal();

    void setVal(STErrBarType.Enum enumR);

    void unsetVal();

    STErrBarType xgetVal();

    void xsetVal(STErrBarType sTErrBarType);

    static {
        DocumentFactory<CTErrBarType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cterrbartypedcb4type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
