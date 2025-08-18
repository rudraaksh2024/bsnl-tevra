package com.microsoft.schemas.vml;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.office.CTCallout;
import com.microsoft.schemas.office.office.CTClipPath;
import com.microsoft.schemas.office.office.CTExtrusion;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.office.office.CTSkew;
import com.microsoft.schemas.office.office.STBWMode;
import com.microsoft.schemas.office.office.STConnectorType;
import com.microsoft.schemas.office.office.STDiagramLayout;
import com.microsoft.schemas.office.office.STHrAlign;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.powerpoint.CTRel;
import com.microsoft.schemas.office.word.CTAnchorLock;
import com.microsoft.schemas.office.word.CTBorder;
import com.microsoft.schemas.office.word.CTWrap;
import java.math.BigInteger;
import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

public interface CTRect extends XmlObject {
    public static final DocumentFactory<CTRect> Factory;
    public static final SchemaType type;

    CTAnchorLock addNewAnchorlock();

    CTBorder addNewBorderbottom();

    CTBorder addNewBorderleft();

    CTBorder addNewBorderright();

    CTBorder addNewBordertop();

    CTCallout addNewCallout();

    CTClientData addNewClientData();

    CTClipPath addNewClippath();

    CTExtrusion addNewExtrusion();

    CTFill addNewFill();

    CTFormulas addNewFormulas();

    CTHandles addNewHandles();

    CTImageData addNewImagedata();

    CTLock addNewLock();

    CTPath addNewPath();

    CTShadow addNewShadow();

    CTSignatureLine addNewSignatureline();

    CTSkew addNewSkew();

    CTStroke addNewStroke();

    CTTextbox addNewTextbox();

    CTRel addNewTextdata();

    CTTextPath addNewTextpath();

    CTWrap addNewWrap();

    STTrueFalse.Enum getAllowincell();

    STTrueFalse.Enum getAllowoverlap();

    String getAlt();

    CTAnchorLock getAnchorlockArray(int i);

    CTAnchorLock[] getAnchorlockArray();

    List<CTAnchorLock> getAnchorlockList();

    CTBorder getBorderbottomArray(int i);

    CTBorder[] getBorderbottomArray();

    List<CTBorder> getBorderbottomList();

    String getBorderbottomcolor();

    CTBorder getBorderleftArray(int i);

    CTBorder[] getBorderleftArray();

    List<CTBorder> getBorderleftList();

    String getBorderleftcolor();

    CTBorder getBorderrightArray(int i);

    CTBorder[] getBorderrightArray();

    List<CTBorder> getBorderrightList();

    String getBorderrightcolor();

    CTBorder getBordertopArray(int i);

    CTBorder[] getBordertopArray();

    List<CTBorder> getBordertopList();

    String getBordertopcolor();

    STTrueFalse.Enum getBullet();

    STTrueFalse.Enum getButton();

    STBWMode.Enum getBwmode();

    STBWMode.Enum getBwnormal();

    STBWMode.Enum getBwpure();

    CTCallout getCalloutArray(int i);

    CTCallout[] getCalloutArray();

    List<CTCallout> getCalloutList();

    String getChromakey();

    String getClass1();

    CTClientData getClientDataArray(int i);

    CTClientData[] getClientDataArray();

    List<CTClientData> getClientDataList();

    STTrueFalse.Enum getClip();

    CTClipPath getClippathArray(int i);

    CTClipPath[] getClippathArray();

    List<CTClipPath> getClippathList();

    STTrueFalse.Enum getCliptowrap();

    STConnectorType.Enum getConnectortype();

    String getCoordorigin();

    String getCoordsize();

    BigInteger getDgmlayout();

    BigInteger getDgmlayoutmru();

    BigInteger getDgmnodekind();

    STTrueFalse.Enum getDoubleclicknotify();

    CTExtrusion getExtrusionArray(int i);

    CTExtrusion[] getExtrusionArray();

    List<CTExtrusion> getExtrusionList();

    CTFill getFillArray(int i);

