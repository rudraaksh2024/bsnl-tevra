package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;

public interface CTBorder extends XmlObject {
    public static final DocumentFactory<CTBorder> Factory;
    public static final SchemaType type;

    Object getColor();

    Object getFrame();

    Object getShadow();

    BigInteger getSpace();

    BigInteger getSz();

    STThemeColor.Enum getThemeColor();

    byte[] getThemeShade();

    byte[] getThemeTint();

    STBorder.Enum getVal();

    boolean isSetColor();

    boolean isSetFrame();

    boolean isSetShadow();

    boolean isSetSpace();

    boolean isSetSz();

    boolean isSetThemeColor();

    boolean isSetThemeShade();

    boolean isSetThemeTint();

    void setColor(Object obj);

    void setFrame(Object obj);

    void setShadow(Object obj);

    void setSpace(BigInteger bigInteger);

    void setSz(BigInteger bigInteger);

    void setThemeColor(STThemeColor.Enum enumR);

    void setThemeShade(byte[] bArr);

    void setThemeTint(byte[] bArr);

    void setVal(STBorder.Enum enumR);

    void unsetColor();

    void unsetFrame();

    void unsetShadow();

    void unsetSpace();

    void unsetSz();

    void unsetThemeColor();

    void unsetThemeShade();

    void unsetThemeTint();

    STHexColor xgetColor();

    STOnOff xgetFrame();

    STOnOff xgetShadow();

    STPointMeasure xgetSpace();

    STEighthPointMeasure xgetSz();

    STThemeColor xgetThemeColor();

    STUcharHexNumber xgetThemeShade();

    STUcharHexNumber xgetThemeTint();

    STBorder xgetVal();

    void xsetColor(STHexColor sTHexColor);

    void xsetFrame(STOnOff sTOnOff);

    void xsetShadow(STOnOff sTOnOff);

    void xsetSpace(STPointMeasure sTPointMeasure);

    void xsetSz(STEighthPointMeasure sTEighthPointMeasure);

    void xsetThemeColor(STThemeColor sTThemeColor);

    void xsetThemeShade(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeTint(STUcharHexNumber sTUcharHexNumber);

    void xsetVal(STBorder sTBorder);

    static {
        DocumentFactory<CTBorder> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbordercdfctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
