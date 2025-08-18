package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBooleanProperty extends XmlObject {
    public static final DocumentFactory<CTBooleanProperty> Factory;
    public static final SchemaType type;

    boolean getVal();

    boolean isSetVal();

    void setVal(boolean z);

    void unsetVal();

    XmlBoolean xgetVal();

    void xsetVal(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTBooleanProperty> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbooleanproperty1f3ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
