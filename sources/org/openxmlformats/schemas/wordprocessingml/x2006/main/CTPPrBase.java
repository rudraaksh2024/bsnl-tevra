package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPPrBase extends XmlObject {
    public static final DocumentFactory<CTPPrBase> Factory;
    public static final SchemaType type;

    CTOnOff addNewAdjustRightInd();

    CTOnOff addNewAutoSpaceDE();

    CTOnOff addNewAutoSpaceDN();

    CTOnOff addNewBidi();

    CTCnf addNewCnfStyle();

    CTOnOff addNewContextualSpacing();

    CTDecimalNumber addNewDivId();

    CTFramePr addNewFramePr();

    CTInd addNewInd();

    CTJc addNewJc();

    CTOnOff addNewKeepLines();

    CTOnOff addNewKeepNext();

    CTOnOff addNewKinsoku();

    CTOnOff addNewMirrorIndents();

    CTNumPr addNewNumPr();

    CTDecimalNumber addNewOutlineLvl();

    CTOnOff addNewOverflowPunct();

    CTPBdr addNewPBdr();

    CTString addNewPStyle();

    CTOnOff addNewPageBreakBefore();

    CTShd addNewShd();

    CTOnOff addNewSnapToGrid();

    CTSpacing addNewSpacing();

    CTOnOff addNewSuppressAutoHyphens();

    CTOnOff addNewSuppressLineNumbers();

    CTOnOff addNewSuppressOverlap();

    CTTabs addNewTabs();

    CTTextAlignment addNewTextAlignment();

    CTTextDirection addNewTextDirection();

    CTTextboxTightWrap addNewTextboxTightWrap();

    CTOnOff addNewTopLinePunct();

    CTOnOff addNewWidowControl();

    CTOnOff addNewWordWrap();

    CTOnOff getAdjustRightInd();

    CTOnOff getAutoSpaceDE();

    CTOnOff getAutoSpaceDN();

    CTOnOff getBidi();

    CTCnf getCnfStyle();

    CTOnOff getContextualSpacing();

    CTDecimalNumber getDivId();

    CTFramePr getFramePr();

    CTInd getInd();

    CTJc getJc();

    CTOnOff getKeepLines();

    CTOnOff getKeepNext();

    CTOnOff getKinsoku();

    CTOnOff getMirrorIndents();

    CTNumPr getNumPr();

    CTDecimalNumber getOutlineLvl();

    CTOnOff getOverflowPunct();

    CTPBdr getPBdr();

    CTString getPStyle();

    CTOnOff getPageBreakBefore();

    CTShd getShd();

    CTOnOff getSnapToGrid();

    CTSpacing getSpacing();

    CTOnOff getSuppressAutoHyphens();

    CTOnOff getSuppressLineNumbers();

    CTOnOff getSuppressOverlap();

    CTTabs getTabs();

    CTTextAlignment getTextAlignment();

    CTTextDirection getTextDirection();

    CTTextboxTightWrap getTextboxTightWrap();

    CTOnOff getTopLinePunct();

    CTOnOff getWidowControl();

    CTOnOff getWordWrap();

    boolean isSetAdjustRightInd();

    boolean isSetAutoSpaceDE();

    boolean isSetAutoSpaceDN();

    boolean isSetBidi();

    boolean isSetCnfStyle();

    boolean isSetContextualSpacing();

    boolean isSetDivId();

    boolean isSetFramePr();

    boolean isSetInd();

    boolean isSetJc();

    boolean isSetKeepLines();

    boolean isSetKeepNext();

    boolean isSetKinsoku();

    boolean isSetMirrorIndents();

    boolean isSetNumPr();

    boolean isSetOutlineLvl();

    boolean isSetOverflowPunct();

    boolean isSetPBdr();

    boolean isSetPStyle();

    boolean isSetPageBreakBefore();

    boolean isSetShd();

    boolean isSetSnapToGrid();

    boolean isSetSpacing();

    boolean isSetSuppressAutoHyphens();

    boolean isSetSuppressLineNumbers();

    boolean isSetSuppressOverlap();

    boolean isSetTabs();

    boolean isSetTextAlignment();

    boolean isSetTextDirection();

    boolean isSetTextboxTightWrap();

    boolean isSetTopLinePunct();

    boolean isSetWidowControl();

    boolean isSetWordWrap();

    void setAdjustRightInd(CTOnOff cTOnOff);

    void setAutoSpaceDE(CTOnOff cTOnOff);

    void setAutoSpaceDN(CTOnOff cTOnOff);

    void setBidi(CTOnOff cTOnOff);

    void setCnfStyle(CTCnf cTCnf);

    void setContextualSpacing(CTOnOff cTOnOff);

    void setDivId(CTDecimalNumber cTDecimalNumber);

    void setFramePr(CTFramePr cTFramePr);

    void setInd(CTInd cTInd);

    void setJc(CTJc cTJc);

    void setKeepLines(CTOnOff cTOnOff);

    void setKeepNext(CTOnOff cTOnOff);

    void setKinsoku(CTOnOff cTOnOff);

    void setMirrorIndents(CTOnOff cTOnOff);

    void setNumPr(CTNumPr cTNumPr);

    void setOutlineLvl(CTDecimalNumber cTDecimalNumber);

    void setOverflowPunct(CTOnOff cTOnOff);

    void setPBdr(CTPBdr cTPBdr);

    void setPStyle(CTString cTString);

    void setPageBreakBefore(CTOnOff cTOnOff);

    void setShd(CTShd cTShd);

    void setSnapToGrid(CTOnOff cTOnOff);

    void setSpacing(CTSpacing cTSpacing);

    void setSuppressAutoHyphens(CTOnOff cTOnOff);

    void setSuppressLineNumbers(CTOnOff cTOnOff);

    void setSuppressOverlap(CTOnOff cTOnOff);

    void setTabs(CTTabs cTTabs);

    void setTextAlignment(CTTextAlignment cTTextAlignment);

    void setTextDirection(CTTextDirection cTTextDirection);

    void setTextboxTightWrap(CTTextboxTightWrap cTTextboxTightWrap);

    void setTopLinePunct(CTOnOff cTOnOff);

    void setWidowControl(CTOnOff cTOnOff);

    void setWordWrap(CTOnOff cTOnOff);

    void unsetAdjustRightInd();

    void unsetAutoSpaceDE();

    void unsetAutoSpaceDN();

    void unsetBidi();

    void unsetCnfStyle();

    void unsetContextualSpacing();

    void unsetDivId();

    void unsetFramePr();

    void unsetInd();

    void unsetJc();

    void unsetKeepLines();

    void unsetKeepNext();

    void unsetKinsoku();

    void unsetMirrorIndents();

    void unsetNumPr();

    void unsetOutlineLvl();

    void unsetOverflowPunct();

    void unsetPBdr();

    void unsetPStyle();

    void unsetPageBreakBefore();

    void unsetShd();

    void unsetSnapToGrid();

    void unsetSpacing();

    void unsetSuppressAutoHyphens();

    void unsetSuppressLineNumbers();

    void unsetSuppressOverlap();

    void unsetTabs();

    void unsetTextAlignment();

    void unsetTextDirection();

    void unsetTextboxTightWrap();

    void unsetTopLinePunct();

    void unsetWidowControl();

    void unsetWordWrap();

    static {
        DocumentFactory<CTPPrBase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpprbasebaeftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
