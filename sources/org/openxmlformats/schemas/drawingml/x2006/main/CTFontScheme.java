package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFontScheme extends XmlObject {
    public static final DocumentFactory<CTFontScheme> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTFontCollection addNewMajorFont();

    CTFontCollection addNewMinorFont();

    CTOfficeArtExtensionList getExtLst();

    CTFontCollection getMajorFont();

    CTFontCollection getMinorFont();

    String getName();

    boolean isSetExtLst();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setMajorFont(CTFontCollection cTFontCollection);

    void setMinorFont(CTFontCollection cTFontCollection);

    void setName(String str);

    void unsetExtLst();

    XmlString xgetName();

    void xsetName(XmlString xmlString);

    static {
        DocumentFactory<CTFontScheme> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontscheme232ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
