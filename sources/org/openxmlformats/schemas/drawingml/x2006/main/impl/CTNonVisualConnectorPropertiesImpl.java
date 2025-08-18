package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectorLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTNonVisualConnectorPropertiesImpl extends XmlComplexContentImpl implements CTNonVisualConnectorProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "cxnSpLocks"), new QName(XSSFRelation.NS_DRAWINGML, "stCxn"), new QName(XSSFRelation.NS_DRAWINGML, "endCxn"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTNonVisualConnectorPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTConnectorLocking getCxnSpLocks() {
        CTConnectorLocking find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCxnSpLocks() {
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

    public void setCxnSpLocks(CTConnectorLocking cTConnectorLocking) {
        generatedSetterHelperImpl(cTConnectorLocking, PROPERTY_QNAME[0], 0, 1);
    }

    public CTConnectorLocking addNewCxnSpLocks() {
        CTConnectorLocking add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetCxnSpLocks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTConnection getStCxn() {
        CTConnection cTConnection;
        synchronized (monitor()) {
            check_orphaned();
            cTConnection = (CTConnection) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTConnection == null) {
                cTConnection = null;
            }
        }
        return cTConnection;
    }

    public boolean isSetStCxn() {
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

    public void setStCxn(CTConnection cTConnection) {
        generatedSetterHelperImpl(cTConnection, PROPERTY_QNAME[1], 0, 1);
    }

    public CTConnection addNewStCxn() {
        CTConnection cTConnection;
        synchronized (monitor()) {
            check_orphaned();
            cTConnection = (CTConnection) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTConnection;
    }

    public void unsetStCxn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTConnection getEndCxn() {
        CTConnection cTConnection;
        synchronized (monitor()) {
            check_orphaned();
            cTConnection = (CTConnection) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTConnection == null) {
                cTConnection = null;
            }
        }
        return cTConnection;
    }

    public boolean isSetEndCxn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setEndCxn(CTConnection cTConnection) {
        generatedSetterHelperImpl(cTConnection, PROPERTY_QNAME[2], 0, 1);
    }

    public CTConnection addNewEndCxn() {
        CTConnection cTConnection;
        synchronized (monitor()) {
            check_orphaned();
            cTConnection = (CTConnection) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTConnection;
    }

    public void unsetEndCxn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTOfficeArtExtensionList == null) {
                cTOfficeArtExtensionList = null;
            }
        }
        return cTOfficeArtExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[3], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
