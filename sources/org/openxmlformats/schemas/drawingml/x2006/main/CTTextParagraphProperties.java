package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;

public interface CTTextParagraphProperties extends XmlObject {
    public static final DocumentFactory<CTTextParagraphProperties> Factory;
    public static final SchemaType type;

    CTTextAutonumberBullet addNewBuAutoNum();

    CTTextBlipBullet addNewBuBlip();

    CTTextCharBullet addNewBuChar();

    CTColor addNewBuClr();

    CTTextBulletColorFollowText addNewBuClrTx();

    CTTextFont addNewBuFont();

    CTTextBulletTypefaceFollowText addNewBuFontTx();

    CTTextNoBullet addNewBuNone();

    CTTextBulletSizePercent addNewBuSzPct();

    CTTextBulletSizePoint addNewBuSzPts();

    CTTextBulletSizeFollowText addNewBuSzTx();

    CTTextCharacterProperties addNewDefRPr();

    CTOfficeArtExtensionList addNewExtLst();

    CTTextSpacing addNewLnSpc();

    CTTextSpacing addNewSpcAft();

    CTTextSpacing addNewSpcBef();

    CTTextTabStopList addNewTabLst();

    STTextAlignType.Enum getAlgn();

    CTTextAutonumberBullet getBuAutoNum();

    CTTextBlipBullet getBuBlip();

    CTTextCharBullet getBuChar();

    CTColor getBuClr();

    CTTextBulletColorFollowText getBuClrTx();

    CTTextFont getBuFont();

    CTTextBulletTypefaceFollowText getBuFontTx();

    CTTextNoBullet getBuNone();

    CTTextBulletSizePercent getBuSzPct();

    CTTextBulletSizePoint getBuSzPts();

    CTTextBulletSizeFollowText getBuSzTx();

    CTTextCharacterProperties getDefRPr();

    Object getDefTabSz();

    boolean getEaLnBrk();

    CTOfficeArtExtensionList getExtLst();

    STTextFontAlignType.Enum getFontAlgn();

    boolean getHangingPunct();

    int getIndent();

    boolean getLatinLnBrk();

    CTTextSpacing getLnSpc();

    int getLvl();

    int getMarL();

    int getMarR();

    boolean getRtl();

    CTTextSpacing getSpcAft();

    CTTextSpacing getSpcBef();

    CTTextTabStopList getTabLst();

    boolean isSetAlgn();

    boolean isSetBuAutoNum();

    boolean isSetBuBlip();

    boolean isSetBuChar();

    boolean isSetBuClr();

    boolean isSetBuClrTx();

    boolean isSetBuFont();

    boolean isSetBuFontTx();

    boolean isSetBuNone();

    boolean isSetBuSzPct();

    boolean isSetBuSzPts();

    boolean isSetBuSzTx();

    boolean isSetDefRPr();

    boolean isSetDefTabSz();

    boolean isSetEaLnBrk();

    boolean isSetExtLst();

    boolean isSetFontAlgn();

    boolean isSetHangingPunct();

    boolean isSetIndent();

    boolean isSetLatinLnBrk();

    boolean isSetLnSpc();

    boolean isSetLvl();

    boolean isSetMarL();

    boolean isSetMarR();

    boolean isSetRtl();

    boolean isSetSpcAft();

    boolean isSetSpcBef();

    boolean isSetTabLst();

    void setAlgn(STTextAlignType.Enum enumR);

    void setBuAutoNum(CTTextAutonumberBullet cTTextAutonumberBullet);

    void setBuBlip(CTTextBlipBullet cTTextBlipBullet);

    void setBuChar(CTTextCharBullet cTTextCharBullet);

    void setBuClr(CTColor cTColor);

    void setBuClrTx(CTTextBulletColorFollowText cTTextBulletColorFollowText);

    void setBuFont(CTTextFont cTTextFont);

    void setBuFontTx(CTTextBulletTypefaceFollowText cTTextBulletTypefaceFollowText);

    void setBuNone(CTTextNoBullet cTTextNoBullet);

    void setBuSzPct(CTTextBulletSizePercent cTTextBulletSizePercent);

    void setBuSzPts(CTTextBulletSizePoint cTTextBulletSizePoint);

    void setBuSzTx(CTTextBulletSizeFollowText cTTextBulletSizeFollowText);

    void setDefRPr(CTTextCharacterProperties cTTextCharacterProperties);

    void setDefTabSz(Object obj);

    void setEaLnBrk(boolean z);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFontAlgn(STTextFontAlignType.Enum enumR);

    void setHangingPunct(boolean z);

    void setIndent(int i);

    void setLatinLnBrk(boolean z);

    void setLnSpc(CTTextSpacing cTTextSpacing);

    void setLvl(int i);

    void setMarL(int i);

    void setMarR(int i);

    void setRtl(boolean z);

    void setSpcAft(CTTextSpacing cTTextSpacing);

    void setSpcBef(CTTextSpacing cTTextSpacing);

    void setTabLst(CTTextTabStopList cTTextTabStopList);

    void unsetAlgn();

    void unsetBuAutoNum();

    void unsetBuBlip();

    void unsetBuChar();

    void unsetBuClr();

    void unsetBuClrTx();

    void unsetBuFont();

    void unsetBuFontTx();

    void unsetBuNone();

    void unsetBuSzPct();

    void unsetBuSzPts();

    void unsetBuSzTx();

    void unsetDefRPr();

    void unsetDefTabSz();

    void unsetEaLnBrk();

    void unsetExtLst();

    void unsetFontAlgn();

    void unsetHangingPunct();

    void unsetIndent();

    void unsetLatinLnBrk();

    void unsetLnSpc();

    void unsetLvl();

    void unsetMarL();

    void unsetMarR();

    void unsetRtl();

    void unsetSpcAft();

    void unsetSpcBef();

    void unsetTabLst();

    STTextAlignType xgetAlgn();

    STCoordinate32 xgetDefTabSz();

    XmlBoolean xgetEaLnBrk();

    STTextFontAlignType xgetFontAlgn();

    XmlBoolean xgetHangingPunct();

    STTextIndent xgetIndent();

    XmlBoolean xgetLatinLnBrk();

    STTextIndentLevelType xgetLvl();

    STTextMargin xgetMarL();

    STTextMargin xgetMarR();

    XmlBoolean xgetRtl();

    void xsetAlgn(STTextAlignType sTTextAlignType);

    void xsetDefTabSz(STCoordinate32 sTCoordinate32);

    void xsetEaLnBrk(XmlBoolean xmlBoolean);

    void xsetFontAlgn(STTextFontAlignType sTTextFontAlignType);

    void xsetHangingPunct(XmlBoolean xmlBoolean);

    void xsetIndent(STTextIndent sTTextIndent);

    void xsetLatinLnBrk(XmlBoolean xmlBoolean);

    void xsetLvl(STTextIndentLevelType sTTextIndentLevelType);

    void xsetMarL(STTextMargin sTTextMargin);

    void xsetMarR(STTextMargin sTTextMargin);

    void xsetRtl(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTTextParagraphProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextparagraphpropertiesdd05type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
