package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface HdrDocument extends XmlObject {
    public static final DocumentFactory<HdrDocument> Factory;
    public static final SchemaType type;

    CTHdrFtr addNewHdr();

    CTHdrFtr getHdr();

    void setHdr(CTHdrFtr cTHdrFtr);

    static {
        DocumentFactory<HdrDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "hdra530doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
