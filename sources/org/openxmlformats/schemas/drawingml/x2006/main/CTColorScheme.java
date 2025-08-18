package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTColorScheme extends XmlObject {
    public static final DocumentFactory<CTColorScheme> Factory;
    public static final SchemaType type;

    CTColor addNewAccent1();

    CTColor addNewAccent2();

    CTColor addNewAccent3();

    CTColor addNewAccent4();

    CTColor addNewAccent5();

    CTColor addNewAccent6();

    CTColor addNewDk1();

    CTColor addNewDk2();

    CTOfficeArtExtensionList addNewExtLst();

    CTColor addNewFolHlink();

    CTColor addNewHlink();

    CTColor addNewLt1();

    CTColor addNewLt2();

    CTColor getAccent1();

    CTColor getAccent2();

    CTColor getAccent3();

    CTColor getAccent4();

    CTColor getAccent5();

    CTColor getAccent6();

    CTColor getDk1();

    CTColor getDk2();

    CTOfficeArtExtensionList getExtLst();

    CTColor getFolHlink();

    CTColor getHlink();

    CTColor getLt1();

    CTColor getLt2();

    String getName();

    boolean isSetExtLst();

    void setAccent1(CTColor cTColor);

    void setAccent2(CTColor cTColor);

    void setAccent3(CTColor cTColor);

    void setAccent4(CTColor cTColor);

    void setAccent5(CTColor cTColor);

    void setAccent6(CTColor cTColor);

    void setDk1(CTColor cTColor);

    void setDk2(CTColor cTColor);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFolHlink(CTColor cTColor);

    void setHlink(CTColor cTColor);

    void setLt1(CTColor cTColor);

    void setLt2(CTColor cTColor);

    void setName(String str);

    void unsetExtLst();

    XmlString xgetName();

    void xsetName(XmlString xmlString);

    static {
        DocumentFactory<CTColorScheme> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolorscheme0e99type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
