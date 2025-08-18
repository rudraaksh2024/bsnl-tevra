package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

public interface CTEmbeddedFontListEntry extends XmlObject {
    public static final DocumentFactory<CTEmbeddedFontListEntry> Factory;
    public static final SchemaType type;

    CTEmbeddedFontDataId addNewBold();

    CTEmbeddedFontDataId addNewBoldItalic();

    CTTextFont addNewFont();

    CTEmbeddedFontDataId addNewItalic();

    CTEmbeddedFontDataId addNewRegular();

    CTEmbeddedFontDataId getBold();

    CTEmbeddedFontDataId getBoldItalic();

    CTTextFont getFont();

    CTEmbeddedFontDataId getItalic();

    CTEmbeddedFontDataId getRegular();

    boolean isSetBold();

    boolean isSetBoldItalic();

    boolean isSetItalic();

    boolean isSetRegular();

    void setBold(CTEmbeddedFontDataId cTEmbeddedFontDataId);

    void setBoldItalic(CTEmbeddedFontDataId cTEmbeddedFontDataId);

    void setFont(CTTextFont cTTextFont);

    void setItalic(CTEmbeddedFontDataId cTEmbeddedFontDataId);

    void setRegular(CTEmbeddedFontDataId cTEmbeddedFontDataId);

    void unsetBold();

    void unsetBoldItalic();

    void unsetItalic();

    void unsetRegular();

    static {
        DocumentFactory<CTEmbeddedFontListEntry> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctembeddedfontlistentry48b4type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
