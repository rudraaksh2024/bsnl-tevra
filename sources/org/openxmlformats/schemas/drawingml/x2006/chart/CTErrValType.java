package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrValType;

public interface CTErrValType extends XmlObject {
    public static final DocumentFactory<CTErrValType> Factory;
    public static final SchemaType type;

    STErrValType.Enum getVal();

    boolean isSetVal();

    void setVal(STErrValType.Enum enumR);

    void unsetVal();

    STErrValType xgetVal();

    void xsetVal(STErrValType sTErrValType);

    static {
        DocumentFactory<CTErrValType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cterrvaltyped0e6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