    CTFill[] getFillArray();

    List<CTFill> getFillList();

    String getFillcolor();

    STTrueFalse.Enum getFilled();

    STTrueFalse.Enum getForcedash();

    CTFormulas getFormulasArray(int i);

    CTFormulas[] getFormulasArray();

    List<CTFormulas> getFormulasList();

    CTHandles getHandlesArray(int i);

    CTHandles[] getHandlesArray();

    List<CTHandles> getHandlesList();

    STTrueFalse.Enum getHr();

    STHrAlign.Enum getHralign();

    String getHref();

    STTrueFalse.Enum getHrnoshade();

    float getHrpct();

    STTrueFalse.Enum getHrstd();

    String getId();

    CTImageData getImagedataArray(int i);

    CTImageData[] getImagedataArray();

    List<CTImageData> getImagedataList();

    STInsetMode.Enum getInsetmode();

    STTrueFalse.Enum getInsetpen();

    CTLock getLockArray(int i);

    CTLock[] getLockArray();

    List<CTLock> getLockList();

    STTrueFalseBlank.Enum getOle();

    STTrueFalse.Enum getOleicon();

    STTrueFalse.Enum getOned();

    String getOpacity();

    CTPath getPathArray(int i);

    CTPath[] getPathArray();

    List<CTPath> getPathList();

    STTrueFalse.Enum getPreferrelative();

    STTrueFalse.Enum getPrint();

    BigInteger getRegroupid();

    CTShadow getShadowArray(int i);

    CTShadow[] getShadowArray();

    List<CTShadow> getShadowList();

    CTSignatureLine getSignaturelineArray(int i);

    CTSignatureLine[] getSignaturelineArray();

    List<CTSignatureLine> getSignaturelineList();

    CTSkew getSkewArray(int i);

    CTSkew[] getSkewArray();

    List<CTSkew> getSkewList();

    String getSpid();

    float getSpt();

    CTStroke getStrokeArray(int i);

    CTStroke[] getStrokeArray();

    List<CTStroke> getStrokeList();

    String getStrokecolor();

    STTrueFalse.Enum getStroked();

    String getStrokeweight();

    String getStyle();

    String getTarget();

    CTTextbox getTextboxArray(int i);

    CTTextbox[] getTextboxArray();

    List<CTTextbox> getTextboxList();

    CTRel getTextdataArray(int i);

    CTRel[] getTextdataArray();

    List<CTRel> getTextdataList();

    CTTextPath getTextpathArray(int i);

    CTTextPath[] getTextpathArray();

    List<CTTextPath> getTextpathList();

    String getTitle();

    STTrueFalse.Enum getUserdrawn();

    STTrueFalse.Enum getUserhidden();

    CTWrap getWrapArray(int i);

    CTWrap[] getWrapArray();

    List<CTWrap> getWrapList();

    String getWrapcoords();

    CTAnchorLock insertNewAnchorlock(int i);

    CTBorder insertNewBorderbottom(int i);

    CTBorder insertNewBorderleft(int i);

    CTBorder insertNewBorderright(int i);

    CTBorder insertNewBordertop(int i);

    CTCallout insertNewCallout(int i);

    CTClientData insertNewClientData(int i);

    CTClipPath insertNewClippath(int i);

    CTExtrusion insertNewExtrusion(int i);

    CTFill insertNewFill(int i);

    CTFormulas insertNewFormulas(int i);

    CTHandles insertNewHandles(int i);

    CTImageData insertNewImagedata(int i);

    CTLock insertNewLock(int i);

    CTPath insertNewPath(int i);

    CTShadow insertNewShadow(int i);

    CTSignatureLine insertNewSignatureline(int i);

    CTSkew insertNewSkew(int i);

    CTStroke insertNewStroke(int i);

    CTTextbox insertNewTextbox(int i);

    CTRel insertNewTextdata(int i);

    CTTextPath insertNewTextpath(int i);

    CTWrap insertNewWrap(int i);

    boolean isSetAllowincell();

    boolean isSetAllowoverlap();

