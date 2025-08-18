package com.microsoft.schemas.office.excel;

import com.microsoft.schemas.office.excel.STObjectType;
import java.math.BigInteger;
import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

public interface CTClientData extends XmlObject {
    public static final DocumentFactory<CTClientData> Factory;
    public static final SchemaType type;

    void addAccel(BigInteger bigInteger);

    void addAccel2(BigInteger bigInteger);

    void addAnchor(String str);

    void addAutoFill(STTrueFalseBlank.Enum enumR);

    void addAutoLine(STTrueFalseBlank.Enum enumR);

    void addAutoPict(STTrueFalseBlank.Enum enumR);

    void addAutoScale(STTrueFalseBlank.Enum enumR);

    void addCF(String str);

    void addCamera(STTrueFalseBlank.Enum enumR);

    void addCancel(STTrueFalseBlank.Enum enumR);

    void addChecked(BigInteger bigInteger);

    void addColHidden(STTrueFalseBlank.Enum enumR);

    void addColored(STTrueFalseBlank.Enum enumR);

    void addColumn(BigInteger bigInteger);

    void addDDE(STTrueFalseBlank.Enum enumR);

    void addDefault(STTrueFalseBlank.Enum enumR);

    void addDefaultSize(STTrueFalseBlank.Enum enumR);

    void addDisabled(STTrueFalseBlank.Enum enumR);

    void addDismiss(STTrueFalseBlank.Enum enumR);

    void addDropLines(BigInteger bigInteger);

    void addDropStyle(String str);

    void addDx(BigInteger bigInteger);

    void addFirstButton(STTrueFalseBlank.Enum enumR);

    void addFmlaGroup(String str);

    void addFmlaLink(String str);

    void addFmlaMacro(String str);

    void addFmlaPict(String str);

    void addFmlaRange(String str);

    void addFmlaTxbx(String str);

    void addHelp(STTrueFalseBlank.Enum enumR);

    void addHoriz(STTrueFalseBlank.Enum enumR);

    void addInc(BigInteger bigInteger);

    void addJustLastX(STTrueFalseBlank.Enum enumR);

    void addLCT(String str);

    void addListItem(String str);

    void addLockText(STTrueFalseBlank.Enum enumR);

    void addLocked(STTrueFalseBlank.Enum enumR);

    void addMapOCX(STTrueFalseBlank.Enum enumR);

    void addMax(BigInteger bigInteger);

    void addMin(BigInteger bigInteger);

    void addMoveWithCells(STTrueFalseBlank.Enum enumR);

    void addMultiLine(STTrueFalseBlank.Enum enumR);

    void addMultiSel(String str);

    XmlInteger addNewAccel();

    XmlInteger addNewAccel2();

    XmlString addNewAnchor();

    STTrueFalseBlank addNewAutoFill();

    STTrueFalseBlank addNewAutoLine();

    STTrueFalseBlank addNewAutoPict();

    STTrueFalseBlank addNewAutoScale();

    STCF addNewCF();

    STTrueFalseBlank addNewCamera();

    STTrueFalseBlank addNewCancel();

    XmlInteger addNewChecked();

    STTrueFalseBlank addNewColHidden();

    STTrueFalseBlank addNewColored();

    XmlInteger addNewColumn();

    STTrueFalseBlank addNewDDE();

    STTrueFalseBlank addNewDefault();

    STTrueFalseBlank addNewDefaultSize();

    STTrueFalseBlank addNewDisabled();

    STTrueFalseBlank addNewDismiss();

    XmlInteger addNewDropLines();

    XmlString addNewDropStyle();

    XmlInteger addNewDx();

    STTrueFalseBlank addNewFirstButton();

    XmlString addNewFmlaGroup();

    XmlString addNewFmlaLink();

    XmlString addNewFmlaMacro();

    XmlString addNewFmlaPict();

    XmlString addNewFmlaRange();

    XmlString addNewFmlaTxbx();

    STTrueFalseBlank addNewHelp();

    STTrueFalseBlank addNewHoriz();

    XmlInteger addNewInc();

    STTrueFalseBlank addNewJustLastX();

    XmlString addNewLCT();

    XmlString addNewListItem();

    STTrueFalseBlank addNewLockText();

    STTrueFalseBlank addNewLocked();

    STTrueFalseBlank addNewMapOCX();

    XmlInteger addNewMax();

    XmlInteger addNewMin();

    STTrueFalseBlank addNewMoveWithCells();

    STTrueFalseBlank addNewMultiLine();

    XmlString addNewMultiSel();

    STTrueFalseBlank addNewNoThreeD();

    STTrueFalseBlank addNewNoThreeD2();

    XmlInteger addNewPage();

    STTrueFalseBlank addNewPrintObject();

    STTrueFalseBlank addNewRecalcAlways();

    XmlInteger addNewRow();

    STTrueFalseBlank addNewRowHidden();

    XmlString addNewScriptExtended();

    XmlNonNegativeInteger addNewScriptLanguage();

    XmlNonNegativeInteger addNewScriptLocation();

    XmlString addNewScriptText();

    STTrueFalseBlank addNewSecretEdit();

    XmlInteger addNewSel();

    XmlString addNewSelType();

    STTrueFalseBlank addNewSizeWithCells();

    XmlString addNewTextHAlign();

    XmlString addNewTextVAlign();

    STTrueFalseBlank addNewUIObj();

    STTrueFalseBlank addNewVScroll();

    XmlInteger addNewVTEdit();

    XmlInteger addNewVal();

    STTrueFalseBlank addNewValidIds();

    STTrueFalseBlank addNewVisible();

    XmlInteger addNewWidthMin();

    void addNoThreeD(STTrueFalseBlank.Enum enumR);

    void addNoThreeD2(STTrueFalseBlank.Enum enumR);

    void addPage(BigInteger bigInteger);

    void addPrintObject(STTrueFalseBlank.Enum enumR);

    void addRecalcAlways(STTrueFalseBlank.Enum enumR);

    void addRow(BigInteger bigInteger);

    void addRowHidden(STTrueFalseBlank.Enum enumR);

    void addScriptExtended(String str);

    void addScriptLanguage(BigInteger bigInteger);

    void addScriptLocation(BigInteger bigInteger);

    void addScriptText(String str);

    void addSecretEdit(STTrueFalseBlank.Enum enumR);

    void addSel(BigInteger bigInteger);

    void addSelType(String str);

    void addSizeWithCells(STTrueFalseBlank.Enum enumR);

    void addTextHAlign(String str);

    void addTextVAlign(String str);

    void addUIObj(STTrueFalseBlank.Enum enumR);

    void addVScroll(STTrueFalseBlank.Enum enumR);

    void addVTEdit(BigInteger bigInteger);

    void addVal(BigInteger bigInteger);

    void addValidIds(STTrueFalseBlank.Enum enumR);

    void addVisible(STTrueFalseBlank.Enum enumR);

    void addWidthMin(BigInteger bigInteger);

    BigInteger getAccel2Array(int i);

    BigInteger[] getAccel2Array();

    List<BigInteger> getAccel2List();

    BigInteger getAccelArray(int i);

    BigInteger[] getAccelArray();

    List<BigInteger> getAccelList();

    String getAnchorArray(int i);

