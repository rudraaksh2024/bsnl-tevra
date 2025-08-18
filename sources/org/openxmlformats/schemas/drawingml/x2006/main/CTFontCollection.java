package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFontCollection extends XmlObject {
    public static final DocumentFactory<CTFontCollection> Factory;
    public static final SchemaType type;

    CTTextFont addNewCs();

    CTTextFont addNewEa();

    CTOfficeArtExtensionList addNewExtLst();

    CTSupplementalFont addNewFont();

    CTTextFont addNewLatin();

    CTTextFont getCs();

    CTTextFont getEa();

    CTOfficeArtExtensionList getExtLst();

    CTSupplementalFont getFontArray(int i);

    CTSupplementalFont[] getFontArray();

    List<CTSupplementalFont> getFontList();

    CTTextFont getLatin();

    CTSupplementalFont insertNewFont(int i);

    boolean isSetExtLst();

    void removeFont(int i);

    void setCs(CTTextFont cTTextFont);

    void setEa(CTTextFont cTTextFont);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFontArray(int i, CTSupplementalFont cTSupplementalFont);

    void setFontArray(CTSupplementalFont[] cTSupplementalFontArr);

    void setLatin(CTTextFont cTTextFont);

    int sizeOfFontArray();

    void unsetExtLst();

    static {
        DocumentFactory<CTFontCollection> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontcollectiondd68type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