    boolean isSetAlt();

    boolean isSetBorderbottomcolor();

    boolean isSetBorderleftcolor();

    boolean isSetBorderrightcolor();

    boolean isSetBordertopcolor();

    boolean isSetBullet();

    boolean isSetButton();

    boolean isSetBwmode();

    boolean isSetBwnormal();

    boolean isSetBwpure();

    boolean isSetChromakey();

    boolean isSetClass1();

    boolean isSetClip();

    boolean isSetCliptowrap();

    boolean isSetConnectortype();

    boolean isSetCoordorigin();

    boolean isSetCoordsize();

    boolean isSetDgmlayout();

    boolean isSetDgmlayoutmru();

    boolean isSetDgmnodekind();

    boolean isSetDoubleclicknotify();

    boolean isSetFillcolor();

    boolean isSetFilled();

    boolean isSetForcedash();

    boolean isSetHr();

    boolean isSetHralign();

    boolean isSetHref();

    boolean isSetHrnoshade();

    boolean isSetHrpct();

    boolean isSetHrstd();

    boolean isSetId();

    boolean isSetInsetmode();

    boolean isSetInsetpen();

    boolean isSetOle();

    boolean isSetOleicon();

    boolean isSetOned();

    boolean isSetOpacity();

    boolean isSetPreferrelative();

    boolean isSetPrint();

    boolean isSetRegroupid();

    boolean isSetSpid();

    boolean isSetSpt();

    boolean isSetStrokecolor();

    boolean isSetStroked();

    boolean isSetStrokeweight();

    boolean isSetStyle();

    boolean isSetTarget();

    boolean isSetTitle();

    boolean isSetUserdrawn();

    boolean isSetUserhidden();

    boolean isSetWrapcoords();

    void removeAnchorlock(int i);

    void removeBorderbottom(int i);

    void removeBorderleft(int i);

    void removeBorderright(int i);

    void removeBordertop(int i);

    void removeCallout(int i);

    void removeClientData(int i);

    void removeClippath(int i);

    void removeExtrusion(int i);

    void removeFill(int i);

    void removeFormulas(int i);

    void removeHandles(int i);

    void removeImagedata(int i);

    void removeLock(int i);

    void removePath(int i);

    void removeShadow(int i);

    void removeSignatureline(int i);

    void removeSkew(int i);

    void removeStroke(int i);

    void removeTextbox(int i);

    void removeTextdata(int i);

    void removeTextpath(int i);

    void removeWrap(int i);

    void setAllowincell(STTrueFalse.Enum enumR);

    void setAllowoverlap(STTrueFalse.Enum enumR);

    void setAlt(String str);

    void setAnchorlockArray(int i, CTAnchorLock cTAnchorLock);

    void setAnchorlockArray(CTAnchorLock[] cTAnchorLockArr);

    void setBorderbottomArray(int i, CTBorder cTBorder);

    void setBorderbottomArray(CTBorder[] cTBorderArr);

    void setBorderbottomcolor(String str);

    void setBorderleftArray(int i, CTBorder cTBorder);

    void setBorderleftArray(CTBorder[] cTBorderArr);

    void setBorderleftcolor(String str);

    void setBorderrightArray(int i, CTBorder cTBorder);

    void setBorderrightArray(CTBorder[] cTBorderArr);

    void setBorderrightcolor(String str);

    void setBordertopArray(int i, CTBorder cTBorder);

    void setBordertopArray(CTBorder[] cTBorderArr);

    void setBordertopcolor(String str);

    void setBullet(STTrueFalse.Enum enumR);

    void setButton(STTrueFalse.Enum enumR);

    void setBwmode(STBWMode.Enum enumR);

    void setBwnormal(STBWMode.Enum enumR);

    void setBwpure(STBWMode.Enum enumR);

    void setCalloutArray(int i, CTCallout cTCallout);

    void setCalloutArray(CTCallout[] cTCalloutArr);

    void setChromakey(String str);

    void setClass1(String str);

    void setClientDataArray(int i, CTClientData cTClientData);

