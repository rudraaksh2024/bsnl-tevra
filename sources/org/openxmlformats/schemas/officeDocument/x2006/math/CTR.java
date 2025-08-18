package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSym;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public interface CTR extends XmlObject {
    public static final DocumentFactory<CTR> Factory;
    public static final SchemaType type;

    CTEmpty addNewAnnotationRef();

    CTBr addNewBr();

    CTMarkup addNewCommentReference();

    CTRel addNewContentPart();

    CTEmpty addNewContinuationSeparator();

    CTEmpty addNewCr();

    CTEmpty addNewDayLong();

    CTEmpty addNewDayShort();

    CTText addNewDelInstrText();

    CTText addNewDelText();

    CTDrawing addNewDrawing();

    CTEmpty addNewEndnoteRef();

    CTFtnEdnRef addNewEndnoteReference();

    CTFldChar addNewFldChar();

    CTEmpty addNewFootnoteRef();

    CTFtnEdnRef addNewFootnoteReference();

    CTText addNewInstrText();

    CTEmpty addNewLastRenderedPageBreak();

    CTEmpty addNewMonthLong();

    CTEmpty addNewMonthShort();

    CTEmpty addNewNoBreakHyphen();

    CTObject addNewObject();

    CTEmpty addNewPgNum();

    CTPicture addNewPict();

    CTPTab addNewPtab();

    CTRPR addNewRPr();

    CTRPr addNewRPr2();

    CTRuby addNewRuby();

    CTEmpty addNewSeparator();

    CTEmpty addNewSoftHyphen();

    CTSym addNewSym();

    CTText addNewT();

    CTText addNewT2();

    CTEmpty addNewTab();

    CTEmpty addNewYearLong();

    CTEmpty addNewYearShort();

    CTEmpty getAnnotationRefArray(int i);

    CTEmpty[] getAnnotationRefArray();

    List<CTEmpty> getAnnotationRefList();

    CTBr getBrArray(int i);

    CTBr[] getBrArray();

    List<CTBr> getBrList();

    CTMarkup getCommentReferenceArray(int i);

    CTMarkup[] getCommentReferenceArray();

    List<CTMarkup> getCommentReferenceList();

    CTRel getContentPartArray(int i);

    CTRel[] getContentPartArray();

    List<CTRel> getContentPartList();

    CTEmpty getContinuationSeparatorArray(int i);

    CTEmpty[] getContinuationSeparatorArray();

    List<CTEmpty> getContinuationSeparatorList();

    CTEmpty getCrArray(int i);

    CTEmpty[] getCrArray();

    List<CTEmpty> getCrList();

    CTEmpty getDayLongArray(int i);

    CTEmpty[] getDayLongArray();

    List<CTEmpty> getDayLongList();

    CTEmpty getDayShortArray(int i);

    CTEmpty[] getDayShortArray();

    List<CTEmpty> getDayShortList();

    CTText getDelInstrTextArray(int i);

    CTText[] getDelInstrTextArray();

    List<CTText> getDelInstrTextList();

    CTText getDelTextArray(int i);

    CTText[] getDelTextArray();

    List<CTText> getDelTextList();

    CTDrawing getDrawingArray(int i);

    CTDrawing[] getDrawingArray();

    List<CTDrawing> getDrawingList();

    CTEmpty getEndnoteRefArray(int i);

    CTEmpty[] getEndnoteRefArray();

    List<CTEmpty> getEndnoteRefList();

    CTFtnEdnRef getEndnoteReferenceArray(int i);

    CTFtnEdnRef[] getEndnoteReferenceArray();

    List<CTFtnEdnRef> getEndnoteReferenceList();

    CTFldChar getFldCharArray(int i);

    CTFldChar[] getFldCharArray();

    List<CTFldChar> getFldCharList();

    CTEmpty getFootnoteRefArray(int i);

    CTEmpty[] getFootnoteRefArray();

    List<CTEmpty> getFootnoteRefList();

    CTFtnEdnRef getFootnoteReferenceArray(int i);

    CTFtnEdnRef[] getFootnoteReferenceArray();

    List<CTFtnEdnRef> getFootnoteReferenceList();

    CTText getInstrTextArray(int i);

    CTText[] getInstrTextArray();

    List<CTText> getInstrTextList();

    CTEmpty getLastRenderedPageBreakArray(int i);

    CTEmpty[] getLastRenderedPageBreakArray();

    List<CTEmpty> getLastRenderedPageBreakList();

    CTEmpty getMonthLongArray(int i);

    CTEmpty[] getMonthLongArray();

    List<CTEmpty> getMonthLongList();

    CTEmpty getMonthShortArray(int i);

    CTEmpty[] getMonthShortArray();

    List<CTEmpty> getMonthShortList();

    CTEmpty getNoBreakHyphenArray(int i);

    CTEmpty[] getNoBreakHyphenArray();

    List<CTEmpty> getNoBreakHyphenList();

    CTObject getObjectArray(int i);

    CTObject[] getObjectArray();

    List<CTObject> getObjectList();

    CTEmpty getPgNumArray(int i);

    CTEmpty[] getPgNumArray();

    List<CTEmpty> getPgNumList();

    CTPicture getPictArray(int i);

    CTPicture[] getPictArray();

    List<CTPicture> getPictList();

    CTPTab getPtabArray(int i);

    CTPTab[] getPtabArray();

    List<CTPTab> getPtabList();

    CTRPR getRPr();

    CTRPr getRPr2();

    CTRuby getRubyArray(int i);

    CTRuby[] getRubyArray();

    List<CTRuby> getRubyList();

    CTEmpty getSeparatorArray(int i);

    CTEmpty[] getSeparatorArray();

    List<CTEmpty> getSeparatorList();

    CTEmpty getSoftHyphenArray(int i);

    CTEmpty[] getSoftHyphenArray();

    List<CTEmpty> getSoftHyphenList();

    CTSym getSymArray(int i);

    CTSym[] getSymArray();

    List<CTSym> getSymList();

    CTText getT2Array(int i);

    CTText[] getT2Array();

    List<CTText> getT2List();

    CTText getTArray(int i);

    CTText[] getTArray();

    List<CTText> getTList();

    CTEmpty getTabArray(int i);

    CTEmpty[] getTabArray();

    List<CTEmpty> getTabList();

    CTEmpty getYearLongArray(int i);

    CTEmpty[] getYearLongArray();

    List<CTEmpty> getYearLongList();

    CTEmpty getYearShortArray(int i);

    CTEmpty[] getYearShortArray();

    List<CTEmpty> getYearShortList();

    CTEmpty insertNewAnnotationRef(int i);

    CTBr insertNewBr(int i);

    CTMarkup insertNewCommentReference(int i);

    CTRel insertNewContentPart(int i);

    CTEmpty insertNewContinuationSeparator(int i);

    CTEmpty insertNewCr(int i);

    CTEmpty insertNewDayLong(int i);

    CTEmpty insertNewDayShort(int i);

    CTText insertNewDelInstrText(int i);

    CTText insertNewDelText(int i);

    CTDrawing insertNewDrawing(int i);

    CTEmpty insertNewEndnoteRef(int i);

    CTFtnEdnRef insertNewEndnoteReference(int i);

    CTFldChar insertNewFldChar(int i);

    CTEmpty insertNewFootnoteRef(int i);

    CTFtnEdnRef insertNewFootnoteReference(int i);

    CTText insertNewInstrText(int i);

    CTEmpty insertNewLastRenderedPageBreak(int i);

    CTEmpty insertNewMonthLong(int i);

    CTEmpty insertNewMonthShort(int i);

    CTEmpty insertNewNoBreakHyphen(int i);

    CTObject insertNewObject(int i);

    CTEmpty insertNewPgNum(int i);

    CTPicture insertNewPict(int i);

    CTPTab insertNewPtab(int i);

    CTRuby insertNewRuby(int i);

    CTEmpty insertNewSeparator(int i);

    CTEmpty insertNewSoftHyphen(int i);

    CTSym insertNewSym(int i);

    CTText insertNewT(int i);

    CTText insertNewT2(int i);

    CTEmpty insertNewTab(int i);

    CTEmpty insertNewYearLong(int i);

    CTEmpty insertNewYearShort(int i);

    boolean isSetRPr();

    boolean isSetRPr2();

    void removeAnnotationRef(int i);

    void removeBr(int i);

    void removeCommentReference(int i);

    void removeContentPart(int i);

    void removeContinuationSeparator(int i);

    void removeCr(int i);

    void removeDayLong(int i);

    void removeDayShort(int i);

    void removeDelInstrText(int i);

    void removeDelText(int i);

    void removeDrawing(int i);

    void removeEndnoteRef(int i);

    void removeEndnoteReference(int i);

    void removeFldChar(int i);

    void removeFootnoteRef(int i);

    void removeFootnoteReference(int i);

    void removeInstrText(int i);

    void removeLastRenderedPageBreak(int i);

    void removeMonthLong(int i);

    void removeMonthShort(int i);

    void removeNoBreakHyphen(int i);

    void removeObject(int i);

    void removePgNum(int i);

    void removePict(int i);

    void removePtab(int i);

    void removeRuby(int i);

    void removeSeparator(int i);

    void removeSoftHyphen(int i);

    void removeSym(int i);

    void removeT(int i);

    void removeT2(int i);

    void removeTab(int i);

    void removeYearLong(int i);

    void removeYearShort(int i);

    void setAnnotationRefArray(int i, CTEmpty cTEmpty);

    void setAnnotationRefArray(CTEmpty[] cTEmptyArr);

    void setBrArray(int i, CTBr cTBr);

    void setBrArray(CTBr[] cTBrArr);

    void setCommentReferenceArray(int i, CTMarkup cTMarkup);

    void setCommentReferenceArray(CTMarkup[] cTMarkupArr);

    void setContentPartArray(int i, CTRel cTRel);

    void setContentPartArray(CTRel[] cTRelArr);

    void setContinuationSeparatorArray(int i, CTEmpty cTEmpty);

    void setContinuationSeparatorArray(CTEmpty[] cTEmptyArr);

    void setCrArray(int i, CTEmpty cTEmpty);

    void setCrArray(CTEmpty[] cTEmptyArr);

    void setDayLongArray(int i, CTEmpty cTEmpty);

    void setDayLongArray(CTEmpty[] cTEmptyArr);

    void setDayShortArray(int i, CTEmpty cTEmpty);

    void setDayShortArray(CTEmpty[] cTEmptyArr);

    void setDelInstrTextArray(int i, CTText cTText);

    void setDelInstrTextArray(CTText[] cTTextArr);

    void setDelTextArray(int i, CTText cTText);

    void setDelTextArray(CTText[] cTTextArr);

    void setDrawingArray(int i, CTDrawing cTDrawing);

    void setDrawingArray(CTDrawing[] cTDrawingArr);

    void setEndnoteRefArray(int i, CTEmpty cTEmpty);

    void setEndnoteRefArray(CTEmpty[] cTEmptyArr);

    void setEndnoteReferenceArray(int i, CTFtnEdnRef cTFtnEdnRef);

    void setEndnoteReferenceArray(CTFtnEdnRef[] cTFtnEdnRefArr);

    void setFldCharArray(int i, CTFldChar cTFldChar);

    void setFldCharArray(CTFldChar[] cTFldCharArr);

    void setFootnoteRefArray(int i, CTEmpty cTEmpty);

    void setFootnoteRefArray(CTEmpty[] cTEmptyArr);

    void setFootnoteReferenceArray(int i, CTFtnEdnRef cTFtnEdnRef);

    void setFootnoteReferenceArray(CTFtnEdnRef[] cTFtnEdnRefArr);

    void setInstrTextArray(int i, CTText cTText);

    void setInstrTextArray(CTText[] cTTextArr);

    void setLastRenderedPageBreakArray(int i, CTEmpty cTEmpty);

    void setLastRenderedPageBreakArray(CTEmpty[] cTEmptyArr);

    void setMonthLongArray(int i, CTEmpty cTEmpty);

    void setMonthLongArray(CTEmpty[] cTEmptyArr);

    void setMonthShortArray(int i, CTEmpty cTEmpty);

    void setMonthShortArray(CTEmpty[] cTEmptyArr);

    void setNoBreakHyphenArray(int i, CTEmpty cTEmpty);

    void setNoBreakHyphenArray(CTEmpty[] cTEmptyArr);

    void setObjectArray(int i, CTObject cTObject);

    void setObjectArray(CTObject[] cTObjectArr);

    void setPgNumArray(int i, CTEmpty cTEmpty);

    void setPgNumArray(CTEmpty[] cTEmptyArr);

    void setPictArray(int i, CTPicture cTPicture);

    void setPictArray(CTPicture[] cTPictureArr);

    void setPtabArray(int i, CTPTab cTPTab);

    void setPtabArray(CTPTab[] cTPTabArr);

    void setRPr(CTRPR ctrpr);

    void setRPr2(CTRPr cTRPr);

    void setRubyArray(int i, CTRuby cTRuby);

    void setRubyArray(CTRuby[] cTRubyArr);

    void setSeparatorArray(int i, CTEmpty cTEmpty);

    void setSeparatorArray(CTEmpty[] cTEmptyArr);

    void setSoftHyphenArray(int i, CTEmpty cTEmpty);

    void setSoftHyphenArray(CTEmpty[] cTEmptyArr);

    void setSymArray(int i, CTSym cTSym);

    void setSymArray(CTSym[] cTSymArr);

    void setT2Array(int i, CTText cTText);

    void setT2Array(CTText[] cTTextArr);

    void setTArray(int i, CTText cTText);

    void setTArray(CTText[] cTTextArr);

    void setTabArray(int i, CTEmpty cTEmpty);

    void setTabArray(CTEmpty[] cTEmptyArr);

    void setYearLongArray(int i, CTEmpty cTEmpty);

    void setYearLongArray(CTEmpty[] cTEmptyArr);

    void setYearShortArray(int i, CTEmpty cTEmpty);

    void setYearShortArray(CTEmpty[] cTEmptyArr);

    int sizeOfAnnotationRefArray();

    int sizeOfBrArray();

    int sizeOfCommentReferenceArray();

    int sizeOfContentPartArray();

    int sizeOfContinuationSeparatorArray();

    int sizeOfCrArray();

    int sizeOfDayLongArray();

    int sizeOfDayShortArray();

    int sizeOfDelInstrTextArray();

    int sizeOfDelTextArray();

    int sizeOfDrawingArray();

    int sizeOfEndnoteRefArray();

    int sizeOfEndnoteReferenceArray();

    int sizeOfFldCharArray();

    int sizeOfFootnoteRefArray();

    int sizeOfFootnoteReferenceArray();

    int sizeOfInstrTextArray();

    int sizeOfLastRenderedPageBreakArray();

    int sizeOfMonthLongArray();

    int sizeOfMonthShortArray();

    int sizeOfNoBreakHyphenArray();

    int sizeOfObjectArray();

    int sizeOfPgNumArray();

    int sizeOfPictArray();

    int sizeOfPtabArray();

    int sizeOfRubyArray();

    int sizeOfSeparatorArray();

    int sizeOfSoftHyphenArray();

    int sizeOfSymArray();

    int sizeOfT2Array();

    int sizeOfTArray();

    int sizeOfTabArray();

    int sizeOfYearLongArray();

    int sizeOfYearShortArray();

    void unsetRPr();

    void unsetRPr2();

    static {
        DocumentFactory<CTR> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctr386atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
