package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTIntProperty extends XmlObject {
    public static final DocumentFactory<CTIntProperty> Factory;
    public static final SchemaType type;

    int getVal();

    void setVal(int i);

    XmlInt xgetVal();

    void xsetVal(XmlInt xmlInt);

    static {
        DocumentFactory<CTIntProperty> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctintproperty32c3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
