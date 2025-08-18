package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

public interface CTOMathArg extends XmlObject {
    public static final DocumentFactory<CTOMathArg> Factory;
    public static final SchemaType type;

    CTAcc addNewAcc();

    CTOMathArgPr addNewArgPr();

    CTBar addNewBar();

    CTMarkupRange addNewBookmarkEnd();

    CTBookmark addNewBookmarkStart();

    CTBorderBox addNewBorderBox();

    CTBox addNewBox();

    CTMarkupRange addNewCommentRangeEnd();

    CTMarkupRange addNewCommentRangeStart();

    CTCtrlPr addNewCtrlPr();

    CTCustomXmlRun addNewCustomXml();

    CTMarkup addNewCustomXmlDelRangeEnd();

    CTTrackChange addNewCustomXmlDelRangeStart();

    CTMarkup addNewCustomXmlInsRangeEnd();

    CTTrackChange addNewCustomXmlInsRangeStart();

    CTMarkup addNewCustomXmlMoveFromRangeEnd();

    CTTrackChange addNewCustomXmlMoveFromRangeStart();

    CTMarkup addNewCustomXmlMoveToRangeEnd();

    CTTrackChange addNewCustomXmlMoveToRangeStart();

    CTD addNewD();

    CTRunTrackChange addNewDel();

    CTEqArr addNewEqArr();

    CTF addNewF();

    CTSimpleField addNewFldSimple();

    CTFunc addNewFunc();

    CTGroupChr addNewGroupChr();

    CTHyperlink addNewHyperlink();

    CTRunTrackChange addNewIns();

    CTLimLow addNewLimLow();

    CTLimUpp addNewLimUpp();

    CTM addNewM();

    CTRunTrackChange addNewMoveFrom();

    CTMarkupRange addNewMoveFromRangeEnd();

    CTMoveBookmark addNewMoveFromRangeStart();

    CTRunTrackChange addNewMoveTo();

    CTMarkupRange addNewMoveToRangeEnd();

    CTMoveBookmark addNewMoveToRangeStart();

    CTNary addNewNary();

    CTOMath addNewOMath();

    CTOMathPara addNewOMathPara();

    CTPerm addNewPermEnd();

    CTPermStart addNewPermStart();

    CTPhant addNewPhant();

    CTProofErr addNewProofErr();

    CTR addNewR();

    CTRad addNewRad();

    CTSPre addNewSPre();

    CTSSub addNewSSub();

    CTSSubSup addNewSSubSup();

    CTSSup addNewSSup();

    CTSdtRun addNewSdt();

    CTSmartTagRun addNewSmartTag();

    CTAcc getAccArray(int i);

    CTAcc[] getAccArray();

    List<CTAcc> getAccList();

    CTOMathArgPr getArgPr();

    CTBar getBarArray(int i);

    CTBar[] getBarArray();

    List<CTBar> getBarList();

    CTMarkupRange getBookmarkEndArray(int i);

    CTMarkupRange[] getBookmarkEndArray();

    List<CTMarkupRange> getBookmarkEndList();

    CTBookmark getBookmarkStartArray(int i);

    CTBookmark[] getBookmarkStartArray();

    List<CTBookmark> getBookmarkStartList();

    CTBorderBox getBorderBoxArray(int i);

    CTBorderBox[] getBorderBoxArray();

    List<CTBorderBox> getBorderBoxList();

    CTBox getBoxArray(int i);

    CTBox[] getBoxArray();

    List<CTBox> getBoxList();

    CTMarkupRange getCommentRangeEndArray(int i);

    CTMarkupRange[] getCommentRangeEndArray();

    List<CTMarkupRange> getCommentRangeEndList();

    CTMarkupRange getCommentRangeStartArray(int i);

    CTMarkupRange[] getCommentRangeStartArray();

    List<CTMarkupRange> getCommentRangeStartList();

    CTCtrlPr getCtrlPr();

    CTCustomXmlRun getCustomXmlArray(int i);

