package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAxisUnit extends XmlObject {
    public static final DocumentFactory<CTAxisUnit> Factory;
    public static final SchemaType type;

    double getVal();

    void setVal(double d);

    STAxisUnit xgetVal();

    void xsetVal(STAxisUnit sTAxisUnit);

    static {
        DocumentFactory<CTAxisUnit> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctaxisunitead7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
