package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBoolean extends XmlObject {
    public static final DocumentFactory<CTBoolean> Factory;
    public static final SchemaType type;

    boolean getVal();

    boolean isSetVal();

    void setVal(boolean z);

    void unsetVal();

    XmlBoolean xgetVal();

    void xsetVal(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTBoolean> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbooleancc3etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
