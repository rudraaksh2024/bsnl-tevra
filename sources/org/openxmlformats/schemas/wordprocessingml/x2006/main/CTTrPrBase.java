package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTrPrBase extends XmlObject {
    public static final DocumentFactory<CTTrPrBase> Factory;
    public static final SchemaType type;

    CTOnOff addNewCantSplit();

    CTCnf addNewCnfStyle();

    CTDecimalNumber addNewDivId();

    CTDecimalNumber addNewGridAfter();

    CTDecimalNumber addNewGridBefore();

    CTOnOff addNewHidden();

    CTJcTable addNewJc();

    CTTblWidth addNewTblCellSpacing();

    CTOnOff addNewTblHeader();

    CTHeight addNewTrHeight();

    CTTblWidth addNewWAfter();

    CTTblWidth addNewWBefore();

    CTOnOff getCantSplitArray(int i);

    CTOnOff[] getCantSplitArray();

    List<CTOnOff> getCantSplitList();

    CTCnf getCnfStyleArray(int i);

    CTCnf[] getCnfStyleArray();

    List<CTCnf> getCnfStyleList();

    CTDecimalNumber getDivIdArray(int i);

    CTDecimalNumber[] getDivIdArray();

    List<CTDecimalNumber> getDivIdList();

    CTDecimalNumber getGridAfterArray(int i);

    CTDecimalNumber[] getGridAfterArray();

    List<CTDecimalNumber> getGridAfterList();

    CTDecimalNumber getGridBeforeArray(int i);

    CTDecimalNumber[] getGridBeforeArray();

    List<CTDecimalNumber> getGridBeforeList();

    CTOnOff getHiddenArray(int i);

    CTOnOff[] getHiddenArray();

    List<CTOnOff> getHiddenList();

    CTJcTable getJcArray(int i);

    CTJcTable[] getJcArray();

    List<CTJcTable> getJcList();

    CTTblWidth getTblCellSpacingArray(int i);

    CTTblWidth[] getTblCellSpacingArray();

    List<CTTblWidth> getTblCellSpacingList();

    CTOnOff getTblHeaderArray(int i);

    CTOnOff[] getTblHeaderArray();

    List<CTOnOff> getTblHeaderList();

    CTHeight getTrHeightArray(int i);

    CTHeight[] getTrHeightArray();

    List<CTHeight> getTrHeightList();

    CTTblWidth getWAfterArray(int i);

    CTTblWidth[] getWAfterArray();

    List<CTTblWidth> getWAfterList();

    CTTblWidth getWBeforeArray(int i);

    CTTblWidth[] getWBeforeArray();

    List<CTTblWidth> getWBeforeList();

    CTOnOff insertNewCantSplit(int i);

    CTCnf insertNewCnfStyle(int i);

    CTDecimalNumber insertNewDivId(int i);

    CTDecimalNumber insertNewGridAfter(int i);

    CTDecimalNumber insertNewGridBefore(int i);

    CTOnOff insertNewHidden(int i);

    CTJcTable insertNewJc(int i);

    CTTblWidth insertNewTblCellSpacing(int i);

    CTOnOff insertNewTblHeader(int i);

    CTHeight insertNewTrHeight(int i);

    CTTblWidth insertNewWAfter(int i);

    CTTblWidth insertNewWBefore(int i);

    void removeCantSplit(int i);

    void removeCnfStyle(int i);

    void removeDivId(int i);

    void removeGridAfter(int i);

    void removeGridBefore(int i);

    void removeHidden(int i);

    void removeJc(int i);

    void removeTblCellSpacing(int i);

    void removeTblHeader(int i);

    void removeTrHeight(int i);

    void removeWAfter(int i);

    void removeWBefore(int i);

    void setCantSplitArray(int i, CTOnOff cTOnOff);

    void setCantSplitArray(CTOnOff[] cTOnOffArr);

    void setCnfStyleArray(int i, CTCnf cTCnf);

    void setCnfStyleArray(CTCnf[] cTCnfArr);

    void setDivIdArray(int i, CTDecimalNumber cTDecimalNumber);

    void setDivIdArray(CTDecimalNumber[] cTDecimalNumberArr);

    void setGridAfterArray(int i, CTDecimalNumber cTDecimalNumber);

    void setGridAfterArray(CTDecimalNumber[] cTDecimalNumberArr);

    void setGridBeforeArray(int i, CTDecimalNumber cTDecimalNumber);

    void setGridBeforeArray(CTDecimalNumber[] cTDecimalNumberArr);

    void setHiddenArray(int i, CTOnOff cTOnOff);

    void setHiddenArray(CTOnOff[] cTOnOffArr);

    void setJcArray(int i, CTJcTable cTJcTable);

    void setJcArray(CTJcTable[] cTJcTableArr);

    void setTblCellSpacingArray(int i, CTTblWidth cTTblWidth);

    void setTblCellSpacingArray(CTTblWidth[] cTTblWidthArr);

    void setTblHeaderArray(int i, CTOnOff cTOnOff);

    void setTblHeaderArray(CTOnOff[] cTOnOffArr);

    void setTrHeightArray(int i, CTHeight cTHeight);

    void setTrHeightArray(CTHeight[] cTHeightArr);

    void setWAfterArray(int i, CTTblWidth cTTblWidth);

    void setWAfterArray(CTTblWidth[] cTTblWidthArr);

    void setWBeforeArray(int i, CTTblWidth cTTblWidth);

    void setWBeforeArray(CTTblWidth[] cTTblWidthArr);

    int sizeOfCantSplitArray();

    int sizeOfCnfStyleArray();

    int sizeOfDivIdArray();

    int sizeOfGridAfterArray();

    int sizeOfGridBeforeArray();

    int sizeOfHiddenArray();

    int sizeOfJcArray();

    int sizeOfTblCellSpacingArray();

    int sizeOfTblHeaderArray();

    int sizeOfTrHeightArray();

    int sizeOfWAfterArray();

    int sizeOfWBeforeArray();

    static {
        DocumentFactory<CTTrPrBase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttrprbase5d77type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
