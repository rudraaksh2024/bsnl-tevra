package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;

public interface CTSdtContentBlock extends XmlObject {
    public static final DocumentFactory<CTSdtContentBlock> Factory;
    public static final SchemaType type;

    CTMarkupRange addNewBookmarkEnd();

    CTBookmark addNewBookmarkStart();

    CTMarkupRange addNewCommentRangeEnd();

    CTMarkupRange addNewCommentRangeStart();

    CTCustomXmlBlock addNewCustomXml();

    CTMarkup addNewCustomXmlDelRangeEnd();

    CTTrackChange addNewCustomXmlDelRangeStart();

    CTMarkup addNewCustomXmlInsRangeEnd();

    CTTrackChange addNewCustomXmlInsRangeStart();

    CTMarkup addNewCustomXmlMoveFromRangeEnd();

    CTTrackChange addNewCustomXmlMoveFromRangeStart();

    CTMarkup addNewCustomXmlMoveToRangeEnd();

    CTTrackChange addNewCustomXmlMoveToRangeStart();

    CTRunTrackChange addNewDel();

    CTRunTrackChange addNewIns();

    CTRunTrackChange addNewMoveFrom();

    CTMarkupRange addNewMoveFromRangeEnd();

    CTMoveBookmark addNewMoveFromRangeStart();

    CTRunTrackChange addNewMoveTo();

    CTMarkupRange addNewMoveToRangeEnd();

    CTMoveBookmark addNewMoveToRangeStart();

    CTOMath addNewOMath();

    CTOMathPara addNewOMathPara();

    CTP addNewP();

    CTPerm addNewPermEnd();

    CTPermStart addNewPermStart();

    CTProofErr addNewProofErr();

    CTSdtBlock addNewSdt();

    CTTbl addNewTbl();

    CTMarkupRange getBookmarkEndArray(int i);

    CTMarkupRange[] getBookmarkEndArray();

    List<CTMarkupRange> getBookmarkEndList();

    CTBookmark getBookmarkStartArray(int i);

    CTBookmark[] getBookmarkStartArray();

    List<CTBookmark> getBookmarkStartList();

    CTMarkupRange getCommentRangeEndArray(int i);

    CTMarkupRange[] getCommentRangeEndArray();

    List<CTMarkupRange> getCommentRangeEndList();

    CTMarkupRange getCommentRangeStartArray(int i);

    CTMarkupRange[] getCommentRangeStartArray();

    List<CTMarkupRange> getCommentRangeStartList();

    CTCustomXmlBlock getCustomXmlArray(int i);

    CTCustomXmlBlock[] getCustomXmlArray();

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

    List<CTCustomXmlBlock> getCustomXmlList();

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

    CTRunTrackChange getDelArray(int i);

    CTRunTrackChange[] getDelArray();

    List<CTRunTrackChange> getDelList();

    CTRunTrackChange getInsArray(int i);

    CTRunTrackChange[] getInsArray();

    List<CTRunTrackChange> getInsList();

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

    CTOMath getOMathArray(int i);

    CTOMath[] getOMathArray();

    List<CTOMath> getOMathList();

    CTOMathPara getOMathParaArray(int i);

    CTOMathPara[] getOMathParaArray();

    List<CTOMathPara> getOMathParaList();

    CTP getPArray(int i);

    CTP[] getPArray();

    List<CTP> getPList();

    CTPerm getPermEndArray(int i);

    CTPerm[] getPermEndArray();

    List<CTPerm> getPermEndList();

    CTPermStart getPermStartArray(int i);

    CTPermStart[] getPermStartArray();

    List<CTPermStart> getPermStartList();

    CTProofErr getProofErrArray(int i);

    CTProofErr[] getProofErrArray();

    List<CTProofErr> getProofErrList();

    CTSdtBlock getSdtArray(int i);

    CTSdtBlock[] getSdtArray();

    List<CTSdtBlock> getSdtList();

    CTTbl getTblArray(int i);

    CTTbl[] getTblArray();

    List<CTTbl> getTblList();

    CTMarkupRange insertNewBookmarkEnd(int i);

    CTBookmark insertNewBookmarkStart(int i);

    CTMarkupRange insertNewCommentRangeEnd(int i);

    CTMarkupRange insertNewCommentRangeStart(int i);

    CTCustomXmlBlock insertNewCustomXml(int i);

    CTMarkup insertNewCustomXmlDelRangeEnd(int i);

    CTTrackChange insertNewCustomXmlDelRangeStart(int i);

    CTMarkup insertNewCustomXmlInsRangeEnd(int i);

    CTTrackChange insertNewCustomXmlInsRangeStart(int i);

    CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i);

    CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i);

    CTMarkup insertNewCustomXmlMoveToRangeEnd(int i);

    CTTrackChange insertNewCustomXmlMoveToRangeStart(int i);

    CTRunTrackChange insertNewDel(int i);

    CTRunTrackChange insertNewIns(int i);

    CTRunTrackChange insertNewMoveFrom(int i);

    CTMarkupRange insertNewMoveFromRangeEnd(int i);

    CTMoveBookmark insertNewMoveFromRangeStart(int i);

    CTRunTrackChange insertNewMoveTo(int i);

    CTMarkupRange insertNewMoveToRangeEnd(int i);

    CTMoveBookmark insertNewMoveToRangeStart(int i);

    CTOMath insertNewOMath(int i);

    CTOMathPara insertNewOMathPara(int i);

    CTP insertNewP(int i);

    CTPerm insertNewPermEnd(int i);

    CTPermStart insertNewPermStart(int i);

    CTProofErr insertNewProofErr(int i);

    CTSdtBlock insertNewSdt(int i);

    CTTbl insertNewTbl(int i);

    void removeBookmarkEnd(int i);

    void removeBookmarkStart(int i);

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

    void removeDel(int i);

    void removeIns(int i);

    void removeMoveFrom(int i);

    void removeMoveFromRangeEnd(int i);

    void removeMoveFromRangeStart(int i);

    void removeMoveTo(int i);

    void removeMoveToRangeEnd(int i);

    void removeMoveToRangeStart(int i);

    void removeOMath(int i);

    void removeOMathPara(int i);

    void removeP(int i);

    void removePermEnd(int i);

    void removePermStart(int i);

    void removeProofErr(int i);

    void removeSdt(int i);

    void removeTbl(int i);

    void setBookmarkEndArray(int i, CTMarkupRange cTMarkupRange);

    void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArr);

    void setBookmarkStartArray(int i, CTBookmark cTBookmark);

    void setBookmarkStartArray(CTBookmark[] cTBookmarkArr);

    void setCommentRangeEndArray(int i, CTMarkupRange cTMarkupRange);

    void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArr);

    void setCommentRangeStartArray(int i, CTMarkupRange cTMarkupRange);

    void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArr);

    void setCustomXmlArray(int i, CTCustomXmlBlock cTCustomXmlBlock);

    void setCustomXmlArray(CTCustomXmlBlock[] cTCustomXmlBlockArr);

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

    void setDelArray(int i, CTRunTrackChange cTRunTrackChange);

    void setDelArray(CTRunTrackChange[] cTRunTrackChangeArr);

    void setInsArray(int i, CTRunTrackChange cTRunTrackChange);

    void setInsArray(CTRunTrackChange[] cTRunTrackChangeArr);

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

    void setOMathArray(int i, CTOMath cTOMath);

    void setOMathArray(CTOMath[] cTOMathArr);

    void setOMathParaArray(int i, CTOMathPara cTOMathPara);

    void setOMathParaArray(CTOMathPara[] cTOMathParaArr);

    void setPArray(int i, CTP ctp);

    void setPArray(CTP[] ctpArr);

    void setPermEndArray(int i, CTPerm cTPerm);

    void setPermEndArray(CTPerm[] cTPermArr);

    void setPermStartArray(int i, CTPermStart cTPermStart);

    void setPermStartArray(CTPermStart[] cTPermStartArr);

    void setProofErrArray(int i, CTProofErr cTProofErr);

    void setProofErrArray(CTProofErr[] cTProofErrArr);

    void setSdtArray(int i, CTSdtBlock cTSdtBlock);

    void setSdtArray(CTSdtBlock[] cTSdtBlockArr);

    void setTblArray(int i, CTTbl cTTbl);

    void setTblArray(CTTbl[] cTTblArr);

    int sizeOfBookmarkEndArray();

    int sizeOfBookmarkStartArray();

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

    int sizeOfDelArray();

    int sizeOfInsArray();

    int sizeOfMoveFromArray();

    int sizeOfMoveFromRangeEndArray();

    int sizeOfMoveFromRangeStartArray();

    int sizeOfMoveToArray();

    int sizeOfMoveToRangeEndArray();

    int sizeOfMoveToRangeStartArray();

    int sizeOfOMathArray();

    int sizeOfOMathParaArray();

    int sizeOfPArray();

    int sizeOfPermEndArray();

    int sizeOfPermStartArray();

    int sizeOfProofErrArray();

    int sizeOfSdtArray();

    int sizeOfTblArray();

    static {
        DocumentFactory<CTSdtContentBlock> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtcontentblockb37btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
