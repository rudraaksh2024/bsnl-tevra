package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme;

public interface CTFonts extends XmlObject {
    public static final DocumentFactory<CTFonts> Factory;
    public static final SchemaType type;

    String getAscii();

    STTheme.Enum getAsciiTheme();

    String getCs();

    STTheme.Enum getCstheme();

    String getEastAsia();

    STTheme.Enum getEastAsiaTheme();

    String getHAnsi();

    STTheme.Enum getHAnsiTheme();

    STHint.Enum getHint();

    boolean isSetAscii();

    boolean isSetAsciiTheme();

    boolean isSetCs();

    boolean isSetCstheme();

    boolean isSetEastAsia();

    boolean isSetEastAsiaTheme();

    boolean isSetHAnsi();

    boolean isSetHAnsiTheme();

    boolean isSetHint();

    void setAscii(String str);

    void setAsciiTheme(STTheme.Enum enumR);

    void setCs(String str);

    void setCstheme(STTheme.Enum enumR);

    void setEastAsia(String str);

    void setEastAsiaTheme(STTheme.Enum enumR);

    void setHAnsi(String str);

    void setHAnsiTheme(STTheme.Enum enumR);

    void setHint(STHint.Enum enumR);

    void unsetAscii();

    void unsetAsciiTheme();

    void unsetCs();

    void unsetCstheme();

    void unsetEastAsia();

    void unsetEastAsiaTheme();

    void unsetHAnsi();

    void unsetHAnsiTheme();

    void unsetHint();

    STString xgetAscii();

    STTheme xgetAsciiTheme();

    STString xgetCs();

    STTheme xgetCstheme();

    STString xgetEastAsia();

    STTheme xgetEastAsiaTheme();

    STString xgetHAnsi();

    STTheme xgetHAnsiTheme();

    STHint xgetHint();

    void xsetAscii(STString sTString);

    void xsetAsciiTheme(STTheme sTTheme);

    void xsetCs(STString sTString);

    void xsetCstheme(STTheme sTTheme);

    void xsetEastAsia(STString sTString);

    void xsetEastAsiaTheme(STTheme sTTheme);

    void xsetHAnsi(STString sTString);

    void xsetHAnsiTheme(STTheme sTTheme);

    void xsetHint(STHint sTHint);

    static {
        DocumentFactory<CTFonts> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfonts124etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
