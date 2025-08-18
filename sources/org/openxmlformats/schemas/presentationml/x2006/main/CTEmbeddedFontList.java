package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTEmbeddedFontList extends XmlObject {
    public static final DocumentFactory<CTEmbeddedFontList> Factory;
    public static final SchemaType type;

    CTEmbeddedFontListEntry addNewEmbeddedFont();

    CTEmbeddedFontListEntry getEmbeddedFontArray(int i);

    CTEmbeddedFontListEntry[] getEmbeddedFontArray();

    List<CTEmbeddedFontListEntry> getEmbeddedFontList();

    CTEmbeddedFontListEntry insertNewEmbeddedFont(int i);

    void removeEmbeddedFont(int i);

    void setEmbeddedFontArray(int i, CTEmbeddedFontListEntry cTEmbeddedFontListEntry);

    void setEmbeddedFontArray(CTEmbeddedFontListEntry[] cTEmbeddedFontListEntryArr);

    int sizeOfEmbeddedFontArray();

    static {
        DocumentFactory<CTEmbeddedFontList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctembeddedfontlist240etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
