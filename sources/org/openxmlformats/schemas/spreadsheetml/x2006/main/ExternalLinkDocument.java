package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ExternalLinkDocument extends XmlObject {
    public static final DocumentFactory<ExternalLinkDocument> Factory;
    public static final SchemaType type;

    CTExternalLink addNewExternalLink();

    CTExternalLink getExternalLink();

    void setExternalLink(CTExternalLink cTExternalLink);

    static {
        DocumentFactory<ExternalLinkDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "externallinkb4c2doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
