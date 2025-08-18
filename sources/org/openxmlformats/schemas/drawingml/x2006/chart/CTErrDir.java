package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrDir;

public interface CTErrDir extends XmlObject {
    public static final DocumentFactory<CTErrDir> Factory;
    public static final SchemaType type;

    STErrDir.Enum getVal();

    void setVal(STErrDir.Enum enumR);

    STErrDir xgetVal();

    void xsetVal(STErrDir sTErrDir);

    static {
        DocumentFactory<CTErrDir> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cterrdirc214type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
