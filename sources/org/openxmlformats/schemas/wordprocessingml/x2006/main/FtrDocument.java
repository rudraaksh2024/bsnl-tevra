package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface FtrDocument extends XmlObject {
    public static final DocumentFactory<FtrDocument> Factory;
    public static final SchemaType type;

    CTHdrFtr addNewFtr();

    CTHdrFtr getFtr();

    void setFtr(CTHdrFtr cTHdrFtr);

    static {
        DocumentFactory<FtrDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ftre182doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
