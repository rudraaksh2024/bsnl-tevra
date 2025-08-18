package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSdtDocPart extends XmlObject {
    public static final DocumentFactory<CTSdtDocPart> Factory;
    public static final SchemaType type;

    CTString addNewDocPartCategory();

    CTString addNewDocPartGallery();

    CTOnOff addNewDocPartUnique();

    CTString getDocPartCategory();

    CTString getDocPartGallery();

    CTOnOff getDocPartUnique();

    boolean isSetDocPartCategory();

    boolean isSetDocPartGallery();

    boolean isSetDocPartUnique();

    void setDocPartCategory(CTString cTString);

    void setDocPartGallery(CTString cTString);

    void setDocPartUnique(CTOnOff cTOnOff);

    void unsetDocPartCategory();

    void unsetDocPartGallery();

    void unsetDocPartUnique();

    static {
        DocumentFactory<CTSdtDocPart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtdocpartcea0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
