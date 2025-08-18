package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;

public class CTChartsheetImpl extends XmlComplexContentImpl implements CTChartsheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "picture"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishItems"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTChartsheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTChartsheetPr getSheetPr() {
        CTChartsheetPr find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
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

    public void setSheetPr(CTChartsheetPr cTChartsheetPr) {
        generatedSetterHelperImpl(cTChartsheetPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTChartsheetPr addNewSheetPr() {
        CTChartsheetPr add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetSheetPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTChartsheetViews getSheetViews() {
        CTChartsheetViews find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public void setSheetViews(CTChartsheetViews cTChartsheetViews) {
        generatedSetterHelperImpl(cTChartsheetViews, PROPERTY_QNAME[1], 0, 1);
    }

    public CTChartsheetViews addNewSheetViews() {
        CTChartsheetViews add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public CTChartsheetProtection getSheetProtection() {
        CTChartsheetProtection find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSheetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSheetProtection(CTChartsheetProtection cTChartsheetProtection) {
        generatedSetterHelperImpl(cTChartsheetProtection, PROPERTY_QNAME[2], 0, 1);
    }

    public CTChartsheetProtection addNewSheetProtection() {
        CTChartsheetProtection add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetSheetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTCustomChartsheetViews getCustomSheetViews() {
        CTCustomChartsheetViews find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCustomSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setCustomSheetViews(CTCustomChartsheetViews cTCustomChartsheetViews) {
        generatedSetterHelperImpl(cTCustomChartsheetViews, PROPERTY_QNAME[3], 0, 1);
    }

    public CTCustomChartsheetViews addNewCustomSheetViews() {
        CTCustomChartsheetViews add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void unsetCustomSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setPageMargins(CTPageMargins cTPageMargins) {
        generatedSetterHelperImpl(cTPageMargins, PROPERTY_QNAME[4], 0, 1);
    }

    public CTPageMargins addNewPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTPageMargins;
    }

    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTCsPageSetup getPageSetup() {
        CTCsPageSetup find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPageSetup(CTCsPageSetup cTCsPageSetup) {
        generatedSetterHelperImpl(cTCsPageSetup, PROPERTY_QNAME[5], 0, 1);
    }

    public CTCsPageSetup addNewPageSetup() {
        CTCsPageSetup add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[6], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        generatedSetterHelperImpl(cTHeaderFooter, PROPERTY_QNAME[6], 0, 1);
    }

    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTHeaderFooter;
    }

    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTDrawing == null) {
                cTDrawing = null;
            }
        }
        return cTDrawing;
    }

    public void setDrawing(CTDrawing cTDrawing) {
        generatedSetterHelperImpl(cTDrawing, PROPERTY_QNAME[7], 0, 1);
    }

    public CTDrawing addNewDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTDrawing;
    }

    public CTLegacyDrawing getLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[8], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing) {
        generatedSetterHelperImpl(cTLegacyDrawing, PROPERTY_QNAME[8], 0, 1);
    }

    public CTLegacyDrawing addNewLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTLegacyDrawing;
    }

    public void unsetLegacyDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTLegacyDrawing getLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[9], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing) {
        generatedSetterHelperImpl(cTLegacyDrawing, PROPERTY_QNAME[9], 0, 1);
    }

    public CTLegacyDrawing addNewLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTLegacyDrawing = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTLegacyDrawing;
    }

    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTDrawingHF getDrawingHF() {
        CTDrawingHF find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setDrawingHF(CTDrawingHF cTDrawingHF) {
        generatedSetterHelperImpl(cTDrawingHF, PROPERTY_QNAME[10], 0, 1);
    }

    public CTDrawingHF addNewDrawingHF() {
        CTDrawingHF add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return add_element_user;
    }

    public void unsetDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTSheetBackgroundPicture getPicture() {
        CTSheetBackgroundPicture cTSheetBackgroundPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture) get_store().find_element_user(PROPERTY_QNAME[11], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setPicture(CTSheetBackgroundPicture cTSheetBackgroundPicture) {
        generatedSetterHelperImpl(cTSheetBackgroundPicture, PROPERTY_QNAME[11], 0, 1);
    }

    public CTSheetBackgroundPicture addNewPicture() {
        CTSheetBackgroundPicture cTSheetBackgroundPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTSheetBackgroundPicture;
    }

    public void unsetPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTWebPublishItems getWebPublishItems() {
        CTWebPublishItems find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[12], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setWebPublishItems(CTWebPublishItems cTWebPublishItems) {
        generatedSetterHelperImpl(cTWebPublishItems, PROPERTY_QNAME[12], 0, 1);
    }

    public CTWebPublishItems addNewWebPublishItems() {
        CTWebPublishItems add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return add_element_user;
    }

    public void unsetWebPublishItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[13], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[13], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }
}
