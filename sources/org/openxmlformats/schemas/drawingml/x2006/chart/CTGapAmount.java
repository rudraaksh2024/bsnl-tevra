package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTGapAmount extends XmlObject {
    public static final DocumentFactory<CTGapAmount> Factory;
    public static final SchemaType type;

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STGapAmount xgetVal();

    void xsetVal(STGapAmount sTGapAmount);

    static {
        DocumentFactory<CTGapAmount> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgapamountdd98type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
