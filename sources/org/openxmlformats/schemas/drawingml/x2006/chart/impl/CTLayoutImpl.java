package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;

public class CTLayoutImpl extends XmlComplexContentImpl implements CTLayout {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "manualLayout"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTLayoutImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTManualLayout getManualLayout() {
        CTManualLayout cTManualLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTManualLayout = (CTManualLayout) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTManualLayout == null) {
                cTManualLayout = null;
            }
        }
        return cTManualLayout;
    }

    public boolean isSetManualLayout() {
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

    public void setManualLayout(CTManualLayout cTManualLayout) {
        generatedSetterHelperImpl(cTManualLayout, PROPERTY_QNAME[0], 0, 1);
    }

    public CTManualLayout addNewManualLayout() {
        CTManualLayout cTManualLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTManualLayout = (CTManualLayout) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTManualLayout;
    }

    public void unsetManualLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
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
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