    void setClientDataArray(CTClientData[] cTClientDataArr);

    void setClip(STTrueFalse.Enum enumR);

    void setClippathArray(int i, CTClipPath cTClipPath);

    void setClippathArray(CTClipPath[] cTClipPathArr);

    void setCliptowrap(STTrueFalse.Enum enumR);

    void setConnectortype(STConnectorType.Enum enumR);

    void setCoordorigin(String str);

    void setCoordsize(String str);

    void setDgmlayout(BigInteger bigInteger);

    void setDgmlayoutmru(BigInteger bigInteger);

    void setDgmnodekind(BigInteger bigInteger);

    void setDoubleclicknotify(STTrueFalse.Enum enumR);

    void setExtrusionArray(int i, CTExtrusion cTExtrusion);

    void setExtrusionArray(CTExtrusion[] cTExtrusionArr);

    void setFillArray(int i, CTFill cTFill);

    void setFillArray(CTFill[] cTFillArr);

    void setFillcolor(String str);

    void setFilled(STTrueFalse.Enum enumR);

    void setForcedash(STTrueFalse.Enum enumR);

    void setFormulasArray(int i, CTFormulas cTFormulas);

    void setFormulasArray(CTFormulas[] cTFormulasArr);

    void setHandlesArray(int i, CTHandles cTHandles);

    void setHandlesArray(CTHandles[] cTHandlesArr);

    void setHr(STTrueFalse.Enum enumR);

    void setHralign(STHrAlign.Enum enumR);

    void setHref(String str);

    void setHrnoshade(STTrueFalse.Enum enumR);

    void setHrpct(float f);

    void setHrstd(STTrueFalse.Enum enumR);

    void setId(String str);

    void setImagedataArray(int i, CTImageData cTImageData);

    void setImagedataArray(CTImageData[] cTImageDataArr);

    void setInsetmode(STInsetMode.Enum enumR);

    void setInsetpen(STTrueFalse.Enum enumR);

    void setLockArray(int i, CTLock cTLock);

    void setLockArray(CTLock[] cTLockArr);

    void setOle(STTrueFalseBlank.Enum enumR);

    void setOleicon(STTrueFalse.Enum enumR);

    void setOned(STTrueFalse.Enum enumR);

    void setOpacity(String str);

    void setPathArray(int i, CTPath cTPath);

    void setPathArray(CTPath[] cTPathArr);

    void setPreferrelative(STTrueFalse.Enum enumR);

    void setPrint(STTrueFalse.Enum enumR);

    void setRegroupid(BigInteger bigInteger);

    void setShadowArray(int i, CTShadow cTShadow);

    void setShadowArray(CTShadow[] cTShadowArr);

    void setSignaturelineArray(int i, CTSignatureLine cTSignatureLine);

    void setSignaturelineArray(CTSignatureLine[] cTSignatureLineArr);

    void setSkewArray(int i, CTSkew cTSkew);

    void setSkewArray(CTSkew[] cTSkewArr);

    void setSpid(String str);

    void setSpt(float f);

    void setStrokeArray(int i, CTStroke cTStroke);

    void setStrokeArray(CTStroke[] cTStrokeArr);

    void setStrokecolor(String str);

    void setStroked(STTrueFalse.Enum enumR);

    void setStrokeweight(String str);

    void setStyle(String str);

    void setTarget(String str);

    void setTextboxArray(int i, CTTextbox cTTextbox);

    void setTextboxArray(CTTextbox[] cTTextboxArr);

    void setTextdataArray(int i, CTRel cTRel);

    void setTextdataArray(CTRel[] cTRelArr);

    void setTextpathArray(int i, CTTextPath cTTextPath);

    void setTextpathArray(CTTextPath[] cTTextPathArr);

    void setTitle(String str);

    void setUserdrawn(STTrueFalse.Enum enumR);

    void setUserhidden(STTrueFalse.Enum enumR);

    void setWrapArray(int i, CTWrap cTWrap);

    void setWrapArray(CTWrap[] cTWrapArr);

