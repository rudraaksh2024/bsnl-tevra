package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTHoleSize extends XmlObject {
    public static final DocumentFactory<CTHoleSize> Factory;
    public static final SchemaType type;

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STHoleSize xgetVal();

    void xsetVal(STHoleSize sTHoleSize);

    static {
        DocumentFactory<CTHoleSize> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctholesize0f3btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
