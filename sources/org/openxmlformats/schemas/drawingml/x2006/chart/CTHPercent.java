package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTHPercent extends XmlObject {
    public static final DocumentFactory<CTHPercent> Factory;
    public static final SchemaType type;

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STHPercent xgetVal();

    void xsetVal(STHPercent sTHPercent);

    static {
        DocumentFactory<CTHPercent> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthpercent59dftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