    String[] getAnchorArray();

    List<String> getAnchorList();

    STTrueFalseBlank.Enum getAutoFillArray(int i);

    STTrueFalseBlank.Enum[] getAutoFillArray();

    List<STTrueFalseBlank.Enum> getAutoFillList();

    STTrueFalseBlank.Enum getAutoLineArray(int i);

    STTrueFalseBlank.Enum[] getAutoLineArray();

    List<STTrueFalseBlank.Enum> getAutoLineList();

    STTrueFalseBlank.Enum getAutoPictArray(int i);

    STTrueFalseBlank.Enum[] getAutoPictArray();

    List<STTrueFalseBlank.Enum> getAutoPictList();

    STTrueFalseBlank.Enum getAutoScaleArray(int i);

    STTrueFalseBlank.Enum[] getAutoScaleArray();

    List<STTrueFalseBlank.Enum> getAutoScaleList();

    String getCFArray(int i);

    String[] getCFArray();

    List<String> getCFList();

    STTrueFalseBlank.Enum getCameraArray(int i);

    STTrueFalseBlank.Enum[] getCameraArray();

    List<STTrueFalseBlank.Enum> getCameraList();

    STTrueFalseBlank.Enum getCancelArray(int i);

    STTrueFalseBlank.Enum[] getCancelArray();

    List<STTrueFalseBlank.Enum> getCancelList();

    BigInteger getCheckedArray(int i);

    BigInteger[] getCheckedArray();

    List<BigInteger> getCheckedList();

    STTrueFalseBlank.Enum getColHiddenArray(int i);

    STTrueFalseBlank.Enum[] getColHiddenArray();

    List<STTrueFalseBlank.Enum> getColHiddenList();

    STTrueFalseBlank.Enum getColoredArray(int i);

    STTrueFalseBlank.Enum[] getColoredArray();

    List<STTrueFalseBlank.Enum> getColoredList();

    BigInteger getColumnArray(int i);

    BigInteger[] getColumnArray();

    List<BigInteger> getColumnList();

    STTrueFalseBlank.Enum getDDEArray(int i);

    STTrueFalseBlank.Enum[] getDDEArray();

    List<STTrueFalseBlank.Enum> getDDEList();

    STTrueFalseBlank.Enum getDefaultArray(int i);

    STTrueFalseBlank.Enum[] getDefaultArray();

    List<STTrueFalseBlank.Enum> getDefaultList();

    STTrueFalseBlank.Enum getDefaultSizeArray(int i);

    STTrueFalseBlank.Enum[] getDefaultSizeArray();

    List<STTrueFalseBlank.Enum> getDefaultSizeList();

    STTrueFalseBlank.Enum getDisabledArray(int i);

    STTrueFalseBlank.Enum[] getDisabledArray();

    List<STTrueFalseBlank.Enum> getDisabledList();

    STTrueFalseBlank.Enum getDismissArray(int i);

    STTrueFalseBlank.Enum[] getDismissArray();

    List<STTrueFalseBlank.Enum> getDismissList();

    BigInteger getDropLinesArray(int i);

    BigInteger[] getDropLinesArray();

    List<BigInteger> getDropLinesList();

    String getDropStyleArray(int i);

    String[] getDropStyleArray();

    List<String> getDropStyleList();

    BigInteger getDxArray(int i);

    BigInteger[] getDxArray();

    List<BigInteger> getDxList();

    STTrueFalseBlank.Enum getFirstButtonArray(int i);

    STTrueFalseBlank.Enum[] getFirstButtonArray();

    List<STTrueFalseBlank.Enum> getFirstButtonList();

    String getFmlaGroupArray(int i);

    String[] getFmlaGroupArray();

    List<String> getFmlaGroupList();

    String getFmlaLinkArray(int i);

    String[] getFmlaLinkArray();

    List<String> getFmlaLinkList();

    String getFmlaMacroArray(int i);

    String[] getFmlaMacroArray();

    List<String> getFmlaMacroList();

    String getFmlaPictArray(int i);

    String[] getFmlaPictArray();

    List<String> getFmlaPictList();

    String getFmlaRangeArray(int i);

    String[] getFmlaRangeArray();

    List<String> getFmlaRangeList();

    String getFmlaTxbxArray(int i);

    String[] getFmlaTxbxArray();

    List<String> getFmlaTxbxList();

    STTrueFalseBlank.Enum getHelpArray(int i);

    STTrueFalseBlank.Enum[] getHelpArray();

    List<STTrueFalseBlank.Enum> getHelpList();

    STTrueFalseBlank.Enum getHorizArray(int i);

    STTrueFalseBlank.Enum[] getHorizArray();

    List<STTrueFalseBlank.Enum> getHorizList();

    BigInteger getIncArray(int i);

    BigInteger[] getIncArray();

    List<BigInteger> getIncList();

    STTrueFalseBlank.Enum getJustLastXArray(int i);

    STTrueFalseBlank.Enum[] getJustLastXArray();

    List<STTrueFalseBlank.Enum> getJustLastXList();

    String getLCTArray(int i);

    String[] getLCTArray();

    List<String> getLCTList();

    String getListItemArray(int i);

    String[] getListItemArray();

    List<String> getListItemList();

    STTrueFalseBlank.Enum getLockTextArray(int i);

    STTrueFalseBlank.Enum[] getLockTextArray();

    List<STTrueFalseBlank.Enum> getLockTextList();

    STTrueFalseBlank.Enum getLockedArray(int i);

    STTrueFalseBlank.Enum[] getLockedArray();

    List<STTrueFalseBlank.Enum> getLockedList();

    STTrueFalseBlank.Enum getMapOCXArray(int i);

    STTrueFalseBlank.Enum[] getMapOCXArray();

    List<STTrueFalseBlank.Enum> getMapOCXList();

    BigInteger getMaxArray(int i);

    BigInteger[] getMaxArray();

    List<BigInteger> getMaxList();

    BigInteger getMinArray(int i);

    BigInteger[] getMinArray();

    List<BigInteger> getMinList();

    STTrueFalseBlank.Enum getMoveWithCellsArray(int i);

    STTrueFalseBlank.Enum[] getMoveWithCellsArray();

    List<STTrueFalseBlank.Enum> getMoveWithCellsList();

    STTrueFalseBlank.Enum getMultiLineArray(int i);

    STTrueFalseBlank.Enum[] getMultiLineArray();

    List<STTrueFalseBlank.Enum> getMultiLineList();

    String getMultiSelArray(int i);

    String[] getMultiSelArray();

    List<String> getMultiSelList();

    STTrueFalseBlank.Enum getNoThreeD2Array(int i);

    STTrueFalseBlank.Enum[] getNoThreeD2Array();

    List<STTrueFalseBlank.Enum> getNoThreeD2List();

    STTrueFalseBlank.Enum getNoThreeDArray(int i);

    STTrueFalseBlank.Enum[] getNoThreeDArray();

    List<STTrueFalseBlank.Enum> getNoThreeDList();

    STObjectType.Enum getObjectType();

    BigInteger getPageArray(int i);

    BigInteger[] getPageArray();

    List<BigInteger> getPageList();

    STTrueFalseBlank.Enum getPrintObjectArray(int i);

