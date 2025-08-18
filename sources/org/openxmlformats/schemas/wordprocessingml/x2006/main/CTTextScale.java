package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextScale extends XmlObject {
    public static final DocumentFactory<CTTextScale> Factory;
    public static final SchemaType type;

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STTextScale xgetVal();

    void xsetVal(STTextScale sTTextScale);

    static {
        DocumentFactory<CTTextScale> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextscale3455type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