    CTCustomXmlRun[] getCustomXmlArray();

    CTMarkup getCustomXmlDelRangeEndArray(int i);

    CTMarkup[] getCustomXmlDelRangeEndArray();

    List<CTMarkup> getCustomXmlDelRangeEndList();

    CTTrackChange getCustomXmlDelRangeStartArray(int i);

    CTTrackChange[] getCustomXmlDelRangeStartArray();

    List<CTTrackChange> getCustomXmlDelRangeStartList();

    CTMarkup getCustomXmlInsRangeEndArray(int i);

    CTMarkup[] getCustomXmlInsRangeEndArray();

    List<CTMarkup> getCustomXmlInsRangeEndList();

    CTTrackChange getCustomXmlInsRangeStartArray(int i);

    CTTrackChange[] getCustomXmlInsRangeStartArray();

    List<CTTrackChange> getCustomXmlInsRangeStartList();

    List<CTCustomXmlRun> getCustomXmlList();

    CTMarkup getCustomXmlMoveFromRangeEndArray(int i);

    CTMarkup[] getCustomXmlMoveFromRangeEndArray();

    List<CTMarkup> getCustomXmlMoveFromRangeEndList();

    CTTrackChange getCustomXmlMoveFromRangeStartArray(int i);

    CTTrackChange[] getCustomXmlMoveFromRangeStartArray();

    List<CTTrackChange> getCustomXmlMoveFromRangeStartList();

    CTMarkup getCustomXmlMoveToRangeEndArray(int i);

    CTMarkup[] getCustomXmlMoveToRangeEndArray();

    List<CTMarkup> getCustomXmlMoveToRangeEndList();

    CTTrackChange getCustomXmlMoveToRangeStartArray(int i);

    CTTrackChange[] getCustomXmlMoveToRangeStartArray();

    List<CTTrackChange> getCustomXmlMoveToRangeStartList();

    CTD getDArray(int i);

    CTD[] getDArray();

    List<CTD> getDList();

    CTRunTrackChange getDelArray(int i);

    CTRunTrackChange[] getDelArray();

    List<CTRunTrackChange> getDelList();

    CTEqArr getEqArrArray(int i);

    CTEqArr[] getEqArrArray();

    List<CTEqArr> getEqArrList();

    CTF getFArray(int i);

    CTF[] getFArray();

    List<CTF> getFList();

    CTSimpleField getFldSimpleArray(int i);

    CTSimpleField[] getFldSimpleArray();

    List<CTSimpleField> getFldSimpleList();

    CTFunc getFuncArray(int i);

    CTFunc[] getFuncArray();

    List<CTFunc> getFuncList();

    CTGroupChr getGroupChrArray(int i);

    CTGroupChr[] getGroupChrArray();

    List<CTGroupChr> getGroupChrList();

    CTHyperlink getHyperlinkArray(int i);

    CTHyperlink[] getHyperlinkArray();

    List<CTHyperlink> getHyperlinkList();

    CTRunTrackChange getInsArray(int i);

    CTRunTrackChange[] getInsArray();

    List<CTRunTrackChange> getInsList();

    CTLimLow getLimLowArray(int i);

    CTLimLow[] getLimLowArray();

    List<CTLimLow> getLimLowList();

    CTLimUpp getLimUppArray(int i);

    CTLimUpp[] getLimUppArray();

    List<CTLimUpp> getLimUppList();

    CTM getMArray(int i);

    CTM[] getMArray();

    List<CTM> getMList();

    CTRunTrackChange getMoveFromArray(int i);

    CTRunTrackChange[] getMoveFromArray();

    List<CTRunTrackChange> getMoveFromList();

    CTMarkupRange getMoveFromRangeEndArray(int i);

    CTMarkupRange[] getMoveFromRangeEndArray();

    List<CTMarkupRange> getMoveFromRangeEndList();

    CTMoveBookmark getMoveFromRangeStartArray(int i);

    CTMoveBookmark[] getMoveFromRangeStartArray();

