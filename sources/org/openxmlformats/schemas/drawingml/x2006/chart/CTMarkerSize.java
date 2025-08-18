package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTMarkerSize extends XmlObject {
    public static final DocumentFactory<CTMarkerSize> Factory;
    public static final SchemaType type;

    short getVal();

    boolean isSetVal();

    void setVal(short s);

    void unsetVal();

    STMarkerSize xgetVal();

    void xsetVal(STMarkerSize sTMarkerSize);

    static {
        DocumentFactory<CTMarkerSize> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkersized8c1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
