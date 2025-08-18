package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDouble extends XmlObject {
    public static final DocumentFactory<CTDouble> Factory;
    public static final SchemaType type;

    double getVal();

    void setVal(double d);

    XmlDouble xgetVal();

    void xsetVal(XmlDouble xmlDouble);

    static {
        DocumentFactory<CTDouble> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdoublec10btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
