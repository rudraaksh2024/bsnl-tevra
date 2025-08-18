package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutMode;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutTarget;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;

public class CTManualLayoutImpl extends XmlComplexContentImpl implements CTManualLayout {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "layoutTarget"), new QName(XSSFRelation.NS_CHART, "xMode"), new QName(XSSFRelation.NS_CHART, "yMode"), new QName(XSSFRelation.NS_CHART, "wMode"), new QName(XSSFRelation.NS_CHART, "hMode"), new QName(XSSFRelation.NS_CHART, "x"), new QName(XSSFRelation.NS_CHART, "y"), new QName(XSSFRelation.NS_CHART, "w"), new QName(XSSFRelation.NS_CHART, "h"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTManualLayoutImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTLayoutTarget getLayoutTarget() {
        CTLayoutTarget cTLayoutTarget;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutTarget = (CTLayoutTarget) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTLayoutTarget == null) {
                cTLayoutTarget = null;
            }
        }
        return cTLayoutTarget;
    }

    public boolean isSetLayoutTarget() {
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

    public void setLayoutTarget(CTLayoutTarget cTLayoutTarget) {
        generatedSetterHelperImpl(cTLayoutTarget, PROPERTY_QNAME[0], 0, 1);
    }

    public CTLayoutTarget addNewLayoutTarget() {
        CTLayoutTarget cTLayoutTarget;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutTarget = (CTLayoutTarget) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTLayoutTarget;
    }

    public void unsetLayoutTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTLayoutMode getXMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTLayoutMode == null) {
                cTLayoutMode = null;
            }
        }
        return cTLayoutMode;
    }

    public boolean isSetXMode() {
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

    public void setXMode(CTLayoutMode cTLayoutMode) {
        generatedSetterHelperImpl(cTLayoutMode, PROPERTY_QNAME[1], 0, 1);
    }

    public CTLayoutMode addNewXMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTLayoutMode;
    }

    public void unsetXMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTLayoutMode getYMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTLayoutMode == null) {
                cTLayoutMode = null;
            }
        }
        return cTLayoutMode;
    }

    public boolean isSetYMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setYMode(CTLayoutMode cTLayoutMode) {
        generatedSetterHelperImpl(cTLayoutMode, PROPERTY_QNAME[2], 0, 1);
    }

    public CTLayoutMode addNewYMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTLayoutMode;
    }

    public void unsetYMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTLayoutMode getWMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTLayoutMode == null) {
                cTLayoutMode = null;
            }
        }
        return cTLayoutMode;
    }

    public boolean isSetWMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setWMode(CTLayoutMode cTLayoutMode) {
        generatedSetterHelperImpl(cTLayoutMode, PROPERTY_QNAME[3], 0, 1);
    }

    public CTLayoutMode addNewWMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTLayoutMode;
    }

    public void unsetWMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTLayoutMode getHMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTLayoutMode == null) {
                cTLayoutMode = null;
            }
        }
        return cTLayoutMode;
    }

    public boolean isSetHMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setHMode(CTLayoutMode cTLayoutMode) {
        generatedSetterHelperImpl(cTLayoutMode, PROPERTY_QNAME[4], 0, 1);
    }

    public CTLayoutMode addNewHMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            cTLayoutMode = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTLayoutMode;
    }

    public void unsetHMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTDouble getX() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setX(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[5], 0, 1);
    }

    public CTDouble addNewX() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTDouble;
    }

    public void unsetX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTDouble getY() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setY(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[6], 0, 1);
    }

    public CTDouble addNewY() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTDouble;
    }

    public void unsetY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTDouble getW() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setW(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[7], 0, 1);
    }

    public CTDouble addNewW() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTDouble;
    }

    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTDouble getH() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setH(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[8], 0, 1);
    }

    public CTDouble addNewH() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTDouble;
    }

    public void unsetH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[9], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[9], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }
}
