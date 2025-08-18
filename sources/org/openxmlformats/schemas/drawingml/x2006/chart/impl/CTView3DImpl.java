package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

public class CTView3DImpl extends XmlComplexContentImpl implements CTView3D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "rotX"), new QName(XSSFRelation.NS_CHART, "hPercent"), new QName(XSSFRelation.NS_CHART, "rotY"), new QName(XSSFRelation.NS_CHART, "depthPercent"), new QName(XSSFRelation.NS_CHART, "rAngAx"), new QName(XSSFRelation.NS_CHART, "perspective"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTView3DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRotX getRotX() {
        CTRotX cTRotX;
        synchronized (monitor()) {
            check_orphaned();
            cTRotX = (CTRotX) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRotX == null) {
                cTRotX = null;
            }
        }
        return cTRotX;
    }

    public boolean isSetRotX() {
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

    public void setRotX(CTRotX cTRotX) {
        generatedSetterHelperImpl(cTRotX, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRotX addNewRotX() {
        CTRotX cTRotX;
        synchronized (monitor()) {
            check_orphaned();
            cTRotX = (CTRotX) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRotX;
    }

    public void unsetRotX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTHPercent getHPercent() {
        CTHPercent cTHPercent;
        synchronized (monitor()) {
            check_orphaned();
            cTHPercent = (CTHPercent) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTHPercent == null) {
                cTHPercent = null;
            }
        }
        return cTHPercent;
    }

    public boolean isSetHPercent() {
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

    public void setHPercent(CTHPercent cTHPercent) {
        generatedSetterHelperImpl(cTHPercent, PROPERTY_QNAME[1], 0, 1);
    }

    public CTHPercent addNewHPercent() {
        CTHPercent cTHPercent;
        synchronized (monitor()) {
            check_orphaned();
            cTHPercent = (CTHPercent) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTHPercent;
    }

    public void unsetHPercent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTRotY getRotY() {
        CTRotY cTRotY;
        synchronized (monitor()) {
            check_orphaned();
            cTRotY = (CTRotY) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTRotY == null) {
                cTRotY = null;
            }
        }
        return cTRotY;
    }

    public boolean isSetRotY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setRotY(CTRotY cTRotY) {
        generatedSetterHelperImpl(cTRotY, PROPERTY_QNAME[2], 0, 1);
    }

    public CTRotY addNewRotY() {
        CTRotY cTRotY;
        synchronized (monitor()) {
            check_orphaned();
            cTRotY = (CTRotY) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTRotY;
    }

    public void unsetRotY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTDepthPercent getDepthPercent() {
        CTDepthPercent cTDepthPercent;
        synchronized (monitor()) {
            check_orphaned();
            cTDepthPercent = (CTDepthPercent) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTDepthPercent == null) {
                cTDepthPercent = null;
            }
        }
        return cTDepthPercent;
    }

    public boolean isSetDepthPercent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setDepthPercent(CTDepthPercent cTDepthPercent) {
        generatedSetterHelperImpl(cTDepthPercent, PROPERTY_QNAME[3], 0, 1);
    }

    public CTDepthPercent addNewDepthPercent() {
        CTDepthPercent cTDepthPercent;
        synchronized (monitor()) {
            check_orphaned();
            cTDepthPercent = (CTDepthPercent) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDepthPercent;
    }

    public void unsetDepthPercent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTBoolean getRAngAx() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetRAngAx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setRAngAx(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[4], 0, 1);
    }

    public CTBoolean addNewRAngAx() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBoolean;
    }

    public void unsetRAngAx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPerspective getPerspective() {
        CTPerspective cTPerspective;
        synchronized (monitor()) {
            check_orphaned();
            cTPerspective = (CTPerspective) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTPerspective == null) {
                cTPerspective = null;
            }
        }
        return cTPerspective;
    }

    public boolean isSetPerspective() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPerspective(CTPerspective cTPerspective) {
        generatedSetterHelperImpl(cTPerspective, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPerspective addNewPerspective() {
        CTPerspective cTPerspective;
        synchronized (monitor()) {
            check_orphaned();
            cTPerspective = (CTPerspective) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPerspective;
    }

    public void unsetPerspective() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[6], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[6], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }
}
