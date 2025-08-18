package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;

public interface CTBackground extends XmlObject {
    public static final DocumentFactory<CTBackground> Factory;
    public static final SchemaType type;

    CTDrawing addNewDrawing();

    Object getColor();

    CTDrawing getDrawing();

    STThemeColor.Enum getThemeColor();

    byte[] getThemeShade();

    byte[] getThemeTint();

    boolean isSetColor();

    boolean isSetDrawing();

    boolean isSetThemeColor();

    boolean isSetThemeShade();

    boolean isSetThemeTint();

    void setColor(Object obj);

    void setDrawing(CTDrawing cTDrawing);

    void setThemeColor(STThemeColor.Enum enumR);

    void setThemeShade(byte[] bArr);

    void setThemeTint(byte[] bArr);

    void unsetColor();

    void unsetDrawing();

    void unsetThemeColor();

    void unsetThemeShade();

    void unsetThemeTint();

    STHexColor xgetColor();

    STThemeColor xgetThemeColor();

    STUcharHexNumber xgetThemeShade();

    STUcharHexNumber xgetThemeTint();

    void xsetColor(STHexColor sTHexColor);

    void xsetThemeColor(STThemeColor sTThemeColor);

    void xsetThemeShade(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeTint(STUcharHexNumber sTUcharHexNumber);

    static {
        DocumentFactory<CTBackground> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbackground96batype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