    STTrueFalseBlank.Enum[] getPrintObjectArray();

    List<STTrueFalseBlank.Enum> getPrintObjectList();

    STTrueFalseBlank.Enum getRecalcAlwaysArray(int i);

    STTrueFalseBlank.Enum[] getRecalcAlwaysArray();

    List<STTrueFalseBlank.Enum> getRecalcAlwaysList();

    BigInteger getRowArray(int i);

    BigInteger[] getRowArray();

    STTrueFalseBlank.Enum getRowHiddenArray(int i);

    STTrueFalseBlank.Enum[] getRowHiddenArray();

    List<STTrueFalseBlank.Enum> getRowHiddenList();

    List<BigInteger> getRowList();

    String getScriptExtendedArray(int i);

    String[] getScriptExtendedArray();

    List<String> getScriptExtendedList();

    BigInteger getScriptLanguageArray(int i);

    BigInteger[] getScriptLanguageArray();

    List<BigInteger> getScriptLanguageList();

    BigInteger getScriptLocationArray(int i);

    BigInteger[] getScriptLocationArray();

    List<BigInteger> getScriptLocationList();

    String getScriptTextArray(int i);

    String[] getScriptTextArray();

    List<String> getScriptTextList();

    STTrueFalseBlank.Enum getSecretEditArray(int i);

    STTrueFalseBlank.Enum[] getSecretEditArray();

    List<STTrueFalseBlank.Enum> getSecretEditList();

    BigInteger getSelArray(int i);

    BigInteger[] getSelArray();

    List<BigInteger> getSelList();

    String getSelTypeArray(int i);

    String[] getSelTypeArray();

    List<String> getSelTypeList();

    STTrueFalseBlank.Enum getSizeWithCellsArray(int i);

    STTrueFalseBlank.Enum[] getSizeWithCellsArray();

    List<STTrueFalseBlank.Enum> getSizeWithCellsList();

    String getTextHAlignArray(int i);

    String[] getTextHAlignArray();

    List<String> getTextHAlignList();

    String getTextVAlignArray(int i);

    String[] getTextVAlignArray();

    List<String> getTextVAlignList();

    STTrueFalseBlank.Enum getUIObjArray(int i);

    STTrueFalseBlank.Enum[] getUIObjArray();

    List<STTrueFalseBlank.Enum> getUIObjList();

    STTrueFalseBlank.Enum getVScrollArray(int i);

    STTrueFalseBlank.Enum[] getVScrollArray();

    List<STTrueFalseBlank.Enum> getVScrollList();

    BigInteger getVTEditArray(int i);

    BigInteger[] getVTEditArray();

    List<BigInteger> getVTEditList();

    BigInteger getValArray(int i);

    BigInteger[] getValArray();

    List<BigInteger> getValList();

    STTrueFalseBlank.Enum getValidIdsArray(int i);

    STTrueFalseBlank.Enum[] getValidIdsArray();

    List<STTrueFalseBlank.Enum> getValidIdsList();

    STTrueFalseBlank.Enum getVisibleArray(int i);

    STTrueFalseBlank.Enum[] getVisibleArray();

    List<STTrueFalseBlank.Enum> getVisibleList();

    BigInteger getWidthMinArray(int i);

    BigInteger[] getWidthMinArray();

    List<BigInteger> getWidthMinList();

    void insertAccel(int i, BigInteger bigInteger);

    void insertAccel2(int i, BigInteger bigInteger);

    void insertAnchor(int i, String str);

    void insertAutoFill(int i, STTrueFalseBlank.Enum enumR);

    void insertAutoLine(int i, STTrueFalseBlank.Enum enumR);

    void insertAutoPict(int i, STTrueFalseBlank.Enum enumR);

    void insertAutoScale(int i, STTrueFalseBlank.Enum enumR);

    void insertCF(int i, String str);

    void insertCamera(int i, STTrueFalseBlank.Enum enumR);

    void insertCancel(int i, STTrueFalseBlank.Enum enumR);

    void insertChecked(int i, BigInteger bigInteger);

    void insertColHidden(int i, STTrueFalseBlank.Enum enumR);

    void insertColored(int i, STTrueFalseBlank.Enum enumR);

    void insertColumn(int i, BigInteger bigInteger);

    void insertDDE(int i, STTrueFalseBlank.Enum enumR);

    void insertDefault(int i, STTrueFalseBlank.Enum enumR);

    void insertDefaultSize(int i, STTrueFalseBlank.Enum enumR);

    void insertDisabled(int i, STTrueFalseBlank.Enum enumR);

    void insertDismiss(int i, STTrueFalseBlank.Enum enumR);

    void insertDropLines(int i, BigInteger bigInteger);

    void insertDropStyle(int i, String str);

    void insertDx(int i, BigInteger bigInteger);

    void insertFirstButton(int i, STTrueFalseBlank.Enum enumR);

    void insertFmlaGroup(int i, String str);

    void insertFmlaLink(int i, String str);

    void insertFmlaMacro(int i, String str);

    void insertFmlaPict(int i, String str);

    void insertFmlaRange(int i, String str);

    void insertFmlaTxbx(int i, String str);

    void insertHelp(int i, STTrueFalseBlank.Enum enumR);

    void insertHoriz(int i, STTrueFalseBlank.Enum enumR);

    void insertInc(int i, BigInteger bigInteger);

    void insertJustLastX(int i, STTrueFalseBlank.Enum enumR);

    void insertLCT(int i, String str);

    void insertListItem(int i, String str);

    void insertLockText(int i, STTrueFalseBlank.Enum enumR);

    void insertLocked(int i, STTrueFalseBlank.Enum enumR);

    void insertMapOCX(int i, STTrueFalseBlank.Enum enumR);

    void insertMax(int i, BigInteger bigInteger);

    void insertMin(int i, BigInteger bigInteger);

    void insertMoveWithCells(int i, STTrueFalseBlank.Enum enumR);

    void insertMultiLine(int i, STTrueFalseBlank.Enum enumR);

    void insertMultiSel(int i, String str);

    XmlInteger insertNewAccel(int i);

    XmlInteger insertNewAccel2(int i);

    XmlString insertNewAnchor(int i);

    STTrueFalseBlank insertNewAutoFill(int i);

    STTrueFalseBlank insertNewAutoLine(int i);

    STTrueFalseBlank insertNewAutoPict(int i);

    STTrueFalseBlank insertNewAutoScale(int i);

    STCF insertNewCF(int i);

    STTrueFalseBlank insertNewCamera(int i);

    STTrueFalseBlank insertNewCancel(int i);

    XmlInteger insertNewChecked(int i);

    STTrueFalseBlank insertNewColHidden(int i);

    STTrueFalseBlank insertNewColored(int i);

    XmlInteger insertNewColumn(int i);

    STTrueFalseBlank insertNewDDE(int i);

    STTrueFalseBlank insertNewDefault(int i);

    STTrueFalseBlank insertNewDefaultSize(int i);

    STTrueFalseBlank insertNewDisabled(int i);

    STTrueFalseBlank insertNewDismiss(int i);

    XmlInteger insertNewDropLines(int i);

    XmlString insertNewDropStyle(int i);

    XmlInteger insertNewDx(int i);

    STTrueFalseBlank insertNewFirstButton(int i);

