package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTColors extends XmlObject {
    public static final DocumentFactory<CTColors> Factory;
    public static final SchemaType type;

    CTIndexedColors addNewIndexedColors();

    CTMRUColors addNewMruColors();

    CTIndexedColors getIndexedColors();

    CTMRUColors getMruColors();

    boolean isSetIndexedColors();

    boolean isSetMruColors();

    void setIndexedColors(CTIndexedColors cTIndexedColors);

    void setMruColors(CTMRUColors cTMRUColors);

    void unsetIndexedColors();

    void unsetMruColors();

    static {
        DocumentFactory<CTColors> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolors6579type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
