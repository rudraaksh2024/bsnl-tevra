package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTString extends XmlObject {
    public static final DocumentFactory<CTString> Factory;
    public static final SchemaType type;

    String getVal();

    void setVal(String str);

    STString xgetVal();

    void xsetVal(STString sTString);

    static {
        DocumentFactory<CTString> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstring9c37type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
