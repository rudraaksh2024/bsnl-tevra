package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTLogBase extends XmlObject {
    public static final DocumentFactory<CTLogBase> Factory;
    public static final SchemaType type;

    double getVal();

    void setVal(double d);

    STLogBase xgetVal();

    void xsetVal(STLogBase sTLogBase);

    static {
        DocumentFactory<CTLogBase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlogbase9191type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