    XmlString insertNewFmlaGroup(int i);

    XmlString insertNewFmlaLink(int i);

    XmlString insertNewFmlaMacro(int i);

    XmlString insertNewFmlaPict(int i);

    XmlString insertNewFmlaRange(int i);

    XmlString insertNewFmlaTxbx(int i);

    STTrueFalseBlank insertNewHelp(int i);

    STTrueFalseBlank insertNewHoriz(int i);

    XmlInteger insertNewInc(int i);

    STTrueFalseBlank insertNewJustLastX(int i);

    XmlString insertNewLCT(int i);

    XmlString insertNewListItem(int i);

    STTrueFalseBlank insertNewLockText(int i);

    STTrueFalseBlank insertNewLocked(int i);

    STTrueFalseBlank insertNewMapOCX(int i);

    XmlInteger insertNewMax(int i);

    XmlInteger insertNewMin(int i);

    STTrueFalseBlank insertNewMoveWithCells(int i);

    STTrueFalseBlank insertNewMultiLine(int i);

    XmlString insertNewMultiSel(int i);

    STTrueFalseBlank insertNewNoThreeD(int i);

    STTrueFalseBlank insertNewNoThreeD2(int i);

    XmlInteger insertNewPage(int i);

    STTrueFalseBlank insertNewPrintObject(int i);

    STTrueFalseBlank insertNewRecalcAlways(int i);

    XmlInteger insertNewRow(int i);

    STTrueFalseBlank insertNewRowHidden(int i);

    XmlString insertNewScriptExtended(int i);

    XmlNonNegativeInteger insertNewScriptLanguage(int i);

    XmlNonNegativeInteger insertNewScriptLocation(int i);

    XmlString insertNewScriptText(int i);

    STTrueFalseBlank insertNewSecretEdit(int i);

    XmlInteger insertNewSel(int i);

    XmlString insertNewSelType(int i);

    STTrueFalseBlank insertNewSizeWithCells(int i);

    XmlString insertNewTextHAlign(int i);

    XmlString insertNewTextVAlign(int i);

    STTrueFalseBlank insertNewUIObj(int i);

    STTrueFalseBlank insertNewVScroll(int i);

    XmlInteger insertNewVTEdit(int i);

    XmlInteger insertNewVal(int i);

    STTrueFalseBlank insertNewValidIds(int i);

    STTrueFalseBlank insertNewVisible(int i);

    XmlInteger insertNewWidthMin(int i);

    void insertNoThreeD(int i, STTrueFalseBlank.Enum enumR);

    void insertNoThreeD2(int i, STTrueFalseBlank.Enum enumR);

    void insertPage(int i, BigInteger bigInteger);

    void insertPrintObject(int i, STTrueFalseBlank.Enum enumR);

    void insertRecalcAlways(int i, STTrueFalseBlank.Enum enumR);

    void insertRow(int i, BigInteger bigInteger);

    void insertRowHidden(int i, STTrueFalseBlank.Enum enumR);

    void insertScriptExtended(int i, String str);

    void insertScriptLanguage(int i, BigInteger bigInteger);

    void insertScriptLocation(int i, BigInteger bigInteger);

    void insertScriptText(int i, String str);

    void insertSecretEdit(int i, STTrueFalseBlank.Enum enumR);

    void insertSel(int i, BigInteger bigInteger);

    void insertSelType(int i, String str);

    void insertSizeWithCells(int i, STTrueFalseBlank.Enum enumR);

    void insertTextHAlign(int i, String str);

    void insertTextVAlign(int i, String str);

    void insertUIObj(int i, STTrueFalseBlank.Enum enumR);

    void insertVScroll(int i, STTrueFalseBlank.Enum enumR);

    void insertVTEdit(int i, BigInteger bigInteger);

    void insertVal(int i, BigInteger bigInteger);

    void insertValidIds(int i, STTrueFalseBlank.Enum enumR);

    void insertVisible(int i, STTrueFalseBlank.Enum enumR);

    void insertWidthMin(int i, BigInteger bigInteger);

    void removeAccel(int i);

    void removeAccel2(int i);

    void removeAnchor(int i);

    void removeAutoFill(int i);

    void removeAutoLine(int i);

    void removeAutoPict(int i);

    void removeAutoScale(int i);

    void removeCF(int i);

    void removeCamera(int i);

    void removeCancel(int i);

    void removeChecked(int i);

    void removeColHidden(int i);

    void removeColored(int i);

    void removeColumn(int i);

    void removeDDE(int i);

    void removeDefault(int i);

    void removeDefaultSize(int i);

    void removeDisabled(int i);

    void removeDismiss(int i);

    void removeDropLines(int i);

    void removeDropStyle(int i);

    void removeDx(int i);

    void removeFirstButton(int i);

    void removeFmlaGroup(int i);

    void removeFmlaLink(int i);

    void removeFmlaMacro(int i);

    void removeFmlaPict(int i);

    void removeFmlaRange(int i);

    void removeFmlaTxbx(int i);

    void removeHelp(int i);

    void removeHoriz(int i);

    void removeInc(int i);

    void removeJustLastX(int i);

    void removeLCT(int i);

    void removeListItem(int i);

    void removeLockText(int i);

    void removeLocked(int i);

    void removeMapOCX(int i);

    void removeMax(int i);

    void removeMin(int i);

    void removeMoveWithCells(int i);

    void removeMultiLine(int i);

    void removeMultiSel(int i);

    void removeNoThreeD(int i);

    void removeNoThreeD2(int i);

    void removePage(int i);

    void removePrintObject(int i);

    void removeRecalcAlways(int i);

    void removeRow(int i);

    void removeRowHidden(int i);

    void removeScriptExtended(int i);

    void removeScriptLanguage(int i);

    void removeScriptLocation(int i);

    void removeScriptText(int i);

    void removeSecretEdit(int i);

    void removeSel(int i);

    void removeSelType(int i);

    void removeSizeWithCells(int i);

    void removeTextHAlign(int i);

    void removeTextVAlign(int i);

    void removeUIObj(int i);

    void removeVScroll(int i);

    void removeVTEdit(int i);

    void removeVal(int i);

    void removeValidIds(int i);

    void removeVisible(int i);

    void removeWidthMin(int i);

    void setAccel2Array(int i, BigInteger bigInteger);

    void setAccel2Array(BigInteger[] bigIntegerArr);

    void setAccelArray(int i, BigInteger bigInteger);

    void setAccelArray(BigInteger[] bigIntegerArr);

    void setAnchorArray(int i, String str);

    void setAnchorArray(String[] strArr);

    void setAutoFillArray(int i, STTrueFalseBlank.Enum enumR);

    void setAutoFillArray(STTrueFalseBlank.Enum[] enumArr);

    void setAutoLineArray(int i, STTrueFalseBlank.Enum enumR);

    void setAutoLineArray(STTrueFalseBlank.Enum[] enumArr);

    void setAutoPictArray(int i, STTrueFalseBlank.Enum enumR);

    void setAutoPictArray(STTrueFalseBlank.Enum[] enumArr);

    void setAutoScaleArray(int i, STTrueFalseBlank.Enum enumR);

    void setAutoScaleArray(STTrueFalseBlank.Enum[] enumArr);

