package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;

public interface CTAxPos extends XmlObject {
    public static final DocumentFactory<CTAxPos> Factory;
    public static final SchemaType type;

    STAxPos.Enum getVal();

    void setVal(STAxPos.Enum enumR);

    STAxPos xgetVal();

    void xsetVal(STAxPos sTAxPos);

    static {
        DocumentFactory<CTAxPos> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctaxposff69type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
