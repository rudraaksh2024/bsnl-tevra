package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHeaderFooter;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageSetup;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;

public class CTPrintSettingsImpl extends XmlComplexContentImpl implements CTPrintSettings {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "headerFooter"), new QName(XSSFRelation.NS_CHART, "pageMargins"), new QName(XSSFRelation.NS_CHART, "pageSetup"), new QName(XSSFRelation.NS_CHART, "legacyDrawingHF")};
    private static final long serialVersionUID = 1;

    public CTPrintSettingsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[0], 0);
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
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        generatedSetterHelperImpl(cTHeaderFooter, PROPERTY_QNAME[0], 0, 1);
    }

    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTHeaderFooter;
    }

    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[1], 0);
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
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setPageMargins(CTPageMargins cTPageMargins) {
        generatedSetterHelperImpl(cTPageMargins, PROPERTY_QNAME[1], 0, 1);
    }

    public CTPageMargins addNewPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMargins = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPageMargins;
    }

    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSetup = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[2], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setPageSetup(CTPageSetup cTPageSetup) {
        generatedSetterHelperImpl(cTPageSetup, PROPERTY_QNAME[2], 0, 1);
    }

    public CTPageSetup addNewPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSetup = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPageSetup;
    }

    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTRelId getLegacyDrawingHF() {
        CTRelId cTRelId;
        synchronized (monitor()) {
            check_orphaned();
            cTRelId = (CTRelId) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTRelId == null) {
                cTRelId = null;
            }
        }
        return cTRelId;
    }

    public boolean isSetLegacyDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setLegacyDrawingHF(CTRelId cTRelId) {
        generatedSetterHelperImpl(cTRelId, PROPERTY_QNAME[3], 0, 1);
    }

    public CTRelId addNewLegacyDrawingHF() {
        CTRelId cTRelId;
        synchronized (monitor()) {
            check_orphaned();
            cTRelId = (CTRelId) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTRelId;
    }

    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
