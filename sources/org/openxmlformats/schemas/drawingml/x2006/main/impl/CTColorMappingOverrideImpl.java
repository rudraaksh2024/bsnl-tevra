package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmptyElement;

public class CTColorMappingOverrideImpl extends XmlComplexContentImpl implements CTColorMappingOverride {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "masterClrMapping"), new QName(XSSFRelation.NS_DRAWINGML, "overrideClrMapping")};
    private static final long serialVersionUID = 1;

    public CTColorMappingOverrideImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTEmptyElement getMasterClrMapping() {
        CTEmptyElement cTEmptyElement;
        synchronized (monitor()) {
            check_orphaned();
            cTEmptyElement = (CTEmptyElement) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTEmptyElement == null) {
                cTEmptyElement = null;
            }
        }
        return cTEmptyElement;
    }

    public boolean isSetMasterClrMapping() {
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

    public void setMasterClrMapping(CTEmptyElement cTEmptyElement) {
        generatedSetterHelperImpl(cTEmptyElement, PROPERTY_QNAME[0], 0, 1);
    }

    public CTEmptyElement addNewMasterClrMapping() {
        CTEmptyElement cTEmptyElement;
        synchronized (monitor()) {
            check_orphaned();
            cTEmptyElement = (CTEmptyElement) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTEmptyElement;
    }

    public void unsetMasterClrMapping() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTColorMapping getOverrideClrMapping() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            cTColorMapping = (CTColorMapping) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTColorMapping == null) {
                cTColorMapping = null;
            }
        }
        return cTColorMapping;
    }

    public boolean isSetOverrideClrMapping() {
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

    public void setOverrideClrMapping(CTColorMapping cTColorMapping) {
        generatedSetterHelperImpl(cTColorMapping, PROPERTY_QNAME[1], 0, 1);
    }

    public CTColorMapping addNewOverrideClrMapping() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            cTColorMapping = (CTColorMapping) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTColorMapping;
    }

    public void unsetOverrideClrMapping() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
