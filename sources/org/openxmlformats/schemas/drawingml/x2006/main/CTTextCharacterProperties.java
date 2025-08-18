package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STLang;

public interface CTTextCharacterProperties extends XmlObject {
    public static final DocumentFactory<CTTextCharacterProperties> Factory;
    public static final SchemaType type;

    CTBlipFillProperties addNewBlipFill();

    CTTextFont addNewCs();

    CTTextFont addNewEa();

    CTEffectContainer addNewEffectDag();

    CTEffectList addNewEffectLst();

    CTOfficeArtExtensionList addNewExtLst();

    CTGradientFillProperties addNewGradFill();

    CTGroupFillProperties addNewGrpFill();

    CTColor addNewHighlight();

    CTHyperlink addNewHlinkClick();

    CTHyperlink addNewHlinkMouseOver();

    CTTextFont addNewLatin();

    CTLineProperties addNewLn();

    CTNoFillProperties addNewNoFill();

    CTPatternFillProperties addNewPattFill();

    CTBoolean addNewRtl();

    CTSolidColorFillProperties addNewSolidFill();

    CTTextFont addNewSym();

    CTTextUnderlineFillGroupWrapper addNewUFill();

    CTTextUnderlineFillFollowText addNewUFillTx();

    CTLineProperties addNewULn();

    CTTextUnderlineLineFollowText addNewULnTx();

    String getAltLang();

    boolean getB();

    Object getBaseline();

    CTBlipFillProperties getBlipFill();

    String getBmk();

    STTextCapsType.Enum getCap();

    CTTextFont getCs();

    boolean getDirty();

    CTTextFont getEa();

    CTEffectContainer getEffectDag();

    CTEffectList getEffectLst();

    boolean getErr();

    CTOfficeArtExtensionList getExtLst();

    CTGradientFillProperties getGradFill();

    CTGroupFillProperties getGrpFill();

    CTColor getHighlight();

    CTHyperlink getHlinkClick();

    CTHyperlink getHlinkMouseOver();

    boolean getI();

    int getKern();

    boolean getKumimoji();

    String getLang();

    CTTextFont getLatin();

    CTLineProperties getLn();

    CTNoFillProperties getNoFill();

    boolean getNoProof();

    boolean getNormalizeH();

    CTPatternFillProperties getPattFill();

    CTBoolean getRtl();

    boolean getSmtClean();

    long getSmtId();

    CTSolidColorFillProperties getSolidFill();

    Object getSpc();

    STTextStrikeType.Enum getStrike();

    CTTextFont getSym();

    int getSz();

    STTextUnderlineType.Enum getU();

    CTTextUnderlineFillGroupWrapper getUFill();

    CTTextUnderlineFillFollowText getUFillTx();

    CTLineProperties getULn();

    CTTextUnderlineLineFollowText getULnTx();

    boolean isSetAltLang();

    boolean isSetB();

    boolean isSetBaseline();

    boolean isSetBlipFill();

    boolean isSetBmk();

    boolean isSetCap();

    boolean isSetCs();

    boolean isSetDirty();

    boolean isSetEa();

    boolean isSetEffectDag();

    boolean isSetEffectLst();

    boolean isSetErr();

    boolean isSetExtLst();

    boolean isSetGradFill();

    boolean isSetGrpFill();

    boolean isSetHighlight();

    boolean isSetHlinkClick();

    boolean isSetHlinkMouseOver();

    boolean isSetI();

    boolean isSetKern();

    boolean isSetKumimoji();

    boolean isSetLang();

    boolean isSetLatin();

    boolean isSetLn();

    boolean isSetNoFill();

    boolean isSetNoProof();

    boolean isSetNormalizeH();

    boolean isSetPattFill();

    boolean isSetRtl();

    boolean isSetSmtClean();

    boolean isSetSmtId();

    boolean isSetSolidFill();

    boolean isSetSpc();

    boolean isSetStrike();

    boolean isSetSym();

    boolean isSetSz();

    boolean isSetU();

    boolean isSetUFill();

    boolean isSetUFillTx();

    boolean isSetULn();

    boolean isSetULnTx();

    void setAltLang(String str);

    void setB(boolean z);

    void setBaseline(Object obj);

    void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

    void setBmk(String str);

    void setCap(STTextCapsType.Enum enumR);

    void setCs(CTTextFont cTTextFont);

    void setDirty(boolean z);

    void setEa(CTTextFont cTTextFont);

    void setEffectDag(CTEffectContainer cTEffectContainer);

    void setEffectLst(CTEffectList cTEffectList);

    void setErr(boolean z);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGradFill(CTGradientFillProperties cTGradientFillProperties);

