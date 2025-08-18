package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPerspective extends XmlObject {
    public static final DocumentFactory<CTPerspective> Factory;
    public static final SchemaType type;

    short getVal();

    boolean isSetVal();

    void setVal(short s);

    void unsetVal();

    STPerspective xgetVal();

    void xsetVal(STPerspective sTPerspective);

    static {
        DocumentFactory<CTPerspective> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctperspectivefd2atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
