package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTWorksheet extends XmlObject {
    public static final DocumentFactory<CTWorksheet> Factory;
    public static final SchemaType type;

    CTAutoFilter addNewAutoFilter();

    CTCellWatches addNewCellWatches();

    CTPageBreak addNewColBreaks();

    CTCols addNewCols();

    CTConditionalFormatting addNewConditionalFormatting();

    CTControls addNewControls();

    CTCustomProperties addNewCustomProperties();

    CTCustomSheetViews addNewCustomSheetViews();

    CTDataConsolidate addNewDataConsolidate();

    CTDataValidations addNewDataValidations();

    CTSheetDimension addNewDimension();

    CTDrawing addNewDrawing();

    CTDrawingHF addNewDrawingHF();

    CTExtensionList addNewExtLst();

    CTHeaderFooter addNewHeaderFooter();

    CTHyperlinks addNewHyperlinks();

    CTIgnoredErrors addNewIgnoredErrors();

    CTLegacyDrawing addNewLegacyDrawing();

    CTLegacyDrawing addNewLegacyDrawingHF();

    CTMergeCells addNewMergeCells();

    CTOleObjects addNewOleObjects();

    CTPageMargins addNewPageMargins();

    CTPageSetup addNewPageSetup();

    CTPhoneticPr addNewPhoneticPr();

    CTSheetBackgroundPicture addNewPicture();

    CTPrintOptions addNewPrintOptions();

    CTProtectedRanges addNewProtectedRanges();

    CTPageBreak addNewRowBreaks();

    CTScenarios addNewScenarios();

    CTSheetCalcPr addNewSheetCalcPr();

    CTSheetData addNewSheetData();

    CTSheetFormatPr addNewSheetFormatPr();

    CTSheetPr addNewSheetPr();

    CTSheetProtection addNewSheetProtection();

    CTSheetViews addNewSheetViews();

    CTSmartTags addNewSmartTags();

    CTSortState addNewSortState();

    CTTableParts addNewTableParts();

    CTWebPublishItems addNewWebPublishItems();

    CTAutoFilter getAutoFilter();

    CTCellWatches getCellWatches();

    CTPageBreak getColBreaks();

    CTCols getColsArray(int i);

    CTCols[] getColsArray();

    List<CTCols> getColsList();

    CTConditionalFormatting getConditionalFormattingArray(int i);

    CTConditionalFormatting[] getConditionalFormattingArray();

    List<CTConditionalFormatting> getConditionalFormattingList();

    CTControls getControls();

    CTCustomProperties getCustomProperties();

    CTCustomSheetViews getCustomSheetViews();

    CTDataConsolidate getDataConsolidate();

    CTDataValidations getDataValidations();

    CTSheetDimension getDimension();

    CTDrawing getDrawing();

    CTDrawingHF getDrawingHF();

    CTExtensionList getExtLst();

    CTHeaderFooter getHeaderFooter();

    CTHyperlinks getHyperlinks();

    CTIgnoredErrors getIgnoredErrors();

    CTLegacyDrawing getLegacyDrawing();

    CTLegacyDrawing getLegacyDrawingHF();

    CTMergeCells getMergeCells();

    CTOleObjects getOleObjects();

    CTPageMargins getPageMargins();

    CTPageSetup getPageSetup();

    CTPhoneticPr getPhoneticPr();

    CTSheetBackgroundPicture getPicture();

    CTPrintOptions getPrintOptions();

    CTProtectedRanges getProtectedRanges();

    CTPageBreak getRowBreaks();

    CTScenarios getScenarios();

    CTSheetCalcPr getSheetCalcPr();

    CTSheetData getSheetData();

    CTSheetFormatPr getSheetFormatPr();

    CTSheetPr getSheetPr();

    CTSheetProtection getSheetProtection();

    CTSheetViews getSheetViews();

    CTSmartTags getSmartTags();

    CTSortState getSortState();

    CTTableParts getTableParts();

    CTWebPublishItems getWebPublishItems();

    CTCols insertNewCols(int i);

    CTConditionalFormatting insertNewConditionalFormatting(int i);

    boolean isSetAutoFilter();

    boolean isSetCellWatches();

    boolean isSetColBreaks();

    boolean isSetControls();

    boolean isSetCustomProperties();

    boolean isSetCustomSheetViews();

    boolean isSetDataConsolidate();

    boolean isSetDataValidations();

    boolean isSetDimension();

    boolean isSetDrawing();

    boolean isSetDrawingHF();

    boolean isSetExtLst();

    boolean isSetHeaderFooter();

    boolean isSetHyperlinks();

    boolean isSetIgnoredErrors();

    boolean isSetLegacyDrawing();

    boolean isSetLegacyDrawingHF();

    boolean isSetMergeCells();

    boolean isSetOleObjects();

    boolean isSetPageMargins();

    boolean isSetPageSetup();

    boolean isSetPhoneticPr();

    boolean isSetPicture();

    boolean isSetPrintOptions();

    boolean isSetProtectedRanges();

    boolean isSetRowBreaks();

    boolean isSetScenarios();

    boolean isSetSheetCalcPr();

    boolean isSetSheetFormatPr();

    boolean isSetSheetPr();

    boolean isSetSheetProtection();

    boolean isSetSheetViews();

    boolean isSetSmartTags();

    boolean isSetSortState();

    boolean isSetTableParts();

    boolean isSetWebPublishItems();

    void removeCols(int i);

    void removeConditionalFormatting(int i);

    void setAutoFilter(CTAutoFilter cTAutoFilter);

    void setCellWatches(CTCellWatches cTCellWatches);

    void setColBreaks(CTPageBreak cTPageBreak);

    void setColsArray(int i, CTCols cTCols);

    void setColsArray(CTCols[] cTColsArr);

    void setConditionalFormattingArray(int i, CTConditionalFormatting cTConditionalFormatting);

    void setConditionalFormattingArray(CTConditionalFormatting[] cTConditionalFormattingArr);

    void setControls(CTControls cTControls);

    void setCustomProperties(CTCustomProperties cTCustomProperties);

    void setCustomSheetViews(CTCustomSheetViews cTCustomSheetViews);

    void setDataConsolidate(CTDataConsolidate cTDataConsolidate);

    void setDataValidations(CTDataValidations cTDataValidations);

    void setDimension(CTSheetDimension cTSheetDimension);

    void setDrawing(CTDrawing cTDrawing);

    void setDrawingHF(CTDrawingHF cTDrawingHF);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHeaderFooter(CTHeaderFooter cTHeaderFooter);

    void setHyperlinks(CTHyperlinks cTHyperlinks);

    void setIgnoredErrors(CTIgnoredErrors cTIgnoredErrors);

    void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing);

    void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing);

    void setMergeCells(CTMergeCells cTMergeCells);

    void setOleObjects(CTOleObjects cTOleObjects);

    void setPageMargins(CTPageMargins cTPageMargins);

    void setPageSetup(CTPageSetup cTPageSetup);

    void setPhoneticPr(CTPhoneticPr cTPhoneticPr);

    void setPicture(CTSheetBackgroundPicture cTSheetBackgroundPicture);

    void setPrintOptions(CTPrintOptions cTPrintOptions);

    void setProtectedRanges(CTProtectedRanges cTProtectedRanges);

    void setRowBreaks(CTPageBreak cTPageBreak);

    void setScenarios(CTScenarios cTScenarios);

    void setSheetCalcPr(CTSheetCalcPr cTSheetCalcPr);

    void setSheetData(CTSheetData cTSheetData);

    void setSheetFormatPr(CTSheetFormatPr cTSheetFormatPr);

    void setSheetPr(CTSheetPr cTSheetPr);

    void setSheetProtection(CTSheetProtection cTSheetProtection);

    void setSheetViews(CTSheetViews cTSheetViews);

    void setSmartTags(CTSmartTags cTSmartTags);

    void setSortState(CTSortState cTSortState);

    void setTableParts(CTTableParts cTTableParts);

    void setWebPublishItems(CTWebPublishItems cTWebPublishItems);

    int sizeOfColsArray();

    int sizeOfConditionalFormattingArray();

    void unsetAutoFilter();

    void unsetCellWatches();

    void unsetColBreaks();

    void unsetControls();

    void unsetCustomProperties();

    void unsetCustomSheetViews();

    void unsetDataConsolidate();

    void unsetDataValidations();

    void unsetDimension();

    void unsetDrawing();

    void unsetDrawingHF();

    void unsetExtLst();

    void unsetHeaderFooter();

    void unsetHyperlinks();

    void unsetIgnoredErrors();

    void unsetLegacyDrawing();

    void unsetLegacyDrawingHF();

    void unsetMergeCells();

    void unsetOleObjects();

    void unsetPageMargins();

    void unsetPageSetup();

    void unsetPhoneticPr();

    void unsetPicture();

    void unsetPrintOptions();

    void unsetProtectedRanges();

    void unsetRowBreaks();

    void unsetScenarios();

    void unsetSheetCalcPr();

    void unsetSheetFormatPr();

    void unsetSheetPr();

    void unsetSheetProtection();

    void unsetSheetViews();

    void unsetSmartTags();

    void unsetSortState();

    void unsetTableParts();

    void unsetWebPublishItems();

    static {
        DocumentFactory<CTWorksheet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctworksheet530dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
