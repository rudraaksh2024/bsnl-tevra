package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdListEntry;

public class CTHandoutMasterIdListImpl extends XmlComplexContentImpl implements CTHandoutMasterIdList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "handoutMasterId")};
    private static final long serialVersionUID = 1;

    public CTHandoutMasterIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTHandoutMasterIdListEntry getHandoutMasterId() {
        CTHandoutMasterIdListEntry cTHandoutMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTHandoutMasterIdListEntry = (CTHandoutMasterIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTHandoutMasterIdListEntry == null) {
                cTHandoutMasterIdListEntry = null;
            }
        }
        return cTHandoutMasterIdListEntry;
    }

    public boolean isSetHandoutMasterId() {
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

    public void setHandoutMasterId(CTHandoutMasterIdListEntry cTHandoutMasterIdListEntry) {
        generatedSetterHelperImpl(cTHandoutMasterIdListEntry, PROPERTY_QNAME[0], 0, 1);
    }

    public CTHandoutMasterIdListEntry addNewHandoutMasterId() {
        CTHandoutMasterIdListEntry cTHandoutMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTHandoutMasterIdListEntry = (CTHandoutMasterIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTHandoutMasterIdListEntry;
    }

    public void unsetHandoutMasterId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
