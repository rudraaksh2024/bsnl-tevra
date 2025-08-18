package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdListEntry;

public class CTNotesMasterIdListImpl extends XmlComplexContentImpl implements CTNotesMasterIdList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesMasterId")};
    private static final long serialVersionUID = 1;

    public CTNotesMasterIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTNotesMasterIdListEntry getNotesMasterId() {
        CTNotesMasterIdListEntry cTNotesMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTNotesMasterIdListEntry = (CTNotesMasterIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTNotesMasterIdListEntry == null) {
                cTNotesMasterIdListEntry = null;
            }
        }
        return cTNotesMasterIdListEntry;
    }

    public boolean isSetNotesMasterId() {
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

    public void setNotesMasterId(CTNotesMasterIdListEntry cTNotesMasterIdListEntry) {
        generatedSetterHelperImpl(cTNotesMasterIdListEntry, PROPERTY_QNAME[0], 0, 1);
    }

    public CTNotesMasterIdListEntry addNewNotesMasterId() {
        CTNotesMasterIdListEntry cTNotesMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTNotesMasterIdListEntry = (CTNotesMasterIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNotesMasterIdListEntry;
    }

    public void unsetNotesMasterId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