    List<CTMoveBookmark> getMoveFromRangeStartList();

    CTRunTrackChange getMoveToArray(int i);

    CTRunTrackChange[] getMoveToArray();

    List<CTRunTrackChange> getMoveToList();

    CTMarkupRange getMoveToRangeEndArray(int i);

    CTMarkupRange[] getMoveToRangeEndArray();

    List<CTMarkupRange> getMoveToRangeEndList();

    CTMoveBookmark getMoveToRangeStartArray(int i);

    CTMoveBookmark[] getMoveToRangeStartArray();

    List<CTMoveBookmark> getMoveToRangeStartList();

    CTNary getNaryArray(int i);

    CTNary[] getNaryArray();

    List<CTNary> getNaryList();

    CTOMath getOMathArray(int i);

    CTOMath[] getOMathArray();

    List<CTOMath> getOMathList();

    CTOMathPara getOMathParaArray(int i);

    CTOMathPara[] getOMathParaArray();

    List<CTOMathPara> getOMathParaList();

    CTPerm getPermEndArray(int i);

    CTPerm[] getPermEndArray();

    List<CTPerm> getPermEndList();

    CTPermStart getPermStartArray(int i);

    CTPermStart[] getPermStartArray();

    List<CTPermStart> getPermStartList();

    CTPhant getPhantArray(int i);

    CTPhant[] getPhantArray();

    List<CTPhant> getPhantList();

    CTProofErr getProofErrArray(int i);

    CTProofErr[] getProofErrArray();

    List<CTProofErr> getProofErrList();

    CTR getRArray(int i);

    CTR[] getRArray();

    List<CTR> getRList();

    CTRad getRadArray(int i);

    CTRad[] getRadArray();

    List<CTRad> getRadList();

    CTSPre getSPreArray(int i);

    CTSPre[] getSPreArray();

    List<CTSPre> getSPreList();

    CTSSub getSSubArray(int i);

    CTSSub[] getSSubArray();

    List<CTSSub> getSSubList();

    CTSSubSup getSSubSupArray(int i);

    CTSSubSup[] getSSubSupArray();

    List<CTSSubSup> getSSubSupList();

    CTSSup getSSupArray(int i);

    CTSSup[] getSSupArray();

    List<CTSSup> getSSupList();

    CTSdtRun getSdtArray(int i);

    CTSdtRun[] getSdtArray();

    List<CTSdtRun> getSdtList();

    CTSmartTagRun getSmartTagArray(int i);

    CTSmartTagRun[] getSmartTagArray();

    List<CTSmartTagRun> getSmartTagList();

    CTAcc insertNewAcc(int i);

    CTBar insertNewBar(int i);

    CTMarkupRange insertNewBookmarkEnd(int i);

    CTBookmark insertNewBookmarkStart(int i);

    CTBorderBox insertNewBorderBox(int i);

    CTBox insertNewBox(int i);

    CTMarkupRange insertNewCommentRangeEnd(int i);

    CTMarkupRange insertNewCommentRangeStart(int i);

    CTCustomXmlRun insertNewCustomXml(int i);

    CTMarkup insertNewCustomXmlDelRangeEnd(int i);

    CTTrackChange insertNewCustomXmlDelRangeStart(int i);

    CTMarkup insertNewCustomXmlInsRangeEnd(int i);

    CTTrackChange insertNewCustomXmlInsRangeStart(int i);

    CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i);

    CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i);

    CTMarkup insertNewCustomXmlMoveToRangeEnd(int i);

    CTTrackChange insertNewCustomXmlMoveToRangeStart(int i);

    CTD insertNewD(int i);

    CTRunTrackChange insertNewDel(int i);

    CTEqArr insertNewEqArr(int i);

    CTF insertNewF(int i);

    CTSimpleField insertNewFldSimple(int i);

    CTFunc insertNewFunc(int i);

    CTGroupChr insertNewGroupChr(int i);

    CTHyperlink insertNewHyperlink(int i);

    CTRunTrackChange insertNewIns(int i);

    CTLimLow insertNewLimLow(int i);

    CTLimUpp insertNewLimUpp(int i);

    CTM insertNewM(int i);

    CTRunTrackChange insertNewMoveFrom(int i);

    CTMarkupRange insertNewMoveFromRangeEnd(int i);

    CTMoveBookmark insertNewMoveFromRangeStart(int i);

    CTRunTrackChange insertNewMoveTo(int i);

    CTMarkupRange insertNewMoveToRangeEnd(int i);

    CTMoveBookmark insertNewMoveToRangeStart(int i);

    CTNary insertNewNary(int i);

    CTOMath insertNewOMath(int i);

    CTOMathPara insertNewOMathPara(int i);

    CTPerm insertNewPermEnd(int i);

    CTPermStart insertNewPermStart(int i);

    CTPhant insertNewPhant(int i);

    CTProofErr insertNewProofErr(int i);

    CTR insertNewR(int i);

    CTRad insertNewRad(int i);

    CTSPre insertNewSPre(int i);

    CTSSub insertNewSSub(int i);

    CTSSubSup insertNewSSubSup(int i);

    CTSSup insertNewSSup(int i);

    CTSdtRun insertNewSdt(int i);

    CTSmartTagRun insertNewSmartTag(int i);

    boolean isSetArgPr();

    boolean isSetCtrlPr();

    void removeAcc(int i);

    void removeBar(int i);

    void removeBookmarkEnd(int i);

    void removeBookmarkStart(int i);

    void removeBorderBox(int i);

    void removeBox(int i);

    void removeCommentRangeEnd(int i);

    void removeCommentRangeStart(int i);

    void removeCustomXml(int i);

    void removeCustomXmlDelRangeEnd(int i);

    void removeCustomXmlDelRangeStart(int i);

    void removeCustomXmlInsRangeEnd(int i);

    void removeCustomXmlInsRangeStart(int i);

    void removeCustomXmlMoveFromRangeEnd(int i);

    void removeCustomXmlMoveFromRangeStart(int i);

    void removeCustomXmlMoveToRangeEnd(int i);

    void removeCustomXmlMoveToRangeStart(int i);

    void removeD(int i);

    void removeDel(int i);

    void removeEqArr(int i);

    void removeF(int i);

    void removeFldSimple(int i);

    void removeFunc(int i);

    void removeGroupChr(int i);

    void removeHyperlink(int i);

    void removeIns(int i);

    void removeLimLow(int i);

    void removeLimUpp(int i);

    void removeM(int i);

    void removeMoveFrom(int i);

    void removeMoveFromRangeEnd(int i);

    void removeMoveFromRangeStart(int i);

    void removeMoveTo(int i);

    void removeMoveToRangeEnd(int i);

    void removeMoveToRangeStart(int i);

    void removeNary(int i);

    void removeOMath(int i);

    void removeOMathPara(int i);

    void removePermEnd(int i);

    void removePermStart(int i);

    void removePhant(int i);

    void removeProofErr(int i);

    void removeR(int i);

    void removeRad(int i);

    void removeSPre(int i);

    void removeSSub(int i);

    void removeSSubSup(int i);

    void removeSSup(int i);

    void removeSdt(int i);

    void removeSmartTag(int i);

    void setAccArray(int i, CTAcc cTAcc);

    void setAccArray(CTAcc[] cTAccArr);

    void setArgPr(CTOMathArgPr cTOMathArgPr);

    void setBarArray(int i, CTBar cTBar);

    void setBarArray(CTBar[] cTBarArr);

    void setBookmarkEndArray(int i, CTMarkupRange cTMarkupRange);

    void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArr);

    void setBookmarkStartArray(int i, CTBookmark cTBookmark);

    void setBookmarkStartArray(CTBookmark[] cTBookmarkArr);

    void setBorderBoxArray(int i, CTBorderBox cTBorderBox);

    void setBorderBoxArray(CTBorderBox[] cTBorderBoxArr);

    void setBoxArray(int i, CTBox cTBox);

    void setBoxArray(CTBox[] cTBoxArr);

    void setCommentRangeEndArray(int i, CTMarkupRange cTMarkupRange);

    void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArr);

    void setCommentRangeStartArray(int i, CTMarkupRange cTMarkupRange);

    void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArr);

    void setCtrlPr(CTCtrlPr cTCtrlPr);

    void setCustomXmlArray(int i, CTCustomXmlRun cTCustomXmlRun);

    void setCustomXmlArray(CTCustomXmlRun[] cTCustomXmlRunArr);

    void setCustomXmlDelRangeEndArray(int i, CTMarkup cTMarkup);

    void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArr);

    void setCustomXmlDelRangeStartArray(int i, CTTrackChange cTTrackChange);

    void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArr);

    void setCustomXmlInsRangeEndArray(int i, CTMarkup cTMarkup);

    void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArr);

    void setCustomXmlInsRangeStartArray(int i, CTTrackChange cTTrackChange);

    void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArr);

    void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup cTMarkup);

    void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArr);

    void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange cTTrackChange);

    void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArr);

    void setCustomXmlMoveToRangeEndArray(int i, CTMarkup cTMarkup);

    void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArr);

    void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange cTTrackChange);

    void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArr);

    void setDArray(int i, CTD ctd);

    void setDArray(CTD[] ctdArr);

    void setDelArray(int i, CTRunTrackChange cTRunTrackChange);

    void setDelArray(CTRunTrackChange[] cTRunTrackChangeArr);

    void setEqArrArray(int i, CTEqArr cTEqArr);

    void setEqArrArray(CTEqArr[] cTEqArrArr);

    void setFArray(int i, CTF ctf);

    void setFArray(CTF[] ctfArr);

    void setFldSimpleArray(int i, CTSimpleField cTSimpleField);

    void setFldSimpleArray(CTSimpleField[] cTSimpleFieldArr);

    void setFuncArray(int i, CTFunc cTFunc);

    void setFuncArray(CTFunc[] cTFuncArr);

    void setGroupChrArray(int i, CTGroupChr cTGroupChr);

    void setGroupChrArray(CTGroupChr[] cTGroupChrArr);

    void setHyperlinkArray(int i, CTHyperlink cTHyperlink);

    void setHyperlinkArray(CTHyperlink[] cTHyperlinkArr);

    void setInsArray(int i, CTRunTrackChange cTRunTrackChange);

    void setInsArray(CTRunTrackChange[] cTRunTrackChangeArr);

    void setLimLowArray(int i, CTLimLow cTLimLow);

    void setLimLowArray(CTLimLow[] cTLimLowArr);

    void setLimUppArray(int i, CTLimUpp cTLimUpp);

    void setLimUppArray(CTLimUpp[] cTLimUppArr);

    void setMArray(int i, CTM ctm);

    void setMArray(CTM[] ctmArr);

    void setMoveFromArray(int i, CTRunTrackChange cTRunTrackChange);

    void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArr);

    void setMoveFromRangeEndArray(int i, CTMarkupRange cTMarkupRange);

    void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArr);

    void setMoveFromRangeStartArray(int i, CTMoveBookmark cTMoveBookmark);

    void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr);

    void setMoveToArray(int i, CTRunTrackChange cTRunTrackChange);

    void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArr);

    void setMoveToRangeEndArray(int i, CTMarkupRange cTMarkupRange);

    void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArr);

    void setMoveToRangeStartArray(int i, CTMoveBookmark cTMoveBookmark);

    void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr);

    void setNaryArray(int i, CTNary cTNary);

    void setNaryArray(CTNary[] cTNaryArr);

    void setOMathArray(int i, CTOMath cTOMath);

    void setOMathArray(CTOMath[] cTOMathArr);

    void setOMathParaArray(int i, CTOMathPara cTOMathPara);

    void setOMathParaArray(CTOMathPara[] cTOMathParaArr);

    void setPermEndArray(int i, CTPerm cTPerm);

    void setPermEndArray(CTPerm[] cTPermArr);

    void setPermStartArray(int i, CTPermStart cTPermStart);

    void setPermStartArray(CTPermStart[] cTPermStartArr);

    void setPhantArray(int i, CTPhant cTPhant);

    void setPhantArray(CTPhant[] cTPhantArr);

    void setProofErrArray(int i, CTProofErr cTProofErr);

    void setProofErrArray(CTProofErr[] cTProofErrArr);

    void setRArray(int i, CTR ctr);

    void setRArray(CTR[] ctrArr);

    void setRadArray(int i, CTRad cTRad);

    void setRadArray(CTRad[] cTRadArr);

    void setSPreArray(int i, CTSPre cTSPre);

    void setSPreArray(CTSPre[] cTSPreArr);

    void setSSubArray(int i, CTSSub cTSSub);

    void setSSubArray(CTSSub[] cTSSubArr);

    void setSSubSupArray(int i, CTSSubSup cTSSubSup);

    void setSSubSupArray(CTSSubSup[] cTSSubSupArr);

    void setSSupArray(int i, CTSSup cTSSup);

    void setSSupArray(CTSSup[] cTSSupArr);

    void setSdtArray(int i, CTSdtRun cTSdtRun);

    void setSdtArray(CTSdtRun[] cTSdtRunArr);

    void setSmartTagArray(int i, CTSmartTagRun cTSmartTagRun);

    void setSmartTagArray(CTSmartTagRun[] cTSmartTagRunArr);

    int sizeOfAccArray();

    int sizeOfBarArray();

    int sizeOfBookmarkEndArray();

    int sizeOfBookmarkStartArray();

    int sizeOfBorderBoxArray();

    int sizeOfBoxArray();

    int sizeOfCommentRangeEndArray();

    int sizeOfCommentRangeStartArray();

    int sizeOfCustomXmlArray();

    int sizeOfCustomXmlDelRangeEndArray();

    int sizeOfCustomXmlDelRangeStartArray();

    int sizeOfCustomXmlInsRangeEndArray();

    int sizeOfCustomXmlInsRangeStartArray();

    int sizeOfCustomXmlMoveFromRangeEndArray();

    int sizeOfCustomXmlMoveFromRangeStartArray();

    int sizeOfCustomXmlMoveToRangeEndArray();

    int sizeOfCustomXmlMoveToRangeStartArray();

    int sizeOfDArray();

    int sizeOfDelArray();

    int sizeOfEqArrArray();

    int sizeOfFArray();

    int sizeOfFldSimpleArray();

    int sizeOfFuncArray();

    int sizeOfGroupChrArray();

    int sizeOfHyperlinkArray();

    int sizeOfInsArray();

    int sizeOfLimLowArray();

    int sizeOfLimUppArray();

    int sizeOfMArray();

    int sizeOfMoveFromArray();

    int sizeOfMoveFromRangeEndArray();

    int sizeOfMoveFromRangeStartArray();

    int sizeOfMoveToArray();

    int sizeOfMoveToRangeEndArray();

    int sizeOfMoveToRangeStartArray();

    int sizeOfNaryArray();

    int sizeOfOMathArray();

    int sizeOfOMathParaArray();

    int sizeOfPermEndArray();

    int sizeOfPermStartArray();

    int sizeOfPhantArray();

    int sizeOfProofErrArray();

    int sizeOfRArray();

    int sizeOfRadArray();

    int sizeOfSPreArray();

    int sizeOfSSubArray();

    int sizeOfSSubSupArray();

    int sizeOfSSupArray();

    int sizeOfSdtArray();

    int sizeOfSmartTagArray();

    void unsetArgPr();

    void unsetCtrlPr();

    static {
        DocumentFactory<CTOMathArg> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctomathargcb13type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
