package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;

public class CTDialogsheetImpl extends XmlComplexContentImpl implements CTDialogsheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetFormatPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "printOptions"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "oleObjects"), new QName(XSSFRelation.NS_SPREADSHEETML, "controls"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDialogsheetImpl(SchemaType schemaType) {
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

    public CTSheetViews getSheetViews() {
        CTSheetViews cTSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetViews = (CTSheetViews) get_store().find_element_user(PROPERTY_QNAME[1], 0);
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
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setSheetViews(CTSheetViews cTSheetViews) {
        generatedSetterHelperImpl(cTSheetViews, PROPERTY_QNAME[1], 0, 1);
    }

    public CTSheetViews addNewSheetViews() {
        CTSheetViews cTSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetViews = (CTSheetViews) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSheetViews;
    }

    public void unsetSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTSheetFormatPr getSheetFormatPr() {
        CTSheetFormatPr cTSheetFormatPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetFormatPr = (CTSheetFormatPr) get_store().find_element_user(PROPERTY_QNAME[2], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSheetFormatPr(CTSheetFormatPr cTSheetFormatPr) {
        generatedSetterHelperImpl(cTSheetFormatPr, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSheetFormatPr addNewSheetFormatPr() {
        CTSheetFormatPr cTSheetFormatPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetFormatPr = (CTSheetFormatPr) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTSheetFormatPr;
    }

    public void unsetSheetFormatPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTSheetProtection getSheetProtection() {
        CTSheetProtection cTSheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetProtection = (CTSheetProtection) get_store().find_element_user(PROPERTY_QNAME[3], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSheetProtection(CTSheetProtection cTSheetProtection) {
        generatedSetterHelperImpl(cTSheetProtection, PROPERTY_QNAME[3], 0, 1);
    }

    public CTSheetProtection addNewSheetProtection() {
        CTSheetProtection cTSheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetProtection = (CTSheetProtection) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTSheetProtection;
    }

    public void unsetSheetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTCustomSheetViews getCustomSheetViews() {
        CTCustomSheetViews cTCustomSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetViews = (CTCustomSheetViews) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setCustomSheetViews(CTCustomSheetViews cTCustomSheetViews) {
        generatedSetterHelperImpl(cTCustomSheetViews, PROPERTY_QNAME[4], 0, 1);
    }

    public CTCustomSheetViews addNewCustomSheetViews() {
        CTCustomSheetViews cTCustomSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetViews = (CTCustomSheetViews) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTCustomSheetViews;
    }

    public void unsetCustomSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPrintOptions getPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            cTPrintOptions = (CTPrintOptions) get_store().find_element_user(PROPERTY_QNAME[5], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPrintOptions(CTPrintOptions cTPrintOptions) {
        generatedSetterHelperImpl(cTPrintOptions, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPrintOptions addNewPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            cTPrintOptions = (CTPrintOptions) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPrintOptions;
    }

    public void unsetPrintOptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[6], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setPageMargins(CTPageMargins cTPageMargins) {
        generatedSetterHelperImpl(cTPageMargins, PROPERTY_QNAME[6], 0, 1);
    }

    public CTPageMargins addNewPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTPageMargins;
    }

    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSetup = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[7], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setPageSetup(CTPageSetup cTPageSetup) {
        generatedSetterHelperImpl(cTPageSetup, PROPERTY_QNAME[7], 0, 1);
    }

    public CTPageSetup addNewPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSetup = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPageSetup;
    }

    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[8], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        generatedSetterHelperImpl(cTHeaderFooter, PROPERTY_QNAME[8], 0, 1);
    }

    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTHeaderFooter;
    }

    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[9], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setDrawing(CTDrawing cTDrawing) {
        generatedSetterHelperImpl(cTDrawing, PROPERTY_QNAME[9], 0, 1);
    }

    public CTDrawing addNewDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTDrawing;
    }

    public void unsetDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTLegacyDrawing getLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[10], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing) {
        generatedSetterHelperImpl(cTLegacyDrawing, PROPERTY_QNAME[10], 0, 1);
    }

    public CTLegacyDrawing addNewLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTLegacyDrawing;
    }

    public void unsetLegacyDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTLegacyDrawing getLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[11], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing) {
        generatedSetterHelperImpl(cTLegacyDrawing, PROPERTY_QNAME[11], 0, 1);
    }

    public CTLegacyDrawing addNewLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTLegacyDrawing;
    }

    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTDrawingHF getDrawingHF() {
        CTDrawingHF find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[12], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setDrawingHF(CTDrawingHF cTDrawingHF) {
        generatedSetterHelperImpl(cTDrawingHF, PROPERTY_QNAME[12], 0, 1);
    }

    public CTDrawingHF addNewDrawingHF() {
        CTDrawingHF add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return add_element_user;
    }

    public void unsetDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTOleObjects getOleObjects() {
        CTOleObjects cTOleObjects;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObjects = (CTOleObjects) get_store().find_element_user(PROPERTY_QNAME[13], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setOleObjects(CTOleObjects cTOleObjects) {
        generatedSetterHelperImpl(cTOleObjects, PROPERTY_QNAME[13], 0, 1);
    }

    public CTOleObjects addNewOleObjects() {
        CTOleObjects cTOleObjects;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObjects = (CTOleObjects) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTOleObjects;
    }

    public void unsetOleObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTControls getControls() {
        CTControls cTControls;
        synchronized (monitor()) {
            check_orphaned();
            cTControls = (CTControls) get_store().find_element_user(PROPERTY_QNAME[14], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setControls(CTControls cTControls) {
        generatedSetterHelperImpl(cTControls, PROPERTY_QNAME[14], 0, 1);
    }

    public CTControls addNewControls() {
        CTControls cTControls;
        synchronized (monitor()) {
            check_orphaned();
            cTControls = (CTControls) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTControls;
    }

    public void unsetControls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[15], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[15], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }
}
