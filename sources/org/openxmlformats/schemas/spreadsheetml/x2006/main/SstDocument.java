package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SstDocument extends XmlObject {
    public static final DocumentFactory<SstDocument> Factory;
    public static final SchemaType type;

    CTSst addNewSst();

    CTSst getSst();

    void setSst(CTSst cTSst);

    static {
        DocumentFactory<SstDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sstf81fdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
