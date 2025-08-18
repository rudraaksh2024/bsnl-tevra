package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataConsolidate;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidations;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlinks;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class CTWorksheetImpl extends XmlComplexContentImpl implements CTWorksheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetPr"), new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.Custom.S_DIMENSION), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetFormatPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "cols"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetData"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetCalcPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "protectedRanges"), new QName(XSSFRelation.NS_SPREADSHEETML, "scenarios"), new QName(XSSFRelation.NS_SPREADSHEETML, "autoFilter"), new QName(XSSFRelation.NS_SPREADSHEETML, "sortState"), new QName(XSSFRelation.NS_SPREADSHEETML, "dataConsolidate"), new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "mergeCells"), new QName(XSSFRelation.NS_SPREADSHEETML, "phoneticPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "conditionalFormatting"), new QName(XSSFRelation.NS_SPREADSHEETML, "dataValidations"), new QName(XSSFRelation.NS_SPREADSHEETML, "hyperlinks"), new QName(XSSFRelation.NS_SPREADSHEETML, "printOptions"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "rowBreaks"), new QName(XSSFRelation.NS_SPREADSHEETML, "colBreaks"), new QName(XSSFRelation.NS_SPREADSHEETML, "customProperties"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellWatches"), new QName(XSSFRelation.NS_SPREADSHEETML, "ignoredErrors"), new QName(XSSFRelation.NS_SPREADSHEETML, "smartTags"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "picture"), new QName(XSSFRelation.NS_SPREADSHEETML, "oleObjects"), new QName(XSSFRelation.NS_SPREADSHEETML, "controls"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishItems"), new QName(XSSFRelation.NS_SPREADSHEETML, "tableParts"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTWorksheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSheetPr getSheetPr() {
        CTSheetPr cTSheetPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetPr = (CTSheetPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSheetPr == null) {
                cTSheetPr = null;
            }
        }
        return cTSheetPr;
    }

    public boolean isSetSheetPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setSheetPr(CTSheetPr cTSheetPr) {
        generatedSetterHelperImpl(cTSheetPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSheetPr addNewSheetPr() {
        CTSheetPr cTSheetPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetPr = (CTSheetPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSheetPr;
    }

    public void unsetSheetPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTSheetDimension getDimension() {
        CTSheetDimension cTSheetDimension;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetDimension = (CTSheetDimension) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTSheetDimension == null) {
                cTSheetDimension = null;
            }
        }
        return cTSheetDimension;
    }

    public boolean isSetDimension() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setDimension(CTSheetDimension cTSheetDimension) {
        generatedSetterHelperImpl(cTSheetDimension, PROPERTY_QNAME[1], 0, 1);
    }

    public CTSheetDimension addNewDimension() {
        CTSheetDimension cTSheetDimension;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetDimension = (CTSheetDimension) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSheetDimension;
    }

    public void unsetDimension() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTSheetViews getSheetViews() {
        CTSheetViews cTSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetViews = (CTSheetViews) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTSheetViews == null) {
                cTSheetViews = null;
            }
        }
        return cTSheetViews;
    }

    public boolean isSetSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSheetViews(CTSheetViews cTSheetViews) {
        generatedSetterHelperImpl(cTSheetViews, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSheetViews addNewSheetViews() {
        CTSheetViews cTSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetViews = (CTSheetViews) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTSheetViews;
    }

    public void unsetSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTSheetFormatPr getSheetFormatPr() {
        CTSheetFormatPr cTSheetFormatPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetFormatPr = (CTSheetFormatPr) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTSheetFormatPr == null) {
                cTSheetFormatPr = null;
            }
        }
        return cTSheetFormatPr;
    }

    public boolean isSetSheetFormatPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSheetFormatPr(CTSheetFormatPr cTSheetFormatPr) {
        generatedSetterHelperImpl(cTSheetFormatPr, PROPERTY_QNAME[3], 0, 1);
    }

    public CTSheetFormatPr addNewSheetFormatPr() {
        CTSheetFormatPr cTSheetFormatPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetFormatPr = (CTSheetFormatPr) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTSheetFormatPr;
    }

    public void unsetSheetFormatPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public List<CTCols> getColsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTWorksheetImpl$$ExternalSyntheticLambda5(this), new CTWorksheetImpl$$ExternalSyntheticLambda6(this), new CTWorksheetImpl$$ExternalSyntheticLambda7(this), new CTWorksheetImpl$$ExternalSyntheticLambda8(this), new CTWorksheetImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTCols[] getColsArray() {
        return (CTCols[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTCols[0]);
    }

    public CTCols getColsArray(int i) {
        CTCols cTCols;
        synchronized (monitor()) {
            check_orphaned();
            cTCols = (CTCols) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTCols == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCols;
    }

    public int sizeOfColsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setColsArray(CTCols[] cTColsArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColsArr, PROPERTY_QNAME[4]);
    }

    public void setColsArray(int i, CTCols cTCols) {
        generatedSetterHelperImpl(cTCols, PROPERTY_QNAME[4], i, 2);
    }

    public CTCols insertNewCols(int i) {
        CTCols cTCols;
        synchronized (monitor()) {
            check_orphaned();
            cTCols = (CTCols) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTCols;
    }

    public CTCols addNewCols() {
        CTCols cTCols;
        synchronized (monitor()) {
            check_orphaned();
            cTCols = (CTCols) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTCols;
    }

    public void removeCols(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public CTSheetData getSheetData() {
        CTSheetData cTSheetData;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetData = (CTSheetData) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTSheetData == null) {
                cTSheetData = null;
            }
        }
        return cTSheetData;
    }

    public void setSheetData(CTSheetData cTSheetData) {
        generatedSetterHelperImpl(cTSheetData, PROPERTY_QNAME[5], 0, 1);
    }

    public CTSheetData addNewSheetData() {
        CTSheetData cTSheetData;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetData = (CTSheetData) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTSheetData;
    }

    public CTSheetCalcPr getSheetCalcPr() {
        CTSheetCalcPr cTSheetCalcPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetCalcPr = (CTSheetCalcPr) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTSheetCalcPr == null) {
                cTSheetCalcPr = null;
            }
        }
        return cTSheetCalcPr;
    }

    public boolean isSetSheetCalcPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setSheetCalcPr(CTSheetCalcPr cTSheetCalcPr) {
        generatedSetterHelperImpl(cTSheetCalcPr, PROPERTY_QNAME[6], 0, 1);
    }

    public CTSheetCalcPr addNewSheetCalcPr() {
        CTSheetCalcPr cTSheetCalcPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetCalcPr = (CTSheetCalcPr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTSheetCalcPr;
    }

    public void unsetSheetCalcPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTSheetProtection getSheetProtection() {
        CTSheetProtection cTSheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetProtection = (CTSheetProtection) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTSheetProtection == null) {
                cTSheetProtection = null;
            }
        }
        return cTSheetProtection;
    }

    public boolean isSetSheetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setSheetProtection(CTSheetProtection cTSheetProtection) {
        generatedSetterHelperImpl(cTSheetProtection, PROPERTY_QNAME[7], 0, 1);
    }

    public CTSheetProtection addNewSheetProtection() {
        CTSheetProtection cTSheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetProtection = (CTSheetProtection) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTSheetProtection;
    }

    public void unsetSheetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTProtectedRanges getProtectedRanges() {
        CTProtectedRanges cTProtectedRanges;
        synchronized (monitor()) {
            check_orphaned();
            cTProtectedRanges = (CTProtectedRanges) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTProtectedRanges == null) {
                cTProtectedRanges = null;
            }
        }
        return cTProtectedRanges;
    }

    public boolean isSetProtectedRanges() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setProtectedRanges(CTProtectedRanges cTProtectedRanges) {
        generatedSetterHelperImpl(cTProtectedRanges, PROPERTY_QNAME[8], 0, 1);
    }

    public CTProtectedRanges addNewProtectedRanges() {
        CTProtectedRanges cTProtectedRanges;
        synchronized (monitor()) {
            check_orphaned();
            cTProtectedRanges = (CTProtectedRanges) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTProtectedRanges;
    }

    public void unsetProtectedRanges() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTScenarios getScenarios() {
        CTScenarios find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetScenarios() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setScenarios(CTScenarios cTScenarios) {
        generatedSetterHelperImpl(cTScenarios, PROPERTY_QNAME[9], 0, 1);
    }

    public CTScenarios addNewScenarios() {
        CTScenarios add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return add_element_user;
    }

    public void unsetScenarios() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTAutoFilter getAutoFilter() {
        CTAutoFilter cTAutoFilter;
        synchronized (monitor()) {
            check_orphaned();
            cTAutoFilter = (CTAutoFilter) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTAutoFilter == null) {
                cTAutoFilter = null;
            }
        }
        return cTAutoFilter;
    }

    public boolean isSetAutoFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setAutoFilter(CTAutoFilter cTAutoFilter) {
        generatedSetterHelperImpl(cTAutoFilter, PROPERTY_QNAME[10], 0, 1);
    }

    public CTAutoFilter addNewAutoFilter() {
        CTAutoFilter cTAutoFilter;
        synchronized (monitor()) {
            check_orphaned();
            cTAutoFilter = (CTAutoFilter) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTAutoFilter;
    }

    public void unsetAutoFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTSortState getSortState() {
        CTSortState cTSortState;
        synchronized (monitor()) {
            check_orphaned();
            cTSortState = (CTSortState) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTSortState == null) {
                cTSortState = null;
            }
        }
        return cTSortState;
    }

    public boolean isSetSortState() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setSortState(CTSortState cTSortState) {
        generatedSetterHelperImpl(cTSortState, PROPERTY_QNAME[11], 0, 1);
    }

    public CTSortState addNewSortState() {
        CTSortState cTSortState;
        synchronized (monitor()) {
            check_orphaned();
            cTSortState = (CTSortState) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTSortState;
    }

    public void unsetSortState() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTDataConsolidate getDataConsolidate() {
        CTDataConsolidate cTDataConsolidate;
        synchronized (monitor()) {
            check_orphaned();
            cTDataConsolidate = (CTDataConsolidate) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTDataConsolidate == null) {
                cTDataConsolidate = null;
            }
        }
        return cTDataConsolidate;
    }

    public boolean isSetDataConsolidate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setDataConsolidate(CTDataConsolidate cTDataConsolidate) {
        generatedSetterHelperImpl(cTDataConsolidate, PROPERTY_QNAME[12], 0, 1);
    }

    public CTDataConsolidate addNewDataConsolidate() {
        CTDataConsolidate cTDataConsolidate;
        synchronized (monitor()) {
            check_orphaned();
            cTDataConsolidate = (CTDataConsolidate) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTDataConsolidate;
    }

    public void unsetDataConsolidate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTCustomSheetViews getCustomSheetViews() {
        CTCustomSheetViews cTCustomSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetViews = (CTCustomSheetViews) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTCustomSheetViews == null) {
                cTCustomSheetViews = null;
            }
        }
        return cTCustomSheetViews;
    }

    public boolean isSetCustomSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setCustomSheetViews(CTCustomSheetViews cTCustomSheetViews) {
        generatedSetterHelperImpl(cTCustomSheetViews, PROPERTY_QNAME[13], 0, 1);
    }

    public CTCustomSheetViews addNewCustomSheetViews() {
        CTCustomSheetViews cTCustomSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetViews = (CTCustomSheetViews) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTCustomSheetViews;
    }

    public void unsetCustomSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTMergeCells getMergeCells() {
        CTMergeCells cTMergeCells;
        synchronized (monitor()) {
            check_orphaned();
            cTMergeCells = (CTMergeCells) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTMergeCells == null) {
                cTMergeCells = null;
            }
        }
        return cTMergeCells;
    }

    public boolean isSetMergeCells() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setMergeCells(CTMergeCells cTMergeCells) {
        generatedSetterHelperImpl(cTMergeCells, PROPERTY_QNAME[14], 0, 1);
    }

    public CTMergeCells addNewMergeCells() {
        CTMergeCells cTMergeCells;
        synchronized (monitor()) {
            check_orphaned();
            cTMergeCells = (CTMergeCells) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTMergeCells;
    }

    public void unsetMergeCells() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTPhoneticPr getPhoneticPr() {
        CTPhoneticPr cTPhoneticPr;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticPr = (CTPhoneticPr) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTPhoneticPr == null) {
                cTPhoneticPr = null;
            }
        }
        return cTPhoneticPr;
    }

    public boolean isSetPhoneticPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setPhoneticPr(CTPhoneticPr cTPhoneticPr) {
        generatedSetterHelperImpl(cTPhoneticPr, PROPERTY_QNAME[15], 0, 1);
    }

    public CTPhoneticPr addNewPhoneticPr() {
        CTPhoneticPr cTPhoneticPr;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticPr = (CTPhoneticPr) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTPhoneticPr;
    }

    public void unsetPhoneticPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public List<CTConditionalFormatting> getConditionalFormattingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTWorksheetImpl$$ExternalSyntheticLambda0(this), new CTWorksheetImpl$$ExternalSyntheticLambda1(this), new CTWorksheetImpl$$ExternalSyntheticLambda2(this), new CTWorksheetImpl$$ExternalSyntheticLambda3(this), new CTWorksheetImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTConditionalFormatting[] getConditionalFormattingArray() {
        return (CTConditionalFormatting[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTConditionalFormatting[0]);
    }

    public CTConditionalFormatting getConditionalFormattingArray(int i) {
        CTConditionalFormatting cTConditionalFormatting;
        synchronized (monitor()) {
            check_orphaned();
            cTConditionalFormatting = (CTConditionalFormatting) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (cTConditionalFormatting == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTConditionalFormatting;
    }

    public int sizeOfConditionalFormattingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setConditionalFormattingArray(CTConditionalFormatting[] cTConditionalFormattingArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTConditionalFormattingArr, PROPERTY_QNAME[16]);
    }

    public void setConditionalFormattingArray(int i, CTConditionalFormatting cTConditionalFormatting) {
        generatedSetterHelperImpl(cTConditionalFormatting, PROPERTY_QNAME[16], i, 2);
    }

    public CTConditionalFormatting insertNewConditionalFormatting(int i) {
        CTConditionalFormatting cTConditionalFormatting;
        synchronized (monitor()) {
            check_orphaned();
            cTConditionalFormatting = (CTConditionalFormatting) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTConditionalFormatting;
    }

    public CTConditionalFormatting addNewConditionalFormatting() {
        CTConditionalFormatting cTConditionalFormatting;
        synchronized (monitor()) {
            check_orphaned();
            cTConditionalFormatting = (CTConditionalFormatting) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTConditionalFormatting;
    }

    public void removeConditionalFormatting(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public CTDataValidations getDataValidations() {
        CTDataValidations cTDataValidations;
        synchronized (monitor()) {
            check_orphaned();
            cTDataValidations = (CTDataValidations) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (cTDataValidations == null) {
                cTDataValidations = null;
            }
        }
        return cTDataValidations;
    }

    public boolean isSetDataValidations() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setDataValidations(CTDataValidations cTDataValidations) {
        generatedSetterHelperImpl(cTDataValidations, PROPERTY_QNAME[17], 0, 1);
    }

    public CTDataValidations addNewDataValidations() {
        CTDataValidations cTDataValidations;
        synchronized (monitor()) {
            check_orphaned();
            cTDataValidations = (CTDataValidations) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTDataValidations;
    }

    public void unsetDataValidations() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public CTHyperlinks getHyperlinks() {
        CTHyperlinks cTHyperlinks;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlinks = (CTHyperlinks) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (cTHyperlinks == null) {
                cTHyperlinks = null;
            }
        }
        return cTHyperlinks;
    }

    public boolean isSetHyperlinks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    public void setHyperlinks(CTHyperlinks cTHyperlinks) {
        generatedSetterHelperImpl(cTHyperlinks, PROPERTY_QNAME[18], 0, 1);
    }

    public CTHyperlinks addNewHyperlinks() {
        CTHyperlinks cTHyperlinks;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlinks = (CTHyperlinks) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTHyperlinks;
    }

    public void unsetHyperlinks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    public CTPrintOptions getPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            cTPrintOptions = (CTPrintOptions) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (cTPrintOptions == null) {
                cTPrintOptions = null;
            }
        }
        return cTPrintOptions;
    }

    public boolean isSetPrintOptions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    public void setPrintOptions(CTPrintOptions cTPrintOptions) {
        generatedSetterHelperImpl(cTPrintOptions, PROPERTY_QNAME[19], 0, 1);
    }

    public CTPrintOptions addNewPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            cTPrintOptions = (CTPrintOptions) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTPrintOptions;
    }

    public void unsetPrintOptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (cTPageMargins == null) {
                cTPageMargins = null;
            }
        }
        return cTPageMargins;
    }

    public boolean isSetPageMargins() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    public void setPageMargins(CTPageMargins cTPageMargins) {
        generatedSetterHelperImpl(cTPageMargins, PROPERTY_QNAME[20], 0, 1);
    }

    public CTPageMargins addNewPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTPageMargins;
    }

    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSetup = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (cTPageSetup == null) {
                cTPageSetup = null;
            }
        }
        return cTPageSetup;
    }

    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    public void setPageSetup(CTPageSetup cTPageSetup) {
        generatedSetterHelperImpl(cTPageSetup, PROPERTY_QNAME[21], 0, 1);
    }

    public CTPageSetup addNewPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSetup = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTPageSetup;
    }

    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (cTHeaderFooter == null) {
                cTHeaderFooter = null;
            }
        }
        return cTHeaderFooter;
    }

    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        generatedSetterHelperImpl(cTHeaderFooter, PROPERTY_QNAME[22], 0, 1);
    }

    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTHeaderFooter;
    }

    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    public CTPageBreak getRowBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTPageBreak = (CTPageBreak) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            if (cTPageBreak == null) {
                cTPageBreak = null;
            }
        }
        return cTPageBreak;
    }

    public boolean isSetRowBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    public void setRowBreaks(CTPageBreak cTPageBreak) {
        generatedSetterHelperImpl(cTPageBreak, PROPERTY_QNAME[23], 0, 1);
    }

    public CTPageBreak addNewRowBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTPageBreak = (CTPageBreak) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTPageBreak;
    }

    public void unsetRowBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    public CTPageBreak getColBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTPageBreak = (CTPageBreak) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (cTPageBreak == null) {
                cTPageBreak = null;
            }
        }
        return cTPageBreak;
    }

    public boolean isSetColBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    public void setColBreaks(CTPageBreak cTPageBreak) {
        generatedSetterHelperImpl(cTPageBreak, PROPERTY_QNAME[24], 0, 1);
    }

    public CTPageBreak addNewColBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTPageBreak = (CTPageBreak) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTPageBreak;
    }

    public void unsetColBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    public CTCustomProperties getCustomProperties() {
        CTCustomProperties cTCustomProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomProperties = (CTCustomProperties) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            if (cTCustomProperties == null) {
                cTCustomProperties = null;
            }
        }
        return cTCustomProperties;
    }

    public boolean isSetCustomProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    public void setCustomProperties(CTCustomProperties cTCustomProperties) {
        generatedSetterHelperImpl(cTCustomProperties, PROPERTY_QNAME[25], 0, 1);
    }

    public CTCustomProperties addNewCustomProperties() {
        CTCustomProperties cTCustomProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomProperties = (CTCustomProperties) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTCustomProperties;
    }

    public void unsetCustomProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    public CTCellWatches getCellWatches() {
        CTCellWatches cTCellWatches;
        synchronized (monitor()) {
            check_orphaned();
            cTCellWatches = (CTCellWatches) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (cTCellWatches == null) {
                cTCellWatches = null;
            }
        }
        return cTCellWatches;
    }

    public boolean isSetCellWatches() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    public void setCellWatches(CTCellWatches cTCellWatches) {
        generatedSetterHelperImpl(cTCellWatches, PROPERTY_QNAME[26], 0, 1);
    }

    public CTCellWatches addNewCellWatches() {
        CTCellWatches cTCellWatches;
        synchronized (monitor()) {
            check_orphaned();
            cTCellWatches = (CTCellWatches) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return cTCellWatches;
    }

    public void unsetCellWatches() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }

    public CTIgnoredErrors getIgnoredErrors() {
        CTIgnoredErrors cTIgnoredErrors;
        synchronized (monitor()) {
            check_orphaned();
            cTIgnoredErrors = (CTIgnoredErrors) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            if (cTIgnoredErrors == null) {
                cTIgnoredErrors = null;
            }
        }
        return cTIgnoredErrors;
    }

    public boolean isSetIgnoredErrors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[27]) != 0;
        }
        return z;
    }

    public void setIgnoredErrors(CTIgnoredErrors cTIgnoredErrors) {
        generatedSetterHelperImpl(cTIgnoredErrors, PROPERTY_QNAME[27], 0, 1);
    }

    public CTIgnoredErrors addNewIgnoredErrors() {
        CTIgnoredErrors cTIgnoredErrors;
        synchronized (monitor()) {
            check_orphaned();
            cTIgnoredErrors = (CTIgnoredErrors) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return cTIgnoredErrors;
    }

    public void unsetIgnoredErrors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], 0);
        }
    }

    public CTSmartTags getSmartTags() {
        CTSmartTags find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[28], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSmartTags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[28]) != 0;
        }
        return z;
    }

    public void setSmartTags(CTSmartTags cTSmartTags) {
        generatedSetterHelperImpl(cTSmartTags, PROPERTY_QNAME[28], 0, 1);
    }

    public CTSmartTags addNewSmartTags() {
        CTSmartTags add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return add_element_user;
    }

    public void unsetSmartTags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], 0);
        }
    }

    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[29], 0);
            if (cTDrawing == null) {
                cTDrawing = null;
            }
        }
        return cTDrawing;
    }

    public boolean isSetDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[29]) != 0;
        }
        return z;
    }

    public void setDrawing(CTDrawing cTDrawing) {
        generatedSetterHelperImpl(cTDrawing, PROPERTY_QNAME[29], 0, 1);
    }

    public CTDrawing addNewDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return cTDrawing;
    }

    public void unsetDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], 0);
        }
    }

    public CTLegacyDrawing getLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            if (cTLegacyDrawing == null) {
                cTLegacyDrawing = null;
            }
        }
        return cTLegacyDrawing;
    }

    public boolean isSetLegacyDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[30]) != 0;
        }
        return z;
    }

    public void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing) {
        generatedSetterHelperImpl(cTLegacyDrawing, PROPERTY_QNAME[30], 0, 1);
    }

    public CTLegacyDrawing addNewLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return cTLegacyDrawing;
    }

    public void unsetLegacyDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], 0);
        }
    }

    public CTLegacyDrawing getLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[31], 0);
            if (cTLegacyDrawing == null) {
                cTLegacyDrawing = null;
            }
        }
        return cTLegacyDrawing;
    }

    public boolean isSetLegacyDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[31]) != 0;
        }
        return z;
    }

    public void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing) {
        generatedSetterHelperImpl(cTLegacyDrawing, PROPERTY_QNAME[31], 0, 1);
    }

    public CTLegacyDrawing addNewLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return cTLegacyDrawing;
    }

    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], 0);
        }
    }

    public CTDrawingHF getDrawingHF() {
        CTDrawingHF find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[32], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[32]) != 0;
        }
        return z;
    }

    public void setDrawingHF(CTDrawingHF cTDrawingHF) {
        generatedSetterHelperImpl(cTDrawingHF, PROPERTY_QNAME[32], 0, 1);
    }

    public CTDrawingHF addNewDrawingHF() {
        CTDrawingHF add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return add_element_user;
    }

    public void unsetDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], 0);
        }
    }

    public CTSheetBackgroundPicture getPicture() {
        CTSheetBackgroundPicture cTSheetBackgroundPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture) get_store().find_element_user(PROPERTY_QNAME[33], 0);
            if (cTSheetBackgroundPicture == null) {
                cTSheetBackgroundPicture = null;
            }
        }
        return cTSheetBackgroundPicture;
    }

    public boolean isSetPicture() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[33]) != 0;
        }
        return z;
    }

    public void setPicture(CTSheetBackgroundPicture cTSheetBackgroundPicture) {
        generatedSetterHelperImpl(cTSheetBackgroundPicture, PROPERTY_QNAME[33], 0, 1);
    }

    public CTSheetBackgroundPicture addNewPicture() {
        CTSheetBackgroundPicture cTSheetBackgroundPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return cTSheetBackgroundPicture;
    }

    public void unsetPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], 0);
        }
    }

    public CTOleObjects getOleObjects() {
        CTOleObjects cTOleObjects;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObjects = (CTOleObjects) get_store().find_element_user(PROPERTY_QNAME[34], 0);
            if (cTOleObjects == null) {
                cTOleObjects = null;
            }
        }
        return cTOleObjects;
    }

    public boolean isSetOleObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[34]) != 0;
        }
        return z;
    }

    public void setOleObjects(CTOleObjects cTOleObjects) {
        generatedSetterHelperImpl(cTOleObjects, PROPERTY_QNAME[34], 0, 1);
    }

    public CTOleObjects addNewOleObjects() {
        CTOleObjects cTOleObjects;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObjects = (CTOleObjects) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return cTOleObjects;
    }

    public void unsetOleObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], 0);
        }
    }

    public CTControls getControls() {
        CTControls cTControls;
        synchronized (monitor()) {
            check_orphaned();
            cTControls = (CTControls) get_store().find_element_user(PROPERTY_QNAME[35], 0);
            if (cTControls == null) {
                cTControls = null;
            }
        }
        return cTControls;
    }

    public boolean isSetControls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[35]) != 0;
        }
        return z;
    }

    public void setControls(CTControls cTControls) {
        generatedSetterHelperImpl(cTControls, PROPERTY_QNAME[35], 0, 1);
    }

    public CTControls addNewControls() {
        CTControls cTControls;
        synchronized (monitor()) {
            check_orphaned();
            cTControls = (CTControls) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return cTControls;
    }

    public void unsetControls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], 0);
        }
    }

    public CTWebPublishItems getWebPublishItems() {
        CTWebPublishItems find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[36], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetWebPublishItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[36]) != 0;
        }
        return z;
    }

    public void setWebPublishItems(CTWebPublishItems cTWebPublishItems) {
        generatedSetterHelperImpl(cTWebPublishItems, PROPERTY_QNAME[36], 0, 1);
    }

    public CTWebPublishItems addNewWebPublishItems() {
        CTWebPublishItems add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return add_element_user;
    }

    public void unsetWebPublishItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], 0);
        }
    }

    public CTTableParts getTableParts() {
        CTTableParts cTTableParts;
        synchronized (monitor()) {
            check_orphaned();
            cTTableParts = (CTTableParts) get_store().find_element_user(PROPERTY_QNAME[37], 0);
            if (cTTableParts == null) {
                cTTableParts = null;
            }
        }
        return cTTableParts;
    }

    public boolean isSetTableParts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[37]) != 0;
        }
        return z;
    }

    public void setTableParts(CTTableParts cTTableParts) {
        generatedSetterHelperImpl(cTTableParts, PROPERTY_QNAME[37], 0, 1);
    }

    public CTTableParts addNewTableParts() {
        CTTableParts cTTableParts;
        synchronized (monitor()) {
            check_orphaned();
            cTTableParts = (CTTableParts) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return cTTableParts;
    }

    public void unsetTableParts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[38], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[38]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[38], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], 0);
        }
    }
}