    void setWrapcoords(String str);

    int sizeOfAnchorlockArray();

    int sizeOfBorderbottomArray();

    int sizeOfBorderleftArray();

    int sizeOfBorderrightArray();

    int sizeOfBordertopArray();

    int sizeOfCalloutArray();

    int sizeOfClientDataArray();

    int sizeOfClippathArray();

    int sizeOfExtrusionArray();

    int sizeOfFillArray();

    int sizeOfFormulasArray();

    int sizeOfHandlesArray();

    int sizeOfImagedataArray();

    int sizeOfLockArray();

    int sizeOfPathArray();

    int sizeOfShadowArray();

    int sizeOfSignaturelineArray();

    int sizeOfSkewArray();

    int sizeOfStrokeArray();

    int sizeOfTextboxArray();

    int sizeOfTextdataArray();

    int sizeOfTextpathArray();

    int sizeOfWrapArray();

    void unsetAllowincell();

    void unsetAllowoverlap();

    void unsetAlt();

    void unsetBorderbottomcolor();

    void unsetBorderleftcolor();

    void unsetBorderrightcolor();

    void unsetBordertopcolor();

    void unsetBullet();

    void unsetButton();

    void unsetBwmode();

    void unsetBwnormal();

    void unsetBwpure();

    void unsetChromakey();

    void unsetClass1();

    void unsetClip();

    void unsetCliptowrap();

    void unsetConnectortype();

    void unsetCoordorigin();

    void unsetCoordsize();

    void unsetDgmlayout();

    void unsetDgmlayoutmru();

    void unsetDgmnodekind();

    void unsetDoubleclicknotify();

    void unsetFillcolor();

    void unsetFilled();

    void unsetForcedash();

    void unsetHr();

    void unsetHralign();

    void unsetHref();

    void unsetHrnoshade();

    void unsetHrpct();

    void unsetHrstd();

    void unsetId();

    void unsetInsetmode();

    void unsetInsetpen();

    void unsetOle();

    void unsetOleicon();

    void unsetOned();

    void unsetOpacity();

    void unsetPreferrelative();

    void unsetPrint();

    void unsetRegroupid();

    void unsetSpid();

    void unsetSpt();

    void unsetStrokecolor();

    void unsetStroked();

    void unsetStrokeweight();

    void unsetStyle();

    void unsetTarget();

    void unsetTitle();

    void unsetUserdrawn();

    void unsetUserhidden();

    void unsetWrapcoords();

    STTrueFalse xgetAllowincell();

    STTrueFalse xgetAllowoverlap();

    XmlString xgetAlt();

    XmlString xgetBorderbottomcolor();

    XmlString xgetBorderleftcolor();

    XmlString xgetBorderrightcolor();

    XmlString xgetBordertopcolor();

    STTrueFalse xgetBullet();

    STTrueFalse xgetButton();

    STBWMode xgetBwmode();

    STBWMode xgetBwnormal();

    STBWMode xgetBwpure();

    STColorType xgetChromakey();

    XmlString xgetClass1();

    STTrueFalse xgetClip();

    STTrueFalse xgetCliptowrap();

    STConnectorType xgetConnectortype();

    XmlString xgetCoordorigin();

    XmlString xgetCoordsize();

    STDiagramLayout xgetDgmlayout();

    STDiagramLayout xgetDgmlayoutmru();

    XmlInteger xgetDgmnodekind();

    STTrueFalse xgetDoubleclicknotify();

    STColorType xgetFillcolor();

    STTrueFalse xgetFilled();

    STTrueFalse xgetForcedash();

    STTrueFalse xgetHr();

    STHrAlign xgetHralign();

    XmlString xgetHref();

    STTrueFalse xgetHrnoshade();

    XmlFloat xgetHrpct();

    STTrueFalse xgetHrstd();

    XmlString xgetId();

    STInsetMode xgetInsetmode();

    STTrueFalse xgetInsetpen();

    STTrueFalseBlank xgetOle();