    void setCFArray(int i, String str);

    void setCFArray(String[] strArr);

    void setCameraArray(int i, STTrueFalseBlank.Enum enumR);

    void setCameraArray(STTrueFalseBlank.Enum[] enumArr);

    void setCancelArray(int i, STTrueFalseBlank.Enum enumR);

    void setCancelArray(STTrueFalseBlank.Enum[] enumArr);

    void setCheckedArray(int i, BigInteger bigInteger);

    void setCheckedArray(BigInteger[] bigIntegerArr);

    void setColHiddenArray(int i, STTrueFalseBlank.Enum enumR);

    void setColHiddenArray(STTrueFalseBlank.Enum[] enumArr);

    void setColoredArray(int i, STTrueFalseBlank.Enum enumR);

    void setColoredArray(STTrueFalseBlank.Enum[] enumArr);

    void setColumnArray(int i, BigInteger bigInteger);

    void setColumnArray(BigInteger[] bigIntegerArr);

    void setDDEArray(int i, STTrueFalseBlank.Enum enumR);

    void setDDEArray(STTrueFalseBlank.Enum[] enumArr);

    void setDefaultArray(int i, STTrueFalseBlank.Enum enumR);

    void setDefaultArray(STTrueFalseBlank.Enum[] enumArr);

    void setDefaultSizeArray(int i, STTrueFalseBlank.Enum enumR);

    void setDefaultSizeArray(STTrueFalseBlank.Enum[] enumArr);

    void setDisabledArray(int i, STTrueFalseBlank.Enum enumR);

    void setDisabledArray(STTrueFalseBlank.Enum[] enumArr);

    void setDismissArray(int i, STTrueFalseBlank.Enum enumR);

    void setDismissArray(STTrueFalseBlank.Enum[] enumArr);

    void setDropLinesArray(int i, BigInteger bigInteger);

    void setDropLinesArray(BigInteger[] bigIntegerArr);

    void setDropStyleArray(int i, String str);

    void setDropStyleArray(String[] strArr);

    void setDxArray(int i, BigInteger bigInteger);

    void setDxArray(BigInteger[] bigIntegerArr);

    void setFirstButtonArray(int i, STTrueFalseBlank.Enum enumR);

    void setFirstButtonArray(STTrueFalseBlank.Enum[] enumArr);

    void setFmlaGroupArray(int i, String str);

    void setFmlaGroupArray(String[] strArr);

    void setFmlaLinkArray(int i, String str);

    void setFmlaLinkArray(String[] strArr);

    void setFmlaMacroArray(int i, String str);

    void setFmlaMacroArray(String[] strArr);

    void setFmlaPictArray(int i, String str);

    void setFmlaPictArray(String[] strArr);

    void setFmlaRangeArray(int i, String str);

    void setFmlaRangeArray(String[] strArr);

    void setFmlaTxbxArray(int i, String str);

    void setFmlaTxbxArray(String[] strArr);

    void setHelpArray(int i, STTrueFalseBlank.Enum enumR);

    void setHelpArray(STTrueFalseBlank.Enum[] enumArr);

    void setHorizArray(int i, STTrueFalseBlank.Enum enumR);

    void setHorizArray(STTrueFalseBlank.Enum[] enumArr);

    void setIncArray(int i, BigInteger bigInteger);

    void setIncArray(BigInteger[] bigIntegerArr);

    void setJustLastXArray(int i, STTrueFalseBlank.Enum enumR);

    void setJustLastXArray(STTrueFalseBlank.Enum[] enumArr);

    void setLCTArray(int i, String str);

    void setLCTArray(String[] strArr);

    void setListItemArray(int i, String str);

    void setListItemArray(String[] strArr);

    void setLockTextArray(int i, STTrueFalseBlank.Enum enumR);

    void setLockTextArray(STTrueFalseBlank.Enum[] enumArr);

    void setLockedArray(int i, STTrueFalseBlank.Enum enumR);

    void setLockedArray(STTrueFalseBlank.Enum[] enumArr);

    void setMapOCXArray(int i, STTrueFalseBlank.Enum enumR);

    void setMapOCXArray(STTrueFalseBlank.Enum[] enumArr);

    void setMaxArray(int i, BigInteger bigInteger);

    void setMaxArray(BigInteger[] bigIntegerArr);

    void setMinArray(int i, BigInteger bigInteger);

    void setMinArray(BigInteger[] bigIntegerArr);

    void setMoveWithCellsArray(int i, STTrueFalseBlank.Enum enumR);

    void setMoveWithCellsArray(STTrueFalseBlank.Enum[] enumArr);

    void setMultiLineArray(int i, STTrueFalseBlank.Enum enumR);

    void setMultiLineArray(STTrueFalseBlank.Enum[] enumArr);

    void setMultiSelArray(int i, String str);

    void setMultiSelArray(String[] strArr);

    void setNoThreeD2Array(int i, STTrueFalseBlank.Enum enumR);

    void setNoThreeD2Array(STTrueFalseBlank.Enum[] enumArr);

    void setNoThreeDArray(int i, STTrueFalseBlank.Enum enumR);

    void setNoThreeDArray(STTrueFalseBlank.Enum[] enumArr);

    void setObjectType(STObjectType.Enum enumR);

    void setPageArray(int i, BigInteger bigInteger);

    void setPageArray(BigInteger[] bigIntegerArr);

    void setPrintObjectArray(int i, STTrueFalseBlank.Enum enumR);

    void setPrintObjectArray(STTrueFalseBlank.Enum[] enumArr);

    void setRecalcAlwaysArray(int i, STTrueFalseBlank.Enum enumR);

    void setRecalcAlwaysArray(STTrueFalseBlank.Enum[] enumArr);

    void setRowArray(int i, BigInteger bigInteger);

    void setRowArray(BigInteger[] bigIntegerArr);

    void setRowHiddenArray(int i, STTrueFalseBlank.Enum enumR);

    void setRowHiddenArray(STTrueFalseBlank.Enum[] enumArr);

    void setScriptExtendedArray(int i, String str);

    void setScriptExtendedArray(String[] strArr);

    void setScriptLanguageArray(int i, BigInteger bigInteger);

    void setScriptLanguageArray(BigInteger[] bigIntegerArr);

    void setScriptLocationArray(int i, BigInteger bigInteger);

    void setScriptLocationArray(BigInteger[] bigIntegerArr);

    void setScriptTextArray(int i, String str);

    void setScriptTextArray(String[] strArr);

    void setSecretEditArray(int i, STTrueFalseBlank.Enum enumR);

    void setSecretEditArray(STTrueFalseBlank.Enum[] enumArr);

    void setSelArray(int i, BigInteger bigInteger);

    void setSelArray(BigInteger[] bigIntegerArr);

    void setSelTypeArray(int i, String str);

    void setSelTypeArray(String[] strArr);

    void setSizeWithCellsArray(int i, STTrueFalseBlank.Enum enumR);

    void setSizeWithCellsArray(STTrueFalseBlank.Enum[] enumArr);

    void setTextHAlignArray(int i, String str);

    void setTextHAlignArray(String[] strArr);

    void setTextVAlignArray(int i, String str);

    void setTextVAlignArray(String[] strArr);

