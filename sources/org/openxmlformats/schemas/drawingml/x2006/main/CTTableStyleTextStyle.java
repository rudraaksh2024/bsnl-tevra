package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STOnOffStyleType;

public interface CTTableStyleTextStyle extends XmlObject {
    public static final DocumentFactory<CTTableStyleTextStyle> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTFontCollection addNewFont();

    CTFontReference addNewFontRef();

    CTHslColor addNewHslClr();

    CTPresetColor addNewPrstClr();

    CTSchemeColor addNewSchemeClr();

    CTScRgbColor addNewScrgbClr();

    CTSRgbColor addNewSrgbClr();

    CTSystemColor addNewSysClr();

    STOnOffStyleType.Enum getB();

    CTOfficeArtExtensionList getExtLst();

    CTFontCollection getFont();

    CTFontReference getFontRef();

    CTHslColor getHslClr();

    STOnOffStyleType.Enum getI();

    CTPresetColor getPrstClr();

    CTSchemeColor getSchemeClr();

    CTScRgbColor getScrgbClr();

    CTSRgbColor getSrgbClr();

    CTSystemColor getSysClr();

    boolean isSetB();

    boolean isSetExtLst();

    boolean isSetFont();

    boolean isSetFontRef();

    boolean isSetHslClr();

    boolean isSetI();

    boolean isSetPrstClr();

    boolean isSetSchemeClr();

    boolean isSetScrgbClr();

    boolean isSetSrgbClr();

    boolean isSetSysClr();

    void setB(STOnOffStyleType.Enum enumR);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFont(CTFontCollection cTFontCollection);

    void setFontRef(CTFontReference cTFontReference);

    void setHslClr(CTHslColor cTHslColor);

    void setI(STOnOffStyleType.Enum enumR);

    void setPrstClr(CTPresetColor cTPresetColor);

    void setSchemeClr(CTSchemeColor cTSchemeColor);

    void setScrgbClr(CTScRgbColor cTScRgbColor);

    void setSrgbClr(CTSRgbColor cTSRgbColor);

    void setSysClr(CTSystemColor cTSystemColor);

    void unsetB();

    void unsetExtLst();

    void unsetFont();

    void unsetFontRef();

    void unsetHslClr();

    void unsetI();

    void unsetPrstClr();

    void unsetSchemeClr();

    void unsetScrgbClr();

    void unsetSrgbClr();

    void unsetSysClr();

    STOnOffStyleType xgetB();

    STOnOffStyleType xgetI();

    void xsetB(STOnOffStyleType sTOnOffStyleType);

    void xsetI(STOnOffStyleType sTOnOffStyleType);

    static {
        DocumentFactory<CTTableStyleTextStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestyletextstylec932type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
