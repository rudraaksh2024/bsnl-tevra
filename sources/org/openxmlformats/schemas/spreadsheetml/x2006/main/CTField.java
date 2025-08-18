package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTField extends XmlObject {
    public static final DocumentFactory<CTField> Factory;
    public static final SchemaType type;

    int getX();

    void setX(int i);

    XmlInt xgetX();

    void xsetX(XmlInt xmlInt);

    static {
        DocumentFactory<CTField> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfieldc999type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
