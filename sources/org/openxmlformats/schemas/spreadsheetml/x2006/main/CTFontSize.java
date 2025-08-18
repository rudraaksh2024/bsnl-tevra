package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFontSize extends XmlObject {
    public static final DocumentFactory<CTFontSize> Factory;
    public static final SchemaType type;

    double getVal();

    void setVal(double d);

    XmlDouble xgetVal();

    void xsetVal(XmlDouble xmlDouble);

    static {
        DocumentFactory<CTFontSize> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontsizeb3b9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
