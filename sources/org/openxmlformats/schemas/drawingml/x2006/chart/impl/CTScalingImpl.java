package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;

public class CTScalingImpl extends XmlComplexContentImpl implements CTScaling {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "logBase"), new QName(XSSFRelation.NS_CHART, "orientation"), new QName(XSSFRelation.NS_CHART, "max"), new QName(XSSFRelation.NS_CHART, "min"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTScalingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTLogBase getLogBase() {
        CTLogBase cTLogBase;
        synchronized (monitor()) {
            check_orphaned();
            cTLogBase = (CTLogBase) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTLogBase == null) {
                cTLogBase = null;
            }
        }
        return cTLogBase;
    }

    public boolean isSetLogBase() {
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

    public void setLogBase(CTLogBase cTLogBase) {
        generatedSetterHelperImpl(cTLogBase, PROPERTY_QNAME[0], 0, 1);
    }

    public CTLogBase addNewLogBase() {
        CTLogBase cTLogBase;
        synchronized (monitor()) {
            check_orphaned();
            cTLogBase = (CTLogBase) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTLogBase;
    }

    public void unsetLogBase() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTOrientation getOrientation() {
        CTOrientation cTOrientation;
        synchronized (monitor()) {
            check_orphaned();
            cTOrientation = (CTOrientation) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTOrientation == null) {
                cTOrientation = null;
            }
        }
        return cTOrientation;
    }

    public boolean isSetOrientation() {
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

    public void setOrientation(CTOrientation cTOrientation) {
        generatedSetterHelperImpl(cTOrientation, PROPERTY_QNAME[1], 0, 1);
    }

    public CTOrientation addNewOrientation() {
        CTOrientation cTOrientation;
        synchronized (monitor()) {
            check_orphaned();
            cTOrientation = (CTOrientation) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTOrientation;
    }

    public void unsetOrientation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTDouble getMax() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetMax() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setMax(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[2], 0, 1);
    }

    public CTDouble addNewMax() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTDouble;
    }

    public void unsetMax() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTDouble getMin() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetMin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setMin(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[3], 0, 1);
    }

    public CTDouble addNewMin() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDouble;
    }

    public void unsetMin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[4], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