    void setGrpFill(CTGroupFillProperties cTGroupFillProperties);

    void setHighlight(CTColor cTColor);

    void setHlinkClick(CTHyperlink cTHyperlink);

    void setHlinkMouseOver(CTHyperlink cTHyperlink);

    void setI(boolean z);

    void setKern(int i);

    void setKumimoji(boolean z);

    void setLang(String str);

    void setLatin(CTTextFont cTTextFont);

    void setLn(CTLineProperties cTLineProperties);

    void setNoFill(CTNoFillProperties cTNoFillProperties);

    void setNoProof(boolean z);

    void setNormalizeH(boolean z);

    void setPattFill(CTPatternFillProperties cTPatternFillProperties);

    void setRtl(CTBoolean cTBoolean);

    void setSmtClean(boolean z);

    void setSmtId(long j);

    void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties);

    void setSpc(Object obj);

    void setStrike(STTextStrikeType.Enum enumR);

    void setSym(CTTextFont cTTextFont);

    void setSz(int i);

    void setU(STTextUnderlineType.Enum enumR);

    void setUFill(CTTextUnderlineFillGroupWrapper cTTextUnderlineFillGroupWrapper);

    void setUFillTx(CTTextUnderlineFillFollowText cTTextUnderlineFillFollowText);

    void setULn(CTLineProperties cTLineProperties);

    void setULnTx(CTTextUnderlineLineFollowText cTTextUnderlineLineFollowText);

    void unsetAltLang();

    void unsetB();

    void unsetBaseline();

    void unsetBlipFill();

    void unsetBmk();

    void unsetCap();

    void unsetCs();

    void unsetDirty();

    void unsetEa();

    void unsetEffectDag();

    void unsetEffectLst();

    void unsetErr();

    void unsetExtLst();

    void unsetGradFill();

    void unsetGrpFill();

    void unsetHighlight();

    void unsetHlinkClick();

    void unsetHlinkMouseOver();

    void unsetI();

    void unsetKern();

    void unsetKumimoji();

    void unsetLang();

    void unsetLatin();

    void unsetLn();

    void unsetNoFill();

    void unsetNoProof();

    void unsetNormalizeH();

    void unsetPattFill();

    void unsetRtl();

    void unsetSmtClean();

    void unsetSmtId();

    void unsetSolidFill();

    void unsetSpc();

    void unsetStrike();

    void unsetSym();

    void unsetSz();

    void unsetU();

    void unsetUFill();

    void unsetUFillTx();

    void unsetULn();

    void unsetULnTx();

    STLang xgetAltLang();

    XmlBoolean xgetB();

    STPercentage xgetBaseline();

    XmlString xgetBmk();

    STTextCapsType xgetCap();

    XmlBoolean xgetDirty();

    XmlBoolean xgetErr();

    XmlBoolean xgetI();

    STTextNonNegativePoint xgetKern();

    XmlBoolean xgetKumimoji();

    STLang xgetLang();

    XmlBoolean xgetNoProof();

    XmlBoolean xgetNormalizeH();

    XmlBoolean xgetSmtClean();

    XmlUnsignedInt xgetSmtId();

    STTextPoint xgetSpc();

    STTextStrikeType xgetStrike();

    STTextFontSize xgetSz();

    STTextUnderlineType xgetU();

    void xsetAltLang(STLang sTLang);

    void xsetB(XmlBoolean xmlBoolean);

    void xsetBaseline(STPercentage sTPercentage);

    void xsetBmk(XmlString xmlString);

    void xsetCap(STTextCapsType sTTextCapsType);

    void xsetDirty(XmlBoolean xmlBoolean);

    void xsetErr(XmlBoolean xmlBoolean);

    void xsetI(XmlBoolean xmlBoolean);

    void xsetKern(STTextNonNegativePoint sTTextNonNegativePoint);

    void xsetKumimoji(XmlBoolean xmlBoolean);

    void xsetLang(STLang sTLang);

    void xsetNoProof(XmlBoolean xmlBoolean);

    void xsetNormalizeH(XmlBoolean xmlBoolean);

    void xsetSmtClean(XmlBoolean xmlBoolean);

    void xsetSmtId(XmlUnsignedInt xmlUnsignedInt);

    void xsetSpc(STTextPoint sTTextPoint);

    void xsetStrike(STTextStrikeType sTTextStrikeType);

    void xsetSz(STTextFontSize sTTextFontSize);

    void xsetU(STTextUnderlineType sTTextUnderlineType);

    static {
        DocumentFactory<CTTextCharacterProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextcharacterproperties76c0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
