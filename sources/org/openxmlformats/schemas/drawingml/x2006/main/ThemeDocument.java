package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ThemeDocument extends XmlObject {
    public static final DocumentFactory<ThemeDocument> Factory;
    public static final SchemaType type;

    CTOfficeStyleSheet addNewTheme();

    CTOfficeStyleSheet getTheme();

    void setTheme(CTOfficeStyleSheet cTOfficeStyleSheet);

    static {
        DocumentFactory<ThemeDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "themefd26doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