    void setUIObjArray(int i, STTrueFalseBlank.Enum enumR);

    void setUIObjArray(STTrueFalseBlank.Enum[] enumArr);

    void setVScrollArray(int i, STTrueFalseBlank.Enum enumR);

    void setVScrollArray(STTrueFalseBlank.Enum[] enumArr);

    void setVTEditArray(int i, BigInteger bigInteger);

    void setVTEditArray(BigInteger[] bigIntegerArr);

    void setValArray(int i, BigInteger bigInteger);

    void setValArray(BigInteger[] bigIntegerArr);

    void setValidIdsArray(int i, STTrueFalseBlank.Enum enumR);

    void setValidIdsArray(STTrueFalseBlank.Enum[] enumArr);

    void setVisibleArray(int i, STTrueFalseBlank.Enum enumR);

    void setVisibleArray(STTrueFalseBlank.Enum[] enumArr);

    void setWidthMinArray(int i, BigInteger bigInteger);

    void setWidthMinArray(BigInteger[] bigIntegerArr);

    int sizeOfAccel2Array();

    int sizeOfAccelArray();

    int sizeOfAnchorArray();

    int sizeOfAutoFillArray();

    int sizeOfAutoLineArray();

    int sizeOfAutoPictArray();

    int sizeOfAutoScaleArray();

    int sizeOfCFArray();

    int sizeOfCameraArray();

    int sizeOfCancelArray();

    int sizeOfCheckedArray();

    int sizeOfColHiddenArray();

    int sizeOfColoredArray();

    int sizeOfColumnArray();

    int sizeOfDDEArray();

    int sizeOfDefaultArray();

    int sizeOfDefaultSizeArray();

    int sizeOfDisabledArray();

    int sizeOfDismissArray();

    int sizeOfDropLinesArray();

    int sizeOfDropStyleArray();

    int sizeOfDxArray();

    int sizeOfFirstButtonArray();

    int sizeOfFmlaGroupArray();

    int sizeOfFmlaLinkArray();

    int sizeOfFmlaMacroArray();

    int sizeOfFmlaPictArray();

    int sizeOfFmlaRangeArray();

    int sizeOfFmlaTxbxArray();

    int sizeOfHelpArray();

    int sizeOfHorizArray();

    int sizeOfIncArray();

    int sizeOfJustLastXArray();

    int sizeOfLCTArray();

    int sizeOfListItemArray();

    int sizeOfLockTextArray();

    int sizeOfLockedArray();

    int sizeOfMapOCXArray();

    int sizeOfMaxArray();

    int sizeOfMinArray();

    int sizeOfMoveWithCellsArray();

    int sizeOfMultiLineArray();

    int sizeOfMultiSelArray();

    int sizeOfNoThreeD2Array();

    int sizeOfNoThreeDArray();

    int sizeOfPageArray();

    int sizeOfPrintObjectArray();

    int sizeOfRecalcAlwaysArray();

    int sizeOfRowArray();

    int sizeOfRowHiddenArray();

    int sizeOfScriptExtendedArray();

    int sizeOfScriptLanguageArray();

    int sizeOfScriptLocationArray();

    int sizeOfScriptTextArray();

    int sizeOfSecretEditArray();

    int sizeOfSelArray();

    int sizeOfSelTypeArray();

    int sizeOfSizeWithCellsArray();

    int sizeOfTextHAlignArray();

    int sizeOfTextVAlignArray();

    int sizeOfUIObjArray();

    int sizeOfVScrollArray();

    int sizeOfVTEditArray();

    int sizeOfValArray();

    int sizeOfValidIdsArray();

    int sizeOfVisibleArray();

    int sizeOfWidthMinArray();

    XmlInteger xgetAccel2Array(int i);

    XmlInteger[] xgetAccel2Array();

    List<XmlInteger> xgetAccel2List();

    XmlInteger xgetAccelArray(int i);

    XmlInteger[] xgetAccelArray();

    List<XmlInteger> xgetAccelList();

    XmlString xgetAnchorArray(int i);

    XmlString[] xgetAnchorArray();

    List<XmlString> xgetAnchorList();

    STTrueFalseBlank xgetAutoFillArray(int i);

    STTrueFalseBlank[] xgetAutoFillArray();

    List<STTrueFalseBlank> xgetAutoFillList();

    STTrueFalseBlank xgetAutoLineArray(int i);

    STTrueFalseBlank[] xgetAutoLineArray();

    List<STTrueFalseBlank> xgetAutoLineList();

    STTrueFalseBlank xgetAutoPictArray(int i);

    STTrueFalseBlank[] xgetAutoPictArray();

    List<STTrueFalseBlank> xgetAutoPictList();

    STTrueFalseBlank xgetAutoScaleArray(int i);

    STTrueFalseBlank[] xgetAutoScaleArray();

    List<STTrueFalseBlank> xgetAutoScaleList();

    STCF xgetCFArray(int i);

    STCF[] xgetCFArray();

    List<STCF> xgetCFList();

    STTrueFalseBlank xgetCameraArray(int i);

    STTrueFalseBlank[] xgetCameraArray();

    List<STTrueFalseBlank> xgetCameraList();

    STTrueFalseBlank xgetCancelArray(int i);

    STTrueFalseBlank[] xgetCancelArray();

    List<STTrueFalseBlank> xgetCancelList();

    XmlInteger xgetCheckedArray(int i);

    XmlInteger[] xgetCheckedArray();

    List<XmlInteger> xgetCheckedList();

    STTrueFalseBlank xgetColHiddenArray(int i);

    STTrueFalseBlank[] xgetColHiddenArray();

    List<STTrueFalseBlank> xgetColHiddenList();

    STTrueFalseBlank xgetColoredArray(int i);

    STTrueFalseBlank[] xgetColoredArray();

    List<STTrueFalseBlank> xgetColoredList();

    XmlInteger xgetColumnArray(int i);

    XmlInteger[] xgetColumnArray();

    List<XmlInteger> xgetColumnList();

    STTrueFalseBlank xgetDDEArray(int i);

    STTrueFalseBlank[] xgetDDEArray();

    List<STTrueFalseBlank> xgetDDEList();

    STTrueFalseBlank xgetDefaultArray(int i);

    STTrueFalseBlank[] xgetDefaultArray();

    List<STTrueFalseBlank> xgetDefaultList();

    STTrueFalseBlank xgetDefaultSizeArray(int i);

    STTrueFalseBlank[] xgetDefaultSizeArray();

    List<STTrueFalseBlank> xgetDefaultSizeList();

    STTrueFalseBlank xgetDisabledArray(int i);

    STTrueFalseBlank[] xgetDisabledArray();

    List<STTrueFalseBlank> xgetDisabledList();

    STTrueFalseBlank xgetDismissArray(int i);

    STTrueFalseBlank[] xgetDismissArray();

    List<STTrueFalseBlank> xgetDismissList();

    XmlInteger xgetDropLinesArray(int i);

    XmlInteger[] xgetDropLinesArray();

    List<XmlInteger> xgetDropLinesList();

    XmlString xgetDropStyleArray(int i);

    XmlString[] xgetDropStyleArray();

    List<XmlString> xgetDropStyleList();

    XmlInteger xgetDxArray(int i);

