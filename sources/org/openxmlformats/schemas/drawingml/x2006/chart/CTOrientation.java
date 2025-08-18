package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;

public interface CTOrientation extends XmlObject {
    public static final DocumentFactory<CTOrientation> Factory;
    public static final SchemaType type;

    STOrientation.Enum getVal();

    boolean isSetVal();

    void setVal(STOrientation.Enum enumR);

    void unsetVal();

    STOrientation xgetVal();

    void xsetVal(STOrientation sTOrientation);

    static {
        DocumentFactory<CTOrientation> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctorientationcb16type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
