package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDocumentBase extends XmlObject {
    public static final DocumentFactory<CTDocumentBase> Factory;
    public static final SchemaType type;

    CTBackground addNewBackground();

    CTBackground getBackground();

    boolean isSetBackground();

    void setBackground(CTBackground cTBackground);

    void unsetBackground();

    static {
        DocumentFactory<CTDocumentBase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdocumentbasedf5ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