    XmlInteger[] xgetDxArray();

    List<XmlInteger> xgetDxList();

    STTrueFalseBlank xgetFirstButtonArray(int i);

    STTrueFalseBlank[] xgetFirstButtonArray();

    List<STTrueFalseBlank> xgetFirstButtonList();

    XmlString xgetFmlaGroupArray(int i);

    XmlString[] xgetFmlaGroupArray();

    List<XmlString> xgetFmlaGroupList();

    XmlString xgetFmlaLinkArray(int i);

    XmlString[] xgetFmlaLinkArray();

    List<XmlString> xgetFmlaLinkList();

    XmlString xgetFmlaMacroArray(int i);

    XmlString[] xgetFmlaMacroArray();

    List<XmlString> xgetFmlaMacroList();

    XmlString xgetFmlaPictArray(int i);

    XmlString[] xgetFmlaPictArray();

    List<XmlString> xgetFmlaPictList();

    XmlString xgetFmlaRangeArray(int i);

    XmlString[] xgetFmlaRangeArray();

    List<XmlString> xgetFmlaRangeList();

    XmlString xgetFmlaTxbxArray(int i);

    XmlString[] xgetFmlaTxbxArray();

    List<XmlString> xgetFmlaTxbxList();

    STTrueFalseBlank xgetHelpArray(int i);

    STTrueFalseBlank[] xgetHelpArray();

    List<STTrueFalseBlank> xgetHelpList();

    STTrueFalseBlank xgetHorizArray(int i);

    STTrueFalseBlank[] xgetHorizArray();

    List<STTrueFalseBlank> xgetHorizList();

    XmlInteger xgetIncArray(int i);

    XmlInteger[] xgetIncArray();

    List<XmlInteger> xgetIncList();

    STTrueFalseBlank xgetJustLastXArray(int i);

    STTrueFalseBlank[] xgetJustLastXArray();

    List<STTrueFalseBlank> xgetJustLastXList();

    XmlString xgetLCTArray(int i);

    XmlString[] xgetLCTArray();

    List<XmlString> xgetLCTList();

    XmlString xgetListItemArray(int i);

    XmlString[] xgetListItemArray();

    List<XmlString> xgetListItemList();

    STTrueFalseBlank xgetLockTextArray(int i);

    STTrueFalseBlank[] xgetLockTextArray();

    List<STTrueFalseBlank> xgetLockTextList();

    STTrueFalseBlank xgetLockedArray(int i);

    STTrueFalseBlank[] xgetLockedArray();

    List<STTrueFalseBlank> xgetLockedList();

    STTrueFalseBlank xgetMapOCXArray(int i);

    STTrueFalseBlank[] xgetMapOCXArray();

    List<STTrueFalseBlank> xgetMapOCXList();

    XmlInteger xgetMaxArray(int i);

    XmlInteger[] xgetMaxArray();

    List<XmlInteger> xgetMaxList();

    XmlInteger xgetMinArray(int i);

    XmlInteger[] xgetMinArray();

    List<XmlInteger> xgetMinList();

    STTrueFalseBlank xgetMoveWithCellsArray(int i);

    STTrueFalseBlank[] xgetMoveWithCellsArray();

    List<STTrueFalseBlank> xgetMoveWithCellsList();

    STTrueFalseBlank xgetMultiLineArray(int i);

    STTrueFalseBlank[] xgetMultiLineArray();

    List<STTrueFalseBlank> xgetMultiLineList();

    XmlString xgetMultiSelArray(int i);

    XmlString[] xgetMultiSelArray();

    List<XmlString> xgetMultiSelList();

    STTrueFalseBlank xgetNoThreeD2Array(int i);

    STTrueFalseBlank[] xgetNoThreeD2Array();

    List<STTrueFalseBlank> xgetNoThreeD2List();

    STTrueFalseBlank xgetNoThreeDArray(int i);

    STTrueFalseBlank[] xgetNoThreeDArray();

    List<STTrueFalseBlank> xgetNoThreeDList();

    STObjectType xgetObjectType();

    XmlInteger xgetPageArray(int i);

    XmlInteger[] xgetPageArray();

    List<XmlInteger> xgetPageList();

    STTrueFalseBlank xgetPrintObjectArray(int i);

    STTrueFalseBlank[] xgetPrintObjectArray();

    List<STTrueFalseBlank> xgetPrintObjectList();

    STTrueFalseBlank xgetRecalcAlwaysArray(int i);

    STTrueFalseBlank[] xgetRecalcAlwaysArray();

    List<STTrueFalseBlank> xgetRecalcAlwaysList();

    XmlInteger xgetRowArray(int i);

    XmlInteger[] xgetRowArray();

    STTrueFalseBlank xgetRowHiddenArray(int i);

    STTrueFalseBlank[] xgetRowHiddenArray();

    List<STTrueFalseBlank> xgetRowHiddenList();

    List<XmlInteger> xgetRowList();

    XmlString xgetScriptExtendedArray(int i);

    XmlString[] xgetScriptExtendedArray();

    List<XmlString> xgetScriptExtendedList();

    XmlNonNegativeInteger xgetScriptLanguageArray(int i);

    XmlNonNegativeInteger[] xgetScriptLanguageArray();

    List<XmlNonNegativeInteger> xgetScriptLanguageList();

    XmlNonNegativeInteger xgetScriptLocationArray(int i);

    XmlNonNegativeInteger[] xgetScriptLocationArray();

    List<XmlNonNegativeInteger> xgetScriptLocationList();

    XmlString xgetScriptTextArray(int i);

    XmlString[] xgetScriptTextArray();

    List<XmlString> xgetScriptTextList();

    STTrueFalseBlank xgetSecretEditArray(int i);

    STTrueFalseBlank[] xgetSecretEditArray();

    List<STTrueFalseBlank> xgetSecretEditList();

    XmlInteger xgetSelArray(int i);

    XmlInteger[] xgetSelArray();

    List<XmlInteger> xgetSelList();

    XmlString xgetSelTypeArray(int i);

    XmlString[] xgetSelTypeArray();

    List<XmlString> xgetSelTypeList();

    STTrueFalseBlank xgetSizeWithCellsArray(int i);

    STTrueFalseBlank[] xgetSizeWithCellsArray();

    List<STTrueFalseBlank> xgetSizeWithCellsList();

    XmlString xgetTextHAlignArray(int i);

    XmlString[] xgetTextHAlignArray();

    List<XmlString> xgetTextHAlignList();

    XmlString xgetTextVAlignArray(int i);

    XmlString[] xgetTextVAlignArray();

    List<XmlString> xgetTextVAlignList();

    STTrueFalseBlank xgetUIObjArray(int i);

    STTrueFalseBlank[] xgetUIObjArray();

    List<STTrueFalseBlank> xgetUIObjList();

    STTrueFalseBlank xgetVScrollArray(int i);

    STTrueFalseBlank[] xgetVScrollArray();

    List<STTrueFalseBlank> xgetVScrollList();

    XmlInteger xgetVTEditArray(int i);

    XmlInteger[] xgetVTEditArray();

    List<XmlInteger> xgetVTEditList();

    XmlInteger xgetValArray(int i);

    XmlInteger[] xgetValArray();

    List<XmlInteger> xgetValList();

