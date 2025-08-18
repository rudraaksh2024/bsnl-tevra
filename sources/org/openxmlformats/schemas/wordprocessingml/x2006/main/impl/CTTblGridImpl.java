package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridChange;

public class CTTblGridImpl extends CTTblGridBaseImpl implements CTTblGrid {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblGridChange")};
    private static final long serialVersionUID = 1;

    public CTTblGridImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTblGridChange getTblGridChange() {
        CTTblGridChange find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTblGridChange() {
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

    public void setTblGridChange(CTTblGridChange cTTblGridChange) {
        generatedSetterHelperImpl(cTTblGridChange, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTblGridChange addNewTblGridChange() {
        CTTblGridChange add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetTblGridChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