    STTrueFalse xgetOleicon();

    STTrueFalse xgetOned();

    XmlString xgetOpacity();

    STTrueFalse xgetPreferrelative();

    STTrueFalse xgetPrint();

    XmlInteger xgetRegroupid();

    XmlString xgetSpid();

    XmlFloat xgetSpt();

    STColorType xgetStrokecolor();

    STTrueFalse xgetStroked();

    XmlString xgetStrokeweight();

    XmlString xgetStyle();

    XmlString xgetTarget();

    XmlString xgetTitle();

    STTrueFalse xgetUserdrawn();

    STTrueFalse xgetUserhidden();

    XmlString xgetWrapcoords();

    void xsetAllowincell(STTrueFalse sTTrueFalse);

    void xsetAllowoverlap(STTrueFalse sTTrueFalse);

    void xsetAlt(XmlString xmlString);

    void xsetBorderbottomcolor(XmlString xmlString);

    void xsetBorderleftcolor(XmlString xmlString);

    void xsetBorderrightcolor(XmlString xmlString);

    void xsetBordertopcolor(XmlString xmlString);

    void xsetBullet(STTrueFalse sTTrueFalse);

    void xsetButton(STTrueFalse sTTrueFalse);

    void xsetBwmode(STBWMode sTBWMode);

    void xsetBwnormal(STBWMode sTBWMode);

    void xsetBwpure(STBWMode sTBWMode);

    void xsetChromakey(STColorType sTColorType);

    void xsetClass1(XmlString xmlString);

    void xsetClip(STTrueFalse sTTrueFalse);

    void xsetCliptowrap(STTrueFalse sTTrueFalse);

    void xsetConnectortype(STConnectorType sTConnectorType);

    void xsetCoordorigin(XmlString xmlString);

    void xsetCoordsize(XmlString xmlString);

    void xsetDgmlayout(STDiagramLayout sTDiagramLayout);

    void xsetDgmlayoutmru(STDiagramLayout sTDiagramLayout);

    void xsetDgmnodekind(XmlInteger xmlInteger);

    void xsetDoubleclicknotify(STTrueFalse sTTrueFalse);

    void xsetFillcolor(STColorType sTColorType);

    void xsetFilled(STTrueFalse sTTrueFalse);

    void xsetForcedash(STTrueFalse sTTrueFalse);

    void xsetHr(STTrueFalse sTTrueFalse);

    void xsetHralign(STHrAlign sTHrAlign);

    void xsetHref(XmlString xmlString);

    void xsetHrnoshade(STTrueFalse sTTrueFalse);

    void xsetHrpct(XmlFloat xmlFloat);

    void xsetHrstd(STTrueFalse sTTrueFalse);

    void xsetId(XmlString xmlString);

    void xsetInsetmode(STInsetMode sTInsetMode);

    void xsetInsetpen(STTrueFalse sTTrueFalse);

    void xsetOle(STTrueFalseBlank sTTrueFalseBlank);

    void xsetOleicon(STTrueFalse sTTrueFalse);

    void xsetOned(STTrueFalse sTTrueFalse);

    void xsetOpacity(XmlString xmlString);

    void xsetPreferrelative(STTrueFalse sTTrueFalse);

    void xsetPrint(STTrueFalse sTTrueFalse);

    void xsetRegroupid(XmlInteger xmlInteger);

    void xsetSpid(XmlString xmlString);

    void xsetSpt(XmlFloat xmlFloat);

    void xsetStrokecolor(STColorType sTColorType);

    void xsetStroked(STTrueFalse sTTrueFalse);

    void xsetStrokeweight(XmlString xmlString);

    void xsetStyle(XmlString xmlString);

    void xsetTarget(XmlString xmlString);

    void xsetTitle(XmlString xmlString);

    void xsetUserdrawn(STTrueFalse sTTrueFalse);

    void xsetUserhidden(STTrueFalse sTTrueFalse);

    void xsetWrapcoords(XmlString xmlString);

    static {
        DocumentFactory<CTRect> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrectf6e2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
