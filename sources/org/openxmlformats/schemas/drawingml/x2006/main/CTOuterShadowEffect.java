package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;

public interface CTOuterShadowEffect extends XmlObject {
    public static final DocumentFactory<CTOuterShadowEffect> Factory;
    public static final SchemaType type;

    CTHslColor addNewHslClr();

    CTPresetColor addNewPrstClr();

    CTSchemeColor addNewSchemeClr();

    CTScRgbColor addNewScrgbClr();

    CTSRgbColor addNewSrgbClr();

    CTSystemColor addNewSysClr();

    STRectAlignment.Enum getAlgn();

    long getBlurRad();

    int getDir();

    long getDist();

    CTHslColor getHslClr();

    int getKx();

    int getKy();

    CTPresetColor getPrstClr();

    boolean getRotWithShape();

    CTSchemeColor getSchemeClr();

    CTScRgbColor getScrgbClr();

    CTSRgbColor getSrgbClr();

    Object getSx();

    Object getSy();

    CTSystemColor getSysClr();

    boolean isSetAlgn();

    boolean isSetBlurRad();

    boolean isSetDir();

    boolean isSetDist();

    boolean isSetHslClr();

    boolean isSetKx();

    boolean isSetKy();

    boolean isSetPrstClr();

    boolean isSetRotWithShape();

    boolean isSetSchemeClr();

    boolean isSetScrgbClr();

    boolean isSetSrgbClr();

    boolean isSetSx();

    boolean isSetSy();

    boolean isSetSysClr();

    void setAlgn(STRectAlignment.Enum enumR);

    void setBlurRad(long j);

    void setDir(int i);

    void setDist(long j);

    void setHslClr(CTHslColor cTHslColor);

    void setKx(int i);

    void setKy(int i);

    void setPrstClr(CTPresetColor cTPresetColor);

    void setRotWithShape(boolean z);

    void setSchemeClr(CTSchemeColor cTSchemeColor);

    void setScrgbClr(CTScRgbColor cTScRgbColor);

    void setSrgbClr(CTSRgbColor cTSRgbColor);

    void setSx(Object obj);

    void setSy(Object obj);

    void setSysClr(CTSystemColor cTSystemColor);

    void unsetAlgn();

    void unsetBlurRad();

    void unsetDir();

    void unsetDist();

    void unsetHslClr();

    void unsetKx();

    void unsetKy();

    void unsetPrstClr();

    void unsetRotWithShape();

    void unsetSchemeClr();

    void unsetScrgbClr();

    void unsetSrgbClr();

    void unsetSx();

    void unsetSy();

    void unsetSysClr();

    STRectAlignment xgetAlgn();

    STPositiveCoordinate xgetBlurRad();

    STPositiveFixedAngle xgetDir();

    STPositiveCoordinate xgetDist();

    STFixedAngle xgetKx();

    STFixedAngle xgetKy();

    XmlBoolean xgetRotWithShape();

    STPercentage xgetSx();

    STPercentage xgetSy();

    void xsetAlgn(STRectAlignment sTRectAlignment);

    void xsetBlurRad(STPositiveCoordinate sTPositiveCoordinate);

    void xsetDir(STPositiveFixedAngle sTPositiveFixedAngle);

    void xsetDist(STPositiveCoordinate sTPositiveCoordinate);

    void xsetKx(STFixedAngle sTFixedAngle);

    void xsetKy(STFixedAngle sTFixedAngle);

    void xsetRotWithShape(XmlBoolean xmlBoolean);

    void xsetSx(STPercentage sTPercentage);

    void xsetSy(STPercentage sTPercentage);

    static {
        DocumentFactory<CTOuterShadowEffect> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctoutershadoweffect7b5dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
