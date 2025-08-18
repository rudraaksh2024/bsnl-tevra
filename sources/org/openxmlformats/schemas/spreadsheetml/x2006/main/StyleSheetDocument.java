package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface StyleSheetDocument extends XmlObject {
    public static final DocumentFactory<StyleSheetDocument> Factory;
    public static final SchemaType type;

    CTStylesheet addNewStyleSheet();

    CTStylesheet getStyleSheet();

    void setStyleSheet(CTStylesheet cTStylesheet);

    static {
        DocumentFactory<StyleSheetDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "stylesheet5d8bdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