    STTrueFalseBlank xgetValidIdsArray(int i);

    STTrueFalseBlank[] xgetValidIdsArray();

    List<STTrueFalseBlank> xgetValidIdsList();

    STTrueFalseBlank xgetVisibleArray(int i);

    STTrueFalseBlank[] xgetVisibleArray();

    List<STTrueFalseBlank> xgetVisibleList();

    XmlInteger xgetWidthMinArray(int i);

    XmlInteger[] xgetWidthMinArray();

    List<XmlInteger> xgetWidthMinList();

    void xsetAccel2Array(int i, XmlInteger xmlInteger);

    void xsetAccel2Array(XmlInteger[] xmlIntegerArr);

    void xsetAccelArray(int i, XmlInteger xmlInteger);

    void xsetAccelArray(XmlInteger[] xmlIntegerArr);

    void xsetAnchorArray(int i, XmlString xmlString);

    void xsetAnchorArray(XmlString[] xmlStringArr);

    void xsetAutoFillArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetAutoFillArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetAutoLineArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetAutoLineArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetAutoPictArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetAutoPictArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetAutoScaleArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetAutoScaleArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetCFArray(int i, STCF stcf);

    void xsetCFArray(STCF[] stcfArr);

    void xsetCameraArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetCameraArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetCancelArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetCancelArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetCheckedArray(int i, XmlInteger xmlInteger);

    void xsetCheckedArray(XmlInteger[] xmlIntegerArr);

    void xsetColHiddenArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetColHiddenArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetColoredArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetColoredArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetColumnArray(int i, XmlInteger xmlInteger);

    void xsetColumnArray(XmlInteger[] xmlIntegerArr);

    void xsetDDEArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetDDEArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetDefaultArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetDefaultArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetDefaultSizeArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetDefaultSizeArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetDisabledArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetDisabledArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetDismissArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetDismissArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetDropLinesArray(int i, XmlInteger xmlInteger);

    void xsetDropLinesArray(XmlInteger[] xmlIntegerArr);

    void xsetDropStyleArray(int i, XmlString xmlString);

    void xsetDropStyleArray(XmlString[] xmlStringArr);

    void xsetDxArray(int i, XmlInteger xmlInteger);

    void xsetDxArray(XmlInteger[] xmlIntegerArr);

    void xsetFirstButtonArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetFirstButtonArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetFmlaGroupArray(int i, XmlString xmlString);

    void xsetFmlaGroupArray(XmlString[] xmlStringArr);

    void xsetFmlaLinkArray(int i, XmlString xmlString);

    void xsetFmlaLinkArray(XmlString[] xmlStringArr);

    void xsetFmlaMacroArray(int i, XmlString xmlString);

    void xsetFmlaMacroArray(XmlString[] xmlStringArr);

    void xsetFmlaPictArray(int i, XmlString xmlString);

    void xsetFmlaPictArray(XmlString[] xmlStringArr);

    void xsetFmlaRangeArray(int i, XmlString xmlString);

    void xsetFmlaRangeArray(XmlString[] xmlStringArr);

    void xsetFmlaTxbxArray(int i, XmlString xmlString);

    void xsetFmlaTxbxArray(XmlString[] xmlStringArr);

    void xsetHelpArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetHelpArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetHorizArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetHorizArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetIncArray(int i, XmlInteger xmlInteger);

    void xsetIncArray(XmlInteger[] xmlIntegerArr);

    void xsetJustLastXArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetJustLastXArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetLCTArray(int i, XmlString xmlString);

    void xsetLCTArray(XmlString[] xmlStringArr);

    void xsetListItemArray(int i, XmlString xmlString);

    void xsetListItemArray(XmlString[] xmlStringArr);

    void xsetLockTextArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetLockTextArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetLockedArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetLockedArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetMapOCXArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetMapOCXArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetMaxArray(int i, XmlInteger xmlInteger);

    void xsetMaxArray(XmlInteger[] xmlIntegerArr);

    void xsetMinArray(int i, XmlInteger xmlInteger);

    void xsetMinArray(XmlInteger[] xmlIntegerArr);

    void xsetMoveWithCellsArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetMoveWithCellsArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetMultiLineArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetMultiLineArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetMultiSelArray(int i, XmlString xmlString);

    void xsetMultiSelArray(XmlString[] xmlStringArr);

    void xsetNoThreeD2Array(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetNoThreeD2Array(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetNoThreeDArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetNoThreeDArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetObjectType(STObjectType sTObjectType);

    void xsetPageArray(int i, XmlInteger xmlInteger);

    void xsetPageArray(XmlInteger[] xmlIntegerArr);

    void xsetPrintObjectArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetPrintObjectArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetRecalcAlwaysArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetRecalcAlwaysArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetRowArray(int i, XmlInteger xmlInteger);

    void xsetRowArray(XmlInteger[] xmlIntegerArr);

    void xsetRowHiddenArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetRowHiddenArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetScriptExtendedArray(int i, XmlString xmlString);

    void xsetScriptExtendedArray(XmlString[] xmlStringArr);

    void xsetScriptLanguageArray(int i, XmlNonNegativeInteger xmlNonNegativeInteger);

    void xsetScriptLanguageArray(XmlNonNegativeInteger[] xmlNonNegativeIntegerArr);

    void xsetScriptLocationArray(int i, XmlNonNegativeInteger xmlNonNegativeInteger);

    void xsetScriptLocationArray(XmlNonNegativeInteger[] xmlNonNegativeIntegerArr);

    void xsetScriptTextArray(int i, XmlString xmlString);

    void xsetScriptTextArray(XmlString[] xmlStringArr);

    void xsetSecretEditArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetSecretEditArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetSelArray(int i, XmlInteger xmlInteger);

    void xsetSelArray(XmlInteger[] xmlIntegerArr);

    void xsetSelTypeArray(int i, XmlString xmlString);

    void xsetSelTypeArray(XmlString[] xmlStringArr);

    void xsetSizeWithCellsArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetSizeWithCellsArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetTextHAlignArray(int i, XmlString xmlString);

    void xsetTextHAlignArray(XmlString[] xmlStringArr);

    void xsetTextVAlignArray(int i, XmlString xmlString);

    void xsetTextVAlignArray(XmlString[] xmlStringArr);

    void xsetUIObjArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetUIObjArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetVScrollArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetVScrollArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetVTEditArray(int i, XmlInteger xmlInteger);

    void xsetVTEditArray(XmlInteger[] xmlIntegerArr);

    void xsetValArray(int i, XmlInteger xmlInteger);

    void xsetValArray(XmlInteger[] xmlIntegerArr);

    void xsetValidIdsArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetValidIdsArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetVisibleArray(int i, STTrueFalseBlank sTTrueFalseBlank);

    void xsetVisibleArray(STTrueFalseBlank[] sTTrueFalseBlankArr);

    void xsetWidthMinArray(int i, XmlInteger xmlInteger);

    void xsetWidthMinArray(XmlInteger[] xmlIntegerArr);

    static {
        DocumentFactory<CTClientData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctclientdata433btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
