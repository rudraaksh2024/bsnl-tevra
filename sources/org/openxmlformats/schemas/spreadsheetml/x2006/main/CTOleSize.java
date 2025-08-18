package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTOleSize extends XmlObject {
    public static final DocumentFactory<CTOleSize> Factory;
    public static final SchemaType type;

    String getRef();

    void setRef(String str);

    STRef xgetRef();

    void xsetRef(STRef sTRef);

    static {
        DocumentFactory<CTOleSize> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctolesizea368type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
